package com.nhn.android.oauth.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.nhn.android.oauth.R.id;
import com.nhn.android.oauth.R.layout;

public final class NidProgressDialogBinding implements ViewBinding {
    public final LottieAnimationView nidProgressDialogAnimation;
    public final AppCompatTextView nidProgressDialogMessage;
    private final FrameLayout rootView;

    private NidProgressDialogBinding(FrameLayout frameLayout0, LottieAnimationView lottieAnimationView0, AppCompatTextView appCompatTextView0) {
        this.rootView = frameLayout0;
        this.nidProgressDialogAnimation = lottieAnimationView0;
        this.nidProgressDialogMessage = appCompatTextView0;
    }

    public static NidProgressDialogBinding bind(View view0) {
        int v = id.nid_progress_dialog_animation;
        LottieAnimationView lottieAnimationView0 = (LottieAnimationView)ViewBindings.findChildViewById(view0, v);
        if(lottieAnimationView0 != null) {
            v = id.nid_progress_dialog_message;
            AppCompatTextView appCompatTextView0 = (AppCompatTextView)ViewBindings.findChildViewById(view0, v);
            if(appCompatTextView0 != null) {
                return new NidProgressDialogBinding(((FrameLayout)view0), lottieAnimationView0, appCompatTextView0);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view0.getResources().getResourceName(v));
    }

    @Override  // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.getRoot();
    }

    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static NidProgressDialogBinding inflate(LayoutInflater layoutInflater0) {
        return NidProgressDialogBinding.inflate(layoutInflater0, null, false);
    }

    public static NidProgressDialogBinding inflate(LayoutInflater layoutInflater0, ViewGroup viewGroup0, boolean z) {
        View view0 = layoutInflater0.inflate(layout.nid_progress_dialog, viewGroup0, false);
        if(z) {
            viewGroup0.addView(view0);
        }
        return NidProgressDialogBinding.bind(view0);
    }
}

