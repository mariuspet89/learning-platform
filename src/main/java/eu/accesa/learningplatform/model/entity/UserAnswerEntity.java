package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "USER_ANSWER")
public class UserAnswerEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "USER_ANSWER_TEXT")
    private String userAnswerText;

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "ANSWER_ID")
    private AnswerEntity answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAnswerText() {
        return userAnswerText;
    }

    public void setUserAnswerText(String userAnswerText) {
        this.userAnswerText = userAnswerText;
    }

    public AnswerEntity getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerEntity answer) {
        this.answer = answer;
    }
}
