package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer findById(Integer id);

    List<Answer> findAll();

    void save(Answer answer);

    void update(Answer answer, Integer id);

    void delete(Integer id);
}