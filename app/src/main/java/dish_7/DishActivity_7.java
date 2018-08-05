package dish_7;

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

public class DishActivity_7 extends AppCompatActivity implements DishView_7 {

    DishPresenter_7 dishPresenter_7;

    ArrayAdapter<String> adapter;

    public static void start(@NonNull Activity activity){
        Intent intent = new Intent(activity, DishActivity_7.class);
        activity.startActivity(intent);
    }

    public ArrayAdapter<String>getListIngredients(){
       // adapter = new ArrayAdapter<String>(DishActivity_7.class);

        return null;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_7);
        ButterKnife.bind(this);
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        dishPresenter_7 = new DishPresenter_7(lifecycleHandler, this);
    }

    @Override
    public void getDish() {

    }
}
