package androidx.core.app;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

final class ActivityRecreator {
    static final class LifecycleCheckCallbacks implements Application.ActivityLifecycleCallbacks {
        Object currentlyRecreatingToken;
        private Activity mActivity;
        private boolean mDestroyed;
        private final int mRecreatingHashCode;
        private boolean mStarted;
        private boolean mStopQueued;

        LifecycleCheckCallbacks(Activity activity0) {
            this.mStarted = false;
            this.mDestroyed = false;
            this.mStopQueued = false;
            this.mActivity = activity0;
            this.mRecreatingHashCode = activity0.hashCode();
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity0, Bundle bundle0) {
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity0) {
            if(this.mActivity == activity0) {
                this.mActivity = null;
                this.mDestroyed = true;
            }
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity0) {
            if(this.mDestroyed && !this.mStopQueued && !this.mStarted && ActivityRecreator.queueOnStopIfNecessary(this.currentlyRecreatingToken, this.mRecreatingHashCode, activity0)) {
                this.mStopQueued = true;
                this.currentlyRecreatingToken = null;
            }
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity0) {
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity0) {
            if(this.mActivity == activity0) {
                this.mStarted = true;
            }
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity0) {
        }
    }

    private static final String LOG_TAG = "ActivityRecreator";
    protected static final Class activityThreadClass;
    private static final Handler mainHandler;
    protected static final Field mainThreadField;
    protected static final Method performStopActivity2ParamsMethod;
    protected static final Method performStopActivity3ParamsMethod;
    protected static final Method requestRelaunchActivityMethod;
    protected static final Field tokenField;

    static {
        ActivityRecreator.mainHandler = new Handler(Looper.getMainLooper());
        Class class0 = ActivityRecreator.getActivityThreadClass();
        ActivityRecreator.activityThreadClass = class0;
        ActivityRecreator.mainThreadField = ActivityRecreator.getMainThreadField();
        ActivityRecreator.tokenField = ActivityRecreator.getTokenField();
        ActivityRecreator.performStopActivity3ParamsMethod = ActivityRecreator.getPerformStopActivity3Params(class0);
        ActivityRecreator.performStopActivity2ParamsMethod = ActivityRecreator.getPerformStopActivity2Params(class0);
        ActivityRecreator.requestRelaunchActivityMethod = ActivityRecreator.getRequestRelaunchActivityMethod(class0);
    }

    private static Class getActivityThreadClass() {
        try {
            return Class.forName("android.app.ActivityThread");
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    private static Field getMainThreadField() {
        try {
            Field field0 = Activity.class.getDeclaredField("mMainThread");
            field0.setAccessible(true);
            return field0;
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    private static Method getPerformStopActivity2Params(Class class0) {
        if(class0 == null) {
            return null;
        }
        try {
            Method method0 = class0.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE);
            method0.setAccessible(true);
            return method0;
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    private static Method getPerformStopActivity3Params(Class class0) {
        if(class0 == null) {
            return null;
        }
        try {
            Method method0 = class0.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE, String.class);
            method0.setAccessible(true);
            return method0;
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    // 去混淆评级： 低(30)
    private static Method getRequestRelaunchActivityMethod(Class class0) {
        return null;
    }

    private static Field getTokenField() {
        try {
            Field field0 = Activity.class.getDeclaredField("mToken");
            field0.setAccessible(true);
            return field0;
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    private static boolean needsRelaunchCall() [...] // 潜在的解密器

    protected static boolean queueOnStopIfNecessary(Object object0, int v, Activity activity0) {
        try {
            Object object1 = ActivityRecreator.tokenField.get(activity0);
            if(object1 == object0 && activity0.hashCode() == v) {
                androidx.core.app.ActivityRecreator.3 activityRecreator$30 = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(ActivityRecreator.performStopActivity3ParamsMethod != null) {
                                ActivityRecreator.performStopActivity3ParamsMethod.invoke(ActivityRecreator.mainThreadField.get(activity0), object1, Boolean.FALSE, "AppCompat recreation");
                                return;
                            }
                            ActivityRecreator.performStopActivity2ParamsMethod.invoke(ActivityRecreator.mainThreadField.get(activity0), object1, Boolean.FALSE);
                        }
                        catch(RuntimeException runtimeException0) {
                            if(runtimeException0.getClass() == RuntimeException.class && runtimeException0.getMessage() != null && runtimeException0.getMessage().startsWith("Unable to stop")) {
                                throw runtimeException0;
                            }
                        }
                        catch(Throwable throwable0) {
                            Log.e("ActivityRecreator", "Exception while invoking performStopActivity", throwable0);
                        }
                    }
                };
                ActivityRecreator.mainHandler.postAtFrontOfQueue(activityRecreator$30);
                return true;
            }
        }
        catch(Throwable throwable0) {
            Log.e("ActivityRecreator", "Exception while fetching field values", throwable0);
        }
        return false;
    }

    static boolean recreate(Activity activity0) {
        if(Build.VERSION.SDK_INT >= 28) {
            activity0.recreate();
            return true;
        }
        if(ActivityRecreator.performStopActivity2ParamsMethod == null && ActivityRecreator.performStopActivity3ParamsMethod == null) {
            return false;
        }
        try {
            Object object0 = ActivityRecreator.tokenField.get(activity0);
            if(object0 == null) {
                return false;
            }
            if(ActivityRecreator.mainThreadField.get(activity0) == null) {
                return false;
            }
            Application application0 = activity0.getApplication();
            LifecycleCheckCallbacks activityRecreator$LifecycleCheckCallbacks0 = new LifecycleCheckCallbacks(activity0);
            application0.registerActivityLifecycleCallbacks(activityRecreator$LifecycleCheckCallbacks0);
            Handler handler0 = ActivityRecreator.mainHandler;
            handler0.post(new Runnable() {
                @Override
                public void run() {
                    activityRecreator$LifecycleCheckCallbacks0.currentlyRecreatingToken = object0;
                }
            });
            try {
                activity0.recreate();
            }
            catch(Throwable throwable0) {
                androidx.core.app.ActivityRecreator.2 activityRecreator$20 = new Runnable() {
                    @Override
                    public void run() {
                        application0.unregisterActivityLifecycleCallbacks(activityRecreator$LifecycleCheckCallbacks0);
                    }
                };
                ActivityRecreator.mainHandler.post(activityRecreator$20);
                throw throwable0;
            }
            handler0.post(new Runnable() {
                @Override
                public void run() {
                    application0.unregisterActivityLifecycleCallbacks(activityRecreator$LifecycleCheckCallbacks0);
                }
            });
            return true;
        }
        catch(Throwable unused_ex) {
            return false;
        }
    }
}

