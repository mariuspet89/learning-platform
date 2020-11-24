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

    @OneToOne
    @JoinColumn(name = "FEEDBACK_ID")
    private FeedbackEntity feedbackEntity;

    public FeedbackEntity getFeedbackEntity() {
        return feedbackEntity;
    }

    public void setFeedbackEntity(FeedbackEntity feedbackEntity) {
        this.feedbackEntity = feedbackEntity;
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
                Objects.equals(feedbackEntity, that.feedbackEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feedbackEntity);
    }
}