package services;

import java.util.ArrayList;
import java.util.List;

import entity.Dish;
import entity.Ingredient;
import io.realm.Realm;

public class DishAndIngredientService {

    private ConfigDb configDb;

    private Realm realm;

    private DishService dishService;

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


    /*
    private Dish dish;

    private Ingredient ingredient;

    private DatabaseService databaseService = App.getInstance().getDatabaseService();

    private DishDao dishDao = databaseService.dishDao();

    private IngredientDao ingredientDao = databaseService.ingredientDao();

    private List<Ingredient> ingredients;

    private IngredientService ingredientService;

    public void deleteDish(String dish_name){
        dish = dishDao.findDishByName(dish_name);
       // ingredients = ingredientDao.getIngredientByDishId((dishDao.findIdByName(dish_name)));
        ingredientDao.deleteAllIngredients(ingredients);
        dishDao.delete(dish);
    }

    public void deleteIngredientFromDish(int dish_id){
        ingredient = ingredientDao.findIngredientByDishName(dish_id);
        //ingredient.setDish_id(0);
        ingredientDao.update(ingredient);
    }

    public void addIngredientToDish(String dish_name, String ing_name) {
       dish = dishDao.findDishByName(dish_name);
       ingredient = ingredientDao.findIngredientByName(ing_name);
       ingredient.id = dish.getId();
       ingredientDao.update(ingredient);
    }

    public List<String> loadAllIngredientsWithoutDish(int dish_id){
        return ingredientDao.findIngredientsNotAssigneToDishByDishName(dish_id);
    }

    public List<String> findIngredientsOfDish(int dish_id){
        return ingredientDao.findIngredientsByDishName(dish_id);
    }
    */
}
