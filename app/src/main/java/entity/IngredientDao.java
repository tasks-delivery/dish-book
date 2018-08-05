package entity;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface IngredientDao {

    @Query("SELECT * FROM ingredient")
    List<Ingredient> getAll();

    @Query("SELECT id FROM ingredient")
    List<Integer> getAllId();

    @Query("SELECT dish_id FROM ingredient")
    List<Integer> getAllDishId();

    @Query("SELECT * FROM ingredient WHERE id = :id")
    Ingredient getIngredientById(long id);

    @Query("SELECT * FROM ingredient WHERE dish_id = :dish_id")
    List<Ingredient> getIngredientByDishId(int dish_id);

    @Query("SELECT ing_name FROM ingredient WHERE dish_id = :dish_id")
    List<String> getIngredientsByDishId(int dish_id);

    @Insert
    void insert(Ingredient ingredient);

    @Update
    void update(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

}
