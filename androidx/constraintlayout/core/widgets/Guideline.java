package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import java.util.HashMap;

public class Guideline extends ConstraintWidget {
    public static final int HORIZONTAL = 0;
    public static final int RELATIVE_BEGIN = 1;
    public static final int RELATIVE_END = 2;
    public static final int RELATIVE_PERCENT = 0;
    public static final int RELATIVE_UNKNOWN = -1;
    public static final int VERTICAL = 1;
    private ConstraintAnchor mAnchor;
    protected boolean mGuidelineUseRtl;
    private int mMinimumPosition;
    private int mOrientation;
    protected int mRelativeBegin;
    protected int mRelativeEnd;
    protected float mRelativePercent;
    private boolean mResolved;

    public Guideline() {
        this.mRelativePercent = -1.0f;
        this.mRelativeBegin = -1;
        this.mRelativeEnd = -1;
        this.mGuidelineUseRtl = true;
        this.mAnchor = this.mTop;
        this.mOrientation = 0;
        this.mMinimumPosition = 0;
        this.mAnchors.clear();
        this.mAnchors.add(this.mAnchor);
        for(int v = 0; v < this.mListAnchors.length; ++v) {
            this.mListAnchors[v] = this.mAnchor;
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem0, boolean z) {
        ConstraintWidgetContainer constraintWidgetContainer0 = (ConstraintWidgetContainer)this.getParent();
        if(constraintWidgetContainer0 != null) {
            ConstraintAnchor constraintAnchor0 = constraintWidgetContainer0.getAnchor(Type.LEFT);
            ConstraintAnchor constraintAnchor1 = constraintWidgetContainer0.getAnchor(Type.RIGHT);
            int v = 1;
            int v1 = this.mParent == null || this.mParent.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT ? 0 : 1;
            if(this.mOrientation == 0) {
                constraintAnchor0 = constraintWidgetContainer0.getAnchor(Type.TOP);
                constraintAnchor1 = constraintWidgetContainer0.getAnchor(Type.BOTTOM);
                if(this.mParent == null || this.mParent.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT) {
                    v = 0;
                }
                v1 = v;
            }
            if(this.mResolved && this.mAnchor.hasFinalValue()) {
                SolverVariable solverVariable0 = linearSystem0.createObjectVariable(this.mAnchor);
                linearSystem0.addEquality(solverVariable0, this.mAnchor.getFinalValue());
                if(this.mRelativeBegin == -1) {
                    if(this.mRelativeEnd != -1 && v1 != 0) {
                        SolverVariable solverVariable1 = linearSystem0.createObjectVariable(constraintAnchor1);
                        linearSystem0.addGreaterThan(solverVariable0, linearSystem0.createObjectVariable(constraintAnchor0), 0, 5);
                        linearSystem0.addGreaterThan(solverVariable1, solverVariable0, 0, 5);
                    }
                }
                else if(v1 != 0) {
                    linearSystem0.addGreaterThan(linearSystem0.createObjectVariable(constraintAnchor1), solverVariable0, 0, 5);
                }
                this.mResolved = false;
                return;
            }
            if(this.mRelativeBegin != -1) {
                SolverVariable solverVariable2 = linearSystem0.createObjectVariable(this.mAnchor);
                linearSystem0.addEquality(solverVariable2, linearSystem0.createObjectVariable(constraintAnchor0), this.mRelativeBegin, 8);
                if(v1 != 0) {
                    linearSystem0.addGreaterThan(linearSystem0.createObjectVariable(constraintAnchor1), solverVariable2, 0, 5);
                }
            }
            else if(this.mRelativeEnd != -1) {
                SolverVariable solverVariable3 = linearSystem0.createObjectVariable(this.mAnchor);
                SolverVariable solverVariable4 = linearSystem0.createObjectVariable(constraintAnchor1);
                linearSystem0.addEquality(solverVariable3, solverVariable4, -this.mRelativeEnd, 8);
                if(v1 != 0) {
                    linearSystem0.addGreaterThan(solverVariable3, linearSystem0.createObjectVariable(constraintAnchor0), 0, 5);
                    linearSystem0.addGreaterThan(solverVariable4, solverVariable3, 0, 5);
                }
            }
            else if(this.mRelativePercent != -1.0f) {
                linearSystem0.addConstraint(LinearSystem.createRowDimensionPercent(linearSystem0, linearSystem0.createObjectVariable(this.mAnchor), linearSystem0.createObjectVariable(constraintAnchor1), this.mRelativePercent));
            }
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget0, HashMap hashMap0) {
        super.copy(constraintWidget0, hashMap0);
        this.mRelativePercent = ((Guideline)constraintWidget0).mRelativePercent;
        this.mRelativeBegin = ((Guideline)constraintWidget0).mRelativeBegin;
        this.mRelativeEnd = ((Guideline)constraintWidget0).mRelativeEnd;
        this.mGuidelineUseRtl = ((Guideline)constraintWidget0).mGuidelineUseRtl;
        this.setOrientation(((Guideline)constraintWidget0).mOrientation);
    }

    public void cyclePosition() {
        if(this.mRelativeBegin != -1) {
            this.inferRelativePercentPosition();
            return;
        }
        if(this.mRelativePercent != -1.0f) {
            this.inferRelativeEndPosition();
            return;
        }
        if(this.mRelativeEnd != -1) {
            this.inferRelativeBeginPosition();
        }
    }

    public ConstraintAnchor getAnchor() {
        return this.mAnchor;
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public ConstraintAnchor getAnchor(Type constraintAnchor$Type0) {
        switch(constraintAnchor$Type0) {
            case 1: 
            case 2: {
                return this.mOrientation == 1 ? this.mAnchor : null;
            }
            case 3: 
            case 4: {
                return this.mOrientation == 0 ? this.mAnchor : null;
            }
            default: {
                return null;
            }
        }
    }

    public int getMinimumPosition() {
        return this.mMinimumPosition;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getRelativeBegin() {
        return this.mRelativeBegin;
    }

    public int getRelativeBehaviour() {
        if(this.mRelativePercent != -1.0f) {
            return 0;
        }
        if(this.mRelativeBegin != -1) {
            return 1;
        }
        return this.mRelativeEnd == -1 ? -1 : 2;
    }

    public int getRelativeEnd() {
        return this.mRelativeEnd;
    }

    public float getRelativePercent() {
        return this.mRelativePercent;
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public String getType() {
        return "Guideline";
    }

    void inferRelativeBeginPosition() {
        int v = this.getX();
        if(this.mOrientation == 0) {
            v = this.getY();
        }
        this.setGuideBegin(v);
    }

    void inferRelativeEndPosition() {
        int v = this.getParent().getWidth() - this.getX();
        if(this.mOrientation == 0) {
            v = this.getParent().getHeight() - this.getY();
        }
        this.setGuideEnd(v);
    }

    void inferRelativePercentPosition() {
        float f = ((float)this.getX()) / ((float)this.getParent().getWidth());
        if(this.mOrientation == 0) {
            f = ((float)this.getY()) / ((float)this.getParent().getHeight());
        }
        this.setGuidePercent(f);
    }

    public boolean isPercent() {
        return this.mRelativePercent != -1.0f && this.mRelativeBegin == -1 && this.mRelativeEnd == -1;
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean isResolvedHorizontally() {
        return this.mResolved;
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean isResolvedVertically() {
        return this.mResolved;
    }

    public void setFinalValue(int v) {
        this.mAnchor.setFinalValue(v);
        this.mResolved = true;
    }

    public void setGuideBegin(int v) {
        if(v > -1) {
            this.mRelativePercent = -1.0f;
            this.mRelativeBegin = v;
            this.mRelativeEnd = -1;
        }
    }

    public void setGuideEnd(int v) {
        if(v > -1) {
            this.mRelativePercent = -1.0f;
            this.mRelativeBegin = -1;
            this.mRelativeEnd = v;
        }
    }

    public void setGuidePercent(float f) {
        if(f > -1.0f) {
            this.mRelativePercent = f;
            this.mRelativeBegin = -1;
            this.mRelativeEnd = -1;
        }
    }

    public void setGuidePercent(int v) {
        this.setGuidePercent(((float)v) / 100.0f);
    }

    public void setMinimumPosition(int v) {
        this.mMinimumPosition = v;
    }

    public void setOrientation(int v) {
        if(this.mOrientation != v) {
            this.mOrientation = v;
            this.mAnchors.clear();
            this.mAnchor = this.mOrientation == 1 ? this.mLeft : this.mTop;
            this.mAnchors.add(this.mAnchor);
            for(int v1 = 0; v1 < this.mListAnchors.length; ++v1) {
                this.mListAnchors[v1] = this.mAnchor;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void updateFromSolver(LinearSystem linearSystem0, boolean z) {
        if(this.getParent() == null) {
            return;
        }
        int v = linearSystem0.getObjectVariableValue(this.mAnchor);
        if(this.mOrientation == 1) {
            this.setX(v);
            this.setY(0);
            this.setHeight(this.getParent().getHeight());
            this.setWidth(0);
            return;
        }
        this.setX(0);
        this.setY(v);
        this.setWidth(this.getParent().getWidth());
        this.setHeight(0);
    }
}

