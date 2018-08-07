package ingredient_1;

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

public class IngredientActivity_1 extends AppCompatActivity implements IngredientView_1 {

    IngredientPresenter_1 ingredientPresenter_1;

    @BindView(R.id.dish_6_field_ingredient_name)
    EditText dish_6_field_ingredient_name;

    @BindView(R.id.dish_6_btn_save)
    Button dish_6_btn_save;

    @BindView(R.id.dish_6_btn_back)
    Button dish_6_btn_back;

    @OnClick({R.id.dish_6_btn_save})
    public void createNewDish(){
        String ingredientName = dish_6_field_ingredient_name.getText().toString();
        ingredientPresenter_1.addNewIngredient(ingredientName);
    }

    @OnClick({R.id.dish_6_btn_back})
    public void backToDish_4(){
        onBackPressed();
      //  openDish_4();
       // openIngredient_3();
    }

    public static void start(@NonNull Activity activity){
        Intent intent = new Intent(activity, IngredientActivity_1.class);
        activity.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_1);
        ButterKnife.bind(this);
        LifecycleHandler lifecycleHandler1 = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        ingredientPresenter_1 = new IngredientPresenter_1(lifecycleHandler1, this);
    }

    @Override
    public void openDish_4() {
        Intent dishProfile = new Intent(IngredientActivity_1.this, DishActivity_4.class);
        dishProfile.putExtra("name", findDish());
        startActivity(dishProfile);
        finish();
    }

    @Override
    public void openIngredient_3() {

        IngredientActivity_1.super.onBackPressed();
        finish();
        /*
        Intent dishProfile = new Intent(IngredientActivity_1.this, IngredientActivity_3.class);
       // dishProfile.putExtra("name", findDish());
        startActivity(dishProfile);
        finish();
        */
    }

    @Override
    public String findDish() {
        Intent intent = getIntent();
        return intent.getStringExtra("name");
    }
}
