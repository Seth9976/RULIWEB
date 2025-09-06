package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;

class RunGroup {
    public static final int BASELINE = 2;
    public static final int END = 1;
    public static final int START;
    public boolean dual;
    public static int index;
    int mDirection;
    WidgetRun mFirstRun;
    int mGroupIndex;
    WidgetRun mLastRun;
    ArrayList mRuns;
    public int position;

    RunGroup(WidgetRun widgetRun0, int v) {
        this.position = 0;
        this.dual = false;
        this.mFirstRun = null;
        this.mLastRun = null;
        this.mRuns = new ArrayList();
        this.mGroupIndex = RunGroup.index;
        ++RunGroup.index;
        this.mFirstRun = widgetRun0;
        this.mLastRun = widgetRun0;
        this.mDirection = v;
    }

    public void add(WidgetRun widgetRun0) {
        this.mRuns.add(widgetRun0);
        this.mLastRun = widgetRun0;
    }

    public long computeWrapSize(ConstraintWidgetContainer constraintWidgetContainer0, int v) {
        WidgetRun widgetRun0 = this.mFirstRun;
        long v1 = 0L;
        if(widgetRun0 instanceof ChainRun) {
            if(((ChainRun)widgetRun0).orientation != v) {
                return 0L;
            }
        }
        else if(v == 0) {
            if(!(widgetRun0 instanceof HorizontalWidgetRun)) {
                return 0L;
            }
        }
        else if(!(widgetRun0 instanceof VerticalWidgetRun)) {
            return 0L;
        }
        DependencyNode dependencyNode0 = v == 0 ? constraintWidgetContainer0.mHorizontalRun.end : constraintWidgetContainer0.mVerticalRun.end;
        boolean z = this.mFirstRun.start.mTargets.contains((v == 0 ? constraintWidgetContainer0.mHorizontalRun.start : constraintWidgetContainer0.mVerticalRun.start));
        boolean z1 = this.mFirstRun.end.mTargets.contains(dependencyNode0);
        long v2 = this.mFirstRun.getWrapDimension();
        if(z && z1) {
            long v3 = this.traverseStart(this.mFirstRun.start, 0L);
            long v4 = this.traverseEnd(this.mFirstRun.end, 0L);
            long v5 = v3 - v2;
            v5 = v5 < ((long)(-this.mFirstRun.end.mMargin)) ? v3 - v2 : v5 + ((long)this.mFirstRun.end.mMargin);
            long v6 = -v4 - v2 - ((long)this.mFirstRun.start.mMargin);
            v6 = v6 < ((long)this.mFirstRun.start.mMargin) ? -v4 - v2 - ((long)this.mFirstRun.start.mMargin) : v6 - ((long)this.mFirstRun.start.mMargin);
            float f = this.mFirstRun.mWidget.getBiasPercent(v);
            if(f > 0.0f) {
                v1 = (long)(((float)v6) / f + ((float)v5) / (1.0f - f));
            }
            return ((long)this.mFirstRun.start.mMargin) + (((long)(((float)v1) * f + 0.5f)) + v2 + ((long)(((float)v1) * (1.0f - f) + 0.5f))) - ((long)this.mFirstRun.end.mMargin);
        }
        if(z) {
            return Math.max(this.traverseStart(this.mFirstRun.start, ((long)this.mFirstRun.start.mMargin)), ((long)this.mFirstRun.start.mMargin) + v2);
        }
        return z1 ? Math.max(-this.traverseEnd(this.mFirstRun.end, ((long)this.mFirstRun.end.mMargin)), ((long)(-this.mFirstRun.end.mMargin)) + v2) : ((long)this.mFirstRun.start.mMargin) + this.mFirstRun.getWrapDimension() - ((long)this.mFirstRun.end.mMargin);
    }

