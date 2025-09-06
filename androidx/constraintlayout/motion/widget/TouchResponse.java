package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.R.styleable;
import androidx.core.widget.NestedScrollView.OnScrollChangeListener;
import androidx.core.widget.NestedScrollView;
import org.xmlpull.v1.XmlPullParser;

class TouchResponse {
    public static final int COMPLETE_MODE_CONTINUOUS_VELOCITY = 0;
    public static final int COMPLETE_MODE_SPRING = 1;
    private static final boolean DEBUG = false;
    private static final float EPSILON = 1.000000E-07f;
    static final int FLAG_DISABLE_POST_SCROLL = 1;
    static final int FLAG_DISABLE_SCROLL = 2;
    static final int FLAG_SUPPORT_SCROLL_UP = 4;
    private static final int SEC_TO_MILLISECONDS = 1000;
    private static final int SIDE_BOTTOM = 3;
    private static final int SIDE_END = 6;
    private static final int SIDE_LEFT = 1;
    private static final int SIDE_MIDDLE = 4;
    private static final int SIDE_RIGHT = 2;
    private static final int SIDE_START = 5;
    private static final int SIDE_TOP = 0;
    private static final String TAG = "TouchResponse";
    private static final float[][] TOUCH_DIRECTION = null;
    private static final int TOUCH_DOWN = 1;
    private static final int TOUCH_END = 5;
    private static final int TOUCH_LEFT = 2;
    private static final int TOUCH_RIGHT = 3;
    private static final float[][] TOUCH_SIDES = null;
    private static final int TOUCH_START = 4;
    private static final int TOUCH_UP;
    private float[] mAnchorDpDt;
    private int mAutoCompleteMode;
    private float mDragScale;
    private boolean mDragStarted;
    private float mDragThreshold;
    private int mFlags;
    boolean mIsRotateMode;
    private float mLastTouchX;
    private float mLastTouchY;
    private int mLimitBoundsTo;
    private float mMaxAcceleration;
    private float mMaxVelocity;
    private final MotionLayout mMotionLayout;
    private boolean mMoveWhenScrollAtTop;
    private int mOnTouchUp;
    float mRotateCenterX;
    float mRotateCenterY;
    private int mRotationCenterId;
    private int mSpringBoundary;
    private float mSpringDamping;
    private float mSpringMass;
    private float mSpringStiffness;
    private float mSpringStopThreshold;
    private int[] mTempLoc;
    private int mTouchAnchorId;
    private int mTouchAnchorSide;
    private float mTouchAnchorX;
    private float mTouchAnchorY;
    private float mTouchDirectionX;
    private float mTouchDirectionY;
    private int mTouchRegionId;
    private int mTouchSide;

