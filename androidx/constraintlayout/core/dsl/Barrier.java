package androidx.constraintlayout.core.dsl;

import java.util.ArrayList;

public class Barrier extends Helper {
    private Side mDirection;
    private int mMargin;
    private ArrayList references;

    public Barrier(String s) {
        super(s, new HelperType("barrier"));
        this.mDirection = null;
        this.mMargin = 0x80000000;
        this.references = new ArrayList();
    }

    public Barrier(String s, String s1) {
        super(s, new HelperType("barrier"), s1);
        this.mDirection = null;
        this.mMargin = 0x80000000;
        this.references = new ArrayList();
        this.configMap = this.convertConfigToMap();
        if(this.configMap.containsKey("contains")) {
            Ref.addStringToReferences(((String)this.configMap.get("contains")), this.references);
        }
    }

    public Barrier addReference(Ref ref0) {
        this.references.add(ref0);
        this.configMap.put("contains", this.referencesToString());
        return this;
    }

    public Barrier addReference(String s) {
        return this.addReference(Ref.parseStringToRef(s));
    }

    public Side getDirection() {
        return this.mDirection;
    }

    public int getMargin() {
        return this.mMargin;
    }

    public String referencesToString() {
        if(this.references.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder0 = new StringBuilder("[");
        for(Object object0: this.references) {
            stringBuilder0.append(((Ref)object0).toString());
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }

    public void setDirection(Side constraint$Side0) {
        this.mDirection = constraint$Side0;
        this.configMap.put("direction", ((String)Barrier.sideMap.get(constraint$Side0)));
    }

    public void setMargin(int v) {
        this.mMargin = v;
        this.configMap.put("margin", String.valueOf(v));
    }
}

