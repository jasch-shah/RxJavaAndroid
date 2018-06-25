package com.jasch.devdroid.rxjavaexample.model;

/**
 * Created by jashshah on 24/06/18.
 */

public class UserDetail {
    public long id;
    public String firstname;
    public String lastname;

    @Override
    public String toString() {
        return "UserDetail{"+
                "id="+id+
                ", firstname='"+firstname+'\''+
                ", lastname='"+lastname+'\''+
                '}';

    }
}
