package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zza implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        String s = parcel0.readString();
        try {
            return Attachment.fromString(s);
        }
        catch(UnsupportedAttachmentException attachment$UnsupportedAttachmentException0) {
            throw new RuntimeException(attachment$UnsupportedAttachmentException0);
        }
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new Attachment[v];
    }
}

