package dish_4;

import android.support.annotation.NonNull;

import java.util.List;

import entity.DishDao;
import entity.IngredientDao;
import ru.arturvasilov.rxloader.LifecycleHandler;
import services.App;
import services.DatabaseService;

public class DishPresenter_4 {

    LifecycleHandler mLifecycleHandler;

    DishView_4 mDishView_4;

    DatabaseService db = App.getInstance().getDatabaseService();

    DishDao dishDao;

    IngredientDao ingredientDao;

    public DishPresenter_4(@NonNull LifecycleHandler lifecycleHandler, @NonNull DishView_4 dishView_4) {
        mLifecycleHandler = lifecycleHandler;
        mDishView_4 = dishView_4;
        dishDao = db.dishDao();
        ingredientDao = db.ingredientDao();
    }

    public String shownDescription() {
        return dishDao.getByName(mDishView_4.findDish()).description;
    }

    public void openDish_5() {
        mDishView_4.openDish_5();
    }

    public List<String> loadIngredients() {
        return ingredientDao.getIngredientsByDishId(dishDao.getIdByName(mDishView_4.findDish()));
    }

}
