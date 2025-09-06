package okhttp3.internal.http;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Response;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.ByteString;

@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\u001A\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007\u001A\u0018\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\b*\u00020\n2\u0006\u0010\u000B\u001A\u00020\f\u001A\n\u0010\r\u001A\u00020\u0004*\u00020\u0006\u001A\u001A\u0010\u000E\u001A\u00020\u000F*\u00020\u00102\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\t0\u0012H\u0002\u001A\u000E\u0010\u0013\u001A\u0004\u0018\u00010\f*\u00020\u0010H\u0002\u001A\u000E\u0010\u0014\u001A\u0004\u0018\u00010\f*\u00020\u0010H\u0002\u001A\u001A\u0010\u0015\u001A\u00020\u000F*\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\n\u001A\f\u0010\u001A\u001A\u00020\u0004*\u00020\u0010H\u0002\u001A\u0014\u0010\u001B\u001A\u00020\u0004*\u00020\u00102\u0006\u0010\u001C\u001A\u00020\u001DH\u0002\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001E"}, d2 = {"QUOTED_STRING_DELIMITERS", "Lokio/ByteString;", "TOKEN_DELIMITERS", "hasBody", "", "response", "Lokhttp3/Response;", "parseChallenges", "", "Lokhttp3/Challenge;", "Lokhttp3/Headers;", "headerName", "", "promisesBody", "readChallengeHeader", "", "Lokio/Buffer;", "result", "", "readQuotedString", "readToken", "receiveHeaders", "Lokhttp3/CookieJar;", "url", "Lokhttp3/HttpUrl;", "headers", "skipCommasAndWhitespace", "startsWith", "prefix", "", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class HttpHeaders {
    private static final ByteString QUOTED_STRING_DELIMITERS;
    private static final ByteString TOKEN_DELIMITERS;

    static {
        HttpHeaders.QUOTED_STRING_DELIMITERS = ByteString.Companion.encodeUtf8("\"\\");
        HttpHeaders.TOKEN_DELIMITERS = ByteString.Companion.encodeUtf8("\t ,=");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "No longer supported", replaceWith = @ReplaceWith(expression = "response.promisesBody()", imports = {}))
    public static final boolean hasBody(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "response");
        return HttpHeaders.promisesBody(response0);
    }

    public static final List parseChallenges(Headers headers0, String s) {
        Intrinsics.checkNotNullParameter(headers0, "<this>");
        Intrinsics.checkNotNullParameter(s, "headerName");
        List list0 = new ArrayList();
        int v = headers0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(StringsKt.equals(s, headers0.name(v1), true)) {
                Buffer buffer0 = new Buffer().writeUtf8(headers0.value(v1));
                try {
                    HttpHeaders.readChallengeHeader(buffer0, list0);
                }
                catch(EOFException eOFException0) {
                    Platform.Companion.get().log("Unable to parse challenge", 5, eOFException0);
                }
            }
        }
        return list0;
    }

    public static final boolean promisesBody(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        if(Intrinsics.areEqual(response0.request().method(), "HEAD")) {
            return false;
        }
        int v = response0.code();
        return v >= 100 && v < 200 || (v == 204 || v == 304) ? _UtilJvmKt.headersContentLength(response0) != -1L || StringsKt.equals("chunked", Response.header$default(response0, "Transfer-Encoding", null, 2, null), true) : true;
    }

    private static final void readChallengeHeader(Buffer buffer0, List list0) throws EOFException {
        int v;
        String s1;
        String s;
        while(true) {
            s = null;
        label_1:
            if(s == null) {
                HttpHeaders.skipCommasAndWhitespace(buffer0);
                s = HttpHeaders.readToken(buffer0);
                if(s != null) {
                    goto label_5;
                }
                return;
            }
        label_5:
            boolean z = HttpHeaders.skipCommasAndWhitespace(buffer0);
            s1 = HttpHeaders.readToken(buffer0);
            if(s1 == null) {
                if(!buffer0.exhausted()) {
                    return;
                }
                list0.add(new Challenge(s, MapsKt.emptyMap()));
                return;
            }
            v = _UtilCommonKt.skipAll(buffer0, 61);
            if(z || !HttpHeaders.skipCommasAndWhitespace(buffer0) && !buffer0.exhausted()) {
                break;
            }
            Map map0 = Collections.singletonMap(null, s1 + StringsKt.repeat("=", v));
            Intrinsics.checkNotNullExpressionValue(map0, "singletonMap<String, Str…ek + \"=\".repeat(eqCount))");
            list0.add(new Challenge(s, map0));
        }
        Map map1 = new LinkedHashMap();
        int v1 = v + _UtilCommonKt.skipAll(buffer0, 61);
        while(true) {
            if(s1 == null) {
                s1 = HttpHeaders.readToken(buffer0);
                if(HttpHeaders.skipCommasAndWhitespace(buffer0)) {
                    list0.add(new Challenge(s, map1));
                    s = s1;
                    goto label_1;
                }
                else {
                    v1 = _UtilCommonKt.skipAll(buffer0, 61);
                }
            }
            if(v1 == 0) {
                list0.add(new Challenge(s, map1));
                s = s1;
                goto label_1;
            }
            if(v1 > 1 || HttpHeaders.skipCommasAndWhitespace(buffer0)) {
                break;
            }
            String s2 = HttpHeaders.startsWith(buffer0, 34) ? HttpHeaders.readQuotedString(buffer0) : HttpHeaders.readToken(buffer0);
            if(s2 == null || ((String)map1.put(s1, s2)) != null || !HttpHeaders.skipCommasAndWhitespace(buffer0) && !buffer0.exhausted()) {
                break;
            }
            s1 = null;
        }
    }

    private static final String readQuotedString(Buffer buffer0) throws EOFException {
        if(buffer0.readByte() == 34) {
            Buffer buffer1 = new Buffer();
            while(true) {
                long v = buffer0.indexOfElement(HttpHeaders.QUOTED_STRING_DELIMITERS);
                if(v == -1L) {
                    return null;
                }
                if(buffer0.getByte(v) == 34) {
                    buffer1.write(buffer0, v);
                    buffer0.readByte();
                    return "";
                }
                if(buffer0.size() == v + 1L) {
                    return null;
                }
                buffer1.write(buffer0, v);
                buffer0.readByte();
                buffer1.write(buffer0, 1L);
            }
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    private static final String readToken(Buffer buffer0) {
        long v = buffer0.indexOfElement(HttpHeaders.TOKEN_DELIMITERS);
        if(v == -1L) {
            v = buffer0.size();
        }
        return v == 0L ? null : buffer0.readUtf8(v);
    }

    public static final void receiveHeaders(CookieJar cookieJar0, HttpUrl httpUrl0, Headers headers0) {
        Intrinsics.checkNotNullParameter(cookieJar0, "<this>");
        Intrinsics.checkNotNullParameter(httpUrl0, "url");
        Intrinsics.checkNotNullParameter(headers0, "headers");
        if(cookieJar0 != CookieJar.NO_COOKIES) {
            List list0 = Cookie.Companion.parseAll(httpUrl0, headers0);
            if(!list0.isEmpty()) {
                cookieJar0.saveFromResponse(httpUrl0, list0);
            }
        }
    }

    private static final boolean skipCommasAndWhitespace(Buffer buffer0) {
        boolean z = false;
        while(!buffer0.exhausted()) {
            int v = buffer0.getByte(0L);
            if(v == 44) {
                buffer0.readByte();
                z = true;
            }
            else {
                if(v != 9 && v != 0x20) {
                    break;
                }
                buffer0.readByte();
            }
        }
        return z;
    }

    private static final boolean startsWith(Buffer buffer0, byte b) {
        return !buffer0.exhausted() && buffer0.getByte(0L) == b;
    }
}

