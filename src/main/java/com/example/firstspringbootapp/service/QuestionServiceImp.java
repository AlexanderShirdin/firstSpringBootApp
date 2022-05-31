package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Question;
import com.example.firstspringbootapp.exception.QuestionNotFoundException;
import com.example.firstspringbootapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImp(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question findById(Integer id) {
        return questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException("Question with id" + id + "not found in database"));
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) throw new QuestionNotFoundException("Questions is not found in database");
        return questions;
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void update(Question question, Integer id) {
        Question questionById = findById(id);
        if (questionById == null) throw new QuestionNotFoundException("Question with id" + id + "not found in database");
        question.setName(question.getName())
                .setNumOfCorrect(question.getNumOfCorrect())
                .setProfile(question.getProfile())
                .setLevel(question.getLevel());
        questionRepository.save(questionById);
    }

    @Override
    public void delete(Integer id) {
        Question questionById = findById(id);
        if (questionById == null) throw new QuestionNotFoundException("Question with id" + id + "not found in database");
        questionRepository.delete(questionById);
    }
}