    private boolean defineTerminalWidget(WidgetRun widgetRun0, int v) {
        if(!widgetRun0.mWidget.isTerminalWidget[v]) {
            return false;
        }
        for(Object object0: widgetRun0.start.mDependencies) {
            Dependency dependency0 = (Dependency)object0;
            if(dependency0 instanceof DependencyNode && ((DependencyNode)dependency0).mRun != widgetRun0 && ((DependencyNode)dependency0) == ((DependencyNode)dependency0).mRun.start) {
                if(widgetRun0 instanceof ChainRun) {
                    for(Object object1: ((ChainRun)widgetRun0).mWidgets) {
                        this.defineTerminalWidget(((WidgetRun)object1), v);
                    }
                }
                else if(!(widgetRun0 instanceof HelperReferences)) {
                    widgetRun0.mWidget.isTerminalWidget[v] = false;
                }
                this.defineTerminalWidget(((DependencyNode)dependency0).mRun, v);
            }
        }
        for(Object object2: widgetRun0.end.mDependencies) {
            Dependency dependency1 = (Dependency)object2;
            if(dependency1 instanceof DependencyNode && ((DependencyNode)dependency1).mRun != widgetRun0 && ((DependencyNode)dependency1) == ((DependencyNode)dependency1).mRun.start) {
                if(widgetRun0 instanceof ChainRun) {
                    for(Object object3: ((ChainRun)widgetRun0).mWidgets) {
                        this.defineTerminalWidget(((WidgetRun)object3), v);
                    }
                }
                else if(!(widgetRun0 instanceof HelperReferences)) {
                    widgetRun0.mWidget.isTerminalWidget[v] = false;
                }
                this.defineTerminalWidget(((DependencyNode)dependency1).mRun, v);
            }
        }
        return false;
    }

    public void defineTerminalWidgets(boolean z, boolean z1) {
        if(z) {
            WidgetRun widgetRun0 = this.mFirstRun;
            if(widgetRun0 instanceof HorizontalWidgetRun) {
                this.defineTerminalWidget(widgetRun0, 0);
            }
        }
        if(z1) {
            WidgetRun widgetRun1 = this.mFirstRun;
            if(widgetRun1 instanceof VerticalWidgetRun) {
                this.defineTerminalWidget(widgetRun1, 1);
            }
        }
    }

    private long traverseEnd(DependencyNode dependencyNode0, long v) {
        WidgetRun widgetRun0 = dependencyNode0.mRun;
        if(widgetRun0 instanceof HelperReferences) {
            return v;
        }
        int v1 = dependencyNode0.mDependencies.size();
        long v3 = v;
        for(int v2 = 0; v2 < v1; ++v2) {
            Dependency dependency0 = (Dependency)dependencyNode0.mDependencies.get(v2);
            if(dependency0 instanceof DependencyNode && ((DependencyNode)dependency0).mRun != widgetRun0) {
                v3 = Math.min(v3, this.traverseEnd(((DependencyNode)dependency0), ((long)((DependencyNode)dependency0).mMargin) + v));
            }
        }
        if(dependencyNode0 == widgetRun0.end) {
            long v4 = v - widgetRun0.getWrapDimension();
            return Math.min(Math.min(v3, this.traverseEnd(widgetRun0.start, v4)), v4 - ((long)widgetRun0.start.mMargin));
        }
        return v3;
    }

    private long traverseStart(DependencyNode dependencyNode0, long v) {
        WidgetRun widgetRun0 = dependencyNode0.mRun;
        if(widgetRun0 instanceof HelperReferences) {
            return v;
        }
        int v1 = dependencyNode0.mDependencies.size();
        long v3 = v;
        for(int v2 = 0; v2 < v1; ++v2) {
            Dependency dependency0 = (Dependency)dependencyNode0.mDependencies.get(v2);
            if(dependency0 instanceof DependencyNode && ((DependencyNode)dependency0).mRun != widgetRun0) {
                v3 = Math.max(v3, this.traverseStart(((DependencyNode)dependency0), ((long)((DependencyNode)dependency0).mMargin) + v));
            }
        }
        if(dependencyNode0 == widgetRun0.start) {
            long v4 = v + widgetRun0.getWrapDimension();
            return Math.max(Math.max(v3, this.traverseStart(widgetRun0.end, v4)), v4 - ((long)widgetRun0.end.mMargin));
        }
        return v3;
    }
}

