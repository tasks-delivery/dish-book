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
    List<Ingredient> findAll();

    @Query("SELECT ing_name FROM ingredient")
    List<String> findAllIngredientsNames();

    @Query("SELECT id FROM ingredient")
    List<Integer> getAllId();

    @Query("SELECT dish_id FROM ingredient")
    List<Integer> getAllDishId();

    @Query("SELECT * FROM ingredient WHERE id = :id")
    Ingredient getIngredientById(String id);

  //  @Query("SELECT id FROM ingredient WHERE ing_name = :ing_name")
 //   Integer findIngredientIdByName(String ing_name);


  //  @Query("SELECT * FROM ingredient WHERE dish_name = :dish_id")
   // List<Ingredient> getIngredientByDishId(int dish_id);

    @Query("SELECT ing_name FROM ingredient WHERE dish_id = :dish_id")
    List<String> findIngredientsByDishName(int dish_id);

    @Query("SELECT ing_name FROM ingredient WHERE dish_id !=:dish_id OR dish_id IS NULL")
    List<String> findIngredientsNotAssigneToDishByDishName(int dish_id);

 //   @Query("SELECT ing_name FROM ingredient WHERE dish_name !=:name OR dish_name IS NULL")
 //   List<String> testing(String name);

    @Query("SELECT * FROM ingredient WHERE ing_name = :ing_name")
    Ingredient findIngredientByName(String ing_name);

    @Query("SELECT ing_name FROM ingredient WHERE dish_id = :dish_id")
    String findIngredientNameByDishName(int dish_id);

    @Query("SELECT * FROM ingredient WHERE dish_id = :dish_id")
    Ingredient findIngredientByDishName(int dish_id);

    @Insert
    void insert(Ingredient ingredient);

    @Insert
    void insertSeveralIngredients(List<Ingredient> ingredients);

    @Update
    void update(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Delete
    void deleteAllIngredients(List<Ingredient> ingredients);

   // @Delete
   // void deleteIngredientsByNames(List<String> strings);

}
