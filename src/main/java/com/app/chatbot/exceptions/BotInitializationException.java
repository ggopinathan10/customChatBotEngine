package com.app.chatbot.exceptions;

public class BotInitializationException extends RuntimeException{

    public BotInitializationException(String errorMessage){
        super(errorMessage);
    }

}
