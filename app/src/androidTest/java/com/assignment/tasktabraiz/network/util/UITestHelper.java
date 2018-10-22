package com.assignment.tasktabraiz.network.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.MotionEvents;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.assignment.tasktabraiz.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;


public class UITestHelper {

    public static void enqueRequestWithJsonAndStatusCode(MockWebServer server, int statusCode,
                                                         String fileName, Context context) {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(statusCode);
        if (fileName != null) {
            mockResponse.setBody(loadJSONFromAsset(context, fileName));
        }
        server.enqueue(mockResponse);


    }


    public static String loadJSONFromAsset(Context context, String fileName) {

        String json;

        try {
            InputStream is = context.getResources().getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }


    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }

                return itemMatcher.matches((viewHolder.itemView).findViewById(R.id.movieTitle));
            }
        };
    }

    public static ViewAction touchDownAndUp() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayed();
            }

            @Override
            public String getDescription() {
                return "Send touch events.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                // Get view absolute position
                int[] location = new int[2];
                view.getLocationOnScreen(location);

                // Offset coordinates by view position
                float[] coordinates = new float[] { location[0], location[1] };
                float[] precision = new float[] { 1f, 1f };

                // Send down event, pause, and send up
                MotionEvent down = MotionEvents.sendDown(uiController, coordinates, precision).down;
                uiController.loopMainThreadForAtLeast(200);
                MotionEvents.sendUp(uiController, down, coordinates);
            }
        };
    }
}
