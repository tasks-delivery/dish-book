package services;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ProductionDb extends Application {

    public static ProductionDb instance;

    public RealmConfiguration realmConfiguration;

    public void setRealmConfiguration(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    public RealmConfiguration getRealmConfiguration() {
        realmConfiguration = new RealmConfiguration.Builder()
                .name("production")
                .schemaVersion(1)
                .build();
        return realmConfiguration;
    }

    public void closeDb(){
        Realm realm = Realm.getInstance(getRealmConfiguration());
        while (realm.isClosed() == false)  {
            realm.close();
        }
    }

    public static ProductionDb getInstance(){
        return instance;
    }

}
