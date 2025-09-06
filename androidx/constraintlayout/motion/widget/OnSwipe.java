package androidx.constraintlayout.motion.widget;

public class OnSwipe {
    public static final int COMPLETE_MODE_CONTINUOUS_VELOCITY = 0;
    public static final int COMPLETE_MODE_SPRING = 1;
    public static final int DRAG_ANTICLOCKWISE = 7;
    public static final int DRAG_CLOCKWISE = 6;
    public static final int DRAG_DOWN = 1;
    public static final int DRAG_END = 5;
    public static final int DRAG_LEFT = 2;
    public static final int DRAG_RIGHT = 3;
    public static final int DRAG_START = 4;
    public static final int DRAG_UP = 0;
    public static final int FLAG_DISABLE_POST_SCROLL = 1;
    public static final int FLAG_DISABLE_SCROLL = 2;
    public static final int ON_UP_AUTOCOMPLETE = 0;
    public static final int ON_UP_AUTOCOMPLETE_TO_END = 2;
    public static final int ON_UP_AUTOCOMPLETE_TO_START = 1;
    public static final int ON_UP_DECELERATE = 4;
    public static final int ON_UP_DECELERATE_AND_COMPLETE = 5;
    public static final int ON_UP_NEVER_TO_END = 7;
    public static final int ON_UP_NEVER_TO_START = 6;
    public static final int ON_UP_STOP = 3;
    public static final int SIDE_BOTTOM = 3;
    public static final int SIDE_END = 6;
    public static final int SIDE_LEFT = 1;
    public static final int SIDE_MIDDLE = 4;
    public static final int SIDE_RIGHT = 2;
    public static final int SIDE_START = 5;
    public static final int SIDE_TOP = 0;
    public static final int SPRING_BOUNDARY_BOUNCEBOTH = 3;
    public static final int SPRING_BOUNDARY_BOUNCEEND = 2;
    public static final int SPRING_BOUNDARY_BOUNCESTART = 1;
    public static final int SPRING_BOUNDARY_OVERSHOOT;
    private int mAutoCompleteMode;
    private int mDragDirection;
    private float mDragScale;
    private float mDragThreshold;
    private int mFlags;
    private int mLimitBoundsTo;
    private float mMaxAcceleration;
    private float mMaxVelocity;
    private boolean mMoveWhenScrollAtTop;
    private int mOnTouchUp;
    private int mRotationCenterId;
    private int mSpringBoundary;
    private float mSpringDamping;
    private float mSpringMass;
    private float mSpringStiffness;
    private float mSpringStopThreshold;
    private int mTouchAnchorId;
    private int mTouchAnchorSide;
    private int mTouchRegionId;

    public OnSwipe() {
        this.mDragDirection = 0;
        this.mTouchAnchorSide = 0;
        this.mTouchAnchorId = -1;
        this.mTouchRegionId = -1;
        this.mLimitBoundsTo = -1;
        this.mOnTouchUp = 0;
        this.mRotationCenterId = -1;
        this.mMaxVelocity = 4.0f;
        this.mMaxAcceleration = 1.2f;
        this.mMoveWhenScrollAtTop = true;
        this.mDragScale = 1.0f;
        this.mFlags = 0;
        this.mDragThreshold = 10.0f;
        this.mSpringDamping = NaNf;
        this.mSpringMass = 1.0f;
        this.mSpringStiffness = NaNf;
        this.mSpringStopThreshold = NaNf;
        this.mSpringBoundary = 0;
        this.mAutoCompleteMode = 0;
    }

    public int getAutoCompleteMode() {
        return this.mAutoCompleteMode;
    }

    public int getDragDirection() {
        return this.mDragDirection;
    }

    public float getDragScale() {
        return this.mDragScale;
    }

    public float getDragThreshold() {
        return this.mDragThreshold;
    }

    public int getLimitBoundsTo() {
        return this.mLimitBoundsTo;
    }

    public float getMaxAcceleration() {
        return this.mMaxAcceleration;
    }

    public float getMaxVelocity() {
        return this.mMaxVelocity;
    }

    public boolean getMoveWhenScrollAtTop() {
        return this.mMoveWhenScrollAtTop;
    }

    public int getNestedScrollFlags() {
        return this.mFlags;
    }

    public int getOnTouchUp() {
        return this.mOnTouchUp;
    }

    public int getRotationCenterId() {
        return this.mRotationCenterId;
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

    public int getTouchAnchorId() {
        return this.mTouchAnchorId;
    }

    public int getTouchAnchorSide() {
        return this.mTouchAnchorSide;
    }

    public int getTouchRegionId() {
        return this.mTouchRegionId;
    }

    public void setAutoCompleteMode(int v) {
        this.mAutoCompleteMode = v;
    }

    public OnSwipe setDragDirection(int v) {
        this.mDragDirection = v;
        return this;
    }

    public OnSwipe setDragScale(int v) {
        this.mDragScale = (float)v;
        return this;
    }

    public OnSwipe setDragThreshold(int v) {
        this.mDragThreshold = (float)v;
        return this;
    }

    public OnSwipe setLimitBoundsTo(int v) {
        this.mLimitBoundsTo = v;
        return this;
    }

    public OnSwipe setMaxAcceleration(int v) {
        this.mMaxAcceleration = (float)v;
        return this;
    }

    public OnSwipe setMaxVelocity(int v) {
        this.mMaxVelocity = (float)v;
        return this;
    }

    public OnSwipe setMoveWhenScrollAtTop(boolean z) {
        this.mMoveWhenScrollAtTop = z;
        return this;
    }

    public OnSwipe setNestedScrollFlags(int v) {
        this.mFlags = v;
        return this;
    }

    public OnSwipe setOnTouchUp(int v) {
        this.mOnTouchUp = v;
        return this;
    }

    public OnSwipe setRotateCenter(int v) {
        this.mRotationCenterId = v;
        return this;
    }

    public OnSwipe setSpringBoundary(int v) {
        this.mSpringBoundary = v;
        return this;
    }

    public OnSwipe setSpringDamping(float f) {
        this.mSpringDamping = f;
        return this;
    }

    public OnSwipe setSpringMass(float f) {
        this.mSpringMass = f;
        return this;
    }

    public OnSwipe setSpringStiffness(float f) {
        this.mSpringStiffness = f;
        return this;
    }

    public OnSwipe setSpringStopThreshold(float f) {
        this.mSpringStopThreshold = f;
        return this;
    }

    public OnSwipe setTouchAnchorId(int v) {
        this.mTouchAnchorId = v;
        return this;
    }

    public OnSwipe setTouchAnchorSide(int v) {
        this.mTouchAnchorSide = v;
        return this;
    }

    public OnSwipe setTouchRegionId(int v) {
        this.mTouchRegionId = v;
        return this;
    }
}

