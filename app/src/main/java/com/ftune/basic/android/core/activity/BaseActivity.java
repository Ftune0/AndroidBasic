package com.ftune.basic.android.core.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.ftune.basic.android.R;
import com.ftune.basic.android.core.widget.AsyncDialogFragment;

/**
 * Created by ftune on 17/5/4.
 * <p>
 * Base activity.
 */
public class BaseActivity extends AppCompatActivity {

    private AsyncDialogFragment asyncDialogFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setUpToolBar();
    }

    public void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView ivBack = (ImageView) toolbar.findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> onBack());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAsyncDialog(boolean cancelable) {
        if (asyncDialogFragment != null) {
            return;
        }
        asyncDialogFragment = AsyncDialogFragment.newInstance();
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(asyncDialogFragment, getLocalClassName());
        transaction.commitAllowingStateLoss();
        asyncDialogFragment.setCancelable(cancelable);
    }

    public void dismissAsyncDialog() {
        if (asyncDialogFragment != null) {
            asyncDialogFragment.dismiss();
        }
        asyncDialogFragment = null;
    }

    public void showDialog(DialogFragment dialogFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(dialogFragment, dialogFragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }

    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    public void toast(String toast, String defualt) {
        if (TextUtils.isEmpty(toast)) {
            Toast.makeText(this, defualt, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void toast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public void setTitle(String title) {
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        titleTv.setText(title);
    }

    public void setTitle(int resId) {
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        titleTv.setText(resId);
    }

    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        onBack();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    public void layoutFullScreenSupport() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }
}
