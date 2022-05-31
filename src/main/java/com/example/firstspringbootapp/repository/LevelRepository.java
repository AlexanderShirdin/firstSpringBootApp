package com.example.firstspringbootapp.repository;

import com.example.firstspringbootapp.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Integer> {
}
