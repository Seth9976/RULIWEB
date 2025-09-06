package androidx.core.text;

import android.text.Html.ImageGetter;
import android.text.Html.TagHandler;
import android.text.Spanned;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A/\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u00042\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\bH\u0086\b\u001A\u0017\u0010\t\u001A\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\n\u001A\u00020\u0004H\u0086\b¨\u0006\u000B"}, d2 = {"parseAsHtml", "Landroid/text/Spanned;", "", "flags", "", "imageGetter", "Landroid/text/Html$ImageGetter;", "tagHandler", "Landroid/text/Html$TagHandler;", "toHtml", "option", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class HtmlKt {
    public static final Spanned parseAsHtml(String s, int v, Html.ImageGetter html$ImageGetter0, Html.TagHandler html$TagHandler0) {
        return HtmlCompat.fromHtml(s, v, html$ImageGetter0, html$TagHandler0);
    }

    public static Spanned parseAsHtml$default(String s, int v, Html.ImageGetter html$ImageGetter0, Html.TagHandler html$TagHandler0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0;
        }
        if((v1 & 2) != 0) {
            html$ImageGetter0 = null;
        }
        if((v1 & 4) != 0) {
            html$TagHandler0 = null;
        }
        return HtmlCompat.fromHtml(s, v, html$ImageGetter0, html$TagHandler0);
    }

    public static final String toHtml(Spanned spanned0, int v) {
        return HtmlCompat.toHtml(spanned0, v);
    }

    public static String toHtml$default(Spanned spanned0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0;
        }
        return HtmlCompat.toHtml(spanned0, v);
    }
}

