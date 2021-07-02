package banking.ui;

import banking.helpers.Action;
import banking.menu.Menu;

import java.util.Scanner;

public class UserInterfaceImpl implements UserInterface {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Action showMenu(Menu menu) {
        menu.show();

        return menu.getUserInput();
    }

    @Override
    public void println(String log) {
        System.out.println(log);
    }

    @Override
    public void print(String log) {
        System.out.print(log);
    }

    @Override
    public int readInt() {
        int nextInt = scanner.nextInt();
        // throw away the \n char,
        // https://stackoverflow.com/questions/23450524/java-scanner-doesnt-wait-for-user-input
        scanner.nextLine();

        return nextInt;
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
