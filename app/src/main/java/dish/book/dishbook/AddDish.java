package dish.book.dishbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddDish extends Activity implements View.OnClickListener, TextWatcher{

    Button cancel, save;
    EditText dishName;
    ArrayAdapter<String> adapter;
    DatabaseHelper db;
    Toolbar toolbar_add_dish;

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (dishName.getText().length() != 0){
            save.setEnabled(true);
        }
        else
            save.setEnabled(false);
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

    public List<String> loadDishes(){
        Set<String> set = db.getAllData();
        List<String> list = new ArrayList<String>(set);
        return list;
    }

    public void saveDish(){
        String name = dishName.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {

            dishName.requestFocus();
        } else {
            db.insertData(name);
            dishName.setText("");

            // Hiding the keyboard
            InputMethodManager inputmangager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmangager.hideSoftInputFromWindow(dishName.getWindowToken(), 0);
        }

        Intent dishList = new Intent(AddDish.this, DishList.class);
        startActivity(dishList);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);
        toolbar_add_dish = findViewById(R.id.toolbar_add_dish);
        toolbar_add_dish.setTitle(R.string.title_activity_add_dish);

        db = new DatabaseHelper(AddDish.this);

        cancel = findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(this);

        save = findViewById(R.id.btn_save);
        save.setOnClickListener(this);
        save.setEnabled(false);

        dishName = findViewById(R.id.input_dish);
        dishName.addTextChangedListener(this);

    }
}