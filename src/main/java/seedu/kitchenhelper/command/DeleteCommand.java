package seedu.kitchenhelper.command;

import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;

public class DeleteCommand extends Command {
    
    public static final String COMMAND_WORD = "delete";
    public static int quantity;

    /**
     * Set the Object Type for the Command.
     *
     * @param object the name of the object type
     */

    public void setTypeOfObject(String object) {
        String[] attributes = object.split("\\s+");
        if (attributes[0].equalsIgnoreCase("recipe")) {
            objectType = "recipe";
        } else if (attributes[0].equalsIgnoreCase("ingredient")) {
            objectType = "ingredient";
        } else if (attributes[0].equalsIgnoreCase("chore")) {
            objectType = "chore";
        }
    }

    /**
     * Set the other parameters for the DeleteCommand.
     *
     * @param attributes all the parameters for DeleteCommand
     */

    public void setDeleteParams(HashMap<String, String> attributes) {
        setAction();
        setTypeOfObject(attributes.get("type"));
        setOtherAttributes(attributes);
    }


    /**
     * Set the Action Type for the Command (delete).
     */

    public void setAction() {
        actionType = COMMAND_WORD;
    }

    /**
     * Set the other parameters for the DeleteCommand.
     *
     * @param attributes all the parameters for DeleteCommand
     */

    public void setOtherAttributes(HashMap<String, String> attributes) {
        objectVariables = attributes.get("nameToDelete");
        quantity = Integer.parseInt(attributes.get("quantity"));
    }

    /**
     * Delete the ingredient from the ingredient list.
     *
     * @param ingredientName the ingredient name to be deleted
     * @param ingredientsList the list of ingredients
     */

    @Override
    public String deleteIngredient(String ingredientName, ArrayList<Ingredient> ingredientsList) {
        String feedbackToUser = "";
        int count = 0;
        for (Ingredient ingredient : ingredientsList) {
            if (ingredient.getIngredientName().equalsIgnoreCase(ingredientName)) {
                if (quantity <= -1) {
                    ingredientsList.remove(ingredient);
                    feedbackToUser = ingredientName + " has been deleted.";
                } else {
                    ingredient.setQuantity(ingredient.getQuantity() - quantity);
                    feedbackToUser = "The quantity of " + ingredientName + " has been changed!";
                }
                count += 1;
            }
        }

        if (count < 1) {
            feedbackToUser = "This ingredient does not exist! Please type in a correct ingredient name.";
        }

        return feedbackToUser;
    }

    /**
     * Delete the recipe for the recipe list.
     *
     * @param recipeName the recipe name to be deleted
     * @param recipeList the list of recipe
     */

    @Override
    public String deleteRecipe(String recipeName, ArrayList<Recipe> recipeList) {
        String feedbackToUser;
        int count = 0;

        for (Recipe recipe : recipeList) {
            System.out.println(recipe.getRecipeName());
            if (recipe.getRecipeName().equalsIgnoreCase(recipeName.trim())) {
                recipeList.remove(recipe);
                count += 1;
            }
        }
        if (count == 1) {
            feedbackToUser = recipeName + " has been deleted.";
        } else {
            feedbackToUser = "This recipe does not exist! Please type in a correct recipe name.";
        }
        return feedbackToUser;
    }
    
    public void deleteChores(String attributes) {
    
    }

    /**
     * {@inheritDoc}
     *
     * @param ingredientList list of ingredients.
     * @param recipeList     list of recipes.
     * @param choreList      list of chores.
     * @return the execution of the deletion of ingredients or tasks.
     */

    @Override
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) throws KitchenHelperException {
        return super.execute(ingredientList, recipeList, choreList);
    }
}