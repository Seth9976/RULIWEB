package androidx.constraintlayout.core.parser;

public class CLParser {
    static enum TYPE {
        UNKNOWN,
        OBJECT,
        ARRAY,
        NUMBER,
        STRING,
        KEY,
        TOKEN;

        private static TYPE[] $values() [...] // Inlined contents
    }

    private String mContent;
    private boolean mHasComment;
    private int mLineNumber;
    static boolean sDebug = false;

    static {
    }

    public CLParser(String s) {
        this.mHasComment = false;
        this.mContent = s;
    }

    private CLElement createElement(CLElement cLElement0, int v, TYPE cLParser$TYPE0, boolean z, char[] arr_c) {
        CLElement cLElement1;
        if(CLParser.sDebug) {
            System.out.println("CREATE " + cLParser$TYPE0 + " at " + arr_c[v]);
        }
        switch(cLParser$TYPE0.ordinal()) {
            case 1: {
                cLElement1 = CLObject.allocate(arr_c);
                ++v;
                break;
            }
            case 2: {
                cLElement1 = CLArray.allocate(arr_c);
                ++v;
                break;
            }
            case 3: {
                cLElement1 = CLNumber.allocate(arr_c);
                break;
            }
            case 4: {
                cLElement1 = CLString.allocate(arr_c);
                break;
            }
            case 5: {
                cLElement1 = CLKey.allocate(arr_c);
                break;
            }
            case 6: {
                cLElement1 = CLToken.allocate(arr_c);
                break;
            }
            default: {
                cLElement1 = null;
            }
        }
        if(cLElement1 == null) {
            return null;
        }
        cLElement1.setLine(this.mLineNumber);
        if(z) {
            cLElement1.setStart(((long)v));
        }
        if(cLElement0 instanceof CLContainer) {
            cLElement1.setContainer(((CLContainer)cLElement0));
        }
        return cLElement1;
    }

    private CLElement getNextJsonElement(int v, char c, CLElement cLElement0, char[] arr_c) throws CLParsingException {
        switch(c) {
            case 34: 
            case 39: {
                return cLElement0 instanceof CLObject ? this.createElement(cLElement0, v, TYPE.KEY, true, arr_c) : this.createElement(cLElement0, v, TYPE.STRING, true, arr_c);
            }
            case 0x2F: {
                if(v + 1 < arr_c.length && arr_c[v + 1] == 0x2F) {
                    this.mHasComment = true;
                    return cLElement0;
                }
                break;
            }
            case 43: 
            case 45: 
            case 46: 
            case 0x30: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: {
                return this.createElement(cLElement0, v, TYPE.NUMBER, true, arr_c);
            }
            case 9: 
            case 10: 
            case 13: 
            case 0x20: 
            case 44: 
            case 58: {
                break;
            }
            case 91: {
                return this.createElement(cLElement0, v, TYPE.ARRAY, true, arr_c);
            }
            case 0x7B: {
                return this.createElement(cLElement0, v, TYPE.OBJECT, true, arr_c);
            }
            case 93: 
            case 0x7D: {
                cLElement0.setEnd(((long)(v - 1)));
                CLElement cLElement2 = cLElement0.getContainer();
                cLElement2.setEnd(((long)v));
                return cLElement2;
            }
            default: {
                if(cLElement0 instanceof CLContainer && !(cLElement0 instanceof CLObject)) {
                    CLElement cLElement1 = this.createElement(cLElement0, v, TYPE.TOKEN, true, arr_c);
                    if(!((CLToken)cLElement1).validate(c, ((long)v))) {
                        throw new CLParsingException("incorrect token <" + c + "> at line " + this.mLineNumber, ((CLToken)cLElement1));
                    }
                    return cLElement1;
                }
                return this.createElement(cLElement0, v, TYPE.KEY, true, arr_c);
            }
        }
        return cLElement0;
    }

    public static CLObject parse(String s) throws CLParsingException {
        return new CLParser(s).parse();
    }

