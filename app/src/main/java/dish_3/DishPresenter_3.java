package dish_3;

import java.util.List;

<<<<<<< HEAD
import services.DishService;
=======
import io.realm.Realm;
import services.ConfigDb;
import services.DishService;
import services.UserService;
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330

public class DishPresenter_3 {

    private DishView_3 mDishView_3;

    private DishService dishService;

<<<<<<< HEAD
=======
    private Realm realm;

    private ConfigDb configDb;

    private UserService userService;

>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
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

<<<<<<< HEAD
=======
    public List<String> loadUsers(){
        userService = new UserService();
        return null;
    }

>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
}
