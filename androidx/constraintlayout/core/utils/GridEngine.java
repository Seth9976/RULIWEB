package androidx.constraintlayout.core.utils;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GridEngine {
    private static final int DEFAULT_SIZE = 3;
    public static final int HORIZONTAL = 0;
    private static final int MAX_COLUMNS = 50;
    private static final int MAX_ROWS = 50;
    public static final int VERTICAL = 1;
    private int mColumns;
    private int mColumnsSet;
    private int[][] mConstraintMatrix;
    private int mNextAvailableIndex;
    private int mNumWidgets;
    private int mOrientation;
    private boolean[][] mPositionMatrix;
    private int mRows;
    private int mRowsSet;
    private String mStrSkips;
    private String mStrSpans;

    public GridEngine() {
        this.mNextAvailableIndex = 0;
    }

    public GridEngine(int v, int v1) {
        this.mNextAvailableIndex = 0;
        this.mRowsSet = v;
        this.mColumnsSet = v1;
        if(v > 50) {
            this.mRowsSet = 3;
        }
        if(v1 > 50) {
            this.mColumnsSet = 3;
        }
        this.updateActualRowsAndColumns();
        this.initVariables();
    }

    public GridEngine(int v, int v1, int v2) {
        this.mNextAvailableIndex = 0;
        this.mRowsSet = v;
        this.mColumnsSet = v1;
        this.mNumWidgets = v2;
        if(v > 50) {
            this.mRowsSet = 3;
        }
        if(v1 > 50) {
            this.mColumnsSet = 3;
        }
        this.updateActualRowsAndColumns();
        int v3 = this.mRows;
        int v4 = this.mColumns;
        if(v2 > v3 * v4 || v2 < 1) {
            this.mNumWidgets = v3 * v4;
        }
        this.initVariables();
        this.fillConstraintMatrix(false);
    }

    private void addAllConstraintPositions() {
        for(int v = 0; v < this.mNumWidgets; ++v) {
            if(this.leftOfWidget(v) == -1) {
                int v1 = this.getNextPosition();
                int v2 = this.getRowByIndex(v1);
                int v3 = this.getColByIndex(v1);
                if(v1 == -1) {
                    break;
                }
                this.addConstraintPosition(v, v2, v3, 1, 1);
            }
        }
    }

    private void addConstraintPosition(int v, int v1, int v2, int v3, int v4) {
        int[] arr_v = this.mConstraintMatrix[v];
        arr_v[0] = v2;
        arr_v[1] = v1;
        arr_v[2] = v2 + v4 - 1;
        arr_v[3] = v1 + v3 - 1;
    }

    public int bottomOfWidget(int v) {
        return this.mConstraintMatrix == null || v >= this.mConstraintMatrix.length ? 0 : this.mConstraintMatrix[v][3];
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
        if(this.mStrSkips != null && !this.mStrSkips.trim().isEmpty()) {
            int[][] arr2_v1 = this.parseSpans(this.mStrSkips);
            if(arr2_v1 != null) {
                this.handleSkips(arr2_v1);
            }
        }
        if(this.mStrSpans != null && !this.mStrSpans.trim().isEmpty()) {
            int[][] arr2_v2 = this.parseSpans(this.mStrSpans);
            if(arr2_v2 != null) {
                this.handleSpans(arr2_v2);
            }
        }
        this.addAllConstraintPositions();
    }

    private int getColByIndex(int v) {
        return this.mOrientation == 1 ? v / this.mRows : v % this.mColumns;
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

    private int getRowByIndex(int v) {
        return this.mOrientation == 1 ? v % this.mRows : v / this.mColumns;
    }

    private void handleSkips(int[][] arr2_v) {
        for(int v = 0; v < arr2_v.length; ++v) {
            int v1 = this.getRowByIndex(arr2_v[v][0]);
            int v2 = this.getColByIndex(arr2_v[v][0]);
            int[] arr_v = arr2_v[v];
            if(!this.invalidatePositions(v1, v2, arr_v[1], arr_v[2])) {
                break;
            }
        }
    }

    private void handleSpans(int[][] arr2_v) {
        for(int v = 0; v < arr2_v.length; ++v) {
            int v1 = this.getRowByIndex(arr2_v[v][0]);
            int v2 = this.getColByIndex(arr2_v[v][0]);
            int[] arr_v = arr2_v[v];
            if(!this.invalidatePositions(v1, v2, arr_v[1], arr_v[2])) {
                break;
            }
            int[] arr_v1 = arr2_v[v];
            this.addConstraintPosition(v, v1, v2, arr_v1[1], arr_v1[2]);
        }
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
        int v2 = this.mNumWidgets;
        if(v2 > 0) {
            int[][] arr2_v = new int[v2][4];
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

    private boolean isSpansValid(CharSequence charSequence0) {
        return charSequence0 != null;
    }

    public int leftOfWidget(int v) {
        return this.mConstraintMatrix == null || v >= this.mConstraintMatrix.length ? 0 : this.mConstraintMatrix[v][0];
    }

    private int[][] parseSpans(String s) {
        if(!this.isSpansValid(s)) {
            return null;
        }
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

    public int rightOfWidget(int v) {
        return this.mConstraintMatrix == null || v >= this.mConstraintMatrix.length ? 0 : this.mConstraintMatrix[v][2];
    }

    public void setColumns(int v) {
        if(v > 50 || this.mColumnsSet == v) {
            return;
        }
        this.mColumnsSet = v;
        this.updateActualRowsAndColumns();
    }

    public void setNumWidgets(int v) {
        if(v > this.mRows * this.mColumns) {
            return;
        }
        this.mNumWidgets = v;
    }

    public void setOrientation(int v) {
        if(v != 0 && v != 1 || this.mOrientation == v) {
            return;
        }
        this.mOrientation = v;
    }

    public void setRows(int v) {
        if(v > 50 || this.mRowsSet == v) {
            return;
        }
        this.mRowsSet = v;
        this.updateActualRowsAndColumns();
    }

    public void setSkips(String s) {
        if(this.mStrSkips != null && this.mStrSkips.equals(s)) {
            return;
        }
        this.mStrSkips = s;
    }

    public void setSpans(CharSequence charSequence0) {
        if(this.mStrSpans != null && this.mStrSpans.equals(charSequence0.toString())) {
            return;
        }
        this.mStrSpans = charSequence0.toString();
    }

    public void setup() {
        boolean z = false;
        if(this.mConstraintMatrix == null || this.mConstraintMatrix.length != this.mNumWidgets || (this.mPositionMatrix == null || this.mPositionMatrix.length != this.mRows || this.mPositionMatrix[0].length != this.mColumns)) {
            this.initVariables();
        }
        else {
            z = true;
        }
        this.fillConstraintMatrix(z);
    }

    public int topOfWidget(int v) {
        return this.mConstraintMatrix == null || v >= this.mConstraintMatrix.length ? 0 : this.mConstraintMatrix[v][1];
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
            this.mRows = (this.mNumWidgets + v2 - 1) / v2;
            return;
        }
        if(v > 0) {
            this.mRows = v;
            this.mColumns = (this.mNumWidgets + v - 1) / v;
            return;
        }
        double f = Math.sqrt(this.mNumWidgets);
        this.mRows = (int)(f + 1.5);
        this.mColumns = (this.mNumWidgets + ((int)(f + 1.5)) - 1) / ((int)(f + 1.5));
    }
}

