package com.app.chatbot.utils;

import com.app.chatbot.dao.chat.BotStatementsDao;
import com.app.chatbot.dao.rules.VariableRegexRulesDao;
import com.app.chatbot.dao.rules.ContextBasedStatementsDao;
import com.google.gson.Gson;

import java.io.*;
import java.util.logging.Logger;

public class JSONDocParser implements DocParser{

    private static final Logger logger = Logger.getLogger(JSONDocParser.class.getName());

    private ClassLoader classLoader = getClass().getClassLoader();

    @Override
    public BotStatementsDao parseStatementResource(String fileName) {
        BufferedReader br = null;
        File file = new File(classLoader.getResource(fileName).getFile());
        BotStatementsDao botStatementsDao = null;
        try {
            br = new BufferedReader(new FileReader(file));
            botStatementsDao = new Gson().fromJson(br, BotStatementsDao.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return botStatementsDao;
    }

    @Override
    public VariableRegexRulesDao parseVariableRulesResource(String fileName) {
        BufferedReader br = null;
        File file = new File(classLoader.getResource(fileName).getFile());
        VariableRegexRulesDao variableRegexRulesDao = null;
        try {
            br = new BufferedReader(new FileReader(file));
            variableRegexRulesDao = new Gson().fromJson(br, VariableRegexRulesDao.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return variableRegexRulesDao;
    }

    @Override
    public ContextBasedStatementsDao parseContextResource(String fileName) {
        BufferedReader br = null;
        File file = new File(classLoader.getResource(fileName).getFile());
        ContextBasedStatementsDao contextBasedStatementsDao = null;
        try {
            br = new BufferedReader(new FileReader(file));
            contextBasedStatementsDao = new Gson().fromJson(br, ContextBasedStatementsDao.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return contextBasedStatementsDao;
    }
}
