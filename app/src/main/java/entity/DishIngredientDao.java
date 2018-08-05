package entity;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Transaction;

@Dao
public abstract class DishIngredientDao {

    @Insert
    public abstract void insertDish(Dish dish);

    @Insert
    public abstract void insertIngredient(Ingredient ingredient);

    @Transaction
    public void insertDishAndIngredient(Dish dish, Ingredient ingredient) {
        insertDish(dish);
        insertIngredient(ingredient);
    }

}
