package services;

import android.app.Application;
import android.arch.persistence.room.Room;

public class App extends Application {

    public static App instance;

    private DatabaseService databaseService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        databaseService = Room.databaseBuilder(this, DatabaseService.class, "databaseService")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public static App getInstance(){
        return instance;
    }

    public DatabaseService getDatabaseService() {
        return databaseService;
    }

}
