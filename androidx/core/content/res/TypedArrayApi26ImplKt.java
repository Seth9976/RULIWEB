package androidx.core.content.res;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001A\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\b\u0001\u0010\u0007\u001A\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/core/content/res/TypedArrayApi26ImplKt;", "", "()V", "getFont", "Landroid/graphics/Typeface;", "typedArray", "Landroid/content/res/TypedArray;", "index", "", "core-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class TypedArrayApi26ImplKt {
    public static final TypedArrayApi26ImplKt INSTANCE;

    static {
        TypedArrayApi26ImplKt.INSTANCE = new TypedArrayApi26ImplKt();
    }

    @JvmStatic
    public static final Typeface getFont(TypedArray typedArray0, int v) {
        Typeface typeface0 = typedArray0.getFont(v);
        Intrinsics.checkNotNull(typeface0);
        return typeface0;
    }
}

