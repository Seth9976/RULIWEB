package androidx.constraintlayout.core.dsl;

public class OnSwipe {
    public static enum Boundary {
        OVERSHOOT,
        BOUNCE_START,
        BOUNCE_END,
        BOUNCE_BOTH;

        private static Boundary[] $values() [...] // Inlined contents
    }

    public static enum Drag {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        START,
        END,
        CLOCKWISE,
        ANTICLOCKWISE;

        private static Drag[] $values() [...] // Inlined contents
    }

    public static enum Mode {
        VELOCITY,
        SPRING;

        private static Mode[] $values() [...] // Inlined contents
    }

    public static enum Side {
        TOP,
        LEFT,
        RIGHT,
        BOTTOM,
        MIDDLE,
        START,
        END;

        private static Side[] $values() [...] // Inlined contents
    }

    public static enum TouchUp {
        AUTOCOMPLETE,
        TO_START,
        NEVER_COMPLETE_END,
        TO_END,
        STOP,
        DECELERATE,
        DECELERATE_COMPLETE,
        NEVER_COMPLETE_START;

        private static TouchUp[] $values() [...] // Inlined contents
    }

    public static final int FLAG_DISABLE_POST_SCROLL = 1;
    public static final int FLAG_DISABLE_SCROLL = 2;
    private Mode mAutoCompleteMode;
    private Drag mDragDirection;
    private float mDragScale;
    private float mDragThreshold;
    private String mLimitBoundsTo;
    private float mMaxAcceleration;
    private float mMaxVelocity;
    private TouchUp mOnTouchUp;
    private String mRotationCenterId;
    private Boundary mSpringBoundary;
    private float mSpringDamping;
    private float mSpringMass;
    private float mSpringStiffness;
    private float mSpringStopThreshold;
    private String mTouchAnchorId;
    private Side mTouchAnchorSide;

    public OnSwipe() {
        this.mDragDirection = null;
        this.mTouchAnchorSide = null;
        this.mTouchAnchorId = null;
        this.mLimitBoundsTo = null;
        this.mOnTouchUp = null;
        this.mRotationCenterId = null;
        this.mMaxVelocity = NaNf;
        this.mMaxAcceleration = NaNf;
        this.mDragScale = NaNf;
        this.mDragThreshold = NaNf;
        this.mSpringDamping = NaNf;
        this.mSpringMass = NaNf;
        this.mSpringStiffness = NaNf;
        this.mSpringStopThreshold = NaNf;
        this.mSpringBoundary = null;
        this.mAutoCompleteMode = null;
    }

    public OnSwipe(String s, Side onSwipe$Side0, Drag onSwipe$Drag0) {
        this.mLimitBoundsTo = null;
        this.mOnTouchUp = null;
        this.mRotationCenterId = null;
        this.mMaxVelocity = NaNf;
        this.mMaxAcceleration = NaNf;
        this.mDragScale = NaNf;
        this.mDragThreshold = NaNf;
        this.mSpringDamping = NaNf;
        this.mSpringMass = NaNf;
        this.mSpringStiffness = NaNf;
        this.mSpringStopThreshold = NaNf;
        this.mSpringBoundary = null;
        this.mAutoCompleteMode = null;
        this.mTouchAnchorId = s;
        this.mTouchAnchorSide = onSwipe$Side0;
        this.mDragDirection = onSwipe$Drag0;
    }

    public Mode getAutoCompleteMode() {
        return this.mAutoCompleteMode;
    }

    public Drag getDragDirection() {
        return this.mDragDirection;
    }

    public float getDragScale() {
        return this.mDragScale;
    }

    public float getDragThreshold() {
        return this.mDragThreshold;
    }

    public String getLimitBoundsTo() {
        return this.mLimitBoundsTo;
    }

    public float getMaxAcceleration() {
        return this.mMaxAcceleration;
    }

    public float getMaxVelocity() {
        return this.mMaxVelocity;
    }

    public TouchUp getOnTouchUp() {
        return this.mOnTouchUp;
    }

    public String getRotationCenterId() {
        return this.mRotationCenterId;
    }

    public Boundary getSpringBoundary() {
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

    public String getTouchAnchorId() {
        return this.mTouchAnchorId;
    }

    public Side getTouchAnchorSide() {
        return this.mTouchAnchorSide;
    }

    public void setAutoCompleteMode(Mode onSwipe$Mode0) {
        this.mAutoCompleteMode = onSwipe$Mode0;
    }

    public OnSwipe setDragDirection(Drag onSwipe$Drag0) {
        this.mDragDirection = onSwipe$Drag0;
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

    public OnSwipe setLimitBoundsTo(String s) {
        this.mLimitBoundsTo = s;
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

    public OnSwipe setOnTouchUp(TouchUp onSwipe$TouchUp0) {
        this.mOnTouchUp = onSwipe$TouchUp0;
        return this;
    }

    public OnSwipe setRotateCenter(String s) {
        this.mRotationCenterId = s;
        return this;
    }

    public OnSwipe setSpringBoundary(Boundary onSwipe$Boundary0) {
        this.mSpringBoundary = onSwipe$Boundary0;
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

    public OnSwipe setTouchAnchorId(String s) {
        this.mTouchAnchorId = s;
        return this;
    }

    public OnSwipe setTouchAnchorSide(Side onSwipe$Side0) {
        this.mTouchAnchorSide = onSwipe$Side0;
        return this;
    }

    @Override
    public String toString() [...] // 潜在的解密器
}

