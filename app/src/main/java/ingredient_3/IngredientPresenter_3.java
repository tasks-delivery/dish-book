package ingredient_3;

import android.util.SparseBooleanArray;

import java.util.List;

import services.DishAndIngredientService;
import services.IngredientService;

public class IngredientPresenter_3 {

    private IngredientService ingredientService;

    private IngredientView_3 mIngredientView_3;

    private DishAndIngredientService dishAndIngredientService;

    public IngredientPresenter_3(IngredientView_3 ingredientView_3) {
        mIngredientView_3 = ingredientView_3;
    }

    public void openIngredient_1() {
        mIngredientView_3.openIngredient_1();
    }

    public void openDish_4() {
        mIngredientView_3.openDish_4();
    }

    public void navigateFromIngredient1() {
        mIngredientView_3.navigateFromIngredient1();
    }

    public void navigateFromDish4RemoveIng() {
        mIngredientView_3.navigateFromDish4RemoveIng();
    }

    public void navigateFromDish1() {
        mIngredientView_3.navigateFromDish1();
    }

    public void navigateFormDish4AddIng() {
        mIngredientView_3.navigateFormDish4AddIng();
    }

    public List<String> loadAllIngredients() {
        ingredientService = new IngredientService();
        return ingredientService.findAllIngredients();
    }

    public List<String> loadAllIngredientsWithoutDish() {
        dishAndIngredientService = new DishAndIngredientService();
        return dishAndIngredientService.findAllFreeIngredientNamesByDishName(mIngredientView_3.findDish());
    }

    public List<String> loadIngredientsOfDish() {
        dishAndIngredientService = new DishAndIngredientService();
        return dishAndIngredientService.findAllIngredientNamesOfDishByDishName(mIngredientView_3.findDish());
    }

    public void removeSelectedIngredient(String ingName){
        ingredientService = new IngredientService();
        ingredientService.deleteIngredient(ingName);
    }

    public void removeIngredientFromDish (String ingName){
        dishAndIngredientService = new DishAndIngredientService();
        dishAndIngredientService.deleteIngredientFromDish(mIngredientView_3.findDish(), ingName);
    }

    public void testData(){
        ingredientService = new IngredientService();
        ingredientService.saveIngredient("first");
        ingredientService.saveIngredient("second");
        ingredientService.saveIngredient("third");
        ingredientService.saveIngredient("four");
    }

    public void addSelectedIngredients ( int ing_length, SparseBooleanArray ing_position){
        dishAndIngredientService = new DishAndIngredientService();
        for (int i = 0; i < ing_length; i++)
            if (ing_position.get(i)) {
                String item = loadAllIngredients().get(i);
                dishAndIngredientService.assignIngredientToDish(mIngredientView_3.findDish(), item);
            }
    }

}


