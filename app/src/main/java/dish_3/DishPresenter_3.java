package dish_3;

import java.util.List;

import io.realm.Realm;
import services.ConfigDb;
import services.DishService;
import services.UserService;

public class DishPresenter_3 {

    private DishView_3 mDishView_3;

    private DishService dishService;

    private Realm realm;

    private ConfigDb configDb;

    private UserService userService;

    public DishPresenter_3(DishView_3 dishView_3){
        mDishView_3 = dishView_3;
    }

    public void openDish_1(){
        mDishView_3.openDish_1();
    }

    public void openDish_2(){
        mDishView_3.openDish_2();
    }

    public List<String> loadDishes(){
        dishService = new DishService();
        return dishService.loadDishes();
    }

    public List<String> loadUsers(){
        userService = new UserService();
        return null;
    }

}
