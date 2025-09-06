package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.State.Helper;
import androidx.constraintlayout.core.state.State;

public class VerticalChainReference extends ChainReference {
    public VerticalChainReference(State state0) {
        super(state0, Helper.VERTICAL_CHAIN);
    }

    @Override  // androidx.constraintlayout.core.state.HelperReference
    public void apply() {
        for(Object object0: this.mReferences) {
            this.mHelperState.constraints(object0).clearVertical();
        }
        ConstraintReference constraintReference0 = null;
        ConstraintReference constraintReference1 = null;
        for(Object object1: this.mReferences) {
            ConstraintReference constraintReference2 = this.mHelperState.constraints(object1);
            if(constraintReference1 == null) {
                if(this.mTopToTop != null) {
                    constraintReference2.topToTop(this.mTopToTop).margin(this.mMarginTop).marginGone(this.mMarginTopGone);
                }
                else if(this.mTopToBottom == null) {
                    String s = constraintReference2.getKey().toString();
                    constraintReference2.topToTop(State.PARENT).margin(this.getPreMargin(s)).marginGone(this.getPreGoneMargin(s));
                }
                else {
                    constraintReference2.topToBottom(this.mTopToBottom).margin(this.mMarginTop).marginGone(this.mMarginTopGone);
                }
                constraintReference1 = constraintReference2;
            }
            if(constraintReference0 != null) {
                String s1 = constraintReference0.getKey().toString();
                String s2 = constraintReference2.getKey().toString();
                constraintReference0.bottomToTop(constraintReference2.getKey()).margin(this.getPostMargin(s1)).marginGone(this.getPostGoneMargin(s1));
                constraintReference2.topToBottom(constraintReference0.getKey()).margin(this.getPreMargin(s2)).marginGone(this.getPreGoneMargin(s2));
            }
            float f = this.getWeight(object1.toString());
            if(f != -1.0f) {
                constraintReference2.setVerticalChainWeight(f);
            }
            constraintReference0 = constraintReference2;
        }
        if(constraintReference0 != null) {
            if(this.mBottomToTop != null) {
                constraintReference0.bottomToTop(this.mBottomToTop).margin(this.mMarginBottom).marginGone(this.mMarginBottomGone);
            }
            else if(this.mBottomToBottom == null) {
                String s3 = constraintReference0.getKey().toString();
                constraintReference0.bottomToBottom(State.PARENT).margin(this.getPostMargin(s3)).marginGone(this.getPostGoneMargin(s3));
            }
            else {
                constraintReference0.bottomToBottom(this.mBottomToBottom).margin(this.mMarginBottom).marginGone(this.mMarginBottomGone);
            }
        }
        if(constraintReference1 != null) {
            if(this.mBias != 0.5f) {
                constraintReference1.verticalBias(this.mBias);
            }
            switch(this.mStyle) {
                case 1: {
                    constraintReference1.setVerticalChainStyle(0);
                    return;
                }
                case 2: {
                    constraintReference1.setVerticalChainStyle(1);
                    return;
                }
                case 3: {
                    constraintReference1.setVerticalChainStyle(2);
                }
            }
        }
    }
}

