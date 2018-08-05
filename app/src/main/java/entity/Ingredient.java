package entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = Dish.class, parentColumns = "id", childColumns = "dish_id"))
public class Ingredient {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "dish_id")
    public int dishId;

    @ColumnInfo(name = "ing_name")
    public String ingredient;

    public int getDishId() {
        return dishId;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    @Override
    public String toString() {
        return ingredient;
    }

}
