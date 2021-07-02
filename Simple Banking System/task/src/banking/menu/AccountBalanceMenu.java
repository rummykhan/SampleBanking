package banking.menu;

import banking.helpers.Action;
import banking.helpers.Constants;
import banking.ui.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class AccountBalanceMenu extends BankMenu implements Menu {

    public AccountBalanceMenu(UserInterface userInterface) {
        super(userInterface);
    }

    @Override
    public List<Option> getOptionList() {

        List<Option> optionList = new ArrayList<>();

        optionList.add(new OptionImpl(Action.BALANCE, Constants.MENU_BALANCE));
        optionList.add(new OptionImpl(Action.LOGOUT_ACCOUNT, Constants.MENU_LOGOUT));
        optionList.add(new OptionImpl(Action.EXIT, Constants.MENU_EXIT));

        return optionList;
    }


}
