package ingredient_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import dish_1.DishActivity_1;
import dish_1.book.core.R;
import ingredient_3.IngredientActivity_3;

public class IngredientActivity_1 extends AppCompatActivity implements IngredientView_1 {

    private IngredientPresenter_1 ingredientPresenter_1;

    @BindView(R.id.ingredient_1_field_ingredient_name)
    EditText ingredient_1_field_ingredient_name;

    @BindView(R.id.ingredient_1_btn_save)
    Button ingredient_1_btn_save;

    @BindView(R.id.ingredient_1_btn_back)
    Button ingredient_1_btn_back;

    @OnClick({R.id.ingredient_1_btn_save})
    public void createNewIngredient(){
        String ingredientName = ingredient_1_field_ingredient_name.getText().toString();
        ingredientPresenter_1.addNewIngredient(ingredientName);
    }

    @OnTextChanged(R.id.ingredient_1_field_ingredient_name)
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String ing_name = ingredient_1_field_ingredient_name.getText().toString();
        ingredientPresenter_1.ingredientNameValid(ing_name);
    }

    @OnClick({R.id.ingredient_1_btn_back})
    public void backToDish_4(){
        onBackPressed();
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_1);
        ButterKnife.bind(this);
        ingredientPresenter_1 = new IngredientPresenter_1(this);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = getIntent();
        switch (intent.getStringExtra("navigation")){
            case "DishActivity1":
                ingredientPresenter_1.openDish_1();
                break;
            case "ingredientActivity3":
                ingredientPresenter_1.openIngredient_3();
                break;
            default:
                break;
        }
    }

    @Override
    public void openIngredient_3() {
        Intent intent = new Intent(IngredientActivity_1.this, IngredientActivity_3.class);
        intent.putExtra("navigation", "ingredient1Activity");
        startActivity(intent);
        finish();
    }

    @Override
    public void openDish_1() {
        Intent intent = new Intent(IngredientActivity_1.this, DishActivity_1.class);
        intent.putExtra("navigation", "ingredient1Activity");
        startActivity(intent);
        finish();
    }

    @Override
    public void nameInvalid() {
        ingredient_1_btn_save.setEnabled(false);
    }

    @Override
    public void nameValid() {
        ingredient_1_btn_save.setEnabled(true);
    }

}
