package dish_5;

import services.DishService;

public class DishPresenter_5 {

    private DishView_5 mDishView_5;

    private DishService dishService;

    public DishPresenter_5(DishView_5 dishView_5){
        mDishView_5 = dishView_5;
    }

    public void openDish_4(){
        mDishView_5.openDish_4();
    }

    public void openDish_3(){
        mDishView_5.openDish_3();
    }


    public void shownDishDialog1(){
        mDishView_5.shownDishDialog1();
    }

    public void dishNameValid(String name){
        if (name.isEmpty()){
            mDishView_5.nameInvalid();
        }
        else
            mDishView_5.nameValid();
    }

    public String shownDescription() {
        dishService = new DishService();
        return dishService.shownDescription(mDishView_5.findDish());
    }

    public void updateDish(String dish_name, String dish_descr){
        dishService = new DishService();
        if (dishService.updateDish(mDishView_5.findDish(), dish_name, dish_descr) == "invalid"){
            shownDishDialog1();
        }else {
            openDish_4();
        }
    }

    public void deleteDish(){
        dishService = new DishService();
        dishService.deleteDish(mDishView_5.findDish());
        openDish_3();
    }

}
