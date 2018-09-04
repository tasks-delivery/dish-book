package dish_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import dialogs.DishDialogActivity_1;
import dish_1.DishActivity_1;
import dish_1.book.core.R;
import dish_3.DishActivity_3;
import io.realm.Realm;

public class DishActivity_2 extends AppCompatActivity implements DishView_2{

    private DishPresenter_2 dishPresenter_2;

    @BindView(R.id.dish_2_btn_back)
    Button dish_2_btn_back;

    @BindView(R.id.dish_2_btn_save)
    Button  dish_2_btn_save;

    @BindView(R.id.dish_2_field_dish_name)
    EditText dish_2_field_dish_name;

    @BindView(R.id.dish_2_field_dish_description)
    EditText dish_2_field_dish_description;

    @OnClick(R.id.dish_2_btn_back)
    public void backNavigation(){
        onBackPressed();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = getIntent();
        switch (intent.getStringExtra("navigation")){
            case "DishActivity1":
                dishPresenter_2.openDish_1();
                break;
            case "DishActivity3":
                dishPresenter_2.openDish_3();
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.dish_2_btn_save)
    public void goToDishList(){

       // Realm.init(this);

        String name = dish_2_field_dish_name.getText().toString();
        String description = dish_2_field_dish_description.getText().toString();
        dishPresenter_2.createDish(name, description);
    }

    @OnTextChanged(R.id.dish_2_field_dish_name)
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String name = dish_2_field_dish_name.getText().toString();
        dishPresenter_2.dishNameValid(name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getApplicationContext());

    }

    @Override
    public void onStart(){
        super.onStart();
        setContentView(R.layout.activity_dish_2);
        ButterKnife.bind(this);
        dishPresenter_2 = new DishPresenter_2( this);
        dish_2_field_dish_description.setImeOptions(EditorInfo.IME_ACTION_DONE);
        dish_2_field_dish_description.setRawInputType(InputType.TYPE_CLASS_TEXT);
        dish_2_btn_save.setEnabled(false);
    }

    @Override
    public void openDish_3() {
        startActivity(new Intent(this, DishActivity_3.class));
        finish();
    }

    @Override
    public void openDish_1() {
        startActivity(new Intent(this, DishActivity_1.class));
        finish();
    }

    @Override
    public void shownDishDialog1() {
        DialogFragment dialogFragment = new DishDialogActivity_1();
        dialogFragment.show(getSupportFragmentManager(), "missiles");
    }

    @Override
    public void nameInvalid() {
        dish_2_btn_save.setEnabled(false);
    }

    @Override
    public void nameValid() {
        dish_2_btn_save.setEnabled(true);
    }

}