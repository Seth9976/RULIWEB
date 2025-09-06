package androidx.constraintlayout.core.dsl;

public class VChain extends Chain {
    public class VAnchor extends Anchor {
        VAnchor(VSide constraint$VSide0) {
            super(Side.valueOf(constraint$VSide0.name()));
        }
    }

    private VAnchor mBaseline;
    private VAnchor mBottom;
    private VAnchor mTop;

    public VChain(String s) {
        super(s);
        this.mTop = new VAnchor(this, VSide.TOP);
        this.mBottom = new VAnchor(this, VSide.BOTTOM);
        this.mBaseline = new VAnchor(this, VSide.BASELINE);
        this.type = new HelperType("vChain");
    }

    public VChain(String s, String s1) {
        super(s);
        this.mTop = new VAnchor(this, VSide.TOP);
        this.mBottom = new VAnchor(this, VSide.BOTTOM);
        this.mBaseline = new VAnchor(this, VSide.BASELINE);
        this.config = s1;
        this.type = new HelperType("vChain");
        this.configMap = this.convertConfigToMap();
        if(this.configMap.containsKey("contains")) {
            Ref.addStringToReferences(((String)this.configMap.get("contains")), this.references);
        }
    }

    public VAnchor getBaseline() {
        return this.mBaseline;
    }

    public VAnchor getBottom() {
        return this.mBottom;
    }

    public VAnchor getTop() {
        return this.mTop;
    }

    public void linkToBaseline(androidx.constraintlayout.core.dsl.Constraint.VAnchor constraint$VAnchor0) {
        this.linkToBaseline(constraint$VAnchor0, 0);
    }

    public void linkToBaseline(androidx.constraintlayout.core.dsl.Constraint.VAnchor constraint$VAnchor0, int v) {
        this.linkToBaseline(constraint$VAnchor0, v, 0x80000000);
    }

    public void linkToBaseline(androidx.constraintlayout.core.dsl.Constraint.VAnchor constraint$VAnchor0, int v, int v1) {
        this.mBaseline.mConnection = constraint$VAnchor0;
        this.mBaseline.mMargin = v;
        this.mBaseline.mGoneMargin = v1;
        this.configMap.put("baseline", this.mBaseline.toString());
    }

    public void linkToBottom(androidx.constraintlayout.core.dsl.Constraint.VAnchor constraint$VAnchor0) {
        this.linkToBottom(constraint$VAnchor0, 0);
    }

    public void linkToBottom(androidx.constraintlayout.core.dsl.Constraint.VAnchor constraint$VAnchor0, int v) {
        this.linkToBottom(constraint$VAnchor0, v, 0x80000000);
    }

    public void linkToBottom(androidx.constraintlayout.core.dsl.Constraint.VAnchor constraint$VAnchor0, int v, int v1) {
        this.mBottom.mConnection = constraint$VAnchor0;
        this.mBottom.mMargin = v;
        this.mBottom.mGoneMargin = v1;
        this.configMap.put("bottom", this.mBottom.toString());
    }

    public void linkToTop(androidx.constraintlayout.core.dsl.Constraint.VAnchor constraint$VAnchor0) {
        this.linkToTop(constraint$VAnchor0, 0);
    }

    public void linkToTop(androidx.constraintlayout.core.dsl.Constraint.VAnchor constraint$VAnchor0, int v) {
        this.linkToTop(constraint$VAnchor0, v, 0x80000000);
    }

    public void linkToTop(androidx.constraintlayout.core.dsl.Constraint.VAnchor constraint$VAnchor0, int v, int v1) {
        this.mTop.mConnection = constraint$VAnchor0;
        this.mTop.mMargin = v;
        this.mTop.mGoneMargin = v1;
        this.configMap.put("top", this.mTop.toString());
    }
}

