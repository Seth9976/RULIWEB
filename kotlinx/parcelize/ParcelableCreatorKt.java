package kotlinx.parcelize;

import android.os.Parcelable.Creator;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u001B\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\u0086\b¨\u0006\u0004"}, d2 = {"parcelableCreator", "Landroid/os/Parcelable$Creator;", "T", "Landroid/os/Parcelable;", "parcelize-runtime"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class ParcelableCreatorKt {
    public static final Parcelable.Creator parcelableCreator() {
        Intrinsics.reifiedOperationMarker(4, "T");
        Parcelable.Creator parcelable$Creator0 = null;
        Object object0 = Parcelable.class.getDeclaredField("CREATOR").get(null);
        if(object0 instanceof Parcelable.Creator) {
            parcelable$Creator0 = (Parcelable.Creator)object0;
        }
        if(parcelable$Creator0 != null) {
            return parcelable$Creator0;
        }
        Intrinsics.reifiedOperationMarker(4, "T");
        throw new IllegalArgumentException("Could not access CREATOR field in class " + Reflection.getOrCreateKotlinClass(Parcelable.class).getSimpleName());
    }
}

