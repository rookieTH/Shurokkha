package com.cse.mist.shurokkhav5;

/**
 * Created by TASNEEA on 9/24/2017.
 */

public class Contacts {
    String id;
    String name;
    String number;
    String email;

    public Contacts() {

    }

    public Contacts(String id, String name, String number,String email) {
        this.email=email;
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
