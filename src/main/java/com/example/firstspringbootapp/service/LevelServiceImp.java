package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Level;
import com.example.firstspringbootapp.exception.LevelNotFoundException;
import com.example.firstspringbootapp.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImp implements LevelService {

    private LevelRepository levelRepository;

    @Autowired
    public LevelServiceImp(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level findById(Integer id) {
        return levelRepository.findById(id).orElseThrow(() -> new LevelNotFoundException("Level with id" + id + "not found in database"));
    }

    @Override
    public List<Level> findAll() {
        List<Level> levels = levelRepository.findAll();
        if (levels.isEmpty()) throw new LevelNotFoundException("Levels is not found in database");
        return levels;
    }

    @Override
    public void save(Level level) {
        levelRepository.save(level);
    }

    @Override
    public void update(Level level, Integer id) {
        Level levelById = findById(id);
        if (levelById == null) throw new LevelNotFoundException("Level with id" + id + "not found in database");
        level.setName(level.getName());
        levelRepository.save(levelById);
    }

    @Override
    public void delete(Integer id) {
        Level levelById = findById(id);
        if (levelById == null) throw new LevelNotFoundException("Level with id" + id + "not found in database");
        levelRepository.delete(levelById);
    }
}