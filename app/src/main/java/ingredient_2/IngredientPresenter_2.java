package ingredient_2;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;

public class IngredientPresenter_2 {

    LifecycleHandler mLifecycleHandler;

    IngredientView_2 mIngredientView_2;

    public IngredientPresenter_2(@NonNull LifecycleHandler lifecycleHandler, @NonNull IngredientView_2 ingredientView_2){
        mIngredientView_2 = ingredientView_2;
        mLifecycleHandler = lifecycleHandler;
    }



}
