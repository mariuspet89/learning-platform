package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "QUIZ_ITEM")
public class QuizItem {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=AUTO)
    private Long id;


    @Column(name = "QUESTION")
    private String question;


    @ManyToOne()
    @JoinColumn(name = "QUIZ_ITEM_TYPE_ID")
    private QuizItemType quizItemType;


    @OneToMany(mappedBy = "quizItem")
    Set<Answer> answerSet;

    // todo - relation needs to be mapped in Quiz entity also
//    @ManyToOne()
//    @JoinColumn(name = "QUIZ_ID")
//    private Quiz quiz;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuizItemType getQuizItemType() {
        return quizItemType;
    }

    public void setQuizItemType(QuizItemType quizItemType) {
        this.quizItemType = quizItemType;
    }

    public Set<Answer> getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(Set<Answer> answerSet) {
        this.answerSet = answerSet;
    }
}
