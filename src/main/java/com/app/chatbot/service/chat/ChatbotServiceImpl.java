package com.app.chatbot.service.chat;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import com.app.chatbot.dao.chat.BotStatementsDao;
import com.app.chatbot.dao.chat.StatementDao;
import com.app.chatbot.dao.rules.VariableRegexRulesDao;
import com.app.chatbot.dao.rules.ContextBasedStatementsDao;
import com.app.chatbot.service.observers.ChatContextInfoObserver;
import com.app.chatbot.service.observers.ChatObserver;
import com.app.chatbot.service.observers.VariableObserver;
import com.app.chatbot.utils.AppUtil;
import com.app.chatbot.utils.DocParser;
import com.app.chatbot.utils.JSONDocParser;
import com.app.chatbot.utils.constants.PathConstants;
import com.app.chatbot.utils.constants.VariableConstants;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.tokenize.Tokenizer;

public class ChatbotServiceImpl extends ChatbotService {

    private static final Logger logger = Logger.getLogger(ChatbotServiceImpl.class.getName());

    private BotStatementsDao botStatementsDao;
    private ContextBasedStatementsDao contextBasedStatementsDao;
    private VariableRegexRulesDao variableRegexRulesDao;
    private boolean isActive;
    private int statementIndex = 1;
    private LinkedHashMap<String, StatementDao> statements;
    private StatementDao currentStatementDao;
    private SentenceDetectorME sentenceDetectorME;
    private NameFinderME nameFinderME;
    private Tokenizer tokenizer;
    private Map<String, String> variablesMap;
    private AppUtil appUtil = new AppUtil();
    private boolean isCurrentStatementRepeated;

    public void initializeChatbot(){
        loadResources();
        System.out.println("Chat bot initialised");
    }

    public String loadResources() {
        DocParser jsonDocParser = new JSONDocParser();

        botStatementsDao = jsonDocParser.parseStatementResource(PathConstants.CHATBOT_STATEMENT_RESOURCE);
        setStatements(botStatementsDao.getStatements());
        variableRegexRulesDao = jsonDocParser.parseVariableRulesResource(PathConstants.VARIABLE_REGEX_RULES_RESOURCE);
        contextBasedStatementsDao = jsonDocParser.parseContextResource(PathConstants.CONTEXT_IDENTIFIER_RESOURCE);
        sentenceDetectorME = appUtil.loadSentenceDetectorNLPModel();
        tokenizer = appUtil.loadTokenizerModel();
        nameFinderME = appUtil.loadNameFinderNLPModel();
        variablesMap = new HashMap<>();
        return getBotMessage();
    }

    public String displayChatStatementAndProcessInput(String userInput) {
        String botResponseText = "";
        boolean isExpectedResultReceived = false;
        final String expectations = currentStatementDao.getExpectations();
        if(expectations != null && !expectations.isEmpty()) {
            ChatObserver chatVariableObserver = new VariableObserver(userInput, expectations, nameFinderME, tokenizer);
            String variableValue = chatVariableObserver.interpretChat(variableRegexRulesDao);
            isExpectedResultReceived = chatVariableObserver.isExpectedResultReceived();
            if(isExpectedResultReceived){
                variablesMap.put(expectations, variableValue);
                botResponseText = getBotMessage();
            }else{
                if(isCurrentStatementRepeated){
                    ChatObserver chatContextObserver = new ChatContextInfoObserver(userInput, sentenceDetectorME);
                    botResponseText = chatContextObserver.interpretChat(contextBasedStatementsDao);
                }else{
                    botResponseText = currentStatementDao.getReservedStatement();
                    isCurrentStatementRepeated = true;
                }
                System.out.println("Bot : " +botResponseText);
            }
        }

        return botResponseText;
    }

    private String getBotMessage() {
        String botResponse = "";
        String statementKey = VariableConstants.BOT_STATEMENT + statementIndex;
        if(statements.containsKey(statementKey) && statements.get(statementKey) != null) {
            StatementDao statementDao = statements.get(statementKey);
            botResponse = statementDao.getStatement();
            botResponse = appUtil.replaceVariableInBotMessage(botResponse, variablesMap);
            System.out.println("Bot : " + botResponse);
            setCurrentStatementDao(statementDao);
            statementIndex++;
            if(statementDao.getExpectations() != null && !statementDao.getExpectations().isEmpty()) {

            }else{
                getBotMessage();
            }
        }
        return botResponse;
    }

    private void observeUserInput(String userInput, String expectations){

    }

    public LinkedHashMap<String, StatementDao> getStatements() {
        return statements;
    }

    public void setStatements(LinkedHashMap<String, StatementDao> statements) {
        this.statements = statements;
    }

    public StatementDao getCurrentStatementDao() {
        return currentStatementDao;
    }

    public void setCurrentStatementDao(StatementDao currentStatementDao) {
        this.currentStatementDao = currentStatementDao;
    }

}
