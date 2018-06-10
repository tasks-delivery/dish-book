package dish.book.dishbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DishList extends AddDish implements View.OnClickListener, TextWatcher, AdapterView.OnItemSelectedListener{

    FloatingActionButton addNewDish;
    ListView dish_list;
    Toolbar toolbar_dish_list;

    public void getDishList(){
        adapter = new ArrayAdapter<String>(DishList.this,
                android.R.layout.simple_spinner_item, loadDishes());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dish_list.setAdapter(adapter);
        dish_list.setWillNotDraw(false);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.float_btn_add_dish:
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
        toolbar_dish_list = findViewById(R.id.toolbar_dish_list);
        toolbar_dish_list.setTitle(R.string.title_activity_dish_list);
        dish_list = findViewById(R.id.dish_list);
        dish_list.setOnItemSelectedListener(this);
        addNewDish = findViewById(R.id.float_btn_add_dish);
        addNewDish.setOnClickListener(this);
        getDishList();
    }

}