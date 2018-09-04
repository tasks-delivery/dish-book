package dish_4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DishPresenter_4Test {

    private DishPresenter_4 dishPresenter_4;

    @Mock
    private DishView_4 dishView_4;

    @Before
    public void setUp(){
        dishPresenter_4 = new DishPresenter_4(dishView_4);
    }

    @Test
    public void navigateToDish5(){
        dishPresenter_4.openDish_5();
        Mockito.verify(dishView_4).openDish_5();
    }

    @Test
    public void navigateToDish3(){
        dishPresenter_4.openDish_3();
        Mockito.verify(dishView_4).openDish_3();
    }

    @Test
    public void navigateToIngredient_3ByRemove(){
        dishPresenter_4.openIngredient_3FromRemoveIngredient();
        Mockito.verify(dishView_4).openIngredient_3FromRemoveIngredient();
    }

    @Test
    public void navigateToIngredient_3ByAdd(){
        dishPresenter_4.openIngredient_3FromAddIngredient();
        Mockito.verify(dishView_4).openIngredient_3FromAddIngredient();
    }

}