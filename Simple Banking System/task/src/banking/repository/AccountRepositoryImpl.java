package banking.repository;

import banking.account.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryImpl implements AccountRepository {

    private final Map<String, Account> repository = new HashMap<>();

    @Override
    public Account createAccount(Account account) {
        this.repository.put(account.getCardNumber(), account);

        return account;
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return this.repository.get(accountNumber);
    }
}
