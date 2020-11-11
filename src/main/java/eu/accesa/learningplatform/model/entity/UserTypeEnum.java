package eu.accesa.learningplatform.model.entity;

public enum UserTypeEnum {

    ADMIN("Admin"),
    TRAINEE("Trainee"),
    TRAINER("Trainer");

    private final String userType;

    UserTypeEnum(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "UserTypeEnum{" +
                "userType='" + userType + '\'' +
                '}';
    }
}