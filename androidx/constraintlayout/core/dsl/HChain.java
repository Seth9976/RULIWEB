package androidx.constraintlayout.core.dsl;

public class HChain extends Chain {
    public class HAnchor extends Anchor {
        HAnchor(HSide constraint$HSide0) {
            super(Side.valueOf(constraint$HSide0.name()));
        }
    }

    private HAnchor mEnd;
    private HAnchor mLeft;
    private HAnchor mRight;
    private HAnchor mStart;

    public HChain(String s) {
        super(s);
        this.mLeft = new HAnchor(this, HSide.LEFT);
        this.mRight = new HAnchor(this, HSide.RIGHT);
        this.mStart = new HAnchor(this, HSide.START);
        this.mEnd = new HAnchor(this, HSide.END);
        this.type = new HelperType("hChain");
    }

    public HChain(String s, String s1) {
        super(s);
        this.mLeft = new HAnchor(this, HSide.LEFT);
        this.mRight = new HAnchor(this, HSide.RIGHT);
        this.mStart = new HAnchor(this, HSide.START);
        this.mEnd = new HAnchor(this, HSide.END);
        this.config = s1;
        this.type = new HelperType("hChain");
        this.configMap = this.convertConfigToMap();
        if(this.configMap.containsKey("contains")) {
            Ref.addStringToReferences(((String)this.configMap.get("contains")), this.references);
        }
    }

    public HAnchor getEnd() {
        return this.mEnd;
    }

    public HAnchor getLeft() {
        return this.mLeft;
    }

    public HAnchor getRight() {
        return this.mRight;
    }

    public HAnchor getStart() {
        return this.mStart;
    }

    public void linkToEnd(androidx.constraintlayout.core.dsl.Constraint.HAnchor constraint$HAnchor0) {
        this.linkToEnd(constraint$HAnchor0, 0);
    }

    public void linkToEnd(androidx.constraintlayout.core.dsl.Constraint.HAnchor constraint$HAnchor0, int v) {
        this.linkToEnd(constraint$HAnchor0, v, 0x80000000);
    }

    public void linkToEnd(androidx.constraintlayout.core.dsl.Constraint.HAnchor constraint$HAnchor0, int v, int v1) {
        this.mEnd.mConnection = constraint$HAnchor0;
        this.mEnd.mMargin = v;
        this.mEnd.mGoneMargin = v1;
        this.configMap.put("end", this.mEnd.toString());
    }

    public void linkToLeft(androidx.constraintlayout.core.dsl.Constraint.HAnchor constraint$HAnchor0) {
        this.linkToLeft(constraint$HAnchor0, 0);
    }

    public void linkToLeft(androidx.constraintlayout.core.dsl.Constraint.HAnchor constraint$HAnchor0, int v) {
        this.linkToLeft(constraint$HAnchor0, v, 0x80000000);
    }

    public void linkToLeft(androidx.constraintlayout.core.dsl.Constraint.HAnchor constraint$HAnchor0, int v, int v1) {
        this.mLeft.mConnection = constraint$HAnchor0;
        this.mLeft.mMargin = v;
        this.mLeft.mGoneMargin = v1;
        this.configMap.put("left", this.mLeft.toString());
    }

    public void linkToRight(androidx.constraintlayout.core.dsl.Constraint.HAnchor constraint$HAnchor0) {
        this.linkToRight(constraint$HAnchor0, 0);
    }

    public void linkToRight(androidx.constraintlayout.core.dsl.Constraint.HAnchor constraint$HAnchor0, int v) {
        this.linkToRight(constraint$HAnchor0, v, 0x80000000);
    }

    public void linkToRight(androidx.constraintlayout.core.dsl.Constraint.HAnchor constraint$HAnchor0, int v, int v1) {
        this.mRight.mConnection = constraint$HAnchor0;
        this.mRight.mMargin = v;
        this.mRight.mGoneMargin = v1;
        this.configMap.put("right", this.mRight.toString());
    }

    public void linkToStart(androidx.constraintlayout.core.dsl.Constraint.HAnchor constraint$HAnchor0) {
        this.linkToStart(constraint$HAnchor0, 0);
    }

    public void linkToStart(androidx.constraintlayout.core.dsl.Constraint.HAnchor constraint$HAnchor0, int v) {
        this.linkToStart(constraint$HAnchor0, v, 0x80000000);
    }

    public void linkToStart(androidx.constraintlayout.core.dsl.Constraint.HAnchor constraint$HAnchor0, int v, int v1) {
        this.mStart.mConnection = constraint$HAnchor0;
        this.mStart.mMargin = v;
        this.mStart.mGoneMargin = v1;
        this.configMap.put("start", this.mStart.toString());
    }
}

