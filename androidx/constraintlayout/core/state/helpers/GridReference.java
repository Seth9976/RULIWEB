package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State.Helper;
import androidx.constraintlayout.core.state.State;
import androidx.constraintlayout.core.utils.GridCore;
import androidx.constraintlayout.core.widgets.HelperWidget;

public class GridReference extends HelperReference {
    private static final String SPANS_RESPECT_WIDGET_ORDER_STRING = "spansrespectwidgetorder";
    private static final String SUB_GRID_BY_COL_ROW_STRING = "subgridbycolrow";
    private String mColumnWeights;
    private int mColumnsSet;
    private int mFlags;
    private GridCore mGrid;
    private float mHorizontalGaps;
    private int mOrientation;
    private int mPaddingBottom;
    private int mPaddingEnd;
    private int mPaddingStart;
    private int mPaddingTop;
    private String mRowWeights;
    private int mRowsSet;
    private String mSkips;
    private String mSpans;
    private float mVerticalGaps;

    public GridReference(State state0, Helper state$Helper0) {
        super(state0, state$Helper0);
        this.mPaddingStart = 0;
        this.mPaddingEnd = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        if(state$Helper0 == Helper.ROW) {
            this.mRowsSet = 1;
            return;
        }
        if(state$Helper0 == Helper.COLUMN) {
            this.mColumnsSet = 1;
        }
    }

    @Override  // androidx.constraintlayout.core.state.HelperReference
    public void apply() {
        this.getHelperWidget();
        this.mGrid.setOrientation(this.mOrientation);
        int v = this.mRowsSet;
        if(v != 0) {
            this.mGrid.setRows(v);
        }
        int v1 = this.mColumnsSet;
        if(v1 != 0) {
            this.mGrid.setColumns(v1);
        }
        float f = this.mHorizontalGaps;
        if(f != 0.0f) {
            this.mGrid.setHorizontalGaps(f);
        }
        float f1 = this.mVerticalGaps;
        if(f1 != 0.0f) {
            this.mGrid.setVerticalGaps(f1);
        }
        if(this.mRowWeights != null && !this.mRowWeights.isEmpty()) {
            this.mGrid.setRowWeights(this.mRowWeights);
        }
        if(this.mColumnWeights != null && !this.mColumnWeights.isEmpty()) {
            this.mGrid.setColumnWeights(this.mColumnWeights);
        }
        if(this.mSpans != null && !this.mSpans.isEmpty()) {
            this.mGrid.setSpans(this.mSpans);
        }
        if(this.mSkips != null && !this.mSkips.isEmpty()) {
            this.mGrid.setSkips(this.mSkips);
        }
        this.mGrid.setFlags(this.mFlags);
        this.mGrid.setPaddingStart(this.mPaddingStart);
        this.mGrid.setPaddingEnd(this.mPaddingEnd);
        this.mGrid.setPaddingTop(this.mPaddingTop);
        this.mGrid.setPaddingBottom(this.mPaddingBottom);
        this.applyBase();
    }

    public String getColumnWeights() {
        return this.mColumnWeights;
    }

    public int getColumnsSet() {
        return this.mColumnsSet;
    }

    public int getFlags() {
        return this.mFlags;
    }

    @Override  // androidx.constraintlayout.core.state.HelperReference
    public HelperWidget getHelperWidget() {
        if(this.mGrid == null) {
            this.mGrid = new GridCore();
        }
        return this.mGrid;
    }

    public float getHorizontalGaps() {
        return this.mHorizontalGaps;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getPaddingEnd() {
        return this.mPaddingEnd;
    }

    public int getPaddingStart() {
        return this.mPaddingStart;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public String getRowWeights() {
        return this.mRowWeights;
    }

    public int getRowsSet() {
        return this.mRowsSet;
    }

    public String getSkips() {
        return this.mSkips;
    }

    public String getSpans() {
        return this.mSpans;
    }

    public float getVerticalGaps() {
        return this.mVerticalGaps;
    }

    public void setColumnWeights(String s) {
        this.mColumnWeights = s;
    }

    public void setColumnsSet(int v) {
        if(super.getType() == Helper.ROW) {
            return;
        }
        this.mColumnsSet = v;
    }

    public void setFlags(int v) {
        this.mFlags = v;
    }

    public void setFlags(String s) {
        if(!s.isEmpty()) {
            String[] arr_s = s.split("\\|");
            this.mFlags = 0;
            for(int v = 0; v < arr_s.length; ++v) {
                String s1 = arr_s[v].toLowerCase();
                s1.hashCode();
                if(s1.equals("subgridbycolrow")) {
                    this.mFlags |= 1;
                }
                else if(s1.equals("spansrespectwidgetorder")) {
                    this.mFlags |= 2;
                }
            }
        }
    }

    @Override  // androidx.constraintlayout.core.state.HelperReference
    public void setHelperWidget(HelperWidget helperWidget0) {
        if(helperWidget0 instanceof GridCore) {
            this.mGrid = (GridCore)helperWidget0;
            return;
        }
        this.mGrid = null;
    }

    public void setHorizontalGaps(float f) {
        this.mHorizontalGaps = f;
    }

    public void setOrientation(int v) {
        this.mOrientation = v;
    }

    public void setPaddingBottom(int v) {
        this.mPaddingBottom = v;
    }

    public void setPaddingEnd(int v) {
        this.mPaddingEnd = v;
    }

    public void setPaddingStart(int v) {
        this.mPaddingStart = v;
    }

    public void setPaddingTop(int v) {
        this.mPaddingTop = v;
    }

    public void setRowWeights(String s) {
        this.mRowWeights = s;
    }

    public void setRowsSet(int v) {
        if(super.getType() == Helper.COLUMN) {
            return;
        }
        this.mRowsSet = v;
    }

    public void setSkips(String s) {
        this.mSkips = s;
    }

    public void setSpans(String s) {
        this.mSpans = s;
    }

    public void setVerticalGaps(float f) {
        this.mVerticalGaps = f;
    }
}

