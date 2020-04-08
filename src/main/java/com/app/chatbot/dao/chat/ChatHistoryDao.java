package com.app.chatbot.dao.chat;

import java.util.HashMap;

public class ChatHistoryDao {

    private UserDetailsDao userDetailsDao;

    private HashMap<String, HashMap<String, Object>> chats;

    @Override
    public String toString() {
        return "ChatHistory{" +
                "chats=" + chats +
                '}';
    }


}
