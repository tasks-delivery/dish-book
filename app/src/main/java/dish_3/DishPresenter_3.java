package dish_3;

import android.support.annotation.NonNull;

import java.util.List;

import entity.DishDao;
import ru.arturvasilov.rxloader.LifecycleHandler;
import services.App;
import services.DatabaseService;

public class DishPresenter_3 {

    LifecycleHandler mLifecycleHandler;

    DishView_3 mDishView_3;

    DatabaseService db = App.getInstance().getDatabaseService();
    DishDao dishDao;

    public DishPresenter_3(@NonNull LifecycleHandler lifecycleHandler, @NonNull DishView_3 dishView_3){
        mLifecycleHandler = lifecycleHandler;
        mDishView_3 = dishView_3;
        dishDao = db.dishDao();
    }

    public void openDish_2(){
        mDishView_3.openDish_2();
    }

    public List<String> loadDishes(){
        return dishDao.getAllNames();
    }
}
