package pl.example.android.garbageapp;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.example.android.garbageapp.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void showGreenSectorDetailsOnSectorGreenClick() {

        onView(withId(R.id.sector_2_deep_purple)).perform(click());
        onView(withId(R.id.main_content_sector_green)).check(matches(isDisplayed()));
    }

    @Test
    public void showBlueSectorDetailsOnSectorBlueClick() {

        onView(withId(R.id.sector_blue)).perform(click());
        onView(withId(R.id.main_content_sector_blue)).check(matches(isDisplayed()));
    }

    @Test
    public void showYellowSectorDetailsOnSectorYellowClick() {

        onView(withId(R.id.sector_6_deep_orange)).perform(click());
        onView(withId(R.id.main_content_sector_yellow)).check(matches(isDisplayed()));
    }


}
