package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class DependencyGraph {
    private static final boolean DEBUG = false;
    private static final boolean USE_GROUPS = true;
    private ConstraintWidgetContainer mContainer;
    ArrayList mGroups;
    private Measure mMeasure;
    private Measurer mMeasurer;
    private boolean mNeedBuildGraph;
    private boolean mNeedRedoMeasures;
    private ArrayList mRunGroups;
    private ArrayList mRuns;
    private ConstraintWidgetContainer mWidgetcontainer;

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer0) {
        this.mNeedBuildGraph = true;
        this.mNeedRedoMeasures = true;
        this.mRuns = new ArrayList();
        this.mRunGroups = new ArrayList();
        this.mMeasurer = null;
        this.mMeasure = new Measure();
        this.mGroups = new ArrayList();
        this.mWidgetcontainer = constraintWidgetContainer0;
        this.mContainer = constraintWidgetContainer0;
    }

    private void applyGroup(DependencyNode dependencyNode0, int v, int v1, DependencyNode dependencyNode1, ArrayList arrayList0, RunGroup runGroup0) {
        WidgetRun widgetRun0 = dependencyNode0.mRun;
        if(widgetRun0.mRunGroup == null && widgetRun0 != this.mWidgetcontainer.mHorizontalRun && widgetRun0 != this.mWidgetcontainer.mVerticalRun) {
            if(runGroup0 == null) {
                runGroup0 = new RunGroup(widgetRun0, v1);
                arrayList0.add(runGroup0);
            }
            widgetRun0.mRunGroup = runGroup0;
            runGroup0.add(widgetRun0);
            for(Object object0: widgetRun0.start.mDependencies) {
                Dependency dependency0 = (Dependency)object0;
                if(dependency0 instanceof DependencyNode) {
                    this.applyGroup(((DependencyNode)dependency0), v, 0, dependencyNode1, arrayList0, runGroup0);
                }
            }
            for(Object object1: widgetRun0.end.mDependencies) {
                Dependency dependency1 = (Dependency)object1;
                if(dependency1 instanceof DependencyNode) {
                    this.applyGroup(((DependencyNode)dependency1), v, 1, dependencyNode1, arrayList0, runGroup0);
                }
            }
            if(v == 1 && widgetRun0 instanceof VerticalWidgetRun) {
                for(Object object2: ((VerticalWidgetRun)widgetRun0).baseline.mDependencies) {
                    Dependency dependency2 = (Dependency)object2;
                    if(dependency2 instanceof DependencyNode) {
                        this.applyGroup(((DependencyNode)dependency2), 1, 2, dependencyNode1, arrayList0, runGroup0);
                    }
                }
            }
            for(Object object3: widgetRun0.start.mTargets) {
                if(((DependencyNode)object3) == dependencyNode1) {
                    runGroup0.dual = true;
                }
                this.applyGroup(((DependencyNode)object3), v, 0, dependencyNode1, arrayList0, runGroup0);
            }
            for(Object object4: widgetRun0.end.mTargets) {
                if(((DependencyNode)object4) == dependencyNode1) {
                    runGroup0.dual = true;
                }
                this.applyGroup(((DependencyNode)object4), v, 1, dependencyNode1, arrayList0, runGroup0);
            }
            if(v == 1 && widgetRun0 instanceof VerticalWidgetRun) {
                for(Object object5: ((VerticalWidgetRun)widgetRun0).baseline.mTargets) {
                    this.applyGroup(((DependencyNode)object5), 1, 2, dependencyNode1, arrayList0, runGroup0);
                }
            }
        }
    }

    private boolean basicMeasureWidgets(ConstraintWidgetContainer constraintWidgetContainer0) {
        for(Object object0: constraintWidgetContainer0.mChildren) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)object0;
            DimensionBehaviour constraintWidget$DimensionBehaviour0 = constraintWidget0.mListDimensionBehaviors[0];
            DimensionBehaviour constraintWidget$DimensionBehaviour1 = constraintWidget0.mListDimensionBehaviors[1];
            if(constraintWidget0.getVisibility() == 8) {
                constraintWidget0.measured = true;
            }
            else {
                if(constraintWidget0.mMatchConstraintPercentWidth < 1.0f && constraintWidget$DimensionBehaviour0 == DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget0.mMatchConstraintDefaultWidth = 2;
                }
                if(constraintWidget0.mMatchConstraintPercentHeight < 1.0f && constraintWidget$DimensionBehaviour1 == DimensionBehaviour.MATCH_CONSTRAINT) {
                    constraintWidget0.mMatchConstraintDefaultHeight = 2;
                }
                if(constraintWidget0.getDimensionRatio() > 0.0f) {
                    if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.MATCH_CONSTRAINT && (constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour1 == DimensionBehaviour.FIXED)) {
                        constraintWidget0.mMatchConstraintDefaultWidth = 3;
                    }
                    else if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.MATCH_CONSTRAINT && (constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour0 == DimensionBehaviour.FIXED)) {
                        constraintWidget0.mMatchConstraintDefaultHeight = 3;
                    }
                    else if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget$DimensionBehaviour1 == DimensionBehaviour.MATCH_CONSTRAINT) {
                        if(constraintWidget0.mMatchConstraintDefaultWidth == 0) {
                            constraintWidget0.mMatchConstraintDefaultWidth = 3;
                        }
                        if(constraintWidget0.mMatchConstraintDefaultHeight == 0) {
                            constraintWidget0.mMatchConstraintDefaultHeight = 3;
                        }
                    }
                }
                if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mMatchConstraintDefaultWidth == 1 && (constraintWidget0.mLeft.mTarget == null || constraintWidget0.mRight.mTarget == null)) {
                    constraintWidget$DimensionBehaviour0 = DimensionBehaviour.WRAP_CONTENT;
                }
                DimensionBehaviour constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviour0;
                if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mMatchConstraintDefaultHeight == 1 && (constraintWidget0.mTop.mTarget == null || constraintWidget0.mBottom.mTarget == null)) {
                    constraintWidget$DimensionBehaviour1 = DimensionBehaviour.WRAP_CONTENT;
                }
                DimensionBehaviour constraintWidget$DimensionBehaviour3 = constraintWidget$DimensionBehaviour1;
                constraintWidget0.mHorizontalRun.mDimensionBehavior = constraintWidget$DimensionBehaviour2;
                constraintWidget0.mHorizontalRun.matchConstraintsType = constraintWidget0.mMatchConstraintDefaultWidth;
                constraintWidget0.mVerticalRun.mDimensionBehavior = constraintWidget$DimensionBehaviour3;
                constraintWidget0.mVerticalRun.matchConstraintsType = constraintWidget0.mMatchConstraintDefaultHeight;
                if(constraintWidget$DimensionBehaviour2 != DimensionBehaviour.MATCH_PARENT && constraintWidget$DimensionBehaviour2 != DimensionBehaviour.FIXED && constraintWidget$DimensionBehaviour2 != DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour3 != DimensionBehaviour.MATCH_PARENT && constraintWidget$DimensionBehaviour3 != DimensionBehaviour.FIXED && constraintWidget$DimensionBehaviour3 != DimensionBehaviour.WRAP_CONTENT) {
                    if(constraintWidget$DimensionBehaviour2 == DimensionBehaviour.MATCH_CONSTRAINT && (constraintWidget$DimensionBehaviour3 == DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour3 == DimensionBehaviour.FIXED)) {
                        switch(constraintWidget0.mMatchConstraintDefaultWidth) {
                            case 1: {
                                this.measure(constraintWidget0, DimensionBehaviour.WRAP_CONTENT, 0, constraintWidget$DimensionBehaviour3, 0);
                                constraintWidget0.mHorizontalRun.mDimension.wrapValue = constraintWidget0.getWidth();
                                continue;
                            }
                            case 2: {
                                if(constraintWidgetContainer0.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED || constraintWidgetContainer0.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_PARENT) {
                                    int v = (int)(constraintWidget0.mMatchConstraintPercentWidth * ((float)constraintWidgetContainer0.getWidth()) + 0.5f);
                                    int v1 = constraintWidget0.getHeight();
                                    this.measure(constraintWidget0, DimensionBehaviour.FIXED, v, constraintWidget$DimensionBehaviour3, v1);
                                    constraintWidget0.mHorizontalRun.mDimension.resolve(constraintWidget0.getWidth());
                                    constraintWidget0.mVerticalRun.mDimension.resolve(constraintWidget0.getHeight());
                                    constraintWidget0.measured = true;
                                    continue;
                                }
                                break;
                            }
                            case 3: {
                                if(constraintWidget$DimensionBehaviour3 == DimensionBehaviour.WRAP_CONTENT) {
                                    this.measure(constraintWidget0, DimensionBehaviour.WRAP_CONTENT, 0, DimensionBehaviour.WRAP_CONTENT, 0);
                                }
                                int v2 = constraintWidget0.getHeight();
                                this.measure(constraintWidget0, DimensionBehaviour.FIXED, ((int)(((float)v2) * constraintWidget0.mDimensionRatio + 0.5f)), DimensionBehaviour.FIXED, v2);
                                constraintWidget0.mHorizontalRun.mDimension.resolve(constraintWidget0.getWidth());
                                constraintWidget0.mVerticalRun.mDimension.resolve(constraintWidget0.getHeight());
                                constraintWidget0.measured = true;
                                continue;
                            }
                            default: {
                                if(constraintWidget0.mListAnchors[0].mTarget == null || constraintWidget0.mListAnchors[1].mTarget == null) {
                                    this.measure(constraintWidget0, DimensionBehaviour.WRAP_CONTENT, 0, constraintWidget$DimensionBehaviour3, 0);
                                    constraintWidget0.mHorizontalRun.mDimension.resolve(constraintWidget0.getWidth());
                                    constraintWidget0.mVerticalRun.mDimension.resolve(constraintWidget0.getHeight());
                                    constraintWidget0.measured = true;
                                    continue;
                                }
                            }
                        }
                    }
                    if(constraintWidget$DimensionBehaviour3 == DimensionBehaviour.MATCH_CONSTRAINT && (constraintWidget$DimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour2 == DimensionBehaviour.FIXED)) {
                        switch(constraintWidget0.mMatchConstraintDefaultHeight) {
                            case 1: {
                                this.measure(constraintWidget0, constraintWidget$DimensionBehaviour2, 0, DimensionBehaviour.WRAP_CONTENT, 0);
                                constraintWidget0.mVerticalRun.mDimension.wrapValue = constraintWidget0.getHeight();
                                continue;
                            }
                            case 2: {
                                if(constraintWidgetContainer0.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED || constraintWidgetContainer0.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_PARENT) {
                                    float f = constraintWidget0.mMatchConstraintPercentHeight;
                                    int v3 = constraintWidget0.getWidth();
                                    int v4 = (int)(f * ((float)constraintWidgetContainer0.getHeight()) + 0.5f);
                                    this.measure(constraintWidget0, constraintWidget$DimensionBehaviour2, v3, DimensionBehaviour.FIXED, v4);
                                    constraintWidget0.mHorizontalRun.mDimension.resolve(constraintWidget0.getWidth());
                                    constraintWidget0.mVerticalRun.mDimension.resolve(constraintWidget0.getHeight());
                                    constraintWidget0.measured = true;
                                    continue;
                                }
                                break;
                            }
                            case 3: {
                                if(constraintWidget$DimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
                                    this.measure(constraintWidget0, DimensionBehaviour.WRAP_CONTENT, 0, DimensionBehaviour.WRAP_CONTENT, 0);
                                }
                                int v5 = constraintWidget0.getWidth();
                                this.measure(constraintWidget0, DimensionBehaviour.FIXED, v5, DimensionBehaviour.FIXED, ((int)(((float)v5) * (constraintWidget0.getDimensionRatioSide() == -1 ? 1.0f / constraintWidget0.mDimensionRatio : constraintWidget0.mDimensionRatio) + 0.5f)));
                                constraintWidget0.mHorizontalRun.mDimension.resolve(constraintWidget0.getWidth());
                                constraintWidget0.mVerticalRun.mDimension.resolve(constraintWidget0.getHeight());
                                constraintWidget0.measured = true;
                                continue;
                            }
                            default: {
                                if(constraintWidget0.mListAnchors[2].mTarget == null || constraintWidget0.mListAnchors[3].mTarget == null) {
                                    this.measure(constraintWidget0, DimensionBehaviour.WRAP_CONTENT, 0, constraintWidget$DimensionBehaviour3, 0);
                                    constraintWidget0.mHorizontalRun.mDimension.resolve(constraintWidget0.getWidth());
                                    constraintWidget0.mVerticalRun.mDimension.resolve(constraintWidget0.getHeight());
                                    constraintWidget0.measured = true;
                                    continue;
                                }
                            }
                        }
                    }
                    if(constraintWidget$DimensionBehaviour2 != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget$DimensionBehaviour3 != DimensionBehaviour.MATCH_CONSTRAINT) {
                        continue;
                    }
                    if(constraintWidget0.mMatchConstraintDefaultWidth == 1 || constraintWidget0.mMatchConstraintDefaultHeight == 1) {
                        this.measure(constraintWidget0, DimensionBehaviour.WRAP_CONTENT, 0, DimensionBehaviour.WRAP_CONTENT, 0);
                        constraintWidget0.mHorizontalRun.mDimension.wrapValue = constraintWidget0.getWidth();
                        constraintWidget0.mVerticalRun.mDimension.wrapValue = constraintWidget0.getHeight();
                    }
                    else {
                        if(constraintWidget0.mMatchConstraintDefaultHeight != 2 || constraintWidget0.mMatchConstraintDefaultWidth != 2 || constraintWidgetContainer0.mListDimensionBehaviors[0] != DimensionBehaviour.FIXED || constraintWidgetContainer0.mListDimensionBehaviors[1] != DimensionBehaviour.FIXED) {
                            continue;
                        }
                        float f1 = constraintWidget0.mMatchConstraintPercentHeight;
                        int v6 = (int)(constraintWidget0.mMatchConstraintPercentWidth * ((float)constraintWidgetContainer0.getWidth()) + 0.5f);
                        int v7 = (int)(f1 * ((float)constraintWidgetContainer0.getHeight()) + 0.5f);
                        this.measure(constraintWidget0, DimensionBehaviour.FIXED, v6, DimensionBehaviour.FIXED, v7);
                        constraintWidget0.mHorizontalRun.mDimension.resolve(constraintWidget0.getWidth());
                        constraintWidget0.mVerticalRun.mDimension.resolve(constraintWidget0.getHeight());
                        constraintWidget0.measured = true;
                    }
                }
                else {
                    int v8 = constraintWidget0.getWidth();
                    if(constraintWidget$DimensionBehaviour2 == DimensionBehaviour.MATCH_PARENT) {
                        v8 = constraintWidgetContainer0.getWidth() - constraintWidget0.mLeft.mMargin - constraintWidget0.mRight.mMargin;
                        constraintWidget$DimensionBehaviour2 = DimensionBehaviour.FIXED;
                    }
                    int v9 = constraintWidget0.getHeight();
                    if(constraintWidget$DimensionBehaviour3 == DimensionBehaviour.MATCH_PARENT) {
                        v9 = constraintWidgetContainer0.getHeight() - constraintWidget0.mTop.mMargin - constraintWidget0.mBottom.mMargin;
                        constraintWidget$DimensionBehaviour3 = DimensionBehaviour.FIXED;
                    }
                    this.measure(constraintWidget0, constraintWidget$DimensionBehaviour2, v8, constraintWidget$DimensionBehaviour3, v9);
                    constraintWidget0.mHorizontalRun.mDimension.resolve(constraintWidget0.getWidth());
                    constraintWidget0.mVerticalRun.mDimension.resolve(constraintWidget0.getHeight());
                    constraintWidget0.measured = true;
                }
            }
        }
        return false;
    }

    public void buildGraph() {
        this.buildGraph(this.mRuns);
        this.mGroups.clear();
        RunGroup.index = 0;
        this.findGroup(this.mWidgetcontainer.mHorizontalRun, 0, this.mGroups);
        this.findGroup(this.mWidgetcontainer.mVerticalRun, 1, this.mGroups);
        this.mNeedBuildGraph = false;
    }

    public void buildGraph(ArrayList arrayList0) {
        arrayList0.clear();
        this.mContainer.mHorizontalRun.clear();
        this.mContainer.mVerticalRun.clear();
        arrayList0.add(this.mContainer.mHorizontalRun);
        arrayList0.add(this.mContainer.mVerticalRun);
        Collection collection0 = null;
        for(Object object0: this.mContainer.mChildren) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)object0;
            if(constraintWidget0 instanceof Guideline) {
                arrayList0.add(new GuidelineReference(constraintWidget0));
            }
            else {
                if(constraintWidget0.isInHorizontalChain()) {
                    if(constraintWidget0.horizontalChainRun == null) {
                        constraintWidget0.horizontalChainRun = new ChainRun(constraintWidget0, 0);
                    }
                    if(collection0 == null) {
                        collection0 = new HashSet();
                    }
                    ((HashSet)collection0).add(constraintWidget0.horizontalChainRun);
                }
                else {
                    arrayList0.add(constraintWidget0.mHorizontalRun);
                }
                if(constraintWidget0.isInVerticalChain()) {
                    if(constraintWidget0.verticalChainRun == null) {
                        constraintWidget0.verticalChainRun = new ChainRun(constraintWidget0, 1);
                    }
                    if(collection0 == null) {
                        collection0 = new HashSet();
                    }
                    ((HashSet)collection0).add(constraintWidget0.verticalChainRun);
                }
                else {
                    arrayList0.add(constraintWidget0.mVerticalRun);
                }
                if(constraintWidget0 instanceof HelperWidget) {
                    arrayList0.add(new HelperReferences(constraintWidget0));
                }
            }
        }
        if(collection0 != null) {
            arrayList0.addAll(collection0);
        }
        for(Object object1: arrayList0) {
            ((WidgetRun)object1).clear();
        }
        for(Object object2: arrayList0) {
            WidgetRun widgetRun0 = (WidgetRun)object2;
            if(widgetRun0.mWidget != this.mContainer) {
                widgetRun0.apply();
            }
        }
    }

    private int computeWrap(ConstraintWidgetContainer constraintWidgetContainer0, int v) {
        int v1 = this.mGroups.size();
        long v2 = 0L;
        for(int v3 = 0; v3 < v1; ++v3) {
            v2 = Math.max(v2, ((RunGroup)this.mGroups.get(v3)).computeWrapSize(constraintWidgetContainer0, v));
        }
        return (int)v2;
    }

    public void defineTerminalWidgets(DimensionBehaviour constraintWidget$DimensionBehaviour0, DimensionBehaviour constraintWidget$DimensionBehaviour1) {
        if(this.mNeedBuildGraph) {
            this.buildGraph();
            boolean z = false;
            for(Object object0: this.mWidgetcontainer.mChildren) {
                ((ConstraintWidget)object0).isTerminalWidget[0] = true;
                ((ConstraintWidget)object0).isTerminalWidget[1] = true;
                if(((ConstraintWidget)object0) instanceof Barrier) {
                    z = true;
                }
            }
            if(!z) {
                for(Object object1: this.mGroups) {
                    ((RunGroup)object1).defineTerminalWidgets(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT, constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT);
                }
            }
        }
    }

    public boolean directMeasure(boolean z) {
        boolean z2;
        boolean z1 = false;
        if(this.mNeedBuildGraph || this.mNeedRedoMeasures) {
            for(Object object0: this.mWidgetcontainer.mChildren) {
                ((ConstraintWidget)object0).ensureWidgetRuns();
                ((ConstraintWidget)object0).measured = false;
                ((ConstraintWidget)object0).mHorizontalRun.reset();
                ((ConstraintWidget)object0).mVerticalRun.reset();
            }
            this.mWidgetcontainer.ensureWidgetRuns();
            this.mWidgetcontainer.measured = false;
            this.mWidgetcontainer.mHorizontalRun.reset();
            this.mWidgetcontainer.mVerticalRun.reset();
            this.mNeedRedoMeasures = false;
        }
        if(this.basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.mWidgetcontainer.setX(0);
        this.mWidgetcontainer.setY(0);
        DimensionBehaviour constraintWidget$DimensionBehaviour0 = this.mWidgetcontainer.getDimensionBehaviour(0);
        DimensionBehaviour constraintWidget$DimensionBehaviour1 = this.mWidgetcontainer.getDimensionBehaviour(1);
        if(this.mNeedBuildGraph) {
            this.buildGraph();
        }
        int v = this.mWidgetcontainer.getX();
        int v1 = this.mWidgetcontainer.getY();
        this.mWidgetcontainer.mHorizontalRun.start.resolve(v);
        this.mWidgetcontainer.mVerticalRun.start.resolve(v1);
        this.measureWidgets();
        if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT) {
            if(z) {
                for(Object object1: this.mRuns) {
                    if(!((WidgetRun)object1).supportsWrapComputation()) {
                        z = false;
                        break;
                    }
                    if(false) {
                        break;
                    }
                }
            }
            if(z && constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT) {
                this.mWidgetcontainer.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                this.mWidgetcontainer.setWidth(this.computeWrap(this.mWidgetcontainer, 0));
                this.mWidgetcontainer.mHorizontalRun.mDimension.resolve(this.mWidgetcontainer.getWidth());
            }
            if(z && constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT) {
                this.mWidgetcontainer.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                this.mWidgetcontainer.setHeight(this.computeWrap(this.mWidgetcontainer, 1));
                this.mWidgetcontainer.mVerticalRun.mDimension.resolve(this.mWidgetcontainer.getHeight());
            }
        }
        if(this.mWidgetcontainer.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED || this.mWidgetcontainer.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_PARENT) {
            int v2 = this.mWidgetcontainer.getWidth() + v;
            this.mWidgetcontainer.mHorizontalRun.end.resolve(v2);
            this.mWidgetcontainer.mHorizontalRun.mDimension.resolve(v2 - v);
            this.measureWidgets();
            if(this.mWidgetcontainer.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED || this.mWidgetcontainer.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_PARENT) {
                int v3 = this.mWidgetcontainer.getHeight() + v1;
                this.mWidgetcontainer.mVerticalRun.end.resolve(v3);
                this.mWidgetcontainer.mVerticalRun.mDimension.resolve(v3 - v1);
            }
            this.measureWidgets();
            z2 = true;
        }
        else {
            z2 = false;
        }
        for(Object object2: this.mRuns) {
            WidgetRun widgetRun0 = (WidgetRun)object2;
            if(widgetRun0.mWidget != this.mWidgetcontainer || widgetRun0.mResolved) {
                widgetRun0.applyToWidget();
            }
        }
        for(Object object3: this.mRuns) {
            WidgetRun widgetRun1 = (WidgetRun)object3;
            if(!z2 && widgetRun1.mWidget == this.mWidgetcontainer || widgetRun1.start.resolved && (widgetRun1.end.resolved || widgetRun1 instanceof GuidelineReference) && (widgetRun1.mDimension.resolved || widgetRun1 instanceof ChainRun || widgetRun1 instanceof GuidelineReference)) {
                continue;
            }
            this.mWidgetcontainer.setHorizontalDimensionBehaviour(constraintWidget$DimensionBehaviour0);
            this.mWidgetcontainer.setVerticalDimensionBehaviour(constraintWidget$DimensionBehaviour1);
            return z1;
        }
        this.mWidgetcontainer.setHorizontalDimensionBehaviour(constraintWidget$DimensionBehaviour0);
        this.mWidgetcontainer.setVerticalDimensionBehaviour(constraintWidget$DimensionBehaviour1);
        return true;
    }

    public boolean directMeasureSetup(boolean z) {
        if(this.mNeedBuildGraph) {
            for(Object object0: this.mWidgetcontainer.mChildren) {
                ((ConstraintWidget)object0).ensureWidgetRuns();
                ((ConstraintWidget)object0).measured = false;
                ((ConstraintWidget)object0).mHorizontalRun.mDimension.resolved = false;
                ((ConstraintWidget)object0).mHorizontalRun.mResolved = false;
                ((ConstraintWidget)object0).mHorizontalRun.reset();
                ((ConstraintWidget)object0).mVerticalRun.mDimension.resolved = false;
                ((ConstraintWidget)object0).mVerticalRun.mResolved = false;
                ((ConstraintWidget)object0).mVerticalRun.reset();
            }
            this.mWidgetcontainer.ensureWidgetRuns();
            this.mWidgetcontainer.measured = false;
            this.mWidgetcontainer.mHorizontalRun.mDimension.resolved = false;
            this.mWidgetcontainer.mHorizontalRun.mResolved = false;
            this.mWidgetcontainer.mHorizontalRun.reset();
            this.mWidgetcontainer.mVerticalRun.mDimension.resolved = false;
            this.mWidgetcontainer.mVerticalRun.mResolved = false;
            this.mWidgetcontainer.mVerticalRun.reset();
            this.buildGraph();
        }
        if(this.basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.mWidgetcontainer.setX(0);
        this.mWidgetcontainer.setY(0);
        this.mWidgetcontainer.mHorizontalRun.start.resolve(0);
        this.mWidgetcontainer.mVerticalRun.start.resolve(0);
        return true;
    }

    public boolean directMeasureWithOrientation(boolean z, int v) {
        boolean z2;
        boolean z1 = false;
        DimensionBehaviour constraintWidget$DimensionBehaviour0 = this.mWidgetcontainer.getDimensionBehaviour(0);
        DimensionBehaviour constraintWidget$DimensionBehaviour1 = this.mWidgetcontainer.getDimensionBehaviour(1);
        int v1 = this.mWidgetcontainer.getX();
        int v2 = this.mWidgetcontainer.getY();
        if(z && (constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT)) {
            for(Object object0: this.mRuns) {
                if(((WidgetRun)object0).orientation == v && !((WidgetRun)object0).supportsWrapComputation()) {
                    z = false;
                    break;
                }
                if(false) {
                    break;
                }
            }
            if(v != 0) {
                if(z && constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT) {
                    this.mWidgetcontainer.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                    this.mWidgetcontainer.setHeight(this.computeWrap(this.mWidgetcontainer, 1));
                    this.mWidgetcontainer.mVerticalRun.mDimension.resolve(this.mWidgetcontainer.getHeight());
                }
            }
            else if(z && constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT) {
                this.mWidgetcontainer.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                this.mWidgetcontainer.setWidth(this.computeWrap(this.mWidgetcontainer, 0));
                this.mWidgetcontainer.mHorizontalRun.mDimension.resolve(this.mWidgetcontainer.getWidth());
            }
        }
        if(v != 0) {
            if(this.mWidgetcontainer.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED || this.mWidgetcontainer.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_PARENT) {
                int v4 = this.mWidgetcontainer.getHeight() + v2;
                this.mWidgetcontainer.mVerticalRun.end.resolve(v4);
                this.mWidgetcontainer.mVerticalRun.mDimension.resolve(v4 - v2);
                z2 = true;
            }
            else {
                z2 = false;
            }
        }
        else if(this.mWidgetcontainer.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED || this.mWidgetcontainer.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_PARENT) {
            int v3 = this.mWidgetcontainer.getWidth() + v1;
            this.mWidgetcontainer.mHorizontalRun.end.resolve(v3);
            this.mWidgetcontainer.mHorizontalRun.mDimension.resolve(v3 - v1);
            z2 = true;
        }
        else {
            z2 = false;
        }
        this.measureWidgets();
        for(Object object1: this.mRuns) {
            WidgetRun widgetRun0 = (WidgetRun)object1;
            if(widgetRun0.orientation == v && (widgetRun0.mWidget != this.mWidgetcontainer || widgetRun0.mResolved)) {
                widgetRun0.applyToWidget();
            }
        }
        for(Object object2: this.mRuns) {
            WidgetRun widgetRun1 = (WidgetRun)object2;
            if(widgetRun1.orientation != v || !z2 && widgetRun1.mWidget == this.mWidgetcontainer || widgetRun1.start.resolved && widgetRun1.end.resolved && (widgetRun1 instanceof ChainRun || widgetRun1.mDimension.resolved)) {
                continue;
            }
            this.mWidgetcontainer.setHorizontalDimensionBehaviour(constraintWidget$DimensionBehaviour0);
            this.mWidgetcontainer.setVerticalDimensionBehaviour(constraintWidget$DimensionBehaviour1);
            return z1;
        }
        this.mWidgetcontainer.setHorizontalDimensionBehaviour(constraintWidget$DimensionBehaviour0);
        this.mWidgetcontainer.setVerticalDimensionBehaviour(constraintWidget$DimensionBehaviour1);
        return true;
    }

    private void displayGraph() {
        String s = "digraph {\n";
        for(Object object0: this.mRuns) {
            s = this.generateDisplayGraph(((WidgetRun)object0), s);
        }
        System.out.println("content:<<\n" + (s + "\n}\n") + "\n>>");
    }

    private void findGroup(WidgetRun widgetRun0, int v, ArrayList arrayList0) {
        for(Object object0: widgetRun0.start.mDependencies) {
            Dependency dependency0 = (Dependency)object0;
            if(dependency0 instanceof DependencyNode) {
                this.applyGroup(((DependencyNode)dependency0), v, 0, widgetRun0.end, arrayList0, null);
            }
            else if(dependency0 instanceof WidgetRun) {
                this.applyGroup(((WidgetRun)dependency0).start, v, 0, widgetRun0.end, arrayList0, null);
            }
        }
        for(Object object1: widgetRun0.end.mDependencies) {
            Dependency dependency1 = (Dependency)object1;
            if(dependency1 instanceof DependencyNode) {
                this.applyGroup(((DependencyNode)dependency1), v, 1, widgetRun0.start, arrayList0, null);
            }
            else if(dependency1 instanceof WidgetRun) {
                this.applyGroup(((WidgetRun)dependency1).end, v, 1, widgetRun0.start, arrayList0, null);
            }
        }
        if(v == 1) {
            for(Object object2: ((VerticalWidgetRun)widgetRun0).baseline.mDependencies) {
                Dependency dependency2 = (Dependency)object2;
                if(dependency2 instanceof DependencyNode) {
                    this.applyGroup(((DependencyNode)dependency2), 1, 2, null, arrayList0, null);
                }
            }
        }
    }

    private String generateChainDisplayGraph(ChainRun chainRun0, String s) {
        int v = chainRun0.orientation;
        StringBuilder stringBuilder0 = new StringBuilder("subgraph cluster_");
        stringBuilder0.append(chainRun0.mWidget.getDebugName());
        if(v == 0) {
            stringBuilder0.append("_h");
        }
        else {
            stringBuilder0.append("_v");
        }
        stringBuilder0.append(" {\n");
        String s1 = "";
        for(Object object0: chainRun0.mWidgets) {
            stringBuilder0.append(((WidgetRun)object0).mWidget.getDebugName());
            if(v == 0) {
                stringBuilder0.append("_HORIZONTAL");
            }
            else {
                stringBuilder0.append("_VERTICAL");
            }
            stringBuilder0.append(";\n");
            s1 = this.generateDisplayGraph(((WidgetRun)object0), s1);
        }
        stringBuilder0.append("}\n");
        return s + s1 + stringBuilder0;
    }

    private String generateDisplayGraph(WidgetRun widgetRun0, String s) {
        DependencyNode dependencyNode0 = widgetRun0.start;
        DependencyNode dependencyNode1 = widgetRun0.end;
        StringBuilder stringBuilder0 = new StringBuilder(s);
        if(!(widgetRun0 instanceof HelperReferences) && dependencyNode0.mDependencies.isEmpty() && dependencyNode1.mDependencies.isEmpty() && dependencyNode0.mTargets.isEmpty() && dependencyNode1.mTargets.isEmpty()) {
            return s;
        }
        stringBuilder0.append(this.nodeDefinition(widgetRun0));
        boolean z = this.isCenteredConnection(dependencyNode0, dependencyNode1);
        String s1 = this.generateDisplayNode(dependencyNode1, z, this.generateDisplayNode(dependencyNode0, z, s));
        if(widgetRun0 instanceof VerticalWidgetRun) {
            s1 = this.generateDisplayNode(((VerticalWidgetRun)widgetRun0).baseline, z, s1);
        }
        if(widgetRun0 instanceof HorizontalWidgetRun || widgetRun0 instanceof ChainRun && ((ChainRun)widgetRun0).orientation == 0) {
            DimensionBehaviour constraintWidget$DimensionBehaviour1 = widgetRun0.mWidget.getHorizontalDimensionBehaviour();
            if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.FIXED || constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT) {
                if(!dependencyNode0.mTargets.isEmpty() && dependencyNode1.mTargets.isEmpty()) {
                    stringBuilder0.append("\n");
                    stringBuilder0.append(dependencyNode1.name());
                    stringBuilder0.append(" -> ");
                    stringBuilder0.append(dependencyNode0.name());
                    stringBuilder0.append("\n");
                    return widgetRun0 instanceof ChainRun ? this.generateChainDisplayGraph(((ChainRun)widgetRun0), s1) : stringBuilder0.toString();
                }
                if(dependencyNode0.mTargets.isEmpty() && !dependencyNode1.mTargets.isEmpty()) {
                    stringBuilder0.append("\n");
                    stringBuilder0.append(dependencyNode0.name());
                    stringBuilder0.append(" -> ");
                    stringBuilder0.append(dependencyNode1.name());
                    stringBuilder0.append("\n");
                }
            }
            else if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.MATCH_CONSTRAINT && widgetRun0.mWidget.getDimensionRatio() > 0.0f) {
                stringBuilder0.append("\n");
                stringBuilder0.append(widgetRun0.mWidget.getDebugName());
                stringBuilder0.append("_HORIZONTAL -> ");
                stringBuilder0.append(widgetRun0.mWidget.getDebugName());
                stringBuilder0.append("_VERTICAL;\n");
                return widgetRun0 instanceof ChainRun ? this.generateChainDisplayGraph(((ChainRun)widgetRun0), s1) : stringBuilder0.toString();
            }
        }
        else if(widgetRun0 instanceof VerticalWidgetRun || widgetRun0 instanceof ChainRun && ((ChainRun)widgetRun0).orientation == 1) {
            DimensionBehaviour constraintWidget$DimensionBehaviour0 = widgetRun0.mWidget.getVerticalDimensionBehaviour();
            if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.FIXED || constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT) {
                if(!dependencyNode0.mTargets.isEmpty() && dependencyNode1.mTargets.isEmpty()) {
                    stringBuilder0.append("\n");
                    stringBuilder0.append(dependencyNode1.name());
                    stringBuilder0.append(" -> ");
                    stringBuilder0.append(dependencyNode0.name());
                    stringBuilder0.append("\n");
                    return widgetRun0 instanceof ChainRun ? this.generateChainDisplayGraph(((ChainRun)widgetRun0), s1) : stringBuilder0.toString();
                }
                if(dependencyNode0.mTargets.isEmpty() && !dependencyNode1.mTargets.isEmpty()) {
                    stringBuilder0.append("\n");
                    stringBuilder0.append(dependencyNode0.name());
                    stringBuilder0.append(" -> ");
                    stringBuilder0.append(dependencyNode1.name());
                    stringBuilder0.append("\n");
                    return widgetRun0 instanceof ChainRun ? this.generateChainDisplayGraph(((ChainRun)widgetRun0), s1) : stringBuilder0.toString();
                }
            }
            else if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.MATCH_CONSTRAINT && widgetRun0.mWidget.getDimensionRatio() > 0.0f) {
                stringBuilder0.append("\n");
                stringBuilder0.append(widgetRun0.mWidget.getDebugName());
                stringBuilder0.append("_VERTICAL -> ");
                stringBuilder0.append(widgetRun0.mWidget.getDebugName());
                stringBuilder0.append("_HORIZONTAL;\n");
                return widgetRun0 instanceof ChainRun ? this.generateChainDisplayGraph(((ChainRun)widgetRun0), s1) : stringBuilder0.toString();
            }
        }
        return widgetRun0 instanceof ChainRun ? this.generateChainDisplayGraph(((ChainRun)widgetRun0), s1) : stringBuilder0.toString();
    }

    private String generateDisplayNode(DependencyNode dependencyNode0, boolean z, String s) {
        StringBuilder stringBuilder0 = new StringBuilder(s);
        for(Object object0: dependencyNode0.mTargets) {
            String s1 = "\n" + dependencyNode0.name() + " -> " + ((DependencyNode)object0).name();
            if(dependencyNode0.mMargin > 0 || z || dependencyNode0.mRun instanceof HelperReferences) {
                String s2 = s1 + "[";
                if(dependencyNode0.mMargin > 0) {
                    s2 = s2 + "label=\"" + dependencyNode0.mMargin + "\"";
                    if(z) {
                        s2 = s2 + ",";
                    }
                }
                if(z) {
                    s2 = s2 + " style=dashed ";
                }
                if(dependencyNode0.mRun instanceof HelperReferences) {
                    s2 = s2 + " style=bold,color=gray ";
                }
                s1 = s2 + "]";
            }
            stringBuilder0.append(s1 + "\n");
        }
        return stringBuilder0.toString();
    }

    public void invalidateGraph() {
        this.mNeedBuildGraph = true;
    }

    public void invalidateMeasures() {
        this.mNeedRedoMeasures = true;
    }

    private boolean isCenteredConnection(DependencyNode dependencyNode0, DependencyNode dependencyNode1) {
        int v = 0;
        for(Object object0: dependencyNode0.mTargets) {
            if(((DependencyNode)object0) != dependencyNode1) {
                ++v;
            }
        }
        int v1 = 0;
        for(Object object1: dependencyNode1.mTargets) {
            if(((DependencyNode)object1) != dependencyNode0) {
                ++v1;
            }
        }
        return v > 0 && v1 > 0;
    }

    private void measure(ConstraintWidget constraintWidget0, DimensionBehaviour constraintWidget$DimensionBehaviour0, int v, DimensionBehaviour constraintWidget$DimensionBehaviour1, int v1) {
        this.mMeasure.horizontalBehavior = constraintWidget$DimensionBehaviour0;
        this.mMeasure.verticalBehavior = constraintWidget$DimensionBehaviour1;
        this.mMeasure.horizontalDimension = v;
        this.mMeasure.verticalDimension = v1;
        this.mMeasurer.measure(constraintWidget0, this.mMeasure);
        constraintWidget0.setWidth(this.mMeasure.measuredWidth);
        constraintWidget0.setHeight(this.mMeasure.measuredHeight);
        constraintWidget0.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget0.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    public void measureWidgets() {
        for(Object object0: this.mWidgetcontainer.mChildren) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)object0;
            if(!constraintWidget0.measured) {
                boolean z = false;
                DimensionBehaviour constraintWidget$DimensionBehaviour0 = constraintWidget0.mListDimensionBehaviors[0];
                DimensionBehaviour constraintWidget$DimensionBehaviour1 = constraintWidget0.mListDimensionBehaviors[1];
                boolean z1 = constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour0 == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mMatchConstraintDefaultWidth == 1;
                if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour1 == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mMatchConstraintDefaultHeight == 1) {
                    z = true;
                }
                boolean z2 = constraintWidget0.mHorizontalRun.mDimension.resolved;
                boolean z3 = constraintWidget0.mVerticalRun.mDimension.resolved;
                if(z2 && z3) {
                    this.measure(constraintWidget0, DimensionBehaviour.FIXED, constraintWidget0.mHorizontalRun.mDimension.value, DimensionBehaviour.FIXED, constraintWidget0.mVerticalRun.mDimension.value);
                    constraintWidget0.measured = true;
                }
                else if(z2 && z) {
                    this.measure(constraintWidget0, DimensionBehaviour.FIXED, constraintWidget0.mHorizontalRun.mDimension.value, DimensionBehaviour.WRAP_CONTENT, constraintWidget0.mVerticalRun.mDimension.value);
                    if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget0.mVerticalRun.mDimension.wrapValue = constraintWidget0.getHeight();
                    }
                    else {
                        constraintWidget0.mVerticalRun.mDimension.resolve(constraintWidget0.getHeight());
                        constraintWidget0.measured = true;
                    }
                }
                else if(z3 && z1) {
                    this.measure(constraintWidget0, DimensionBehaviour.WRAP_CONTENT, constraintWidget0.mHorizontalRun.mDimension.value, DimensionBehaviour.FIXED, constraintWidget0.mVerticalRun.mDimension.value);
                    if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget0.mHorizontalRun.mDimension.wrapValue = constraintWidget0.getWidth();
                    }
                    else {
                        constraintWidget0.mHorizontalRun.mDimension.resolve(constraintWidget0.getWidth());
                        constraintWidget0.measured = true;
                    }
                }
                if(constraintWidget0.measured && constraintWidget0.mVerticalRun.mBaselineDimension != null) {
                    constraintWidget0.mVerticalRun.mBaselineDimension.resolve(constraintWidget0.getBaselineDistance());
                }
            }
        }
    }

    private String nodeDefinition(WidgetRun widgetRun0) {
        String s = widgetRun0.mWidget.getDebugName();
        StringBuilder stringBuilder0 = new StringBuilder(s);
        DimensionBehaviour constraintWidget$DimensionBehaviour0 = widgetRun0 instanceof VerticalWidgetRun ? widgetRun0.mWidget.getVerticalDimensionBehaviour() : widgetRun0.mWidget.getHorizontalDimensionBehaviour();
        RunGroup runGroup0 = widgetRun0.mRunGroup;
        if(widgetRun0 instanceof VerticalWidgetRun) {
            stringBuilder0.append("_VERTICAL");
        }
        else {
            stringBuilder0.append("_HORIZONTAL");
        }
        stringBuilder0.append(" [shape=none, label=<<TABLE BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"2\">  <TR>");
        if(widgetRun0 instanceof VerticalWidgetRun) {
            stringBuilder0.append("    <TD ");
            if(widgetRun0.start.resolved) {
                stringBuilder0.append(" BGCOLOR=\"green\"");
            }
            stringBuilder0.append(" PORT=\"TOP\" BORDER=\"1\">T</TD>");
        }
        else {
            stringBuilder0.append("    <TD ");
            if(widgetRun0.start.resolved) {
                stringBuilder0.append(" BGCOLOR=\"green\"");
            }
            stringBuilder0.append(" PORT=\"LEFT\" BORDER=\"1\">L</TD>");
        }
        stringBuilder0.append("    <TD BORDER=\"1\" ");
        if(widgetRun0.mDimension.resolved && !widgetRun0.mWidget.measured) {
            stringBuilder0.append(" BGCOLOR=\"green\" ");
        }
        else if(widgetRun0.mDimension.resolved) {
            stringBuilder0.append(" BGCOLOR=\"lightgray\" ");
        }
        else if(widgetRun0.mWidget.measured) {
            stringBuilder0.append(" BGCOLOR=\"yellow\" ");
        }
        if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.MATCH_CONSTRAINT) {
            stringBuilder0.append("style=\"dashed\"");
        }
        stringBuilder0.append(">");
        stringBuilder0.append(s);
        if(runGroup0 != null) {
            stringBuilder0.append(" [");
            stringBuilder0.append(runGroup0.mGroupIndex + 1);
            stringBuilder0.append("/");
            stringBuilder0.append(RunGroup.index);
            stringBuilder0.append("]");
        }
        stringBuilder0.append(" </TD>");
        if(widgetRun0 instanceof VerticalWidgetRun) {
            stringBuilder0.append("    <TD ");
            if(((VerticalWidgetRun)widgetRun0).baseline.resolved) {
                stringBuilder0.append(" BGCOLOR=\"green\"");
            }
            stringBuilder0.append(" PORT=\"BASELINE\" BORDER=\"1\">b</TD>    <TD ");
            if(widgetRun0.end.resolved) {
                stringBuilder0.append(" BGCOLOR=\"green\"");
            }
            stringBuilder0.append(" PORT=\"BOTTOM\" BORDER=\"1\">B</TD>");
        }
        else {
            stringBuilder0.append("    <TD ");
            if(widgetRun0.end.resolved) {
                stringBuilder0.append(" BGCOLOR=\"green\"");
            }
            stringBuilder0.append(" PORT=\"RIGHT\" BORDER=\"1\">R</TD>");
        }
        stringBuilder0.append("  </TR></TABLE>>];\n");
        return stringBuilder0.toString();
    }

    public void setMeasurer(Measurer basicMeasure$Measurer0) {
        this.mMeasurer = basicMeasure$Measurer0;
    }
}

