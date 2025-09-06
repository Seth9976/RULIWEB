package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State.Helper;
import androidx.constraintlayout.core.state.State;
import androidx.constraintlayout.core.widgets.Flow;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.HashMap;

public class FlowReference extends HelperReference {
    protected float mFirstHorizontalBias;
    protected int mFirstHorizontalStyle;
    protected float mFirstVerticalBias;
    protected int mFirstVerticalStyle;
    protected Flow mFlow;
    protected int mHorizontalAlign;
    protected int mHorizontalGap;
    protected int mHorizontalStyle;
    protected float mLastHorizontalBias;
    protected int mLastHorizontalStyle;
    protected float mLastVerticalBias;
    protected int mLastVerticalStyle;
    protected HashMap mMapPostMargin;
    protected HashMap mMapPreMargin;
    protected HashMap mMapWeights;
    protected int mMaxElementsWrap;
    protected int mOrientation;
    protected int mPaddingBottom;
    protected int mPaddingLeft;
    protected int mPaddingRight;
    protected int mPaddingTop;
    protected int mVerticalAlign;
    protected int mVerticalGap;
    protected int mVerticalStyle;
    protected int mWrapMode;

    public FlowReference(State state0, Helper state$Helper0) {
        super(state0, state$Helper0);
        this.mWrapMode = 0;
        this.mVerticalStyle = -1;
        this.mFirstVerticalStyle = -1;
        this.mLastVerticalStyle = -1;
        this.mHorizontalStyle = -1;
        this.mFirstHorizontalStyle = -1;
        this.mLastHorizontalStyle = -1;
        this.mVerticalAlign = 2;
        this.mHorizontalAlign = 2;
        this.mVerticalGap = 0;
        this.mHorizontalGap = 0;
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mMaxElementsWrap = -1;
        this.mOrientation = 0;
        this.mFirstVerticalBias = 0.5f;
        this.mLastVerticalBias = 0.5f;
        this.mFirstHorizontalBias = 0.5f;
        this.mLastHorizontalBias = 0.5f;
        if(state$Helper0 == Helper.VERTICAL_FLOW) {
            this.mOrientation = 1;
        }
    }

    public void addFlowElement(String s, float f, float f1, float f2) {
        super.add(new Object[]{s});
        if(!Float.isNaN(f)) {
            if(this.mMapWeights == null) {
                this.mMapWeights = new HashMap();
            }
            this.mMapWeights.put(s, f);
        }
        if(!Float.isNaN(f1)) {
            if(this.mMapPreMargin == null) {
                this.mMapPreMargin = new HashMap();
            }
            this.mMapPreMargin.put(s, f1);
        }
        if(!Float.isNaN(f2)) {
            if(this.mMapPostMargin == null) {
                this.mMapPostMargin = new HashMap();
            }
            this.mMapPostMargin.put(s, f2);
        }
    }

    @Override  // androidx.constraintlayout.core.state.HelperReference
    public void apply() {
        this.getHelperWidget();
        this.setConstraintWidget(this.mFlow);
        this.mFlow.setOrientation(this.mOrientation);
        this.mFlow.setWrapMode(this.mWrapMode);
        int v = this.mMaxElementsWrap;
        if(v != -1) {
            this.mFlow.setMaxElementsWrap(v);
        }
        int v1 = this.mPaddingLeft;
        if(v1 != 0) {
            this.mFlow.setPaddingLeft(v1);
        }
        int v2 = this.mPaddingTop;
        if(v2 != 0) {
            this.mFlow.setPaddingTop(v2);
        }
        int v3 = this.mPaddingRight;
        if(v3 != 0) {
            this.mFlow.setPaddingRight(v3);
        }
        int v4 = this.mPaddingBottom;
        if(v4 != 0) {
            this.mFlow.setPaddingBottom(v4);
        }
        int v5 = this.mHorizontalGap;
        if(v5 != 0) {
            this.mFlow.setHorizontalGap(v5);
        }
        int v6 = this.mVerticalGap;
        if(v6 != 0) {
            this.mFlow.setVerticalGap(v6);
        }
        if(this.mHorizontalBias != 0.5f) {
            this.mFlow.setHorizontalBias(this.mHorizontalBias);
        }
        float f = this.mFirstHorizontalBias;
        if(f != 0.5f) {
            this.mFlow.setFirstHorizontalBias(f);
        }
        float f1 = this.mLastHorizontalBias;
        if(f1 != 0.5f) {
            this.mFlow.setLastHorizontalBias(f1);
        }
        if(this.mVerticalBias != 0.5f) {
            this.mFlow.setVerticalBias(this.mVerticalBias);
        }
        float f2 = this.mFirstVerticalBias;
        if(f2 != 0.5f) {
            this.mFlow.setFirstVerticalBias(f2);
        }
        float f3 = this.mLastVerticalBias;
        if(f3 != 0.5f) {
            this.mFlow.setLastVerticalBias(f3);
        }
        int v7 = this.mHorizontalAlign;
        if(v7 != 2) {
            this.mFlow.setHorizontalAlign(v7);
        }
        int v8 = this.mVerticalAlign;
        if(v8 != 2) {
            this.mFlow.setVerticalAlign(v8);
        }
        int v9 = this.mVerticalStyle;
        if(v9 != -1) {
            this.mFlow.setVerticalStyle(v9);
        }
        int v10 = this.mFirstVerticalStyle;
        if(v10 != -1) {
            this.mFlow.setFirstVerticalStyle(v10);
        }
        int v11 = this.mLastVerticalStyle;
        if(v11 != -1) {
            this.mFlow.setLastVerticalStyle(v11);
        }
        int v12 = this.mHorizontalStyle;
        if(v12 != -1) {
            this.mFlow.setHorizontalStyle(v12);
        }
        int v13 = this.mFirstHorizontalStyle;
        if(v13 != -1) {
            this.mFlow.setFirstHorizontalStyle(v13);
        }
        int v14 = this.mLastHorizontalStyle;
        if(v14 != -1) {
            this.mFlow.setLastHorizontalStyle(v14);
        }
        this.applyBase();
    }

