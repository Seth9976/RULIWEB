package androidx.core.os;

import android.os.BadParcelableException;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.SparseArray;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ParcelCompat {
    static class Api29Impl {
        static List readParcelableList(Parcel parcel0, List list0, ClassLoader classLoader0) {
            return parcel0.readParcelableList(list0, classLoader0);
        }

        static void writeBoolean(Parcel parcel0, boolean z) {
            parcel0.writeBoolean(z);
        }
    }

    static class Api30Impl {
        static Parcelable.Creator readParcelableCreator(Parcel parcel0, ClassLoader classLoader0) {
            return parcel0.readParcelableCreator(classLoader0);
        }
    }

    static class Api33Impl {
        static Object[] readArray(Parcel parcel0, ClassLoader classLoader0, Class class0) {
            return parcel0.readArray(classLoader0, class0);
        }

        static ArrayList readArrayList(Parcel parcel0, ClassLoader classLoader0, Class class0) {
            return parcel0.readArrayList(classLoader0, class0);
        }

        static HashMap readHashMap(Parcel parcel0, ClassLoader classLoader0, Class class0, Class class1) {
            return parcel0.readHashMap(classLoader0, class0, class1);
        }

        static void readList(Parcel parcel0, List list0, ClassLoader classLoader0, Class class0) {
            parcel0.readList(list0, classLoader0, class0);
        }

        static void readMap(Parcel parcel0, Map map0, ClassLoader classLoader0, Class class0, Class class1) {
            parcel0.readMap(map0, classLoader0, class0, class1);
        }

        static Parcelable readParcelable(Parcel parcel0, ClassLoader classLoader0, Class class0) {
            return (Parcelable)parcel0.readParcelable(classLoader0, class0);
        }

        static Object[] readParcelableArray(Parcel parcel0, ClassLoader classLoader0, Class class0) {
            return parcel0.readParcelableArray(classLoader0, class0);
        }

        static Parcelable.Creator readParcelableCreator(Parcel parcel0, ClassLoader classLoader0, Class class0) {
            return parcel0.readParcelableCreator(classLoader0, class0);
        }

        static List readParcelableList(Parcel parcel0, List list0, ClassLoader classLoader0, Class class0) {
            return parcel0.readParcelableList(list0, classLoader0, class0);
        }

        static Serializable readSerializable(Parcel parcel0, ClassLoader classLoader0, Class class0) {
            return (Serializable)parcel0.readSerializable(classLoader0, class0);
        }

        static SparseArray readSparseArray(Parcel parcel0, ClassLoader classLoader0, Class class0) {
            return parcel0.readSparseArray(classLoader0, class0);
        }
    }

    public static Object[] readArray(Parcel parcel0, ClassLoader classLoader0, Class class0) {
        return Build.VERSION.SDK_INT < 34 ? parcel0.readArray(classLoader0) : Api33Impl.readArray(parcel0, classLoader0, class0);
    }

    public static ArrayList readArrayList(Parcel parcel0, ClassLoader classLoader0, Class class0) {
        return Build.VERSION.SDK_INT < 34 ? parcel0.readArrayList(classLoader0) : Api33Impl.readArrayList(parcel0, classLoader0, class0);
    }

    public static boolean readBoolean(Parcel parcel0) {
        return parcel0.readInt() != 0;
    }

    public static HashMap readHashMap(Parcel parcel0, ClassLoader classLoader0, Class class0, Class class1) {
        return Build.VERSION.SDK_INT < 34 ? parcel0.readHashMap(classLoader0) : Api33Impl.readHashMap(parcel0, classLoader0, class0, class1);
    }

    public static void readList(Parcel parcel0, List list0, ClassLoader classLoader0, Class class0) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api33Impl.readList(parcel0, list0, classLoader0, class0);
            return;
        }
        parcel0.readList(list0, classLoader0);
    }

    public static void readMap(Parcel parcel0, Map map0, ClassLoader classLoader0, Class class0, Class class1) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api33Impl.readMap(parcel0, map0, classLoader0, class0, class1);
            return;
        }
        parcel0.readMap(map0, classLoader0);
    }

    public static Parcelable readParcelable(Parcel parcel0, ClassLoader classLoader0, Class class0) {
        if(Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readParcelable(parcel0, classLoader0, class0);
        }
        Parcelable parcelable0 = parcel0.readParcelable(classLoader0);
        if(parcelable0 != null && !class0.isInstance(parcelable0)) {
            throw new BadParcelableException("Parcelable " + parcelable0.getClass() + " is not a subclass of required class " + class0.getName() + " provided in the parameter");
        }
        return parcelable0;
    }

    @Deprecated
    public static Object[] readParcelableArray(Parcel parcel0, ClassLoader classLoader0, Class class0) {
        if(Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readParcelableArray(parcel0, classLoader0, class0);
        }
        Parcelable[] arr_parcelable = parcel0.readParcelableArray(classLoader0);
        if(class0.isAssignableFrom(Parcelable.class)) {
            return arr_parcelable;
        }
        Object[] arr_object = (Object[])Array.newInstance(class0, arr_parcelable.length);
        for(int v = 0; v < arr_parcelable.length; ++v) {
            try {
                arr_object[v] = class0.cast(arr_parcelable[v]);
            }
            catch(ClassCastException unused_ex) {
                throw new BadParcelableException("Parcelable at index " + v + " is not a subclass of required class " + class0.getName() + " provided in the parameter");
            }
        }
        return arr_object;
    }

    public static Parcelable[] readParcelableArrayTyped(Parcel parcel0, ClassLoader classLoader0, Class class0) {
        return Build.VERSION.SDK_INT < 34 ? parcel0.readParcelableArray(classLoader0) : ((Parcelable[])Api33Impl.readParcelableArray(parcel0, classLoader0, class0));
    }

    public static Parcelable.Creator readParcelableCreator(Parcel parcel0, ClassLoader classLoader0, Class class0) {
        return Build.VERSION.SDK_INT < 34 ? Api30Impl.readParcelableCreator(parcel0, classLoader0) : Api33Impl.readParcelableCreator(parcel0, classLoader0, class0);
    }

    public static List readParcelableList(Parcel parcel0, List list0, ClassLoader classLoader0, Class class0) {
        return Build.VERSION.SDK_INT < 34 ? Api29Impl.readParcelableList(parcel0, list0, classLoader0) : Api33Impl.readParcelableList(parcel0, list0, classLoader0, class0);
    }

    public static Serializable readSerializable(Parcel parcel0, ClassLoader classLoader0, Class class0) {
        return Build.VERSION.SDK_INT < 33 ? parcel0.readSerializable() : Api33Impl.readSerializable(parcel0, classLoader0, class0);
    }

    public static SparseArray readSparseArray(Parcel parcel0, ClassLoader classLoader0, Class class0) {
        return Build.VERSION.SDK_INT < 34 ? parcel0.readSparseArray(classLoader0) : Api33Impl.readSparseArray(parcel0, classLoader0, class0);
    }

    public static void writeBoolean(Parcel parcel0, boolean z) {
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.writeBoolean(parcel0, z);
            return;
        }
        parcel0.writeInt(((int)z));
    }
}

