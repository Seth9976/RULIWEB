package com.airbnb.lottie.network;

import android.content.Context;
import androidx.core.util.Pair;
import com.airbnb.lottie.utils.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import jeb.synthetic.TWR;

class NetworkCache {
    private final Context appContext;
    private final String url;

    NetworkCache(Context context0, String s) {
        this.appContext = context0.getApplicationContext();
        this.url = s;
    }

    Pair fetch() {
        FileInputStream fileInputStream0;
        File file0;
        try {
            file0 = this.getCachedFile(this.url);
            if(file0 == null) {
                return null;
            }
            fileInputStream0 = new FileInputStream(file0);
        }
        catch(FileNotFoundException unused_ex) {
            return null;
        }
        FileExtension fileExtension0 = file0.getAbsolutePath().endsWith(".zip") ? FileExtension.ZIP : FileExtension.JSON;
        Logger.debug(("Cache hit for " + this.url + " at " + file0.getAbsolutePath()));
        return new Pair(fileExtension0, fileInputStream0);
    }

    // 去混淆评级： 低(20)
    private static String filenameForUrl(String s, FileExtension fileExtension0, boolean z) {
        return "lottie_cache_" + s.replaceAll("\\W+", "") + (z ? fileExtension0.tempExtension() : fileExtension0.extension);
    }

    private File getCachedFile(String s) throws FileNotFoundException {
        File file0 = new File(this.appContext.getCacheDir(), NetworkCache.filenameForUrl(s, FileExtension.JSON, false));
        if(file0.exists()) {
            return file0;
        }
        File file1 = new File(this.appContext.getCacheDir(), NetworkCache.filenameForUrl(s, FileExtension.ZIP, false));
        return file1.exists() ? file1 : null;
    }

    void renameTempFile(FileExtension fileExtension0) {
        String s = NetworkCache.filenameForUrl(this.url, fileExtension0, true);
        File file0 = new File(this.appContext.getCacheDir(), s);
        File file1 = new File(file0.getAbsolutePath().replace(".temp", ""));
        boolean z = file0.renameTo(file1);
        Logger.debug(("Copying temp file to real file (" + file1 + ")"));
        if(!z) {
            Logger.warning(("Unable to rename cache file " + file0.getAbsolutePath() + " to " + file1.getAbsolutePath() + "."));
        }
    }

    File writeTempCacheFile(InputStream inputStream0, FileExtension fileExtension0) throws IOException {
        String s = NetworkCache.filenameForUrl(this.url, fileExtension0, true);
        File file0 = new File(this.appContext.getCacheDir(), s);
        try {
            FileOutputStream fileOutputStream0 = new FileOutputStream(file0);
            try {
                byte[] arr_b = new byte[0x400];
                while(true) {
                    inputStream0 = TWR.getResource$NT(inputStream0);
                    TWR.declareResource(inputStream0);
                    TWR.useResource$NT(inputStream0);
                    int v1 = inputStream0.read(arr_b);
                    if(v1 == -1) {
                        break;
                    }
                    TWR.useResource$NT(inputStream0);
                    fileOutputStream0.write(arr_b, 0, v1);
                }
                TWR.useResource$NT(inputStream0);
                fileOutputStream0.flush();
                TWR.useResource$NT(inputStream0);
                return file0;
            }
            finally {
                fileOutputStream0.close();
            }
        }
        catch(Throwable throwable0) {
            TWR.moot$NT();
            throw throwable0;
        }
    }
}