    public CLObject parse() throws CLParsingException {
        char[] arr_c = this.mContent.toCharArray();
        this.mLineNumber = 1;
        int v;
        for(v = 0; true; ++v) {
            if(v >= arr_c.length) {
                v = -1;
                break;
            }
            int v1 = arr_c[v];
            if(v1 == 0x7B) {
                break;
            }
            if(v1 == 10) {
                ++this.mLineNumber;
            }
        }
        if(v == -1) {
            throw new CLParsingException("invalid json content", null);
        }
        CLObject cLObject0 = CLObject.allocate(arr_c);
        cLObject0.setLine(this.mLineNumber);
        cLObject0.setStart(((long)v));
        int v2 = v + 1;
        CLElement cLElement0 = cLObject0;
        while(v2 < arr_c.length) {
            int v3 = arr_c[v2];
            if(v3 == 10) {
                ++this.mLineNumber;
            }
            if(!this.mHasComment) {
            label_25:
                if(cLElement0 == null) {
                    break;
                }
                if(cLElement0.isDone()) {
                    cLElement0 = this.getNextJsonElement(v2, ((char)v3), cLElement0, arr_c);
                }
                else if(cLElement0 instanceof CLObject) {
                    if(v3 == 0x7D) {
                        cLElement0.setEnd(((long)(v2 - 1)));
                    }
                    else {
                        cLElement0 = this.getNextJsonElement(v2, ((char)v3), cLElement0, arr_c);
                    }
                }
                else if(cLElement0 instanceof CLArray) {
                    if(v3 == 93) {
                        cLElement0.setEnd(((long)(v2 - 1)));
                    }
                    else {
                        cLElement0 = this.getNextJsonElement(v2, ((char)v3), cLElement0, arr_c);
                    }
                }
                else if(!(cLElement0 instanceof CLString)) {
                    if(cLElement0 instanceof CLToken && !((CLToken)cLElement0).validate(((char)v3), ((long)v2))) {
                        throw new CLParsingException("parsing incorrect token " + ((CLToken)cLElement0).content() + " at line " + this.mLineNumber, ((CLToken)cLElement0));
                    }
                    if(cLElement0 instanceof CLKey || cLElement0 instanceof CLString) {
                        int v4 = arr_c[((int)cLElement0.mStart)];
                        if((v4 == 34 || v4 == 39) && v4 == v3) {
                            cLElement0.setStart(cLElement0.mStart + 1L);
                            cLElement0.setEnd(((long)(v2 - 1)));
                        }
                    }
                    if(!cLElement0.isDone()) {
                        if(v3 != 93 && v3 != 0x7D) {
                            switch(v3) {
                                case 9: 
                                case 10: 
                                case 13: 
                                case 0x20: 
                                case 44: 
                                case 58: {
                                    break;
                                }
                                default: {
                                    goto label_64;
                                }
                            }
                        }
                        cLElement0.setEnd(((long)(v2 - 1)));
                        if(v3 == 93 || v3 == 0x7D) {
                            cLElement0 = cLElement0.getContainer();
                            cLElement0.setEnd(((long)(v2 - 1)));
                            if(cLElement0 instanceof CLKey) {
                                cLElement0 = cLElement0.getContainer();
                                cLElement0.setEnd(((long)(v2 - 1)));
                            }
                        }
                    }
                }
                else if(arr_c[((int)cLElement0.mStart)] == v3) {
                    cLElement0.setStart(cLElement0.mStart + 1L);
                    cLElement0.setEnd(((long)(v2 - 1)));
                }
            label_64:
                if(cLElement0.isDone() && (!(cLElement0 instanceof CLKey) || ((CLKey)cLElement0).mElements.size() > 0)) {
                    cLElement0 = cLElement0.getContainer();
                }
            }
            else if(v3 == 10) {
                this.mHasComment = false;
                goto label_25;
            }
            ++v2;
        }
        while(cLElement0 != null && !cLElement0.isDone()) {
            if(cLElement0 instanceof CLString) {
                cLElement0.setStart(((long)(((int)cLElement0.mStart) + 1)));
            }
            cLElement0.setEnd(((long)(arr_c.length - 1)));
            cLElement0 = cLElement0.getContainer();
        }
        if(CLParser.sDebug) {
            System.out.println("Root: " + cLObject0.toJSON());
        }
        return cLObject0;
    }
}

