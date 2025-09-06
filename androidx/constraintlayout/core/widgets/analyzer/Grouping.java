package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor.Type;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Flow;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;

public class Grouping {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_GROUPING = false;
    private static final boolean FORCE_USE = true;

    public static WidgetGroup findDependents(ConstraintWidget constraintWidget0, int v, ArrayList arrayList0, WidgetGroup widgetGroup0) {
        int v1 = v == 0 ? constraintWidget0.horizontalGroup : constraintWidget0.verticalGroup;
        int v2 = 0;
        if(v1 != -1 && (widgetGroup0 == null || v1 != widgetGroup0.getId())) {
            for(int v3 = 0; v3 < arrayList0.size(); ++v3) {
                WidgetGroup widgetGroup1 = (WidgetGroup)arrayList0.get(v3);
                if(widgetGroup1.getId() == v1) {
                    if(widgetGroup0 != null) {
                        widgetGroup0.moveTo(v, widgetGroup1);
                        arrayList0.remove(widgetGroup0);
                    }
                    widgetGroup0 = widgetGroup1;
                    break;
                }
            }
        }
        else if(v1 != -1) {
            return widgetGroup0;
        }
        if(widgetGroup0 == null) {
            if(constraintWidget0 instanceof HelperWidget) {
                int v4 = ((HelperWidget)constraintWidget0).findGroupInDependents(v);
                if(v4 != -1) {
                    for(int v5 = 0; v5 < arrayList0.size(); ++v5) {
                        WidgetGroup widgetGroup2 = (WidgetGroup)arrayList0.get(v5);
                        if(widgetGroup2.getId() == v4) {
                            widgetGroup0 = widgetGroup2;
                            break;
                        }
                    }
                }
            }
            if(widgetGroup0 == null) {
                widgetGroup0 = new WidgetGroup(v);
            }
            arrayList0.add(widgetGroup0);
        }
        if(widgetGroup0.add(constraintWidget0)) {
            if(constraintWidget0 instanceof Guideline) {
                ConstraintAnchor constraintAnchor0 = ((Guideline)constraintWidget0).getAnchor();
                if(((Guideline)constraintWidget0).getOrientation() == 0) {
                    v2 = 1;
                }
                constraintAnchor0.findDependents(v2, arrayList0, widgetGroup0);
            }
            if(v == 0) {
                constraintWidget0.horizontalGroup = widgetGroup0.getId();
                constraintWidget0.mLeft.findDependents(0, arrayList0, widgetGroup0);
                constraintWidget0.mRight.findDependents(0, arrayList0, widgetGroup0);
            }
            else {
                constraintWidget0.verticalGroup = widgetGroup0.getId();
                constraintWidget0.mTop.findDependents(v, arrayList0, widgetGroup0);
                constraintWidget0.mBaseline.findDependents(v, arrayList0, widgetGroup0);
                constraintWidget0.mBottom.findDependents(v, arrayList0, widgetGroup0);
            }
            constraintWidget0.mCenter.findDependents(v, arrayList0, widgetGroup0);
        }
        return widgetGroup0;
    }

