package dish_4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dish_1.book.core.R;
import dish_3.DishActivity_3;
import dish_5.DishActivity_5;
import ingredient_3.IngredientActivity_3;

public class DishActivity_4 extends AppCompatActivity implements DishView_4{

    private DishPresenter_4 dishPresenter_4;

    private ArrayAdapter<String> adapter;

    @BindView(R.id.text_description)
    TextView text_description;

    @BindView(R.id.dish_name_text)
    TextView dish_name_text;

    @BindView(R.id.toolbar_dish_profile)
    Toolbar dish_profile;

    @BindView(R.id.btn_edit_dish)
    ImageButton btn_edit_dish;

    @BindView(R.id.btn_add_ingredient)
    ImageView btn_add_ingredient;

    @BindView(R.id.btn_remove_ingredient)
    ImageView btn_remove_ingredient;

    @BindView(R.id.ingredients_list)
    ListView ingredients_list;

    @OnClick(R.id.btn_remove_ingredient)
    public void deleteIngredients(){
        dishPresenter_4.openIngredient_3FromRemoveIngredient();
    }

    @OnClick(R.id.btn_add_ingredient)
    public void addIngredient(){
        dishPresenter_4.openIngredient_3FromAddIngredient();
    }

    @OnClick(R.id.btn_edit_dish)
    public void goToEditDish(){
        dishPresenter_4.openDish_5();
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_4);
        ButterKnife.bind(this);
        dishPresenter_4 = new DishPresenter_4( this);
        text_description.setText(dishPresenter_4.shownDescription());
        dish_name_text.setText(findDish());
        dish_profile.setNavigationIcon(R.mipmap.back_icon);
        dish_profile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dishPresenter_4.openDish_3();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getIngredientsOfDish();
    }

    @Override
    public ArrayAdapter<String> getIngredientsOfDish() {
        adapter = new ArrayAdapter<String>(DishActivity_4.this,
                android.R.layout.simple_spinner_item, dishPresenter_4.loadIngredientsOfDish());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ingredients_list.setAdapter(adapter);
        ingredients_list.setWillNotDraw(false);
        return adapter;
    }

    @Override
    public void openDish_5() {
        Intent intent = new Intent(DishActivity_4.this, DishActivity_5.class);
        intent.putExtra("dish_name", dish_name_text.getText());
        startActivity(intent);
        finish();
    }

    @Override
    public void openDish_3() {
        startActivity(new Intent(DishActivity_4.this, DishActivity_3.class));
        finish();
    }

    @Override
    public void openIngredient_3FromRemoveIngredient(){
        Intent intent = new Intent(DishActivity_4.this, IngredientActivity_3.class);
        intent.putExtra("navigation", "dishActivity4Remove");
        intent.putExtra("dish_name", dish_name_text.getText());
        startActivity(intent);
        finish();
    }

    @Override
    public void openIngredient_3FromAddIngredient(){
        Intent intent = new Intent(DishActivity_4.this, IngredientActivity_3.class);
        intent.putExtra("navigation", "dishActivity4Add");
        intent.putExtra("dish_name", dish_name_text.getText());
        startActivity(intent);
        finish();
    }

    @Override
    public String findDish() {
        Intent intent = getIntent();
        return intent.getStringExtra("dish_name");
    }

    @Override
    public void onBackPressed(){
        dishPresenter_4.openDish_3();
    }
}
