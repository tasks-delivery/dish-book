package services;

import java.util.ArrayList;
import java.util.List;

import entity.Dish;
import entity.Ingredient;
import io.realm.Realm;

public class DishAndIngredientService {

    private ConfigDb configDb;

    private Realm realm;

    private IngredientService ingredientService;

    public void deleteIngredientFromDish(String dishName, String ingName){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dish dish = realm.where(Dish.class).equalTo("dishName",dishName).findFirst();
                Ingredient ingredient = realm.where(Ingredient.class).equalTo("ingName", ingName).findFirst();
                dish.getIngredients().remove(ingredient);
            }
        });
        configDb.closeDb();
    }

    public void deleteAllIngredientsOfDish(String dishName){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dish dish = realm.where(Dish.class).equalTo("dishName",dishName).findFirst();
                dish.getIngredients().deleteAllFromRealm();
            }
        });
        configDb.closeDb();
    }

    public void assignIngredientToDish(String dishName, String ingName) {
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dish dish = realm.where(Dish.class).equalTo("dishName", dishName).findFirst();
                Ingredient ingredient = realm.where(Ingredient.class).equalTo("ingName", ingName).findFirst();
                dish.getIngredients().add(ingredient);
            }
        });
        configDb.closeDb();
    }

    public List<String> findAllIngredientNamesOfDishByDishName(String dishName){
        configDb = new ConfigDb();
        realm = Realm.getInstance(configDb.getRealmConfiguration());
        ArrayList<String> ingNames = new ArrayList<>();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dish dish = realm.where(Dish.class).equalTo("dishName",dishName).findFirst();
                for (int i = 0; i < dish.getIngredients().size(); i++){
                    ingNames.add(dish.getIngredients().get(i).getIngName());
                }
            }
        });
        return ingNames;
    }

    public List<String> findAllFreeIngredientNamesByDishName(String dishName){
        configDb = new ConfigDb();
        realm = Realm.getInstance(configDb.getRealmConfiguration());
        ArrayList<String> ingNames = new ArrayList<>();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dish dish = realm.where(Dish.class).equalTo("dishName",dishName).findFirst();
                for (int i = 0; i < dish.getIngredients().size(); i++){
                    ingNames.add(dish.getIngredients().get(i).getIngName());
                }
            }
        });
        ingredientService = new IngredientService();
        List<String> freeIngs = new ArrayList<>();
        freeIngs.addAll(ingredientService.findAllIngredients());
        freeIngs.removeAll(ingNames);
        return freeIngs;
    }

}
