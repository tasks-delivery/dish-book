package ingredient_4;

import ru.arturvasilov.rxloader.LifecycleHandler;
import services.App;
import services.DatabaseService;

public class IngredientPresenter_4 {

    LifecycleHandler mLifecycleHandler;

    IngredientView_4 mIngredientView_4;

    DatabaseService db = App.getInstance().getDatabaseService();

    /*
    Dish dish;

    DishDao dishDao;

    Ingredient ingredient;

    IngredientDao ingredientDao;
    */

    public IngredientPresenter_4(LifecycleHandler lifecycleHandler, IngredientView_4 ingredientView_4){
        mLifecycleHandler = lifecycleHandler;
        mIngredientView_4 = ingredientView_4;
        // dishDao = db.dishDao();
        //    ingredientDao = db.ingredientDao();
    }

}
