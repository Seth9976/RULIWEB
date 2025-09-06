package androidx.constraintlayout.widget;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;

public class ConstraintProperties {
    public static final int BASELINE = 5;
    public static final int BOTTOM = 4;
    public static final int END = 7;
    public static final int LEFT = 1;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    public static final int START = 6;
    public static final int TOP = 3;
    public static final int UNSET = -1;
    public static final int WRAP_CONTENT = -2;
    LayoutParams mParams;
    View mView;

    public ConstraintProperties(View view0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        if(!(viewGroup$LayoutParams0 instanceof LayoutParams)) {
            throw new RuntimeException("Only children of ConstraintLayout.LayoutParams supported");
        }
        this.mParams = (LayoutParams)viewGroup$LayoutParams0;
        this.mView = view0;
    }

    public ConstraintProperties addToHorizontalChain(int v, int v1) {
        this.connect(1, v, (v == 0 ? 1 : 2), 0);
        this.connect(2, v1, (v1 == 0 ? 2 : 1), 0);
        if(v != 0) {
            new ConstraintProperties(((ViewGroup)this.mView.getParent()).findViewById(v)).connect(2, this.mView.getId(), 1, 0);
        }
        if(v1 != 0) {
            new ConstraintProperties(((ViewGroup)this.mView.getParent()).findViewById(v1)).connect(1, this.mView.getId(), 2, 0);
        }
        return this;
    }

    public ConstraintProperties addToHorizontalChainRTL(int v, int v1) {
        this.connect(6, v, (v == 0 ? 6 : 7), 0);
        this.connect(7, v1, (v1 == 0 ? 7 : 6), 0);
        if(v != 0) {
            new ConstraintProperties(((ViewGroup)this.mView.getParent()).findViewById(v)).connect(7, this.mView.getId(), 6, 0);
        }
        if(v1 != 0) {
            new ConstraintProperties(((ViewGroup)this.mView.getParent()).findViewById(v1)).connect(6, this.mView.getId(), 7, 0);
        }
        return this;
    }

    public ConstraintProperties addToVerticalChain(int v, int v1) {
        this.connect(3, v, (v == 0 ? 3 : 4), 0);
        this.connect(4, v1, (v1 == 0 ? 4 : 3), 0);
        if(v != 0) {
            new ConstraintProperties(((ViewGroup)this.mView.getParent()).findViewById(v)).connect(4, this.mView.getId(), 3, 0);
        }
        if(v1 != 0) {
            new ConstraintProperties(((ViewGroup)this.mView.getParent()).findViewById(v1)).connect(3, this.mView.getId(), 4, 0);
        }
        return this;
    }

    public ConstraintProperties alpha(float f) {
        this.mView.setAlpha(f);
        return this;
    }

    public void apply() {
    }

