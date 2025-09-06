package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache.Editor;
import okhttp3.internal.cache.DiskLruCache.Snapshot;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.FileSystem;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Path;
import okio.Sink;
import okio.Source;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010)\n\u0002\u0010\u000E\n\u0002\b\u0005\u0018\u0000 F2\u00020\u00012\u00020\u0002:\u0004EFGHB\u0017\b\u0016\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u00A2\u0006\u0002\u0010\u0007B\u001D\u0012\u0006\u0010\u0003\u001A\u00020\b\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\t\u001A\u00020\n\u00A2\u0006\u0002\u0010\u000BJ\u0016\u0010\"\u001A\u00020#2\f\u0010$\u001A\b\u0018\u00010%R\u00020\rH\u0002J\b\u0010&\u001A\u00020#H\u0016J\u0006\u0010\'\u001A\u00020#J\r\u0010\u0003\u001A\u00020\u0004H\u0007\u00A2\u0006\u0002\b(J\u0006\u0010)\u001A\u00020#J\b\u0010*\u001A\u00020#H\u0016J\u0017\u0010+\u001A\u0004\u0018\u00010,2\u0006\u0010-\u001A\u00020.H\u0000\u00A2\u0006\u0002\b/J\u0006\u0010\u0013\u001A\u00020\u0014J\u0006\u00100\u001A\u00020#J\u0006\u0010\u0005\u001A\u00020\u0006J\u0006\u0010\u0018\u001A\u00020\u0014J\u0017\u00101\u001A\u0004\u0018\u0001022\u0006\u00103\u001A\u00020,H\u0000\u00A2\u0006\u0002\b4J\u0015\u00105\u001A\u00020#2\u0006\u0010-\u001A\u00020.H\u0000\u00A2\u0006\u0002\b6J\u0006\u0010\u0019\u001A\u00020\u0014J\u0006\u00107\u001A\u00020\u0006J\r\u00108\u001A\u00020#H\u0000\u00A2\u0006\u0002\b9J\u0015\u0010:\u001A\u00020#2\u0006\u0010;\u001A\u00020<H\u0000\u00A2\u0006\u0002\b=J\u001D\u0010>\u001A\u00020#2\u0006\u0010?\u001A\u00020,2\u0006\u0010@\u001A\u00020,H\u0000\u00A2\u0006\u0002\bAJ\f\u0010B\u001A\b\u0012\u0004\u0012\u00020D0CJ\u0006\u0010\u001A\u001A\u00020\u0014J\u0006\u0010\u001F\u001A\u00020\u0014R\u0014\u0010\f\u001A\u00020\rX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u0011\u0010\u0003\u001A\u00020\u00048G\u00A2\u0006\u0006\u001A\u0004\b\u0003\u0010\u0010R\u0011\u0010\u0011\u001A\u00020\b8G\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u000E\u0010\u0013\u001A\u00020\u0014X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001A\u00020\u00168F\u00A2\u0006\u0006\u001A\u0004\b\u0015\u0010\u0017R\u000E\u0010\u0018\u001A\u00020\u0014X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0019\u001A\u00020\u0014X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u001A\u001A\u00020\u0014X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001B\u0010\u001C\"\u0004\b\u001D\u0010\u001ER\u001A\u0010\u001F\u001A\u00020\u0014X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b \u0010\u001C\"\u0004\b!\u0010\u001E\u00A8\u0006I"}, d2 = {"Lokhttp3/Cache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "directory", "Ljava/io/File;", "maxSize", "", "(Ljava/io/File;J)V", "Lokio/Path;", "fileSystem", "Lokio/FileSystem;", "(Lokio/Path;JLokio/FileSystem;)V", "cache", "Lokhttp3/internal/cache/DiskLruCache;", "getCache$okhttp", "()Lokhttp3/internal/cache/DiskLruCache;", "()Ljava/io/File;", "directoryPath", "()Lokio/Path;", "hitCount", "", "isClosed", "", "()Z", "networkCount", "requestCount", "writeAbortCount", "getWriteAbortCount$okhttp", "()I", "setWriteAbortCount$okhttp", "(I)V", "writeSuccessCount", "getWriteSuccessCount$okhttp", "setWriteSuccessCount$okhttp", "abortQuietly", "", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "close", "delete", "-deprecated_directory", "evictAll", "flush", "get", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "get$okhttp", "initialize", "put", "Lokhttp3/internal/cache/CacheRequest;", "response", "put$okhttp", "remove", "remove$okhttp", "size", "trackConditionalCacheHit", "trackConditionalCacheHit$okhttp", "trackResponse", "cacheStrategy", "Lokhttp3/internal/cache/CacheStrategy;", "trackResponse$okhttp", "update", "cached", "network", "update$okhttp", "urls", "", "", "CacheResponseBody", "Companion", "Entry", "RealCacheRequest", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Cache implements Closeable, Flushable, AutoCloseable {
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\n\u0010\u0002\u001A\u00060\u0003R\u00020\u0004\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\b\u0010\u0007\u001A\u00020\rH\u0016J\n\u0010\u0005\u001A\u0004\u0018\u00010\u000EH\u0016J\b\u0010\u000F\u001A\u00020\nH\u0016R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0002\u001A\u00060\u0003R\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\f¨\u0006\u0010"}, d2 = {"Lokhttp3/Cache$CacheResponseBody;", "Lokhttp3/ResponseBody;", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "contentType", "", "contentLength", "(Lokhttp3/internal/cache/DiskLruCache$Snapshot;Ljava/lang/String;Ljava/lang/String;)V", "bodySource", "Lokio/BufferedSource;", "getSnapshot", "()Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "", "Lokhttp3/MediaType;", "source", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    static final class CacheResponseBody extends ResponseBody {
        private final BufferedSource bodySource;
        private final String contentLength;
        private final String contentType;
        private final Snapshot snapshot;

        public CacheResponseBody(Snapshot diskLruCache$Snapshot0, String s, String s1) {
            Intrinsics.checkNotNullParameter(diskLruCache$Snapshot0, "snapshot");
            super();
            this.snapshot = diskLruCache$Snapshot0;
            this.contentType = s;
            this.contentLength = s1;
            this.bodySource = Okio.buffer(new ForwardingSource(this) {
                @Override  // okio.ForwardingSource
                public void close() throws IOException {
                    CacheResponseBody.this.getSnapshot().close();
                    super.close();
                }
            });
        }

        @Override  // okhttp3.ResponseBody
        public long contentLength() {
            return this.contentLength == null ? -1L : _UtilCommonKt.toLongOrDefault(this.contentLength, -1L);
        }

        @Override  // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.contentType == null ? null : MediaType.Companion.parse(this.contentType);
        }

        public final Snapshot getSnapshot() {
            return this.snapshot;
        }

        @Override  // okhttp3.ResponseBody
        public BufferedSource source() {
            return this.bodySource;
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0007J\u0015\u0010\f\u001A\u00020\u00042\u0006\u0010\r\u001A\u00020\u000EH\u0000¢\u0006\u0002\b\u000FJ\u0018\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u00112\u0006\u0010\u0013\u001A\u00020\u0011H\u0002J\u001E\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u00112\u0006\u0010\u0019\u001A\u00020\u001AJ\n\u0010\u001B\u001A\u00020\u0015*\u00020\u0017J\u0012\u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\t0\u001D*\u00020\u0011H\u0002J\n\u0010\u0010\u001A\u00020\u0011*\u00020\u0017R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001E"}, d2 = {"Lokhttp3/Cache$Companion;", "", "()V", "ENTRY_BODY", "", "ENTRY_COUNT", "ENTRY_METADATA", "VERSION", "key", "", "url", "Lokhttp3/HttpUrl;", "readInt", "source", "Lokio/BufferedSource;", "readInt$okhttp", "varyHeaders", "Lokhttp3/Headers;", "requestHeaders", "responseHeaders", "varyMatches", "", "cachedResponse", "Lokhttp3/Response;", "cachedRequest", "newRequest", "Lokhttp3/Request;", "hasVaryAll", "varyFields", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final boolean hasVaryAll(Response response0) {
            Intrinsics.checkNotNullParameter(response0, "<this>");
            return this.varyFields(response0.headers()).contains("*");
        }

        @JvmStatic
        public final String key(HttpUrl httpUrl0) {
            Intrinsics.checkNotNullParameter(httpUrl0, "url");
            return ByteString.Companion.encodeUtf8(httpUrl0.toString()).md5().hex();
        }

        public final int readInt$okhttp(BufferedSource bufferedSource0) throws IOException {
            Intrinsics.checkNotNullParameter(bufferedSource0, "source");
            try {
                long v = bufferedSource0.readDecimalLong();
                String s = bufferedSource0.readUtf8LineStrict();
                if(v < 0L || v > 0x7FFFFFFFL || s.length() > 0) {
                    throw new IOException("expected an int but was \"" + v + s + '\"');
                }
                return (int)v;
            }
            catch(NumberFormatException numberFormatException0) {
                throw new IOException(numberFormatException0.getMessage());
            }
        }

        private final Set varyFields(Headers headers0) {
            int v = headers0.size();
            Set set0 = null;
            for(int v1 = 0; v1 < v; ++v1) {
                if(StringsKt.equals("Vary", headers0.name(v1), true)) {
                    String s = headers0.value(v1);
                    if(set0 == null) {
                        set0 = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
                    }
                    for(Object object0: StringsKt.split$default(s, new char[]{','}, false, 0, 6, null)) {
                        set0.add(StringsKt.trim(((String)object0)).toString());
                    }
                }
            }
            return set0 == null ? SetsKt.emptySet() : set0;
        }

        private final Headers varyHeaders(Headers headers0, Headers headers1) {
            Set set0 = this.varyFields(headers1);
            if(set0.isEmpty()) {
                return _UtilJvmKt.EMPTY_HEADERS;
            }
            Builder headers$Builder0 = new Builder();
            int v = headers0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                String s = headers0.name(v1);
                if(set0.contains(s)) {
                    headers$Builder0.add(s, headers0.value(v1));
                }
            }
            return headers$Builder0.build();
        }

        public final Headers varyHeaders(Response response0) {
            Intrinsics.checkNotNullParameter(response0, "<this>");
            Response response1 = response0.networkResponse();
            Intrinsics.checkNotNull(response1);
            return this.varyHeaders(response1.request().headers(), response0.headers());
        }

        public final boolean varyMatches(Response response0, Headers headers0, Request request0) {
            Intrinsics.checkNotNullParameter(response0, "cachedResponse");
            Intrinsics.checkNotNullParameter(headers0, "cachedRequest");
            Intrinsics.checkNotNullParameter(request0, "newRequest");
            Iterable iterable0 = this.varyFields(response0.headers());
            if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
                return true;
            }
            for(Object object0: iterable0) {
                if(!Intrinsics.areEqual(headers0.values(((String)object0)), request0.headers(((String)object0)))) {
                    return false;
                }
                if(false) {
                    break;
                }
            }
            return true;
        }
    }

    @Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 -2\u00020\u0001:\u0001-B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000F\b\u0016\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\u001C2\u0006\u0010\u0005\u001A\u00020\u0006J\u0016\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\u001F0\u001E2\u0006\u0010 \u001A\u00020!H\u0002J\u0012\u0010\u0005\u001A\u00020\u00062\n\u0010\"\u001A\u00060#R\u00020$J\u001E\u0010%\u001A\u00020&2\u0006\u0010\'\u001A\u00020(2\f\u0010)\u001A\b\u0012\u0004\u0012\u00020\u001F0\u001EH\u0002J\u0012\u0010*\u001A\u00020&2\n\u0010+\u001A\u00060,R\u00020$R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001A\u0004\u0018\u00010\u000BX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u000FX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0013\u001A\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0015\u001A\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0016\u001A\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0018\u001A\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lokhttp3/Cache$Entry;", "", "rawSource", "Lokio/Source;", "(Lokio/Source;)V", "response", "Lokhttp3/Response;", "(Lokhttp3/Response;)V", "code", "", "handshake", "Lokhttp3/Handshake;", "message", "", "protocol", "Lokhttp3/Protocol;", "receivedResponseMillis", "", "requestMethod", "responseHeaders", "Lokhttp3/Headers;", "sentRequestMillis", "url", "Lokhttp3/HttpUrl;", "varyHeaders", "matches", "", "request", "Lokhttp3/Request;", "readCertificateList", "", "Ljava/security/cert/Certificate;", "source", "Lokio/BufferedSource;", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "writeCertList", "", "sink", "Lokio/BufferedSink;", "certificates", "writeTo", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    static final class Entry {
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lokhttp3/Cache$Entry$Companion;", "", "()V", "RECEIVED_MILLIS", "", "SENT_MILLIS", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
        public static final class okhttp3.Cache.Entry.Companion {
            private okhttp3.Cache.Entry.Companion() {
            }

            public okhttp3.Cache.Entry.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }
        }

        public static final okhttp3.Cache.Entry.Companion Companion;
        private static final String RECEIVED_MILLIS;
        private static final String SENT_MILLIS;
        private final int code;
        private final Handshake handshake;
        private final String message;
        private final Protocol protocol;
        private final long receivedResponseMillis;
        private final String requestMethod;
        private final Headers responseHeaders;
        private final long sentRequestMillis;
        private final HttpUrl url;
        private final Headers varyHeaders;

        // 去混淆评级： 低(20)
        static {
            Entry.Companion = new okhttp3.Cache.Entry.Companion(null);
            Entry.SENT_MILLIS = "OkHttp-Sent-Millis";
            Entry.RECEIVED_MILLIS = "OkHttp-Received-Millis";
        }

        public Entry(Response response0) {
            Intrinsics.checkNotNullParameter(response0, "response");
            super();
            this.url = response0.request().url();
            this.varyHeaders = Cache.Companion.varyHeaders(response0);
            this.requestMethod = response0.request().method();
            this.protocol = response0.protocol();
            this.code = response0.code();
            this.message = response0.message();
            this.responseHeaders = response0.headers();
            this.handshake = response0.handshake();
            this.sentRequestMillis = response0.sentRequestAtMillis();
            this.receivedResponseMillis = response0.receivedResponseAtMillis();
        }

        public Entry(Source source0) throws IOException {
            Intrinsics.checkNotNullParameter(source0, "rawSource");
            TlsVersion tlsVersion0;
            super();
            try {
                BufferedSource bufferedSource0 = Okio.buffer(source0);
                String s = bufferedSource0.readUtf8LineStrict();
                HttpUrl httpUrl0 = HttpUrl.Companion.parse(s);
                if(httpUrl0 == null) {
                    IOException iOException0 = new IOException("Cache corruption for " + s);
                    Platform.Companion.get().log("cache corruption", 5, iOException0);
                    throw iOException0;
                }
                this.url = httpUrl0;
                this.requestMethod = bufferedSource0.readUtf8LineStrict();
                Builder headers$Builder0 = new Builder();
                int v = Cache.Companion.readInt$okhttp(bufferedSource0);
                for(int v2 = 0; v2 < v; ++v2) {
                    headers$Builder0.addLenient$okhttp(bufferedSource0.readUtf8LineStrict());
                }
                this.varyHeaders = headers$Builder0.build();
                String s1 = bufferedSource0.readUtf8LineStrict();
                StatusLine statusLine0 = StatusLine.Companion.parse(s1);
                this.protocol = statusLine0.protocol;
                this.code = statusLine0.code;
                this.message = statusLine0.message;
                Builder headers$Builder1 = new Builder();
                int v3 = Cache.Companion.readInt$okhttp(bufferedSource0);
                for(int v1 = 0; v1 < v3; ++v1) {
                    headers$Builder1.addLenient$okhttp(bufferedSource0.readUtf8LineStrict());
                }
                String s2 = headers$Builder1.get(Entry.SENT_MILLIS);
                String s3 = headers$Builder1.get(Entry.RECEIVED_MILLIS);
                headers$Builder1.removeAll(Entry.SENT_MILLIS);
                headers$Builder1.removeAll(Entry.RECEIVED_MILLIS);
                long v4 = 0L;
                this.sentRequestMillis = s2 == null ? 0L : Long.parseLong(s2);
                if(s3 != null) {
                    v4 = Long.parseLong(s3);
                }
                this.receivedResponseMillis = v4;
                this.responseHeaders = headers$Builder1.build();
                if(this.url.isHttps()) {
                    String s4 = bufferedSource0.readUtf8LineStrict();
                    if(s4.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + s4 + '\"');
                    }
                    String s5 = bufferedSource0.readUtf8LineStrict();
                    CipherSuite cipherSuite0 = CipherSuite.Companion.forJavaName(s5);
                    List list0 = this.readCertificateList(bufferedSource0);
                    List list1 = this.readCertificateList(bufferedSource0);
                    if(bufferedSource0.exhausted()) {
                        tlsVersion0 = TlsVersion.SSL_3_0;
                    }
                    else {
                        String s6 = bufferedSource0.readUtf8LineStrict();
                        tlsVersion0 = TlsVersion.Companion.forJavaName(s6);
                    }
                    this.handshake = Handshake.Companion.get(tlsVersion0, cipherSuite0, list0, list1);
                }
                else {
                    this.handshake = null;
                }
            }
            catch(Throwable throwable0) {
                CloseableKt.closeFinally(source0, throwable0);
                throw throwable0;
            }
            CloseableKt.closeFinally(source0, null);
        }

        public final boolean matches(Request request0, Response response0) {
            Intrinsics.checkNotNullParameter(request0, "request");
            Intrinsics.checkNotNullParameter(response0, "response");
            return Intrinsics.areEqual(this.url, request0.url()) && Intrinsics.areEqual(this.requestMethod, request0.method()) && Cache.Companion.varyMatches(response0, this.varyHeaders, request0);
        }

        private final List readCertificateList(BufferedSource bufferedSource0) throws IOException {
            int v = Cache.Companion.readInt$okhttp(bufferedSource0);
            if(v == -1) {
                return CollectionsKt.emptyList();
            }
            try {
                CertificateFactory certificateFactory0 = CertificateFactory.getInstance("X.509");
                ArrayList arrayList0 = new ArrayList(v);
                for(int v1 = 0; v1 < v; ++v1) {
                    String s = bufferedSource0.readUtf8LineStrict();
                    Buffer buffer0 = new Buffer();
                    ByteString byteString0 = ByteString.Companion.decodeBase64(s);
                    Intrinsics.checkNotNull(byteString0);
                    buffer0.write(byteString0);
                    arrayList0.add(certificateFactory0.generateCertificate(buffer0.inputStream()));
                }
                return arrayList0;
            }
            catch(CertificateException certificateException0) {
                throw new IOException(certificateException0.getMessage());
            }
        }

        public final Response response(Snapshot diskLruCache$Snapshot0) {
            Intrinsics.checkNotNullParameter(diskLruCache$Snapshot0, "snapshot");
            String s = this.responseHeaders.get("Content-Type");
            String s1 = this.responseHeaders.get("Content-Length");
            Request request0 = new Request(this.url, this.varyHeaders, this.requestMethod, null, 8, null);
            return new okhttp3.Response.Builder().request(request0).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new CacheResponseBody(diskLruCache$Snapshot0, s, s1)).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
        }

        private final void writeCertList(BufferedSink bufferedSink0, List list0) throws IOException {
            try {
                bufferedSink0.writeDecimalLong(((long)list0.size())).writeByte(10);
                for(Object object0: list0) {
                    byte[] arr_b = ((Certificate)object0).getEncoded();
                    Intrinsics.checkNotNullExpressionValue(arr_b, "bytes");
                    bufferedSink0.writeUtf8(okio.ByteString.Companion.of$default(ByteString.Companion, arr_b, 0, 0, 3, null).base64()).writeByte(10);
                }
            }
            catch(CertificateEncodingException certificateEncodingException0) {
                throw new IOException(certificateEncodingException0.getMessage());
            }
        }

        public final void writeTo(Editor diskLruCache$Editor0) throws IOException {
            Intrinsics.checkNotNullParameter(diskLruCache$Editor0, "editor");
            Closeable closeable0 = Okio.buffer(diskLruCache$Editor0.newSink(0));
            try {
                ((BufferedSink)closeable0).writeUtf8(this.url.toString()).writeByte(10);
                ((BufferedSink)closeable0).writeUtf8(this.requestMethod).writeByte(10);
                ((BufferedSink)closeable0).writeDecimalLong(((long)this.varyHeaders.size())).writeByte(10);
                int v1 = this.varyHeaders.size();
                for(int v2 = 0; v2 < v1; ++v2) {
                    ((BufferedSink)closeable0).writeUtf8(this.varyHeaders.name(v2)).writeUtf8(": ").writeUtf8(this.varyHeaders.value(v2)).writeByte(10);
                }
                ((BufferedSink)closeable0).writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString()).writeByte(10);
                ((BufferedSink)closeable0).writeDecimalLong(((long)(this.responseHeaders.size() + 2))).writeByte(10);
                int v3 = this.responseHeaders.size();
                for(int v = 0; v < v3; ++v) {
                    ((BufferedSink)closeable0).writeUtf8(this.responseHeaders.name(v)).writeUtf8(": ").writeUtf8(this.responseHeaders.value(v)).writeByte(10);
                }
                ((BufferedSink)closeable0).writeUtf8(Entry.SENT_MILLIS).writeUtf8(": ").writeDecimalLong(this.sentRequestMillis).writeByte(10);
                ((BufferedSink)closeable0).writeUtf8(Entry.RECEIVED_MILLIS).writeUtf8(": ").writeDecimalLong(this.receivedResponseMillis).writeByte(10);
                if(this.url.isHttps()) {
                    ((BufferedSink)closeable0).writeByte(10);
                    Intrinsics.checkNotNull(this.handshake);
                    ((BufferedSink)closeable0).writeUtf8(this.handshake.cipherSuite().javaName()).writeByte(10);
                    this.writeCertList(((BufferedSink)closeable0), this.handshake.peerCertificates());
                    this.writeCertList(((BufferedSink)closeable0), this.handshake.localCertificates());
                    ((BufferedSink)closeable0).writeUtf8(this.handshake.tlsVersion().javaName()).writeByte(10);
                }
            }
            catch(Throwable throwable0) {
                CloseableKt.closeFinally(closeable0, throwable0);
                throw throwable0;
            }
            CloseableKt.closeFinally(closeable0, null);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001A\u00060\u0003R\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u000F\u001A\u00020\u0010H\u0016J\b\u0010\u0006\u001A\u00020\u0007H\u0016R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\t\u001A\u00020\nX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000B\u0010\f\"\u0004\b\r\u0010\u000ER\u0012\u0010\u0002\u001A\u00060\u0003R\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/Cache$RealCacheRequest;", "Lokhttp3/internal/cache/CacheRequest;", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "(Lokhttp3/Cache;Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "body", "Lokio/Sink;", "cacheOut", "done", "", "getDone", "()Z", "setDone", "(Z)V", "abort", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    final class RealCacheRequest implements CacheRequest {
        private final Sink body;
        private final Sink cacheOut;
        private boolean done;
        private final Editor editor;

        public RealCacheRequest(Editor diskLruCache$Editor0) {
            Intrinsics.checkNotNullParameter(diskLruCache$Editor0, "editor");
            Cache.this = cache0;
            super();
            this.editor = diskLruCache$Editor0;
            Sink sink0 = diskLruCache$Editor0.newSink(1);
            this.cacheOut = sink0;
            this.body = new ForwardingSink(/*ERROR_MISSING_ARG_2/*) {
                @Override  // okio.ForwardingSink
                public void close() throws IOException {
                    Cache cache0 = this;
                    RealCacheRequest cache$RealCacheRequest0 = sink0;
                    synchronized(cache0) {
                        if(cache$RealCacheRequest0.getDone()) {
                            return;
                        }
                        cache$RealCacheRequest0.setDone(true);
                        cache0.setWriteSuccessCount$okhttp(cache0.getWriteSuccessCount$okhttp() + 1);
                    }
                    super.close();
                    sink0.editor.commit();
                }
            };
        }

        @Override  // okhttp3.internal.cache.CacheRequest
        public void abort() {
            Cache cache0 = Cache.this;
            synchronized(cache0) {
                if(this.done) {
                    return;
                }
                this.done = true;
                cache0.setWriteAbortCount$okhttp(cache0.getWriteAbortCount$okhttp() + 1);
            }
            _UtilCommonKt.closeQuietly(this.cacheOut);
            try {
                this.editor.abort();
            }
            catch(IOException unused_ex) {
            }
        }

        @Override  // okhttp3.internal.cache.CacheRequest
        public Sink body() {
            return this.body;
        }

        public final boolean getDone() {
            return this.done;
        }

        public final void setDone(boolean z) {
            this.done = z;
        }
    }

    public static final Companion Companion = null;
    private static final int ENTRY_BODY = 1;
    private static final int ENTRY_COUNT = 2;
    private static final int ENTRY_METADATA = 0;
    private static final int VERSION = 201105;
    private final DiskLruCache cache;
    private int hitCount;
    private int networkCount;
    private int requestCount;
    private int writeAbortCount;
    private int writeSuccessCount;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "directory", imports = {}))
    public final File -deprecated_directory() {
        return this.cache.getDirectory().toFile();
    }

    static {
        Cache.Companion = new Companion(null);
    }

    public Cache(File file0, long v) {
        Intrinsics.checkNotNullParameter(file0, "directory");
        this(okio.Path.Companion.get$default(Path.Companion, file0, false, 1, null), v, FileSystem.SYSTEM);
    }

    public Cache(Path path0, long v, FileSystem fileSystem0) {
        Intrinsics.checkNotNullParameter(path0, "directory");
        Intrinsics.checkNotNullParameter(fileSystem0, "fileSystem");
        super();
        this.cache = new DiskLruCache(fileSystem0, path0, 201105, 2, v, TaskRunner.INSTANCE);
    }

    private final void abortQuietly(Editor diskLruCache$Editor0) {
        if(diskLruCache$Editor0 != null) {
            try {
                diskLruCache$Editor0.abort();
            }
            catch(IOException unused_ex) {
            }
        }
    }

    @Override
    public void close() throws IOException {
        this.cache.close();
    }

    public final void delete() throws IOException {
        this.cache.delete();
    }

    public final File directory() {
        return this.cache.getDirectory().toFile();
    }

    public final Path directoryPath() {
        return this.cache.getDirectory();
    }

    public final void evictAll() throws IOException {
        this.cache.evictAll();
    }

    @Override
    public void flush() throws IOException {
        this.cache.flush();
    }

    public final Response get$okhttp(Request request0) {
        Entry cache$Entry0;
        Snapshot diskLruCache$Snapshot0;
        Intrinsics.checkNotNullParameter(request0, "request");
        String s = Cache.Companion.key(request0.url());
        try {
            diskLruCache$Snapshot0 = this.cache.get(s);
            if(diskLruCache$Snapshot0 == null) {
                return null;
            }
            goto label_5;
        }
        catch(IOException unused_ex) {
        }
        return null;
        try {
        label_5:
            cache$Entry0 = new Entry(diskLruCache$Snapshot0.getSource(0));
        }
        catch(IOException unused_ex) {
            _UtilCommonKt.closeQuietly(diskLruCache$Snapshot0);
            return null;
        }
        Response response0 = cache$Entry0.response(diskLruCache$Snapshot0);
        if(!cache$Entry0.matches(request0, response0)) {
            _UtilCommonKt.closeQuietly(response0.body());
            return null;
        }
        return response0;
    }

    public final DiskLruCache getCache$okhttp() {
        return this.cache;
    }

    public final int getWriteAbortCount$okhttp() {
        return this.writeAbortCount;
    }

    public final int getWriteSuccessCount$okhttp() {
        return this.writeSuccessCount;
    }

    public final int hitCount() {
        synchronized(this) {
        }
        return this.hitCount;
    }

    public final void initialize() throws IOException {
        this.cache.initialize();
    }

    public final boolean isClosed() {
        return this.cache.isClosed();
    }

    @JvmStatic
    public static final String key(HttpUrl httpUrl0) {
        return Cache.Companion.key(httpUrl0);
    }

    public final long maxSize() {
        return this.cache.getMaxSize();
    }

    public final int networkCount() {
        synchronized(this) {
        }
        return this.networkCount;
    }

    public final CacheRequest put$okhttp(Response response0) {
        Editor diskLruCache$Editor0;
        Intrinsics.checkNotNullParameter(response0, "response");
        String s = response0.request().method();
        if(HttpMethod.invalidatesCache(response0.request().method())) {
            try {
                this.remove$okhttp(response0.request());
            }
            catch(IOException unused_ex) {
            }
            return null;
        }
        if(!Intrinsics.areEqual(s, "GET")) {
            return null;
        }
        Companion cache$Companion0 = Cache.Companion;
        if(cache$Companion0.hasVaryAll(response0)) {
            return null;
        }
        Entry cache$Entry0 = new Entry(response0);
        try {
            String s1 = cache$Companion0.key(response0.request().url());
            diskLruCache$Editor0 = null;
            diskLruCache$Editor0 = DiskLruCache.edit$default(this.cache, s1, 0L, 2, null);
            if(diskLruCache$Editor0 != null) {
                cache$Entry0.writeTo(diskLruCache$Editor0);
                return new RealCacheRequest(this, diskLruCache$Editor0);
            }
        }
        catch(IOException unused_ex) {
            this.abortQuietly(diskLruCache$Editor0);
        }
        return null;
    }

    public final void remove$okhttp(Request request0) throws IOException {
        Intrinsics.checkNotNullParameter(request0, "request");
        String s = Cache.Companion.key(request0.url());
        this.cache.remove(s);
    }

    public final int requestCount() {
        synchronized(this) {
        }
        return this.requestCount;
    }

    public final void setWriteAbortCount$okhttp(int v) {
        this.writeAbortCount = v;
    }

    public final void setWriteSuccessCount$okhttp(int v) {
        this.writeSuccessCount = v;
    }

    public final long size() throws IOException {
        return this.cache.size();
    }

    public final void trackConditionalCacheHit$okhttp() {
        synchronized(this) {
            ++this.hitCount;
        }
    }

    public final void trackResponse$okhttp(CacheStrategy cacheStrategy0) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(cacheStrategy0, "cacheStrategy");
            ++this.requestCount;
            if(cacheStrategy0.getNetworkRequest() != null) {
                ++this.networkCount;
            }
            else if(cacheStrategy0.getCacheResponse() != null) {
                ++this.hitCount;
            }
        }
    }

    public final void update$okhttp(Response response0, Response response1) {
        Editor diskLruCache$Editor0;
        Intrinsics.checkNotNullParameter(response0, "cached");
        Intrinsics.checkNotNullParameter(response1, "network");
        Entry cache$Entry0 = new Entry(response1);
        ResponseBody responseBody0 = response0.body();
        Intrinsics.checkNotNull(responseBody0, "null cannot be cast to non-null type okhttp3.Cache.CacheResponseBody");
        Snapshot diskLruCache$Snapshot0 = ((CacheResponseBody)responseBody0).getSnapshot();
        try {
            diskLruCache$Editor0 = null;
            diskLruCache$Editor0 = diskLruCache$Snapshot0.edit();
            if(diskLruCache$Editor0 != null) {
                cache$Entry0.writeTo(diskLruCache$Editor0);
                diskLruCache$Editor0.commit();
            }
        }
        catch(IOException unused_ex) {
            this.abortQuietly(diskLruCache$Editor0);
        }
    }

    public final Iterator urls() throws IOException {
        return new Object() {
            private boolean canRemove;
            private final Iterator delegate;
            private String nextUrl;

            {
                this.delegate = cache0.getCache$okhttp().snapshots();
            }

            @Override
            public boolean hasNext() {
                if(this.nextUrl != null) {
                    return true;
                }
                this.canRemove = false;
                while(this.delegate.hasNext()) {
                    try {
                        Object object0 = this.delegate.next();
                        Closeable closeable0 = (Closeable)object0;
                        try {
                            this.nextUrl = Okio.buffer(((Snapshot)closeable0).getSource(0)).readUtf8LineStrict();
                        }
                        catch(Throwable throwable0) {
                            CloseableKt.closeFinally(closeable0, throwable0);
                            throw throwable0;
                        }
                        CloseableKt.closeFinally(closeable0, null);
                        return true;
                    }
                    catch(IOException unused_ex) {
                    }
                }
                return false;
            }

            @Override
            public Object next() {
                return this.next();
            }

            public String next() {
                if(!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                String s = this.nextUrl;
                Intrinsics.checkNotNull(s);
                this.nextUrl = null;
                this.canRemove = true;
                return s;
            }

            @Override
            public void remove() {
                if(!this.canRemove) {
                    throw new IllegalStateException("remove() before next()");
                }
                this.delegate.remove();
            }
        };
    }

    public final int writeAbortCount() {
        synchronized(this) {
        }
        return this.writeAbortCount;
    }

    public final int writeSuccessCount() {
        synchronized(this) {
        }
        return this.writeSuccessCount;
    }
}

