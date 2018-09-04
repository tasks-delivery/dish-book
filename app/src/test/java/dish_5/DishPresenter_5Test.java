package dish_5;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DishPresenter_5Test {

    private DishPresenter_5 dishPresenter_5;

    @Mock
    private DishView_5 dishView_5;

    @Before
    public void setUp(){
        dishPresenter_5 = new DishPresenter_5(dishView_5);
    }

    @Test
    public void navigateToDish3(){
        dishPresenter_5.openDish_3();
        Mockito.verify(dishView_5).openDish_3();
    }

    @Test
    public void navigateToDish4(){
        dishPresenter_5.openDish_4();
        Mockito.verify(dishView_5).openDish_4();
    }

    @Test
    public void navigateToDialog1(){
        dishPresenter_5.shownDishDialog1();
        Mockito.verify(dishView_5).shownDishDialog1();
    }

    @Test
    public void dishNameShouldBeValid(){
        dishPresenter_5.dishNameValid("test");
        Mockito.verify(dishView_5).nameValid();
    }

    @Test
    public void dishNameShouldNotBeValid(){
        dishPresenter_5.dishNameValid("");
        Mockito.verify(dishView_5).nameInvalid();
    }

}