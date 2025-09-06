package com.google.android.gms.common.internal.safeparcel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.internal.common.zzag;
import java.util.ArrayList;

public final class SafeParcelableSerializer {
    public static SafeParcelable deserializeFromBytes(byte[] arr_b, Parcelable.Creator parcelable$Creator0) {
        Preconditions.checkNotNull(parcelable$Creator0);
        Parcel parcel0 = Parcel.obtain();
        parcel0.unmarshall(arr_b, 0, arr_b.length);
        parcel0.setDataPosition(0);
        SafeParcelable safeParcelable0 = (SafeParcelable)parcelable$Creator0.createFromParcel(parcel0);
        parcel0.recycle();
        return safeParcelable0;
    }

    public static SafeParcelable deserializeFromIntentExtra(Intent intent0, String s, Parcelable.Creator parcelable$Creator0) {
        byte[] arr_b = intent0.getByteArrayExtra(s);
        return arr_b == null ? null : SafeParcelableSerializer.deserializeFromBytes(arr_b, parcelable$Creator0);
    }

    public static SafeParcelable deserializeFromString(String s, Parcelable.Creator parcelable$Creator0) {
        return SafeParcelableSerializer.deserializeFromBytes(Base64Utils.decodeUrlSafe(s), parcelable$Creator0);
    }

    @Deprecated
    public static ArrayList deserializeIterableFromBundle(Bundle bundle0, String s, Parcelable.Creator parcelable$Creator0) {
        ArrayList arrayList0 = (ArrayList)bundle0.getSerializable(s);
        if(arrayList0 == null) {
            return null;
        }
        ArrayList arrayList1 = new ArrayList(arrayList0.size());
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            arrayList1.add(SafeParcelableSerializer.deserializeFromBytes(((byte[])arrayList0.get(v1)), parcelable$Creator0));
        }
        return arrayList1;
    }

    public static ArrayList deserializeIterableFromBundleSafe(Bundle bundle0, String s, Parcelable.Creator parcelable$Creator0) {
        return SafeParcelableSerializer.deserializeIterableFromBytes(bundle0.getByteArray(s), parcelable$Creator0);
    }

    public static ArrayList deserializeIterableFromBytes(byte[] arr_b, Parcelable.Creator parcelable$Creator0) {
        if(arr_b == null) {
            return null;
        }
        Parcel parcel0 = Parcel.obtain();
        parcel0.unmarshall(arr_b, 0, arr_b.length);
        parcel0.setDataPosition(0);
        try {
            ArrayList arrayList0 = new ArrayList();
            parcel0.readTypedList(arrayList0, parcelable$Creator0);
            return arrayList0;
        }
        finally {
            parcel0.recycle();
        }
    }

    @Deprecated
    public static ArrayList deserializeIterableFromIntentExtra(Intent intent0, String s, Parcelable.Creator parcelable$Creator0) {
        ArrayList arrayList0 = (ArrayList)intent0.getSerializableExtra(s);
        if(arrayList0 == null) {
            return null;
        }
        ArrayList arrayList1 = new ArrayList(arrayList0.size());
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            arrayList1.add(SafeParcelableSerializer.deserializeFromBytes(((byte[])arrayList0.get(v1)), parcelable$Creator0));
        }
        return arrayList1;
    }

    public static ArrayList deserializeIterableFromIntentExtraSafe(Intent intent0, String s, Parcelable.Creator parcelable$Creator0) {
        return SafeParcelableSerializer.deserializeIterableFromBytes(intent0.getByteArrayExtra(s), parcelable$Creator0);
    }

    @Deprecated
    public static void serializeIterableToBundle(Iterable iterable0, Bundle bundle0, String s) {
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: iterable0) {
            arrayList0.add(SafeParcelableSerializer.serializeToBytes(((SafeParcelable)object0)));
        }
        bundle0.putSerializable(s, arrayList0);
    }

    public static void serializeIterableToBundleSafe(Iterable iterable0, Bundle bundle0, String s) {
        bundle0.putByteArray(s, SafeParcelableSerializer.zza(iterable0));
    }

    @Deprecated
    public static void serializeIterableToIntentExtra(Iterable iterable0, Intent intent0, String s) {
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: iterable0) {
            arrayList0.add(SafeParcelableSerializer.serializeToBytes(((SafeParcelable)object0)));
        }
        intent0.putExtra(s, arrayList0);
    }

    public static void serializeIterableToIntentExtraSafe(Iterable iterable0, Intent intent0, String s) {
        intent0.putExtra(s, SafeParcelableSerializer.zza(iterable0));
    }

    public static byte[] serializeToBytes(SafeParcelable safeParcelable0) {
        Parcel parcel0 = Parcel.obtain();
        safeParcelable0.writeToParcel(parcel0, 0);
        byte[] arr_b = parcel0.marshall();
        parcel0.recycle();
        return arr_b;
    }

    public static void serializeToIntentExtra(SafeParcelable safeParcelable0, Intent intent0, String s) {
        intent0.putExtra(s, SafeParcelableSerializer.serializeToBytes(safeParcelable0));
    }

    public static String serializeToString(SafeParcelable safeParcelable0) {
        return Base64Utils.encodeUrlSafe(SafeParcelableSerializer.serializeToBytes(safeParcelable0));
    }

    private static byte[] zza(Iterable iterable0) {
        Parcel parcel0 = Parcel.obtain();
        try {
            parcel0.writeTypedList(zzag.zzj(iterable0));
            return parcel0.marshall();
        }
        finally {
            parcel0.recycle();
        }
    }
}

