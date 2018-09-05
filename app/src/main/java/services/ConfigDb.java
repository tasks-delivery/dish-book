package services;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ConfigDb extends Application {

    public static ConfigDb instance;

    public RealmConfiguration realmConfiguration;

    public void setRealmConfiguration(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    public RealmConfiguration getRealmConfiguration() {
        realmConfiguration = new RealmConfiguration.Builder()
                .name("pre-production")
                .schemaVersion(2)
                .build();
        return realmConfiguration;
    }

    public void closeDb(){
        Realm realm = Realm.getInstance(getRealmConfiguration());
        while (realm.isClosed() == false)  {
            realm.close();
        }
    }

    public static ConfigDb getInstance(){
        return instance;
    }

}
