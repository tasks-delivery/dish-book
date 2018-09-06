package services;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import dish_2.DishActivity_2;
import io.realm.Realm;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DishAndIngredientServiceTest {

    @Rule
    public ActivityTestRule<DishActivity_2> mActivityRule = new ActivityTestRule<>(DishActivity_2.class);

    private ProductionDb productionDb;

    private Realm realm;

    private DishService dishService;

    private IngredientService ingredientService;

    private DishAndIngredientService dishAndIngredientService;

    @Before
    public void setUp(){
        productionDb = new ProductionDb();
        realm = Realm.getInstance(productionDb.getRealmConfiguration());
        dishService = new DishService();
        ingredientService = new IngredientService();
        dishAndIngredientService = new DishAndIngredientService();
    }

    @Test
    public void ingredientShouldBeAddToDish(){
        ingredientService.saveIngredient("test work");
        dishService.saveDish("test user", "");
        dishAndIngredientService.assignIngredientToDish("test user", "test work");
        assertEquals(Arrays.asList("test work"), dishAndIngredientService.findAllIngredientNamesOfDishByDishName("test user"));
    }

    @Test
    public void ingredientShouldBeDeleteFromDish(){
        ingredientService.saveIngredient("work");
        dishService.saveDish("user", "");
        dishAndIngredientService.assignIngredientToDish("user", "work");
        dishAndIngredientService.deleteIngredientFromDish("user", "work");
        Assert.assertFalse(dishAndIngredientService.findAllIngredientNamesOfDishByDishName("user").contains("work"));
    }

    @Test
    public void allIngredientsOfDishShouldBeDelete(){
        dishService.saveDish("user", "");
        ingredientService.saveIngredient("first work");
        ingredientService.saveIngredient("second work");
        dishAndIngredientService.assignIngredientToDish("user","first work");
        dishAndIngredientService.assignIngredientToDish("user","second work");
        dishAndIngredientService.deleteAllIngredientsOfDish("user");
        Assert.assertFalse(dishAndIngredientService.findAllIngredientNamesOfDishByDishName("user").contains("first work"));
        Assert.assertFalse(dishAndIngredientService.findAllIngredientNamesOfDishByDishName("user").contains("first work"));
    }

    @Test
    public void freeIngredientsShouldBeVisible(){
        ingredientService.saveIngredient("first");
        ingredientService.saveIngredient("second");
        ingredientService.saveIngredient("third");
        dishService.saveDish("user", "");
        dishAndIngredientService.assignIngredientToDish("user", "first");
        Assert.assertTrue(dishAndIngredientService.findAllFreeIngredientNamesByDishName("user").contains("second"));
        Assert.assertTrue(dishAndIngredientService.findAllFreeIngredientNamesByDishName("user").contains("third"));
        Assert.assertTrue(dishAndIngredientService.findAllFreeIngredientNamesByDishName("user").size() == 2);
        Assert.assertFalse(dishAndIngredientService.findAllFreeIngredientNamesByDishName("user").contains("first"));
    }

    @After
    public void clearAllData() {
        productionDb.closeDb();
        Realm.deleteRealm(productionDb.getRealmConfiguration());
    }

}