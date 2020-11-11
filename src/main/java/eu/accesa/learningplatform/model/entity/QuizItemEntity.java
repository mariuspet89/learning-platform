package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "QUIZ_ITEM")
public class QuizItemEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=AUTO)
    private Long id;


    @Column(name = "QUESTION")
    private String question;


    @ManyToOne()
    @JoinColumn(name = "QUIZ_ITEM_TYPE_ID")
    private QuizItemTypeEntity quizItemTypeEntity;


    @OneToMany(mappedBy = "quizItem")
    Set<AnswerEntity> answerSet;

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

    public QuizItemTypeEntity getQuizItemType() {
        return quizItemTypeEntity;
    }

    public void setQuizItemType(QuizItemTypeEntity quizItemTypeEntity) {
        this.quizItemTypeEntity = quizItemTypeEntity;
    }

    public Set<AnswerEntity> getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(Set<AnswerEntity> answerSet) {
        this.answerSet = answerSet;
    }
}
