package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "ANSWER")
public class Answer {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=AUTO)
    private Long id;

    @Column(name = "ANSWER_TEXT")
    private String answer;

    @Column(name = "CORRECT_ANSWER")
    private boolean isCorrect;

    @ManyToOne()
    @JoinColumn(name = "QUIZITEM_ID")
    private QuizItem quizItem;

    @OneToOne(mappedBy = "answer")
    private UserAnswer userAnswer;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public QuizItem getQuizItem() {
        return quizItem;
    }

    public void setQuizItem(QuizItem quizItem) {
        this.quizItem = quizItem;
    }

    public UserAnswer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(UserAnswer userAnswer) {
        this.userAnswer = userAnswer;
    }
}
