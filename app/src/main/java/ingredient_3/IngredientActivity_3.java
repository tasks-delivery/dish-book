package ingredient_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dish_1.book.core.R;
import dish_4.DishActivity_4;
import ingredient_1.IngredientActivity_1;
import services.NavigationService;

public class IngredientActivity_3 extends NavigationService implements IngredientView_3 {

    private IngredientPresenter_3 ingredientPresenter_3;

    @BindView(R.id.ingredient_3_btn_add_ingredient)
    FloatingActionButton ingredient_3_btn_add_ingredient;

    @BindView(R.id.ingredient_3_ingredient_list)
    ListView ingredient_3_ingredient_list;

    @BindView(R.id.ingredient_3_btn_delete_selected_ingredient)
    Button ingredient_3_btn_delete_selected_ingredient;

    @BindView(R.id.ingredient_3_btn_add_selected_ingredient)
    Button ingredient_3_btn_add_selected_ingredient;

    private ArrayAdapter<String> adapter;

    @Override
    public ArrayAdapter<String> getIngredientsList(List<String> ingredients) {
        adapter = new ArrayAdapter<String>(IngredientActivity_3.this,
                android.R.layout.simple_list_item_multiple_choice, ingredients);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ingredient_3_ingredient_list.setAdapter(adapter);
        ingredient_3_ingredient_list.setWillNotDraw(false);
        return adapter;
    }

    /*
    public void blockSelectedItems(){
        ingredient_3_ingredient_list.getItemAtPosition(1);
        ingredient_3_ingredient_list.setEnabled(false);
        ingredient_3_ingredient_list.setBackgroundColor(Color.rgb(220,220,220));
    }
    */

    @OnClick(R.id.ingredient_3_btn_delete_selected_ingredient)
    public void removeSelectedIngredient() {
        int ing_length = ingredient_3_ingredient_list.getCount();
        SparseBooleanArray checked = ingredient_3_ingredient_list.getCheckedItemPositions();
        if (findDish() != null) {
            ingredientPresenter_3.removeIngredientFromDish(ing_length, checked);
            ingredientPresenter_3.openDish_4();
        }else {
            ingredientPresenter_3.removeSelectedIngredients(ing_length, checked);
            onStart();
        }
    }

    @OnClick(R.id.ingredient_3_btn_add_selected_ingredient)
    public void addSelectedIngredient() {
        int ing_length = ingredient_3_ingredient_list.getCount();
        SparseBooleanArray checked = ingredient_3_ingredient_list.getCheckedItemPositions();
        ingredientPresenter_3.addSelectedIngredients(ing_length,checked);
        ingredientPresenter_3.openDish_4();
    }

    @OnClick(R.id.ingredient_3_btn_add_ingredient)
    public void goToIngredient1() {
        ingredientPresenter_3.openIngredient_1();
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_3);
        ButterKnife.bind(this);
        ingredientPresenter_3 = new IngredientPresenter_3(this);
        ingredient_3_ingredient_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView checkedTextView = (CheckedTextView) view;
                boolean currentCheck = checkedTextView.isChecked();
                //System.out.println(currentCheck);
              //  System.out.println(checkedTextView.getText());
             //   ing_name = checkedTextView.getText().toString();
            }
        });
    }

    @Override
    public void navigateFromDish4RemoveIng(){
        ingredient_3_btn_add_selected_ingredient.setVisibility(View.GONE);
        getIngredientsList(ingredientPresenter_3.loadIngredientsOfDish());
    }

    @Override
    public void navigateFromIngredient1(){
        ingredient_3_btn_add_selected_ingredient.setVisibility(View.GONE);
        getIngredientsList(ingredientPresenter_3.loadAllIngredients());
    }

    @Override
    public void navigateFormDish4AddIng(){
        ingredient_3_btn_delete_selected_ingredient.setVisibility(View.GONE);
        getIngredientsList(ingredientPresenter_3.loadAllIngredientsWithoutDish());
    }

    @Override
    public void navigateFromDish1(){
        ingredient_3_btn_add_selected_ingredient.setVisibility(View.GONE);
        getIngredientsList(ingredientPresenter_3.loadAllIngredients());
    }

    public void navigateResolver(){
        switch (navigationResponse()){
            case "ingredient1Activity":
                ingredientPresenter_3.navigateFromIngredient1();
                break;
            case "dishActivity1":
                ingredientPresenter_3.navigateFromDish1();
                break;
            case "dishActivity4Remove":
                ingredientPresenter_3.navigateFromDish4RemoveIng();
                break;
            case "dishActivity4Add":
                ingredientPresenter_3.navigateFormDish4AddIng();
                break;
            default:
            break;
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        navigateResolver();
    }

    @Override
    public void openIngredient_1() {
        Intent intent = new Intent(IngredientActivity_3.this, IngredientActivity_1.class);
        intent.putExtra("navigation", "ingredientActivity3");
        startActivity(intent);
        finish();
    }

    @Override
    public void openDish_4() {
        Intent intent = new Intent(IngredientActivity_3.this, DishActivity_4.class);
        intent.putExtra("dish_name", findDish());
        startActivity(intent);
        finish();
    }

    @Override
    public String findDish() {
        Intent intent = getIntent();
        return intent.getStringExtra("dish_name");
    }

    @Override
    public String navigationResponse() {
        Intent intent = getIntent();
        return intent.getStringExtra("navigation");
    }

}
