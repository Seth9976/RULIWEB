package androidx.core.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public class DifferentialMotionFlingController {
    interface DifferentialVelocityProvider {
        float getCurrentVelocity(VelocityTracker arg1, MotionEvent arg2, int arg3);
    }

    interface FlingVelocityThresholdCalculator {
        void calculateFlingVelocityThresholds(Context arg1, int[] arg2, MotionEvent arg3, int arg4);
    }

    private final Context mContext;
    private final int[] mFlingVelocityThresholds;
    private float mLastFlingVelocity;
    private int mLastProcessedAxis;
    private int mLastProcessedDeviceId;
    private int mLastProcessedSource;
    private final DifferentialMotionFlingTarget mTarget;
    private final DifferentialVelocityProvider mVelocityProvider;
    private final FlingVelocityThresholdCalculator mVelocityThresholdCalculator;
    private VelocityTracker mVelocityTracker;

    public DifferentialMotionFlingController(Context context0, DifferentialMotionFlingTarget differentialMotionFlingTarget0) {
        this(context0, differentialMotionFlingTarget0, (Context context0, int[] arr_v, MotionEvent motionEvent0, int v) -> {
            ViewConfiguration viewConfiguration0 = ViewConfiguration.get(context0);
            arr_v[0] = ViewConfigurationCompat.getScaledMinimumFlingVelocity(context0, viewConfiguration0, motionEvent0.getDeviceId(), v, motionEvent0.getSource());
            arr_v[1] = ViewConfigurationCompat.getScaledMaximumFlingVelocity(context0, viewConfiguration0, motionEvent0.getDeviceId(), v, motionEvent0.getSource());
        }, (VelocityTracker velocityTracker0, MotionEvent motionEvent0, int v) -> {
            VelocityTrackerCompat.addMovement(velocityTracker0, motionEvent0);
            VelocityTrackerCompat.computeCurrentVelocity(velocityTracker0, 1000);
            return VelocityTrackerCompat.getAxisVelocity(velocityTracker0, v);
        });
    }

    DifferentialMotionFlingController(Context context0, DifferentialMotionFlingTarget differentialMotionFlingTarget0, FlingVelocityThresholdCalculator differentialMotionFlingController$FlingVelocityThresholdCalculator0, DifferentialVelocityProvider differentialMotionFlingController$DifferentialVelocityProvider0) {
        this.mLastProcessedAxis = -1;
        this.mLastProcessedSource = -1;
        this.mLastProcessedDeviceId = -1;
        this.mFlingVelocityThresholds = new int[]{0x7FFFFFFF, 0};
        this.mContext = context0;
        this.mTarget = differentialMotionFlingTarget0;
        this.mVelocityThresholdCalculator = differentialMotionFlingController$FlingVelocityThresholdCalculator0;
        this.mVelocityProvider = differentialMotionFlingController$DifferentialVelocityProvider0;
    }

    // 检测为 Lambda 实现
    private static void calculateFlingVelocityThresholds(Context context0, int[] arr_v, MotionEvent motionEvent0, int v) [...]

    private boolean calculateFlingVelocityThresholds(MotionEvent motionEvent0, int v) {
        int v1 = motionEvent0.getSource();
        int v2 = motionEvent0.getDeviceId();
        if(this.mLastProcessedSource == v1 && this.mLastProcessedDeviceId == v2 && this.mLastProcessedAxis == v) {
            return false;
        }
        this.mVelocityThresholdCalculator.calculateFlingVelocityThresholds(this.mContext, this.mFlingVelocityThresholds, motionEvent0, v);
        this.mLastProcessedSource = v1;
        this.mLastProcessedDeviceId = v2;
        this.mLastProcessedAxis = v;
        return true;
    }

    private float getCurrentVelocity(MotionEvent motionEvent0, int v) {
        if(this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        return this.mVelocityProvider.getCurrentVelocity(this.mVelocityTracker, motionEvent0, v);
    }

    // 检测为 Lambda 实现
    private static float getCurrentVelocity(VelocityTracker velocityTracker0, MotionEvent motionEvent0, int v) [...]

    public void onMotionEvent(MotionEvent motionEvent0, int v) {
        boolean z = this.calculateFlingVelocityThresholds(motionEvent0, v);
        if(this.mFlingVelocityThresholds[0] == 0x7FFFFFFF) {
            VelocityTracker velocityTracker0 = this.mVelocityTracker;
            if(velocityTracker0 == null) {
                return;
            }
            velocityTracker0.recycle();
            this.mVelocityTracker = null;
            return;
        }
        float f = this.getCurrentVelocity(motionEvent0, v) * this.mTarget.getScaledScrollFactor();
        float f1 = Math.signum(f);
        float f2 = 0.0f;
        if(z || f1 != Math.signum(this.mLastFlingVelocity) && f1 != 0.0f) {
            this.mTarget.stopDifferentialMotionFling();
        }
        int[] arr_v = this.mFlingVelocityThresholds;
        if(Math.abs(f) < ((float)arr_v[0])) {
            return;
        }
        float f3 = Math.max(-arr_v[1], Math.min(f, arr_v[1]));
        if(this.mTarget.startDifferentialMotionFling(f3)) {
            f2 = f3;
        }
        this.mLastFlingVelocity = f2;
    }
}

