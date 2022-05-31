package com.example.firstspringbootapp.repository;

import com.example.firstspringbootapp.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
