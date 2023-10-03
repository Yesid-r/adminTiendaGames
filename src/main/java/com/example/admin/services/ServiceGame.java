package com.example.admin.services;


import com.example.admin.entities.Game;
import com.example.admin.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceGame {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> findAll(){
        return gameRepository.findAll();
    }
    public Game findById( Long id ){
        return gameRepository.findById(id).get();
    }
    public Game save( Game game){
        return gameRepository.save( game );
    }

    public Game delete(Long id) {
        Game game = findById( id );
        if( game != null ){
            gameRepository.delete( game );
            return game;
        }
        return null;
    }
    public Game update(Game game, Long id){
        Game game1 = findById( id );
        if(game != null){
            game1.setName( game.getName() );
            game1.setPrice( game.getPrice() );
            game1.setDescription( game.getDescription());
            game1.setReleaseDate( game.getReleaseDate() );
            game1.setClasification( game.getClasification() );
            game1.setStorage( game.getStorage() );
            game1.setMemory( game.getMemory() );

            return gameRepository.save( game1 );
        }
        return null;
    }
}
