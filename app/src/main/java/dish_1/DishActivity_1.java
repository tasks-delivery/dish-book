package dish_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dish_1.book.core.R;
import dish_2.DishActivity_2;
import dish_3.DishActivity_3;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class DishActivity_1 extends AppCompatActivity implements DishView_1 {

    DishPresenter_1 dishPresenter_1;

    @BindView(R.id.dish_1_btn_add_dish)
    Button dish_1_btn_add_dish;

    @BindView(R.id.dish_1_btn_all_dishes)
    Button dish_1_btn_all_dishes;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, DishActivity_1.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_1);
        ButterKnife.bind(this);
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        dishPresenter_1 = new DishPresenter_1(lifecycleHandler, this);
    }

    @OnClick(R.id.dish_1_btn_add_dish)
    public void goToAddDish() {
        dishPresenter_1.openDish_2();
    }

    @OnClick(R.id.dish_1_btn_all_dishes)
    public void goToDishList() {
        dishPresenter_1.openDish_3();
    }

    @Override
    public void openDish_3() {
        startActivity(new Intent(this, DishActivity_3.class));
    }

    @Override
    public void openDish_2() {
        startActivity(new Intent(this, DishActivity_2.class));
    }

}
