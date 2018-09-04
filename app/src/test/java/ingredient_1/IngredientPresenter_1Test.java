package ingredient_1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IngredientPresenter_1Test {

    private IngredientPresenter_1 ingredientPresenter_1;

    @Mock
    private IngredientView_1 ingredientView_1;

    @Before
    public void setUp(){
        ingredientPresenter_1 = new IngredientPresenter_1(ingredientView_1);
    }

    @Test
    public void navigateToIngredient3(){
        ingredientPresenter_1.openIngredient_3();
        Mockito.verify(ingredientView_1).openIngredient_3();
    }

    @Test
    public void ingredientNameShouldBeValid(){
        ingredientPresenter_1.ingredientNameValid("test");
        Mockito.verify(ingredientView_1).nameValid();
    }

    @Test
    public void ingredientNameShouldNotBeValid(){
        ingredientPresenter_1.ingredientNameValid("");
        Mockito.verify(ingredientView_1).nameInvalid();
    }

    @Test
    public void navigateToDish1(){
        ingredientPresenter_1.openDish_1();
        Mockito.verify(ingredientView_1).openDish_1();
    }

}