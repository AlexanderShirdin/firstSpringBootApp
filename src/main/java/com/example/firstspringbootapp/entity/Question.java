package com.example.firstspringbootapp.entity;

import com.example.firstspringbootapp.annotation.Unique;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Questions")
@Data
@Accessors(chain = true)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Unique
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer numOfCorrect;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "question", cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<Answer> answers;

    public void addAnswerToQuestion(Answer answer) {
        if (answers == null) answers = new ArrayList<>();
        answer.setQuestion(this);
        answers.add(answer);
    }
}