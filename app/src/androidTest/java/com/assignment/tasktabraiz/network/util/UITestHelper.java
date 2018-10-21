package hackernews.nouman.a2.hackernews.network.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.assignment.tasktabraiz.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;


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
}
