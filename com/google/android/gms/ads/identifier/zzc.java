package com.google.android.gms.ads.identifier;

import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class zzc {
    public static final void zza(String s) {
        try {
            HttpURLConnection httpURLConnection0 = (HttpURLConnection)new URL(s).openConnection();
            try {
                int v1 = httpURLConnection0.getResponseCode();
                if(v1 < 200 || v1 >= 300) {
                    Log.w("HttpUrlPinger", "Received non-success response code " + v1 + " from pinging URL: " + s);
                }
            }
            finally {
                httpURLConnection0.disconnect();
            }
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            Log.w("HttpUrlPinger", "Error while parsing ping URL: " + s + ". " + indexOutOfBoundsException0.getMessage(), indexOutOfBoundsException0);
        }
        catch(IOException | RuntimeException iOException0) {
            Log.w("HttpUrlPinger", "Error while pinging URL: " + s + ". " + iOException0.getMessage(), iOException0);
        }
    }
}

