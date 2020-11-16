package eu.accesa.learningplatform.model.entity;

public enum QuizItemTypeEnum {
    SINGLE_CHOICE("SINGLE CHOICE"), MULTIPLE_CHOICE("MULTIPLE CHOICE"), OPEN_ANSWER("OPEN ANSWER");
    private final String choice;

    QuizItemTypeEnum(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }

    public static QuizItemTypeEnum of(String choice) {
        for (QuizItemTypeEnum quizItemTypeEnum : QuizItemTypeEnum.values()) {
            if (quizItemTypeEnum.getChoice().equalsIgnoreCase(choice)) {
                return quizItemTypeEnum;
            }
        }
        throw new IllegalArgumentException("Unknown choice value:" + choice);
    }
}
