package dish_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class DishActivity_2 extends AppCompatActivity implements DishView_2{

    DishPresenter_2 dishPresenter_2;

    @BindView(R.id.dish_2_btn_back)
    Button dish_2_btn_back;

    @BindView(R.id.dish_2_btn_save)
    Button  dish_2_btn_save;

    @BindView(R.id.dish_2_field_dish_name)
    EditText dish_2_field_dish_name;

    @BindView(R.id.dish_2_field_dish_description)
    EditText dish_2_field_dish_description;

    public static void start(@NonNull Activity activity){
        Intent intent = new Intent(activity, DishActivity_2.class);
        activity.startActivity(intent);
    }

    @OnClick(R.id.dish_2_btn_back)
    public void goToMainBack(){
        dishPresenter_2.openDish_1();
    }

    @OnClick(R.id.dish_2_btn_save)
    public void goToDishList(){
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
        setContentView(R.layout.activity_dish_2);
        ButterKnife.bind(this);
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        dishPresenter_2 = new DishPresenter_2(lifecycleHandler, this);
        dish_2_field_dish_description.setImeOptions(EditorInfo.IME_ACTION_DONE);
        dish_2_field_dish_description.setRawInputType(InputType.TYPE_CLASS_TEXT);
        dish_2_btn_save.setEnabled(false);
    }

    @Override
    public void openDish_1() {
        DishActivity_2.super.onBackPressed();
        finish();
    }

    @Override
    public void openDish_3() {
        startActivity(new Intent(this, DishActivity_3.class));
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