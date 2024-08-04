package com.example.techeazy.authentication;

import com.example.techeazy.config.jwtService;
import com.example.techeazy.entities.Student;
import com.example.techeazy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentRepository userrepository;

    @Autowired
    private jwtService jwtservice;


    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public AuthenticationResponse register(Student request){
        var user = new Student();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userrepository.save(user);
        String token = jwtservice.generateToken(user, generateExtraClaims(user));
        return  new AuthenticationResponse(token);
    }


    public AuthenticationResponse login (AuthenticationRequest authenticationRequest){

        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUserName(),authenticationRequest.getPassword()
        );

        authenticationManager.authenticate(authToken);
        Student user= userrepository.findByUsername(authenticationRequest.getUserName()).get();
        String jwt =jwtservice.generateToken(user,generateExtraClaims(user));
        return new AuthenticationResponse(jwt);
    }

    private Map<String,Object> generateExtraClaims(Student user) {
        Map<String,Object> extraClaims=new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("role",user.getRole().name());
        return extraClaims;
    }
}

