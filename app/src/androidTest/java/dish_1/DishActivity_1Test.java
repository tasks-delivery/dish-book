package dish_1;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dish_1.book.core.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DishActivity_1Test {


    @Rule
    public ActivityTestRule<DishActivity_1> activityActivityTestRule = new ActivityTestRule<DishActivity_1>(DishActivity_1.class);


    @Test
    public void dishActivityTest() {
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
        onView(withId(R.id.dish_1_btn_add_dish)).check(matches(isDisplayed()));
        onView(withId(R.id.dish_1_btn_add_ingredient)).check(matches(isDisplayed()));
        onView(withId(R.id.dish_1_btn_all_dishes)).check(matches(isDisplayed()));
        onView(withId(R.id.dish_1_btn_all_ingredients)).check(matches(isDisplayed()));
    }

    @Test
    public void createDish() {
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
        onView(withId(R.id.dish_1_btn_add_dish)).perform(click());
        onView(withId(R.id.dish_2_field_dish_name)).perform(typeText("test"));
        onView(withId(R.id.dish_2_field_dish_description)).perform(typeText("pass"),
                pressImeActionButton());
        onView(withId(R.id.dish_2_btn_save)).perform(click());
    }
}