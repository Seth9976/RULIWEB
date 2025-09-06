package androidx.core.net;

public class ParseException extends RuntimeException {
    public final String response;

    ParseException(String s) {
        super(s);
        this.response = s;
    }
}

