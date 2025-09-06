package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.Reference;
import androidx.constraintlayout.core.state.State;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;

public class GuidelineReference implements Reference, Facade {
    private int mEnd;
    private Guideline mGuidelineWidget;
    private Object mKey;
    private int mOrientation;
    private float mPercent;
    private int mStart;
    final State mState;

    public GuidelineReference(State state0) {
        this.mStart = -1;
        this.mEnd = -1;
        this.mPercent = 0.0f;
        this.mState = state0;
    }

    @Override  // androidx.constraintlayout.core.state.helpers.Facade, androidx.constraintlayout.core.state.Reference
    public void apply() {
        this.mGuidelineWidget.setOrientation(this.mOrientation);
        int v = this.mStart;
        if(v != -1) {
            this.mGuidelineWidget.setGuideBegin(v);
            return;
        }
        int v1 = this.mEnd;
        if(v1 != -1) {
            this.mGuidelineWidget.setGuideEnd(v1);
            return;
        }
        this.mGuidelineWidget.setGuidePercent(this.mPercent);
    }

    public GuidelineReference end(Object object0) {
        this.mStart = -1;
        this.mEnd = this.mState.convertDimension(object0);
        this.mPercent = 0.0f;
        return this;
    }

    @Override  // androidx.constraintlayout.core.state.helpers.Facade, androidx.constraintlayout.core.state.Reference
    public ConstraintWidget getConstraintWidget() {
        if(this.mGuidelineWidget == null) {
            this.mGuidelineWidget = new Guideline();
        }
        return this.mGuidelineWidget;
    }

    @Override  // androidx.constraintlayout.core.state.Reference
    public Facade getFacade() {
        return null;
    }

    @Override  // androidx.constraintlayout.core.state.Reference
    public Object getKey() {
        return this.mKey;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public GuidelineReference percent(float f) {
        this.mStart = -1;
        this.mEnd = -1;
        this.mPercent = f;
        return this;
    }

    @Override  // androidx.constraintlayout.core.state.Reference
    public void setConstraintWidget(ConstraintWidget constraintWidget0) {
        if(constraintWidget0 instanceof Guideline) {
            this.mGuidelineWidget = (Guideline)constraintWidget0;
            return;
        }
        this.mGuidelineWidget = null;
    }

    @Override  // androidx.constraintlayout.core.state.Reference
    public void setKey(Object object0) {
        this.mKey = object0;
    }

    public void setOrientation(int v) {
        this.mOrientation = v;
    }

    public GuidelineReference start(Object object0) {
        this.mStart = this.mState.convertDimension(object0);
        this.mEnd = -1;
        this.mPercent = 0.0f;
        return this;
    }
}

