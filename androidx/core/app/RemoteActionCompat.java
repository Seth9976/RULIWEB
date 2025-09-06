package androidx.core.app;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.graphics.drawable.Icon;
import android.os.Build.VERSION;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Preconditions;
import androidx.versionedparcelable.VersionedParcelable;

public final class RemoteActionCompat implements VersionedParcelable {
    static class Api26Impl {
        static RemoteAction createRemoteAction(Icon icon0, CharSequence charSequence0, CharSequence charSequence1, PendingIntent pendingIntent0) {
            return new RemoteAction(icon0, charSequence0, charSequence1, pendingIntent0);
        }

        static PendingIntent getActionIntent(RemoteAction remoteAction0) {
            return remoteAction0.getActionIntent();
        }

        static CharSequence getContentDescription(RemoteAction remoteAction0) {
            return remoteAction0.getContentDescription();
        }

        static Icon getIcon(RemoteAction remoteAction0) {
            return remoteAction0.getIcon();
        }

        static CharSequence getTitle(RemoteAction remoteAction0) {
            return remoteAction0.getTitle();
        }

        static boolean isEnabled(RemoteAction remoteAction0) {
            return remoteAction0.isEnabled();
        }

        static void setEnabled(RemoteAction remoteAction0, boolean z) {
            remoteAction0.setEnabled(z);
        }
    }

    static class Api28Impl {
        static void setShouldShowIcon(RemoteAction remoteAction0, boolean z) {
            remoteAction0.setShouldShowIcon(z);
        }

        static boolean shouldShowIcon(RemoteAction remoteAction0) {
            return remoteAction0.shouldShowIcon();
        }
    }

    public PendingIntent mActionIntent;
    public CharSequence mContentDescription;
    public boolean mEnabled;
    public IconCompat mIcon;
    public boolean mShouldShowIcon;
    public CharSequence mTitle;

    public RemoteActionCompat() {
    }

    public RemoteActionCompat(RemoteActionCompat remoteActionCompat0) {
        Preconditions.checkNotNull(remoteActionCompat0);
        this.mIcon = remoteActionCompat0.mIcon;
        this.mTitle = remoteActionCompat0.mTitle;
        this.mContentDescription = remoteActionCompat0.mContentDescription;
        this.mActionIntent = remoteActionCompat0.mActionIntent;
        this.mEnabled = remoteActionCompat0.mEnabled;
        this.mShouldShowIcon = remoteActionCompat0.mShouldShowIcon;
    }

    public RemoteActionCompat(IconCompat iconCompat0, CharSequence charSequence0, CharSequence charSequence1, PendingIntent pendingIntent0) {
        this.mIcon = (IconCompat)Preconditions.checkNotNull(iconCompat0);
        this.mTitle = (CharSequence)Preconditions.checkNotNull(charSequence0);
        this.mContentDescription = (CharSequence)Preconditions.checkNotNull(charSequence1);
        this.mActionIntent = (PendingIntent)Preconditions.checkNotNull(pendingIntent0);
        this.mEnabled = true;
        this.mShouldShowIcon = true;
    }

    public static RemoteActionCompat createFromRemoteAction(RemoteAction remoteAction0) {
        Preconditions.checkNotNull(remoteAction0);
        RemoteActionCompat remoteActionCompat0 = new RemoteActionCompat(IconCompat.createFromIcon(Api26Impl.getIcon(remoteAction0)), Api26Impl.getTitle(remoteAction0), Api26Impl.getContentDescription(remoteAction0), Api26Impl.getActionIntent(remoteAction0));
        remoteActionCompat0.setEnabled(Api26Impl.isEnabled(remoteAction0));
        if(Build.VERSION.SDK_INT >= 28) {
            remoteActionCompat0.setShouldShowIcon(Api28Impl.shouldShowIcon(remoteAction0));
        }
        return remoteActionCompat0;
    }

    public PendingIntent getActionIntent() {
        return this.mActionIntent;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    public IconCompat getIcon() {
        return this.mIcon;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }

    public void setShouldShowIcon(boolean z) {
        this.mShouldShowIcon = z;
    }

    public boolean shouldShowIcon() {
        return this.mShouldShowIcon;
    }

    public RemoteAction toRemoteAction() {
        RemoteAction remoteAction0 = Api26Impl.createRemoteAction(this.mIcon.toIcon(), this.mTitle, this.mContentDescription, this.mActionIntent);
        Api26Impl.setEnabled(remoteAction0, this.isEnabled());
        if(Build.VERSION.SDK_INT >= 28) {
            Api28Impl.setShouldShowIcon(remoteAction0, this.shouldShowIcon());
        }
        return remoteAction0;
    }
}

