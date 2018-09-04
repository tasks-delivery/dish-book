package services;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import entity.Dish;
import entity.DishDao;
import entity.Ingredient;
import entity.IngredientDao;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class IngredientServiceTest {

    private IngredientDao ingredientDao;

    private Ingredient ingredient;

    private Dish dish;

    private DishDao dishDao;

    private DishService dishService;

    private IngredientService ingredientService;

    private DatabaseService databaseService = App.getInstance().getDatabaseService();

    @Before
    public  void setUp(){
        dishDao = databaseService.dishDao();
        ingredientDao = databaseService.ingredientDao();
        ingredientService = new IngredientService();
        ingredientDao.deleteAllIngredients(ingredientDao.findAll());


        dishService = new DishService();
        dishDao.deleteAllDishes(dishDao.findAll());
    }


    public void addIngredientToDish(String dish_name, String ing_name){
        dish = dishDao.findDishByName(dish_name);
       // ingredient = new Ingredient();
       // ingredient.ingredient = ing_name;
        ingredient.dishId = dishDao.findIdByName("dish");
        ingredientDao.insert(ingredient);
    }

    public void createIngredient(String ing_name){
       // dishDao = databaseService.dishDao();
        dish = new Dish("dish", "descr");
       // dishDao.insert(dish);
        ingredient = new Ingredient(ing_name);
      //  ingredient.ingredient = ing_name;
       // ingredient.dishId = -1;
        ingredientDao.insert(ingredient);
    }

    @Test
    public void ingredientShouldBeCreate(){

        createIngredient("ing");

    }

    @Test
    public void testing(){
        dishService.createDish("dish", "descr");
        addIngredientToDish("dish", "ing");

        System.out.println(ingredientDao.findAllIngredientsNames());
        assertTrue(ingredientDao.findAllIngredientsNames().contains("ing"));
    }

    @Test
    public void ingredientShouldBeRemove(){
        ingredientService.createIngredient("test");
        ingredientService.deleteIngredient("test");
        assertFalse(ingredientDao.findAllIngredientsNames().contains("test"));
    }

    @After
    public void clearData(){
        ingredientDao = databaseService.ingredientDao();
        ingredientDao.deleteAllIngredients(ingredientDao.findAll());
        dishDao = databaseService.dishDao();
        dishDao.deleteAllDishes(dishDao.findAll());

    }

}