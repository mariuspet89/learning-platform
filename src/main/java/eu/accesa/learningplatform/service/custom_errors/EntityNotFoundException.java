package eu.accesa.learningplatform.service.custom_errors;

public class EntityNotFoundException extends Exception {

    private final String entityName;
    private final String entityField;
    private final String entityFieldValue;

    public EntityNotFoundException(String entityName, String entityField, String entityFieldValue) {
        super(entityName + " with " + entityField + "=" + entityFieldValue + " not found");
        this.entityField = entityField;
        this.entityName = entityName;
        this.entityFieldValue = entityFieldValue;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getEntityField() {
        return entityField;
    }

    public String getEntityFieldValue() {
        return entityFieldValue;
    }
}
