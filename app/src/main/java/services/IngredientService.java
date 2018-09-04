package services;

import java.util.List;

import entity.Ingredient;
import entity.IngredientDao;

public class IngredientService {

    private Ingredient ingredient;

    private DatabaseService databaseService = App.getInstance().getDatabaseService();

    private IngredientDao ingredientDao = databaseService.ingredientDao();

    public List<String> findAllIngredientsNames(){
        return ingredientDao.findAllIngredientsNames();
    }

    public void createIngredient(String ing_name) {
        ingredient = new Ingredient(ing_name);
     //   ingredient.dishId = 5;
        ingredientDao.insert(ingredient);
    }

    public void deleteIngredient(String ing_name){
        ingredient = ingredientDao.findIngredientByName(ing_name);
        ingredientDao.delete(ingredient);
    }

}
