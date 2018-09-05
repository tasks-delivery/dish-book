package services;

import android.app.Application;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import dish_2.DishActivity_2;
import io.realm.Realm;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class testing extends Application {

    @Rule
    public ActivityTestRule<DishActivity_2> mActivityRule = new ActivityTestRule<>(DishActivity_2.class);

    private ConfigDb configDb;

    private Realm realm;

    private WorkService workService;

    private UserService userService;

    private UserAndworkService userAndWorkService;

    @Before
    public void setUp(){
        configDb = new ConfigDb();
        realm = Realm.getInstance(configDb.getRealmConfiguration());
        workService = new WorkService();
        userService = new UserService();
        userAndWorkService = new UserAndworkService();
    }

    @Test
    public void severalUsersShouldBeCreate(){
        userService.saveUser("first", "");
        userService.saveUser("second", "");
        assertTrue(userService.findAllUsers().contains("first"));
        realm.close();
        assertTrue(userService.findAllUsers().contains("second"));
        realm.close();
    }

    @Test
    public void workShouldBeCreated(){
        workService.saveWork("test work");
        assertEquals("test work",workService.findWorkNameByName("test work"));
        realm.close();
    }

    @Test
    public void userShouldBeCreate() {
        userService.saveUser("test user", "test descr");
        assertEquals("test user",userService.findUserNameByName("test user"));
        realm.close();
    }

    @Test
    public void workShouldBeUpdate(){
        workService.saveWork("work");
        workService.updateWork("work","new work");
        assertEquals(null,workService.findWorkNameByName("work"));
        realm.close();
        assertEquals("new work",workService.findWorkNameByName("new work"));
        realm.close();
    }

    @Test
    public void userShouldBeUpdate(){
        userService.saveUser("user", "");
        userService.updateUser("user", "new user");
        assertEquals(null,userService.findUserNameByName("user"));
        realm.close();
        assertEquals("new user",userService.findUserNameByName("new user"));
        realm.close();
    }

    @Test
    public void userShouldBeDelete(){
        userService.saveUser("test user", "");
        userService.deleteUser("test user");
        assertEquals(null,userService.findUserNameByName("test user"));
        realm.close();
    }

    @Test
    public void workShouldBeDelete(){
        workService.saveWork("test work");
        workService.deleteWork("test work");
        assertEquals(null,workService.findWorkNameByName("test work"));
        realm.close();
    }

    @Test
    public void workShouldBeAddToUser(){
        workService.saveWork("test work");
        userService.saveUser("test user", "");
        userAndWorkService.assignWorkToUser("test user", "test work");
        assertEquals(Arrays.asList("test work"), userAndWorkService.findAllWorkNamesOfUserByUserName("test user"));
        realm.close();
    }

    @Test
    public void workShouldBeDeleteFromUser(){
        workService.saveWork("work");
        userService.saveUser("user", "");
        userAndWorkService.assignWorkToUser("user", "work");
        userAndWorkService.deleteWorkFromUser("user", "work");
        assertEquals(null, userAndWorkService.findAllWorkNamesOfUserByUserName("user"));
        realm.close();
    }

    @Test
    public void allWorksOfUserShouldBeDelete(){
        userService.saveUser("user", "");
        workService.saveWork("first work");
        workService.saveWork("second work");
        userAndWorkService.assignWorkToUser("user","first work");
        userAndWorkService.assignWorkToUser("user","second work");
        userAndWorkService.deleteAllWorksOfUser("user");
        assertEquals(null, userAndWorkService.findAllWorkNamesOfUserByUserName("user"));
        realm.close();
    }

    @After
    public void clearAllData() {
        if (realm.isClosed() == false) {
            realm.close();
            try {
                Realm.deleteRealm(configDb.getRealmConfiguration());
            } catch (IllegalStateException e) {
                Realm.deleteRealm(configDb.getRealmConfiguration());
            }

        } else {
            Realm.deleteRealm(configDb.getRealmConfiguration());
        }
    }

}




