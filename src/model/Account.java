package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by michal on 30/04/14.
 */
public class Account {

    private List<Journal> journals;

    private Account() {}

    public static Account newInstance() {
        Account returnValue = new Account();
        returnValue.journals = new ArrayList<Journal>();
        return returnValue;
    }

    public Account addJournal(Journal journal) {
        this.journals.add(journal);
        return this;
    }

    public double getBalance() {
        double returnValue = 0;
        for(Journal journal: journals) {
            returnValue = returnValue +
                    (journal.getCreditsAmount() + journal.getDebitsAmount());
        }
        return returnValue;
    }

    public double getCreditsToday() {
        double returnValue = 0;
        for(Journal journal: journals) {
            returnValue = returnValue + journal.getCreditsAmountToday();
        }
        return returnValue;
    }

    public double getDebitsToday() {
        double returnValue = 0;
        for(Journal journal: journals) {
            returnValue = returnValue + journal.getDebitsAmountToday();
        }
        return returnValue;
    }

    public double getAllowancePerDay() {
        double daysLeft = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH) -
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return daysLeft > 0 ?
                getBalance() / daysLeft : getBalance();
    }

    public double getDebitsSameDayLastWeek() {
        double returnValue = 0;
        for(Journal journal: journals) {
            returnValue = returnValue + journal.getDebitsSameDayLastWeek();
        }
        return returnValue;
    }

    public double getAverageDebitsSameDayLastWeek() {
        double returnValue = 0;
        double total = 0;
        for(Journal journal: journals) {
            total = total + journal.getAverageDebitsSameDayLastWeek();
        }
        returnValue = total / Double.valueOf(journals.size());
        return returnValue;
    }

    public double getDebitsThisMonth() {
        double returnValue = 0;
        for(Journal journal: journals) {
            returnValue = returnValue + journal.getDebitsThisMonth();
        }
        return returnValue;
    }


}
