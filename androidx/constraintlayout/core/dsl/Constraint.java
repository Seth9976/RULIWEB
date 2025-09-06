package androidx.constraintlayout.core.dsl;

import java.util.HashMap;
import java.util.Map;

public class Constraint {
    public class Anchor {
        Anchor mConnection;
        int mGoneMargin;
        int mMargin;
        final Side mSide;

        Anchor(Side constraint$Side0) {
            this.mConnection = null;
            this.mGoneMargin = 0x80000000;
            this.mSide = constraint$Side0;
        }

        public void build(StringBuilder stringBuilder0) {
            if(this.mConnection != null) {
                stringBuilder0.append(this.mSide.toString().toLowerCase());
                stringBuilder0.append(":");
                stringBuilder0.append(this);
                stringBuilder0.append(",\n");
            }
        }

        public String getId() {
            return Constraint.this.mId;
        }

        Constraint getParent() {
            return Constraint.this;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder0 = new StringBuilder("[");
            if(this.mConnection != null) {
                stringBuilder0.append("\'");
                stringBuilder0.append(this.mConnection.getId());
                stringBuilder0.append("\',\'");
                stringBuilder0.append(this.mConnection.mSide.toString().toLowerCase());
                stringBuilder0.append("\'");
            }
            if(this.mMargin != 0) {
                stringBuilder0.append(",");
                stringBuilder0.append(this.mMargin);
            }
            if(this.mGoneMargin != 0x80000000) {
                if(this.mMargin == 0) {
                    stringBuilder0.append(",0,");
                }
                else {
                    stringBuilder0.append(",");
                }
                stringBuilder0.append(this.mGoneMargin);
            }
            stringBuilder0.append("]");
            return stringBuilder0.toString();
        }
    }

    public static enum Behaviour {
        SPREAD,
        WRAP,
        PERCENT,
        RATIO,
        RESOLVED;

        private static Behaviour[] $values() [...] // Inlined contents
    }

    public static enum ChainMode {
        SPREAD,
        SPREAD_INSIDE,
        PACKED;

        private static ChainMode[] $values() [...] // Inlined contents
    }

    public class HAnchor extends Anchor {
        HAnchor(HSide constraint$HSide0) {
            super(Side.valueOf(constraint$HSide0.name()));
        }
    }

    public static enum HSide {
        LEFT,
        RIGHT,
        START,
        END;

        private static HSide[] $values() [...] // Inlined contents
    }

    public static enum Side {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        START,
        END,
        BASELINE;

        private static Side[] $values() [...] // Inlined contents
    }

    public class VAnchor extends Anchor {
        VAnchor(VSide constraint$VSide0) {
            super(Side.valueOf(constraint$VSide0.name()));
        }
    }

    public static enum VSide {
        TOP,
        BOTTOM,
        BASELINE;

        private static VSide[] $values() [...] // Inlined contents
    }

    public static final Constraint PARENT;
    static int UNSET;
    static Map chainModeMap;
    String helperJason;
    String helperType;
    private VAnchor mBaseline;
    private VAnchor mBottom;
    private float mCircleAngle;
    private String mCircleConstraint;
    private int mCircleRadius;
    private boolean mConstrainedHeight;
    private boolean mConstrainedWidth;
    private String mDimensionRatio;
    private int mEditorAbsoluteX;
    private int mEditorAbsoluteY;
    private HAnchor mEnd;
    private int mHeight;
    private Behaviour mHeightDefault;
    private int mHeightMax;
    private int mHeightMin;
    private float mHeightPercent;
    private float mHorizontalBias;
    private ChainMode mHorizontalChainStyle;
    private float mHorizontalWeight;
    private final String mId;
    private HAnchor mLeft;
    private String[] mReferenceIds;
    private HAnchor mRight;
    private HAnchor mStart;
    private VAnchor mTop;
    private float mVerticalBias;
    private ChainMode mVerticalChainStyle;
    private float mVerticalWeight;
    private int mWidth;
    private Behaviour mWidthDefault;
    private int mWidthMax;
    private int mWidthMin;
    private float mWidthPercent;

