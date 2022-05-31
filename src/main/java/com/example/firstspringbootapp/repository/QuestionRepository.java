package com.example.firstspringbootapp.repository;

import com.example.firstspringbootapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
//    List<Question> findQuestionByNameAndLevel(String name, String level);
}