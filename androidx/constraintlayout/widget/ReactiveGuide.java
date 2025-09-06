package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;

public class ReactiveGuide extends View implements SharedValuesListener {
    private boolean mAnimateChange;
    private boolean mApplyToAllConstraintSets;
    private int mApplyToConstraintSetId;
    private int mAttributeId;

    public ReactiveGuide(Context context0) {
        super(context0);
        this.mAttributeId = -1;
        this.mAnimateChange = false;
        this.mApplyToConstraintSetId = 0;
        this.mApplyToAllConstraintSets = true;
        super.setVisibility(8);
        this.init(null);
    }

    public ReactiveGuide(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mAttributeId = -1;
        this.mAnimateChange = false;
        this.mApplyToConstraintSetId = 0;
        this.mApplyToAllConstraintSets = true;
        super.setVisibility(8);
        this.init(attributeSet0);
    }

    public ReactiveGuide(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mAttributeId = -1;
        this.mAnimateChange = false;
        this.mApplyToConstraintSetId = 0;
        this.mApplyToAllConstraintSets = true;
        super.setVisibility(8);
        this.init(attributeSet0);
    }

    public ReactiveGuide(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v);
        this.mAttributeId = -1;
        this.mAnimateChange = false;
        this.mApplyToConstraintSetId = 0;
        this.mApplyToAllConstraintSets = true;
        super.setVisibility(8);
        this.init(attributeSet0);
    }

    private void changeValue(int v, int v1, MotionLayout motionLayout0, int v2) {
        ConstraintSet constraintSet0 = motionLayout0.getConstraintSet(v2);
        constraintSet0.setGuidelineEnd(v1, v);
        motionLayout0.updateState(v2, constraintSet0);
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
    }

    public int getApplyToConstraintSetId() {
        return this.mApplyToConstraintSetId;
    }

    public int getAttributeId() {
        return this.mAttributeId;
    }

    private void init(AttributeSet attributeSet0) {
        if(attributeSet0 != null) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.ConstraintLayout_ReactiveGuide);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.ConstraintLayout_ReactiveGuide_reactiveGuide_valueId) {
                    this.mAttributeId = typedArray0.getResourceId(v2, this.mAttributeId);
                }
                else if(v2 == styleable.ConstraintLayout_ReactiveGuide_reactiveGuide_animateChange) {
                    this.mAnimateChange = typedArray0.getBoolean(v2, this.mAnimateChange);
                }
                else if(v2 == styleable.ConstraintLayout_ReactiveGuide_reactiveGuide_applyToConstraintSet) {
                    this.mApplyToConstraintSetId = typedArray0.getResourceId(v2, this.mApplyToConstraintSetId);
                }
                else if(v2 == styleable.ConstraintLayout_ReactiveGuide_reactiveGuide_applyToAllConstraintSets) {
                    this.mApplyToAllConstraintSets = typedArray0.getBoolean(v2, this.mApplyToAllConstraintSets);
                }
            }
            typedArray0.recycle();
        }
        if(this.mAttributeId != -1) {
            ConstraintLayout.getSharedValues().addListener(this.mAttributeId, this);
        }
    }

    public boolean isAnimatingChange() {
        return this.mAnimateChange;
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        this.setMeasuredDimension(0, 0);
    }

    @Override  // androidx.constraintlayout.widget.SharedValues$SharedValuesListener
    public void onNewValue(int v, int v1, int v2) {
        this.setGuidelineBegin(v1);
        int v3 = this.getId();
        if(v3 > 0 && this.getParent() instanceof MotionLayout) {
            MotionLayout motionLayout0 = (MotionLayout)this.getParent();
            int v4 = motionLayout0.getCurrentState();
            int v5 = this.mApplyToConstraintSetId;
            if(v5 != 0) {
                v4 = v5;
            }
            int v6 = 0;
            if(this.mAnimateChange) {
                if(this.mApplyToAllConstraintSets) {
                    int[] arr_v = motionLayout0.getConstraintSetIds();
                    while(v6 < arr_v.length) {
                        int v7 = arr_v[v6];
                        if(v7 != v4) {
                            this.changeValue(v1, v3, motionLayout0, v7);
                        }
                        ++v6;
                    }
                }
                ConstraintSet constraintSet0 = motionLayout0.cloneConstraintSet(v4);
                constraintSet0.setGuidelineEnd(v3, v1);
                motionLayout0.updateStateAnimate(v4, constraintSet0, 1000);
                return;
            }
            if(this.mApplyToAllConstraintSets) {
                int[] arr_v1 = motionLayout0.getConstraintSetIds();
                while(v6 < arr_v1.length) {
                    this.changeValue(v1, v3, motionLayout0, arr_v1[v6]);
                    ++v6;
                }
                return;
            }
            this.changeValue(v1, v3, motionLayout0, v4);
        }
    }

    public void setAnimateChange(boolean z) {
        this.mAnimateChange = z;
    }

    public void setApplyToConstraintSetId(int v) {
        this.mApplyToConstraintSetId = v;
    }

    public void setAttributeId(int v) {
        SharedValues sharedValues0 = ConstraintLayout.getSharedValues();
        int v1 = this.mAttributeId;
        if(v1 != -1) {
            sharedValues0.removeListener(v1, this);
        }
        this.mAttributeId = v;
        if(v != -1) {
            sharedValues0.addListener(v, this);
        }
    }

    public void setGuidelineBegin(int v) {
        LayoutParams constraintLayout$LayoutParams0 = (LayoutParams)this.getLayoutParams();
        constraintLayout$LayoutParams0.guideBegin = v;
        this.setLayoutParams(constraintLayout$LayoutParams0);
    }

    public void setGuidelineEnd(int v) {
        LayoutParams constraintLayout$LayoutParams0 = (LayoutParams)this.getLayoutParams();
        constraintLayout$LayoutParams0.guideEnd = v;
        this.setLayoutParams(constraintLayout$LayoutParams0);
    }

    public void setGuidelinePercent(float f) {
        LayoutParams constraintLayout$LayoutParams0 = (LayoutParams)this.getLayoutParams();
        constraintLayout$LayoutParams0.guidePercent = f;
        this.setLayoutParams(constraintLayout$LayoutParams0);
    }

    @Override  // android.view.View
    public void setVisibility(int v) {
    }
}

