package androidx.core.graphics.drawable;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0087\b\u001A\r\u0010\u0003\u001A\u00020\u0001*\u00020\u0002H\u0087\b\u001A\r\u0010\u0003\u001A\u00020\u0001*\u00020\u0004H\u0087\b\u001A\r\u0010\u0003\u001A\u00020\u0001*\u00020\u0005H\u0087\b¨\u0006\u0006"}, d2 = {"toAdaptiveIcon", "Landroid/graphics/drawable/Icon;", "Landroid/graphics/Bitmap;", "toIcon", "Landroid/net/Uri;", "", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class IconKt {
    public static final Icon toAdaptiveIcon(Bitmap bitmap0) {
        return Icon.createWithAdaptiveBitmap(bitmap0);
    }

    public static final Icon toIcon(Bitmap bitmap0) {
        return Icon.createWithBitmap(bitmap0);
    }

    public static final Icon toIcon(Uri uri0) {
        return Icon.createWithContentUri(uri0);
    }

    public static final Icon toIcon(byte[] arr_b) {
        return Icon.createWithData(arr_b, 0, arr_b.length);
    }
}

