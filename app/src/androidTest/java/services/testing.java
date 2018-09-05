package services;

import android.app.Application;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dish_2.DishActivity_2;
import entity.User;
import entity.Work;
import io.realm.Realm;

import static junit.framework.Assert.assertEquals;
=======
import java.util.Arrays;

import dish_2.DishActivity_2;
import io.realm.Realm;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330

@RunWith(AndroidJUnit4.class)
public class testing extends Application {

    @Rule
    public ActivityTestRule<DishActivity_2> mActivityRule = new ActivityTestRule<>(DishActivity_2.class);

    private ConfigDb configDb;

    private Realm realm;

<<<<<<< HEAD
=======
    private WorkService workService;

    private UserService userService;

    private UserAndworkService userAndWorkService;

>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
    @Before
    public void setUp(){
        configDb = new ConfigDb();
        realm = Realm.getInstance(configDb.getRealmConfiguration());
<<<<<<< HEAD
        /*

        if (realm.isClosed() == false){
            realm.close();
            realm = Realm.getInstance(configDb.getRealmConfiguration());
        }else {
            System.out.println("Real already closed");
            realm = Realm.getInstance(configDb.getRealmConfiguration());
        }
        */
=======
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
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
    }

    @Test
    public void workShouldBeCreated(){
<<<<<<< HEAD
        saveWork("test work");
        assertEquals("test work",findWorkNameByName("test work"));
=======
        workService.saveWork("test work");
        assertEquals("test work",workService.findWorkNameByName("test work"));
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
        realm.close();
    }

    @Test
    public void userShouldBeCreate() {
<<<<<<< HEAD
        saveUser("test user");
        assertEquals("test user",findUserNameByName("test user"));
=======
        userService.saveUser("test user", "test descr");
        assertEquals("test user",userService.findUserNameByName("test user"));
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
        realm.close();
    }

    @Test
    public void workShouldBeUpdate(){
<<<<<<< HEAD
        saveWork("work");
        updateWork("work","new work");
        assertEquals(null,findWorkNameByName("work"));
        assertEquals("new work",findWorkNameByName("new work"));
=======
        workService.saveWork("work");
        workService.updateWork("work","new work");
        assertEquals(null,workService.findWorkNameByName("work"));
        realm.close();
        assertEquals("new work",workService.findWorkNameByName("new work"));
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
        realm.close();
    }

    @Test
    public void userShouldBeUpdate(){
<<<<<<< HEAD
        saveUser("user");
        updateUser("user", "new user");
        assertEquals(null,findUserNameByName("user"));
        assertEquals("new user",findUserNameByName("new user"));
=======
        userService.saveUser("user", "");
        userService.updateUser("user", "new user");
        assertEquals(null,userService.findUserNameByName("user"));
        realm.close();
        assertEquals("new user",userService.findUserNameByName("new user"));
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
        realm.close();
    }

    @Test
    public void userShouldBeDelete(){
<<<<<<< HEAD
        saveUser("test user");
        deleteUser("test user");
        assertEquals(null,findUserNameByName("test user"));
=======
        userService.saveUser("test user", "");
        userService.deleteUser("test user");
        assertEquals(null,userService.findUserNameByName("test user"));
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
        realm.close();
    }

    @Test
    public void workShouldBeDelete(){
<<<<<<< HEAD
        saveWork("test work");
        deleteWork("test work");
        assertEquals(null,findWorkNameByName("test work"));
=======
        workService.saveWork("test work");
        workService.deleteWork("test work");
        assertEquals(null,workService.findWorkNameByName("test work"));
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
        realm.close();
    }

    @Test
    public void workShouldBeAddToUser(){
<<<<<<< HEAD
        saveWork("test work");
        saveUser("test user");
        assignWorkToUser("test user", "test work");
        assertEquals(Arrays.asList(findWorkNameByName("test work")), findAllWorkNamesOfUserByUserName("test user"));
=======
        workService.saveWork("test work");
        userService.saveUser("test user", "");
        userAndWorkService.assignWorkToUser("test user", "test work");
        assertEquals(Arrays.asList("test work"), userAndWorkService.findAllWorkNamesOfUserByUserName("test user"));
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
        realm.close();
    }

    @Test
    public void workShouldBeDeleteFromUser(){
<<<<<<< HEAD
        saveWork("work");
        saveUser("user");
        assignWorkToUser("user", "work");
        deleteWorkFromUser("user", "work");
        assertEquals(null, findAllWorkNamesOfUserByUserName("user"));
=======
        workService.saveWork("work");
        userService.saveUser("user", "");
        userAndWorkService.assignWorkToUser("user", "work");
        userAndWorkService.deleteWorkFromUser("user", "work");
        assertEquals(null, userAndWorkService.findAllWorkNamesOfUserByUserName("user"));
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
        realm.close();
    }

