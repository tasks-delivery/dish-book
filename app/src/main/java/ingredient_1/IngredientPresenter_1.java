package ingredient_1;

import entity.Dish;
import entity.DishDao;
import entity.Ingredient;
import entity.IngredientDao;
import ru.arturvasilov.rxloader.LifecycleHandler;
import services.App;
import services.DatabaseService;

public class IngredientPresenter_1 {

    LifecycleHandler mLifecycleHandler;

    IngredientView_1 mIngredientView_1;

    DatabaseService db = App.getInstance().getDatabaseService();

    Dish dish;

    DishDao dishDao;

    Ingredient ingredient;

    IngredientDao ingredientDao;

    public IngredientPresenter_1(LifecycleHandler lifecycleHandler, IngredientView_1 ingredientView_1){
        mLifecycleHandler = lifecycleHandler;
        mIngredientView_1 = ingredientView_1;
        dishDao = db.dishDao();
        ingredientDao = db.ingredientDao();
    }

    public void addNewIngredient(String name){
        ingredient = new Ingredient();
        ingredient.ingredient = name;
        ingredient.dishId = (int) Math.random() + 1;
        ingredientDao.insert(ingredient);
        mIngredientView_1.openIngredient_3();
       // mIngredientView_1.openDish_4();

        /*
        dish = dishDao.getByName(mIngredientView_1.findDish());
        ingredient = new Ingredient();
        ingredient.ingredient = name;
        ingredient.dishId = dishDao.getIdByName(mIngredientView_1.findDish());
        ingredientDao.insert(ingredient);
        mIngredientView_1.openDish_4();
        */
    }

}
