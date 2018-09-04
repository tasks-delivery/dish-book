package entity;

import io.realm.RealmObject;

public class Work extends RealmObject {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
