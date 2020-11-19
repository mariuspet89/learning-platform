package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FEEDBACK_ARCHIVED")
public class FeedbackArchivedEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FEEDBACK_ID")
    private Long FeedbackEntityId;

    @OneToOne(mappedBy = "feedbackArchivedEntity")
    private FeedbackEntity feedbackEntity;

    public Long getFeedbackEntityID() {
        return FeedbackEntityId;
    }

    public void setFeedbackEntityID(Long feedbackEntityID) {
        FeedbackEntityId = feedbackEntityID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackArchivedEntity that = (FeedbackArchivedEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(FeedbackEntityId, that.FeedbackEntityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, FeedbackEntityId);
    }
}
