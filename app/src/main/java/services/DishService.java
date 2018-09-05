package services;

import java.util.ArrayList;
import java.util.List;

import entity.Dish;
import io.realm.Realm;

public class DishService {

    private ConfigDb configDb;

    public void saveDish(String name, String description) {
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        if (findAllDishes().contains(name) == true) {
        } else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Dish dish = realm.createObject(Dish.class);
                    dish.setDishName(name);
                    dish.setDescription(description);
                }
            });
        }
        configDb.closeDb();
    }

    public void updateDish(String oldDish, String newDish, String dishDescr){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dish dish = realm.where(Dish.class).equalTo("dishName", oldDish).findFirst();
                dish.setDishName(newDish);
                dish.setDescription(dishDescr);
            }
        });
        configDb.closeDb();
    }

    public void deleteDish(String dishName){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dish dish = realm.where(Dish.class).equalTo("dishName", dishName).findFirst();
                dish.deleteFromRealm();
            }
        });
        configDb.closeDb();
    }

    public String findDescriptionOfDishByDishName(String dishName){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        Dish dish = realm.where(Dish.class).equalTo("dishName", dishName).findFirst();
        return dish.getDescription();
    }

    public String findDishNameByName(String dishName){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        Dish dish = realm.where(Dish.class).equalTo("dishName",dishName).findFirst();
        try {
            dish.getDishName();
        }catch (NullPointerException e){
            return null;
        }
        return dish.getDishName();
    }

    public List<String> findAllDishes(){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        ArrayList<String> dishNames = new ArrayList<>();
        List<Dish> dishList = realm.where(Dish.class).findAll();
        for (int i = 0; i < dishList.size(); i++){
            dishNames.add(dishList.get(i).getDishName());
        }
        return dishNames;
    }

    /*
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

    */
}
