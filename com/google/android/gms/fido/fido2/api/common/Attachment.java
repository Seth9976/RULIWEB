package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;

public enum Attachment implements Parcelable {
    public static class UnsupportedAttachmentException extends Exception {
        public UnsupportedAttachmentException(String s) {
            super(String.format("Attachment %s not supported", s));
        }
    }

    PLATFORM("platform"),
    CROSS_PLATFORM("cross-platform");

    public static final Parcelable.Creator CREATOR;
    private final String zzb;

    static {
        Attachment.CREATOR = new zza();
    }

    private Attachment(String s1) {
        this.zzb = s1;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static Attachment fromString(String s) throws UnsupportedAttachmentException {
        Attachment[] arr_attachment = Attachment.values();
        for(int v = 0; v < arr_attachment.length; ++v) {
            Attachment attachment0 = arr_attachment[v];
            if(s.equals(attachment0.zzb)) {
                return attachment0;
            }
        }
        throw new UnsupportedAttachmentException(s);
    }

    @Override
    public String toString() {
        return this.zzb;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeString(this.zzb);
    }
}

