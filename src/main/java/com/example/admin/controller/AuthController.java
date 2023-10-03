package com.example.admin.controller;

import com.example.admin.DTO.LoginDTO;
import com.example.admin.responses.ResponseHandler;
import com.example.admin.security.AuthService;
import com.example.admin.security.JWTAuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    @GetMapping
    public  String test (){
        return "test";
    }
    @GetMapping("/authenticate")
    public  String auth (){
        return "test";
    }
    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody LoginDTO loginDto){
        String token = "nothing";
        try {
            token = authService.login(loginDto);
            return new ResponseHandler().generateResponse("Success", HttpStatus.OK, token);
        }catch (Exception e){
            return new ResponseHandler().generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
