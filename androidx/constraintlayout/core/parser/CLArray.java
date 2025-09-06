package androidx.constraintlayout.core.parser;

public class CLArray extends CLContainer {
    public CLArray(char[] arr_c) {
        super(arr_c);
    }

    @Override  // androidx.constraintlayout.core.parser.CLContainer
    public static CLElement allocate(char[] arr_c) {
        return new CLArray(arr_c);
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    protected String toFormattedJSON(int v, int v1) {
        StringBuilder stringBuilder0 = new StringBuilder();
        String s = this.toJSON();
        if(v1 <= 0 && s.length() + v < CLArray.sMaxLine) {
            stringBuilder0.append(s);
            return stringBuilder0.toString();
        }
        stringBuilder0.append("[\n");
        boolean z = true;
        for(Object object0: this.mElements) {
            if(z) {
                z = false;
            }
            else {
                stringBuilder0.append(",\n");
            }
            this.addIndent(stringBuilder0, CLArray.sBaseIndent + v);
            stringBuilder0.append(((CLElement)object0).toFormattedJSON(CLArray.sBaseIndent + v, v1 - 1));
        }
        stringBuilder0.append("\n");
        this.addIndent(stringBuilder0, v);
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    protected String toJSON() {
        StringBuilder stringBuilder0 = new StringBuilder(this.getDebugName() + "[");
        boolean z = true;
        for(int v = 0; v < this.mElements.size(); ++v) {
            if(z) {
                z = false;
            }
            else {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append("");
        }
        return stringBuilder0 + "]";
    }
}

