package eu.accesa.learningplatform.model.entity;

public enum CompetenceAreaEnum {
    PROJECT_MANAGEMENT("Project Management"),
    PRODUCT_DESIGN("Product Design"),
    SOFTWARE_ARCHITECTURE("Software Architecture"),
    QUALITY_ASSURANCE("Quality Assurance"),
    SYSTEM_OPERATIONS("System Operations"),
    JAVA_TECHNOLOGY("Java Technology"),
    WEB_AND_MOBILE("Web & mobile"),
    DOT_NET_TECHNOLOGY(".NET Technology");
    private final String name;

    CompetenceAreaEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
