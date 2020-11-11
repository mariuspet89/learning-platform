package eu.accesa.learningplatform.model.entity;

public enum ApplicationStatusEnum {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");
    private final String status;

    ApplicationStatusEnum(String status){
        this.status  = status;
    }

    @Override
    public String toString() {
        return "ApplicationStatusEnum{" +
                "status='" + status + '\'' +
                '}';
    }
}
