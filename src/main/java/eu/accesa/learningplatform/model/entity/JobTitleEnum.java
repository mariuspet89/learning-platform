package eu.accesa.learningplatform.model.entity;

public enum JobTitleEnum {
    JAVA_DEVELOPER("Java Developer"), NET_DEVELOPER(".NET DEVELOPER"),
    DEVOPS_ENGINER("DevOps Engineer"), BUSINESS_ANALIST("Business Analyst"),
    UI_UXDESIGNER("UI/UX Designer"), TEST_ENGINEER("Test Engineer"), PROJECT_DELIVERY_MANAGER("Project Delivery Manager");

    private final String s;

    JobTitleEnum(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}


