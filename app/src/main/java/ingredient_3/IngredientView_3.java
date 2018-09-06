package ingredient_3;

import android.widget.ArrayAdapter;

import java.util.List;

public interface IngredientView_3 {

    ArrayAdapter<String> getIngredientsList(List<String> ingredients);

    void navigateFromDish4RemoveIng();

    void navigateFromIngredient1();

    void navigateFormDish4AddIng();


    void navigateFromDish1();

    String findDish();

    void openIngredient_1();

    void openDish_4();

    String navigationResponse();

}
