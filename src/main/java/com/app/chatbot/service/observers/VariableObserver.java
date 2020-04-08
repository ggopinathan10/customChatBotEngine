package com.app.chatbot.service.observers;

import com.app.chatbot.dao.rules.ObserverRulesDao;
import com.app.chatbot.utils.AppUtil;
import com.app.chatbot.utils.constants.VariableConstants;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableObserver extends ChatObserver {

    private String expectation;
    private NameFinderME nameFinderME;
    private Tokenizer tokenizer;

    public VariableObserver(){
        super();
    }

    public VariableObserver(String observableChat, String expectation, NameFinderME nameFinderME, Tokenizer tokenizer) {
        this.observableChat = observableChat;
        this.expectation = expectation;
        this.nameFinderME = nameFinderME;
        this.tokenizer = tokenizer;
    }



    public String interpretChat(ObserverRulesDao observerRulesDao){
        return extractVariableBasedOnExpectations(observableChat);
    }

    private String extractVariableBasedOnExpectations(String userInput){
        String expectedVariable = "";
        switch (expectation){
            case VariableConstants
                    .FIRST_NAME :
                expectedVariable = extractName(userInput);
                setExpectedResultReceived(AppUtil.checkEmpty(expectedVariable));
                break;
            case VariableConstants
                    .LAST_NAME :
                expectedVariable = extractName(userInput);
                setExpectedResultReceived(AppUtil.checkEmpty(expectedVariable));
                break;
            case VariableConstants
                    .DOB :
                expectedVariable = extractDate(userInput);
                setExpectedResultReceived(AppUtil.checkEmpty(expectedVariable));
                break;
            case VariableConstants
                    .EMAIL_ADDRESS :
                expectedVariable = extractEmail(userInput);
                setExpectedResultReceived(AppUtil.checkEmpty(expectedVariable));
                break;
            default: System.out.println("No expectation matches");
        }
        return expectedVariable;
    }

    public String extractName(String userInput){
        String name = "";
        if(userInput.indexOf(" ") == -1){
            name = userInput;
        }else {
            tokenizer = new AppUtil().loadTokenizerModel();
            nameFinderME = new AppUtil().loadNameFinderNLPModel();
            String tokens[] = tokenizer.tokenize(userInput);
            Span nameSpans[] = nameFinderME.find(tokens);
            if (nameSpans.length > 0) {
                name = Span.spansToStrings(nameSpans, tokens)[0];
            }
        }
        return name;
    }

    public static void main(String[] args){
        new VariableObserver().extractName("John is 26 years old. His best friend's "
                + "name is Leonard. He has a sister named Penny.");
    }

    public String extractDate(String userInput){
        List<Pattern> regexList = new ArrayList<>();
        String date = "";
        regexList.add(Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}"));
        regexList.add(Pattern.compile("[0-9]{2}-[0-9]{2}-[0-9]{4}"));
        regexList.add(Pattern.compile("[0-9]{2}.[0-9]{2}.[0-9]{4}"));
        for (Pattern pattern: regexList) {
            Matcher matcher = pattern.matcher(userInput);
            while (matcher.find()){
                date = matcher.group();
            }
        }
        return date;
    }

    public String extractEmail(String userInput){
        String email = "";
        Pattern pattern = Pattern.compile("[\\w.]+@[\\w.]+");
        Matcher matcher = pattern.matcher(userInput);
        while (matcher.find()){
            email = matcher.group();
        }
        return email;
    }

}
