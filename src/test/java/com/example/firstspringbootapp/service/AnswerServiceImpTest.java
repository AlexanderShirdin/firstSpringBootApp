package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Answer;
import com.example.firstspringbootapp.exception.AnswerNotFoundException;
import com.example.firstspringbootapp.repository.AnswerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AnswerServiceImpTest {

    @MockBean
    private AnswerRepository answerRepository;
    @Autowired
    private AnswerServiceImp answerService;

    @Test
    void AnswerListEmptyIfIsNotAdded() {
        Mockito.doReturn(new ArrayList<>()).when(answerRepository).findAll();
        assertThrows(AnswerNotFoundException.class, () -> answerService.findAll());
        List<Answer> answerList = answerService.findAll();
        assertThat(answerList).isEmpty();
    }

    @Test
    void AnswerIsNullIfIdNotFoundInDb() {

    }
}