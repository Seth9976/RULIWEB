package androidx.core.os;

import android.os.Build.VERSION;
import android.os.PersistableBundle;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J$\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00042\b\u0010\n\u001A\u0004\u0018\u00010\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\u0001H\u0007¨\u0006\r"}, d2 = {"Landroidx/core/os/PersistableBundleApi21ImplKt;", "", "()V", "createPersistableBundle", "Landroid/os/PersistableBundle;", "capacity", "", "putValue", "", "persistableBundle", "key", "", "value", "core-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class PersistableBundleApi21ImplKt {
    public static final PersistableBundleApi21ImplKt INSTANCE;

    static {
        PersistableBundleApi21ImplKt.INSTANCE = new PersistableBundleApi21ImplKt();
    }

    @JvmStatic
    public static final PersistableBundle createPersistableBundle(int v) {
        return new PersistableBundle(v);
    }

    @JvmStatic
    public static final void putValue(PersistableBundle persistableBundle0, String s, Object object0) {
        if(object0 == null) {
            persistableBundle0.putString(s, null);
            return;
        }
        if(object0 instanceof Boolean) {
            if(Build.VERSION.SDK_INT < 22) {
                throw new IllegalArgumentException("Unsupported value type boolean for key \"" + s + "\" (requires API level 22+)");
            }
            PersistableBundleApi22ImplKt.putBoolean(persistableBundle0, s, ((Boolean)object0).booleanValue());
            return;
        }
        if(object0 instanceof Double) {
            persistableBundle0.putDouble(s, ((Number)object0).doubleValue());
            return;
        }
        if(object0 instanceof Integer) {
            persistableBundle0.putInt(s, ((Number)object0).intValue());
            return;
        }
        if(object0 instanceof Long) {
            persistableBundle0.putLong(s, ((Number)object0).longValue());
            return;
        }
        if(object0 instanceof String) {
            persistableBundle0.putString(s, ((String)object0));
            return;
        }
        if(object0 instanceof PersistableBundle) {
            persistableBundle0.putPersistableBundle(s, ((PersistableBundle)object0));
            return;
        }
        if(object0 instanceof boolean[]) {
            if(Build.VERSION.SDK_INT < 22) {
                throw new IllegalArgumentException("Unsupported value type boolean[] for key \"" + s + "\" (requires API level 22+)");
            }
            PersistableBundleApi22ImplKt.putBooleanArray(persistableBundle0, s, ((boolean[])object0));
            return;
        }
        if(object0 instanceof double[]) {
            persistableBundle0.putDoubleArray(s, ((double[])object0));
            return;
        }
        if(object0 instanceof int[]) {
            persistableBundle0.putIntArray(s, ((int[])object0));
            return;
        }
        if(object0 instanceof long[]) {
            persistableBundle0.putLongArray(s, ((long[])object0));
            return;
        }
        if(!(object0 instanceof Object[])) {
            throw new IllegalArgumentException("Unsupported value type " + object0.getClass().getCanonicalName() + " for key \"" + s + '\"');
        }
        Class class0 = object0.getClass().getComponentType();
        Intrinsics.checkNotNull(class0);
        if(!String.class.isAssignableFrom(class0)) {
            throw new IllegalArgumentException("Unsupported value array type " + class0.getCanonicalName() + " for key \"" + s + '\"');
        }
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
        persistableBundle0.putStringArray(s, ((String[])object0));
    }
}

