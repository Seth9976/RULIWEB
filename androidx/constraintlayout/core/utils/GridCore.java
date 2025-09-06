package androidx.constraintlayout.core.utils;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GridCore extends VirtualLayout {
    private static final int DEFAULT_SIZE = 3;
    public static final int HORIZONTAL = 0;
    private static final int MAX_COLUMNS = 50;
    private static final int MAX_ROWS = 50;
    public static final int SPANS_RESPECT_WIDGET_ORDER = 2;
    public static final int SUB_GRID_BY_COL_ROW = 1;
    public static final int VERTICAL = 1;
    private ConstraintWidget[] mBoxWidgets;
    private String mColumnWeights;
    private int mColumns;
    private int mColumnsSet;
    private int[][] mConstraintMatrix;
    ConstraintWidgetContainer mContainer;
    private boolean mExtraSpaceHandled;
    private int mFlags;
    private float mHorizontalGaps;
    private int mNextAvailableIndex;
    private int mOrientation;
    private boolean[][] mPositionMatrix;
    private String mRowWeights;
    private int mRows;
    private int mRowsSet;
    private String mSkips;
    Set mSpanIds;
    private int mSpanIndex;
    private int[][] mSpanMatrix;
    private String mSpans;
    private float mVerticalGaps;

    public GridCore() {
        this.mExtraSpaceHandled = false;
        this.mNextAvailableIndex = 0;
        this.mSpanIds = new HashSet();
        this.mSpanIndex = 0;
        this.updateActualRowsAndColumns();
        this.initMatrices();
    }

    public GridCore(int v, int v1) {
        this.mExtraSpaceHandled = false;
        this.mNextAvailableIndex = 0;
        this.mSpanIds = new HashSet();
        this.mSpanIndex = 0;
        this.mRowsSet = v;
        this.mColumnsSet = v1;
        if(v > 50) {
            this.mRowsSet = 3;
        }
        if(v1 > 50) {
            this.mColumnsSet = 3;
        }
        this.updateActualRowsAndColumns();
        this.initMatrices();
    }

    private void addConstraints() {
        this.setBoxWidgetVerticalChains();
        this.setBoxWidgetHorizontalChains();
        this.arrangeWidgets();
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem0, boolean z) {
        super.addToSolver(linearSystem0, z);
        this.addConstraints();
    }

    private void arrangeWidgets() {
        for(int v = 0; v < this.mWidgetsCount; ++v) {
            if(!this.mSpanIds.contains(this.mWidgets[v].stringId)) {
                int v1 = this.getNextPosition();
                int v2 = this.getRowByIndex(v1);
                int v3 = this.getColByIndex(v1);
                if(v1 == -1) {
                    break;
                }
                if(this.isSpansRespectWidgetOrder()) {
                    int[][] arr2_v = this.mSpanMatrix;
                    if(arr2_v == null) {
                        this.connectWidget(this.mWidgets[v], v2, v3, 1, 1);
                    }
                    else {
                        int v4 = this.mSpanIndex;
                        if(v4 < arr2_v.length) {
                            int[] arr_v = arr2_v[v4];
                            if(arr_v[0] == v1) {
                                this.mPositionMatrix[v2][v3] = true;
                                if(this.invalidatePositions(v2, v3, arr_v[1], arr_v[2])) {
                                    ConstraintWidget constraintWidget0 = this.mWidgets[v];
                                    int[] arr_v1 = this.mSpanMatrix[this.mSpanIndex];
                                    this.connectWidget(constraintWidget0, v2, v3, arr_v1[1], arr_v1[2]);
                                    ++this.mSpanIndex;
                                }
                            }
                            else {
                                this.connectWidget(this.mWidgets[v], v2, v3, 1, 1);
                            }
                        }
                        else {
                            this.connectWidget(this.mWidgets[v], v2, v3, 1, 1);
                        }
                    }
                }
                else {
                    this.connectWidget(this.mWidgets[v], v2, v3, 1, 1);
                }
            }
        }
    }

    private void clearHorizontalAttributes(ConstraintWidget constraintWidget0) {
        constraintWidget0.setHorizontalWeight(-1.0f);
        constraintWidget0.mLeft.reset();
        constraintWidget0.mRight.reset();
    }

    private void clearVerticalAttributes(ConstraintWidget constraintWidget0) {
        constraintWidget0.setVerticalWeight(-1.0f);
        constraintWidget0.mTop.reset();
        constraintWidget0.mBottom.reset();
        constraintWidget0.mBaseline.reset();
    }

    private void connectWidget(ConstraintWidget constraintWidget0, int v, int v1, int v2, int v3) {
        constraintWidget0.mLeft.connect(this.mBoxWidgets[v1].mLeft, 0);
        constraintWidget0.mTop.connect(this.mBoxWidgets[v].mTop, 0);
        constraintWidget0.mRight.connect(this.mBoxWidgets[v1 + v3 - 1].mRight, 0);
        constraintWidget0.mBottom.connect(this.mBoxWidgets[v + v2 - 1].mBottom, 0);
    }

    private void createBoxes() {
        int v = Math.max(this.mRows, this.mColumns);
        ConstraintWidget[] arr_constraintWidget = this.mBoxWidgets;
        int v1 = 0;
        if(arr_constraintWidget == null) {
            this.mBoxWidgets = new ConstraintWidget[v];
            while(true) {
                ConstraintWidget[] arr_constraintWidget1 = this.mBoxWidgets;
                if(v1 >= arr_constraintWidget1.length) {
                    break;
                }
                arr_constraintWidget1[v1] = this.makeNewWidget();
                ++v1;
            }
        }
        else if(v != arr_constraintWidget.length) {
            ConstraintWidget[] arr_constraintWidget2 = new ConstraintWidget[v];
            while(v1 < v) {
                ConstraintWidget[] arr_constraintWidget3 = this.mBoxWidgets;
                arr_constraintWidget2[v1] = v1 < arr_constraintWidget3.length ? arr_constraintWidget3[v1] : this.makeNewWidget();
                ++v1;
            }
            while(true) {
                ConstraintWidget[] arr_constraintWidget4 = this.mBoxWidgets;
                if(v >= arr_constraintWidget4.length) {
                    break;
                }
                this.mContainer.remove(arr_constraintWidget4[v]);
                ++v;
            }
            this.mBoxWidgets = arr_constraintWidget2;
        }
    }

    private void fillConstraintMatrix(boolean z) {
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
            for(int v2 = 0; v2 < this.mConstraintMatrix.length; ++v2) {
                for(int v3 = 0; true; ++v3) {
                    int[][] arr2_v = this.mConstraintMatrix;
                    if(v3 >= arr2_v[0].length) {
                        break;
                    }
                    arr2_v[v2][v3] = -1;
                }
            }
        }
        this.mNextAvailableIndex = 0;
        if(this.mSkips != null && !this.mSkips.trim().isEmpty()) {
            int[][] arr2_v1 = this.parseSpans(this.mSkips, false);
            if(arr2_v1 != null) {
                this.handleSkips(arr2_v1);
            }
        }
        if(this.mSpans != null && !this.mSpans.trim().isEmpty()) {
            int[][] arr2_v2 = this.parseSpans(this.mSpans, true);
            if(arr2_v2 != null) {
                this.handleSpans(arr2_v2);
            }
        }
    }

    private int getColByIndex(int v) {
        return this.mOrientation == 1 ? v / this.mRows : v % this.mColumns;
    }

    public String getColumnWeights() {
        return this.mColumnWeights;
    }

    public ConstraintWidgetContainer getContainer() {
        return this.mContainer;
    }

    public int getFlags() {
        return this.mFlags;
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
        return this.mRowWeights;
    }

    public float getVerticalGaps() {
        return this.mVerticalGaps;
    }

    private void handleSkips(int[][] arr2_v) {
        for(int v = 0; v < arr2_v.length; ++v) {
            int[] arr_v = arr2_v[v];
            if(!this.invalidatePositions(this.getRowByIndex(arr_v[0]), this.getColByIndex(arr_v[0]), arr_v[1], arr_v[2])) {
                break;
            }
        }
    }

    private void handleSpans(int[][] arr2_v) {
        if(!this.isSpansRespectWidgetOrder()) {
            for(int v = 0; v < arr2_v.length; ++v) {
                int v1 = this.getRowByIndex(arr2_v[v][0]);
                int v2 = this.getColByIndex(arr2_v[v][0]);
                int[] arr_v = arr2_v[v];
                if(!this.invalidatePositions(v1, v2, arr_v[1], arr_v[2])) {
                    break;
                }
                ConstraintWidget constraintWidget0 = this.mWidgets[v];
                int[] arr_v1 = arr2_v[v];
                this.connectWidget(constraintWidget0, v1, v2, arr_v1[1], arr_v1[2]);
                this.mSpanIds.add(this.mWidgets[v].stringId);
            }
        }
    }

    private void initMatrices() {
        boolean z = false;
        if(this.mConstraintMatrix == null || this.mConstraintMatrix.length != this.mWidgetsCount || (this.mPositionMatrix == null || this.mPositionMatrix.length != this.mRows || this.mPositionMatrix[0].length != this.mColumns)) {
            this.initVariables();
        }
        else {
            z = true;
        }
        this.fillConstraintMatrix(z);
    }

    private void initVariables() {
        int[] arr_v = new int[2];
        arr_v[1] = this.mColumns;
        arr_v[0] = this.mRows;
        boolean[][] arr2_z = (boolean[][])Array.newInstance(Boolean.TYPE, arr_v);
        this.mPositionMatrix = arr2_z;
        for(int v1 = 0; v1 < arr2_z.length; ++v1) {
            Arrays.fill(arr2_z[v1], true);
        }
        if(this.mWidgetsCount > 0) {
            int[][] arr2_v = new int[this.mWidgetsCount][4];
            this.mConstraintMatrix = arr2_v;
            for(int v = 0; v < arr2_v.length; ++v) {
                Arrays.fill(arr2_v[v], -1);
            }
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

    private boolean isSpansRespectWidgetOrder() {
        return (this.mFlags & 2) > 0;
    }

    private boolean isSubGridByColRow() {
        return (this.mFlags & 1) > 0;
    }

    // 检测为 Lambda 实现
    static int lambda$parseSpans$0(String s, String s1) [...]

    private ConstraintWidget makeNewWidget() {
        ConstraintWidget constraintWidget0 = new ConstraintWidget();
        constraintWidget0.mListDimensionBehaviors[0] = DimensionBehaviour.MATCH_CONSTRAINT;
        constraintWidget0.mListDimensionBehaviors[1] = DimensionBehaviour.MATCH_CONSTRAINT;
        constraintWidget0.stringId = "334685199";
        return constraintWidget0;
    }

    @Override  // androidx.constraintlayout.core.widgets.VirtualLayout
    public void measure(int v, int v1, int v2, int v3) {
        super.measure(v, v1, v2, v3);
        this.mContainer = (ConstraintWidgetContainer)this.getParent();
        this.setupGrid(false);
        this.mContainer.add(this.mBoxWidgets);
    }

    private int[][] parseSpans(String s, boolean z) {
        try {
            String[] arr_s = s.split(",");
            Arrays.sort(arr_s, (String s, String s1) -> Integer.parseInt(s.split(":")[0]) - Integer.parseInt(s1.split(":")[0]));
            int[][] arr2_v = new int[arr_s.length][3];
            if(this.mRows != 1 && this.mColumns != 1) {
                for(int v = 0; v < arr_s.length; ++v) {
                    String[] arr_s1 = arr_s[v].trim().split(":");
                    String[] arr_s2 = arr_s1[1].split("x");
                    int[] arr_v = arr2_v[v];
                    arr_v[0] = Integer.parseInt(arr_s1[0]);
                    if(this.isSubGridByColRow()) {
                        int[] arr_v1 = arr2_v[v];
                        arr_v1[1] = Integer.parseInt(arr_s2[1]);
                        int[] arr_v2 = arr2_v[v];
                        arr_v2[2] = Integer.parseInt(arr_s2[0]);
                    }
                    else {
                        int[] arr_v3 = arr2_v[v];
                        arr_v3[1] = Integer.parseInt(arr_s2[0]);
                        int[] arr_v4 = arr2_v[v];
                        arr_v4[2] = Integer.parseInt(arr_s2[1]);
                    }
                }
                return arr2_v;
            }
            int v2 = 0;
            int v3 = 0;
            for(int v1 = 0; v1 < arr_s.length; ++v1) {
                String[] arr_s3 = arr_s[v1].trim().split(":");
                int[] arr_v5 = arr2_v[v1];
                arr_v5[0] = Integer.parseInt(arr_s3[0]);
                int[] arr_v6 = arr2_v[v1];
                arr_v6[1] = 1;
                arr_v6[2] = 1;
                if(this.mColumns == 1) {
                    arr_v6[1] = Integer.parseInt(arr_s3[1]);
                    v2 += arr2_v[v1][1];
                    if(z) {
                        --v2;
                    }
                }
                if(this.mRows == 1) {
                    int[] arr_v7 = arr2_v[v1];
                    arr_v7[2] = Integer.parseInt(arr_s3[1]);
                    v3 += arr2_v[v1][2];
                    if(z) {
                        --v3;
                    }
                }
            }
            if(v2 != 0 && !this.mExtraSpaceHandled) {
                this.setRows(this.mRows + v2);
            }
            if(v3 != 0 && !this.mExtraSpaceHandled) {
                this.setColumns(this.mColumns + v3);
            }
            this.mExtraSpaceHandled = true;
            return arr2_v;
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    private float[] parseWeights(int v, String s) {
        if(s != null && !s.trim().isEmpty()) {
            String[] arr_s = s.split(",");
            float[] arr_f = new float[v];
            for(int v1 = 0; v1 < v; ++v1) {
                if(v1 < arr_s.length) {
                    try {
                        arr_f[v1] = Float.parseFloat(arr_s[v1]);
                    }
                    catch(Exception exception0) {
                        System.err.println("Error parsing `" + arr_s[v1] + "`: " + exception0.getMessage());
                        arr_f[v1] = 1.0f;
                    }
                }
                else {
                    arr_f[v1] = 1.0f;
                }
            }
            return arr_f;
        }
        return null;
    }

    private void setBoxWidgetHorizontalChains() {
        int v2;
        int v = Math.max(this.mRows, this.mColumns);
        ConstraintWidget constraintWidget0 = this.mBoxWidgets[0];
        float[] arr_f = this.parseWeights(this.mColumns, this.mColumnWeights);
        if(this.mColumns == 1) {
            this.clearHorizontalAttributes(constraintWidget0);
            constraintWidget0.mLeft.connect(this.mLeft, 0);
            constraintWidget0.mRight.connect(this.mRight, 0);
            return;
        }
        for(int v1 = 0; true; ++v1) {
            v2 = this.mColumns;
            if(v1 >= v2) {
                break;
            }
            ConstraintWidget constraintWidget1 = this.mBoxWidgets[v1];
            this.clearHorizontalAttributes(constraintWidget1);
            if(arr_f != null) {
                constraintWidget1.setHorizontalWeight(arr_f[v1]);
            }
            if(v1 > 0) {
                constraintWidget1.mLeft.connect(this.mBoxWidgets[v1 - 1].mRight, 0);
            }
            else {
                constraintWidget1.mLeft.connect(this.mLeft, 0);
            }
            if(v1 < this.mColumns - 1) {
                constraintWidget1.mRight.connect(this.mBoxWidgets[v1 + 1].mLeft, 0);
            }
            else {
                constraintWidget1.mRight.connect(this.mRight, 0);
            }
            if(v1 > 0) {
                constraintWidget1.mLeft.mMargin = (int)this.mHorizontalGaps;
            }
        }
        while(v2 < v) {
            ConstraintWidget constraintWidget2 = this.mBoxWidgets[v2];
            this.clearHorizontalAttributes(constraintWidget2);
            constraintWidget2.mLeft.connect(this.mLeft, 0);
            constraintWidget2.mRight.connect(this.mRight, 0);
            ++v2;
        }
    }

    private void setBoxWidgetVerticalChains() {
        int v2;
        int v = Math.max(this.mRows, this.mColumns);
        ConstraintWidget constraintWidget0 = this.mBoxWidgets[0];
        float[] arr_f = this.parseWeights(this.mRows, this.mRowWeights);
        if(this.mRows == 1) {
            this.clearVerticalAttributes(constraintWidget0);
            constraintWidget0.mTop.connect(this.mTop, 0);
            constraintWidget0.mBottom.connect(this.mBottom, 0);
            return;
        }
        for(int v1 = 0; true; ++v1) {
            v2 = this.mRows;
            if(v1 >= v2) {
                break;
            }
            ConstraintWidget constraintWidget1 = this.mBoxWidgets[v1];
            this.clearVerticalAttributes(constraintWidget1);
            if(arr_f != null) {
                constraintWidget1.setVerticalWeight(arr_f[v1]);
            }
            if(v1 > 0) {
                constraintWidget1.mTop.connect(this.mBoxWidgets[v1 - 1].mBottom, 0);
            }
            else {
                constraintWidget1.mTop.connect(this.mTop, 0);
            }
            if(v1 < this.mRows - 1) {
                constraintWidget1.mBottom.connect(this.mBoxWidgets[v1 + 1].mTop, 0);
            }
            else {
                constraintWidget1.mBottom.connect(this.mBottom, 0);
            }
            if(v1 > 0) {
                constraintWidget1.mTop.mMargin = (int)this.mVerticalGaps;
            }
        }
        while(v2 < v) {
            ConstraintWidget constraintWidget2 = this.mBoxWidgets[v2];
            this.clearVerticalAttributes(constraintWidget2);
            constraintWidget2.mTop.connect(this.mTop, 0);
            constraintWidget2.mBottom.connect(this.mBottom, 0);
            ++v2;
        }
    }

    public void setColumnWeights(String s) {
        if(this.mColumnWeights != null && this.mColumnWeights.equals(s)) {
            return;
        }
        this.mColumnWeights = s;
    }

    public void setColumns(int v) {
        if(v > 50 || this.mColumnsSet == v) {
            return;
        }
        this.mColumnsSet = v;
        this.updateActualRowsAndColumns();
        this.initVariables();
    }

    public void setContainer(ConstraintWidgetContainer constraintWidgetContainer0) {
        this.mContainer = constraintWidgetContainer0;
    }

    public void setFlags(int v) {
        this.mFlags = v;
    }

    public void setHorizontalGaps(float f) {
        if(f < 0.0f || this.mHorizontalGaps == f) {
            return;
        }
        this.mHorizontalGaps = f;
    }

    public void setOrientation(int v) {
        if(v != 0 && v != 1 || this.mOrientation == v) {
            return;
        }
        this.mOrientation = v;
    }

    public void setRowWeights(String s) {
        if(this.mRowWeights != null && this.mRowWeights.equals(s)) {
            return;
        }
        this.mRowWeights = s;
    }

    public void setRows(int v) {
        if(v > 50 || this.mRowsSet == v) {
            return;
        }
        this.mRowsSet = v;
        this.updateActualRowsAndColumns();
        this.initVariables();
    }

    public void setSkips(String s) {
        if(this.mSkips != null && this.mSkips.equals(s)) {
            return;
        }
        this.mExtraSpaceHandled = false;
        this.mSkips = s;
    }

    public void setSpans(CharSequence charSequence0) {
        if(this.mSpans != null && this.mSpans.equals(charSequence0.toString())) {
            return;
        }
        this.mExtraSpaceHandled = false;
        this.mSpans = charSequence0.toString();
    }

    public void setVerticalGaps(float f) {
        if(f < 0.0f || this.mVerticalGaps == f) {
            return;
        }
        this.mVerticalGaps = f;
    }

    private void setupGrid(boolean z) {
        if(this.mRows >= 1 && this.mColumns >= 1) {
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
            if(this.mSkips != null && !this.mSkips.trim().isEmpty()) {
                int[][] arr2_v = this.parseSpans(this.mSkips, false);
                if(arr2_v != null) {
                    this.handleSkips(arr2_v);
                }
            }
            if(this.mSpans != null && !this.mSpans.trim().isEmpty()) {
                this.mSpanMatrix = this.parseSpans(this.mSpans, true);
            }
            this.createBoxes();
            int[][] arr2_v1 = this.mSpanMatrix;
            if(arr2_v1 != null) {
                this.handleSpans(arr2_v1);
            }
        }
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
            this.mRows = (this.mWidgetsCount + this.mColumns - 1) / this.mColumnsSet;
            return;
        }
        if(v > 0) {
            this.mRows = v;
            this.mColumns = (this.mWidgetsCount + this.mRowsSet - 1) / this.mRowsSet;
            return;
        }
        this.mRows = (int)(Math.sqrt(this.mWidgetsCount) + 1.5);
        this.mColumns = (this.mWidgetsCount + this.mRows - 1) / this.mRows;
    }
}

