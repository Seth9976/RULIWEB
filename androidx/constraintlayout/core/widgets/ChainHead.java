package androidx.constraintlayout.core.widgets;

import java.util.ArrayList;

public class ChainHead {
    private boolean mDefined;
    protected ConstraintWidget mFirst;
    protected ConstraintWidget mFirstMatchConstraintWidget;
    protected ConstraintWidget mFirstVisibleWidget;
    protected boolean mHasComplexMatchWeights;
    protected boolean mHasDefinedWeights;
    protected boolean mHasRatio;
    protected boolean mHasUndefinedWeights;
    protected ConstraintWidget mHead;
    private boolean mIsRtl;
    protected ConstraintWidget mLast;
    protected ConstraintWidget mLastMatchConstraintWidget;
    protected ConstraintWidget mLastVisibleWidget;
    boolean mOptimizable;
    private int mOrientation;
    int mTotalMargins;
    int mTotalSize;
    protected float mTotalWeight;
    int mVisibleWidgets;
    protected ArrayList mWeightedMatchConstraintsWidgets;
    protected int mWidgetsCount;
    protected int mWidgetsMatchCount;

    public ChainHead(ConstraintWidget constraintWidget0, int v, boolean z) {
        this.mTotalWeight = 0.0f;
        this.mFirst = constraintWidget0;
        this.mOrientation = v;
        this.mIsRtl = z;
    }

    public void define() {
        if(!this.mDefined) {
            this.defineChainProperties();
        }
        this.mDefined = true;
    }

