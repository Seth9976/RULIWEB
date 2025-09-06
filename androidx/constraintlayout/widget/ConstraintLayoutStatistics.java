package androidx.constraintlayout.widget;

import android.util.Log;
import androidx.constraintlayout.core.Metrics;
import java.text.DecimalFormat;

public class ConstraintLayoutStatistics {
    public static final int DURATION_OF_CHILD_MEASURES = 5;
    public static final int DURATION_OF_LAYOUT = 7;
    public static final int DURATION_OF_MEASURES = 6;
    private static int MAX_WORD = 25;
    public static final int NUMBER_OF_CHILD_MEASURES = 4;
    public static final int NUMBER_OF_CHILD_VIEWS = 3;
    public static final int NUMBER_OF_EQUATIONS = 9;
    public static final int NUMBER_OF_LAYOUTS = 1;
    public static final int NUMBER_OF_ON_MEASURES = 2;
    public static final int NUMBER_OF_SIMPLE_EQUATIONS = 10;
    public static final int NUMBER_OF_VARIABLES = 8;
    private static final String WORD_PAD;
    ConstraintLayout mConstraintLayout;
    private final Metrics mMetrics;

    static {
        ConstraintLayoutStatistics.WORD_PAD = "                         ";
    }

    public ConstraintLayoutStatistics(ConstraintLayout constraintLayout0) {
        this.mMetrics = new Metrics();
        this.attach(constraintLayout0);
    }

    public ConstraintLayoutStatistics(ConstraintLayoutStatistics constraintLayoutStatistics0) {
        Metrics metrics0 = new Metrics();
        this.mMetrics = metrics0;
        metrics0.copy(constraintLayoutStatistics0.mMetrics);
    }

    public void attach(ConstraintLayout constraintLayout0) {
        constraintLayout0.fillMetrics(this.mMetrics);
        this.mConstraintLayout = constraintLayout0;
    }

    public ConstraintLayoutStatistics clone() {
        return new ConstraintLayoutStatistics(this);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    private String compare(ConstraintLayoutStatistics constraintLayoutStatistics0, int v) {
        String s = "                         " + this.geName(v);
        return "CL Perf: " + (s.substring(s.length() - ConstraintLayoutStatistics.MAX_WORD) + " = ") + (this.getValue(v) + " -> " + constraintLayoutStatistics0.getValue(v));
    }

    private String compare(DecimalFormat decimalFormat0, ConstraintLayoutStatistics constraintLayoutStatistics0, int v) {
        String s = this.fmt(decimalFormat0, ((float)this.getValue(v)) * 0.000001f, 7);
        String s1 = "                         " + this.geName(v);
        return "CL Perf: " + (s1.substring(s1.length() - 25) + " = ") + (s + " -> " + this.fmt(decimalFormat0, ((float)constraintLayoutStatistics0.getValue(v)) * 0.000001f, 7) + "ms");
    }

    public void detach() {
        ConstraintLayout constraintLayout0 = this.mConstraintLayout;
        if(constraintLayout0 != null) {
            constraintLayout0.fillMetrics(null);
        }
    }

    private String fmt(DecimalFormat decimalFormat0, float f, int v) {
        String s = new String(new char[v]).replace('\u0000', ' ') + decimalFormat0.format(((double)f));
        return s.substring(s.length() - v);
    }

    String geName(int v) {
        switch(v) {
            case 1: {
                return "NumberOfLayouts";
            }
            case 2: {
                return "MeasureCalls";
            }
            case 3: {
                return "ChildCount";
            }
            case 4: {
                return "ChildrenMeasures";
            }
            case 5: {
                return "MeasuresWidgetsDuration ";
            }
            case 6: {
                return "MeasureDuration";
            }
            case 7: {
                return "MeasuresLayoutDuration";
            }
            case 8: {
                return "SolverVariables";
            }
            case 9: {
                return "SolverEquations";
            }
            case 10: {
                return "SimpleEquations";
            }
            default: {
                return "";
            }
        }
    }

    public long getValue(int v) {
        switch(v) {
            case 1: {
                return (long)this.mMetrics.mNumberOfLayouts;
            }
            case 2: {
                return this.mMetrics.mMeasureCalls;
            }
            case 3: {
                return this.mMetrics.mChildCount;
            }
            case 4: {
                return (long)this.mMetrics.mNumberOfMeasures;
            }
            case 5: {
                return this.mMetrics.measuresWidgetsDuration;
            }
            case 6: {
                return this.mMetrics.mMeasureDuration;
            }
            case 7: {
                return this.mMetrics.measuresLayoutDuration;
            }
            case 8: {
                return this.mMetrics.mVariables;
            }
            case 9: {
                return this.mMetrics.mEquations;
            }
            case 10: {
                return this.mMetrics.mSimpleEquations;
            }
            default: {
                return 0L;
            }
        }
    }

    private String log(int v) {
        String s = Long.toString(this.getValue(v));
        String s1 = "                         " + this.geName(v);
        return "CL Perf: " + (s1.substring(s1.length() - 25) + " = ") + s;
    }

    private String log(DecimalFormat decimalFormat0, int v) {
        String s = this.fmt(decimalFormat0, ((float)this.getValue(v)) * 0.000001f, 7);
        String s1 = "                         " + this.geName(v);
        return "CL Perf: " + (s1.substring(s1.length() - 25) + " = ") + s;
    }

    private void log(String s) {
        StackTraceElement stackTraceElement0 = new Throwable().getStackTrace()[2];
        Log.v(s, "CL Perf: --------  Performance .(" + stackTraceElement0.getFileName() + ":" + stackTraceElement0.getLineNumber() + ")  ------ ");
        DecimalFormat decimalFormat0 = new DecimalFormat("###.000");
        Log.v(s, this.log(decimalFormat0, 5));
        Log.v(s, this.log(decimalFormat0, 7));
        Log.v(s, this.log(decimalFormat0, 6));
        Log.v(s, this.log(1));
        Log.v(s, this.log(2));
        Log.v(s, this.log(3));
        Log.v(s, this.log(4));
        Log.v(s, this.log(8));
        Log.v(s, this.log(9));
        Log.v(s, this.log(10));
    }

    public void logSummary(String s) {
        this.log(s);
    }

    public void logSummary(String s, ConstraintLayoutStatistics constraintLayoutStatistics0) {
        if(constraintLayoutStatistics0 == null) {
            this.log(s);
            return;
        }
        DecimalFormat decimalFormat0 = new DecimalFormat("###.000");
        StackTraceElement stackTraceElement0 = new Throwable().getStackTrace()[1];
        Log.v(s, "CL Perf: -=  Performance .(" + stackTraceElement0.getFileName() + ":" + stackTraceElement0.getLineNumber() + ")  =- ");
        Log.v(s, this.compare(decimalFormat0, constraintLayoutStatistics0, 5));
        Log.v(s, this.compare(decimalFormat0, constraintLayoutStatistics0, 7));
        Log.v(s, this.compare(decimalFormat0, constraintLayoutStatistics0, 6));
        Log.v(s, this.compare(constraintLayoutStatistics0, 1));
        Log.v(s, this.compare(constraintLayoutStatistics0, 2));
        Log.v(s, this.compare(constraintLayoutStatistics0, 3));
        Log.v(s, this.compare(constraintLayoutStatistics0, 4));
        Log.v(s, this.compare(constraintLayoutStatistics0, 8));
        Log.v(s, this.compare(constraintLayoutStatistics0, 9));
        Log.v(s, this.compare(constraintLayoutStatistics0, 10));
    }

    public void reset() {
        this.mMetrics.reset();
    }
}

