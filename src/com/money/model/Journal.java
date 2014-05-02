package com.money.model;

import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by michal on 30/04/14.
 */
public class Journal {

    private String id;

    private List<Entry> entries;

    private Journal() {}

    public static Journal newInstance() {
        Journal returnValue = new Journal();
        returnValue.entries = new ArrayList<Entry>();
        returnValue.id = UUID.randomUUID().toString();
        return returnValue;
    }

    public Journal addEntry(Entry entry) {
        entries.add(entry);

        System.out.println("adding: " + entry.getAmount() + ", on: " + entry.getDate().getTime().toString());

        return this;
    }

    public double getCreditsAmount() {
        double returnValue = 0;
        for(Entry entry: entries) {
            if(entry.getAmount()>0) {
                returnValue = returnValue + entry.getAmount();
            }
        }
        return returnValue;
    }

    public double getDebitsAmount() {
        double returnValue = 0;
        for(Entry entry: entries) {
            if(entry.getAmount()<0) {
                returnValue = returnValue + entry.getAmount();
            }
        }
        return returnValue;
    }

    public double getCreditsAmountToday() {
        double returnValue = 0;
        Calendar today = Calendar.getInstance();
        for(Entry entry: entries) {
            if(DateUtils.isSameDay(today, entry.getDate()) &&
                    entry.getAmount()>0) {
                returnValue = returnValue + entry.getAmount();
            }
        }
        return returnValue;
    }

    public double getDebitsAmountToday() {
        double returnValue = 0;
        Calendar today = Calendar.getInstance();
        for (Entry entry : entries) {
            if (DateUtils.isSameDay(today, entry.getDate()) &&
                    entry.getAmount()<0) {
                returnValue = returnValue + entry.getAmount();
            }
        }
        return returnValue;
    }

    public double getDebitsSameDayLastWeek() {
        double returnValue = 0;
        for(Entry entry: entries) {
            if(DateUtils.isSameDay(entry.getDate(), getSameDayLastWeek()) &&
                    entry.getAmount()<0) {
                returnValue = returnValue + entry.getAmount();
            }
        }
        return returnValue;
    }

    public double getAverageDebitsSameDayLastWeek() {
        double returnValue = 0;
        double total = 0;

        List<Calendar> days =
                new ArrayList<Calendar>();

        for(Entry entry: entries) {

            if(!DateUtils.isSameDay(entry.getDate(), Calendar.getInstance()) &&
                    entry.getDate().get(Calendar.DAY_OF_WEEK) ==
                    Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {

                Calendar truncated = Calendar.getInstance();
                truncated.setTime(entry.getDate().getTime());
                truncated = DateUtils.truncate(truncated, Calendar.DAY_OF_MONTH);

                if(!days.contains(truncated)) {
                    days.add(truncated);
                }

                total = total + entry.getAmount();
            }

        }

        if(days.size()>0) {
            returnValue = total / Double.valueOf(days.size());
        }

        return returnValue;
    }

    public double getDebitsThisMonth() {
        double returnValue = 0;

        Calendar monthStart =
                DateUtils.truncate(Calendar.getInstance(), Calendar.MONTH);

        for(Entry entry: entries) {
            if(entry.getAmount()<0 &&
                    entry.getDate().after(monthStart)) {
                returnValue = returnValue + entry.getAmount();
            }
        }

        return returnValue;
    }

    private Calendar getSameDayLastWeek() {
        Calendar returnValue = Calendar.getInstance();
        returnValue.add(Calendar.DATE, -7);
        return returnValue;
    }

}
