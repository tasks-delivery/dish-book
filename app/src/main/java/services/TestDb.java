package services;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TestDb extends Application {

    public static TestDb instance;

    public RealmConfiguration realmConfiguration;

    public RealmConfiguration getRealmConfiguration() {
        realmConfiguration = new RealmConfiguration.Builder()
                .name("test")
                .schemaVersion(1)
                .build();
        return realmConfiguration;
    }

    public void setRealmConfiguration(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    public void closeDb(){
        Realm realm = Realm.getInstance(getRealmConfiguration());
        while (realm.isClosed() == false)  {
            realm.close();
        }
    }

    public static TestDb getInstance(){
        return instance;
    }

}
