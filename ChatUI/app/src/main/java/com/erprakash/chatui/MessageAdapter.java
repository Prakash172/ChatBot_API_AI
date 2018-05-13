package com.erprakash.chatui;

public class MessageAdapter {
    private int imageID;
    private int imageArrowId;
    private String messageSent ;
    private String messageRecieved ;

    public MessageAdapter(int imageID, int imageArrowId, String messageSent) {
        this.imageID = imageID;
        this.imageArrowId = imageArrowId;
        this.messageSent = messageSent;

    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getImageArrowId() {
        return imageArrowId;
    }

    public void setImageArrowId(int imageArrowId) {
        this.imageArrowId = imageArrowId;
    }

    public String getMessageSent() {
        return messageSent;
    }

    public void setMessageSent(String messageSent) {
        this.messageSent = messageSent;
    }

    public String getMessageRecieved() {
        return messageRecieved;
    }

    public void setMessageRecieved(String messageRecieved) {
        this.messageRecieved = messageRecieved;
    }
}
