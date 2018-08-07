package ingredient_3;

import java.util.List;

import entity.Dish;
import entity.DishDao;
import entity.Ingredient;
import entity.IngredientDao;
import ru.arturvasilov.rxloader.LifecycleHandler;
import services.App;
import services.DatabaseService;

public class IngredientPresenter_3 {

    LifecycleHandler mLifecycleHandler;

    IngredientView_3 mIngredientView_3;

    DatabaseService db = App.getInstance().getDatabaseService();


    Dish dish;

    DishDao dishDao;

    Ingredient ingredient;

    IngredientDao ingredientDao;


    public IngredientPresenter_3(LifecycleHandler lifecycleHandler, IngredientView_3 ingredientView_3){
        mLifecycleHandler = lifecycleHandler;
        mIngredientView_3 = ingredientView_3;
        dishDao = db.dishDao();
        ingredientDao = db.ingredientDao();
    }

    public List<String> loadIngredients() {
        System.out.println(ingredientDao.getAllIngredients());

        return ingredientDao.getAllIngredients();
    }

    public void removeIngredient(String ing_name){
        ingredient = ingredientDao.getIngredientByName(ing_name);
        ingredientDao.delete(ingredient);
    }

    public void openIngreidnet_1(){
        mIngredientView_3.openIngredient_1();
    }

    public Boolean ingredientExists(String ingredient){
        if (ingredientDao.getAll().contains(ingredient) == true){
            mIngredientView_3.openDish_4();
            return true;
        }else {
            return false;
        }

       // ingredientDao.getAllIngredients().contains()
    }

    public void addNewIngredient(String name){
        ingredient = new Ingredient();
        ingredient.ingredient = name;
        ingredient.dishId = (int) Math.random() + 1;
        ingredientDao.insert(ingredient);
      //  mIngredientView_3.openIngredient_3();
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
