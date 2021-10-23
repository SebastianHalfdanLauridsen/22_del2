package account;

/**
 * Contains a specific player's balance and can manipulate it
 */
public class Account {
    private long id;
    private String name;
    private long balance;

    public Account(long id, String name, long balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public void getAccountDetails() {
        System.out.println("Field " + id + " '" + name + "' " + balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    /**
     * Adds or decreases the balance of the player based on the input (positive or negative)
     * @param balance the amount to be added or subtracted from the players account balance
     * @return        true if the balance was edited
     */
    public boolean setBalance(long balance){
        //If balance is negative check if this.balance is going to be negative.
        //If true set this.balance to 0, if it is already 0 return false
        if(balance < 0) {
            if(this.balance <= (balance * -1)) {
                if(this.balance == 0) {
                    return false;
                }
                this.balance = 0;
                return true;
            }
        }
        //add or subtract the balance
        this.balance += balance;
        return true;
    }
}
