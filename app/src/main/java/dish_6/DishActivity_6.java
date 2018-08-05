package dish_6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dish_1.book.core.R;
import dish_4.DishActivity_4;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class DishActivity_6 extends AppCompatActivity implements DishView_6 {

    DishPresenter_6 dishPresenter_6;

    @BindView(R.id.dish_6_field_ingredient_name)
    EditText dish_6_field_ingredient_name;

    @BindView(R.id.dish_6_btn_save)
    Button dish_6_btn_save;

    @BindView(R.id.dish_6_btn_back)
    Button dish_6_btn_back;

    @OnClick({R.id.dish_6_btn_save})
    public void createNewDish(){
        String ingredientName = dish_6_field_ingredient_name.getText().toString();
        dishPresenter_6.addNewIngredient(ingredientName);
    }

    @OnClick({R.id.dish_6_btn_back})
    public void backToDish_4(){
        openDish_4();
    }

    public static void start(@NonNull Activity activity){
        Intent intent = new Intent(activity, DishActivity_6.class);
        activity.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_6);
        ButterKnife.bind(this);
        LifecycleHandler lifecycleHandler1 = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        dishPresenter_6 = new DishPresenter_6(lifecycleHandler1, this);
    }

    @Override
    public void openDish_4() {
        Intent dishProfile = new Intent(DishActivity_6.this, DishActivity_4.class);
        dishProfile.putExtra("name", findDish());
        startActivity(dishProfile);
        finish();
    }

    @Override
    public String findDish() {
        Intent intent = getIntent();
        return intent.getStringExtra("name");
    }
}
