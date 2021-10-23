package account;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds all instances of Account
 */
public class AccountManager{

    private final long id;
    private final String name;

    private final List<Account> childAccounts;

    public AccountManager(long id, String name) {
        this.id = id;
        this.name = name;
        this.childAccounts = new ArrayList<>();
    }

    public void getAccountDetails() {
        childAccounts.forEach(Account::getAccountDetails);
    }

    /**
     * Takes an object of type Account and adds it to the ArrayList childAccount
     * @param account An object of type Account
     */
    public void addAccount(Account account) {
        childAccounts.add(account);
    }

    /**
     * Takes an index to the ArrayList childAccounts and returns an object of type Account from childAccounts
     * @param index An index of type int in childAccounts
     * @return An object of type Account from childAccounts
     */
    public Account getAccount(int index) {
        return childAccounts.get(index-1);
    }

    /**
     * Removes an object of type Account from the ArrayList childAccounts
     * @param index An index of type int in ChildAccounts
     */
    public void removeAccount(int index) {
        childAccounts.remove(index-1);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AccountManager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
