package model;

import java.util.Calendar;

/**
 * Created by michal on 30/04/14.
 */
public class Entry {

    private double amount;
    private Calendar date;

    private Entry() {}

    public static Entry newInstance(double amount) {
        Entry returnValue = new Entry();
        returnValue.amount = amount;
        returnValue.date = Calendar.getInstance();
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
