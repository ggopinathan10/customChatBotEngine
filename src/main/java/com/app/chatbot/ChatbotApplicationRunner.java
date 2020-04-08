package com.app.chatbot;

import com.app.chatbot.service.chat.ChatbotServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatbotApplicationRunner {


    public static void main(String[] args) throws Exception {
        ChatbotServiceImpl chatbotService = new ChatbotServiceImpl();
        chatbotService.initializeChatbot();
        String userInput = null;
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        while(!"exit".equals(userInput)){
            userInput = reader.readLine();
            if(!"exit".equals(userInput)) {
                chatbotService.displayChatStatementAndProcessInput(userInput);
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
