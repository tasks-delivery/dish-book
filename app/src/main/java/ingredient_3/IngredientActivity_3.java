package ingredient_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dish_1.book.core.R;
import dish_4.DishActivity_4;
import ingredient_1.IngredientActivity_1;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class IngredientActivity_3 extends AppCompatActivity implements IngredientView_3 {

    IngredientPresenter_3 ingredientPresenter_3;

    @BindView(R.id.ingredient_3_btn_add_ingredient)
    FloatingActionButton ingredient_3_btn_add_ingredient;

    @BindView(R.id.ingredient_3_ingredient_list)
    ListView ingredient_3_ingredient_list;

    @BindView(R.id.ingredient_3_btn_delete_selected_ingredient)
    Button ingredient_3_btn_delete_selected_ingredient;

    @BindView(R.id.ingredient_3_btn_add_selected_ingredient)
    Button ingredient_3_btn_add_selected_ingredient;

    private ArrayAdapter<String> adapter;

    private String ing_name;

    private ArrayAdapter<String> getIngredientsList() {
        adapter = new ArrayAdapter<String>(IngredientActivity_3.this,
                android.R.layout.simple_list_item_multiple_choice, ingredientPresenter_3.loadIngredients());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ingredient_3_ingredient_list.setAdapter(adapter);
        ingredient_3_ingredient_list.setWillNotDraw(false);
        return adapter;
    }

    public static void start(@NonNull Activity activity){
        Intent intent = new Intent(activity, IngredientActivity_3.class);
        activity.startActivity(intent);
    }

    @OnClick(R.id.ingredient_3_btn_delete_selected_ingredient)
    public void removeSelectedIngredient(){
        ingredientPresenter_3.removeIngredient(ing_name);
        onStart();
    }

    @OnClick(R.id.ingredient_3_btn_add_ingredient)
    public void goToDish_2() {
        ingredientPresenter_3.openIngreidnet_1();
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_3);
        ButterKnife.bind(this);
        LifecycleHandler lifecycleHandler1 = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        ingredientPresenter_3 = new IngredientPresenter_3(lifecycleHandler1, this);
        System.out.println(findDish());
        uiResolver();
       // if (findDish() == "dishActivity1"){


       // }

        ingredient_3_ingredient_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView checkedTextView = (CheckedTextView) view;
                boolean currentCheck = checkedTextView.isChecked();
                System.out.println(currentCheck);
                System.out.println(checkedTextView.getText());
                ing_name = checkedTextView.getText().toString();
            }
        });
    }

    public void uiResolver(){
        switch (findDish()){
            case "dishActivity1":
               // Button button = (Button) findViewById(R.id.ingredient_3_btn_add_selected_ingredient);
                ingredient_3_btn_add_selected_ingredient.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        getIngredientsList();
    }

    @Override
    public String findDish() {
        Intent intent = getIntent();
        return intent.getStringExtra("name");
    }

    @Override
    public void openIngredient_1() {
        startActivity(new Intent(this, IngredientActivity_1.class));
    }

    @Override
    public void openDish_4() {
        Intent dishProfile = new Intent(IngredientActivity_3.this, DishActivity_4.class);
        startActivity(dishProfile);
    }

}
