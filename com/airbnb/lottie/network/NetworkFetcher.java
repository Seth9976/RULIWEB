package com.airbnb.lottie.network;

import android.content.Context;
import androidx.core.util.Pair;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.airbnb.lottie.utils.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipInputStream;
import jeb.synthetic.TWR;

public class NetworkFetcher {
    private final Context appContext;
    private final NetworkCache networkCache;
    private final String url;

    private NetworkFetcher(Context context0, String s) {
        Context context1 = context0.getApplicationContext();
        this.appContext = context1;
        this.url = s;
        this.networkCache = new NetworkCache(context1, s);
    }

    private LottieComposition fetchFromCache() {
        Pair pair0 = this.networkCache.fetch();
        if(pair0 == null) {
            return null;
        }
        InputStream inputStream0 = (InputStream)pair0.second;
        LottieResult lottieResult0 = ((FileExtension)pair0.first) == FileExtension.ZIP ? LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(inputStream0), this.url) : LottieCompositionFactory.fromJsonInputStreamSync(inputStream0, this.url);
        return lottieResult0.getValue() == null ? null : ((LottieComposition)lottieResult0.getValue());
    }

    private LottieResult fetchFromNetwork() {
        try {
            return this.fetchFromNetworkInternal();
        }
        catch(IOException iOException0) {
            return new LottieResult(iOException0);
        }
    }

    private LottieResult fetchFromNetworkInternal() throws IOException {
        Logger.debug(("Fetching " + this.url));
        HttpURLConnection httpURLConnection0 = (HttpURLConnection)new URL(this.url).openConnection();
        httpURLConnection0.setRequestMethod("GET");
        try {
            httpURLConnection0.connect();
            if(httpURLConnection0.getErrorStream() == null && httpURLConnection0.getResponseCode() == 200) {
                LottieResult lottieResult0 = this.getResultFromConnection(httpURLConnection0);
                Logger.debug(("Completed fetch from network. Success: " + (lottieResult0.getValue() != null)));
                return lottieResult0;
            }
            String s = this.getErrorFromConnection(httpURLConnection0);
            return new LottieResult(new IllegalArgumentException("Unable to fetch " + this.url + ". Failed with " + httpURLConnection0.getResponseCode() + "\n" + s));
        }
        catch(Exception exception0) {
            return new LottieResult(exception0);
        }
        finally {
            httpURLConnection0.disconnect();
        }
    }

    public static LottieResult fetchSync(Context context0, String s) {
        return new NetworkFetcher(context0, s).fetchSync();
    }

    public LottieResult fetchSync() {
        LottieComposition lottieComposition0 = this.fetchFromCache();
        if(lottieComposition0 != null) {
            return new LottieResult(lottieComposition0);
        }
        Logger.debug(("Animation for " + this.url + " not found in cache. Fetching from network."));
        return this.fetchFromNetwork();
    }

    private String getErrorFromConnection(HttpURLConnection httpURLConnection0) throws IOException {
        httpURLConnection0.getResponseCode();
        BufferedReader bufferedReader0 = new BufferedReader(new InputStreamReader(httpURLConnection0.getErrorStream()));
        StringBuilder stringBuilder0 = new StringBuilder();
        try {
            try {
                String s;
                while((s = bufferedReader0.readLine()) != null) {
                    stringBuilder0.append(s);
                    stringBuilder0.append('\n');
                }
            }
            catch(Exception exception0) {
                throw exception0;
            }
        }
        catch(Throwable throwable0) {
            TWR.safeClose$NT(bufferedReader0, throwable0);
            throw throwable0;
        }
        try {
            bufferedReader0.close();
        }
        catch(Exception unused_ex) {
        }
        return stringBuilder0.toString();
    }

    private LottieResult getResultFromConnection(HttpURLConnection httpURLConnection0) throws IOException {
        LottieResult lottieResult0;
        FileExtension fileExtension0;
        String s = httpURLConnection0.getContentType();
        if(s == null) {
            s = "application/json";
        }
        if(s.contains("application/zip")) {
            Logger.debug("Handling zip response.");
            fileExtension0 = FileExtension.ZIP;
            InputStream inputStream0 = httpURLConnection0.getInputStream();
            lottieResult0 = LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(new FileInputStream(this.networkCache.writeTempCacheFile(inputStream0, fileExtension0))), this.url);
        }
        else {
            Logger.debug("Received json response.");
            fileExtension0 = FileExtension.JSON;
            InputStream inputStream1 = httpURLConnection0.getInputStream();
            lottieResult0 = LottieCompositionFactory.fromJsonInputStreamSync(new FileInputStream(new File(this.networkCache.writeTempCacheFile(inputStream1, fileExtension0).getAbsolutePath())), this.url);
        }
        if(lottieResult0.getValue() != null) {
            this.networkCache.renameTempFile(fileExtension0);
        }
        return lottieResult0;
    }
}

