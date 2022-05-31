package com.example.firstspringbootapp.repository;

import com.example.firstspringbootapp.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
