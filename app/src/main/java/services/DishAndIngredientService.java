package services;

import java.util.List;

import entity.Dish;
import entity.DishDao;
import entity.Ingredient;
import entity.IngredientDao;

public class DishAndIngredientService {

    private Dish dish;

    private Ingredient ingredient;

    private DatabaseService databaseService = App.getInstance().getDatabaseService();

    private DishDao dishDao = databaseService.dishDao();

    private IngredientDao ingredientDao = databaseService.ingredientDao();

    private List<Ingredient> ingredients;

    private IngredientService ingredientService;

    public void deleteDish(String dish_name){
        dish = dishDao.findDishByName(dish_name);
       // ingredients = ingredientDao.getIngredientByDishId((dishDao.findIdByName(dish_name)));
        ingredientDao.deleteAllIngredients(ingredients);
        dishDao.delete(dish);
    }

    public void deleteIngredientFromDish(int dish_id){
        ingredient = ingredientDao.findIngredientByDishName(dish_id);
        //ingredient.setDish_id(0);
        ingredientDao.update(ingredient);
    }

    public void addIngredientToDish(String dish_name, String ing_name) {
       dish = dishDao.findDishByName(dish_name);
       ingredient = ingredientDao.findIngredientByName(ing_name);
       ingredient.id = dish.getId();
       ingredientDao.update(ingredient);
    }

    public List<String> loadAllIngredientsWithoutDish(int dish_id){
        return ingredientDao.findIngredientsNotAssigneToDishByDishName(dish_id);
    }

    public List<String> findIngredientsOfDish(int dish_id){
        return ingredientDao.findIngredientsByDishName(dish_id);
    }
}
