package services;

import android.app.Application;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dish_2.DishActivity_2;
import io.realm.Realm;

@RunWith(AndroidJUnit4.class)
public class DishServiceTest extends Application {

    private DishService dishService;

    @Rule
    public ActivityTestRule<DishActivity_2> mActivityRule = new ActivityTestRule<>(DishActivity_2.class);

    private ConfigDb configDb;

    private Realm realm;

    @Before
    public void setUp(){
        configDb = new ConfigDb();
        realm = Realm.getInstance(configDb.getRealmConfiguration());
        dishService = new DishService();
    }

    @Test
    public void sameDishShouldNotBeCreate(){
        dishService.saveDish("user", "");
        dishService.saveDish("user", "");
        Assert.assertTrue(dishService.findAllDishes().size() == 1);
    }

    @Test
    public void descriptionOfDishShouldBeCreated(){
        dishService.saveDish("user", "descr");
        Assert.assertTrue(dishService.findDescriptionOfDishByDishName("user").contains("descr"));
    }

    @Test
    public void severalDishesShouldBeCreate(){
        dishService.saveDish("first", "");
        dishService.saveDish("second", "");
        Assert.assertTrue(dishService.findAllDishes().contains("first"));
        Assert.assertTrue(dishService.findAllDishes().contains("second"));
    }

    @Test
    public void dishShouldBeCreate() {
        dishService.saveDish("test user", "test descr");
        Assert.assertTrue(dishService.findAllDishes().contains("test user"));
    }

    @Test
    public void dishShouldBeUpdate(){
        dishService.saveDish("user", "");
        dishService.updateDish("user", "new user", "");
        Assert.assertFalse(dishService.findAllDishes().contains("user"));
        Assert.assertTrue(dishService.findAllDishes().contains("new user"));
    }

    @Test
    public void dishShouldBeDelete(){
        dishService.saveDish("test user", "");
        dishService.deleteDish("test user");
        Assert.assertEquals(null,dishService.findDishNameByName("test user"));
    }

    @After
    public void clearAllData() {
        configDb.closeDb();
        Realm.deleteRealm(configDb.getRealmConfiguration());
    }

}