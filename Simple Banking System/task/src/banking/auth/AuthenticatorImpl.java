package banking.auth;

import banking.account.Account;
import banking.repository.AccountRepository;
import banking.ui.UserInterface;

public class AuthenticatorImpl implements Authenticator {

    private final AccountRepository accountRepository;
    private final UserInterface userInterface;

    private Account loggedInUser;

    public AuthenticatorImpl(AccountRepository accountRepository, UserInterface userInterface) {
        this.accountRepository = accountRepository;
        this.userInterface = userInterface;
        this.loggedInUser = null;
    }

    @Override
    public boolean login(String accountNumber, String pinCode) {
        Account account = this.accountRepository.getAccountByNumber(accountNumber);

        if (account == null || !account.pinMatches(pinCode)) {
            this.loggedInUser = null;
            return false;
        }

        this.loggedInUser = account;
        return true;
    }

    @Override
    public void logout() {
        this.loggedInUser = null;
    }

    @Override
    public boolean isLoggedIn() {
        return this.loggedInUser != null;
    }

    @Override
    public Account account() {
        return this.loggedInUser;
    }


}
