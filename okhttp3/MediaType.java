package okhttp3;

import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._MediaTypeCommonKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 \u001B2\u00020\u0001:\u0001\u001BB-\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0003\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u000E\u001A\u0004\u0018\u00010\u000F2\n\b\u0002\u0010\u0010\u001A\u0004\u0018\u00010\u000FH\u0007J\u0013\u0010\u0011\u001A\u00020\u00122\b\u0010\u0013\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0014\u001A\u00020\u0015H\u0016J\u0010\u0010\u0016\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u0017\u001A\u00020\u0003J\r\u0010\u0005\u001A\u00020\u0003H\u0007¢\u0006\u0002\b\u0018J\b\u0010\u0019\u001A\u00020\u0003H\u0016J\r\u0010\u0004\u001A\u00020\u0003H\u0007¢\u0006\u0002\b\u001AR\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u001C\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\r\u001A\u0004\b\u000B\u0010\fR\u0013\u0010\u0005\u001A\u00020\u00038\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\nR\u0013\u0010\u0004\u001A\u00020\u00038\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\n¨\u0006\u001C"}, d2 = {"Lokhttp3/MediaType;", "", "mediaType", "", "type", "subtype", "parameterNamesAndValues", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "getMediaType$okhttp", "()Ljava/lang/String;", "getParameterNamesAndValues$okhttp", "()[Ljava/lang/String;", "[Ljava/lang/String;", "charset", "Ljava/nio/charset/Charset;", "defaultValue", "equals", "", "other", "hashCode", "", "parameter", "name", "-deprecated_subtype", "toString", "-deprecated_type", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class MediaType {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¢\u0006\u0002\b\u0007J\u0017\u0010\b\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¢\u0006\u0002\b\tJ\u0011\u0010\n\u001A\u00020\u0004*\u00020\u0006H\u0007¢\u0006\u0002\b\u0003J\u0013\u0010\u000B\u001A\u0004\u0018\u00010\u0004*\u00020\u0006H\u0007¢\u0006\u0002\b\b¨\u0006\f"}, d2 = {"Lokhttp3/MediaType$Companion;", "", "()V", "get", "Lokhttp3/MediaType;", "mediaType", "", "-deprecated_get", "parse", "-deprecated_parse", "toMediaType", "toMediaTypeOrNull", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "mediaType.toMediaType()", imports = {"okhttp3.MediaType.Companion.toMediaType"}))
        public final MediaType -deprecated_get(String s) {
            Intrinsics.checkNotNullParameter(s, "mediaType");
            return this.get(s);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "mediaType.toMediaTypeOrNull()", imports = {"okhttp3.MediaType.Companion.toMediaTypeOrNull"}))
        public final MediaType -deprecated_parse(String s) {
            Intrinsics.checkNotNullParameter(s, "mediaType");
            return this.parse(s);
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final MediaType get(String s) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            return _MediaTypeCommonKt.commonToMediaType(s);
        }

        @JvmStatic
        public final MediaType parse(String s) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            return _MediaTypeCommonKt.commonToMediaTypeOrNull(s);
        }
    }

    public static final Companion Companion;
    private final String mediaType;
    private final String[] parameterNamesAndValues;
    private final String subtype;
    private final String type;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "subtype", imports = {}))
    public final String -deprecated_subtype() {
        return this.subtype;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "type", imports = {}))
    public final String -deprecated_type() {
        return this.type;
    }

    static {
        MediaType.Companion = new Companion(null);
    }

    public MediaType(String s, String s1, String s2, String[] arr_s) {
        Intrinsics.checkNotNullParameter(s, "mediaType");
        Intrinsics.checkNotNullParameter(s1, "type");
        Intrinsics.checkNotNullParameter(s2, "subtype");
        Intrinsics.checkNotNullParameter(arr_s, "parameterNamesAndValues");
        super();
        this.mediaType = s;
        this.type = s1;
        this.subtype = s2;
        this.parameterNamesAndValues = arr_s;
    }

    public final Charset charset() {
        return MediaType.charset$default(this, null, 1, null);
    }

    public final Charset charset(Charset charset0) {
        String s = this.parameter("charset");
        if(s != null) {
            try {
                return Charset.forName(s);
            }
            catch(IllegalArgumentException unused_ex) {
            }
        }
        return charset0;
    }

    public static Charset charset$default(MediaType mediaType0, Charset charset0, int v, Object object0) {
        if((v & 1) != 0) {
            charset0 = null;
        }
        return mediaType0.charset(charset0);
    }

    @Override
    public boolean equals(Object object0) {
        return _MediaTypeCommonKt.commonEquals(this, object0);
    }

    @JvmStatic
    public static final MediaType get(String s) {
        return MediaType.Companion.get(s);
    }

    public final String getMediaType$okhttp() {
        return this.mediaType;
    }

    public final String[] getParameterNamesAndValues$okhttp() {
        return this.parameterNamesAndValues;
    }

    @Override
    public int hashCode() {
        return _MediaTypeCommonKt.commonHashCode(this);
    }

    public final String parameter(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        return _MediaTypeCommonKt.commonParameter(this, s);
    }

    @JvmStatic
    public static final MediaType parse(String s) {
        return MediaType.Companion.parse(s);
    }

    public final String subtype() {
        return this.subtype;
    }

    @Override
    public String toString() {
        return _MediaTypeCommonKt.commonToString(this);
    }

    public final String type() {
        return this.type;
    }
}

