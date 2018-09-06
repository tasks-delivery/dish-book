package services;

import java.util.ArrayList;
import java.util.List;

import entity.Ingredient;
import io.realm.Realm;

public class IngredientService {

    private ConfigDb configDb;

    private Realm realm;

    public void saveIngredient(String name){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Ingredient ingredient = realm.createObject(Ingredient.class);
                ingredient.setIngName(name);
            }
        });
        configDb.closeDb();
    }

    public void updateIngredient(String oldIng, String newIng){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Ingredient ingredient = realm.where(Ingredient.class).equalTo("ingName", oldIng).findFirst();
                ingredient.setIngName(newIng);
            }
        });
        configDb.closeDb();
    }

    public void deleteIngredient(String ingName){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Ingredient ingredient = realm.where(Ingredient.class).equalTo("ingName", ingName).findFirst();
                ingredient.deleteFromRealm();
            }
        });
        configDb.closeDb();
    }

    public String findIngredientNameByName(String ingName){
        configDb = new ConfigDb();
        realm = Realm.getInstance(configDb.getRealmConfiguration());
        Ingredient ingredient = realm.where(Ingredient.class).equalTo("ingName",ingName).findFirst();
        try {
            ingredient.getIngName();
        }catch (NullPointerException e){
            return null;
        }
        return ingredient.getIngName();
    }

    public List<String> findAllIngredients(){
        configDb = new ConfigDb();
        Realm realm = Realm.getInstance(configDb.getRealmConfiguration());
        ArrayList<String> ingNames = new ArrayList<>();
        List<Ingredient> ingredientList = realm.where(Ingredient.class).findAll();
        for (int i = 0; i < ingredientList.size(); i++){
            ingNames.add(ingredientList.get(i).getIngName());
        }
        return ingNames;
    }

}
