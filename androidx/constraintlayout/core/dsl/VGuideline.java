package androidx.constraintlayout.core.dsl;

public class VGuideline extends Guideline {
    public VGuideline(String s) {
        super(s);
        this.type = new HelperType("vGuideline");
    }

    public VGuideline(String s, String s1) {
        super(s);
        this.config = s1;
        this.type = new HelperType("vGuideline");
        this.configMap = this.convertConfigToMap();
    }
}