    static {
        TouchResponse.TOUCH_SIDES = new float[][]{new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
        TouchResponse.TOUCH_DIRECTION = new float[][]{new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    }

    TouchResponse(Context context0, MotionLayout motionLayout0, XmlPullParser xmlPullParser0) {
        this.mTouchAnchorSide = 0;
        this.mTouchSide = 0;
        this.mOnTouchUp = 0;
        this.mTouchAnchorId = -1;
        this.mTouchRegionId = -1;
        this.mLimitBoundsTo = -1;
        this.mTouchAnchorY = 0.5f;
        this.mTouchAnchorX = 0.5f;
        this.mRotateCenterX = 0.5f;
        this.mRotateCenterY = 0.5f;
        this.mRotationCenterId = -1;
        this.mIsRotateMode = false;
        this.mTouchDirectionX = 0.0f;
        this.mTouchDirectionY = 1.0f;
        this.mDragStarted = false;
        this.mAnchorDpDt = new float[2];
        this.mTempLoc = new int[2];
        this.mMaxVelocity = 4.0f;
        this.mMaxAcceleration = 1.2f;
        this.mMoveWhenScrollAtTop = true;
        this.mDragScale = 1.0f;
        this.mFlags = 0;
        this.mDragThreshold = 10.0f;
        this.mSpringDamping = 10.0f;
        this.mSpringMass = 1.0f;
        this.mSpringStiffness = NaNf;
        this.mSpringStopThreshold = NaNf;
        this.mSpringBoundary = 0;
        this.mAutoCompleteMode = 0;
        this.mMotionLayout = motionLayout0;
        this.fillFromAttributeList(context0, Xml.asAttributeSet(xmlPullParser0));
    }

    TouchResponse(MotionLayout motionLayout0, OnSwipe onSwipe0) {
        this.mTouchAnchorSide = 0;
        this.mTouchSide = 0;
        this.mOnTouchUp = 0;
        this.mTouchRegionId = -1;
        this.mLimitBoundsTo = -1;
        this.mTouchAnchorY = 0.5f;
        this.mTouchAnchorX = 0.5f;
        this.mRotateCenterX = 0.5f;
        this.mRotateCenterY = 0.5f;
        this.mRotationCenterId = -1;
        this.mIsRotateMode = false;
        this.mTouchDirectionX = 0.0f;
        this.mTouchDirectionY = 1.0f;
        this.mDragStarted = false;
        this.mAnchorDpDt = new float[2];
        this.mTempLoc = new int[2];
        this.mMaxVelocity = 4.0f;
        this.mMaxAcceleration = 1.2f;
        this.mMoveWhenScrollAtTop = true;
        this.mDragScale = 1.0f;
        this.mFlags = 0;
        this.mDragThreshold = 10.0f;
        this.mSpringDamping = 10.0f;
        this.mSpringMass = 1.0f;
        this.mSpringStiffness = NaNf;
        this.mSpringStopThreshold = NaNf;
        this.mSpringBoundary = 0;
        this.mAutoCompleteMode = 0;
        this.mMotionLayout = motionLayout0;
        this.mTouchAnchorId = onSwipe0.getTouchAnchorId();
        int v = onSwipe0.getTouchAnchorSide();
        this.mTouchAnchorSide = v;
        if(v != -1) {
            float[] arr_f = TouchResponse.TOUCH_SIDES[v];
            this.mTouchAnchorX = arr_f[0];
            this.mTouchAnchorY = arr_f[1];
        }
        int v1 = onSwipe0.getDragDirection();
        this.mTouchSide = v1;
        float[][] arr2_f = TouchResponse.TOUCH_DIRECTION;
        if(v1 < arr2_f.length) {
            float[] arr_f1 = arr2_f[v1];
            this.mTouchDirectionX = arr_f1[0];
            this.mTouchDirectionY = arr_f1[1];
        }
        else {
            this.mTouchDirectionY = NaNf;
            this.mTouchDirectionX = NaNf;
            this.mIsRotateMode = true;
        }
        this.mMaxVelocity = onSwipe0.getMaxVelocity();
        this.mMaxAcceleration = onSwipe0.getMaxAcceleration();
        this.mMoveWhenScrollAtTop = onSwipe0.getMoveWhenScrollAtTop();
        this.mDragScale = onSwipe0.getDragScale();
        this.mDragThreshold = onSwipe0.getDragThreshold();
        this.mTouchRegionId = onSwipe0.getTouchRegionId();
        this.mOnTouchUp = onSwipe0.getOnTouchUp();
        this.mFlags = onSwipe0.getNestedScrollFlags();
        this.mLimitBoundsTo = onSwipe0.getLimitBoundsTo();
        this.mRotationCenterId = onSwipe0.getRotationCenterId();
        this.mSpringBoundary = onSwipe0.getSpringBoundary();
        this.mSpringDamping = onSwipe0.getSpringDamping();
        this.mSpringMass = onSwipe0.getSpringMass();
        this.mSpringStiffness = onSwipe0.getSpringStiffness();
        this.mSpringStopThreshold = onSwipe0.getSpringStopThreshold();
        this.mAutoCompleteMode = onSwipe0.getAutoCompleteMode();
    }

    float dot(float f, float f1) {
        return f * this.mTouchDirectionX + f1 * this.mTouchDirectionY;
    }

    private void fill(TypedArray typedArray0) {
        int v = typedArray0.getIndexCount();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = typedArray0.getIndex(v1);
            if(v2 == styleable.OnSwipe_touchAnchorId) {
                this.mTouchAnchorId = typedArray0.getResourceId(v2, this.mTouchAnchorId);
            }
            else if(v2 == styleable.OnSwipe_touchAnchorSide) {
                int v3 = typedArray0.getInt(v2, this.mTouchAnchorSide);
                this.mTouchAnchorSide = v3;
                float[] arr_f = TouchResponse.TOUCH_SIDES[v3];
                this.mTouchAnchorX = arr_f[0];
                this.mTouchAnchorY = arr_f[1];
            }
            else if(v2 == styleable.OnSwipe_dragDirection) {
                int v4 = typedArray0.getInt(v2, this.mTouchSide);
                this.mTouchSide = v4;
                float[][] arr2_f = TouchResponse.TOUCH_DIRECTION;
                if(v4 < arr2_f.length) {
                    float[] arr_f1 = arr2_f[v4];
                    this.mTouchDirectionX = arr_f1[0];
                    this.mTouchDirectionY = arr_f1[1];
                }
                else {
                    this.mTouchDirectionY = NaNf;
                    this.mTouchDirectionX = NaNf;
                    this.mIsRotateMode = true;
                }
            }
            else if(v2 == styleable.OnSwipe_maxVelocity) {
                this.mMaxVelocity = typedArray0.getFloat(v2, this.mMaxVelocity);
            }
            else if(v2 == styleable.OnSwipe_maxAcceleration) {
                this.mMaxAcceleration = typedArray0.getFloat(v2, this.mMaxAcceleration);
            }
            else if(v2 == styleable.OnSwipe_moveWhenScrollAtTop) {
                this.mMoveWhenScrollAtTop = typedArray0.getBoolean(v2, this.mMoveWhenScrollAtTop);
            }
            else if(v2 == styleable.OnSwipe_dragScale) {
                this.mDragScale = typedArray0.getFloat(v2, this.mDragScale);
            }
            else if(v2 == styleable.OnSwipe_dragThreshold) {
                this.mDragThreshold = typedArray0.getFloat(v2, this.mDragThreshold);
            }
            else if(v2 == styleable.OnSwipe_touchRegionId) {
                this.mTouchRegionId = typedArray0.getResourceId(v2, this.mTouchRegionId);
            }
            else if(v2 == styleable.OnSwipe_onTouchUp) {
                this.mOnTouchUp = typedArray0.getInt(v2, this.mOnTouchUp);
            }
            else if(v2 == styleable.OnSwipe_nestedScrollFlags) {
                this.mFlags = typedArray0.getInteger(v2, 0);
            }
            else if(v2 == styleable.OnSwipe_limitBoundsTo) {
                this.mLimitBoundsTo = typedArray0.getResourceId(v2, 0);
            }
            else if(v2 == styleable.OnSwipe_rotationCenterId) {
                this.mRotationCenterId = typedArray0.getResourceId(v2, this.mRotationCenterId);
            }
            else if(v2 == styleable.OnSwipe_springDamping) {
                this.mSpringDamping = typedArray0.getFloat(v2, this.mSpringDamping);
            }
            else if(v2 == styleable.OnSwipe_springMass) {
                this.mSpringMass = typedArray0.getFloat(v2, this.mSpringMass);
            }
            else if(v2 == styleable.OnSwipe_springStiffness) {
                this.mSpringStiffness = typedArray0.getFloat(v2, this.mSpringStiffness);
            }
            else if(v2 == styleable.OnSwipe_springStopThreshold) {
                this.mSpringStopThreshold = typedArray0.getFloat(v2, this.mSpringStopThreshold);
            }
            else if(v2 == styleable.OnSwipe_springBoundary) {
                this.mSpringBoundary = typedArray0.getInt(v2, this.mSpringBoundary);
            }
            else if(v2 == styleable.OnSwipe_autoCompleteMode) {
                this.mAutoCompleteMode = typedArray0.getInt(v2, this.mAutoCompleteMode);
            }
        }
    }

    private void fillFromAttributeList(Context context0, AttributeSet attributeSet0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.OnSwipe);
        this.fill(typedArray0);
        typedArray0.recycle();
    }

