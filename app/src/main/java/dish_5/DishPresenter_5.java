package dish_5;

import android.support.annotation.NonNull;

import java.util.List;

import entity.Dish;
import entity.DishDao;
import entity.Ingredient;
import entity.IngredientDao;
import ru.arturvasilov.rxloader.LifecycleHandler;
import services.App;
import services.DatabaseService;

public class DishPresenter_5 {

    LifecycleHandler mLifecycleHandler;

    DishView_5 mDishView_5;

    DatabaseService db = App.getInstance().getDatabaseService();

    Dish dish;

    DishDao dishDao;

    List<Ingredient> ingredients;

    IngredientDao ingredientDao;


    public DishPresenter_5(@NonNull LifecycleHandler lifecycleHandler, @NonNull DishView_5 dishView_5){
        mLifecycleHandler = lifecycleHandler;
        mDishView_5 = dishView_5;
        dishDao = db.dishDao();
        ingredientDao = db.ingredientDao();
    }

    public void openDish_4(){
        mDishView_5.openDish_4();
    }

    public void dishNameValid(String name){
        if (name.isEmpty()){
            mDishView_5.nameInvalid();
        }
        else
            mDishView_5.nameValid();
    }

    public String shownDescription(){
        return dishDao.getByName(mDishView_5.findDish()).description;
    }

    public void updateDish(String name, String description) {
        dish = dishDao.getByName(mDishView_5.findDish());
        if (dishDao.getAllNames().contains(name) == true){
            mDishView_5.shownDishDialog1();
        }else {
            dish.id = dish.getId();
            dish.dish_name = name;
            dish.description = description;
            dishDao.update(dish);
            mDishView_5.openDish_3();
        }
    }

    public void deleteDish(){
        dish = dishDao.getByName(mDishView_5.findDish());
        ingredients = ingredientDao.getIngredientByDishId((dishDao.getIdByName(mDishView_5.findDish())));
        ingredientDao.deleteAllIngredients(ingredients);
        dishDao.delete(dish);
        mDishView_5.openDish_3();
    }

}
