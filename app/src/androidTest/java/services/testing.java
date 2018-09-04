package services;

import android.app.Application;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dish_2.DishActivity_2;
import entity.User;
import entity.Work;
import io.realm.Realm;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class testing extends Application {

    @Rule
    public ActivityTestRule<DishActivity_2> mActivityRule = new ActivityTestRule<>(DishActivity_2.class);

    private ConfigDb configDb;

    private Realm realm;

    @Before
    public void setUp(){
        configDb = new ConfigDb();
        realm = Realm.getInstance(configDb.getRealmConfiguration());
        /*

        if (realm.isClosed() == false){
            realm.close();
            realm = Realm.getInstance(configDb.getRealmConfiguration());
        }else {
            System.out.println("Real already closed");
            realm = Realm.getInstance(configDb.getRealmConfiguration());
        }
        */
    }

    @Test
    public void workShouldBeCreated(){
        saveWork("test work");
        assertEquals("test work",findWorkNameByName("test work"));
        realm.close();
    }

    @Test
    public void userShouldBeCreate() {
        saveUser("test user");
        assertEquals("test user",findUserNameByName("test user"));
        realm.close();
    }

    @Test
    public void workShouldBeUpdate(){
        saveWork("work");
        updateWork("work","new work");
        assertEquals(null,findWorkNameByName("work"));
        assertEquals("new work",findWorkNameByName("new work"));
        realm.close();
    }

    @Test
    public void userShouldBeUpdate(){
        saveUser("user");
        updateUser("user", "new user");
        assertEquals(null,findUserNameByName("user"));
        assertEquals("new user",findUserNameByName("new user"));
        realm.close();
    }

    @Test
    public void userShouldBeDelete(){
        saveUser("test user");
        deleteUser("test user");
        assertEquals(null,findUserNameByName("test user"));
        realm.close();
    }

    @Test
    public void workShouldBeDelete(){
        saveWork("test work");
        deleteWork("test work");
        assertEquals(null,findWorkNameByName("test work"));
        realm.close();
    }

    @Test
    public void workShouldBeAddToUser(){
        saveWork("test work");
        saveUser("test user");
        assignWorkToUser("test user", "test work");
        assertEquals(Arrays.asList(findWorkNameByName("test work")), findAllWorkNamesOfUserByUserName("test user"));
        realm.close();
    }

    @Test
    public void workShouldBeDeleteFromUser(){
        saveWork("work");
        saveUser("user");
        assignWorkToUser("user", "work");
        deleteWorkFromUser("user", "work");
        assertEquals(null, findAllWorkNamesOfUserByUserName("user"));
        realm.close();
    }

    @Test
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
    }

}



