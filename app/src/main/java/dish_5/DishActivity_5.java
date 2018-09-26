package dish_5;

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
import dish_1.book.core.R;
import dish_3.DishActivity_3;
import dish_4.DishActivity_4;

public class DishActivity_5 extends AppCompatActivity implements DishView_5 {

    private DishPresenter_5 dishPresenter_5;

    @BindView(R.id.dish_5_btn_save)
    Button dish_5_btn_save;

    @BindView(R.id.dish_5_btn_back)
    Button dish_5_btn_back;

    @BindView(R.id.dish_5_btn_delete)
    Button dish_5_btn_delete;

    @BindView(R.id.dish_5_btn_dish_photo)
    Button dish_5_btn_dish_photo;

    @BindView(R.id.dish_5_field_dish_description)
    EditText dish_5_field_dish_description;

    @BindView(R.id.dish_5_field_dish_name)
    EditText dish_5_field_dish_name;

    @OnClick(R.id.dish_5_btn_back)
    public void goBack(){
        dishPresenter_5.openDish_4();
    }

    @OnClick(R.id.dish_5_btn_delete)
    public void removeDish(){
        dishPresenter_5.deleteDish();
    }

    @OnClick(R.id.dish_5_btn_save)
    public void goToDishList(){
        String dish_name = dish_5_field_dish_name.getText().toString();
        String dish_descr = dish_5_field_dish_description.getText().toString();
        dishPresenter_5.updateDish(dish_name, dish_descr);
    }

    @OnTextChanged(R.id.dish_5_field_dish_name)
    public void onTextChanged(CharSequence s, int start, int before, int count) {
          dishPresenter_5.dishNameValid(dish_5_field_dish_name.getText().toString());
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_5);
        ButterKnife.bind(this);
        dishPresenter_5 = new DishPresenter_5( this);
        dish_5_field_dish_description.setImeOptions(EditorInfo.IME_ACTION_DONE);
        dish_5_field_dish_description.setRawInputType(InputType.TYPE_CLASS_TEXT);
        dish_5_btn_save.setEnabled(false);
        dish_5_field_dish_name.setText(findDish());
        dish_5_field_dish_description.setText(dishPresenter_5.shownDescription());
    }

    @Override
    public void openDish_3() {
        startActivity(new Intent(DishActivity_5.this, DishActivity_3.class));
        finish();
    }

    @Override
    public void shownDishDialog1() {
        DialogFragment dialogFragment = new DishDialogActivity_1();
        dialogFragment.show(getSupportFragmentManager(), "missiles");
    }

    @Override
    public void nameInvalid() {
        dish_5_btn_save.setEnabled(false);
    }

    @Override
    public void nameValid() {
        dish_5_btn_save.setEnabled(true);
    }

    @Override
    public void openDish_4() {
        Intent intent = new Intent(DishActivity_5.this, DishActivity_4.class);
        intent.putExtra("dish_name", dish_5_field_dish_name.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public String findDish() {
        Intent intent = getIntent();
        return intent.getStringExtra("dish_name");
    }

    @Override
    public void onBackPressed(){
        dishPresenter_5.openDish_4();
    }

}
