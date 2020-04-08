package com.app.chatbot.service.observers;

import com.app.chatbot.dao.rules.ObserverRulesDao;

public abstract class ChatObserver {

    protected String observableChat;
    private boolean isExpectedResultReceived;

    public boolean isExpectedResultReceived() {
        return isExpectedResultReceived;
    }

    public void setExpectedResultReceived(boolean expectedResultReceived) {
        isExpectedResultReceived = expectedResultReceived;
    }

    public abstract String interpretChat(ObserverRulesDao observerRulesDao);

}
