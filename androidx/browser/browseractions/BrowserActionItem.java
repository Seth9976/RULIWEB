package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.net.Uri;

@Deprecated
public class BrowserActionItem {
    private final PendingIntent mAction;
    private int mIconId;
    private Uri mIconUri;
    private Runnable mRunnableAction;
    private final String mTitle;

    public BrowserActionItem(String s, PendingIntent pendingIntent0) {
        this(s, pendingIntent0, 0);
    }

    public BrowserActionItem(String s, PendingIntent pendingIntent0, int v) {
        this.mTitle = s;
        this.mAction = pendingIntent0;
        this.mIconId = v;
    }

    public BrowserActionItem(String s, PendingIntent pendingIntent0, Uri uri0) {
        this.mTitle = s;
        this.mAction = pendingIntent0;
        this.mIconUri = uri0;
    }

    BrowserActionItem(String s, Runnable runnable0) {
        this.mTitle = s;
        this.mAction = null;
        this.mRunnableAction = runnable0;
    }

    public PendingIntent getAction() {
        PendingIntent pendingIntent0 = this.mAction;
        if(pendingIntent0 == null) {
            throw new IllegalStateException("Can\'t call getAction on BrowserActionItem with null action.");
        }
        return pendingIntent0;
    }

    public int getIconId() {
        return this.mIconId;
    }

    public Uri getIconUri() {
        return this.mIconUri;
    }

    Runnable getRunnableAction() {
        return this.mRunnableAction;
    }

    public String getTitle() {
        return this.mTitle;
    }
}

