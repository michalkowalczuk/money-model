import model.Account;
import model.Entry;
import model.Journal;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by michal on 11/04/14.
 */
public class App {

    public static void main(String[] args) {

        Journal journal = Journal.newInstance();

        journal.addEntry(Entry.newInstance(3000));

        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, 1);

        for(int i=0; i<21; i++) {

            date.add(Calendar.DATE, -1);

            for(int j=0; j<2; j++) {

                double amount =
                        new Random().nextInt(50);

                journal.addEntry(Entry.newInstance(-amount).setDate(date));

            }

        }






        Account someAccount = Account.newInstance();

        someAccount.addJournal(journal);

        System.out.println("b: " + someAccount.getBalance());
        System.out.println("dt: " + someAccount.getDebitsToday());
        System.out.println("dc: " + someAccount.getCreditsToday());

        System.out.println("per day: " + someAccount.getAllowancePerDay());

        System.out.println("same day last week: " + someAccount.getDebitsSameDayLastWeek());
        System.out.println("same day last week (avr): " + someAccount.getAverageDebitsSameDayLastWeek());

        System.out.println("this month: " + someAccount.getDebitsThisMonth());

    }

}
