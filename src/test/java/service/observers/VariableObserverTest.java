package service.observers;

import com.app.chatbot.service.observers.VariableObserver;
import static org.junit.Assert.*;

import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;



public class VariableObserverTest {

    private VariableObserver variableObserver;


    @Before
    public void setup(){
        variableObserver = new VariableObserver();
    }

    @Test
    public void test_extractName_singleWord(){

        String testName = "Mark";
        assertEquals("Mark", variableObserver.extractName(testName));
    }

    @Test
    public void test_extractDate(){
        String testSentence = "My Date of birth is 22/03/1990";
        assertEquals("22/03/1990", variableObserver.extractDate(testSentence));

    }


    @Test
    public void test_extractEmail(){
        String testSentence = "my email is user@jeffapp.com";
        assertEquals("user@jeffapp.com", variableObserver.extractEmail(testSentence));
    }


}
