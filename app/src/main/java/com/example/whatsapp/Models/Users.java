package com.example.whatsapp.Models;

public class Users {

    String profilePic,UserName,mail,password,userId,lastMessage,About;


    public Users(String profilePic, String userName, String mail, String password, String userId, String lastMessage, String About) {
        this.profilePic = profilePic;
        UserName = userName;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
        this.About = About;
    }

    public Users(){}

    /* SignUp Constructor */
    public Users(  String userName, String mail, String password) {
        UserName = userName;
        this.mail = mail;
        this.password = password;
    }


    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    }

