package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.State.Helper;
import androidx.constraintlayout.core.state.State;

public class HorizontalChainReference extends ChainReference {
    public HorizontalChainReference(State state0) {
        super(state0, Helper.HORIZONTAL_CHAIN);
    }

    @Override  // androidx.constraintlayout.core.state.HelperReference
    public void apply() {
        for(Object object0: this.mReferences) {
            this.mHelperState.constraints(object0).clearHorizontal();
        }
        ConstraintReference constraintReference0 = null;
        ConstraintReference constraintReference1 = null;
        for(Object object1: this.mReferences) {
            ConstraintReference constraintReference2 = this.mHelperState.constraints(object1);
            if(constraintReference1 == null) {
                if(this.mStartToStart != null) {
                    constraintReference2.startToStart(this.mStartToStart).margin(this.mMarginStart).marginGone(this.mMarginStartGone);
                }
                else if(this.mStartToEnd != null) {
                    constraintReference2.startToEnd(this.mStartToEnd).margin(this.mMarginStart).marginGone(this.mMarginStartGone);
                }
                else if(this.mLeftToLeft != null) {
                    constraintReference2.startToStart(this.mLeftToLeft).margin(this.mMarginLeft).marginGone(this.mMarginLeftGone);
                }
                else if(this.mLeftToRight == null) {
                    String s = constraintReference2.getKey().toString();
                    constraintReference2.startToStart(State.PARENT).margin(this.getPreMargin(s)).marginGone(this.getPreGoneMargin(s));
                }
                else {
                    constraintReference2.startToEnd(this.mLeftToRight).margin(this.mMarginLeft).marginGone(this.mMarginLeftGone);
                }
                constraintReference1 = constraintReference2;
            }
            if(constraintReference0 != null) {
                String s1 = constraintReference0.getKey().toString();
                String s2 = constraintReference2.getKey().toString();
                constraintReference0.endToStart(constraintReference2.getKey()).margin(this.getPostMargin(s1)).marginGone(this.getPostGoneMargin(s1));
                constraintReference2.startToEnd(constraintReference0.getKey()).margin(this.getPreMargin(s2)).marginGone(this.getPreGoneMargin(s2));
            }
            float f = this.getWeight(object1.toString());
            if(f != -1.0f) {
                constraintReference2.setHorizontalChainWeight(f);
            }
            constraintReference0 = constraintReference2;
        }
        if(constraintReference0 != null) {
            if(this.mEndToStart != null) {
                constraintReference0.endToStart(this.mEndToStart).margin(this.mMarginEnd).marginGone(this.mMarginEndGone);
            }
            else if(this.mEndToEnd != null) {
                constraintReference0.endToEnd(this.mEndToEnd).margin(this.mMarginEnd).marginGone(this.mMarginEndGone);
            }
            else if(this.mRightToLeft != null) {
                constraintReference0.endToStart(this.mRightToLeft).margin(this.mMarginRight).marginGone(this.mMarginRightGone);
            }
            else if(this.mRightToRight == null) {
                String s3 = constraintReference0.getKey().toString();
                constraintReference0.endToEnd(State.PARENT).margin(this.getPostMargin(s3)).marginGone(this.getPostGoneMargin(s3));
            }
            else {
                constraintReference0.endToEnd(this.mRightToRight).margin(this.mMarginRight).marginGone(this.mMarginRightGone);
            }
        }
        if(constraintReference1 != null) {
            if(this.mBias != 0.5f) {
                constraintReference1.horizontalBias(this.mBias);
            }
            switch(this.mStyle) {
                case 1: {
                    constraintReference1.setHorizontalChainStyle(0);
                    return;
                }
                case 2: {
                    constraintReference1.setHorizontalChainStyle(1);
                    return;
                }
                case 3: {
                    constraintReference1.setHorizontalChainStyle(2);
                }
            }
        }
    }
}

