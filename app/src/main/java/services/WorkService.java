package services;

import entity.Work;
import io.realm.Realm;

public class WorkService {

    private ConfigDb configDb;

    private Realm realm;

    public void saveWork(String name){
        configDb = new ConfigDb();
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

    public void updateWork(String oldWork, String newWork){
        configDb = new ConfigDb();
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

    public void deleteWork(String workName){
        configDb = new ConfigDb();
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

    public String findWorkNameByName(String workName){
        configDb = new ConfigDb();
        realm = Realm.getInstance(configDb.getRealmConfiguration());
        Work work = realm.where(Work.class).equalTo("name",workName).findFirst();
        try {
            work.getName();
        }catch (NullPointerException e){
            return null;
        }
        return work.getName();
    }

}
