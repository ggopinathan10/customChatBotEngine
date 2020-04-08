package service.observers;

import com.app.chatbot.dao.rules.ContextBasedStatementsDao;
import com.app.chatbot.service.observers.ChatContextInfoObserver;
import com.app.chatbot.utils.AppUtil;
import com.app.chatbot.utils.DocParser;
import com.app.chatbot.utils.JSONDocParser;
import com.app.chatbot.utils.constants.PathConstants;
import mockit.Mocked;
import mockit.Tested;
import opennlp.tools.sentdetect.SentenceDetectorME;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChatContextInfoObserverTest {

    @Tested
    private ChatContextInfoObserver chatContextInfoObserver;

    @Mocked
    private SentenceDetectorME sentenceDetectorME;

    private ContextBasedStatementsDao contextBasedStatementsDao;



    @Before
    public void setup(){
        String observableChat = "What is Jeff App";
        AppUtil appUtil = new AppUtil();
        sentenceDetectorME = appUtil.loadSentenceDetectorNLPModel();
        DocParser jsonDocParser = new JSONDocParser();
        contextBasedStatementsDao = jsonDocParser.parseContextResource(PathConstants.CONTEXT_IDENTIFIER_RESOURCE);
        chatContextInfoObserver = new ChatContextInfoObserver(observableChat, sentenceDetectorME);
    }

    @Test
    public void test_interpretChat(){
        assertTrue(AppUtil.checkEmpty(chatContextInfoObserver.interpretChat(contextBasedStatementsDao)));
    }



}
