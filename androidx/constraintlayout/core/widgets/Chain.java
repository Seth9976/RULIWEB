package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.ArrayRow;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import java.util.ArrayList;

public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    // This method was un-flattened
    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer0, LinearSystem linearSystem0, int v, int v1, ChainHead chainHead0) {
        ConstraintWidget constraintWidget20;
        ConstraintWidget constraintWidget19;
        SolverVariable solverVariable13;
        SolverVariable solverVariable12;
        ConstraintAnchor constraintAnchor9;
        ConstraintWidget constraintWidget14;
        ConstraintWidget constraintWidget13;
        ConstraintWidget constraintWidget15;
        SolverVariable solverVariable8;
        ConstraintAnchor constraintAnchor7;
        float f4;
        ArrayList arrayList1;
        int v5;
        boolean z2;
        boolean z1;
        int v2;
        ConstraintWidget constraintWidget0 = chainHead0.mFirst;
        ConstraintWidget constraintWidget1 = chainHead0.mLast;
        ConstraintWidget constraintWidget2 = chainHead0.mFirstVisibleWidget;
        ConstraintWidget constraintWidget3 = chainHead0.mLastVisibleWidget;
        ConstraintWidget constraintWidget4 = chainHead0.mHead;
        float f = chainHead0.mTotalWeight;
        boolean z = constraintWidgetContainer0.mListDimensionBehaviors[v] == DimensionBehaviour.WRAP_CONTENT;
        if(v == 0) {
            v2 = constraintWidget4.mHorizontalChainStyle == 0 ? 1 : 0;
            z1 = constraintWidget4.mHorizontalChainStyle == 1;
            z2 = constraintWidget4.mHorizontalChainStyle == 2;
        }
        else {
            v2 = constraintWidget4.mVerticalChainStyle == 0 ? 1 : 0;
            z1 = constraintWidget4.mVerticalChainStyle == 1;
            z2 = constraintWidget4.mVerticalChainStyle == 2;
        }
        ConstraintWidget constraintWidget5 = constraintWidget0;
        ConstraintWidget constraintWidget6;
        for(constraintWidget6 = null; true; constraintWidget6 = null) {
            ConstraintAnchor constraintAnchor0 = constraintWidget5.mListAnchors[v1];
            int v3 = z2 ? 1 : 4;
            int v4 = constraintAnchor0.getMargin();
            boolean z3 = constraintWidget5.mListDimensionBehaviors[v] == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget5.mResolvedMatchConstraintDefault[v] == 0;
            if(constraintAnchor0.mTarget != null && constraintWidget5 != constraintWidget0) {
                v4 += constraintAnchor0.mTarget.getMargin();
            }
            if(z2 && constraintWidget5 != constraintWidget0 && constraintWidget5 != constraintWidget2) {
                v3 = 8;
            }
            if(constraintAnchor0.mTarget == null) {
                v5 = v2;
            }
            else {
                if(constraintWidget5 == constraintWidget2) {
                    v5 = v2;
                    linearSystem0.addGreaterThan(constraintAnchor0.mSolverVariable, constraintAnchor0.mTarget.mSolverVariable, v4, 6);
                }
                else {
                    v5 = v2;
                    linearSystem0.addGreaterThan(constraintAnchor0.mSolverVariable, constraintAnchor0.mTarget.mSolverVariable, v4, 8);
                }
                if(z3 && !z2) {
                    v3 = 5;
                }
                int v6 = constraintWidget5 != constraintWidget2 || !z2 || !constraintWidget5.isInBarrier(v) ? v3 : 5;
                linearSystem0.addEquality(constraintAnchor0.mSolverVariable, constraintAnchor0.mTarget.mSolverVariable, v4, v6);
            }
            if(z) {
                if(constraintWidget5.getVisibility() != 8 && constraintWidget5.mListDimensionBehaviors[v] == DimensionBehaviour.MATCH_CONSTRAINT) {
                    linearSystem0.addGreaterThan(constraintWidget5.mListAnchors[v1 + 1].mSolverVariable, constraintWidget5.mListAnchors[v1].mSolverVariable, 0, 5);
                }
                linearSystem0.addGreaterThan(constraintWidget5.mListAnchors[v1].mSolverVariable, constraintWidgetContainer0.mListAnchors[v1].mSolverVariable, 0, 8);
            }
            ConstraintAnchor constraintAnchor1 = constraintWidget5.mListAnchors[v1 + 1].mTarget;
            if(constraintAnchor1 != null) {
                ConstraintWidget constraintWidget7 = constraintAnchor1.mOwner;
                if(constraintWidget7.mListAnchors[v1].mTarget != null && constraintWidget7.mListAnchors[v1].mTarget.mOwner == constraintWidget5) {
                    constraintWidget6 = constraintWidget7;
                }
            }
            if(constraintWidget6 == null) {
                break;
            }
            constraintWidget5 = constraintWidget6;
            v2 = v5;
        }
        constraintWidget6 = null;
        if(constraintWidget3 != null && constraintWidget1.mListAnchors[v1 + 1].mTarget != null) {
            ConstraintAnchor constraintAnchor2 = constraintWidget3.mListAnchors[v1 + 1];
            if(constraintWidget3.mListDimensionBehaviors[v] == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget3.mResolvedMatchConstraintDefault[v] == 0 && !z2 && constraintAnchor2.mTarget.mOwner == constraintWidgetContainer0) {
                linearSystem0.addEquality(constraintAnchor2.mSolverVariable, constraintAnchor2.mTarget.mSolverVariable, -constraintAnchor2.getMargin(), 5);
            }
            else if(z2 && constraintAnchor2.mTarget.mOwner == constraintWidgetContainer0) {
                linearSystem0.addEquality(constraintAnchor2.mSolverVariable, constraintAnchor2.mTarget.mSolverVariable, -constraintAnchor2.getMargin(), 4);
            }
            linearSystem0.addLowerThan(constraintAnchor2.mSolverVariable, constraintWidget1.mListAnchors[v1 + 1].mTarget.mSolverVariable, -constraintAnchor2.getMargin(), 6);
        }
        if(z) {
            linearSystem0.addGreaterThan(constraintWidgetContainer0.mListAnchors[v1 + 1].mSolverVariable, constraintWidget1.mListAnchors[v1 + 1].mSolverVariable, constraintWidget1.mListAnchors[v1 + 1].getMargin(), 8);
        }
        ArrayList arrayList0 = chainHead0.mWeightedMatchConstraintsWidgets;
        if(arrayList0 != null) {
            int v7 = arrayList0.size();
            if(v7 > 1) {
                float f1 = !chainHead0.mHasUndefinedWeights || chainHead0.mHasComplexMatchWeights ? f : ((float)chainHead0.mWidgetsMatchCount);
                ConstraintWidget constraintWidget8 = null;
                int v8 = 0;
                float f2 = 0.0f;
                while(v8 < v7) {
                    ConstraintWidget constraintWidget9 = (ConstraintWidget)arrayList0.get(v8);
                    float f3 = constraintWidget9.mWeight[v];
                    if(f3 < 0.0f) {
                        if(chainHead0.mHasComplexMatchWeights) {
                            arrayList1 = arrayList0;
                            linearSystem0.addEquality(constraintWidget9.mListAnchors[v1 + 1].mSolverVariable, constraintWidget9.mListAnchors[v1].mSolverVariable, 0, 4);
                            goto label_100;
                        }
                        else {
                            arrayList1 = arrayList0;
                            f4 = 1.0f;
                            goto label_87;
                        }
                        goto label_85;
                    }
                    else {
                    label_85:
                        arrayList1 = arrayList0;
                        f4 = f3;
                    }
                label_87:
                    if(f4 == 0.0f) {
                        linearSystem0.addEquality(constraintWidget9.mListAnchors[v1 + 1].mSolverVariable, constraintWidget9.mListAnchors[v1].mSolverVariable, 0, 8);
                    }
                    else {
                        if(constraintWidget8 != null) {
                            SolverVariable solverVariable0 = constraintWidget8.mListAnchors[v1].mSolverVariable;
                            SolverVariable solverVariable1 = constraintWidget8.mListAnchors[v1 + 1].mSolverVariable;
                            SolverVariable solverVariable2 = constraintWidget9.mListAnchors[v1].mSolverVariable;
                            SolverVariable solverVariable3 = constraintWidget9.mListAnchors[v1 + 1].mSolverVariable;
                            ArrayRow arrayRow0 = linearSystem0.createRow();
                            arrayRow0.createRowEqualMatchDimensions(f2, f1, f4, solverVariable0, solverVariable1, solverVariable2, solverVariable3);
                            linearSystem0.addConstraint(arrayRow0);
                        }
                        constraintWidget8 = constraintWidget9;
                        f2 = f4;
                    }
                label_100:
                    ++v8;
                    arrayList0 = arrayList1;
                }
            }
        }
        if(constraintWidget2 != null && (constraintWidget2 == constraintWidget3 || z2)) {
            ConstraintAnchor constraintAnchor3 = constraintWidget0.mListAnchors[v1];
            ConstraintAnchor constraintAnchor4 = constraintWidget1.mListAnchors[v1 + 1];
            SolverVariable solverVariable4 = constraintAnchor3.mTarget == null ? null : constraintAnchor3.mTarget.mSolverVariable;
            SolverVariable solverVariable5 = constraintAnchor4.mTarget == null ? null : constraintAnchor4.mTarget.mSolverVariable;
            ConstraintAnchor constraintAnchor5 = constraintWidget2.mListAnchors[v1];
            if(constraintWidget3 != null) {
                constraintAnchor4 = constraintWidget3.mListAnchors[v1 + 1];
            }
            if(solverVariable4 != null && solverVariable5 != null) {
                float f5 = v == 0 ? constraintWidget4.mHorizontalBiasPercent : constraintWidget4.mVerticalBiasPercent;
                int v9 = constraintAnchor5.getMargin();
                int v10 = constraintAnchor4.getMargin();
                linearSystem0.addCentering(constraintAnchor5.mSolverVariable, solverVariable4, v9, f5, solverVariable5, constraintAnchor4.mSolverVariable, v10, 7);
            }
        }
        else if(v5 != 0 && constraintWidget2 != null) {
            boolean z4 = chainHead0.mWidgetsMatchCount > 0 && chainHead0.mWidgetsCount == chainHead0.mWidgetsMatchCount;
            ConstraintWidget constraintWidget10 = constraintWidget2;
            for(ConstraintWidget constraintWidget11 = constraintWidget10; constraintWidget11 != null; constraintWidget11 = constraintWidget14) {
                ConstraintWidget constraintWidget12;
                for(constraintWidget12 = constraintWidget11.mNextChainWidget[v]; constraintWidget12 != null && constraintWidget12.getVisibility() == 8; constraintWidget12 = constraintWidget12.mNextChainWidget[v]) {
                }
                if(constraintWidget12 != null || constraintWidget11 == constraintWidget3) {
                    ConstraintAnchor constraintAnchor6 = constraintWidget11.mListAnchors[v1];
                    SolverVariable solverVariable6 = constraintAnchor6.mSolverVariable;
                    SolverVariable solverVariable7 = constraintAnchor6.mTarget == null ? null : constraintAnchor6.mTarget.mSolverVariable;
                    if(constraintWidget10 != constraintWidget11) {
                        solverVariable7 = constraintWidget10.mListAnchors[v1 + 1].mSolverVariable;
                    }
                    else if(constraintWidget11 == constraintWidget2) {
                        solverVariable7 = constraintWidget0.mListAnchors[v1].mTarget == null ? null : constraintWidget0.mListAnchors[v1].mTarget.mSolverVariable;
                    }
                    int v11 = constraintAnchor6.getMargin();
                    int v12 = constraintWidget11.mListAnchors[v1 + 1].getMargin();
                    if(constraintWidget12 == null) {
                        constraintAnchor7 = constraintWidget1.mListAnchors[v1 + 1].mTarget;
                        if(constraintAnchor7 == null) {
                            constraintWidget15 = null;
                            solverVariable8 = null;
                        }
                        else {
                            solverVariable8 = constraintAnchor7.mSolverVariable;
                            constraintWidget15 = null;
                        }
                    }
                    else {
                        constraintAnchor7 = constraintWidget12.mListAnchors[v1];
                        solverVariable8 = constraintAnchor7.mSolverVariable;
                        constraintWidget15 = constraintWidget12;
                    }
                    SolverVariable solverVariable9 = constraintWidget11.mListAnchors[v1 + 1].mSolverVariable;
                    if(constraintAnchor7 != null) {
                        v12 += constraintAnchor7.getMargin();
                    }
                    int v13 = v11 + constraintWidget10.mListAnchors[v1 + 1].getMargin();
                    if(solverVariable6 == null || solverVariable7 == null || solverVariable8 == null || solverVariable9 == null) {
                        constraintWidget14 = constraintWidget15;
                        constraintWidget13 = constraintWidget10;
                    }
                    else {
                        if(constraintWidget11 == constraintWidget2) {
                            v13 = constraintWidget2.mListAnchors[v1].getMargin();
                        }
                        if(constraintWidget11 == constraintWidget3) {
                            v12 = constraintWidget3.mListAnchors[v1 + 1].getMargin();
                        }
                        constraintWidget14 = constraintWidget15;
                        constraintWidget13 = constraintWidget10;
                        linearSystem0.addCentering(solverVariable6, solverVariable7, v13, 0.5f, solverVariable8, solverVariable9, v12, (z4 ? 8 : 5));
                    }
                }
                else {
                    constraintWidget13 = constraintWidget10;
                    constraintWidget14 = null;
                }
                constraintWidget10 = constraintWidget11.getVisibility() == 8 ? constraintWidget13 : constraintWidget11;
            }
        }
        else if(z1 && constraintWidget2 != null) {
            boolean z5 = chainHead0.mWidgetsMatchCount > 0 && chainHead0.mWidgetsCount == chainHead0.mWidgetsMatchCount;
            ConstraintWidget constraintWidget16 = constraintWidget2;
            for(ConstraintWidget constraintWidget17 = constraintWidget16; constraintWidget17 != null; constraintWidget17 = constraintWidget18) {
                ConstraintWidget constraintWidget18;
                for(constraintWidget18 = constraintWidget17.mNextChainWidget[v]; constraintWidget18 != null && constraintWidget18.getVisibility() == 8; constraintWidget18 = constraintWidget18.mNextChainWidget[v]) {
                }
                if(constraintWidget17 == constraintWidget2 || constraintWidget17 == constraintWidget3 || constraintWidget18 == null) {
                    constraintWidget20 = constraintWidget16;
                }
                else {
                    if(constraintWidget18 == constraintWidget3) {
                        constraintWidget18 = null;
                    }
                    ConstraintAnchor constraintAnchor8 = constraintWidget17.mListAnchors[v1];
                    SolverVariable solverVariable10 = constraintAnchor8.mSolverVariable;
                    SolverVariable solverVariable11 = constraintWidget16.mListAnchors[v1 + 1].mSolverVariable;
                    int v14 = constraintAnchor8.getMargin();
                    int v15 = constraintWidget17.mListAnchors[v1 + 1].getMargin();
                    if(constraintWidget18 == null) {
                        constraintAnchor9 = constraintWidget3.mListAnchors[v1];
                        solverVariable12 = constraintAnchor9 == null ? null : constraintAnchor9.mSolverVariable;
                        solverVariable13 = constraintWidget17.mListAnchors[v1 + 1].mSolverVariable;
                    }
                    else {
                        constraintAnchor9 = constraintWidget18.mListAnchors[v1];
                        solverVariable12 = constraintAnchor9.mSolverVariable;
                        solverVariable13 = constraintAnchor9.mTarget == null ? null : constraintAnchor9.mTarget.mSolverVariable;
                    }
                    if(constraintAnchor9 != null) {
                        v15 += constraintAnchor9.getMargin();
                    }
                    int v16 = v14 + constraintWidget16.mListAnchors[v1 + 1].getMargin();
                    if(solverVariable10 == null || solverVariable11 == null || solverVariable12 == null || solverVariable13 == null) {
                        constraintWidget20 = constraintWidget16;
                        constraintWidget19 = constraintWidget18;
                    }
                    else {
                        constraintWidget19 = constraintWidget18;
                        constraintWidget20 = constraintWidget16;
                        linearSystem0.addCentering(solverVariable10, solverVariable11, v16, 0.5f, solverVariable12, solverVariable13, v15, (z5 ? 8 : 4));
                    }
                    constraintWidget18 = constraintWidget19;
                }
                if(constraintWidget17.getVisibility() == 8) {
                    constraintWidget17 = constraintWidget20;
                }
                constraintWidget16 = constraintWidget17;
            }
            ConstraintAnchor constraintAnchor10 = constraintWidget2.mListAnchors[v1];
            ConstraintAnchor constraintAnchor11 = constraintWidget0.mListAnchors[v1].mTarget;
            ConstraintAnchor constraintAnchor12 = constraintWidget3.mListAnchors[v1 + 1];
            ConstraintAnchor constraintAnchor13 = constraintWidget1.mListAnchors[v1 + 1].mTarget;
            if(constraintAnchor11 != null) {
                if(constraintWidget2 != constraintWidget3) {
                    linearSystem0.addEquality(constraintAnchor10.mSolverVariable, constraintAnchor11.mSolverVariable, constraintAnchor10.getMargin(), 5);
                }
                else if(constraintAnchor13 != null) {
                    linearSystem0.addCentering(constraintAnchor10.mSolverVariable, constraintAnchor11.mSolverVariable, constraintAnchor10.getMargin(), 0.5f, constraintAnchor12.mSolverVariable, constraintAnchor13.mSolverVariable, constraintAnchor12.getMargin(), 5);
                }
            }
            if(constraintAnchor13 != null && constraintWidget2 != constraintWidget3) {
                linearSystem0.addEquality(constraintAnchor12.mSolverVariable, constraintAnchor13.mSolverVariable, -constraintAnchor12.getMargin(), 5);
            }
        }
        if((v5 != 0 || z1) && constraintWidget2 != null && constraintWidget2 != constraintWidget3) {
            ConstraintAnchor constraintAnchor14 = constraintWidget2.mListAnchors[v1];
            if(constraintWidget3 == null) {
                constraintWidget3 = constraintWidget2;
            }
            ConstraintAnchor constraintAnchor15 = constraintWidget3.mListAnchors[v1 + 1];
            SolverVariable solverVariable14 = constraintAnchor14.mTarget == null ? null : constraintAnchor14.mTarget.mSolverVariable;
            SolverVariable solverVariable15 = constraintAnchor15.mTarget == null ? null : constraintAnchor15.mTarget.mSolverVariable;
            if(constraintWidget1 != constraintWidget3) {
                ConstraintAnchor constraintAnchor16 = constraintWidget1.mListAnchors[v1 + 1];
                if(constraintAnchor16.mTarget != null) {
                    constraintWidget6 = constraintAnchor16.mTarget.mSolverVariable;
                }
                solverVariable15 = constraintWidget6;
            }
            if(constraintWidget2 == constraintWidget3) {
                constraintAnchor14 = constraintWidget2.mListAnchors[v1];
                constraintAnchor15 = constraintWidget2.mListAnchors[v1 + 1];
            }
            if(solverVariable14 != null && solverVariable15 != null) {
                int v17 = constraintAnchor14.getMargin();
                int v18 = constraintWidget3.mListAnchors[v1 + 1].getMargin();
                linearSystem0.addCentering(constraintAnchor14.mSolverVariable, solverVariable14, v17, 0.5f, solverVariable15, constraintAnchor15.mSolverVariable, v18, 5);
            }
        }
    }

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer0, LinearSystem linearSystem0, ArrayList arrayList0, int v) {
        int v3;
        ChainHead[] arr_chainHead;
        int v2;
        if(v == 0) {
            v2 = constraintWidgetContainer0.mHorizontalChainsSize;
            arr_chainHead = constraintWidgetContainer0.mHorizontalChainsArray;
            v3 = 0;
        }
        else {
            v2 = constraintWidgetContainer0.mVerticalChainsSize;
            arr_chainHead = constraintWidgetContainer0.mVerticalChainsArray;
            v3 = 2;
        }
        for(int v1 = 0; v1 < v2; ++v1) {
            ChainHead chainHead0 = arr_chainHead[v1];
            chainHead0.define();
            if(arrayList0 == null || arrayList0.contains(chainHead0.mFirst)) {
                Chain.applyChainConstraints(constraintWidgetContainer0, linearSystem0, v, v3, chainHead0);
            }
        }
    }
}

