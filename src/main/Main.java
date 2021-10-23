package main;

import account.Account;
import account.AccountManager;

import field.FieldManager;
import field.InterfaceField;
import field.Field;

import player.InterfacePlayer;
import player.Player;
import player.PlayerManager;

public class Main {

    public static final long START_BALANCE = 1000;

    public static void main(String[] args) {

        //field
        InterfaceField field2 = new Field(
                2, "Tower", "You climb the tower for its riches and you get them", 250);
        InterfaceField field3 = new Field(
                3, "Crator", "You come across a crater and fall into it, ouch", -100);
        InterfaceField field4 = new Field(
                4, "Palace gates",
                "You arrive at the foot of the giant Palace gates" +
                        " and are greeted by the Palace maids," +
                        " who hand you gifts",
                100);

        InterfaceField field7 = new Field(
                7, "Monastery",
                "You come across a monastery, " +
                        "the monks inside feed you and let you sleep in their quarters, " +
                        "you feel well rested and get an extra turn",
                0);

        FieldManager fieldManager = new FieldManager(0, "Field manager");

        //player
        InterfacePlayer player1 = new Player(1, "Karl");
        InterfacePlayer player2 = new Player(2, "Kevse");

        PlayerManager playerManager = new PlayerManager(0, "Player manager");

        //account (could perhaps be shortened dynamically https://stackoverflow.com/questions/13868986/)
        Account account1 = new Account(1, "Player 1 account", START_BALANCE);
        Account account2 = new Account(2, "Player 2 account", START_BALANCE);

        AccountManager accountManager = new AccountManager(0, "Account Manager");

        accountManager.addAccount(account1);
        accountManager.addAccount(account2);

        fieldManager.addField(field2);
        fieldManager.addField(field3);
        fieldManager.addField(field4);
        fieldManager.addField(field7);


        fieldManager.getFieldDetails();


        System.out.println(player1);

        System.out.println(accountManager.getAccount(1).getBalance());
        accountManager.getAccount(1).setBalance(-100);
        System.out.println(accountManager.getAccount(1).getBalance());

        System.out.println(accountManager.getAccount(2).getBalance());
        accountManager.getAccount(2).setBalance(-300);
        System.out.println(accountManager.getAccount(2).getBalance());
        accountManager.getAccount(2).setBalance(0);
        System.out.println(accountManager.getAccount(2).getBalance());
    }
}