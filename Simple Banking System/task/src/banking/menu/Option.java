package banking.menu;

import banking.helpers.Action;

public interface Option {

    public Action getAction();
    public String getOption();
    public String toString();
}
