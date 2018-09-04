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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DishAndIngredientServiceTest {

    private DishDao dishDao;

    private Dish dish;

    private DishService dishService;

    private Ingredient ingredient;

    private IngredientDao ingredientDao;

    private IngredientService ingredientService;

    private DishAndIngredientService dishAndIngredientService;

    private DatabaseService databaseService = App.getInstance().getDatabaseService();

    @Before
    public  void setUp(){
        dishDao = databaseService.dishDao();
        dishService = new DishService();
        dishDao.deleteAllDishes(dishDao.findAll());
        ingredientDao = databaseService.ingredientDao();
        ingredientService = new IngredientService();
        ingredientDao.deleteAllIngredients(ingredientDao.findAll());
        dishAndIngredientService = new DishAndIngredientService();
    }



    @Test
    public void dishWithoutIngredientsShouldBeDelete(){
        dishService.createDish("name", "descr");
        dishAndIngredientService.deleteDish("name");
        assertFalse(dishDao.findAllNames().contains("name"));
        assertFalse(dishDao.findAllDescriptions().contains("descr"));
    }

    @Test
    public void ingredientShouldBeAddToDish(){
        dishService.createDish("name", "descr");
        //ingredientService.createIngredient("ingredient");
        dishAndIngredientService.addIngredientToDish("name", "ingredient");
       // assertEquals("ingredient",ingredientDao.findIngredientNameByDishName("name"));
    }

    @Test
    public void ingredientShouldNotBeAddToDish(){
        dishService.createDish("name", "descr");
        ingredientService.createIngredient("ingredient");
        ingredientService.createIngredient("not ing");
        dishAndIngredientService.addIngredientToDish("name", "ingredient");
       // assertTrue(dishAndIngredientService.loadAllIngredientsWithoutDish("name").contains("not ing"));
    }

    @Test
    public void ingredientShouldBeDeleteFromDish(){
        dishService.createDish("dish", "descr");
        ingredientService.createIngredient("ingr");
        dishAndIngredientService.addIngredientToDish("dish", "ingr");
       // dishAndIngredientService.deleteIngredientFromDish("dish");
       // assertFalse(dishAndIngredientService.findIngredientsOfDish("dish").contains("ingr"));
        assertTrue(ingredientDao.findAllIngredientsNames().contains("ingr"));
    }

    @After
    public void clearData(){
        ingredientDao.deleteAllIngredients(ingredientDao.findAll());
        dishDao.deleteAllDishes(dishDao.findAll());
    }

}