package com.example.firstspringbootapp.repository;

import com.example.firstspringbootapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findQuestionByNameAndLevel(String name, String level);
    Optional<Question> findQuestionByName(String name);
}