package dish_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dish_1.book.core.R;
import dish_5.DishActivity_5;
import ingredient_1.IngredientActivity_1;
import ingredient_3.IngredientActivity_3;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class DishActivity_4 extends AppCompatActivity implements DishView_4, AdapterView.OnItemClickListener {

    DishPresenter_4 dishPresenter_4;

    @BindView(R.id.text_description)
    TextView text_description;

    @BindView(R.id.toolbar_dish_profile)
    Toolbar dish_profile;

    @BindView(R.id.btn_edit_dish)
    ImageButton btn_edit_dish;

    @BindView(R.id.btn_add_ingredient)
    ImageButton btn_add_ingredient;

    @BindView(R.id.dish_name_text)
    TextView dish_name_text;

    @BindView(R.id.ingredients_list)
    ListView ingredients_list;

    private ArrayAdapter<String> adapter;

    private ArrayAdapter<String> getIngredientsList() {
        adapter = new ArrayAdapter<String>(DishActivity_4.this,
                android.R.layout.simple_spinner_item, dishPresenter_4.loadIngredients());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ingredients_list.setAdapter(adapter);
        ingredients_list.setWillNotDraw(false);
        return adapter;
    }

    public static void start(@NonNull Activity activity){
        Intent intent = new Intent(activity, DishActivity_4.class);
        activity.startActivity(intent);
    }

    @OnClick(R.id.btn_add_ingredient)
    public void addIngredient(){
        Intent intent = new Intent(DishActivity_4.this, IngredientActivity_3.class);
        intent.putExtra("name", dish_name_text.getText());
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_edit_dish)
    public void goToEditDish(){
        dishPresenter_4.openDish_5();
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        /*
        setContentView(R.layout.activity_dish_4);
        ButterKnife.bind(this);
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        dishPresenter_4 = new DishPresenter_4(lifecycleHandler, this);



        dish_profile.setNavigationIcon(R.mipmap.back_icon);
        dish_profile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ingredients_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editIngredient = new Intent(DishActivity_4.this, IngredientActivity_3.class);
                editIngredient.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
             //   dishProfile.putExtra("name", parent.getAdapter().getItem(position).toString());
                startActivity(editIngredient);
                finish();
                DishActivity_4.super.finish();
            }
        });
        */
    }


    @Override
    public void onStop(){
        super.onStop();
        getIngredientsList().clear();
        getIngredientsList().notifyDataSetChanged();
    }



    @Override
    public void onStart() {
        super.onStart();


        //   getIngredientsList().clear();
        //   getIngredientsList().notifyDataSetChanged();
        setContentView(R.layout.activity_dish_4);
        ButterKnife.bind(this);
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        dishPresenter_4 = new DishPresenter_4(lifecycleHandler, this);

        text_description.setText(dishPresenter_4.shownDescription());
        dish_name_text.setText(findDish());
        getIngredientsList();
        dish_profile.setNavigationIcon(R.mipmap.back_icon);
        dish_profile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        ingredients_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editIngredient = new Intent(DishActivity_4.this, IngredientActivity_1.class);
                editIngredient.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                //   dishProfile.putExtra("name", parent.getAdapter().getItem(position).toString());
                startActivity(editIngredient);
                //show selected
             //   Toast.makeText(main_activity, stringText, Toast.LENGTH_LONG).show();
            }
        });


    }




                /*
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent editIngredient = new Intent(DishActivity_4.this, IngredientActivity_1.class);
                            editIngredient.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            //   dishProfile.putExtra("name", parent.getAdapter().getItem(position).toString());
                            startActivity(editIngredient);
                            finish();
                            DishActivity_4.super.finish();
                        }
                    });
                    */



    @Override
    public void openDish_5() {
        Intent dishProfile = new Intent(DishActivity_4.this, DishActivity_5.class);
        dishProfile.putExtra("name", dish_name_text.getText());
        startActivity(dishProfile);
        finish();
    }

    @Override
    public String findDish() {
        Intent intent = getIntent();
        return intent.getStringExtra("name");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
