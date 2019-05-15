package semanticalwaysauthentic.co.listbarang;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class gantikwhtest {
    @Rule
    public ActivityTestRule<PilihKwh> mActivityRule = new ActivityTestRule<>(
            PilihKwh.class);


    @Before
    public void setUp() throws Exception {
        //Before Test case execution
    }

    @Test
    public void pilihkwh() {
        onView(withId(R.id.pilih_kwh)).perform(click());

        onData(anything()).atPosition(1).perform(click());
    }

    @Test
    public void OK() {

        onView(withId(R.id.ok)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        //After Test case Execution
    }
}

