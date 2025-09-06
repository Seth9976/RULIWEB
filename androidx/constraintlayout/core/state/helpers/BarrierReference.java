package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State.Direction;
import androidx.constraintlayout.core.state.State.Helper;
import androidx.constraintlayout.core.state.State;
import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.HelperWidget;

public class BarrierReference extends HelperReference {
    private Barrier mBarrierWidget;
    private Direction mDirection;
    private int mMargin;

    public BarrierReference(State state0) {
        super(state0, Helper.BARRIER);
    }

    @Override  // androidx.constraintlayout.core.state.HelperReference
    public void apply() {
        this.getHelperWidget();
        int v = 3;
        switch(this.mDirection) {
            case 3: 
            case 4: {
                v = 1;
                break;
            }
            case 5: {
                v = 2;
                break;
            }
            case 6: {
                break;
            }
            default: {
                v = 0;
            }
        }
        this.mBarrierWidget.setBarrierType(v);
        this.mBarrierWidget.setMargin(this.mMargin);
    }

    @Override  // androidx.constraintlayout.core.state.HelperReference
    public HelperWidget getHelperWidget() {
        if(this.mBarrierWidget == null) {
            this.mBarrierWidget = new Barrier();
        }
        return this.mBarrierWidget;
    }

    @Override  // androidx.constraintlayout.core.state.ConstraintReference
    public ConstraintReference margin(int v) {
        this.mMargin = v;
        return this;
    }

    @Override  // androidx.constraintlayout.core.state.ConstraintReference
    public ConstraintReference margin(Object object0) {
        this.margin(this.mHelperState.convertDimension(object0));
        return this;
    }

    public void setBarrierDirection(Direction state$Direction0) {
        this.mDirection = state$Direction0;
    }
}

