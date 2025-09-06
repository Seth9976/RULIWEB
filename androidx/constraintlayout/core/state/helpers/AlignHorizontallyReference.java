package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State.Helper;
import androidx.constraintlayout.core.state.State;

public class AlignHorizontallyReference extends HelperReference {
    private float mBias;

    public AlignHorizontallyReference(State state0) {
        super(state0, Helper.ALIGN_VERTICALLY);
        this.mBias = 0.5f;
    }

    @Override  // androidx.constraintlayout.core.state.HelperReference
    public void apply() {
        for(Object object0: this.mReferences) {
            ConstraintReference constraintReference0 = this.mHelperState.constraints(object0);
            constraintReference0.clearHorizontal();
            if(this.mStartToStart != null) {
                constraintReference0.startToStart(this.mStartToStart);
            }
            else if(this.mStartToEnd == null) {
                constraintReference0.startToStart(State.PARENT);
            }
            else {
                constraintReference0.startToEnd(this.mStartToEnd);
            }
            if(this.mEndToStart != null) {
                constraintReference0.endToStart(this.mEndToStart);
            }
            else if(this.mEndToEnd == null) {
                constraintReference0.endToEnd(State.PARENT);
            }
            else {
                constraintReference0.endToEnd(this.mEndToEnd);
            }
            float f = this.mBias;
            if(f != 0.5f) {
                constraintReference0.horizontalBias(f);
            }
        }
    }
}

