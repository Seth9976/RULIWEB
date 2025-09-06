package androidx.core.text;

import android.text.Spanned;
import android.text.SpannedString;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\u001A:\u0010\u0000\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\u0005\u001A\u00020\u00062\b\b\u0002\u0010\u0007\u001A\u00020\u0006H\u0086\b¢\u0006\u0002\u0010\b\u001A\r\u0010\t\u001A\u00020\u0004*\u00020\nH\u0086\b¨\u0006\u000B"}, d2 = {"getSpans", "", "T", "", "Landroid/text/Spanned;", "start", "", "end", "(Landroid/text/Spanned;II)[Ljava/lang/Object;", "toSpanned", "", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SpannedStringKt {
    public static final Object[] getSpans(Spanned spanned0, int v, int v1) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return spanned0.getSpans(v, v1, Object.class);
    }

    public static Object[] getSpans$default(Spanned spanned0, int v, int v1, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = spanned0.length();
        }
        Intrinsics.reifiedOperationMarker(4, "T");
        return spanned0.getSpans(v, v1, Object.class);
    }

    public static final Spanned toSpanned(CharSequence charSequence0) {
        return SpannedString.valueOf(charSequence0);
    }
}

