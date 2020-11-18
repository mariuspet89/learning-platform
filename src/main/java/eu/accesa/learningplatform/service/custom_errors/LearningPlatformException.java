package eu.accesa.learningplatform.service.custom_errors;

public class LearningPlatformException extends RuntimeException {

    public LearningPlatformException(String entityName, String entityField, String entityFieldValue) {
        super(entityName + " with " + entityField + "=" + entityFieldValue + " not found");
    }
}
