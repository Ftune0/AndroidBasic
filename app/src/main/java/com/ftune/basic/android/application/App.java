package com.ftune.basic.android.application;

import android.app.Application;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by wufuchang on 17/5/4.
 *
 * Custom Application use to handle globle state or action.
 */

public class App extends Application {

    //If the application is foreground running.
    public boolean inApp = true;

    //Application instance
    private static App INSTATNCE = null;

    @Override
    public void onCreate() {
        super.onCreate();
        if (shouldInitApp()) {
            initApp();
        }
    }

    /**
     * Initial application env.
     */
    private void initApp() {
        INSTATNCE = this;
        registerActivityLifecycleCallbacks(new com.ftune.basic.android.application.ActivityLifecycleCallbacks());
        Thread.setDefaultUncaughtExceptionHandler(new RuntimeCrashHandler());
    }

    /**
     * If it is need to init application. Some 3th part sdk will fork a process while init,
     * and will do application onCreate again. Use this function to check.
     * @return true if application is not init.
     */
    private boolean shouldInitApp() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return (!TextUtils.isEmpty(processName) && processName.equals(this.getPackageName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get application instance
     * @return  application instance
     */
    public static synchronized App getApplication() {
        return INSTATNCE;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
