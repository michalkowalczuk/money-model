package com.money.model;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by michal on 30/04/14.
 */
public class Entry {

    private String id;

    private double amount;
    private Calendar date;

    private Entry() {}

    public static Entry newInstance(double amount) {
        Entry returnValue = new Entry();
        returnValue.amount = amount;
        returnValue.date = Calendar.getInstance();
        returnValue.id = UUID.randomUUID().toString();
        return returnValue;
    }

    public double getAmount() {
        return amount;
    }

    public Calendar getDate() {
        return date;
    }

    public Entry setDate(Calendar date) {
        this.date.setTime(date.getTime());
        return this;
    }

}
