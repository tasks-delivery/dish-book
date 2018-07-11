package activity;

import android.content.Intent;
import android.graphics.PorterDuff;
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

public class EditDish extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    Toolbar toolbar_edit_dish;
    Button btn_save;
    EditText field_dish_name;
    DatabaseService db = App.getInstance().getDatabaseService();
    Dish dish = new Dish();
    DishDao dishDao = db.dishDao();

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                    saveDish();
                break;
            default:
                break;

        }

    }

    public void updateDish(){
        String name = field_dish_name.getText().toString();
        Intent intent = getIntent();
        dish = dishDao.getIdByName(intent.getStringExtra("name"));
        if (dishDao.getAllNames().contains(name) == true){
            System.out.println("found");
        }else {
            System.out.println("not found");
            dish.id = dish.getId();
            dish.name = name;
            dishDao.update(dish);
            Intent dishList = new Intent(EditDish.this, DishList.class);
            startActivity(dishList);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dish);
        field_dish_name = findViewById(R.id.field_dish_name);
        field_dish_name.addTextChangedListener(this);

        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_save.setEnabled(false);

        btn_save.getBackground().setColorFilter(0xFF808000, PorterDuff.Mode.MULTIPLY);

        toolbar_edit_dish = findViewById(R.id.toolbar_edit_dish);
        toolbar_edit_dish.setOnClickListener(this);

        toolbar_edit_dish.setNavigationIcon(R.mipmap.back_icon);
        toolbar_edit_dish.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void saveDish(){
        String name = field_dish_name.getText().toString();
        if (TextUtils.isEmpty(name)) {
            field_dish_name.requestFocus();
        } else {
            updateDish();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String name = field_dish_name.getText().toString();
        if (name.length() != 0 && name.length() < 20){
            btn_save.setEnabled(true);
        }
        else
            btn_save.setEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
