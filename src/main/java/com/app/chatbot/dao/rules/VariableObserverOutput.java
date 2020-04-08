package com.app.chatbot.dao.rules;

public class VariableObserverOutput {


    private boolean isExpectedResultReceived;
    private String variableValue;

    public boolean isExpectedResultReceived() {
        return isExpectedResultReceived;
    }

    public void setExpectedResultReceived(boolean expectedResultReceived) {
        isExpectedResultReceived = expectedResultReceived;
    }



}
