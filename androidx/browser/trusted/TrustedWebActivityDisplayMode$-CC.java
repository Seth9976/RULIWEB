package androidx.browser.trusted;

import android.os.Bundle;

public final class TrustedWebActivityDisplayMode.-CC {
    public static TrustedWebActivityDisplayMode fromBundle(Bundle bundle0) {
        return bundle0.getInt("androidx.browser.trusted.displaymode.KEY_ID") != 1 ? new DefaultMode() : ImmersiveMode.fromBundle(bundle0);
    }
}

