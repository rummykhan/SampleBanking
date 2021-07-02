package banking.menu;

import banking.helpers.Action;

public class OptionImpl implements Option {
    private final Action action;
    private final String option;

    public OptionImpl(Action action, String option) {
        this.action = action;
        this.option = option;
    }

    @Override
    public Action getAction() {
        return this.action;
    }

    @Override
    public String getOption() {
        return this.option;
    }

    @Override
    public String toString() {
        return this.getAction().getValue() + ". " + this.getOption();
    }
}
