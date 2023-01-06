package com.example.demo.controller;

import com.example.demo.config.JwtAuthFilter;
import com.example.demo.config.JwtUtils;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.AuthRequest;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        //final UserDetails user = userDao.findUserByEmail(request.getEmail());
        final UserDetails user = userService.loadUserByUsername(request.getEmail());

        System.out.println(user);

        if (user != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }

        return ResponseEntity.ok("Some error has occurred");
    }

    @GetMapping("/read-json")
    public ResponseEntity<Map<String, Object>> getJsonStates() throws Exception{
        ClassPathResource jsonData = new ClassPathResource("states.json");
        String jsonDataString = IOUtils.toString(jsonData.getInputStream(), StandardCharsets.UTF_8);

        JSONObject statesArray = new JSONObject(jsonDataString);

        System.out.println("json states" + statesArray.toMap());

        return new ResponseEntity<>(new JSONObject(jsonDataString).toMap(), HttpStatus.OK);
    }
}
