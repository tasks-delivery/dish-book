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
    List<Dish> findAll();

    @Query("SELECT id FROM dish WHERE dish_name =:dish_name")
    Integer findIdByName(String dish_name);

   // @Query("SELECT * FROM dish WHERE dish_name =:dish_name")
   // String findDishName(String dish_name);

    @Query("SELECT dish_name FROM dish")
    List<String> findAllNames();

    @Query("SELECT description FROM dish")
    List<String> findAllDescriptions();

    @Query("SELECT description FROM dish WHERE dish_name = :dish_name")
    String findDescriptionByName(String dish_name);

  //  @Query("SELECT * FROM dish WHERE id = :id")
  //  Dish findDishById(int id);

    @Query("SELECT * FROM dish WHERE dish_name = :dish_name")
    Dish findDishByName(String dish_name);

    @Query("SELECT dish_name FROM dish WHERE description = :description")
    String findNameByDescriptione(String description);

    @Insert
    void insert(Dish dish);

    @Update
    void update(Dish dish);

    @Delete
    void delete(Dish dish);

    @Delete
    void deleteAllDishes(List<Dish> dishes);

}
