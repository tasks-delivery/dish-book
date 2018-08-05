package dish_2;

import android.support.annotation.NonNull;

import entity.Dish;
import entity.DishDao;
import ru.arturvasilov.rxloader.LifecycleHandler;
import services.App;
import services.DatabaseService;

public class DishPresenter_2 {

    LifecycleHandler mLifecycleHandler;

    DishView_2 mDishView_2;

    Dish dish;

    DatabaseService db = App.getInstance().getDatabaseService();

    DishDao dishDao;

    public DishPresenter_2(@NonNull LifecycleHandler lifecycleHandler, @NonNull DishView_2 dishView_2){
        mLifecycleHandler = lifecycleHandler;
        mDishView_2 = dishView_2;
        dishDao = db.dishDao();
    }

    public void openDish_1(){
        mDishView_2.openDish_1();
    }

    public void dishNameValid(String name){
        if (name.isEmpty()){
            mDishView_2.nameInvalid();
        }
        else
            mDishView_2.nameValid();
    }

    public void createDish(String name, String descr) {
        dish = new Dish(name, descr);
        if (dishDao.getAllNames().contains(dish.getDish_name())== true){
            mDishView_2.shownDishDialog1();
        }else {
            dishDao.insert(dish);
            mDishView_2.openDish_3();
        }

    }
}
