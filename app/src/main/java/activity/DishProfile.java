package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import dish.book.core.R;

public class DishProfile extends AppCompatActivity implements View.OnClickListener{

    Toolbar dish_profile;
    ImageButton btn_edit_dish;
    TextView dish_name_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_profile);

        btn_edit_dish = findViewById(R.id.btn_edit_dish);
        btn_edit_dish.setOnClickListener(this);

        dish_profile = findViewById(R.id.toolbar_dish_profile);
        dish_profile.setOnClickListener(this);

        Intent intent = getIntent();
        dish_name_text = findViewById(R.id.dish_name_text);

        dish_name_text.setText(intent.getStringExtra("name"));

        dish_profile.setNavigationIcon(R.mipmap.back_icon);
        dish_profile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_edit_dish:
                Intent editDish = new Intent(DishProfile.this, EditDish.class);
                editDish.putExtra("name", dish_name_text.getText());
                startActivity(editDish);
                break;

            default:
                break;

        }

    }
}
