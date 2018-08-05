package dish_6;

import entity.Dish;
import entity.DishDao;
import entity.Ingredient;
import entity.IngredientDao;
import ru.arturvasilov.rxloader.LifecycleHandler;
import services.App;
import services.DatabaseService;

public class DishPresenter_6 {

    LifecycleHandler mLifecycleHandler;

    DishView_6 mDishView_6;

    DatabaseService db = App.getInstance().getDatabaseService();

    Dish dish;

    DishDao dishDao;

    Ingredient ingredient;

    IngredientDao ingredientDao;

    public DishPresenter_6(LifecycleHandler lifecycleHandler, DishView_6 dishView_6){
        mLifecycleHandler = lifecycleHandler;
        mDishView_6 = dishView_6;
        dishDao = db.dishDao();
        ingredientDao = db.ingredientDao();
    }

    public void addNewIngredient(String name){
        dish = dishDao.getByName(mDishView_6.findDish());
        ingredient = new Ingredient();
        ingredient.ingredient = name;
        ingredient.dishId = dishDao.getIdByName(mDishView_6.findDish());
        ingredientDao.insert(ingredient);
        mDishView_6.openDish_4();
    }

}
