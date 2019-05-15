package semanticalwaysauthentic.co.listbarang;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
    @LargeTest
    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    //j unit test yang bisa menjalankan suatu method tertentu : disini kita memakai sorter
    public class HomeScreenTest {

        @Rule
        public ActivityTestRule<HomeScreenActivity> mActivityRule = new ActivityTestRule<>(
                HomeScreenActivity.class);


        @Before
        public void setUp() throws Exception {
            //Before Test case execution
        }

        @Test
        public void menu1ceklistbarang() {

            onView(withId(R.id.menu1)).perform(click());
        }

        @Test
        public void menu2hargalistrik() {

            onView(withId(R.id.menu2)).perform(click());
        }

        @Test
        public void menu3cekvoltase() {

            onView(withId(R.id.menu3)).perform(click());
        }

        @Test
        public void menu4gantikwh() {

            onView(withId(R.id.menu4)).perform(click());
        }

        @After
        public void tearDown() throws Exception {
            //After Test case Execution
        }
    }