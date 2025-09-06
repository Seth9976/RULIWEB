package com.google.android.gms.common.internal;

import com.google.android.gms.common.util.IOUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public class LibraryVersion {
    private static final GmsLogger zza;
    private static final LibraryVersion zzb;
    private final ConcurrentHashMap zzc;

    static {
        LibraryVersion.zza = new GmsLogger("LibraryVersion", "");
        LibraryVersion.zzb = new LibraryVersion();
    }

    protected LibraryVersion() {
        this.zzc = new ConcurrentHashMap();
    }

    public static LibraryVersion getInstance() {
        return LibraryVersion.zzb;
    }

    @Deprecated
    public String getVersion(String s) {
        InputStream inputStream0;
        Preconditions.checkNotEmpty(s, "Please provide a valid libraryName");
        if(this.zzc.containsKey(s)) {
            return (String)this.zzc.get(s);
        }
        Properties properties0 = new Properties();
        String s1 = null;
        try {
            inputStream0 = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", s));
            goto label_10;
        }
        catch(IOException iOException0) {
            String s2 = null;
            goto label_20;
        label_10:
            if(inputStream0 == null) {
                goto label_15;
            label_18:
                s2 = s1;
                s1 = inputStream0;
                try {
                label_20:
                    LibraryVersion.zza.e("LibraryVersion", "Failed to get app version for libraryName: " + s, iOException0);
                    inputStream0 = s1;
                    s1 = s2;
                    goto label_31;
                }
                catch(Throwable throwable0) {
                }
                goto label_28;
            }
            else {
                try {
                    properties0.load(inputStream0);
                    s1 = properties0.getProperty("version", null);
                    LibraryVersion.zza.v("LibraryVersion", s + " version is " + s1);
                    goto label_31;
                label_15:
                    LibraryVersion.zza.w("LibraryVersion", "Failed to get app version for libraryName: " + s);
                }
                catch(IOException iOException0) {
                    goto label_18;
                }
                catch(Throwable throwable0) {
                    goto label_27;
                }
            }
            goto label_31;
        }
        catch(Throwable throwable0) {
            goto label_28;
        }
    label_27:
        s1 = inputStream0;
    label_28:
        if(s1 != null) {
            IOUtils.closeQuietly(((Closeable)s1));
        }
        throw throwable0;
    label_31:
        if(inputStream0 != null) {
            IOUtils.closeQuietly(inputStream0);
        }
        if(s1 == null) {
            LibraryVersion.zza.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used");
            s1 = "UNKNOWN";
        }
        this.zzc.put(s, s1);
        return s1;
    }
}

