package com.app.chatbot.service.observers;

import com.app.chatbot.dao.rules.ContextBasedStatementsDao;
import com.app.chatbot.dao.rules.ObserverRulesDao;
import com.app.chatbot.utils.AppUtil;
import opennlp.tools.sentdetect.SentenceDetectorME;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;

public class ChatContextInfoObserver extends ChatObserver {

    private SentenceDetectorME sentenceDetectorME;


    public ChatContextInfoObserver(String observableChat, SentenceDetectorME sentenceDetectorME) {
        this.observableChat = observableChat;
        this.sentenceDetectorME = sentenceDetectorME;
    }



    @Override
    public String interpretChat(ObserverRulesDao observerRulesDao) {
        LinkedHashMap<String, LinkedList<String>> sentenceRules = ((ContextBasedStatementsDao)observerRulesDao).getSentenceRules();
        String responseChat = "";
            String sentences[] = sentenceDetectorME.sentDetect(observableChat);
            for (String sentence : sentences){
                if(sentenceRules.containsKey(sentence) && sentenceRules.get(sentence) != null){
                    LinkedList<String> responseList = sentenceRules.get(sentence);
                    Random rand = new Random();
                    responseChat = responseList.get(rand.nextInt(responseList.size()));
                }
            }
            if(!AppUtil.checkEmpty(responseChat)){
                responseChat = "Sorry, I didn't get that";
            }
        return responseChat;
    }
}