    private static WidgetGroup findGroup(ArrayList arrayList0, int v) {
        int v1 = arrayList0.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            WidgetGroup widgetGroup0 = (WidgetGroup)arrayList0.get(v2);
            if(v == widgetGroup0.getId()) {
                return widgetGroup0;
            }
        }
        return null;
    }

    public static boolean simpleSolvingPass(ConstraintWidgetContainer constraintWidgetContainer0, Measurer basicMeasure$Measurer0) {
        WidgetGroup widgetGroup4;
        ArrayList arrayList0 = constraintWidgetContainer0.getChildren();
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)arrayList0.get(v1);
            if(!Grouping.validInGroup(constraintWidgetContainer0.getHorizontalDimensionBehaviour(), constraintWidgetContainer0.getVerticalDimensionBehaviour(), constraintWidget0.getHorizontalDimensionBehaviour(), constraintWidget0.getVerticalDimensionBehaviour())) {
                return false;
            }
            if(constraintWidget0 instanceof Flow) {
                return false;
            }
        }
        if(constraintWidgetContainer0.mMetrics != null) {
            ++constraintWidgetContainer0.mMetrics.grouping;
        }
        ArrayList arrayList1 = null;
        ArrayList arrayList2 = null;
        ArrayList arrayList3 = null;
        ArrayList arrayList4 = null;
        ArrayList arrayList5 = null;
        ArrayList arrayList6 = null;
        for(int v2 = 0; v2 < v; ++v2) {
            ConstraintWidget constraintWidget1 = (ConstraintWidget)arrayList0.get(v2);
            if(!Grouping.validInGroup(constraintWidgetContainer0.getHorizontalDimensionBehaviour(), constraintWidgetContainer0.getVerticalDimensionBehaviour(), constraintWidget1.getHorizontalDimensionBehaviour(), constraintWidget1.getVerticalDimensionBehaviour())) {
                ConstraintWidgetContainer.measure(0, constraintWidget1, basicMeasure$Measurer0, constraintWidgetContainer0.mMeasure, Measure.SELF_DIMENSIONS);
            }
            if(constraintWidget1 instanceof Guideline) {
                if(((Guideline)constraintWidget1).getOrientation() == 0) {
                    if(arrayList3 == null) {
                        arrayList3 = new ArrayList();
                    }
                    arrayList3.add(((Guideline)constraintWidget1));
                }
                if(((Guideline)constraintWidget1).getOrientation() == 1) {
                    if(arrayList1 == null) {
                        arrayList1 = new ArrayList();
                    }
                    arrayList1.add(((Guideline)constraintWidget1));
                }
            }
            if(constraintWidget1 instanceof HelperWidget) {
                if(constraintWidget1 instanceof Barrier) {
                    if(((Barrier)constraintWidget1).getOrientation() == 0) {
                        if(arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        arrayList2.add(((Barrier)constraintWidget1));
                    }
                    if(((Barrier)constraintWidget1).getOrientation() == 1) {
                        if(arrayList4 == null) {
                            arrayList4 = new ArrayList();
                        }
                        arrayList4.add(((Barrier)constraintWidget1));
                    }
                }
                else {
                    if(arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    arrayList2.add(((HelperWidget)constraintWidget1));
                    if(arrayList4 == null) {
                        arrayList4 = new ArrayList();
                    }
                    arrayList4.add(((HelperWidget)constraintWidget1));
                }
            }
            if(constraintWidget1.mLeft.mTarget == null && constraintWidget1.mRight.mTarget == null && !(constraintWidget1 instanceof Guideline) && !(constraintWidget1 instanceof Barrier)) {
                if(arrayList5 == null) {
                    arrayList5 = new ArrayList();
                }
                arrayList5.add(constraintWidget1);
            }
            if(constraintWidget1.mTop.mTarget == null && constraintWidget1.mBottom.mTarget == null && constraintWidget1.mBaseline.mTarget == null && !(constraintWidget1 instanceof Guideline) && !(constraintWidget1 instanceof Barrier)) {
                if(arrayList6 == null) {
                    arrayList6 = new ArrayList();
                }
                arrayList6.add(constraintWidget1);
            }
        }
        ArrayList arrayList7 = new ArrayList();
        if(arrayList1 != null) {
            for(Object object0: arrayList1) {
                Grouping.findDependents(((Guideline)object0), 0, arrayList7, null);
            }
        }
        if(arrayList2 != null) {
            for(Object object1: arrayList2) {
                WidgetGroup widgetGroup0 = Grouping.findDependents(((HelperWidget)object1), 0, arrayList7, null);
                ((HelperWidget)object1).addDependents(arrayList7, 0, widgetGroup0);
                widgetGroup0.cleanup(arrayList7);
            }
        }
        ConstraintAnchor constraintAnchor0 = constraintWidgetContainer0.getAnchor(Type.LEFT);
        if(constraintAnchor0.getDependents() != null) {
            for(Object object2: constraintAnchor0.getDependents()) {
                Grouping.findDependents(((ConstraintAnchor)object2).mOwner, 0, arrayList7, null);
            }
        }
        ConstraintAnchor constraintAnchor1 = constraintWidgetContainer0.getAnchor(Type.RIGHT);
        if(constraintAnchor1.getDependents() != null) {
            for(Object object3: constraintAnchor1.getDependents()) {
                Grouping.findDependents(((ConstraintAnchor)object3).mOwner, 0, arrayList7, null);
            }
        }
        ConstraintAnchor constraintAnchor2 = constraintWidgetContainer0.getAnchor(Type.CENTER);
        if(constraintAnchor2.getDependents() != null) {
            for(Object object4: constraintAnchor2.getDependents()) {
                Grouping.findDependents(((ConstraintAnchor)object4).mOwner, 0, arrayList7, null);
            }
        }
        if(arrayList5 != null) {
            for(Object object5: arrayList5) {
                Grouping.findDependents(((ConstraintWidget)object5), 0, arrayList7, null);
            }
        }
        if(arrayList3 != null) {
            for(Object object6: arrayList3) {
                Grouping.findDependents(((Guideline)object6), 1, arrayList7, null);
            }
        }
        if(arrayList4 != null) {
            for(Object object7: arrayList4) {
                WidgetGroup widgetGroup1 = Grouping.findDependents(((HelperWidget)object7), 1, arrayList7, null);
                ((HelperWidget)object7).addDependents(arrayList7, 1, widgetGroup1);
                widgetGroup1.cleanup(arrayList7);
            }
        }
        ConstraintAnchor constraintAnchor3 = constraintWidgetContainer0.getAnchor(Type.TOP);
        if(constraintAnchor3.getDependents() != null) {
            for(Object object8: constraintAnchor3.getDependents()) {
                Grouping.findDependents(((ConstraintAnchor)object8).mOwner, 1, arrayList7, null);
            }
        }
        ConstraintAnchor constraintAnchor4 = constraintWidgetContainer0.getAnchor(Type.BASELINE);
        if(constraintAnchor4.getDependents() != null) {
            for(Object object9: constraintAnchor4.getDependents()) {
                Grouping.findDependents(((ConstraintAnchor)object9).mOwner, 1, arrayList7, null);
            }
        }
        ConstraintAnchor constraintAnchor5 = constraintWidgetContainer0.getAnchor(Type.BOTTOM);
        if(constraintAnchor5.getDependents() != null) {
            for(Object object10: constraintAnchor5.getDependents()) {
                Grouping.findDependents(((ConstraintAnchor)object10).mOwner, 1, arrayList7, null);
            }
        }
        ConstraintAnchor constraintAnchor6 = constraintWidgetContainer0.getAnchor(Type.CENTER);
        if(constraintAnchor6.getDependents() != null) {
            for(Object object11: constraintAnchor6.getDependents()) {
                Grouping.findDependents(((ConstraintAnchor)object11).mOwner, 1, arrayList7, null);
            }
        }
        if(arrayList6 != null) {
            for(Object object12: arrayList6) {
                Grouping.findDependents(((ConstraintWidget)object12), 1, arrayList7, null);
            }
        }
        for(int v3 = 0; v3 < v; ++v3) {
            ConstraintWidget constraintWidget2 = (ConstraintWidget)arrayList0.get(v3);
            if(constraintWidget2.oppositeDimensionsTied()) {
                WidgetGroup widgetGroup2 = Grouping.findGroup(arrayList7, constraintWidget2.horizontalGroup);
                WidgetGroup widgetGroup3 = Grouping.findGroup(arrayList7, constraintWidget2.verticalGroup);
                if(widgetGroup2 != null && widgetGroup3 != null) {
                    widgetGroup2.moveTo(0, widgetGroup3);
                    widgetGroup3.setOrientation(2);
                    arrayList7.remove(widgetGroup2);
                }
            }
        }
        if(arrayList7.size() <= 1) {
            return false;
        }
        if(constraintWidgetContainer0.getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT) {
            widgetGroup4 = null;
            int v4 = 0;
            for(Object object13: arrayList7) {
                WidgetGroup widgetGroup5 = (WidgetGroup)object13;
                if(widgetGroup5.getOrientation() != 1) {
                    widgetGroup5.setAuthoritative(false);
                    int v5 = widgetGroup5.measureWrap(constraintWidgetContainer0.getSystem(), 0);
                    if(v5 > v4) {
                        widgetGroup4 = widgetGroup5;
                        v4 = v5;
                    }
                }
            }
            if(widgetGroup4 == null) {
                widgetGroup4 = null;
            }
            else {
                constraintWidgetContainer0.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                constraintWidgetContainer0.setWidth(v4);
                widgetGroup4.setAuthoritative(true);
            }
        }
        else {
            widgetGroup4 = null;
        }
        if(constraintWidgetContainer0.getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT) {
            WidgetGroup widgetGroup6 = null;
            int v6 = 0;
            for(Object object14: arrayList7) {
                WidgetGroup widgetGroup7 = (WidgetGroup)object14;
                if(widgetGroup7.getOrientation() != 0) {
                    widgetGroup7.setAuthoritative(false);
                    int v7 = widgetGroup7.measureWrap(constraintWidgetContainer0.getSystem(), 1);
                    if(v7 > v6) {
                        widgetGroup6 = widgetGroup7;
                        v6 = v7;
                    }
                }
            }
            if(widgetGroup6 != null) {
                constraintWidgetContainer0.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                constraintWidgetContainer0.setHeight(v6);
                widgetGroup6.setAuthoritative(true);
                return widgetGroup4 != null || widgetGroup6 != null;
            }
        }
        return widgetGroup4 != null;
    }

    // 去混淆评级： 低(40)
    public static boolean validInGroup(DimensionBehaviour constraintWidget$DimensionBehaviour0, DimensionBehaviour constraintWidget$DimensionBehaviour1, DimensionBehaviour constraintWidget$DimensionBehaviour2, DimensionBehaviour constraintWidget$DimensionBehaviour3) {
        return constraintWidget$DimensionBehaviour2 == DimensionBehaviour.FIXED || constraintWidget$DimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour2 == DimensionBehaviour.MATCH_PARENT && constraintWidget$DimensionBehaviour0 != DimensionBehaviour.WRAP_CONTENT || (constraintWidget$DimensionBehaviour3 == DimensionBehaviour.FIXED || constraintWidget$DimensionBehaviour3 == DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour3 == DimensionBehaviour.MATCH_PARENT && constraintWidget$DimensionBehaviour1 != DimensionBehaviour.WRAP_CONTENT);
    }
}

