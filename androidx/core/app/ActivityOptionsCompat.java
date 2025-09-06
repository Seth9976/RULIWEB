package androidx.core.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import androidx.core.util.Pair;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ActivityOptionsCompat {
    static class ActivityOptionsCompatImpl extends ActivityOptionsCompat {
        private final ActivityOptions mActivityOptions;

        ActivityOptionsCompatImpl(ActivityOptions activityOptions0) {
            this.mActivityOptions = activityOptions0;
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public Rect getLaunchBounds() {
            return Build.VERSION.SDK_INT >= 24 ? this.mActivityOptions.getLaunchBounds() : null;
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public int getLaunchDisplayId() {
            return Build.VERSION.SDK_INT < 26 ? -1 : this.mActivityOptions.getLaunchDisplayId();
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public void requestUsageTimeReport(PendingIntent pendingIntent0) {
            if(Build.VERSION.SDK_INT >= 23) {
                this.mActivityOptions.requestUsageTimeReport(pendingIntent0);
            }
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public ActivityOptionsCompat setLaunchBounds(Rect rect0) {
            return Build.VERSION.SDK_INT >= 24 ? new ActivityOptionsCompatImpl(this.mActivityOptions.setLaunchBounds(rect0)) : this;
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public ActivityOptionsCompat setLaunchDisplayId(int v) {
            if(Build.VERSION.SDK_INT >= 26) {
                this.mActivityOptions.setLaunchDisplayId(v);
            }
            return this;
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public ActivityOptionsCompat setPendingIntentBackgroundActivityStartMode(int v) {
            if(Build.VERSION.SDK_INT >= 34) {
                this.mActivityOptions.setPendingIntentBackgroundActivityStartMode(v);
                return this;
            }
            if(Build.VERSION.SDK_INT >= 33) {
                this.mActivityOptions.setPendingIntentBackgroundActivityLaunchAllowed(v != 2);
            }
            return this;
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public ActivityOptionsCompat setShareIdentityEnabled(boolean z) {
            return Build.VERSION.SDK_INT >= 34 ? new ActivityOptionsCompatImpl(this.mActivityOptions.setShareIdentityEnabled(z)) : this;
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public Bundle toBundle() {
            return this.mActivityOptions.toBundle();
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public void update(ActivityOptionsCompat activityOptionsCompat0) {
            if(activityOptionsCompat0 instanceof ActivityOptionsCompatImpl) {
                this.mActivityOptions.update(((ActivityOptionsCompatImpl)activityOptionsCompat0).mActivityOptions);
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BackgroundActivityStartMode {
    }

    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";

    public Rect getLaunchBounds() {
        return null;
    }

    public int getLaunchDisplayId() {
        return -1;
    }

    public static ActivityOptionsCompat makeBasic() {
        return Build.VERSION.SDK_INT >= 23 ? new ActivityOptionsCompatImpl(ActivityOptions.makeBasic()) : new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeClipRevealAnimation(View view0, int v, int v1, int v2, int v3) {
        return Build.VERSION.SDK_INT >= 23 ? new ActivityOptionsCompatImpl(ActivityOptions.makeClipRevealAnimation(view0, v, v1, v2, v3)) : new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeCustomAnimation(Context context0, int v, int v1) {
        return new ActivityOptionsCompatImpl(ActivityOptions.makeCustomAnimation(context0, v, v1));
    }

    public static ActivityOptionsCompat makeScaleUpAnimation(View view0, int v, int v1, int v2, int v3) {
        return new ActivityOptionsCompatImpl(ActivityOptions.makeScaleUpAnimation(view0, v, v1, v2, v3));
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity0, View view0, String s) {
        return new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity0, view0, s));
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity0, Pair[] arr_pair) {
        if(arr_pair != null) {
            android.util.Pair[] arr_pair1 = new android.util.Pair[arr_pair.length];
            for(int v = 0; v < arr_pair.length; ++v) {
                arr_pair1[v] = android.util.Pair.create(((View)arr_pair[v].first), ((String)arr_pair[v].second));
            }
            return new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity0, arr_pair1));
        }
        return new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity0, null));
    }

    public static ActivityOptionsCompat makeTaskLaunchBehind() {
        return new ActivityOptionsCompatImpl(ActivityOptions.makeTaskLaunchBehind());
    }

    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View view0, Bitmap bitmap0, int v, int v1) {
        return new ActivityOptionsCompatImpl(ActivityOptions.makeThumbnailScaleUpAnimation(view0, bitmap0, v, v1));
    }

    public void requestUsageTimeReport(PendingIntent pendingIntent0) {
    }

    public ActivityOptionsCompat setLaunchBounds(Rect rect0) {
        return this;
    }

    public ActivityOptionsCompat setLaunchDisplayId(int v) {
        return this;
    }

    public ActivityOptionsCompat setPendingIntentBackgroundActivityStartMode(int v) {
        return this;
    }

    public ActivityOptionsCompat setShareIdentityEnabled(boolean z) {
        return this;
    }

    public Bundle toBundle() {
        return null;
    }

    public void update(ActivityOptionsCompat activityOptionsCompat0) {
    }
}

