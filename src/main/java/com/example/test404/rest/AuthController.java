package com.example.test404.rest;

import com.example.test404.models.Person;
import com.example.test404.repository.PersonRepository;
import com.example.test404.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private PersonRepository personRepository;
    private JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, PersonRepository personRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.personRepository = personRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody AuthRequestDTO request){
        AuthResponseDTO response = new AuthResponseDTO();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            Person person = personRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Person doesn`t exists"));
            String token = jwtTokenProvider.createToken(request.getEmail(), person.getRole().name());
//            Map<Object, Object> response = new HashMap<>();
//            response.put("email", request.getEmail());
//            response.put("token", token);
//            response.put("role", person.getRole().name());
            response.setEmail(request.getEmail());
            response.setToken(token);
            response.setRoles(person.getRole());
            response.setStatus(person.getStatus());



            return ResponseEntity.ok(response);
        } catch (AuthenticationException e){
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
