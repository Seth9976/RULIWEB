package androidx.browser.trusted;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import java.util.List;

public final class TrustedWebActivityIntent {
    private final Intent mIntent;
    private final List mSharedFileUris;

    TrustedWebActivityIntent(Intent intent0, List list0) {
        this.mIntent = intent0;
        this.mSharedFileUris = list0;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    private void grantUriPermissionToProvider(Context context0) {
        for(Object object0: this.mSharedFileUris) {
            context0.grantUriPermission(this.mIntent.getPackage(), ((Uri)object0), 1);
        }
    }

    public void launchTrustedWebActivity(Context context0) {
        this.grantUriPermissionToProvider(context0);
        ContextCompat.startActivity(context0, this.mIntent, null);
    }
}

