package banking;

import banking.account.Account;
import banking.account.AccountManager;
import banking.auth.Authenticator;
import banking.helpers.Action;
import banking.helpers.Constants;
import banking.menu.AccountBalanceMenu;
import banking.menu.CreateAccountMenu;
import banking.menu.Menu;
import banking.repository.AccountRepository;
import banking.ui.UserInterface;

public class BankingFacade {
    AccountRepository accountRepository = null;
    Authenticator authenticator = null;
    UserInterface userInterface = null;

    Menu createAccountMenu;
    Menu accountBalanceMenu;

    private BankingFacade() {

    }

    public BankingFacade(AccountRepository accountRepository,
                         Authenticator authenticator,
                         UserInterface userInterface
    ) {
        this.accountRepository = accountRepository;
        this.authenticator = authenticator;
        this.userInterface = userInterface;

        this.createAccountMenu = new CreateAccountMenu(this.userInterface);
        this.accountBalanceMenu = new AccountBalanceMenu(this.userInterface);

    }

    public void start() {
        this.showCreateAccountMenu();
        Action action = this.createAccountMenu.getUserInput();

        switch (action) {

            case CREATE_ACCOUNT:
                this.createAccount();
                break;

            case LOGIN_ACCOUNT:
                this.loginAccount();
                break;

            case EXIT:
                this.exitApplication();
                break;

        }
    }

    private void showCreateAccountMenu() {
        this.createAccountMenu.show();
    }

    private void createAccount() {
        Account account = AccountManager.newAccount();

        this.accountRepository.createAccount(account);

        this.showAccount(account);

        this.start();
    }

    private void showAccount(Account account) {
        this.prepareOutput();
        this.userInterface.println(Constants.MESSAGE_CARD_CREATED);
        this.userInterface.println(Constants.LABEL_YOUR_CARD);
        this.userInterface.println(account.getCardNumber());
        this.userInterface.println(Constants.LABEL_YOUR_PIN);
        this.userInterface.println(account.getPinCode());
    }

    private void loginAccount() {
        this.prepareOutput();

        this.userInterface.println(Constants.QUESTION_YOUR_CARD);
        String cardNumber = this.userInterface.readLine();

        this.userInterface.println(Constants.QUESTION_YOUR_PIN);
        String pinCode = this.userInterface.readLine();

        if (this.authenticator.login(cardNumber, pinCode)) {
            this.prepareOutput();
            this.userInterface.println(Constants.MESSAGE_LOGGED_IN);
            this.showAccountMenu();
        } else {
            this.prepareOutput();
            this.userInterface.println(Constants.MESSAGE_WRONG_CARD);
            this.start();
        }
    }

    private void showAccountMenu() {
        this.accountBalanceMenu.show();
        Action action = this.accountBalanceMenu.getUserInput();

        switch (action) {

            case BALANCE:
                this.showBalance();
                this.showAccountMenu();
                break;

            case LOGOUT_ACCOUNT:
                this.logoutAccount();
                this.start();
                break;

            case EXIT:
                this.exitApplication();
                break;

        }
    }

    private void showBalance() {
        Account account = this.authenticator.account();
        this.prepareOutput();
        this.userInterface.println(Constants.LABEL_BALANCE + " " + account.getBalance());
    }

    private void logoutAccount() {
        this.authenticator.logout();
        this.prepareOutput();
        this.userInterface.println(Constants.MESSAGE_LOGGED_OUT);
    }

    private void exitApplication() {
        this.prepareOutput();
        this.userInterface.println(Constants.MESSAGE_BYE);
        System.exit(0);
    }

    private void prepareOutput(){
        this.userInterface.println("");
    }
}