    @Test
<<<<<<< HEAD
    public void AllWorksOfUserShouldBeDelete(){
        saveUser("user");
        saveWork("first work");
        saveWork("second work");
        assignWorkToUser("user","first work");
        assignWorkToUser("user","second work");
        deleteAllWorksOfUser("user");
        assertEquals(null, findAllWorkNamesOfUserByUserName("user"));
        realm.close();
    }

    public String findWorkNameByName(String workName){
        Work work = realm.where(Work.class).equalTo("name",workName).findFirst();
        try {
            work.getName();
        }catch (NullPointerException e){
            return null;
        }
        return work.getName();
    }

    public String findUserNameByName(String userName){
        User user = realm.where(User.class).equalTo("name",userName).findFirst();
        try {
            user.getName();
        }catch (NullPointerException e){
            return null;
        }
        return user.getName();
    }

    public List<String> findAllWorkNamesOfUserByUserName(String userName){
        ArrayList<String> workNames = new ArrayList<>();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    User user = realm.where(User.class).equalTo("name",userName).findFirst();
                    for (int i = 0; i < user.getWorks().size(); i++){
                        workNames.add(user.getWorks().get(i).getName());
                    }
                }
            });
        if (workNames.size() == 0){
            return null;
        }else{
            return workNames;
        }
    }

    public void deleteAllWorksOfUser(String userName){
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).equalTo("name",userName).findFirst();
                for (int i = 0; i < user.getWorks().size(); i++){
                    System.out.println(user.getWorks().get(i).getName());
                }


                user.getWorks().deleteAllFromRealm();
            }
        });
        realm.close();
    }





      //  realm.close();





        //System.out.println(user.getWorks().size());


     //   realm.close();
        // System.out.println(realm.where(User.class).contains("works","first work").findAll());
       // Work work = realm.where(Work.class).findAll();
       /*
        List<Work> works = realm.where(Work.class).findAll();
      // System.out.println(works.get(1));


       for (int i = 0; i < works.toArray().length; i++ ){
           System.out.println(works.get(i).getName());
            deleteWorkFromUser("user", works.get(i).getName());
        }

        System.out.println(works.toArray().length);
       System.out.println(realm.where(User.class).contains("", "").findAll());

       */
       // works.add(work);
       // System.out.println(work);

        //Work work1 = realm.where(Work.class).contains("name", "second work").findFirst();
       // realm.close();


       // works.add(work1);
      //  realm.close();
       // deleteSeveralWorksFromUser("user", works);
       // realm.close();



    @After
    public void clearAllData(){
        //Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
       // realm.close();
        if (realm.isClosed() == false){
            realm.close();
            Realm.deleteRealm(configDb.getRealmConfiguration());
        }else {
            Realm.deleteRealm(configDb.getRealmConfiguration());
        }
    }

    public void saveWork(String name){
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Work work = realm.createObject(Work.class);
                work.setName(name);
            }
        });
        realm.close();
    }

    public void saveUser(String name){
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class);
                user.setName(name);
            }
        });
        realm.close();
    }

    public void updateWork(String oldWork, String newWork){
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Work work = realm.where(Work.class).equalTo("name", oldWork).findFirst();
                work.setName(newWork);
            }
        });
        realm.close();
    }

    public void updateUser(String oldUser, String newUser){
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).equalTo("name", oldUser).findFirst();
                user.setName(newUser);
            }
        });
        realm.close();
    }

    public void deleteWork(String workName){
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Work work = realm.where(Work.class).equalTo("name", workName).findFirst();
                work.deleteFromRealm();
            }
        });
        realm.close();
    }

    public void deleteUser(String userName){
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).equalTo("name", userName).findFirst();
                user.deleteFromRealm();
            }
        });
        realm.close();
    }

    public void deleteWorkFromUser(String userName, String workName){
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).equalTo("name",userName).findFirst();
                Work work = realm.where(Work.class).equalTo("name", workName).findFirst();
                user.getWorks().remove(work);
            }
        });
        realm.close();
    }



    public void deleteSeveralWorksFromUser(String userName, List<Work> works){
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).equalTo("name", userName).findFirst();
                user.getWorks().removeAll(works);
            }
        });
        realm.close();
    }

    public void assignWorkToUser(String userName, String workName) {
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).equalTo("name", userName).findFirst();
                Work work = realm.where(Work.class).equalTo("name", workName).findFirst();
                user.getWorks().add(work);
            }
        });
        realm.close();
=======
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
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
    }

}



<<<<<<< HEAD
=======

>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
