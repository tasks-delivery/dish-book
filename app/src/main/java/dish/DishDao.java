package dish;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DishDao {

    @Query("SELECT * FROM dish")
    List<Dish> getAll();

    @Query("SELECT name FROM dish")
    List<String> getAllNames();

    @Query("SELECT * FROM dish WHERE id = :id")
    Dish getById(int id);

    @Query("SELECT * FROM dish WHERE name = :name")
    Dish getByName(String name);

    @Query("SELECT id FROM dish WHERE name = :name")
    Dish getIdByName(String name);

    @Insert
    void inster(Dish dish);

    @Update
    void update(Dish dish);

    @Delete
    void delete(Dish dish);

}
