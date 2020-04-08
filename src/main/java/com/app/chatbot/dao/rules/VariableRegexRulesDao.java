package com.app.chatbot.dao.rules;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class VariableRegexRulesDao extends ObserverRulesDao {

    private LinkedHashMap<String, LinkedList> rules;

    public LinkedHashMap<String, LinkedList> getRules() {
        return rules;
    }

    public void setRules(LinkedHashMap<String, LinkedList> rules) {
        this.rules = rules;
    }
}
