package com.google.android.material.motion;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.activity.BackEventCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.google.android.material.R.attr;

public abstract class MaterialBackAnimationHelper {
    private static final int CANCEL_DURATION_DEFAULT = 100;
    private static final int HIDE_DURATION_MAX_DEFAULT = 300;
    private static final int HIDE_DURATION_MIN_DEFAULT = 150;
    private static final String TAG = "MaterialBackHelper";
    private BackEventCompat backEvent;
    protected final int cancelDuration;
    protected final int hideDurationMax;
    protected final int hideDurationMin;
    private final TimeInterpolator progressInterpolator;
    protected final View view;

    public MaterialBackAnimationHelper(View view0) {
        this.view = view0;
        Context context0 = view0.getContext();
        Interpolator interpolator0 = PathInterpolatorCompat.create(0.0f, 0.0f, 0.0f, 1.0f);
        this.progressInterpolator = MotionUtils.resolveThemeInterpolator(context0, attr.motionEasingStandardDecelerateInterpolator, interpolator0);
        this.hideDurationMax = MotionUtils.resolveThemeDuration(context0, attr.motionDurationMedium2, 300);
        this.hideDurationMin = MotionUtils.resolveThemeDuration(context0, attr.motionDurationShort3, 150);
        this.cancelDuration = MotionUtils.resolveThemeDuration(context0, attr.motionDurationShort2, 100);
    }

    public float interpolateProgress(float f) {
        return this.progressInterpolator.getInterpolation(f);
    }

    protected BackEventCompat onCancelBackProgress() {
        if(this.backEvent == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() and updateBackProgress() before cancelBackProgress()");
        }
        BackEventCompat backEventCompat0 = this.backEvent;
        this.backEvent = null;
        return backEventCompat0;
    }

    public BackEventCompat onHandleBackInvoked() {
        BackEventCompat backEventCompat0 = this.backEvent;
        this.backEvent = null;
        return backEventCompat0;
    }

    protected void onStartBackProgress(BackEventCompat backEventCompat0) {
        this.backEvent = backEventCompat0;
    }

    protected BackEventCompat onUpdateBackProgress(BackEventCompat backEventCompat0) {
        if(this.backEvent == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() before updateBackProgress()");
        }
        BackEventCompat backEventCompat1 = this.backEvent;
        this.backEvent = backEventCompat0;
        return backEventCompat1;
    }
}

