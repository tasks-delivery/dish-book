package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dish.Dish;
import dish.DishDao;
import dish.book.core.R;
import services.App;
import services.DatabaseService;

public class AddDish extends AppCompatActivity implements View.OnClickListener, TextWatcher{

    Button btn_cancel, btn_save;
    EditText field_dish_name;
    Toolbar toolbar_add_dish;
    DatabaseService db = App.getInstance().getDatabaseService();
    Dish dish = new Dish();
    DishDao dishDao = db.dishDao();

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (field_dish_name.getText().length() != 0 && field_dish_name.getText().length() < 20){
            btn_save.setEnabled(true);
        }
        else
            btn_save.setEnabled(false);
    }

    public void afterTextChanged(Editable s) {

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_save:
                saveDish();
                break;

            case R.id.btn_cancel:
                AddDish.super.onBackPressed();
                finish();
                break;

            default:
                break;
        }
    }

    public void saveDish(){
        String name = field_dish_name.getText().toString();
        if (TextUtils.isEmpty(name)) {
            field_dish_name.requestFocus();
        } else {
            dish.name = name;
            dishDao.inster(dish);
        }
        Intent dishList = new Intent(AddDish.this, DishList.class);
        startActivity(dishList);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);

        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_save.setEnabled(false);

        field_dish_name = findViewById(R.id.field_dish_name);
        field_dish_name.addTextChangedListener(this);
    }
}