package com.google.android.datatransport.cct;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.cct.internal.AndroidClientInfo;
import com.google.android.datatransport.cct.internal.BatchedLogRequest;
import com.google.android.datatransport.cct.internal.ClientInfo.ClientType;
import com.google.android.datatransport.cct.internal.ClientInfo;
import com.google.android.datatransport.cct.internal.LogEvent.Builder;
import com.google.android.datatransport.cct.internal.LogEvent;
import com.google.android.datatransport.cct.internal.LogRequest;
import com.google.android.datatransport.cct.internal.LogResponse;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo.MobileSubtype;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo.NetworkType;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo;
import com.google.android.datatransport.cct.internal.QosTier;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.retries.Retries;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

final class CctTransportBackend implements TransportBackend {
    static final class HttpRequest {
        final String apiKey;
        final BatchedLogRequest requestBody;
        final URL url;

        HttpRequest(URL uRL0, BatchedLogRequest batchedLogRequest0, String s) {
            this.url = uRL0;
            this.requestBody = batchedLogRequest0;
            this.apiKey = s;
        }

        HttpRequest withUrl(URL uRL0) {
            return new HttpRequest(uRL0, this.requestBody, this.apiKey);
        }
    }

    static final class HttpResponse {
        final int code;
        final long nextRequestMillis;
        final URL redirectUrl;

        HttpResponse(int v, URL uRL0, long v1) {
            this.code = v;
            this.redirectUrl = uRL0;
            this.nextRequestMillis = v1;
        }
    }

    private static final String ACCEPT_ENCODING_HEADER_KEY = "Accept-Encoding";
    static final String API_KEY_HEADER_KEY = "X-Goog-Api-Key";
    private static final int CONNECTION_TIME_OUT = 30000;
    private static final String CONTENT_ENCODING_HEADER_KEY = "Content-Encoding";
    private static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
    private static final String GZIP_CONTENT_ENCODING = "gzip";
    private static final int INVALID_VERSION_CODE = -1;
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String KEY_APPLICATION_BUILD = "application_build";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_DEVICE = "device";
    private static final String KEY_FINGERPRINT = "fingerprint";
    private static final String KEY_HARDWARE = "hardware";
    private static final String KEY_LOCALE = "locale";
    private static final String KEY_MANUFACTURER = "manufacturer";
    private static final String KEY_MCC_MNC = "mcc_mnc";
    static final String KEY_MOBILE_SUBTYPE = "mobile-subtype";
    private static final String KEY_MODEL = "model";
    static final String KEY_NETWORK_TYPE = "net-type";
    private static final String KEY_OS_BUILD = "os-uild";
    private static final String KEY_PRODUCT = "product";
    private static final String KEY_SDK_VERSION = "sdk-version";
    private static final String KEY_TIMEZONE_OFFSET = "tz-offset";
    private static final String LOG_TAG = "CctTransportBackend";
    private static final int READ_TIME_OUT = 130000;
    private final Context applicationContext;
    private final ConnectivityManager connectivityManager;
    private final DataEncoder dataEncoder;
    final URL endPoint;
    private final int readTimeout;
    private final Clock uptimeClock;
    private final Clock wallTimeClock;

    // 检测为 Lambda 实现
    public static HttpResponse $r8$lambda$5Jm_DhYLwiwBKncaqypAUFDEpD8(CctTransportBackend cctTransportBackend0, HttpRequest cctTransportBackend$HttpRequest0) [...]

    CctTransportBackend(Context context0, Clock clock0, Clock clock1) {
        this(context0, clock0, clock1, 130000);
    }

    CctTransportBackend(Context context0, Clock clock0, Clock clock1, int v) {
        this.dataEncoder = BatchedLogRequest.createDataEncoder();
        this.applicationContext = context0;
        this.connectivityManager = (ConnectivityManager)context0.getSystemService("connectivity");
        this.endPoint = CctTransportBackend.parseUrlOrThrow("https://firebaselogging.googleapis.com/v0cc/log/batch?format=json_proto3");
        this.uptimeClock = clock1;
        this.wallTimeClock = clock0;
        this.readTimeout = v;
    }

