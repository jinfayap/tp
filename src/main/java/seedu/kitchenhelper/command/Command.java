package seedu.kitchenhelper.command;

import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.storage.Storage;

import java.util.ArrayList;

/**
 * Abstract class to represent user's command.
 */
public abstract class Command {
    
    public static String actionType; //add, delete, list
    public static String objectType; //ingredient, recipe, chore
    public static String objectVariables;

    public Command() {
    }

    public String getActionType() {
        return actionType;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getObjectVariables() {
        return objectVariables;
    }

    /**
     * Set the Action Type for the Command.
     */

    public void setActionType(String command) {
        actionType = command;
    }

    /**
     * Set the Object Type for the Command.
     *
     * @param type the name of the object type
     */

    public void setObjectType(String type) {
        objectType = type;
    }

    /**
     * Set the Object Variables for Command.
     *
     * @param attribute the values for the variables, can be recipe or ingredient names
     */
    public void setObjectVariables(String attribute) {
        objectVariables = attribute;
    }


    public String addChore(String objectVariables, ArrayList<Chore> choreList) {
        return "";
    }

    public String deleteChore(String objectVariables, ArrayList<Chore> choreList) {
        return "";
    }

    public String listIngredients(ArrayList<Ingredient> ingredientsList) {
        return "";
    }

    public String listRecipe(String objectVariables, ArrayList<Recipe> recipeList) {
        return "";
    }

    public String listChore(ArrayList<Chore> choreList) {
        return "";
    }

    /**
     * Runs the command given by user.
     *
     * @param ingredientList list of ingredients.
     * @param recipeList list of recipes.
     * @param choreList list of chores.
     * @return cmdResult response given to user after successful execution.
     * @throws KitchenHelperException if the command is invalid.
     */
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) throws KitchenHelperException {
        String feedbackToUser = "";

        if (actionType.equals(DeleteCommand.COMMAND_WORD) && objectType.equals("chore")) {
            feedbackToUser = deleteChore(objectVariables, choreList);
        } else if (actionType.equals(ListCommand.COMMAND_WORD) && objectType.equals("ingredient")) {
            feedbackToUser = listIngredients(ingredientList);
        } else if (actionType.equals(ListCommand.COMMAND_WORD) && objectType.equals("recipe")) {
            feedbackToUser = listRecipe(objectVariables, recipeList);
        } else if (actionType.equals(ListCommand.COMMAND_WORD) && objectType.equals("chore")) {
            feedbackToUser = listChore(choreList);
        }
        CommandResult cmdResult = new CommandResult(feedbackToUser);
        return cmdResult;
    }
    
    public void executeIngredientStorage(ArrayList<Ingredient> ingredientList, Storage storage){
    
    }
    
    public void executeChoreStorage(ArrayList<Chore> choreList, Storage storage){
    
    }
    
    public void executeRecipeStorage(ArrayList<Recipe> recipeList, Storage storage){
    
    }
}
