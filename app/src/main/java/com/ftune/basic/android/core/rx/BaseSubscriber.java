package com.ftune.basic.android.core.rx;

import android.widget.Toast;
import com.ftune.basic.android.R;
import com.ftune.basic.android.application.App;
import com.ftune.basic.android.core.exception.BaseException;
import com.ftune.basic.android.core.network.ApiException;
import rx.Subscriber;

/**
 * Created by ftune on 17/5/8.
 * <p>
 * Base subscriber with Rx. Handle globel error scene.
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();

        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            switch (apiException.errorCode) {
                case ApiException.SESSION_TIMEOUT:
                    Toast.makeText(App.getApplication(), R.string.toast_session_timeout, Toast.LENGTH_LONG).show();
                    App.getApplication().toLogon();
                    break;
                case ApiException.APP_UPDATE_INFO:
                    onUpdateInfo();
                    break;
                default:
                    onFailure(apiException.errorCode, apiException.getMessage());
                    break;
            }
        } else if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            onFailure(baseException.errorCode, baseException.getMessage());
        } else {
            onFailure(-1, "");
        }
    }

    public void onFailure(int errCode, String msg) {
    }

    public void onUpdateInfo() {
    }
}
