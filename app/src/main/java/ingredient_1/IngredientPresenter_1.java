package ingredient_1;

import services.IngredientService;

public class IngredientPresenter_1 {

    private IngredientView_1 mIngredientView_1;

    private IngredientService ingredientService;

    public IngredientPresenter_1(IngredientView_1 ingredientView_1){
        mIngredientView_1 = ingredientView_1;
    }

    public void openIngredient_3() {
        mIngredientView_1.openIngredient_3();
    }

    public void openDish_1(){
        mIngredientView_1.openDish_1();
    }

    public void addNewIngredient(String ingName){
        ingredientService = new IngredientService();
        ingredientService.saveIngredient(ingName);
        openIngredient_3();
    }

    public void ingredientNameValid(String ing_name){
        if (ing_name.isEmpty()){
            mIngredientView_1.nameInvalid();
        }
        else
            mIngredientView_1.nameValid();
    }

}
