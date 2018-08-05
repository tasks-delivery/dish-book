package entity;

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

    @Query("SELECT id FROM dish WHERE dish_name =:dish_name")
    Integer getIdByName(String dish_name);

    @Query("SELECT dish_name FROM dish")
    List<String> getAllNames();

    @Query("SELECT description FROM dish")
    List<String> getAllDescriptions();

    @Query("SELECT * FROM dish WHERE id = :id")
    Dish getById(int id);

    @Query("SELECT * FROM dish WHERE dish_name = :dish_name")
    Dish getByName(String dish_name);

    @Insert
    void insert(Dish dish);

    @Update
    void update(Dish dish);

    @Delete
    void delete(Dish dish);

}
