package services;

import java.util.ArrayList;
import java.util.List;

import entity.User;
import entity.Work;
import io.realm.Realm;

public class UserAndworkService {

    private ConfigDb configDb;

    private Realm realm;

    public void deleteWorkFromUser(String userName, String workName){
        configDb = new ConfigDb();
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

    public void deleteAllWorksOfUser(String userName){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).equalTo("name",userName).findFirst();
                user.getWorks().deleteAllFromRealm();
            }
        });
        realm.close();
    }

    public void assignWorkToUser(String userName, String workName) {
        configDb = new ConfigDb();
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

    public List<String> findAllWorkNamesOfUserByUserName(String userName){
        configDb = new ConfigDb();
        realm = Realm.getInstance(configDb.getRealmConfiguration());
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
}