    public float getFirstHorizontalBias() {
        return this.mFirstHorizontalBias;
    }

    public int getFirstHorizontalStyle() {
        return this.mFirstHorizontalStyle;
    }

    public float getFirstVerticalBias() {
        return this.mFirstVerticalBias;
    }

    public int getFirstVerticalStyle() {
        return this.mFirstVerticalStyle;
    }

    @Override  // androidx.constraintlayout.core.state.HelperReference
    public HelperWidget getHelperWidget() {
        if(this.mFlow == null) {
            this.mFlow = new Flow();
        }
        return this.mFlow;
    }

    public int getHorizontalAlign() {
        return this.mHorizontalAlign;
    }

    public float getHorizontalBias() {
        return this.mHorizontalBias;
    }

    public int getHorizontalGap() {
        return this.mHorizontalGap;
    }

    public int getHorizontalStyle() {
        return this.mHorizontalStyle;
    }

    public float getLastHorizontalBias() {
        return this.mLastHorizontalBias;
    }

    public int getLastHorizontalStyle() {
        return this.mLastHorizontalStyle;
    }

    public float getLastVerticalBias() {
        return this.mLastVerticalBias;
    }

    public int getLastVerticalStyle() {
        return this.mLastVerticalStyle;
    }

    public int getMaxElementsWrap() {
        return this.mMaxElementsWrap;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public int getPaddingRight() {
        return this.mPaddingRight;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    protected float getPostMargin(String s) {
        return this.mMapPreMargin == null || !this.mMapPreMargin.containsKey(s) ? 0.0f : ((float)(((Float)this.mMapPreMargin.get(s))));
    }

    protected float getPreMargin(String s) {
        return this.mMapPostMargin == null || !this.mMapPostMargin.containsKey(s) ? 0.0f : ((float)(((Float)this.mMapPostMargin.get(s))));
    }

    public int getVerticalAlign() {
        return this.mVerticalAlign;
    }

    public float getVerticalBias() {
        return this.mVerticalBias;
    }

    public int getVerticalGap() {
        return this.mVerticalGap;
    }

    public int getVerticalStyle() {
        return this.mVerticalStyle;
    }

    protected float getWeight(String s) {
        HashMap hashMap0 = this.mMapWeights;
        if(hashMap0 == null) {
            return -1.0f;
        }
        return hashMap0.containsKey(s) ? ((float)(((Float)this.mMapWeights.get(s)))) : -1.0f;
    }

    public int getWrapMode() {
        return this.mWrapMode;
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

    @Override  // androidx.constraintlayout.core.state.HelperReference
    public void setHelperWidget(HelperWidget helperWidget0) {
        if(helperWidget0 instanceof Flow) {
            this.mFlow = (Flow)helperWidget0;
            return;
        }
        this.mFlow = null;
    }

    public void setHorizontalAlign(int v) {
        this.mHorizontalAlign = v;
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

    public void setPaddingBottom(int v) {
        this.mPaddingBottom = v;
    }

    public void setPaddingLeft(int v) {
        this.mPaddingLeft = v;
    }

    public void setPaddingRight(int v) {
        this.mPaddingRight = v;
    }

    public void setPaddingTop(int v) {
        this.mPaddingTop = v;
    }

    public void setVerticalAlign(int v) {
        this.mVerticalAlign = v;
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