    public int getAnchorId() {
        return this.mTouchAnchorId;
    }

    public int getAutoCompleteMode() {
        return this.mAutoCompleteMode;
    }

    public int getFlags() {
        return this.mFlags;
    }

    RectF getLimitBoundsTo(ViewGroup viewGroup0, RectF rectF0) {
        int v = this.mLimitBoundsTo;
        if(v == -1) {
            return null;
        }
        View view0 = viewGroup0.findViewById(v);
        if(view0 == null) {
            return null;
        }
        rectF0.set(((float)view0.getLeft()), ((float)view0.getTop()), ((float)view0.getRight()), ((float)view0.getBottom()));
        return rectF0;
    }

    int getLimitBoundsToId() {
        return this.mLimitBoundsTo;
    }

    float getMaxAcceleration() {
        return this.mMaxAcceleration;
    }

    public float getMaxVelocity() {
        return this.mMaxVelocity;
    }

    boolean getMoveWhenScrollAtTop() {
        return this.mMoveWhenScrollAtTop;
    }

    float getProgressDirection(float f, float f1) {
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, this.mMotionLayout.getProgress(), this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f2 = this.mTouchDirectionX;
        if(f2 != 0.0f) {
            float[] arr_f = this.mAnchorDpDt;
            if(arr_f[0] == 0.0f) {
                arr_f[0] = 1.000000E-07f;
            }
            return f * f2 / arr_f[0];
        }
        float[] arr_f1 = this.mAnchorDpDt;
        if(arr_f1[1] == 0.0f) {
            arr_f1[1] = 1.000000E-07f;
        }
        return f1 * this.mTouchDirectionY / arr_f1[1];
    }

