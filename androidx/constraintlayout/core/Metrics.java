package androidx.constraintlayout.core;

import java.util.ArrayList;

public class Metrics {
    public long additionalMeasures;
    public long bfs;
    public long constraints;
    public long determineGroups;
    public long errors;
    public long extravariables;
    public long fullySolved;
    public long graphOptimizer;
    public long graphSolved;
    public long grouping;
    public long infeasibleDetermineGroups;
    public long iterations;
    public long lastTableSize;
    public long layouts;
    public long linearSolved;
    public long mChildCount;
    public long mEquations;
    public long mMeasureCalls;
    public long mMeasureDuration;
    public int mNumberOfLayouts;
    public int mNumberOfMeasures;
    public long mSimpleEquations;
    public long mSolverPasses;
    public long mVariables;
    public long maxRows;
    public long maxTableSize;
    public long maxVariables;
    public long measuredMatchWidgets;
    public long measuredWidgets;
    public long measures;
    public long measuresLayoutDuration;
    public long measuresWidgetsDuration;
    public long measuresWrap;
    public long measuresWrapInfeasible;
    public long minimize;
    public long minimizeGoal;
    public long nonresolvedWidgets;
    public long optimize;
    public long pivots;
    public ArrayList problematicLayouts;
    public long resolutions;
    public long resolvedWidgets;
    public long simpleconstraints;
    public long slackvariables;
    public long tableSizeIncrease;
    public long variables;
    public long widgets;

    public Metrics() {
        this.problematicLayouts = new ArrayList();
    }

    public void copy(Metrics metrics0) {
        this.mVariables = metrics0.mVariables;
        this.mEquations = metrics0.mEquations;
        this.mSimpleEquations = metrics0.mSimpleEquations;
        this.mNumberOfMeasures = metrics0.mNumberOfMeasures;
        this.mNumberOfLayouts = metrics0.mNumberOfLayouts;
        this.mMeasureDuration = metrics0.mMeasureDuration;
        this.mChildCount = metrics0.mChildCount;
        this.mMeasureCalls = metrics0.mMeasureCalls;
        this.measuresWidgetsDuration = metrics0.measuresWidgetsDuration;
        this.mSolverPasses = metrics0.mSolverPasses;
        this.measuresLayoutDuration = metrics0.measuresLayoutDuration;
        this.measures = metrics0.measures;
        this.widgets = metrics0.widgets;
        this.additionalMeasures = metrics0.additionalMeasures;
        this.resolutions = metrics0.resolutions;
        this.tableSizeIncrease = metrics0.tableSizeIncrease;
        this.maxTableSize = metrics0.maxTableSize;
        this.lastTableSize = metrics0.lastTableSize;
        this.maxVariables = metrics0.maxVariables;
        this.maxRows = metrics0.maxRows;
        this.minimize = metrics0.minimize;
        this.minimizeGoal = metrics0.minimizeGoal;
        this.constraints = metrics0.constraints;
        this.simpleconstraints = metrics0.simpleconstraints;
        this.optimize = metrics0.optimize;
        this.iterations = metrics0.iterations;
        this.pivots = metrics0.pivots;
        this.bfs = metrics0.bfs;
        this.variables = metrics0.variables;
        this.errors = metrics0.errors;
        this.slackvariables = metrics0.slackvariables;
        this.extravariables = metrics0.extravariables;
        this.fullySolved = metrics0.fullySolved;
        this.graphOptimizer = metrics0.graphOptimizer;
        this.graphSolved = metrics0.graphSolved;
        this.resolvedWidgets = metrics0.resolvedWidgets;
        this.nonresolvedWidgets = metrics0.nonresolvedWidgets;
    }

    public void reset() {
        this.measures = 0L;
        this.widgets = 0L;
        this.additionalMeasures = 0L;
        this.resolutions = 0L;
        this.tableSizeIncrease = 0L;
        this.maxTableSize = 0L;
        this.lastTableSize = 0L;
        this.maxVariables = 0L;
        this.maxRows = 0L;
        this.minimize = 0L;
        this.minimizeGoal = 0L;
        this.constraints = 0L;
        this.simpleconstraints = 0L;
        this.optimize = 0L;
        this.iterations = 0L;
        this.pivots = 0L;
        this.bfs = 0L;
        this.variables = 0L;
        this.errors = 0L;
        this.slackvariables = 0L;
        this.extravariables = 0L;
        this.fullySolved = 0L;
        this.graphOptimizer = 0L;
        this.graphSolved = 0L;
        this.resolvedWidgets = 0L;
        this.nonresolvedWidgets = 0L;
        this.linearSolved = 0L;
        this.problematicLayouts.clear();
        this.mNumberOfMeasures = 0;
        this.mNumberOfLayouts = 0;
        this.measuresWidgetsDuration = 0L;
        this.measuresLayoutDuration = 0L;
        this.mChildCount = 0L;
        this.mMeasureDuration = 0L;
        this.mMeasureCalls = 0L;
        this.mSolverPasses = 0L;
        this.mVariables = 0L;
        this.mEquations = 0L;
        this.mSimpleEquations = 0L;
    }

    @Override
    public String toString() {
        return "\n*** Metrics ***\nmeasures: " + this.measures + "\nmeasuresWrap: " + this.measuresWrap + "\nmeasuresWrapInfeasible: " + this.measuresWrapInfeasible + "\ndetermineGroups: " + this.determineGroups + "\ninfeasibleDetermineGroups: " + this.infeasibleDetermineGroups + "\ngraphOptimizer: " + this.graphOptimizer + "\nwidgets: " + this.widgets + "\ngraphSolved: " + this.graphSolved + "\nlinearSolved: " + this.linearSolved + "\n";
    }
}

