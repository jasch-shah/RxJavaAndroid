package com.jasch.devdroid.rxjavaexample.model;

/**
 * Created by jashshah on 24/06/18.
 */

public class ApiUser {

    public long id;
    public String firstname;
    public String lastname;

    @Override
    public String toString() {
        return "ApiUser{"+
                "id="+id+
                ", firstname='"+firstname+'\''+
                ", lastname='"+lastname+'\''+
                '}';
    }
}
