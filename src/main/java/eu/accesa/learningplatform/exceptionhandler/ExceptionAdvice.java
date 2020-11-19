package eu.accesa.learningplatform.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(LearningPlatformException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    String entityNotFoundExceptionHandler
            (LearningPlatformException learningPlatformException) {

        return learningPlatformException.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    List<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();

        List<String> body = new ArrayList<>();

        if (!allErrors.isEmpty()) {
            for (ObjectError o : allErrors) {
                body.add(o.getDefaultMessage());
            }
        }
        return body;
    }

}
