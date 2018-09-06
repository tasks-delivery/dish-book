package dish_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dish_1.DishActivity_1;
import dish_1.book.core.R;
import dish_2.DishActivity_2;
import dish_4.DishActivity_4;

public class DishActivity_3 extends AppCompatActivity implements DishView_3 {

    private DishPresenter_3 dishPresenter_3;

    private ArrayAdapter<String> adapter;

    @BindView(R.id.dish_3_btn_add_dish)
    FloatingActionButton dish_3_btn_add_dish;

    @BindView(R.id.dish_3_dish_list)
    ListView dish_3_dish_list;

    @OnClick(R.id.dish_3_btn_add_dish)
    public void goToDish_2() {
        dishPresenter_3.openDish_2();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_3);
        ButterKnife.bind(this);
        dishPresenter_3 = new DishPresenter_3(this);
        dish_3_dish_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DishActivity_3.this, DishActivity_4.class);
                intent.putExtra("dish_name", parent.getAdapter().getItem(position).toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        getAllDishes();
    }

    @Override
    public void onBackPressed(){
        dishPresenter_3.openDish_1();
    }

    @Override
    public void openDish_2() {
        Intent intent = new Intent(DishActivity_3.this, DishActivity_2.class);
        intent.putExtra("navigation", "DishActivity3");
        startActivity(intent);
        finish();
    }

    @Override
    public void openDish_1() {
        startActivity(new Intent(this, DishActivity_1.class));
        finish();
    }

    @Override
    public ArrayAdapter<String> getAllDishes() {
        adapter = new ArrayAdapter<String>(DishActivity_3.this,
                android.R.layout.simple_spinner_item, dishPresenter_3.loadDishes());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dish_3_dish_list.setAdapter(adapter);
        dish_3_dish_list.setWillNotDraw(false);
        return adapter;
    }

}
