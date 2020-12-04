package eu.accesa.learningplatform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.accesa.learningplatform.model.dto.LessonDto;
import eu.accesa.learningplatform.security.LdapAuthenticationProvider;
import eu.accesa.learningplatform.service.LessonService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static eu.accesa.learningplatform.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LessonController.class)
@RunWith(SpringRunner.class)
public class LessonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LessonService lessonService;
    @MockBean
    private LdapAuthenticationProvider ldapAuthenticationProvider;
    @Captor
    private ArgumentCaptor<LessonDto> lessonDtoDbCapture;

    @Test
    @WithMockUser
    public void save() throws Exception {
        LessonDto lessonToBeSaved = testUtilsLessonDtoNoId("java", 1.0, 1l);
        when(lessonService.createLesson(any())).thenReturn(lessonToBeSaved);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String lessonJson = objectMapper.writeValueAsString(lessonToBeSaved);
        final ResultActions resultActions = mockMvc.perform(post("http://localhost:8080/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lessonJson));
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("java"))
                .andExpect(jsonPath("$.duration").value(1l))
                .andExpect(jsonPath("$.courseId").value(1l));
        verify(lessonService).createLesson(lessonDtoDbCapture.capture());
        assertThat(lessonDtoDbCapture.getValue().getName()).isEqualTo("java");
        assertThat(lessonDtoDbCapture.getValue().getDuration()).isEqualTo(1l);
        assertThat(lessonDtoDbCapture.getValue().getCourseId()).isEqualTo(1l);
        verify(lessonService).createLesson(lessonToBeSaved);
        verifyNoMoreInteractions(lessonService);
    }

    @Test
    @WithMockUser
    public void getLessonById() throws Exception {
        LessonDto foundLesson = testUtilsLessonDto(1l, "java", 2.0, 1l);
        when(lessonService.getLessonById(1l)).thenReturn(foundLesson);
        mockMvc.perform(get("http://localhost:8080/lessons/{id}", 1l))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(foundLesson.getId()))
                .andExpect(jsonPath("$.name").value(foundLesson.getName()))
                .andExpect(jsonPath("$.duration").value(foundLesson.getDuration()))
                .andExpect(jsonPath("$.courseId").value(foundLesson.getCourseId()));
        verify(lessonService, times(1)).getLessonById(1l);
        verifyNoMoreInteractions(lessonService);
    }

    @Test
    @WithMockUser
    public void getLessonsByCourseId() throws Exception {
        Long courseId = 1l;
        when(lessonService.getLessonsByCourse(courseId)).thenReturn(testUtilsLessonDtoList());
        final List<LessonDto> lessonDtoListFound = lessonService.getLessonsByCourse(courseId);
        mockMvc.perform(get("http://localhost:8080/lessons/course/{id}", courseId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].name").value(containsInAnyOrder("java", "java basic")))
                .andExpect(jsonPath("$[*].duration").isNotEmpty())
                .andExpect(jsonPath("$[*].duration").value(containsInAnyOrder(1.0, 2.0)))
                .andExpect(jsonPath("$[*].courseId").value(containsInAnyOrder(1, 1)));
        verify(lessonService, times(2)).getLessonsByCourse(courseId);
        verifyNoMoreInteractions(lessonService);
    }

    @Test
    @WithMockUser
    public void updateLesson() throws Exception {
        LessonDto lessonFound = testUtilsLessonDto(1l, "java", 2.0, 1l);
        LessonDto lessonToPut = testUtilsLessonDto(2l, "java to put", 3.0, 2l);
        Long id = 1l;
        when(lessonService.getLessonById(id)).thenReturn(lessonFound);
        when(lessonService.updateLesson(lessonFound)).thenReturn(lessonToPut);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String lessonToPutJson = objectMapper.writeValueAsString(lessonToPut);
        final ResultActions resultActions = mockMvc.perform(put("http://localhost:8080/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lessonToPutJson))
                .andExpect(status().isOk());
        verify(lessonService).updateLesson(lessonDtoDbCapture.capture());
        assertThat(lessonDtoDbCapture.getValue().getId()).isEqualTo(2l);
        assertThat(lessonDtoDbCapture.getValue().getName()).isEqualTo("java to put");
        assertThat(lessonDtoDbCapture.getValue().getDuration()).isEqualTo(3.0);
        assertThat(lessonDtoDbCapture.getValue().getCourseId()).isEqualTo(2l);
        verifyNoMoreInteractions(lessonService);
    }

    @Test
    @WithMockUser
    public void deleteById() throws Exception {
        LessonDto lessonToDelete = testUtilsLessonDto(1l, "java", 1.0, 1l);
        Long id = 1l;
        when(lessonService.getLessonById(id)).thenReturn(lessonToDelete);
        doNothing().when(lessonService).deleteLesson(id);
        mockMvc.perform(delete("http://localhost:8080/lessons/{id}", id))
                .andExpect(status().isOk());
        verify(lessonService, times(1)).deleteLesson(id);
        verifyNoMoreInteractions(lessonService);
    }
}