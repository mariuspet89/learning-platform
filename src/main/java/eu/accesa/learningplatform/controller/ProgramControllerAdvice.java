package eu.accesa.learningplatform.controller;

import eu.accesa.learningplatform.service.custom_errors.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class ProgramControllerAdvice {

    private final Logger logger = Logger.getLogger(ProgramControllerAdvice.class.getName());

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException exception) {
        String entityName = exception.getEntityName();
        String entityField = exception.getEntityField();
        String entityFieldValue = exception.getEntityFieldValue();
        String message = "Entity " + " with field " + entityField + "=" + entityFieldValue + " not found!";
        logger.log(Level.WARNING, message);
        return new ResponseEntity(message, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException exception) {
        logger.log(Level.WARNING, exception.getMessage());
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        logger.log(Level.WARNING, exception.getMessage());
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity handleIllegalStateException(IllegalStateException exception) {
        logger.log(Level.WARNING, exception.getMessage());
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
