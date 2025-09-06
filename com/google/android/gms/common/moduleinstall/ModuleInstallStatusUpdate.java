package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ModuleInstallStatusUpdate extends AbstractSafeParcelable {
    @Retention(RetentionPolicy.CLASS)
    public @interface InstallState {
        public static final int STATE_CANCELED = 3;
        public static final int STATE_COMPLETED = 4;
        public static final int STATE_DOWNLOADING = 2;
        public static final int STATE_DOWNLOAD_PAUSED = 7;
        public static final int STATE_FAILED = 5;
        public static final int STATE_INSTALLING = 6;
        public static final int STATE_PENDING = 1;
        public static final int STATE_UNKNOWN;

    }

    public static class ProgressInfo {
        private final long zaa;
        private final long zab;

        ProgressInfo(long v, long v1) {
            Preconditions.checkNotZero(v1);
            this.zaa = v;
            this.zab = v1;
        }

        public long getBytesDownloaded() {
            return this.zaa;
        }

        public long getTotalBytesToDownload() {
            return this.zab;
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final int zaa;
    private final int zab;
    private final Long zac;
    private final Long zad;
    private final int zae;
    private final ProgressInfo zaf;

    static {
        ModuleInstallStatusUpdate.CREATOR = new zae();
    }

    public ModuleInstallStatusUpdate(int v, int v1, Long long0, Long long1, int v2) {
        this.zaa = v;
        this.zab = v1;
        this.zac = long0;
        this.zad = long1;
        this.zae = v2;
        this.zaf = long0 == null || long1 == null || ((long)long1) == 0L ? null : new ProgressInfo(((long)long0), ((long)long1));
    }

    public int getErrorCode() {
        return this.zae;
    }

    public int getInstallState() {
        return this.zab;
    }

    public ProgressInfo getProgressInfo() {
        return this.zaf;
    }

    public int getSessionId() {
        return this.zaa;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.getSessionId());
        SafeParcelWriter.writeInt(parcel0, 2, this.getInstallState());
        SafeParcelWriter.writeLongObject(parcel0, 3, this.zac, false);
        SafeParcelWriter.writeLongObject(parcel0, 4, this.zad, false);
        SafeParcelWriter.writeInt(parcel0, 5, this.getErrorCode());
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

