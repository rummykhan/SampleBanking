package banking.menu;

import banking.helpers.Action;

import java.util.List;

public interface Menu {

    public List<Option> getOptions();

    public void show();

    public Action getUserInput();
}
