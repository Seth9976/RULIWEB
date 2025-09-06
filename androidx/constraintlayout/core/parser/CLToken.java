package androidx.constraintlayout.core.parser;

public class CLToken extends CLElement {
    static enum Type {
        UNKNOWN,
        TRUE,
        FALSE,
        NULL;

        private static Type[] $values() [...] // Inlined contents
    }

    int mIndex;
    char[] mTokenFalse;
    char[] mTokenNull;
    char[] mTokenTrue;
    Type mType;

    public CLToken(char[] arr_c) {
        super(arr_c);
        this.mIndex = 0;
        this.mType = Type.UNKNOWN;
        this.mTokenTrue = "true".toCharArray();
        this.mTokenFalse = "false".toCharArray();
        this.mTokenNull = "null".toCharArray();
    }

    public static CLElement allocate(char[] arr_c) {
        return new CLToken(arr_c);
    }

    public boolean getBoolean() throws CLParsingException {
        if(this.mType == Type.TRUE) {
            return true;
        }
        if(this.mType != Type.FALSE) {
            throw new CLParsingException("this token is not a boolean: <" + this.content() + ">", this);
        }
        return false;
    }

    public Type getType() {
        return this.mType;
    }

    public boolean isNull() throws CLParsingException {
        if(this.mType != Type.NULL) {
            throw new CLParsingException("this token is not a null: <" + this.content() + ">", this);
        }
        return true;
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    protected String toFormattedJSON(int v, int v1) {
        StringBuilder stringBuilder0 = new StringBuilder();
        this.addIndent(stringBuilder0, v);
        stringBuilder0.append(this.content());
        return stringBuilder0.toString();
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.constraintlayout.core.parser.CLElement
    protected String toJSON() {
        return CLParser.sDebug ? "<" + this.content() + ">" : this.content();
    }

    public boolean validate(char c, long v) {
        boolean z = false;
        switch(this.mType.ordinal()) {
            case 0: {
                int v1 = this.mIndex;
                if(this.mTokenTrue[v1] == c) {
                    this.mType = Type.TRUE;
                    z = true;
                }
                else if(this.mTokenFalse[v1] == c) {
                    this.mType = Type.FALSE;
                    z = true;
                }
                else if(this.mTokenNull[v1] == c) {
                    this.mType = Type.NULL;
                    z = true;
                }
                break;
            }
            case 1: {
                char[] arr_c = this.mTokenTrue;
                int v2 = this.mIndex;
                if(arr_c[v2] == c) {
                    z = true;
                    if(v2 + 1 == arr_c.length) {
                        this.setEnd(v);
                    }
                }
                break;
            }
            case 2: {
                char[] arr_c1 = this.mTokenFalse;
                int v3 = this.mIndex;
                if(arr_c1[v3] == c) {
                    z = true;
                    if(v3 + 1 == arr_c1.length) {
                        this.setEnd(v);
                    }
                }
                break;
            }
            case 3: {
                char[] arr_c2 = this.mTokenNull;
                int v4 = this.mIndex;
                if(arr_c2[v4] == c) {
                    z = true;
                    if(v4 + 1 == arr_c2.length) {
                        this.setEnd(v);
                    }
                }
            }
        }
        ++this.mIndex;
        return z;
    }
}

