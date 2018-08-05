package dish_1;


import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import services.App;
import services.DatabaseService;

public class DishPresenter_1 {

    private final LifecycleHandler mLifecycleHandler;

    private final DishView_1 mDishView_1;

    DatabaseService db = App.getInstance().getDatabaseService();

    public DishPresenter_1(@NonNull LifecycleHandler lifecycleHandler, @NonNull DishView_1 dishView_1){
        mLifecycleHandler = lifecycleHandler;
        mDishView_1 = dishView_1;
    }

    public void openDish_3(){
        mDishView_1.openDish_3();
    }

    public void openDish_2(){
        mDishView_1.openDish_2();
    }

}
