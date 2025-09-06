package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.Chain;
import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;

public class WidgetGroup {
    static class MeasureResult {
        int mBaseline;
        int mBottom;
        int mLeft;
        int mOrientation;
        int mRight;
        int mTop;
        WeakReference mWidgetRef;

        MeasureResult(ConstraintWidget constraintWidget0, LinearSystem linearSystem0, int v) {
            this.mWidgetRef = new WeakReference(constraintWidget0);
            this.mLeft = linearSystem0.getObjectVariableValue(constraintWidget0.mLeft);
            this.mTop = linearSystem0.getObjectVariableValue(constraintWidget0.mTop);
            this.mRight = linearSystem0.getObjectVariableValue(constraintWidget0.mRight);
            this.mBottom = linearSystem0.getObjectVariableValue(constraintWidget0.mBottom);
            this.mBaseline = linearSystem0.getObjectVariableValue(constraintWidget0.mBaseline);
            this.mOrientation = v;
        }

        public void apply() {
            Object object0 = this.mWidgetRef.get();
            if(((ConstraintWidget)object0) != null) {
                ((ConstraintWidget)object0).setFinalFrame(this.mLeft, this.mTop, this.mRight, this.mBottom, this.mBaseline, this.mOrientation);
            }
        }
    }

    private static final boolean DEBUG = false;
    boolean mAuthoritative;
    int mId;
    private int mMoveTo;
    int mOrientation;
    ArrayList mResults;
    ArrayList mWidgets;
    static int sCount;

    static {
    }

    public WidgetGroup(int v) {
        this.mWidgets = new ArrayList();
        this.mAuthoritative = false;
        this.mResults = null;
        this.mMoveTo = -1;
        int v1 = WidgetGroup.sCount;
        WidgetGroup.sCount = v1 + 1;
        this.mId = v1;
        this.mOrientation = v;
    }

    public boolean add(ConstraintWidget constraintWidget0) {
        if(this.mWidgets.contains(constraintWidget0)) {
            return false;
        }
        this.mWidgets.add(constraintWidget0);
        return true;
    }

    public void apply() {
        if(this.mResults != null && this.mAuthoritative) {
            for(int v = 0; v < this.mResults.size(); ++v) {
                ((MeasureResult)this.mResults.get(v)).apply();
            }
        }
    }

    public void cleanup(ArrayList arrayList0) {
        int v = this.mWidgets.size();
        if(this.mMoveTo != -1 && v > 0) {
            for(int v1 = 0; v1 < arrayList0.size(); ++v1) {
                WidgetGroup widgetGroup0 = (WidgetGroup)arrayList0.get(v1);
                if(this.mMoveTo == widgetGroup0.mId) {
                    this.moveTo(this.mOrientation, widgetGroup0);
                }
            }
        }
        if(v == 0) {
            arrayList0.remove(this);
        }
    }

    public void clear() {
        this.mWidgets.clear();
    }

    private boolean contains(ConstraintWidget constraintWidget0) {
        return this.mWidgets.contains(constraintWidget0);
    }

    public int getId() {
        return this.mId;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    private String getOrientationString() {
        int v = this.mOrientation;
        if(v == 0) {
            return "Horizontal";
        }
        switch(v) {
            case 1: {
                return "Vertical";
            }
            case 2: {
                return "Both";
            }
            default: {
                return "Unknown";
            }
        }
    }

    public boolean intersectWith(WidgetGroup widgetGroup0) {
        for(int v = 0; v < this.mWidgets.size(); ++v) {
            if(widgetGroup0.contains(((ConstraintWidget)this.mWidgets.get(v)))) {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthoritative() {
        return this.mAuthoritative;
    }

    private int measureWrap(int v, ConstraintWidget constraintWidget0) {
        DimensionBehaviour constraintWidget$DimensionBehaviour0 = constraintWidget0.getDimensionBehaviour(v);
        if(constraintWidget$DimensionBehaviour0 != DimensionBehaviour.WRAP_CONTENT && constraintWidget$DimensionBehaviour0 != DimensionBehaviour.MATCH_PARENT && constraintWidget$DimensionBehaviour0 != DimensionBehaviour.FIXED) {
            return -1;
        }
        return v == 0 ? constraintWidget0.getWidth() : constraintWidget0.getHeight();
    }

    public int measureWrap(LinearSystem linearSystem0, int v) {
        return this.mWidgets.size() == 0 ? 0 : this.solverMeasure(linearSystem0, this.mWidgets, v);
    }

    public void moveTo(int v, WidgetGroup widgetGroup0) {
        for(Object object0: this.mWidgets) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)object0;
            widgetGroup0.add(constraintWidget0);
            if(v == 0) {
                constraintWidget0.horizontalGroup = widgetGroup0.getId();
            }
            else {
                constraintWidget0.verticalGroup = widgetGroup0.getId();
            }
        }
        this.mMoveTo = widgetGroup0.mId;
    }

    public void setAuthoritative(boolean z) {
        this.mAuthoritative = z;
    }

    public void setOrientation(int v) {
        this.mOrientation = v;
    }

    public int size() {
        return this.mWidgets.size();
    }

    private int solverMeasure(LinearSystem linearSystem0, ArrayList arrayList0, int v) {
        int v4;
        int v3;
        ConstraintWidgetContainer constraintWidgetContainer0 = (ConstraintWidgetContainer)((ConstraintWidget)arrayList0.get(0)).getParent();
        linearSystem0.reset();
        constraintWidgetContainer0.addToSolver(linearSystem0, false);
        for(int v2 = 0; v2 < arrayList0.size(); ++v2) {
            ((ConstraintWidget)arrayList0.get(v2)).addToSolver(linearSystem0, false);
        }
        if(v == 0 && constraintWidgetContainer0.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer0, linearSystem0, arrayList0, 0);
        }
        if(v == 1 && constraintWidgetContainer0.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer0, linearSystem0, arrayList0, 1);
        }
        try {
            linearSystem0.minimize();
        }
        catch(Exception exception0) {
            System.err.println(exception0.toString() + "\n" + Arrays.toString(exception0.getStackTrace()).replace("[", "   at ").replace(",", "\n   at").replace("]", ""));
        }
        this.mResults = new ArrayList();
        for(int v1 = 0; v1 < arrayList0.size(); ++v1) {
            MeasureResult widgetGroup$MeasureResult0 = new MeasureResult(((ConstraintWidget)arrayList0.get(v1)), linearSystem0, v);
            this.mResults.add(widgetGroup$MeasureResult0);
        }
        if(v == 0) {
            v3 = linearSystem0.getObjectVariableValue(constraintWidgetContainer0.mLeft);
            v4 = linearSystem0.getObjectVariableValue(constraintWidgetContainer0.mRight);
            linearSystem0.reset();
            return v4 - v3;
        }
        v3 = linearSystem0.getObjectVariableValue(constraintWidgetContainer0.mTop);
        v4 = linearSystem0.getObjectVariableValue(constraintWidgetContainer0.mBottom);
        linearSystem0.reset();
        return v4 - v3;
    }

    @Override
    public String toString() {
        String s = this.getOrientationString() + " [" + this.mId + "] <";
        for(Object object0: this.mWidgets) {
            s = s + " " + ((ConstraintWidget)object0).getDebugName();
        }
        return s + " >";
    }
}

