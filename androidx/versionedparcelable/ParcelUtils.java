package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.Parcelable;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ParcelUtils {
    private static final String INNER_BUNDLE_KEY = "a";

    public static VersionedParcelable fromInputStream(InputStream inputStream0) {
        return new VersionedParcelStream(inputStream0, null).readVersionedParcelable();
    }

    public static VersionedParcelable fromParcelable(Parcelable parcelable0) {
        if(!(parcelable0 instanceof ParcelImpl)) {
            throw new IllegalArgumentException("Invalid parcel");
        }
        return ((ParcelImpl)parcelable0).getVersionedParcel();
    }

    public static VersionedParcelable getVersionedParcelable(Bundle bundle0, String s) {
        try {
            Bundle bundle1 = (Bundle)bundle0.getParcelable(s);
            if(bundle1 == null) {
                return null;
            }
            bundle1.setClassLoader(ParcelUtils.class.getClassLoader());
            return ParcelUtils.fromParcelable(bundle1.getParcelable("a"));
        }
        catch(RuntimeException unused_ex) {
            return null;
        }
    }

    public static List getVersionedParcelableList(Bundle bundle0, String s) {
        List list0 = new ArrayList();
        try {
            Bundle bundle1 = (Bundle)bundle0.getParcelable(s);
            bundle1.setClassLoader(ParcelUtils.class.getClassLoader());
            for(Object object0: bundle1.getParcelableArrayList("a")) {
                list0.add(ParcelUtils.fromParcelable(((Parcelable)object0)));
            }
            return list0;
        }
        catch(RuntimeException unused_ex) {
            return null;
        }
    }

    public static void putVersionedParcelable(Bundle bundle0, String s, VersionedParcelable versionedParcelable0) {
        if(versionedParcelable0 == null) {
            return;
        }
        Bundle bundle1 = new Bundle();
        bundle1.putParcelable("a", ParcelUtils.toParcelable(versionedParcelable0));
        bundle0.putParcelable(s, bundle1);
    }

    public static void putVersionedParcelableList(Bundle bundle0, String s, List list0) {
        Bundle bundle1 = new Bundle();
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: list0) {
            arrayList0.add(ParcelUtils.toParcelable(((VersionedParcelable)object0)));
        }
        bundle1.putParcelableArrayList("a", arrayList0);
        bundle0.putParcelable(s, bundle1);
    }

    public static void toOutputStream(VersionedParcelable versionedParcelable0, OutputStream outputStream0) {
        VersionedParcelStream versionedParcelStream0 = new VersionedParcelStream(null, outputStream0);
        versionedParcelStream0.writeVersionedParcelable(versionedParcelable0);
        versionedParcelStream0.closeField();
    }

    public static Parcelable toParcelable(VersionedParcelable versionedParcelable0) {
        return new ParcelImpl(versionedParcelable0);
    }
}

