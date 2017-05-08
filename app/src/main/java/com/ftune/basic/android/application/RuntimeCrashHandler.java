package com.ftune.basic.android.application;

/**
 * Created by ftune on 17/5/4.
 * <p>
 * Runtime exception handler.
 */
public class RuntimeCrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler defaultHandler = null;

    public RuntimeCrashHandler() {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        handleException(e);
        if (defaultHandler != null) {
            defaultHandler.uncaughtException(t, e);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    /**
     * Handle uncaught exception here
     *
     * @param exception exception
     */
    private void handleException(Throwable exception) {

    }
}
