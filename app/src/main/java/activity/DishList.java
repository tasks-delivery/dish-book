package activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dish.Dish;
import dish.DishDao;
import dish.book.core.R;
import services.App;
import services.DatabaseService;

public class DishList extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    FloatingActionButton btn_add_dish;
    ListView dish_list;
    Toolbar toolbar_dish_list;
    ArrayAdapter<String> adapter;
    DatabaseService db = App.getInstance().getDatabaseService();
    Dish dish = new Dish();
    DishDao dishDao = db.dishDao();

    private EditText queryEditText;
    private Button searchButton;

    public List<String> loadDishes(){
        Set<String> set = new HashSet<String>();
        String selectQuery = "select * from " + "dish";
        Cursor cursor = db.query(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                set.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        List<String> list = new ArrayList<String>(set);
        return list;
    }

    public ArrayAdapter<String> getDishList(){
        adapter = new ArrayAdapter<String>(DishList.this,
                android.R.layout.simple_spinner_item, loadDishes());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dish_list.setAdapter(adapter);
        dish_list.setWillNotDraw(false);
        return adapter;
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_add_dish:
                Intent createDish = new Intent(DishList.this, AddDish.class);
                startActivity(createDish);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {
        String name = parent.getItemAtPosition(position).toString();
        showToast("Selected Item :: " + name);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);

        dish_list = findViewById(R.id.dish_list);
        dish_list.setOnItemSelectedListener(this);
        btn_add_dish = findViewById(R.id.btn_add_dish);
        btn_add_dish.setOnClickListener(this);
        getDishList();

        dish_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent dishProfile = new Intent(DishList.this, DishProfile.class);
                dishProfile.putExtra("name", parent.getAdapter().getItem(position).toString());
                startActivity(dishProfile);
            }
        });

    }

}