    private void defineChainProperties() {
        int v = this.mOrientation * 2;
        ConstraintWidget constraintWidget0 = this.mFirst;
        boolean z = true;
        this.mOptimizable = true;
        ConstraintWidget constraintWidget1 = constraintWidget0;
        boolean z1 = false;
        while(!z1) {
            ++this.mWidgetsCount;
            ConstraintWidget constraintWidget2 = null;
            constraintWidget0.mNextChainWidget[this.mOrientation] = null;
            constraintWidget0.mListNextMatchConstraintsWidget[this.mOrientation] = null;
            if(constraintWidget0.getVisibility() != 8) {
                ++this.mVisibleWidgets;
                if(constraintWidget0.getDimensionBehaviour(this.mOrientation) != DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mTotalSize += constraintWidget0.getLength(this.mOrientation);
                }
                this.mTotalSize = this.mTotalSize + constraintWidget0.mListAnchors[v].getMargin() + constraintWidget0.mListAnchors[v + 1].getMargin();
                this.mTotalMargins = this.mTotalMargins + constraintWidget0.mListAnchors[v].getMargin() + constraintWidget0.mListAnchors[v + 1].getMargin();
                if(this.mFirstVisibleWidget == null) {
                    this.mFirstVisibleWidget = constraintWidget0;
                }
                this.mLastVisibleWidget = constraintWidget0;
                if(constraintWidget0.mListDimensionBehaviors[this.mOrientation] == DimensionBehaviour.MATCH_CONSTRAINT) {
                    switch(constraintWidget0.mResolvedMatchConstraintDefault[this.mOrientation]) {
                        case 0: 
                        case 2: 
                        case 3: {
                            ++this.mWidgetsMatchCount;
                            float f = constraintWidget0.mWeight[this.mOrientation];
                            if(f > 0.0f) {
                                this.mTotalWeight += constraintWidget0.mWeight[this.mOrientation];
                            }
                            if(ChainHead.isMatchConstraintEqualityCandidate(constraintWidget0, this.mOrientation)) {
                                if(f < 0.0f) {
                                    this.mHasUndefinedWeights = true;
                                }
                                else {
                                    this.mHasDefinedWeights = true;
                                }
                                if(this.mWeightedMatchConstraintsWidgets == null) {
                                    this.mWeightedMatchConstraintsWidgets = new ArrayList();
                                }
                                this.mWeightedMatchConstraintsWidgets.add(constraintWidget0);
                            }
                            if(this.mFirstMatchConstraintWidget == null) {
                                this.mFirstMatchConstraintWidget = constraintWidget0;
                            }
                            ConstraintWidget constraintWidget3 = this.mLastMatchConstraintWidget;
                            if(constraintWidget3 != null) {
                                constraintWidget3.mListNextMatchConstraintsWidget[this.mOrientation] = constraintWidget0;
                            }
                            this.mLastMatchConstraintWidget = constraintWidget0;
                        }
                    }
                    if(this.mOrientation != 0) {
                        if(constraintWidget0.mMatchConstraintDefaultHeight != 0) {
                            this.mOptimizable = false;
                        }
                        else if(constraintWidget0.mMatchConstraintMinHeight != 0 || constraintWidget0.mMatchConstraintMaxHeight != 0) {
                            this.mOptimizable = false;
                        }
                    }
                    else if(constraintWidget0.mMatchConstraintDefaultWidth != 0) {
                        this.mOptimizable = false;
                    }
                    else if(constraintWidget0.mMatchConstraintMinWidth != 0 || constraintWidget0.mMatchConstraintMaxWidth != 0) {
                        this.mOptimizable = false;
                    }
                    if(constraintWidget0.mDimensionRatio != 0.0f) {
                        this.mOptimizable = false;
                        this.mHasRatio = true;
                    }
                }
            }
            if(constraintWidget1 != constraintWidget0) {
                constraintWidget1.mNextChainWidget[this.mOrientation] = constraintWidget0;
            }
            ConstraintAnchor constraintAnchor0 = constraintWidget0.mListAnchors[v + 1].mTarget;
            if(constraintAnchor0 != null) {
                ConstraintWidget constraintWidget4 = constraintAnchor0.mOwner;
                if(constraintWidget4.mListAnchors[v].mTarget != null && constraintWidget4.mListAnchors[v].mTarget.mOwner == constraintWidget0) {
                    constraintWidget2 = constraintWidget4;
                }
            }
            if(constraintWidget2 == null) {
                constraintWidget2 = constraintWidget0;
                z1 = true;
            }
            constraintWidget1 = constraintWidget0;
            constraintWidget0 = constraintWidget2;
        }
        ConstraintWidget constraintWidget5 = this.mFirstVisibleWidget;
        if(constraintWidget5 != null) {
            this.mTotalSize -= constraintWidget5.mListAnchors[v].getMargin();
        }
        ConstraintWidget constraintWidget6 = this.mLastVisibleWidget;
        if(constraintWidget6 != null) {
            this.mTotalSize -= constraintWidget6.mListAnchors[v + 1].getMargin();
        }
        this.mLast = constraintWidget0;
        this.mHead = this.mOrientation != 0 || !this.mIsRtl ? this.mFirst : constraintWidget0;
        if(!this.mHasDefinedWeights || !this.mHasUndefinedWeights) {
            z = false;
        }
        this.mHasComplexMatchWeights = z;
    }

    public ConstraintWidget getFirst() {
        return this.mFirst;
    }

    public ConstraintWidget getFirstMatchConstraintWidget() {
        return this.mFirstMatchConstraintWidget;
    }

    public ConstraintWidget getFirstVisibleWidget() {
        return this.mFirstVisibleWidget;
    }

    public ConstraintWidget getHead() {
        return this.mHead;
    }

    public ConstraintWidget getLast() {
        return this.mLast;
    }

    public ConstraintWidget getLastMatchConstraintWidget() {
        return this.mLastMatchConstraintWidget;
    }

    public ConstraintWidget getLastVisibleWidget() {
        return this.mLastVisibleWidget;
    }

    public float getTotalWeight() {
        return this.mTotalWeight;
    }

    private static boolean isMatchConstraintEqualityCandidate(ConstraintWidget constraintWidget0, int v) {
        if(constraintWidget0.getVisibility() != 8 && constraintWidget0.mListDimensionBehaviors[v] == DimensionBehaviour.MATCH_CONSTRAINT) {
            switch(constraintWidget0.mResolvedMatchConstraintDefault[v]) {
                case 0: 
                case 3: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        return false;
    }
}

