package com.khbd.app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.util.Logger;
import com.util.ToastUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import javakit.network.HttpClientResponse;
import okhttp3.ResponseBody;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.khbd.app", appContext.getPackageName());

    }
}
