package services;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import entity.Dish;
import entity.DishDao;
import entity.DishIngredientDao;
import entity.Ingredient;
import entity.IngredientDao;

@Database(entities = {Dish.class, Ingredient.class}, version = 48, exportSchema = false)
public abstract class DatabaseService extends RoomDatabase {

    public abstract DishDao dishDao();

    public abstract IngredientDao ingredientDao();

    public abstract DishIngredientDao dishIngredientDao();

}
