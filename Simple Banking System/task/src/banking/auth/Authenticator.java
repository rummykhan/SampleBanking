package banking.auth;

import banking.account.Account;

public interface Authenticator {

    public boolean login(String accountNumber, String pinCode);

    public void logout();

    public boolean isLoggedIn();

    public Account account();
}
