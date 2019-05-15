package semanticalwaysauthentic.co.listbarang;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
    @LargeTest
    public class editbarangtest {
        @Rule
        public ActivityTestRule<EditListBarang> mActivityRule = new ActivityTestRule<>(
                EditListBarang.class);


        @Before
        public void setUp() throws Exception {
            //Before Test case execution
        }

        @Test
        public void editnamabarang() {
            String a = "test 1234";
            onView(withId(R.id.edit_word)).perform(typeText(a));

            onView(withId(R.id.edit_word)).perform(pressImeActionButton());

            onView(withId(R.id.edit_word)).check(matches(withText(a)));
        }

        @Test
        public void editwatt() {
            String b = "1234";
            onView(withId(R.id.edit_watt)).perform(typeText(b));

            onView(withId(R.id.edit_watt)).perform(pressImeActionButton());

            onView(withId(R.id.edit_watt)).check(matches(withText(b)));
        }

        @Test
        public void editdurasi() {
            String c = "1234";
            onView(withId(R.id.edit_durasi)).perform(typeText(c));

            onView(withId(R.id.edit_durasi)).perform(pressImeActionButton());

            onView(withId(R.id.edit_durasi)).check(matches(withText(c)));
        }

        @Test
        public void editjumlah() {
            String d = "1234";
            onView(withId(R.id.jumlah_barang)).perform(typeText(d));

            onView(withId(R.id.jumlah_barang)).perform(pressImeActionButton());

            onView(withId(R.id.jumlah_barang)).check(matches(withText(d)));

        }

        @After
        public void tearDown() throws Exception {
            //After Test case Execution
        }
    }

