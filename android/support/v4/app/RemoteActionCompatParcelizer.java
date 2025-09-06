package android.support.v4.app;

import androidx.core.app.RemoteActionCompat;
import androidx.versionedparcelable.VersionedParcel;

public final class RemoteActionCompatParcelizer extends androidx.core.app.RemoteActionCompatParcelizer {
    @Override  // androidx.core.app.RemoteActionCompatParcelizer
    public static RemoteActionCompat read(VersionedParcel versionedParcel0) {
        return androidx.core.app.RemoteActionCompatParcelizer.read(versionedParcel0);
    }

    @Override  // androidx.core.app.RemoteActionCompatParcelizer
    public static void write(RemoteActionCompat remoteActionCompat0, VersionedParcel versionedParcel0) {
        androidx.core.app.RemoteActionCompatParcelizer.write(remoteActionCompat0, versionedParcel0);
    }
}