    public ConstraintProperties center(int v, int v1, int v2, int v3, int v4, int v5, float f) {
        if(v2 < 0 || v5 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if(f <= 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
        }
        if(v1 != 1 && v1 != 2) {
            if(v1 != 6 && v1 != 7) {
                this.connect(3, v, v1, v2);
                this.connect(4, v3, v4, v5);
                this.mParams.verticalBias = f;
                return this;
            }
            this.connect(6, v, v1, v2);
            this.connect(7, v3, v4, v5);
            this.mParams.horizontalBias = f;
            return this;
        }
        this.connect(1, v, v1, v2);
        this.connect(2, v3, v4, v5);
        this.mParams.horizontalBias = f;
        return this;
    }

    public ConstraintProperties centerHorizontally(int v) {
        if(v == 0) {
            this.center(0, 1, 0, 0, 2, 0, 0.5f);
            return this;
        }
        this.center(v, 2, 0, v, 1, 0, 0.5f);
        return this;
    }

    public ConstraintProperties centerHorizontally(int v, int v1, int v2, int v3, int v4, int v5, float f) {
        this.connect(1, v, v1, v2);
        this.connect(2, v3, v4, v5);
        this.mParams.horizontalBias = f;
        return this;
    }

    public ConstraintProperties centerHorizontallyRtl(int v) {
        if(v == 0) {
            this.center(0, 6, 0, 0, 7, 0, 0.5f);
            return this;
        }
        this.center(v, 7, 0, v, 6, 0, 0.5f);
        return this;
    }

    public ConstraintProperties centerHorizontallyRtl(int v, int v1, int v2, int v3, int v4, int v5, float f) {
        this.connect(6, v, v1, v2);
        this.connect(7, v3, v4, v5);
        this.mParams.horizontalBias = f;
        return this;
    }

    public ConstraintProperties centerVertically(int v) {
        if(v == 0) {
            this.center(0, 3, 0, 0, 4, 0, 0.5f);
            return this;
        }
        this.center(v, 4, 0, v, 3, 0, 0.5f);
        return this;
    }

    public ConstraintProperties centerVertically(int v, int v1, int v2, int v3, int v4, int v5, float f) {
        this.connect(3, v, v1, v2);
        this.connect(4, v3, v4, v5);
        this.mParams.verticalBias = f;
        return this;
    }

    public ConstraintProperties connect(int v, int v1, int v2, int v3) {
        switch(v) {
            case 1: {
                if(v2 == 1) {
                    this.mParams.leftToLeft = v1;
                    this.mParams.leftToRight = -1;
                }
                else if(v2 == 2) {
                    this.mParams.leftToRight = v1;
                    this.mParams.leftToLeft = -1;
                }
                else {
                    throw new IllegalArgumentException("Left to " + this.sideToString(v2) + " undefined");
                }
                this.mParams.leftMargin = v3;
                return this;
            }
            case 2: {
                if(v2 == 1) {
                    this.mParams.rightToLeft = v1;
                    this.mParams.rightToRight = -1;
                }
                else if(v2 == 2) {
                    this.mParams.rightToRight = v1;
                    this.mParams.rightToLeft = -1;
                }
                else {
                    throw new IllegalArgumentException("right to " + this.sideToString(v2) + " undefined");
                }
                this.mParams.rightMargin = v3;
                return this;
            }
            case 3: {
                if(v2 == 3) {
                    this.mParams.topToTop = v1;
                    this.mParams.topToBottom = -1;
                    this.mParams.baselineToBaseline = -1;
                    this.mParams.baselineToTop = -1;
                    this.mParams.baselineToBottom = -1;
                }
                else if(v2 == 4) {
                    this.mParams.topToBottom = v1;
                    this.mParams.topToTop = -1;
                    this.mParams.baselineToBaseline = -1;
                    this.mParams.baselineToTop = -1;
                    this.mParams.baselineToBottom = -1;
                }
                else {
                    throw new IllegalArgumentException("right to " + this.sideToString(v2) + " undefined");
                }
                this.mParams.topMargin = v3;
                return this;
            }
            case 4: {
                if(v2 == 4) {
                    this.mParams.bottomToBottom = v1;
                    this.mParams.bottomToTop = -1;
                    this.mParams.baselineToBaseline = -1;
                    this.mParams.baselineToTop = -1;
                    this.mParams.baselineToBottom = -1;
                }
                else if(v2 == 3) {
                    this.mParams.bottomToTop = v1;
                    this.mParams.bottomToBottom = -1;
                    this.mParams.baselineToBaseline = -1;
                    this.mParams.baselineToTop = -1;
                    this.mParams.baselineToBottom = -1;
                }
                else {
                    throw new IllegalArgumentException("right to " + this.sideToString(v2) + " undefined");
                }
                this.mParams.bottomMargin = v3;
                return this;
            }
            case 5: {
                switch(v2) {
                    case 3: {
                        this.mParams.baselineToTop = v1;
                        this.mParams.bottomToBottom = -1;
                        this.mParams.bottomToTop = -1;
                        this.mParams.topToTop = -1;
                        this.mParams.topToBottom = -1;
                        break;
                    }
                    case 4: {
                        this.mParams.baselineToBottom = v1;
                        this.mParams.bottomToBottom = -1;
                        this.mParams.bottomToTop = -1;
                        this.mParams.topToTop = -1;
                        this.mParams.topToBottom = -1;
                        break;
                    }
                    case 5: {
                        this.mParams.baselineToBaseline = v1;
                        this.mParams.bottomToBottom = -1;
                        this.mParams.bottomToTop = -1;
                        this.mParams.topToTop = -1;
                        this.mParams.topToBottom = -1;
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("right to " + this.sideToString(v2) + " undefined");
                    }
                }
                this.mParams.baselineMargin = v3;
                return this;
            }
            case 6: {
                if(v2 == 6) {
                    this.mParams.startToStart = v1;
                    this.mParams.startToEnd = -1;
                }
                else if(v2 == 7) {
                    this.mParams.startToEnd = v1;
                    this.mParams.startToStart = -1;
                }
                else {
                    throw new IllegalArgumentException("right to " + this.sideToString(v2) + " undefined");
                }
                this.mParams.setMarginStart(v3);
                return this;
            }
            case 7: {
                if(v2 == 7) {
                    this.mParams.endToEnd = v1;
                    this.mParams.endToStart = -1;
                }
                else if(v2 == 6) {
                    this.mParams.endToStart = v1;
                    this.mParams.endToEnd = -1;
                }
                else {
                    throw new IllegalArgumentException("right to " + this.sideToString(v2) + " undefined");
                }
                this.mParams.setMarginEnd(v3);
                return this;
            }
            default: {
                throw new IllegalArgumentException(this.sideToString(v) + " to " + this.sideToString(v2) + " unknown");
            }
        }
    }

    public ConstraintProperties constrainDefaultHeight(int v) {
        this.mParams.matchConstraintDefaultHeight = v;
        return this;
    }

    public ConstraintProperties constrainDefaultWidth(int v) {
        this.mParams.matchConstraintDefaultWidth = v;
        return this;
    }

    public ConstraintProperties constrainHeight(int v) {
        this.mParams.height = v;
        return this;
    }

    public ConstraintProperties constrainMaxHeight(int v) {
        this.mParams.matchConstraintMaxHeight = v;
        return this;
    }

    public ConstraintProperties constrainMaxWidth(int v) {
        this.mParams.matchConstraintMaxWidth = v;
        return this;
    }

    public ConstraintProperties constrainMinHeight(int v) {
        this.mParams.matchConstraintMinHeight = v;
        return this;
    }

    public ConstraintProperties constrainMinWidth(int v) {
        this.mParams.matchConstraintMinWidth = v;
        return this;
    }

    public ConstraintProperties constrainWidth(int v) {
        this.mParams.width = v;
        return this;
    }

    public ConstraintProperties dimensionRatio(String s) {
        this.mParams.dimensionRatio = s;
        return this;
    }

    public ConstraintProperties elevation(float f) {
        this.mView.setElevation(f);
        return this;
    }

    public ConstraintProperties goneMargin(int v, int v1) {
        switch(v) {
            case 1: {
                this.mParams.goneLeftMargin = v1;
                return this;
            }
            case 2: {
                this.mParams.goneRightMargin = v1;
                return this;
            }
            case 3: {
                this.mParams.goneTopMargin = v1;
                return this;
            }
            case 4: {
                this.mParams.goneBottomMargin = v1;
                return this;
            }
            case 5: {
                throw new IllegalArgumentException("baseline does not support margins");
            }
            case 6: {
                this.mParams.goneStartMargin = v1;
                return this;
            }
            case 7: {
                this.mParams.goneEndMargin = v1;
                return this;
            }
            default: {
                throw new IllegalArgumentException("unknown constraint");
            }
        }
    }

    public ConstraintProperties horizontalBias(float f) {
        this.mParams.horizontalBias = f;
        return this;
    }

    public ConstraintProperties horizontalChainStyle(int v) {
        this.mParams.horizontalChainStyle = v;
        return this;
    }

    public ConstraintProperties horizontalWeight(float f) {
        this.mParams.horizontalWeight = f;
        return this;
    }

    public ConstraintProperties margin(int v, int v1) {
        switch(v) {
            case 1: {
                this.mParams.leftMargin = v1;
                return this;
            }
            case 2: {
                this.mParams.rightMargin = v1;
                return this;
            }
            case 3: {
                this.mParams.topMargin = v1;
                return this;
            }
            case 4: {
                this.mParams.bottomMargin = v1;
                return this;
            }
            case 5: {
                throw new IllegalArgumentException("baseline does not support margins");
            }
            case 6: {
                this.mParams.setMarginStart(v1);
                return this;
            }
            case 7: {
                this.mParams.setMarginEnd(v1);
                return this;
            }
            default: {
                throw new IllegalArgumentException("unknown constraint");
            }
        }
    }

    public ConstraintProperties removeConstraints(int v) {
        switch(v) {
            case 1: {
                this.mParams.leftToRight = -1;
                this.mParams.leftToLeft = -1;
                this.mParams.leftMargin = -1;
                this.mParams.goneLeftMargin = 0x80000000;
                return this;
            }
            case 2: {
                this.mParams.rightToRight = -1;
                this.mParams.rightToLeft = -1;
                this.mParams.rightMargin = -1;
                this.mParams.goneRightMargin = 0x80000000;
                return this;
            }
            case 3: {
                this.mParams.topToBottom = -1;
                this.mParams.topToTop = -1;
                this.mParams.topMargin = -1;
                this.mParams.goneTopMargin = 0x80000000;
                return this;
            }
            case 4: {
                this.mParams.bottomToTop = -1;
                this.mParams.bottomToBottom = -1;
                this.mParams.bottomMargin = -1;
                this.mParams.goneBottomMargin = 0x80000000;
                return this;
            }
            case 5: {
                this.mParams.baselineToBaseline = -1;
                return this;
            }
            case 6: {
                this.mParams.startToEnd = -1;
                this.mParams.startToStart = -1;
                this.mParams.setMarginStart(-1);
                this.mParams.goneStartMargin = 0x80000000;
                return this;
            }
            case 7: {
                this.mParams.endToStart = -1;
                this.mParams.endToEnd = -1;
                this.mParams.setMarginEnd(-1);
                this.mParams.goneEndMargin = 0x80000000;
                return this;
            }
            default: {
                throw new IllegalArgumentException("unknown constraint");
            }
        }
    }

    public ConstraintProperties removeFromHorizontalChain() {
        int v = this.mParams.leftToRight;
        int v1 = this.mParams.rightToLeft;
        LayoutParams constraintLayout$LayoutParams0 = this.mParams;
        if(v == -1 && v1 == -1) {
            int v2 = constraintLayout$LayoutParams0.startToEnd;
            int v3 = this.mParams.endToStart;
            if(v2 != -1 || v3 != -1) {
                ConstraintProperties constraintProperties0 = new ConstraintProperties(((ViewGroup)this.mView.getParent()).findViewById(v2));
                ConstraintProperties constraintProperties1 = new ConstraintProperties(((ViewGroup)this.mView.getParent()).findViewById(v3));
                LayoutParams constraintLayout$LayoutParams1 = this.mParams;
                if(v2 != -1 && v3 != -1) {
                    constraintProperties0.connect(7, v3, 6, 0);
                    constraintProperties1.connect(6, -1, 7, 0);
                }
                else if(v3 != -1) {
                    LayoutParams constraintLayout$LayoutParams2 = this.mParams;
                    if(constraintLayout$LayoutParams1.rightToRight == -1) {
                        LayoutParams constraintLayout$LayoutParams3 = this.mParams;
                        if(constraintLayout$LayoutParams2.leftToLeft != -1) {
                            constraintProperties1.connect(6, constraintLayout$LayoutParams3.leftToLeft, 6, 0);
                        }
                    }
                    else {
                        constraintProperties0.connect(7, constraintLayout$LayoutParams2.rightToRight, 7, 0);
                    }
                }
            }
            this.removeConstraints(6);
            this.removeConstraints(7);
            return this;
        }
        ConstraintProperties constraintProperties2 = new ConstraintProperties(((ViewGroup)this.mView.getParent()).findViewById(v));
        ConstraintProperties constraintProperties3 = new ConstraintProperties(((ViewGroup)this.mView.getParent()).findViewById(v1));
        LayoutParams constraintLayout$LayoutParams4 = this.mParams;
        if(v != -1 && v1 != -1) {
            constraintProperties2.connect(2, v1, 1, 0);
            constraintProperties3.connect(1, v, 2, 0);
        }
        else if(v != -1 || v1 != -1) {
            LayoutParams constraintLayout$LayoutParams5 = this.mParams;
            if(constraintLayout$LayoutParams4.rightToRight == -1) {
                LayoutParams constraintLayout$LayoutParams6 = this.mParams;
                if(constraintLayout$LayoutParams5.leftToLeft != -1) {
                    constraintProperties3.connect(1, constraintLayout$LayoutParams6.leftToLeft, 1, 0);
                }
            }
            else {
                constraintProperties2.connect(2, constraintLayout$LayoutParams5.rightToRight, 2, 0);
            }
        }
        this.removeConstraints(1);
        this.removeConstraints(2);
        return this;
    }

    public ConstraintProperties removeFromVerticalChain() {
        int v = this.mParams.topToBottom;
        int v1 = this.mParams.bottomToTop;
        if(v != -1 || v1 != -1) {
            ConstraintProperties constraintProperties0 = new ConstraintProperties(((ViewGroup)this.mView.getParent()).findViewById(v));
            ConstraintProperties constraintProperties1 = new ConstraintProperties(((ViewGroup)this.mView.getParent()).findViewById(v1));
            LayoutParams constraintLayout$LayoutParams0 = this.mParams;
            if(v != -1 && v1 != -1) {
                constraintProperties0.connect(4, v1, 3, 0);
                constraintProperties1.connect(3, v, 4, 0);
            }
            else if(v != -1 || v1 != -1) {
                LayoutParams constraintLayout$LayoutParams1 = this.mParams;
                if(constraintLayout$LayoutParams0.bottomToBottom == -1) {
                    LayoutParams constraintLayout$LayoutParams2 = this.mParams;
                    if(constraintLayout$LayoutParams1.topToTop != -1) {
                        constraintProperties1.connect(3, constraintLayout$LayoutParams2.topToTop, 3, 0);
                    }
                }
                else {
                    constraintProperties0.connect(4, constraintLayout$LayoutParams1.bottomToBottom, 4, 0);
                }
            }
        }
        this.removeConstraints(3);
        this.removeConstraints(4);
        return this;
    }

    public ConstraintProperties rotation(float f) {
        this.mView.setRotation(f);
        return this;
    }

    public ConstraintProperties rotationX(float f) {
        this.mView.setRotationX(f);
        return this;
    }

    public ConstraintProperties rotationY(float f) {
        this.mView.setRotationY(f);
        return this;
    }

    public ConstraintProperties scaleX(float f) {
        this.mView.setScaleY(f);
        return this;
    }

    public ConstraintProperties scaleY(float f) {
        return this;
    }

    private String sideToString(int v) {
        switch(v) {
            case 1: {
                return "left";
            }
            case 2: {
                return "right";
            }
            case 3: {
                return "top";
            }
            case 4: {
                return "bottom";
            }
            case 5: {
                return "baseline";
            }
            case 6: {
                return "start";
            }
            case 7: {
                return "end";
            }
            default: {
                return "undefined";
            }
        }
    }

    public ConstraintProperties transformPivot(float f, float f1) {
        this.mView.setPivotX(f);
        this.mView.setPivotY(f1);
        return this;
    }

    public ConstraintProperties transformPivotX(float f) {
        this.mView.setPivotX(f);
        return this;
    }

    public ConstraintProperties transformPivotY(float f) {
        this.mView.setPivotY(f);
        return this;
    }

    public ConstraintProperties translation(float f, float f1) {
        this.mView.setTranslationX(f);
        this.mView.setTranslationY(f1);
        return this;
    }

    public ConstraintProperties translationX(float f) {
        this.mView.setTranslationX(f);
        return this;
    }

    public ConstraintProperties translationY(float f) {
        this.mView.setTranslationY(f);
        return this;
    }

    public ConstraintProperties translationZ(float f) {
        this.mView.setTranslationZ(f);
        return this;
    }

    public ConstraintProperties verticalBias(float f) {
        this.mParams.verticalBias = f;
        return this;
    }

    public ConstraintProperties verticalChainStyle(int v) {
        this.mParams.verticalChainStyle = v;
        return this;
    }

    public ConstraintProperties verticalWeight(float f) {
        this.mParams.verticalWeight = f;
        return this;
    }

    public ConstraintProperties visibility(int v) {
        this.mView.setVisibility(v);
        return this;
    }
}

