package utils;

import com.app.chatbot.utils.AppUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AppUtilTest {

    private Map<String, String> variablesMap;

    @Before
    public void setup(){
        variablesMap = new HashMap<>();
        variablesMap.put("FIRST_NAME", "John");
    }

    @Test
    public void test_replaceVariableInBotMessage(){
        String testResponse = "Welcome to Jeff App ${FIRST_NAME}";
        String expectedStr = "Welcome to Jeff App John";
        assertEquals(expectedStr, new AppUtil().replaceVariableInBotMessage(testResponse, variablesMap));
    }
}
