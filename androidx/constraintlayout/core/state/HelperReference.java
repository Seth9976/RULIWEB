package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.state.helpers.Facade;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;
import java.util.Collections;

public class HelperReference extends ConstraintReference implements Facade {
    protected final State mHelperState;
    private HelperWidget mHelperWidget;
    protected ArrayList mReferences;
    final Helper mType;

    public HelperReference(State state0, Helper state$Helper0) {
        super(state0);
        this.mReferences = new ArrayList();
        this.mHelperState = state0;
        this.mType = state$Helper0;
    }

    public HelperReference add(Object[] arr_object) {
        Collections.addAll(this.mReferences, arr_object);
        return this;
    }

    @Override  // androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.helpers.Facade
    public void apply() {
    }

    public void applyBase() {
        super.apply();
    }

    @Override  // androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.helpers.Facade
    public ConstraintWidget getConstraintWidget() {
        return this.getHelperWidget();
    }

    public HelperWidget getHelperWidget() {
        return this.mHelperWidget;
    }

    public Helper getType() {
        return this.mType;
    }

    public void setHelperWidget(HelperWidget helperWidget0) {
        this.mHelperWidget = helperWidget0;
    }
}

