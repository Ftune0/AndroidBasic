package com.ftune.basic.android.core.widget;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.ftune.basic.android.R;

/**
 * Created by ftune on 17/5/4.
 * <p>
 * Async request loading dialog.
 */
public class AsyncDialogFragment extends DialogFragment {

    private ImageView iv_loading;

    public static AsyncDialogFragment newInstance() {
        return new AsyncDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_async, null);
        iv_loading = (ImageView) view.findViewById(R.id.iv_loading);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AsyncDialogTheme);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void dismiss() {
        dismissAllowingStateLoss();
    }

    @Override
    public void dismissAllowingStateLoss() {
        if (iv_loading != null) {
            Drawable drawable = iv_loading.getDrawable();
            if (drawable != null && drawable instanceof AnimationDrawable)
                ((AnimationDrawable) drawable).stop();
            iv_loading.setImageBitmap(null);
        }
        super.dismissAllowingStateLoss();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (iv_loading != null) {
            AnimationDrawable animationDrawable = (AnimationDrawable) iv_loading.getDrawable();
            if (animationDrawable != null)
                animationDrawable.start();
        }
    }
}

