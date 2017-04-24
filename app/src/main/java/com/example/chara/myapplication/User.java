package com.example.chara.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by chara on 04-03-2017.
 */

@IgnoreExtraProperties
public class User {

    private String name;
    private String age;
    private String lang;
    private String date;
    private String time;
     private String email;
    private String about;
    private String song;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public User() {
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public User(String name1, String age1, String email1, String about1, String song1,String lang1) {
        this.setname(name1);

        this.setage(age1);
        this.setemail(email1);
        this.setAbout(about1);
        this.setSong(song1);
        this.setLang(lang1);

    }
    public void  setname(String name1)
    {
         this.name=name1;
    }
    public String  getname()
    {
          return name;
    }
    public void  setage(String age1)
    {
        this.age=age1;
    }
    public String  getage()
    {
        return age;
    }

    public void  setemail(String email1)
    {
        this.email=email1;
    }
    public String  getemail()
    {
        return email;
    }
}
