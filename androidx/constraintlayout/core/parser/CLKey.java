package androidx.constraintlayout.core.parser;

import java.util.ArrayList;
import java.util.Objects;

public class CLKey extends CLContainer {
    private static ArrayList sSections;

    static {
        ArrayList arrayList0 = new ArrayList();
        CLKey.sSections = arrayList0;
        arrayList0.add("ConstraintSets");
        CLKey.sSections.add("Variables");
        CLKey.sSections.add("Generate");
        CLKey.sSections.add("Transitions");
        CLKey.sSections.add("KeyFrames");
        CLKey.sSections.add("KeyAttributes");
        CLKey.sSections.add("KeyPositions");
        CLKey.sSections.add("KeyCycles");
    }

    public CLKey(char[] arr_c) {
        super(arr_c);
    }

    public static CLElement allocate(String s, CLElement cLElement0) {
        CLElement cLElement1 = new CLKey(s.toCharArray());
        ((CLKey)cLElement1).setStart(0L);
        ((CLKey)cLElement1).setEnd(((long)(s.length() - 1)));
        ((CLKey)cLElement1).set(cLElement0);
        return cLElement1;
    }

    @Override  // androidx.constraintlayout.core.parser.CLContainer
    public static CLElement allocate(char[] arr_c) {
        return new CLKey(arr_c);
    }

    @Override  // androidx.constraintlayout.core.parser.CLContainer
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return !(object0 instanceof CLKey) || Objects.equals(this.getName(), ((CLKey)object0).getName()) ? super.equals(object0) : false;
    }

    public String getName() {
        return this.content();
    }

    public CLElement getValue() {
        return this.mElements.size() <= 0 ? null : ((CLElement)this.mElements.get(0));
    }

    @Override  // androidx.constraintlayout.core.parser.CLContainer
    public int hashCode() {
        return super.hashCode();
    }

    public void set(CLElement cLElement0) {
        if(this.mElements.size() > 0) {
            this.mElements.set(0, cLElement0);
            return;
        }
        this.mElements.add(cLElement0);
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    protected String toFormattedJSON(int v, int v1) {
        StringBuilder stringBuilder0 = new StringBuilder(this.getDebugName());
        this.addIndent(stringBuilder0, v);
        String s = this.content();
        if(this.mElements.size() > 0) {
            stringBuilder0.append(s);
            stringBuilder0.append(": ");
            if(CLKey.sSections.contains(s)) {
                v1 = 3;
            }
            if(v1 > 0) {
                stringBuilder0.append(((CLElement)this.mElements.get(0)).toFormattedJSON(v, v1 - 1));
                return stringBuilder0.toString();
            }
            if(v < CLKey.sMaxLine) {
                stringBuilder0.append("");
                return stringBuilder0.toString();
            }
            stringBuilder0.append(((CLElement)this.mElements.get(0)).toFormattedJSON(v, v1 - 1));
            return stringBuilder0.toString();
        }
        return s + ": <> ";
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.constraintlayout.core.parser.CLElement
    protected String toJSON() {
        return this.mElements.size() <= 0 ? this.getDebugName() + this.content() + ": <> " : this.getDebugName() + this.content() + ": " + "";
    }
}

