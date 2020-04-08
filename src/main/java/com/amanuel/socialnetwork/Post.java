package com.amanuel.socialnetwork;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue

    private long id;
    private String text;
    private Date date;
    private String userID;

    public Post(){

    }

    public Post(long id, String text, Date date, String userID) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.userID = userID;
    }

    public Post(String text, Date date, String userID) {
        this.text = text;
        this.date = date;
        this.userID = userID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserID() { return userID; }

    public void setUserID(String userID) { this.userID = userID; }
}
