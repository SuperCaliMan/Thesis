
@RunWith(JUnit4.class)
public class LoginTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginTest() {
        //SETUP
        onView(withId(R.id.edTxtUser)).
            perform(clearText(),typeText("STUDIOP"),closeSoftKeyboard());

        onView(withId(R.id.edTextPassword)).
            perform(clearText(),typeText("********"),closeSoftKeyboard());

        onView(withId(R.id.checkBox)).perform(click());
        onView((withId(R.id.btnLogin))).perform(click());

        //VALIDAZIONE
        onView(withId(R.id.custom_calendar)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_day_operation)).check(matches(isDisplayed()));
    }
}