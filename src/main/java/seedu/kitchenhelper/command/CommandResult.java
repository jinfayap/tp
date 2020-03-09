package seedu.kitchenhelper.command;

import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.storage.Storage;

import java.util.ArrayList;

public class CommandResult {
    
    public final String feedbackToUser;

    public void executeIngredientStorage(ArrayList<Ingredient> ingredientList, Storage storage){

    }

    public void executeChoreStorage(ArrayList<Chore> choreList, Storage storage){

    }
    
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }
    
}
