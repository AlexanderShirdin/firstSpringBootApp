package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Answer;
import com.example.firstspringbootapp.entity.Question;
import com.example.firstspringbootapp.exception.AnswerQuantityMismatchException;
import com.example.firstspringbootapp.exception.QuestionExistException;
import com.example.firstspringbootapp.exception.QuestionNotFoundException;
import com.example.firstspringbootapp.repository.QuestionRepository;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        checkCountCorrectAnswer(question);
        checkQuestionExistByName(question.getName());
        List<Answer> answers = question.getAnswers();
        answers.forEach(a -> a.setQuestion(question));
        question.setAnswers(answers);
        questionRepository.save(question);
    }

    @Override
    public void update(Question newQuestion, Integer id) {
        checkCountCorrectAnswer(newQuestion);
        checkQuestionExistByName(newQuestion.getName());
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

    public void checkCountCorrectAnswer(Question question){
        long countAnswerCorrect = question.getAnswers()
                .stream()
                .filter(Answer::getCorrect)
                .count();
        if (question.getNumOfCorrect() != countAnswerCorrect) {
            throw new AnswerQuantityMismatchException("Quantity add true answers, is not correct");
        }
    }
    public void checkQuestionExistByName(String questionName){
        boolean isPresent = questionRepository.findQuestionByName(questionName).isPresent();
        if (isPresent) throw new QuestionNotFoundException("Question with name " + questionName + " exist");
    }
}