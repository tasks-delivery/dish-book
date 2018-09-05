package services;

import android.app.Application;

import io.realm.RealmConfiguration;

public class ConfigDb extends Application {

    public static ConfigDb instance;

    public RealmConfiguration realmConfiguration;

    public void setRealmConfiguration(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    public RealmConfiguration getRealmConfiguration() {
        realmConfiguration = new RealmConfiguration.Builder()
                .name("tetst")
                .schemaVersion(4)
                .build();
        return realmConfiguration;
    }


    public static ConfigDb getInstance(){
        return instance;
    }

 //   public DatabaseService getDatabaseService() {
   //     return databaseService;
  //  }

}
