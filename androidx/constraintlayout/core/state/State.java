package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.state.helpers.AlignHorizontallyReference;
import androidx.constraintlayout.core.state.helpers.AlignVerticallyReference;
import androidx.constraintlayout.core.state.helpers.BarrierReference;
import androidx.constraintlayout.core.state.helpers.FlowReference;
import androidx.constraintlayout.core.state.helpers.GridReference;
import androidx.constraintlayout.core.state.helpers.GuidelineReference;
import androidx.constraintlayout.core.state.helpers.HorizontalChainReference;
import androidx.constraintlayout.core.state.helpers.VerticalChainReference;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class State {
    public static enum Chain {
        SPREAD,
        SPREAD_INSIDE,
        PACKED;

        public static Map chainMap;
        public static Map valueMap;

        private static Chain[] $values() [...] // Inlined contents

        static {
            Chain.chainMap = new HashMap();
            Chain.valueMap = new HashMap();
            Chain.chainMap.put("packed", Chain.PACKED);
            Chain.chainMap.put("spread_inside", Chain.SPREAD_INSIDE);
            Chain.chainMap.put("spread", Chain.SPREAD);
            Chain.valueMap.put("packed", 2);
            Chain.valueMap.put("spread_inside", 1);
            Chain.valueMap.put("spread", 0);
        }

        // 去混淆评级： 低(20)
        public static Chain getChainByString(String s) {
            return Chain.chainMap.containsKey(s) ? ((Chain)Chain.chainMap.get(s)) : null;
        }

        // 去混淆评级： 低(20)
        public static int getValueByString(String s) {
            return Chain.valueMap.containsKey(s) ? ((int)(((Integer)Chain.valueMap.get(s)))) : -1;
        }
    }

    public static enum Constraint {
        LEFT_TO_LEFT,
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT,
        RIGHT_TO_RIGHT,
        START_TO_START,
        START_TO_END,
        END_TO_START,
        END_TO_END,
        TOP_TO_TOP,
        TOP_TO_BOTTOM,
        TOP_TO_BASELINE,
        BOTTOM_TO_TOP,
        BOTTOM_TO_BOTTOM,
        BOTTOM_TO_BASELINE,
        BASELINE_TO_BASELINE,
        BASELINE_TO_TOP,
        BASELINE_TO_BOTTOM,
        CENTER_HORIZONTALLY,
        CENTER_VERTICALLY,
        CIRCULAR_CONSTRAINT;

        private static Constraint[] $values() [...] // Inlined contents
    }

    public static enum Direction {
        LEFT,
        RIGHT,
        START,
        END,
        TOP,
        BOTTOM;

        private static Direction[] $values() [...] // Inlined contents
    }

    public static enum Helper {
        HORIZONTAL_CHAIN,
        VERTICAL_CHAIN,
        ALIGN_HORIZONTALLY,
        ALIGN_VERTICALLY,
        BARRIER,
        LAYER,
        HORIZONTAL_FLOW,
        VERTICAL_FLOW,
        GRID,
        ROW,
        COLUMN,
        FLOW;

        private static Helper[] $values() [...] // Inlined contents
    }

    public static enum Wrap {
        NONE,
        CHAIN,
        ALIGNED;

        public static Map valueMap;
        public static Map wrapMap;

        private static Wrap[] $values() [...] // Inlined contents

        static {
            Wrap.wrapMap = new HashMap();
            Wrap.valueMap = new HashMap();
            Wrap.wrapMap.put("none", Wrap.NONE);
            Wrap.wrapMap.put("chain", Wrap.CHAIN);
            Wrap.wrapMap.put("aligned", Wrap.ALIGNED);
            Wrap.valueMap.put("none", 0);
            Wrap.valueMap.put("chain", 3);
            Wrap.valueMap.put("aligned", 2);
        }

        // 去混淆评级： 低(20)
        public static Wrap getChainByString(String s) {
            return Wrap.wrapMap.containsKey(s) ? ((Wrap)Wrap.wrapMap.get(s)) : null;
        }

        // 去混淆评级： 低(20)
        public static int getValueByString(String s) {
            return Wrap.valueMap.containsKey(s) ? ((int)(((Integer)Wrap.valueMap.get(s)))) : -1;
        }
    }

    static final int CONSTRAINT_RATIO = 2;
    static final int CONSTRAINT_SPREAD = 0;
    static final int CONSTRAINT_WRAP = 1;
    public static final Integer PARENT = null;
    static final int UNKNOWN = -1;
    ArrayList mBaselineNeeded;
    ArrayList mBaselineNeededWidgets;
    boolean mDirtyBaselineNeededWidgets;
    private CorePixelDp mDpToPixel;
    protected HashMap mHelperReferences;
    private boolean mIsLtr;
    private int mNumHelpers;
    public final ConstraintReference mParent;
    protected HashMap mReferences;
    HashMap mTags;

    static {
        State.PARENT = 0;
    }

    public State() {
        this.mIsLtr = true;
        this.mReferences = new HashMap();
        this.mHelperReferences = new HashMap();
        this.mTags = new HashMap();
        ConstraintReference constraintReference0 = new ConstraintReference(this);
        this.mParent = constraintReference0;
        this.mNumHelpers = 0;
        this.mBaselineNeeded = new ArrayList();
        this.mBaselineNeededWidgets = new ArrayList();
        this.mDirtyBaselineNeededWidgets = true;
        constraintReference0.setKey(State.PARENT);
        this.mReferences.put(State.PARENT, constraintReference0);
    }

    public void apply(ConstraintWidgetContainer constraintWidgetContainer0) {
        constraintWidgetContainer0.removeAllChildren();
        this.mParent.getWidth().apply(this, constraintWidgetContainer0, 0);
        this.mParent.getHeight().apply(this, constraintWidgetContainer0, 1);
        for(Object object0: this.mHelperReferences.keySet()) {
            HelperWidget helperWidget0 = ((HelperReference)this.mHelperReferences.get(object0)).getHelperWidget();
            if(helperWidget0 != null) {
                Reference reference0 = (Reference)this.mReferences.get(object0);
                if(reference0 == null) {
                    reference0 = this.constraints(object0);
                }
                reference0.setConstraintWidget(helperWidget0);
            }
        }
        for(Object object1: this.mReferences.keySet()) {
            Reference reference1 = (Reference)this.mReferences.get(object1);
            if(reference1 != this.mParent && reference1.getFacade() instanceof HelperReference) {
                HelperWidget helperWidget1 = ((HelperReference)reference1.getFacade()).getHelperWidget();
                if(helperWidget1 != null) {
                    Reference reference2 = (Reference)this.mReferences.get(object1);
                    if(reference2 == null) {
                        reference2 = this.constraints(object1);
                    }
                    reference2.setConstraintWidget(helperWidget1);
                }
            }
        }
        for(Object object2: this.mReferences.keySet()) {
            Reference reference3 = (Reference)this.mReferences.get(object2);
            if(reference3 == this.mParent) {
                reference3.setConstraintWidget(constraintWidgetContainer0);
            }
            else {
                ConstraintWidget constraintWidget0 = reference3.getConstraintWidget();
                constraintWidget0.setDebugName(reference3.getKey().toString());
                constraintWidget0.setParent(null);
                if(reference3.getFacade() instanceof GuidelineReference) {
                    reference3.apply();
                }
                constraintWidgetContainer0.add(constraintWidget0);
            }
        }
        for(Object object3: this.mHelperReferences.keySet()) {
            HelperReference helperReference0 = (HelperReference)this.mHelperReferences.get(object3);
            if(helperReference0.getHelperWidget() != null) {
                for(Object object4: helperReference0.mReferences) {
                    Reference reference4 = (Reference)this.mReferences.get(object4);
                    helperReference0.getHelperWidget().add(reference4.getConstraintWidget());
                }
            }
            helperReference0.apply();
        }
        for(Object object5: this.mReferences.keySet()) {
            Reference reference5 = (Reference)this.mReferences.get(object5);
            if(reference5 != this.mParent && reference5.getFacade() instanceof HelperReference) {
                HelperReference helperReference1 = (HelperReference)reference5.getFacade();
                HelperWidget helperWidget2 = helperReference1.getHelperWidget();
                if(helperWidget2 != null) {
                    for(Object object6: helperReference1.mReferences) {
                        Reference reference6 = (Reference)this.mReferences.get(object6);
                        if(reference6 != null) {
                            helperWidget2.add(reference6.getConstraintWidget());
                        }
                        else if(object6 instanceof Reference) {
                            helperWidget2.add(((Reference)object6).getConstraintWidget());
                        }
                        else {
                            System.out.println("couldn\'t find reference for " + object6);
                        }
                    }
                    reference5.apply();
                }
            }
        }
        for(Object object7: this.mReferences.keySet()) {
            Reference reference7 = (Reference)this.mReferences.get(object7);
            reference7.apply();
            ConstraintWidget constraintWidget1 = reference7.getConstraintWidget();
            if(constraintWidget1 != null && object7 != null) {
                constraintWidget1.stringId = object7.toString();
            }
        }
    }

    public BarrierReference barrier(Object object0, Direction state$Direction0) {
        ConstraintReference constraintReference0 = this.constraints(object0);
        if(constraintReference0.getFacade() == null || !(constraintReference0.getFacade() instanceof BarrierReference)) {
            BarrierReference barrierReference0 = new BarrierReference(this);
            barrierReference0.setBarrierDirection(state$Direction0);
            constraintReference0.setFacade(barrierReference0);
        }
        return (BarrierReference)constraintReference0.getFacade();
    }

    public void baselineNeededFor(Object object0) {
        this.mBaselineNeeded.add(object0);
        this.mDirtyBaselineNeededWidgets = true;
    }

    public AlignHorizontallyReference centerHorizontally(Object[] arr_object) {
        AlignHorizontallyReference alignHorizontallyReference0 = (AlignHorizontallyReference)this.helper(null, Helper.ALIGN_HORIZONTALLY);
        alignHorizontallyReference0.add(arr_object);
        return alignHorizontallyReference0;
    }

    public AlignVerticallyReference centerVertically(Object[] arr_object) {
        AlignVerticallyReference alignVerticallyReference0 = (AlignVerticallyReference)this.helper(null, Helper.ALIGN_VERTICALLY);
        alignVerticallyReference0.add(arr_object);
        return alignVerticallyReference0;
    }

    public ConstraintReference constraints(Object object0) {
        Reference reference0 = (Reference)this.mReferences.get(object0);
        if(reference0 == null) {
            reference0 = this.createConstraintReference(object0);
            this.mReferences.put(object0, reference0);
            reference0.setKey(object0);
        }
        return reference0 instanceof ConstraintReference ? ((ConstraintReference)reference0) : null;
    }

    public int convertDimension(Object object0) {
        if(object0 instanceof Float) {
            return Math.round(((float)(((Float)object0))));
        }
        return object0 instanceof Integer ? ((int)(((Integer)object0))) : 0;
    }

    public ConstraintReference createConstraintReference(Object object0) {
        return new ConstraintReference(this);
    }

    private String createHelperKey() [...] // 潜在的解密器

    public void directMapping() {
        for(Object object0: this.mReferences.keySet()) {
            ConstraintReference constraintReference0 = this.constraints(object0);
            if(constraintReference0 instanceof ConstraintReference) {
                constraintReference0.setView(object0);
            }
        }
    }

    CorePixelDp getDpToPixel() {
        return this.mDpToPixel;
    }

    public FlowReference getFlow(Object object0, boolean z) {
        ConstraintReference constraintReference0 = this.constraints(object0);
        if(constraintReference0.getFacade() == null || !(constraintReference0.getFacade() instanceof FlowReference)) {
            constraintReference0.setFacade((z ? new FlowReference(this, Helper.VERTICAL_FLOW) : new FlowReference(this, Helper.HORIZONTAL_FLOW)));
        }
        return (FlowReference)constraintReference0.getFacade();
    }

    public GridReference getGrid(Object object0, String s) {
        ConstraintReference constraintReference0 = this.constraints(object0);
        if(constraintReference0.getFacade() == null || !(constraintReference0.getFacade() instanceof GridReference)) {
            Helper state$Helper0 = Helper.GRID;
            if(s.charAt(0) == 0x72) {
                state$Helper0 = Helper.ROW;
            }
            else if(s.charAt(0) == 99) {
                state$Helper0 = Helper.COLUMN;
            }
            constraintReference0.setFacade(new GridReference(this, state$Helper0));
        }
        return (GridReference)constraintReference0.getFacade();
    }

    public FlowReference getHorizontalFlow() {
        return (FlowReference)this.helper(null, Helper.HORIZONTAL_FLOW);
    }

    public FlowReference getHorizontalFlow(Object[] arr_object) {
        FlowReference flowReference0 = (FlowReference)this.helper(null, Helper.HORIZONTAL_FLOW);
        flowReference0.add(arr_object);
        return flowReference0;
    }

    // 去混淆评级： 低(20)
    public ArrayList getIdsForTag(String s) {
        return this.mTags.containsKey(s) ? ((ArrayList)this.mTags.get(s)) : null;
    }

    public FlowReference getVerticalFlow() {
        return (FlowReference)this.helper(null, Helper.VERTICAL_FLOW);
    }

    public FlowReference getVerticalFlow(Object[] arr_object) {
        FlowReference flowReference0 = (FlowReference)this.helper(null, Helper.VERTICAL_FLOW);
        flowReference0.add(arr_object);
        return flowReference0;
    }

    public GuidelineReference guideline(Object object0, int v) {
        ConstraintReference constraintReference0 = this.constraints(object0);
        if(constraintReference0.getFacade() == null || !(constraintReference0.getFacade() instanceof GuidelineReference)) {
            GuidelineReference guidelineReference0 = new GuidelineReference(this);
            guidelineReference0.setOrientation(v);
            guidelineReference0.setKey(object0);
            constraintReference0.setFacade(guidelineReference0);
        }
        return (GuidelineReference)constraintReference0.getFacade();
    }

    public State height(Dimension dimension0) {
        return this.setHeight(dimension0);
    }

    public HelperReference helper(Object object0, Helper state$Helper0) {
        if(object0 == null) {
            object0 = "__HELPER_KEY_0__";
        }
        HelperReference helperReference0 = (HelperReference)this.mHelperReferences.get(object0);
        if(helperReference0 == null) {
            switch(state$Helper0.ordinal()) {
                case 0: {
                    helperReference0 = new HorizontalChainReference(this);
                    break;
                }
                case 1: {
                    helperReference0 = new VerticalChainReference(this);
                    break;
                }
                case 2: {
                    helperReference0 = new AlignHorizontallyReference(this);
                    break;
                }
                case 3: {
                    helperReference0 = new AlignVerticallyReference(this);
                    break;
                }
                case 4: {
                    helperReference0 = new BarrierReference(this);
                    break;
                }
                case 6: 
                case 7: {
                    helperReference0 = new FlowReference(this, state$Helper0);
                    break;
                }
                case 8: 
                case 9: 
                case 10: {
                    helperReference0 = new GridReference(this, state$Helper0);
                    break;
                }
                default: {
                    helperReference0 = new HelperReference(this, state$Helper0);
                }
            }
            helperReference0.setKey(object0);
            this.mHelperReferences.put(object0, helperReference0);
        }
        return helperReference0;
    }

    public HorizontalChainReference horizontalChain() {
        return (HorizontalChainReference)this.helper(null, Helper.HORIZONTAL_CHAIN);
    }

    public HorizontalChainReference horizontalChain(Object[] arr_object) {
        HorizontalChainReference horizontalChainReference0 = (HorizontalChainReference)this.helper(null, Helper.HORIZONTAL_CHAIN);
        horizontalChainReference0.add(arr_object);
        return horizontalChainReference0;
    }

    public GuidelineReference horizontalGuideline(Object object0) {
        return this.guideline(object0, 0);
    }

    public boolean isBaselineNeeded(ConstraintWidget constraintWidget0) {
        if(this.mDirtyBaselineNeededWidgets) {
            this.mBaselineNeededWidgets.clear();
            for(Object object0: this.mBaselineNeeded) {
                ConstraintWidget constraintWidget1 = ((Reference)this.mReferences.get(object0)).getConstraintWidget();
                if(constraintWidget1 != null) {
                    this.mBaselineNeededWidgets.add(constraintWidget1);
                }
            }
            this.mDirtyBaselineNeededWidgets = false;
        }
        return this.mBaselineNeededWidgets.contains(constraintWidget0);
    }

    @Deprecated
    public boolean isLtr() {
        return this.mIsLtr;
    }

    public boolean isRtl() {
        return !this.mIsLtr;
    }

    public void map(Object object0, Object object1) {
        ConstraintReference constraintReference0 = this.constraints(object0);
        if(constraintReference0 != null) {
            constraintReference0.setView(object1);
        }
    }

    Reference reference(Object object0) {
        return (Reference)this.mReferences.get(object0);
    }

    public void reset() {
        for(Object object0: this.mReferences.keySet()) {
            ((Reference)this.mReferences.get(object0)).getConstraintWidget().reset();
        }
        this.mReferences.clear();
        this.mReferences.put(State.PARENT, this.mParent);
        this.mHelperReferences.clear();
        this.mTags.clear();
        this.mBaselineNeeded.clear();
        this.mDirtyBaselineNeededWidgets = true;
    }

    public boolean sameFixedHeight(int v) {
        return this.mParent.getHeight().equalsFixedValue(v);
    }

    public boolean sameFixedWidth(int v) {
        return this.mParent.getWidth().equalsFixedValue(v);
    }

    public void setDpToPixel(CorePixelDp corePixelDp0) {
        this.mDpToPixel = corePixelDp0;
    }

    public State setHeight(Dimension dimension0) {
        this.mParent.setHeight(dimension0);
        return this;
    }

    @Deprecated
    public void setLtr(boolean z) {
        this.mIsLtr = z;
    }

    public void setRtl(boolean z) {
        this.mIsLtr = !z;
    }

    public void setTag(String s, String s1) {
        ArrayList arrayList0;
        ConstraintReference constraintReference0 = this.constraints(s);
        if(constraintReference0 instanceof ConstraintReference) {
            constraintReference0.setTag(s1);
            if(this.mTags.containsKey(s1)) {
                arrayList0 = (ArrayList)this.mTags.get(s1);
            }
            else {
                arrayList0 = new ArrayList();
                this.mTags.put(s1, arrayList0);
            }
            arrayList0.add(s);
        }
    }

    public State setWidth(Dimension dimension0) {
        this.mParent.setWidth(dimension0);
        return this;
    }

    public VerticalChainReference verticalChain() {
        return (VerticalChainReference)this.helper(null, Helper.VERTICAL_CHAIN);
    }

    public VerticalChainReference verticalChain(Object[] arr_object) {
        VerticalChainReference verticalChainReference0 = (VerticalChainReference)this.helper(null, Helper.VERTICAL_CHAIN);
        verticalChainReference0.add(arr_object);
        return verticalChainReference0;
    }

    public GuidelineReference verticalGuideline(Object object0) {
        return this.guideline(object0, 1);
    }

    public State width(Dimension dimension0) {
        return this.setWidth(dimension0);
    }
}

