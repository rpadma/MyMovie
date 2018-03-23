package f1.com.example.rohit.homework2;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.core.deps.guava.base.Predicate;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.SeekBar;
import android.widget.Spinner;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Predicates.instanceOf;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    private String moviename;
    private String moviedescription;
    private  String movieyear;
    private String movieimdb;
    public static final String INVALID_COUNTRY_NAME = "Family";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void initSetValues() {
        // Specify a valid string.
        moviename = "moviename";
        moviedescription="movie description";
        movieyear="2017";
        movieimdb="https://imdb.com/moviename";

    }


    @Test
    public void mainActivityTest() {

         onView(allOf(withId(R.id.btnadd), withText("Add Movie"),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.edtxtname),

                        isDisplayed())).perform(replaceText(moviename), closeSoftKeyboard());
        onView(
                allOf(withId(R.id.edtxdesc),
                        isDisplayed())).perform(replaceText(moviedescription),closeSoftKeyboard());

        onView(allOf(withId(R.id.edtxtimdblink),isDisplayed())).perform(replaceText(movieimdb),closeSoftKeyboard());

        onView(allOf(withId(R.id.edtxtyear),isDisplayed())).perform(replaceText(movieyear),closeSoftKeyboard());

        onView(allOf(withId(R.id.Sbrating),isDisplayed())).perform(setProgress(2));
        onView(allOf(withId(R.id.spgenre),isDisplayed())).perform(setPosition(2));


        onView(allOf(withId(R.id.btnaddmovie),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.btnedit),
                isDisplayed())).perform(click());



        onView(withId(R.id.btnedit)).perform(click());
        onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),isDisplayed())).atPosition(0).perform(click());



    }




    public static ViewAction setPosition(final int pos)
    {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                Spinner sb = (Spinner) view;
                 sb.setSelection(pos);
            }

            @Override
            public String getDescription() {
                return "Set a progress";
            }

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(Spinner.class);
            }
        };
    }



    public static ViewAction setProgress(final int progress) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress(progress);
            }

            @Override
            public String getDescription() {
                return "Set a progress";
            }

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }
        };
    }
}
