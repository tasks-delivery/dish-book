package services;

import java.util.ArrayList;
import java.util.List;

import entity.Ingredient;
import io.realm.Realm;

public class IngredientService {

    private ProductionDb productionDb;

    private Realm realm;

    public void saveIngredient(String name){
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Ingredient ingredient = realm.createObject(Ingredient.class);
                ingredient.setIngName(name);
            }
        });
        productionDb.closeDb();
    }

    public void updateIngredient(String oldIng, String newIng){
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Ingredient ingredient = realm.where(Ingredient.class).equalTo("ingName", oldIng).findFirst();
                ingredient.setIngName(newIng);
            }
        });
        productionDb.closeDb();
    }

    public void deleteIngredient(String ingName){
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Ingredient ingredient = realm.where(Ingredient.class).equalTo("ingName", ingName).findFirst();
                ingredient.deleteFromRealm();
            }
        });
        productionDb.closeDb();
    }

    public String findIngredientNameByName(String ingName){
        productionDb = new ProductionDb();
        realm = Realm.getInstance(productionDb.getRealmConfiguration());
        Ingredient ingredient = realm.where(Ingredient.class).equalTo("ingName",ingName).findFirst();
        try {
            ingredient.getIngName();
        }catch (NullPointerException e){
            return null;
        }
        return ingredient.getIngName();
    }

    public List<String> findAllIngredients(){
        productionDb = new ProductionDb();
        Realm realm = Realm.getInstance(productionDb.getRealmConfiguration());
        ArrayList<String> ingNames = new ArrayList<>();
        List<Ingredient> ingredientList = realm.where(Ingredient.class).findAll();
        for (int i = 0; i < ingredientList.size(); i++){
            ingNames.add(ingredientList.get(i).getIngName());
        }
        return ingNames;
    }

}
