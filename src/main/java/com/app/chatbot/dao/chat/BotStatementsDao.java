package com.app.chatbot.dao.chat;

import java.util.LinkedHashMap;

public class BotStatementsDao {

    private LinkedHashMap<String, StatementDao> statements;

    public LinkedHashMap<String, StatementDao> getStatements() {
        return statements;
    }

    public void setStatements(LinkedHashMap<String, StatementDao> statements) {
        this.statements = statements;
    }

    @Override
    public String toString() {
        return "ChatBotStatements{" + statements +
                '}';
    }
}
