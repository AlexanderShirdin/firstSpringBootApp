package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Profile;
import com.example.firstspringbootapp.exception.AnswerNotFoundException;
import com.example.firstspringbootapp.exception.ProfileNotFoundException;
import com.example.firstspringbootapp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImp implements ProfileService {

    private ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImp(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile findById(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException("Profile with id" + id + "not found in database"));
    }

    @Override
    public List<Profile> findAll() {
        List<Profile> profiles = profileRepository.findAll();
        if (profiles.isEmpty()) throw new AnswerNotFoundException("Profiles is not found in database");
        return profiles;
    }

    @Override
    public void save(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public void update(Profile newProfile, Integer id) {
        Profile oldProfile = findById(id);
        oldProfile.setName(newProfile.getName())
                .setQuestions(newProfile.getQuestions());
        profileRepository.save(oldProfile);
    }

    @Override
    public void delete(Integer id) {
        Profile profileById = findById(id);
        profileRepository.delete(profileById);
    }
}