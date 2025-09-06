package androidx.core.service.quicksettings;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.PendingIntentCompat;

public class PendingIntentActivityWrapper {
    private final Context mContext;
    private final int mFlags;
    private final Intent mIntent;
    private final boolean mIsMutable;
    private final Bundle mOptions;
    private final PendingIntent mPendingIntent;
    private final int mRequestCode;

    public PendingIntentActivityWrapper(Context context0, int v, Intent intent0, int v1, Bundle bundle0, boolean z) {
        this.mContext = context0;
        this.mRequestCode = v;
        this.mIntent = intent0;
        this.mFlags = v1;
        this.mOptions = bundle0;
        this.mIsMutable = z;
        this.mPendingIntent = this.createPendingIntent();
    }

    public PendingIntentActivityWrapper(Context context0, int v, Intent intent0, int v1, boolean z) {
        this(context0, v, intent0, v1, null, z);
    }

    private PendingIntent createPendingIntent() {
        return this.mOptions == null ? PendingIntentCompat.getActivity(this.mContext, this.mRequestCode, this.mIntent, this.mFlags, this.mIsMutable) : PendingIntentCompat.getActivity(this.mContext, this.mRequestCode, this.mIntent, this.mFlags, this.mOptions, this.mIsMutable);
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public Bundle getOptions() {
        return this.mOptions;
    }

    public PendingIntent getPendingIntent() {
        return this.mPendingIntent;
    }

    public int getRequestCode() {
        return this.mRequestCode;
    }

    public boolean isMutable() {
        return this.mIsMutable;
    }
}

