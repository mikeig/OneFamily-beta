package com.example.chaohan.onefamily;

import java.util.ArrayList;

public class Chatting {

    private Friends curFriend;

    private static Chatting chatStore;

    private ArrayList<String> friendMessages;
    private ArrayList<String> myMessages;
    private ArrayList<String> allMessages;

    public Chatting(){

    }

    public Chatting(Friends curFriend) {
        this.curFriend = curFriend;
        chatStore = this;
    }

    public static Chatting getChatStore() {
        return chatStore;
    }

    public Friends getCurFriend() {
        return curFriend;
    }

    public String getFriendName() {
        return curFriend.getName();
    }



    public ArrayList<String> getFriendMessages() {
        return friendMessages;
    }

    public void setFriendMessages(ArrayList<String> friendMessages) {
        this.friendMessages = friendMessages;
    }

    public void setMyMessages(ArrayList<String> myMessages) {
        this.myMessages = myMessages;
    }

    ArrayList<String> getMyMessages() {
        return myMessages;
    }

    public ArrayList<String> getAllMessages() {
        return allMessages;
    }

    public void addMyMessage(String msg) {
        myMessages.add(msg);
        allMessages.add(msg);
    }

    public void addFriendMessage(String msg) {
        friendMessages.add(msg);
        allMessages.add(msg);
    }
}
