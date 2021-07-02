package banking.repository;

import banking.account.Account;

public interface AccountRepository {

    public Account createAccount(Account account);

    public Account getAccountByNumber(String accountNumber);
}
