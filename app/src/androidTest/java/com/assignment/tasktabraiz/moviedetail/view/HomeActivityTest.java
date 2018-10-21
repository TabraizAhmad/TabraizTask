package com.assignment.tasktabraiz.moviedetail.view;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import com.assignment.tasktabraiz.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import hackernews.nouman.a2.hackernews.network.util.UITestHelper;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest extends InstrumentationTestCase {

    public static String BASE_URL = "";
    private MockWebServer server;
    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        /*TestTaskApplicationCompenent compenent = DaggerTestTaskApplicationCompenent.builder().build();*/
        BASE_URL = server.url("/").toString();
    }

    @Test
    public void mainActivityTest() throws Exception {
        String fileName = "moviesList.json";
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 200, fileName, getInstrumentation().getContext());
        /*String detailItem = "movieDetail.json";
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 200, detailItem, getInstrumentation().getContext());*/
        launchActivity();
        onView(withId(R.id.moviesList)).check(matches(UITestHelper.atPosition(0, withText("Halloween"))));
    }

    /*@Test
    public void noMovieFromApiCall() throws Exception {
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 200, null, getInstrumentation().getContext());
        launchActivity();
        Assert.assertEquals(0, getRecyclyViewItemCount());
    }

    @Test
    public void noMovieDetail() throws Exception {
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
    public void errorFetchingMovieDetailFromId() throws Exception {
        String fileName = "moviesList.json";
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 200, fileName, getInstrumentation().getContext());
        String postItem = "movieDetail.json";
        UITestHelper.enqueRequestWithJsonAndStatusCode(server, 404, postItem, getInstrumentation().getContext());
        launchActivity();
        Assert.assertEquals(0, getRecyclyViewItemCount());
    }*/


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
