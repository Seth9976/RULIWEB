package okhttp3.internal.http;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0010\u0010\u0007\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u000E\u0010\b\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006J\u000E\u0010\t\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006J\u0010\u0010\n\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\u000B"}, d2 = {"Lokhttp3/internal/http/HttpMethod;", "", "()V", "invalidatesCache", "", "method", "", "permitsRequestBody", "redirectsToGet", "redirectsWithBody", "requiresRequestBody", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class HttpMethod {
    public static final HttpMethod INSTANCE;

    static {
        HttpMethod.INSTANCE = new HttpMethod();
    }

    // 去混淆评级： 低(25)
    @JvmStatic
    public static final boolean invalidatesCache(String s) {
        Intrinsics.checkNotNullParameter(s, "method");
        return Intrinsics.areEqual(s, "POST") || Intrinsics.areEqual(s, "PATCH") || Intrinsics.areEqual(s, "PUT") || Intrinsics.areEqual(s, "DELETE") || Intrinsics.areEqual(s, "MOVE");
    }

    @JvmStatic
    public static final boolean permitsRequestBody(String s) {
        Intrinsics.checkNotNullParameter(s, "method");
        return !Intrinsics.areEqual(s, "GET") && !Intrinsics.areEqual(s, "HEAD");
    }

    public final boolean redirectsToGet(String s) {
        Intrinsics.checkNotNullParameter(s, "method");
        return !Intrinsics.areEqual(s, "PROPFIND");
    }

    public final boolean redirectsWithBody(String s) {
        Intrinsics.checkNotNullParameter(s, "method");
        return Intrinsics.areEqual(s, "PROPFIND");
    }

    // 去混淆评级： 低(25)
    @JvmStatic
    public static final boolean requiresRequestBody(String s) {
        Intrinsics.checkNotNullParameter(s, "method");
        return Intrinsics.areEqual(s, "POST") || Intrinsics.areEqual(s, "PUT") || Intrinsics.areEqual(s, "PATCH") || Intrinsics.areEqual(s, "PROPPATCH") || Intrinsics.areEqual(s, "REPORT");
    }
}

