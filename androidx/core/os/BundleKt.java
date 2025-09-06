package androidx.core.os;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0010\u0000\n\u0002\b\u0002\u001A\u0006\u0010\u0000\u001A\u00020\u0001\u001A;\u0010\u0000\u001A\u00020\u00012.\u0010\u0002\u001A\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"bundleOf", "Landroid/os/Bundle;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/Bundle;", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class BundleKt {
    public static final Bundle bundleOf() {
        return new Bundle(0);
    }

    public static final Bundle bundleOf(Pair[] arr_pair) {
        Bundle bundle0 = new Bundle(arr_pair.length);
        for(int v = 0; v < arr_pair.length; ++v) {
            Pair pair0 = arr_pair[v];
            String s = (String)pair0.component1();
            Object object0 = pair0.component2();
            if(object0 == null) {
                bundle0.putString(s, null);
            }
            else if(object0 instanceof Boolean) {
                bundle0.putBoolean(s, ((Boolean)object0).booleanValue());
            }
            else if(object0 instanceof Byte) {
                bundle0.putByte(s, ((Number)object0).byteValue());
            }
            else if(object0 instanceof Character) {
                bundle0.putChar(s, ((Character)object0).charValue());
            }
            else if(object0 instanceof Double) {
                bundle0.putDouble(s, ((Number)object0).doubleValue());
            }
            else if(object0 instanceof Float) {
                bundle0.putFloat(s, ((Number)object0).floatValue());
            }
            else if(object0 instanceof Integer) {
                bundle0.putInt(s, ((Number)object0).intValue());
            }
            else if(object0 instanceof Long) {
                bundle0.putLong(s, ((Number)object0).longValue());
            }
            else if(object0 instanceof Short) {
                bundle0.putShort(s, ((Number)object0).shortValue());
            }
            else if(object0 instanceof Bundle) {
                bundle0.putBundle(s, ((Bundle)object0));
            }
            else if(object0 instanceof CharSequence) {
                bundle0.putCharSequence(s, ((CharSequence)object0));
            }
            else if(object0 instanceof Parcelable) {
                bundle0.putParcelable(s, ((Parcelable)object0));
            }
            else if(object0 instanceof boolean[]) {
                bundle0.putBooleanArray(s, ((boolean[])object0));
            }
            else if(object0 instanceof byte[]) {
                bundle0.putByteArray(s, ((byte[])object0));
            }
            else if(object0 instanceof char[]) {
                bundle0.putCharArray(s, ((char[])object0));
            }
            else if(object0 instanceof double[]) {
                bundle0.putDoubleArray(s, ((double[])object0));
            }
            else if(object0 instanceof float[]) {
                bundle0.putFloatArray(s, ((float[])object0));
            }
            else if(object0 instanceof int[]) {
                bundle0.putIntArray(s, ((int[])object0));
            }
            else if(object0 instanceof long[]) {
                bundle0.putLongArray(s, ((long[])object0));
            }
            else if(object0 instanceof short[]) {
                bundle0.putShortArray(s, ((short[])object0));
            }
            else if(object0 instanceof Object[]) {
                Class class0 = object0.getClass().getComponentType();
                Intrinsics.checkNotNull(class0);
                if(Parcelable.class.isAssignableFrom(class0)) {
                    Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                    bundle0.putParcelableArray(s, ((Parcelable[])object0));
                }
                else if(String.class.isAssignableFrom(class0)) {
                    Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                    bundle0.putStringArray(s, ((String[])object0));
                }
                else if(CharSequence.class.isAssignableFrom(class0)) {
                    Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                    bundle0.putCharSequenceArray(s, ((CharSequence[])object0));
                }
                else {
                    if(!Serializable.class.isAssignableFrom(class0)) {
                        throw new IllegalArgumentException("Illegal value array type " + class0.getCanonicalName() + " for key \"" + s + '\"');
                    }
                    bundle0.putSerializable(s, ((Serializable)object0));
                }
            }
            else if(object0 instanceof Serializable) {
                bundle0.putSerializable(s, ((Serializable)object0));
            }
            else if(object0 instanceof IBinder) {
                bundle0.putBinder(s, ((IBinder)object0));
            }
            else if(object0 instanceof Size) {
                BundleApi21ImplKt.putSize(bundle0, s, ((Size)object0));
            }
            else {
                if(!(object0 instanceof SizeF)) {
                    throw new IllegalArgumentException("Illegal value type " + object0.getClass().getCanonicalName() + " for key \"" + s + '\"');
                }
                BundleApi21ImplKt.putSizeF(bundle0, s, ((SizeF)object0));
            }
        }
        return bundle0;
    }
}

