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

    /**
     * Adds or decreases the balance of the player based on the input (positive or negative)
     * @param balance the amount to be added or subtracted from the players account balance
     * @return        true if the balance was edited
     */
    public boolean setBalance(long balance){

        //If true then no changes has been made
        if((this.balance == 0 && balance < 0) || balance == 0) {
            return false;
        }
        //If this.balance is going to be negative set balance to 0
        if(this.balance < (balance * -1)) {
            this.balance = 0;
            return true;
        }
        //Adds balance to this.balance
        this.balance += balance;
        return true;
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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }


}
