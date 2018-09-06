package services;

import java.util.ArrayList;
import java.util.List;

import entity.Dish;
import entity.Ingredient;
import io.realm.Realm;

public class DishAndIngredientService {

    private ProductionDb productionDb;

    private Realm realm;

    private IngredientService ingredientService;

    public void deleteIngredientFromDish(String dishName, String ingName){
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dish dish = realm.where(Dish.class).equalTo("dishName",dishName).findFirst();
                Ingredient ingredient = realm.where(Ingredient.class).equalTo("ingName", ingName).findFirst();
                dish.getIngredients().remove(ingredient);
            }
        });
        productionDb.closeDb();
    }

    public void deleteAllIngredientsOfDish(String dishName){
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dish dish = realm.where(Dish.class).equalTo("dishName",dishName).findFirst();
                dish.getIngredients().deleteAllFromRealm();
            }
        });
        productionDb.closeDb();
    }

    public void assignIngredientToDish(String dishName, String ingName) {
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dish dish = realm.where(Dish.class).equalTo("dishName", dishName).findFirst();
                Ingredient ingredient = realm.where(Ingredient.class).equalTo("ingName", ingName).findFirst();
                dish.getIngredients().add(ingredient);
            }
        });
        productionDb.closeDb();
    }

    public List<String> findAllIngredientNamesOfDishByDishName(String dishName){
        productionDb = new ProductionDb();
        realm = Realm.getInstance(productionDb.getRealmConfiguration());
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
        productionDb = new ProductionDb();
        realm = Realm.getInstance(productionDb.getRealmConfiguration());
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
