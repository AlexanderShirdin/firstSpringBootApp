package com.example.firstspringbootapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer numOfCorrect;

    @OneToMany(mappedBy = "questions", cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    private List<Answer> answers;
}


//    @ManyToOne(cascade = {
//            CascadeType.DETACH,
//            CascadeType.PERSIST,
//            CascadeType.MERGE,
//            CascadeType.REFRESH
//    })
//    private Profile profile;
//
//    @ManyToOne(cascade = {
//            CascadeType.DETACH,
//            CascadeType.PERSIST,
//            CascadeType.MERGE,
//            CascadeType.REFRESH
//    })
//    private Level level;