    static {
        Constraint.PARENT = new Constraint("parent");
        Constraint.UNSET = 0x80000000;
        HashMap hashMap0 = new HashMap();
        Constraint.chainModeMap = hashMap0;
        hashMap0.put(ChainMode.SPREAD, "spread");
        Constraint.chainModeMap.put(ChainMode.SPREAD_INSIDE, "spread_inside");
        Constraint.chainModeMap.put(ChainMode.PACKED, "packed");
    }

    public Constraint(String s) {
        this.helperType = null;
        this.helperJason = null;
        this.mLeft = new HAnchor(this, HSide.LEFT);
        this.mRight = new HAnchor(this, HSide.RIGHT);
        this.mTop = new VAnchor(this, VSide.TOP);
        this.mBottom = new VAnchor(this, VSide.BOTTOM);
        this.mStart = new HAnchor(this, HSide.START);
        this.mEnd = new HAnchor(this, HSide.END);
        this.mBaseline = new VAnchor(this, VSide.BASELINE);
        this.mWidth = Constraint.UNSET;
        this.mHeight = Constraint.UNSET;
        this.mHorizontalBias = NaNf;
        this.mVerticalBias = NaNf;
        this.mDimensionRatio = null;
        this.mCircleConstraint = null;
        this.mCircleRadius = 0x80000000;
        this.mCircleAngle = NaNf;
        this.mEditorAbsoluteX = 0x80000000;
        this.mEditorAbsoluteY = 0x80000000;
        this.mVerticalWeight = NaNf;
        this.mHorizontalWeight = NaNf;
        this.mHorizontalChainStyle = null;
        this.mVerticalChainStyle = null;
        this.mWidthDefault = null;
        this.mHeightDefault = null;
        this.mWidthMax = Constraint.UNSET;
        this.mHeightMax = Constraint.UNSET;
        this.mWidthMin = Constraint.UNSET;
        this.mHeightMin = Constraint.UNSET;
        this.mWidthPercent = NaNf;
        this.mHeightPercent = NaNf;
        this.mReferenceIds = null;
        this.mConstrainedWidth = false;
        this.mConstrainedHeight = false;
        this.mId = s;
    }

    protected void append(StringBuilder stringBuilder0, String s, float f) {
        if(Float.isNaN(f)) {
            return;
        }
        stringBuilder0.append(s);
        stringBuilder0.append(":");
        stringBuilder0.append(f);
        stringBuilder0.append(",\n");
    }

