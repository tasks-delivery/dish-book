package services;

import java.util.ArrayList;
import java.util.List;

import entity.Dish;
import io.realm.Realm;

public class DishService {

    private ProductionDb productionDb;

    public void saveDish(String name, String description) {
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
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
        productionDb.closeDb();
    }

    public void updateDish(String oldDish, String newDish, String dishDescr){
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dish dish = realm.where(Dish.class).equalTo("dishName", oldDish).findFirst();
                dish.setDishName(newDish);
                dish.setDescription(dishDescr);
            }
        });
        productionDb.closeDb();
    }

    public void deleteDish(String dishName){
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dish dish = realm.where(Dish.class).equalTo("dishName", dishName).findFirst();
                dish.deleteFromRealm();
            }
        });
        productionDb.closeDb();
    }

    public String findDescriptionOfDishByDishName(String dishName){
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
        Dish dish = realm.where(Dish.class).equalTo("dishName", dishName).findFirst();
        return dish.getDescription();
    }

    public String findDishNameByName(String dishName){
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
        Dish dish = realm.where(Dish.class).equalTo("dishName",dishName).findFirst();
        try {
            dish.getDishName();
        }catch (NullPointerException e){
            return null;
        }
        return dish.getDishName();
    }

    public List<String> findAllDishes(){
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
        ArrayList<String> dishNames = new ArrayList<>();
        List<Dish> dishList = realm.where(Dish.class).findAll();
        for (int i = 0; i < dishList.size(); i++){
            dishNames.add(dishList.get(i).getDishName());
        }
        return dishNames;
    }

}
