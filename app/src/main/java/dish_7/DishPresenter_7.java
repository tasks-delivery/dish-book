package dish_7;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;

public class DishPresenter_7 {

    LifecycleHandler mLifecycleHandler;

    DishView_7 mDishView_7;

    public DishPresenter_7(@NonNull LifecycleHandler lifecycleHandler, @NonNull DishView_7 dishView_7){
        mDishView_7 = dishView_7;
        mLifecycleHandler = lifecycleHandler;
    }



}
