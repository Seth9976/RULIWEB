package androidx.activity.result;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000B\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001A2\u00020\u0001:\u0002\u0019\u001AB\u000F\b\u0010\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B/\b\u0000\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001A\u00020\n\u0012\b\b\u0002\u0010\u000B\u001A\u00020\n¢\u0006\u0002\u0010\fJ\b\u0010\u0014\u001A\u00020\nH\u0016J\u0018\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u00032\u0006\u0010\u0018\u001A\u00020\nH\u0016R\u0013\u0010\u0007\u001A\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u0011\u0010\t\u001A\u00020\n¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\u000B\u001A\u00020\n¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u0013¨\u0006\u001B"}, d2 = {"Landroidx/activity/result/IntentSenderRequest;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "intentSender", "Landroid/content/IntentSender;", "fillInIntent", "Landroid/content/Intent;", "flagsMask", "", "flagsValues", "(Landroid/content/IntentSender;Landroid/content/Intent;II)V", "getFillInIntent", "()Landroid/content/Intent;", "getFlagsMask", "()I", "getFlagsValues", "getIntentSender", "()Landroid/content/IntentSender;", "describeContents", "writeToParcel", "", "dest", "flags", "Builder", "Companion", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class IntentSenderRequest implements Parcelable {
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0013B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\r\u001A\u00020\u000EJ\u0010\u0010\u000F\u001A\u00020\u00002\b\u0010\b\u001A\u0004\u0018\u00010\tJ\u0016\u0010\u0010\u001A\u00020\u00002\u0006\u0010\u0011\u001A\u00020\u000B2\u0006\u0010\u0012\u001A\u00020\u000BR\u0010\u0010\b\u001A\u0004\u0018\u00010\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u000BX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/activity/result/IntentSenderRequest$Builder;", "", "pendingIntent", "Landroid/app/PendingIntent;", "(Landroid/app/PendingIntent;)V", "intentSender", "Landroid/content/IntentSender;", "(Landroid/content/IntentSender;)V", "fillInIntent", "Landroid/content/Intent;", "flagsMask", "", "flagsValues", "build", "Landroidx/activity/result/IntentSenderRequest;", "setFillInIntent", "setFlags", "values", "mask", "Flag", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Builder {
        @Retention(RetentionPolicy.SOURCE)
        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\b\u0083\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Landroidx/activity/result/IntentSenderRequest$Builder$Flag;", "", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
        @interface Flag {
        }

        private Intent fillInIntent;
        private int flagsMask;
        private int flagsValues;
        private final IntentSender intentSender;

        public Builder(PendingIntent pendingIntent0) {
            Intrinsics.checkNotNullParameter(pendingIntent0, "pendingIntent");
            IntentSender intentSender0 = pendingIntent0.getIntentSender();
            Intrinsics.checkNotNullExpressionValue(intentSender0, "pendingIntent.intentSender");
            this(intentSender0);
        }

        public Builder(IntentSender intentSender0) {
            Intrinsics.checkNotNullParameter(intentSender0, "intentSender");
            super();
            this.intentSender = intentSender0;
        }

        public final IntentSenderRequest build() {
            return new IntentSenderRequest(this.intentSender, this.fillInIntent, this.flagsMask, this.flagsValues);
        }

        public final Builder setFillInIntent(Intent intent0) {
            this.fillInIntent = intent0;
            return this;
        }

        public final Builder setFlags(int v, int v1) {
            this.flagsValues = v;
            this.flagsMask = v1;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001C\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0002¨\u0006\u0007"}, d2 = {"Landroidx/activity/result/IntentSenderRequest$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Landroidx/activity/result/IntentSenderRequest;", "getCREATOR$annotations", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public static void getCREATOR$annotations() {
        }
    }

    public static final Parcelable.Creator CREATOR;
    public static final Companion Companion;
    private final Intent fillInIntent;
    private final int flagsMask;
    private final int flagsValues;
    private final IntentSender intentSender;

    static {
        IntentSenderRequest.Companion = new Companion(null);
        IntentSenderRequest.CREATOR = new IntentSenderRequest.Companion.CREATOR.1();
    }

    public IntentSenderRequest(IntentSender intentSender0, Intent intent0, int v, int v1) {
        Intrinsics.checkNotNullParameter(intentSender0, "intentSender");
        super();
        this.intentSender = intentSender0;
        this.fillInIntent = intent0;
        this.flagsMask = v;
        this.flagsValues = v1;
    }

    public IntentSenderRequest(IntentSender intentSender0, Intent intent0, int v, int v1, int v2, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v2 & 2) != 0) {
            intent0 = null;
        }
        if((v2 & 4) != 0) {
            v = 0;
        }
        if((v2 & 8) != 0) {
            v1 = 0;
        }
        this(intentSender0, intent0, v, v1);
    }

    public IntentSenderRequest(Parcel parcel0) {
        Intrinsics.checkNotNullParameter(parcel0, "parcel");
        Parcelable parcelable0 = parcel0.readParcelable(IntentSender.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable0);
        this(((IntentSender)parcelable0), ((Intent)parcel0.readParcelable(Intent.class.getClassLoader())), parcel0.readInt(), parcel0.readInt());
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final Intent getFillInIntent() {
        return this.fillInIntent;
    }

    public final int getFlagsMask() {
        return this.flagsMask;
    }

    public final int getFlagsValues() {
        return this.flagsValues;
    }

    public final IntentSender getIntentSender() {
        return this.intentSender;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "dest");
        parcel0.writeParcelable(this.intentSender, v);
        parcel0.writeParcelable(this.fillInIntent, v);
        parcel0.writeInt(this.flagsMask);
        parcel0.writeInt(this.flagsValues);
    }
}

