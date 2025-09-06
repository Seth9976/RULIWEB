package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State.Helper;
import androidx.constraintlayout.core.state.State;

public class AlignVerticallyReference extends HelperReference {
    private float mBias;

    public AlignVerticallyReference(State state0) {
        super(state0, Helper.ALIGN_VERTICALLY);
        this.mBias = 0.5f;
    }

    @Override  // androidx.constraintlayout.core.state.HelperReference
    public void apply() {
        for(Object object0: this.mReferences) {
            ConstraintReference constraintReference0 = this.mHelperState.constraints(object0);
            constraintReference0.clearVertical();
            if(this.mTopToTop != null) {
                constraintReference0.topToTop(this.mTopToTop);
            }
            else if(this.mTopToBottom == null) {
                constraintReference0.topToTop(State.PARENT);
            }
            else {
                constraintReference0.topToBottom(this.mTopToBottom);
            }
            if(this.mBottomToTop != null) {
                constraintReference0.bottomToTop(this.mBottomToTop);
            }
            else if(this.mBottomToBottom == null) {
                constraintReference0.bottomToBottom(State.PARENT);
            }
            else {
                constraintReference0.bottomToBottom(this.mBottomToBottom);
            }
            float f = this.mBias;
            if(f != 0.5f) {
                constraintReference0.verticalBias(f);
            }
        }
    }
}

