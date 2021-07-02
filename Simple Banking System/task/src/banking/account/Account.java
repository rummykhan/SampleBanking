package banking.account;

public interface Account {
    public double getBalance();

    public String getCardNumber();

    public String getPinCode();

    public boolean pinMatches(String pinCode);
}
