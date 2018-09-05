package dish_2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DishPresenter_2Test {

    private DishPresenter_2 dishPresenter_2;

    @Mock
    private DishView_2 dishView_2;

    @Before
    public void setUp(){
        dishPresenter_2 = new DishPresenter_2(dishView_2);
    }

    @Test
    public void navigateToDish3(){
        dishPresenter_2.openDish_3();
        Mockito.verify(dishView_2).openDish_3();
    }

    @Test
<<<<<<< HEAD
    public void navigateToDish1(){
=======
    public void navigatetoDish1(){
>>>>>>> 959002c546f3fa7b5c2682e8901c1792a4636330
        dishPresenter_2.openDish_1();
        Mockito.verify(dishView_2).openDish_1();
    }

    @Test
    public void navigateToDialog1(){
        dishPresenter_2.shownDishDialog1();
        Mockito.verify(dishView_2).shownDishDialog1();
    }

    @Test
    public void dishNameShouldBeValid(){
       dishPresenter_2.dishNameValid("test");
       Mockito.verify(dishView_2).nameValid();
    }

    @Test
    public void dishNameShouldNotBeValid(){
        dishPresenter_2.dishNameValid("");
        Mockito.verify(dishView_2).nameInvalid();
    }

}