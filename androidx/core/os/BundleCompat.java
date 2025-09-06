package androidx.core.os;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.annotation.ReplaceWith;
import java.io.Serializable;
import java.util.ArrayList;

public final class BundleCompat {
    static class Api33Impl {
        static Object getParcelable(Bundle bundle0, String s, Class class0) {
            return bundle0.getParcelable(s, class0);
        }

        static Object[] getParcelableArray(Bundle bundle0, String s, Class class0) {
            return bundle0.getParcelableArray(s, class0);
        }

        static ArrayList getParcelableArrayList(Bundle bundle0, String s, Class class0) {
            return bundle0.getParcelableArrayList(s, class0);
        }

        static Serializable getSerializable(Bundle bundle0, String s, Class class0) {
            return bundle0.getSerializable(s, class0);
        }

        static SparseArray getSparseParcelableArray(Bundle bundle0, String s, Class class0) {
            return bundle0.getSparseParcelableArray(s, class0);
        }
    }

    @ReplaceWith(expression = "bundle.getBinder(key)")
    @Deprecated
    public static IBinder getBinder(Bundle bundle0, String s) {
        return bundle0.getBinder(s);
    }

    public static Object getParcelable(Bundle bundle0, String s, Class class0) {
        if(Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.getParcelable(bundle0, s, class0);
        }
        Parcelable parcelable0 = bundle0.getParcelable(s);
        return class0.isInstance(parcelable0) ? parcelable0 : null;
    }

    public static Parcelable[] getParcelableArray(Bundle bundle0, String s, Class class0) {
        return Build.VERSION.SDK_INT < 34 ? bundle0.getParcelableArray(s) : ((Parcelable[])Api33Impl.getParcelableArray(bundle0, s, class0));
    }

    public static ArrayList getParcelableArrayList(Bundle bundle0, String s, Class class0) {
        return Build.VERSION.SDK_INT < 34 ? bundle0.getParcelableArrayList(s) : Api33Impl.getParcelableArrayList(bundle0, s, class0);
    }

    public static Serializable getSerializable(Bundle bundle0, String s, Class class0) {
        if(Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.getSerializable(bundle0, s, class0);
        }
        Serializable serializable0 = bundle0.getSerializable(s);
        return class0.isInstance(serializable0) ? serializable0 : null;
    }

    public static SparseArray getSparseParcelableArray(Bundle bundle0, String s, Class class0) {
        return Build.VERSION.SDK_INT < 34 ? bundle0.getSparseParcelableArray(s) : Api33Impl.getSparseParcelableArray(bundle0, s, class0);
    }

    @ReplaceWith(expression = "bundle.putBinder(key, binder)")
    @Deprecated
    public static void putBinder(Bundle bundle0, String s, IBinder iBinder0) {
        bundle0.putBinder(s, iBinder0);
    }
}

