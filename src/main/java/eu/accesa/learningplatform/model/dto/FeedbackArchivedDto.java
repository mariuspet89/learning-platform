package eu.accesa.learningplatform.model.dto;

public class FeedbackArchivedDto {

    private Long id;

    private Long feedbackEntityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeedbackEntityId() {
        return feedbackEntityId;
    }

    public void setFeedbackEntityId(Long feedbackEntityId) {
        this.feedbackEntityId = feedbackEntityId;
    }
}
