package services;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import entity.Dish;
import entity.DishDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DishServiceTest {

    private DishDao dishDao;

    private Dish dish;

    private DishService dishService;

    private DatabaseService databaseService = App.getInstance().getDatabaseService();

    @Before
    public  void setUp(){
        dishDao = databaseService.dishDao();
        dishService = new DishService();
        dishDao.deleteAllDishes(dishDao.findAll());
    }

    @Test
    public void dishShouldBeCreate(){
        dishService.createDish("test", "descr");
        assertTrue(dishDao.findAllNames().contains("test"));
        assertTrue(dishDao.findAllDescriptions().contains("descr"));
    }

    @Test
    public void existingDishShouldNotBeCreate(){
        dishService.createDish("test", "12345");
        dishService.createDish("test", "descr");
        assertEquals(null, dishDao.findNameByDescriptione("descr"));
        assertEquals("test", dishDao.findNameByDescriptione("12345"));
    }

    @Test
    public void allDishesShouldBeVisible(){
        dishService.createDish("first", "12345");
        dishService.createDish("second", "12345");
        assertTrue(dishService.loadDishes().contains("first"));
        assertTrue(dishService.loadDishes().contains("second"));
    }

    @Test
    public void dishShouldBeUpdate(){
        dishService.createDish("test", "descr");
        dishService.updateDish("test","update test", "update descr");
        assertFalse(dishDao.findAllNames().contains("test"));
        assertFalse(dishDao.findAllDescriptions().contains("descr"));
        assertTrue(dishDao.findAllNames().contains("update test"));
        assertTrue(dishDao.findAllDescriptions().contains("update descr"));
    }

    @Test
    public void existingDishShouldNotBeUpdate(){
        dishService.createDish("test", "descr");
        dishService.updateDish("test","test", "update descr");
        assertTrue(dishDao.findAllNames().contains("test"));
        assertTrue(dishDao.findAllDescriptions().contains("descr"));
        assertFalse(dishDao.findAllNames().contains("update test"));
        assertFalse(dishDao.findAllDescriptions().contains("update descr"));
    }

    @Test
    public void dishShouldBeRemove(){
        dishService.createDish("test", "descr");
        dishService.deleteDish("test");
        assertFalse(dishDao.findAllNames().contains("test"));
        assertFalse(dishDao.findAllDescriptions().contains("descr"));
    }

    @After
    public void clearData(){
        dishDao.deleteAllDishes(dishDao.findAll());
    }

}