    public int getSpringBoundary() {
        return this.mSpringBoundary;
    }

    public float getSpringDamping() {
        return this.mSpringDamping;
    }

    public float getSpringMass() {
        return this.mSpringMass;
    }

    public float getSpringStiffness() {
        return this.mSpringStiffness;
    }

    public float getSpringStopThreshold() {
        return this.mSpringStopThreshold;
    }

    RectF getTouchRegion(ViewGroup viewGroup0, RectF rectF0) {
        int v = this.mTouchRegionId;
        if(v == -1) {
            return null;
        }
        View view0 = viewGroup0.findViewById(v);
        if(view0 == null) {
            return null;
        }
        rectF0.set(((float)view0.getLeft()), ((float)view0.getTop()), ((float)view0.getRight()), ((float)view0.getBottom()));
        return rectF0;
    }

    int getTouchRegionId() {
        return this.mTouchRegionId;
    }

    boolean isDragStarted() {
        return this.mDragStarted;
    }

    void processTouchEvent(MotionEvent motionEvent0, MotionTracker motionLayout$MotionTracker0, int v, MotionScene motionScene0) {
        float f10;
        if(this.mIsRotateMode) {
            this.processTouchRotateEvent(motionEvent0, motionLayout$MotionTracker0, v, motionScene0);
            return;
        }
        motionLayout$MotionTracker0.addMovement(motionEvent0);
        switch(motionEvent0.getAction()) {
            case 0: {
                this.mLastTouchX = motionEvent0.getRawX();
                this.mLastTouchY = motionEvent0.getRawY();
                this.mDragStarted = false;
                return;
            }
            case 1: {
                this.mDragStarted = false;
                motionLayout$MotionTracker0.computeCurrentVelocity(1000);
                float f = motionLayout$MotionTracker0.getXVelocity();
                float f1 = motionLayout$MotionTracker0.getYVelocity();
                float f2 = this.mMotionLayout.getProgress();
                int v1 = this.mTouchAnchorId;
                if(v1 == -1) {
                    float f3 = (float)Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                    float[] arr_f = this.mAnchorDpDt;
                    arr_f[1] = this.mTouchDirectionY * f3;
                    arr_f[0] = f3 * this.mTouchDirectionX;
                }
                else {
                    this.mMotionLayout.getAnchorDpDt(v1, f2, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                }
                float f4 = this.mTouchDirectionX == 0.0f ? f1 / this.mAnchorDpDt[1] : f / this.mAnchorDpDt[0];
                float f5 = Float.isNaN(f4) ? f2 : f4 / 3.0f + f2;
                if(f5 == 0 || f5 == 0x3F800000) {
                label_41:
                    if(0.0f >= f5 || 1.0f <= f5) {
                        this.mMotionLayout.setState(TransitionState.FINISHED);
                        return;
                    }
                }
                else {
                    int v2 = this.mOnTouchUp;
                    if(v2 == 3) {
                        goto label_41;
                    }
                    else {
                        float f6 = ((double)f5) < 0.5 ? 0.0f : 1.0f;
                        if(v2 == 6) {
                            if(f2 + f4 < 0.0f) {
                                f4 = Math.abs(f4);
                            }
                            f6 = 1.0f;
                        }
                        if(this.mOnTouchUp == 7) {
                            if(f2 + f4 > 1.0f) {
                                f4 = -Math.abs(f4);
                            }
                            f6 = 0.0f;
                        }
                        this.mMotionLayout.touchAnimateTo(this.mOnTouchUp, f6, f4);
                        if(0.0f >= f2 || 1.0f <= f2) {
                            this.mMotionLayout.setState(TransitionState.FINISHED);
                            return;
                        }
                    }
                }
                break;
            }
            case 2: {
                float f7 = motionEvent0.getRawY() - this.mLastTouchY;
                float f8 = motionEvent0.getRawX() - this.mLastTouchX;
                if(Math.abs(this.mTouchDirectionX * f8 + this.mTouchDirectionY * f7) > this.mDragThreshold || this.mDragStarted) {
                    float f9 = this.mMotionLayout.getProgress();
                    if(!this.mDragStarted) {
                        this.mDragStarted = true;
                        this.mMotionLayout.setProgress(f9);
                    }
                    int v3 = this.mTouchAnchorId;
                    if(v3 == -1) {
                        f10 = f9;
                        float f11 = (float)Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                        float[] arr_f1 = this.mAnchorDpDt;
                        arr_f1[1] = this.mTouchDirectionY * f11;
                        arr_f1[0] = f11 * this.mTouchDirectionX;
                    }
                    else {
                        f10 = f9;
                        this.mMotionLayout.getAnchorDpDt(v3, f10, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                    }
                    if(((double)Math.abs((this.mTouchDirectionX * this.mAnchorDpDt[0] + this.mTouchDirectionY * this.mAnchorDpDt[1]) * this.mDragScale)) < 0.01) {
                        float[] arr_f2 = this.mAnchorDpDt;
                        arr_f2[0] = 0.01f;
                        arr_f2[1] = 0.01f;
                    }
                    float f12 = this.mTouchDirectionX == 0.0f ? f7 / this.mAnchorDpDt[1] : f8 / this.mAnchorDpDt[0];
                    float f13 = this.mOnTouchUp == 6 ? Math.max(Math.max(Math.min(f10 + f12, 1.0f), 0.0f), 0.01f) : Math.max(Math.min(f10 + f12, 1.0f), 0.0f);
                    if(this.mOnTouchUp == 7) {
                        f13 = Math.min(f13, 0.99f);
                    }
                    float f14 = this.mMotionLayout.getProgress();
                    if(f13 == f14) {
                        this.mMotionLayout.mLastVelocity = 0.0f;
                    }
                    else {
                        int v4 = Float.compare(f14, 0.0f);
                        if(v4 == 0 || f14 == 1.0f) {
                            this.mMotionLayout.endTrigger(v4 == 0);
                        }
                        this.mMotionLayout.setProgress(f13);
                        motionLayout$MotionTracker0.computeCurrentVelocity(1000);
                        float f15 = motionLayout$MotionTracker0.getXVelocity();
                        float f16 = motionLayout$MotionTracker0.getYVelocity();
                        this.mMotionLayout.mLastVelocity = this.mTouchDirectionX == 0.0f ? f16 / this.mAnchorDpDt[1] : f15 / this.mAnchorDpDt[0];
                    }
                    this.mLastTouchX = motionEvent0.getRawX();
                    this.mLastTouchY = motionEvent0.getRawY();
                    return;
                }
                break;
            }
        }
    }

    void processTouchRotateEvent(MotionEvent motionEvent0, MotionTracker motionLayout$MotionTracker0, int v, MotionScene motionScene0) {
        float f23;
        float f6;
        float f5;
        boolean z = true;
        motionLayout$MotionTracker0.addMovement(motionEvent0);
        int v1 = motionEvent0.getAction();
        if(v1 != 0) {
            switch(v1) {
                case 1: {
                    this.mDragStarted = false;
                    motionLayout$MotionTracker0.computeCurrentVelocity(16);
                    float f = motionLayout$MotionTracker0.getXVelocity();
                    float f1 = motionLayout$MotionTracker0.getYVelocity();
                    float f2 = this.mMotionLayout.getProgress();
                    float f3 = ((float)this.mMotionLayout.getWidth()) / 2.0f;
                    float f4 = ((float)this.mMotionLayout.getHeight()) / 2.0f;
                    int v2 = this.mRotationCenterId;
                    if(v2 == -1) {
                        int v3 = this.mTouchAnchorId;
                        if(v3 != -1) {
                            int v4 = this.mMotionLayout.getMotionController(v3).getAnimateRelativeTo();
                            View view1 = this.mMotionLayout.findViewById(v4);
                            this.mMotionLayout.getLocationOnScreen(this.mTempLoc);
                            f5 = ((float)this.mTempLoc[0]) + ((float)(view1.getLeft() + view1.getRight())) / 2.0f;
                            f6 = (float)this.mTempLoc[1];
                            f4 = ((float)(view1.getTop() + view1.getBottom())) / 2.0f + f6;
                            f3 = f5;
                        }
                    }
                    else {
                        View view0 = this.mMotionLayout.findViewById(v2);
                        this.mMotionLayout.getLocationOnScreen(this.mTempLoc);
                        f5 = ((float)this.mTempLoc[0]) + ((float)(view0.getLeft() + view0.getRight())) / 2.0f;
                        f6 = (float)this.mTempLoc[1];
                        f4 = ((float)(view0.getTop() + view0.getBottom())) / 2.0f + f6;
                        f3 = f5;
                    }
                    float f7 = motionEvent0.getRawX() - f3;
                    float f8 = motionEvent0.getRawY() - f4;
                    double f9 = Math.toDegrees(Math.atan2(f8, f7));
                    int v5 = this.mTouchAnchorId;
                    if(v5 == -1) {
                        this.mAnchorDpDt[1] = 360.0f;
                    }
                    else {
                        this.mMotionLayout.getAnchorDpDt(v5, f2, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                        this.mAnchorDpDt[1] = (float)Math.toDegrees(this.mAnchorDpDt[1]);
                    }
                    float f10 = ((float)(Math.toDegrees(Math.atan2(f1 + f8, f + f7)) - f9)) * 62.5f;
                    float f11 = Float.isNaN(f10) ? f2 : f10 * 3.0f * this.mDragScale / this.mAnchorDpDt[1] + f2;
                    if(f11 == 0 || f11 == 0x3F800000) {
                    label_59:
                        if(0.0f >= f11 || 1.0f <= f11) {
                            this.mMotionLayout.setState(TransitionState.FINISHED);
                            return;
                        }
                    }
                    else {
                        int v6 = this.mOnTouchUp;
                        if(v6 == 3) {
                            goto label_59;
                        }
                        else {
                            float f12 = f10 * this.mDragScale / this.mAnchorDpDt[1];
                            float f13 = ((double)f11) < 0.5 ? 0.0f : 1.0f;
                            if(v6 == 6) {
                                if(f2 + f12 < 0.0f) {
                                    f12 = Math.abs(f12);
                                }
                                f13 = 1.0f;
                            }
                            if(this.mOnTouchUp == 7) {
                                if(f2 + f12 > 1.0f) {
                                    f12 = -Math.abs(f12);
                                }
                                f13 = 0.0f;
                            }
                            this.mMotionLayout.touchAnimateTo(this.mOnTouchUp, f13, f12 * 3.0f);
                            if(0.0f >= f2 || 1.0f <= f2) {
                                this.mMotionLayout.setState(TransitionState.FINISHED);
                                return;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    motionEvent0.getRawY();
                    motionEvent0.getRawX();
                    float f14 = ((float)this.mMotionLayout.getWidth()) / 2.0f;
                    float f15 = ((float)this.mMotionLayout.getHeight()) / 2.0f;
                    int v7 = this.mRotationCenterId;
                    if(v7 == -1) {
                        int v10 = this.mTouchAnchorId;
                        if(v10 != -1) {
                            int v11 = this.mMotionLayout.getMotionController(v10).getAnimateRelativeTo();
                            View view3 = this.mMotionLayout.findViewById(v11);
                            if(view3 == null) {
                                Log.e("TouchResponse", "could not find view to animate to");
                            }
                            else {
                                this.mMotionLayout.getLocationOnScreen(this.mTempLoc);
                                f14 = ((float)this.mTempLoc[0]) + ((float)(view3.getLeft() + view3.getRight())) / 2.0f;
                                f15 = ((float)this.mTempLoc[1]) + ((float)(view3.getTop() + view3.getBottom())) / 2.0f;
                            }
                        }
                    }
                    else {
                        View view2 = this.mMotionLayout.findViewById(v7);
                        this.mMotionLayout.getLocationOnScreen(this.mTempLoc);
                        float f16 = (float)this.mTempLoc[0];
                        int v8 = view2.getLeft();
                        int v9 = view2.getRight();
                        float f17 = (float)this.mTempLoc[1];
                        f15 = ((float)(view2.getTop() + view2.getBottom())) / 2.0f + f17;
                        f14 = f16 + ((float)(v8 + v9)) / 2.0f;
                    }
                    float f18 = motionEvent0.getRawX();
                    float f19 = motionEvent0.getRawY();
                    double f20 = Math.atan2(motionEvent0.getRawY() - f15, motionEvent0.getRawX() - f14);
                    float f21 = (float)((f20 - Math.atan2(this.mLastTouchY - f15, this.mLastTouchX - f14)) * 180.0 / 3.141593);
                    if(f21 > 330.0f) {
                        f21 -= 360.0f;
                    }
                    else if(f21 < -330.0f) {
                        f21 += 360.0f;
                    }
                    if(((double)Math.abs(f21)) > 0.01 || this.mDragStarted) {
                        float f22 = this.mMotionLayout.getProgress();
                        if(!this.mDragStarted) {
                            this.mDragStarted = true;
                            this.mMotionLayout.setProgress(f22);
                        }
                        int v12 = this.mTouchAnchorId;
                        if(v12 == -1) {
                            f23 = f22;
                            this.mAnchorDpDt[1] = 360.0f;
                        }
                        else {
                            f23 = f22;
                            this.mMotionLayout.getAnchorDpDt(v12, f23, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                            this.mAnchorDpDt[1] = (float)Math.toDegrees(this.mAnchorDpDt[1]);
                        }
                        float f24 = Math.max(Math.min(f23 + f21 * this.mDragScale / this.mAnchorDpDt[1], 1.0f), 0.0f);
                        float f25 = this.mMotionLayout.getProgress();
                        if(f24 == f25) {
                            this.mMotionLayout.mLastVelocity = 0.0f;
                        }
                        else {
                            int v13 = Float.compare(f25, 0.0f);
                            if(v13 == 0 || f25 == 1.0f) {
                                MotionLayout motionLayout0 = this.mMotionLayout;
                                if(v13 != 0) {
                                    z = false;
                                }
                                motionLayout0.endTrigger(z);
                            }
                            this.mMotionLayout.setProgress(f24);
                            motionLayout$MotionTracker0.computeCurrentVelocity(1000);
                            float f26 = motionLayout$MotionTracker0.getXVelocity();
                            double f27 = (double)motionLayout$MotionTracker0.getYVelocity();
                            this.mMotionLayout.mLastVelocity = (float)Math.toDegrees(((float)(Math.hypot(f27, f26) * Math.sin(Math.atan2(f27, f26) - f20) / Math.hypot(f18 - f14, f19 - f15))));
                        }
                        this.mLastTouchX = motionEvent0.getRawX();
                        this.mLastTouchY = motionEvent0.getRawY();
                        return;
                    }
                    break;
                }
                default: {
                    return;
                }
            }
            return;
        }
        this.mLastTouchX = motionEvent0.getRawX();
        this.mLastTouchY = motionEvent0.getRawY();
        this.mDragStarted = false;
    }

    void scrollMove(float f, float f1) {
        float f2 = this.mMotionLayout.getProgress();
        if(!this.mDragStarted) {
            this.mDragStarted = true;
            this.mMotionLayout.setProgress(f2);
        }
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, f2, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        if(((double)Math.abs(this.mTouchDirectionX * this.mAnchorDpDt[0] + this.mTouchDirectionY * this.mAnchorDpDt[1])) < 0.01) {
            float[] arr_f = this.mAnchorDpDt;
            arr_f[0] = 0.01f;
            arr_f[1] = 0.01f;
        }
        float f3 = Math.max(Math.min(f2 + (this.mTouchDirectionX == 0.0f ? f1 * this.mTouchDirectionY / this.mAnchorDpDt[1] : f * this.mTouchDirectionX / this.mAnchorDpDt[0]), 1.0f), 0.0f);
        if(f3 != this.mMotionLayout.getProgress()) {
            this.mMotionLayout.setProgress(f3);
        }
    }

    void scrollUp(float f, float f1) {
        this.mDragStarted = false;
        float f2 = this.mMotionLayout.getProgress();
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, f2, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f3 = 0.0f;
        float f4 = this.mTouchDirectionX == 0.0f ? f1 * this.mTouchDirectionY / this.mAnchorDpDt[1] : f * this.mTouchDirectionX / this.mAnchorDpDt[0];
        if(!Float.isNaN(f4)) {
            f2 += f4 / 3.0f;
        }
        if(f2 != 0 && f2 != 0x3F800000) {
            int v = this.mOnTouchUp;
            if(v != 3) {
                MotionLayout motionLayout0 = this.mMotionLayout;
                if(((double)f2) >= 0.5) {
                    f3 = 1.0f;
                }
                motionLayout0.touchAnimateTo(v, f3, f4);
            }
        }
    }

    public void setAnchorId(int v) {
        this.mTouchAnchorId = v;
    }

    void setAutoCompleteMode(int v) {
        this.mAutoCompleteMode = v;
    }

    void setDown(float f, float f1) {
        this.mLastTouchX = f;
        this.mLastTouchY = f1;
    }

    public void setMaxAcceleration(float f) {
        this.mMaxAcceleration = f;
    }

    public void setMaxVelocity(float f) {
        this.mMaxVelocity = f;
    }

    public void setRTL(boolean z) {
        if(z) {
            TouchResponse.TOUCH_DIRECTION[4] = TouchResponse.TOUCH_DIRECTION[3];
            TouchResponse.TOUCH_DIRECTION[5] = TouchResponse.TOUCH_DIRECTION[2];
            TouchResponse.TOUCH_SIDES[5] = TouchResponse.TOUCH_SIDES[2];
            TouchResponse.TOUCH_SIDES[6] = TouchResponse.TOUCH_SIDES[1];
        }
        else {
            TouchResponse.TOUCH_DIRECTION[4] = TouchResponse.TOUCH_DIRECTION[2];
            TouchResponse.TOUCH_DIRECTION[5] = TouchResponse.TOUCH_DIRECTION[3];
            TouchResponse.TOUCH_SIDES[5] = TouchResponse.TOUCH_SIDES[1];
            TouchResponse.TOUCH_SIDES[6] = TouchResponse.TOUCH_SIDES[2];
        }
        float[] arr_f = TouchResponse.TOUCH_SIDES[this.mTouchAnchorSide];
        this.mTouchAnchorX = arr_f[0];
        this.mTouchAnchorY = arr_f[1];
        int v = this.mTouchSide;
        float[][] arr2_f = TouchResponse.TOUCH_DIRECTION;
        if(v >= arr2_f.length) {
            return;
        }
        float[] arr_f1 = arr2_f[v];
        this.mTouchDirectionX = arr_f1[0];
        this.mTouchDirectionY = arr_f1[1];
    }

    public void setTouchAnchorLocation(float f, float f1) {
        this.mTouchAnchorX = f;
        this.mTouchAnchorY = f1;
    }

    public void setTouchUpMode(int v) {
        this.mOnTouchUp = v;
    }

    void setUpTouchEvent(float f, float f1) {
        this.mLastTouchX = f;
        this.mLastTouchY = f1;
        this.mDragStarted = false;
    }

    void setupTouch() {
        View view0;
        int v = this.mTouchAnchorId;
        if(v == -1) {
            view0 = null;
        }
        else {
            view0 = this.mMotionLayout.findViewById(v);
            if(view0 == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + Debug.getName(this.mMotionLayout.getContext(), this.mTouchAnchorId));
            }
        }
        if(view0 instanceof NestedScrollView) {
            ((NestedScrollView)view0).setOnTouchListener(new View.OnTouchListener() {
                @Override  // android.view.View$OnTouchListener
                public boolean onTouch(View view0, MotionEvent motionEvent0) {
                    return false;
                }
            });
            ((NestedScrollView)view0).setOnScrollChangeListener(new OnScrollChangeListener() {
                @Override  // androidx.core.widget.NestedScrollView$OnScrollChangeListener
                public void onScrollChange(NestedScrollView nestedScrollView0, int v, int v1, int v2, int v3) {
                }
            });
        }
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return Float.isNaN(this.mTouchDirectionX) ? "rotation" : this.mTouchDirectionX + " , " + this.mTouchDirectionY;
    }
}

