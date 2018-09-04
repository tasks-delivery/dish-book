package dish_3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DishPresenter_3Test {

    private DishPresenter_3 dishPresenter_3;

    @Mock
    private DishView_3 dishView_3;

    @Before
    public void setUp(){
        dishPresenter_3 = new DishPresenter_3(dishView_3);
    }

    @Test
    public void navigateToDish2(){
        dishPresenter_3.openDish_2();
        Mockito.verify(dishView_3).openDish_2();
    }

    @Test
    public void navigateToDish1(){
        dishPresenter_3.openDish_1();
        Mockito.verify(dishView_3).openDish_1();
    }



}