package com.app.chatbot.utils;

import com.app.chatbot.utils.constants.PathConstants;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.StringTokenizer;

public class AppUtil {

    private ClassLoader classLoader = getClass().getClassLoader();

    public NameFinderME loadNameFinderNLPModel(){
        NameFinderME nameFinderME = null;
        try {
            InputStream NameFinderIS = new FileInputStream(classLoader.getResource(PathConstants.NLP_NAME_FINDER_PATH).getFile());
            TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(NameFinderIS);
            nameFinderME = new NameFinderME(tokenNameFinderModel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameFinderME;
    }

    public SentenceDetectorME loadSentenceDetectorNLPModel(){
        SentenceDetectorME detector = null;
        try {
            InputStream sentenceFinderIS = new FileInputStream(classLoader.getResource(PathConstants.NLP_SENTENCE_MODEL_PATH).getFile());
            SentenceModel sentenceModel = new SentenceModel(sentenceFinderIS);
            detector = new SentenceDetectorME(sentenceModel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return detector;
    }

    public Tokenizer loadTokenizerModel(){
        Tokenizer tokenizer = null;
        try {
            InputStream tokenIS = new FileInputStream(classLoader.getResource(PathConstants.NLP_TOKENIZER_PATH).getFile());
            TokenizerModel tokenizerModel = new TokenizerModel(tokenIS);
            tokenizer = new TokenizerME(tokenizerModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokenizer;

    }

    public static boolean checkEmpty(String testResponseStr){
            return (testResponseStr != null && !testResponseStr.isEmpty());
    }

    public String replaceVariableInBotMessage(String botMessage, Map<String, String> variablesMap){
            StringTokenizer st = new StringTokenizer(botMessage);
            while (st.hasMoreTokens()){
                String variableToken = st.nextToken();
                if(variableToken.contains("${") && variableToken.contains("}")) {
                    variableToken = botMessage.substring(botMessage.indexOf("${") + 2, botMessage.indexOf("}"));
                    if (variablesMap.containsKey(variableToken) && variablesMap.get(variableToken) != null) {
                        String variableValue = variablesMap.get(variableToken);
                        botMessage = botMessage.replace("${" + variableToken + "}", variableValue);
                    }
                }
            }
        return botMessage;
    }


}
