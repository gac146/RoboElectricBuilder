package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RobolectricTest {
    Button btn;


    @Test
    public void testFirst() {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        TextView results = (TextView) activity.findViewById(R.id.textView);
        String resultsText = results.getText().toString();

        // failing tests gives much better feedback
        // to show that all works correctly
        assertThat(resultsText, equalTo("Hello world!"));

    }
    @Test
    public void testSecond() {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        ShadowActivity shadowActivity = shadowOf(activity);
        btn = (Button) activity.findViewById(R.id.myButton);
        btn.performClick();

        Intent intent = shadowActivity.getNextStartedActivity();
        assertThat(intent.getComponent().getClassName(), equalTo(Main2Activity.class.getName()));
    }



}
