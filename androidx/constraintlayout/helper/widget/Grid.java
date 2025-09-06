package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R.styleable;
import androidx.constraintlayout.widget.VirtualLayout;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Grid extends VirtualLayout {
    private static final boolean DEBUG_BOXES = false;
    public static final int HORIZONTAL = 0;
    private static final String TAG = "Grid";
    public static final int VERTICAL = 1;
    private int[] mBoxViewIds;
    private View[] mBoxViews;
    private int mColumns;
    private int mColumnsSet;
    ConstraintLayout mContainer;
    private float mHorizontalGaps;
    private final int mMaxColumns;
    private final int mMaxRows;
    private int mNextAvailableIndex;
    private int mOrientation;
    private boolean[][] mPositionMatrix;
    private int mRows;
    private int mRowsSet;
    Set mSpanIds;
    private String mStrColumnWeights;
    private String mStrRowWeights;
    private String mStrSkips;
    private String mStrSpans;
    private boolean mUseRtl;
    private boolean mValidateInputs;
    private float mVerticalGaps;

    public Grid(Context context0) {
        super(context0);
        this.mMaxRows = 50;
        this.mMaxColumns = 50;
        this.mNextAvailableIndex = 0;
        this.mSpanIds = new HashSet();
    }

    public Grid(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mMaxRows = 50;
        this.mMaxColumns = 50;
        this.mNextAvailableIndex = 0;
        this.mSpanIds = new HashSet();
    }

    public Grid(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mMaxRows = 50;
        this.mMaxColumns = 50;
        this.mNextAvailableIndex = 0;
        this.mSpanIds = new HashSet();
    }

    private boolean arrangeWidgets() {
        View[] arr_view = this.getViews(this.mContainer);
        for(int v = 0; v < this.mCount; ++v) {
            if(!this.mSpanIds.contains(((int)this.mIds[v]))) {
                int v1 = this.getNextPosition();
                int v2 = this.getRowByIndex(v1);
                int v3 = this.getColByIndex(v1);
                if(v1 == -1) {
                    return false;
                }
                this.connectView(arr_view[v], v2, v3, 1, 1);
            }
        }
        return true;
    }

    private void buildBoxes() {
        int v = Math.max(this.mRows, this.mColumns);
        View[] arr_view = this.mBoxViews;
        if(arr_view == null) {
            this.mBoxViews = new View[v];
            for(int v2 = 0; true; ++v2) {
                View[] arr_view1 = this.mBoxViews;
                if(v2 >= arr_view1.length) {
                    break;
                }
                arr_view1[v2] = this.makeNewView();
            }
        }
        else if(v != arr_view.length) {
            View[] arr_view2 = new View[v];
            for(int v3 = 0; v3 < v; ++v3) {
                View[] arr_view3 = this.mBoxViews;
                arr_view2[v3] = v3 < arr_view3.length ? arr_view3[v3] : this.makeNewView();
            }
            for(int v4 = v; true; ++v4) {
                View[] arr_view4 = this.mBoxViews;
                if(v4 >= arr_view4.length) {
                    break;
                }
                this.mContainer.removeView(arr_view4[v4]);
            }
            this.mBoxViews = arr_view2;
        }
        this.mBoxViewIds = new int[v];
        for(int v1 = 0; true; ++v1) {
            View[] arr_view5 = this.mBoxViews;
            if(v1 >= arr_view5.length) {
                break;
            }
            int[] arr_v = this.mBoxViewIds;
            arr_v[v1] = arr_view5[v1].getId();
        }
        this.setBoxViewVerticalChains();
        this.setBoxViewHorizontalChains();
    }

    private void clearHParams(View view0) {
        LayoutParams constraintLayout$LayoutParams0 = this.params(view0);
        constraintLayout$LayoutParams0.horizontalWeight = -1.0f;
        constraintLayout$LayoutParams0.leftToRight = -1;
        constraintLayout$LayoutParams0.leftToLeft = -1;
        constraintLayout$LayoutParams0.rightToLeft = -1;
        constraintLayout$LayoutParams0.rightToRight = -1;
        constraintLayout$LayoutParams0.leftMargin = -1;
        view0.setLayoutParams(constraintLayout$LayoutParams0);
    }

    private void clearVParams(View view0) {
        LayoutParams constraintLayout$LayoutParams0 = this.params(view0);
        constraintLayout$LayoutParams0.verticalWeight = -1.0f;
        constraintLayout$LayoutParams0.topToBottom = -1;
        constraintLayout$LayoutParams0.topToTop = -1;
        constraintLayout$LayoutParams0.bottomToTop = -1;
        constraintLayout$LayoutParams0.bottomToBottom = -1;
        constraintLayout$LayoutParams0.topMargin = -1;
        view0.setLayoutParams(constraintLayout$LayoutParams0);
    }

    private void connectView(View view0, int v, int v1, int v2, int v3) {
        LayoutParams constraintLayout$LayoutParams0 = this.params(view0);
        constraintLayout$LayoutParams0.leftToLeft = this.mBoxViewIds[v1];
        constraintLayout$LayoutParams0.topToTop = this.mBoxViewIds[v];
        constraintLayout$LayoutParams0.rightToRight = this.mBoxViewIds[v1 + v3 - 1];
        constraintLayout$LayoutParams0.bottomToBottom = this.mBoxViewIds[v + v2 - 1];
        view0.setLayoutParams(constraintLayout$LayoutParams0);
    }

    private boolean generateGrid(boolean z) {
        boolean z1;
        if(this.mContainer != null && this.mRows >= 1 && this.mColumns >= 1) {
            if(z) {
                for(int v = 0; v < this.mPositionMatrix.length; ++v) {
                    for(int v1 = 0; true; ++v1) {
                        boolean[][] arr2_z = this.mPositionMatrix;
                        if(v1 >= arr2_z[0].length) {
                            break;
                        }
                        arr2_z[v][v1] = true;
                    }
                }
                this.mSpanIds.clear();
            }
            this.mNextAvailableIndex = 0;
            this.buildBoxes();
            if(this.mStrSkips == null || this.mStrSkips.trim().isEmpty()) {
                z1 = true;
            }
            else {
                int[][] arr2_v = this.parseSpans(this.mStrSkips);
                z1 = arr2_v == null ? true : this.handleSkips(arr2_v);
            }
            if(this.mStrSpans != null && !this.mStrSpans.trim().isEmpty()) {
                int[][] arr2_v1 = this.parseSpans(this.mStrSpans);
                if(arr2_v1 != null) {
                    z1 &= this.handleSpans(this.mIds, arr2_v1);
                }
            }
            return (z1 & this.arrangeWidgets()) != 0 || !this.mValidateInputs;
        }
        return false;
    }

    private int getColByIndex(int v) {
        return this.mOrientation == 1 ? v / this.mRows : v % this.mColumns;
    }

    public String getColumnWeights() {
        return this.mStrColumnWeights;
    }

    public int getColumns() {
        return this.mColumnsSet;
    }

    public float getHorizontalGaps() {
        return this.mHorizontalGaps;
    }

    private int getNextPosition() {
        int v = 0;
        boolean z = false;
        while(!z) {
            v = this.mNextAvailableIndex;
            if(v >= this.mRows * this.mColumns) {
                return -1;
            }
            int v1 = this.getRowByIndex(v);
            int v2 = this.getColByIndex(this.mNextAvailableIndex);
            boolean[] arr_z = this.mPositionMatrix[v1];
            if(arr_z[v2]) {
                arr_z[v2] = false;
                z = true;
            }
            ++this.mNextAvailableIndex;
        }
        return v;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    private int getRowByIndex(int v) {
        return this.mOrientation == 1 ? v % this.mRows : v / this.mColumns;
    }

    public String getRowWeights() {
        return this.mStrRowWeights;
    }

    public int getRows() {
        return this.mRowsSet;
    }

    public String getSkips() {
        return this.mStrSkips;
    }

    public String getSpans() {
        return this.mStrSpans;
    }

    public float getVerticalGaps() {
        return this.mVerticalGaps;
    }

    private boolean handleSkips(int[][] arr2_v) {
        for(int v = 0; v < arr2_v.length; ++v) {
            int v1 = this.getRowByIndex(arr2_v[v][0]);
            int v2 = this.getColByIndex(arr2_v[v][0]);
            int[] arr_v = arr2_v[v];
            if(!this.invalidatePositions(v1, v2, arr_v[1], arr_v[2])) {
                return false;
            }
        }
        return true;
    }

    private boolean handleSpans(int[] arr_v, int[][] arr2_v) {
        View[] arr_view = this.getViews(this.mContainer);
        for(int v = 0; v < arr2_v.length; ++v) {
            int v1 = this.getRowByIndex(arr2_v[v][0]);
            int v2 = this.getColByIndex(arr2_v[v][0]);
            int[] arr_v1 = arr2_v[v];
            if(!this.invalidatePositions(v1, v2, arr_v1[1], arr_v1[2])) {
                return false;
            }
            View view0 = arr_view[v];
            int[] arr_v2 = arr2_v[v];
            this.connectView(view0, v1, v2, arr_v2[1], arr_v2[2]);
            this.mSpanIds.add(((int)arr_v[v]));
        }
        return true;
    }

    @Override  // androidx.constraintlayout.widget.VirtualLayout
    protected void init(AttributeSet attributeSet0) {
        super.init(attributeSet0);
        this.mUseViewMeasure = true;
        if(attributeSet0 != null) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.Grid);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.Grid_grid_rows) {
                    this.mRowsSet = typedArray0.getInteger(v2, 0);
                }
                else if(v2 == styleable.Grid_grid_columns) {
                    this.mColumnsSet = typedArray0.getInteger(v2, 0);
                }
                else if(v2 == styleable.Grid_grid_spans) {
                    this.mStrSpans = typedArray0.getString(v2);
                }
                else if(v2 == styleable.Grid_grid_skips) {
                    this.mStrSkips = typedArray0.getString(v2);
                }
                else if(v2 == styleable.Grid_grid_rowWeights) {
                    this.mStrRowWeights = typedArray0.getString(v2);
                }
                else if(v2 == styleable.Grid_grid_columnWeights) {
                    this.mStrColumnWeights = typedArray0.getString(v2);
                }
                else if(v2 == styleable.Grid_grid_orientation) {
                    this.mOrientation = typedArray0.getInt(v2, 0);
                }
                else if(v2 == styleable.Grid_grid_horizontalGaps) {
                    this.mHorizontalGaps = typedArray0.getDimension(v2, 0.0f);
                }
                else if(v2 == styleable.Grid_grid_verticalGaps) {
                    this.mVerticalGaps = typedArray0.getDimension(v2, 0.0f);
                }
                else if(v2 == styleable.Grid_grid_validateInputs) {
                    this.mValidateInputs = typedArray0.getBoolean(v2, false);
                }
                else if(v2 == styleable.Grid_grid_useRtl) {
                    this.mUseRtl = typedArray0.getBoolean(v2, false);
                }
            }
            this.updateActualRowsAndColumns();
            this.initVariables();
            typedArray0.recycle();
        }
    }

    private void initVariables() {
        int[] arr_v = new int[2];
        arr_v[1] = this.mColumns;
        arr_v[0] = this.mRows;
        boolean[][] arr2_z = (boolean[][])Array.newInstance(Boolean.TYPE, arr_v);
        this.mPositionMatrix = arr2_z;
        for(int v = 0; v < arr2_z.length; ++v) {
            Arrays.fill(arr2_z[v], true);
        }
    }

    private boolean invalidatePositions(int v, int v1, int v2, int v3) {
        int v4 = v;
        while(v4 < v + v2) {
            int v5 = v1;
            while(v5 < v1 + v3) {
                boolean[][] arr2_z = this.mPositionMatrix;
                if(v4 < arr2_z.length && v5 < arr2_z[0].length) {
                    boolean[] arr_z = arr2_z[v4];
                    if(!arr_z[v5]) {
                        return false;
                    }
                    arr_z[v5] = false;
                    ++v5;
                    continue;
                }
                return false;
            }
            ++v4;
        }
        return true;
    }

    private boolean isSpansValid(CharSequence charSequence0) [...] // Inlined contents

    private boolean isWeightsValid(String s) [...] // Inlined contents

    private View makeNewView() {
        View view0 = new View(this.getContext());
        view0.setId(View.generateViewId());
        view0.setVisibility(4);
        LayoutParams constraintLayout$LayoutParams0 = new LayoutParams(0, 0);
        this.mContainer.addView(view0, constraintLayout$LayoutParams0);
        return view0;
    }

    @Override  // androidx.constraintlayout.widget.VirtualLayout
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mContainer = (ConstraintLayout)this.getParent();
        this.generateGrid(false);
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    public void onDraw(Canvas canvas0) {
        super.onDraw(canvas0);
        if(this.isInEditMode()) {
            Paint paint0 = new Paint();
            paint0.setColor(0xFFFF0000);
            paint0.setStyle(Paint.Style.STROKE);
            int v = this.getTop();
            int v1 = this.getLeft();
            int v2 = this.getBottom();
            int v3 = this.getRight();
            View[] arr_view = this.mBoxViews;
            for(int v4 = 0; v4 < arr_view.length; ++v4) {
                View view0 = arr_view[v4];
                int v5 = view0.getLeft();
                int v6 = view0.getTop();
                int v7 = view0.getRight();
                int v8 = view0.getBottom();
                canvas0.drawRect(((float)(v5 - v1)), 0.0f, ((float)(v7 - v1)), ((float)(v2 - v)), paint0);
                canvas0.drawRect(0.0f, ((float)(v6 - v)), ((float)(v3 - v1)), ((float)(v8 - v)), paint0);
            }
        }
    }

    private LayoutParams params(View view0) {
        return (LayoutParams)view0.getLayoutParams();
    }

    private int[][] parseSpans(String s) {
        String[] arr_s = s.split(",");
        int[][] arr2_v = new int[arr_s.length][3];
        for(int v = 0; v < arr_s.length; ++v) {
            String[] arr_s1 = arr_s[v].trim().split(":");
            String[] arr_s2 = arr_s1[1].split("x");
            int[] arr_v = arr2_v[v];
            arr_v[0] = Integer.parseInt(arr_s1[0]);
            int[] arr_v1 = arr2_v[v];
            arr_v1[1] = Integer.parseInt(arr_s2[0]);
            int[] arr_v2 = arr2_v[v];
            arr_v2[2] = Integer.parseInt(arr_s2[1]);
        }
        return arr2_v;
    }

    private float[] parseWeights(int v, String s) {
        float[] arr_f = null;
        if(s != null && !s.trim().isEmpty()) {
            String[] arr_s = s.split(",");
            if(arr_s.length != v) {
                return null;
            }
            arr_f = new float[v];
            for(int v1 = 0; v1 < v; ++v1) {
                arr_f[v1] = Float.parseFloat(arr_s[v1].trim());
            }
        }
        return arr_f;
    }

    private void setBoxViewHorizontalChains() {
        int v3;
        int v = this.getId();
        int v1 = Math.max(this.mRows, this.mColumns);
        float[] arr_f = this.parseWeights(this.mColumns, this.mStrColumnWeights);
        LayoutParams constraintLayout$LayoutParams0 = this.params(this.mBoxViews[0]);
        if(this.mColumns == 1) {
            this.clearHParams(this.mBoxViews[0]);
            constraintLayout$LayoutParams0.leftToLeft = v;
            constraintLayout$LayoutParams0.rightToRight = v;
            this.mBoxViews[0].setLayoutParams(constraintLayout$LayoutParams0);
            return;
        }
        for(int v2 = 0; true; ++v2) {
            v3 = this.mColumns;
            if(v2 >= v3) {
                break;
            }
            LayoutParams constraintLayout$LayoutParams1 = this.params(this.mBoxViews[v2]);
            this.clearHParams(this.mBoxViews[v2]);
            if(arr_f != null) {
                constraintLayout$LayoutParams1.horizontalWeight = arr_f[v2];
            }
            if(v2 > 0) {
                constraintLayout$LayoutParams1.leftToRight = this.mBoxViewIds[v2 - 1];
            }
            else {
                constraintLayout$LayoutParams1.leftToLeft = v;
            }
            if(v2 < this.mColumns - 1) {
                constraintLayout$LayoutParams1.rightToLeft = this.mBoxViewIds[v2 + 1];
            }
            else {
                constraintLayout$LayoutParams1.rightToRight = v;
            }
            if(v2 > 0) {
                constraintLayout$LayoutParams1.leftMargin = (int)this.mHorizontalGaps;
            }
            this.mBoxViews[v2].setLayoutParams(constraintLayout$LayoutParams1);
        }
        while(v3 < v1) {
            LayoutParams constraintLayout$LayoutParams2 = this.params(this.mBoxViews[v3]);
            this.clearHParams(this.mBoxViews[v3]);
            constraintLayout$LayoutParams2.leftToLeft = v;
            constraintLayout$LayoutParams2.rightToRight = v;
            this.mBoxViews[v3].setLayoutParams(constraintLayout$LayoutParams2);
            ++v3;
        }
    }

    private void setBoxViewVerticalChains() {
        int v3;
        int v = this.getId();
        int v1 = Math.max(this.mRows, this.mColumns);
        float[] arr_f = this.parseWeights(this.mRows, this.mStrRowWeights);
        if(this.mRows == 1) {
            LayoutParams constraintLayout$LayoutParams0 = this.params(this.mBoxViews[0]);
            this.clearVParams(this.mBoxViews[0]);
            constraintLayout$LayoutParams0.topToTop = v;
            constraintLayout$LayoutParams0.bottomToBottom = v;
            this.mBoxViews[0].setLayoutParams(constraintLayout$LayoutParams0);
            return;
        }
        for(int v2 = 0; true; ++v2) {
            v3 = this.mRows;
            if(v2 >= v3) {
                break;
            }
            LayoutParams constraintLayout$LayoutParams1 = this.params(this.mBoxViews[v2]);
            this.clearVParams(this.mBoxViews[v2]);
            if(arr_f != null) {
                constraintLayout$LayoutParams1.verticalWeight = arr_f[v2];
            }
            if(v2 > 0) {
                constraintLayout$LayoutParams1.topToBottom = this.mBoxViewIds[v2 - 1];
            }
            else {
                constraintLayout$LayoutParams1.topToTop = v;
            }
            if(v2 < this.mRows - 1) {
                constraintLayout$LayoutParams1.bottomToTop = this.mBoxViewIds[v2 + 1];
            }
            else {
                constraintLayout$LayoutParams1.bottomToBottom = v;
            }
            if(v2 > 0) {
                constraintLayout$LayoutParams1.topMargin = (int)this.mHorizontalGaps;
            }
            this.mBoxViews[v2].setLayoutParams(constraintLayout$LayoutParams1);
        }
        while(v3 < v1) {
            LayoutParams constraintLayout$LayoutParams2 = this.params(this.mBoxViews[v3]);
            this.clearVParams(this.mBoxViews[v3]);
            constraintLayout$LayoutParams2.topToTop = v;
            constraintLayout$LayoutParams2.bottomToBottom = v;
            this.mBoxViews[v3].setLayoutParams(constraintLayout$LayoutParams2);
            ++v3;
        }
    }

    public void setColumnWeights(String s) {
        if(this.mStrColumnWeights != null && this.mStrColumnWeights.equals(s)) {
            return;
        }
        this.mStrColumnWeights = s;
        this.generateGrid(true);
        this.invalidate();
    }

    public void setColumns(int v) {
        if(v > 50 || this.mColumnsSet == v) {
            return;
        }
        this.mColumnsSet = v;
        this.updateActualRowsAndColumns();
        this.initVariables();
        this.generateGrid(false);
        this.invalidate();
    }

    public void setHorizontalGaps(float f) {
        if(f < 0.0f || this.mHorizontalGaps == f) {
            return;
        }
        this.mHorizontalGaps = f;
        this.generateGrid(true);
        this.invalidate();
    }

    public void setOrientation(int v) {
        if(v != 0 && v != 1 || this.mOrientation == v) {
            return;
        }
        this.mOrientation = v;
        this.generateGrid(true);
        this.invalidate();
    }

    public void setRowWeights(String s) {
        if(this.mStrRowWeights != null && this.mStrRowWeights.equals(s)) {
            return;
        }
        this.mStrRowWeights = s;
        this.generateGrid(true);
        this.invalidate();
    }

    public void setRows(int v) {
        if(v > 50 || this.mRowsSet == v) {
            return;
        }
        this.mRowsSet = v;
        this.updateActualRowsAndColumns();
        this.initVariables();
        this.generateGrid(false);
        this.invalidate();
    }

    public void setSkips(String s) {
        if(this.mStrSkips != null && this.mStrSkips.equals(s)) {
            return;
        }
        this.mStrSkips = s;
        this.generateGrid(true);
        this.invalidate();
    }

    public void setSpans(CharSequence charSequence0) {
        if(this.mStrSpans != null && this.mStrSpans.contentEquals(charSequence0)) {
            return;
        }
        this.mStrSpans = charSequence0.toString();
        this.generateGrid(true);
        this.invalidate();
    }

    public void setVerticalGaps(float f) {
        if(f < 0.0f || this.mVerticalGaps == f) {
            return;
        }
        this.mVerticalGaps = f;
        this.generateGrid(true);
        this.invalidate();
    }

    private void updateActualRowsAndColumns() {
        int v = this.mRowsSet;
        if(v != 0) {
            int v1 = this.mColumnsSet;
            if(v1 != 0) {
                this.mRows = v;
                this.mColumns = v1;
                return;
            }
        }
        int v2 = this.mColumnsSet;
        if(v2 > 0) {
            this.mColumns = v2;
            this.mRows = (this.mCount + this.mColumns - 1) / this.mColumnsSet;
            return;
        }
        if(v > 0) {
            this.mRows = v;
            this.mColumns = (this.mCount + this.mRowsSet - 1) / this.mRowsSet;
            return;
        }
        this.mRows = (int)(Math.sqrt(this.mCount) + 1.5);
        this.mColumns = (this.mCount + this.mRows - 1) / this.mRows;
    }
}

