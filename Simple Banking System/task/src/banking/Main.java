package banking;

import banking.auth.Authenticator;
import banking.auth.AuthenticatorImpl;
import banking.infrastructure.DBSource;
import banking.infrastructure.DBSourceImpl;
import banking.infrastructure.Table;
import banking.repository.AccountRepository;
import banking.repository.DatabaseAccountRepositoryImpl;
import banking.ui.UserInterface;
import banking.ui.UserInterfaceImpl;

public class Main {

    public static void main(String[] args) {

        String filename = null;

        if (args.length > 1) {
            filename = args[1];
        }

        //filename = "/Users/manzoorr/Documents/sample-banking-system.db";

        UserInterface userInterface = new UserInterfaceImpl();

        DBSource dbSource = DBSourceImpl.getInstance(filename, userInterface);

        AccountRepository accountRepository = new DatabaseAccountRepositoryImpl(dbSource);
        Authenticator authenticator = new AuthenticatorImpl(accountRepository, userInterface);

        BankingFacade bankingFacade = new BankingFacade(accountRepository, authenticator, userInterface);
        bankingFacade.start();
    }
}