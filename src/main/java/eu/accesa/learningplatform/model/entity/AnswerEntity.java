package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "ANSWER")
public class AnswerEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=AUTO)
    private Long id;

    @Column(name = "ANSWER_TEXT")
    private String answerText;

    @Column(name = "CORRECT_ANSWER")
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
}
