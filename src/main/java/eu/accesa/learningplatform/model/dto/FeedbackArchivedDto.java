package eu.accesa.learningplatform.model.dto;

public class FeedbackArchivedDto {

    private Long id;

    private Long FeedbackEntityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeedbackEntityId() {
        return FeedbackEntityId;
    }

    public void setFeedbackEntityId(Long feedbackEntityId) {
        FeedbackEntityId = feedbackEntityId;
    }
}
