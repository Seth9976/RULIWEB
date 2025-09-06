package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class zbb implements Runnable {
    private static final Logger zba;
    private final String zbb;
    private final StatusPendingResult zbc;

    static {
        zbb.zba = new Logger("RevokeAccessOperation", new String[0]);
    }

    public zbb(String s) {
        this.zbb = Preconditions.checkNotEmpty(s);
        this.zbc = new StatusPendingResult(null);
    }

    @Override
    public final void run() {
        Status status0;
        try {
            status0 = Status.RESULT_INTERNAL_ERROR;
            HttpURLConnection httpURLConnection0 = (HttpURLConnection)new URL("https://accounts.google.com/o/oauth2/revoke?token=" + this.zbb).openConnection();
            httpURLConnection0.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            int v = httpURLConnection0.getResponseCode();
            if(v == 200) {
                status0 = Status.RESULT_SUCCESS;
            }
            else {
                zbb.zba.e("Unable to revoke access!", new Object[0]);
            }
            zbb.zba.d("Response Code: " + v, new Object[0]);
        }
        catch(IOException iOException0) {
            zbb.zba.e("IOException when revoking access: " + iOException0.toString(), new Object[0]);
        }
        catch(Exception exception0) {
            zbb.zba.e("Exception when revoking access: " + exception0.toString(), new Object[0]);
        }
        this.zbc.setResult(status0);
    }

    public static PendingResult zba(String s) {
        if(s == null) {
            return PendingResults.immediateFailedResult(new Status(4), null);
        }
        zbb zbb0 = new zbb(s);
        new Thread(zbb0).start();
        return zbb0.zbc;
    }
}

