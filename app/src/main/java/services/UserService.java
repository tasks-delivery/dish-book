package services;

import java.util.ArrayList;
import java.util.List;

import entity.User;
import io.realm.Realm;

public class UserService {

    private ConfigDb configDb;

    private Realm realm;

    public void saveUser(String name, String description){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class);
                user.setName(name);
                user.setDescription(description);
            }
        });
        realm.close();
    }

    public void updateUser(String oldUser, String newUser){
        configDb = new ConfigDb();
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

    public void deleteUser(String userName){
        configDb = new ConfigDb();
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

    public String findUserNameByName(String userName){
        configDb = new ConfigDb();
        realm = Realm.getInstance(configDb.getRealmConfiguration());
        User user = realm.where(User.class).equalTo("name",userName).findFirst();
        try {
            user.getName();
        }catch (NullPointerException e){
            return null;
        }
        return user.getName();
    }

    public List<String> findAllUsers(){
        configDb = new ConfigDb();
        realm = Realm.getInstance(configDb.getRealmConfiguration());
        ArrayList<String> userNames = new ArrayList<>();
        List<User> userList = realm.where(User.class).findAll();
        for (int i = 0; i < userList.size(); i++){
            userNames.add(userList.get(i).getName());
        }
        return userNames;
    }

}