    @Override  // com.google.android.datatransport.runtime.backends.TransportBackend
    public EventInternal decorate(EventInternal eventInternal0) {
        NetworkInfo networkInfo0 = this.connectivityManager.getActiveNetworkInfo();
        return eventInternal0.toBuilder().addMetadata("sdk-version", Build.VERSION.SDK_INT).addMetadata("model", Build.MODEL).addMetadata("hardware", Build.HARDWARE).addMetadata("device", Build.DEVICE).addMetadata("product", Build.PRODUCT).addMetadata("os-uild", Build.ID).addMetadata("manufacturer", Build.MANUFACTURER).addMetadata("fingerprint", Build.FINGERPRINT).addMetadata("tz-offset", CctTransportBackend.getTzOffset()).addMetadata("net-type", CctTransportBackend.getNetTypeValue(networkInfo0)).addMetadata("mobile-subtype", CctTransportBackend.getNetSubtypeValue(networkInfo0)).addMetadata("country", Locale.getDefault().getCountry()).addMetadata("locale", Locale.getDefault().getLanguage()).addMetadata("mcc_mnc", CctTransportBackend.getTelephonyManager(this.applicationContext).getSimOperator()).addMetadata("application_build", Integer.toString(CctTransportBackend.getPackageVersionCode(this.applicationContext))).build();
    }

    private HttpResponse doSend(HttpRequest cctTransportBackend$HttpRequest0) throws IOException {
        Logging.i("CctTransportBackend", "Making request to: %s", cctTransportBackend$HttpRequest0.url);
        HttpURLConnection httpURLConnection0 = (HttpURLConnection)cctTransportBackend$HttpRequest0.url.openConnection();
        httpURLConnection0.setConnectTimeout(30000);
        httpURLConnection0.setReadTimeout(this.readTimeout);
        httpURLConnection0.setDoOutput(true);
        httpURLConnection0.setInstanceFollowRedirects(false);
        httpURLConnection0.setRequestMethod("POST");
        httpURLConnection0.setRequestProperty("User-Agent", "datatransport/3.1.8 android/");
        httpURLConnection0.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection0.setRequestProperty("Content-Type", "application/json");
        httpURLConnection0.setRequestProperty("Accept-Encoding", "gzip");
        if(cctTransportBackend$HttpRequest0.apiKey != null) {
            httpURLConnection0.setRequestProperty("X-Goog-Api-Key", cctTransportBackend$HttpRequest0.apiKey);
        }
        try(OutputStream outputStream0 = httpURLConnection0.getOutputStream(); GZIPOutputStream gZIPOutputStream0 = new GZIPOutputStream(outputStream0)) {
            BufferedWriter bufferedWriter0 = new BufferedWriter(new OutputStreamWriter(gZIPOutputStream0));
            this.dataEncoder.encode(cctTransportBackend$HttpRequest0.requestBody, bufferedWriter0);
        }
        catch(ConnectException | UnknownHostException connectException0) {
            Logging.e("CctTransportBackend", "Couldn\'t open connection, returning with 500", connectException0);
            return new HttpResponse(500, null, 0L);
        }
        catch(EncodingException | IOException encodingException0) {
            Logging.e("CctTransportBackend", "Couldn\'t encode request, returning with 400", encodingException0);
            return new HttpResponse(400, null, 0L);
        }
        int v = httpURLConnection0.getResponseCode();
        Logging.i("CctTransportBackend", "Status Code: %d", v);
        Logging.d("CctTransportBackend", "Content-Type: %s", httpURLConnection0.getHeaderField("Content-Type"));
        Logging.d("CctTransportBackend", "Content-Encoding: %s", httpURLConnection0.getHeaderField("Content-Encoding"));
        switch(v) {
            case 200: {
                try(InputStream inputStream0 = httpURLConnection0.getInputStream(); InputStream inputStream1 = CctTransportBackend.maybeUnGzip(inputStream0, httpURLConnection0.getHeaderField("Content-Encoding"))) {
                    HttpResponse cctTransportBackend$HttpResponse0 = new HttpResponse(200, null, LogResponse.fromJson(new BufferedReader(new InputStreamReader(inputStream1))).getNextRequestWaitMillis());
                    if(inputStream1 != null) {
                    }
                    return cctTransportBackend$HttpResponse0;
                }
            }
            case 301: 
            case 302: 
            case 307: {
                return new HttpResponse(v, new URL(httpURLConnection0.getHeaderField("Location")), 0L);
            }
            default: {
                return new HttpResponse(v, null, 0L);
            }
        }
    }

