package eu.accesa.learningplatform.model.entity;

public enum JobTitleEnum {
    JAVA_DEVELOPER("Java Developer"), NET_DEVELOPER(".NET DEVELOPER"),
    DEVOPS_ENGINER("DevOps Engineer"), BUSINESS_ANALIST("Business Analyst"),
    UI_UXDESIGNER("UI/UX Designer"), TEST_ENGINEER("Test Engineer"), PROJECT_DELIVERY_MANAGER("Project Delivery Manager");

    private final String name;

    JobTitleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static JobTitleEnum of(String name) {
        for (JobTitleEnum jobTitleEnum : JobTitleEnum.values()) {
            if (jobTitleEnum.getName().equalsIgnoreCase(name)) {
                return jobTitleEnum;
            }
        }
        throw new IllegalArgumentException("Unknown name value:" + name);
    }
}
