package dish_2;

import services.DishService;

public class DishPresenter_2 {

    private DishView_2 mDishView_2;

    private DishService dishService;

    public DishPresenter_2(DishView_2 dishView_2){
        mDishView_2 = dishView_2;
    }

    public void openDish_1(){
        mDishView_2.openDish_1();
    }

    public void openDish_3(){
        mDishView_2.openDish_3();
    }

    public void shownDishDialog1(){
        mDishView_2.shownDishDialog1();
    }

    public void createDish(String name, String descr){
        dishService = new DishService();
        if (dishService.createDish(name, descr) == "invalid"){
            shownDishDialog1();
        }else {
            openDish_3();
        }
    }

    public void dishNameValid(String dish_name){
        if (dish_name.isEmpty()){
            mDishView_2.nameInvalid();
        }
        else
            mDishView_2.nameValid();
    }

}
