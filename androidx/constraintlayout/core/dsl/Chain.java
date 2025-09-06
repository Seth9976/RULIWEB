package androidx.constraintlayout.core.dsl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Chain extends Helper {
    public class Anchor {
        androidx.constraintlayout.core.dsl.Constraint.Anchor mConnection;
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
            return Chain.this.name;
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

    public static enum Style {
        PACKED,
        SPREAD,
        SPREAD_INSIDE;

        private static Style[] $values() [...] // Inlined contents
    }

    private Style mStyle;
    protected ArrayList references;
    protected static final Map styleMap;

    static {
        HashMap hashMap0 = new HashMap();
        Chain.styleMap = hashMap0;
        hashMap0.put(Style.SPREAD, "\'spread\'");
        hashMap0.put(Style.SPREAD_INSIDE, "\'spread_inside\'");
        hashMap0.put(Style.PACKED, "\'packed\'");
    }

    public Chain(String s) {
        super(s, new HelperType(""));
        this.mStyle = null;
        this.references = new ArrayList();
    }

    public Chain addReference(Ref ref0) {
        this.references.add(ref0);
        this.configMap.put("contains", this.referencesToString());
        return this;
    }

    public Chain addReference(String s) {
        return this.addReference(Ref.parseStringToRef(s));
    }

    public Style getStyle() {
        return this.mStyle;
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

    public void setStyle(Style chain$Style0) {
        this.mStyle = chain$Style0;
        this.configMap.put("style", ((String)Chain.styleMap.get(chain$Style0)));
    }
}

