package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;

public class ChainRun extends WidgetRun {
    private int mChainStyle;
    ArrayList mWidgets;

    public ChainRun(ConstraintWidget constraintWidget0, int v) {
        super(constraintWidget0);
        this.mWidgets = new ArrayList();
        this.orientation = v;
        this.build();
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void apply() {
        for(Object object0: this.mWidgets) {
            ((WidgetRun)object0).apply();
        }
        int v = this.mWidgets.size();
        if(v < 1) {
            return;
        }
        ConstraintWidget constraintWidget0 = ((WidgetRun)this.mWidgets.get(0)).mWidget;
        ConstraintWidget constraintWidget1 = ((WidgetRun)this.mWidgets.get(v - 1)).mWidget;
        if(this.orientation == 0) {
            ConstraintAnchor constraintAnchor0 = constraintWidget0.mLeft;
            ConstraintAnchor constraintAnchor1 = constraintWidget1.mRight;
            DependencyNode dependencyNode0 = this.getTarget(constraintAnchor0, 0);
            int v1 = constraintAnchor0.getMargin();
            ConstraintWidget constraintWidget2 = this.getFirstVisibleWidget();
            if(constraintWidget2 != null) {
                v1 = constraintWidget2.mLeft.getMargin();
            }
            if(dependencyNode0 != null) {
                this.addTarget(this.start, dependencyNode0, v1);
            }
            DependencyNode dependencyNode1 = this.getTarget(constraintAnchor1, 0);
            int v2 = constraintAnchor1.getMargin();
            ConstraintWidget constraintWidget3 = this.getLastVisibleWidget();
            if(constraintWidget3 != null) {
                v2 = constraintWidget3.mRight.getMargin();
            }
            if(dependencyNode1 != null) {
                this.addTarget(this.end, dependencyNode1, -v2);
            }
        }
        else {
            ConstraintAnchor constraintAnchor2 = constraintWidget0.mTop;
            ConstraintAnchor constraintAnchor3 = constraintWidget1.mBottom;
            DependencyNode dependencyNode2 = this.getTarget(constraintAnchor2, 1);
            int v3 = constraintAnchor2.getMargin();
            ConstraintWidget constraintWidget4 = this.getFirstVisibleWidget();
            if(constraintWidget4 != null) {
                v3 = constraintWidget4.mTop.getMargin();
            }
            if(dependencyNode2 != null) {
                this.addTarget(this.start, dependencyNode2, v3);
            }
            DependencyNode dependencyNode3 = this.getTarget(constraintAnchor3, 1);
            int v4 = constraintAnchor3.getMargin();
            ConstraintWidget constraintWidget5 = this.getLastVisibleWidget();
            if(constraintWidget5 != null) {
                v4 = constraintWidget5.mBottom.getMargin();
            }
            if(dependencyNode3 != null) {
                this.addTarget(this.end, dependencyNode3, -v4);
            }
        }
        this.start.updateDelegate = this;
        this.end.updateDelegate = this;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        for(int v = 0; v < this.mWidgets.size(); ++v) {
            ((WidgetRun)this.mWidgets.get(v)).applyToWidget();
        }
    }

    private void build() {
        ConstraintWidget constraintWidget2;
        ConstraintWidget constraintWidget0 = this.mWidget;
        for(ConstraintWidget constraintWidget1 = constraintWidget0.getPreviousChainMember(this.orientation); true; constraintWidget1 = constraintWidget0.getPreviousChainMember(this.orientation)) {
            constraintWidget2 = constraintWidget0;
            constraintWidget0 = constraintWidget1;
            if(constraintWidget0 == null) {
                break;
            }
        }
        this.mWidget = constraintWidget2;
        this.mWidgets.add(constraintWidget2.getRun(this.orientation));
        for(ConstraintWidget constraintWidget3 = constraintWidget2.getNextChainMember(this.orientation); constraintWidget3 != null; constraintWidget3 = constraintWidget3.getNextChainMember(this.orientation)) {
            this.mWidgets.add(constraintWidget3.getRun(this.orientation));
        }
        for(Object object0: this.mWidgets) {
            WidgetRun widgetRun0 = (WidgetRun)object0;
            if(this.orientation == 0) {
                widgetRun0.mWidget.horizontalChainRun = this;
            }
            else if(this.orientation == 1) {
                widgetRun0.mWidget.verticalChainRun = this;
            }
        }
        if(this.orientation == 0 && ((ConstraintWidgetContainer)this.mWidget.getParent()).isRtl() && this.mWidgets.size() > 1) {
            this.mWidget = ((WidgetRun)this.mWidgets.get(this.mWidgets.size() - 1)).mWidget;
        }
        this.mChainStyle = this.orientation == 0 ? this.mWidget.getHorizontalChainStyle() : this.mWidget.getVerticalChainStyle();
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void clear() {
        this.mRunGroup = null;
        for(Object object0: this.mWidgets) {
            ((WidgetRun)object0).clear();
        }
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for(int v = 0; v < this.mWidgets.size(); ++v) {
            WidgetRun widgetRun0 = (WidgetRun)this.mWidgets.get(v);
            if(widgetRun0.mWidget.getVisibility() != 8) {
                return widgetRun0.mWidget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for(int v = this.mWidgets.size() - 1; v >= 0; --v) {
            WidgetRun widgetRun0 = (WidgetRun)this.mWidgets.get(v);
            if(widgetRun0.mWidget.getVisibility() != 8) {
                return widgetRun0.mWidget;
            }
        }
        return null;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public long getWrapDimension() {
        int v = this.mWidgets.size();
        long v1 = 0L;
        for(int v2 = 0; v2 < v; ++v2) {
            WidgetRun widgetRun0 = (WidgetRun)this.mWidgets.get(v2);
            v1 = v1 + ((long)widgetRun0.start.mMargin) + widgetRun0.getWrapDimension() + ((long)widgetRun0.end.mMargin);
        }
        return v1;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        int v = this.mWidgets.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(!((WidgetRun)this.mWidgets.get(v1)).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("ChainRun ");
        stringBuilder0.append((this.orientation == 0 ? "horizontal : " : "vertical : "));
        for(Object object0: this.mWidgets) {
            stringBuilder0.append("<");
            stringBuilder0.append(((WidgetRun)object0));
            stringBuilder0.append("> ");
        }
        return stringBuilder0.toString();
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void update(Dependency dependency0) {
        int v27;
        int v23;
        boolean z2;
        int v20;
        float f3;
        int v19;
        int v18;
        int v17;
        float f;
        int v9;
        int v8;
        int v3;
        if(this.start.resolved && this.end.resolved) {
            ConstraintWidget constraintWidget0 = this.mWidget.getParent();
            boolean z = constraintWidget0 instanceof ConstraintWidgetContainer ? ((ConstraintWidgetContainer)constraintWidget0).isRtl() : false;
            int v = this.end.value - this.start.value;
            int v1 = this.mWidgets.size();
            int v2;
            for(v2 = 0; true; ++v2) {
                v3 = -1;
                if(v2 >= v1) {
                    v2 = -1;
                    break;
                }
                if(((WidgetRun)this.mWidgets.get(v2)).mWidget.getVisibility() != 8) {
                    break;
                }
            }
            int v4 = v1 - 1;
            while(v4 >= 0) {
                if(((WidgetRun)this.mWidgets.get(v4)).mWidget.getVisibility() == 8) {
                    --v4;
                }
                else {
                    v3 = v4;
                    if(true) {
                        break;
                    }
                }
            }
            int v5 = 0;
            int v6 = 0;
            while(v6 < 2) {
                v8 = 0;
                v9 = 0;
                int v10 = 0;
                f = 0.0f;
                for(int v7 = 0; v7 < v1; ++v7) {
                    WidgetRun widgetRun0 = (WidgetRun)this.mWidgets.get(v7);
                    if(widgetRun0.mWidget.getVisibility() != 8) {
                        ++v10;
                        if(v7 > 0 && v7 >= v2) {
                            v8 += widgetRun0.start.mMargin;
                        }
                        int v11 = widgetRun0.mDimension.value;
                        boolean z1 = widgetRun0.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT;
                        if(z1) {
                            if(this.orientation == 0 && !widgetRun0.mWidget.mHorizontalRun.mDimension.resolved || this.orientation == 1 && !widgetRun0.mWidget.mVerticalRun.mDimension.resolved) {
                                return;
                            }
                        }
                        else if(widgetRun0.matchConstraintsType == 1 && v6 == 0) {
                            v11 = widgetRun0.mDimension.wrapValue;
                            ++v9;
                            z1 = true;
                        }
                        else if(widgetRun0.mDimension.resolved) {
                            z1 = true;
                        }
                        if(z1) {
                            v8 += v11;
                        }
                        else {
                            ++v9;
                            float f1 = widgetRun0.mWidget.mWeight[this.orientation];
                            if(f1 >= 0.0f) {
                                f += f1;
                            }
                        }
                        if(v7 < v1 - 1 && v7 < v3) {
                            v8 -= widgetRun0.end.mMargin;
                        }
                    }
                }
                if(v8 < v || v9 == 0) {
                    v5 = v10;
                }
                else {
                    ++v6;
                    continue;
                }
                goto label_65;
            }
            v8 = 0;
            v9 = 0;
            f = 0.0f;
        label_65:
            int v12 = z ? this.end.value : this.start.value;
            if(v8 > v) {
                v12 = z ? v12 + ((int)(((float)(v8 - v)) / 2.0f + 0.5f)) : v12 - ((int)(((float)(v8 - v)) / 2.0f + 0.5f));
            }
            if(v9 > 0) {
                float f2 = (float)(v - v8);
                int v13 = (int)(f2 / ((float)v9) + 0.5f);
                int v14 = 0;
                int v15 = 0;
                while(v14 < v1) {
                    WidgetRun widgetRun1 = (WidgetRun)this.mWidgets.get(v14);
                    if(widgetRun1.mWidget.getVisibility() == 8 || widgetRun1.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT || widgetRun1.mDimension.resolved) {
                        v18 = v12;
                        f3 = f2;
                        v20 = v13;
                    }
                    else {
                        int v16 = f > 0.0f ? ((int)(widgetRun1.mWidget.mWeight[this.orientation] * f2 / f + 0.5f)) : v13;
                        if(this.orientation == 0) {
                            v17 = widgetRun1.mWidget.mMatchConstraintMaxWidth;
                            v18 = v12;
                            v19 = widgetRun1.mWidget.mMatchConstraintMinWidth;
                        }
                        else {
                            v18 = v12;
                            v17 = widgetRun1.mWidget.mMatchConstraintMaxHeight;
                            v19 = widgetRun1.mWidget.mMatchConstraintMinHeight;
                        }
                        f3 = f2;
                        v20 = v13;
                        int v21 = widgetRun1.matchConstraintsType == 1 ? Math.min(v16, widgetRun1.mDimension.wrapValue) : v16;
                        int v22 = v17 <= 0 ? Math.max(v19, v21) : Math.min(v17, Math.max(v19, v21));
                        if(v22 != v16) {
                            ++v15;
                            v16 = v22;
                        }
                        widgetRun1.mDimension.resolve(v16);
                    }
                    ++v14;
                    v12 = v18;
                    f2 = f3;
                    v13 = v20;
                }
                z2 = z;
                v23 = v12;
                if(v15 > 0) {
                    v9 -= v15;
                    int v25 = 0;
                    for(int v24 = 0; v24 < v1; ++v24) {
                        WidgetRun widgetRun2 = (WidgetRun)this.mWidgets.get(v24);
                        if(widgetRun2.mWidget.getVisibility() != 8) {
                            if(v24 > 0 && v24 >= v2) {
                                v25 += widgetRun2.start.mMargin;
                            }
                            v25 += widgetRun2.mDimension.value;
                            if(v24 < v1 - 1 && v24 < v3) {
                                v25 -= widgetRun2.end.mMargin;
                            }
                        }
                    }
                    v8 = v25;
                }
                if(this.mChainStyle == 2 && v15 == 0) {
                    this.mChainStyle = 0;
                }
            }
            else {
                z2 = z;
                v23 = v12;
            }
            if(v8 > v) {
                this.mChainStyle = 2;
            }
            if(v5 > 0 && v9 == 0 && v2 == v3) {
                this.mChainStyle = 2;
            }
            int v26 = this.mChainStyle;
            if(v26 == 1) {
                if(v5 > 1) {
                    v27 = (v - v8) / (v5 - 1);
                }
                else {
                    v27 = v5 == 1 ? (v - v8) / 2 : 0;
                }
                if(v9 > 0) {
                    v27 = 0;
                }
                int v28 = v23;
                for(int v29 = 0; v29 < v1; ++v29) {
                    WidgetRun widgetRun3 = (WidgetRun)this.mWidgets.get((z2 ? v1 - (v29 + 1) : v29));
                    if(widgetRun3.mWidget.getVisibility() == 8) {
                        widgetRun3.start.resolve(v28);
                        widgetRun3.end.resolve(v28);
                    }
                    else {
                        if(v29 > 0) {
                            v28 = z2 ? v28 - v27 : v28 + v27;
                        }
                        if(v29 > 0 && v29 >= v2) {
                            v28 = z2 ? v28 - widgetRun3.start.mMargin : v28 + widgetRun3.start.mMargin;
                        }
                        if(z2) {
                            widgetRun3.end.resolve(v28);
                        }
                        else {
                            widgetRun3.start.resolve(v28);
                        }
                        int v30 = widgetRun3.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT || widgetRun3.matchConstraintsType != 1 ? widgetRun3.mDimension.value : widgetRun3.mDimension.wrapValue;
                        v28 = z2 ? v28 - v30 : v28 + v30;
                        if(z2) {
                            widgetRun3.start.resolve(v28);
                        }
                        else {
                            widgetRun3.end.resolve(v28);
                        }
                        widgetRun3.mResolved = true;
                        if(v29 < v1 - 1 && v29 < v3) {
                            v28 = z2 ? v28 - -widgetRun3.end.mMargin : v28 - widgetRun3.end.mMargin;
                        }
                    }
                }
                return;
            }
            if(v26 == 0) {
                int v31 = (v - v8) / (v5 + 1);
                if(v9 > 0) {
                    v31 = 0;
                }
                int v32 = v23;
                for(int v33 = 0; v33 < v1; ++v33) {
                    WidgetRun widgetRun4 = (WidgetRun)this.mWidgets.get((z2 ? v1 - (v33 + 1) : v33));
                    if(widgetRun4.mWidget.getVisibility() == 8) {
                        widgetRun4.start.resolve(v32);
                        widgetRun4.end.resolve(v32);
                    }
                    else {
                        int v34 = z2 ? v32 - v31 : v32 + v31;
                        if(v33 > 0 && v33 >= v2) {
                            v34 = z2 ? v34 - widgetRun4.start.mMargin : v34 + widgetRun4.start.mMargin;
                        }
                        if(z2) {
                            widgetRun4.end.resolve(v34);
                        }
                        else {
                            widgetRun4.start.resolve(v34);
                        }
                        int v35 = widgetRun4.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT || widgetRun4.matchConstraintsType != 1 ? widgetRun4.mDimension.value : Math.min(widgetRun4.mDimension.value, widgetRun4.mDimension.wrapValue);
                        v32 = z2 ? v34 - v35 : v34 + v35;
                        if(z2) {
                            widgetRun4.start.resolve(v32);
                        }
                        else {
                            widgetRun4.end.resolve(v32);
                        }
                        if(v33 < v1 - 1 && v33 < v3) {
                            v32 = z2 ? v32 - -widgetRun4.end.mMargin : v32 - widgetRun4.end.mMargin;
                        }
                    }
                }
                return;
            }
            if(v26 == 2) {
                float f4 = this.orientation == 0 ? this.mWidget.getHorizontalBiasPercent() : this.mWidget.getVerticalBiasPercent();
                if(z2) {
                    f4 = 1.0f - f4;
                }
                int v36 = ((int)(((float)(v - v8)) * f4 + 0.5f)) >= 0 && v9 <= 0 ? ((int)(((float)(v - v8)) * f4 + 0.5f)) : 0;
                int v37 = z2 ? v23 - v36 : v23 + v36;
                for(int v38 = 0; v38 < v1; ++v38) {
                    WidgetRun widgetRun5 = (WidgetRun)this.mWidgets.get((z2 ? v1 - (v38 + 1) : v38));
                    if(widgetRun5.mWidget.getVisibility() == 8) {
                        widgetRun5.start.resolve(v37);
                        widgetRun5.end.resolve(v37);
                    }
                    else {
                        if(v38 > 0 && v38 >= v2) {
                            v37 = z2 ? v37 - widgetRun5.start.mMargin : v37 + widgetRun5.start.mMargin;
                        }
                        if(z2) {
                            widgetRun5.end.resolve(v37);
                        }
                        else {
                            widgetRun5.start.resolve(v37);
                        }
                        int v39 = widgetRun5.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT || widgetRun5.matchConstraintsType != 1 ? widgetRun5.mDimension.value : widgetRun5.mDimension.wrapValue;
                        v37 = z2 ? v37 - v39 : v37 + v39;
                        if(z2) {
                            widgetRun5.start.resolve(v37);
                        }
                        else {
                            widgetRun5.end.resolve(v37);
                        }
                        if(v38 < v1 - 1 && v38 < v3) {
                            v37 = z2 ? v37 - -widgetRun5.end.mMargin : v37 - widgetRun5.end.mMargin;
                        }
                    }
                }
            }
        }
    }
}

