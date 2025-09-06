package androidx.core.content;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A \u0010\u0000\u001A\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004\u001AN\u0010\u0005\u001A\u00020\u0006*\u00020\u00032\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\b2\u0006\u0010\t\u001A\u00020\n2\b\b\u0003\u0010\u000B\u001A\u00020\f2\b\b\u0003\u0010\r\u001A\u00020\f2\u0017\u0010\u000E\u001A\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000F¢\u0006\u0002\b\u0011H\u0086\b\u001A8\u0010\u0005\u001A\u00020\u0006*\u00020\u00032\b\b\u0001\u0010\u0012\u001A\u00020\f2\u0006\u0010\t\u001A\u00020\n2\u0017\u0010\u000E\u001A\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000F¢\u0006\u0002\b\u0011H\u0086\b¨\u0006\u0013"}, d2 = {"getSystemService", "T", "", "Landroid/content/Context;", "(Landroid/content/Context;)Ljava/lang/Object;", "withStyledAttributes", "", "set", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "", "defStyleRes", "block", "Lkotlin/Function1;", "Landroid/content/res/TypedArray;", "Lkotlin/ExtensionFunctionType;", "resourceId", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ContextKt {
    public static final Object getSystemService(Context context0) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return ContextCompat.getSystemService(context0, Object.class);
    }

    public static final void withStyledAttributes(Context context0, int v, int[] arr_v, Function1 function10) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(v, arr_v);
        function10.invoke(typedArray0);
        typedArray0.recycle();
    }

    public static final void withStyledAttributes(Context context0, AttributeSet attributeSet0, int[] arr_v, int v, int v1, Function1 function10) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, arr_v, v, v1);
        function10.invoke(typedArray0);
        typedArray0.recycle();
    }

    public static void withStyledAttributes$default(Context context0, AttributeSet attributeSet0, int[] arr_v, int v, int v1, Function1 function10, int v2, Object object0) {
        if((v2 & 1) != 0) {
            attributeSet0 = null;
        }
        if((v2 & 4) != 0) {
            v = 0;
        }
        if((v2 & 8) != 0) {
            v1 = 0;
        }
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, arr_v, v, v1);
        function10.invoke(typedArray0);
        typedArray0.recycle();
    }
}

