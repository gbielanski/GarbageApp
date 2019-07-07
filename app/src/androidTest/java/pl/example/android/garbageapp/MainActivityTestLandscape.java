package pl.example.android.garbageapp;


import android.content.pm.ActivityInfo;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.example.android.garbageapp.ui.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTestLandscape {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void showGreenSectorDetailsOnSectorGreenClickInLandscape() {
        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.sector_2_deep_purple)).perform(click());
        onView(withId(R.id.main_content_sector_green)).check(matches(isDisplayed()));
    }


    @Test
    public void showBlueSectorDetailsOnSectorBlueClickInLandscape() {
        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.sector_blue)).perform(click());
        onView(withId(R.id.main_content_sector_blue)).check(matches(isDisplayed()));
    }


    @Test
    public void showYellowSectorDetailsOnSectorYellowClickInLandscape() {
        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.sector_6_deep_orange)).perform(click());
        onView(withId(R.id.main_content_sector_yellow)).check(matches(isDisplayed()));
    }

}
