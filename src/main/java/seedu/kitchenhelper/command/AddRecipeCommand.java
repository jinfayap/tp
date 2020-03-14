package seedu.kitchenhelper.command;

import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.storage.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Perform addition-related commands.
 */
public class AddRecipeCommand extends Command {

    public static final Logger kitchenLogs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "addrecipe";
    public static final String COMMAND_DESC = "Adds a recipe to the recipe list.";
    public static final String COMMAND_PARAMETER
            = "/n RECIPENAME /i INGRNAME:QUANTITY:CATEGORY <optional: , INGRNAME:QUANTITY:CATEGORY>";
    public static final String COMMAND_EXAMPLE
            = "Example: addrecipe /n Chicken Salad /i Chicken Breast:2:meat, Lettuce:4:vegetable";
    public static final String COMMAND_FORMAT =
            String.format("%s %s\n%s", COMMAND_DESC, COMMAND_PARAMETER, COMMAND_EXAMPLE);
    public final String logAddRecipe = "A new recipe has been added";
    public HashMap<String[], Integer> parsedIngr;

    /**
     * Set the object's type.
     */
    public void setTypeOfObject() {
        objectType = "recipe";
    }

    /**
     * Set the attributes of the Command class.
     *
     * @param rawString full user input string.
     * @param ingrAndQty a hashmap of ingredient with [ingredientName, ingredientCategory] as key
     *                   and ingredientQuantity as value
     */
    public void setAttributesOfCmd(String rawString, HashMap<String[], Integer> ingrAndQty) {
        setTypeOfObject();
        setObjectVariables(rawString);
        setAction();
        this.parsedIngr = ingrAndQty;
    }

    public void setObjectVariables(String rawString) {
        objectVariables = rawString;
    }

    public void setAction() {
        actionType = COMMAND_WORD;
    }

    public String addRecipe(String attributes, ArrayList<Recipe> recipeList) {

        Recipe freshRecipe = new Recipe();
        freshRecipe.setRecipeName(attributes);
        freshRecipe.addIngredientsToRecipe(parsedIngr);
        recipeList.add(freshRecipe);
        Storage.saveRecipeData(recipeList);
        assert freshRecipe.recipeName.length() > 0;
        assert recipeList.size() > 0;
        kitchenLogs.info(logAddRecipe);
        return freshRecipe.recipeName + " Recipe has been created with "
                + freshRecipe.recipeIngrQty + " ingredients inside.";
    }
    
    @Override
    public String addChore(String objectVariables, ArrayList<Chore> choreList) {
        String feedbackToUser;
        try {
            String[] objectTypeAndOthers = objectVariables.split("chore ", 2);
            String[] descriptionAndDate = objectTypeAndOthers[1].trim().split("/by");
            String description = descriptionAndDate[0].trim();
            String date = descriptionAndDate[1].trim();
            Chore newChore = new Chore(description, date);
            choreList.add(newChore);
            newChore.setEditType(COMMAND_WORD);
            feedbackToUser = String.format(Chore.MESSAGE_SUCCESS,
                    newChore.editType, newChore, choreList.size(), newChore.checkSingular(choreList));
        } catch (IndexOutOfBoundsException e) {
            feedbackToUser = "You need to add a date with \"/by\" in the description.";
        }
        return feedbackToUser;
    }
    
    @Override
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) throws KitchenHelperException {
        String message = addRecipe(this.objectVariables,recipeList);
        return new CommandResult(message);
    }
}
