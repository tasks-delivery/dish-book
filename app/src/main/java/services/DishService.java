package services;

import java.util.List;

import entity.Dish;
import entity.DishDao;

public class DishService {

    private Dish dish;

    private DatabaseService databaseService = App.getInstance().getDatabaseService();

    private DishDao dishDao = databaseService.dishDao();

    public String createDish(String dish_name, String dish_descr) {
        dish = new Dish(dish_name, dish_descr);
        if (dishDao.findAllNames().contains(dish.getDish_name())== true){
            return "invalid";
        }else {
            dishDao.insert(dish);
            return "valid";
        }

    }

    public String updateDish(String old_dish_name, String new_dish_name, String dish_descr) {
        dish = dishDao.findDishByName(old_dish_name);
        if (dishDao.findAllNames().contains(new_dish_name)== true){
            return "invalid";

        }else {
            dish.id = dish.getId();
            dish.dish_name = new_dish_name;
            dish.description = dish_descr;
            dishDao.update(dish);
            return "valid";
        }
    }

    public void deleteDish(String dish_name){
        dish = dishDao.findDishByName(dish_name);
        dishDao.delete(dish);
    }


  //  public Integer findIdByName(String dish_name){
  //      return dishDao.findIdByName(dish_name);
   // }

    public int findDishByName(String dish_name){
        return dishDao.findIdByName(dish_name);
    }

    public List<String> loadDishes(){
        return dishDao.findAllNames();
    }

    public String shownDescription(String dish_name) {
        return  dishDao.findDescriptionByName(dish_name);
    }

}
