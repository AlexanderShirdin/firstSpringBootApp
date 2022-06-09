package com.example.firstspringbootapp;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FirstSpringBootAppApplicationTests {

    static String hello = "";
    String world = "";
    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll ");
        hello = "Hello";
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach ");
        world = "World";
    }

    @Test
    void contextLoads() {
        assertThat(true).isTrue();
        hello = "Hello1";
        System.out.println("Test # 1");
    }

    @Test
    void contextLoads2() {
        assertThat(true).isTrue();
        assertThat(hello).isEqualTo("Hello");
        System.out.println("Test # 2");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("afterEach ");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("afterAll ");
    }
}