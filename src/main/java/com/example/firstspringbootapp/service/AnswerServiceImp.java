package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Answer;
import com.example.firstspringbootapp.exception.AnswerNotFoundException;
import com.example.firstspringbootapp.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImp implements AnswerService {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImp(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer findById(Integer id) {
        return answerRepository.findById(id).orElseThrow(() -> new AnswerNotFoundException("Answer with id" + id + "not found in database"));
    }

    @Override
    public List<Answer> findAll() {
        List<Answer> answers = answerRepository.findAll();
        if (answers.isEmpty()) throw new AnswerNotFoundException("Answers is not found in database");
        return answers;
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void update(Answer answer, Integer id) {
        Answer answerById = findById(id);
        if (answerById == null) throw new AnswerNotFoundException("Answer with id" + id + "not found in database");
        answer.setName(answer.getName())
                .setCorrect(answer.getCorrect())
                .setQuestion(answer.getQuestion());
        answerRepository.save(answerById);
    }

    @Override
    public void delete(Integer id) {
        Answer answerById = findById(id);
        if (answerById == null) throw new AnswerNotFoundException("Answer with id" + id + "not found in database");
        answerRepository.delete(answerById);
    }
}