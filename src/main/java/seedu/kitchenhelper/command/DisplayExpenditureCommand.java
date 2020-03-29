package seedu.kitchenhelper.command;

import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Expenditure;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.ui.Ui;

import java.util.ArrayList;

public class DisplayExpenditureCommand extends Command {

    public static final String COMMAND_WORD = "expenditure";
    public static final String COMMAND_DESC = "Displays the expenditure in that week.";
    public static final String COMMAND_FORMAT = "expenditure *No parameter";
    public static final String MESSAGE_USAGE = String.format("%s: %s", COMMAND_WORD, COMMAND_DESC) + Ui.LS + String
            .format("Example: %s", COMMAND_WORD);

    public static final String EXPENDITURE = "This is the total amount you spent on buying "
            + "Ingredients so far this week: $%.2f";
    public static final String AMOUNT_USED_IN_COOKING = "This is the amount you actually spent "
            + "for Ingredients used in your cooking this week: $%.2f";


    @Override
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) {
        Expenditure expenditureObject = new Expenditure();
        String feedbackToUser = String.format(EXPENDITURE, expenditureObject.totalExpenditure) + Ui.LS
                + String.format(AMOUNT_USED_IN_COOKING, expenditureObject.amountUsedInCooking) + Ui.LS;
        return new CommandResult(feedbackToUser);
    }
}
