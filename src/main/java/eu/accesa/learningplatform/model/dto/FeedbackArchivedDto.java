package eu.accesa.learningplatform.model.dto;

public class FeedbackArchivedDto {

    private Long id;

    private Long FeedbackEntityID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeedbackEntityID() {
        return FeedbackEntityID;
    }

    public void setFeedbackEntityID(Long feedbackEntityID) {
        FeedbackEntityID = feedbackEntityID;
    }
}
