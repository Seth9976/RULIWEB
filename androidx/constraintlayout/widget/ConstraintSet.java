package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionScene;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConstraintSet {
    public static class Constraint {
        static class Delta {
            private static final int INITIAL_BOOLEAN = 4;
            private static final int INITIAL_FLOAT = 10;
            private static final int INITIAL_INT = 10;
            private static final int INITIAL_STRING = 5;
            int mCountBoolean;
            int mCountFloat;
            int mCountInt;
            int mCountString;
            int[] mTypeBoolean;
            int[] mTypeFloat;
            int[] mTypeInt;
            int[] mTypeString;
            boolean[] mValueBoolean;
            float[] mValueFloat;
            int[] mValueInt;
            String[] mValueString;

            Delta() {
                this.mTypeInt = new int[10];
                this.mValueInt = new int[10];
                this.mCountInt = 0;
                this.mTypeFloat = new int[10];
                this.mValueFloat = new float[10];
                this.mCountFloat = 0;
                this.mTypeString = new int[5];
                this.mValueString = new String[5];
                this.mCountString = 0;
                this.mTypeBoolean = new int[4];
                this.mValueBoolean = new boolean[4];
                this.mCountBoolean = 0;
            }

            void add(int v, float f) {
                int[] arr_v = this.mTypeFloat;
                if(this.mCountFloat >= arr_v.length) {
                    this.mTypeFloat = Arrays.copyOf(arr_v, arr_v.length * 2);
                    this.mValueFloat = Arrays.copyOf(this.mValueFloat, this.mValueFloat.length * 2);
                }
                int v1 = this.mCountFloat;
                this.mTypeFloat[v1] = v;
                this.mCountFloat = v1 + 1;
                this.mValueFloat[v1] = f;
            }

            void add(int v, int v1) {
                int[] arr_v = this.mTypeInt;
                if(this.mCountInt >= arr_v.length) {
                    this.mTypeInt = Arrays.copyOf(arr_v, arr_v.length * 2);
                    this.mValueInt = Arrays.copyOf(this.mValueInt, this.mValueInt.length * 2);
                }
                int v2 = this.mCountInt;
                this.mTypeInt[v2] = v;
                this.mCountInt = v2 + 1;
                this.mValueInt[v2] = v1;
            }

            void add(int v, String s) {
                int[] arr_v = this.mTypeString;
                if(this.mCountString >= arr_v.length) {
                    this.mTypeString = Arrays.copyOf(arr_v, arr_v.length * 2);
                    this.mValueString = (String[])Arrays.copyOf(this.mValueString, this.mValueString.length * 2);
                }
                int v1 = this.mCountString;
                this.mTypeString[v1] = v;
                this.mCountString = v1 + 1;
                this.mValueString[v1] = s;
            }

            void add(int v, boolean z) {
                int[] arr_v = this.mTypeBoolean;
                if(this.mCountBoolean >= arr_v.length) {
                    this.mTypeBoolean = Arrays.copyOf(arr_v, arr_v.length * 2);
                    this.mValueBoolean = Arrays.copyOf(this.mValueBoolean, this.mValueBoolean.length * 2);
                }
                int v1 = this.mCountBoolean;
                this.mTypeBoolean[v1] = v;
                this.mCountBoolean = v1 + 1;
                this.mValueBoolean[v1] = z;
            }

            void applyDelta(Constraint constraintSet$Constraint0) {
                for(int v1 = 0; v1 < this.mCountInt; ++v1) {
                    ConstraintSet.setDeltaValue(constraintSet$Constraint0, this.mTypeInt[v1], this.mValueInt[v1]);
                }
                for(int v2 = 0; v2 < this.mCountFloat; ++v2) {
                    ConstraintSet.setDeltaValue(constraintSet$Constraint0, this.mTypeFloat[v2], this.mValueFloat[v2]);
                }
                for(int v3 = 0; v3 < this.mCountString; ++v3) {
                    ConstraintSet.setDeltaValue(constraintSet$Constraint0, this.mTypeString[v3], this.mValueString[v3]);
                }
                for(int v = 0; v < this.mCountBoolean; ++v) {
                    ConstraintSet.setDeltaValue(constraintSet$Constraint0, this.mTypeBoolean[v], this.mValueBoolean[v]);
                }
            }

            void printDelta(String s) {
                Log.v(s, "int");
                for(int v1 = 0; v1 < this.mCountInt; ++v1) {
                    Log.v(s, this.mTypeInt[v1] + " = " + this.mValueInt[v1]);
                }
                Log.v(s, "float");
                for(int v2 = 0; v2 < this.mCountFloat; ++v2) {
                    Log.v(s, this.mTypeFloat[v2] + " = " + this.mValueFloat[v2]);
                }
                Log.v(s, "strings");
                for(int v3 = 0; v3 < this.mCountString; ++v3) {
                    Log.v(s, this.mTypeString[v3] + " = " + this.mValueString[v3]);
                }
                Log.v(s, "boolean");
                for(int v = 0; v < this.mCountBoolean; ++v) {
                    Log.v(s, this.mTypeBoolean[v] + " = " + this.mValueBoolean[v]);
                }
            }
        }

        public final Layout layout;
        public HashMap mCustomConstraints;
        Delta mDelta;
        String mTargetString;
        int mViewId;
        public final Motion motion;
        public final PropertySet propertySet;
        public final Transform transform;

        public Constraint() {
            this.propertySet = new PropertySet();
            this.motion = new Motion();
            this.layout = new Layout();
            this.transform = new Transform();
            this.mCustomConstraints = new HashMap();
        }

        public void applyDelta(Constraint constraintSet$Constraint0) {
            Delta constraintSet$Constraint$Delta0 = this.mDelta;
            if(constraintSet$Constraint$Delta0 != null) {
                constraintSet$Constraint$Delta0.applyDelta(constraintSet$Constraint0);
            }
        }

        public void applyTo(LayoutParams constraintLayout$LayoutParams0) {
            constraintLayout$LayoutParams0.leftToLeft = this.layout.leftToLeft;
            constraintLayout$LayoutParams0.leftToRight = this.layout.leftToRight;
            constraintLayout$LayoutParams0.rightToLeft = this.layout.rightToLeft;
            constraintLayout$LayoutParams0.rightToRight = this.layout.rightToRight;
            constraintLayout$LayoutParams0.topToTop = this.layout.topToTop;
            constraintLayout$LayoutParams0.topToBottom = this.layout.topToBottom;
            constraintLayout$LayoutParams0.bottomToTop = this.layout.bottomToTop;
            constraintLayout$LayoutParams0.bottomToBottom = this.layout.bottomToBottom;
            constraintLayout$LayoutParams0.baselineToBaseline = this.layout.baselineToBaseline;
            constraintLayout$LayoutParams0.baselineToTop = this.layout.baselineToTop;
            constraintLayout$LayoutParams0.baselineToBottom = this.layout.baselineToBottom;
            constraintLayout$LayoutParams0.startToEnd = this.layout.startToEnd;
            constraintLayout$LayoutParams0.startToStart = this.layout.startToStart;
            constraintLayout$LayoutParams0.endToStart = this.layout.endToStart;
            constraintLayout$LayoutParams0.endToEnd = this.layout.endToEnd;
            constraintLayout$LayoutParams0.leftMargin = this.layout.leftMargin;
            constraintLayout$LayoutParams0.rightMargin = this.layout.rightMargin;
            constraintLayout$LayoutParams0.topMargin = this.layout.topMargin;
            constraintLayout$LayoutParams0.bottomMargin = this.layout.bottomMargin;
            constraintLayout$LayoutParams0.goneStartMargin = this.layout.goneStartMargin;
            constraintLayout$LayoutParams0.goneEndMargin = this.layout.goneEndMargin;
            constraintLayout$LayoutParams0.goneTopMargin = this.layout.goneTopMargin;
            constraintLayout$LayoutParams0.goneBottomMargin = this.layout.goneBottomMargin;
            constraintLayout$LayoutParams0.horizontalBias = this.layout.horizontalBias;
            constraintLayout$LayoutParams0.verticalBias = this.layout.verticalBias;
            constraintLayout$LayoutParams0.circleConstraint = this.layout.circleConstraint;
            constraintLayout$LayoutParams0.circleRadius = this.layout.circleRadius;
            constraintLayout$LayoutParams0.circleAngle = this.layout.circleAngle;
            constraintLayout$LayoutParams0.dimensionRatio = this.layout.dimensionRatio;
            constraintLayout$LayoutParams0.editorAbsoluteX = this.layout.editorAbsoluteX;
            constraintLayout$LayoutParams0.editorAbsoluteY = this.layout.editorAbsoluteY;
            constraintLayout$LayoutParams0.verticalWeight = this.layout.verticalWeight;
            constraintLayout$LayoutParams0.horizontalWeight = this.layout.horizontalWeight;
            constraintLayout$LayoutParams0.verticalChainStyle = this.layout.verticalChainStyle;
            constraintLayout$LayoutParams0.horizontalChainStyle = this.layout.horizontalChainStyle;
            constraintLayout$LayoutParams0.constrainedWidth = this.layout.constrainedWidth;
            constraintLayout$LayoutParams0.constrainedHeight = this.layout.constrainedHeight;
            constraintLayout$LayoutParams0.matchConstraintDefaultWidth = this.layout.widthDefault;
            constraintLayout$LayoutParams0.matchConstraintDefaultHeight = this.layout.heightDefault;
            constraintLayout$LayoutParams0.matchConstraintMaxWidth = this.layout.widthMax;
            constraintLayout$LayoutParams0.matchConstraintMaxHeight = this.layout.heightMax;
            constraintLayout$LayoutParams0.matchConstraintMinWidth = this.layout.widthMin;
            constraintLayout$LayoutParams0.matchConstraintMinHeight = this.layout.heightMin;
            constraintLayout$LayoutParams0.matchConstraintPercentWidth = this.layout.widthPercent;
            constraintLayout$LayoutParams0.matchConstraintPercentHeight = this.layout.heightPercent;
            constraintLayout$LayoutParams0.orientation = this.layout.orientation;
            constraintLayout$LayoutParams0.guidePercent = this.layout.guidePercent;
            constraintLayout$LayoutParams0.guideBegin = this.layout.guideBegin;
            constraintLayout$LayoutParams0.guideEnd = this.layout.guideEnd;
            constraintLayout$LayoutParams0.width = this.layout.mWidth;
            constraintLayout$LayoutParams0.height = this.layout.mHeight;
            if(this.layout.mConstraintTag != null) {
                constraintLayout$LayoutParams0.constraintTag = this.layout.mConstraintTag;
            }
            constraintLayout$LayoutParams0.wrapBehaviorInParent = this.layout.mWrapBehavior;
            constraintLayout$LayoutParams0.setMarginStart(this.layout.startMargin);
            constraintLayout$LayoutParams0.setMarginEnd(this.layout.endMargin);
            constraintLayout$LayoutParams0.validate();
        }

        public Constraint clone() {
            Constraint constraintSet$Constraint0 = new Constraint();
            constraintSet$Constraint0.layout.copyFrom(this.layout);
            constraintSet$Constraint0.motion.copyFrom(this.motion);
            constraintSet$Constraint0.propertySet.copyFrom(this.propertySet);
            constraintSet$Constraint0.transform.copyFrom(this.transform);
            constraintSet$Constraint0.mViewId = this.mViewId;
            constraintSet$Constraint0.mDelta = this.mDelta;
            return constraintSet$Constraint0;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return this.clone();
        }

        private void fillFrom(int v, LayoutParams constraintLayout$LayoutParams0) {
            this.mViewId = v;
            this.layout.leftToLeft = constraintLayout$LayoutParams0.leftToLeft;
            this.layout.leftToRight = constraintLayout$LayoutParams0.leftToRight;
            this.layout.rightToLeft = constraintLayout$LayoutParams0.rightToLeft;
            this.layout.rightToRight = constraintLayout$LayoutParams0.rightToRight;
            this.layout.topToTop = constraintLayout$LayoutParams0.topToTop;
            this.layout.topToBottom = constraintLayout$LayoutParams0.topToBottom;
            this.layout.bottomToTop = constraintLayout$LayoutParams0.bottomToTop;
            this.layout.bottomToBottom = constraintLayout$LayoutParams0.bottomToBottom;
            this.layout.baselineToBaseline = constraintLayout$LayoutParams0.baselineToBaseline;
            this.layout.baselineToTop = constraintLayout$LayoutParams0.baselineToTop;
            this.layout.baselineToBottom = constraintLayout$LayoutParams0.baselineToBottom;
            this.layout.startToEnd = constraintLayout$LayoutParams0.startToEnd;
            this.layout.startToStart = constraintLayout$LayoutParams0.startToStart;
            this.layout.endToStart = constraintLayout$LayoutParams0.endToStart;
            this.layout.endToEnd = constraintLayout$LayoutParams0.endToEnd;
            this.layout.horizontalBias = constraintLayout$LayoutParams0.horizontalBias;
            this.layout.verticalBias = constraintLayout$LayoutParams0.verticalBias;
            this.layout.dimensionRatio = constraintLayout$LayoutParams0.dimensionRatio;
            this.layout.circleConstraint = constraintLayout$LayoutParams0.circleConstraint;
            this.layout.circleRadius = constraintLayout$LayoutParams0.circleRadius;
            this.layout.circleAngle = constraintLayout$LayoutParams0.circleAngle;
            this.layout.editorAbsoluteX = constraintLayout$LayoutParams0.editorAbsoluteX;
            this.layout.editorAbsoluteY = constraintLayout$LayoutParams0.editorAbsoluteY;
            this.layout.orientation = constraintLayout$LayoutParams0.orientation;
            this.layout.guidePercent = constraintLayout$LayoutParams0.guidePercent;
            this.layout.guideBegin = constraintLayout$LayoutParams0.guideBegin;
            this.layout.guideEnd = constraintLayout$LayoutParams0.guideEnd;
            this.layout.mWidth = constraintLayout$LayoutParams0.width;
            this.layout.mHeight = constraintLayout$LayoutParams0.height;
            this.layout.leftMargin = constraintLayout$LayoutParams0.leftMargin;
            this.layout.rightMargin = constraintLayout$LayoutParams0.rightMargin;
            this.layout.topMargin = constraintLayout$LayoutParams0.topMargin;
            this.layout.bottomMargin = constraintLayout$LayoutParams0.bottomMargin;
            this.layout.baselineMargin = constraintLayout$LayoutParams0.baselineMargin;
            this.layout.verticalWeight = constraintLayout$LayoutParams0.verticalWeight;
            this.layout.horizontalWeight = constraintLayout$LayoutParams0.horizontalWeight;
            this.layout.verticalChainStyle = constraintLayout$LayoutParams0.verticalChainStyle;
            this.layout.horizontalChainStyle = constraintLayout$LayoutParams0.horizontalChainStyle;
            this.layout.constrainedWidth = constraintLayout$LayoutParams0.constrainedWidth;
            this.layout.constrainedHeight = constraintLayout$LayoutParams0.constrainedHeight;
            this.layout.widthDefault = constraintLayout$LayoutParams0.matchConstraintDefaultWidth;
            this.layout.heightDefault = constraintLayout$LayoutParams0.matchConstraintDefaultHeight;
            this.layout.widthMax = constraintLayout$LayoutParams0.matchConstraintMaxWidth;
            this.layout.heightMax = constraintLayout$LayoutParams0.matchConstraintMaxHeight;
            this.layout.widthMin = constraintLayout$LayoutParams0.matchConstraintMinWidth;
            this.layout.heightMin = constraintLayout$LayoutParams0.matchConstraintMinHeight;
            this.layout.widthPercent = constraintLayout$LayoutParams0.matchConstraintPercentWidth;
            this.layout.heightPercent = constraintLayout$LayoutParams0.matchConstraintPercentHeight;
            this.layout.mConstraintTag = constraintLayout$LayoutParams0.constraintTag;
            this.layout.goneTopMargin = constraintLayout$LayoutParams0.goneTopMargin;
            this.layout.goneBottomMargin = constraintLayout$LayoutParams0.goneBottomMargin;
            this.layout.goneLeftMargin = constraintLayout$LayoutParams0.goneLeftMargin;
            this.layout.goneRightMargin = constraintLayout$LayoutParams0.goneRightMargin;
            this.layout.goneStartMargin = constraintLayout$LayoutParams0.goneStartMargin;
            this.layout.goneEndMargin = constraintLayout$LayoutParams0.goneEndMargin;
            this.layout.goneBaselineMargin = constraintLayout$LayoutParams0.goneBaselineMargin;
            this.layout.mWrapBehavior = constraintLayout$LayoutParams0.wrapBehaviorInParent;
            this.layout.endMargin = constraintLayout$LayoutParams0.getMarginEnd();
            this.layout.startMargin = constraintLayout$LayoutParams0.getMarginStart();
        }

        private void fillFromConstraints(int v, androidx.constraintlayout.widget.Constraints.LayoutParams constraints$LayoutParams0) {
            this.fillFrom(v, constraints$LayoutParams0);
            this.propertySet.alpha = constraints$LayoutParams0.alpha;
            this.transform.rotation = constraints$LayoutParams0.rotation;
            this.transform.rotationX = constraints$LayoutParams0.rotationX;
            this.transform.rotationY = constraints$LayoutParams0.rotationY;
            this.transform.scaleX = constraints$LayoutParams0.scaleX;
            this.transform.scaleY = constraints$LayoutParams0.scaleY;
            this.transform.transformPivotX = constraints$LayoutParams0.transformPivotX;
            this.transform.transformPivotY = constraints$LayoutParams0.transformPivotY;
            this.transform.translationX = constraints$LayoutParams0.translationX;
            this.transform.translationY = constraints$LayoutParams0.translationY;
            this.transform.translationZ = constraints$LayoutParams0.translationZ;
            this.transform.elevation = constraints$LayoutParams0.elevation;
            this.transform.applyElevation = constraints$LayoutParams0.applyElevation;
        }

        private void fillFromConstraints(ConstraintHelper constraintHelper0, int v, androidx.constraintlayout.widget.Constraints.LayoutParams constraints$LayoutParams0) {
            this.fillFromConstraints(v, constraints$LayoutParams0);
            if(constraintHelper0 instanceof Barrier) {
                this.layout.mHelperType = 1;
                this.layout.mBarrierDirection = ((Barrier)constraintHelper0).getType();
                this.layout.mReferenceIds = ((Barrier)constraintHelper0).getReferencedIds();
                this.layout.mBarrierMargin = ((Barrier)constraintHelper0).getMargin();
            }
        }

        private ConstraintAttribute get(String s, AttributeType constraintAttribute$AttributeType0) {
            if(this.mCustomConstraints.containsKey(s)) {
                ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)this.mCustomConstraints.get(s);
                if(constraintAttribute0.getType() != constraintAttribute$AttributeType0) {
                    throw new IllegalArgumentException("ConstraintAttribute is already a " + constraintAttribute0.getType().name());
                }
                return constraintAttribute0;
            }
            ConstraintAttribute constraintAttribute1 = new ConstraintAttribute(s, constraintAttribute$AttributeType0);
            this.mCustomConstraints.put(s, constraintAttribute1);
            return constraintAttribute1;
        }

        public void printDelta(String s) {
            Delta constraintSet$Constraint$Delta0 = this.mDelta;
            if(constraintSet$Constraint$Delta0 != null) {
                constraintSet$Constraint$Delta0.printDelta(s);
                return;
            }
            Log.v(s, "DELTA IS NULL");
        }

        private void setColorValue(String s, int v) {
            this.get(s, AttributeType.COLOR_TYPE).setColorValue(v);
        }

        private void setFloatValue(String s, float f) {
            this.get(s, AttributeType.FLOAT_TYPE).setFloatValue(f);
        }

        private void setIntValue(String s, int v) {
            this.get(s, AttributeType.INT_TYPE).setIntValue(v);
        }

        private void setStringValue(String s, String s1) {
            this.get(s, AttributeType.STRING_TYPE).setStringValue(s1);
        }
    }

    public static class Layout {
        private static final int BARRIER_ALLOWS_GONE_WIDGETS = 75;
        private static final int BARRIER_DIRECTION = 72;
        private static final int BARRIER_MARGIN = 73;
        private static final int BASELINE_MARGIN = 80;
        private static final int BASELINE_TO_BASELINE = 1;
        private static final int BASELINE_TO_BOTTOM = 78;
        private static final int BASELINE_TO_TOP = 77;
        private static final int BOTTOM_MARGIN = 2;
        private static final int BOTTOM_TO_BOTTOM = 3;
        private static final int BOTTOM_TO_TOP = 4;
        private static final int CHAIN_USE_RTL = 71;
        private static final int CIRCLE = 61;
        private static final int CIRCLE_ANGLE = 0x3F;
        private static final int CIRCLE_RADIUS = 62;
        private static final int CONSTRAINED_HEIGHT = 88;
        private static final int CONSTRAINED_WIDTH = 87;
        private static final int CONSTRAINT_REFERENCED_IDS = 74;
        private static final int CONSTRAINT_TAG = 89;
        private static final int DIMENSION_RATIO = 5;
        private static final int EDITOR_ABSOLUTE_X = 6;
        private static final int EDITOR_ABSOLUTE_Y = 7;
        private static final int END_MARGIN = 8;
        private static final int END_TO_END = 9;
        private static final int END_TO_START = 10;
        private static final int GONE_BASELINE_MARGIN = 0x4F;
        private static final int GONE_BOTTOM_MARGIN = 11;
        private static final int GONE_END_MARGIN = 12;
        private static final int GONE_LEFT_MARGIN = 13;
        private static final int GONE_RIGHT_MARGIN = 14;
        private static final int GONE_START_MARGIN = 15;
        private static final int GONE_TOP_MARGIN = 16;
        private static final int GUIDE_BEGIN = 17;
        private static final int GUIDE_END = 18;
        private static final int GUIDE_PERCENT = 19;
        private static final int GUIDE_USE_RTL = 90;
        private static final int HEIGHT_DEFAULT = 82;
        private static final int HEIGHT_MAX = 83;
        private static final int HEIGHT_MIN = 85;
        private static final int HEIGHT_PERCENT = 70;
        private static final int HORIZONTAL_BIAS = 20;
        private static final int HORIZONTAL_STYLE = 39;
        private static final int HORIZONTAL_WEIGHT = 37;
        private static final int LAYOUT_CONSTRAINT_HEIGHT = 42;
        private static final int LAYOUT_CONSTRAINT_WIDTH = 41;
        private static final int LAYOUT_HEIGHT = 21;
        private static final int LAYOUT_WIDTH = 22;
        private static final int LAYOUT_WRAP_BEHAVIOR = 76;
        private static final int LEFT_MARGIN = 23;
        private static final int LEFT_TO_LEFT = 24;
        private static final int LEFT_TO_RIGHT = 25;
        private static final int ORIENTATION = 26;
        private static final int RIGHT_MARGIN = 27;
        private static final int RIGHT_TO_LEFT = 28;
        private static final int RIGHT_TO_RIGHT = 29;
        private static final int START_MARGIN = 30;
        private static final int START_TO_END = 0x1F;
        private static final int START_TO_START = 0x20;
        private static final int TOP_MARGIN = 33;
        private static final int TOP_TO_BOTTOM = 34;
        private static final int TOP_TO_TOP = 35;
        public static final int UNSET = -1;
        public static final int UNSET_GONE_MARGIN = 0x80000000;
        private static final int UNUSED = 91;
        private static final int VERTICAL_BIAS = 36;
        private static final int VERTICAL_STYLE = 40;
        private static final int VERTICAL_WEIGHT = 38;
        private static final int WIDTH_DEFAULT = 81;
        private static final int WIDTH_MAX = 84;
        private static final int WIDTH_MIN = 86;
        private static final int WIDTH_PERCENT = 69;
        public int baselineMargin;
        public int baselineToBaseline;
        public int baselineToBottom;
        public int baselineToTop;
        public int bottomMargin;
        public int bottomToBottom;
        public int bottomToTop;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String dimensionRatio;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endMargin;
        public int endToEnd;
        public int endToStart;
        public int goneBaselineMargin;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public boolean guidelineUseRtl;
        public int heightDefault;
        public int heightMax;
        public int heightMin;
        public float heightPercent;
        public float horizontalBias;
        public int horizontalChainStyle;
        public float horizontalWeight;
        public int leftMargin;
        public int leftToLeft;
        public int leftToRight;
        public boolean mApply;
        public boolean mBarrierAllowsGoneWidgets;
        public int mBarrierDirection;
        public int mBarrierMargin;
        public String mConstraintTag;
        public int mHeight;
        public int mHelperType;
        public boolean mIsGuideline;
        public boolean mOverride;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        public int mWidth;
        public int mWrapBehavior;
        public int orientation;
        public int rightMargin;
        public int rightToLeft;
        public int rightToRight;
        private static SparseIntArray sMapToConstant;
        public int startMargin;
        public int startToEnd;
        public int startToStart;
        public int topMargin;
        public int topToBottom;
        public int topToTop;
        public float verticalBias;
        public int verticalChainStyle;
        public float verticalWeight;
        public int widthDefault;
        public int widthMax;
        public int widthMin;
        public float widthPercent;

        static {
            SparseIntArray sparseIntArray0 = new SparseIntArray();
            Layout.sMapToConstant = sparseIntArray0;
            sparseIntArray0.append(styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintLeft_toRightOf, 25);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintRight_toLeftOf, 28);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintRight_toRightOf, 29);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintTop_toTopOf, 35);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintTop_toBottomOf, 34);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintBottom_toTopOf, 4);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            Layout.sMapToConstant.append(styleable.Layout_layout_editor_absoluteX, 6);
            Layout.sMapToConstant.append(styleable.Layout_layout_editor_absoluteY, 7);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintGuide_begin, 17);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintGuide_end, 18);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintGuide_percent, 19);
            Layout.sMapToConstant.append(styleable.Layout_guidelineUseRtl, 90);
            Layout.sMapToConstant.append(styleable.Layout_android_orientation, 26);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintStart_toEndOf, 0x1F);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintStart_toStartOf, 0x20);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintEnd_toStartOf, 10);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintEnd_toEndOf, 9);
            Layout.sMapToConstant.append(styleable.Layout_layout_goneMarginLeft, 13);
            Layout.sMapToConstant.append(styleable.Layout_layout_goneMarginTop, 16);
            Layout.sMapToConstant.append(styleable.Layout_layout_goneMarginRight, 14);
            Layout.sMapToConstant.append(styleable.Layout_layout_goneMarginBottom, 11);
            Layout.sMapToConstant.append(styleable.Layout_layout_goneMarginStart, 15);
            Layout.sMapToConstant.append(styleable.Layout_layout_goneMarginEnd, 12);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintVertical_weight, 38);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintHorizontal_weight, 37);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintVertical_chainStyle, 40);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintHorizontal_bias, 20);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintVertical_bias, 36);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintDimensionRatio, 5);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintLeft_creator, 91);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintTop_creator, 91);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintRight_creator, 91);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintBottom_creator, 91);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintBaseline_creator, 91);
            Layout.sMapToConstant.append(styleable.Layout_android_layout_marginLeft, 23);
            Layout.sMapToConstant.append(styleable.Layout_android_layout_marginRight, 27);
            Layout.sMapToConstant.append(styleable.Layout_android_layout_marginStart, 30);
            Layout.sMapToConstant.append(styleable.Layout_android_layout_marginEnd, 8);
            Layout.sMapToConstant.append(styleable.Layout_android_layout_marginTop, 33);
            Layout.sMapToConstant.append(styleable.Layout_android_layout_marginBottom, 2);
            Layout.sMapToConstant.append(styleable.Layout_android_layout_width, 22);
            Layout.sMapToConstant.append(styleable.Layout_android_layout_height, 21);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintWidth, 41);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintHeight, 42);
            Layout.sMapToConstant.append(styleable.Layout_layout_constrainedWidth, 87);
            Layout.sMapToConstant.append(styleable.Layout_layout_constrainedHeight, 88);
            Layout.sMapToConstant.append(styleable.Layout_layout_wrapBehaviorInParent, 76);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintCircle, 61);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintCircleRadius, 62);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintCircleAngle, 0x3F);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintWidth_percent, 69);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintHeight_percent, 70);
            Layout.sMapToConstant.append(styleable.Layout_chainUseRtl, 71);
            Layout.sMapToConstant.append(styleable.Layout_barrierDirection, 72);
            Layout.sMapToConstant.append(styleable.Layout_barrierMargin, 73);
            Layout.sMapToConstant.append(styleable.Layout_constraint_referenced_ids, 74);
            Layout.sMapToConstant.append(styleable.Layout_barrierAllowsGoneWidgets, 75);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintWidth_max, 84);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintWidth_min, 86);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintWidth_max, 83);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintHeight_min, 85);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintWidth, 87);
            Layout.sMapToConstant.append(styleable.Layout_layout_constraintHeight, 88);
            Layout.sMapToConstant.append(styleable.ConstraintLayout_Layout_layout_constraintTag, 89);
            Layout.sMapToConstant.append(styleable.Layout_guidelineUseRtl, 90);
        }

        public Layout() {
            this.mIsGuideline = false;
            this.mApply = false;
            this.mOverride = false;
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.guidelineUseRtl = true;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.leftMargin = 0;
            this.rightMargin = 0;
            this.topMargin = 0;
            this.bottomMargin = 0;
            this.endMargin = 0;
            this.startMargin = 0;
            this.baselineMargin = 0;
            this.goneLeftMargin = 0x80000000;
            this.goneTopMargin = 0x80000000;
            this.goneRightMargin = 0x80000000;
            this.goneBottomMargin = 0x80000000;
            this.goneEndMargin = 0x80000000;
            this.goneStartMargin = 0x80000000;
            this.goneBaselineMargin = 0x80000000;
            this.verticalWeight = -1.0f;
            this.horizontalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.widthDefault = 0;
            this.heightDefault = 0;
            this.widthMax = 0;
            this.heightMax = 0;
            this.widthMin = 0;
            this.heightMin = 0;
            this.widthPercent = 1.0f;
            this.heightPercent = 1.0f;
            this.mBarrierDirection = -1;
            this.mBarrierMargin = 0;
            this.mHelperType = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.mBarrierAllowsGoneWidgets = true;
            this.mWrapBehavior = 0;
        }

        public void copyFrom(Layout constraintSet$Layout0) {
            this.mIsGuideline = constraintSet$Layout0.mIsGuideline;
            this.mWidth = constraintSet$Layout0.mWidth;
            this.mApply = constraintSet$Layout0.mApply;
            this.mHeight = constraintSet$Layout0.mHeight;
            this.guideBegin = constraintSet$Layout0.guideBegin;
            this.guideEnd = constraintSet$Layout0.guideEnd;
            this.guidePercent = constraintSet$Layout0.guidePercent;
            this.guidelineUseRtl = constraintSet$Layout0.guidelineUseRtl;
            this.leftToLeft = constraintSet$Layout0.leftToLeft;
            this.leftToRight = constraintSet$Layout0.leftToRight;
            this.rightToLeft = constraintSet$Layout0.rightToLeft;
            this.rightToRight = constraintSet$Layout0.rightToRight;
            this.topToTop = constraintSet$Layout0.topToTop;
            this.topToBottom = constraintSet$Layout0.topToBottom;
            this.bottomToTop = constraintSet$Layout0.bottomToTop;
            this.bottomToBottom = constraintSet$Layout0.bottomToBottom;
            this.baselineToBaseline = constraintSet$Layout0.baselineToBaseline;
            this.baselineToTop = constraintSet$Layout0.baselineToTop;
            this.baselineToBottom = constraintSet$Layout0.baselineToBottom;
            this.startToEnd = constraintSet$Layout0.startToEnd;
            this.startToStart = constraintSet$Layout0.startToStart;
            this.endToStart = constraintSet$Layout0.endToStart;
            this.endToEnd = constraintSet$Layout0.endToEnd;
            this.horizontalBias = constraintSet$Layout0.horizontalBias;
            this.verticalBias = constraintSet$Layout0.verticalBias;
            this.dimensionRatio = constraintSet$Layout0.dimensionRatio;
            this.circleConstraint = constraintSet$Layout0.circleConstraint;
            this.circleRadius = constraintSet$Layout0.circleRadius;
            this.circleAngle = constraintSet$Layout0.circleAngle;
            this.editorAbsoluteX = constraintSet$Layout0.editorAbsoluteX;
            this.editorAbsoluteY = constraintSet$Layout0.editorAbsoluteY;
            this.orientation = constraintSet$Layout0.orientation;
            this.leftMargin = constraintSet$Layout0.leftMargin;
            this.rightMargin = constraintSet$Layout0.rightMargin;
            this.topMargin = constraintSet$Layout0.topMargin;
            this.bottomMargin = constraintSet$Layout0.bottomMargin;
            this.endMargin = constraintSet$Layout0.endMargin;
            this.startMargin = constraintSet$Layout0.startMargin;
            this.baselineMargin = constraintSet$Layout0.baselineMargin;
            this.goneLeftMargin = constraintSet$Layout0.goneLeftMargin;
            this.goneTopMargin = constraintSet$Layout0.goneTopMargin;
            this.goneRightMargin = constraintSet$Layout0.goneRightMargin;
            this.goneBottomMargin = constraintSet$Layout0.goneBottomMargin;
            this.goneEndMargin = constraintSet$Layout0.goneEndMargin;
            this.goneStartMargin = constraintSet$Layout0.goneStartMargin;
            this.goneBaselineMargin = constraintSet$Layout0.goneBaselineMargin;
            this.verticalWeight = constraintSet$Layout0.verticalWeight;
            this.horizontalWeight = constraintSet$Layout0.horizontalWeight;
            this.horizontalChainStyle = constraintSet$Layout0.horizontalChainStyle;
            this.verticalChainStyle = constraintSet$Layout0.verticalChainStyle;
            this.widthDefault = constraintSet$Layout0.widthDefault;
            this.heightDefault = constraintSet$Layout0.heightDefault;
            this.widthMax = constraintSet$Layout0.widthMax;
            this.heightMax = constraintSet$Layout0.heightMax;
            this.widthMin = constraintSet$Layout0.widthMin;
            this.heightMin = constraintSet$Layout0.heightMin;
            this.widthPercent = constraintSet$Layout0.widthPercent;
            this.heightPercent = constraintSet$Layout0.heightPercent;
            this.mBarrierDirection = constraintSet$Layout0.mBarrierDirection;
            this.mBarrierMargin = constraintSet$Layout0.mBarrierMargin;
            this.mHelperType = constraintSet$Layout0.mHelperType;
            this.mConstraintTag = constraintSet$Layout0.mConstraintTag;
            int[] arr_v = constraintSet$Layout0.mReferenceIds;
            this.mReferenceIds = arr_v == null || constraintSet$Layout0.mReferenceIdString != null ? null : Arrays.copyOf(arr_v, arr_v.length);
            this.mReferenceIdString = constraintSet$Layout0.mReferenceIdString;
            this.constrainedWidth = constraintSet$Layout0.constrainedWidth;
            this.constrainedHeight = constraintSet$Layout0.constrainedHeight;
            this.mBarrierAllowsGoneWidgets = constraintSet$Layout0.mBarrierAllowsGoneWidgets;
            this.mWrapBehavior = constraintSet$Layout0.mWrapBehavior;
        }

        public void dump(MotionScene motionScene0, StringBuilder stringBuilder0) {
            Field[] arr_field = this.getClass().getDeclaredFields();
            stringBuilder0.append("\n");
            for(int v = 0; v < arr_field.length; ++v) {
                Field field0 = arr_field[v];
                String s = field0.getName();
                if(!Modifier.isStatic(field0.getModifiers())) {
                    try {
                        Object object0 = field0.get(this);
                        Class class0 = field0.getType();
                        if(class0 == Integer.TYPE) {
                            Integer integer0 = (Integer)object0;
                            if(((int)integer0) != -1) {
                                String s1 = motionScene0.lookUpConstraintName(((int)integer0));
                                stringBuilder0.append("    ");
                                stringBuilder0.append(s);
                                stringBuilder0.append(" = \"");
                                if(s1 != null) {
                                    integer0 = s1;
                                }
                                stringBuilder0.append(integer0);
                                stringBuilder0.append("\"\n");
                            }
                        }
                        else if(class0 == Float.TYPE && ((float)(((Float)object0))) != -1.0f) {
                            stringBuilder0.append("    ");
                            stringBuilder0.append(s);
                            stringBuilder0.append(" = \"");
                            stringBuilder0.append(((Float)object0));
                            stringBuilder0.append("\"\n");
                        }
                    }
                    catch(IllegalAccessException illegalAccessException0) {
                        Log.e("ConstraintSet", "Error accessing ConstraintSet field", illegalAccessException0);
                    }
                }
            }
        }

        void fillFromAttributeList(Context context0, AttributeSet attributeSet0) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.Layout);
            this.mApply = true;
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                switch(Layout.sMapToConstant.get(v2)) {
                    case 1: {
                        this.baselineToBaseline = ConstraintSet.lookupID(typedArray0, v2, this.baselineToBaseline);
                        break;
                    }
                    case 2: {
                        this.bottomMargin = typedArray0.getDimensionPixelSize(v2, this.bottomMargin);
                        break;
                    }
                    case 3: {
                        this.bottomToBottom = ConstraintSet.lookupID(typedArray0, v2, this.bottomToBottom);
                        break;
                    }
                    case 4: {
                        this.bottomToTop = ConstraintSet.lookupID(typedArray0, v2, this.bottomToTop);
                        break;
                    }
                    case 5: {
                        this.dimensionRatio = typedArray0.getString(v2);
                        break;
                    }
                    case 6: {
                        this.editorAbsoluteX = typedArray0.getDimensionPixelOffset(v2, this.editorAbsoluteX);
                        break;
                    }
                    case 7: {
                        this.editorAbsoluteY = typedArray0.getDimensionPixelOffset(v2, this.editorAbsoluteY);
                        break;
                    }
                    case 8: {
                        this.endMargin = typedArray0.getDimensionPixelSize(v2, this.endMargin);
                        break;
                    }
                    case 9: {
                        this.endToEnd = ConstraintSet.lookupID(typedArray0, v2, this.endToEnd);
                        break;
                    }
                    case 10: {
                        this.endToStart = ConstraintSet.lookupID(typedArray0, v2, this.endToStart);
                        break;
                    }
                    case 11: {
                        this.goneBottomMargin = typedArray0.getDimensionPixelSize(v2, this.goneBottomMargin);
                        break;
                    }
                    case 12: {
                        this.goneEndMargin = typedArray0.getDimensionPixelSize(v2, this.goneEndMargin);
                        break;
                    }
                    case 13: {
                        this.goneLeftMargin = typedArray0.getDimensionPixelSize(v2, this.goneLeftMargin);
                        break;
                    }
                    case 14: {
                        this.goneRightMargin = typedArray0.getDimensionPixelSize(v2, this.goneRightMargin);
                        break;
                    }
                    case 15: {
                        this.goneStartMargin = typedArray0.getDimensionPixelSize(v2, this.goneStartMargin);
                        break;
                    }
                    case 16: {
                        this.goneTopMargin = typedArray0.getDimensionPixelSize(v2, this.goneTopMargin);
                        break;
                    }
                    case 17: {
                        this.guideBegin = typedArray0.getDimensionPixelOffset(v2, this.guideBegin);
                        break;
                    }
                    case 18: {
                        this.guideEnd = typedArray0.getDimensionPixelOffset(v2, this.guideEnd);
                        break;
                    }
                    case 19: {
                        this.guidePercent = typedArray0.getFloat(v2, this.guidePercent);
                        break;
                    }
                    case 20: {
                        this.horizontalBias = typedArray0.getFloat(v2, this.horizontalBias);
                        break;
                    }
                    case 21: {
                        this.mHeight = typedArray0.getLayoutDimension(v2, this.mHeight);
                        break;
                    }
                    case 22: {
                        this.mWidth = typedArray0.getLayoutDimension(v2, this.mWidth);
                        break;
                    }
                    case 23: {
                        this.leftMargin = typedArray0.getDimensionPixelSize(v2, this.leftMargin);
                        break;
                    }
                    case 24: {
                        this.leftToLeft = ConstraintSet.lookupID(typedArray0, v2, this.leftToLeft);
                        break;
                    }
                    case 25: {
                        this.leftToRight = ConstraintSet.lookupID(typedArray0, v2, this.leftToRight);
                        break;
                    }
                    case 26: {
                        this.orientation = typedArray0.getInt(v2, this.orientation);
                        break;
                    }
                    case 27: {
                        this.rightMargin = typedArray0.getDimensionPixelSize(v2, this.rightMargin);
                        break;
                    }
                    case 28: {
                        this.rightToLeft = ConstraintSet.lookupID(typedArray0, v2, this.rightToLeft);
                        break;
                    }
                    case 29: {
                        this.rightToRight = ConstraintSet.lookupID(typedArray0, v2, this.rightToRight);
                        break;
                    }
                    case 30: {
                        this.startMargin = typedArray0.getDimensionPixelSize(v2, this.startMargin);
                        break;
                    }
                    case 0x1F: {
                        this.startToEnd = ConstraintSet.lookupID(typedArray0, v2, this.startToEnd);
                        break;
                    }
                    case 0x20: {
                        this.startToStart = ConstraintSet.lookupID(typedArray0, v2, this.startToStart);
                        break;
                    }
                    case 33: {
                        this.topMargin = typedArray0.getDimensionPixelSize(v2, this.topMargin);
                        break;
                    }
                    case 34: {
                        this.topToBottom = ConstraintSet.lookupID(typedArray0, v2, this.topToBottom);
                        break;
                    }
                    case 35: {
                        this.topToTop = ConstraintSet.lookupID(typedArray0, v2, this.topToTop);
                        break;
                    }
                    case 36: {
                        this.verticalBias = typedArray0.getFloat(v2, this.verticalBias);
                        break;
                    }
                    case 37: {
                        this.horizontalWeight = typedArray0.getFloat(v2, this.horizontalWeight);
                        break;
                    }
                    case 38: {
                        this.verticalWeight = typedArray0.getFloat(v2, this.verticalWeight);
                        break;
                    }
                    case 39: {
                        this.horizontalChainStyle = typedArray0.getInt(v2, this.horizontalChainStyle);
                        break;
                    }
                    case 40: {
                        this.verticalChainStyle = typedArray0.getInt(v2, this.verticalChainStyle);
                        break;
                    }
                    case 41: {
                        ConstraintSet.parseDimensionConstraints(this, typedArray0, v2, 0);
                        break;
                    }
                    case 42: {
                        ConstraintSet.parseDimensionConstraints(this, typedArray0, v2, 1);
                        break;
                    }
                    case 61: {
                        this.circleConstraint = ConstraintSet.lookupID(typedArray0, v2, this.circleConstraint);
                        break;
                    }
                    case 62: {
                        this.circleRadius = typedArray0.getDimensionPixelSize(v2, this.circleRadius);
                        break;
                    }
                    case 0x3F: {
                        this.circleAngle = typedArray0.getFloat(v2, this.circleAngle);
                        break;
                    }
                    case 69: {
                        this.widthPercent = typedArray0.getFloat(v2, 1.0f);
                        break;
                    }
                    case 70: {
                        this.heightPercent = typedArray0.getFloat(v2, 1.0f);
                        break;
                    }
                    case 71: {
                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                        break;
                    }
                    case 72: {
                        this.mBarrierDirection = typedArray0.getInt(v2, this.mBarrierDirection);
                        break;
                    }
                    case 73: {
                        this.mBarrierMargin = typedArray0.getDimensionPixelSize(v2, this.mBarrierMargin);
                        break;
                    }
                    case 74: {
                        this.mReferenceIdString = typedArray0.getString(v2);
                        break;
                    }
                    case 75: {
                        this.mBarrierAllowsGoneWidgets = typedArray0.getBoolean(v2, this.mBarrierAllowsGoneWidgets);
                        break;
                    }
                    case 76: {
                        this.mWrapBehavior = typedArray0.getInt(v2, this.mWrapBehavior);
                        break;
                    }
                    case 77: {
                        this.baselineToTop = ConstraintSet.lookupID(typedArray0, v2, this.baselineToTop);
                        break;
                    }
                    case 78: {
                        this.baselineToBottom = ConstraintSet.lookupID(typedArray0, v2, this.baselineToBottom);
                        break;
                    }
                    case 0x4F: {
                        this.goneBaselineMargin = typedArray0.getDimensionPixelSize(v2, this.goneBaselineMargin);
                        break;
                    }
                    case 80: {
                        this.baselineMargin = typedArray0.getDimensionPixelSize(v2, this.baselineMargin);
                        break;
                    }
                    case 81: {
                        this.widthDefault = typedArray0.getInt(v2, this.widthDefault);
                        break;
                    }
                    case 82: {
                        this.heightDefault = typedArray0.getInt(v2, this.heightDefault);
                        break;
                    }
                    case 83: {
                        this.heightMax = typedArray0.getDimensionPixelSize(v2, this.heightMax);
                        break;
                    }
                    case 84: {
                        this.widthMax = typedArray0.getDimensionPixelSize(v2, this.widthMax);
                        break;
                    }
                    case 85: {
                        this.heightMin = typedArray0.getDimensionPixelSize(v2, this.heightMin);
                        break;
                    }
                    case 86: {
                        this.widthMin = typedArray0.getDimensionPixelSize(v2, this.widthMin);
                        break;
                    }
                    case 87: {
                        this.constrainedWidth = typedArray0.getBoolean(v2, this.constrainedWidth);
                        break;
                    }
                    case 88: {
                        this.constrainedHeight = typedArray0.getBoolean(v2, this.constrainedHeight);
                        break;
                    }
                    case 89: {
                        this.mConstraintTag = typedArray0.getString(v2);
                        break;
                    }
                    case 90: {
                        this.guidelineUseRtl = typedArray0.getBoolean(v2, this.guidelineUseRtl);
                        break;
                    }
                    case 91: {
                        Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(v2) + "   " + Layout.sMapToConstant.get(v2));
                        break;
                    }
                    default: {
                        Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(v2) + "   " + Layout.sMapToConstant.get(v2));
                    }
                }
            }
            typedArray0.recycle();
        }
    }

    public static class Motion {
        private static final int ANIMATE_CIRCLE_ANGLE_TO = 6;
        private static final int ANIMATE_RELATIVE_TO = 5;
        private static final int INTERPOLATOR_REFERENCE_ID = -2;
        private static final int INTERPOLATOR_UNDEFINED = -3;
        private static final int MOTION_DRAW_PATH = 4;
        private static final int MOTION_STAGGER = 7;
        private static final int PATH_MOTION_ARC = 2;
        private static final int QUANTIZE_MOTION_INTERPOLATOR = 10;
        private static final int QUANTIZE_MOTION_PHASE = 9;
        private static final int QUANTIZE_MOTION_STEPS = 8;
        private static final int SPLINE_STRING = -1;
        private static final int TRANSITION_EASING = 3;
        private static final int TRANSITION_PATH_ROTATE = 1;
        public int mAnimateCircleAngleTo;
        public int mAnimateRelativeTo;
        public boolean mApply;
        public int mDrawPath;
        public float mMotionStagger;
        public int mPathMotionArc;
        public float mPathRotate;
        public int mPolarRelativeTo;
        public int mQuantizeInterpolatorID;
        public String mQuantizeInterpolatorString;
        public int mQuantizeInterpolatorType;
        public float mQuantizeMotionPhase;
        public int mQuantizeMotionSteps;
        public String mTransitionEasing;
        private static SparseIntArray sMapToConstant;

        static {
            SparseIntArray sparseIntArray0 = new SparseIntArray();
            Motion.sMapToConstant = sparseIntArray0;
            sparseIntArray0.append(styleable.Motion_motionPathRotate, 1);
            Motion.sMapToConstant.append(styleable.Motion_pathMotionArc, 2);
            Motion.sMapToConstant.append(styleable.Motion_transitionEasing, 3);
            Motion.sMapToConstant.append(styleable.Motion_drawPath, 4);
            Motion.sMapToConstant.append(styleable.Motion_animateRelativeTo, 5);
            Motion.sMapToConstant.append(styleable.Motion_animateCircleAngleTo, 6);
            Motion.sMapToConstant.append(styleable.Motion_motionStagger, 7);
            Motion.sMapToConstant.append(styleable.Motion_quantizeMotionSteps, 8);
            Motion.sMapToConstant.append(styleable.Motion_quantizeMotionPhase, 9);
            Motion.sMapToConstant.append(styleable.Motion_quantizeMotionInterpolator, 10);
        }

        public Motion() {
            this.mApply = false;
            this.mAnimateRelativeTo = -1;
            this.mAnimateCircleAngleTo = 0;
            this.mTransitionEasing = null;
            this.mPathMotionArc = -1;
            this.mDrawPath = 0;
            this.mMotionStagger = NaNf;
            this.mPolarRelativeTo = -1;
            this.mPathRotate = NaNf;
            this.mQuantizeMotionPhase = NaNf;
            this.mQuantizeMotionSteps = -1;
            this.mQuantizeInterpolatorString = null;
            this.mQuantizeInterpolatorType = -3;
            this.mQuantizeInterpolatorID = -1;
        }

        public void copyFrom(Motion constraintSet$Motion0) {
            this.mApply = constraintSet$Motion0.mApply;
            this.mAnimateRelativeTo = constraintSet$Motion0.mAnimateRelativeTo;
            this.mTransitionEasing = constraintSet$Motion0.mTransitionEasing;
            this.mPathMotionArc = constraintSet$Motion0.mPathMotionArc;
            this.mDrawPath = constraintSet$Motion0.mDrawPath;
            this.mPathRotate = constraintSet$Motion0.mPathRotate;
            this.mMotionStagger = constraintSet$Motion0.mMotionStagger;
            this.mPolarRelativeTo = constraintSet$Motion0.mPolarRelativeTo;
        }

        void fillFromAttributeList(Context context0, AttributeSet attributeSet0) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.Motion);
            this.mApply = true;
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                switch(Motion.sMapToConstant.get(v2)) {
                    case 1: {
                        this.mPathRotate = typedArray0.getFloat(v2, this.mPathRotate);
                        break;
                    }
                    case 2: {
                        this.mPathMotionArc = typedArray0.getInt(v2, this.mPathMotionArc);
                        break;
                    }
                    case 3: {
                        if(typedArray0.peekValue(v2).type == 3) {
                            this.mTransitionEasing = typedArray0.getString(v2);
                        }
                        else {
                            String[] arr_s = Easing.NAMED_EASING;
                            this.mTransitionEasing = arr_s[typedArray0.getInteger(v2, 0)];
                        }
                        break;
                    }
                    case 4: {
                        this.mDrawPath = typedArray0.getInt(v2, 0);
                        break;
                    }
                    case 5: {
                        this.mAnimateRelativeTo = ConstraintSet.lookupID(typedArray0, v2, this.mAnimateRelativeTo);
                        break;
                    }
                    case 6: {
                        this.mAnimateCircleAngleTo = typedArray0.getInteger(v2, this.mAnimateCircleAngleTo);
                        break;
                    }
                    case 7: {
                        this.mMotionStagger = typedArray0.getFloat(v2, this.mMotionStagger);
                        break;
                    }
                    case 8: {
                        this.mQuantizeMotionSteps = typedArray0.getInteger(v2, this.mQuantizeMotionSteps);
                        break;
                    }
                    case 9: {
                        this.mQuantizeMotionPhase = typedArray0.getFloat(v2, this.mQuantizeMotionPhase);
                        break;
                    }
                    case 10: {
                        TypedValue typedValue0 = typedArray0.peekValue(v2);
                        if(typedValue0.type == 1) {
                            int v3 = typedArray0.getResourceId(v2, -1);
                            this.mQuantizeInterpolatorID = v3;
                            if(v3 != -1) {
                                this.mQuantizeInterpolatorType = -2;
                            }
                        }
                        else if(typedValue0.type == 3) {
                            String s = typedArray0.getString(v2);
                            this.mQuantizeInterpolatorString = s;
                            if(s.indexOf("/") > 0) {
                                this.mQuantizeInterpolatorID = typedArray0.getResourceId(v2, -1);
                                this.mQuantizeInterpolatorType = -2;
                            }
                            else {
                                this.mQuantizeInterpolatorType = -1;
                            }
                        }
                        else {
                            this.mQuantizeInterpolatorType = typedArray0.getInteger(v2, this.mQuantizeInterpolatorID);
                        }
                    }
                }
            }
            typedArray0.recycle();
        }
    }

    public static class PropertySet {
        public float alpha;
        public boolean mApply;
        public float mProgress;
        public int mVisibilityMode;
        public int visibility;

        public PropertySet() {
            this.mApply = false;
            this.visibility = 0;
            this.mVisibilityMode = 0;
            this.alpha = 1.0f;
            this.mProgress = NaNf;
        }

        public void copyFrom(PropertySet constraintSet$PropertySet0) {
            this.mApply = constraintSet$PropertySet0.mApply;
            this.visibility = constraintSet$PropertySet0.visibility;
            this.alpha = constraintSet$PropertySet0.alpha;
            this.mProgress = constraintSet$PropertySet0.mProgress;
            this.mVisibilityMode = constraintSet$PropertySet0.mVisibilityMode;
        }

        void fillFromAttributeList(Context context0, AttributeSet attributeSet0) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.PropertySet);
            this.mApply = true;
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.PropertySet_android_alpha) {
                    this.alpha = typedArray0.getFloat(v2, this.alpha);
                }
                else if(v2 == styleable.PropertySet_android_visibility) {
                    this.visibility = typedArray0.getInt(v2, this.visibility);
                    this.visibility = ConstraintSet.VISIBILITY_FLAGS[this.visibility];
                }
                else if(v2 == styleable.PropertySet_visibilityMode) {
                    this.mVisibilityMode = typedArray0.getInt(v2, this.mVisibilityMode);
                }
                else if(v2 == styleable.PropertySet_motionProgress) {
                    this.mProgress = typedArray0.getFloat(v2, this.mProgress);
                }
            }
            typedArray0.recycle();
        }
    }

    public static class Transform {
        private static final int ELEVATION = 11;
        private static final int ROTATION = 1;
        private static final int ROTATION_X = 2;
        private static final int ROTATION_Y = 3;
        private static final int SCALE_X = 4;
        private static final int SCALE_Y = 5;
        private static final int TRANSFORM_PIVOT_TARGET = 12;
        private static final int TRANSFORM_PIVOT_X = 6;
        private static final int TRANSFORM_PIVOT_Y = 7;
        private static final int TRANSLATION_X = 8;
        private static final int TRANSLATION_Y = 9;
        private static final int TRANSLATION_Z = 10;
        public boolean applyElevation;
        public float elevation;
        public boolean mApply;
        public float rotation;
        public float rotationX;
        public float rotationY;
        private static SparseIntArray sMapToConstant;
        public float scaleX;
        public float scaleY;
        public int transformPivotTarget;
        public float transformPivotX;
        public float transformPivotY;
        public float translationX;
        public float translationY;
        public float translationZ;

        static {
            SparseIntArray sparseIntArray0 = new SparseIntArray();
            Transform.sMapToConstant = sparseIntArray0;
            sparseIntArray0.append(styleable.Transform_android_rotation, 1);
            Transform.sMapToConstant.append(styleable.Transform_android_rotationX, 2);
            Transform.sMapToConstant.append(styleable.Transform_android_rotationY, 3);
            Transform.sMapToConstant.append(styleable.Transform_android_scaleX, 4);
            Transform.sMapToConstant.append(styleable.Transform_android_scaleY, 5);
            Transform.sMapToConstant.append(styleable.Transform_android_transformPivotX, 6);
            Transform.sMapToConstant.append(styleable.Transform_android_transformPivotY, 7);
            Transform.sMapToConstant.append(styleable.Transform_android_translationX, 8);
            Transform.sMapToConstant.append(styleable.Transform_android_translationY, 9);
            Transform.sMapToConstant.append(styleable.Transform_android_translationZ, 10);
            Transform.sMapToConstant.append(styleable.Transform_android_elevation, 11);
            Transform.sMapToConstant.append(styleable.Transform_transformPivotTarget, 12);
        }

        public Transform() {
            this.mApply = false;
            this.rotation = 0.0f;
            this.rotationX = 0.0f;
            this.rotationY = 0.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.transformPivotX = NaNf;
            this.transformPivotY = NaNf;
            this.transformPivotTarget = -1;
            this.translationX = 0.0f;
            this.translationY = 0.0f;
            this.translationZ = 0.0f;
            this.applyElevation = false;
            this.elevation = 0.0f;
        }

        public void copyFrom(Transform constraintSet$Transform0) {
            this.mApply = constraintSet$Transform0.mApply;
            this.rotation = constraintSet$Transform0.rotation;
            this.rotationX = constraintSet$Transform0.rotationX;
            this.rotationY = constraintSet$Transform0.rotationY;
            this.scaleX = constraintSet$Transform0.scaleX;
            this.scaleY = constraintSet$Transform0.scaleY;
            this.transformPivotX = constraintSet$Transform0.transformPivotX;
            this.transformPivotY = constraintSet$Transform0.transformPivotY;
            this.transformPivotTarget = constraintSet$Transform0.transformPivotTarget;
            this.translationX = constraintSet$Transform0.translationX;
            this.translationY = constraintSet$Transform0.translationY;
            this.translationZ = constraintSet$Transform0.translationZ;
            this.applyElevation = constraintSet$Transform0.applyElevation;
            this.elevation = constraintSet$Transform0.elevation;
        }

        void fillFromAttributeList(Context context0, AttributeSet attributeSet0) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.Transform);
            this.mApply = true;
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                switch(Transform.sMapToConstant.get(v2)) {
                    case 1: {
                        this.rotation = typedArray0.getFloat(v2, this.rotation);
                        break;
                    }
                    case 2: {
                        this.rotationX = typedArray0.getFloat(v2, this.rotationX);
                        break;
                    }
                    case 3: {
                        this.rotationY = typedArray0.getFloat(v2, this.rotationY);
                        break;
                    }
                    case 4: {
                        this.scaleX = typedArray0.getFloat(v2, this.scaleX);
                        break;
                    }
                    case 5: {
                        this.scaleY = typedArray0.getFloat(v2, this.scaleY);
                        break;
                    }
                    case 6: {
                        this.transformPivotX = typedArray0.getDimension(v2, this.transformPivotX);
                        break;
                    }
                    case 7: {
                        this.transformPivotY = typedArray0.getDimension(v2, this.transformPivotY);
                        break;
                    }
                    case 8: {
                        this.translationX = typedArray0.getDimension(v2, this.translationX);
                        break;
                    }
                    case 9: {
                        this.translationY = typedArray0.getDimension(v2, this.translationY);
                        break;
                    }
                    case 10: {
                        this.translationZ = typedArray0.getDimension(v2, this.translationZ);
                        break;
                    }
                    case 11: {
                        this.applyElevation = true;
                        this.elevation = typedArray0.getDimension(v2, this.elevation);
                        break;
                    }
                    case 12: {
                        this.transformPivotTarget = ConstraintSet.lookupID(typedArray0, v2, this.transformPivotTarget);
                    }
                }
            }
            typedArray0.recycle();
        }
    }

    class WriteJsonEngine {
        private static final String SPACE = "       ";
        final String mBASELINE;
        final String mBOTTOM;
        Context mContext;
        final String mEND;
        int mFlags;
        HashMap mIdMap;
        final String mLEFT;
        ConstraintLayout mLayout;
        final String mRIGHT;
        final String mSTART;
        final String mTOP;
        int mUnknownCount;
        Writer mWriter;

        WriteJsonEngine(Writer writer0, ConstraintLayout constraintLayout0, int v) throws IOException {
            this.mUnknownCount = 0;
            this.mLEFT = "\'left\'";
            this.mRIGHT = "\'right\'";
            this.mBASELINE = "\'baseline\'";
            this.mBOTTOM = "\'bottom\'";
            this.mTOP = "\'top\'";
            this.mSTART = "\'start\'";
            this.mEND = "\'end\'";
            this.mIdMap = new HashMap();
            this.mWriter = writer0;
            this.mLayout = constraintLayout0;
            this.mContext = constraintLayout0.getContext();
            this.mFlags = v;
        }

        String getName(int v) {
            if(this.mIdMap.containsKey(v)) {
                return "\'" + ((String)this.mIdMap.get(v)) + "\'";
            }
            if(v == 0) {
                return "\'parent\'";
            }
            String s = this.lookup(v);
            this.mIdMap.put(v, s);
            return "\'" + s + "\'";
        }

        String lookup(int v) {
            try {
                if(v != -1) {
                    return this.mContext.getResources().getResourceEntryName(v);
                }
                int v1 = this.mUnknownCount + 1;
                this.mUnknownCount = v1;
                return "unknown" + v1;
            }
            catch(Exception unused_ex) {
                int v2 = this.mUnknownCount + 1;
                this.mUnknownCount = v2;
                return "unknown" + v2;
            }
        }

        void writeCircle(int v, float f, int v1) throws IOException {
            if(v == -1) {
                return;
            }
            this.mWriter.write("       circle");
            this.mWriter.write(":[");
            this.mWriter.write(this.getName(v));
            this.mWriter.write(", " + f);
            this.mWriter.write(v1 + "]");
        }

        void writeConstraint(String s, int v, String s1, int v1, int v2) throws IOException {
            if(v == -1) {
                return;
            }
            this.mWriter.write("       " + s);
            this.mWriter.write(":[");
            this.mWriter.write(this.getName(v));
            this.mWriter.write(" , ");
            this.mWriter.write(s1);
            if(v1 != 0) {
                this.mWriter.write(" , " + v1);
            }
            this.mWriter.write("],\n");
        }

        private void writeDimension(String s, int v, int v1, float f, int v2, int v3, boolean z) throws IOException {
            switch(v) {
                case -2: {
                    this.mWriter.write("       " + s + ": \'wrap\'\n");
                    return;
                }
                case -1: {
                    this.mWriter.write("       " + s + ": \'parent\'\n");
                    return;
                }
                case 0: {
                    if(v3 == -1 && v2 == -1) {
                        switch(v1) {
                            case 1: {
                                this.mWriter.write("       " + s + ": \'???????????\',\n");
                                return;
                            }
                            case 2: {
                                this.mWriter.write("       " + s + ": \'" + f + "%\',\n");
                                return;
                            }
                            default: {
                                return;
                            }
                        }
                    }
                    switch(v1) {
                        case 0: {
                            this.mWriter.write("       " + s + ": {\'spread\' ," + v2 + ", " + v3 + "}\n");
                            return;
                        }
                        case 1: {
                            this.mWriter.write("       " + s + ": {\'wrap\' ," + v2 + ", " + v3 + "}\n");
                            return;
                        }
                        case 2: {
                            this.mWriter.write("       " + s + ": {\'" + f + "\'% ," + v2 + ", " + v3 + "}\n");
                            return;
                        }
                        default: {
                            return;
                        }
                    }
                }
                default: {
                    this.mWriter.write("       " + s + ": " + v + ",\n");
                }
            }
        }

        private void writeGuideline(int v, int v1, int v2, float f) throws IOException {
            this.writeVariable("\'orientation\'", v);
            this.writeVariable("\'guideBegin\'", v1);
            this.writeVariable("\'guideEnd\'", v2);
            this.writeVariable("\'guidePercent\'", f);
        }

        void writeLayout() throws IOException {
            this.mWriter.write("\n\'ConstraintSet\':{\n");
            for(Object object0: ConstraintSet.this.mConstraints.keySet()) {
                Constraint constraintSet$Constraint0 = (Constraint)ConstraintSet.this.mConstraints.get(((Integer)object0));
                String s = this.getName(((int)(((Integer)object0))));
                this.mWriter.write(s + ":{\n");
                Layout constraintSet$Layout0 = constraintSet$Constraint0.layout;
                this.writeDimension("height", constraintSet$Layout0.mHeight, constraintSet$Layout0.heightDefault, constraintSet$Layout0.heightPercent, constraintSet$Layout0.heightMin, constraintSet$Layout0.heightMax, constraintSet$Layout0.constrainedHeight);
                this.writeDimension("width", constraintSet$Layout0.mWidth, constraintSet$Layout0.widthDefault, constraintSet$Layout0.widthPercent, constraintSet$Layout0.widthMin, constraintSet$Layout0.widthMax, constraintSet$Layout0.constrainedWidth);
                this.writeConstraint("\'left\'", constraintSet$Layout0.leftToLeft, "\'left\'", constraintSet$Layout0.leftMargin, constraintSet$Layout0.goneLeftMargin);
                this.writeConstraint("\'left\'", constraintSet$Layout0.leftToRight, "\'right\'", constraintSet$Layout0.leftMargin, constraintSet$Layout0.goneLeftMargin);
                this.writeConstraint("\'right\'", constraintSet$Layout0.rightToLeft, "\'left\'", constraintSet$Layout0.rightMargin, constraintSet$Layout0.goneRightMargin);
                this.writeConstraint("\'right\'", constraintSet$Layout0.rightToRight, "\'right\'", constraintSet$Layout0.rightMargin, constraintSet$Layout0.goneRightMargin);
                this.writeConstraint("\'baseline\'", constraintSet$Layout0.baselineToBaseline, "\'baseline\'", -1, constraintSet$Layout0.goneBaselineMargin);
                this.writeConstraint("\'baseline\'", constraintSet$Layout0.baselineToTop, "\'top\'", -1, constraintSet$Layout0.goneBaselineMargin);
                this.writeConstraint("\'baseline\'", constraintSet$Layout0.baselineToBottom, "\'bottom\'", -1, constraintSet$Layout0.goneBaselineMargin);
                this.writeConstraint("\'top\'", constraintSet$Layout0.topToBottom, "\'bottom\'", constraintSet$Layout0.topMargin, constraintSet$Layout0.goneTopMargin);
                this.writeConstraint("\'top\'", constraintSet$Layout0.topToTop, "\'top\'", constraintSet$Layout0.topMargin, constraintSet$Layout0.goneTopMargin);
                this.writeConstraint("\'bottom\'", constraintSet$Layout0.bottomToBottom, "\'bottom\'", constraintSet$Layout0.bottomMargin, constraintSet$Layout0.goneBottomMargin);
                this.writeConstraint("\'bottom\'", constraintSet$Layout0.bottomToTop, "\'top\'", constraintSet$Layout0.bottomMargin, constraintSet$Layout0.goneBottomMargin);
                this.writeConstraint("\'start\'", constraintSet$Layout0.startToStart, "\'start\'", constraintSet$Layout0.startMargin, constraintSet$Layout0.goneStartMargin);
                this.writeConstraint("\'start\'", constraintSet$Layout0.startToEnd, "\'end\'", constraintSet$Layout0.startMargin, constraintSet$Layout0.goneStartMargin);
                this.writeConstraint("\'end\'", constraintSet$Layout0.endToStart, "\'start\'", constraintSet$Layout0.endMargin, constraintSet$Layout0.goneEndMargin);
                this.writeConstraint("\'end\'", constraintSet$Layout0.endToEnd, "\'end\'", constraintSet$Layout0.endMargin, constraintSet$Layout0.goneEndMargin);
                this.writeVariable("\'horizontalBias\'", constraintSet$Layout0.horizontalBias, 0.5f);
                this.writeVariable("\'verticalBias\'", constraintSet$Layout0.verticalBias, 0.5f);
                this.writeCircle(constraintSet$Layout0.circleConstraint, constraintSet$Layout0.circleAngle, constraintSet$Layout0.circleRadius);
                this.writeGuideline(constraintSet$Layout0.orientation, constraintSet$Layout0.guideBegin, constraintSet$Layout0.guideEnd, constraintSet$Layout0.guidePercent);
                this.writeVariable("\'dimensionRatio\'", constraintSet$Layout0.dimensionRatio);
                this.writeVariable("\'barrierMargin\'", constraintSet$Layout0.mBarrierMargin);
                this.writeVariable("\'type\'", constraintSet$Layout0.mHelperType);
                this.writeVariable("\'ReferenceId\'", constraintSet$Layout0.mReferenceIdString);
                this.writeVariable("\'mBarrierAllowsGoneWidgets\'", constraintSet$Layout0.mBarrierAllowsGoneWidgets, true);
                this.writeVariable("\'WrapBehavior\'", constraintSet$Layout0.mWrapBehavior);
                this.writeVariable("\'verticalWeight\'", constraintSet$Layout0.verticalWeight);
                this.writeVariable("\'horizontalWeight\'", constraintSet$Layout0.horizontalWeight);
                this.writeVariable("\'horizontalChainStyle\'", constraintSet$Layout0.horizontalChainStyle);
                this.writeVariable("\'verticalChainStyle\'", constraintSet$Layout0.verticalChainStyle);
                this.writeVariable("\'barrierDirection\'", constraintSet$Layout0.mBarrierDirection);
                if(constraintSet$Layout0.mReferenceIds != null) {
                    this.writeVariable("\'ReferenceIds\'", constraintSet$Layout0.mReferenceIds);
                }
                this.mWriter.write("}\n");
            }
            this.mWriter.write("}\n");
        }

        void writeVariable(String s, float f) throws IOException {
            if(f == -1.0f) {
                return;
            }
            this.mWriter.write("       " + s);
            this.mWriter.write(": " + f);
            this.mWriter.write(",\n");
        }

        void writeVariable(String s, float f, float f1) throws IOException {
            if(f == f1) {
                return;
            }
            this.mWriter.write("       " + s);
            this.mWriter.write(": " + f);
            this.mWriter.write(",\n");
        }

        void writeVariable(String s, int v) throws IOException {
            if(v != -1 && v != 0) {
                this.mWriter.write("       " + s);
                this.mWriter.write(":");
                this.mWriter.write(", " + v);
                this.mWriter.write("\n");
            }
        }

        void writeVariable(String s, String s1) throws IOException {
            if(s1 == null) {
                return;
            }
            this.mWriter.write("       " + s);
            this.mWriter.write(":");
            this.mWriter.write(", " + s1);
            this.mWriter.write("\n");
        }

        void writeVariable(String s, boolean z) throws IOException {
            if(!z) {
                return;
            }
            this.mWriter.write("       " + s);
            this.mWriter.write(": " + true);
            this.mWriter.write(",\n");
        }

        void writeVariable(String s, boolean z, boolean z1) throws IOException {
            if(z == z1) {
                return;
            }
            this.mWriter.write("       " + s);
            this.mWriter.write(": " + z);
            this.mWriter.write(",\n");
        }

        void writeVariable(String s, int[] arr_v) throws IOException {
            if(arr_v == null) {
                return;
            }
            this.mWriter.write("       " + s);
            this.mWriter.write(": ");
            for(int v = 0; v < arr_v.length; ++v) {
                this.mWriter.write((v == 0 ? "[" : ", ") + this.getName(arr_v[v]));
            }
            this.mWriter.write("],\n");
        }
    }

    class WriteXmlEngine {
        private static final String SPACE = "\n       ";
        final String mBASELINE;
        final String mBOTTOM;
        Context mContext;
        final String mEND;
        int mFlags;
        HashMap mIdMap;
        final String mLEFT;
        ConstraintLayout mLayout;
        final String mRIGHT;
        final String mSTART;
        final String mTOP;
        int mUnknownCount;
        Writer mWriter;

        WriteXmlEngine(Writer writer0, ConstraintLayout constraintLayout0, int v) throws IOException {
            this.mUnknownCount = 0;
            this.mLEFT = "\'left\'";
            this.mRIGHT = "\'right\'";
            this.mBASELINE = "\'baseline\'";
            this.mBOTTOM = "\'bottom\'";
            this.mTOP = "\'top\'";
            this.mSTART = "\'start\'";
            this.mEND = "\'end\'";
            this.mIdMap = new HashMap();
            this.mWriter = writer0;
            this.mLayout = constraintLayout0;
            this.mContext = constraintLayout0.getContext();
            this.mFlags = v;
        }

        String getName(int v) {
            if(this.mIdMap.containsKey(v)) {
                return "@+id/" + ((String)this.mIdMap.get(v)) + "";
            }
            if(v == 0) {
                return "parent";
            }
            String s = this.lookup(v);
            this.mIdMap.put(v, s);
            return "@+id/" + s + "";
        }

        String lookup(int v) {
            try {
                if(v != -1) {
                    return this.mContext.getResources().getResourceEntryName(v);
                }
                int v1 = this.mUnknownCount + 1;
                this.mUnknownCount = v1;
                return "unknown" + v1;
            }
            catch(Exception unused_ex) {
                int v2 = this.mUnknownCount + 1;
                this.mUnknownCount = v2;
                return "unknown" + v2;
            }
        }

        private void writeBaseDimension(String s, int v, int v1) throws IOException {
            if(v != v1) {
                switch(v) {
                    case -2: {
                        this.mWriter.write("\n       " + s + "=\"wrap_content\"");
                        return;
                    }
                    case -1: {
                        this.mWriter.write("\n       " + s + "=\"match_parent\"");
                        return;
                    }
                    default: {
                        this.mWriter.write("\n       " + s + "=\"" + v + "dp\"");
                        break;
                    }
                }
            }
        }

        private void writeBoolen(String s, boolean z, boolean z1) throws IOException {
            if(z != z1) {
                this.mWriter.write("\n       " + s + "=\"" + z + "dp\"");
            }
        }

        void writeCircle(int v, float f, int v1) throws IOException {
            if(v == -1) {
                return;
            }
            this.mWriter.write("circle");
            this.mWriter.write(":[");
            this.mWriter.write(this.getName(v));
            this.mWriter.write(", " + f);
            this.mWriter.write(v1 + "]");
        }

        void writeConstraint(String s, int v, String s1, int v1, int v2) throws IOException {
            if(v == -1) {
                return;
            }
            this.mWriter.write("\n       " + s);
            this.mWriter.write(":[");
            this.mWriter.write(this.getName(v));
            this.mWriter.write(" , ");
            this.mWriter.write(s1);
            if(v1 != 0) {
                this.mWriter.write(" , " + v1);
            }
            this.mWriter.write("],\n");
        }

        private void writeDimension(String s, int v, int v1) throws IOException {
            if(v != v1) {
                this.mWriter.write("\n       " + s + "=\"" + v + "dp\"");
            }
        }

        private void writeEnum(String s, int v, String[] arr_s, int v1) throws IOException {
            if(v != v1) {
                this.mWriter.write("\n       " + s + "=\"" + arr_s[v] + "\"");
            }
        }

        void writeLayout() throws IOException {
            this.mWriter.write("\n<ConstraintSet>\n");
            for(Object object0: ConstraintSet.this.mConstraints.keySet()) {
                Constraint constraintSet$Constraint0 = (Constraint)ConstraintSet.this.mConstraints.get(((Integer)object0));
                String s = this.getName(((int)(((Integer)object0))));
                this.mWriter.write("  <Constraint");
                this.mWriter.write("\n       android:id=\"" + s + "\"");
                Layout constraintSet$Layout0 = constraintSet$Constraint0.layout;
                this.writeBaseDimension("android:layout_width", constraintSet$Layout0.mWidth, -5);
                this.writeBaseDimension("android:layout_height", constraintSet$Layout0.mHeight, -5);
                this.writeVariable("app:layout_constraintGuide_begin", ((float)constraintSet$Layout0.guideBegin), -1.0f);
                this.writeVariable("app:layout_constraintGuide_end", ((float)constraintSet$Layout0.guideEnd), -1.0f);
                this.writeVariable("app:layout_constraintGuide_percent", constraintSet$Layout0.guidePercent, -1.0f);
                this.writeVariable("app:layout_constraintHorizontal_bias", constraintSet$Layout0.horizontalBias, 0.5f);
                this.writeVariable("app:layout_constraintVertical_bias", constraintSet$Layout0.verticalBias, 0.5f);
                this.writeVariable("app:layout_constraintDimensionRatio", constraintSet$Layout0.dimensionRatio, null);
                this.writeXmlConstraint("app:layout_constraintCircle", constraintSet$Layout0.circleConstraint);
                this.writeVariable("app:layout_constraintCircleRadius", ((float)constraintSet$Layout0.circleRadius), 0.0f);
                this.writeVariable("app:layout_constraintCircleAngle", constraintSet$Layout0.circleAngle, 0.0f);
                this.writeVariable("android:orientation", ((float)constraintSet$Layout0.orientation), -1.0f);
                this.writeVariable("app:layout_constraintVertical_weight", constraintSet$Layout0.verticalWeight, -1.0f);
                this.writeVariable("app:layout_constraintHorizontal_weight", constraintSet$Layout0.horizontalWeight, -1.0f);
                this.writeVariable("app:layout_constraintHorizontal_chainStyle", ((float)constraintSet$Layout0.horizontalChainStyle), 0.0f);
                this.writeVariable("app:layout_constraintVertical_chainStyle", ((float)constraintSet$Layout0.verticalChainStyle), 0.0f);
                this.writeVariable("app:barrierDirection", ((float)constraintSet$Layout0.mBarrierDirection), -1.0f);
                this.writeVariable("app:barrierMargin", ((float)constraintSet$Layout0.mBarrierMargin), 0.0f);
                this.writeDimension("app:layout_marginLeft", constraintSet$Layout0.leftMargin, 0);
                this.writeDimension("app:layout_goneMarginLeft", constraintSet$Layout0.goneLeftMargin, 0x80000000);
                this.writeDimension("app:layout_marginRight", constraintSet$Layout0.rightMargin, 0);
                this.writeDimension("app:layout_goneMarginRight", constraintSet$Layout0.goneRightMargin, 0x80000000);
                this.writeDimension("app:layout_marginStart", constraintSet$Layout0.startMargin, 0);
                this.writeDimension("app:layout_goneMarginStart", constraintSet$Layout0.goneStartMargin, 0x80000000);
                this.writeDimension("app:layout_marginEnd", constraintSet$Layout0.endMargin, 0);
                this.writeDimension("app:layout_goneMarginEnd", constraintSet$Layout0.goneEndMargin, 0x80000000);
                this.writeDimension("app:layout_marginTop", constraintSet$Layout0.topMargin, 0);
                this.writeDimension("app:layout_goneMarginTop", constraintSet$Layout0.goneTopMargin, 0x80000000);
                this.writeDimension("app:layout_marginBottom", constraintSet$Layout0.bottomMargin, 0);
                this.writeDimension("app:layout_goneMarginBottom", constraintSet$Layout0.goneBottomMargin, 0x80000000);
                this.writeDimension("app:goneBaselineMargin", constraintSet$Layout0.goneBaselineMargin, 0x80000000);
                this.writeDimension("app:baselineMargin", constraintSet$Layout0.baselineMargin, 0);
                this.writeBoolen("app:layout_constrainedWidth", constraintSet$Layout0.constrainedWidth, false);
                this.writeBoolen("app:layout_constrainedHeight", constraintSet$Layout0.constrainedHeight, false);
                this.writeBoolen("app:barrierAllowsGoneWidgets", constraintSet$Layout0.mBarrierAllowsGoneWidgets, true);
                this.writeVariable("app:layout_wrapBehaviorInParent", ((float)constraintSet$Layout0.mWrapBehavior), 0.0f);
                this.writeXmlConstraint("app:baselineToBaseline", constraintSet$Layout0.baselineToBaseline);
                this.writeXmlConstraint("app:baselineToBottom", constraintSet$Layout0.baselineToBottom);
                this.writeXmlConstraint("app:baselineToTop", constraintSet$Layout0.baselineToTop);
                this.writeXmlConstraint("app:layout_constraintBottom_toBottomOf", constraintSet$Layout0.bottomToBottom);
                this.writeXmlConstraint("app:layout_constraintBottom_toTopOf", constraintSet$Layout0.bottomToTop);
                this.writeXmlConstraint("app:layout_constraintEnd_toEndOf", constraintSet$Layout0.endToEnd);
                this.writeXmlConstraint("app:layout_constraintEnd_toStartOf", constraintSet$Layout0.endToStart);
                this.writeXmlConstraint("app:layout_constraintLeft_toLeftOf", constraintSet$Layout0.leftToLeft);
                this.writeXmlConstraint("app:layout_constraintLeft_toRightOf", constraintSet$Layout0.leftToRight);
                this.writeXmlConstraint("app:layout_constraintRight_toLeftOf", constraintSet$Layout0.rightToLeft);
                this.writeXmlConstraint("app:layout_constraintRight_toRightOf", constraintSet$Layout0.rightToRight);
                this.writeXmlConstraint("app:layout_constraintStart_toEndOf", constraintSet$Layout0.startToEnd);
                this.writeXmlConstraint("app:layout_constraintStart_toStartOf", constraintSet$Layout0.startToStart);
                this.writeXmlConstraint("app:layout_constraintTop_toBottomOf", constraintSet$Layout0.topToBottom);
                this.writeXmlConstraint("app:layout_constraintTop_toTopOf", constraintSet$Layout0.topToTop);
                String[] arr_s = {"spread", "wrap", "percent"};
                this.writeEnum("app:layout_constraintHeight_default", constraintSet$Layout0.heightDefault, arr_s, 0);
                this.writeVariable("app:layout_constraintHeight_percent", constraintSet$Layout0.heightPercent, 1.0f);
                this.writeDimension("app:layout_constraintHeight_min", constraintSet$Layout0.heightMin, 0);
                this.writeDimension("app:layout_constraintHeight_max", constraintSet$Layout0.heightMax, 0);
                this.writeBoolen("android:layout_constrainedHeight", constraintSet$Layout0.constrainedHeight, false);
                this.writeEnum("app:layout_constraintWidth_default", constraintSet$Layout0.widthDefault, arr_s, 0);
                this.writeVariable("app:layout_constraintWidth_percent", constraintSet$Layout0.widthPercent, 1.0f);
                this.writeDimension("app:layout_constraintWidth_min", constraintSet$Layout0.widthMin, 0);
                this.writeDimension("app:layout_constraintWidth_max", constraintSet$Layout0.widthMax, 0);
                this.writeBoolen("android:layout_constrainedWidth", constraintSet$Layout0.constrainedWidth, false);
                this.writeVariable("app:layout_constraintVertical_weight", constraintSet$Layout0.verticalWeight, -1.0f);
                this.writeVariable("app:layout_constraintHorizontal_weight", constraintSet$Layout0.horizontalWeight, -1.0f);
                this.writeVariable("app:layout_constraintHorizontal_chainStyle", constraintSet$Layout0.horizontalChainStyle);
                this.writeVariable("app:layout_constraintVertical_chainStyle", constraintSet$Layout0.verticalChainStyle);
                this.writeEnum("app:barrierDirection", constraintSet$Layout0.mBarrierDirection, new String[]{"left", "right", "top", "bottom", "start", "end"}, -1);
                this.writeVariable("app:layout_constraintTag", constraintSet$Layout0.mConstraintTag, null);
                if(constraintSet$Layout0.mReferenceIds != null) {
                    this.writeVariable("\'ReferenceIds\'", constraintSet$Layout0.mReferenceIds);
                }
                this.mWriter.write(" />\n");
            }
            this.mWriter.write("</ConstraintSet>\n");
        }

        void writeVariable(String s, float f, float f1) throws IOException {
            if(f == f1) {
                return;
            }
            this.mWriter.write("\n       " + s);
            this.mWriter.write("=\"" + f + "\"");
        }

        void writeVariable(String s, int v) throws IOException {
            if(v != -1 && v != 0) {
                this.mWriter.write("\n       " + s + "=\"" + v + "\"\n");
            }
        }

        void writeVariable(String s, String s1) throws IOException {
            if(s1 == null) {
                return;
            }
            this.mWriter.write(s);
            this.mWriter.write(":");
            this.mWriter.write(", " + s1);
            this.mWriter.write("\n");
        }

        void writeVariable(String s, String s1, String s2) throws IOException {
            if(s1 != null && !s1.equals(s2)) {
                this.mWriter.write("\n       " + s);
                this.mWriter.write("=\"" + s1 + "\"");
            }
        }

        void writeVariable(String s, int[] arr_v) throws IOException {
            if(arr_v == null) {
                return;
            }
            this.mWriter.write("\n       " + s);
            this.mWriter.write(":");
            for(int v = 0; v < arr_v.length; ++v) {
                this.mWriter.write((v == 0 ? "[" : ", ") + this.getName(arr_v[v]));
            }
            this.mWriter.write("],\n");
        }

        void writeXmlConstraint(String s, int v) throws IOException {
            if(v == -1) {
                return;
            }
            this.mWriter.write("\n       " + s);
            this.mWriter.write("=\"" + this.getName(v) + "\"");
        }
    }

    private static final int ALPHA = 43;
    private static final int ANIMATE_CIRCLE_ANGLE_TO = 82;
    private static final int ANIMATE_RELATIVE_TO = 0x40;
    private static final int BARRIER_ALLOWS_GONE_WIDGETS = 75;
    private static final int BARRIER_DIRECTION = 72;
    private static final int BARRIER_MARGIN = 73;
    private static final int BARRIER_TYPE = 1;
    public static final int BASELINE = 5;
    private static final int BASELINE_MARGIN = 93;
    private static final int BASELINE_TO_BASELINE = 1;
    private static final int BASELINE_TO_BOTTOM = 92;
    private static final int BASELINE_TO_TOP = 91;
    public static final int BOTTOM = 4;
    private static final int BOTTOM_MARGIN = 2;
    private static final int BOTTOM_TO_BOTTOM = 3;
    private static final int BOTTOM_TO_TOP = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    private static final int CHAIN_USE_RTL = 71;
    private static final int CIRCLE = 61;
    private static final int CIRCLE_ANGLE = 0x3F;
    private static final int CIRCLE_RADIUS = 62;
    public static final int CIRCLE_REFERENCE = 8;
    private static final int CONSTRAINED_HEIGHT = 81;
    private static final int CONSTRAINED_WIDTH = 80;
    private static final int CONSTRAINT_REFERENCED_IDS = 74;
    private static final int CONSTRAINT_TAG = 77;
    private static final boolean DEBUG = false;
    private static final int DIMENSION_RATIO = 5;
    private static final int DRAW_PATH = 66;
    private static final int EDITOR_ABSOLUTE_X = 6;
    private static final int EDITOR_ABSOLUTE_Y = 7;
    private static final int ELEVATION = 44;
    public static final int END = 7;
    private static final int END_MARGIN = 8;
    private static final int END_TO_END = 9;
    private static final int END_TO_START = 10;
    private static final String ERROR_MESSAGE = "XML parser error must be within a Constraint ";
    public static final int GONE = 8;
    private static final int GONE_BASELINE_MARGIN = 94;
    private static final int GONE_BOTTOM_MARGIN = 11;
    private static final int GONE_END_MARGIN = 12;
    private static final int GONE_LEFT_MARGIN = 13;
    private static final int GONE_RIGHT_MARGIN = 14;
    private static final int GONE_START_MARGIN = 15;
    private static final int GONE_TOP_MARGIN = 16;
    private static final int GUIDELINE_USE_RTL = 99;
    private static final int GUIDE_BEGIN = 17;
    private static final int GUIDE_END = 18;
    private static final int GUIDE_PERCENT = 19;
    private static final int HEIGHT_DEFAULT = 55;
    private static final int HEIGHT_MAX = 57;
    private static final int HEIGHT_MIN = 59;
    private static final int HEIGHT_PERCENT = 70;
    public static final int HORIZONTAL = 0;
    private static final int HORIZONTAL_BIAS = 20;
    public static final int HORIZONTAL_GUIDELINE = 0;
    private static final int HORIZONTAL_STYLE = 41;
    private static final int HORIZONTAL_WEIGHT = 39;
    private static final int INTERNAL_MATCH_CONSTRAINT = -3;
    private static final int INTERNAL_MATCH_PARENT = -1;
    private static final int INTERNAL_WRAP_CONTENT = -2;
    private static final int INTERNAL_WRAP_CONTENT_CONSTRAINED = -4;
    public static final int INVISIBLE = 4;
    private static final String KEY_PERCENT_PARENT = "parent";
    private static final String KEY_RATIO = "ratio";
    private static final String KEY_WEIGHT = "weight";
    private static final int LAYOUT_CONSTRAINT_HEIGHT = 0x60;
    private static final int LAYOUT_CONSTRAINT_WIDTH = 0x5F;
    private static final int LAYOUT_HEIGHT = 21;
    private static final int LAYOUT_VISIBILITY = 22;
    private static final int LAYOUT_WIDTH = 23;
    private static final int LAYOUT_WRAP_BEHAVIOR = 97;
    public static final int LEFT = 1;
    private static final int LEFT_MARGIN = 24;
    private static final int LEFT_TO_LEFT = 25;
    private static final int LEFT_TO_RIGHT = 26;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    private static final int MOTION_STAGGER = 0x4F;
    private static final int MOTION_TARGET = 98;
    private static final int ORIENTATION = 27;
    public static final int PARENT_ID = 0;
    private static final int PATH_MOTION_ARC = 76;
    private static final int PROGRESS = 68;
    private static final int QUANTIZE_MOTION_INTERPOLATOR = 86;
    private static final int QUANTIZE_MOTION_INTERPOLATOR_ID = 89;
    private static final int QUANTIZE_MOTION_INTERPOLATOR_STR = 90;
    private static final int QUANTIZE_MOTION_INTERPOLATOR_TYPE = 88;
    private static final int QUANTIZE_MOTION_PHASE = 85;
    private static final int QUANTIZE_MOTION_STEPS = 84;
    public static final int RIGHT = 2;
    private static final int RIGHT_MARGIN = 28;
    private static final int RIGHT_TO_LEFT = 29;
    private static final int RIGHT_TO_RIGHT = 30;
    public static final int ROTATE_LEFT_OF_PORTRATE = 4;
    public static final int ROTATE_NONE = 0;
    public static final int ROTATE_PORTRATE_OF_LEFT = 2;
    public static final int ROTATE_PORTRATE_OF_RIGHT = 1;
    public static final int ROTATE_RIGHT_OF_PORTRATE = 3;
    private static final int ROTATION = 60;
    private static final int ROTATION_X = 45;
    private static final int ROTATION_Y = 46;
    private static final int SCALE_X = 0x2F;
    private static final int SCALE_Y = 0x30;
    public static final int START = 6;
    private static final int START_MARGIN = 0x1F;
    private static final int START_TO_END = 0x20;
    private static final int START_TO_START = 33;
    private static final String TAG = "ConstraintSet";
    public static final int TOP = 3;
    private static final int TOP_MARGIN = 34;
    private static final int TOP_TO_BOTTOM = 35;
    private static final int TOP_TO_TOP = 36;
    private static final int TRANSFORM_PIVOT_TARGET = 83;
    private static final int TRANSFORM_PIVOT_X = 49;
    private static final int TRANSFORM_PIVOT_Y = 50;
    private static final int TRANSITION_EASING = 65;
    private static final int TRANSITION_PATH_ROTATE = 67;
    private static final int TRANSLATION_X = 51;
    private static final int TRANSLATION_Y = 52;
    private static final int TRANSLATION_Z = 53;
    public static final int UNSET = -1;
    private static final int UNUSED = 87;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_BIAS = 37;
    public static final int VERTICAL_GUIDELINE = 1;
    private static final int VERTICAL_STYLE = 42;
    private static final int VERTICAL_WEIGHT = 40;
    private static final int VIEW_ID = 38;
    private static final int[] VISIBILITY_FLAGS = null;
    private static final int VISIBILITY_MODE = 78;
    public static final int VISIBILITY_MODE_IGNORE = 1;
    public static final int VISIBILITY_MODE_NORMAL = 0;
    public static final int VISIBLE = 0;
    private static final int WIDTH_DEFAULT = 54;
    private static final int WIDTH_MAX = 56;
    private static final int WIDTH_MIN = 58;
    private static final int WIDTH_PERCENT = 69;
    public static final int WRAP_CONTENT = -2;
    public String derivedState;
    private HashMap mConstraints;
    private boolean mForceId;
    public String mIdString;
    private String[] mMatchLabels;
    public int mRotate;
    private HashMap mSavedAttributes;
    private boolean mValidate;
    private static SparseIntArray sMapToConstant;
    private static SparseIntArray sOverrideMapToConstant;

    static {
        ConstraintSet.VISIBILITY_FLAGS = new int[]{0, 4, 8};
        ConstraintSet.sMapToConstant = new SparseIntArray();
        ConstraintSet.sOverrideMapToConstant = new SparseIntArray();
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintRight_toRightOf, 30);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintTop_toTopOf, 36);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintBaseline_toTopOf, 91);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintBaseline_toBottomOf, 92);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_editor_absoluteX, 6);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_editor_absoluteY, 7);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintGuide_begin, 17);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintGuide_end, 18);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintGuide_percent, 19);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_guidelineUseRtl, 99);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_orientation, 27);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintStart_toEndOf, 0x20);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintStart_toStartOf, 33);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_goneMarginLeft, 13);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_goneMarginTop, 16);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_goneMarginRight, 14);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_goneMarginBottom, 11);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_goneMarginStart, 15);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_goneMarginEnd, 12);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintVertical_weight, 40);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintHorizontal_weight, 39);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintHorizontal_bias, 20);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintVertical_bias, 37);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintDimensionRatio, 5);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintLeft_creator, 87);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintTop_creator, 87);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintRight_creator, 87);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintBottom_creator, 87);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintBaseline_creator, 87);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_layout_marginLeft, 24);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_layout_marginRight, 28);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_layout_marginStart, 0x1F);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_layout_marginEnd, 8);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_layout_marginTop, 34);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_layout_marginBottom, 2);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_layout_width, 23);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_layout_height, 21);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintWidth, 0x5F);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintHeight, 0x60);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_visibility, 22);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_alpha, 43);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_elevation, 44);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_rotationX, 45);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_rotationY, 46);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_rotation, 60);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_scaleX, 0x2F);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_scaleY, 0x30);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_transformPivotX, 49);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_transformPivotY, 50);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_translationX, 51);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_translationY, 52);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_translationZ, 53);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintWidth_default, 54);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintHeight_default, 55);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintWidth_max, 56);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintHeight_max, 57);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintWidth_min, 58);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintHeight_min, 59);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintCircle, 61);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintCircleRadius, 62);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintCircleAngle, 0x3F);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_animateRelativeTo, 0x40);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_transitionEasing, 65);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_drawPath, 66);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_transitionPathRotate, 67);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_motionStagger, 0x4F);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_android_id, 38);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_motionProgress, 68);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintWidth_percent, 69);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintHeight_percent, 70);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_wrapBehaviorInParent, 97);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_chainUseRtl, 71);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_barrierDirection, 72);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_barrierMargin, 73);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_constraint_referenced_ids, 74);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_barrierAllowsGoneWidgets, 75);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_pathMotionArc, 76);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constraintTag, 77);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_visibilityMode, 78);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constrainedWidth, 80);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_layout_constrainedHeight, 81);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_polarRelativeTo, 82);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_transformPivotTarget, 83);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_quantizeMotionSteps, 84);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_quantizeMotionPhase, 85);
        ConstraintSet.sMapToConstant.append(styleable.Constraint_quantizeMotionInterpolator, 86);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_editor_absoluteY, 6);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_editor_absoluteY, 7);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_orientation, 27);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_goneMarginLeft, 13);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_goneMarginTop, 16);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_goneMarginRight, 14);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_goneMarginBottom, 11);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_goneMarginStart, 15);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_goneMarginEnd, 12);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintVertical_weight, 40);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintHorizontal_weight, 39);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintHorizontal_chainStyle, 41);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintVertical_chainStyle, 42);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintHorizontal_bias, 20);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintVertical_bias, 37);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintDimensionRatio, 5);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintLeft_creator, 87);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintTop_creator, 87);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintRight_creator, 87);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintBottom_creator, 87);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintBaseline_creator, 87);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_layout_marginLeft, 24);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_layout_marginRight, 28);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_layout_marginStart, 0x1F);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_layout_marginEnd, 8);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_layout_marginTop, 34);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_layout_marginBottom, 2);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_layout_width, 23);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_layout_height, 21);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintWidth, 0x5F);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintHeight, 0x60);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_visibility, 22);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_alpha, 43);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_elevation, 44);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_rotationX, 45);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_rotationY, 46);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_rotation, 60);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_scaleX, 0x2F);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_scaleY, 0x30);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_transformPivotX, 49);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_transformPivotY, 50);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_translationX, 51);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_translationY, 52);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_translationZ, 53);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintWidth_default, 54);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintHeight_default, 55);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintWidth_max, 56);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintHeight_max, 57);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintWidth_min, 58);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintHeight_min, 59);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintCircleRadius, 62);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintCircleAngle, 0x3F);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_animateRelativeTo, 0x40);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_transitionEasing, 65);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_drawPath, 66);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_transitionPathRotate, 67);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_motionStagger, 0x4F);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_android_id, 38);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_motionTarget, 98);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_motionProgress, 68);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintWidth_percent, 69);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintHeight_percent, 70);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_chainUseRtl, 71);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_barrierDirection, 72);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_barrierMargin, 73);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_constraint_referenced_ids, 74);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_barrierAllowsGoneWidgets, 75);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_pathMotionArc, 76);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constraintTag, 77);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_visibilityMode, 78);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constrainedWidth, 80);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_constrainedHeight, 81);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_polarRelativeTo, 82);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_transformPivotTarget, 83);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_quantizeMotionSteps, 84);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_quantizeMotionPhase, 85);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_quantizeMotionInterpolator, 86);
        ConstraintSet.sOverrideMapToConstant.append(styleable.ConstraintOverride_layout_wrapBehaviorInParent, 97);
    }

    public ConstraintSet() {
        this.derivedState = "";
        this.mMatchLabels = new String[0];
        this.mRotate = 0;
        this.mSavedAttributes = new HashMap();
        this.mForceId = true;
        this.mConstraints = new HashMap();
    }

    private void addAttributes(AttributeType constraintAttribute$AttributeType0, String[] arr_s) {
        for(int v = 0; v < arr_s.length; ++v) {
            if(this.mSavedAttributes.containsKey(arr_s[v])) {
                ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)this.mSavedAttributes.get(arr_s[v]);
                if(constraintAttribute0 != null && constraintAttribute0.getType() != constraintAttribute$AttributeType0) {
                    throw new IllegalArgumentException("ConstraintAttribute is already a " + constraintAttribute0.getType().name());
                }
            }
            else {
                ConstraintAttribute constraintAttribute1 = new ConstraintAttribute(arr_s[v], constraintAttribute$AttributeType0);
                this.mSavedAttributes.put(arr_s[v], constraintAttribute1);
            }
        }
    }

    public void addColorAttributes(String[] arr_s) {
        this.addAttributes(AttributeType.COLOR_TYPE, arr_s);
    }

    public void addFloatAttributes(String[] arr_s) {
        this.addAttributes(AttributeType.FLOAT_TYPE, arr_s);
    }

    public void addIntAttributes(String[] arr_s) {
        this.addAttributes(AttributeType.INT_TYPE, arr_s);
    }

    public void addStringAttributes(String[] arr_s) {
        this.addAttributes(AttributeType.STRING_TYPE, arr_s);
    }

    public void addToHorizontalChain(int v, int v1, int v2) {
        this.connect(v, 1, v1, (v1 == 0 ? 1 : 2), 0);
        this.connect(v, 2, v2, (v2 == 0 ? 2 : 1), 0);
        if(v1 != 0) {
            this.connect(v1, 2, v, 1, 0);
        }
        if(v2 != 0) {
            this.connect(v2, 1, v, 2, 0);
        }
    }

    public void addToHorizontalChainRTL(int v, int v1, int v2) {
        this.connect(v, 6, v1, (v1 == 0 ? 6 : 7), 0);
        this.connect(v, 7, v2, (v2 == 0 ? 7 : 6), 0);
        if(v1 != 0) {
            this.connect(v1, 7, v, 6, 0);
        }
        if(v2 != 0) {
            this.connect(v2, 6, v, 7, 0);
        }
    }

    public void addToVerticalChain(int v, int v1, int v2) {
        this.connect(v, 3, v1, (v1 == 0 ? 3 : 4), 0);
        this.connect(v, 4, v2, (v2 == 0 ? 4 : 3), 0);
        if(v1 != 0) {
            this.connect(v1, 4, v, 3, 0);
        }
        if(v2 != 0) {
            this.connect(v2, 3, v, 4, 0);
        }
    }

    public void applyCustomAttributes(ConstraintLayout constraintLayout0) {
        int v = constraintLayout0.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = constraintLayout0.getChildAt(v1);
            int v2 = view0.getId();
            if(this.mConstraints.containsKey(v2)) {
                if(this.mForceId && v2 == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if(this.mConstraints.containsKey(v2)) {
                    Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v2);
                    if(constraintSet$Constraint0 != null) {
                        ConstraintAttribute.setAttributes(view0, constraintSet$Constraint0.mCustomConstraints);
                    }
                }
            }
            else {
                Log.w("ConstraintSet", "id unknown " + Debug.getName(view0));
            }
        }
    }

    public void applyDeltaFrom(ConstraintSet constraintSet0) {
        for(Object object0: constraintSet0.mConstraints.values()) {
            Constraint constraintSet$Constraint0 = (Constraint)object0;
            if(constraintSet$Constraint0.mDelta == null) {
            }
            else if(constraintSet$Constraint0.mTargetString == null) {
                Constraint constraintSet$Constraint1 = this.getConstraint(constraintSet$Constraint0.mViewId);
                constraintSet$Constraint0.mDelta.applyDelta(constraintSet$Constraint1);
            }
            else {
                for(Object object1: this.mConstraints.keySet()) {
                    Constraint constraintSet$Constraint2 = this.getConstraint(((int)(((Integer)object1))));
                    if(constraintSet$Constraint2.layout.mConstraintTag != null && constraintSet$Constraint0.mTargetString.matches(constraintSet$Constraint2.layout.mConstraintTag)) {
                        constraintSet$Constraint0.mDelta.applyDelta(constraintSet$Constraint2);
                        HashMap hashMap0 = (HashMap)constraintSet$Constraint0.mCustomConstraints.clone();
                        constraintSet$Constraint2.mCustomConstraints.putAll(hashMap0);
                    }
                }
            }
        }
    }

    public void applyTo(ConstraintLayout constraintLayout0) {
        this.applyToInternal(constraintLayout0, true);
        constraintLayout0.setConstraintSet(null);
        constraintLayout0.requestLayout();
    }

    public void applyToHelper(ConstraintHelper constraintHelper0, ConstraintWidget constraintWidget0, LayoutParams constraintLayout$LayoutParams0, SparseArray sparseArray0) {
        int v = constraintHelper0.getId();
        if(this.mConstraints.containsKey(v)) {
            Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v);
            if(constraintSet$Constraint0 != null && constraintWidget0 instanceof HelperWidget) {
                constraintHelper0.loadParameters(constraintSet$Constraint0, ((HelperWidget)constraintWidget0), constraintLayout$LayoutParams0, sparseArray0);
            }
        }
    }

    void applyToInternal(ConstraintLayout constraintLayout0, boolean z) {
        int v = constraintLayout0.getChildCount();
        HashSet hashSet0 = new HashSet(this.mConstraints.keySet());
        for(int v2 = 0; v2 < v; ++v2) {
            View view0 = constraintLayout0.getChildAt(v2);
            int v3 = view0.getId();
            if(this.mConstraints.containsKey(v3)) {
                if(this.mForceId && v3 == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if(v3 != -1) {
                    if(this.mConstraints.containsKey(v3)) {
                        hashSet0.remove(v3);
                        Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v3);
                        if(constraintSet$Constraint0 != null) {
                            if(view0 instanceof Barrier) {
                                constraintSet$Constraint0.layout.mHelperType = 1;
                                ((Barrier)view0).setId(v3);
                                ((Barrier)view0).setType(constraintSet$Constraint0.layout.mBarrierDirection);
                                ((Barrier)view0).setMargin(constraintSet$Constraint0.layout.mBarrierMargin);
                                ((Barrier)view0).setAllowsGoneWidget(constraintSet$Constraint0.layout.mBarrierAllowsGoneWidgets);
                                if(constraintSet$Constraint0.layout.mReferenceIds != null) {
                                    ((Barrier)view0).setReferencedIds(constraintSet$Constraint0.layout.mReferenceIds);
                                }
                                else if(constraintSet$Constraint0.layout.mReferenceIdString != null) {
                                    constraintSet$Constraint0.layout.mReferenceIds = this.convertReferenceString(((Barrier)view0), constraintSet$Constraint0.layout.mReferenceIdString);
                                    ((Barrier)view0).setReferencedIds(constraintSet$Constraint0.layout.mReferenceIds);
                                }
                            }
                            LayoutParams constraintLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                            constraintLayout$LayoutParams0.validate();
                            constraintSet$Constraint0.applyTo(constraintLayout$LayoutParams0);
                            if(z) {
                                ConstraintAttribute.setAttributes(view0, constraintSet$Constraint0.mCustomConstraints);
                            }
                            view0.setLayoutParams(constraintLayout$LayoutParams0);
                            if(constraintSet$Constraint0.propertySet.mVisibilityMode == 0) {
                                view0.setVisibility(constraintSet$Constraint0.propertySet.visibility);
                            }
                            view0.setAlpha(constraintSet$Constraint0.propertySet.alpha);
                            view0.setRotation(constraintSet$Constraint0.transform.rotation);
                            view0.setRotationX(constraintSet$Constraint0.transform.rotationX);
                            view0.setRotationY(constraintSet$Constraint0.transform.rotationY);
                            view0.setScaleX(constraintSet$Constraint0.transform.scaleX);
                            view0.setScaleY(constraintSet$Constraint0.transform.scaleY);
                            if(constraintSet$Constraint0.transform.transformPivotTarget == -1) {
                                if(!Float.isNaN(constraintSet$Constraint0.transform.transformPivotX)) {
                                    view0.setPivotX(constraintSet$Constraint0.transform.transformPivotX);
                                }
                                if(!Float.isNaN(constraintSet$Constraint0.transform.transformPivotY)) {
                                    view0.setPivotY(constraintSet$Constraint0.transform.transformPivotY);
                                }
                            }
                            else {
                                View view1 = ((View)view0.getParent()).findViewById(constraintSet$Constraint0.transform.transformPivotTarget);
                                if(view1 != null) {
                                    float f = ((float)(view1.getTop() + view1.getBottom())) / 2.0f;
                                    float f1 = ((float)(view1.getLeft() + view1.getRight())) / 2.0f;
                                    if(view0.getRight() - view0.getLeft() > 0 && view0.getBottom() - view0.getTop() > 0) {
                                        float f2 = f1 - ((float)view0.getLeft());
                                        float f3 = f - ((float)view0.getTop());
                                        view0.setPivotX(f2);
                                        view0.setPivotY(f3);
                                    }
                                }
                            }
                            view0.setTranslationX(constraintSet$Constraint0.transform.translationX);
                            view0.setTranslationY(constraintSet$Constraint0.transform.translationY);
                            view0.setTranslationZ(constraintSet$Constraint0.transform.translationZ);
                            if(constraintSet$Constraint0.transform.applyElevation) {
                                view0.setElevation(constraintSet$Constraint0.transform.elevation);
                            }
                        }
                    }
                    else {
                        Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + v3);
                    }
                }
            }
            else {
                Log.w("ConstraintSet", "id unknown " + Debug.getName(view0));
            }
        }
        for(Object object0: hashSet0) {
            Integer integer0 = (Integer)object0;
            Constraint constraintSet$Constraint1 = (Constraint)this.mConstraints.get(integer0);
            if(constraintSet$Constraint1 != null) {
                if(constraintSet$Constraint1.layout.mHelperType == 1) {
                    Barrier barrier0 = new Barrier(constraintLayout0.getContext());
                    barrier0.setId(((int)integer0));
                    if(constraintSet$Constraint1.layout.mReferenceIds != null) {
                        barrier0.setReferencedIds(constraintSet$Constraint1.layout.mReferenceIds);
                    }
                    else if(constraintSet$Constraint1.layout.mReferenceIdString != null) {
                        constraintSet$Constraint1.layout.mReferenceIds = this.convertReferenceString(barrier0, constraintSet$Constraint1.layout.mReferenceIdString);
                        barrier0.setReferencedIds(constraintSet$Constraint1.layout.mReferenceIds);
                    }
                    barrier0.setType(constraintSet$Constraint1.layout.mBarrierDirection);
                    barrier0.setMargin(constraintSet$Constraint1.layout.mBarrierMargin);
                    LayoutParams constraintLayout$LayoutParams1 = constraintLayout0.generateDefaultLayoutParams();
                    barrier0.validateParams();
                    constraintSet$Constraint1.applyTo(constraintLayout$LayoutParams1);
                    constraintLayout0.addView(barrier0, constraintLayout$LayoutParams1);
                }
                if(constraintSet$Constraint1.layout.mIsGuideline) {
                    Guideline guideline0 = new Guideline(constraintLayout0.getContext());
                    guideline0.setId(((int)integer0));
                    LayoutParams constraintLayout$LayoutParams2 = constraintLayout0.generateDefaultLayoutParams();
                    constraintSet$Constraint1.applyTo(constraintLayout$LayoutParams2);
                    constraintLayout0.addView(guideline0, constraintLayout$LayoutParams2);
                }
            }
        }
        for(int v1 = 0; v1 < v; ++v1) {
            View view2 = constraintLayout0.getChildAt(v1);
            if(view2 instanceof ConstraintHelper) {
                ((ConstraintHelper)view2).applyLayoutFeaturesInConstraintSet(constraintLayout0);
            }
        }
    }

    public void applyToLayoutParams(int v, LayoutParams constraintLayout$LayoutParams0) {
        if(this.mConstraints.containsKey(v)) {
            Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v);
            if(constraintSet$Constraint0 != null) {
                constraintSet$Constraint0.applyTo(constraintLayout$LayoutParams0);
            }
        }
    }

    public void applyToWithoutCustom(ConstraintLayout constraintLayout0) {
        this.applyToInternal(constraintLayout0, false);
        constraintLayout0.setConstraintSet(null);
    }

    public static Constraint buildDelta(Context context0, XmlPullParser xmlPullParser0) {
        AttributeSet attributeSet0 = Xml.asAttributeSet(xmlPullParser0);
        Constraint constraintSet$Constraint0 = new Constraint();
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ConstraintOverride);
        ConstraintSet.populateOverride(constraintSet$Constraint0, typedArray0);
        typedArray0.recycle();
        return constraintSet$Constraint0;
    }

    public void center(int v, int v1, int v2, int v3, int v4, int v5, int v6, float f) {
        if(v3 < 0 || v6 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if(f <= 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
        }
        switch(v2) {
            case 1: 
            case 2: {
                this.connect(v, 1, v1, v2, v3);
                this.connect(v, 2, v4, v5, v6);
                Constraint constraintSet$Constraint1 = (Constraint)this.mConstraints.get(v);
                if(constraintSet$Constraint1 != null) {
                    constraintSet$Constraint1.layout.horizontalBias = f;
                    return;
                }
                break;
            }
            case 6: 
            case 7: {
                this.connect(v, 6, v1, v2, v3);
                this.connect(v, 7, v4, v5, v6);
                Constraint constraintSet$Constraint2 = (Constraint)this.mConstraints.get(v);
                if(constraintSet$Constraint2 != null) {
                    constraintSet$Constraint2.layout.horizontalBias = f;
                    return;
                }
                break;
            }
            default: {
                this.connect(v, 3, v1, v2, v3);
                this.connect(v, 4, v4, v5, v6);
                Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v);
                if(constraintSet$Constraint0 != null) {
                    constraintSet$Constraint0.layout.verticalBias = f;
                }
            }
        }
    }

    public void centerHorizontally(int v, int v1) {
        if(v1 == 0) {
            this.center(v, 0, 1, 0, 0, 2, 0, 0.5f);
            return;
        }
        this.center(v, v1, 2, 0, v1, 1, 0, 0.5f);
    }

    public void centerHorizontally(int v, int v1, int v2, int v3, int v4, int v5, int v6, float f) {
        this.connect(v, 1, v1, v2, v3);
        this.connect(v, 2, v4, v5, v6);
        Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v);
        if(constraintSet$Constraint0 != null) {
            constraintSet$Constraint0.layout.horizontalBias = f;
        }
    }

    public void centerHorizontallyRtl(int v, int v1) {
        if(v1 == 0) {
            this.center(v, 0, 6, 0, 0, 7, 0, 0.5f);
            return;
        }
        this.center(v, v1, 7, 0, v1, 6, 0, 0.5f);
    }

    public void centerHorizontallyRtl(int v, int v1, int v2, int v3, int v4, int v5, int v6, float f) {
        this.connect(v, 6, v1, v2, v3);
        this.connect(v, 7, v4, v5, v6);
        Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v);
        if(constraintSet$Constraint0 != null) {
            constraintSet$Constraint0.layout.horizontalBias = f;
        }
    }

    public void centerVertically(int v, int v1) {
        if(v1 == 0) {
            this.center(v, 0, 3, 0, 0, 4, 0, 0.5f);
            return;
        }
        this.center(v, v1, 4, 0, v1, 3, 0, 0.5f);
    }

    public void centerVertically(int v, int v1, int v2, int v3, int v4, int v5, int v6, float f) {
        this.connect(v, 3, v1, v2, v3);
        this.connect(v, 4, v4, v5, v6);
        Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v);
        if(constraintSet$Constraint0 != null) {
            constraintSet$Constraint0.layout.verticalBias = f;
        }
    }

    public void clear(int v) {
        this.mConstraints.remove(v);
    }

    public void clear(int v, int v1) {
        if(this.mConstraints.containsKey(v)) {
            Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v);
            if(constraintSet$Constraint0 != null) {
                switch(v1) {
                    case 1: {
                        constraintSet$Constraint0.layout.leftToRight = -1;
                        constraintSet$Constraint0.layout.leftToLeft = -1;
                        constraintSet$Constraint0.layout.leftMargin = -1;
                        constraintSet$Constraint0.layout.goneLeftMargin = 0x80000000;
                        break;
                    }
                    case 2: {
                        constraintSet$Constraint0.layout.rightToRight = -1;
                        constraintSet$Constraint0.layout.rightToLeft = -1;
                        constraintSet$Constraint0.layout.rightMargin = -1;
                        constraintSet$Constraint0.layout.goneRightMargin = 0x80000000;
                        return;
                    }
                    case 3: {
                        constraintSet$Constraint0.layout.topToBottom = -1;
                        constraintSet$Constraint0.layout.topToTop = -1;
                        constraintSet$Constraint0.layout.topMargin = 0;
                        constraintSet$Constraint0.layout.goneTopMargin = 0x80000000;
                        return;
                    }
                    case 4: {
                        constraintSet$Constraint0.layout.bottomToTop = -1;
                        constraintSet$Constraint0.layout.bottomToBottom = -1;
                        constraintSet$Constraint0.layout.bottomMargin = 0;
                        constraintSet$Constraint0.layout.goneBottomMargin = 0x80000000;
                        return;
                    }
                    case 5: {
                        constraintSet$Constraint0.layout.baselineToBaseline = -1;
                        constraintSet$Constraint0.layout.baselineToTop = -1;
                        constraintSet$Constraint0.layout.baselineToBottom = -1;
                        constraintSet$Constraint0.layout.baselineMargin = 0;
                        constraintSet$Constraint0.layout.goneBaselineMargin = 0x80000000;
                        return;
                    }
                    case 6: {
                        constraintSet$Constraint0.layout.startToEnd = -1;
                        constraintSet$Constraint0.layout.startToStart = -1;
                        constraintSet$Constraint0.layout.startMargin = 0;
                        constraintSet$Constraint0.layout.goneStartMargin = 0x80000000;
                        return;
                    }
                    case 7: {
                        constraintSet$Constraint0.layout.endToStart = -1;
                        constraintSet$Constraint0.layout.endToEnd = -1;
                        constraintSet$Constraint0.layout.endMargin = 0;
                        constraintSet$Constraint0.layout.goneEndMargin = 0x80000000;
                        return;
                    }
                    case 8: {
                        constraintSet$Constraint0.layout.circleAngle = -1.0f;
                        constraintSet$Constraint0.layout.circleRadius = -1;
                        constraintSet$Constraint0.layout.circleConstraint = -1;
                        return;
                    }
                    default: {
                        throw new IllegalArgumentException("unknown constraint");
                    }
                }
            }
        }
    }

    public void clone(Context context0, int v) {
        this.clone(((ConstraintLayout)LayoutInflater.from(context0).inflate(v, null)));
    }

    public void clone(ConstraintLayout constraintLayout0) {
        int v = constraintLayout0.getChildCount();
        this.mConstraints.clear();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = constraintLayout0.getChildAt(v1);
            LayoutParams constraintLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            int v2 = view0.getId();
            if(this.mForceId && v2 == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if(!this.mConstraints.containsKey(v2)) {
                this.mConstraints.put(v2, new Constraint());
            }
            Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v2);
            if(constraintSet$Constraint0 != null) {
                constraintSet$Constraint0.mCustomConstraints = ConstraintAttribute.extractAttributes(this.mSavedAttributes, view0);
                constraintSet$Constraint0.fillFrom(v2, constraintLayout$LayoutParams0);
                constraintSet$Constraint0.propertySet.visibility = view0.getVisibility();
                constraintSet$Constraint0.propertySet.alpha = view0.getAlpha();
                constraintSet$Constraint0.transform.rotation = view0.getRotation();
                constraintSet$Constraint0.transform.rotationX = view0.getRotationX();
                constraintSet$Constraint0.transform.rotationY = view0.getRotationY();
                constraintSet$Constraint0.transform.scaleX = view0.getScaleX();
                constraintSet$Constraint0.transform.scaleY = view0.getScaleY();
                float f = view0.getPivotX();
                float f1 = view0.getPivotY();
                if(((double)f) != 0.0 || ((double)f1) != 0.0) {
                    constraintSet$Constraint0.transform.transformPivotX = f;
                    constraintSet$Constraint0.transform.transformPivotY = f1;
                }
                constraintSet$Constraint0.transform.translationX = view0.getTranslationX();
                constraintSet$Constraint0.transform.translationY = view0.getTranslationY();
                constraintSet$Constraint0.transform.translationZ = view0.getTranslationZ();
                if(constraintSet$Constraint0.transform.applyElevation) {
                    constraintSet$Constraint0.transform.elevation = view0.getElevation();
                }
                if(view0 instanceof Barrier) {
                    constraintSet$Constraint0.layout.mBarrierAllowsGoneWidgets = ((Barrier)view0).getAllowsGoneWidget();
                    constraintSet$Constraint0.layout.mReferenceIds = ((Barrier)view0).getReferencedIds();
                    constraintSet$Constraint0.layout.mBarrierDirection = ((Barrier)view0).getType();
                    constraintSet$Constraint0.layout.mBarrierMargin = ((Barrier)view0).getMargin();
                }
            }
        }
    }

    public void clone(ConstraintSet constraintSet0) {
        this.mConstraints.clear();
        for(Object object0: constraintSet0.mConstraints.keySet()) {
            Integer integer0 = (Integer)object0;
            Constraint constraintSet$Constraint0 = (Constraint)constraintSet0.mConstraints.get(integer0);
            if(constraintSet$Constraint0 != null) {
                this.mConstraints.put(integer0, constraintSet$Constraint0.clone());
            }
        }
    }

    public void clone(Constraints constraints0) {
        int v = constraints0.getChildCount();
        this.mConstraints.clear();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = constraints0.getChildAt(v1);
            androidx.constraintlayout.widget.Constraints.LayoutParams constraints$LayoutParams0 = (androidx.constraintlayout.widget.Constraints.LayoutParams)view0.getLayoutParams();
            int v2 = view0.getId();
            if(this.mForceId && v2 == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if(!this.mConstraints.containsKey(v2)) {
                this.mConstraints.put(v2, new Constraint());
            }
            Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v2);
            if(constraintSet$Constraint0 != null) {
                if(view0 instanceof ConstraintHelper) {
                    constraintSet$Constraint0.fillFromConstraints(((ConstraintHelper)view0), v2, constraints$LayoutParams0);
                }
                constraintSet$Constraint0.fillFromConstraints(v2, constraints$LayoutParams0);
            }
        }
    }

    public void connect(int v, int v1, int v2, int v3) {
        if(!this.mConstraints.containsKey(v)) {
            this.mConstraints.put(v, new Constraint());
        }
        Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v);
        if(constraintSet$Constraint0 == null) {
            return;
        }
        switch(v1) {
            case 1: {
                if(v3 == 1) {
                    constraintSet$Constraint0.layout.leftToLeft = v2;
                    constraintSet$Constraint0.layout.leftToRight = -1;
                    return;
                }
                if(v3 != 2) {
                    throw new IllegalArgumentException("left to " + this.sideToString(v3) + " undefined");
                }
                constraintSet$Constraint0.layout.leftToRight = v2;
                constraintSet$Constraint0.layout.leftToLeft = -1;
                return;
            }
            case 2: {
                if(v3 == 1) {
                    constraintSet$Constraint0.layout.rightToLeft = v2;
                    constraintSet$Constraint0.layout.rightToRight = -1;
                    return;
                }
                if(v3 != 2) {
                    throw new IllegalArgumentException("right to " + this.sideToString(v3) + " undefined");
                }
                constraintSet$Constraint0.layout.rightToRight = v2;
                constraintSet$Constraint0.layout.rightToLeft = -1;
                return;
            }
            case 3: {
                if(v3 == 3) {
                    constraintSet$Constraint0.layout.topToTop = v2;
                    constraintSet$Constraint0.layout.topToBottom = -1;
                    constraintSet$Constraint0.layout.baselineToBaseline = -1;
                    constraintSet$Constraint0.layout.baselineToTop = -1;
                    constraintSet$Constraint0.layout.baselineToBottom = -1;
                    return;
                }
                if(v3 != 4) {
                    throw new IllegalArgumentException("right to " + this.sideToString(v3) + " undefined");
                }
                constraintSet$Constraint0.layout.topToBottom = v2;
                constraintSet$Constraint0.layout.topToTop = -1;
                constraintSet$Constraint0.layout.baselineToBaseline = -1;
                constraintSet$Constraint0.layout.baselineToTop = -1;
                constraintSet$Constraint0.layout.baselineToBottom = -1;
                return;
            }
            case 4: {
                if(v3 == 4) {
                    constraintSet$Constraint0.layout.bottomToBottom = v2;
                    constraintSet$Constraint0.layout.bottomToTop = -1;
                    constraintSet$Constraint0.layout.baselineToBaseline = -1;
                    constraintSet$Constraint0.layout.baselineToTop = -1;
                    constraintSet$Constraint0.layout.baselineToBottom = -1;
                    return;
                }
                if(v3 != 3) {
                    throw new IllegalArgumentException("right to " + this.sideToString(v3) + " undefined");
                }
                constraintSet$Constraint0.layout.bottomToTop = v2;
                constraintSet$Constraint0.layout.bottomToBottom = -1;
                constraintSet$Constraint0.layout.baselineToBaseline = -1;
                constraintSet$Constraint0.layout.baselineToTop = -1;
                constraintSet$Constraint0.layout.baselineToBottom = -1;
                return;
            }
            case 5: {
                switch(v3) {
                    case 3: {
                        constraintSet$Constraint0.layout.baselineToTop = v2;
                        constraintSet$Constraint0.layout.bottomToBottom = -1;
                        constraintSet$Constraint0.layout.bottomToTop = -1;
                        constraintSet$Constraint0.layout.topToTop = -1;
                        constraintSet$Constraint0.layout.topToBottom = -1;
                        return;
                    }
                    case 4: {
                        constraintSet$Constraint0.layout.baselineToBottom = v2;
                        constraintSet$Constraint0.layout.bottomToBottom = -1;
                        constraintSet$Constraint0.layout.bottomToTop = -1;
                        constraintSet$Constraint0.layout.topToTop = -1;
                        constraintSet$Constraint0.layout.topToBottom = -1;
                        return;
                    }
                    case 5: {
                        constraintSet$Constraint0.layout.baselineToBaseline = v2;
                        constraintSet$Constraint0.layout.bottomToBottom = -1;
                        constraintSet$Constraint0.layout.bottomToTop = -1;
                        constraintSet$Constraint0.layout.topToTop = -1;
                        constraintSet$Constraint0.layout.topToBottom = -1;
                        return;
                    }
                    default: {
                        throw new IllegalArgumentException("right to " + this.sideToString(v3) + " undefined");
                    }
                }
            }
            case 6: {
                if(v3 == 6) {
                    constraintSet$Constraint0.layout.startToStart = v2;
                    constraintSet$Constraint0.layout.startToEnd = -1;
                    return;
                }
                if(v3 != 7) {
                    throw new IllegalArgumentException("right to " + this.sideToString(v3) + " undefined");
                }
                constraintSet$Constraint0.layout.startToEnd = v2;
                constraintSet$Constraint0.layout.startToStart = -1;
                return;
            }
            case 7: {
                if(v3 == 7) {
                    constraintSet$Constraint0.layout.endToEnd = v2;
                    constraintSet$Constraint0.layout.endToStart = -1;
                    return;
                }
                if(v3 != 6) {
                    throw new IllegalArgumentException("right to " + this.sideToString(v3) + " undefined");
                }
                constraintSet$Constraint0.layout.endToStart = v2;
                constraintSet$Constraint0.layout.endToEnd = -1;
                return;
            }
            default: {
                throw new IllegalArgumentException(this.sideToString(v1) + " to " + this.sideToString(v3) + " unknown");
            }
        }
    }

    public void connect(int v, int v1, int v2, int v3, int v4) {
        if(!this.mConstraints.containsKey(v)) {
            this.mConstraints.put(v, new Constraint());
        }
        Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v);
        if(constraintSet$Constraint0 == null) {
            return;
        }
        switch(v1) {
            case 1: {
                if(v3 == 1) {
                    constraintSet$Constraint0.layout.leftToLeft = v2;
                    constraintSet$Constraint0.layout.leftToRight = -1;
                }
                else if(v3 == 2) {
                    constraintSet$Constraint0.layout.leftToRight = v2;
                    constraintSet$Constraint0.layout.leftToLeft = -1;
                }
                else {
                    throw new IllegalArgumentException("Left to " + this.sideToString(v3) + " undefined");
                }
                constraintSet$Constraint0.layout.leftMargin = v4;
                return;
            }
            case 2: {
                if(v3 == 1) {
                    constraintSet$Constraint0.layout.rightToLeft = v2;
                    constraintSet$Constraint0.layout.rightToRight = -1;
                }
                else if(v3 == 2) {
                    constraintSet$Constraint0.layout.rightToRight = v2;
                    constraintSet$Constraint0.layout.rightToLeft = -1;
                }
                else {
                    throw new IllegalArgumentException("right to " + this.sideToString(v3) + " undefined");
                }
                constraintSet$Constraint0.layout.rightMargin = v4;
                return;
            }
            case 3: {
                if(v3 == 3) {
                    constraintSet$Constraint0.layout.topToTop = v2;
                    constraintSet$Constraint0.layout.topToBottom = -1;
                    constraintSet$Constraint0.layout.baselineToBaseline = -1;
                    constraintSet$Constraint0.layout.baselineToTop = -1;
                    constraintSet$Constraint0.layout.baselineToBottom = -1;
                }
                else if(v3 == 4) {
                    constraintSet$Constraint0.layout.topToBottom = v2;
                    constraintSet$Constraint0.layout.topToTop = -1;
                    constraintSet$Constraint0.layout.baselineToBaseline = -1;
                    constraintSet$Constraint0.layout.baselineToTop = -1;
                    constraintSet$Constraint0.layout.baselineToBottom = -1;
                }
                else {
                    throw new IllegalArgumentException("right to " + this.sideToString(v3) + " undefined");
                }
                constraintSet$Constraint0.layout.topMargin = v4;
                return;
            }
            case 4: {
                if(v3 == 4) {
                    constraintSet$Constraint0.layout.bottomToBottom = v2;
                    constraintSet$Constraint0.layout.bottomToTop = -1;
                    constraintSet$Constraint0.layout.baselineToBaseline = -1;
                    constraintSet$Constraint0.layout.baselineToTop = -1;
                    constraintSet$Constraint0.layout.baselineToBottom = -1;
                }
                else if(v3 == 3) {
                    constraintSet$Constraint0.layout.bottomToTop = v2;
                    constraintSet$Constraint0.layout.bottomToBottom = -1;
                    constraintSet$Constraint0.layout.baselineToBaseline = -1;
                    constraintSet$Constraint0.layout.baselineToTop = -1;
                    constraintSet$Constraint0.layout.baselineToBottom = -1;
                }
                else {
                    throw new IllegalArgumentException("right to " + this.sideToString(v3) + " undefined");
                }
                constraintSet$Constraint0.layout.bottomMargin = v4;
                return;
            }
            case 5: {
                switch(v3) {
                    case 3: {
                        constraintSet$Constraint0.layout.baselineToTop = v2;
                        constraintSet$Constraint0.layout.bottomToBottom = -1;
                        constraintSet$Constraint0.layout.bottomToTop = -1;
                        constraintSet$Constraint0.layout.topToTop = -1;
                        constraintSet$Constraint0.layout.topToBottom = -1;
                        return;
                    }
                    case 4: {
                        constraintSet$Constraint0.layout.baselineToBottom = v2;
                        constraintSet$Constraint0.layout.bottomToBottom = -1;
                        constraintSet$Constraint0.layout.bottomToTop = -1;
                        constraintSet$Constraint0.layout.topToTop = -1;
                        constraintSet$Constraint0.layout.topToBottom = -1;
                        return;
                    }
                    case 5: {
                        constraintSet$Constraint0.layout.baselineToBaseline = v2;
                        constraintSet$Constraint0.layout.bottomToBottom = -1;
                        constraintSet$Constraint0.layout.bottomToTop = -1;
                        constraintSet$Constraint0.layout.topToTop = -1;
                        constraintSet$Constraint0.layout.topToBottom = -1;
                        return;
                    }
                    default: {
                        throw new IllegalArgumentException("right to " + this.sideToString(v3) + " undefined");
                    }
                }
            }
            case 6: {
                if(v3 == 6) {
                    constraintSet$Constraint0.layout.startToStart = v2;
                    constraintSet$Constraint0.layout.startToEnd = -1;
                }
                else if(v3 == 7) {
                    constraintSet$Constraint0.layout.startToEnd = v2;
                    constraintSet$Constraint0.layout.startToStart = -1;
                }
                else {
                    throw new IllegalArgumentException("right to " + this.sideToString(v3) + " undefined");
                }
                constraintSet$Constraint0.layout.startMargin = v4;
                return;
            }
            case 7: {
                if(v3 == 7) {
                    constraintSet$Constraint0.layout.endToEnd = v2;
                    constraintSet$Constraint0.layout.endToStart = -1;
                }
                else if(v3 == 6) {
                    constraintSet$Constraint0.layout.endToStart = v2;
                    constraintSet$Constraint0.layout.endToEnd = -1;
                }
                else {
                    throw new IllegalArgumentException("right to " + this.sideToString(v3) + " undefined");
                }
                constraintSet$Constraint0.layout.endMargin = v4;
                return;
            }
            default: {
                throw new IllegalArgumentException(this.sideToString(v1) + " to " + this.sideToString(v3) + " unknown");
            }
        }
    }

    public void constrainCircle(int v, int v1, int v2, float f) {
        Constraint constraintSet$Constraint0 = this.get(v);
        constraintSet$Constraint0.layout.circleConstraint = v1;
        constraintSet$Constraint0.layout.circleRadius = v2;
        constraintSet$Constraint0.layout.circleAngle = f;
    }

    public void constrainDefaultHeight(int v, int v1) {
        this.get(v).layout.heightDefault = v1;
    }

    public void constrainDefaultWidth(int v, int v1) {
        this.get(v).layout.widthDefault = v1;
    }

    public void constrainHeight(int v, int v1) {
        this.get(v).layout.mHeight = v1;
    }

    public void constrainMaxHeight(int v, int v1) {
        this.get(v).layout.heightMax = v1;
    }

    public void constrainMaxWidth(int v, int v1) {
        this.get(v).layout.widthMax = v1;
    }

    public void constrainMinHeight(int v, int v1) {
        this.get(v).layout.heightMin = v1;
    }

    public void constrainMinWidth(int v, int v1) {
        this.get(v).layout.widthMin = v1;
    }

    public void constrainPercentHeight(int v, float f) {
        this.get(v).layout.heightPercent = f;
    }

    public void constrainPercentWidth(int v, float f) {
        this.get(v).layout.widthPercent = f;
    }

    public void constrainWidth(int v, int v1) {
        this.get(v).layout.mWidth = v1;
    }

    public void constrainedHeight(int v, boolean z) {
        this.get(v).layout.constrainedHeight = z;
    }

    public void constrainedWidth(int v, boolean z) {
        this.get(v).layout.constrainedWidth = z;
    }

    private int[] convertReferenceString(View view0, String s) {
        int v2;
        String[] arr_s = s.split(",");
        Context context0 = view0.getContext();
        int[] arr_v = new int[arr_s.length];
        int v = 0;
        int v1;
        for(v1 = 0; v < arr_s.length; ++v1) {
            String s1 = arr_s[v].trim();
            try {
                v2 = 0;
                v2 = id.class.getField(s1).getInt(null);
            }
            catch(Exception unused_ex) {
            }
            if(v2 == 0) {
                v2 = context0.getResources().getIdentifier(s1, "id", "com.ruliweb.www.ruliapp");
            }
            if(v2 == 0 && view0.isInEditMode() && view0.getParent() instanceof ConstraintLayout) {
                Object object0 = ((ConstraintLayout)view0.getParent()).getDesignInformation(0, s1);
                if(object0 != null && object0 instanceof Integer) {
                    v2 = (int)(((Integer)object0));
                }
            }
            arr_v[v1] = v2;
            ++v;
        }
        return v1 == arr_s.length ? arr_v : Arrays.copyOf(arr_v, v1);
    }

    public void create(int v, int v1) {
        Constraint constraintSet$Constraint0 = this.get(v);
        constraintSet$Constraint0.layout.mIsGuideline = true;
        constraintSet$Constraint0.layout.orientation = v1;
    }

    public void createBarrier(int v, int v1, int v2, int[] arr_v) {
        Constraint constraintSet$Constraint0 = this.get(v);
        constraintSet$Constraint0.layout.mHelperType = 1;
        constraintSet$Constraint0.layout.mBarrierDirection = v1;
        constraintSet$Constraint0.layout.mBarrierMargin = v2;
        constraintSet$Constraint0.layout.mIsGuideline = false;
        constraintSet$Constraint0.layout.mReferenceIds = arr_v;
    }

    private void createHorizontalChain(int v, int v1, int v2, int v3, int[] arr_v, float[] arr_f, int v4, int v5, int v6) {
        if(arr_v.length < 2 || arr_f != null && arr_f.length != arr_v.length) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if(arr_f != null) {
            Constraint constraintSet$Constraint0 = this.get(arr_v[0]);
            constraintSet$Constraint0.layout.horizontalWeight = arr_f[0];
        }
        this.get(arr_v[0]).layout.horizontalChainStyle = v4;
        this.connect(arr_v[0], v5, v, v1, -1);
        for(int v7 = 1; v7 < arr_v.length; ++v7) {
            this.connect(arr_v[v7], v5, arr_v[v7 - 1], v6, -1);
            this.connect(arr_v[v7 - 1], v6, arr_v[v7], v5, -1);
            if(arr_f != null) {
                Constraint constraintSet$Constraint1 = this.get(arr_v[v7]);
                constraintSet$Constraint1.layout.horizontalWeight = arr_f[v7];
            }
        }
        this.connect(arr_v[arr_v.length - 1], v6, v2, v3, -1);
    }

    public void createHorizontalChain(int v, int v1, int v2, int v3, int[] arr_v, float[] arr_f, int v4) {
        this.createHorizontalChain(v, v1, v2, v3, arr_v, arr_f, v4, 1, 2);
    }

    public void createHorizontalChainRtl(int v, int v1, int v2, int v3, int[] arr_v, float[] arr_f, int v4) {
        this.createHorizontalChain(v, v1, v2, v3, arr_v, arr_f, v4, 6, 7);
    }

    public void createVerticalChain(int v, int v1, int v2, int v3, int[] arr_v, float[] arr_f, int v4) {
        if(arr_v.length < 2 || arr_f != null && arr_f.length != arr_v.length) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if(arr_f != null) {
            Constraint constraintSet$Constraint0 = this.get(arr_v[0]);
            constraintSet$Constraint0.layout.verticalWeight = arr_f[0];
        }
        this.get(arr_v[0]).layout.verticalChainStyle = v4;
        this.connect(arr_v[0], 3, v, v1, 0);
        for(int v5 = 1; v5 < arr_v.length; ++v5) {
            this.connect(arr_v[v5], 3, arr_v[v5 - 1], 4, 0);
            this.connect(arr_v[v5 - 1], 4, arr_v[v5], 3, 0);
            if(arr_f != null) {
                Constraint constraintSet$Constraint1 = this.get(arr_v[v5]);
                constraintSet$Constraint1.layout.verticalWeight = arr_f[v5];
            }
        }
        this.connect(arr_v[arr_v.length - 1], 4, v2, v3, 0);
    }

    public void dump(MotionScene motionScene0, int[] arr_v) {
        HashSet hashSet0;
        Set set0 = this.mConstraints.keySet();
        if(arr_v.length == 0) {
            hashSet0 = new HashSet(set0);
        }
        else {
            hashSet0 = new HashSet();
            for(int v1 = 0; v1 < arr_v.length; ++v1) {
                hashSet0.add(((int)arr_v[v1]));
            }
        }
        System.out.println(hashSet0.size() + " constraints");
        StringBuilder stringBuilder0 = new StringBuilder();
        Integer[] arr_integer = (Integer[])hashSet0.toArray(new Integer[0]);
        for(int v = 0; v < arr_integer.length; ++v) {
            Integer integer0 = arr_integer[v];
            Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(integer0);
            if(constraintSet$Constraint0 != null) {
                stringBuilder0.append("<Constraint id=");
                stringBuilder0.append(integer0);
                stringBuilder0.append(" \n");
                constraintSet$Constraint0.layout.dump(motionScene0, stringBuilder0);
                stringBuilder0.append("/>\n");
            }
        }
        System.out.println(stringBuilder0.toString());
    }

    private Constraint fillFromAttributeList(Context context0, AttributeSet attributeSet0, boolean z) {
        Constraint constraintSet$Constraint0 = new Constraint();
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, (z ? styleable.ConstraintOverride : styleable.Constraint));
        this.populateConstraint(constraintSet$Constraint0, typedArray0, z);
        typedArray0.recycle();
        return constraintSet$Constraint0;
    }

    private Constraint get(int v) {
        if(!this.mConstraints.containsKey(v)) {
            this.mConstraints.put(v, new Constraint());
        }
        return (Constraint)this.mConstraints.get(v);
    }

    public boolean getApplyElevation(int v) {
        return this.get(v).transform.applyElevation;
    }

    // 去混淆评级： 低(20)
    public Constraint getConstraint(int v) {
        return this.mConstraints.containsKey(v) ? ((Constraint)this.mConstraints.get(v)) : null;
    }

    public HashMap getCustomAttributeSet() {
        return this.mSavedAttributes;
    }

    static String getDebugName(int v) {
        Field[] arr_field = ConstraintSet.class.getDeclaredFields();
        for(int v1 = 0; v1 < arr_field.length; ++v1) {
            Field field0 = arr_field[v1];
            if(field0.getName().contains("_") && field0.getType() == Integer.TYPE && Modifier.isStatic(field0.getModifiers()) && Modifier.isFinal(field0.getModifiers())) {
                try {
                    if(field0.getInt(null) == v) {
                        return field0.getName();
                    }
                }
                catch(IllegalAccessException illegalAccessException0) {
                    Log.e("ConstraintSet", "Error accessing ConstraintSet field", illegalAccessException0);
                }
            }
        }
        return "UNKNOWN";
    }

    public int getHeight(int v) {
        return this.get(v).layout.mHeight;
    }

    public int[] getKnownIds() {
        Integer[] arr_integer = (Integer[])this.mConstraints.keySet().toArray(new Integer[0]);
        int[] arr_v = new int[arr_integer.length];
        for(int v = 0; v < arr_integer.length; ++v) {
            arr_v[v] = (int)arr_integer[v];
        }
        return arr_v;
    }

    static String getLine(Context context0, int v, XmlPullParser xmlPullParser0) {
        return ".(" + Debug.getName(context0, v) + ".xml:" + xmlPullParser0.getLineNumber() + ") \"" + xmlPullParser0.getName() + "\"";
    }

    public Constraint getParameters(int v) {
        return this.get(v);
    }

    public int[] getReferencedIds(int v) {
        Constraint constraintSet$Constraint0 = this.get(v);
        return constraintSet$Constraint0.layout.mReferenceIds == null ? new int[0] : Arrays.copyOf(constraintSet$Constraint0.layout.mReferenceIds, constraintSet$Constraint0.layout.mReferenceIds.length);
    }

    public String[] getStateLabels() {
        return (String[])Arrays.copyOf(this.mMatchLabels, this.mMatchLabels.length);
    }

    public int getVisibility(int v) {
        return this.get(v).propertySet.visibility;
    }

    public int getVisibilityMode(int v) {
        return this.get(v).propertySet.mVisibilityMode;
    }

    public int getWidth(int v) {
        return this.get(v).layout.mWidth;
    }

    public boolean isForceId() {
        return this.mForceId;
    }

    public boolean isValidateOnParse() {
        return this.mValidate;
    }

    public void load(Context context0, int v) {
        XmlResourceParser xmlResourceParser0 = context0.getResources().getXml(v);
        try {
            int v1 = xmlResourceParser0.getEventType();
            while(true) {
                switch(v1) {
                    case 1: {
                        return;
                    }
                    case 2: {
                        String s = xmlResourceParser0.getName();
                        Constraint constraintSet$Constraint0 = this.fillFromAttributeList(context0, Xml.asAttributeSet(xmlResourceParser0), false);
                        if(s.equalsIgnoreCase("Guideline")) {
                            constraintSet$Constraint0.layout.mIsGuideline = true;
                        }
                        this.mConstraints.put(constraintSet$Constraint0.mViewId, constraintSet$Constraint0);
                        v1 = xmlResourceParser0.next();
                        break;
                    }
                    default: {
                        v1 = xmlResourceParser0.next();
                        break;
                    }
                }
            }
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("ConstraintSet", "Error parsing resource: " + v, xmlPullParserException0);
        }
        catch(IOException iOException0) {
            Log.e("ConstraintSet", "Error parsing resource: " + v, iOException0);
        }
    }

    public void load(Context context0, XmlPullParser xmlPullParser0) {
        Constraint constraintSet$Constraint1;
        try {
            int v = xmlPullParser0.getEventType();
            Constraint constraintSet$Constraint0 = null;
            while(true) {
                switch(v) {
                    case 0: {
                        xmlPullParser0.getName();
                        v = xmlPullParser0.next();
                        break;
                    }
                    case 1: {
                        return;
                    }
                    case 2: {
                        switch(xmlPullParser0.getName()) {
                            case "Barrier": {
                                constraintSet$Constraint1 = this.fillFromAttributeList(context0, Xml.asAttributeSet(xmlPullParser0), false);
                                constraintSet$Constraint1.layout.mHelperType = 1;
                                constraintSet$Constraint0 = constraintSet$Constraint1;
                                break;
                            }
                            case "Constraint": {
                                constraintSet$Constraint0 = this.fillFromAttributeList(context0, Xml.asAttributeSet(xmlPullParser0), false);
                                break;
                            }
                            case "ConstraintOverride": {
                                constraintSet$Constraint0 = this.fillFromAttributeList(context0, Xml.asAttributeSet(xmlPullParser0), true);
                                break;
                            }
                            case "CustomAttribute": {
                            label_39:
                                if(constraintSet$Constraint0 == null) {
                                    throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser0.getLineNumber());
                                }
                                ConstraintAttribute.parse(context0, xmlPullParser0, constraintSet$Constraint0.mCustomConstraints);
                                break;
                            }
                            case "CustomMethod": {
                                goto label_39;
                            }
                            case "Guideline": {
                                constraintSet$Constraint1 = this.fillFromAttributeList(context0, Xml.asAttributeSet(xmlPullParser0), false);
                                constraintSet$Constraint1.layout.mIsGuideline = true;
                                constraintSet$Constraint1.layout.mApply = true;
                                constraintSet$Constraint0 = constraintSet$Constraint1;
                                break;
                            }
                            case "Layout": {
                                if(constraintSet$Constraint0 == null) {
                                    throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser0.getLineNumber());
                                }
                                AttributeSet attributeSet0 = Xml.asAttributeSet(xmlPullParser0);
                                constraintSet$Constraint0.layout.fillFromAttributeList(context0, attributeSet0);
                                break;
                            }
                            case "Motion": {
                                if(constraintSet$Constraint0 == null) {
                                    throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser0.getLineNumber());
                                }
                                AttributeSet attributeSet1 = Xml.asAttributeSet(xmlPullParser0);
                                constraintSet$Constraint0.motion.fillFromAttributeList(context0, attributeSet1);
                                break;
                            }
                            case "PropertySet": {
                                if(constraintSet$Constraint0 == null) {
                                    throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser0.getLineNumber());
                                }
                                AttributeSet attributeSet2 = Xml.asAttributeSet(xmlPullParser0);
                                constraintSet$Constraint0.propertySet.fillFromAttributeList(context0, attributeSet2);
                                break;
                            }
                            case "Transform": {
                                if(constraintSet$Constraint0 == null) {
                                    throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser0.getLineNumber());
                                }
                                AttributeSet attributeSet3 = Xml.asAttributeSet(xmlPullParser0);
                                constraintSet$Constraint0.transform.fillFromAttributeList(context0, attributeSet3);
                            }
                        }
                        v = xmlPullParser0.next();
                        break;
                    }
                    case 3: {
                        switch(xmlPullParser0.getName().toLowerCase(Locale.ROOT)) {
                            case "constraint": 
                            case "constraintoverride": 
                            case "guideline": {
                                this.mConstraints.put(constraintSet$Constraint0.mViewId, constraintSet$Constraint0);
                                constraintSet$Constraint0 = null;
                                break;
                            }
                            case "constraintset": {
                                return;
                            }
                        }
                        v = xmlPullParser0.next();
                        break;
                    }
                    default: {
                        v = xmlPullParser0.next();
                        break;
                    }
                }
            }
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("ConstraintSet", "Error parsing XML resource", xmlPullParserException0);
        }
        catch(IOException iOException0) {
            Log.e("ConstraintSet", "Error parsing XML resource", iOException0);
        }
    }

    private static int lookupID(TypedArray typedArray0, int v, int v1) {
        int v2 = typedArray0.getResourceId(v, v1);
        return v2 == -1 ? typedArray0.getInt(v, -1) : v2;
    }

    public boolean matchesLabels(String[] arr_s) {
        int v = 0;
    label_1:
        while(v < arr_s.length) {
            String s = arr_s[v];
            String[] arr_s1 = this.mMatchLabels;
            int v1 = 0;
            while(v1 < arr_s1.length) {
                if(!arr_s1[v1].equals(s)) {
                    ++v1;
                    continue;
                }
                ++v;
                continue label_1;
            }
            return false;
        }
        return true;
    }

    public void parseColorAttributes(Constraint constraintSet$Constraint0, String s) {
        String[] arr_s = s.split(",");
        for(int v = 0; v < arr_s.length; ++v) {
            String[] arr_s1 = arr_s[v].split("=");
            if(arr_s1.length == 2) {
                constraintSet$Constraint0.setColorValue(arr_s1[0], Color.parseColor(arr_s1[1]));
            }
            else {
                Log.w("ConstraintSet", " Unable to parse " + arr_s[v]);
            }
        }
    }

    static void parseDimensionConstraints(Object object0, TypedArray typedArray0, int v, int v1) {
        boolean z;
        if(object0 != null) {
            int v2 = 0;
        alab1:
            switch(typedArray0.peekValue(v).type) {
                case 3: {
                    ConstraintSet.parseDimensionConstraintsString(object0, typedArray0.getString(v), v1);
                    return;
                label_6:
                    int v3 = typedArray0.getInt(v, 0);
                    switch(v3) {
                        case -4: {
                            z = true;
                            v2 = -2;
                            break alab1;
                        }
                        case -2: 
                        case -1: {
                            v2 = v3;
                            z = false;
                            break alab1;
                        }
                        default: {
                            z = false;
                            break alab1;
                        }
                    }
                }
                case 5: {
                    v2 = typedArray0.getDimensionPixelSize(v, 0);
                    z = false;
                    break;
                }
                default: {
                    goto label_6;
                }
            }
            if(object0 instanceof LayoutParams) {
                if(v1 == 0) {
                    ((LayoutParams)object0).width = v2;
                    ((LayoutParams)object0).constrainedWidth = z;
                    return;
                }
                ((LayoutParams)object0).height = v2;
                ((LayoutParams)object0).constrainedHeight = z;
                return;
            }
            if(object0 instanceof Layout) {
                if(v1 == 0) {
                    ((Layout)object0).mWidth = v2;
                    ((Layout)object0).constrainedWidth = z;
                    return;
                }
                ((Layout)object0).mHeight = v2;
                ((Layout)object0).constrainedHeight = z;
                return;
            }
            if(object0 instanceof Delta) {
                if(v1 == 0) {
                    ((Delta)object0).add(23, v2);
                    ((Delta)object0).add(80, z);
                    return;
                }
                ((Delta)object0).add(21, v2);
                ((Delta)object0).add(81, z);
            }
        }
    }

    static void parseDimensionConstraintsString(Object object0, String s, int v) {
        if(s != null) {
            int v1 = s.indexOf(61);
            if(v1 > 0 && v1 < s.length() - 1) {
                String s1 = s.substring(0, v1);
                String s2 = s.substring(v1 + 1);
                if(s2.length() > 0) {
                    String s3 = s1.trim();
                    String s4 = s2.trim();
                    if("ratio".equalsIgnoreCase(s3)) {
                        if(object0 instanceof LayoutParams) {
                            if(v == 0) {
                                ((LayoutParams)object0).width = 0;
                            }
                            else {
                                ((LayoutParams)object0).height = 0;
                            }
                            ConstraintSet.parseDimensionRatioString(((LayoutParams)object0), s4);
                            return;
                        }
                        if(object0 instanceof Layout) {
                            ((Layout)object0).dimensionRatio = s4;
                            return;
                        }
                        if(object0 instanceof Delta) {
                            ((Delta)object0).add(5, s4);
                        }
                    }
                    else if("weight".equalsIgnoreCase(s3)) {
                        try {
                            float f = Float.parseFloat(s4);
                            if(object0 instanceof LayoutParams) {
                                if(v == 0) {
                                    ((LayoutParams)object0).width = 0;
                                    ((LayoutParams)object0).horizontalWeight = f;
                                    return;
                                }
                                ((LayoutParams)object0).height = 0;
                                ((LayoutParams)object0).verticalWeight = f;
                                return;
                            }
                            if(object0 instanceof Layout) {
                                if(v == 0) {
                                    ((Layout)object0).mWidth = 0;
                                    ((Layout)object0).horizontalWeight = f;
                                    return;
                                }
                                ((Layout)object0).mHeight = 0;
                                ((Layout)object0).verticalWeight = f;
                                return;
                            }
                            if(object0 instanceof Delta) {
                                if(v == 0) {
                                    ((Delta)object0).add(23, 0);
                                    ((Delta)object0).add(39, f);
                                    return;
                                }
                                ((Delta)object0).add(21, 0);
                                ((Delta)object0).add(40, f);
                            }
                        }
                        catch(NumberFormatException unused_ex) {
                        }
                    }
                    else if("parent".equalsIgnoreCase(s3)) {
                        try {
                            float f1 = Math.max(0.0f, Math.min(1.0f, Float.parseFloat(s4)));
                            if(object0 instanceof LayoutParams) {
                                if(v == 0) {
                                    ((LayoutParams)object0).width = 0;
                                    ((LayoutParams)object0).matchConstraintPercentWidth = f1;
                                    ((LayoutParams)object0).matchConstraintDefaultWidth = 2;
                                    return;
                                }
                                ((LayoutParams)object0).height = 0;
                                ((LayoutParams)object0).matchConstraintPercentHeight = f1;
                                ((LayoutParams)object0).matchConstraintDefaultHeight = 2;
                                return;
                            }
                            if(object0 instanceof Layout) {
                                if(v == 0) {
                                    ((Layout)object0).mWidth = 0;
                                    ((Layout)object0).widthPercent = f1;
                                    ((Layout)object0).widthDefault = 2;
                                    return;
                                }
                                ((Layout)object0).mHeight = 0;
                                ((Layout)object0).heightPercent = f1;
                                ((Layout)object0).heightDefault = 2;
                                return;
                            }
                            if(object0 instanceof Delta) {
                                if(v == 0) {
                                    ((Delta)object0).add(23, 0);
                                    ((Delta)object0).add(54, 2);
                                    return;
                                }
                                ((Delta)object0).add(21, 0);
                                ((Delta)object0).add(55, 2);
                            }
                        }
                        catch(NumberFormatException unused_ex) {
                        }
                    }
                }
            }
        }
    }

    static void parseDimensionRatioString(LayoutParams constraintLayout$LayoutParams0, String s) {
        float f = NaNf;
        int v = -1;
        if(s != null) {
            int v1 = s.length();
            int v2 = s.indexOf(44);
            int v3 = 0;
            if(v2 > 0 && v2 < v1 - 1) {
                String s1 = s.substring(0, v2);
                if(s1.equalsIgnoreCase("W")) {
                    v = 0;
                }
                else if(s1.equalsIgnoreCase("H")) {
                    v = 1;
                }
                v3 = v2 + 1;
            }
            int v4 = s.indexOf(58);
            if(v4 < 0 || v4 >= v1 - 1) {
                String s4 = s.substring(v3);
                if(s4.length() > 0) {
                    try {
                        f = Float.parseFloat(s4);
                        constraintLayout$LayoutParams0.dimensionRatio = s;
                        constraintLayout$LayoutParams0.mDimensionRatioValue = f;
                        constraintLayout$LayoutParams0.mDimensionRatioSide = v;
                        return;
                    }
                    catch(NumberFormatException unused_ex) {
                    }
                }
            }
            else {
                String s2 = s.substring(v3, v4);
                String s3 = s.substring(v4 + 1);
                if(s2.length() > 0 && s3.length() > 0) {
                    try {
                        float f1 = Float.parseFloat(s2);
                        float f2 = Float.parseFloat(s3);
                        if(f1 > 0.0f && f2 > 0.0f) {
                            f = v == 1 ? Math.abs(f2 / f1) : Math.abs(f1 / f2);
                        }
                    }
                    catch(NumberFormatException unused_ex) {
                    }
                }
            }
        }
        constraintLayout$LayoutParams0.dimensionRatio = s;
        constraintLayout$LayoutParams0.mDimensionRatioValue = f;
        constraintLayout$LayoutParams0.mDimensionRatioSide = v;
    }

    public void parseFloatAttributes(Constraint constraintSet$Constraint0, String s) {
        String[] arr_s = s.split(",");
        for(int v = 0; v < arr_s.length; ++v) {
            String[] arr_s1 = arr_s[v].split("=");
            if(arr_s1.length == 2) {
                constraintSet$Constraint0.setFloatValue(arr_s1[0], Float.parseFloat(arr_s1[1]));
            }
            else {
                Log.w("ConstraintSet", " Unable to parse " + arr_s[v]);
            }
        }
    }

    public void parseIntAttributes(Constraint constraintSet$Constraint0, String s) {
        String[] arr_s = s.split(",");
        for(int v = 0; v < arr_s.length; ++v) {
            String[] arr_s1 = arr_s[v].split("=");
            if(arr_s1.length == 2) {
                constraintSet$Constraint0.setFloatValue(arr_s1[0], ((float)(((int)Integer.decode(arr_s1[1])))));
            }
            else {
                Log.w("ConstraintSet", " Unable to parse " + arr_s[v]);
            }
        }
    }

    public void parseStringAttributes(Constraint constraintSet$Constraint0, String s) {
        String[] arr_s = ConstraintSet.splitString(s);
        for(int v = 0; v < arr_s.length; ++v) {
            String[] arr_s1 = arr_s[v].split("=");
            Log.w("ConstraintSet", " Unable to parse " + arr_s[v]);
            constraintSet$Constraint0.setStringValue(arr_s1[0], arr_s1[1]);
        }
    }

    private void populateConstraint(Constraint constraintSet$Constraint0, TypedArray typedArray0, boolean z) {
        if(z) {
            ConstraintSet.populateOverride(constraintSet$Constraint0, typedArray0);
            return;
        }
        int v = typedArray0.getIndexCount();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = typedArray0.getIndex(v1);
            if(v2 != styleable.Constraint_android_id && styleable.Constraint_android_layout_marginStart != v2 && styleable.Constraint_android_layout_marginEnd != v2) {
                constraintSet$Constraint0.motion.mApply = true;
                constraintSet$Constraint0.layout.mApply = true;
                constraintSet$Constraint0.propertySet.mApply = true;
                constraintSet$Constraint0.transform.mApply = true;
            }
            switch(ConstraintSet.sMapToConstant.get(v2)) {
                case 1: {
                    constraintSet$Constraint0.layout.baselineToBaseline = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.baselineToBaseline);
                    break;
                }
                case 2: {
                    constraintSet$Constraint0.layout.bottomMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.bottomMargin);
                    break;
                }
                case 3: {
                    constraintSet$Constraint0.layout.bottomToBottom = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.bottomToBottom);
                    break;
                }
                case 4: {
                    constraintSet$Constraint0.layout.bottomToTop = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.bottomToTop);
                    break;
                }
                case 5: {
                    constraintSet$Constraint0.layout.dimensionRatio = typedArray0.getString(v2);
                    break;
                }
                case 6: {
                    constraintSet$Constraint0.layout.editorAbsoluteX = typedArray0.getDimensionPixelOffset(v2, constraintSet$Constraint0.layout.editorAbsoluteX);
                    break;
                }
                case 7: {
                    constraintSet$Constraint0.layout.editorAbsoluteY = typedArray0.getDimensionPixelOffset(v2, constraintSet$Constraint0.layout.editorAbsoluteY);
                    break;
                }
                case 8: {
                    constraintSet$Constraint0.layout.endMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.endMargin);
                    break;
                }
                case 9: {
                    constraintSet$Constraint0.layout.endToEnd = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.endToEnd);
                    break;
                }
                case 10: {
                    constraintSet$Constraint0.layout.endToStart = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.endToStart);
                    break;
                }
                case 11: {
                    constraintSet$Constraint0.layout.goneBottomMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneBottomMargin);
                    break;
                }
                case 12: {
                    constraintSet$Constraint0.layout.goneEndMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneEndMargin);
                    break;
                }
                case 13: {
                    constraintSet$Constraint0.layout.goneLeftMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneLeftMargin);
                    break;
                }
                case 14: {
                    constraintSet$Constraint0.layout.goneRightMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneRightMargin);
                    break;
                }
                case 15: {
                    constraintSet$Constraint0.layout.goneStartMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneStartMargin);
                    break;
                }
                case 16: {
                    constraintSet$Constraint0.layout.goneTopMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneTopMargin);
                    break;
                }
                case 17: {
                    constraintSet$Constraint0.layout.guideBegin = typedArray0.getDimensionPixelOffset(v2, constraintSet$Constraint0.layout.guideBegin);
                    break;
                }
                case 18: {
                    constraintSet$Constraint0.layout.guideEnd = typedArray0.getDimensionPixelOffset(v2, constraintSet$Constraint0.layout.guideEnd);
                    break;
                }
                case 19: {
                    constraintSet$Constraint0.layout.guidePercent = typedArray0.getFloat(v2, constraintSet$Constraint0.layout.guidePercent);
                    break;
                }
                case 20: {
                    constraintSet$Constraint0.layout.horizontalBias = typedArray0.getFloat(v2, constraintSet$Constraint0.layout.horizontalBias);
                    break;
                }
                case 21: {
                    constraintSet$Constraint0.layout.mHeight = typedArray0.getLayoutDimension(v2, constraintSet$Constraint0.layout.mHeight);
                    break;
                }
                case 22: {
                    constraintSet$Constraint0.propertySet.visibility = typedArray0.getInt(v2, constraintSet$Constraint0.propertySet.visibility);
                    constraintSet$Constraint0.propertySet.visibility = ConstraintSet.VISIBILITY_FLAGS[constraintSet$Constraint0.propertySet.visibility];
                    break;
                }
                case 23: {
                    constraintSet$Constraint0.layout.mWidth = typedArray0.getLayoutDimension(v2, constraintSet$Constraint0.layout.mWidth);
                    break;
                }
                case 24: {
                    constraintSet$Constraint0.layout.leftMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.leftMargin);
                    break;
                }
                case 25: {
                    constraintSet$Constraint0.layout.leftToLeft = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.leftToLeft);
                    break;
                }
                case 26: {
                    constraintSet$Constraint0.layout.leftToRight = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.leftToRight);
                    break;
                }
                case 27: {
                    constraintSet$Constraint0.layout.orientation = typedArray0.getInt(v2, constraintSet$Constraint0.layout.orientation);
                    break;
                }
                case 28: {
                    constraintSet$Constraint0.layout.rightMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.rightMargin);
                    break;
                }
                case 29: {
                    constraintSet$Constraint0.layout.rightToLeft = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.rightToLeft);
                    break;
                }
                case 30: {
                    constraintSet$Constraint0.layout.rightToRight = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.rightToRight);
                    break;
                }
                case 0x1F: {
                    constraintSet$Constraint0.layout.startMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.startMargin);
                    break;
                }
                case 0x20: {
                    constraintSet$Constraint0.layout.startToEnd = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.startToEnd);
                    break;
                }
                case 33: {
                    constraintSet$Constraint0.layout.startToStart = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.startToStart);
                    break;
                }
                case 34: {
                    constraintSet$Constraint0.layout.topMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.topMargin);
                    break;
                }
                case 35: {
                    constraintSet$Constraint0.layout.topToBottom = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.topToBottom);
                    break;
                }
                case 36: {
                    constraintSet$Constraint0.layout.topToTop = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.topToTop);
                    break;
                }
                case 37: {
                    constraintSet$Constraint0.layout.verticalBias = typedArray0.getFloat(v2, constraintSet$Constraint0.layout.verticalBias);
                    break;
                }
                case 38: {
                    constraintSet$Constraint0.mViewId = typedArray0.getResourceId(v2, constraintSet$Constraint0.mViewId);
                    break;
                }
                case 39: {
                    constraintSet$Constraint0.layout.horizontalWeight = typedArray0.getFloat(v2, constraintSet$Constraint0.layout.horizontalWeight);
                    break;
                }
                case 40: {
                    constraintSet$Constraint0.layout.verticalWeight = typedArray0.getFloat(v2, constraintSet$Constraint0.layout.verticalWeight);
                    break;
                }
                case 41: {
                    constraintSet$Constraint0.layout.horizontalChainStyle = typedArray0.getInt(v2, constraintSet$Constraint0.layout.horizontalChainStyle);
                    break;
                }
                case 42: {
                    constraintSet$Constraint0.layout.verticalChainStyle = typedArray0.getInt(v2, constraintSet$Constraint0.layout.verticalChainStyle);
                    break;
                }
                case 43: {
                    constraintSet$Constraint0.propertySet.alpha = typedArray0.getFloat(v2, constraintSet$Constraint0.propertySet.alpha);
                    break;
                }
                case 44: {
                    constraintSet$Constraint0.transform.applyElevation = true;
                    constraintSet$Constraint0.transform.elevation = typedArray0.getDimension(v2, constraintSet$Constraint0.transform.elevation);
                    break;
                }
                case 45: {
                    constraintSet$Constraint0.transform.rotationX = typedArray0.getFloat(v2, constraintSet$Constraint0.transform.rotationX);
                    break;
                }
                case 46: {
                    constraintSet$Constraint0.transform.rotationY = typedArray0.getFloat(v2, constraintSet$Constraint0.transform.rotationY);
                    break;
                }
                case 0x2F: {
                    constraintSet$Constraint0.transform.scaleX = typedArray0.getFloat(v2, constraintSet$Constraint0.transform.scaleX);
                    break;
                }
                case 0x30: {
                    constraintSet$Constraint0.transform.scaleY = typedArray0.getFloat(v2, constraintSet$Constraint0.transform.scaleY);
                    break;
                }
                case 49: {
                    constraintSet$Constraint0.transform.transformPivotX = typedArray0.getDimension(v2, constraintSet$Constraint0.transform.transformPivotX);
                    break;
                }
                case 50: {
                    constraintSet$Constraint0.transform.transformPivotY = typedArray0.getDimension(v2, constraintSet$Constraint0.transform.transformPivotY);
                    break;
                }
                case 51: {
                    constraintSet$Constraint0.transform.translationX = typedArray0.getDimension(v2, constraintSet$Constraint0.transform.translationX);
                    break;
                }
                case 52: {
                    constraintSet$Constraint0.transform.translationY = typedArray0.getDimension(v2, constraintSet$Constraint0.transform.translationY);
                    break;
                }
                case 53: {
                    constraintSet$Constraint0.transform.translationZ = typedArray0.getDimension(v2, constraintSet$Constraint0.transform.translationZ);
                    break;
                }
                case 54: {
                    constraintSet$Constraint0.layout.widthDefault = typedArray0.getInt(v2, constraintSet$Constraint0.layout.widthDefault);
                    break;
                }
                case 55: {
                    constraintSet$Constraint0.layout.heightDefault = typedArray0.getInt(v2, constraintSet$Constraint0.layout.heightDefault);
                    break;
                }
                case 56: {
                    constraintSet$Constraint0.layout.widthMax = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.widthMax);
                    break;
                }
                case 57: {
                    constraintSet$Constraint0.layout.heightMax = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.heightMax);
                    break;
                }
                case 58: {
                    constraintSet$Constraint0.layout.widthMin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.widthMin);
                    break;
                }
                case 59: {
                    constraintSet$Constraint0.layout.heightMin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.heightMin);
                    break;
                }
                case 60: {
                    constraintSet$Constraint0.transform.rotation = typedArray0.getFloat(v2, constraintSet$Constraint0.transform.rotation);
                    break;
                }
                case 61: {
                    constraintSet$Constraint0.layout.circleConstraint = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.circleConstraint);
                    break;
                }
                case 62: {
                    constraintSet$Constraint0.layout.circleRadius = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.circleRadius);
                    break;
                }
                case 0x3F: {
                    constraintSet$Constraint0.layout.circleAngle = typedArray0.getFloat(v2, constraintSet$Constraint0.layout.circleAngle);
                    break;
                }
                case 0x40: {
                    constraintSet$Constraint0.motion.mAnimateRelativeTo = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.motion.mAnimateRelativeTo);
                    break;
                }
                case 65: {
                    if(typedArray0.peekValue(v2).type == 3) {
                        constraintSet$Constraint0.motion.mTransitionEasing = typedArray0.getString(v2);
                    }
                    else {
                        String[] arr_s = Easing.NAMED_EASING;
                        constraintSet$Constraint0.motion.mTransitionEasing = arr_s[typedArray0.getInteger(v2, 0)];
                    }
                    break;
                }
                case 66: {
                    constraintSet$Constraint0.motion.mDrawPath = typedArray0.getInt(v2, 0);
                    break;
                }
                case 67: {
                    constraintSet$Constraint0.motion.mPathRotate = typedArray0.getFloat(v2, constraintSet$Constraint0.motion.mPathRotate);
                    break;
                }
                case 68: {
                    constraintSet$Constraint0.propertySet.mProgress = typedArray0.getFloat(v2, constraintSet$Constraint0.propertySet.mProgress);
                    break;
                }
                case 69: {
                    constraintSet$Constraint0.layout.widthPercent = typedArray0.getFloat(v2, 1.0f);
                    break;
                }
                case 70: {
                    constraintSet$Constraint0.layout.heightPercent = typedArray0.getFloat(v2, 1.0f);
                    break;
                }
                case 71: {
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                }
                case 72: {
                    constraintSet$Constraint0.layout.mBarrierDirection = typedArray0.getInt(v2, constraintSet$Constraint0.layout.mBarrierDirection);
                    break;
                }
                case 73: {
                    constraintSet$Constraint0.layout.mBarrierMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.mBarrierMargin);
                    break;
                }
                case 74: {
                    constraintSet$Constraint0.layout.mReferenceIdString = typedArray0.getString(v2);
                    break;
                }
                case 75: {
                    constraintSet$Constraint0.layout.mBarrierAllowsGoneWidgets = typedArray0.getBoolean(v2, constraintSet$Constraint0.layout.mBarrierAllowsGoneWidgets);
                    break;
                }
                case 76: {
                    constraintSet$Constraint0.motion.mPathMotionArc = typedArray0.getInt(v2, constraintSet$Constraint0.motion.mPathMotionArc);
                    break;
                }
                case 77: {
                    constraintSet$Constraint0.layout.mConstraintTag = typedArray0.getString(v2);
                    break;
                }
                case 78: {
                    constraintSet$Constraint0.propertySet.mVisibilityMode = typedArray0.getInt(v2, constraintSet$Constraint0.propertySet.mVisibilityMode);
                    break;
                }
                case 0x4F: {
                    constraintSet$Constraint0.motion.mMotionStagger = typedArray0.getFloat(v2, constraintSet$Constraint0.motion.mMotionStagger);
                    break;
                }
                case 80: {
                    constraintSet$Constraint0.layout.constrainedWidth = typedArray0.getBoolean(v2, constraintSet$Constraint0.layout.constrainedWidth);
                    break;
                }
                case 81: {
                    constraintSet$Constraint0.layout.constrainedHeight = typedArray0.getBoolean(v2, constraintSet$Constraint0.layout.constrainedHeight);
                    break;
                }
                case 82: {
                    constraintSet$Constraint0.motion.mAnimateCircleAngleTo = typedArray0.getInteger(v2, constraintSet$Constraint0.motion.mAnimateCircleAngleTo);
                    break;
                }
                case 83: {
                    constraintSet$Constraint0.transform.transformPivotTarget = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.transform.transformPivotTarget);
                    break;
                }
                case 84: {
                    constraintSet$Constraint0.motion.mQuantizeMotionSteps = typedArray0.getInteger(v2, constraintSet$Constraint0.motion.mQuantizeMotionSteps);
                    break;
                }
                case 85: {
                    constraintSet$Constraint0.motion.mQuantizeMotionPhase = typedArray0.getFloat(v2, constraintSet$Constraint0.motion.mQuantizeMotionPhase);
                    break;
                }
                case 86: {
                    TypedValue typedValue0 = typedArray0.peekValue(v2);
                    if(typedValue0.type == 1) {
                        constraintSet$Constraint0.motion.mQuantizeInterpolatorID = typedArray0.getResourceId(v2, -1);
                        if(constraintSet$Constraint0.motion.mQuantizeInterpolatorID != -1) {
                            constraintSet$Constraint0.motion.mQuantizeInterpolatorType = -2;
                        }
                    }
                    else if(typedValue0.type == 3) {
                        constraintSet$Constraint0.motion.mQuantizeInterpolatorString = typedArray0.getString(v2);
                        if(constraintSet$Constraint0.motion.mQuantizeInterpolatorString.indexOf("/") > 0) {
                            constraintSet$Constraint0.motion.mQuantizeInterpolatorID = typedArray0.getResourceId(v2, -1);
                            constraintSet$Constraint0.motion.mQuantizeInterpolatorType = -2;
                        }
                        else {
                            constraintSet$Constraint0.motion.mQuantizeInterpolatorType = -1;
                        }
                    }
                    else {
                        constraintSet$Constraint0.motion.mQuantizeInterpolatorType = typedArray0.getInteger(v2, constraintSet$Constraint0.motion.mQuantizeInterpolatorID);
                    }
                    break;
                }
                case 87: {
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(v2) + "   " + ConstraintSet.sMapToConstant.get(v2));
                    break;
                }
                case 91: {
                    constraintSet$Constraint0.layout.baselineToTop = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.baselineToTop);
                    break;
                }
                case 92: {
                    constraintSet$Constraint0.layout.baselineToBottom = ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.layout.baselineToBottom);
                    break;
                }
                case 93: {
                    constraintSet$Constraint0.layout.baselineMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.baselineMargin);
                    break;
                }
                case 94: {
                    constraintSet$Constraint0.layout.goneBaselineMargin = typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneBaselineMargin);
                    break;
                }
                case 0x5F: {
                    ConstraintSet.parseDimensionConstraints(constraintSet$Constraint0.layout, typedArray0, v2, 0);
                    break;
                }
                case 0x60: {
                    ConstraintSet.parseDimensionConstraints(constraintSet$Constraint0.layout, typedArray0, v2, 1);
                    break;
                }
                case 97: {
                    constraintSet$Constraint0.layout.mWrapBehavior = typedArray0.getInt(v2, constraintSet$Constraint0.layout.mWrapBehavior);
                    break;
                }
                default: {
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(v2) + "   " + ConstraintSet.sMapToConstant.get(v2));
                }
            }
        }
        if(constraintSet$Constraint0.layout.mReferenceIdString != null) {
            constraintSet$Constraint0.layout.mReferenceIds = null;
        }
    }

    private static void populateOverride(Constraint constraintSet$Constraint0, TypedArray typedArray0) {
        int v = typedArray0.getIndexCount();
        Delta constraintSet$Constraint$Delta0 = new Delta();
        constraintSet$Constraint0.mDelta = constraintSet$Constraint$Delta0;
        constraintSet$Constraint0.motion.mApply = false;
        constraintSet$Constraint0.layout.mApply = false;
        constraintSet$Constraint0.propertySet.mApply = false;
        constraintSet$Constraint0.transform.mApply = false;
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = typedArray0.getIndex(v1);
            switch(ConstraintSet.sOverrideMapToConstant.get(v2)) {
                case 2: {
                    constraintSet$Constraint$Delta0.add(2, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.bottomMargin));
                    break;
                }
                case 5: {
                    constraintSet$Constraint$Delta0.add(5, typedArray0.getString(v2));
                    break;
                }
                case 6: {
                    constraintSet$Constraint$Delta0.add(6, typedArray0.getDimensionPixelOffset(v2, constraintSet$Constraint0.layout.editorAbsoluteX));
                    break;
                }
                case 7: {
                    constraintSet$Constraint$Delta0.add(7, typedArray0.getDimensionPixelOffset(v2, constraintSet$Constraint0.layout.editorAbsoluteY));
                    break;
                }
                case 8: {
                    constraintSet$Constraint$Delta0.add(8, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.endMargin));
                    break;
                }
                case 11: {
                    constraintSet$Constraint$Delta0.add(11, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneBottomMargin));
                    break;
                }
                case 12: {
                    constraintSet$Constraint$Delta0.add(12, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneEndMargin));
                    break;
                }
                case 13: {
                    constraintSet$Constraint$Delta0.add(13, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneLeftMargin));
                    break;
                }
                case 14: {
                    constraintSet$Constraint$Delta0.add(14, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneRightMargin));
                    break;
                }
                case 15: {
                    constraintSet$Constraint$Delta0.add(15, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneStartMargin));
                    break;
                }
                case 16: {
                    constraintSet$Constraint$Delta0.add(16, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneTopMargin));
                    break;
                }
                case 17: {
                    constraintSet$Constraint$Delta0.add(17, typedArray0.getDimensionPixelOffset(v2, constraintSet$Constraint0.layout.guideBegin));
                    break;
                }
                case 18: {
                    constraintSet$Constraint$Delta0.add(18, typedArray0.getDimensionPixelOffset(v2, constraintSet$Constraint0.layout.guideEnd));
                    break;
                }
                case 19: {
                    constraintSet$Constraint$Delta0.add(19, typedArray0.getFloat(v2, constraintSet$Constraint0.layout.guidePercent));
                    break;
                }
                case 20: {
                    constraintSet$Constraint$Delta0.add(20, typedArray0.getFloat(v2, constraintSet$Constraint0.layout.horizontalBias));
                    break;
                }
                case 21: {
                    constraintSet$Constraint$Delta0.add(21, typedArray0.getLayoutDimension(v2, constraintSet$Constraint0.layout.mHeight));
                    break;
                }
                case 22: {
                    constraintSet$Constraint$Delta0.add(22, ConstraintSet.VISIBILITY_FLAGS[typedArray0.getInt(v2, constraintSet$Constraint0.propertySet.visibility)]);
                    break;
                }
                case 23: {
                    constraintSet$Constraint$Delta0.add(23, typedArray0.getLayoutDimension(v2, constraintSet$Constraint0.layout.mWidth));
                    break;
                }
                case 24: {
                    constraintSet$Constraint$Delta0.add(24, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.leftMargin));
                    break;
                }
                case 27: {
                    constraintSet$Constraint$Delta0.add(27, typedArray0.getInt(v2, constraintSet$Constraint0.layout.orientation));
                    break;
                }
                case 28: {
                    constraintSet$Constraint$Delta0.add(28, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.rightMargin));
                    break;
                }
                case 0x1F: {
                    constraintSet$Constraint$Delta0.add(0x1F, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.startMargin));
                    break;
                }
                case 34: {
                    constraintSet$Constraint$Delta0.add(34, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.topMargin));
                    break;
                }
                case 37: {
                    constraintSet$Constraint$Delta0.add(37, typedArray0.getFloat(v2, constraintSet$Constraint0.layout.verticalBias));
                    break;
                }
                case 38: {
                    constraintSet$Constraint0.mViewId = typedArray0.getResourceId(v2, constraintSet$Constraint0.mViewId);
                    constraintSet$Constraint$Delta0.add(38, constraintSet$Constraint0.mViewId);
                    break;
                }
                case 39: {
                    constraintSet$Constraint$Delta0.add(39, typedArray0.getFloat(v2, constraintSet$Constraint0.layout.horizontalWeight));
                    break;
                }
                case 40: {
                    constraintSet$Constraint$Delta0.add(40, typedArray0.getFloat(v2, constraintSet$Constraint0.layout.verticalWeight));
                    break;
                }
                case 41: {
                    constraintSet$Constraint$Delta0.add(41, typedArray0.getInt(v2, constraintSet$Constraint0.layout.horizontalChainStyle));
                    break;
                }
                case 42: {
                    constraintSet$Constraint$Delta0.add(42, typedArray0.getInt(v2, constraintSet$Constraint0.layout.verticalChainStyle));
                    break;
                }
                case 43: {
                    constraintSet$Constraint$Delta0.add(43, typedArray0.getFloat(v2, constraintSet$Constraint0.propertySet.alpha));
                    break;
                }
                case 44: {
                    constraintSet$Constraint$Delta0.add(44, true);
                    constraintSet$Constraint$Delta0.add(44, typedArray0.getDimension(v2, constraintSet$Constraint0.transform.elevation));
                    break;
                }
                case 45: {
                    constraintSet$Constraint$Delta0.add(45, typedArray0.getFloat(v2, constraintSet$Constraint0.transform.rotationX));
                    break;
                }
                case 46: {
                    constraintSet$Constraint$Delta0.add(46, typedArray0.getFloat(v2, constraintSet$Constraint0.transform.rotationY));
                    break;
                }
                case 0x2F: {
                    constraintSet$Constraint$Delta0.add(0x2F, typedArray0.getFloat(v2, constraintSet$Constraint0.transform.scaleX));
                    break;
                }
                case 0x30: {
                    constraintSet$Constraint$Delta0.add(0x30, typedArray0.getFloat(v2, constraintSet$Constraint0.transform.scaleY));
                    break;
                }
                case 49: {
                    constraintSet$Constraint$Delta0.add(49, typedArray0.getDimension(v2, constraintSet$Constraint0.transform.transformPivotX));
                    break;
                }
                case 50: {
                    constraintSet$Constraint$Delta0.add(50, typedArray0.getDimension(v2, constraintSet$Constraint0.transform.transformPivotY));
                    break;
                }
                case 51: {
                    constraintSet$Constraint$Delta0.add(51, typedArray0.getDimension(v2, constraintSet$Constraint0.transform.translationX));
                    break;
                }
                case 52: {
                    constraintSet$Constraint$Delta0.add(52, typedArray0.getDimension(v2, constraintSet$Constraint0.transform.translationY));
                    break;
                }
                case 53: {
                    constraintSet$Constraint$Delta0.add(53, typedArray0.getDimension(v2, constraintSet$Constraint0.transform.translationZ));
                    break;
                }
                case 54: {
                    constraintSet$Constraint$Delta0.add(54, typedArray0.getInt(v2, constraintSet$Constraint0.layout.widthDefault));
                    break;
                }
                case 55: {
                    constraintSet$Constraint$Delta0.add(55, typedArray0.getInt(v2, constraintSet$Constraint0.layout.heightDefault));
                    break;
                }
                case 56: {
                    constraintSet$Constraint$Delta0.add(56, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.widthMax));
                    break;
                }
                case 57: {
                    constraintSet$Constraint$Delta0.add(57, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.heightMax));
                    break;
                }
                case 58: {
                    constraintSet$Constraint$Delta0.add(58, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.widthMin));
                    break;
                }
                case 59: {
                    constraintSet$Constraint$Delta0.add(59, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.heightMin));
                    break;
                }
                case 60: {
                    constraintSet$Constraint$Delta0.add(60, typedArray0.getFloat(v2, constraintSet$Constraint0.transform.rotation));
                    break;
                }
                case 62: {
                    constraintSet$Constraint$Delta0.add(62, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.circleRadius));
                    break;
                }
                case 0x3F: {
                    constraintSet$Constraint$Delta0.add(0x3F, typedArray0.getFloat(v2, constraintSet$Constraint0.layout.circleAngle));
                    break;
                }
                case 0x40: {
                    constraintSet$Constraint$Delta0.add(0x40, ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.motion.mAnimateRelativeTo));
                    break;
                }
                case 65: {
                    if(typedArray0.peekValue(v2).type == 3) {
                        constraintSet$Constraint$Delta0.add(65, typedArray0.getString(v2));
                    }
                    else {
                        String[] arr_s = Easing.NAMED_EASING;
                        constraintSet$Constraint$Delta0.add(65, arr_s[typedArray0.getInteger(v2, 0)]);
                    }
                    break;
                }
                case 66: {
                    constraintSet$Constraint$Delta0.add(66, typedArray0.getInt(v2, 0));
                    break;
                }
                case 67: {
                    constraintSet$Constraint$Delta0.add(67, typedArray0.getFloat(v2, constraintSet$Constraint0.motion.mPathRotate));
                    break;
                }
                case 68: {
                    constraintSet$Constraint$Delta0.add(68, typedArray0.getFloat(v2, constraintSet$Constraint0.propertySet.mProgress));
                    break;
                }
                case 69: {
                    constraintSet$Constraint$Delta0.add(69, typedArray0.getFloat(v2, 1.0f));
                    break;
                }
                case 70: {
                    constraintSet$Constraint$Delta0.add(70, typedArray0.getFloat(v2, 1.0f));
                    break;
                }
                case 71: {
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                }
                case 72: {
                    constraintSet$Constraint$Delta0.add(72, typedArray0.getInt(v2, constraintSet$Constraint0.layout.mBarrierDirection));
                    break;
                }
                case 73: {
                    constraintSet$Constraint$Delta0.add(73, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.mBarrierMargin));
                    break;
                }
                case 74: {
                    constraintSet$Constraint$Delta0.add(74, typedArray0.getString(v2));
                    break;
                }
                case 75: {
                    constraintSet$Constraint$Delta0.add(75, typedArray0.getBoolean(v2, constraintSet$Constraint0.layout.mBarrierAllowsGoneWidgets));
                    break;
                }
                case 76: {
                    constraintSet$Constraint$Delta0.add(76, typedArray0.getInt(v2, constraintSet$Constraint0.motion.mPathMotionArc));
                    break;
                }
                case 77: {
                    constraintSet$Constraint$Delta0.add(77, typedArray0.getString(v2));
                    break;
                }
                case 78: {
                    constraintSet$Constraint$Delta0.add(78, typedArray0.getInt(v2, constraintSet$Constraint0.propertySet.mVisibilityMode));
                    break;
                }
                case 0x4F: {
                    constraintSet$Constraint$Delta0.add(0x4F, typedArray0.getFloat(v2, constraintSet$Constraint0.motion.mMotionStagger));
                    break;
                }
                case 80: {
                    constraintSet$Constraint$Delta0.add(80, typedArray0.getBoolean(v2, constraintSet$Constraint0.layout.constrainedWidth));
                    break;
                }
                case 81: {
                    constraintSet$Constraint$Delta0.add(81, typedArray0.getBoolean(v2, constraintSet$Constraint0.layout.constrainedHeight));
                    break;
                }
                case 82: {
                    constraintSet$Constraint$Delta0.add(82, typedArray0.getInteger(v2, constraintSet$Constraint0.motion.mAnimateCircleAngleTo));
                    break;
                }
                case 83: {
                    constraintSet$Constraint$Delta0.add(83, ConstraintSet.lookupID(typedArray0, v2, constraintSet$Constraint0.transform.transformPivotTarget));
                    break;
                }
                case 84: {
                    constraintSet$Constraint$Delta0.add(84, typedArray0.getInteger(v2, constraintSet$Constraint0.motion.mQuantizeMotionSteps));
                    break;
                }
                case 85: {
                    constraintSet$Constraint$Delta0.add(85, typedArray0.getFloat(v2, constraintSet$Constraint0.motion.mQuantizeMotionPhase));
                    break;
                }
                case 86: {
                    TypedValue typedValue0 = typedArray0.peekValue(v2);
                    if(typedValue0.type == 1) {
                        constraintSet$Constraint0.motion.mQuantizeInterpolatorID = typedArray0.getResourceId(v2, -1);
                        constraintSet$Constraint$Delta0.add(89, constraintSet$Constraint0.motion.mQuantizeInterpolatorID);
                        if(constraintSet$Constraint0.motion.mQuantizeInterpolatorID != -1) {
                            constraintSet$Constraint0.motion.mQuantizeInterpolatorType = -2;
                            constraintSet$Constraint$Delta0.add(88, constraintSet$Constraint0.motion.mQuantizeInterpolatorType);
                        }
                    }
                    else {
                        if(typedValue0.type == 3) {
                            constraintSet$Constraint0.motion.mQuantizeInterpolatorString = typedArray0.getString(v2);
                            constraintSet$Constraint$Delta0.add(90, constraintSet$Constraint0.motion.mQuantizeInterpolatorString);
                            if(constraintSet$Constraint0.motion.mQuantizeInterpolatorString.indexOf("/") > 0) {
                                constraintSet$Constraint0.motion.mQuantizeInterpolatorID = typedArray0.getResourceId(v2, -1);
                                constraintSet$Constraint$Delta0.add(89, constraintSet$Constraint0.motion.mQuantizeInterpolatorID);
                                constraintSet$Constraint0.motion.mQuantizeInterpolatorType = -2;
                            }
                            else {
                                constraintSet$Constraint0.motion.mQuantizeInterpolatorType = -1;
                            }
                        }
                        else {
                            constraintSet$Constraint0.motion.mQuantizeInterpolatorType = typedArray0.getInteger(v2, constraintSet$Constraint0.motion.mQuantizeInterpolatorID);
                        }
                        constraintSet$Constraint$Delta0.add(88, constraintSet$Constraint0.motion.mQuantizeInterpolatorType);
                    }
                    break;
                }
                case 87: {
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(v2) + "   " + ConstraintSet.sMapToConstant.get(v2));
                    break;
                }
                case 93: {
                    constraintSet$Constraint$Delta0.add(93, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.baselineMargin));
                    break;
                }
                case 94: {
                    constraintSet$Constraint$Delta0.add(94, typedArray0.getDimensionPixelSize(v2, constraintSet$Constraint0.layout.goneBaselineMargin));
                    break;
                }
                case 0x5F: {
                    ConstraintSet.parseDimensionConstraints(constraintSet$Constraint$Delta0, typedArray0, v2, 0);
                    break;
                }
                case 0x60: {
                    ConstraintSet.parseDimensionConstraints(constraintSet$Constraint$Delta0, typedArray0, v2, 1);
                    break;
                }
                case 97: {
                    constraintSet$Constraint$Delta0.add(97, typedArray0.getInt(v2, constraintSet$Constraint0.layout.mWrapBehavior));
                    break;
                }
                case 98: {
                    if(MotionLayout.IS_IN_EDIT_MODE) {
                        constraintSet$Constraint0.mViewId = typedArray0.getResourceId(v2, constraintSet$Constraint0.mViewId);
                        if(constraintSet$Constraint0.mViewId == -1) {
                            constraintSet$Constraint0.mTargetString = typedArray0.getString(v2);
                        }
                    }
                    else if(typedArray0.peekValue(v2).type == 3) {
                        constraintSet$Constraint0.mTargetString = typedArray0.getString(v2);
                    }
                    else {
                        constraintSet$Constraint0.mViewId = typedArray0.getResourceId(v2, constraintSet$Constraint0.mViewId);
                    }
                    break;
                }
                case 99: {
                    constraintSet$Constraint$Delta0.add(99, typedArray0.getBoolean(v2, constraintSet$Constraint0.layout.guidelineUseRtl));
                    break;
                }
                default: {
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(v2) + "   " + ConstraintSet.sMapToConstant.get(v2));
                }
            }
        }
    }

    public void readFallback(ConstraintLayout constraintLayout0) {
        int v = constraintLayout0.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = constraintLayout0.getChildAt(v1);
            LayoutParams constraintLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            int v2 = view0.getId();
            if(this.mForceId && v2 == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if(!this.mConstraints.containsKey(v2)) {
                this.mConstraints.put(v2, new Constraint());
            }
            Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v2);
            if(constraintSet$Constraint0 != null) {
                if(!constraintSet$Constraint0.layout.mApply) {
                    constraintSet$Constraint0.fillFrom(v2, constraintLayout$LayoutParams0);
                    if(view0 instanceof ConstraintHelper) {
                        constraintSet$Constraint0.layout.mReferenceIds = ((ConstraintHelper)view0).getReferencedIds();
                        if(view0 instanceof Barrier) {
                            constraintSet$Constraint0.layout.mBarrierAllowsGoneWidgets = ((Barrier)view0).getAllowsGoneWidget();
                            constraintSet$Constraint0.layout.mBarrierDirection = ((Barrier)view0).getType();
                            constraintSet$Constraint0.layout.mBarrierMargin = ((Barrier)view0).getMargin();
                        }
                    }
                    constraintSet$Constraint0.layout.mApply = true;
                }
                if(!constraintSet$Constraint0.propertySet.mApply) {
                    constraintSet$Constraint0.propertySet.visibility = view0.getVisibility();
                    constraintSet$Constraint0.propertySet.alpha = view0.getAlpha();
                    constraintSet$Constraint0.propertySet.mApply = true;
                }
                if(!constraintSet$Constraint0.transform.mApply) {
                    constraintSet$Constraint0.transform.mApply = true;
                    constraintSet$Constraint0.transform.rotation = view0.getRotation();
                    constraintSet$Constraint0.transform.rotationX = view0.getRotationX();
                    constraintSet$Constraint0.transform.rotationY = view0.getRotationY();
                    constraintSet$Constraint0.transform.scaleX = view0.getScaleX();
                    constraintSet$Constraint0.transform.scaleY = view0.getScaleY();
                    float f = view0.getPivotX();
                    float f1 = view0.getPivotY();
                    if(((double)f) != 0.0 || ((double)f1) != 0.0) {
                        constraintSet$Constraint0.transform.transformPivotX = f;
                        constraintSet$Constraint0.transform.transformPivotY = f1;
                    }
                    constraintSet$Constraint0.transform.translationX = view0.getTranslationX();
                    constraintSet$Constraint0.transform.translationY = view0.getTranslationY();
                    constraintSet$Constraint0.transform.translationZ = view0.getTranslationZ();
                    if(constraintSet$Constraint0.transform.applyElevation) {
                        constraintSet$Constraint0.transform.elevation = view0.getElevation();
                    }
                }
            }
        }
    }

    public void readFallback(ConstraintSet constraintSet0) {
        for(Object object0: constraintSet0.mConstraints.keySet()) {
            Integer integer0 = (Integer)object0;
            integer0.intValue();
            Constraint constraintSet$Constraint0 = (Constraint)constraintSet0.mConstraints.get(integer0);
            if(!this.mConstraints.containsKey(integer0)) {
                this.mConstraints.put(integer0, new Constraint());
            }
            Constraint constraintSet$Constraint1 = (Constraint)this.mConstraints.get(integer0);
            if(constraintSet$Constraint1 != null) {
                if(!constraintSet$Constraint1.layout.mApply) {
                    constraintSet$Constraint1.layout.copyFrom(constraintSet$Constraint0.layout);
                }
                if(!constraintSet$Constraint1.propertySet.mApply) {
                    constraintSet$Constraint1.propertySet.copyFrom(constraintSet$Constraint0.propertySet);
                }
                if(!constraintSet$Constraint1.transform.mApply) {
                    constraintSet$Constraint1.transform.copyFrom(constraintSet$Constraint0.transform);
                }
                if(!constraintSet$Constraint1.motion.mApply) {
                    constraintSet$Constraint1.motion.copyFrom(constraintSet$Constraint0.motion);
                }
                for(Object object1: constraintSet$Constraint0.mCustomConstraints.keySet()) {
                    String s = (String)object1;
                    if(!constraintSet$Constraint1.mCustomConstraints.containsKey(s)) {
                        constraintSet$Constraint1.mCustomConstraints.put(s, ((ConstraintAttribute)constraintSet$Constraint0.mCustomConstraints.get(s)));
                    }
                }
            }
        }
    }

    public void removeAttribute(String s) {
        this.mSavedAttributes.remove(s);
    }

    public void removeFromHorizontalChain(int v) {
        if(this.mConstraints.containsKey(v)) {
            Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v);
            if(constraintSet$Constraint0 != null) {
                int v1 = constraintSet$Constraint0.layout.leftToRight;
                int v2 = constraintSet$Constraint0.layout.rightToLeft;
                if(v1 == -1 && v2 == -1) {
                    int v3 = constraintSet$Constraint0.layout.startToEnd;
                    int v4 = constraintSet$Constraint0.layout.endToStart;
                    if(v3 != -1 || v4 != -1) {
                        if(v3 != -1 && v4 != -1) {
                            this.connect(v3, 7, v4, 6, 0);
                            this.connect(v4, 6, -1, 7, 0);
                        }
                        else if(v4 != -1) {
                            if(constraintSet$Constraint0.layout.rightToRight != -1) {
                                this.connect(-1, 7, constraintSet$Constraint0.layout.rightToRight, 7, 0);
                            }
                            else if(constraintSet$Constraint0.layout.leftToLeft != -1) {
                                this.connect(v4, 6, constraintSet$Constraint0.layout.leftToLeft, 6, 0);
                            }
                        }
                    }
                    this.clear(v, 6);
                    this.clear(v, 7);
                    return;
                }
                if(v1 != -1 && v2 != -1) {
                    this.connect(v1, 2, v2, 1, 0);
                    this.connect(v2, 1, v1, 2, 0);
                }
                else if(constraintSet$Constraint0.layout.rightToRight != -1) {
                    this.connect(v1, 2, constraintSet$Constraint0.layout.rightToRight, 2, 0);
                }
                else if(constraintSet$Constraint0.layout.leftToLeft != -1) {
                    this.connect(v2, 1, constraintSet$Constraint0.layout.leftToLeft, 1, 0);
                }
                this.clear(v, 1);
                this.clear(v, 2);
            }
        }
    }

    public void removeFromVerticalChain(int v) {
        if(this.mConstraints.containsKey(v)) {
            Constraint constraintSet$Constraint0 = (Constraint)this.mConstraints.get(v);
            if(constraintSet$Constraint0 == null) {
                return;
            }
            int v1 = constraintSet$Constraint0.layout.topToBottom;
            int v2 = constraintSet$Constraint0.layout.bottomToTop;
            if(v1 != -1 || v2 != -1) {
                if(v1 != -1 && v2 != -1) {
                    this.connect(v1, 4, v2, 3, 0);
                    this.connect(v2, 3, v1, 4, 0);
                }
                else if(constraintSet$Constraint0.layout.bottomToBottom != -1) {
                    this.connect(v1, 4, constraintSet$Constraint0.layout.bottomToBottom, 4, 0);
                }
                else if(constraintSet$Constraint0.layout.topToTop != -1) {
                    this.connect(v2, 3, constraintSet$Constraint0.layout.topToTop, 3, 0);
                }
            }
        }
        this.clear(v, 3);
        this.clear(v, 4);
    }

    public void setAlpha(int v, float f) {
        this.get(v).propertySet.alpha = f;
    }

    public void setApplyElevation(int v, boolean z) {
        this.get(v).transform.applyElevation = z;
    }

    public void setBarrierType(int v, int v1) {
        this.get(v).layout.mHelperType = v1;
    }

    public void setColorValue(int v, String s, int v1) {
        this.get(v).setColorValue(s, v1);
    }

    private static void setDeltaValue(Constraint constraintSet$Constraint0, int v, float f) {
        switch(v) {
            case 19: {
                constraintSet$Constraint0.layout.guidePercent = f;
                return;
            }
            case 20: {
                constraintSet$Constraint0.layout.horizontalBias = f;
                return;
            }
            case 37: {
                constraintSet$Constraint0.layout.verticalBias = f;
                return;
            }
            case 39: {
                constraintSet$Constraint0.layout.horizontalWeight = f;
                break;
            }
            case 40: {
                constraintSet$Constraint0.layout.verticalWeight = f;
                return;
            }
            case 43: {
                constraintSet$Constraint0.propertySet.alpha = f;
                return;
            }
            case 44: {
                constraintSet$Constraint0.transform.elevation = f;
                constraintSet$Constraint0.transform.applyElevation = true;
                return;
            }
            case 45: {
                constraintSet$Constraint0.transform.rotationX = f;
                return;
            }
            case 46: {
                constraintSet$Constraint0.transform.rotationY = f;
                return;
            }
            case 0x2F: {
                constraintSet$Constraint0.transform.scaleX = f;
                return;
            }
            case 0x30: {
                constraintSet$Constraint0.transform.scaleY = f;
                return;
            }
            case 49: {
                constraintSet$Constraint0.transform.transformPivotX = f;
                return;
            }
            case 50: {
                constraintSet$Constraint0.transform.transformPivotY = f;
                return;
            }
            case 51: {
                constraintSet$Constraint0.transform.translationX = f;
                return;
            }
            case 52: {
                constraintSet$Constraint0.transform.translationY = f;
                return;
            }
            case 53: {
                constraintSet$Constraint0.transform.translationZ = f;
                return;
            }
            case 60: {
                constraintSet$Constraint0.transform.rotation = f;
                return;
            }
            case 0x3F: {
                constraintSet$Constraint0.layout.circleAngle = f;
                return;
            }
            case 67: {
                constraintSet$Constraint0.motion.mPathRotate = f;
                return;
            }
            case 68: {
                constraintSet$Constraint0.propertySet.mProgress = f;
                return;
            }
            case 69: {
                constraintSet$Constraint0.layout.widthPercent = f;
                return;
            }
            case 70: {
                constraintSet$Constraint0.layout.heightPercent = f;
                return;
            }
            case 0x4F: {
                constraintSet$Constraint0.motion.mMotionStagger = f;
                return;
            }
            case 85: {
                constraintSet$Constraint0.motion.mQuantizeMotionPhase = f;
                return;
            }
            case 87: {
                break;
            }
            default: {
                Log.w("ConstraintSet", "Unknown attribute 0x");
            }
        }
    }

    private static void setDeltaValue(Constraint constraintSet$Constraint0, int v, int v1) {
        switch(v) {
            case 2: {
                constraintSet$Constraint0.layout.bottomMargin = v1;
                return;
            }
            case 6: {
                constraintSet$Constraint0.layout.editorAbsoluteX = v1;
                return;
            }
            case 7: {
                constraintSet$Constraint0.layout.editorAbsoluteY = v1;
                return;
            }
            case 8: {
                constraintSet$Constraint0.layout.endMargin = v1;
                return;
            }
            case 11: {
                constraintSet$Constraint0.layout.goneBottomMargin = v1;
                return;
            }
            case 12: {
                constraintSet$Constraint0.layout.goneEndMargin = v1;
                return;
            }
            case 13: {
                constraintSet$Constraint0.layout.goneLeftMargin = v1;
                return;
            }
            case 14: {
                constraintSet$Constraint0.layout.goneRightMargin = v1;
                return;
            }
            case 15: {
                constraintSet$Constraint0.layout.goneStartMargin = v1;
                return;
            }
            case 16: {
                constraintSet$Constraint0.layout.goneTopMargin = v1;
                return;
            }
            case 17: {
                constraintSet$Constraint0.layout.guideBegin = v1;
                return;
            }
            case 18: {
                constraintSet$Constraint0.layout.guideEnd = v1;
                return;
            }
            case 21: {
                constraintSet$Constraint0.layout.mHeight = v1;
                return;
            }
            case 22: {
                constraintSet$Constraint0.propertySet.visibility = v1;
                return;
            }
            case 23: {
                constraintSet$Constraint0.layout.mWidth = v1;
                return;
            }
            case 24: {
                constraintSet$Constraint0.layout.leftMargin = v1;
                return;
            }
            case 27: {
                constraintSet$Constraint0.layout.orientation = v1;
                return;
            }
            case 28: {
                constraintSet$Constraint0.layout.rightMargin = v1;
                return;
            }
            case 0x1F: {
                constraintSet$Constraint0.layout.startMargin = v1;
                return;
            }
            case 34: {
                constraintSet$Constraint0.layout.topMargin = v1;
                return;
            }
            case 38: {
                constraintSet$Constraint0.mViewId = v1;
                return;
            }
            case 41: {
                constraintSet$Constraint0.layout.horizontalChainStyle = v1;
                return;
            }
            case 42: {
                constraintSet$Constraint0.layout.verticalChainStyle = v1;
                return;
            }
            case 54: {
                constraintSet$Constraint0.layout.widthDefault = v1;
                return;
            }
            case 55: {
                constraintSet$Constraint0.layout.heightDefault = v1;
                return;
            }
            case 56: {
                constraintSet$Constraint0.layout.widthMax = v1;
                return;
            }
            case 57: {
                constraintSet$Constraint0.layout.heightMax = v1;
                return;
            }
            case 58: {
                constraintSet$Constraint0.layout.widthMin = v1;
                return;
            }
            case 59: {
                constraintSet$Constraint0.layout.heightMin = v1;
                return;
            }
            case 61: {
                constraintSet$Constraint0.layout.circleConstraint = v1;
                return;
            }
            case 62: {
                constraintSet$Constraint0.layout.circleRadius = v1;
                return;
            }
            case 0x40: {
                constraintSet$Constraint0.motion.mAnimateRelativeTo = v1;
                return;
            }
            case 66: {
                constraintSet$Constraint0.motion.mDrawPath = v1;
                return;
            }
            case 72: {
                constraintSet$Constraint0.layout.mBarrierDirection = v1;
                return;
            }
            case 73: {
                constraintSet$Constraint0.layout.mBarrierMargin = v1;
                return;
            }
            case 76: {
                constraintSet$Constraint0.motion.mPathMotionArc = v1;
                return;
            }
            case 78: {
                constraintSet$Constraint0.propertySet.mVisibilityMode = v1;
                return;
            }
            case 82: {
                constraintSet$Constraint0.motion.mAnimateCircleAngleTo = v1;
                return;
            }
            case 83: {
                constraintSet$Constraint0.transform.transformPivotTarget = v1;
                return;
            }
            case 84: {
                constraintSet$Constraint0.motion.mQuantizeMotionSteps = v1;
                return;
            }
            case 87: {
                break;
            }
            case 88: {
                constraintSet$Constraint0.motion.mQuantizeInterpolatorType = v1;
                break;
            }
            case 89: {
                constraintSet$Constraint0.motion.mQuantizeInterpolatorID = v1;
                return;
            }
            case 93: {
                constraintSet$Constraint0.layout.baselineMargin = v1;
                return;
            }
            case 94: {
                constraintSet$Constraint0.layout.goneBaselineMargin = v1;
                return;
            }
            case 97: {
                constraintSet$Constraint0.layout.mWrapBehavior = v1;
                return;
            }
            default: {
                Log.w("ConstraintSet", "Unknown attribute 0x");
            }
        }
    }

    private static void setDeltaValue(Constraint constraintSet$Constraint0, int v, String s) {
        switch(v) {
            case 5: {
                constraintSet$Constraint0.layout.dimensionRatio = s;
                return;
            }
            case 65: {
                constraintSet$Constraint0.motion.mTransitionEasing = s;
                return;
            }
            case 74: {
                constraintSet$Constraint0.layout.mReferenceIdString = s;
                constraintSet$Constraint0.layout.mReferenceIds = null;
                return;
            }
            case 77: {
                constraintSet$Constraint0.layout.mConstraintTag = s;
                return;
            }
            case 87: {
                break;
            }
            case 90: {
                constraintSet$Constraint0.motion.mQuantizeInterpolatorString = s;
                break;
            }
            default: {
                Log.w("ConstraintSet", "Unknown attribute 0x");
            }
        }
    }

    private static void setDeltaValue(Constraint constraintSet$Constraint0, int v, boolean z) {
        switch(v) {
            case 44: {
                constraintSet$Constraint0.transform.applyElevation = z;
                return;
            }
            case 75: {
                constraintSet$Constraint0.layout.mBarrierAllowsGoneWidgets = z;
                return;
            }
            case 80: {
                constraintSet$Constraint0.layout.constrainedWidth = z;
                break;
            }
            case 81: {
                constraintSet$Constraint0.layout.constrainedHeight = z;
                return;
            }
            case 87: {
                break;
            }
            default: {
                Log.w("ConstraintSet", "Unknown attribute 0x");
            }
        }
    }

    public void setDimensionRatio(int v, String s) {
        this.get(v).layout.dimensionRatio = s;
    }

    public void setEditorAbsoluteX(int v, int v1) {
        this.get(v).layout.editorAbsoluteX = v1;
    }

    public void setEditorAbsoluteY(int v, int v1) {
        this.get(v).layout.editorAbsoluteY = v1;
    }

    public void setElevation(int v, float f) {
        this.get(v).transform.elevation = f;
        this.get(v).transform.applyElevation = true;
    }

    public void setFloatValue(int v, String s, float f) {
        this.get(v).setFloatValue(s, f);
    }

    public void setForceId(boolean z) {
        this.mForceId = z;
    }

    public void setGoneMargin(int v, int v1, int v2) {
        Constraint constraintSet$Constraint0 = this.get(v);
        switch(v1) {
            case 1: {
                constraintSet$Constraint0.layout.goneLeftMargin = v2;
                return;
            }
            case 2: {
                constraintSet$Constraint0.layout.goneRightMargin = v2;
                return;
            }
            case 3: {
                constraintSet$Constraint0.layout.goneTopMargin = v2;
                return;
            }
            case 4: {
                constraintSet$Constraint0.layout.goneBottomMargin = v2;
                return;
            }
            case 5: {
                constraintSet$Constraint0.layout.goneBaselineMargin = v2;
                return;
            }
            case 6: {
                constraintSet$Constraint0.layout.goneStartMargin = v2;
                return;
            }
            case 7: {
                constraintSet$Constraint0.layout.goneEndMargin = v2;
                return;
            }
            default: {
                throw new IllegalArgumentException("unknown constraint");
            }
        }
    }

    public void setGuidelineBegin(int v, int v1) {
        this.get(v).layout.guideBegin = v1;
        this.get(v).layout.guideEnd = -1;
        this.get(v).layout.guidePercent = -1.0f;
    }

    public void setGuidelineEnd(int v, int v1) {
        this.get(v).layout.guideEnd = v1;
        this.get(v).layout.guideBegin = -1;
        this.get(v).layout.guidePercent = -1.0f;
    }

    public void setGuidelinePercent(int v, float f) {
        this.get(v).layout.guidePercent = f;
        this.get(v).layout.guideEnd = -1;
        this.get(v).layout.guideBegin = -1;
    }

    public void setHorizontalBias(int v, float f) {
        this.get(v).layout.horizontalBias = f;
    }

    public void setHorizontalChainStyle(int v, int v1) {
        this.get(v).layout.horizontalChainStyle = v1;
    }

    public void setHorizontalWeight(int v, float f) {
        this.get(v).layout.horizontalWeight = f;
    }

    public void setIntValue(int v, String s, int v1) {
        this.get(v).setIntValue(s, v1);
    }

    public void setLayoutWrapBehavior(int v, int v1) {
        if(v1 >= 0 && v1 <= 3) {
            this.get(v).layout.mWrapBehavior = v1;
        }
    }

    public void setMargin(int v, int v1, int v2) {
        Constraint constraintSet$Constraint0 = this.get(v);
        switch(v1) {
            case 1: {
                constraintSet$Constraint0.layout.leftMargin = v2;
                return;
            }
            case 2: {
                constraintSet$Constraint0.layout.rightMargin = v2;
                return;
            }
            case 3: {
                constraintSet$Constraint0.layout.topMargin = v2;
                return;
            }
            case 4: {
                constraintSet$Constraint0.layout.bottomMargin = v2;
                return;
            }
            case 5: {
                constraintSet$Constraint0.layout.baselineMargin = v2;
                return;
            }
            case 6: {
                constraintSet$Constraint0.layout.startMargin = v2;
                return;
            }
            case 7: {
                constraintSet$Constraint0.layout.endMargin = v2;
                return;
            }
            default: {
                throw new IllegalArgumentException("unknown constraint");
            }
        }
    }

    public void setReferencedIds(int v, int[] arr_v) {
        this.get(v).layout.mReferenceIds = arr_v;
    }

    public void setRotation(int v, float f) {
        this.get(v).transform.rotation = f;
    }

    public void setRotationX(int v, float f) {
        this.get(v).transform.rotationX = f;
    }

    public void setRotationY(int v, float f) {
        this.get(v).transform.rotationY = f;
    }

    public void setScaleX(int v, float f) {
        this.get(v).transform.scaleX = f;
    }

    public void setScaleY(int v, float f) {
        this.get(v).transform.scaleY = f;
    }

    public void setStateLabels(String s) {
        this.mMatchLabels = s.split(",");
        for(int v = 0; true; ++v) {
            String[] arr_s = this.mMatchLabels;
            if(v >= arr_s.length) {
                break;
            }
            arr_s[v] = arr_s[v].trim();
        }
    }

    public void setStateLabelsList(String[] arr_s) {
        this.mMatchLabels = arr_s;
        for(int v = 0; true; ++v) {
            String[] arr_s1 = this.mMatchLabels;
            if(v >= arr_s1.length) {
                break;
            }
            arr_s1[v] = arr_s1[v].trim();
        }
    }

    public void setStringValue(int v, String s, String s1) {
        this.get(v).setStringValue(s, s1);
    }

    public void setTransformPivot(int v, float f, float f1) {
        Constraint constraintSet$Constraint0 = this.get(v);
        constraintSet$Constraint0.transform.transformPivotY = f1;
        constraintSet$Constraint0.transform.transformPivotX = f;
    }

    public void setTransformPivotX(int v, float f) {
        this.get(v).transform.transformPivotX = f;
    }

    public void setTransformPivotY(int v, float f) {
        this.get(v).transform.transformPivotY = f;
    }

    public void setTranslation(int v, float f, float f1) {
        Constraint constraintSet$Constraint0 = this.get(v);
        constraintSet$Constraint0.transform.translationX = f;
        constraintSet$Constraint0.transform.translationY = f1;
    }

    public void setTranslationX(int v, float f) {
        this.get(v).transform.translationX = f;
    }

    public void setTranslationY(int v, float f) {
        this.get(v).transform.translationY = f;
    }

    public void setTranslationZ(int v, float f) {
        this.get(v).transform.translationZ = f;
    }

    public void setValidateOnParse(boolean z) {
        this.mValidate = z;
    }

    public void setVerticalBias(int v, float f) {
        this.get(v).layout.verticalBias = f;
    }

    public void setVerticalChainStyle(int v, int v1) {
        this.get(v).layout.verticalChainStyle = v1;
    }

    public void setVerticalWeight(int v, float f) {
        this.get(v).layout.verticalWeight = f;
    }

    public void setVisibility(int v, int v1) {
        this.get(v).propertySet.visibility = v1;
    }

    public void setVisibilityMode(int v, int v1) {
        this.get(v).propertySet.mVisibilityMode = v1;
    }

    private String sideToString(int v) {
        switch(v) {
            case 1: {
                return "left";
            }
            case 2: {
                return "right";
            }
            case 3: {
                return "top";
            }
            case 4: {
                return "bottom";
            }
            case 5: {
                return "baseline";
            }
            case 6: {
                return "start";
            }
            case 7: {
                return "end";
            }
            default: {
                return "undefined";
            }
        }
    }

    private static String[] splitString(String s) {
        char[] arr_c = s.toCharArray();
        ArrayList arrayList0 = new ArrayList();
        int v1 = 0;
        int v2 = 0;
        for(int v = 0; v < arr_c.length; ++v) {
            int v3 = arr_c[v];
            if(v3 == 44 && v2 == 0) {
                arrayList0.add(new String(arr_c, v1, v - v1));
                v1 = v + 1;
            }
            else if(v3 == 34) {
                v2 ^= 1;
            }
        }
        arrayList0.add(new String(arr_c, v1, arr_c.length - v1));
        return (String[])arrayList0.toArray(new String[arrayList0.size()]);
    }

    public void writeState(Writer writer0, ConstraintLayout constraintLayout0, int v) throws IOException {
        writer0.write("\n---------------------------------------------\n");
        if((v & 1) == 1) {
            new WriteXmlEngine(this, writer0, constraintLayout0, v).writeLayout();
        }
        else {
            new WriteJsonEngine(this, writer0, constraintLayout0, v).writeLayout();
        }
        writer0.write("\n---------------------------------------------\n");
    }
}

