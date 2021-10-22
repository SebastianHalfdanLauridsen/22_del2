package main;

public class Account {
    private long id;
    private long balance;


    public Account(long id, long start_balance){
        this.id = id;
        this.balance = start_balance;
    }

    /**
     * Adds or decreases the balance of the player based on the input (positive or negative)
     * @param balance  the amount to be added or subtracted
     * @return         true if the balance was edited
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

    public long getBalance() {
        return balance;
    }

    public String toString(){
        return id + " " + balance;
    }

}
