package dish.book.dishbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity implements View.OnClickListener{

    Button addNewDish, allDishes;

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_new_dish:
                Intent createDish = new Intent(Main.this, AddDish.class);
                startActivity(createDish);
                break;

            case R.id.btn_dish_list:
                Intent dishList = new Intent(Main.this, DishList.class);
                startActivity(dishList);
                break;

            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNewDish = findViewById(R.id.btn_add_new_dish);
        addNewDish.setOnClickListener(this);

        allDishes =  findViewById(R.id.btn_dish_list);
        allDishes.setOnClickListener(this);
    }

}
