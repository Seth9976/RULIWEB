package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.analyzer.Grouping;
import androidx.constraintlayout.core.widgets.analyzer.WidgetGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ConstraintAnchor {
    public static enum Type {
        NONE,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        BASELINE,
        CENTER,
        CENTER_X,
        CENTER_Y;

        private static Type[] $values() [...] // Inlined contents
    }

    private static final boolean ALLOW_BINARY = false;
    private static final int UNSET_GONE_MARGIN = 0x80000000;
    private HashSet mDependents;
    private int mFinalValue;
    int mGoneMargin;
    private boolean mHasFinalValue;
    public int mMargin;
    public final ConstraintWidget mOwner;
    SolverVariable mSolverVariable;
    public ConstraintAnchor mTarget;
    public final Type mType;

    public ConstraintAnchor(ConstraintWidget constraintWidget0, Type constraintAnchor$Type0) {
        this.mDependents = null;
        this.mMargin = 0;
        this.mGoneMargin = 0x80000000;
        this.mOwner = constraintWidget0;
        this.mType = constraintAnchor$Type0;
    }

    public boolean connect(ConstraintAnchor constraintAnchor0, int v) {
        return this.connect(constraintAnchor0, v, 0x80000000, false);
    }

    public boolean connect(ConstraintAnchor constraintAnchor0, int v, int v1, boolean z) {
        if(constraintAnchor0 == null) {
            this.reset();
            return true;
        }
        if(!z && !this.isValidConnection(constraintAnchor0)) {
            return false;
        }
        this.mTarget = constraintAnchor0;
        if(constraintAnchor0.mDependents == null) {
            constraintAnchor0.mDependents = new HashSet();
        }
        HashSet hashSet0 = this.mTarget.mDependents;
        if(hashSet0 != null) {
            hashSet0.add(this);
        }
        this.mMargin = v;
        this.mGoneMargin = v1;
        return true;
    }

    public void copyFrom(ConstraintAnchor constraintAnchor0, HashMap hashMap0) {
        ConstraintAnchor constraintAnchor1 = this.mTarget;
        if(constraintAnchor1 != null) {
            HashSet hashSet0 = constraintAnchor1.mDependents;
            if(hashSet0 != null) {
                hashSet0.remove(this);
            }
        }
        ConstraintAnchor constraintAnchor2 = constraintAnchor0.mTarget;
        this.mTarget = constraintAnchor2 == null ? null : ((ConstraintWidget)hashMap0.get(constraintAnchor0.mTarget.mOwner)).getAnchor(constraintAnchor2.getType());
        ConstraintAnchor constraintAnchor3 = this.mTarget;
        if(constraintAnchor3 != null) {
            if(constraintAnchor3.mDependents == null) {
                constraintAnchor3.mDependents = new HashSet();
            }
            this.mTarget.mDependents.add(this);
        }
        this.mMargin = constraintAnchor0.mMargin;
        this.mGoneMargin = constraintAnchor0.mGoneMargin;
    }

    public void findDependents(int v, ArrayList arrayList0, WidgetGroup widgetGroup0) {
        HashSet hashSet0 = this.mDependents;
        if(hashSet0 != null) {
            for(Object object0: hashSet0) {
                Grouping.findDependents(((ConstraintAnchor)object0).mOwner, v, arrayList0, widgetGroup0);
            }
        }
    }

    public HashSet getDependents() {
        return this.mDependents;
    }

    public int getFinalValue() {
        return this.mHasFinalValue ? this.mFinalValue : 0;
    }

    public int getMargin() {
        if(this.mOwner.getVisibility() == 8) {
            return 0;
        }
        return this.mGoneMargin == 0x80000000 || (this.mTarget == null || this.mTarget.mOwner.getVisibility() != 8) ? this.mMargin : this.mGoneMargin;
    }

    public final ConstraintAnchor getOpposite() {
        switch(this.mType.ordinal()) {
            case 1: {
                return this.mOwner.mRight;
            }
            case 2: {
                return this.mOwner.mBottom;
            }
            case 3: {
                return this.mOwner.mLeft;
            }
            case 4: {
                return this.mOwner.mTop;
            }
            case 0: 
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                return null;
            }
            default: {
                throw new AssertionError(this.mType.name());
            }
        }
    }

    public ConstraintWidget getOwner() {
        return this.mOwner;
    }

    public SolverVariable getSolverVariable() {
        return this.mSolverVariable;
    }

    public ConstraintAnchor getTarget() {
        return this.mTarget;
    }

    public Type getType() {
        return this.mType;
    }

    public boolean hasCenteredDependents() {
        HashSet hashSet0 = this.mDependents;
        if(hashSet0 == null) {
            return false;
        }
        for(Object object0: hashSet0) {
            if(((ConstraintAnchor)object0).getOpposite().isConnected()) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public boolean hasDependents() {
        return this.mDependents == null ? false : this.mDependents.size() > 0;
    }

    public boolean hasFinalValue() {
        return this.mHasFinalValue;
    }

    public boolean isConnected() {
        return this.mTarget != null;
    }

    public boolean isConnectionAllowed(ConstraintWidget constraintWidget0) {
        if(this.isConnectionToMe(constraintWidget0, new HashSet())) {
            return false;
        }
        ConstraintWidget constraintWidget1 = this.getOwner().getParent();
        return constraintWidget1 == constraintWidget0 ? true : constraintWidget0.getParent() == constraintWidget1;
    }

    public boolean isConnectionAllowed(ConstraintWidget constraintWidget0, ConstraintAnchor constraintAnchor0) {
        return this.isConnectionAllowed(constraintWidget0);
    }

    private boolean isConnectionToMe(ConstraintWidget constraintWidget0, HashSet hashSet0) {
        if(hashSet0.contains(constraintWidget0)) {
            return false;
        }
        hashSet0.add(constraintWidget0);
        if(constraintWidget0 == this.getOwner()) {
            return true;
        }
        ArrayList arrayList0 = constraintWidget0.getAnchors();
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ConstraintAnchor constraintAnchor0 = (ConstraintAnchor)arrayList0.get(v1);
            if(constraintAnchor0.isSimilarDimensionConnection(this) && constraintAnchor0.isConnected() && this.isConnectionToMe(constraintAnchor0.getTarget().getOwner(), hashSet0)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSideAnchor() {
        switch(this.mType.ordinal()) {
            case 1: 
            case 2: 
            case 3: 
            case 4: {
                return true;
            }
            case 0: 
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                return false;
            }
            default: {
                throw new AssertionError(this.mType.name());
            }
        }
    }

    public boolean isSimilarDimensionConnection(ConstraintAnchor constraintAnchor0) {
        Type constraintAnchor$Type0 = constraintAnchor0.getType();
        Type constraintAnchor$Type1 = this.mType;
        if(constraintAnchor$Type0 == constraintAnchor$Type1) {
            return true;
        }
        switch(constraintAnchor$Type1.ordinal()) {
            case 0: {
                return false;
            }
            case 6: {
                return constraintAnchor$Type0 != Type.BASELINE;
            }
            case 1: 
            case 3: 
            case 7: {
                return constraintAnchor$Type0 == Type.LEFT || constraintAnchor$Type0 == Type.RIGHT || constraintAnchor$Type0 == Type.CENTER_X;
            }
            case 2: 
            case 4: 
            case 5: 
            case 8: {
                return constraintAnchor$Type0 == Type.TOP || constraintAnchor$Type0 == Type.BOTTOM || constraintAnchor$Type0 == Type.CENTER_Y || constraintAnchor$Type0 == Type.BASELINE;
            }
            default: {
                throw new AssertionError(this.mType.name());
            }
        }
    }

    public boolean isValidConnection(ConstraintAnchor constraintAnchor0) {
        if(constraintAnchor0 == null) {
            return false;
        }
        Type constraintAnchor$Type0 = constraintAnchor0.getType();
        Type constraintAnchor$Type1 = this.mType;
        if(constraintAnchor$Type0 == constraintAnchor$Type1) {
            return constraintAnchor$Type1 != Type.BASELINE || constraintAnchor0.getOwner().hasBaseline() && this.getOwner().hasBaseline();
        }
        switch(constraintAnchor$Type1.ordinal()) {
            case 1: 
            case 3: {
                boolean z = constraintAnchor$Type0 == Type.LEFT || constraintAnchor$Type0 == Type.RIGHT;
                return constraintAnchor0.getOwner() instanceof Guideline ? z || constraintAnchor$Type0 == Type.CENTER_X : z;
            }
            case 2: 
            case 4: {
                boolean z1 = constraintAnchor$Type0 == Type.TOP || constraintAnchor$Type0 == Type.BOTTOM;
                return constraintAnchor0.getOwner() instanceof Guideline ? z1 || constraintAnchor$Type0 == Type.CENTER_Y : z1;
            }
            case 5: {
                return constraintAnchor$Type0 != Type.LEFT && constraintAnchor$Type0 != Type.RIGHT;
            }
            case 6: {
                return constraintAnchor$Type0 != Type.BASELINE && constraintAnchor$Type0 != Type.CENTER_X && constraintAnchor$Type0 != Type.CENTER_Y;
            }
            case 0: 
            case 7: 
            case 8: {
                return false;
            }
            default: {
                throw new AssertionError(this.mType.name());
            }
        }
    }

    public boolean isVerticalAnchor() {
        switch(this.mType.ordinal()) {
            case 1: 
            case 3: 
            case 6: 
            case 7: {
                return false;
            }
            case 0: 
            case 2: 
            case 4: 
            case 5: 
            case 8: {
                return true;
            }
            default: {
                throw new AssertionError(this.mType.name());
            }
        }
    }

    public void reset() {
        ConstraintAnchor constraintAnchor0 = this.mTarget;
        if(constraintAnchor0 != null) {
            HashSet hashSet0 = constraintAnchor0.mDependents;
            if(hashSet0 != null) {
                hashSet0.remove(this);
                if(this.mTarget.mDependents.size() == 0) {
                    this.mTarget.mDependents = null;
                }
            }
        }
        this.mDependents = null;
        this.mTarget = null;
        this.mMargin = 0;
        this.mGoneMargin = 0x80000000;
        this.mHasFinalValue = false;
        this.mFinalValue = 0;
    }

    public void resetFinalResolution() {
        this.mHasFinalValue = false;
        this.mFinalValue = 0;
    }

    public void resetSolverVariable(Cache cache0) {
        SolverVariable solverVariable0 = this.mSolverVariable;
        if(solverVariable0 == null) {
            this.mSolverVariable = new SolverVariable(androidx.constraintlayout.core.SolverVariable.Type.UNRESTRICTED, null);
            return;
        }
        solverVariable0.reset();
    }

    public void setFinalValue(int v) {
        this.mFinalValue = v;
        this.mHasFinalValue = true;
    }

    public void setGoneMargin(int v) {
        if(this.isConnected()) {
            this.mGoneMargin = v;
        }
    }

    public void setMargin(int v) {
        if(this.isConnected()) {
            this.mMargin = v;
        }
    }

    @Override
    public String toString() {
        return this.mOwner.getDebugName() + ":" + this.mType.toString();
    }
}

