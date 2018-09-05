package ingredient_4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dish_1.book.core.R;
import dish_4.DishActivity_4;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class IngredientActivity_4 extends AppCompatActivity implements IngredientView_4 {

    IngredientPresenter_4 ingredientPresenter_4;

    @BindView(R.id.ingredient_4_btn_back)
    Button ingredient_4_btn_back;

    @OnClick({R.id.ingredient_4_btn_back})
    public void backToDish_4(){
        Intent ttt = new Intent(IngredientActivity_4.this, DishActivity_4.class);
        //  dishProfile.putExtra("name", findDish());
        startActivity(ttt);
        finish();
        super.finish();
        // openDish_4();
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_4);
        ButterKnife.bind(this);
        LifecycleHandler lifecycleHandler1 = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        ingredientPresenter_4 = new IngredientPresenter_4(lifecycleHandler1, this);
    }

    @Override
    public String findDish() {
        return null;
    }

    @Override
    public void openDish_4() {

    }
}
