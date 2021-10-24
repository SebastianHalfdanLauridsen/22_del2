package test;

import account.Account;
import account.AccountManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountBalanceTest {

    @Test
    public void setBalance() {
        // Starter med at lave et nyt account som har en balance på 0, og navn AccountTest1
        Account account1 = new Account(1, "AccountTest1", 0);

        // Opretter en AccountManager til at teste om AccountManager-systemet virker
        AccountManager accountManager = new AccountManager(1, "AccountManagerTest1");

        // Tilføjer account til accountmanager
        accountManager.addAccount(account1);

        // Prøver igennem accountmanager at sætte balance til at være negativ
        accountManager.getAccount(1).setBalance(-100);

        // Spørger om balance på account 1 er mindre end 0
        assertEquals(0,accountManager.getAccount(1).getBalance());

        // Hvis balance på AccountTest1 er under 0 vil testen fejle
        // Printer account balance til terminal med komentar
        System.out.println("Account Balance på AccountTest1 er: " + accountManager.getAccount(1).getBalance() + " Efter at dets tideligere beløb på 0, blev sat til at være -100");
    }
}