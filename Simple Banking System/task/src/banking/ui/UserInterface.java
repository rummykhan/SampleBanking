package banking.ui;

import banking.helpers.Action;
import banking.menu.Menu;

public interface UserInterface {

    public Action showMenu(Menu menu);

    public void println(String log);

    public void print(String log);

    public int readInt();

    public String readLine();
}
