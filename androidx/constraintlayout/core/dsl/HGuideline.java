package androidx.constraintlayout.core.dsl;

class HGuideline extends Guideline {
    public HGuideline(String s) {
        super(s);
        this.type = new HelperType("hGuideline");
    }

    public HGuideline(String s, String s1) {
        super(s);
        this.config = s1;
        this.type = new HelperType("hGuideline");
        this.configMap = this.convertConfigToMap();
    }
}

