package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.state.helpers.Facade;
import androidx.constraintlayout.core.widgets.ConstraintAnchor.Type;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintReference implements Reference {
    public interface ConstraintReferenceFactory {
        ConstraintReference create(State arg1);
    }

    static class IncorrectConstraintException extends Exception {
        private final ArrayList mErrors;

        IncorrectConstraintException(ArrayList arrayList0) {
            this.mErrors = arrayList0;
        }

        public ArrayList getErrors() {
            return this.mErrors;
        }

        @Override
        public String getMessage() {
            return this.toString();
        }

        @Override
        public String toString() {
            return "IncorrectConstraintException: " + this.mErrors.toString();
        }
    }

    float mAlpha;
    Object mBaselineToBaseline;
    Object mBaselineToBottom;
    Object mBaselineToTop;
    Object mBottomToBaseline;
    protected Object mBottomToBottom;
    protected Object mBottomToTop;
    private float mCircularAngle;
    Object mCircularConstraint;
    private float mCircularDistance;
    private ConstraintWidget mConstraintWidget;
    private HashMap mCustomColors;
    private HashMap mCustomFloats;
    protected Object mEndToEnd;
    protected Object mEndToStart;
    Facade mFacade;
    protected float mHorizontalBias;
    int mHorizontalChainStyle;
    float mHorizontalChainWeight;
    Dimension mHorizontalDimension;
    private Object mKey;
    Constraint mLast;
    protected Object mLeftToLeft;
    protected Object mLeftToRight;
    int mMarginBaseline;
    int mMarginBaselineGone;
    protected int mMarginBottom;
    protected int mMarginBottomGone;
    protected int mMarginEnd;
    protected int mMarginEndGone;
    protected int mMarginLeft;
    protected int mMarginLeftGone;
    protected int mMarginRight;
    protected int mMarginRightGone;
    protected int mMarginStart;
    protected int mMarginStartGone;
    protected int mMarginTop;
    protected int mMarginTopGone;
    TypedBundle mMotionProperties;
    float mPivotX;
    float mPivotY;
    protected Object mRightToLeft;
    protected Object mRightToRight;
    float mRotationX;
    float mRotationY;
    float mRotationZ;
    float mScaleX;
    float mScaleY;
    protected Object mStartToEnd;
    protected Object mStartToStart;
    final State mState;
    String mTag;
    Object mTopToBaseline;
    protected Object mTopToBottom;
    protected Object mTopToTop;
    float mTranslationX;
    float mTranslationY;
    float mTranslationZ;
    protected float mVerticalBias;
    int mVerticalChainStyle;
    float mVerticalChainWeight;
    Dimension mVerticalDimension;
    private Object mView;
    int mVisibility;

    public ConstraintReference(State state0) {
        this.mTag = null;
        this.mFacade = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainWeight = -1.0f;
        this.mVerticalChainWeight = -1.0f;
        this.mHorizontalBias = 0.5f;
        this.mVerticalBias = 0.5f;
        this.mMarginLeft = 0;
        this.mMarginRight = 0;
        this.mMarginStart = 0;
        this.mMarginEnd = 0;
        this.mMarginTop = 0;
        this.mMarginBottom = 0;
        this.mMarginLeftGone = 0;
        this.mMarginRightGone = 0;
        this.mMarginStartGone = 0;
        this.mMarginEndGone = 0;
        this.mMarginTopGone = 0;
        this.mMarginBottomGone = 0;
        this.mMarginBaseline = 0;
        this.mMarginBaselineGone = 0;
        this.mPivotX = NaNf;
        this.mPivotY = NaNf;
        this.mRotationX = NaNf;
        this.mRotationY = NaNf;
        this.mRotationZ = NaNf;
        this.mTranslationX = NaNf;
        this.mTranslationY = NaNf;
        this.mTranslationZ = NaNf;
        this.mAlpha = NaNf;
        this.mScaleX = NaNf;
        this.mScaleY = NaNf;
        this.mVisibility = 0;
        this.mLeftToLeft = null;
        this.mLeftToRight = null;
        this.mRightToLeft = null;
        this.mRightToRight = null;
        this.mStartToStart = null;
        this.mStartToEnd = null;
        this.mEndToStart = null;
        this.mEndToEnd = null;
        this.mTopToTop = null;
        this.mTopToBottom = null;
        this.mTopToBaseline = null;
        this.mBottomToTop = null;
        this.mBottomToBottom = null;
        this.mBottomToBaseline = null;
        this.mBaselineToBaseline = null;
        this.mBaselineToTop = null;
        this.mBaselineToBottom = null;
        this.mCircularConstraint = null;
        this.mLast = null;
        this.mHorizontalDimension = Dimension.createFixed(Dimension.WRAP_DIMENSION);
        this.mVerticalDimension = Dimension.createFixed(Dimension.WRAP_DIMENSION);
        this.mCustomColors = new HashMap();
        this.mCustomFloats = new HashMap();
        this.mMotionProperties = null;
        this.mState = state0;
    }

    public void addCustomColor(String s, int v) {
        this.mCustomColors.put(s, v);
    }

    public void addCustomFloat(String s, float f) {
        if(this.mCustomFloats == null) {
            this.mCustomFloats = new HashMap();
        }
        this.mCustomFloats.put(s, f);
    }

    public ConstraintReference alpha(float f) {
        this.mAlpha = f;
        return this;
    }

    @Override  // androidx.constraintlayout.core.state.Reference
    public void apply() {
        if(this.mConstraintWidget != null) {
            Facade facade0 = this.mFacade;
            if(facade0 != null) {
                facade0.apply();
            }
            this.mHorizontalDimension.apply(this.mState, this.mConstraintWidget, 0);
            this.mVerticalDimension.apply(this.mState, this.mConstraintWidget, 1);
            this.dereference();
            this.applyWidgetConstraints();
            int v = this.mHorizontalChainStyle;
            if(v != 0) {
                this.mConstraintWidget.setHorizontalChainStyle(v);
            }
            int v1 = this.mVerticalChainStyle;
            if(v1 != 0) {
                this.mConstraintWidget.setVerticalChainStyle(v1);
            }
            float f = this.mHorizontalChainWeight;
            if(f != -1.0f) {
                this.mConstraintWidget.setHorizontalWeight(f);
            }
            float f1 = this.mVerticalChainWeight;
            if(f1 != -1.0f) {
                this.mConstraintWidget.setVerticalWeight(f1);
            }
            this.mConstraintWidget.setHorizontalBiasPercent(this.mHorizontalBias);
            this.mConstraintWidget.setVerticalBiasPercent(this.mVerticalBias);
            this.mConstraintWidget.frame.pivotX = this.mPivotX;
            this.mConstraintWidget.frame.pivotY = this.mPivotY;
            this.mConstraintWidget.frame.rotationX = this.mRotationX;
            this.mConstraintWidget.frame.rotationY = this.mRotationY;
            this.mConstraintWidget.frame.rotationZ = this.mRotationZ;
            this.mConstraintWidget.frame.translationX = this.mTranslationX;
            this.mConstraintWidget.frame.translationY = this.mTranslationY;
            this.mConstraintWidget.frame.translationZ = this.mTranslationZ;
            this.mConstraintWidget.frame.scaleX = this.mScaleX;
            this.mConstraintWidget.frame.scaleY = this.mScaleY;
            this.mConstraintWidget.frame.alpha = this.mAlpha;
            this.mConstraintWidget.frame.visibility = this.mVisibility;
            this.mConstraintWidget.setVisibility(this.mVisibility);
            this.mConstraintWidget.frame.setMotionAttributes(this.mMotionProperties);
            HashMap hashMap0 = this.mCustomColors;
            if(hashMap0 != null) {
                for(Object object0: hashMap0.keySet()) {
                    Integer integer0 = (Integer)this.mCustomColors.get(((String)object0));
                    this.mConstraintWidget.frame.setCustomAttribute(((String)object0), 902, ((int)integer0));
                }
            }
            HashMap hashMap1 = this.mCustomFloats;
            if(hashMap1 != null) {
                for(Object object1: hashMap1.keySet()) {
                    float f2 = (float)(((Float)this.mCustomFloats.get(((String)object1))));
                    this.mConstraintWidget.frame.setCustomAttribute(((String)object1), 901, f2);
                }
            }
        }
    }

    private void applyConnection(ConstraintWidget constraintWidget0, Object object0, Constraint state$Constraint0) {
        ConstraintWidget constraintWidget1 = this.getTarget(object0);
        if(constraintWidget1 != null) {
            int v = androidx.constraintlayout.core.state.ConstraintReference.1.$SwitchMap$androidx$constraintlayout$core$state$State$Constraint[state$Constraint0.ordinal()];
            switch(androidx.constraintlayout.core.state.ConstraintReference.1.$SwitchMap$androidx$constraintlayout$core$state$State$Constraint[state$Constraint0.ordinal()]) {
                case 1: {
                    constraintWidget0.getAnchor(Type.LEFT).connect(constraintWidget1.getAnchor(Type.LEFT), this.mMarginLeft, this.mMarginLeftGone, false);
                    break;
                }
                case 2: {
                    constraintWidget0.getAnchor(Type.LEFT).connect(constraintWidget1.getAnchor(Type.RIGHT), this.mMarginLeft, this.mMarginLeftGone, false);
                    return;
                }
                case 3: {
                    constraintWidget0.getAnchor(Type.RIGHT).connect(constraintWidget1.getAnchor(Type.LEFT), this.mMarginRight, this.mMarginRightGone, false);
                    return;
                }
                case 4: {
                    constraintWidget0.getAnchor(Type.RIGHT).connect(constraintWidget1.getAnchor(Type.RIGHT), this.mMarginRight, this.mMarginRightGone, false);
                    return;
                }
                case 5: {
                    constraintWidget0.getAnchor(Type.LEFT).connect(constraintWidget1.getAnchor(Type.LEFT), this.mMarginStart, this.mMarginStartGone, false);
                    return;
                }
                case 6: {
                    constraintWidget0.getAnchor(Type.LEFT).connect(constraintWidget1.getAnchor(Type.RIGHT), this.mMarginStart, this.mMarginStartGone, false);
                    return;
                }
                case 7: {
                    constraintWidget0.getAnchor(Type.RIGHT).connect(constraintWidget1.getAnchor(Type.LEFT), this.mMarginEnd, this.mMarginEndGone, false);
                    return;
                }
                case 8: {
                    constraintWidget0.getAnchor(Type.RIGHT).connect(constraintWidget1.getAnchor(Type.RIGHT), this.mMarginEnd, this.mMarginEndGone, false);
                    return;
                }
                case 9: {
                    constraintWidget0.getAnchor(Type.TOP).connect(constraintWidget1.getAnchor(Type.TOP), this.mMarginTop, this.mMarginTopGone, false);
                    return;
                }
                case 10: {
                    constraintWidget0.getAnchor(Type.TOP).connect(constraintWidget1.getAnchor(Type.BOTTOM), this.mMarginTop, this.mMarginTopGone, false);
                    return;
                }
                case 11: {
                    constraintWidget0.immediateConnect(Type.TOP, constraintWidget1, Type.BASELINE, this.mMarginTop, this.mMarginTopGone);
                    return;
                }
                case 12: {
                    constraintWidget0.getAnchor(Type.BOTTOM).connect(constraintWidget1.getAnchor(Type.TOP), this.mMarginBottom, this.mMarginBottomGone, false);
                    return;
                }
                case 13: {
                    constraintWidget0.getAnchor(Type.BOTTOM).connect(constraintWidget1.getAnchor(Type.BOTTOM), this.mMarginBottom, this.mMarginBottomGone, false);
                    return;
                }
                case 14: {
                    constraintWidget0.immediateConnect(Type.BOTTOM, constraintWidget1, Type.BASELINE, this.mMarginBottom, this.mMarginBottomGone);
                    return;
                }
                case 15: {
                    constraintWidget0.immediateConnect(Type.BASELINE, constraintWidget1, Type.BOTTOM, this.mMarginBaseline, this.mMarginBaselineGone);
                    return;
                }
                case 16: {
                    constraintWidget0.immediateConnect(Type.BASELINE, constraintWidget1, Type.TOP, this.mMarginBaseline, this.mMarginBaselineGone);
                    return;
                }
                case 17: {
                    constraintWidget0.immediateConnect(Type.BASELINE, constraintWidget1, Type.BASELINE, this.mMarginBaseline, this.mMarginBaselineGone);
                    return;
                }
                case 18: {
                    constraintWidget0.connectCircularConstraint(constraintWidget1, this.mCircularAngle, ((int)this.mCircularDistance));
                }
            }
        }
    }

    public void applyWidgetConstraints() {
        this.applyConnection(this.mConstraintWidget, this.mLeftToLeft, Constraint.LEFT_TO_LEFT);
        this.applyConnection(this.mConstraintWidget, this.mLeftToRight, Constraint.LEFT_TO_RIGHT);
        this.applyConnection(this.mConstraintWidget, this.mRightToLeft, Constraint.RIGHT_TO_LEFT);
        this.applyConnection(this.mConstraintWidget, this.mRightToRight, Constraint.RIGHT_TO_RIGHT);
        this.applyConnection(this.mConstraintWidget, this.mStartToStart, Constraint.START_TO_START);
        this.applyConnection(this.mConstraintWidget, this.mStartToEnd, Constraint.START_TO_END);
        this.applyConnection(this.mConstraintWidget, this.mEndToStart, Constraint.END_TO_START);
        this.applyConnection(this.mConstraintWidget, this.mEndToEnd, Constraint.END_TO_END);
        this.applyConnection(this.mConstraintWidget, this.mTopToTop, Constraint.TOP_TO_TOP);
        this.applyConnection(this.mConstraintWidget, this.mTopToBottom, Constraint.TOP_TO_BOTTOM);
        this.applyConnection(this.mConstraintWidget, this.mTopToBaseline, Constraint.TOP_TO_BASELINE);
        this.applyConnection(this.mConstraintWidget, this.mBottomToTop, Constraint.BOTTOM_TO_TOP);
        this.applyConnection(this.mConstraintWidget, this.mBottomToBottom, Constraint.BOTTOM_TO_BOTTOM);
        this.applyConnection(this.mConstraintWidget, this.mBottomToBaseline, Constraint.BOTTOM_TO_BASELINE);
        this.applyConnection(this.mConstraintWidget, this.mBaselineToBaseline, Constraint.BASELINE_TO_BASELINE);
        this.applyConnection(this.mConstraintWidget, this.mBaselineToTop, Constraint.BASELINE_TO_TOP);
        this.applyConnection(this.mConstraintWidget, this.mBaselineToBottom, Constraint.BASELINE_TO_BOTTOM);
        this.applyConnection(this.mConstraintWidget, this.mCircularConstraint, Constraint.CIRCULAR_CONSTRAINT);
    }

    public ConstraintReference baseline() {
        this.mLast = Constraint.BASELINE_TO_BASELINE;
        return this;
    }

    public ConstraintReference baselineToBaseline(Object object0) {
        this.mLast = Constraint.BASELINE_TO_BASELINE;
        this.mBaselineToBaseline = object0;
        return this;
    }

    public ConstraintReference baselineToBottom(Object object0) {
        this.mLast = Constraint.BASELINE_TO_BOTTOM;
        this.mBaselineToBottom = object0;
        return this;
    }

    public ConstraintReference baselineToTop(Object object0) {
        this.mLast = Constraint.BASELINE_TO_TOP;
        this.mBaselineToTop = object0;
        return this;
    }

    public ConstraintReference bias(float f) {
        if(this.mLast != null) {
            switch(this.mLast) {
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: 
                case 8: 
                case 19: {
                    this.mHorizontalBias = f;
                    return this;
                }
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 13: 
                case 14: 
                case 20: {
                    this.mVerticalBias = f;
                    return this;
                }
                default: {
                    return this;
                }
            }
        }
        return this;
    }

    public ConstraintReference bottom() {
        if(this.mBottomToTop != null) {
            this.mLast = Constraint.BOTTOM_TO_TOP;
            return this;
        }
        this.mLast = Constraint.BOTTOM_TO_BOTTOM;
        return this;
    }

    ConstraintReference bottomToBaseline(Object object0) {
        this.mLast = Constraint.BOTTOM_TO_BASELINE;
        this.mBottomToBaseline = object0;
        return this;
    }

    public ConstraintReference bottomToBottom(Object object0) {
        this.mLast = Constraint.BOTTOM_TO_BOTTOM;
        this.mBottomToBottom = object0;
        return this;
    }

    public ConstraintReference bottomToTop(Object object0) {
        this.mLast = Constraint.BOTTOM_TO_TOP;
        this.mBottomToTop = object0;
        return this;
    }

    public ConstraintReference centerHorizontally(Object object0) {
        Object object1 = this.get(object0);
        this.mStartToStart = object1;
        this.mEndToEnd = object1;
        this.mLast = Constraint.CENTER_HORIZONTALLY;
        this.mHorizontalBias = 0.5f;
        return this;
    }

    public ConstraintReference centerVertically(Object object0) {
        Object object1 = this.get(object0);
        this.mTopToTop = object1;
        this.mBottomToBottom = object1;
        this.mLast = Constraint.CENTER_VERTICALLY;
        this.mVerticalBias = 0.5f;
        return this;
    }

    public ConstraintReference circularConstraint(Object object0, float f, float f1) {
        this.mCircularConstraint = this.get(object0);
        this.mCircularAngle = f;
        this.mCircularDistance = f1;
        this.mLast = Constraint.CIRCULAR_CONSTRAINT;
        return this;
    }

    public ConstraintReference clear() {
        if(this.mLast != null) {
            switch(androidx.constraintlayout.core.state.ConstraintReference.1.$SwitchMap$androidx$constraintlayout$core$state$State$Constraint[this.mLast.ordinal()]) {
                case 1: 
                case 2: {
                    this.mLeftToLeft = null;
                    this.mLeftToRight = null;
                    this.mMarginLeft = 0;
                    this.mMarginLeftGone = 0;
                    return this;
                }
                case 3: 
                case 4: {
                    this.mRightToLeft = null;
                    this.mRightToRight = null;
                    this.mMarginRight = 0;
                    this.mMarginRightGone = 0;
                    return this;
                }
                case 5: 
                case 6: {
                    this.mStartToStart = null;
                    this.mStartToEnd = null;
                    this.mMarginStart = 0;
                    this.mMarginStartGone = 0;
                    return this;
                }
                case 7: 
                case 8: {
                    this.mEndToStart = null;
                    this.mEndToEnd = null;
                    this.mMarginEnd = 0;
                    this.mMarginEndGone = 0;
                    return this;
                }
                case 9: 
                case 10: 
                case 11: {
                    this.mTopToTop = null;
                    this.mTopToBottom = null;
                    this.mTopToBaseline = null;
                    this.mMarginTop = 0;
                    this.mMarginTopGone = 0;
                    return this;
                }
                case 12: 
                case 13: 
                case 14: {
                    this.mBottomToTop = null;
                    this.mBottomToBottom = null;
                    this.mBottomToBaseline = null;
                    this.mMarginBottom = 0;
                    this.mMarginBottomGone = 0;
                    return this;
                }
                case 17: {
                    this.mBaselineToBaseline = null;
                    return this;
                }
                case 18: {
                    this.mCircularConstraint = null;
                    return this;
                }
                default: {
                    return this;
                }
            }
        }
        this.clearAll();
        return this;
    }

    public ConstraintReference clearAll() {
        this.mLeftToLeft = null;
        this.mLeftToRight = null;
        this.mMarginLeft = 0;
        this.mRightToLeft = null;
        this.mRightToRight = null;
        this.mMarginRight = 0;
        this.mStartToStart = null;
        this.mStartToEnd = null;
        this.mMarginStart = 0;
        this.mEndToStart = null;
        this.mEndToEnd = null;
        this.mMarginEnd = 0;
        this.mTopToTop = null;
        this.mTopToBottom = null;
        this.mMarginTop = 0;
        this.mBottomToTop = null;
        this.mBottomToBottom = null;
        this.mMarginBottom = 0;
        this.mBaselineToBaseline = null;
        this.mCircularConstraint = null;
        this.mHorizontalBias = 0.5f;
        this.mVerticalBias = 0.5f;
        this.mMarginLeftGone = 0;
        this.mMarginRightGone = 0;
        this.mMarginStartGone = 0;
        this.mMarginEndGone = 0;
        this.mMarginTopGone = 0;
        this.mMarginBottomGone = 0;
        return this;
    }

    public ConstraintReference clearHorizontal() {
        this.start().clear();
        this.end().clear();
        this.left().clear();
        this.right().clear();
        return this;
    }

    public ConstraintReference clearVertical() {
        this.top().clear();
        this.baseline().clear();
        this.bottom().clear();
        return this;
    }

    public ConstraintWidget createConstraintWidget() {
        return new ConstraintWidget(this.getWidth().getValue(), this.getHeight().getValue());
    }

    private void dereference() {
        this.mLeftToLeft = this.get(this.mLeftToLeft);
        this.mLeftToRight = this.get(this.mLeftToRight);
        this.mRightToLeft = this.get(this.mRightToLeft);
        this.mRightToRight = this.get(this.mRightToRight);
        this.mStartToStart = this.get(this.mStartToStart);
        this.mStartToEnd = this.get(this.mStartToEnd);
        this.mEndToStart = this.get(this.mEndToStart);
        this.mEndToEnd = this.get(this.mEndToEnd);
        this.mTopToTop = this.get(this.mTopToTop);
        this.mTopToBottom = this.get(this.mTopToBottom);
        this.mBottomToTop = this.get(this.mBottomToTop);
        this.mBottomToBottom = this.get(this.mBottomToBottom);
        this.mBaselineToBaseline = this.get(this.mBaselineToBaseline);
        this.mBaselineToTop = this.get(this.mBaselineToTop);
        this.mBaselineToBottom = this.get(this.mBaselineToBottom);
    }

    public ConstraintReference end() {
        if(this.mEndToStart != null) {
            this.mLast = Constraint.END_TO_START;
            return this;
        }
        this.mLast = Constraint.END_TO_END;
        return this;
    }

    public ConstraintReference endToEnd(Object object0) {
        this.mLast = Constraint.END_TO_END;
        this.mEndToEnd = object0;
        return this;
    }

    public ConstraintReference endToStart(Object object0) {
        this.mLast = Constraint.END_TO_START;
        this.mEndToStart = object0;
        return this;
    }

    private Object get(Object object0) {
        if(object0 == null) {
            return null;
        }
        return !(object0 instanceof ConstraintReference) ? this.mState.reference(object0) : object0;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    @Override  // androidx.constraintlayout.core.state.Reference
    public ConstraintWidget getConstraintWidget() {
        if(this.mConstraintWidget == null) {
            ConstraintWidget constraintWidget0 = this.createConstraintWidget();
            this.mConstraintWidget = constraintWidget0;
            constraintWidget0.setCompanionWidget(this.mView);
        }
        return this.mConstraintWidget;
    }

    @Override  // androidx.constraintlayout.core.state.Reference
    public Facade getFacade() {
        return this.mFacade;
    }

    public Dimension getHeight() {
        return this.mVerticalDimension;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public float getHorizontalChainWeight() {
        return this.mHorizontalChainWeight;
    }

    @Override  // androidx.constraintlayout.core.state.Reference
    public Object getKey() {
        return this.mKey;
    }

    public float getPivotX() {
        return this.mPivotX;
    }

    public float getPivotY() {
        return this.mPivotY;
    }

    public float getRotationX() {
        return this.mRotationX;
    }

    public float getRotationY() {
        return this.mRotationY;
    }

    public float getRotationZ() {
        return this.mRotationZ;
    }

    public float getScaleX() {
        return this.mScaleX;
    }

    public float getScaleY() {
        return this.mScaleY;
    }

    public String getTag() {
        return this.mTag;
    }

    // 去混淆评级： 低(20)
    private ConstraintWidget getTarget(Object object0) {
        return object0 instanceof Reference ? ((Reference)object0).getConstraintWidget() : null;
    }

    public float getTranslationX() {
        return this.mTranslationX;
    }

    public float getTranslationY() {
        return this.mTranslationY;
    }

    public float getTranslationZ() {
        return this.mTranslationZ;
    }

    public int getVerticalChainStyle(int v) {
        return this.mVerticalChainStyle;
    }

    public float getVerticalChainWeight() {
        return this.mVerticalChainWeight;
    }

    public Object getView() {
        return this.mView;
    }

    public Dimension getWidth() {
        return this.mHorizontalDimension;
    }

    public ConstraintReference height(Dimension dimension0) {
        return this.setHeight(dimension0);
    }

    public ConstraintReference horizontalBias(float f) {
        this.mHorizontalBias = f;
        return this;
    }

    public ConstraintReference left() {
        if(this.mLeftToLeft != null) {
            this.mLast = Constraint.LEFT_TO_LEFT;
            return this;
        }
        this.mLast = Constraint.LEFT_TO_RIGHT;
        return this;
    }

    public ConstraintReference leftToLeft(Object object0) {
        this.mLast = Constraint.LEFT_TO_LEFT;
        this.mLeftToLeft = object0;
        return this;
    }

    public ConstraintReference leftToRight(Object object0) {
        this.mLast = Constraint.LEFT_TO_RIGHT;
        this.mLeftToRight = object0;
        return this;
    }

    public ConstraintReference margin(int v) {
        if(this.mLast != null) {
            switch(this.mLast) {
                case 1: 
                case 2: {
                    this.mMarginLeft = v;
                    return this;
                }
                case 3: 
                case 4: {
                    this.mMarginRight = v;
                    return this;
                }
                case 5: 
                case 6: {
                    this.mMarginStart = v;
                    return this;
                }
                case 7: 
                case 8: {
                    this.mMarginEnd = v;
                    return this;
                }
                case 9: 
                case 10: 
                case 11: {
                    this.mMarginTop = v;
                    return this;
                }
                case 12: 
                case 13: 
                case 14: {
                    this.mMarginBottom = v;
                    return this;
                }
                case 15: 
                case 16: 
                case 17: {
                    this.mMarginBaseline = v;
                    return this;
                }
                case 18: {
                    this.mCircularDistance = (float)v;
                    return this;
                }
                default: {
                    return this;
                }
            }
        }
        this.mMarginLeft = v;
        this.mMarginRight = v;
        this.mMarginStart = v;
        this.mMarginEnd = v;
        this.mMarginTop = v;
        this.mMarginBottom = v;
        return this;
    }

    public ConstraintReference margin(Object object0) {
        return this.margin(this.mState.convertDimension(object0));
    }

    public ConstraintReference marginGone(int v) {
        if(this.mLast != null) {
            switch(this.mLast) {
                case 1: 
                case 2: {
                    this.mMarginLeftGone = v;
                    return this;
                }
                case 3: 
                case 4: {
                    this.mMarginRightGone = v;
                    return this;
                }
                case 5: 
                case 6: {
                    this.mMarginStartGone = v;
                    return this;
                }
                case 7: 
                case 8: {
                    this.mMarginEndGone = v;
                    return this;
                }
                case 9: 
                case 10: 
                case 11: {
                    this.mMarginTopGone = v;
                    return this;
                }
                case 12: 
                case 13: 
                case 14: {
                    this.mMarginBottomGone = v;
                    return this;
                }
                case 15: 
                case 16: 
                case 17: {
                    this.mMarginBaselineGone = v;
                    return this;
                }
                default: {
                    return this;
                }
            }
        }
        this.mMarginLeftGone = v;
        this.mMarginRightGone = v;
        this.mMarginStartGone = v;
        this.mMarginEndGone = v;
        this.mMarginTopGone = v;
        this.mMarginBottomGone = v;
        return this;
    }

    public ConstraintReference marginGone(Object object0) {
        return this.marginGone(this.mState.convertDimension(object0));
    }

    public ConstraintReference pivotX(float f) {
        this.mPivotX = f;
        return this;
    }

    public ConstraintReference pivotY(float f) {
        this.mPivotY = f;
        return this;
    }

    public ConstraintReference right() {
        if(this.mRightToLeft != null) {
            this.mLast = Constraint.RIGHT_TO_LEFT;
            return this;
        }
        this.mLast = Constraint.RIGHT_TO_RIGHT;
        return this;
    }

    public ConstraintReference rightToLeft(Object object0) {
        this.mLast = Constraint.RIGHT_TO_LEFT;
        this.mRightToLeft = object0;
        return this;
    }

    public ConstraintReference rightToRight(Object object0) {
        this.mLast = Constraint.RIGHT_TO_RIGHT;
        this.mRightToRight = object0;
        return this;
    }

    public ConstraintReference rotationX(float f) {
        this.mRotationX = f;
        return this;
    }

    public ConstraintReference rotationY(float f) {
        this.mRotationY = f;
        return this;
    }

    public ConstraintReference rotationZ(float f) {
        this.mRotationZ = f;
        return this;
    }

    public ConstraintReference scaleX(float f) {
        this.mScaleX = f;
        return this;
    }

    public ConstraintReference scaleY(float f) {
        this.mScaleY = f;
        return this;
    }

    @Override  // androidx.constraintlayout.core.state.Reference
    public void setConstraintWidget(ConstraintWidget constraintWidget0) {
        if(constraintWidget0 == null) {
            return;
        }
        this.mConstraintWidget = constraintWidget0;
        constraintWidget0.setCompanionWidget(this.mView);
    }

    public void setFacade(Facade facade0) {
        this.mFacade = facade0;
        if(facade0 != null) {
            this.setConstraintWidget(facade0.getConstraintWidget());
        }
    }

    public ConstraintReference setHeight(Dimension dimension0) {
        this.mVerticalDimension = dimension0;
        return this;
    }

    public void setHorizontalChainStyle(int v) {
        this.mHorizontalChainStyle = v;
    }

    public void setHorizontalChainWeight(float f) {
        this.mHorizontalChainWeight = f;
    }

    @Override  // androidx.constraintlayout.core.state.Reference
    public void setKey(Object object0) {
        this.mKey = object0;
    }

    public void setTag(String s) {
        this.mTag = s;
    }

    public void setVerticalChainStyle(int v) {
        this.mVerticalChainStyle = v;
    }

    public void setVerticalChainWeight(float f) {
        this.mVerticalChainWeight = f;
    }

    public void setView(Object object0) {
        this.mView = object0;
        ConstraintWidget constraintWidget0 = this.mConstraintWidget;
        if(constraintWidget0 != null) {
            constraintWidget0.setCompanionWidget(object0);
        }
    }

    public ConstraintReference setWidth(Dimension dimension0) {
        this.mHorizontalDimension = dimension0;
        return this;
    }

    public ConstraintReference start() {
        if(this.mStartToStart != null) {
            this.mLast = Constraint.START_TO_START;
            return this;
        }
        this.mLast = Constraint.START_TO_END;
        return this;
    }

    public ConstraintReference startToEnd(Object object0) {
        this.mLast = Constraint.START_TO_END;
        this.mStartToEnd = object0;
        return this;
    }

    public ConstraintReference startToStart(Object object0) {
        this.mLast = Constraint.START_TO_START;
        this.mStartToStart = object0;
        return this;
    }

    public ConstraintReference top() {
        if(this.mTopToTop != null) {
            this.mLast = Constraint.TOP_TO_TOP;
            return this;
        }
        this.mLast = Constraint.TOP_TO_BOTTOM;
        return this;
    }

    ConstraintReference topToBaseline(Object object0) {
        this.mLast = Constraint.TOP_TO_BASELINE;
        this.mTopToBaseline = object0;
        return this;
    }

    public ConstraintReference topToBottom(Object object0) {
        this.mLast = Constraint.TOP_TO_BOTTOM;
        this.mTopToBottom = object0;
        return this;
    }

    public ConstraintReference topToTop(Object object0) {
        this.mLast = Constraint.TOP_TO_TOP;
        this.mTopToTop = object0;
        return this;
    }

    public ConstraintReference translationX(float f) {
        this.mTranslationX = f;
        return this;
    }

    public ConstraintReference translationY(float f) {
        this.mTranslationY = f;
        return this;
    }

    public ConstraintReference translationZ(float f) {
        this.mTranslationZ = f;
        return this;
    }

    public void validate() throws IncorrectConstraintException {
        ArrayList arrayList0 = new ArrayList();
        if(this.mLeftToLeft != null && this.mLeftToRight != null) {
            arrayList0.add("LeftToLeft and LeftToRight both defined");
        }
        if(this.mRightToLeft != null && this.mRightToRight != null) {
            arrayList0.add("RightToLeft and RightToRight both defined");
        }
        if(this.mStartToStart != null && this.mStartToEnd != null) {
            arrayList0.add("StartToStart and StartToEnd both defined");
        }
        if(this.mEndToStart != null && this.mEndToEnd != null) {
            arrayList0.add("EndToStart and EndToEnd both defined");
        }
        if((this.mLeftToLeft != null || this.mLeftToRight != null || this.mRightToLeft != null || this.mRightToRight != null) && (this.mStartToStart != null || this.mStartToEnd != null || this.mEndToStart != null || this.mEndToEnd != null)) {
            arrayList0.add("Both left/right and start/end constraints defined");
        }
        if(arrayList0.size() > 0) {
            throw new IncorrectConstraintException(arrayList0);
        }
    }

    public ConstraintReference verticalBias(float f) {
        this.mVerticalBias = f;
        return this;
    }

    public ConstraintReference visibility(int v) {
        this.mVisibility = v;
        return this;
    }

    public ConstraintReference width(Dimension dimension0) {
        return this.setWidth(dimension0);
    }
}

