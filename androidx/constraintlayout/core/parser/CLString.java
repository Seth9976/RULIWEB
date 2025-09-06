package androidx.constraintlayout.core.parser;

public class CLString extends CLElement {
    public CLString(char[] arr_c) {
        super(arr_c);
    }

    public static CLElement allocate(char[] arr_c) {
        return new CLString(arr_c);
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return !(object0 instanceof CLString) || !this.content().equals(((CLString)object0).content()) ? super.equals(object0) : true;
    }

    public static CLString from(String s) {
        CLString cLString0 = new CLString(s.toCharArray());
        cLString0.setStart(0L);
        cLString0.setEnd(((long)(s.length() - 1)));
        return cLString0;
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public int hashCode() {
        return super.hashCode();
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    protected String toFormattedJSON(int v, int v1) {
        StringBuilder stringBuilder0 = new StringBuilder();
        this.addIndent(stringBuilder0, v);
        stringBuilder0.append("\'");
        stringBuilder0.append(this.content());
        stringBuilder0.append("\'");
        return stringBuilder0.toString();
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    protected String toJSON() {
        return "\'" + this.content() + "\'";
    }
}

