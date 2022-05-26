package com.example.firstspringbootapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "levels")
@Data
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}



//    @ManyToMany(mappedBy = "levels")
//    private List<Profile> profiles;