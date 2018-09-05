package ingredient_3;

import android.util.SparseBooleanArray;

import java.util.List;

import services.DishAndIngredientService;
import services.IngredientService;

public class IngredientPresenter_3 {

    private IngredientService ingredientService;

    private IngredientView_3 mIngredientView_3;

    private DishAndIngredientService dishAndIngredientService;

    public IngredientPresenter_3(IngredientView_3 ingredientView_3){
        mIngredientView_3 = ingredientView_3;
    }

    public void openIngredient_1(){
        mIngredientView_3.openIngredient_1();
    }

    public void openDish_4(){
        mIngredientView_3.openDish_4();
    }

    public void navigateFromIngredient1(){
        mIngredientView_3.navigateFromIngredient1();
    }

    public void navigateFromDish4RemoveIng(){
        mIngredientView_3.navigateFromDish4RemoveIng();
    }

    public void navigateFromDish1(){
        mIngredientView_3.navigateFromDish1();
    }

    public void navigateFormDish4AddIng(){
        mIngredientView_3.navigateFormDish4AddIng();
    }

    public List<String> loadAllIngredients() {
        ingredientService = new IngredientService();
        return ingredientService.findAllIngredientsNames();
    }

    public List<String> loadAllIngredientsWithoutDish() {
        dishAndIngredientService = new DishAndIngredientService();
      //  return dishAndIngredientService.loadAllIngredientsWithoutDish(mIngredientView_3.findDish());
        return null;
    }

    public List<String> loadIngredientsOfDish() {
        dishAndIngredientService = new DishAndIngredientService();
      //  return dishAndIngredientService.findIngredientsOfDish(mIngredientView_3.findDish());
        return null;
    }

    public void removeSelectedIngredients(int ing_length, SparseBooleanArray ing_position) {
        ingredientService = new IngredientService();
        for (int i = 0; i < ing_length; i++)
            if (ing_position.get(i)) {
                String item = loadAllIngredients().get(i);
                ingredientService.deleteIngredient(item);
            }
    }

    public void removeIngredientFromDish(int ing_length, SparseBooleanArray ing_position){
        dishAndIngredientService = new DishAndIngredientService();
      //  ingredientService = new IngredientService();
        for (int i = 0; i < ing_length; i++)
            if (ing_position.get(i)) {
                String item = loadAllIngredients().get(i);

              //  dishAndIngredientService.findIngredientsOfDish(item);
            }
    }


    public void addSelectedIngredients(int ing_length, SparseBooleanArray ing_position) {
        dishAndIngredientService = new DishAndIngredientService();
        for (int i = 0; i < ing_length; i++)
            if (ing_position.get(i)) {
                String item = loadAllIngredients().get(i);
                dishAndIngredientService.addIngredientToDish(mIngredientView_3.findDish(),item);
            }
    }


}
