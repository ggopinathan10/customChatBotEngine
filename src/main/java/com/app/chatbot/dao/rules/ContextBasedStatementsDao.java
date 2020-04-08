package com.app.chatbot.dao.rules;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ContextBasedStatementsDao extends ObserverRulesDao {



    private LinkedHashMap<String, LinkedList<String>> sentenceRules;

    public LinkedHashMap<String, LinkedList<String>> getSentenceRules() {
        return sentenceRules;
    }

    public void setSentenceRules(LinkedHashMap<String, LinkedList<String>> sentenceRules) {
        this.sentenceRules = sentenceRules;
    }


}
