package okhttp3;

import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationUnit;
import okhttp3.internal._CacheControlCommonKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000E\n\u0002\b\u0015\u0018\u0000 %2\u00020\u0001:\u0002$%Bq\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\u0006\u0012\u0006\u0010\b\u001A\u00020\u0003\u0012\u0006\u0010\t\u001A\u00020\u0003\u0012\u0006\u0010\n\u001A\u00020\u0003\u0012\u0006\u0010\u000B\u001A\u00020\u0006\u0012\u0006\u0010\f\u001A\u00020\u0006\u0012\u0006\u0010\r\u001A\u00020\u0003\u0012\u0006\u0010\u000E\u001A\u00020\u0003\u0012\u0006\u0010\u000F\u001A\u00020\u0003\u0012\b\u0010\u0010\u001A\u0004\u0018\u00010\u0011\u00A2\u0006\u0002\u0010\u0012J\r\u0010\u000F\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b\u0019J\r\u0010\u0005\u001A\u00020\u0006H\u0007\u00A2\u0006\u0002\b\u001AJ\r\u0010\u000B\u001A\u00020\u0006H\u0007\u00A2\u0006\u0002\b\u001BJ\r\u0010\f\u001A\u00020\u0006H\u0007\u00A2\u0006\u0002\b\u001CJ\r\u0010\n\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b\u001DJ\r\u0010\u0002\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b\u001EJ\r\u0010\u0004\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b\u001FJ\r\u0010\u000E\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b J\r\u0010\r\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b!J\r\u0010\u0007\u001A\u00020\u0006H\u0007\u00A2\u0006\u0002\b\"J\b\u0010#\u001A\u00020\u0011H\u0016R\u001C\u0010\u0010\u001A\u0004\u0018\u00010\u0011X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0013\u0010\u000F\u001A\u00020\u00038\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0017R\u0011\u0010\b\u001A\u00020\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u0017R\u0011\u0010\t\u001A\u00020\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\t\u0010\u0017R\u0013\u0010\u0005\u001A\u00020\u00068\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0018R\u0013\u0010\u000B\u001A\u00020\u00068\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\u0018R\u0013\u0010\f\u001A\u00020\u00068\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\f\u0010\u0018R\u0013\u0010\n\u001A\u00020\u00038\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u0017R\u0013\u0010\u0002\u001A\u00020\u00038\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0017R\u0013\u0010\u0004\u001A\u00020\u00038\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0017R\u0013\u0010\u000E\u001A\u00020\u00038\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u0017R\u0013\u0010\r\u001A\u00020\u00038\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u0017R\u0013\u0010\u0007\u001A\u00020\u00068\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\u0018\u00A8\u0006&"}, d2 = {"Lokhttp3/CacheControl;", "", "noCache", "", "noStore", "maxAgeSeconds", "", "sMaxAgeSeconds", "isPrivate", "isPublic", "mustRevalidate", "maxStaleSeconds", "minFreshSeconds", "onlyIfCached", "noTransform", "immutable", "headerValue", "", "(ZZIIZZZIIZZZLjava/lang/String;)V", "getHeaderValue$okhttp", "()Ljava/lang/String;", "setHeaderValue$okhttp", "(Ljava/lang/String;)V", "()Z", "()I", "-deprecated_immutable", "-deprecated_maxAgeSeconds", "-deprecated_maxStaleSeconds", "-deprecated_minFreshSeconds", "-deprecated_mustRevalidate", "-deprecated_noCache", "-deprecated_noStore", "-deprecated_noTransform", "-deprecated_onlyIfCached", "-deprecated_sMaxAgeSeconds", "toString", "Builder", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class CacheControl {
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010!\u001A\u00020\"J\u0006\u0010\u0003\u001A\u00020\u0000J\u0016\u0010#\u001A\u00020\u00002\u0006\u0010#\u001A\u00020\n2\u0006\u0010$\u001A\u00020%J\u0016\u0010#\u001A\u00020\u00002\u0006\u0010#\u001A\u00020\n2\u0006\u0010$\u001A\u00020&J\u0016\u0010\'\u001A\u00020\u00002\u0006\u0010\'\u001A\u00020\n2\u0006\u0010$\u001A\u00020%J\u0016\u0010\'\u001A\u00020\u00002\u0006\u0010\'\u001A\u00020\n2\u0006\u0010$\u001A\u00020&J\u0016\u0010(\u001A\u00020\u00002\u0006\u0010(\u001A\u00020\n2\u0006\u0010$\u001A\u00020%J\u0016\u0010(\u001A\u00020\u00002\u0006\u0010(\u001A\u00020\n2\u0006\u0010$\u001A\u00020&J\u0006\u0010\u0015\u001A\u00020\u0000J\u0006\u0010\u0018\u001A\u00020\u0000J\u0006\u0010\u001B\u001A\u00020\u0000J\u0006\u0010\u001E\u001A\u00020\u0000R\u001A\u0010\u0003\u001A\u00020\u0004X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001A\u0010\t\u001A\u00020\nX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000B\u0010\f\"\u0004\b\r\u0010\u000ER\u001A\u0010\u000F\u001A\u00020\nX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000ER\u001A\u0010\u0012\u001A\u00020\nX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000ER\u001A\u0010\u0015\u001A\u00020\u0004X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001A\u0010\u0018\u001A\u00020\u0004X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0019\u0010\u0006\"\u0004\b\u001A\u0010\bR\u001A\u0010\u001B\u001A\u00020\u0004X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u001C\u0010\u0006\"\u0004\b\u001D\u0010\bR\u001A\u0010\u001E\u001A\u00020\u0004X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u001F\u0010\u0006\"\u0004\b \u0010\b¨\u0006)"}, d2 = {"Lokhttp3/CacheControl$Builder;", "", "()V", "immutable", "", "getImmutable$okhttp", "()Z", "setImmutable$okhttp", "(Z)V", "maxAgeSeconds", "", "getMaxAgeSeconds$okhttp", "()I", "setMaxAgeSeconds$okhttp", "(I)V", "maxStaleSeconds", "getMaxStaleSeconds$okhttp", "setMaxStaleSeconds$okhttp", "minFreshSeconds", "getMinFreshSeconds$okhttp", "setMinFreshSeconds$okhttp", "noCache", "getNoCache$okhttp", "setNoCache$okhttp", "noStore", "getNoStore$okhttp", "setNoStore$okhttp", "noTransform", "getNoTransform$okhttp", "setNoTransform$okhttp", "onlyIfCached", "getOnlyIfCached$okhttp", "setOnlyIfCached$okhttp", "build", "Lokhttp3/CacheControl;", "maxAge", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "maxStale", "minFresh", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Builder {
        private boolean immutable;
        private int maxAgeSeconds;
        private int maxStaleSeconds;
        private int minFreshSeconds;
        private boolean noCache;
        private boolean noStore;
        private boolean noTransform;
        private boolean onlyIfCached;

        public Builder() {
            this.maxAgeSeconds = -1;
            this.maxStaleSeconds = -1;
            this.minFreshSeconds = -1;
        }

        public final CacheControl build() {
            return _CacheControlCommonKt.commonBuild(this);
        }

        public final boolean getImmutable$okhttp() {
            return this.immutable;
        }

        public final int getMaxAgeSeconds$okhttp() {
            return this.maxAgeSeconds;
        }

        public final int getMaxStaleSeconds$okhttp() {
            return this.maxStaleSeconds;
        }

        public final int getMinFreshSeconds$okhttp() {
            return this.minFreshSeconds;
        }

        public final boolean getNoCache$okhttp() {
            return this.noCache;
        }

        public final boolean getNoStore$okhttp() {
            return this.noStore;
        }

        public final boolean getNoTransform$okhttp() {
            return this.noTransform;
        }

        public final boolean getOnlyIfCached$okhttp() {
            return this.onlyIfCached;
        }

        public final Builder immutable() {
            return _CacheControlCommonKt.commonImmutable(this);
        }

        public final Builder maxAge(int v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
            if(v < 0) {
                throw new IllegalArgumentException(("maxAge < 0: " + v).toString());
            }
            this.maxAgeSeconds = _CacheControlCommonKt.commonClampToInt(timeUnit0.toSeconds(((long)v)));
            return this;
        }

        public final Builder maxAge(int v, DurationUnit durationUnit0) {
            Intrinsics.checkNotNullParameter(durationUnit0, "timeUnit");
            return _CacheControlCommonKt.commonMaxAge(this, v, durationUnit0);
        }

        public final Builder maxStale(int v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
            if(v < 0) {
                throw new IllegalArgumentException(("maxStale < 0: " + v).toString());
            }
            this.maxStaleSeconds = _CacheControlCommonKt.commonClampToInt(timeUnit0.toSeconds(((long)v)));
            return this;
        }

        public final Builder maxStale(int v, DurationUnit durationUnit0) {
            Intrinsics.checkNotNullParameter(durationUnit0, "timeUnit");
            return _CacheControlCommonKt.commonMaxStale(this, v, durationUnit0);
        }

        public final Builder minFresh(int v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
            if(v < 0) {
                throw new IllegalArgumentException(("minFresh < 0: " + v).toString());
            }
            this.minFreshSeconds = _CacheControlCommonKt.commonClampToInt(timeUnit0.toSeconds(((long)v)));
            return this;
        }

        public final Builder minFresh(int v, DurationUnit durationUnit0) {
            Intrinsics.checkNotNullParameter(durationUnit0, "timeUnit");
            return _CacheControlCommonKt.commonMinFresh(this, v, durationUnit0);
        }

        public final Builder noCache() {
            return _CacheControlCommonKt.commonNoCache(this);
        }

        public final Builder noStore() {
            return _CacheControlCommonKt.commonNoStore(this);
        }

        public final Builder noTransform() {
            return _CacheControlCommonKt.commonNoTransform(this);
        }

        public final Builder onlyIfCached() {
            return _CacheControlCommonKt.commonOnlyIfCached(this);
        }

        public final void setImmutable$okhttp(boolean z) {
            this.immutable = z;
        }

        public final void setMaxAgeSeconds$okhttp(int v) {
            this.maxAgeSeconds = v;
        }

        public final void setMaxStaleSeconds$okhttp(int v) {
            this.maxStaleSeconds = v;
        }

        public final void setMinFreshSeconds$okhttp(int v) {
            this.minFreshSeconds = v;
        }

        public final void setNoCache$okhttp(boolean z) {
            this.noCache = z;
        }

        public final void setNoStore$okhttp(boolean z) {
            this.noStore = z;
        }

        public final void setNoTransform$okhttp(boolean z) {
            this.noTransform = z;
        }

        public final void setOnlyIfCached$okhttp(boolean z) {
            this.onlyIfCached = z;
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001A\u00020\u00042\u0006\u0010\u0007\u001A\u00020\bH\u0007R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lokhttp3/CacheControl$Companion;", "", "()V", "FORCE_CACHE", "Lokhttp3/CacheControl;", "FORCE_NETWORK", "parse", "headers", "Lokhttp3/Headers;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final CacheControl parse(Headers headers0) {
            Intrinsics.checkNotNullParameter(headers0, "headers");
            return _CacheControlCommonKt.commonParse(this, headers0);
        }
    }

    public static final Companion Companion;
    public static final CacheControl FORCE_CACHE;
    public static final CacheControl FORCE_NETWORK;
    private String headerValue;
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "immutable", imports = {}))
    public final boolean -deprecated_immutable() {
        return this.immutable;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "maxAgeSeconds", imports = {}))
    public final int -deprecated_maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "maxStaleSeconds", imports = {}))
    public final int -deprecated_maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "minFreshSeconds", imports = {}))
    public final int -deprecated_minFreshSeconds() {
        return this.minFreshSeconds;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "mustRevalidate", imports = {}))
    public final boolean -deprecated_mustRevalidate() {
        return this.mustRevalidate;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "noCache", imports = {}))
    public final boolean -deprecated_noCache() {
        return this.noCache;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "noStore", imports = {}))
    public final boolean -deprecated_noStore() {
        return this.noStore;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "noTransform", imports = {}))
    public final boolean -deprecated_noTransform() {
        return this.noTransform;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "onlyIfCached", imports = {}))
    public final boolean -deprecated_onlyIfCached() {
        return this.onlyIfCached;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sMaxAgeSeconds", imports = {}))
    public final int -deprecated_sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    static {
        Companion cacheControl$Companion0 = new Companion(null);
        CacheControl.Companion = cacheControl$Companion0;
        CacheControl.FORCE_NETWORK = _CacheControlCommonKt.commonForceNetwork(cacheControl$Companion0);
        CacheControl.FORCE_CACHE = _CacheControlCommonKt.commonForceCache(cacheControl$Companion0);
    }

    public CacheControl(boolean z, boolean z1, int v, int v1, boolean z2, boolean z3, boolean z4, int v2, int v3, boolean z5, boolean z6, boolean z7, String s) {
        this.noCache = z;
        this.noStore = z1;
        this.maxAgeSeconds = v;
        this.sMaxAgeSeconds = v1;
        this.isPrivate = z2;
        this.isPublic = z3;
        this.mustRevalidate = z4;
        this.maxStaleSeconds = v2;
        this.minFreshSeconds = v3;
        this.onlyIfCached = z5;
        this.noTransform = z6;
        this.immutable = z7;
        this.headerValue = s;
    }

    public final String getHeaderValue$okhttp() {
        return this.headerValue;
    }

    public final boolean immutable() {
        return this.immutable;
    }

    public final boolean isPrivate() {
        return this.isPrivate;
    }

    public final boolean isPublic() {
        return this.isPublic;
    }

    public final int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public final int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public final int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public final boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public final boolean noCache() {
        return this.noCache;
    }

    public final boolean noStore() {
        return this.noStore;
    }

    public final boolean noTransform() {
        return this.noTransform;
    }

    public final boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    @JvmStatic
    public static final CacheControl parse(Headers headers0) {
        return CacheControl.Companion.parse(headers0);
    }

    public final int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public final void setHeaderValue$okhttp(String s) {
        this.headerValue = s;
    }

    @Override
    public String toString() {
        return _CacheControlCommonKt.commonToString(this);
    }
}

