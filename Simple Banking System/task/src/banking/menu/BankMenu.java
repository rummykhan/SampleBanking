package banking.menu;

import banking.helpers.Action;
import banking.helpers.Constants;
import banking.ui.UserInterface;

import java.util.List;
import java.util.Optional;

public abstract class BankMenu {

    protected UserInterface userInterface;

    public BankMenu(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void show() {
        this.userInterface.println("");
        for (Option option : this.getOptions()) {
            this.userInterface.println(String.valueOf(option));
        }
        this.userInterface.print(Constants.SYMBOL_INPUT);
    }

    public Action getUserInput() {
        int userInput = this.userInterface.readInt();

        Optional<Option> optionalOption = this.getOptionList().stream()
                .filter(option -> option.getAction().getValue() == userInput)
                .findFirst();

        return optionalOption.orElse(null).getAction();
    }

    public abstract List<Option> getOptionList();

    public List<Option> getOptions() {
        return this.getOptionList();
    }
}
