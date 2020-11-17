package eu.accesa.learningplatform.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(LearningPlatformException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    String entityNotFoundExceptionHandler
            (LearningPlatformException learningPlatformException) {
        return learningPlatformException.getMessage();
    }

    @ExceptionHandler(EmptyFieldsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    String emptyFieldsException
            (EmptyFieldsException emptyFieldsException) {
        return emptyFieldsException.getMessage();
    }

}
