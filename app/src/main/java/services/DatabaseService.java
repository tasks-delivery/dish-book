package services;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import dish.Dish;
import dish.DishDao;

@Database(entities = {Dish.class}, version = 14, exportSchema = false)
public abstract class DatabaseService extends RoomDatabase {

    public abstract DishDao dishDao();

}
