package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;

public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(VersionedParcel versionedParcel0) {
        RemoteActionCompat remoteActionCompat0 = new RemoteActionCompat();
        remoteActionCompat0.mIcon = (IconCompat)versionedParcel0.readVersionedParcelable(remoteActionCompat0.mIcon, 1);
        remoteActionCompat0.mTitle = versionedParcel0.readCharSequence(remoteActionCompat0.mTitle, 2);
        remoteActionCompat0.mContentDescription = versionedParcel0.readCharSequence(remoteActionCompat0.mContentDescription, 3);
        remoteActionCompat0.mActionIntent = (PendingIntent)versionedParcel0.readParcelable(remoteActionCompat0.mActionIntent, 4);
        remoteActionCompat0.mEnabled = versionedParcel0.readBoolean(remoteActionCompat0.mEnabled, 5);
        remoteActionCompat0.mShouldShowIcon = versionedParcel0.readBoolean(remoteActionCompat0.mShouldShowIcon, 6);
        return remoteActionCompat0;
    }

    public static void write(RemoteActionCompat remoteActionCompat0, VersionedParcel versionedParcel0) {
        versionedParcel0.setSerializationFlags(false, false);
        versionedParcel0.writeVersionedParcelable(remoteActionCompat0.mIcon, 1);
        versionedParcel0.writeCharSequence(remoteActionCompat0.mTitle, 2);
        versionedParcel0.writeCharSequence(remoteActionCompat0.mContentDescription, 3);
        versionedParcel0.writeParcelable(remoteActionCompat0.mActionIntent, 4);
        versionedParcel0.writeBoolean(remoteActionCompat0.mEnabled, 5);
        versionedParcel0.writeBoolean(remoteActionCompat0.mShouldShowIcon, 6);
    }
}

