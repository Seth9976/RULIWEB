package okhttp3.logging;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002\u001E\u001FB\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u0011H\u0002J\r\u0010\u000B\u001A\u00020\tH\u0007¢\u0006\u0002\b\u0012J\u0010\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0016H\u0016J\u0018\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0019\u001A\u00020\u001AH\u0002J\u000E\u0010\u001B\u001A\u00020\u00182\u0006\u0010\u001C\u001A\u00020\u0007J\u000E\u0010\u001D\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\tR\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R$\u0010\n\u001A\u00020\t2\u0006\u0010\b\u001A\u00020\t@GX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000B\u0010\f\"\u0004\b\n\u0010\rR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor;", "Lokhttp3/Interceptor;", "logger", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "(Lokhttp3/logging/HttpLoggingInterceptor$Logger;)V", "headersToRedact", "", "", "<set-?>", "Lokhttp3/logging/HttpLoggingInterceptor$Level;", "level", "getLevel", "()Lokhttp3/logging/HttpLoggingInterceptor$Level;", "(Lokhttp3/logging/HttpLoggingInterceptor$Level;)V", "bodyHasUnknownEncoding", "", "headers", "Lokhttp3/Headers;", "-deprecated_level", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "logHeader", "", "i", "", "redactHeader", "name", "setLevel", "Level", "Logger", "okhttp-logging-interceptor"}, k = 1, mv = {1, 4, 0})
public final class HttpLoggingInterceptor implements Interceptor {
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Level;", "", "(Ljava/lang/String;I)V", "NONE", "BASIC", "HEADERS", "BODY", "okhttp-logging-interceptor"}, k = 1, mv = {1, 4, 0})
    public static enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY;

    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "", "log", "", "message", "", "Companion", "okhttp-logging-interceptor"}, k = 1, mv = {1, 4, 0})
    public interface Logger {
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000¨\u0006\u0001\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001¨\u0006\u0006"}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Logger$Companion;", "", "()V", "DEFAULT", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "DefaultLogger", "okhttp-logging-interceptor"}, k = 1, mv = {1, 4, 0})
        public static final class Companion {
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lokhttp3/logging/HttpLoggingInterceptor$Logger$Companion$DefaultLogger;", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "()V", "log", "", "message", "", "okhttp-logging-interceptor"}, k = 1, mv = {1, 4, 0})
            static final class DefaultLogger implements Logger {
                @Override  // okhttp3.logging.HttpLoggingInterceptor$Logger
                public void log(String s) {
                    Intrinsics.checkNotNullParameter(s, "message");
                    Platform.log$default(Platform.Companion.get(), s, 0, null, 6, null);
                }
            }

            static final Companion $$INSTANCE;

            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }
        }

        public static final Companion Companion;
        public static final Logger DEFAULT;

        static {
            Logger.Companion = new Companion(null);
            Logger.DEFAULT = new DefaultLogger();
        }

        void log(String arg1);
    }

    private volatile Set headersToRedact;
    private volatile Level level;
    private final Logger logger;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to var", replaceWith = @ReplaceWith(expression = "level", imports = {}))
    public final Level -deprecated_level() {
        return this.level;
    }

    public HttpLoggingInterceptor() {
        this(null, 1, null);
    }

    public HttpLoggingInterceptor(Logger httpLoggingInterceptor$Logger0) {
        Intrinsics.checkNotNullParameter(httpLoggingInterceptor$Logger0, "logger");
        super();
        this.logger = httpLoggingInterceptor$Logger0;
        this.headersToRedact = SetsKt.emptySet();
        this.level = Level.NONE;
    }

    public HttpLoggingInterceptor(Logger httpLoggingInterceptor$Logger0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            httpLoggingInterceptor$Logger0 = Logger.DEFAULT;
        }
        this(httpLoggingInterceptor$Logger0);
    }

    private final boolean bodyHasUnknownEncoding(Headers headers0) {
        String s = headers0.get("Content-Encoding");
        return s != null && !StringsKt.equals(s, "identity", true) && !StringsKt.equals(s, "gzip", true);
    }

    public final Level getLevel() {
        return this.level;
    }

    @Override  // okhttp3.Interceptor
    public Response intercept(Chain interceptor$Chain0) throws IOException {
        Charset charset1;
        Buffer buffer2;
        String s2;
        int v5;
        ResponseBody responseBody1;
        Response response0;
        Charset charset0;
        Intrinsics.checkNotNullParameter(interceptor$Chain0, "chain");
        Level httpLoggingInterceptor$Level0 = this.level;
        Request request0 = interceptor$Chain0.request();
        if(httpLoggingInterceptor$Level0 == Level.NONE) {
            return interceptor$Chain0.proceed(request0);
        }
        int v = httpLoggingInterceptor$Level0 == Level.BODY ? 1 : 0;
        boolean z = v != 0 || httpLoggingInterceptor$Level0 == Level.HEADERS;
        RequestBody requestBody0 = request0.body();
        Connection connection0 = interceptor$Chain0.connection();
        StringBuilder stringBuilder0 = new StringBuilder("--> ");
        stringBuilder0.append(request0.method());
        stringBuilder0.append(' ');
        stringBuilder0.append(request0.url());
        stringBuilder0.append((connection0 == null ? "" : " " + connection0.protocol()));
        String s = z || requestBody0 == null ? stringBuilder0.toString() : stringBuilder0.toString() + " (" + requestBody0.contentLength() + "-byte body)";
        this.logger.log(s);
        if(z) {
            Headers headers0 = request0.headers();
            if(requestBody0 != null) {
                MediaType mediaType0 = requestBody0.contentType();
                if(mediaType0 != null && headers0.get("Content-Type") == null) {
                    this.logger.log("Content-Type: " + mediaType0);
                }
                if(requestBody0.contentLength() != -1L && headers0.get("Content-Length") == null) {
                    this.logger.log("Content-Length: " + requestBody0.contentLength());
                }
            }
            int v1 = headers0.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                this.logHeader(headers0, v2);
            }
            if(v == 0 || requestBody0 == null) {
                this.logger.log("--> END " + request0.method());
            }
            else if(this.bodyHasUnknownEncoding(request0.headers())) {
                this.logger.log("--> END " + request0.method() + " (encoded body omitted)");
            }
            else if(requestBody0.isDuplex()) {
                this.logger.log("--> END " + request0.method() + " (duplex request body omitted)");
            }
            else if(requestBody0.isOneShot()) {
                this.logger.log("--> END " + request0.method() + " (one-shot body omitted)");
            }
            else {
                Buffer buffer0 = new Buffer();
                requestBody0.writeTo(buffer0);
                MediaType mediaType1 = requestBody0.contentType();
                if(mediaType1 == null) {
                    charset0 = StandardCharsets.UTF_8;
                    Intrinsics.checkNotNullExpressionValue(charset0, "UTF_8");
                }
                else {
                    charset0 = mediaType1.charset(StandardCharsets.UTF_8);
                    if(charset0 == null) {
                        charset0 = StandardCharsets.UTF_8;
                        Intrinsics.checkNotNullExpressionValue(charset0, "UTF_8");
                    }
                }
                this.logger.log("");
                if(Utf8Kt.isProbablyUtf8(buffer0)) {
                    String s1 = buffer0.readString(charset0);
                    this.logger.log(s1);
                    this.logger.log("--> END " + request0.method() + " (" + requestBody0.contentLength() + "-byte body)");
                }
                else {
                    this.logger.log("--> END " + request0.method() + " (binary " + requestBody0.contentLength() + "-byte body omitted)");
                }
            }
        }
        try {
            response0 = interceptor$Chain0.proceed(request0);
        }
        catch(Exception exception0) {
            this.logger.log("<-- HTTP FAILED: " + exception0);
            throw exception0;
        }
        long v3 = TimeUnit.NANOSECONDS.toMillis(0L);
        ResponseBody responseBody0 = response0.body();
        Intrinsics.checkNotNull(responseBody0);
        long v4 = responseBody0.contentLength();
        Logger httpLoggingInterceptor$Logger0 = this.logger;
        StringBuilder stringBuilder1 = new StringBuilder("<-- ");
        stringBuilder1.append(response0.code());
        if(response0.message().length() == 0) {
            responseBody1 = responseBody0;
            v5 = v;
            s2 = "";
        }
        else {
            responseBody1 = responseBody0;
            v5 = v;
            s2 = " " + response0.message();
        }
        stringBuilder1.append(s2);
        stringBuilder1.append(' ');
        stringBuilder1.append(response0.request().url());
        stringBuilder1.append(" (");
        stringBuilder1.append(v3);
        stringBuilder1.append("ms");
        stringBuilder1.append((z ? "" : ", " + (v4 == -1L ? "unknown-length" : v4 + "-byte") + " body"));
        stringBuilder1.append(')');
        httpLoggingInterceptor$Logger0.log(stringBuilder1.toString());
        if(z) {
            Headers headers1 = response0.headers();
            int v6 = headers1.size();
            for(int v7 = 0; v7 < v6; ++v7) {
                this.logHeader(headers1, v7);
            }
            if(v5 != 0 && HttpHeaders.promisesBody(response0)) {
                if(this.bodyHasUnknownEncoding(response0.headers())) {
                    this.logger.log("<-- END HTTP (encoded body omitted)");
                    return response0;
                }
                BufferedSource bufferedSource0 = responseBody1.source();
                bufferedSource0.request(0x7FFFFFFFFFFFFFFFL);
                Buffer buffer1 = bufferedSource0.getBuffer();
                Long long0 = null;
                if(StringsKt.equals("gzip", headers1.get("Content-Encoding"), true)) {
                    Long long1 = buffer1.size();
                    Closeable closeable0 = new GzipSource(buffer1.clone());
                    try {
                        buffer2 = new Buffer();
                        buffer2.writeAll(((GzipSource)closeable0));
                    }
                    catch(Throwable throwable0) {
                        CloseableKt.closeFinally(closeable0, throwable0);
                        throw throwable0;
                    }
                    CloseableKt.closeFinally(closeable0, null);
                    long0 = long1;
                    buffer1 = buffer2;
                }
                MediaType mediaType2 = responseBody1.contentType();
                if(mediaType2 == null) {
                    charset1 = StandardCharsets.UTF_8;
                    Intrinsics.checkNotNullExpressionValue(charset1, "UTF_8");
                }
                else {
                    charset1 = mediaType2.charset(StandardCharsets.UTF_8);
                    if(charset1 == null) {
                        charset1 = StandardCharsets.UTF_8;
                        Intrinsics.checkNotNullExpressionValue(charset1, "UTF_8");
                    }
                }
                if(!Utf8Kt.isProbablyUtf8(buffer1)) {
                    this.logger.log("");
                    this.logger.log("<-- END HTTP (binary " + buffer1.size() + "-byte body omitted)");
                    return response0;
                }
                if(v4 != 0L) {
                    this.logger.log("");
                    String s3 = buffer1.clone().readString(charset1);
                    this.logger.log(s3);
                }
                if(long0 != null) {
                    this.logger.log("<-- END HTTP (" + buffer1.size() + "-byte, " + long0 + "-gzipped-byte body)");
                    return response0;
                }
                this.logger.log("<-- END HTTP (" + buffer1.size() + "-byte body)");
                return response0;
            }
            this.logger.log("<-- END HTTP");
        }
        return response0;
    }

    public final void level(Level httpLoggingInterceptor$Level0) {
        Intrinsics.checkNotNullParameter(httpLoggingInterceptor$Level0, "<set-?>");
        this.level = httpLoggingInterceptor$Level0;
    }

    private final void logHeader(Headers headers0, int v) {
        String s = this.headersToRedact.contains(headers0.name(v)) ? "██" : headers0.value(v);
        this.logger.log(headers0.name(v) + ": " + s);
    }

    public final void redactHeader(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        TreeSet treeSet0 = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        CollectionsKt.addAll(treeSet0, this.headersToRedact);
        treeSet0.add(s);
        this.headersToRedact = treeSet0;
    }

    public final HttpLoggingInterceptor setLevel(Level httpLoggingInterceptor$Level0) {
        Intrinsics.checkNotNullParameter(httpLoggingInterceptor$Level0, "level");
        this.level = httpLoggingInterceptor$Level0;
        return this;
    }
}

