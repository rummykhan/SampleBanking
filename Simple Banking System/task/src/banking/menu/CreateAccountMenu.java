package banking.menu;

import banking.helpers.Action;
import banking.helpers.Constants;
import banking.ui.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class CreateAccountMenu extends BankMenu implements Menu {

    public CreateAccountMenu(UserInterface userInterface) {
        super(userInterface);
    }

    @Override
    public List<Option> getOptionList() {

        List<Option> optionList = new ArrayList<>();

        optionList.add(new OptionImpl(Action.CREATE_ACCOUNT, Constants.MENU_CREATE_ACCOUNT));
        optionList.add(new OptionImpl(Action.LOGIN_ACCOUNT, Constants.MENU_LOG_IN));
        optionList.add(new OptionImpl(Action.EXIT, Constants.MENU_EXIT));

        return optionList;
    }


}
