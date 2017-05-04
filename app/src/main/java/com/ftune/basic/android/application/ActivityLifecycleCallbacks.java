package com.ftune.basic.android.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import timber.log.Timber;

/**
 * Created by ftune on 17/5/4.
 *
 * Activity life cycle callbacks.
 */

public class ActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private int activityCount;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Timber.d("activity %s created", activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Timber.d("activity %s started", activity.getClass().getSimpleName());
        activityCount++;
        if (activityCount == 1) {
            App.getApplication().inApp = true;
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Timber.d("activity %s resumed", activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Timber.d("activity %s paused", activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Timber.d("activity %s stopped", activity.getClass().getSimpleName());
        activityCount--;
        if (activityCount == 0) {
            App.getApplication().inApp = false;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Timber.d("activity %s save instance state", activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Timber.d("activity %s destroyed", activity.getClass().getSimpleName());
    }
}
