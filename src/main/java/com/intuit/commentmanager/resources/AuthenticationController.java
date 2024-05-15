package com.intuit.commentmanager.resources;

import com.intuit.commentmanager.dto.inbound.LoginUserDto;
import com.intuit.commentmanager.entity.Profile;
import com.intuit.commentmanager.repository.ProfileRepository;
import com.intuit.commentmanager.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginUserDto loginUserDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getEmail(),
                        loginUserDto.getPassword()
                )
        );
        Profile authenticatedUser= profileRepository.findByEmail(loginUserDto.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(authenticatedUser);
        return ResponseEntity.ok(jwtToken);
    }
}
