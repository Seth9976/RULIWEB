package androidx.browser.trusted;

import android.net.Uri;

public final class TrustedWebActivityServiceConnectionPool..ExternalSyntheticLambda0 implements Runnable {
    public final TrustedWebActivityServiceConnectionPool f$0;
    public final Uri f$1;

    public TrustedWebActivityServiceConnectionPool..ExternalSyntheticLambda0(TrustedWebActivityServiceConnectionPool trustedWebActivityServiceConnectionPool0, Uri uri0) {
        this.f$0 = trustedWebActivityServiceConnectionPool0;
        this.f$1 = uri0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$connect$0$androidx-browser-trusted-TrustedWebActivityServiceConnectionPool(this.f$1);
    }
}

