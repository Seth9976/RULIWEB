package androidx.constraintlayout.core.parser;

public class CLParsingException extends Exception {
    private final String mElementClass;
    private final int mLineNumber;
    private final String mReason;

    public CLParsingException(String s, CLElement cLElement0) {
        super(s);
        this.mReason = s;
        if(cLElement0 != null) {
            this.mElementClass = cLElement0.getStrClass();
            this.mLineNumber = cLElement0.getLine();
            return;
        }
        this.mElementClass = "unknown";
        this.mLineNumber = 0;
    }

    public String reason() {
        return this.mReason + " (" + this.mElementClass + " at line " + this.mLineNumber + ")";
    }

    @Override
    public String toString() {
        return "CLParsingException (" + this.hashCode() + ") : " + this.reason();
    }
}

