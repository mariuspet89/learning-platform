package eu.accesa.learningplatform.model.entity;

public enum ApplicationStatusEnum {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");
    private final String status;

    ApplicationStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static ApplicationStatusEnum of(String status) {
        for (ApplicationStatusEnum applicationStatusEnum : ApplicationStatusEnum.values()) {
            if (applicationStatusEnum.getStatus().equalsIgnoreCase(status)) {
                return applicationStatusEnum;
            }
        }
        throw new IllegalArgumentException("Unknown status value:" + status);
    }
}
