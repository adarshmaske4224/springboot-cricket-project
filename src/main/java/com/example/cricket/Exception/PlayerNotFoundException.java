package com.example.cricket.Exception;

public class PlayerNotFoundException extends RuntimeException{

   public PlayerNotFoundException(String message){
        super(message);
    }
}
