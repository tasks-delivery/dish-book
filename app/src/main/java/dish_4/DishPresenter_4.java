package dish_4;

import java.util.List;

import services.DishAndIngredientService;
import services.DishService;

public class DishPresenter_4 {

    private DishView_4 mDishView_4;

    private DishAndIngredientService dishAndIngredientService;

    private DishService dishService;

    public DishPresenter_4(DishView_4 dishView_4) {
        mDishView_4 = dishView_4;
    }

    public void openIngredient_3FromRemoveIngredient(){
        mDishView_4.openIngredient_3FromRemoveIngredient();
    }

    public void openIngredient_3FromAddIngredient(){
        mDishView_4.openIngredient_3FromAddIngredient();
    }

    public void openDish_5() {
        mDishView_4.openDish_5();
    }

    public void openDish_3(){
        mDishView_4.openDish_3();
    }

    public String shownDescription() {
        dishService = new DishService();
        return dishService.shownDescription(mDishView_4.findDish());
    }

    public List<String> loadIngredientsOfDish() {
        dishAndIngredientService = new DishAndIngredientService();
       // return dishAndIngredientService.findIngredientsOfDish(mDishView_4.findDish());
        return null;
    }

}
