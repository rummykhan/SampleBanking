package banking.helpers;

public enum Action {

    CREATE_ACCOUNT(1),
    LOGIN_ACCOUNT(2),
    LOGOUT_ACCOUNT(2),
    BALANCE(1),
    EXIT(0);

    private final int value;

    private Action(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
