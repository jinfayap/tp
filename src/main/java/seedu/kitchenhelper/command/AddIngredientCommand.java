package seedu.kitchenhelper.command;

import seedu.kitchenhelper.storage.Storage;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.object.ingredient.Dairy;
import seedu.kitchenhelper.object.ingredient.Drink;
import seedu.kitchenhelper.object.ingredient.Fruit;
import seedu.kitchenhelper.object.ingredient.Meat;
import seedu.kitchenhelper.object.ingredient.Miscellaneous;
import seedu.kitchenhelper.object.ingredient.Staple;
import seedu.kitchenhelper.object.ingredient.Vegetable;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Adds the ingredient to the inventory list.
 */
public class AddIngredientCommand extends Command {
    
    public final Logger kitchenLogs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "addingredient";
    public static final String MESSAGE_SUCCESS =
            "You have added Ingredient:%s Category:%s Quantity:%d Price:$%.2f Expiry:%s to the inventory list";
    public static final String COMMAND_DESC = "Adds a ingredient to the inventory list.";
    public static final String COMMAND_PARAMETER = "/n INGREDIENT /c CATEGORY /q QUANTITY /p PRICE /e EXPIRY";
    public static final String COMMAND_EXAMPLE = "Example: addingredient /n Beef /c Meat /q 1 /p 13.5 /e 2020-02-13";
    public static final String COMMAND_FORMAT =
            String.format("%s %s\n%s", COMMAND_DESC, COMMAND_PARAMETER, COMMAND_EXAMPLE);
    
    public final String logAddIngredient = "A new ingredient has been added";
    
    private String ingredientName;
    private String categoryName;
    private int quantity;
    private double price;
    private String expiry;
    
    /**
     * Constructor for Add Inventory Command.
     *
     * @param ingredientName name of the ingredient.
     * @param categoryName   category of the ingredient.
     * @param quantity       number of serving of ingredient.
     * @param price          cost of the ingredient.
     * @param expiry         ingredient expiry date.
     */
    public AddIngredientCommand(String ingredientName, String categoryName, int quantity, double price, String expiry) {
        this.ingredientName = ingredientName;
        this.categoryName = categoryName;
        this.quantity = quantity;
        this.price = price;
        this.expiry = expiry;
    }
    
    /**
     * Adds the ingredient to the ArrayList according to the category type.
     *
     * @param category       category of the ingredient.
     * @param ingredientList the ArrayList to store ingredients.
     */
    public void addToCategory(String category, ArrayList<Ingredient> ingredientList) {
        switch (category.toLowerCase()) {
        case Meat.INGREDIENT_WORD:
            ingredientList.add(new Meat(ingredientName, categoryName, quantity, price, expiry));
            Storage.saveIngredientData(ingredientList);
            break;
        case Vegetable.INGREDIENT_WORD:
            ingredientList.add(new Vegetable(ingredientName, categoryName, quantity, price, expiry));
            Storage.saveIngredientData(ingredientList);
            break;
        case Drink.INGREDIENT_WORD:
            ingredientList.add(new Drink(ingredientName, categoryName, quantity, price, expiry));
            Storage.saveIngredientData(ingredientList);
            break;
        case Fruit.INGREDIENT_WORD:
            ingredientList.add(new Fruit(ingredientName, categoryName, quantity, price, expiry));
            Storage.saveIngredientData(ingredientList);
            break;
        case Staple.INGREDIENT_WORD:
            ingredientList.add(new Staple(ingredientName, categoryName, quantity, price, expiry));
            Storage.saveIngredientData(ingredientList);
            break;
        case Dairy.INGREDIENT_WORD:
            ingredientList.add(new Dairy(ingredientName, categoryName, quantity, price, expiry));
            Storage.saveIngredientData(ingredientList);
            break;
        case Miscellaneous.INGREDIENT_WORD:
            // Fallthrough
        default:
            this.categoryName = Miscellaneous.INGREDIENT_WORD;
            ingredientList.add(new Miscellaneous(ingredientName, categoryName, quantity, price, expiry));
            Storage.saveIngredientData(ingredientList);
            break;
        }
        
    }
    
    /**
     * {@inheritDoc}
     *
     * @param ingredientList list of ingredients.
     * @param recipeList     list of recipes.
     * @param choreList      list of chores.
     * @return the success message of adding inventory.
     */
    @Override
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) {
        addToCategory(categoryName, ingredientList);
        kitchenLogs.info(logAddIngredient);
        return new CommandResult(String.format(MESSAGE_SUCCESS, ingredientName, categoryName, quantity, price, expiry));
    }
}