    private static int getNetSubtypeValue(NetworkInfo networkInfo0) {
        if(networkInfo0 == null) {
            return MobileSubtype.UNKNOWN_MOBILE_SUBTYPE.getValue();
        }
        int v = networkInfo0.getSubtype();
        if(v == -1) {
            return MobileSubtype.COMBINED.getValue();
        }
        return MobileSubtype.forNumber(v) == null ? 0 : v;
    }

    private static int getNetTypeValue(NetworkInfo networkInfo0) {
        return networkInfo0 == null ? NetworkType.NONE.getValue() : networkInfo0.getType();
    }

    private static int getPackageVersionCode(Context context0) {
        try {
            return context0.getPackageManager().getPackageInfo("com.ruliweb.www.ruliapp", 0).versionCode;
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            Logging.e("CctTransportBackend", "Unable to find version code for package", packageManager$NameNotFoundException0);
            return -1;
        }
    }

    private BatchedLogRequest getRequestBody(BackendRequest backendRequest0) {
        Builder logEvent$Builder0;
        HashMap hashMap0 = new HashMap();
        for(Object object0: backendRequest0.getEvents()) {
            EventInternal eventInternal0 = (EventInternal)object0;
            String s = eventInternal0.getTransportName();
            if(hashMap0.containsKey(s)) {
                ((List)hashMap0.get(s)).add(eventInternal0);
            }
            else {
                ArrayList arrayList0 = new ArrayList();
                arrayList0.add(eventInternal0);
                hashMap0.put(s, arrayList0);
            }
        }
        ArrayList arrayList1 = new ArrayList();
        for(Object object1: hashMap0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object1;
            EventInternal eventInternal1 = (EventInternal)((List)map$Entry0.getValue()).get(0);
            com.google.android.datatransport.cct.internal.LogRequest.Builder logRequest$Builder0 = LogRequest.builder().setQosTier(QosTier.DEFAULT).setRequestTimeMs(this.wallTimeClock.getTime()).setRequestUptimeMs(this.uptimeClock.getTime()).setClientInfo(ClientInfo.builder().setClientType(ClientType.ANDROID_FIREBASE).setAndroidClientInfo(AndroidClientInfo.builder().setSdkVersion(eventInternal1.getInteger("sdk-version")).setModel(eventInternal1.get("model")).setHardware(eventInternal1.get("hardware")).setDevice(eventInternal1.get("device")).setProduct(eventInternal1.get("product")).setOsBuild(eventInternal1.get("os-uild")).setManufacturer(eventInternal1.get("manufacturer")).setFingerprint(eventInternal1.get("fingerprint")).setCountry(eventInternal1.get("country")).setLocale(eventInternal1.get("locale")).setMccMnc(eventInternal1.get("mcc_mnc")).setApplicationBuild(eventInternal1.get("application_build")).build()).build());
            try {
                logRequest$Builder0.setSource(Integer.parseInt(((String)map$Entry0.getKey())));
            }
            catch(NumberFormatException unused_ex) {
                logRequest$Builder0.setSource(((String)map$Entry0.getKey()));
            }
            ArrayList arrayList2 = new ArrayList();
            for(Object object2: ((List)map$Entry0.getValue())) {
                EventInternal eventInternal2 = (EventInternal)object2;
                EncodedPayload encodedPayload0 = eventInternal2.getEncodedPayload();
                Encoding encoding0 = encodedPayload0.getEncoding();
                if(encoding0.equals(Encoding.of("proto"))) {
                    logEvent$Builder0 = LogEvent.protoBuilder(encodedPayload0.getBytes());
                }
                else if(encoding0.equals(Encoding.of("json"))) {
                    logEvent$Builder0 = LogEvent.jsonBuilder(new String(encodedPayload0.getBytes(), Charset.forName("UTF-8")));
                }
                else {
                    goto label_40;
                }
                logEvent$Builder0.setEventTimeMs(eventInternal2.getEventMillis()).setEventUptimeMs(eventInternal2.getUptimeMillis()).setTimezoneOffsetSeconds(eventInternal2.getLong("tz-offset")).setNetworkConnectionInfo(NetworkConnectionInfo.builder().setNetworkType(NetworkType.forNumber(eventInternal2.getInteger("net-type"))).setMobileSubtype(MobileSubtype.forNumber(eventInternal2.getInteger("mobile-subtype"))).build());
                if(eventInternal2.getCode() != null) {
                    logEvent$Builder0.setEventCode(eventInternal2.getCode());
                }
                arrayList2.add(logEvent$Builder0.build());
                continue;
            label_40:
                Logging.w("CctTransportBackend", "Received event of unsupported encoding %s. Skipping...", encoding0);
            }
            logRequest$Builder0.setLogEvents(arrayList2);
            arrayList1.add(logRequest$Builder0.build());
        }
        return BatchedLogRequest.create(arrayList1);
    }

