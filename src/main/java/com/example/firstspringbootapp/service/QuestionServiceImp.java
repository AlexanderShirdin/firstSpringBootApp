package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Answer;
import com.example.firstspringbootapp.entity.Question;
import com.example.firstspringbootapp.exception.AnswerQuantityMismatchException;
import com.example.firstspringbootapp.exception.QuestionNotFoundException;
import com.example.firstspringbootapp.repository.QuestionRepository;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Answer> answers = question.getAnswers();

        if (question.getNumOfCorrect() != question.getAnswers().stream().filter(Answer::getCorrect).count())
            throw new AnswerQuantityMismatchException("Quantity add true answers, is not correct");

        answers.forEach(a -> a.setQuestion(question));
        question.setAnswers(answers);
//        question.getAnswers().forEach(question::addAnswerToQuestion);
        questionRepository.save(question);
    }

    @Override
    public void update(Question newQuestion, Integer id) {
        Question oldQuestion = findById(id);
        oldQuestion.setName(newQuestion.getName())
                .setNumOfCorrect(newQuestion.getNumOfCorrect())
                .setProfile(newQuestion.getProfile())
                .setLevel(newQuestion.getLevel());
        questionRepository.save(oldQuestion);
    }

    @Override
    public void delete(Integer id) {
        Question questionById = findById(id);
        questionRepository.delete(questionById);
    }
}