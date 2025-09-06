package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Flow extends VirtualLayout {
    class WidgetsList {
        private ConstraintWidget mBiggest;
        int mBiggestDimension;
        private ConstraintAnchor mBottom;
        private int mCount;
        private int mHeight;
        private ConstraintAnchor mLeft;
        private int mMax;
        private int mNbMatchConstraintsWidgets;
        private int mOrientation;
        private int mPaddingBottom;
        private int mPaddingLeft;
        private int mPaddingRight;
        private int mPaddingTop;
        private ConstraintAnchor mRight;
        private int mStartIndex;
        private ConstraintAnchor mTop;
        private int mWidth;

        WidgetsList(int v, ConstraintAnchor constraintAnchor0, ConstraintAnchor constraintAnchor1, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, int v1) {
            this.mBiggest = null;
            this.mBiggestDimension = 0;
            this.mPaddingTop = 0;
            this.mPaddingRight = 0;
            this.mPaddingBottom = 0;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mStartIndex = 0;
            this.mCount = 0;
            this.mNbMatchConstraintsWidgets = 0;
            this.mMax = 0;
            this.mOrientation = v;
            this.mLeft = constraintAnchor0;
            this.mTop = constraintAnchor1;
            this.mRight = constraintAnchor2;
            this.mBottom = constraintAnchor3;
            this.mPaddingLeft = flow0.getPaddingLeft();
            this.mPaddingTop = flow0.getPaddingTop();
            this.mPaddingRight = flow0.getPaddingRight();
            this.mPaddingBottom = flow0.getPaddingBottom();
            this.mMax = v1;
        }

        public void add(ConstraintWidget constraintWidget0) {
            int v = 0;
            if(this.mOrientation == 0) {
                int v1 = Flow.this.getWidgetWidth(constraintWidget0, this.mMax);
                if(constraintWidget0.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                    ++this.mNbMatchConstraintsWidgets;
                    v1 = 0;
                }
                int v2 = Flow.this.mHorizontalGap;
                if(constraintWidget0.getVisibility() != 8) {
                    v = v2;
                }
                this.mWidth += v1 + v;
                int v3 = Flow.this.getWidgetHeight(constraintWidget0, this.mMax);
                if(this.mBiggest == null || this.mBiggestDimension < v3) {
                    this.mBiggest = constraintWidget0;
                    this.mBiggestDimension = v3;
                    this.mHeight = v3;
                }
            }
            else {
                int v4 = Flow.this.getWidgetWidth(constraintWidget0, this.mMax);
                int v5 = Flow.this.getWidgetHeight(constraintWidget0, this.mMax);
                if(constraintWidget0.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                    ++this.mNbMatchConstraintsWidgets;
                    v5 = 0;
                }
                int v6 = Flow.this.mVerticalGap;
                if(constraintWidget0.getVisibility() != 8) {
                    v = v6;
                }
                this.mHeight += v5 + v;
                if(this.mBiggest == null || this.mBiggestDimension < v4) {
                    this.mBiggest = constraintWidget0;
                    this.mBiggestDimension = v4;
                    this.mWidth = v4;
                }
            }
            ++this.mCount;
        }

        public void clear() {
            this.mBiggestDimension = 0;
            this.mBiggest = null;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mStartIndex = 0;
            this.mCount = 0;
            this.mNbMatchConstraintsWidgets = 0;
        }

        public void createConstraints(boolean z, int v, boolean z1) {
            ConstraintWidget constraintWidget5;
            int v1 = this.mCount;
            for(int v2 = 0; v2 < v1 && this.mStartIndex + v2 < Flow.this.mDisplayedWidgetsCount; ++v2) {
                ConstraintWidget constraintWidget0 = Flow.this.mDisplayedWidgets[this.mStartIndex + v2];
                if(constraintWidget0 != null) {
                    constraintWidget0.resetAnchors();
                }
            }
            if(v1 != 0 && this.mBiggest != null) {
                boolean z2 = z1 && v == 0;
                int v4 = -1;
                int v5 = -1;
                for(int v3 = 0; v3 < v1; ++v3) {
                    int v6 = z ? v1 - 1 - v3 : v3;
                    if(this.mStartIndex + v6 >= Flow.this.mDisplayedWidgetsCount) {
                        break;
                    }
                    ConstraintWidget constraintWidget1 = Flow.this.mDisplayedWidgets[this.mStartIndex + v6];
                    if(constraintWidget1 != null && constraintWidget1.getVisibility() == 0) {
                        if(v4 == -1) {
                            v4 = v3;
                        }
                        v5 = v3;
                    }
                }
                ConstraintWidget constraintWidget2 = null;
                if(this.mOrientation == 0) {
                    ConstraintWidget constraintWidget3 = this.mBiggest;
                    constraintWidget3.setVerticalChainStyle(Flow.this.mVerticalStyle);
                    constraintWidget3.mTop.connect(this.mTop, (v <= 0 ? this.mPaddingTop : this.mPaddingTop + Flow.this.mVerticalGap));
                    if(z1) {
                        constraintWidget3.mBottom.connect(this.mBottom, this.mPaddingBottom);
                    }
                    if(v > 0) {
                        this.mTop.mOwner.mBottom.connect(constraintWidget3.mTop, 0);
                    }
                    if(Flow.this.mVerticalAlign == 3 && !constraintWidget3.hasBaseline()) {
                        int v7 = 0;
                        while(v7 < v1) {
                            int v8 = z ? v1 - 1 - v7 : v7;
                            if(this.mStartIndex + v8 >= Flow.this.mDisplayedWidgetsCount) {
                                break;
                            }
                            ConstraintWidget constraintWidget4 = Flow.this.mDisplayedWidgets[this.mStartIndex + v8];
                            if(constraintWidget4.hasBaseline()) {
                                constraintWidget5 = constraintWidget4;
                                goto label_44;
                            }
                            ++v7;
                        }
                    }
                    constraintWidget5 = constraintWidget3;
                label_44:
                    int v9 = 0;
                    while(v9 < v1) {
                        int v10 = z ? v1 - 1 - v9 : v9;
                        if(this.mStartIndex + v10 >= Flow.this.mDisplayedWidgetsCount) {
                            return;
                        }
                        ConstraintWidget constraintWidget6 = Flow.this.mDisplayedWidgets[this.mStartIndex + v10];
                        if(constraintWidget6 == null) {
                            constraintWidget6 = constraintWidget2;
                        }
                        else {
                            if(v9 == 0) {
                                constraintWidget6.connect(constraintWidget6.mLeft, this.mLeft, this.mPaddingLeft);
                            }
                            if(v10 == 0) {
                                int v11 = Flow.this.mHorizontalStyle;
                                float f = z ? 1.0f - Flow.this.mHorizontalBias : Flow.this.mHorizontalBias;
                                if(this.mStartIndex != 0 || Flow.this.mFirstHorizontalStyle == -1) {
                                    if(z1 && Flow.this.mLastHorizontalStyle != -1) {
                                        v11 = Flow.this.mLastHorizontalStyle;
                                        f = z ? 1.0f - Flow.this.mLastHorizontalBias : Flow.this.mLastHorizontalBias;
                                    }
                                    constraintWidget6.setHorizontalChainStyle(v11);
                                    constraintWidget6.setHorizontalBiasPercent(f);
                                }
                                else {
                                    constraintWidget6.setHorizontalChainStyle(Flow.this.mFirstHorizontalStyle);
                                    constraintWidget6.setHorizontalBiasPercent((z ? 1.0f - Flow.this.mFirstHorizontalBias : Flow.this.mFirstHorizontalBias));
                                }
                            }
                            if(v9 == v1 - 1) {
                                constraintWidget6.connect(constraintWidget6.mRight, this.mRight, this.mPaddingRight);
                            }
                            if(constraintWidget2 != null) {
                                constraintWidget6.mLeft.connect(constraintWidget2.mRight, Flow.this.mHorizontalGap);
                                if(v9 == v4) {
                                    constraintWidget6.mLeft.setGoneMargin(this.mPaddingLeft);
                                }
                                constraintWidget2.mRight.connect(constraintWidget6.mLeft, 0);
                                if(v9 == v5 + 1) {
                                    constraintWidget2.mRight.setGoneMargin(this.mPaddingRight);
                                }
                            }
                            if(constraintWidget6 != constraintWidget3) {
                                if(Flow.this.mVerticalAlign != 3 || !constraintWidget5.hasBaseline() || constraintWidget6 == constraintWidget5 || !constraintWidget6.hasBaseline()) {
                                    switch(Flow.this.mVerticalAlign) {
                                        case 0: {
                                            constraintWidget6.mTop.connect(constraintWidget3.mTop, 0);
                                            break;
                                        }
                                        case 1: {
                                            constraintWidget6.mBottom.connect(constraintWidget3.mBottom, 0);
                                            break;
                                        }
                                        default: {
                                            if(z2) {
                                                constraintWidget6.mTop.connect(this.mTop, this.mPaddingTop);
                                                constraintWidget6.mBottom.connect(this.mBottom, this.mPaddingBottom);
                                            }
                                            else {
                                                constraintWidget6.mTop.connect(constraintWidget3.mTop, 0);
                                                constraintWidget6.mBottom.connect(constraintWidget3.mBottom, 0);
                                            }
                                        }
                                    }
                                }
                                else {
                                    constraintWidget6.mBaseline.connect(constraintWidget5.mBaseline, 0);
                                }
                            }
                        }
                        ++v9;
                        constraintWidget2 = constraintWidget6;
                    }
                }
                else {
                    ConstraintWidget constraintWidget7 = this.mBiggest;
                    constraintWidget7.setHorizontalChainStyle(Flow.this.mHorizontalStyle);
                    int v12 = v <= 0 ? this.mPaddingLeft : this.mPaddingLeft + Flow.this.mHorizontalGap;
                    if(z) {
                        constraintWidget7.mRight.connect(this.mRight, v12);
                        if(z1) {
                            constraintWidget7.mLeft.connect(this.mLeft, this.mPaddingRight);
                        }
                        if(v > 0) {
                            this.mRight.mOwner.mLeft.connect(constraintWidget7.mRight, 0);
                        }
                    }
                    else {
                        constraintWidget7.mLeft.connect(this.mLeft, v12);
                        if(z1) {
                            constraintWidget7.mRight.connect(this.mRight, this.mPaddingRight);
                        }
                        if(v > 0) {
                            this.mLeft.mOwner.mRight.connect(constraintWidget7.mLeft, 0);
                        }
                    }
                    for(int v13 = 0; v13 < v1 && this.mStartIndex + v13 < Flow.this.mDisplayedWidgetsCount; ++v13) {
                        ConstraintWidget constraintWidget8 = Flow.this.mDisplayedWidgets[this.mStartIndex + v13];
                        if(constraintWidget8 != null) {
                            if(v13 == 0) {
                                constraintWidget8.connect(constraintWidget8.mTop, this.mTop, this.mPaddingTop);
                                int v14 = Flow.this.mVerticalStyle;
                                float f1 = Flow.this.mVerticalBias;
                                if(this.mStartIndex == 0 && Flow.this.mFirstVerticalStyle != -1) {
                                    v14 = Flow.this.mFirstVerticalStyle;
                                    f1 = Flow.this.mFirstVerticalBias;
                                }
                                else if(z1 && Flow.this.mLastVerticalStyle != -1) {
                                    v14 = Flow.this.mLastVerticalStyle;
                                    f1 = Flow.this.mLastVerticalBias;
                                }
                                constraintWidget8.setVerticalChainStyle(v14);
                                constraintWidget8.setVerticalBiasPercent(f1);
                            }
                            if(v13 == v1 - 1) {
                                constraintWidget8.connect(constraintWidget8.mBottom, this.mBottom, this.mPaddingBottom);
                            }
                            if(constraintWidget2 != null) {
                                constraintWidget8.mTop.connect(constraintWidget2.mBottom, Flow.this.mVerticalGap);
                                if(v13 == v4) {
                                    constraintWidget8.mTop.setGoneMargin(this.mPaddingTop);
                                }
                                constraintWidget2.mBottom.connect(constraintWidget8.mTop, 0);
                                if(v13 == v5 + 1) {
                                    constraintWidget2.mBottom.setGoneMargin(this.mPaddingBottom);
                                }
                            }
                            if(constraintWidget8 != constraintWidget7) {
                                if(z) {
                                    int v15 = Flow.this.mHorizontalAlign;
                                    if(v15 == 0) {
                                        constraintWidget8.mRight.connect(constraintWidget7.mRight, 0);
                                    }
                                    else {
                                        switch(v15) {
                                            case 1: {
                                                constraintWidget8.mLeft.connect(constraintWidget7.mLeft, 0);
                                                break;
                                            }
                                            case 2: {
                                                constraintWidget8.mLeft.connect(constraintWidget7.mLeft, 0);
                                                constraintWidget8.mRight.connect(constraintWidget7.mRight, 0);
                                            }
                                        }
                                    }
                                }
                                else {
                                    int v16 = Flow.this.mHorizontalAlign;
                                    if(v16 == 0) {
                                        constraintWidget8.mLeft.connect(constraintWidget7.mLeft, 0);
                                    }
                                    else {
                                        switch(v16) {
                                            case 1: {
                                                constraintWidget8.mRight.connect(constraintWidget7.mRight, 0);
                                                break;
                                            }
                                            case 2: {
                                                if(z2) {
                                                    constraintWidget8.mLeft.connect(this.mLeft, this.mPaddingLeft);
                                                    constraintWidget8.mRight.connect(this.mRight, this.mPaddingRight);
                                                }
                                                else {
                                                    constraintWidget8.mLeft.connect(constraintWidget7.mLeft, 0);
                                                    constraintWidget8.mRight.connect(constraintWidget7.mRight, 0);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            constraintWidget2 = constraintWidget8;
                        }
                    }
                }
            }
        }

        public int getHeight() {
            return this.mOrientation == 1 ? this.mHeight - Flow.this.mVerticalGap : this.mHeight;
        }

        public int getWidth() {
            return this.mOrientation == 0 ? this.mWidth - Flow.this.mHorizontalGap : this.mWidth;
        }

        public void measureMatchConstraints(int v) {
            int v1 = this.mNbMatchConstraintsWidgets;
            if(v1 == 0) {
                return;
            }
            int v2 = this.mCount;
            int v3 = v / v1;
            for(int v4 = 0; v4 < v2 && this.mStartIndex + v4 < Flow.this.mDisplayedWidgetsCount; ++v4) {
                ConstraintWidget constraintWidget0 = Flow.this.mDisplayedWidgets[this.mStartIndex + v4];
                if(this.mOrientation != 0) {
                    if(constraintWidget0 != null && constraintWidget0.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mMatchConstraintDefaultHeight == 0) {
                        DimensionBehaviour constraintWidget$DimensionBehaviour1 = constraintWidget0.getHorizontalDimensionBehaviour();
                        int v6 = constraintWidget0.getWidth();
                        Flow.this.measure(constraintWidget0, constraintWidget$DimensionBehaviour1, v6, DimensionBehaviour.FIXED, v3);
                    }
                }
                else if(constraintWidget0 != null && constraintWidget0.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mMatchConstraintDefaultWidth == 0) {
                    DimensionBehaviour constraintWidget$DimensionBehaviour0 = constraintWidget0.getVerticalDimensionBehaviour();
                    int v5 = constraintWidget0.getHeight();
                    Flow.this.measure(constraintWidget0, DimensionBehaviour.FIXED, v3, constraintWidget$DimensionBehaviour0, v5);
                }
            }
            this.recomputeDimensions();
        }

        private void recomputeDimensions() {
            this.mWidth = 0;
            this.mHeight = 0;
            this.mBiggest = null;
            this.mBiggestDimension = 0;
            int v = this.mCount;
            for(int v1 = 0; v1 < v && this.mStartIndex + v1 < Flow.this.mDisplayedWidgetsCount; ++v1) {
                ConstraintWidget constraintWidget0 = Flow.this.mDisplayedWidgets[this.mStartIndex + v1];
                if(this.mOrientation == 0) {
                    int v2 = constraintWidget0.getWidth();
                    this.mWidth += v2 + (constraintWidget0.getVisibility() == 8 ? 0 : Flow.this.mHorizontalGap);
                    int v3 = Flow.this.getWidgetHeight(constraintWidget0, this.mMax);
                    if(this.mBiggest == null || this.mBiggestDimension < v3) {
                        this.mBiggest = constraintWidget0;
                        this.mBiggestDimension = v3;
                        this.mHeight = v3;
                    }
                }
                else {
                    int v4 = Flow.this.getWidgetWidth(constraintWidget0, this.mMax);
                    int v5 = Flow.this.getWidgetHeight(constraintWidget0, this.mMax);
                    this.mHeight += v5 + (constraintWidget0.getVisibility() == 8 ? 0 : Flow.this.mVerticalGap);
                    if(this.mBiggest == null || this.mBiggestDimension < v4) {
                        this.mBiggest = constraintWidget0;
                        this.mBiggestDimension = v4;
                        this.mWidth = v4;
                    }
                }
            }
        }

        public void setStartIndex(int v) {
            this.mStartIndex = v;
        }

        public void setup(int v, ConstraintAnchor constraintAnchor0, ConstraintAnchor constraintAnchor1, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, int v1, int v2, int v3, int v4, int v5) {
            this.mOrientation = v;
            this.mLeft = constraintAnchor0;
            this.mTop = constraintAnchor1;
            this.mRight = constraintAnchor2;
            this.mBottom = constraintAnchor3;
            this.mPaddingLeft = v1;
            this.mPaddingTop = v2;
            this.mPaddingRight = v3;
            this.mPaddingBottom = v4;
            this.mMax = v5;
        }
    }

    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_CHAIN_NEW = 3;
    public static final int WRAP_NONE;
    private ConstraintWidget[] mAlignedBiggestElementsInCols;
    private ConstraintWidget[] mAlignedBiggestElementsInRows;
    private int[] mAlignedDimensions;
    private ArrayList mChainList;
    private ConstraintWidget[] mDisplayedWidgets;
    private int mDisplayedWidgetsCount;
    private float mFirstHorizontalBias;
    private int mFirstHorizontalStyle;
    private float mFirstVerticalBias;
    private int mFirstVerticalStyle;
    private int mHorizontalAlign;
    private float mHorizontalBias;
    private int mHorizontalGap;
    private int mHorizontalStyle;
    private float mLastHorizontalBias;
    private int mLastHorizontalStyle;
    private float mLastVerticalBias;
    private int mLastVerticalStyle;
    private int mMaxElementsWrap;
    private int mOrientation;
    private int mVerticalAlign;
    private float mVerticalBias;
    private int mVerticalGap;
    private int mVerticalStyle;
    private int mWrapMode;

    public Flow() {
        this.mHorizontalStyle = -1;
        this.mVerticalStyle = -1;
        this.mFirstHorizontalStyle = -1;
        this.mFirstVerticalStyle = -1;
        this.mLastHorizontalStyle = -1;
        this.mLastVerticalStyle = -1;
        this.mHorizontalBias = 0.5f;
        this.mVerticalBias = 0.5f;
        this.mFirstHorizontalBias = 0.5f;
        this.mFirstVerticalBias = 0.5f;
        this.mLastHorizontalBias = 0.5f;
        this.mLastVerticalBias = 0.5f;
        this.mHorizontalGap = 0;
        this.mVerticalGap = 0;
        this.mHorizontalAlign = 2;
        this.mVerticalAlign = 2;
        this.mWrapMode = 0;
        this.mMaxElementsWrap = -1;
        this.mOrientation = 0;
        this.mChainList = new ArrayList();
        this.mAlignedBiggestElementsInRows = null;
        this.mAlignedBiggestElementsInCols = null;
        this.mAlignedDimensions = null;
        this.mDisplayedWidgetsCount = 0;
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem0, boolean z) {
        super.addToSolver(linearSystem0, z);
        boolean z1 = this.getParent() != null && ((ConstraintWidgetContainer)this.getParent()).isRtl();
        switch(this.mWrapMode) {
            case 0: {
                if(this.mChainList.size() > 0) {
                    ((WidgetsList)this.mChainList.get(0)).createConstraints(z1, 0, true);
                }
                break;
            }
            case 1: {
                int v = this.mChainList.size();
                for(int v1 = 0; v1 < v; ++v1) {
                    ((WidgetsList)this.mChainList.get(v1)).createConstraints(z1, v1, v1 == v - 1);
                }
                break;
            }
            case 2: {
                this.createAlignedConstraints(z1);
                break;
            }
            case 3: {
                int v2 = this.mChainList.size();
                for(int v3 = 0; v3 < v2; ++v3) {
                    ((WidgetsList)this.mChainList.get(v3)).createConstraints(z1, v3, v3 == v2 - 1);
                }
            }
        }
        this.needsCallbackFromSolver(false);
    }

    @Override  // androidx.constraintlayout.core.widgets.HelperWidget
    public void copy(ConstraintWidget constraintWidget0, HashMap hashMap0) {
        super.copy(constraintWidget0, hashMap0);
        this.mHorizontalStyle = ((Flow)constraintWidget0).mHorizontalStyle;
        this.mVerticalStyle = ((Flow)constraintWidget0).mVerticalStyle;
        this.mFirstHorizontalStyle = ((Flow)constraintWidget0).mFirstHorizontalStyle;
        this.mFirstVerticalStyle = ((Flow)constraintWidget0).mFirstVerticalStyle;
        this.mLastHorizontalStyle = ((Flow)constraintWidget0).mLastHorizontalStyle;
        this.mLastVerticalStyle = ((Flow)constraintWidget0).mLastVerticalStyle;
        this.mHorizontalBias = ((Flow)constraintWidget0).mHorizontalBias;
        this.mVerticalBias = ((Flow)constraintWidget0).mVerticalBias;
        this.mFirstHorizontalBias = ((Flow)constraintWidget0).mFirstHorizontalBias;
        this.mFirstVerticalBias = ((Flow)constraintWidget0).mFirstVerticalBias;
        this.mLastHorizontalBias = ((Flow)constraintWidget0).mLastHorizontalBias;
        this.mLastVerticalBias = ((Flow)constraintWidget0).mLastVerticalBias;
        this.mHorizontalGap = ((Flow)constraintWidget0).mHorizontalGap;
        this.mVerticalGap = ((Flow)constraintWidget0).mVerticalGap;
        this.mHorizontalAlign = ((Flow)constraintWidget0).mHorizontalAlign;
        this.mVerticalAlign = ((Flow)constraintWidget0).mVerticalAlign;
        this.mWrapMode = ((Flow)constraintWidget0).mWrapMode;
        this.mMaxElementsWrap = ((Flow)constraintWidget0).mMaxElementsWrap;
        this.mOrientation = ((Flow)constraintWidget0).mOrientation;
    }

    private void createAlignedConstraints(boolean z) {
        float f1;
        int v4;
        if(this.mAlignedDimensions != null && this.mAlignedBiggestElementsInCols != null && this.mAlignedBiggestElementsInRows != null) {
            for(int v = 0; v < this.mDisplayedWidgetsCount; ++v) {
                this.mDisplayedWidgets[v].resetAnchors();
            }
            int[] arr_v = this.mAlignedDimensions;
            int v1 = arr_v[0];
            int v2 = arr_v[1];
            float f = this.mHorizontalBias;
            ConstraintWidget constraintWidget0 = null;
            int v3 = 0;
            while(v3 < v1) {
                if(z) {
                    v4 = v1 - v3 - 1;
                    f1 = 1.0f - this.mHorizontalBias;
                }
                else {
                    f1 = f;
                    v4 = v3;
                }
                ConstraintWidget constraintWidget1 = this.mAlignedBiggestElementsInCols[v4];
                if(constraintWidget1 != null && constraintWidget1.getVisibility() != 8) {
                    if(v3 == 0) {
                        constraintWidget1.connect(constraintWidget1.mLeft, this.mLeft, this.getPaddingLeft());
                        constraintWidget1.setHorizontalChainStyle(this.mHorizontalStyle);
                        constraintWidget1.setHorizontalBiasPercent(f1);
                    }
                    if(v3 == v1 - 1) {
                        constraintWidget1.connect(constraintWidget1.mRight, this.mRight, this.getPaddingRight());
                    }
                    if(v3 > 0 && constraintWidget0 != null) {
                        constraintWidget1.connect(constraintWidget1.mLeft, constraintWidget0.mRight, this.mHorizontalGap);
                        constraintWidget0.connect(constraintWidget0.mRight, constraintWidget1.mLeft, 0);
                    }
                    constraintWidget0 = constraintWidget1;
                }
                ++v3;
                f = f1;
            }
            for(int v5 = 0; v5 < v2; ++v5) {
                ConstraintWidget constraintWidget2 = this.mAlignedBiggestElementsInRows[v5];
                if(constraintWidget2 != null && constraintWidget2.getVisibility() != 8) {
                    if(v5 == 0) {
                        constraintWidget2.connect(constraintWidget2.mTop, this.mTop, this.getPaddingTop());
                        constraintWidget2.setVerticalChainStyle(this.mVerticalStyle);
                        constraintWidget2.setVerticalBiasPercent(this.mVerticalBias);
                    }
                    if(v5 == v2 - 1) {
                        constraintWidget2.connect(constraintWidget2.mBottom, this.mBottom, this.getPaddingBottom());
                    }
                    if(v5 > 0 && constraintWidget0 != null) {
                        constraintWidget2.connect(constraintWidget2.mTop, constraintWidget0.mBottom, this.mVerticalGap);
                        constraintWidget0.connect(constraintWidget0.mBottom, constraintWidget2.mTop, 0);
                    }
                    constraintWidget0 = constraintWidget2;
                }
            }
            for(int v6 = 0; v6 < v1; ++v6) {
                for(int v7 = 0; v7 < v2; ++v7) {
                    int v8 = this.mOrientation == 1 ? v6 * v2 + v7 : v7 * v1 + v6;
                    ConstraintWidget[] arr_constraintWidget = this.mDisplayedWidgets;
                    if(v8 < arr_constraintWidget.length) {
                        ConstraintWidget constraintWidget3 = arr_constraintWidget[v8];
                        if(constraintWidget3 != null && constraintWidget3.getVisibility() != 8) {
                            ConstraintWidget constraintWidget4 = this.mAlignedBiggestElementsInCols[v6];
                            ConstraintWidget constraintWidget5 = this.mAlignedBiggestElementsInRows[v7];
                            if(constraintWidget3 != constraintWidget4) {
                                constraintWidget3.connect(constraintWidget3.mLeft, constraintWidget4.mLeft, 0);
                                constraintWidget3.connect(constraintWidget3.mRight, constraintWidget4.mRight, 0);
                            }
                            if(constraintWidget3 != constraintWidget5) {
                                constraintWidget3.connect(constraintWidget3.mTop, constraintWidget5.mTop, 0);
                                constraintWidget3.connect(constraintWidget3.mBottom, constraintWidget5.mBottom, 0);
                            }
                        }
                    }
                }
            }
        }
    }

    public float getMaxElementsWrap() {
        return (float)this.mMaxElementsWrap;
    }

    private int getWidgetHeight(ConstraintWidget constraintWidget0, int v) {
        if(constraintWidget0 == null) {
            return 0;
        }
        if(constraintWidget0.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
            switch(constraintWidget0.mMatchConstraintDefaultHeight) {
                case 0: {
                    return 0;
                }
                case 1: {
                    return constraintWidget0.getHeight();
                }
                case 2: {
                    int v1 = (int)(constraintWidget0.mMatchConstraintPercentHeight * ((float)v));
                    if(v1 != constraintWidget0.getHeight()) {
                        constraintWidget0.setMeasureRequested(true);
                        this.measure(constraintWidget0, constraintWidget0.getHorizontalDimensionBehaviour(), constraintWidget0.getWidth(), DimensionBehaviour.FIXED, v1);
                    }
                    return v1;
                }
                case 3: {
                    return (int)(((float)constraintWidget0.getWidth()) * constraintWidget0.mDimensionRatio + 0.5f);
                }
                default: {
                    return constraintWidget0.getHeight();
                }
            }
        }
        return constraintWidget0.getHeight();
    }

    private int getWidgetWidth(ConstraintWidget constraintWidget0, int v) {
        if(constraintWidget0 == null) {
            return 0;
        }
        if(constraintWidget0.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
            switch(constraintWidget0.mMatchConstraintDefaultWidth) {
                case 0: {
                    return 0;
                }
                case 1: {
                    return constraintWidget0.getWidth();
                }
                case 2: {
                    int v1 = (int)(constraintWidget0.mMatchConstraintPercentWidth * ((float)v));
                    if(v1 != constraintWidget0.getWidth()) {
                        constraintWidget0.setMeasureRequested(true);
                        DimensionBehaviour constraintWidget$DimensionBehaviour0 = constraintWidget0.getVerticalDimensionBehaviour();
                        int v2 = constraintWidget0.getHeight();
                        this.measure(constraintWidget0, DimensionBehaviour.FIXED, v1, constraintWidget$DimensionBehaviour0, v2);
                    }
                    return v1;
                }
                case 3: {
                    return (int)(((float)constraintWidget0.getHeight()) * constraintWidget0.mDimensionRatio + 0.5f);
                }
                default: {
                    return constraintWidget0.getWidth();
                }
            }
        }
        return constraintWidget0.getWidth();
    }

    @Override  // androidx.constraintlayout.core.widgets.VirtualLayout
    public void measure(int v, int v1, int v2, int v3) {
        ConstraintWidget[] arr_constraintWidget2;
        if(this.mWidgetsCount > 0 && !this.measureChildren()) {
            this.setMeasure(0, 0);
            this.needsCallbackFromSolver(false);
            return;
        }
        int v4 = this.getPaddingLeft();
        int v5 = this.getPaddingRight();
        int v6 = this.getPaddingTop();
        int v7 = this.getPaddingBottom();
        int[] arr_v = new int[2];
        int v8 = v1 - v4 - v5;
        int v9 = this.mOrientation;
        if(v9 == 1) {
            v8 = v3 - v6 - v7;
        }
        if(v9 == 0) {
            if(this.mHorizontalStyle == -1) {
                this.mHorizontalStyle = 0;
            }
            if(this.mVerticalStyle == -1) {
                this.mVerticalStyle = 0;
            }
        }
        else {
            if(this.mHorizontalStyle == -1) {
                this.mHorizontalStyle = 0;
            }
            if(this.mVerticalStyle == -1) {
                this.mVerticalStyle = 0;
            }
        }
        ConstraintWidget[] arr_constraintWidget = this.mWidgets;
        int v11 = 0;
        for(int v10 = 0; v10 < this.mWidgetsCount; ++v10) {
            if(this.mWidgets[v10].getVisibility() == 8) {
                ++v11;
            }
        }
        int v12 = this.mWidgetsCount;
        if(v11 > 0) {
            ConstraintWidget[] arr_constraintWidget1 = new ConstraintWidget[this.mWidgetsCount - v11];
            v12 = 0;
            for(int v13 = 0; v13 < this.mWidgetsCount; ++v13) {
                ConstraintWidget constraintWidget0 = this.mWidgets[v13];
                if(constraintWidget0.getVisibility() != 8) {
                    arr_constraintWidget1[v12] = constraintWidget0;
                    ++v12;
                }
            }
            arr_constraintWidget2 = arr_constraintWidget1;
        }
        else {
            arr_constraintWidget2 = arr_constraintWidget;
        }
        this.mDisplayedWidgets = arr_constraintWidget2;
        this.mDisplayedWidgetsCount = v12;
        switch(this.mWrapMode) {
            case 0: {
                this.measureNoWrap(arr_constraintWidget2, v12, this.mOrientation, v8, arr_v);
                break;
            }
            case 1: {
                this.measureChainWrap(arr_constraintWidget2, v12, this.mOrientation, v8, arr_v);
                break;
            }
            case 2: {
                this.measureAligned(arr_constraintWidget2, v12, this.mOrientation, v8, arr_v);
                break;
            }
            case 3: {
                this.measureChainWrap_new(arr_constraintWidget2, v12, this.mOrientation, v8, arr_v);
            }
        }
        int v14 = arr_v[0] + v4 + v5;
        int v15 = arr_v[1] + v6 + v7;
        if(v == 0x40000000) {
            v14 = v1;
        }
        else if(v == 0x80000000) {
            v14 = Math.min(v14, v1);
        }
        else if(v != 0) {
            v14 = 0;
        }
        if(v2 == 0x40000000) {
            v15 = v3;
        }
        else if(v2 == 0x80000000) {
            v15 = Math.min(v15, v3);
        }
        else if(v2 != 0) {
            v15 = 0;
        }
        this.setMeasure(v14, v15);
        this.setWidth(v14);
        this.setHeight(v15);
        this.needsCallbackFromSolver(this.mWidgetsCount > 0);
    }

    private void measureAligned(ConstraintWidget[] arr_constraintWidget, int v, int v1, int v2, int[] arr_v) {
        boolean z;
        int v7;
        int v6;
        if(v1 == 0) {
            int v3 = this.mMaxElementsWrap;
            if(v3 <= 0) {
                v3 = 0;
                int v5 = 0;
                for(int v4 = 0; v4 < v; ++v4) {
                    if(v4 > 0) {
                        v5 += this.mHorizontalGap;
                    }
                    ConstraintWidget constraintWidget0 = arr_constraintWidget[v4];
                    if(constraintWidget0 != null) {
                        v5 += this.getWidgetWidth(constraintWidget0, v2);
                        if(v5 > v2) {
                            break;
                        }
                        ++v3;
                    }
                }
            }
            v6 = v3;
            v7 = 0;
        }
        else {
            v7 = this.mMaxElementsWrap;
            if(v7 <= 0) {
                v7 = 0;
                int v9 = 0;
                for(int v8 = 0; v8 < v; ++v8) {
                    if(v8 > 0) {
                        v9 += this.mVerticalGap;
                    }
                    ConstraintWidget constraintWidget1 = arr_constraintWidget[v8];
                    if(constraintWidget1 != null) {
                        v9 += this.getWidgetHeight(constraintWidget1, v2);
                        if(v9 > v2) {
                            break;
                        }
                        ++v7;
                    }
                }
            }
            v6 = 0;
        }
        if(this.mAlignedDimensions == null) {
            this.mAlignedDimensions = new int[2];
        }
        if((v7 != 0 || v1 != 1) && (v6 != 0 || v1 != 0)) {
            z = false;
            goto label_41;
        }
    alab1:
        while(true) {
            while(true) {
            label_40:
                z = true;
            label_41:
                if(z) {
                    break alab1;
                }
                if(v1 == 0) {
                    v7 = (int)Math.ceil(((float)v) / ((float)v6));
                }
                else {
                    v6 = (int)Math.ceil(((float)v) / ((float)v7));
                }
                ConstraintWidget[] arr_constraintWidget1 = this.mAlignedBiggestElementsInCols;
                if(arr_constraintWidget1 == null || arr_constraintWidget1.length < v6) {
                    this.mAlignedBiggestElementsInCols = new ConstraintWidget[v6];
                }
                else {
                    Arrays.fill(arr_constraintWidget1, null);
                }
                ConstraintWidget[] arr_constraintWidget2 = this.mAlignedBiggestElementsInRows;
                if(arr_constraintWidget2 == null || arr_constraintWidget2.length < v7) {
                    this.mAlignedBiggestElementsInRows = new ConstraintWidget[v7];
                }
                else {
                    Arrays.fill(arr_constraintWidget2, null);
                }
                for(int v10 = 0; v10 < v6; ++v10) {
                    for(int v11 = 0; v11 < v7; ++v11) {
                        int v12 = v1 == 1 ? v10 * v7 + v11 : v11 * v6 + v10;
                        if(v12 < arr_constraintWidget.length) {
                            ConstraintWidget constraintWidget2 = arr_constraintWidget[v12];
                            if(constraintWidget2 != null) {
                                int v13 = this.getWidgetWidth(constraintWidget2, v2);
                                ConstraintWidget constraintWidget3 = this.mAlignedBiggestElementsInCols[v10];
                                if(constraintWidget3 == null || constraintWidget3.getWidth() < v13) {
                                    this.mAlignedBiggestElementsInCols[v10] = constraintWidget2;
                                }
                                int v14 = this.getWidgetHeight(constraintWidget2, v2);
                                ConstraintWidget constraintWidget4 = this.mAlignedBiggestElementsInRows[v11];
                                if(constraintWidget4 == null || constraintWidget4.getHeight() < v14) {
                                    this.mAlignedBiggestElementsInRows[v11] = constraintWidget2;
                                }
                            }
                        }
                    }
                }
                int v16 = 0;
                for(int v15 = 0; v15 < v6; ++v15) {
                    ConstraintWidget constraintWidget5 = this.mAlignedBiggestElementsInCols[v15];
                    if(constraintWidget5 != null) {
                        if(v15 > 0) {
                            v16 += this.mHorizontalGap;
                        }
                        v16 += this.getWidgetWidth(constraintWidget5, v2);
                    }
                }
                int v18 = 0;
                for(int v17 = 0; v17 < v7; ++v17) {
                    ConstraintWidget constraintWidget6 = this.mAlignedBiggestElementsInRows[v17];
                    if(constraintWidget6 != null) {
                        if(v17 > 0) {
                            v18 += this.mVerticalGap;
                        }
                        v18 += this.getWidgetHeight(constraintWidget6, v2);
                    }
                }
                arr_v[0] = v16;
                arr_v[1] = v18;
                if(v1 == 0) {
                    if(v16 <= v2 || v6 <= 1) {
                        continue;
                    }
                    break;
                }
                else {
                    goto label_102;
                }
                break alab1;
            }
            --v6;
            goto label_41;
        label_102:
            if(v18 <= v2 || v7 <= 1) {
                goto label_40;
            }
            --v7;
            goto label_41;
        }
        int[] arr_v1 = this.mAlignedDimensions;
        arr_v1[0] = v6;
        arr_v1[1] = v7;
    }

    private void measureChainWrap(ConstraintWidget[] arr_constraintWidget, int v, int v1, int v2, int[] arr_v) {
        ConstraintAnchor constraintAnchor9;
        int v3;
        if(v == 0) {
            return;
        }
        this.mChainList.clear();
        WidgetsList flow$WidgetsList0 = new WidgetsList(this, v1, this.mLeft, this.mTop, this.mRight, this.mBottom, v2);
        this.mChainList.add(flow$WidgetsList0);
        if(v1 == 0) {
            v3 = 0;
            int v4 = 0;
            int v5 = 0;
            while(v5 < v) {
                ConstraintWidget constraintWidget0 = arr_constraintWidget[v5];
                int v6 = this.getWidgetWidth(constraintWidget0, v2);
                if(constraintWidget0.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                    ++v3;
                }
                if(((v4 == v2 || this.mHorizontalGap + v4 + v6 > v2) && flow$WidgetsList0.mBiggest != null || v5 <= 0 || (this.mMaxElementsWrap <= 0 || v5 % this.mMaxElementsWrap != 0) ? (v4 == v2 || this.mHorizontalGap + v4 + v6 > v2) && flow$WidgetsList0.mBiggest != null : true)) {
                    flow$WidgetsList0 = new WidgetsList(this, 0, this.mLeft, this.mTop, this.mRight, this.mBottom, v2);
                    flow$WidgetsList0.setStartIndex(v5);
                    this.mChainList.add(flow$WidgetsList0);
                }
                else if(v5 > 0) {
                    v4 += this.mHorizontalGap + v6;
                    goto label_23;
                }
                v4 = v6;
            label_23:
                flow$WidgetsList0.add(constraintWidget0);
                ++v5;
            }
        }
        else {
            v3 = 0;
            int v7 = 0;
            int v8 = 0;
            while(v8 < v) {
                ConstraintWidget constraintWidget1 = arr_constraintWidget[v8];
                int v9 = this.getWidgetHeight(constraintWidget1, v2);
                if(constraintWidget1.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                    ++v3;
                }
                if(((v7 == v2 || this.mVerticalGap + v7 + v9 > v2) && flow$WidgetsList0.mBiggest != null || v8 <= 0 || (this.mMaxElementsWrap <= 0 || v8 % this.mMaxElementsWrap != 0) ? (v7 == v2 || this.mVerticalGap + v7 + v9 > v2) && flow$WidgetsList0.mBiggest != null : true)) {
                    flow$WidgetsList0 = new WidgetsList(this, v1, this.mLeft, this.mTop, this.mRight, this.mBottom, v2);
                    flow$WidgetsList0.setStartIndex(v8);
                    this.mChainList.add(flow$WidgetsList0);
                }
                else if(v8 > 0) {
                    v7 += this.mVerticalGap + v9;
                    goto label_44;
                }
                v7 = v9;
            label_44:
                flow$WidgetsList0.add(constraintWidget1);
                ++v8;
            }
        }
        int v10 = this.mChainList.size();
        ConstraintAnchor constraintAnchor0 = this.mLeft;
        ConstraintAnchor constraintAnchor1 = this.mTop;
        ConstraintAnchor constraintAnchor2 = this.mRight;
        ConstraintAnchor constraintAnchor3 = this.mBottom;
        int v11 = this.getPaddingLeft();
        int v12 = this.getPaddingTop();
        int v13 = this.getPaddingRight();
        int v14 = this.getPaddingBottom();
        if(v3 > 0 && (this.getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT || this.getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT)) {
            for(int v15 = 0; v15 < v10; ++v15) {
                WidgetsList flow$WidgetsList1 = (WidgetsList)this.mChainList.get(v15);
                if(v1 == 0) {
                    flow$WidgetsList1.measureMatchConstraints(v2 - flow$WidgetsList1.getWidth());
                }
                else {
                    flow$WidgetsList1.measureMatchConstraints(v2 - flow$WidgetsList1.getHeight());
                }
            }
        }
        ConstraintAnchor constraintAnchor4 = constraintAnchor0;
        int v16 = v14;
        int v17 = 0;
        int v19 = v13;
        int v20 = v12;
        int v21 = v11;
        ConstraintAnchor constraintAnchor5 = constraintAnchor3;
        ConstraintAnchor constraintAnchor6 = constraintAnchor2;
        ConstraintAnchor constraintAnchor7 = constraintAnchor1;
        int v22 = 0;
        for(int v18 = 0; v18 < v10; ++v18) {
            WidgetsList flow$WidgetsList2 = (WidgetsList)this.mChainList.get(v18);
            if(v1 == 0) {
                if(v18 < v10 - 1) {
                    constraintAnchor5 = ((WidgetsList)this.mChainList.get(v18 + 1)).mBiggest.mTop;
                    v16 = 0;
                }
                else {
                    constraintAnchor5 = this.mBottom;
                    v16 = this.getPaddingBottom();
                }
                ConstraintAnchor constraintAnchor8 = flow$WidgetsList2.mBiggest.mBottom;
                flow$WidgetsList2.setup(0, constraintAnchor4, constraintAnchor7, constraintAnchor6, constraintAnchor5, v21, v20, v19, v16, v2);
                int v23 = Math.max(v17, flow$WidgetsList2.getWidth());
                int v24 = flow$WidgetsList2.getHeight() + v22;
                if(v18 > 0) {
                    v24 += this.mVerticalGap;
                }
                v22 = v24;
                v17 = v23;
                constraintAnchor7 = constraintAnchor8;
                v20 = 0;
            }
            else {
                if(v18 < v10 - 1) {
                    constraintAnchor9 = ((WidgetsList)this.mChainList.get(v18 + 1)).mBiggest.mLeft;
                    v19 = 0;
                }
                else {
                    constraintAnchor9 = this.mRight;
                    v19 = this.getPaddingRight();
                }
                constraintAnchor6 = constraintAnchor9;
                ConstraintAnchor constraintAnchor10 = flow$WidgetsList2.mBiggest.mRight;
                flow$WidgetsList2.setup(v1, constraintAnchor4, constraintAnchor7, constraintAnchor6, constraintAnchor5, v21, v20, v19, v16, v2);
                int v25 = flow$WidgetsList2.getWidth() + v17;
                int v26 = Math.max(v22, flow$WidgetsList2.getHeight());
                if(v18 > 0) {
                    v25 += this.mHorizontalGap;
                }
                v22 = v26;
                v17 = v25;
                constraintAnchor4 = constraintAnchor10;
                v21 = 0;
            }
        }
        arr_v[0] = v17;
        arr_v[1] = v22;
    }

    private void measureChainWrap_new(ConstraintWidget[] arr_constraintWidget, int v, int v1, int v2, int[] arr_v) {
        ConstraintAnchor constraintAnchor9;
        int v4;
        if(v == 0) {
            return;
        }
        this.mChainList.clear();
        WidgetsList flow$WidgetsList0 = new WidgetsList(this, v1, this.mLeft, this.mTop, this.mRight, this.mBottom, v2);
        this.mChainList.add(flow$WidgetsList0);
        if(v1 == 0) {
            int v3 = 0;
            v4 = 0;
            int v5 = 0;
            for(int v6 = 0; v6 < v; ++v6) {
                ++v3;
                ConstraintWidget constraintWidget0 = arr_constraintWidget[v6];
                int v7 = this.getWidgetWidth(constraintWidget0, v2);
                if(constraintWidget0.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                    ++v4;
                }
                if(((v5 == v2 || this.mHorizontalGap + v5 + v7 > v2) && flow$WidgetsList0.mBiggest != null || v6 <= 0 || (this.mMaxElementsWrap <= 0 || v3 <= this.mMaxElementsWrap) ? (v5 == v2 || this.mHorizontalGap + v5 + v7 > v2) && flow$WidgetsList0.mBiggest != null : true)) {
                    flow$WidgetsList0 = new WidgetsList(this, 0, this.mLeft, this.mTop, this.mRight, this.mBottom, v2);
                    flow$WidgetsList0.setStartIndex(v6);
                    this.mChainList.add(flow$WidgetsList0);
                    v5 = v7;
                    v3 = 1;
                }
                else {
                    v5 = v6 <= 0 ? v7 : v5 + (this.mHorizontalGap + v7);
                }
                flow$WidgetsList0.add(constraintWidget0);
            }
        }
        else {
            int v8 = 0;
            v4 = 0;
            int v9 = 0;
            for(int v10 = 0; v10 < v; ++v10) {
                ++v8;
                ConstraintWidget constraintWidget1 = arr_constraintWidget[v10];
                int v11 = this.getWidgetHeight(constraintWidget1, v2);
                if(constraintWidget1.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                    ++v4;
                }
                if(((v9 == v2 || this.mVerticalGap + v9 + v11 > v2) && flow$WidgetsList0.mBiggest != null || v10 <= 0 || (this.mMaxElementsWrap <= 0 || v8 <= this.mMaxElementsWrap) ? (v9 == v2 || this.mVerticalGap + v9 + v11 > v2) && flow$WidgetsList0.mBiggest != null : true)) {
                    flow$WidgetsList0 = new WidgetsList(this, v1, this.mLeft, this.mTop, this.mRight, this.mBottom, v2);
                    flow$WidgetsList0.setStartIndex(v10);
                    this.mChainList.add(flow$WidgetsList0);
                    v9 = v11;
                    v8 = 1;
                }
                else {
                    v9 = v10 <= 0 ? v11 : v9 + (this.mVerticalGap + v11);
                }
                flow$WidgetsList0.add(constraintWidget1);
            }
        }
        int v12 = this.mChainList.size();
        ConstraintAnchor constraintAnchor0 = this.mLeft;
        ConstraintAnchor constraintAnchor1 = this.mTop;
        ConstraintAnchor constraintAnchor2 = this.mRight;
        ConstraintAnchor constraintAnchor3 = this.mBottom;
        int v13 = this.getPaddingLeft();
        int v14 = this.getPaddingTop();
        int v15 = this.getPaddingRight();
        int v16 = this.getPaddingBottom();
        if(v4 > 0 && (this.getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT || this.getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT)) {
            for(int v17 = 0; v17 < v12; ++v17) {
                WidgetsList flow$WidgetsList1 = (WidgetsList)this.mChainList.get(v17);
                if(v1 == 0) {
                    flow$WidgetsList1.measureMatchConstraints(v2 - flow$WidgetsList1.getWidth());
                }
                else {
                    flow$WidgetsList1.measureMatchConstraints(v2 - flow$WidgetsList1.getHeight());
                }
            }
        }
        ConstraintAnchor constraintAnchor4 = constraintAnchor1;
        int v18 = v16;
        int v19 = 0;
        int v21 = v15;
        int v22 = v14;
        int v23 = v13;
        ConstraintAnchor constraintAnchor5 = constraintAnchor3;
        ConstraintAnchor constraintAnchor6 = constraintAnchor2;
        ConstraintAnchor constraintAnchor7 = constraintAnchor0;
        int v24 = 0;
        for(int v20 = 0; v20 < v12; ++v20) {
            WidgetsList flow$WidgetsList2 = (WidgetsList)this.mChainList.get(v20);
            if(v1 == 0) {
                if(v20 < v12 - 1) {
                    constraintAnchor5 = ((WidgetsList)this.mChainList.get(v20 + 1)).mBiggest.mTop;
                    v18 = 0;
                }
                else {
                    constraintAnchor5 = this.mBottom;
                    v18 = this.getPaddingBottom();
                }
                ConstraintAnchor constraintAnchor8 = flow$WidgetsList2.mBiggest.mBottom;
                flow$WidgetsList2.setup(0, constraintAnchor7, constraintAnchor4, constraintAnchor6, constraintAnchor5, v23, v22, v21, v18, v2);
                int v25 = Math.max(v24, flow$WidgetsList2.getWidth());
                int v26 = flow$WidgetsList2.getHeight() + v19;
                if(v20 > 0) {
                    v26 += this.mVerticalGap;
                }
                v19 = v26;
                v24 = v25;
                constraintAnchor4 = constraintAnchor8;
                v22 = 0;
            }
            else {
                if(v20 < v12 - 1) {
                    constraintAnchor9 = ((WidgetsList)this.mChainList.get(v20 + 1)).mBiggest.mLeft;
                    v21 = 0;
                }
                else {
                    constraintAnchor9 = this.mRight;
                    v21 = this.getPaddingRight();
                }
                constraintAnchor6 = constraintAnchor9;
                ConstraintAnchor constraintAnchor10 = flow$WidgetsList2.mBiggest.mRight;
                flow$WidgetsList2.setup(v1, constraintAnchor7, constraintAnchor4, constraintAnchor6, constraintAnchor5, v23, v22, v21, v18, v2);
                int v27 = flow$WidgetsList2.getWidth() + v24;
                int v28 = Math.max(v19, flow$WidgetsList2.getHeight());
                if(v20 > 0) {
                    v27 += this.mHorizontalGap;
                }
                v19 = v28;
                v24 = v27;
                constraintAnchor7 = constraintAnchor10;
                v23 = 0;
            }
        }
        arr_v[0] = v24;
        arr_v[1] = v19;
    }

    private void measureNoWrap(ConstraintWidget[] arr_constraintWidget, int v, int v1, int v2, int[] arr_v) {
        WidgetsList flow$WidgetsList0;
        if(v == 0) {
            return;
        }
        if(this.mChainList.size() == 0) {
            flow$WidgetsList0 = new WidgetsList(this, v1, this.mLeft, this.mTop, this.mRight, this.mBottom, v2);
            this.mChainList.add(flow$WidgetsList0);
        }
        else {
            Object object0 = this.mChainList.get(0);
            ((WidgetsList)object0).clear();
            ((WidgetsList)object0).setup(v1, this.mLeft, this.mTop, this.mRight, this.mBottom, this.getPaddingLeft(), this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom(), v2);
            flow$WidgetsList0 = (WidgetsList)object0;
        }
        for(int v3 = 0; v3 < v; ++v3) {
            flow$WidgetsList0.add(arr_constraintWidget[v3]);
        }
        arr_v[0] = flow$WidgetsList0.getWidth();
        arr_v[1] = flow$WidgetsList0.getHeight();
    }

    public void setFirstHorizontalBias(float f) {
        this.mFirstHorizontalBias = f;
    }

    public void setFirstHorizontalStyle(int v) {
        this.mFirstHorizontalStyle = v;
    }

    public void setFirstVerticalBias(float f) {
        this.mFirstVerticalBias = f;
    }

    public void setFirstVerticalStyle(int v) {
        this.mFirstVerticalStyle = v;
    }

    public void setHorizontalAlign(int v) {
        this.mHorizontalAlign = v;
    }

    public void setHorizontalBias(float f) {
        this.mHorizontalBias = f;
    }

    public void setHorizontalGap(int v) {
        this.mHorizontalGap = v;
    }

    public void setHorizontalStyle(int v) {
        this.mHorizontalStyle = v;
    }

    public void setLastHorizontalBias(float f) {
        this.mLastHorizontalBias = f;
    }

    public void setLastHorizontalStyle(int v) {
        this.mLastHorizontalStyle = v;
    }

    public void setLastVerticalBias(float f) {
        this.mLastVerticalBias = f;
    }

    public void setLastVerticalStyle(int v) {
        this.mLastVerticalStyle = v;
    }

    public void setMaxElementsWrap(int v) {
        this.mMaxElementsWrap = v;
    }

    public void setOrientation(int v) {
        this.mOrientation = v;
    }

    public void setVerticalAlign(int v) {
        this.mVerticalAlign = v;
    }

    public void setVerticalBias(float f) {
        this.mVerticalBias = f;
    }

    public void setVerticalGap(int v) {
        this.mVerticalGap = v;
    }

    public void setVerticalStyle(int v) {
        this.mVerticalStyle = v;
    }

    public void setWrapMode(int v) {
        this.mWrapMode = v;
    }
}

