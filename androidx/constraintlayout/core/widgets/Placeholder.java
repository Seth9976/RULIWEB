package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;

public class Placeholder extends VirtualLayout {
    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem0, boolean z) {
        super.addToSolver(linearSystem0, z);
        if(this.mWidgetsCount > 0) {
            ConstraintWidget constraintWidget0 = this.mWidgets[0];
            constraintWidget0.resetAllConstraints();
            constraintWidget0.connect(Type.LEFT, this, Type.LEFT);
            constraintWidget0.connect(Type.RIGHT, this, Type.RIGHT);
            constraintWidget0.connect(Type.TOP, this, Type.TOP);
            constraintWidget0.connect(Type.BOTTOM, this, Type.BOTTOM);
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.VirtualLayout
    public void measure(int v, int v1, int v2, int v3) {
        int v4 = this.getPaddingLeft() + this.getPaddingRight();
        int v5 = this.getPaddingTop() + this.getPaddingBottom();
        boolean z = false;
        if(this.mWidgetsCount > 0) {
            v4 += this.mWidgets[0].getWidth();
            v5 += this.mWidgets[0].getHeight();
        }
        int v6 = Math.max(this.getMinWidth(), v4);
        int v7 = Math.max(this.getMinHeight(), v5);
        switch(v) {
            case 0x80000000: {
                v1 = Math.min(v6, v1);
                break;
            }
            case 0: {
                v1 = v6;
                break;
            }
            case 0x40000000: {
                break;
            }
            default: {
                v1 = 0;
            }
        }
        switch(v2) {
            case 0x80000000: {
                v3 = Math.min(v7, v3);
                break;
            }
            case 0: {
                v3 = v7;
                break;
            }
            case 0x40000000: {
                break;
            }
            default: {
                v3 = 0;
            }
        }
        this.setMeasure(v1, v3);
        this.setWidth(v1);
        this.setHeight(v3);
        if(this.mWidgetsCount > 0) {
            z = true;
        }
        this.needsCallbackFromSolver(z);
    }
}

