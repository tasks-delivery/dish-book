package entity;

import io.realm.RealmObject;

public class Ingredient extends RealmObject {

    private String ingName;

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

}
