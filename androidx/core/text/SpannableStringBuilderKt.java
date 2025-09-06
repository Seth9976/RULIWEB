package androidx.core.text;

import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\u001A\"\u0010\u0000\u001A\u00020\u00012\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00A2\u0006\u0002\b\u0006H\u0086\b\u001A0\u0010\u0007\u001A\u00020\u0004*\u00020\u00042\b\b\u0001\u0010\b\u001A\u00020\t2\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00A2\u0006\u0002\b\u0006H\u0086\b\u001A&\u0010\n\u001A\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00A2\u0006\u0002\b\u0006H\u0086\b\u001A0\u0010\b\u001A\u00020\u0004*\u00020\u00042\b\b\u0001\u0010\b\u001A\u00020\t2\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00A2\u0006\u0002\b\u0006H\u0086\b\u001A.\u0010\u000B\u001A\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001A\u00020\r2\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00A2\u0006\u0002\b\u0006H\u0086\b\u001A?\u0010\u000B\u001A\u00020\u0004*\u00020\u00042\u0012\u0010\u000E\u001A\n\u0012\u0006\b\u0001\u0012\u00020\r0\u000F\"\u00020\r2\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00A2\u0006\u0002\b\u0006H\u0086\b\u00A2\u0006\u0002\u0010\u0010\u001A&\u0010\u0011\u001A\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00A2\u0006\u0002\b\u0006H\u0086\b\u001A.\u0010\u0012\u001A\u00020\u0004*\u00020\u00042\u0006\u0010\u0013\u001A\u00020\u00142\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00A2\u0006\u0002\b\u0006H\u0086\b\u001A&\u0010\u0015\u001A\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00A2\u0006\u0002\b\u0006H\u0086\b\u001A&\u0010\u0016\u001A\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00A2\u0006\u0002\b\u0006H\u0086\b\u001A&\u0010\u0017\u001A\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00A2\u0006\u0002\b\u0006H\u0086\b\u001A&\u0010\u0018\u001A\u00020\u0004*\u00020\u00042\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00A2\u0006\u0002\b\u0006H\u0086\b\u00A8\u0006\u0019"}, d2 = {"buildSpannedString", "Landroid/text/SpannedString;", "builderAction", "Lkotlin/Function1;", "Landroid/text/SpannableStringBuilder;", "", "Lkotlin/ExtensionFunctionType;", "backgroundColor", "color", "", "bold", "inSpans", "span", "", "spans", "", "(Landroid/text/SpannableStringBuilder;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Landroid/text/SpannableStringBuilder;", "italic", "scale", "proportion", "", "strikeThrough", "subscript", "superscript", "underline", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SpannableStringBuilderKt {
    public static final SpannableStringBuilder backgroundColor(SpannableStringBuilder spannableStringBuilder0, int v, Function1 function10) {
        BackgroundColorSpan backgroundColorSpan0 = new BackgroundColorSpan(v);
        int v1 = spannableStringBuilder0.length();
        function10.invoke(spannableStringBuilder0);
        spannableStringBuilder0.setSpan(backgroundColorSpan0, v1, spannableStringBuilder0.length(), 17);
        return spannableStringBuilder0;
    }

    public static final SpannableStringBuilder bold(SpannableStringBuilder spannableStringBuilder0, Function1 function10) {
        StyleSpan styleSpan0 = new StyleSpan(1);
        int v = spannableStringBuilder0.length();
        function10.invoke(spannableStringBuilder0);
        spannableStringBuilder0.setSpan(styleSpan0, v, spannableStringBuilder0.length(), 17);
        return spannableStringBuilder0;
    }

    public static final SpannedString buildSpannedString(Function1 function10) {
        SpannableStringBuilder spannableStringBuilder0 = new SpannableStringBuilder();
        function10.invoke(spannableStringBuilder0);
        return new SpannedString(spannableStringBuilder0);
    }

    public static final SpannableStringBuilder color(SpannableStringBuilder spannableStringBuilder0, int v, Function1 function10) {
        ForegroundColorSpan foregroundColorSpan0 = new ForegroundColorSpan(v);
        int v1 = spannableStringBuilder0.length();
        function10.invoke(spannableStringBuilder0);
        spannableStringBuilder0.setSpan(foregroundColorSpan0, v1, spannableStringBuilder0.length(), 17);
        return spannableStringBuilder0;
    }

    public static final SpannableStringBuilder inSpans(SpannableStringBuilder spannableStringBuilder0, Object object0, Function1 function10) {
        int v = spannableStringBuilder0.length();
        function10.invoke(spannableStringBuilder0);
        spannableStringBuilder0.setSpan(object0, v, spannableStringBuilder0.length(), 17);
        return spannableStringBuilder0;
    }

    public static final SpannableStringBuilder inSpans(SpannableStringBuilder spannableStringBuilder0, Object[] arr_object, Function1 function10) {
        int v = spannableStringBuilder0.length();
        function10.invoke(spannableStringBuilder0);
        for(int v1 = 0; v1 < arr_object.length; ++v1) {
            spannableStringBuilder0.setSpan(arr_object[v1], v, spannableStringBuilder0.length(), 17);
        }
        return spannableStringBuilder0;
    }

    public static final SpannableStringBuilder italic(SpannableStringBuilder spannableStringBuilder0, Function1 function10) {
        StyleSpan styleSpan0 = new StyleSpan(2);
        int v = spannableStringBuilder0.length();
        function10.invoke(spannableStringBuilder0);
        spannableStringBuilder0.setSpan(styleSpan0, v, spannableStringBuilder0.length(), 17);
        return spannableStringBuilder0;
    }

    public static final SpannableStringBuilder scale(SpannableStringBuilder spannableStringBuilder0, float f, Function1 function10) {
        RelativeSizeSpan relativeSizeSpan0 = new RelativeSizeSpan(f);
        int v = spannableStringBuilder0.length();
        function10.invoke(spannableStringBuilder0);
        spannableStringBuilder0.setSpan(relativeSizeSpan0, v, spannableStringBuilder0.length(), 17);
        return spannableStringBuilder0;
    }

    public static final SpannableStringBuilder strikeThrough(SpannableStringBuilder spannableStringBuilder0, Function1 function10) {
        StrikethroughSpan strikethroughSpan0 = new StrikethroughSpan();
        int v = spannableStringBuilder0.length();
        function10.invoke(spannableStringBuilder0);
        spannableStringBuilder0.setSpan(strikethroughSpan0, v, spannableStringBuilder0.length(), 17);
        return spannableStringBuilder0;
    }

    public static final SpannableStringBuilder subscript(SpannableStringBuilder spannableStringBuilder0, Function1 function10) {
        SubscriptSpan subscriptSpan0 = new SubscriptSpan();
        int v = spannableStringBuilder0.length();
        function10.invoke(spannableStringBuilder0);
        spannableStringBuilder0.setSpan(subscriptSpan0, v, spannableStringBuilder0.length(), 17);
        return spannableStringBuilder0;
    }

    public static final SpannableStringBuilder superscript(SpannableStringBuilder spannableStringBuilder0, Function1 function10) {
        SuperscriptSpan superscriptSpan0 = new SuperscriptSpan();
        int v = spannableStringBuilder0.length();
        function10.invoke(spannableStringBuilder0);
        spannableStringBuilder0.setSpan(superscriptSpan0, v, spannableStringBuilder0.length(), 17);
        return spannableStringBuilder0;
    }

    public static final SpannableStringBuilder underline(SpannableStringBuilder spannableStringBuilder0, Function1 function10) {
        UnderlineSpan underlineSpan0 = new UnderlineSpan();
        int v = spannableStringBuilder0.length();
        function10.invoke(spannableStringBuilder0);
        spannableStringBuilder0.setSpan(underlineSpan0, v, spannableStringBuilder0.length(), 17);
        return spannableStringBuilder0;
    }
}

