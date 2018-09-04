package entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = Dish.class, parentColumns = "id", childColumns = "dish_id"))
//@Entity
public class Ingredient {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "dish_id")
    public int dishId;

    @ColumnInfo(name = "ing_name")
    public String ing_name;

    public Ingredient(String ing_name) {
        this.ing_name = ing_name;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public void setIng_name(String ing_name) {
        this.ing_name = ing_name;
    }

    public String getIng_name() {
        return ing_name;
    }

    @Override
    public String toString() {
        return ing_name;
    }

}
