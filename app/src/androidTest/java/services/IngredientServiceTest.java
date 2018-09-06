package services;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dish_2.DishActivity_2;
import io.realm.Realm;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class IngredientServiceTest {

    @Rule
    public ActivityTestRule<DishActivity_2> mActivityRule = new ActivityTestRule<>(DishActivity_2.class);

    private ProductionDb productionDb;

    private Realm realm;

    private IngredientService ingredientService;

    @Before
    public void setUp(){
        productionDb = new ProductionDb();
        realm = Realm.getInstance(productionDb.getRealmConfiguration());
        ingredientService = new IngredientService();
    }

    @Test
    public void ingredientShouldBeCreated(){
        ingredientService.saveIngredient("test work");
        assertEquals("test work",ingredientService.findIngredientNameByName("test work"));
    }

    @Test
    public void ingredientShouldBeUpdate(){
        ingredientService.saveIngredient("work");
        ingredientService.updateIngredient("work","new work");
        assertEquals(null,ingredientService.findIngredientNameByName("work"));
        assertEquals("new work",ingredientService.findIngredientNameByName("new work"));
    }

    @Test
    public void ingredientShouldBeDelete(){
        ingredientService.saveIngredient("test work");
        ingredientService.deleteIngredient("test work");
        assertEquals(null,ingredientService.findIngredientNameByName("test work"));
    }

    @After
    public void clearAllData() {
        productionDb.closeDb();
        Realm.deleteRealm(productionDb.getRealmConfiguration());
    }

}