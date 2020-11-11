package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "QUIZ_ITEM_TYPE")
public class QuizItemTypeEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=AUTO)
    private Long id;


    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private QuizItemTypeEnum type;


    @OneToMany(mappedBy = "quizItemType")
    Set<QuizItemEntity> quizItemEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuizItemTypeEnum getType() {
        return type;
    }

    public void setType(QuizItemTypeEnum type) {
        this.type = type;
    }
}
