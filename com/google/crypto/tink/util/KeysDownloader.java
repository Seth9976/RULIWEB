package com.google.crypto.tink.util;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport.Builder;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.Instant;

public class KeysDownloader {
    public static class Builder {
        private Executor executor;
        private HttpTransport httpTransport;
        private String url;

        public Builder() {
            this.httpTransport = KeysDownloader.DEFAULT_HTTP_TRANSPORT;
            this.executor = KeysDownloader.DEFAULT_BACKGROUND_EXECUTOR;
        }

        public KeysDownloader build() {
            if(this.url == null) {
                throw new IllegalArgumentException("must provide a url with {#setUrl}");
            }
            return new KeysDownloader(this.executor, this.httpTransport, this.url);
        }

        public Builder setExecutor(Executor executor0) {
            this.executor = executor0;
            return this;
        }

        public Builder setHttpTransport(HttpTransport httpTransport0) {
            this.httpTransport = httpTransport0;
            return this;
        }

        public Builder setUrl(String s) {
            this.url = s;
            return this;
        }
    }

    private static final Executor DEFAULT_BACKGROUND_EXECUTOR;
    private static final NetHttpTransport DEFAULT_HTTP_TRANSPORT;
    private static final Pattern MAX_AGE_PATTERN;
    private static final Charset UTF_8;
    private final Executor backgroundExecutor;
    private long cacheExpirationDurationInMillis;
    private String cachedData;
    private long cachedTimeInMillis;
    private final Object fetchDataLock;
    private final HttpTransport httpTransport;
    private final Object instanceStateLock;
    private Runnable pendingRefreshRunnable;
    private final String url;

    static {
        KeysDownloader.UTF_8 = Charset.forName("UTF-8");
        KeysDownloader.DEFAULT_HTTP_TRANSPORT = new NetHttpTransport.Builder().build();
        KeysDownloader.DEFAULT_BACKGROUND_EXECUTOR = Executors.newCachedThreadPool();
        KeysDownloader.MAX_AGE_PATTERN = Pattern.compile("\\s*max-age\\s*=\\s*(\\d+)\\s*");
    }

    public KeysDownloader(Executor executor0, HttpTransport httpTransport0, String s) {
        KeysDownloader.validate(s);
        this.backgroundExecutor = executor0;
        this.httpTransport = httpTransport0;
        this.instanceStateLock = new Object();
        this.fetchDataLock = new Object();
        this.url = s;
        this.cachedTimeInMillis = 0x8000000000000000L;
        this.cacheExpirationDurationInMillis = 0L;
    }

    public String download() throws IOException {
        synchronized(this.instanceStateLock) {
            if(this.hasNonExpiredDataCached()) {
                if(this.shouldProactivelyRefreshDataInBackground()) {
                    this.refreshInBackground();
                }
                return this.cachedData;
            }
        }
        synchronized(this.fetchDataLock) {
            synchronized(this.instanceStateLock) {
                if(this.hasNonExpiredDataCached()) {
                    return this.cachedData;
                }
            }
            return this.fetchAndCacheData();
        }
    }

    private String fetchAndCacheData() throws IOException {
        String s;
        long v = this.getCurrentTimeInMillis();
        HttpResponse httpResponse0 = this.httpTransport.createRequestFactory().buildGetRequest(new GenericUrl(this.url)).execute();
        if(httpResponse0.getStatusCode() == 200) {
            try(InputStream inputStream0 = httpResponse0.getContent()) {
                s = KeysDownloader.readerToString(new InputStreamReader(inputStream0, KeysDownloader.UTF_8));
            }
            synchronized(this.instanceStateLock) {
                this.cachedTimeInMillis = v;
                this.cacheExpirationDurationInMillis = this.getExpirationDurationInSeconds(httpResponse0.getHeaders()) * 1000L;
                this.cachedData = s;
                return s;
            }
        }
        throw new IOException("Unexpected status code = " + httpResponse0.getStatusCode());
    }

    long getCurrentTimeInMillis() {
        return Instant.now().getMillis();
    }

    long getExpirationDurationInSeconds(HttpHeaders httpHeaders0) {
        long v = 0L;
        if(httpHeaders0.getCacheControl() != null) {
            String[] arr_s = httpHeaders0.getCacheControl().split(",");
            for(int v1 = 0; v1 < arr_s.length; ++v1) {
                Matcher matcher0 = KeysDownloader.MAX_AGE_PATTERN.matcher(arr_s[v1]);
                if(matcher0.matches()) {
                    v = (long)Long.valueOf(matcher0.group(1));
                    break;
                }
            }
        }
        if(httpHeaders0.getAge() != null) {
            v -= (long)httpHeaders0.getAge();
        }
        return Math.max(0L, v);
    }

    public HttpTransport getHttpTransport() {
        return this.httpTransport;
    }

    public String getUrl() {
        return this.url;
    }

    private boolean hasNonExpiredDataCached() {
        long v = this.getCurrentTimeInMillis();
        return this.cachedTimeInMillis + this.cacheExpirationDurationInMillis > v && this.cachedTimeInMillis <= v;
    }

    private Runnable newRefreshRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                synchronized(KeysDownloader.this.fetchDataLock) {
                    try {
                        KeysDownloader.this.fetchAndCacheData();
                    }
                    catch(IOException unused_ex) {
                        Object object1 = KeysDownloader.this.instanceStateLock;
                        synchronized(object1) {
                            if(KeysDownloader.this.pendingRefreshRunnable == this) {
                                KeysDownloader.this.pendingRefreshRunnable = null;
                            }
                        }
                        return;
                    }
                    catch(Throwable throwable0) {
                        Object object2 = KeysDownloader.this.instanceStateLock;
                        synchronized(object2) {
                            if(KeysDownloader.this.pendingRefreshRunnable == this) {
                                KeysDownloader.this.pendingRefreshRunnable = null;
                            }
                        }
                        throw throwable0;
                    }
                }
                synchronized(KeysDownloader.this.instanceStateLock) {
                    if(KeysDownloader.this.pendingRefreshRunnable == this) {
                        KeysDownloader.this.pendingRefreshRunnable = null;
                    }
                }
            }
        };
    }

    private static String readerToString(Reader reader0) throws IOException {
        BufferedReader bufferedReader0 = new BufferedReader(reader0);
        StringBuilder stringBuilder0 = new StringBuilder();
        int v;
        while((v = bufferedReader0.read()) != -1) {
            stringBuilder0.append(((char)v));
        }
        return stringBuilder0.toString();
    }

    public void refreshInBackground() {
        Runnable runnable0 = this.newRefreshRunnable();
        synchronized(this.instanceStateLock) {
            if(this.pendingRefreshRunnable != null) {
                return;
            }
            this.pendingRefreshRunnable = runnable0;
        }
        try {
            this.backgroundExecutor.execute(runnable0);
        }
        catch(Throwable throwable0) {
            synchronized(this.instanceStateLock) {
                if(this.pendingRefreshRunnable == runnable0) {
                    this.pendingRefreshRunnable = null;
                }
            }
            throw throwable0;
        }
    }

    private boolean shouldProactivelyRefreshDataInBackground() {
        return this.cachedTimeInMillis + this.cacheExpirationDurationInMillis / 2L <= this.getCurrentTimeInMillis();
    }

    private static void validate(String s) {
        try {
            if(!new URL(s).getProtocol().toLowerCase(Locale.US).equals("https")) {
                throw new IllegalArgumentException("url must point to a HTTPS server");
            }
        }
        catch(MalformedURLException malformedURLException0) {
            throw new IllegalArgumentException(malformedURLException0);
        }
    }
}

