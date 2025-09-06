package androidx.core.text;

import android.text.TextUtils;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000E\n\u0000\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0001H\u0086\b¨\u0006\u0002"}, d2 = {"htmlEncode", "", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class StringKt {
    public static final String htmlEncode(String s) {
        return TextUtils.htmlEncode(s);
    }
}

