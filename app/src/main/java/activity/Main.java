package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import dish.book.core.R;

public class Main extends AppCompatActivity implements View.OnClickListener{

    Button btn_add_dish, btn_all_dishes;

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_dish:
                Intent createDish = new Intent(Main.this, AddDish.class);
                startActivity(createDish);
                break;

            case R.id.btn_all_dishes:
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
        btn_add_dish = findViewById(R.id.btn_add_dish);
        btn_add_dish.setOnClickListener(this);

        btn_all_dishes =  findViewById(R.id.btn_all_dishes);
        btn_all_dishes.setOnClickListener(this);
    }

}
