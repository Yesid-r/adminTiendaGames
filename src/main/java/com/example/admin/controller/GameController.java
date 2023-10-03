package com.example.admin.controller;

import com.example.admin.entities.Game;
import com.example.admin.responses.ResponseHandler;
import com.example.admin.security.JwtTokenProvider;
import com.example.admin.services.ServiceGame;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private ServiceGame serviceGame;

    private boolean validateUser(String token){
        if (!token.equals("admin")){
            return false;
        }else {
            return true;
        }
    }

    @GetMapping("/test")
    public String test (HttpServletRequest request){
        String token = jwtTokenProvider.getUsername(request.getHeader("Authorization").substring(7));
        System.out.println(token);
        if(token == null){
            return "not authorized";
        } else if (!token.equals("admin")){
            return "not authorized";
        }else {
            return "authorized";
        }
    }
    @GetMapping
    public ResponseEntity<Object> findAll(HttpServletRequest request){


        try {
            List<Game> result = serviceGame.findAll();
            return new ResponseHandler().generateResponse("Success", HttpStatus.OK, result);
        }catch (Exception e) {
            return new ResponseHandler().generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        try{
            Game game = serviceGame.findById( id );
            return new ResponseHandler().generateResponse("Success", HttpStatus.OK, game);
        }catch (Exception e) {
            return new ResponseHandler().generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Game game, HttpServletRequest request ){
        String token = jwtTokenProvider.getUsername(request.getHeader("Authorization").substring(7));
        if (!validateUser(token)){
            return new ResponseHandler().generateResponse("Not authorized", HttpStatus.UNAUTHORIZED, null);
        }

        try{
            Game game1 = serviceGame.save( game );
            return new ResponseHandler().generateResponse("Success", HttpStatus.OK, game1);
        }catch (Exception e) {
            return new ResponseHandler().generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, HttpServletRequest request){
        String token = jwtTokenProvider.getUsername(request.getHeader("Authorization").substring(7));
        if (!validateUser(token)){
            return new ResponseHandler().generateResponse("Not authorized", HttpStatus.UNAUTHORIZED, null);
        }
        try{
            Game game = serviceGame.delete( id );
            return new ResponseHandler().generateResponse("Success", HttpStatus.OK, game);
        }catch (Exception e) {
            return new ResponseHandler().generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Game game, HttpServletRequest request){
        String token = jwtTokenProvider.getUsername(request.getHeader("Authorization").substring(7));
        if (!validateUser(token)){
            return new ResponseHandler().generateResponse("Not authorized", HttpStatus.UNAUTHORIZED, null);
        }
        try{
            Game game1 = serviceGame.update( game, id);
            return new ResponseHandler().generateResponse("Success", HttpStatus.OK, game1);
        }catch (Exception e) {
            return new ResponseHandler().generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
