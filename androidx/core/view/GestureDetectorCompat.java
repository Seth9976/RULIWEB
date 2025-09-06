package androidx.core.view;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;

@Deprecated
public final class GestureDetectorCompat {
    private final GestureDetector mDetector;

    public GestureDetectorCompat(Context context0, GestureDetector.OnGestureListener gestureDetector$OnGestureListener0) {
        this(context0, gestureDetector$OnGestureListener0, null);
    }

    public GestureDetectorCompat(Context context0, GestureDetector.OnGestureListener gestureDetector$OnGestureListener0, Handler handler0) {
        this.mDetector = new GestureDetector(context0, gestureDetector$OnGestureListener0, handler0);
    }

    public boolean isLongpressEnabled() {
        return this.mDetector.isLongpressEnabled();
    }

    public boolean onTouchEvent(MotionEvent motionEvent0) {
        return this.mDetector.onTouchEvent(motionEvent0);
    }

    public void setIsLongpressEnabled(boolean z) {
        this.mDetector.setIsLongpressEnabled(z);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener gestureDetector$OnDoubleTapListener0) {
        this.mDetector.setOnDoubleTapListener(gestureDetector$OnDoubleTapListener0);
    }
}

