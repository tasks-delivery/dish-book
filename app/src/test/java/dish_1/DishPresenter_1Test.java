package dish_1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DishPresenter_1Test {

    private DishPresenter_1 dishPresenter_1;

    @Mock
    private DishView_1 dishView_1;

    @Before
    public void setUp(){
        dishPresenter_1 = new DishPresenter_1(dishView_1);
    }

    @Test
    public void navigateToIngredient1(){
        dishPresenter_1.openIngredient_1();
        Mockito.verify(dishView_1).openIngredient_1();
    }

    @Test
    public void navigateToIngredient3(){
        dishPresenter_1.openIngredient_3();
        Mockito.verify(dishView_1).openIngredient_3();
    }

    @Test
    public void navigateToDish2(){
        dishPresenter_1.openDish_2();
        Mockito.verify(dishView_1).openDish_2();
    }

    @Test
    public void navigateToDish3(){
        dishPresenter_1.openDish_3();
        Mockito.verify(dishView_1).openDish_3();
    }

}