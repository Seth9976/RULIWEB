package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.core.widgets.analyzer.DependencyGraph;
import androidx.constraintlayout.core.widgets.analyzer.Direct;
import androidx.constraintlayout.core.widgets.analyzer.Grouping;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ConstraintWidgetContainer extends WidgetContainer {
    private static final boolean DEBUG = false;
    static final boolean DEBUG_GRAPH = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final int MAX_ITERATIONS = 8;
    BasicMeasure mBasicMeasureSolver;
    int mDebugSolverPassCount;
    public DependencyGraph mDependencyGraph;
    public boolean mGroupsWrapOptimized;
    private boolean mHeightMeasuredTooSmall;
    ChainHead[] mHorizontalChainsArray;
    public int mHorizontalChainsSize;
    private WeakReference mHorizontalWrapMax;
    private WeakReference mHorizontalWrapMin;
    public boolean mHorizontalWrapOptimized;
    private boolean mIsRtl;
    public Measure mMeasure;
    protected Measurer mMeasurer;
    public Metrics mMetrics;
    private int mOptimizationLevel;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    private int mPass;
    public boolean mSkipSolver;
    protected LinearSystem mSystem;
    ChainHead[] mVerticalChainsArray;
    public int mVerticalChainsSize;
    private WeakReference mVerticalWrapMax;
    private WeakReference mVerticalWrapMin;
    public boolean mVerticalWrapOptimized;
    HashSet mWidgetsToAdd;
    private boolean mWidthMeasuredTooSmall;
    public int mWrapFixedHeight;
    public int mWrapFixedWidth;
    static int sMyCounter;

    static {
    }

    public ConstraintWidgetContainer() {
        this.mBasicMeasureSolver = new BasicMeasure(this);
        this.mDependencyGraph = new DependencyGraph(this);
        this.mMeasurer = null;
        this.mIsRtl = false;
        this.mSystem = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ChainHead[4];
        this.mHorizontalChainsArray = new ChainHead[4];
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.mOptimizationLevel = 0x101;
        this.mSkipSolver = false;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
        this.mVerticalWrapMin = null;
        this.mHorizontalWrapMin = null;
        this.mVerticalWrapMax = null;
        this.mHorizontalWrapMax = null;
        this.mWidgetsToAdd = new HashSet();
        this.mMeasure = new Measure();
    }

    public ConstraintWidgetContainer(int v, int v1) {
        super(v, v1);
        this.mBasicMeasureSolver = new BasicMeasure(this);
        this.mDependencyGraph = new DependencyGraph(this);
        this.mMeasurer = null;
        this.mIsRtl = false;
        this.mSystem = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ChainHead[4];
        this.mHorizontalChainsArray = new ChainHead[4];
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.mOptimizationLevel = 0x101;
        this.mSkipSolver = false;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
        this.mVerticalWrapMin = null;
        this.mHorizontalWrapMin = null;
        this.mVerticalWrapMax = null;
        this.mHorizontalWrapMax = null;
        this.mWidgetsToAdd = new HashSet();
        this.mMeasure = new Measure();
    }

    public ConstraintWidgetContainer(int v, int v1, int v2, int v3) {
        super(v, v1, v2, v3);
        this.mBasicMeasureSolver = new BasicMeasure(this);
        this.mDependencyGraph = new DependencyGraph(this);
        this.mMeasurer = null;
        this.mIsRtl = false;
        this.mSystem = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ChainHead[4];
        this.mHorizontalChainsArray = new ChainHead[4];
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.mOptimizationLevel = 0x101;
        this.mSkipSolver = false;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
        this.mVerticalWrapMin = null;
        this.mHorizontalWrapMin = null;
        this.mVerticalWrapMax = null;
        this.mHorizontalWrapMax = null;
        this.mWidgetsToAdd = new HashSet();
        this.mMeasure = new Measure();
    }

    public ConstraintWidgetContainer(String s, int v, int v1) {
        super(v, v1);
        this.mBasicMeasureSolver = new BasicMeasure(this);
        this.mDependencyGraph = new DependencyGraph(this);
        this.mMeasurer = null;
        this.mIsRtl = false;
        this.mSystem = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ChainHead[4];
        this.mHorizontalChainsArray = new ChainHead[4];
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.mOptimizationLevel = 0x101;
        this.mSkipSolver = false;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
        this.mVerticalWrapMin = null;
        this.mHorizontalWrapMin = null;
        this.mVerticalWrapMax = null;
        this.mHorizontalWrapMax = null;
        this.mWidgetsToAdd = new HashSet();
        this.mMeasure = new Measure();
        this.setDebugName(s);
    }

    void addChain(ConstraintWidget constraintWidget0, int v) {
        if(v == 0) {
            this.addHorizontalChain(constraintWidget0);
            return;
        }
        if(v == 1) {
            this.addVerticalChain(constraintWidget0);
        }
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem0) {
        boolean z = this.optimizeFor(0x40);
        this.addToSolver(linearSystem0, z);
        int v = this.mChildren.size();
        boolean z1 = false;
        for(int v1 = 0; v1 < v; ++v1) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)this.mChildren.get(v1);
            constraintWidget0.setInBarrier(0, false);
            constraintWidget0.setInBarrier(1, false);
            if(constraintWidget0 instanceof Barrier) {
                z1 = true;
            }
        }
        if(z1) {
            for(int v2 = 0; v2 < v; ++v2) {
                ConstraintWidget constraintWidget1 = (ConstraintWidget)this.mChildren.get(v2);
                if(constraintWidget1 instanceof Barrier) {
                    ((Barrier)constraintWidget1).markWidgets();
                }
            }
        }
        this.mWidgetsToAdd.clear();
        for(int v3 = 0; v3 < v; ++v3) {
            ConstraintWidget constraintWidget2 = (ConstraintWidget)this.mChildren.get(v3);
        }
        while(this.mWidgetsToAdd.size() > 0) {
            int v4 = this.mWidgetsToAdd.size();
            for(Object object0: this.mWidgetsToAdd) {
                VirtualLayout virtualLayout0 = (VirtualLayout)(((ConstraintWidget)object0));
                if(virtualLayout0.contains(this.mWidgetsToAdd)) {
                    virtualLayout0.addToSolver(linearSystem0, z);
                    this.mWidgetsToAdd.remove(virtualLayout0);
                    break;
                }
                if(false) {
                    break;
                }
            }
            if(v4 == this.mWidgetsToAdd.size()) {
                for(Object object1: this.mWidgetsToAdd) {
                    ((ConstraintWidget)object1).addToSolver(linearSystem0, z);
                }
                this.mWidgetsToAdd.clear();
            }
        }
        if(LinearSystem.USE_DEPENDENCY_ORDERING) {
            HashSet hashSet0 = new HashSet();
            for(int v5 = 0; v5 < v; ++v5) {
                hashSet0.add(((ConstraintWidget)this.mChildren.get(v5)));
            }
            this.addChildrenToSolverByDependency(this, linearSystem0, hashSet0, (this.getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT ? 0 : 1), false);
            for(Object object2: hashSet0) {
                Optimizer.checkMatchParent(this, linearSystem0, ((ConstraintWidget)object2));
                ((ConstraintWidget)object2).addToSolver(linearSystem0, z);
            }
        }
        else {
            for(int v6 = 0; v6 < v; ++v6) {
                ConstraintWidget constraintWidget3 = (ConstraintWidget)this.mChildren.get(v6);
                if(constraintWidget3 instanceof ConstraintWidgetContainer) {
                    DimensionBehaviour constraintWidget$DimensionBehaviour0 = constraintWidget3.mListDimensionBehaviors[0];
                    DimensionBehaviour constraintWidget$DimensionBehaviour1 = constraintWidget3.mListDimensionBehaviors[1];
                    if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget3.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                    }
                    if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget3.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                    }
                    constraintWidget3.addToSolver(linearSystem0, z);
                    if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget3.setHorizontalDimensionBehaviour(constraintWidget$DimensionBehaviour0);
                    }
                    if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget3.setVerticalDimensionBehaviour(constraintWidget$DimensionBehaviour1);
                    }
                }
                else {
                    Optimizer.checkMatchParent(this, linearSystem0, constraintWidget3);
                    constraintWidget3.addToSolver(linearSystem0, z);
                }
            }
        }
        if(this.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem0, null, 0);
        }
        if(this.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem0, null, 1);
        }
        return true;
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget0) {
        ChainHead[] arr_chainHead = this.mHorizontalChainsArray;
        if(this.mHorizontalChainsSize + 1 >= arr_chainHead.length) {
            this.mHorizontalChainsArray = (ChainHead[])Arrays.copyOf(arr_chainHead, arr_chainHead.length * 2);
        }
        ChainHead[] arr_chainHead1 = this.mHorizontalChainsArray;
        int v = this.mHorizontalChainsSize;
        arr_chainHead1[v] = new ChainHead(constraintWidget0, 0, this.isRtl());
        ++this.mHorizontalChainsSize;
    }

    public void addHorizontalWrapMaxVariable(ConstraintAnchor constraintAnchor0) {
        if(this.mHorizontalWrapMax != null && this.mHorizontalWrapMax.get() != null && constraintAnchor0.getFinalValue() <= ((ConstraintAnchor)this.mHorizontalWrapMax.get()).getFinalValue()) {
            return;
        }
        this.mHorizontalWrapMax = new WeakReference(constraintAnchor0);
    }

    public void addHorizontalWrapMinVariable(ConstraintAnchor constraintAnchor0) {
        if(this.mHorizontalWrapMin != null && this.mHorizontalWrapMin.get() != null && constraintAnchor0.getFinalValue() <= ((ConstraintAnchor)this.mHorizontalWrapMin.get()).getFinalValue()) {
            return;
        }
        this.mHorizontalWrapMin = new WeakReference(constraintAnchor0);
    }

    private void addMaxWrap(ConstraintAnchor constraintAnchor0, SolverVariable solverVariable0) {
        SolverVariable solverVariable1 = this.mSystem.createObjectVariable(constraintAnchor0);
        this.mSystem.addGreaterThan(solverVariable0, solverVariable1, 0, 5);
    }

    private void addMinWrap(ConstraintAnchor constraintAnchor0, SolverVariable solverVariable0) {
        SolverVariable solverVariable1 = this.mSystem.createObjectVariable(constraintAnchor0);
        this.mSystem.addGreaterThan(solverVariable1, solverVariable0, 0, 5);
    }

    private void addVerticalChain(ConstraintWidget constraintWidget0) {
        ChainHead[] arr_chainHead = this.mVerticalChainsArray;
        if(this.mVerticalChainsSize + 1 >= arr_chainHead.length) {
            this.mVerticalChainsArray = (ChainHead[])Arrays.copyOf(arr_chainHead, arr_chainHead.length * 2);
        }
        ChainHead[] arr_chainHead1 = this.mVerticalChainsArray;
        int v = this.mVerticalChainsSize;
        arr_chainHead1[v] = new ChainHead(constraintWidget0, 1, this.isRtl());
        ++this.mVerticalChainsSize;
    }

    void addVerticalWrapMaxVariable(ConstraintAnchor constraintAnchor0) {
        if(this.mVerticalWrapMax != null && this.mVerticalWrapMax.get() != null && constraintAnchor0.getFinalValue() <= ((ConstraintAnchor)this.mVerticalWrapMax.get()).getFinalValue()) {
            return;
        }
        this.mVerticalWrapMax = new WeakReference(constraintAnchor0);
    }

    void addVerticalWrapMinVariable(ConstraintAnchor constraintAnchor0) {
        if(this.mVerticalWrapMin != null && this.mVerticalWrapMin.get() != null && constraintAnchor0.getFinalValue() <= ((ConstraintAnchor)this.mVerticalWrapMin.get()).getFinalValue()) {
            return;
        }
        this.mVerticalWrapMin = new WeakReference(constraintAnchor0);
    }

    public void defineTerminalWidgets() {
        this.mDependencyGraph.defineTerminalWidgets(this.getHorizontalDimensionBehaviour(), this.getVerticalDimensionBehaviour());
    }

    public boolean directMeasure(boolean z) {
        return this.mDependencyGraph.directMeasure(z);
    }

    public boolean directMeasureSetup(boolean z) {
        return this.mDependencyGraph.directMeasureSetup(z);
    }

    public boolean directMeasureWithOrientation(boolean z, int v) {
        return this.mDependencyGraph.directMeasureWithOrientation(z, v);
    }

    public void fillMetrics(Metrics metrics0) {
        this.mMetrics = metrics0;
        this.mSystem.fillMetrics(metrics0);
    }

    public ArrayList getHorizontalGuidelines() {
        ArrayList arrayList0 = new ArrayList();
        int v = this.mChildren.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)this.mChildren.get(v1);
            if(constraintWidget0 instanceof Guideline && ((Guideline)constraintWidget0).getOrientation() == 0) {
                arrayList0.add(((Guideline)constraintWidget0));
            }
        }
        return arrayList0;
    }

    public Measurer getMeasurer() {
        return this.mMeasurer;
    }

    public int getOptimizationLevel() {
        return this.mOptimizationLevel;
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void getSceneString(StringBuilder stringBuilder0) {
        stringBuilder0.append(this.stringId + ":{\n");
        stringBuilder0.append("  actualWidth:" + this.mWidth);
        stringBuilder0.append("\n");
        stringBuilder0.append("  actualHeight:" + this.mHeight);
        stringBuilder0.append("\n");
        for(Object object0: this.getChildren()) {
            ((ConstraintWidget)object0).getSceneString(stringBuilder0);
            stringBuilder0.append(",\n");
        }
        stringBuilder0.append("}");
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public String getType() {
        return "ConstraintLayout";
    }

    public ArrayList getVerticalGuidelines() {
        ArrayList arrayList0 = new ArrayList();
        int v = this.mChildren.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)this.mChildren.get(v1);
            if(constraintWidget0 instanceof Guideline && ((Guideline)constraintWidget0).getOrientation() == 1) {
                arrayList0.add(((Guideline)constraintWidget0));
            }
        }
        return arrayList0;
    }

    public boolean handlesInternalConstraints() [...] // 潜在的解密器

    public void invalidateGraph() {
        this.mDependencyGraph.invalidateGraph();
    }

    public void invalidateMeasures() {
        this.mDependencyGraph.invalidateMeasures();
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    @Override  // androidx.constraintlayout.core.widgets.WidgetContainer
    public void layout() {
        boolean z5;
        boolean z4;
        int v6;
        int v5;
        int v4;
        this.mX = 0;
        this.mY = 0;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        int v = this.mChildren.size();
        int v1 = Math.max(0, this.getWidth());
        int v2 = Math.max(0, this.getHeight());
        DimensionBehaviour constraintWidget$DimensionBehaviour0 = this.mListDimensionBehaviors[1];
        DimensionBehaviour constraintWidget$DimensionBehaviour1 = this.mListDimensionBehaviors[0];
        Metrics metrics0 = this.mMetrics;
        if(metrics0 != null) {
            ++metrics0.layouts;
        }
        if(this.mPass == 0 && Optimizer.enabled(this.mOptimizationLevel, 1)) {
            Direct.solvingPass(this, this.getMeasurer());
            for(int v3 = 0; v3 < v; ++v3) {
                ConstraintWidget constraintWidget0 = (ConstraintWidget)this.mChildren.get(v3);
                if(constraintWidget0.isMeasureRequested() && !(constraintWidget0 instanceof Guideline) && !(constraintWidget0 instanceof Barrier) && !(constraintWidget0 instanceof VirtualLayout) && !constraintWidget0.isInVirtualLayout()) {
                    DimensionBehaviour constraintWidget$DimensionBehaviour2 = constraintWidget0.getDimensionBehaviour(0);
                    DimensionBehaviour constraintWidget$DimensionBehaviour3 = constraintWidget0.getDimensionBehaviour(1);
                    if(constraintWidget$DimensionBehaviour2 != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget0.mMatchConstraintDefaultWidth == 1 || constraintWidget$DimensionBehaviour3 != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget0.mMatchConstraintDefaultHeight == 1) {
                        Measure basicMeasure$Measure0 = new Measure();
                        ConstraintWidgetContainer.measure(0, constraintWidget0, this.mMeasurer, basicMeasure$Measure0, Measure.SELF_DIMENSIONS);
                    }
                }
            }
        }
        if(v <= 2 || constraintWidget$DimensionBehaviour1 != DimensionBehaviour.WRAP_CONTENT && constraintWidget$DimensionBehaviour0 != DimensionBehaviour.WRAP_CONTENT || !Optimizer.enabled(this.mOptimizationLevel, 0x400) || !Grouping.simpleSolvingPass(this, this.getMeasurer())) {
            v4 = v2;
            v5 = v1;
            v6 = 0;
        }
        else {
            if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT) {
                if(v1 >= this.getWidth() || v1 <= 0) {
                    v1 = this.getWidth();
                }
                else {
                    this.setWidth(v1);
                    this.mWidthMeasuredTooSmall = true;
                }
            }
            if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT) {
                if(v2 >= this.getHeight() || v2 <= 0) {
                    v2 = this.getHeight();
                }
                else {
                    this.setHeight(v2);
                    this.mHeightMeasuredTooSmall = true;
                }
            }
            v4 = v2;
            v5 = v1;
            v6 = 1;
        }
        boolean z = this.optimizeFor(0x40) || this.optimizeFor(0x80);
        this.mSystem.graphOptimizer = false;
        this.mSystem.newgraphOptimizer = false;
        if(this.mOptimizationLevel != 0 && z) {
            this.mSystem.newgraphOptimizer = true;
        }
        ArrayList arrayList0 = this.mChildren;
        boolean z1 = this.getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT || this.getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT;
        this.resetChains();
        for(int v7 = 0; v7 < v; ++v7) {
            ConstraintWidget constraintWidget1 = (ConstraintWidget)this.mChildren.get(v7);
            if(constraintWidget1 instanceof WidgetContainer) {
                ((WidgetContainer)constraintWidget1).layout();
            }
        }
        boolean z2 = this.optimizeFor(0x40);
        int v8 = v6;
        boolean z3 = true;
        for(int v9 = 0; z3; ++v9) {
            try {
                this.mSystem.reset();
                this.resetChains();
                this.createObjectVariables(this.mSystem);
                for(int v10 = 0; v10 < v; ++v10) {
                    ((ConstraintWidget)this.mChildren.get(v10)).createObjectVariables(this.mSystem);
                }
                z3 = this.addChildrenToSolver(this.mSystem);
                if(this.mVerticalWrapMin != null && this.mVerticalWrapMin.get() != null) {
                    this.addMinWrap(((ConstraintAnchor)this.mVerticalWrapMin.get()), this.mSystem.createObjectVariable(this.mTop));
                    this.mVerticalWrapMin = null;
                }
                if(this.mVerticalWrapMax != null && this.mVerticalWrapMax.get() != null) {
                    this.addMaxWrap(((ConstraintAnchor)this.mVerticalWrapMax.get()), this.mSystem.createObjectVariable(this.mBottom));
                    this.mVerticalWrapMax = null;
                }
                if(this.mHorizontalWrapMin != null && this.mHorizontalWrapMin.get() != null) {
                    this.addMinWrap(((ConstraintAnchor)this.mHorizontalWrapMin.get()), this.mSystem.createObjectVariable(this.mLeft));
                    this.mHorizontalWrapMin = null;
                }
                if(this.mHorizontalWrapMax != null && this.mHorizontalWrapMax.get() != null) {
                    this.addMaxWrap(((ConstraintAnchor)this.mHorizontalWrapMax.get()), this.mSystem.createObjectVariable(this.mRight));
                    this.mHorizontalWrapMax = null;
                }
                if(z3) {
                    this.mSystem.minimize();
                }
            }
            catch(Exception exception0) {
                exception0.printStackTrace();
                System.out.println("EXCEPTION : " + exception0);
            }
            if(z3) {
                z4 = this.updateChildrenFromSolver(this.mSystem, Optimizer.sFlags);
            }
            else {
                this.updateFromSolver(this.mSystem, z2);
                for(int v11 = 0; v11 < v; ++v11) {
                    ((ConstraintWidget)this.mChildren.get(v11)).updateFromSolver(this.mSystem, z2);
                }
                z4 = false;
            }
            if(!z1 || v9 + 1 >= 8 || !Optimizer.sFlags[2]) {
                z5 = z4;
            }
            else {
                int v13 = 0;
                int v14 = 0;
                for(int v12 = 0; v12 < v; ++v12) {
                    ConstraintWidget constraintWidget2 = (ConstraintWidget)this.mChildren.get(v12);
                    v13 = Math.max(v13, constraintWidget2.mX + constraintWidget2.getWidth());
                    v14 = Math.max(v14, constraintWidget2.mY + constraintWidget2.getHeight());
                }
                z5 = z4;
                int v15 = Math.max(this.mMinWidth, v13);
                int v16 = Math.max(this.mMinHeight, v14);
                if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT && this.getWidth() < v15) {
                    this.setWidth(v15);
                    this.mListDimensionBehaviors[0] = DimensionBehaviour.WRAP_CONTENT;
                    v8 = 1;
                    z5 = true;
                }
                if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT && this.getHeight() < v16) {
                    this.setHeight(v16);
                    this.mListDimensionBehaviors[1] = DimensionBehaviour.WRAP_CONTENT;
                    v8 = 1;
                    z5 = true;
                }
            }
            int v17 = Math.max(this.mMinWidth, this.getWidth());
            if(v17 > this.getWidth()) {
                this.setWidth(v17);
                this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
                v8 = 1;
                z5 = true;
            }
            int v18 = Math.max(this.mMinHeight, this.getHeight());
            if(v18 > this.getHeight()) {
                this.setHeight(v18);
                this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
                z5 = true;
                v8 = 1;
            }
            if(v8 == 0) {
                if(this.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT && v5 > 0 && this.getWidth() > v5) {
                    this.mWidthMeasuredTooSmall = true;
                    this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
                    this.setWidth(v5);
                    v8 = 1;
                    z5 = true;
                }
                if(this.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT && v4 > 0 && this.getHeight() > v4) {
                    this.mHeightMeasuredTooSmall = true;
                    this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
                    this.setHeight(v4);
                    v8 = 1;
                    z5 = true;
                }
            }
            z3 = v9 + 1 <= 8 ? z5 : false;
        }
        this.mChildren = arrayList0;
        if(v8 != 0) {
            this.mListDimensionBehaviors[0] = constraintWidget$DimensionBehaviour1;
            this.mListDimensionBehaviors[1] = constraintWidget$DimensionBehaviour0;
        }
        this.resetSolverVariables(this.mSystem.getCache());
    }

    public static boolean measure(int v, ConstraintWidget constraintWidget0, Measurer basicMeasure$Measurer0, Measure basicMeasure$Measure0, int v1) {
        int v3;
        int v2;
        if(basicMeasure$Measurer0 == null) {
            return false;
        }
        if(constraintWidget0.getVisibility() != 8 && !(constraintWidget0 instanceof Guideline) && !(constraintWidget0 instanceof Barrier)) {
            basicMeasure$Measure0.horizontalBehavior = constraintWidget0.getHorizontalDimensionBehaviour();
            basicMeasure$Measure0.verticalBehavior = constraintWidget0.getVerticalDimensionBehaviour();
            basicMeasure$Measure0.horizontalDimension = constraintWidget0.getWidth();
            basicMeasure$Measure0.verticalDimension = constraintWidget0.getHeight();
            basicMeasure$Measure0.measuredNeedsSolverPass = false;
            basicMeasure$Measure0.measureStrategy = v1;
            boolean z = basicMeasure$Measure0.horizontalBehavior == DimensionBehaviour.MATCH_CONSTRAINT;
            boolean z1 = basicMeasure$Measure0.verticalBehavior == DimensionBehaviour.MATCH_CONSTRAINT;
            boolean z2 = z && constraintWidget0.mDimensionRatio > 0.0f;
            boolean z3 = z1 && constraintWidget0.mDimensionRatio > 0.0f;
            if(z && constraintWidget0.hasDanglingDimension(0) && constraintWidget0.mMatchConstraintDefaultWidth == 0 && !z2) {
                basicMeasure$Measure0.horizontalBehavior = DimensionBehaviour.WRAP_CONTENT;
                if(z1 && constraintWidget0.mMatchConstraintDefaultHeight == 0) {
                    basicMeasure$Measure0.horizontalBehavior = DimensionBehaviour.FIXED;
                }
                z = false;
            }
            if(z1 && constraintWidget0.hasDanglingDimension(1) && constraintWidget0.mMatchConstraintDefaultHeight == 0 && !z3) {
                basicMeasure$Measure0.verticalBehavior = DimensionBehaviour.WRAP_CONTENT;
                if(z && constraintWidget0.mMatchConstraintDefaultWidth == 0) {
                    basicMeasure$Measure0.verticalBehavior = DimensionBehaviour.FIXED;
                }
                z1 = false;
            }
            if(constraintWidget0.isResolvedHorizontally()) {
                basicMeasure$Measure0.horizontalBehavior = DimensionBehaviour.FIXED;
                z = false;
            }
            if(constraintWidget0.isResolvedVertically()) {
                basicMeasure$Measure0.verticalBehavior = DimensionBehaviour.FIXED;
                z1 = false;
            }
            if(z2) {
                if(constraintWidget0.mResolvedMatchConstraintDefault[0] == 4) {
                    basicMeasure$Measure0.horizontalBehavior = DimensionBehaviour.FIXED;
                }
                else if(!z1) {
                    if(basicMeasure$Measure0.verticalBehavior == DimensionBehaviour.FIXED) {
                        v2 = basicMeasure$Measure0.verticalDimension;
                    }
                    else {
                        basicMeasure$Measure0.horizontalBehavior = DimensionBehaviour.WRAP_CONTENT;
                        basicMeasure$Measurer0.measure(constraintWidget0, basicMeasure$Measure0);
                        v2 = basicMeasure$Measure0.measuredHeight;
                    }
                    basicMeasure$Measure0.horizontalBehavior = DimensionBehaviour.FIXED;
                    basicMeasure$Measure0.horizontalDimension = (int)(constraintWidget0.getDimensionRatio() * ((float)v2));
                }
            }
            if(z3) {
                if(constraintWidget0.mResolvedMatchConstraintDefault[1] == 4) {
                    basicMeasure$Measure0.verticalBehavior = DimensionBehaviour.FIXED;
                }
                else if(!z) {
                    if(basicMeasure$Measure0.horizontalBehavior == DimensionBehaviour.FIXED) {
                        v3 = basicMeasure$Measure0.horizontalDimension;
                    }
                    else {
                        basicMeasure$Measure0.verticalBehavior = DimensionBehaviour.WRAP_CONTENT;
                        basicMeasure$Measurer0.measure(constraintWidget0, basicMeasure$Measure0);
                        v3 = basicMeasure$Measure0.measuredWidth;
                    }
                    basicMeasure$Measure0.verticalBehavior = DimensionBehaviour.FIXED;
                    basicMeasure$Measure0.verticalDimension = constraintWidget0.getDimensionRatioSide() == -1 ? ((int)(((float)v3) / constraintWidget0.getDimensionRatio())) : ((int)(constraintWidget0.getDimensionRatio() * ((float)v3)));
                }
            }
            basicMeasure$Measurer0.measure(constraintWidget0, basicMeasure$Measure0);
            constraintWidget0.setWidth(basicMeasure$Measure0.measuredWidth);
            constraintWidget0.setHeight(basicMeasure$Measure0.measuredHeight);
            constraintWidget0.setHasBaseline(basicMeasure$Measure0.measuredHasBaseline);
            constraintWidget0.setBaselineDistance(basicMeasure$Measure0.measuredBaseline);
            basicMeasure$Measure0.measureStrategy = Measure.SELF_DIMENSIONS;
            return basicMeasure$Measure0.measuredNeedsSolverPass;
        }
        basicMeasure$Measure0.measuredWidth = 0;
        basicMeasure$Measure0.measuredHeight = 0;
        return false;
    }

    public long measure(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8) {
        this.mPaddingLeft = v7;
        this.mPaddingTop = v8;
        return this.mBasicMeasureSolver.solverMeasure(this, v, v7, v8, v1, v2, v3, v4, v5, v6);
    }

    public boolean optimizeFor(int v) {
        return (this.mOptimizationLevel & v) == v;
    }

    @Override  // androidx.constraintlayout.core.widgets.WidgetContainer
    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mSkipSolver = false;
        super.reset();
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    public void setMeasurer(Measurer basicMeasure$Measurer0) {
        this.mMeasurer = basicMeasure$Measurer0;
        this.mDependencyGraph.setMeasurer(basicMeasure$Measurer0);
    }

    public void setOptimizationLevel(int v) {
        this.mOptimizationLevel = v;
        LinearSystem.USE_DEPENDENCY_ORDERING = this.optimizeFor(0x200);
    }

    public void setPadding(int v, int v1, int v2, int v3) {
        this.mPaddingLeft = v;
        this.mPaddingTop = v1;
        this.mPaddingRight = v2;
        this.mPaddingBottom = v3;
    }

    public void setPass(int v) {
        this.mPass = v;
    }

    public void setRtl(boolean z) {
        this.mIsRtl = z;
    }

    public boolean updateChildrenFromSolver(LinearSystem linearSystem0, boolean[] arr_z) {
        arr_z[2] = false;
        boolean z = this.optimizeFor(0x40);
        this.updateFromSolver(linearSystem0, z);
        int v1 = this.mChildren.size();
        boolean z1 = false;
        for(int v = 0; v < v1; ++v) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)this.mChildren.get(v);
            constraintWidget0.updateFromSolver(linearSystem0, z);
            if(constraintWidget0.hasDimensionOverride()) {
                z1 = true;
            }
        }
        return z1;
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void updateFromRuns(boolean z, boolean z1) {
        super.updateFromRuns(z, z1);
        int v = this.mChildren.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((ConstraintWidget)this.mChildren.get(v1)).updateFromRuns(z, z1);
        }
    }

    public void updateHierarchy() {
        this.mBasicMeasureSolver.updateHierarchy(this);
    }
}

