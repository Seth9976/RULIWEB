package okio;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Deprecated(message = "changed in Okio 2.x")
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J \u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\bH\u0007¨\u0006\n"}, d2 = {"Lokio/-DeprecatedUtf8;", "", "()V", "size", "", "string", "", "beginIndex", "", "endIndex", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class -DeprecatedUtf8 {
    public static final -DeprecatedUtf8 INSTANCE;

    static {
        -DeprecatedUtf8.INSTANCE = new -DeprecatedUtf8();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.utf8Size()", imports = {"okio.utf8Size"}))
    public final long size(String s) {
        Intrinsics.checkNotNullParameter(s, "string");
        return Utf8.size$default(s, 0, 0, 3, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.utf8Size(beginIndex, endIndex)", imports = {"okio.utf8Size"}))
    public final long size(String s, int v, int v1) {
        Intrinsics.checkNotNullParameter(s, "string");
        return Utf8.size(s, v, v1);
    }
}