    private static TelephonyManager getTelephonyManager(Context context0) {
        return (TelephonyManager)context0.getSystemService("phone");
    }

    static long getTzOffset() {
        Calendar.getInstance();
        return (long)(TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis()) / 1000);
    }

    // 检测为 Lambda 实现
    static HttpRequest lambda$send$0(HttpRequest cctTransportBackend$HttpRequest0, HttpResponse cctTransportBackend$HttpResponse0) [...]

    private static InputStream maybeUnGzip(InputStream inputStream0, String s) throws IOException {
        return "gzip".equals(s) ? new GZIPInputStream(inputStream0) : inputStream0;
    }

    private static URL parseUrlOrThrow(String s) {
        try {
            return new URL(s);
        }
        catch(MalformedURLException malformedURLException0) {
            throw new IllegalArgumentException("Invalid url: " + s, malformedURLException0);
        }
    }

    @Override  // com.google.android.datatransport.runtime.backends.TransportBackend
    public BackendResponse send(BackendRequest backendRequest0) {
        BatchedLogRequest batchedLogRequest0 = this.getRequestBody(backendRequest0);
        URL uRL0 = this.endPoint;
        String s = null;
        if(backendRequest0.getExtras() != null) {
            try {
                CCTDestination cCTDestination0 = CCTDestination.fromByteArray(backendRequest0.getExtras());
                if(cCTDestination0.getAPIKey() != null) {
                    s = cCTDestination0.getAPIKey();
                }
                if(cCTDestination0.getEndPoint() != null) {
                    uRL0 = CctTransportBackend.parseUrlOrThrow(cCTDestination0.getEndPoint());
                }
            }
            catch(IllegalArgumentException unused_ex) {
                return BackendResponse.fatalError();
            }
        }
        try {
            HttpResponse cctTransportBackend$HttpResponse0 = (HttpResponse)Retries.retry(5, new HttpRequest(uRL0, batchedLogRequest0, s), (HttpRequest cctTransportBackend$HttpRequest0) -> this.doSend(cctTransportBackend$HttpRequest0), (HttpRequest cctTransportBackend$HttpRequest0, HttpResponse cctTransportBackend$HttpResponse0) -> {
                if(cctTransportBackend$HttpResponse0.redirectUrl != null) {
                    Logging.d("CctTransportBackend", "Following redirect to: %s", cctTransportBackend$HttpResponse0.redirectUrl);
                    return cctTransportBackend$HttpRequest0.withUrl(cctTransportBackend$HttpResponse0.redirectUrl);
                }
                return null;
            });
            if(cctTransportBackend$HttpResponse0.code == 200) {
                return BackendResponse.ok(cctTransportBackend$HttpResponse0.nextRequestMillis);
            }
            if(cctTransportBackend$HttpResponse0.code < 500) {
                switch(cctTransportBackend$HttpResponse0.code) {
                    case 400: {
                        return BackendResponse.invalidPayload();
                    }
                    case 404: {
                        break;
                    }
                    default: {
                        return BackendResponse.fatalError();
                    }
                }
            }
            return BackendResponse.transientError();
        }
        catch(IOException iOException0) {
            Logging.e("CctTransportBackend", "Could not make request to the backend", iOException0);
            return BackendResponse.transientError();
        }
    }
}

