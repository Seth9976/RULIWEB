package okhttp3;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.internal._ChallengeCommonKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003¢\u0006\u0002\u0010\u0005B#\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0014\u0010\u0006\u001A\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bJ\u001B\u0010\u0006\u001A\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00030\u0007H\u0007¢\u0006\u0002\b\u000EJ\r\u0010\n\u001A\u00020\u000BH\u0007¢\u0006\u0002\b\u000FJ\u0013\u0010\u0010\u001A\u00020\u00112\b\u0010\u0012\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001A\u00020\u0014H\u0016J\u000F\u0010\u0004\u001A\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b\u0015J\r\u0010\u0002\u001A\u00020\u0003H\u0007¢\u0006\u0002\b\u0016J\b\u0010\u0017\u001A\u00020\u0003H\u0016J\u000E\u0010\u0018\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u000BR!\u0010\u0006\u001A\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00030\u00078G¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\tR\u0011\u0010\n\u001A\u00020\u000B8G¢\u0006\u0006\u001A\u0004\b\n\u0010\fR\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u00038G¢\u0006\u0006\u001A\u0004\b\u0004\u0010\rR\u0013\u0010\u0002\u001A\u00020\u00038\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\r¨\u0006\u0019"}, d2 = {"Lokhttp3/Challenge;", "", "scheme", "", "realm", "(Ljava/lang/String;Ljava/lang/String;)V", "authParams", "", "(Ljava/lang/String;Ljava/util/Map;)V", "()Ljava/util/Map;", "charset", "Ljava/nio/charset/Charset;", "()Ljava/nio/charset/Charset;", "()Ljava/lang/String;", "-deprecated_authParams", "-deprecated_charset", "equals", "", "other", "hashCode", "", "-deprecated_realm", "-deprecated_scheme", "toString", "withCharset", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Challenge {
    private final Map authParams;
    private final String scheme;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "authParams", imports = {}))
    public final Map -deprecated_authParams() {
        return this.authParams;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "charset", imports = {}))
    public final Charset -deprecated_charset() {
        return this.charset();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "realm", imports = {}))
    public final String -deprecated_realm() {
        return this.realm();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "scheme", imports = {}))
    public final String -deprecated_scheme() {
        return this.scheme;
    }

    public Challenge(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "scheme");
        Intrinsics.checkNotNullParameter(s1, "realm");
        Map map0 = Collections.singletonMap("realm", s1);
        Intrinsics.checkNotNullExpressionValue(map0, "singletonMap(\"realm\", realm)");
        this(s, map0);
    }

    public Challenge(String s, Map map0) {
        Intrinsics.checkNotNullParameter(s, "scheme");
        String s3;
        Intrinsics.checkNotNullParameter(map0, "authParams");
        super();
        this.scheme = s;
        Map map1 = new LinkedHashMap();
        for(Object object0: map0.entrySet()) {
            String s1 = (String)((Map.Entry)object0).getKey();
            String s2 = (String)((Map.Entry)object0).getValue();
            if(s1 == null) {
                s3 = null;
            }
            else {
                Locale locale0 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(locale0, "US");
                s3 = s1.toLowerCase(locale0);
                Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).toLowerCase(locale)");
            }
            map1.put(s3, s2);
        }
        Map map2 = Collections.unmodifiableMap(map1);
        Intrinsics.checkNotNullExpressionValue(map2, "unmodifiableMap<String?, String>(newAuthParams)");
        this.authParams = map2;
    }

    public final Map authParams() {
        return this.authParams;
    }

    public final Charset charset() {
        String s = (String)this.authParams.get("charset");
        if(s != null) {
            try {
                Charset charset0 = Charset.forName(s);
                Intrinsics.checkNotNullExpressionValue(charset0, "forName(charset)");
                return charset0;
            }
            catch(Exception unused_ex) {
            }
        }
        return Charsets.ISO_8859_1;
    }

    @Override
    public boolean equals(Object object0) {
        return _ChallengeCommonKt.commonEquals(this, object0);
    }

    @Override
    public int hashCode() {
        return _ChallengeCommonKt.commonHashCode(this);
    }

    public final String realm() {
        return (String)this.authParams.get("realm");
    }

    public final String scheme() {
        return this.scheme;
    }

    @Override
    public String toString() {
        return _ChallengeCommonKt.commonToString(this);
    }

    public final Challenge withCharset(Charset charset0) {
        Intrinsics.checkNotNullParameter(charset0, "charset");
        Map map0 = MapsKt.toMutableMap(this.authParams);
        String s = charset0.name();
        Intrinsics.checkNotNullExpressionValue(s, "charset.name()");
        map0.put("charset", s);
        return new Challenge(this.scheme, map0);
    }
}

