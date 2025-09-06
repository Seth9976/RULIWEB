package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.analyzer.ChainRun;
import androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ConstraintWidget {
    public static enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT;

        private static DimensionBehaviour[] $values() [...] // Inlined contents
    }

    public static final int ANCHOR_BASELINE = 4;
    public static final int ANCHOR_BOTTOM = 3;
    public static final int ANCHOR_LEFT = 0;
    public static final int ANCHOR_RIGHT = 1;
    public static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int BOTH = 2;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    private static final boolean DO_NOT_USE = false;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    private static final boolean USE_WRAP_DIMENSION_FOR_SPREAD = false;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    public static final int WRAP_BEHAVIOR_HORIZONTAL_ONLY = 1;
    public static final int WRAP_BEHAVIOR_INCLUDED = 0;
    public static final int WRAP_BEHAVIOR_SKIPPED = 3;
    public static final int WRAP_BEHAVIOR_VERTICAL_ONLY = 2;
    public WidgetFrame frame;
    public ChainRun horizontalChainRun;
    public int horizontalGroup;
    public boolean[] isTerminalWidget;
    protected ArrayList mAnchors;
    private boolean mAnimated;
    public ConstraintAnchor mBaseline;
    int mBaselineDistance;
    public ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    public ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    public float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    public float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    boolean mGroupsToSolver;
    private boolean mHasBaseline;
    int mHeight;
    private int mHeightOverride;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    public HorizontalWidgetRun mHorizontalRun;
    private boolean mHorizontalSolvingPass;
    boolean mHorizontalWrapVisited;
    private boolean mInPlaceholder;
    private boolean mInVirtualLayout;
    public boolean mIsHeightWrapContent;
    private boolean[] mIsInBarrier;
    public boolean mIsWidthWrapContent;
    private int mLastHorizontalMeasureSpec;
    private int mLastVerticalMeasureSpec;
    public ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    public int mMatchConstraintDefaultHeight;
    public int mMatchConstraintDefaultWidth;
    public int mMatchConstraintMaxHeight;
    public int mMatchConstraintMaxWidth;
    public int mMatchConstraintMinHeight;
    public int mMatchConstraintMinWidth;
    public float mMatchConstraintPercentHeight;
    public float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    private boolean mMeasureRequested;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    private boolean mOptimizeWrapO;
    private boolean mOptimizeWrapOnResolved;
    public ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    boolean mResolvedHasRatio;
    private boolean mResolvedHorizontal;
    public int[] mResolvedMatchConstraintDefault;
    private boolean mResolvedVertical;
    public ConstraintAnchor mRight;
    boolean mRightHasCentered;
    public ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    public VerticalWidgetRun mVerticalRun;
    private boolean mVerticalSolvingPass;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    public float[] mWeight;
    int mWidth;
    private int mWidthOverride;
    private int mWrapBehaviorInParent;
    protected int mX;
    protected int mY;
    public boolean measured;
    public WidgetRun[] run;
    public String stringId;
    public ChainRun verticalChainRun;
    public int verticalGroup;

    static {
    }

    public ConstraintWidget() {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.mHorizontalRun = null;
        this.mVerticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.mOptimizeWrapO = false;
        this.mOptimizeWrapOnResolved = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{0x7FFFFFFF, 0x7FFFFFFF};
        this.mCircleConstraintAngle = NaNf;
        this.mHasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, Type.LEFT);
        this.mTop = new ConstraintAnchor(this, Type.TOP);
        this.mRight = new ConstraintAnchor(this, Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
        ConstraintAnchor constraintAnchor0 = new ConstraintAnchor(this, Type.CENTER);
        this.mCenter = constraintAnchor0;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor0};
        this.mAnchors = new ArrayList();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = ConstraintWidget.DEFAULT_BIAS;
        this.mVerticalBiasPercent = ConstraintWidget.DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        this.addAnchors();
    }

    public ConstraintWidget(int v, int v1) {
        this(0, 0, v, v1);
    }

    public ConstraintWidget(int v, int v1, int v2, int v3) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.mHorizontalRun = null;
        this.mVerticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.mOptimizeWrapO = false;
        this.mOptimizeWrapOnResolved = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{0x7FFFFFFF, 0x7FFFFFFF};
        this.mCircleConstraintAngle = NaNf;
        this.mHasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, Type.LEFT);
        this.mTop = new ConstraintAnchor(this, Type.TOP);
        this.mRight = new ConstraintAnchor(this, Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
        ConstraintAnchor constraintAnchor0 = new ConstraintAnchor(this, Type.CENTER);
        this.mCenter = constraintAnchor0;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor0};
        this.mAnchors = new ArrayList();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = ConstraintWidget.DEFAULT_BIAS;
        this.mVerticalBiasPercent = ConstraintWidget.DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        this.mX = v;
        this.mY = v1;
        this.mWidth = v2;
        this.mHeight = v3;
        this.addAnchors();
    }

    public ConstraintWidget(String s) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.mHorizontalRun = null;
        this.mVerticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.mOptimizeWrapO = false;
        this.mOptimizeWrapOnResolved = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{0x7FFFFFFF, 0x7FFFFFFF};
        this.mCircleConstraintAngle = NaNf;
        this.mHasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, Type.LEFT);
        this.mTop = new ConstraintAnchor(this, Type.TOP);
        this.mRight = new ConstraintAnchor(this, Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
        ConstraintAnchor constraintAnchor0 = new ConstraintAnchor(this, Type.CENTER);
        this.mCenter = constraintAnchor0;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor0};
        this.mAnchors = new ArrayList();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = ConstraintWidget.DEFAULT_BIAS;
        this.mVerticalBiasPercent = ConstraintWidget.DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        this.addAnchors();
        this.setDebugName(s);
    }

    public ConstraintWidget(String s, int v, int v1) {
        this(v, v1);
        this.setDebugName(s);
    }

    public ConstraintWidget(String s, int v, int v1, int v2, int v3) {
        this(v, v1, v2, v3);
        this.setDebugName(s);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public void addChildrenToSolverByDependency(ConstraintWidgetContainer constraintWidgetContainer0, LinearSystem linearSystem0, HashSet hashSet0, int v, boolean z) {
        if(!z) {
        label_5:
            if(v == 0) {
                HashSet hashSet1 = this.mLeft.getDependents();
                if(hashSet1 != null) {
                    for(Object object0: hashSet1) {
                        ((ConstraintAnchor)object0).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer0, linearSystem0, hashSet0, 0, true);
                    }
                }
                HashSet hashSet2 = this.mRight.getDependents();
                if(hashSet2 != null) {
                    for(Object object1: hashSet2) {
                        ((ConstraintAnchor)object1).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer0, linearSystem0, hashSet0, 0, true);
                    }
                }
            }
            else {
                HashSet hashSet3 = this.mTop.getDependents();
                if(hashSet3 != null) {
                    for(Object object2: hashSet3) {
                        ((ConstraintAnchor)object2).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer0, linearSystem0, hashSet0, v, true);
                    }
                }
                HashSet hashSet4 = this.mBottom.getDependents();
                if(hashSet4 != null) {
                    for(Object object3: hashSet4) {
                        ((ConstraintAnchor)object3).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer0, linearSystem0, hashSet0, v, true);
                    }
                }
                HashSet hashSet5 = this.mBaseline.getDependents();
                if(hashSet5 != null) {
                    for(Object object4: hashSet5) {
                        ((ConstraintAnchor)object4).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer0, linearSystem0, hashSet0, v, true);
                    }
                }
            }
        }
        else if(hashSet0.contains(this)) {
            Optimizer.checkMatchParent(constraintWidgetContainer0, linearSystem0, this);
            hashSet0.remove(this);
            this.addToSolver(linearSystem0, constraintWidgetContainer0.optimizeFor(0x40));
            goto label_5;
        }
    }

    // 去混淆评级： 低(20)
    boolean addFirst() [...] // 潜在的解密器

    public void addToSolver(LinearSystem linearSystem0, boolean z) {
        int v11;
        boolean z20;
        int v10;
        boolean z19;
        int v9;
        boolean z18;
        boolean z17;
        boolean z14;
        boolean z13;
        boolean z12;
        boolean z9;
        int v7;
        int v6;
        boolean z6;
        boolean z5;
        boolean z4;
        boolean z2;
        boolean z1;
        SolverVariable solverVariable0 = linearSystem0.createObjectVariable(this.mLeft);
        SolverVariable solverVariable1 = linearSystem0.createObjectVariable(this.mRight);
        SolverVariable solverVariable2 = linearSystem0.createObjectVariable(this.mTop);
        SolverVariable solverVariable3 = linearSystem0.createObjectVariable(this.mBottom);
        SolverVariable solverVariable4 = linearSystem0.createObjectVariable(this.mBaseline);
        ConstraintWidget constraintWidget0 = this.mParent;
        if(constraintWidget0 == null) {
            z1 = false;
        label_16:
            z2 = false;
        }
        else {
            z1 = constraintWidget0.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT;
            z2 = this.mParent != null && this.mParent.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT;
            switch(this.mWrapBehaviorInParent) {
                case 1: {
                    goto label_16;
                }
                case 2: {
                    z1 = false;
                    break;
                }
                case 3: {
                    z1 = false;
                    goto label_16;
                }
            }
        }
        if(this.mVisibility != 8 || this.mAnimated || this.hasDependencies() || (this.mIsInBarrier[0] || this.mIsInBarrier[1])) {
            boolean z3 = this.mResolvedHorizontal;
            if(z3 || this.mResolvedVertical) {
                if(z3) {
                    linearSystem0.addEquality(solverVariable0, this.mX);
                    linearSystem0.addEquality(solverVariable1, this.mX + this.mWidth);
                    if(z1) {
                        ConstraintWidget constraintWidget1 = this.mParent;
                        if(constraintWidget1 != null) {
                            if(this.mOptimizeWrapOnResolved) {
                                ((ConstraintWidgetContainer)constraintWidget1).addHorizontalWrapMinVariable(this.mLeft);
                                ((ConstraintWidgetContainer)constraintWidget1).addHorizontalWrapMaxVariable(this.mRight);
                            }
                            else {
                                linearSystem0.addGreaterThan(linearSystem0.createObjectVariable(constraintWidget1.mRight), solverVariable1, 0, 5);
                            }
                        }
                    }
                }
                if(this.mResolvedVertical) {
                    linearSystem0.addEquality(solverVariable2, this.mY);
                    linearSystem0.addEquality(solverVariable3, this.mY + this.mHeight);
                    if(this.mBaseline.hasDependents()) {
                        linearSystem0.addEquality(solverVariable4, this.mY + this.mBaselineDistance);
                    }
                    if(z2) {
                        ConstraintWidget constraintWidget2 = this.mParent;
                        if(constraintWidget2 != null) {
                            if(this.mOptimizeWrapOnResolved) {
                                ((ConstraintWidgetContainer)constraintWidget2).addVerticalWrapMinVariable(this.mTop);
                                ((ConstraintWidgetContainer)constraintWidget2).addVerticalWrapMaxVariable(this.mBottom);
                            }
                            else {
                                linearSystem0.addGreaterThan(linearSystem0.createObjectVariable(constraintWidget2.mBottom), solverVariable3, 0, 5);
                            }
                        }
                    }
                }
                if(this.mResolvedHorizontal && this.mResolvedVertical) {
                    this.mResolvedHorizontal = false;
                    this.mResolvedVertical = false;
                    return;
                }
            }
            if(LinearSystem.sMetrics != null) {
                ++LinearSystem.sMetrics.widgets;
            }
            if(z && (this.mHorizontalRun != null && this.mVerticalRun != null && this.mHorizontalRun.start.resolved && this.mHorizontalRun.end.resolved && this.mVerticalRun.start.resolved && this.mVerticalRun.end.resolved)) {
                if(LinearSystem.sMetrics != null) {
                    ++LinearSystem.sMetrics.graphSolved;
                }
                linearSystem0.addEquality(solverVariable0, this.mHorizontalRun.start.value);
                linearSystem0.addEquality(solverVariable1, this.mHorizontalRun.end.value);
                linearSystem0.addEquality(solverVariable2, this.mVerticalRun.start.value);
                linearSystem0.addEquality(solverVariable3, this.mVerticalRun.end.value);
                linearSystem0.addEquality(solverVariable4, this.mVerticalRun.baseline.value);
                if(this.mParent != null) {
                    if(z1 && this.isTerminalWidget[0] && !this.isInHorizontalChain()) {
                        linearSystem0.addGreaterThan(linearSystem0.createObjectVariable(this.mParent.mRight), solverVariable1, 0, 8);
                    }
                    if(z2 && this.isTerminalWidget[1] && !this.isInVerticalChain()) {
                        linearSystem0.addGreaterThan(linearSystem0.createObjectVariable(this.mParent.mBottom), solverVariable3, 0, 8);
                    }
                }
                this.mResolvedHorizontal = false;
                this.mResolvedVertical = false;
                return;
            }
            if(LinearSystem.sMetrics != null) {
                ++LinearSystem.sMetrics.linearSolved;
            }
            if(this.mParent == null) {
                z5 = false;
                z6 = false;
            }
            else {
                if(this.isChainHead(0)) {
                    ((ConstraintWidgetContainer)this.mParent).addChain(this, 0);
                    z4 = true;
                }
                else {
                    z4 = this.isInHorizontalChain();
                }
                if(this.isChainHead(1)) {
                    ((ConstraintWidgetContainer)this.mParent).addChain(this, 1);
                    z5 = true;
                }
                else {
                    z5 = this.isInVerticalChain();
                }
                if(!z4 && z1 && this.mVisibility != 8 && this.mLeft.mTarget == null && this.mRight.mTarget == null) {
                    linearSystem0.addGreaterThan(linearSystem0.createObjectVariable(this.mParent.mRight), solverVariable1, 0, 1);
                }
                if(!z5 && z2 && this.mVisibility != 8 && this.mTop.mTarget == null && this.mBottom.mTarget == null && this.mBaseline == null) {
                    linearSystem0.addGreaterThan(linearSystem0.createObjectVariable(this.mParent.mBottom), solverVariable3, 0, 1);
                }
                z6 = z4;
            }
            int v = this.mWidth;
            int v1 = this.mMinWidth;
            if(v < v1) {
                v = v1;
            }
            int v2 = this.mHeight;
            int v3 = this.mMinHeight;
            if(v2 < v3) {
                v2 = v3;
            }
            boolean z7 = this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT;
            boolean z8 = this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT;
            this.mResolvedDimensionRatioSide = this.mDimensionRatioSide;
            this.mResolvedDimensionRatio = this.mDimensionRatio;
            int v4 = this.mMatchConstraintDefaultWidth;
            int v5 = this.mMatchConstraintDefaultHeight;
            if(this.mDimensionRatio <= 0.0f || this.mVisibility == 8) {
                v7 = v2;
                v6 = v5;
                z9 = false;
            }
            else {
                if(this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && v4 == 0) {
                    v4 = 3;
                }
                v6 = this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT || v5 != 0 ? v5 : 3;
                if(this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && v4 == 3 && v6 == 3) {
                    this.setupDimensionRatio(z1, z2, z7, z8);
                    v7 = v2;
                    z9 = true;
                }
                else if(this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && v4 == 3) {
                    this.mResolvedDimensionRatioSide = 0;
                    v = (int)(this.mResolvedDimensionRatio * ((float)this.mHeight));
                    if(this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
                        v7 = v2;
                        z9 = true;
                    }
                    else {
                        v7 = v2;
                        z9 = false;
                        v4 = 4;
                    }
                }
                else if(this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT || v6 != 3) {
                    v7 = v2;
                    z9 = true;
                }
                else {
                    this.mResolvedDimensionRatioSide = 1;
                    if(this.mDimensionRatioSide == -1) {
                        this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                    }
                    v2 = (int)(this.mResolvedDimensionRatio * ((float)this.mWidth));
                    if(this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
                        v7 = v2;
                        z9 = true;
                    }
                    else {
                        v7 = v2;
                        v6 = 4;
                        z9 = false;
                    }
                }
            }
            int[] arr_v = this.mResolvedMatchConstraintDefault;
            arr_v[0] = v4;
            arr_v[1] = v6;
            this.mResolvedHasRatio = z9;
            boolean z10 = z9 && (this.mResolvedDimensionRatioSide == -1 || this.mResolvedDimensionRatioSide == 0);
            boolean z11 = z9 && (this.mResolvedDimensionRatioSide == -1 || this.mResolvedDimensionRatioSide == 1);
            if(this.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT || !(this instanceof ConstraintWidgetContainer)) {
                z12 = z6;
                z13 = z5;
                z14 = false;
            }
            else {
                z12 = z6;
                z13 = z5;
                z14 = true;
            }
            int v8 = !this.mCenter.isConnected();
            boolean[] arr_z = this.mIsInBarrier;
            boolean z15 = arr_z[0];
            boolean z16 = arr_z[1];
            SolverVariable solverVariable5 = null;
            if(this.mHorizontalResolution == 2 || this.mResolvedHorizontal) {
            label_171:
                z20 = z9;
                z17 = z1;
                z18 = z12;
                v10 = v4;
                v9 = v6;
                z19 = z2;
            }
            else {
                if(!z || (this.mHorizontalRun == null || !this.mHorizontalRun.start.resolved || !this.mHorizontalRun.end.resolved)) {
                    SolverVariable solverVariable6 = this.mParent == null ? null : linearSystem0.createObjectVariable(this.mParent.mRight);
                    SolverVariable solverVariable7 = this.mParent == null ? null : linearSystem0.createObjectVariable(this.mParent.mLeft);
                    z17 = z1;
                    z18 = z12;
                    v9 = v6;
                    z19 = z2;
                    v10 = v4;
                    z20 = z9;
                    this.applyConstraints(linearSystem0, true, z17, z19, this.isTerminalWidget[0], solverVariable7, solverVariable6, this.mListDimensionBehaviors[0], z14, this.mLeft, this.mRight, this.mX, (z14 ? 0 : v), this.mMinWidth, this.mMaxDimension[0], this.mHorizontalBiasPercent, z10, this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT, z18, z13, z15, v10, v9, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth, this.mMatchConstraintPercentWidth, ((boolean)v8));
                    goto label_177;
                }
                else {
                    linearSystem0.addEquality(solverVariable0, this.mHorizontalRun.start.value);
                    linearSystem0.addEquality(solverVariable1, this.mHorizontalRun.end.value);
                    if(this.mParent != null && z1 && this.isTerminalWidget[0] && !this.isInHorizontalChain()) {
                        linearSystem0.addGreaterThan(linearSystem0.createObjectVariable(this.mParent.mRight), solverVariable1, 0, 8);
                    }
                }
                goto label_171;
            }
        label_177:
            if(!z || (this.mVerticalRun == null || !this.mVerticalRun.start.resolved || !this.mVerticalRun.end.resolved)) {
                v11 = 1;
            }
            else {
                linearSystem0.addEquality(solverVariable2, this.mVerticalRun.start.value);
                linearSystem0.addEquality(solverVariable3, this.mVerticalRun.end.value);
                linearSystem0.addEquality(solverVariable4, this.mVerticalRun.baseline.value);
                ConstraintWidget constraintWidget3 = this.mParent;
                if(constraintWidget3 != null && !z13 && z19 && this.isTerminalWidget[1]) {
                    linearSystem0.addGreaterThan(linearSystem0.createObjectVariable(constraintWidget3.mBottom), solverVariable3, 0, 8);
                }
                v11 = 0;
            }
            if((this.mVerticalResolution == 2 ? 0 : v11) != 0 && !this.mResolvedVertical) {
                boolean z21 = this.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT && this instanceof ConstraintWidgetContainer;
                SolverVariable solverVariable8 = this.mParent == null ? null : linearSystem0.createObjectVariable(this.mParent.mBottom);
                ConstraintWidget constraintWidget4 = this.mParent;
                if(constraintWidget4 != null) {
                    solverVariable5 = linearSystem0.createObjectVariable(constraintWidget4.mTop);
                }
                if(this.mBaselineDistance > 0 || this.mVisibility == 8) {
                    if(this.mBaseline.mTarget != null) {
                        linearSystem0.addEquality(solverVariable4, solverVariable2, this.getBaselineDistance(), 8);
                        linearSystem0.addEquality(solverVariable4, linearSystem0.createObjectVariable(this.mBaseline.mTarget), this.mBaseline.getMargin(), 8);
                        if(z19) {
                            linearSystem0.addGreaterThan(solverVariable8, linearSystem0.createObjectVariable(this.mBottom), 0, 5);
                        }
                        v8 = 0;
                    }
                    else if(this.mVisibility == 8) {
                        linearSystem0.addEquality(solverVariable4, solverVariable2, this.mBaseline.getMargin(), 8);
                    }
                    else {
                        linearSystem0.addEquality(solverVariable4, solverVariable2, this.getBaselineDistance(), 8);
                    }
                }
                this.applyConstraints(linearSystem0, false, z19, z17, this.isTerminalWidget[1], solverVariable5, solverVariable8, this.mListDimensionBehaviors[1], z21, this.mTop, this.mBottom, this.mY, (z21 ? 0 : v7), this.mMinHeight, this.mMaxDimension[1], this.mVerticalBiasPercent, z11, this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT, z13, z18, z16, v9, v10, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, ((boolean)v8));
            }
            if(z20) {
                if(this.mResolvedDimensionRatioSide == 1) {
                    linearSystem0.addRatio(solverVariable3, solverVariable2, solverVariable1, solverVariable0, this.mResolvedDimensionRatio, 8);
                }
                else {
                    linearSystem0.addRatio(solverVariable1, solverVariable0, solverVariable3, solverVariable2, this.mResolvedDimensionRatio, 8);
                }
            }
            if(this.mCenter.isConnected()) {
                linearSystem0.addCenterPoint(this, this.mCenter.getTarget().getOwner(), ((float)Math.toRadians(this.mCircleConstraintAngle + 90.0f)), this.mCenter.getMargin());
            }
            this.mResolvedHorizontal = false;
            this.mResolvedVertical = false;
            if(LinearSystem.sMetrics != null) {
                LinearSystem.sMetrics.mEquations = (long)linearSystem0.getNumEquations();
                LinearSystem.sMetrics.mVariables = (long)linearSystem0.getNumVariables();
            }
        }
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    private void applyConstraints(LinearSystem linearSystem0, boolean z, boolean z1, boolean z2, boolean z3, SolverVariable solverVariable0, SolverVariable solverVariable1, DimensionBehaviour constraintWidget$DimensionBehaviour0, boolean z4, ConstraintAnchor constraintAnchor0, ConstraintAnchor constraintAnchor1, int v, int v1, int v2, int v3, float f, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, int v4, int v5, int v6, int v7, float f1, boolean z10) {
        int v50;
        int v38;
        int v37;
        int v36;
        int v35;
        int v34;
        int v33;
        boolean z15;
        int v32;
        int v31;
        int v30;
        int v29;
        int v46;
        int v45;
        int v44;
        int v43;
        ConstraintWidget constraintWidget7;
        ConstraintWidget constraintWidget6;
        ConstraintWidget constraintWidget5;
        boolean z16;
        int v41;
        ConstraintWidget constraintWidget4;
        int v40;
        boolean z14;
        int v27;
        int v26;
        SolverVariable solverVariable7;
        SolverVariable solverVariable6;
        int v23;
        boolean z13;
        int v22;
        int v21;
        int v20;
        int v19;
        int v18;
        int v17;
        int v16;
        int v14;
        int v12;
        SolverVariable solverVariable2 = linearSystem0.createObjectVariable(constraintAnchor0);
        SolverVariable solverVariable3 = linearSystem0.createObjectVariable(constraintAnchor1);
        SolverVariable solverVariable4 = linearSystem0.createObjectVariable(constraintAnchor0.getTarget());
        SolverVariable solverVariable5 = linearSystem0.createObjectVariable(constraintAnchor1.getTarget());
        if(LinearSystem.getMetrics() != null) {
            Metrics metrics0 = LinearSystem.getMetrics();
            ++metrics0.nonresolvedWidgets;
        }
        int v8 = constraintAnchor0.isConnected();
        boolean z11 = constraintAnchor1.isConnected();
        boolean z12 = this.mCenter.isConnected();
        int v9 = z11 ? v8 + 1 : v8;
        if(z12) {
            ++v9;
        }
        int v10 = z5 ? 3 : v4;
        int v11 = constraintWidget$DimensionBehaviour0.ordinal();
        if(v11 == 0 || v11 == 1 || v11 != 2) {
            v12 = 0;
        }
        else if(v10 != 4) {
            v12 = 1;
        }
        else {
            v12 = 0;
        }
        int v13 = this.mWidthOverride;
        if(v13 == -1 || !z) {
            v13 = v1;
            v14 = v12;
        }
        else {
            this.mWidthOverride = -1;
            v14 = 0;
        }
        int v15 = this.mHeightOverride;
        if(v15 == -1 || z) {
            v16 = v14;
        }
        else {
            this.mHeightOverride = -1;
            v13 = v15;
            v16 = 0;
        }
        if(this.mVisibility == 8) {
            v17 = 0;
            v18 = 0;
        }
        else {
            v17 = v16;
            v18 = v13;
        }
        if(!z10) {
            v19 = v17;
        }
        else if(v8 == 0 && !z11 && !z12) {
            linearSystem0.addEquality(solverVariable2, v);
            v19 = v17;
        }
        else if(v8 == 0 || z11) {
            v19 = v17;
        }
        else {
            v19 = v17;
            linearSystem0.addEquality(solverVariable2, solverVariable4, constraintAnchor0.getMargin(), 8);
        }
        if(v19 == 0) {
            if(z4) {
                linearSystem0.addEquality(solverVariable3, solverVariable2, 0, 3);
                if(v2 > 0) {
                    linearSystem0.addGreaterThan(solverVariable3, solverVariable2, v2, 8);
                }
                if(v3 < 0x7FFFFFFF) {
                    linearSystem0.addLowerThan(solverVariable3, solverVariable2, v3, 8);
                }
            }
            else {
                linearSystem0.addEquality(solverVariable3, solverVariable2, v18, 8);
            }
            v20 = v7;
            v21 = v9;
            v22 = 0;
            z13 = z3;
            v23 = v6;
        }
        else if(v9 == 2 || z5 || v10 != 0 && v10 != 1) {
            int v24 = v6 == -2 ? v18 : v6;
            int v25 = v7 == -2 ? v18 : v7;
            if(v18 > 0 && v10 != 1) {
                v18 = 0;
            }
            if(v24 > 0) {
                linearSystem0.addGreaterThan(solverVariable3, solverVariable2, v24, 8);
                v18 = Math.max(v18, v24);
            }
            if(v25 > 0) {
                if(!z1 || v10 != 1) {
                    linearSystem0.addLowerThan(solverVariable3, solverVariable2, v25, 8);
                }
                v18 = Math.min(v18, v25);
            }
            if(v10 == 1) {
                if(z1) {
                    linearSystem0.addEquality(solverVariable3, solverVariable2, v18, 8);
                }
                else {
                    if(!z7) {
                    }
                    linearSystem0.addEquality(solverVariable3, solverVariable2, v18, 5);
                    linearSystem0.addLowerThan(solverVariable3, solverVariable2, v18, 8);
                }
                v22 = v19;
                z13 = z3;
                v23 = v24;
                v20 = v25;
                v21 = v9;
            }
            else {
                if(v10 == 2) {
                    if(constraintAnchor0.getType() == Type.TOP || constraintAnchor0.getType() == Type.BOTTOM) {
                        solverVariable6 = linearSystem0.createObjectVariable(this.mParent.getAnchor(Type.TOP));
                        solverVariable7 = linearSystem0.createObjectVariable(this.mParent.getAnchor(Type.BOTTOM));
                    }
                    else {
                        solverVariable6 = linearSystem0.createObjectVariable(this.mParent.getAnchor(Type.LEFT));
                        solverVariable7 = linearSystem0.createObjectVariable(this.mParent.getAnchor(Type.RIGHT));
                    }
                    v26 = v25;
                    v21 = v9;
                    linearSystem0.addConstraint(linearSystem0.createRow().createRowDimensionRatio(solverVariable3, solverVariable2, solverVariable7, solverVariable6, f1));
                    if(z1) {
                        v19 = 0;
                    }
                    z13 = z3;
                    v22 = v19;
                }
                else {
                    v26 = v25;
                    v21 = v9;
                    v22 = v19;
                    z13 = true;
                }
                v23 = v24;
                v20 = v26;
            }
        }
        else {
            linearSystem0.addEquality(solverVariable3, solverVariable2, (v7 <= 0 ? Math.max(v6, v18) : Math.min(v7, Math.max(v6, v18))), 8);
            v23 = v6;
            v20 = v7;
            v21 = v9;
            v22 = 0;
            z13 = z3;
        }
        if(z10 && !z7) {
            if(v8 == 0 && !z11 && !z12 || (v8 == 0 || !z11)) {
            label_324:
                z14 = z1;
            label_325:
                v27 = 5;
            label_326:
                if(z14 && z13) {
                    int v48 = constraintAnchor1.mTarget == null ? 0 : constraintAnchor1.getMargin();
                    if(solverVariable5 != solverVariable1) {
                        if(this.mOptimizeWrapO && solverVariable3.isFinalValue) {
                            ConstraintWidget constraintWidget8 = this.mParent;
                            if(constraintWidget8 != null) {
                                if(z) {
                                    ((ConstraintWidgetContainer)constraintWidget8).addHorizontalWrapMaxVariable(constraintAnchor1);
                                    return;
                                }
                                ((ConstraintWidgetContainer)constraintWidget8).addVerticalWrapMaxVariable(constraintAnchor1);
                                return;
                            }
                        }
                        linearSystem0.addGreaterThan(solverVariable1, solverVariable3, v48, v27);
                    }
                }
            }
            else if(v8 != 0 && !z11) {
                v27 = !z1 || !(constraintAnchor0.mTarget.mOwner instanceof Barrier) ? 5 : 8;
                z14 = z1;
                goto label_326;
            }
            else if(v8 == 0 && z11) {
                linearSystem0.addEquality(solverVariable3, solverVariable5, -constraintAnchor1.getMargin(), 8);
                if(!z1) {
                }
                else if(this.mOptimizeWrapO && solverVariable2.isFinalValue) {
                    ConstraintWidget constraintWidget0 = this.mParent;
                    if(constraintWidget0 == null) {
                        linearSystem0.addGreaterThan(solverVariable2, solverVariable0, 0, 5);
                    }
                    else if(z) {
                        ((ConstraintWidgetContainer)constraintWidget0).addHorizontalWrapMinVariable(constraintAnchor0);
                    }
                    else {
                        ((ConstraintWidgetContainer)constraintWidget0).addVerticalWrapMinVariable(constraintAnchor0);
                    }
                }
                else {
                    linearSystem0.addGreaterThan(solverVariable2, solverVariable0, 0, 5);
                }
                goto label_324;
            }
            else {
                ConstraintWidget constraintWidget1 = constraintAnchor0.mTarget.mOwner;
                ConstraintWidget constraintWidget2 = constraintAnchor1.mTarget.mOwner;
                ConstraintWidget constraintWidget3 = this.getParent();
                int v28 = 6;
                if(v22 == 0) {
                    if(!solverVariable4.isFinalValue || !solverVariable5.isFinalValue) {
                        v36 = 6;
                        v30 = 4;
                        v35 = 5;
                        v34 = 1;
                        z15 = true;
                        v33 = 0;
                    label_255:
                        if(!z15 || solverVariable4 != solverVariable5 || constraintWidget1 == constraintWidget3) {
                            v40 = 1;
                        }
                        else {
                            z15 = false;
                            v40 = 0;
                        }
                        if(v34 == 0) {
                            constraintWidget5 = constraintWidget1;
                            constraintWidget7 = constraintWidget2;
                            constraintWidget6 = constraintWidget3;
                        }
                        else {
                            if(v22 != 0 || z6 || z8 || solverVariable4 != solverVariable0 || solverVariable5 != solverVariable1) {
                                constraintWidget4 = constraintWidget3;
                                v41 = v36;
                                z16 = z1;
                            }
                            else {
                                constraintWidget4 = constraintWidget3;
                                v41 = 8;
                                z16 = false;
                                v35 = 8;
                                v40 = 0;
                            }
                            constraintWidget5 = constraintWidget1;
                            z1 = z16;
                            constraintWidget6 = constraintWidget4;
                            constraintWidget7 = constraintWidget2;
                            linearSystem0.addCentering(solverVariable2, solverVariable4, constraintAnchor0.getMargin(), f, solverVariable5, solverVariable3, constraintAnchor1.getMargin(), v41);
                        }
                        z14 = z1;
                        if(this.mVisibility != 8 || constraintAnchor1.hasDependents()) {
                            if(z15) {
                                int v42 = !z14 || solverVariable4 == solverVariable5 || v22 != 0 || !(constraintWidget5 instanceof Barrier) && !(constraintWidget7 instanceof Barrier) ? v35 : 6;
                                linearSystem0.addGreaterThan(solverVariable2, solverVariable4, constraintAnchor0.getMargin(), v42);
                                linearSystem0.addLowerThan(solverVariable3, solverVariable5, -constraintAnchor1.getMargin(), v42);
                                v35 = v42;
                            }
                            if(!z14 || !z9 || constraintWidget5 instanceof Barrier || constraintWidget7 instanceof Barrier || constraintWidget7 == constraintWidget6) {
                                v43 = v30;
                                v44 = v35;
                                v45 = v40;
                            }
                            else {
                                v43 = 6;
                                v44 = 6;
                                v45 = 1;
                            }
                            if(v45 != 0) {
                                if(v33 != 0 && (!z8 || z2)) {
                                    if(constraintWidget5 != constraintWidget6 && constraintWidget7 != constraintWidget6) {
                                        v28 = v43;
                                    }
                                    if(constraintWidget5 instanceof Guideline || constraintWidget7 instanceof Guideline) {
                                        v28 = 5;
                                    }
                                    if(constraintWidget5 instanceof Barrier || constraintWidget7 instanceof Barrier) {
                                        v28 = 5;
                                    }
                                    v43 = Math.max((z8 ? 5 : v28), v43);
                                }
                                if(z14) {
                                    v43 = Math.min(v44, v43);
                                    v46 = !z5 || z8 || constraintWidget5 != constraintWidget6 && constraintWidget7 != constraintWidget6 ? v43 : 4;
                                }
                                else {
                                    v46 = v43;
                                }
                                linearSystem0.addEquality(solverVariable2, solverVariable4, constraintAnchor0.getMargin(), v46);
                                linearSystem0.addEquality(solverVariable3, solverVariable5, -constraintAnchor1.getMargin(), v46);
                            }
                            if(z14) {
                                int v47 = solverVariable0 == solverVariable4 ? constraintAnchor0.getMargin() : 0;
                                if(solverVariable4 != solverVariable0) {
                                    linearSystem0.addGreaterThan(solverVariable2, solverVariable0, v47, 5);
                                }
                            }
                            if(z14 && v22 != 0 && v2 == 0 && v23 == 0) {
                                if(v22 == 0 || v10 != 3) {
                                    linearSystem0.addGreaterThan(solverVariable3, solverVariable2, 0, 5);
                                }
                                else {
                                    linearSystem0.addGreaterThan(solverVariable3, solverVariable2, 0, 8);
                                }
                            }
                            goto label_325;
                        }
                    }
                    else {
                        linearSystem0.addCentering(solverVariable2, solverVariable4, constraintAnchor0.getMargin(), f, solverVariable5, solverVariable3, constraintAnchor1.getMargin(), 8);
                        if(z1 && z13) {
                            int v39 = constraintAnchor1.mTarget == null ? 0 : constraintAnchor1.getMargin();
                            if(solverVariable5 != solverVariable1) {
                                linearSystem0.addGreaterThan(solverVariable1, solverVariable3, v39, 5);
                            }
                        }
                    }
                }
                else if(v10 == 0) {
                    if(v20 != 0 || v23 != 0) {
                        v29 = 5;
                        v30 = 5;
                        v31 = 1;
                        v32 = 0;
                        z15 = true;
                    }
                    else {
                        if(solverVariable4.isFinalValue && solverVariable5.isFinalValue) {
                            linearSystem0.addEquality(solverVariable2, solverVariable4, constraintAnchor0.getMargin(), 8);
                            linearSystem0.addEquality(solverVariable3, solverVariable5, -constraintAnchor1.getMargin(), 8);
                            return;
                        }
                        v29 = 8;
                        v30 = 8;
                        v31 = 0;
                        v32 = 1;
                        z15 = false;
                    }
                    if(constraintWidget1 instanceof Barrier || constraintWidget2 instanceof Barrier) {
                        v33 = v32;
                        v30 = 4;
                    }
                    else {
                        v33 = v32;
                    }
                    v34 = v31;
                    v35 = v29;
                    v36 = 6;
                    goto label_255;
                }
                else {
                    switch(v10) {
                        case 1: {
                            v36 = 6;
                            v30 = 4;
                            v35 = 8;
                            v34 = 1;
                            z15 = true;
                            v33 = 0;
                            goto label_255;
                        }
                        case 2: {
                            if(constraintWidget1 instanceof Barrier || constraintWidget2 instanceof Barrier) {
                                v36 = 6;
                                v30 = 4;
                            }
                            else {
                                v36 = 6;
                                v30 = 5;
                            }
                            v35 = 5;
                            v34 = 1;
                            z15 = true;
                            v33 = 0;
                            goto label_255;
                        }
                        case 3: {
                            if(this.mResolvedDimensionRatioSide == -1) {
                                if(!z8) {
                                    v36 = 8;
                                }
                                else if(z1) {
                                    v36 = 5;
                                }
                                else {
                                    v36 = 4;
                                }
                                v30 = 5;
                                v35 = 8;
                            }
                            else if(z5) {
                                if(v5 == 1 || v5 == 2) {
                                    v37 = 5;
                                    v38 = 4;
                                }
                                else {
                                    v37 = 8;
                                    v38 = 5;
                                }
                                v35 = v37;
                                v30 = v38;
                                v36 = 6;
                            }
                            else if(v20 > 0) {
                                v36 = 6;
                                v30 = 5;
                                goto label_229;
                            }
                            else if(v20 != 0 || v23 != 0) {
                                v36 = 6;
                                v30 = 4;
                                v35 = 5;
                            }
                            else if(!z8) {
                                v36 = 6;
                                v30 = 8;
                            label_229:
                                v35 = 5;
                            }
                            else {
                                v35 = constraintWidget1 == constraintWidget3 || constraintWidget2 == constraintWidget3 ? 5 : 4;
                                v36 = 6;
                                v30 = 4;
                            }
                            v34 = 1;
                            z15 = true;
                            v33 = 1;
                            goto label_255;
                        }
                        default: {
                            v36 = 6;
                            v30 = 4;
                            v35 = 5;
                            v34 = 0;
                            z15 = false;
                            v33 = 0;
                            goto label_255;
                        }
                    }
                }
            }
        }
        else if(v21 < 2 && z1 && z13) {
            linearSystem0.addGreaterThan(solverVariable2, solverVariable0, 0, 8);
            int v49 = z || this.mBaseline.mTarget == null ? 1 : 0;
            if(z || this.mBaseline.mTarget == null) {
                v50 = v49;
            }
            else {
                ConstraintWidget constraintWidget9 = this.mBaseline.mTarget.mOwner;
                v50 = constraintWidget9.mDimensionRatio == 0.0f || constraintWidget9.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget9.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT ? 0 : 1;
            }
            if(v50 != 0) {
                linearSystem0.addGreaterThan(solverVariable1, solverVariable3, 0, 8);
            }
        }
    }

    public void connect(Type constraintAnchor$Type0, ConstraintWidget constraintWidget0, Type constraintAnchor$Type1) {
        this.connect(constraintAnchor$Type0, constraintWidget0, constraintAnchor$Type1, 0);
    }

    public void connect(Type constraintAnchor$Type0, ConstraintWidget constraintWidget0, Type constraintAnchor$Type1, int v) {
        boolean z1;
        if(constraintAnchor$Type0 != Type.CENTER) {
            if(constraintAnchor$Type0 == Type.CENTER_X && (constraintAnchor$Type1 == Type.LEFT || constraintAnchor$Type1 == Type.RIGHT)) {
                ConstraintAnchor constraintAnchor4 = this.getAnchor(Type.LEFT);
                ConstraintAnchor constraintAnchor5 = constraintWidget0.getAnchor(constraintAnchor$Type1);
                ConstraintAnchor constraintAnchor6 = this.getAnchor(Type.RIGHT);
                constraintAnchor4.connect(constraintAnchor5, 0);
                constraintAnchor6.connect(constraintAnchor5, 0);
                this.getAnchor(Type.CENTER_X).connect(constraintAnchor5, 0);
                return;
            }
            if(constraintAnchor$Type0 == Type.CENTER_Y && (constraintAnchor$Type1 == Type.TOP || constraintAnchor$Type1 == Type.BOTTOM)) {
                ConstraintAnchor constraintAnchor7 = constraintWidget0.getAnchor(constraintAnchor$Type1);
                this.getAnchor(Type.TOP).connect(constraintAnchor7, 0);
                this.getAnchor(Type.BOTTOM).connect(constraintAnchor7, 0);
                this.getAnchor(Type.CENTER_Y).connect(constraintAnchor7, 0);
                return;
            }
            if(constraintAnchor$Type0 == Type.CENTER_X && constraintAnchor$Type1 == Type.CENTER_X) {
                this.getAnchor(Type.LEFT).connect(constraintWidget0.getAnchor(Type.LEFT), 0);
                this.getAnchor(Type.RIGHT).connect(constraintWidget0.getAnchor(Type.RIGHT), 0);
                this.getAnchor(Type.CENTER_X).connect(constraintWidget0.getAnchor(constraintAnchor$Type1), 0);
                return;
            }
            if(constraintAnchor$Type0 == Type.CENTER_Y && constraintAnchor$Type1 == Type.CENTER_Y) {
                this.getAnchor(Type.TOP).connect(constraintWidget0.getAnchor(Type.TOP), 0);
                this.getAnchor(Type.BOTTOM).connect(constraintWidget0.getAnchor(Type.BOTTOM), 0);
                this.getAnchor(Type.CENTER_Y).connect(constraintWidget0.getAnchor(constraintAnchor$Type1), 0);
                return;
            }
            ConstraintAnchor constraintAnchor8 = this.getAnchor(constraintAnchor$Type0);
            ConstraintAnchor constraintAnchor9 = constraintWidget0.getAnchor(constraintAnchor$Type1);
            if(constraintAnchor8.isValidConnection(constraintAnchor9)) {
                if(constraintAnchor$Type0 == Type.BASELINE) {
                    ConstraintAnchor constraintAnchor10 = this.getAnchor(Type.TOP);
                    ConstraintAnchor constraintAnchor11 = this.getAnchor(Type.BOTTOM);
                    if(constraintAnchor10 != null) {
                        constraintAnchor10.reset();
                    }
                    if(constraintAnchor11 != null) {
                        constraintAnchor11.reset();
                    }
                }
                else if(constraintAnchor$Type0 == Type.TOP || constraintAnchor$Type0 == Type.BOTTOM) {
                    ConstraintAnchor constraintAnchor15 = this.getAnchor(Type.BASELINE);
                    if(constraintAnchor15 != null) {
                        constraintAnchor15.reset();
                    }
                    ConstraintAnchor constraintAnchor16 = this.getAnchor(Type.CENTER);
                    if(constraintAnchor16.getTarget() != constraintAnchor9) {
                        constraintAnchor16.reset();
                    }
                    ConstraintAnchor constraintAnchor17 = this.getAnchor(constraintAnchor$Type0).getOpposite();
                    ConstraintAnchor constraintAnchor18 = this.getAnchor(Type.CENTER_Y);
                    if(constraintAnchor18.isConnected()) {
                        constraintAnchor17.reset();
                        constraintAnchor18.reset();
                    }
                }
                else if(constraintAnchor$Type0 == Type.LEFT || constraintAnchor$Type0 == Type.RIGHT) {
                    ConstraintAnchor constraintAnchor12 = this.getAnchor(Type.CENTER);
                    if(constraintAnchor12.getTarget() != constraintAnchor9) {
                        constraintAnchor12.reset();
                    }
                    ConstraintAnchor constraintAnchor13 = this.getAnchor(constraintAnchor$Type0).getOpposite();
                    ConstraintAnchor constraintAnchor14 = this.getAnchor(Type.CENTER_X);
                    if(constraintAnchor14.isConnected()) {
                        constraintAnchor13.reset();
                        constraintAnchor14.reset();
                    }
                }
                constraintAnchor8.connect(constraintAnchor9, v);
            }
        }
        else if(constraintAnchor$Type1 == Type.CENTER) {
            ConstraintAnchor constraintAnchor0 = this.getAnchor(Type.LEFT);
            ConstraintAnchor constraintAnchor1 = this.getAnchor(Type.RIGHT);
            ConstraintAnchor constraintAnchor2 = this.getAnchor(Type.TOP);
            ConstraintAnchor constraintAnchor3 = this.getAnchor(Type.BOTTOM);
            boolean z = true;
            if((constraintAnchor0 == null || !constraintAnchor0.isConnected()) && (constraintAnchor1 == null || !constraintAnchor1.isConnected())) {
                this.connect(Type.LEFT, constraintWidget0, Type.LEFT, 0);
                this.connect(Type.RIGHT, constraintWidget0, Type.RIGHT, 0);
                z1 = true;
            }
            else {
                z1 = false;
            }
            if((constraintAnchor2 == null || !constraintAnchor2.isConnected()) && (constraintAnchor3 == null || !constraintAnchor3.isConnected())) {
                this.connect(Type.TOP, constraintWidget0, Type.TOP, 0);
                this.connect(Type.BOTTOM, constraintWidget0, Type.BOTTOM, 0);
            }
            else {
                z = false;
            }
            if(z1 && z) {
                this.getAnchor(Type.CENTER).connect(constraintWidget0.getAnchor(Type.CENTER), 0);
                return;
            }
            if(z1) {
                this.getAnchor(Type.CENTER_X).connect(constraintWidget0.getAnchor(Type.CENTER_X), 0);
                return;
            }
            if(z) {
                this.getAnchor(Type.CENTER_Y).connect(constraintWidget0.getAnchor(Type.CENTER_Y), 0);
            }
        }
        else if(constraintAnchor$Type1 == Type.LEFT || constraintAnchor$Type1 == Type.RIGHT) {
            this.connect(Type.LEFT, constraintWidget0, constraintAnchor$Type1, 0);
            this.connect(Type.RIGHT, constraintWidget0, constraintAnchor$Type1, 0);
            this.getAnchor(Type.CENTER).connect(constraintWidget0.getAnchor(constraintAnchor$Type1), 0);
        }
        else if(constraintAnchor$Type1 == Type.TOP || constraintAnchor$Type1 == Type.BOTTOM) {
            this.connect(Type.TOP, constraintWidget0, constraintAnchor$Type1, 0);
            this.connect(Type.BOTTOM, constraintWidget0, constraintAnchor$Type1, 0);
            this.getAnchor(Type.CENTER).connect(constraintWidget0.getAnchor(constraintAnchor$Type1), 0);
        }
    }

    public void connect(ConstraintAnchor constraintAnchor0, ConstraintAnchor constraintAnchor1, int v) {
        if(constraintAnchor0.getOwner() == this) {
            this.connect(constraintAnchor0.getType(), constraintAnchor1.getOwner(), constraintAnchor1.getType(), v);
        }
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget0, float f, int v) {
        this.immediateConnect(Type.CENTER, constraintWidget0, Type.CENTER, v, 0);
        this.mCircleConstraintAngle = f;
    }

    public void copy(ConstraintWidget constraintWidget0, HashMap hashMap0) {
        this.mHorizontalResolution = constraintWidget0.mHorizontalResolution;
        this.mVerticalResolution = constraintWidget0.mVerticalResolution;
        this.mMatchConstraintDefaultWidth = constraintWidget0.mMatchConstraintDefaultWidth;
        this.mMatchConstraintDefaultHeight = constraintWidget0.mMatchConstraintDefaultHeight;
        int[] arr_v = this.mResolvedMatchConstraintDefault;
        int[] arr_v1 = constraintWidget0.mResolvedMatchConstraintDefault;
        arr_v[0] = arr_v1[0];
        arr_v[1] = arr_v1[1];
        this.mMatchConstraintMinWidth = constraintWidget0.mMatchConstraintMinWidth;
        this.mMatchConstraintMaxWidth = constraintWidget0.mMatchConstraintMaxWidth;
        this.mMatchConstraintMinHeight = constraintWidget0.mMatchConstraintMinHeight;
        this.mMatchConstraintMaxHeight = constraintWidget0.mMatchConstraintMaxHeight;
        this.mMatchConstraintPercentHeight = constraintWidget0.mMatchConstraintPercentHeight;
        this.mIsWidthWrapContent = constraintWidget0.mIsWidthWrapContent;
        this.mIsHeightWrapContent = constraintWidget0.mIsHeightWrapContent;
        this.mResolvedDimensionRatioSide = constraintWidget0.mResolvedDimensionRatioSide;
        this.mResolvedDimensionRatio = constraintWidget0.mResolvedDimensionRatio;
        this.mMaxDimension = Arrays.copyOf(constraintWidget0.mMaxDimension, constraintWidget0.mMaxDimension.length);
        this.mCircleConstraintAngle = constraintWidget0.mCircleConstraintAngle;
        this.mHasBaseline = constraintWidget0.mHasBaseline;
        this.mInPlaceholder = constraintWidget0.mInPlaceholder;
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mListDimensionBehaviors = (DimensionBehaviour[])Arrays.copyOf(this.mListDimensionBehaviors, 2);
        ConstraintWidget constraintWidget1 = null;
        this.mParent = this.mParent == null ? null : ((ConstraintWidget)hashMap0.get(constraintWidget0.mParent));
        this.mWidth = constraintWidget0.mWidth;
        this.mHeight = constraintWidget0.mHeight;
        this.mDimensionRatio = constraintWidget0.mDimensionRatio;
        this.mDimensionRatioSide = constraintWidget0.mDimensionRatioSide;
        this.mX = constraintWidget0.mX;
        this.mY = constraintWidget0.mY;
        this.mRelX = constraintWidget0.mRelX;
        this.mRelY = constraintWidget0.mRelY;
        this.mOffsetX = constraintWidget0.mOffsetX;
        this.mOffsetY = constraintWidget0.mOffsetY;
        this.mBaselineDistance = constraintWidget0.mBaselineDistance;
        this.mMinWidth = constraintWidget0.mMinWidth;
        this.mMinHeight = constraintWidget0.mMinHeight;
        this.mHorizontalBiasPercent = constraintWidget0.mHorizontalBiasPercent;
        this.mVerticalBiasPercent = constraintWidget0.mVerticalBiasPercent;
        this.mCompanionWidget = constraintWidget0.mCompanionWidget;
        this.mContainerItemSkip = constraintWidget0.mContainerItemSkip;
        this.mVisibility = constraintWidget0.mVisibility;
        this.mAnimated = constraintWidget0.mAnimated;
        this.mDebugName = constraintWidget0.mDebugName;
        this.mType = constraintWidget0.mType;
        this.mDistToTop = constraintWidget0.mDistToTop;
        this.mDistToLeft = constraintWidget0.mDistToLeft;
        this.mDistToRight = constraintWidget0.mDistToRight;
        this.mDistToBottom = constraintWidget0.mDistToBottom;
        this.mLeftHasCentered = constraintWidget0.mLeftHasCentered;
        this.mRightHasCentered = constraintWidget0.mRightHasCentered;
        this.mTopHasCentered = constraintWidget0.mTopHasCentered;
        this.mBottomHasCentered = constraintWidget0.mBottomHasCentered;
        this.mHorizontalWrapVisited = constraintWidget0.mHorizontalWrapVisited;
        this.mVerticalWrapVisited = constraintWidget0.mVerticalWrapVisited;
        this.mHorizontalChainStyle = constraintWidget0.mHorizontalChainStyle;
        this.mVerticalChainStyle = constraintWidget0.mVerticalChainStyle;
        this.mHorizontalChainFixedPosition = constraintWidget0.mHorizontalChainFixedPosition;
        this.mVerticalChainFixedPosition = constraintWidget0.mVerticalChainFixedPosition;
        float[] arr_f = this.mWeight;
        float[] arr_f1 = constraintWidget0.mWeight;
        arr_f[0] = arr_f1[0];
        arr_f[1] = arr_f1[1];
        ConstraintWidget[] arr_constraintWidget = this.mListNextMatchConstraintsWidget;
        ConstraintWidget[] arr_constraintWidget1 = constraintWidget0.mListNextMatchConstraintsWidget;
        arr_constraintWidget[0] = arr_constraintWidget1[0];
        arr_constraintWidget[1] = arr_constraintWidget1[1];
        ConstraintWidget[] arr_constraintWidget2 = this.mNextChainWidget;
        ConstraintWidget[] arr_constraintWidget3 = constraintWidget0.mNextChainWidget;
        arr_constraintWidget2[0] = arr_constraintWidget3[0];
        arr_constraintWidget2[1] = arr_constraintWidget3[1];
        this.mHorizontalNextWidget = constraintWidget0.mHorizontalNextWidget == null ? null : ((ConstraintWidget)hashMap0.get(constraintWidget0.mHorizontalNextWidget));
        ConstraintWidget constraintWidget2 = constraintWidget0.mVerticalNextWidget;
        if(constraintWidget2 != null) {
            constraintWidget1 = (ConstraintWidget)hashMap0.get(constraintWidget2);
        }
        this.mVerticalNextWidget = constraintWidget1;
    }

    public void createObjectVariables(LinearSystem linearSystem0) {
        linearSystem0.createObjectVariable(this.mLeft);
        linearSystem0.createObjectVariable(this.mTop);
        linearSystem0.createObjectVariable(this.mRight);
        linearSystem0.createObjectVariable(this.mBottom);
        if(this.mBaselineDistance > 0) {
            linearSystem0.createObjectVariable(this.mBaseline);
        }
    }

    public void ensureMeasureRequested() {
        this.mMeasureRequested = true;
    }

    public void ensureWidgetRuns() {
        if(this.mHorizontalRun == null) {
            this.mHorizontalRun = new HorizontalWidgetRun(this);
        }
        if(this.mVerticalRun == null) {
            this.mVerticalRun = new VerticalWidgetRun(this);
        }
    }

    public ConstraintAnchor getAnchor(Type constraintAnchor$Type0) {
        switch(constraintAnchor$Type0) {
            case 1: {
                return this.mLeft;
            }
            case 2: {
                return this.mTop;
            }
            case 3: {
                return this.mRight;
            }
            case 4: {
                return this.mBottom;
            }
            case 5: {
                return this.mBaseline;
            }
            case 6: {
                return this.mCenter;
            }
            case 7: {
                return this.mCenterX;
            }
            case 8: {
                return this.mCenterY;
            }
            case 9: {
                return null;
            }
            default: {
                throw new AssertionError(constraintAnchor$Type0.name());
            }
        }
    }

    public ArrayList getAnchors() {
        return this.mAnchors;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public float getBiasPercent(int v) {
        if(v == 0) {
            return this.mHorizontalBiasPercent;
        }
        return v == 1 ? this.mVerticalBiasPercent : -1.0f;
    }

    public int getBottom() {
        return this.getY() + this.mHeight;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public DimensionBehaviour getDimensionBehaviour(int v) {
        if(v == 0) {
            return this.getHorizontalDimensionBehaviour();
        }
        return v == 1 ? this.getVerticalDimensionBehaviour() : null;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public boolean getHasBaseline() {
        return this.mHasBaseline;
    }

    public int getHeight() {
        return this.mVisibility == 8 ? 0 : this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        if(this.isInHorizontalChain()) {
            ConstraintWidget constraintWidget0 = this;
            ConstraintWidget constraintWidget1 = null;
            while(constraintWidget1 == null && constraintWidget0 != null) {
                ConstraintAnchor constraintAnchor0 = constraintWidget0.getAnchor(Type.LEFT);
                ConstraintAnchor constraintAnchor1 = constraintAnchor0 == null ? null : constraintAnchor0.getTarget();
                ConstraintWidget constraintWidget2 = constraintAnchor1 == null ? null : constraintAnchor1.getOwner();
                if(constraintWidget2 == this.getParent()) {
                    return constraintWidget0;
                }
                ConstraintAnchor constraintAnchor2 = constraintWidget2 == null ? null : constraintWidget2.getAnchor(Type.RIGHT).getTarget();
                if(constraintAnchor2 != null && constraintAnchor2.getOwner() != constraintWidget0) {
                    constraintWidget1 = constraintWidget0;
                }
                else {
                    constraintWidget0 = constraintWidget2;
                }
            }
            return constraintWidget1;
        }
        return null;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public int getHorizontalMargin() {
        int v = this.mLeft == null ? 0 : this.mLeft.mMargin;
        return this.mRight == null ? v : v + this.mRight.mMargin;
    }

    public int getLastHorizontalMeasureSpec() {
        return this.mLastHorizontalMeasureSpec;
    }

    public int getLastVerticalMeasureSpec() {
        return this.mLastVerticalMeasureSpec;
    }

    public int getLeft() {
        return this.getX();
    }

    public int getLength(int v) {
        if(v == 0) {
            return this.getWidth();
        }
        return v == 1 ? this.getHeight() : 0;
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public ConstraintWidget getNextChainMember(int v) {
        if(v == 0) {
            if(this.mRight.mTarget != null) {
                return this.mRight.mTarget.mTarget == this.mRight ? this.mRight.mTarget.mOwner : null;
            }
        }
        else if(v == 1 && this.mBottom.mTarget != null) {
            return this.mBottom.mTarget.mTarget == this.mBottom ? this.mBottom.mTarget.mOwner : null;
        }
        return null;
    }

    public int getOptimizerWrapHeight() {
        int v = this.mHeight;
        if(this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if(this.mMatchConstraintDefaultHeight == 1) {
                v = Math.max(this.mMatchConstraintMinHeight, v);
                return this.mMatchConstraintMaxHeight <= 0 || this.mMatchConstraintMaxHeight >= v ? v : this.mMatchConstraintMaxHeight;
            }
            v = this.mMatchConstraintMinHeight;
            if(v > 0) {
                this.mHeight = v;
                return this.mMatchConstraintMaxHeight <= 0 || this.mMatchConstraintMaxHeight >= v ? v : this.mMatchConstraintMaxHeight;
            }
            return this.mMatchConstraintMaxHeight <= 0 || this.mMatchConstraintMaxHeight >= 0 ? 0 : this.mMatchConstraintMaxHeight;
        }
        return v;
    }

    public int getOptimizerWrapWidth() {
        int v = this.mWidth;
        int v1 = 0;
        if(this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if(this.mMatchConstraintDefaultWidth == 1) {
                v1 = Math.max(this.mMatchConstraintMinWidth, v);
                return this.mMatchConstraintMaxWidth <= 0 || this.mMatchConstraintMaxWidth >= v1 ? v1 : this.mMatchConstraintMaxWidth;
            }
            int v2 = this.mMatchConstraintMinWidth;
            if(v2 > 0) {
                this.mWidth = v2;
                v1 = v2;
            }
            return this.mMatchConstraintMaxWidth <= 0 || this.mMatchConstraintMaxWidth >= v1 ? v1 : this.mMatchConstraintMaxWidth;
        }
        return v;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public ConstraintWidget getPreviousChainMember(int v) {
        if(v == 0) {
            if(this.mLeft.mTarget != null) {
                return this.mLeft.mTarget.mTarget == this.mLeft ? this.mLeft.mTarget.mOwner : null;
            }
        }
        else if(v == 1 && this.mTop.mTarget != null) {
            return this.mTop.mTarget.mTarget == this.mTop ? this.mTop.mTarget.mOwner : null;
        }
        return null;
    }

    int getRelativePositioning(int v) {
        if(v == 0) {
            return this.mRelX;
        }
        return v == 1 ? this.mRelY : 0;
    }

    public int getRight() {
        return this.getX() + this.mWidth;
    }

    protected int getRootX() {
        return this.mX + this.mOffsetX;
    }

    protected int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public WidgetRun getRun(int v) {
        if(v == 0) {
            return this.mHorizontalRun;
        }
        return v == 1 ? this.mVerticalRun : null;
    }

    private void getSceneString(StringBuilder stringBuilder0, String s, int v, int v1, int v2, int v3, int v4, int v5, float f, DimensionBehaviour constraintWidget$DimensionBehaviour0, float f1) {
        stringBuilder0.append(s);
        stringBuilder0.append(" :  {\n");
        this.serializeAttribute(stringBuilder0, "      behavior", constraintWidget$DimensionBehaviour0.toString(), "FIXED");
        this.serializeAttribute(stringBuilder0, "      size", v, 0);
        this.serializeAttribute(stringBuilder0, "      min", v1, 0);
        this.serializeAttribute(stringBuilder0, "      max", v2, 0x7FFFFFFF);
        this.serializeAttribute(stringBuilder0, "      matchMin", v4, 0);
        this.serializeAttribute(stringBuilder0, "      matchDef", v5, 0);
        this.serializeAttribute(stringBuilder0, "      matchPercent", f, 1.0f);
        stringBuilder0.append("    },\n");
    }

    private void getSceneString(StringBuilder stringBuilder0, String s, ConstraintAnchor constraintAnchor0) {
        if(constraintAnchor0.mTarget == null) {
            return;
        }
        stringBuilder0.append("    ");
        stringBuilder0.append(s);
        stringBuilder0.append(" : [ \'");
        stringBuilder0.append(constraintAnchor0.mTarget);
        stringBuilder0.append("\'");
        if(constraintAnchor0.mGoneMargin != 0x80000000 || constraintAnchor0.mMargin != 0) {
            stringBuilder0.append(",");
            stringBuilder0.append(constraintAnchor0.mMargin);
            if(constraintAnchor0.mGoneMargin != 0x80000000) {
                stringBuilder0.append(",");
                stringBuilder0.append(constraintAnchor0.mGoneMargin);
                stringBuilder0.append(",");
            }
        }
        stringBuilder0.append(" ] ,\n");
    }

    public void getSceneString(StringBuilder stringBuilder0) {
        stringBuilder0.append("  " + this.stringId + ":{\n");
        stringBuilder0.append("    actualWidth:" + this.mWidth);
        stringBuilder0.append("\n");
        stringBuilder0.append("    actualHeight:" + this.mHeight);
        stringBuilder0.append("\n");
        stringBuilder0.append("    actualLeft:" + this.mX);
        stringBuilder0.append("\n");
        stringBuilder0.append("    actualTop:" + this.mY);
        stringBuilder0.append("\n");
        this.getSceneString(stringBuilder0, "left", this.mLeft);
        this.getSceneString(stringBuilder0, "top", this.mTop);
        this.getSceneString(stringBuilder0, "right", this.mRight);
        this.getSceneString(stringBuilder0, "bottom", this.mBottom);
        this.getSceneString(stringBuilder0, "baseline", this.mBaseline);
        this.getSceneString(stringBuilder0, "centerX", this.mCenterX);
        this.getSceneString(stringBuilder0, "centerY", this.mCenterY);
        this.getSceneString(stringBuilder0, "    width", this.mWidth, this.mMinWidth, this.mMaxDimension[0], this.mWidthOverride, this.mMatchConstraintMinWidth, this.mMatchConstraintDefaultWidth, this.mMatchConstraintPercentWidth, this.mListDimensionBehaviors[0], this.mWeight[0]);
        this.getSceneString(stringBuilder0, "    height", this.mHeight, this.mMinHeight, this.mMaxDimension[1], this.mHeightOverride, this.mMatchConstraintMinHeight, this.mMatchConstraintDefaultHeight, this.mMatchConstraintPercentHeight, this.mListDimensionBehaviors[1], this.mWeight[1]);
        this.serializeDimensionRatio(stringBuilder0, "    dimensionRatio", this.mDimensionRatio, this.mDimensionRatioSide);
        this.serializeAttribute(stringBuilder0, "    horizontalBias", this.mHorizontalBiasPercent, ConstraintWidget.DEFAULT_BIAS);
        this.serializeAttribute(stringBuilder0, "    verticalBias", this.mVerticalBiasPercent, ConstraintWidget.DEFAULT_BIAS);
        this.serializeAttribute(stringBuilder0, "    horizontalChainStyle", this.mHorizontalChainStyle, 0);
        this.serializeAttribute(stringBuilder0, "    verticalChainStyle", this.mVerticalChainStyle, 0);
        stringBuilder0.append("  }");
    }

    public int getTop() {
        return this.getY();
    }

    public String getType() {
        return this.mType;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        if(this.isInVerticalChain()) {
            ConstraintWidget constraintWidget0 = this;
            ConstraintWidget constraintWidget1 = null;
            while(constraintWidget1 == null && constraintWidget0 != null) {
                ConstraintAnchor constraintAnchor0 = constraintWidget0.getAnchor(Type.TOP);
                ConstraintAnchor constraintAnchor1 = constraintAnchor0 == null ? null : constraintAnchor0.getTarget();
                ConstraintWidget constraintWidget2 = constraintAnchor1 == null ? null : constraintAnchor1.getOwner();
                if(constraintWidget2 == this.getParent()) {
                    return constraintWidget0;
                }
                ConstraintAnchor constraintAnchor2 = constraintWidget2 == null ? null : constraintWidget2.getAnchor(Type.BOTTOM).getTarget();
                if(constraintAnchor2 != null && constraintAnchor2.getOwner() != constraintWidget0) {
                    constraintWidget1 = constraintWidget0;
                }
                else {
                    constraintWidget0 = constraintWidget2;
                }
            }
            return constraintWidget1;
        }
        return null;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public int getVerticalMargin() {
        int v = this.mLeft == null ? 0 : this.mTop.mMargin;
        return this.mRight == null ? v : v + this.mBottom.mMargin;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public int getWidth() {
        return this.mVisibility == 8 ? 0 : this.mWidth;
    }

    public int getWrapBehaviorInParent() {
        return this.mWrapBehaviorInParent;
    }

    public int getX() {
        return this.mParent == null || !(this.mParent instanceof ConstraintWidgetContainer) ? this.mX : ((ConstraintWidgetContainer)this.mParent).mPaddingLeft + this.mX;
    }

    public int getY() {
        return this.mParent == null || !(this.mParent instanceof ConstraintWidgetContainer) ? this.mY : ((ConstraintWidgetContainer)this.mParent).mPaddingTop + this.mY;
    }

    public boolean hasBaseline() {
        return this.mHasBaseline;
    }

    public boolean hasDanglingDimension(int v) {
        return v == 0 ? (this.mLeft.mTarget == null ? 0 : 1) + (this.mRight.mTarget == null ? 0 : 1) < 2 : (this.mTop.mTarget == null ? 0 : 1) + (this.mBottom.mTarget == null ? 0 : 1) + (this.mBaseline.mTarget == null ? 0 : 1) < 2;
    }

    public boolean hasDependencies() {
        int v = this.mAnchors.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(((ConstraintAnchor)this.mAnchors.get(v1)).hasDependents()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDimensionOverride() {
        return this.mWidthOverride != -1 || this.mHeightOverride != -1;
    }

    // 去混淆评级： 中等(50)
    public boolean hasResolvedTargets(int v, int v1) {
        return v == 0 ? this.mLeft.mTarget != null && this.mLeft.mTarget.hasFinalValue() && this.mRight.mTarget != null && this.mRight.mTarget.hasFinalValue() && this.mRight.mTarget.getFinalValue() - this.mRight.getMargin() - (this.mLeft.mTarget.getFinalValue() + this.mLeft.getMargin()) >= v1 : this.mTop.mTarget != null && this.mTop.mTarget.hasFinalValue() && this.mBottom.mTarget != null && this.mBottom.mTarget.hasFinalValue() && this.mBottom.mTarget.getFinalValue() - this.mBottom.getMargin() - (this.mTop.mTarget.getFinalValue() + this.mTop.getMargin()) >= v1;
    }

    public void immediateConnect(Type constraintAnchor$Type0, ConstraintWidget constraintWidget0, Type constraintAnchor$Type1, int v, int v1) {
        this.getAnchor(constraintAnchor$Type0).connect(constraintWidget0.getAnchor(constraintAnchor$Type1), v, v1, true);
    }

    public boolean isAnimated() {
        return this.mAnimated;
    }

    private boolean isChainHead(int v) {
        if(this.mListAnchors[v * 2].mTarget != null) {
            ConstraintAnchor[] arr_constraintAnchor = this.mListAnchors;
            if(this.mListAnchors[v * 2].mTarget.mTarget != arr_constraintAnchor[v * 2]) {
                int v1 = v * 2 + 1;
                return arr_constraintAnchor[v1].mTarget != null && this.mListAnchors[v1].mTarget.mTarget == this.mListAnchors[v1];
            }
        }
        return false;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public boolean isHorizontalSolvingPassDone() {
        return this.mHorizontalSolvingPass;
    }

    public boolean isInBarrier(int v) {
        return this.mIsInBarrier[v];
    }

    // 去混淆评级： 低(20)
    public boolean isInHorizontalChain() {
        return this.mLeft.mTarget != null && this.mLeft.mTarget.mTarget == this.mLeft || this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight;
    }

    public boolean isInPlaceholder() {
        return this.mInPlaceholder;
    }

    // 去混淆评级： 低(20)
    public boolean isInVerticalChain() {
        return this.mTop.mTarget != null && this.mTop.mTarget.mTarget == this.mTop || this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom;
    }

    public boolean isInVirtualLayout() {
        return this.mInVirtualLayout;
    }

    public boolean isMeasureRequested() {
        return this.mMeasureRequested && this.mVisibility != 8;
    }

    // 去混淆评级： 低(30)
    public boolean isResolvedHorizontally() {
        return this.mResolvedHorizontal || this.mLeft.hasFinalValue() && this.mRight.hasFinalValue();
    }

    // 去混淆评级： 低(30)
    public boolean isResolvedVertically() {
        return this.mResolvedVertical || this.mTop.hasFinalValue() && this.mBottom.hasFinalValue();
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isVerticalSolvingPassDone() {
        return this.mVerticalSolvingPass;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void markHorizontalSolvingPassDone() {
        this.mHorizontalSolvingPass = true;
    }

    public void markVerticalSolvingPassDone() {
        this.mVerticalSolvingPass = true;
    }

    public boolean oppositeDimensionDependsOn(int v) {
        return this.mListDimensionBehaviors[v] == DimensionBehaviour.MATCH_CONSTRAINT && this.mListDimensionBehaviors[(v == 0 ? 1 : 0)] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean oppositeDimensionsTied() {
        return this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = NaNf;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mHorizontalBiasPercent = ConstraintWidget.DEFAULT_BIAS;
        this.mVerticalBiasPercent = ConstraintWidget.DEFAULT_BIAS;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] arr_f = this.mWeight;
        arr_f[0] = -1.0f;
        arr_f[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] arr_v = this.mMaxDimension;
        arr_v[0] = 0x7FFFFFFF;
        arr_v[1] = 0x7FFFFFFF;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = 0x7FFFFFFF;
        this.mMatchConstraintMaxHeight = 0x7FFFFFFF;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedHasRatio = false;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mGroupsToSolver = false;
        boolean[] arr_z = this.isTerminalWidget;
        arr_z[0] = true;
        arr_z[1] = true;
        this.mInVirtualLayout = false;
        boolean[] arr_z1 = this.mIsInBarrier;
        arr_z1[0] = false;
        arr_z1[1] = false;
        this.mMeasureRequested = true;
        int[] arr_v1 = this.mResolvedMatchConstraintDefault;
        arr_v1[0] = 0;
        arr_v1[1] = 0;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
    }

    public void resetAllConstraints() {
        this.resetAnchors();
        this.setVerticalBiasPercent(ConstraintWidget.DEFAULT_BIAS);
        this.setHorizontalBiasPercent(ConstraintWidget.DEFAULT_BIAS);
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor0) {
        ConstraintAnchor constraintAnchor1 = this.getAnchor(Type.LEFT);
        ConstraintAnchor constraintAnchor2 = this.getAnchor(Type.RIGHT);
        ConstraintAnchor constraintAnchor3 = this.getAnchor(Type.TOP);
        ConstraintAnchor constraintAnchor4 = this.getAnchor(Type.BOTTOM);
        ConstraintAnchor constraintAnchor5 = this.getAnchor(Type.CENTER);
        ConstraintAnchor constraintAnchor6 = this.getAnchor(Type.CENTER_X);
        ConstraintAnchor constraintAnchor7 = this.getAnchor(Type.CENTER_Y);
        if(constraintAnchor0 == constraintAnchor5) {
            if(constraintAnchor1.isConnected() && constraintAnchor2.isConnected() && constraintAnchor1.getTarget() == constraintAnchor2.getTarget()) {
                constraintAnchor1.reset();
                constraintAnchor2.reset();
            }
            if(constraintAnchor3.isConnected() && constraintAnchor4.isConnected() && constraintAnchor3.getTarget() == constraintAnchor4.getTarget()) {
                constraintAnchor3.reset();
                constraintAnchor4.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
            this.mVerticalBiasPercent = 0.5f;
        }
        else if(constraintAnchor0 == constraintAnchor6) {
            if(constraintAnchor1.isConnected() && constraintAnchor2.isConnected() && constraintAnchor1.getTarget().getOwner() == constraintAnchor2.getTarget().getOwner()) {
                constraintAnchor1.reset();
                constraintAnchor2.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
        }
        else if(constraintAnchor0 == constraintAnchor7) {
            if(constraintAnchor3.isConnected() && constraintAnchor4.isConnected() && constraintAnchor3.getTarget().getOwner() == constraintAnchor4.getTarget().getOwner()) {
                constraintAnchor3.reset();
                constraintAnchor4.reset();
            }
            this.mVerticalBiasPercent = 0.5f;
        }
        else if(constraintAnchor0 == constraintAnchor1 || constraintAnchor0 == constraintAnchor2) {
            if(constraintAnchor1.isConnected() && constraintAnchor1.getTarget() == constraintAnchor2.getTarget()) {
                constraintAnchor5.reset();
            }
        }
        else if((constraintAnchor0 == constraintAnchor3 || constraintAnchor0 == constraintAnchor4) && constraintAnchor3.isConnected() && constraintAnchor3.getTarget() == constraintAnchor4.getTarget()) {
            constraintAnchor5.reset();
        }
        constraintAnchor0.reset();
    }

    public void resetAnchors() {
        int v = this.mAnchors.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((ConstraintAnchor)this.mAnchors.get(v1)).reset();
        }
    }

    public void resetFinalResolution() {
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
        int v1 = this.mAnchors.size();
        for(int v = 0; v < v1; ++v) {
            ((ConstraintAnchor)this.mAnchors.get(v)).resetFinalResolution();
        }
    }

    public void resetSolverVariables(Cache cache0) {
        this.mLeft.resetSolverVariable(cache0);
        this.mTop.resetSolverVariable(cache0);
        this.mRight.resetSolverVariable(cache0);
        this.mBottom.resetSolverVariable(cache0);
        this.mBaseline.resetSolverVariable(cache0);
        this.mCenter.resetSolverVariable(cache0);
        this.mCenterX.resetSolverVariable(cache0);
        this.mCenterY.resetSolverVariable(cache0);
    }

    public void resetSolvingPassFlag() {
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
    }

    public StringBuilder serialize(StringBuilder stringBuilder0) {
        stringBuilder0.append("{\n");
        this.serializeAnchor(stringBuilder0, "left", this.mLeft);
        this.serializeAnchor(stringBuilder0, "top", this.mTop);
        this.serializeAnchor(stringBuilder0, "right", this.mRight);
        this.serializeAnchor(stringBuilder0, "bottom", this.mBottom);
        this.serializeAnchor(stringBuilder0, "baseline", this.mBaseline);
        this.serializeAnchor(stringBuilder0, "centerX", this.mCenterX);
        this.serializeAnchor(stringBuilder0, "centerY", this.mCenterY);
        this.serializeCircle(stringBuilder0, this.mCenter, this.mCircleConstraintAngle);
        this.serializeSize(stringBuilder0, "width", this.mWidth, this.mMinWidth, this.mMaxDimension[0], this.mWidthOverride, this.mMatchConstraintMinWidth, this.mMatchConstraintDefaultWidth, this.mMatchConstraintPercentWidth, this.mWeight[0]);
        this.serializeSize(stringBuilder0, "height", this.mHeight, this.mMinHeight, this.mMaxDimension[1], this.mHeightOverride, this.mMatchConstraintMinHeight, this.mMatchConstraintDefaultHeight, this.mMatchConstraintPercentHeight, this.mWeight[1]);
        this.serializeDimensionRatio(stringBuilder0, "dimensionRatio", this.mDimensionRatio, this.mDimensionRatioSide);
        this.serializeAttribute(stringBuilder0, "horizontalBias", this.mHorizontalBiasPercent, ConstraintWidget.DEFAULT_BIAS);
        this.serializeAttribute(stringBuilder0, "verticalBias", this.mVerticalBiasPercent, ConstraintWidget.DEFAULT_BIAS);
        stringBuilder0.append("}\n");
        return stringBuilder0;
    }

    private void serializeAnchor(StringBuilder stringBuilder0, String s, ConstraintAnchor constraintAnchor0) {
        if(constraintAnchor0.mTarget == null) {
            return;
        }
        stringBuilder0.append(s);
        stringBuilder0.append(" : [ \'");
        stringBuilder0.append(constraintAnchor0.mTarget);
        stringBuilder0.append("\',");
        stringBuilder0.append(constraintAnchor0.mMargin);
        stringBuilder0.append(",");
        stringBuilder0.append(constraintAnchor0.mGoneMargin);
        stringBuilder0.append(",");
        stringBuilder0.append(" ] ,\n");
    }

    private void serializeAttribute(StringBuilder stringBuilder0, String s, float f, float f1) {
        if(f == f1) {
            return;
        }
        stringBuilder0.append(s);
        stringBuilder0.append(" :   ");
        stringBuilder0.append(f);
        stringBuilder0.append(",\n");
    }

    private void serializeAttribute(StringBuilder stringBuilder0, String s, int v, int v1) {
        if(v == v1) {
            return;
        }
        stringBuilder0.append(s);
        stringBuilder0.append(" :   ");
        stringBuilder0.append(v);
        stringBuilder0.append(",\n");
    }

    private void serializeAttribute(StringBuilder stringBuilder0, String s, String s1, String s2) {
        if(s2.equals(s1)) {
            return;
        }
        stringBuilder0.append(s);
        stringBuilder0.append(" :   ");
        stringBuilder0.append(s1);
        stringBuilder0.append(",\n");
    }

    private void serializeCircle(StringBuilder stringBuilder0, ConstraintAnchor constraintAnchor0, float f) {
        if(constraintAnchor0.mTarget != null && !Float.isNaN(f)) {
            stringBuilder0.append("circle : [ \'");
            stringBuilder0.append(constraintAnchor0.mTarget);
            stringBuilder0.append("\',");
            stringBuilder0.append(constraintAnchor0.mMargin);
            stringBuilder0.append(",");
            stringBuilder0.append(f);
            stringBuilder0.append(",");
            stringBuilder0.append(" ] ,\n");
        }
    }

    private void serializeDimensionRatio(StringBuilder stringBuilder0, String s, float f, int v) {
        if(f == 0.0f) {
            return;
        }
        stringBuilder0.append(s);
        stringBuilder0.append(" :  [");
        stringBuilder0.append(f);
        stringBuilder0.append(",");
        stringBuilder0.append(v);
        stringBuilder0.append("");
        stringBuilder0.append("],\n");
    }

    private void serializeSize(StringBuilder stringBuilder0, String s, int v, int v1, int v2, int v3, int v4, int v5, float f, float f1) {
        stringBuilder0.append(s);
        stringBuilder0.append(" :  {\n");
        this.serializeAttribute(stringBuilder0, "size", v, 0x80000000);
        this.serializeAttribute(stringBuilder0, "min", v1, 0);
        this.serializeAttribute(stringBuilder0, "max", v2, 0x7FFFFFFF);
        this.serializeAttribute(stringBuilder0, "matchMin", v4, 0);
        this.serializeAttribute(stringBuilder0, "matchDef", v5, 0);
        this.serializeAttribute(stringBuilder0, "matchPercent", v5, 1);
        this.serializeAttribute(stringBuilder0, "matchConstraintPercent", f, 1.0f);
        this.serializeAttribute(stringBuilder0, "weight", f1, 1.0f);
        this.serializeAttribute(stringBuilder0, "override", v3, 1);
        stringBuilder0.append("},\n");
    }

    public void setAnimated(boolean z) {
        this.mAnimated = z;
    }

    public void setBaselineDistance(int v) {
        this.mBaselineDistance = v;
        this.mHasBaseline = v > 0;
    }

    public void setCompanionWidget(Object object0) {
        this.mCompanionWidget = object0;
    }

    public void setContainerItemSkip(int v) {
        if(v >= 0) {
            this.mContainerItemSkip = v;
            return;
        }
        this.mContainerItemSkip = 0;
    }

    public void setDebugName(String s) {
        this.mDebugName = s;
    }

    public void setDebugSolverName(LinearSystem linearSystem0, String s) {
        this.mDebugName = s;
        SolverVariable solverVariable0 = linearSystem0.createObjectVariable(this.mLeft);
        SolverVariable solverVariable1 = linearSystem0.createObjectVariable(this.mTop);
        SolverVariable solverVariable2 = linearSystem0.createObjectVariable(this.mRight);
        SolverVariable solverVariable3 = linearSystem0.createObjectVariable(this.mBottom);
        solverVariable0.setName(s + ".left");
        solverVariable1.setName(s + ".top");
        solverVariable2.setName(s + ".right");
        solverVariable3.setName(s + ".bottom");
        linearSystem0.createObjectVariable(this.mBaseline).setName(s + ".baseline");
    }

    public void setDimension(int v, int v1) {
        this.mWidth = v;
        int v2 = this.mMinWidth;
        if(v < v2) {
            this.mWidth = v2;
        }
        this.mHeight = v1;
        int v3 = this.mMinHeight;
        if(v1 < v3) {
            this.mHeight = v3;
        }
    }

    public void setDimensionRatio(float f, int v) {
        this.mDimensionRatio = f;
        this.mDimensionRatioSide = v;
    }

    public void setDimensionRatio(String s) {
        float f2;
        if(s != null && s.length() != 0) {
            int v = s.length();
            int v1 = s.indexOf(44);
            int v2 = 0;
            int v3 = -1;
            if(v1 > 0 && v1 < v - 1) {
                String s1 = s.substring(0, v1);
                if(!s1.equalsIgnoreCase("W")) {
                    v2 = s1.equalsIgnoreCase("H") ? 1 : -1;
                }
                v3 = v2;
                v2 = v1 + 1;
            }
            int v4 = s.indexOf(58);
            if(v4 < 0 || v4 >= v - 1) {
                String s4 = s.substring(v2);
                if(s4.length() > 0) {
                    try {
                        f2 = Float.parseFloat(s4);
                        goto label_29;
                    label_28:
                        f2 = 0.0f;
                    }
                    catch(NumberFormatException unused_ex) {
                        goto label_28;
                    }
                }
                else {
                    goto label_28;
                }
            }
            else {
                String s2 = s.substring(v2, v4);
                String s3 = s.substring(v4 + 1);
                if(s2.length() <= 0 || s3.length() <= 0) {
                    goto label_28;
                }
                else {
                    try {
                        float f = Float.parseFloat(s2);
                        float f1 = Float.parseFloat(s3);
                        if(f <= 0.0f || f1 <= 0.0f) {
                            goto label_28;
                        }
                        else if(v3 == 1) {
                            f2 = Math.abs(f1 / f);
                        }
                        else {
                            f2 = Math.abs(f / f1);
                        }
                    }
                    catch(NumberFormatException unused_ex) {
                        goto label_28;
                    }
                }
            }
        label_29:
            if(f2 > 0.0f) {
                this.mDimensionRatio = f2;
                this.mDimensionRatioSide = v3;
            }
            return;
        }
        this.mDimensionRatio = 0.0f;
    }

    public void setFinalBaseline(int v) {
        if(!this.mHasBaseline) {
            return;
        }
        int v1 = v - this.mBaselineDistance;
        int v2 = this.mHeight + v1;
        this.mY = v1;
        this.mTop.setFinalValue(v1);
        this.mBottom.setFinalValue(v2);
        this.mBaseline.setFinalValue(v);
        this.mResolvedVertical = true;
    }

    public void setFinalFrame(int v, int v1, int v2, int v3, int v4, int v5) {
        this.setFrame(v, v1, v2, v3);
        this.setBaselineDistance(v4);
        if(v5 == 0) {
            this.mResolvedHorizontal = true;
            this.mResolvedVertical = false;
            return;
        }
        if(v5 == 1) {
            this.mResolvedHorizontal = false;
            this.mResolvedVertical = true;
            return;
        }
        if(v5 == 2) {
            this.mResolvedHorizontal = true;
            this.mResolvedVertical = true;
            return;
        }
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
    }

    public void setFinalHorizontal(int v, int v1) {
        if(this.mResolvedHorizontal) {
            return;
        }
        this.mLeft.setFinalValue(v);
        this.mRight.setFinalValue(v1);
        this.mX = v;
        this.mWidth = v1 - v;
        this.mResolvedHorizontal = true;
    }

    public void setFinalLeft(int v) {
        this.mLeft.setFinalValue(v);
        this.mX = v;
    }

    public void setFinalTop(int v) {
        this.mTop.setFinalValue(v);
        this.mY = v;
    }

    public void setFinalVertical(int v, int v1) {
        if(this.mResolvedVertical) {
            return;
        }
        this.mTop.setFinalValue(v);
        this.mBottom.setFinalValue(v1);
        this.mY = v;
        this.mHeight = v1 - v;
        if(this.mHasBaseline) {
            this.mBaseline.setFinalValue(v + this.mBaselineDistance);
        }
        this.mResolvedVertical = true;
    }

    public void setFrame(int v, int v1, int v2) {
        if(v2 == 0) {
            this.setHorizontalDimension(v, v1);
            return;
        }
        if(v2 == 1) {
            this.setVerticalDimension(v, v1);
        }
    }

    public void setFrame(int v, int v1, int v2, int v3) {
        int v4 = v2 - v;
        int v5 = v3 - v1;
        this.mX = v;
        this.mY = v1;
        if(this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if(this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED) {
            int v6 = this.mWidth;
            if(v4 < v6) {
                v4 = v6;
            }
        }
        if(this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED) {
            int v7 = this.mHeight;
            if(v5 < v7) {
                v5 = v7;
            }
        }
        this.mWidth = v4;
        this.mHeight = v5;
        int v8 = this.mMinHeight;
        if(v5 < v8) {
            this.mHeight = v8;
        }
        int v9 = this.mMinWidth;
        if(v4 < v9) {
            this.mWidth = v9;
        }
        if(this.mMatchConstraintMaxWidth > 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.mWidth = Math.min(this.mWidth, this.mMatchConstraintMaxWidth);
        }
        if(this.mMatchConstraintMaxHeight > 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.mHeight = Math.min(this.mHeight, this.mMatchConstraintMaxHeight);
        }
        int v10 = this.mWidth;
        if(v4 != v10) {
            this.mWidthOverride = v10;
        }
        int v11 = this.mHeight;
        if(v5 != v11) {
            this.mHeightOverride = v11;
        }
    }

    public void setGoneMargin(Type constraintAnchor$Type0, int v) {
        switch(constraintAnchor$Type0) {
            case 1: {
                this.mLeft.mGoneMargin = v;
                return;
            }
            case 2: {
                this.mTop.mGoneMargin = v;
                return;
            }
            case 3: {
                this.mRight.mGoneMargin = v;
                return;
            }
            case 4: {
                this.mBottom.mGoneMargin = v;
                return;
            }
            case 5: {
                this.mBaseline.mGoneMargin = v;
            }
        }
    }

    public void setHasBaseline(boolean z) {
        this.mHasBaseline = z;
    }

    public void setHeight(int v) {
        this.mHeight = v;
        int v1 = this.mMinHeight;
        if(v < v1) {
            this.mHeight = v1;
        }
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setHorizontalChainStyle(int v) {
        this.mHorizontalChainStyle = v;
    }

    public void setHorizontalDimension(int v, int v1) {
        this.mX = v;
        int v2 = v1 - v;
        this.mWidth = v2;
        int v3 = this.mMinWidth;
        if(v2 < v3) {
            this.mWidth = v3;
        }
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour constraintWidget$DimensionBehaviour0) {
        this.mListDimensionBehaviors[0] = constraintWidget$DimensionBehaviour0;
    }

    public void setHorizontalMatchStyle(int v, int v1, int v2, float f) {
        this.mMatchConstraintDefaultWidth = v;
        this.mMatchConstraintMinWidth = v1;
        if(v2 == 0x7FFFFFFF) {
            v2 = 0;
        }
        this.mMatchConstraintMaxWidth = v2;
        this.mMatchConstraintPercentWidth = f;
        if(f > 0.0f && f < 1.0f && v == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    protected void setInBarrier(int v, boolean z) {
        this.mIsInBarrier[v] = z;
    }

    public void setInPlaceholder(boolean z) {
        this.mInPlaceholder = z;
    }

    public void setInVirtualLayout(boolean z) {
        this.mInVirtualLayout = z;
    }

    public void setLastMeasureSpec(int v, int v1) {
        this.mLastHorizontalMeasureSpec = v;
        this.mLastVerticalMeasureSpec = v1;
        this.setMeasureRequested(false);
    }

    public void setLength(int v, int v1) {
        if(v1 == 0) {
            this.setWidth(v);
            return;
        }
        if(v1 == 1) {
            this.setHeight(v);
        }
    }

    public void setMaxHeight(int v) {
        this.mMaxDimension[1] = v;
    }

    public void setMaxWidth(int v) {
        this.mMaxDimension[0] = v;
    }

    public void setMeasureRequested(boolean z) {
        this.mMeasureRequested = z;
    }

    public void setMinHeight(int v) {
        if(v < 0) {
            this.mMinHeight = 0;
            return;
        }
        this.mMinHeight = v;
    }

    public void setMinWidth(int v) {
        if(v < 0) {
            this.mMinWidth = 0;
            return;
        }
        this.mMinWidth = v;
    }

    public void setOffset(int v, int v1) {
        this.mOffsetX = v;
        this.mOffsetY = v1;
    }

    public void setOrigin(int v, int v1) {
        this.mX = v;
        this.mY = v1;
    }

    public void setParent(ConstraintWidget constraintWidget0) {
        this.mParent = constraintWidget0;
    }

    void setRelativePositioning(int v, int v1) {
        if(v1 == 0) {
            this.mRelX = v;
            return;
        }
        if(v1 == 1) {
            this.mRelY = v;
        }
    }

    public void setType(String s) {
        this.mType = s;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setVerticalChainStyle(int v) {
        this.mVerticalChainStyle = v;
    }

    public void setVerticalDimension(int v, int v1) {
        this.mY = v;
        int v2 = v1 - v;
        this.mHeight = v2;
        int v3 = this.mMinHeight;
        if(v2 < v3) {
            this.mHeight = v3;
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour constraintWidget$DimensionBehaviour0) {
        this.mListDimensionBehaviors[1] = constraintWidget$DimensionBehaviour0;
    }

    public void setVerticalMatchStyle(int v, int v1, int v2, float f) {
        this.mMatchConstraintDefaultHeight = v;
        this.mMatchConstraintMinHeight = v1;
        if(v2 == 0x7FFFFFFF) {
            v2 = 0;
        }
        this.mMatchConstraintMaxHeight = v2;
        this.mMatchConstraintPercentHeight = f;
        if(f > 0.0f && f < 1.0f && v == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setVisibility(int v) {
        this.mVisibility = v;
    }

    public void setWidth(int v) {
        this.mWidth = v;
        int v1 = this.mMinWidth;
        if(v < v1) {
            this.mWidth = v1;
        }
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public void setWrapBehaviorInParent(int v) {
        if(v >= 0 && v <= 3) {
            this.mWrapBehaviorInParent = v;
        }
    }

    public void setX(int v) {
        this.mX = v;
    }

    public void setY(int v) {
        this.mY = v;
    }

    public void setupDimensionRatio(boolean z, boolean z1, boolean z2, boolean z3) {
        if(this.mResolvedDimensionRatioSide == -1) {
            if(z2 && !z3) {
                this.mResolvedDimensionRatioSide = 0;
            }
            else if(!z2 && z3) {
                this.mResolvedDimensionRatioSide = 1;
                if(this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if(this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        }
        else if(this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if(this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if(this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            }
            else if(this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if(this.mResolvedDimensionRatioSide == -1) {
            int v = this.mMatchConstraintMinWidth;
            if(v > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
                return;
            }
            if(v == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        String s = "";
        stringBuilder0.append((this.mType == null ? "" : "type: " + this.mType + " "));
        if(this.mDebugName != null) {
            s = "id: " + this.mDebugName + " ";
        }
        stringBuilder0.append(s);
        stringBuilder0.append("(");
        stringBuilder0.append(this.mX);
        stringBuilder0.append(", ");
        stringBuilder0.append(this.mY);
        stringBuilder0.append(") - (");
        stringBuilder0.append(this.mWidth);
        stringBuilder0.append(" x ");
        stringBuilder0.append(this.mHeight);
        stringBuilder0.append(")");
        return stringBuilder0.toString();
    }

    public void updateFromRuns(boolean z, boolean z1) {
        int v = z & this.mHorizontalRun.isResolved();
        int v1 = z1 & this.mVerticalRun.isResolved();
        int v2 = this.mHorizontalRun.start.value;
        int v3 = this.mVerticalRun.start.value;
        int v4 = this.mHorizontalRun.end.value;
        int v5 = this.mVerticalRun.end.value;
        if(v4 - v2 < 0 || v5 - v3 < 0 || (v2 == 0x80000000 || v2 == 0x7FFFFFFF) || (v3 == 0x80000000 || v3 == 0x7FFFFFFF) || (v4 == 0x80000000 || v4 == 0x7FFFFFFF) || (v5 == 0x80000000 || v5 == 0x7FFFFFFF)) {
            v2 = 0;
            v3 = 0;
            v4 = 0;
            v5 = 0;
        }
        int v6 = v4 - v2;
        int v7 = v5 - v3;
        if(v != 0) {
            this.mX = v2;
        }
        if(v1 != 0) {
            this.mY = v3;
        }
        if(this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if(v != 0) {
            if(this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED) {
                int v8 = this.mWidth;
                if(v6 < v8) {
                    v6 = v8;
                }
            }
            this.mWidth = v6;
            int v9 = this.mMinWidth;
            if(v6 < v9) {
                this.mWidth = v9;
            }
        }
        if(v1 != 0) {
            if(this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED) {
                int v10 = this.mHeight;
                if(v7 < v10) {
                    v7 = v10;
                }
            }
            this.mHeight = v7;
            int v11 = this.mMinHeight;
            if(v7 < v11) {
                this.mHeight = v11;
            }
        }
    }

    public void updateFromSolver(LinearSystem linearSystem0, boolean z) {
        int v = linearSystem0.getObjectVariableValue(this.mLeft);
        int v1 = linearSystem0.getObjectVariableValue(this.mTop);
        int v2 = linearSystem0.getObjectVariableValue(this.mRight);
        int v3 = linearSystem0.getObjectVariableValue(this.mBottom);
        if(z && (this.mHorizontalRun != null && this.mHorizontalRun.start.resolved && this.mHorizontalRun.end.resolved)) {
            v = this.mHorizontalRun.start.value;
            v2 = this.mHorizontalRun.end.value;
        }
        if(z && (this.mVerticalRun != null && this.mVerticalRun.start.resolved && this.mVerticalRun.end.resolved)) {
            v1 = this.mVerticalRun.start.value;
            v3 = this.mVerticalRun.end.value;
        }
        if(v2 - v < 0 || v3 - v1 < 0 || (v == 0x80000000 || v == 0x7FFFFFFF) || (v1 == 0x80000000 || v1 == 0x7FFFFFFF) || (v2 == 0x80000000 || v2 == 0x7FFFFFFF) || (v3 == 0x80000000 || v3 == 0x7FFFFFFF)) {
            v = 0;
            v3 = 0;
            v1 = 0;
            v2 = 0;
        }
        this.setFrame(v, v1, v2, v3);
    }
}

