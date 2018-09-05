package dish_3;

import java.util.List;

import services.DishService;

public class DishPresenter_3 {

    private DishView_3 mDishView_3;

    private DishService dishService;

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
        return dishService.findAllDishes();
    }

}
