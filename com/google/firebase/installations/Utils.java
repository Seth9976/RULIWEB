package com.google.firebase.installations;

import android.text.TextUtils;
import com.google.firebase.installations.local.PersistedInstallationEntry;
import com.google.firebase.installations.time.Clock;
import com.google.firebase.installations.time.SystemClock;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public final class Utils {
    private static final Pattern API_KEY_FORMAT = null;
    private static final String APP_ID_IDENTIFICATION_SUBSTRING = ":";
    public static final long AUTH_TOKEN_EXPIRATION_BUFFER_IN_SECS;
    private final Clock clock;
    private static Utils singleton;

    static {
        Utils.AUTH_TOKEN_EXPIRATION_BUFFER_IN_SECS = TimeUnit.HOURS.toSeconds(1L);
        Utils.API_KEY_FORMAT = Pattern.compile("\\AA[\\w-]{38}\\z");
    }

    private Utils(Clock clock0) {
        this.clock = clock0;
    }

    public long currentTimeInMillis() {
        return this.clock.currentTimeMillis();
    }

    public long currentTimeInSecs() {
        return TimeUnit.MILLISECONDS.toSeconds(this.currentTimeInMillis());
    }

    public static Utils getInstance() {
        return Utils.getInstance(SystemClock.getInstance());
    }

    public static Utils getInstance(Clock clock0) {
        if(Utils.singleton == null) {
            Utils.singleton = new Utils(clock0);
        }
        return Utils.singleton;
    }

    public long getRandomDelayForSyncPrevention() [...] // 潜在的解密器

    // 去混淆评级： 低(20)
    public boolean isAuthTokenExpired(PersistedInstallationEntry persistedInstallationEntry0) {
        return TextUtils.isEmpty(persistedInstallationEntry0.getAuthToken()) ? true : persistedInstallationEntry0.getTokenCreationEpochInSecs() + persistedInstallationEntry0.getExpiresInSecs() < this.currentTimeInSecs() + Utils.AUTH_TOKEN_EXPIRATION_BUFFER_IN_SECS;
    }

    static boolean isValidApiKeyFormat(String s) {
        return Utils.API_KEY_FORMAT.matcher(s).matches();
    }

    static boolean isValidAppIdFormat(String s) {
        return s.contains(":");
    }
}

