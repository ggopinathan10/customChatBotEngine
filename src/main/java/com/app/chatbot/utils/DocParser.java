package com.app.chatbot.utils;

import com.app.chatbot.dao.chat.BotStatementsDao;
import com.app.chatbot.dao.rules.VariableRegexRulesDao;
import com.app.chatbot.dao.rules.ContextBasedStatementsDao;

public interface DocParser {

    BotStatementsDao parseStatementResource(String fileName);

    VariableRegexRulesDao parseVariableRulesResource(String fileName);

    ContextBasedStatementsDao parseContextResource(String fileName);

}
