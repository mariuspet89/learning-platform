package eu.accesa.learningplatform.model.entity;

public enum QuizItemTypeEnum {
    SINGLE_CHOICE("SINGLE CHOICE"), MULTIPLE_CHOICE("MULTIPLE CHOICE"), OPEN_ANSWER("OPEN ANSWER");
    private final String code;

    private QuizItemTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
