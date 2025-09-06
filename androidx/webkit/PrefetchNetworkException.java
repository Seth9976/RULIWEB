package androidx.webkit;

public class PrefetchNetworkException extends PrefetchException {
    public static final int NO_HTTP_RESPONSE_STATUS_CODE;
    public final int httpResponseStatusCode;

    public PrefetchNetworkException() {
        this(0);
    }

    public PrefetchNetworkException(int v) {
        this.httpResponseStatusCode = v;
    }

    public PrefetchNetworkException(String s) {
        this(s, 0);
    }

    public PrefetchNetworkException(String s, int v) {
        super(s);
        this.httpResponseStatusCode = v;
    }
}

