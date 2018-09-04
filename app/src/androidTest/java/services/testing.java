package services;

import android.app.Application;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
        if (realm.isClosed() == false){
            realm.close();
            realm = Realm.getInstance(configDb.getRealmConfiguration());
        }else {
            System.out.println("Real already closed");
            realm = Realm.getInstance(configDb.getRealmConfiguration());
        }
    }

    @Test
    public void workShouldBeCreated(){
        saveWork("test work");
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        Work work = realm.where(Work.class).contains("name","test work").findFirst();
        assertEquals("test work",work.getName());
        realm.close();
    }

    @Test
    public void userShouldBeCreate() {
        saveUser("test user");
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        User user = realm.where(User.class).contains("name","test user").findFirst();
        assertEquals("test user",user.getName());
        realm.close();
    }

    @Test
    public void workShouldBeUpdate(){
        saveWork("work");
        updateWork("work","new work");
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        Work work = realm.where(Work.class).equalTo("name","work").findFirst();
        Work work1 = realm.where(Work.class).equalTo("name","new work").findFirst();
        assertEquals(null,work);
        assertEquals("new work",work1.getName());
        realm.close();
    }

    @Test
    public void userShouldBeUpdate(){
        saveUser("user");
        updateUser("user", "new user");
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        User user = realm.where(User.class).equalTo("name","user").findFirst();
        User user1 = realm.where(User.class).equalTo("name","new user").findFirst();
        assertEquals(null,user);
        assertEquals("new user",user1.getName());
        realm.close();
    }

    @Test
    public void userShouldBeDelete(){
        saveUser("test user");
        deleteUser("test user");
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        User user = realm.where(User.class).contains("name","test user").findFirst();
        assertEquals(null,user);
        realm.close();
    }

    @Test
    public void workShouldBeDelete(){
        saveWork("test");
        deleteWork("test");
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        Work work = realm.where(Work.class).contains("name","test").findFirst();
        assertEquals(null,work);
        realm.close();
    }



    @Test
    public void workShouldBeAddToUser(){
        saveWork("test work");
        saveUser("test user");
        assignWorkToUser("test user", "test work");
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        User user = realm.where(User.class).contains("name","test user").findFirst();
        System.out.println(user.getWorks());
        assertEquals(1,user.getWorks().size());
        realm.close();
    }

    @Test
    public void workShouldBeDeleteFromUser(){
        saveWork("work");
        saveUser("user");
        assignWorkToUser("user", "work");
        deleteWorkFromUser("user", "work");
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        Work work = realm.where(Work.class).contains("name","work").findFirst();
        User user = realm.where(User.class).contains("name","user").findFirst();
        assertEquals("work",work.getName());
        assertEquals(0,user.getWorks().size());
        realm.close();
    }

    @Test
    public void AllWorksOfUserShouldBeDelete(){
        saveUser("user");
        saveWork("first work");
        saveWork("second work");
        saveWork("third work");
        assignWorkToUser("user","first work");
        assignWorkToUser("user","second work");
        deleteAllWorksOfUser("user");
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
                Work work = realm.where(Work.class).contains("name", oldWork).findFirst();
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
                User user = realm.where(User.class).contains("name", oldUser).findFirst();
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
                Work work = realm.where(Work.class).contains("name", workName).findFirst();
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
                User user = realm.where(User.class).contains("name", userName).findFirst();
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
                User user = realm.where(User.class).contains("name",userName).findFirst();
                Work work = realm.where(Work.class).contains("name", workName).findFirst();
                user.getWorks().remove(work);
            }
        });
        realm.close();
    }

    public void deleteAllWorksOfUser(String userName){
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).contains("name",userName).findFirst();
                user.getWorks().deleteAllFromRealm();
            }
        });
        realm.close();
    }

    public void deleteSeveralWorksFromUser(String userName, List<Work> works){
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).contains("name", userName).findFirst();
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
                User user = realm.where(User.class).contains("name", userName).findFirst();
                Work work = realm.where(Work.class).contains("name", workName).findFirst();
                user.getWorks().add(work);
            }
        });
        realm.close();
    }

}



