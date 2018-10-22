package com.assignment.tasktabraiz;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import com.assignment.tasktabraiz.moviedetail.view.HomeActivity;
import com.assignment.tasktabraiz.network.util.UITestHelper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest extends InstrumentationTestCase {

    public static String BASE_URL = "";
    private static final String MOVIES_LIST_JSON = "moviesList.json";
    private static final String MOVIE_DETAIL_JSON = "movieDetail.json";


    private MockWebServer server;
    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        BASE_URL = server.url("/").toString();
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        injectInsrumentation(instrumentation);
        MockTaskApplication app
                = (MockTaskApplication) instrumentation.getTargetContext().getApplicationContext();
        app.createComponent();
    }

    @Test
    public void mainActivityTest() throws Exception {
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 200, MOVIES_LIST_JSON, getInstrumentation().getContext());
        launchActivity();
        onView(withId(R.id.moviesList)).check(matches(UITestHelper.atPosition(0, withText("Halloween"))));
    }

    @Test
    public void noMovieFromApiCall() throws Exception {
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 200, null, getInstrumentation().getContext());
        launchActivity();
        Assert.assertEquals(0, getRecyclyViewItemCount());
    }

    @Test
    public void errorFetchingMoviesFromServer() throws Exception {
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 500, null, getInstrumentation().getContext());
        launchActivity();
        Assert.assertEquals(0, getRecyclyViewItemCount());
    }

    @Test
    public void movieDetailTest() throws Exception {
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 200, MOVIES_LIST_JSON, getInstrumentation().getContext());
        launchActivity();
        onView(withId(R.id.moviesList)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, UITestHelper.touchDownAndUp()));

        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 200, MOVIE_DETAIL_JSON, getInstrumentation().getContext());
        Thread.sleep(200);
        //just checking the release date
        onView(withId(R.id.releaseDate)).check(matches(withText("2018-10-18")));
    }


    @Test
    public void noMovieDetail() throws Exception {
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 200, MOVIES_LIST_JSON, getInstrumentation().getContext());
        launchActivity();
        onView(withId(R.id.moviesList)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, UITestHelper.touchDownAndUp()));

        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 200, null, getInstrumentation().getContext());

        Thread.sleep(200);
        //if detail layout is present or not
        onView(withId(R.id.movieDetailLayout)).check(matches(not(isDisplayed())));

    }


    @Test
    public void tryAgainButton() throws Exception {
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 500, MOVIES_LIST_JSON, getInstrumentation().getContext());
        launchActivity();
        Thread.sleep(200);
        onView(withId(R.id.btnTryAgain)).perform(
                click());
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 200, MOVIES_LIST_JSON, getInstrumentation().getContext());
        Thread.sleep(200);
        onView(withId(R.id.moviesList)).check(matches(UITestHelper.atPosition(0, withText("Halloween"))));

    }



    @Test
    public void errorFetchingMovieDetailFromId() throws Exception {
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 200, MOVIES_LIST_JSON, getInstrumentation().getContext());
        launchActivity();
        onView(withId(R.id.moviesList)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, UITestHelper.touchDownAndUp()));
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 404, MOVIE_DETAIL_JSON, getInstrumentation().getContext());
        Thread.sleep(200);
        //just if detail layout is present or not
        onView(withId(R.id.movieDetailLayout)).check(matches(not(isDisplayed())));

    }

    @After
    public void testComplete() throws IOException {
        server.shutdown();
    }

    private int getRecyclyViewItemCount() {
        RecyclerView recyclerView = mActivityTestRule.getActivity().findViewById(R.id.moviesList);
        return recyclerView.getAdapter().getItemCount();
    }

    private void launchActivity() throws Exception {
        Intent intent = new Intent();
        mActivityTestRule.launchActivity(intent);
        Thread.sleep(600);
    }
}