    public String convertStringArrayToString(String[] arr_s) {
        StringBuilder stringBuilder0 = new StringBuilder("[");
        for(int v = 0; v < arr_s.length; ++v) {
            stringBuilder0.append((v == 0 ? "\'" : ",\'"));
            stringBuilder0.append(arr_s[v]);
            stringBuilder0.append("\'");
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }

    public VAnchor getBaseline() {
        return this.mBaseline;
    }

    public VAnchor getBottom() {
        return this.mBottom;
    }

    public float getCircleAngle() {
        return this.mCircleAngle;
    }

    public String getCircleConstraint() {
        return this.mCircleConstraint;
    }

    public int getCircleRadius() {
        return this.mCircleRadius;
    }

    public String getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getEditorAbsoluteX() {
        return this.mEditorAbsoluteX;
    }

    public int getEditorAbsoluteY() {
        return this.mEditorAbsoluteY;
    }

    public HAnchor getEnd() {
        return this.mEnd;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public Behaviour getHeightDefault() {
        return this.mHeightDefault;
    }

    public int getHeightMax() {
        return this.mHeightMax;
    }

    public int getHeightMin() {
        return this.mHeightMin;
    }

    public float getHeightPercent() {
        return this.mHeightPercent;
    }

    public float getHorizontalBias() {
        return this.mHorizontalBias;
    }

    public ChainMode getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public float getHorizontalWeight() {
        return this.mHorizontalWeight;
    }

    public HAnchor getLeft() {
        return this.mLeft;
    }

    public String[] getReferenceIds() {
        return this.mReferenceIds;
    }

    public HAnchor getRight() {
        return this.mRight;
    }

    public HAnchor getStart() {
        return this.mStart;
    }

    public VAnchor getTop() {
        return this.mTop;
    }

    public float getVerticalBias() {
        return this.mVerticalBias;
    }

    public ChainMode getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public float getVerticalWeight() {
        return this.mVerticalWeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public Behaviour getWidthDefault() {
        return this.mWidthDefault;
    }

    public int getWidthMax() {
        return this.mWidthMax;
    }

    public int getWidthMin() {
        return this.mWidthMin;
    }

    public float getWidthPercent() {
        return this.mWidthPercent;
    }

    public boolean isConstrainedHeight() {
        return this.mConstrainedHeight;
    }

    public boolean isConstrainedWidth() {
        return this.mConstrainedWidth;
    }

    public void linkToBaseline(VAnchor constraint$VAnchor0) {
        this.linkToBaseline(constraint$VAnchor0, 0);
    }

    public void linkToBaseline(VAnchor constraint$VAnchor0, int v) {
        this.linkToBaseline(constraint$VAnchor0, v, 0x80000000);
    }

    public void linkToBaseline(VAnchor constraint$VAnchor0, int v, int v1) {
        this.mBaseline.mConnection = constraint$VAnchor0;
        this.mBaseline.mMargin = v;
        this.mBaseline.mGoneMargin = v1;
    }

    public void linkToBottom(VAnchor constraint$VAnchor0) {
        this.linkToBottom(constraint$VAnchor0, 0);
    }

    public void linkToBottom(VAnchor constraint$VAnchor0, int v) {
        this.linkToBottom(constraint$VAnchor0, v, 0x80000000);
    }

    public void linkToBottom(VAnchor constraint$VAnchor0, int v, int v1) {
        this.mBottom.mConnection = constraint$VAnchor0;
        this.mBottom.mMargin = v;
        this.mBottom.mGoneMargin = v1;
    }

    public void linkToEnd(HAnchor constraint$HAnchor0) {
        this.linkToEnd(constraint$HAnchor0, 0);
    }

    public void linkToEnd(HAnchor constraint$HAnchor0, int v) {
        this.linkToEnd(constraint$HAnchor0, v, 0x80000000);
    }

    public void linkToEnd(HAnchor constraint$HAnchor0, int v, int v1) {
        this.mEnd.mConnection = constraint$HAnchor0;
        this.mEnd.mMargin = v;
        this.mEnd.mGoneMargin = v1;
    }

    public void linkToLeft(HAnchor constraint$HAnchor0) {
        this.linkToLeft(constraint$HAnchor0, 0);
    }

    public void linkToLeft(HAnchor constraint$HAnchor0, int v) {
        this.linkToLeft(constraint$HAnchor0, v, 0x80000000);
    }

    public void linkToLeft(HAnchor constraint$HAnchor0, int v, int v1) {
        this.mLeft.mConnection = constraint$HAnchor0;
        this.mLeft.mMargin = v;
        this.mLeft.mGoneMargin = v1;
    }

    public void linkToRight(HAnchor constraint$HAnchor0) {
        this.linkToRight(constraint$HAnchor0, 0);
    }

    public void linkToRight(HAnchor constraint$HAnchor0, int v) {
        this.linkToRight(constraint$HAnchor0, v, 0x80000000);
    }

    public void linkToRight(HAnchor constraint$HAnchor0, int v, int v1) {
        this.mRight.mConnection = constraint$HAnchor0;
        this.mRight.mMargin = v;
        this.mRight.mGoneMargin = v1;
    }

    public void linkToStart(HAnchor constraint$HAnchor0) {
        this.linkToStart(constraint$HAnchor0, 0);
    }

    public void linkToStart(HAnchor constraint$HAnchor0, int v) {
        this.linkToStart(constraint$HAnchor0, v, 0x80000000);
    }

    public void linkToStart(HAnchor constraint$HAnchor0, int v, int v1) {
        this.mStart.mConnection = constraint$HAnchor0;
        this.mStart.mMargin = v;
        this.mStart.mGoneMargin = v1;
    }

    public void linkToTop(VAnchor constraint$VAnchor0) {
        this.linkToTop(constraint$VAnchor0, 0);
    }

    public void linkToTop(VAnchor constraint$VAnchor0, int v) {
        this.linkToTop(constraint$VAnchor0, v, 0x80000000);
    }

    public void linkToTop(VAnchor constraint$VAnchor0, int v, int v1) {
        this.mTop.mConnection = constraint$VAnchor0;
        this.mTop.mMargin = v;
        this.mTop.mGoneMargin = v1;
    }

    public void setCircleAngle(float f) {
        this.mCircleAngle = f;
    }

    public void setCircleConstraint(String s) {
        this.mCircleConstraint = s;
    }

    public void setCircleRadius(int v) {
        this.mCircleRadius = v;
    }

    public void setConstrainedHeight(boolean z) {
        this.mConstrainedHeight = z;
    }

    public void setConstrainedWidth(boolean z) {
        this.mConstrainedWidth = z;
    }

    public void setDimensionRatio(String s) {
        this.mDimensionRatio = s;
    }

    public void setEditorAbsoluteX(int v) {
        this.mEditorAbsoluteX = v;
    }

    public void setEditorAbsoluteY(int v) {
        this.mEditorAbsoluteY = v;
    }

    public void setHeight(int v) {
        this.mHeight = v;
    }

    public void setHeightDefault(Behaviour constraint$Behaviour0) {
        this.mHeightDefault = constraint$Behaviour0;
    }

    public void setHeightMax(int v) {
        this.mHeightMax = v;
    }

    public void setHeightMin(int v) {
        this.mHeightMin = v;
    }

    public void setHeightPercent(float f) {
        this.mHeightPercent = f;
    }

    public void setHorizontalBias(float f) {
        this.mHorizontalBias = f;
    }

    public void setHorizontalChainStyle(ChainMode constraint$ChainMode0) {
        this.mHorizontalChainStyle = constraint$ChainMode0;
    }

    public void setHorizontalWeight(float f) {
        this.mHorizontalWeight = f;
    }

    public void setReferenceIds(String[] arr_s) {
        this.mReferenceIds = arr_s;
    }

    public void setVerticalBias(float f) {
        this.mVerticalBias = f;
    }

    public void setVerticalChainStyle(ChainMode constraint$ChainMode0) {
        this.mVerticalChainStyle = constraint$ChainMode0;
    }

    public void setVerticalWeight(float f) {
        this.mVerticalWeight = f;
    }

    public void setWidth(int v) {
        this.mWidth = v;
    }

    public void setWidthDefault(Behaviour constraint$Behaviour0) {
        this.mWidthDefault = constraint$Behaviour0;
    }

    public void setWidthMax(int v) {
        this.mWidthMax = v;
    }

    public void setWidthMin(int v) {
        this.mWidthMin = v;
    }

    public void setWidthPercent(float f) {
        this.mWidthPercent = f;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(this.mId + ":{\n");
        this.mLeft.build(stringBuilder0);
        this.mRight.build(stringBuilder0);
        this.mTop.build(stringBuilder0);
        this.mBottom.build(stringBuilder0);
        this.mStart.build(stringBuilder0);
        this.mEnd.build(stringBuilder0);
        this.mBaseline.build(stringBuilder0);
        if(this.mWidth != Constraint.UNSET) {
            stringBuilder0.append("width:");
            stringBuilder0.append(this.mWidth);
            stringBuilder0.append(",\n");
        }
        if(this.mHeight != Constraint.UNSET) {
            stringBuilder0.append("height:");
            stringBuilder0.append(this.mHeight);
            stringBuilder0.append(",\n");
        }
        this.append(stringBuilder0, "horizontalBias", this.mHorizontalBias);
        this.append(stringBuilder0, "verticalBias", this.mVerticalBias);
        if(this.mDimensionRatio != null) {
            stringBuilder0.append("dimensionRatio:\'");
            stringBuilder0.append(this.mDimensionRatio);
            stringBuilder0.append("\',\n");
        }
        if(this.mCircleConstraint != null && (!Float.isNaN(this.mCircleAngle) || this.mCircleRadius != 0x80000000)) {
            stringBuilder0.append("circular:[\'");
            stringBuilder0.append(this.mCircleConstraint);
            stringBuilder0.append("\'");
            if(!Float.isNaN(this.mCircleAngle)) {
                stringBuilder0.append(",");
                stringBuilder0.append(this.mCircleAngle);
            }
            if(this.mCircleRadius != 0x80000000) {
                if(Float.isNaN(this.mCircleAngle)) {
                    stringBuilder0.append(",0,");
                }
                else {
                    stringBuilder0.append(",");
                }
                stringBuilder0.append(this.mCircleRadius);
            }
            stringBuilder0.append("],\n");
        }
        this.append(stringBuilder0, "verticalWeight", this.mVerticalWeight);
        this.append(stringBuilder0, "horizontalWeight", this.mHorizontalWeight);
        if(this.mHorizontalChainStyle != null) {
            stringBuilder0.append("horizontalChainStyle:\'");
            stringBuilder0.append(((String)Constraint.chainModeMap.get(this.mHorizontalChainStyle)));
            stringBuilder0.append("\',\n");
        }
        if(this.mVerticalChainStyle != null) {
            stringBuilder0.append("verticalChainStyle:\'");
            stringBuilder0.append(((String)Constraint.chainModeMap.get(this.mVerticalChainStyle)));
            stringBuilder0.append("\',\n");
        }
        if(this.mWidthDefault != null) {
            if(this.mWidthMax != Constraint.UNSET || this.mWidthMin != Constraint.UNSET) {
                stringBuilder0.append("width:{value:\'");
                stringBuilder0.append(this.mWidthDefault.toString().toLowerCase());
                stringBuilder0.append("\'");
                if(this.mWidthMax != Constraint.UNSET) {
                    stringBuilder0.append(",max:");
                    stringBuilder0.append(this.mWidthMax);
                }
                if(this.mWidthMin != Constraint.UNSET) {
                    stringBuilder0.append(",min:");
                    stringBuilder0.append(this.mWidthMin);
                }
                stringBuilder0.append("},\n");
            }
            else {
                stringBuilder0.append("width:\'");
                stringBuilder0.append(this.mWidthDefault.toString().toLowerCase());
                stringBuilder0.append("\',\n");
            }
        }
        if(this.mHeightDefault != null) {
            if(this.mHeightMax != Constraint.UNSET || this.mHeightMin != Constraint.UNSET) {
                stringBuilder0.append("height:{value:\'");
                stringBuilder0.append(this.mHeightDefault.toString().toLowerCase());
                stringBuilder0.append("\'");
                if(this.mHeightMax != Constraint.UNSET) {
                    stringBuilder0.append(",max:");
                    stringBuilder0.append(this.mHeightMax);
                }
                if(this.mHeightMin != Constraint.UNSET) {
                    stringBuilder0.append(",min:");
                    stringBuilder0.append(this.mHeightMin);
                }
                stringBuilder0.append("},\n");
            }
            else {
                stringBuilder0.append("height:\'");
                stringBuilder0.append(this.mHeightDefault.toString().toLowerCase());
                stringBuilder0.append("\',\n");
            }
        }
        if(!Double.isNaN(this.mWidthPercent)) {
            stringBuilder0.append("width:\'");
            stringBuilder0.append(((int)this.mWidthPercent));
            stringBuilder0.append("%\',\n");
        }
        if(!Double.isNaN(this.mHeightPercent)) {
            stringBuilder0.append("height:\'");
            stringBuilder0.append(((int)this.mHeightPercent));
            stringBuilder0.append("%\',\n");
        }
        if(this.mReferenceIds != null) {
            stringBuilder0.append("referenceIds:");
            stringBuilder0.append(this.convertStringArrayToString(this.mReferenceIds));
            stringBuilder0.append(",\n");
        }
        if(this.mConstrainedWidth) {
            stringBuilder0.append("constrainedWidth:");
            stringBuilder0.append(this.mConstrainedWidth);
            stringBuilder0.append(",\n");
        }
        if(this.mConstrainedHeight) {
            stringBuilder0.append("constrainedHeight:");
            stringBuilder0.append(this.mConstrainedHeight);
            stringBuilder0.append(",\n");
        }
        stringBuilder0.append("},\n");
        return stringBuilder0.toString();
    }
}

