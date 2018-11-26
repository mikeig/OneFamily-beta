package com.example.chaohan.onefamily;

import java.util.ArrayList;

public class Friends {

    private String address;
    private String name;
    private String headSet;
    private int curId;
    private static int id = 0;
    private static ArrayList<Friends> storedFriendList = new ArrayList<>();

    public Friends() {

    }

    public Friends (String name, String address) {
        this.address = address;
        this.name = name;
        this.headSet = "";
        id++;
        curId = id;
    }

    public Friends (String name, String address, String headSet) {
        this.address = address;
        this.name = name;
        this.headSet = headSet;
        id++;
        curId = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadSet() {
        return headSet;
    }

    public void setHeadSet(String headSet) {
        this.headSet = headSet;
    }

    public int getCurId() {
        return curId;
    }

    public void setCurId(int id) {
        curId = id;
    }

    public static ArrayList<Friends> getStoredFriendList() {

        return storedFriendList;
    }

    public static void addNewFriend(Friends newFriend) {
        storedFriendList.add(newFriend);
    }

    public static void loadFriendListTemp(String[] names, String[] address, String[] headSet) {
        for (int i=0; i<names.length; i++) {
            Friends newFriend = new Friends(names[i], address[i], headSet[i]);
            addNewFriend(newFriend);
        }
    }
}
