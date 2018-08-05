package entity;

import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = {@Index("dish_name"), @Index("description")})
public class Dish extends ViewModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "dish_name")
    public String dish_name;

    @ColumnInfo(name = "description")
    public String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dish(String dish_name, String description) {
        this.dish_name = dish_name;
        this.description = description;
    }

    @Override
    public String toString() {
        return dish_name + "\n" + description;
    }

}


