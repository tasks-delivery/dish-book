package ingredient_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import butterknife.ButterKnife;
import dish_1.book.core.R;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class IngredientActivity_2 extends AppCompatActivity implements IngredientView_2 {

    IngredientPresenter_2 ingredientPresenter_2;

    ArrayAdapter<String> adapter;

    public static void start(@NonNull Activity activity){
        Intent intent = new Intent(activity, IngredientActivity_2.class);
        activity.startActivity(intent);
    }

    public ArrayAdapter<String>getListIngredients(){
       // adapter = new ArrayAdapter<String>(IngredientActivity_2.class);

        return null;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_2);
        ButterKnife.bind(this);
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        ingredientPresenter_2 = new IngredientPresenter_2(lifecycleHandler, this);
    }

    @Override
    public void getDish() {

    }
}
