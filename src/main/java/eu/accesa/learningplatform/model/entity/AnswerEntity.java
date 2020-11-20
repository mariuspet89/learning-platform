package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ANSWER")
public class AnswerEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "ANSWER_TEXT", nullable = false)
    private String answerText;
    @Column(name = "CORRECT_ANSWER", nullable = false)
    private boolean isCorrect;

    @ManyToOne()
    @JoinColumn(name = "QUIZ_ITEM_ID")
    private QuizItemEntity quizItemEntity;

    @OneToOne(mappedBy = "answer")
    private UserAnswerEntity userAnswerEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public QuizItemEntity getQuizItem() {
        return quizItemEntity;
    }

    public void setQuizItem(QuizItemEntity quizItemEntity) {
        this.quizItemEntity = quizItemEntity;
    }

    public UserAnswerEntity getUserAnswer() {
        return userAnswerEntity;
    }

    public void setUserAnswer(UserAnswerEntity userAnswerEntity) {
        this.userAnswerEntity = userAnswerEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerEntity that = (AnswerEntity) o;
        return isCorrect == that.isCorrect &&
                Objects.equals(id, that.id) &&
                Objects.equals(answerText, that.answerText) &&
                Objects.equals(quizItemEntity, that.quizItemEntity) &&
                Objects.equals(userAnswerEntity, that.userAnswerEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answerText, isCorrect, quizItemEntity, userAnswerEntity);
    }
}
