package androidx.activity.result;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000F\b\u0010\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\b\u0010\u0007\u001A\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\b\u0010\u000E\u001A\u00020\u0006H\u0016J\b\u0010\u000F\u001A\u00020\u0010H\u0016J\u0018\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u00032\u0006\u0010\u0014\u001A\u00020\u0006H\u0016R\u0013\u0010\u0007\u001A\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Landroidx/activity/result/ActivityResult;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "resultCode", "", "data", "Landroid/content/Intent;", "(ILandroid/content/Intent;)V", "getData", "()Landroid/content/Intent;", "getResultCode", "()I", "describeContents", "toString", "", "writeToParcel", "", "dest", "flags", "Companion", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ActivityResult implements Parcelable {
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0007R\u001C\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0002¨\u0006\u000B"}, d2 = {"Landroidx/activity/result/ActivityResult$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Landroidx/activity/result/ActivityResult;", "getCREATOR$annotations", "resultCodeToString", "", "resultCode", "", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public static void getCREATOR$annotations() {
        }

        @JvmStatic
        public final String resultCodeToString(int v) {
            switch(v) {
                case -1: {
                    return "RESULT_OK";
                }
                case 0: {
                    return "RESULT_CANCELED";
                }
                default: {
                    return String.valueOf(v);
                }
            }
        }
    }

    public static final Parcelable.Creator CREATOR;
    public static final Companion Companion;
    private final Intent data;
    private final int resultCode;

    static {
        ActivityResult.Companion = new Companion(null);
        ActivityResult.CREATOR = new ActivityResult.Companion.CREATOR.1();
    }

    public ActivityResult(int v, Intent intent0) {
        this.resultCode = v;
        this.data = intent0;
    }

    public ActivityResult(Parcel parcel0) {
        Intrinsics.checkNotNullParameter(parcel0, "parcel");
        this(parcel0.readInt(), (parcel0.readInt() == 0 ? null : ((Intent)Intent.CREATOR.createFromParcel(parcel0))));
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final Intent getData() {
        return this.data;
    }

    public final int getResultCode() {
        return this.resultCode;
    }

    @JvmStatic
    public static final String resultCodeToString(int v) {
        return ActivityResult.Companion.resultCodeToString(v);
    }

    @Override
    public String toString() {
        return "ActivityResult{resultCode=" + ActivityResult.Companion.resultCodeToString(this.resultCode) + ", data=" + this.data + '}';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "dest");
        parcel0.writeInt(this.resultCode);
        parcel0.writeInt((this.data == null ? 0 : 1));
        Intent intent0 = this.data;
        if(intent0 != null) {
            intent0.writeToParcel(parcel0, v);
        }
    }
}

