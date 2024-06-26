package com.intuit.commentmanager.resources;

import com.intuit.commentmanager.entity.Profile;
import com.intuit.commentmanager.exceptions.InvalidInputException;
import com.intuit.commentmanager.repository.ProfileRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    @Operation(summary = "Api to create a new user")
    public ResponseEntity<Profile> saveProfile(@RequestBody Profile profile) {
        profile.setCreatedDt(new Date());
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        Profile saveProfile = profileRepository.save(profile);
        return new ResponseEntity<>(saveProfile, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Api to list all the profiles")
    public ResponseEntity<List<Profile>> getProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Api to get the profile by id")
    public Profile getProfileById(@PathVariable long id) {
        Optional<Profile> profile = profileRepository.findById(id);
        if(profile.isEmpty()) {
            throw new InvalidInputException("No profile found with the given id");
        }
        return profile.get();
    }

}
