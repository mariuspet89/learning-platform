package eu.accesa.learningplatform.service.custom_errors;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String entityName, String entityField) {
        super(entityName + " with " + entityField + " not found");
    }
}
