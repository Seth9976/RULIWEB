package okhttp3.internal;

import java.nio.charset.Charset;
import javax.net.ssl.SSLSocket;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.Cache;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Cookie;
import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealConnection;

@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u001A\u0016\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\t\u001A\u001E\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\t2\u0006\u0010\u000B\u001A\u00020\t\u001A\u001E\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0013\u001A\u0018\u0010\u0014\u001A\u0004\u0018\u00010\u00022\u0006\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u0018\u001A\u0016\u0010\u0019\u001A\u00020\t2\u0006\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u0013\u001A \u0010\u001D\u001A\u0004\u0018\u00010\u001B2\u0006\u0010\u001E\u001A\u00020\u001F2\u0006\u0010 \u001A\u00020!2\u0006\u0010\"\u001A\u00020\t\u001A\u0016\u0010#\u001A\u00020$*\u0004\u0018\u00010%2\b\b\u0002\u0010&\u001A\u00020$\u001A\u001A\u0010\'\u001A\u0010\u0012\u0004\u0012\u00020$\u0012\u0006\u0012\u0004\u0018\u00010%0(*\u0004\u0018\u00010%\u001A#\u0010)\u001A\b\u0012\u0004\u0012\u00020\t0**\u00020\u000F2\f\u0010+\u001A\b\u0012\u0004\u0012\u00020\t0*¢\u0006\u0002\u0010,\"\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006-"}, d2 = {"connection", "Lokhttp3/internal/connection/RealConnection;", "Lokhttp3/Response;", "getConnection", "(Lokhttp3/Response;)Lokhttp3/internal/connection/RealConnection;", "addHeaderLenient", "Lokhttp3/Headers$Builder;", "builder", "line", "", "name", "value", "applyConnectionSpec", "", "connectionSpec", "Lokhttp3/ConnectionSpec;", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "isFallback", "", "cacheGet", "cache", "Lokhttp3/Cache;", "request", "Lokhttp3/Request;", "cookieToString", "cookie", "Lokhttp3/Cookie;", "forObsoleteRfc2965", "parseCookie", "currentTimeMillis", "", "url", "Lokhttp3/HttpUrl;", "setCookie", "charset", "Ljava/nio/charset/Charset;", "Lokhttp3/MediaType;", "defaultValue", "chooseCharset", "Lkotlin/Pair;", "effectiveCipherSuites", "", "socketEnabledCipherSuites", "(Lokhttp3/ConnectionSpec;[Ljava/lang/String;)[Ljava/lang/String;", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class Internal {
    public static final Builder addHeaderLenient(Builder headers$Builder0, String s) {
        Intrinsics.checkNotNullParameter(headers$Builder0, "builder");
        Intrinsics.checkNotNullParameter(s, "line");
        return headers$Builder0.addLenient$okhttp(s);
    }

    public static final Builder addHeaderLenient(Builder headers$Builder0, String s, String s1) {
        Intrinsics.checkNotNullParameter(headers$Builder0, "builder");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "value");
        return headers$Builder0.addLenient$okhttp(s, s1);
    }

    public static final void applyConnectionSpec(ConnectionSpec connectionSpec0, SSLSocket sSLSocket0, boolean z) {
        Intrinsics.checkNotNullParameter(connectionSpec0, "connectionSpec");
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        connectionSpec0.apply$okhttp(sSLSocket0, z);
    }

    public static final Response cacheGet(Cache cache0, Request request0) {
        Intrinsics.checkNotNullParameter(cache0, "cache");
        Intrinsics.checkNotNullParameter(request0, "request");
        return cache0.get$okhttp(request0);
    }

    public static final Charset charset(MediaType mediaType0, Charset charset0) {
        Intrinsics.checkNotNullParameter(charset0, "defaultValue");
        if(mediaType0 != null) {
            Charset charset1 = mediaType0.charset(charset0);
            return charset1 == null ? Charsets.UTF_8 : charset1;
        }
        return Charsets.UTF_8;
    }

    public static Charset charset$default(MediaType mediaType0, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = Charsets.UTF_8;
        }
        return Internal.charset(mediaType0, charset0);
    }

    public static final Pair chooseCharset(MediaType mediaType0) {
        Charset charset0 = Charsets.UTF_8;
        if(mediaType0 != null) {
            charset0 = MediaType.charset$default(mediaType0, null, 1, null);
            if(charset0 == null) {
                charset0 = Charsets.UTF_8;
                mediaType0 = MediaType.Companion.parse(mediaType0 + "; charset=utf-8");
            }
        }
        return TuplesKt.to(charset0, mediaType0);
    }

    public static final String cookieToString(Cookie cookie0, boolean z) {
        Intrinsics.checkNotNullParameter(cookie0, "cookie");
        return cookie0.toString$okhttp(z);
    }

    public static final String[] effectiveCipherSuites(ConnectionSpec connectionSpec0, String[] arr_s) {
        Intrinsics.checkNotNullParameter(connectionSpec0, "<this>");
        Intrinsics.checkNotNullParameter(arr_s, "socketEnabledCipherSuites");
        return connectionSpec0.getCipherSuitesAsString$okhttp() == null ? arr_s : _UtilCommonKt.intersect(arr_s, connectionSpec0.getCipherSuitesAsString$okhttp(), CipherSuite.Companion.getORDER_BY_NAME$okhttp());
    }

    public static final RealConnection getConnection(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        Exchange exchange0 = response0.exchange();
        Intrinsics.checkNotNull(exchange0);
        return exchange0.getConnection$okhttp();
    }

    public static final Cookie parseCookie(long v, HttpUrl httpUrl0, String s) {
        Intrinsics.checkNotNullParameter(httpUrl0, "url");
        Intrinsics.checkNotNullParameter(s, "setCookie");
        return Cookie.Companion.parse$okhttp(v, httpUrl0, s);
    }
}

