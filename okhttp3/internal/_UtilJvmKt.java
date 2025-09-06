package okhttp3.internal;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.EventListener.Factory;
import okhttp3.EventListener;
import okhttp3.Headers.Builder;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.Header;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;

@Metadata(d1 = {"\u0000\u00B2\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001A\"\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000B2\u0006\u0010\u000F\u001A\u00020\u00102\b\u0010\u0011\u001A\u0004\u0018\u00010\u0012H\u0000\u001A\'\u0010\u0013\u001A\u00020\u000B2\u0006\u0010\u0013\u001A\u00020\u000B2\u0012\u0010\u0014\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00160\u0015\"\u00020\u0016\u00A2\u0006\u0002\u0010\u0017\u001A-\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u001A0\u0019\"\u0004\b\u0000\u0010\u001A2\u0012\u0010\u001B\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u001A0\u0015\"\u0002H\u001AH\u0007\u00A2\u0006\u0002\u0010\u001C\u001A3\u0010\u001D\u001A\u0004\u0018\u0001H\u001A\"\u0004\b\u0000\u0010\u001A2\u0006\u0010\u001E\u001A\u00020\u00162\f\u0010\u001F\u001A\b\u0012\u0004\u0012\u0002H\u001A0 2\u0006\u0010!\u001A\u00020\u000BH\u0000\u00A2\u0006\u0002\u0010\"\u001A\u0016\u0010#\u001A\u00020$2\u0006\u0010\u000E\u001A\u00020\u000B2\u0006\u0010%\u001A\u00020\t\u001A\"\u0010&\u001A\u00020\'2\u0006\u0010\u000E\u001A\u00020\u000B2\f\u0010(\u001A\b\u0012\u0004\u0012\u00020\'0)H\u0080\b\u00F8\u0001\u0000\u001A\f\u0010*\u001A\u00020+*\u00020,H\u0000\u001A\r\u0010-\u001A\u00020\'*\u00020\u0016H\u0080\b\u001A\r\u0010.\u001A\u00020\'*\u00020\u0016H\u0080\b\u001A\u0014\u0010/\u001A\u00020\t*\u0002002\u0006\u00101\u001A\u000200H\u0000\u001A\n\u00102\u001A\u00020\'*\u000203\u001A\f\u00102\u001A\u00020\'*\u000204H\u0000\u001A\u001C\u00105\u001A\u00020\t*\u0002062\u0006\u00107\u001A\u00020\r2\u0006\u00108\u001A\u00020\u0012H\u0000\u001A\f\u00109\u001A\u00020\u0010*\u00020:H\u0000\u001A\u0014\u0010;\u001A\u00020\t*\u0002042\u0006\u0010<\u001A\u00020=H\u0000\u001A\r\u0010>\u001A\u00020\'*\u00020\u0016H\u0080\b\u001A\r\u0010?\u001A\u00020\'*\u00020\u0016H\u0080\b\u001A\n\u0010@\u001A\u00020\u000B*\u000204\u001A\u0014\u0010A\u001A\u00020B*\u00020=2\u0006\u0010C\u001A\u00020BH\u0000\u001A\u001C\u0010D\u001A\u00020\t*\u0002062\u0006\u0010\u000F\u001A\u00020\r2\u0006\u00108\u001A\u00020\u0012H\u0000\u001A\u0012\u0010E\u001A\b\u0012\u0004\u0012\u00020F0\u0019*\u00020\u0001H\u0000\u001A\u0012\u0010G\u001A\u00020\u0001*\b\u0012\u0004\u0012\u00020F0\u0019H\u0000\u001A\f\u0010H\u001A\u00020\u000B*\u00020\rH\u0000\u001A\f\u0010H\u001A\u00020\u000B*\u00020\u0010H\u0000\u001A\u0016\u0010I\u001A\u00020\u000B*\u0002002\b\b\u0002\u0010J\u001A\u00020\tH\u0000\u001A\u001C\u0010K\u001A\b\u0012\u0004\u0012\u0002H\u001A0\u0019\"\u0004\b\u0000\u0010\u001A*\b\u0012\u0004\u0012\u0002H\u001A0\u0019\u001A\r\u0010L\u001A\u00020\'*\u00020\u0016H\u0080\b\"\u0010\u0010\u0000\u001A\u00020\u00018\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000\"\u0010\u0010\u0004\u001A\u00020\u00058\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000\"\u0010\u0010\u0006\u001A\u00020\u00078\u0000X\u0081\u0004\u00A2\u0006\u0002\n\u0000\"\u0010\u0010\b\u001A\u00020\t8\u0000X\u0081\u0004\u00A2\u0006\u0002\n\u0000\"\u0010\u0010\n\u001A\u00020\u000B8\u0000X\u0081\u0004\u00A2\u0006\u0002\n\u0000*\n\u0010M\"\u0002002\u000200\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006N"}, d2 = {"EMPTY_HEADERS", "Lokhttp3/Headers;", "EMPTY_REQUEST", "Lokhttp3/RequestBody;", "EMPTY_RESPONSE", "Lokhttp3/ResponseBody;", "UTC", "Ljava/util/TimeZone;", "assertionsEnabled", "", "okHttpName", "", "checkDuration", "", "name", "duration", "", "unit", "Ljava/util/concurrent/TimeUnit;", "format", "args", "", "", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "immutableListOf", "", "T", "elements", "([Ljava/lang/Object;)Ljava/util/List;", "readFieldOrNull", "instance", "fieldType", "Ljava/lang/Class;", "fieldName", "(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "threadFactory", "Ljava/util/concurrent/ThreadFactory;", "daemon", "threadName", "", "block", "Lkotlin/Function0;", "asFactory", "Lokhttp3/EventListener$Factory;", "Lokhttp3/EventListener;", "assertThreadDoesntHoldLock", "assertThreadHoldsLock", "canReuseConnectionFor", "Lokhttp3/HttpUrl;", "other", "closeQuietly", "Ljava/net/ServerSocket;", "Ljava/net/Socket;", "discard", "Lokio/Source;", "timeout", "timeUnit", "headersContentLength", "Lokhttp3/Response;", "isHealthy", "source", "Lokio/BufferedSource;", "notify", "notifyAll", "peerName", "readBomAsCharset", "Ljava/nio/charset/Charset;", "default", "skipAll", "toHeaderList", "Lokhttp3/internal/http2/Header;", "toHeaders", "toHexString", "toHostHeader", "includeDefaultPort", "toImmutableList", "wait", "HttpUrlRepresentation", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class _UtilJvmKt {
    public static final Headers EMPTY_HEADERS;
    public static final RequestBody EMPTY_REQUEST;
    public static final ResponseBody EMPTY_RESPONSE;
    public static final TimeZone UTC;
    public static final boolean assertionsEnabled;
    public static final String okHttpName;

    // 检测为 Lambda 实现
    public static EventListener $r8$lambda$fH14ds-wEUmL5I-DC0Q1j9BfXO8(EventListener eventListener0, Call call0) [...]

    // 检测为 Lambda 实现
    public static Thread $r8$lambda$spKDnWFkDT_DsdezkqD7R0-8_OE(String s, boolean z, Runnable runnable0) [...]

    static {
        _UtilJvmKt.EMPTY_HEADERS = _UtilCommonKt.getCommonEmptyHeaders();
        _UtilJvmKt.EMPTY_REQUEST = _UtilCommonKt.getCommonEmptyRequestBody();
        _UtilJvmKt.EMPTY_RESPONSE = _UtilCommonKt.getCommonEmptyResponse();
        TimeZone timeZone0 = TimeZone.getTimeZone("GMT");
        Intrinsics.checkNotNull(timeZone0);
        _UtilJvmKt.UTC = timeZone0;
        _UtilJvmKt.assertionsEnabled = false;
        Intrinsics.checkNotNullExpressionValue("okhttp3.OkHttpClient", "OkHttpClient::class.java.name");
        _UtilJvmKt.okHttpName = "OkHttp";
    }

    public static final Factory asFactory(EventListener eventListener0) {
        Intrinsics.checkNotNullParameter(eventListener0, "<this>");
        return (Call call0) -> _UtilJvmKt.asFactory$lambda-7(eventListener0, call0);
    }

    private static final EventListener asFactory$lambda-7(EventListener eventListener0, Call call0) {
        Intrinsics.checkNotNullParameter(eventListener0, "$this_asFactory");
        Intrinsics.checkNotNullParameter(call0, "it");
        return eventListener0;
    }

    // 去混淆评级： 低(20)
    public static final void assertThreadDoesntHoldLock(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "<this>");
        if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(object0)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13402 MUST NOT hold lock on " + object0);
        }
    }

    // 去混淆评级： 低(20)
    public static final void assertThreadHoldsLock(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "<this>");
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(object0)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13407 MUST hold lock on " + object0);
        }
    }

    public static final boolean canReuseConnectionFor(HttpUrl httpUrl0, HttpUrl httpUrl1) {
        Intrinsics.checkNotNullParameter(httpUrl0, "<this>");
        Intrinsics.checkNotNullParameter(httpUrl1, "other");
        return Intrinsics.areEqual(httpUrl0.host(), httpUrl1.host()) && httpUrl0.port() == httpUrl1.port() && Intrinsics.areEqual(httpUrl0.scheme(), httpUrl1.scheme());
    }

    public static final int checkDuration(String s, long v, TimeUnit timeUnit0) {
        Intrinsics.checkNotNullParameter(s, "name");
        int v1 = Long.compare(v, 0L);
        if(v1 < 0) {
            throw new IllegalStateException((s + " < 0").toString());
        }
        if(timeUnit0 == null) {
            throw new IllegalStateException("unit == null");
        }
        long v2 = timeUnit0.toMillis(v);
        if(v2 > 0x7FFFFFFFL) {
            throw new IllegalArgumentException((s + " too large.").toString());
        }
        if(v2 == 0L && v1 > 0) {
            throw new IllegalArgumentException((s + " too small.").toString());
        }
        return (int)v2;
    }

    public static final void closeQuietly(ServerSocket serverSocket0) {
        Intrinsics.checkNotNullParameter(serverSocket0, "<this>");
        try {
            serverSocket0.close();
        }
        catch(RuntimeException runtimeException0) {
            throw runtimeException0;
        }
        catch(Exception unused_ex) {
        }
    }

    public static final void closeQuietly(Socket socket0) {
        Intrinsics.checkNotNullParameter(socket0, "<this>");
        try {
            socket0.close();
        }
        catch(AssertionError assertionError0) {
            throw assertionError0;
        }
        catch(RuntimeException runtimeException0) {
            if(!Intrinsics.areEqual(runtimeException0.getMessage(), "bio == null")) {
                throw runtimeException0;
            }
        }
        catch(Exception unused_ex) {
        }
    }

    public static final boolean discard(Source source0, int v, TimeUnit timeUnit0) {
        Intrinsics.checkNotNullParameter(source0, "<this>");
        Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
        try {
            return _UtilJvmKt.skipAll(source0, v, timeUnit0);
        }
        catch(IOException unused_ex) {
            return false;
        }
    }

    public static final String format(String s, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(s, "format");
        Intrinsics.checkNotNullParameter(arr_object, "args");
        Locale locale0 = Locale.US;
        Object[] arr_object1 = Arrays.copyOf(arr_object, arr_object.length);
        String s1 = String.format(locale0, s, Arrays.copyOf(arr_object1, arr_object1.length));
        Intrinsics.checkNotNullExpressionValue(s1, "format(locale, format, *args)");
        return s1;
    }

    public static final long headersContentLength(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        String s = response0.headers().get("Content-Length");
        return s == null ? -1L : _UtilCommonKt.toLongOrDefault(s, -1L);
    }

    @SafeVarargs
    public static final List immutableListOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        Object[] arr_object1 = (Object[])arr_object.clone();
        List list0 = Collections.unmodifiableList(CollectionsKt.listOf(Arrays.copyOf(arr_object1, arr_object1.length)));
        Intrinsics.checkNotNullExpressionValue(list0, "unmodifiableList(listOf(*elements.clone()))");
        return list0;
    }

    public static final boolean isHealthy(Socket socket0, BufferedSource bufferedSource0) {
        Intrinsics.checkNotNullParameter(socket0, "<this>");
        Intrinsics.checkNotNullParameter(bufferedSource0, "source");
        try {
            int v = socket0.getSoTimeout();
            try {
                socket0.setSoTimeout(1);
                boolean z = bufferedSource0.exhausted();
                return !z;
            }
            finally {
                socket0.setSoTimeout(v);
            }
        }
        catch(SocketTimeoutException unused_ex) {
            return true;
        }
        catch(IOException unused_ex) {
            return false;
        }
    }

    public static final void notify(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "<this>");
        object0.notify();
    }

    public static final void notifyAll(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "<this>");
        object0.notifyAll();
    }

    public static final String peerName(Socket socket0) {
        Intrinsics.checkNotNullParameter(socket0, "<this>");
        SocketAddress socketAddress0 = socket0.getRemoteSocketAddress();
        if(socketAddress0 instanceof InetSocketAddress) {
            String s = ((InetSocketAddress)socketAddress0).getHostName();
            Intrinsics.checkNotNullExpressionValue(s, "address.hostName");
            return s;
        }
        return socketAddress0.toString();
    }

    public static final Charset readBomAsCharset(BufferedSource bufferedSource0, Charset charset0) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource0, "<this>");
        Intrinsics.checkNotNullParameter(charset0, "default");
        switch(bufferedSource0.select(_UtilCommonKt.getUNICODE_BOMS())) {
            case -1: {
                return charset0;
            }
            case 0: {
                return Charsets.UTF_8;
            }
            case 1: {
                return Charsets.UTF_16BE;
            }
            case 2: {
                return Charsets.UTF_16LE;
            }
            case 3: {
                return Charsets.INSTANCE.UTF32_BE();
            }
            case 4: {
                return Charsets.INSTANCE.UTF32_LE();
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    public static final Object readFieldOrNull(Object object0, Class class0, String s) {
        Intrinsics.checkNotNullParameter(object0, "instance");
        Intrinsics.checkNotNullParameter(class0, "fieldType");
        Intrinsics.checkNotNullParameter(s, "fieldName");
        Class class1 = object0.getClass();
        while(!Intrinsics.areEqual(class1, Object.class)) {
            try {
                Field field0 = class1.getDeclaredField(s);
                field0.setAccessible(true);
                Object object1 = field0.get(object0);
                return !class0.isInstance(object1) ? null : class0.cast(object1);
            }
            catch(NoSuchFieldException unused_ex) {
                class1 = class1.getSuperclass();
                Intrinsics.checkNotNullExpressionValue(class1, "c.superclass");
                continue;
            }
            return null;
        }
        if(!Intrinsics.areEqual(s, "delegate")) {
            Object object2 = _UtilJvmKt.readFieldOrNull(object0, Object.class, "delegate");
            return object2 == null ? null : _UtilJvmKt.readFieldOrNull(object2, class0, s);
        }
        return null;
    }

    public static final boolean skipAll(Source source0, int v, TimeUnit timeUnit0) throws IOException {
        Intrinsics.checkNotNullParameter(source0, "<this>");
        Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
        long v1 = System.nanoTime();
        long v2 = source0.timeout().hasDeadline() ? source0.timeout().deadlineNanoTime() - v1 : 0x7FFFFFFFFFFFFFFFL;
        source0.timeout().deadlineNanoTime(Math.min(v2, timeUnit0.toNanos(((long)v))) + v1);
        try {
            Buffer buffer0 = new Buffer();
            while(source0.read(buffer0, 0x2000L) != -1L) {
                buffer0.clear();
            }
        }
        catch(InterruptedIOException unused_ex) {
            if(v2 == 0x7FFFFFFFFFFFFFFFL) {
                source0.timeout().clearDeadline();
                return false;
            }
            source0.timeout().deadlineNanoTime(v1 + v2);
            return false;
        }
        catch(Throwable throwable0) {
            if(v2 == 0x7FFFFFFFFFFFFFFFL) {
                source0.timeout().clearDeadline();
            }
            else {
                source0.timeout().deadlineNanoTime(v1 + v2);
            }
            throw throwable0;
        }
        if(v2 == 0x7FFFFFFFFFFFFFFFL) {
            source0.timeout().clearDeadline();
            return true;
        }
        source0.timeout().deadlineNanoTime(v1 + v2);
        return true;
    }

    public static final ThreadFactory threadFactory(String s, boolean z) {
        Intrinsics.checkNotNullParameter(s, "name");
        return (Runnable runnable0) -> _UtilJvmKt.threadFactory$lambda-1(s, z, runnable0);
    }

    private static final Thread threadFactory$lambda-1(String s, boolean z, Runnable runnable0) {
        Intrinsics.checkNotNullParameter(s, "$name");
        Thread thread0 = new Thread(runnable0, s);
        thread0.setDaemon(z);
        return thread0;
    }

    public static final void threadName(String s, Function0 function00) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(function00, "block");
        Thread thread0 = Thread.currentThread();
        thread0.setName(s);
        try {
            function00.invoke();
        }
        finally {
            thread0.setName("jeb-dexdec-sb-st-13511");
        }
    }

    public static final List toHeaderList(Headers headers0) {
        Intrinsics.checkNotNullParameter(headers0, "<this>");
        Iterable iterable0 = RangesKt.until(0, headers0.size());
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        Iterator iterator0 = iterable0.iterator();
        while(iterator0.hasNext()) {
            int v = ((IntIterator)iterator0).nextInt();
            arrayList0.add(new Header(headers0.name(v), headers0.value(v)));
        }
        return arrayList0;
    }

    public static final Headers toHeaders(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Builder headers$Builder0 = new Builder();
        for(Object object0: list0) {
            ByteString byteString0 = ((Header)object0).component1();
            ByteString byteString1 = ((Header)object0).component2();
            headers$Builder0.addLenient$okhttp(byteString0.utf8(), byteString1.utf8());
        }
        return headers$Builder0.build();
    }

    public static final String toHexString(int v) {
        String s = Integer.toHexString(v);
        Intrinsics.checkNotNullExpressionValue(s, "toHexString(this)");
        return s;
    }

    public static final String toHexString(long v) {
        String s = Long.toHexString(v);
        Intrinsics.checkNotNullExpressionValue(s, "toHexString(this)");
        return s;
    }

    public static final String toHostHeader(HttpUrl httpUrl0, boolean z) {
        Intrinsics.checkNotNullParameter(httpUrl0, "<this>");
        String s = StringsKt.contains$default(httpUrl0.host(), ":", false, 2, null) ? "[" + httpUrl0.host() + ']' : httpUrl0.host();
        return z || httpUrl0.port() != HttpUrl.Companion.defaultPort(httpUrl0.scheme()) ? s + ':' + httpUrl0.port() : s;
    }

    public static String toHostHeader$default(HttpUrl httpUrl0, boolean z, int v, Object object0) {
        if((v & 1) != 0) {
            z = false;
        }
        return _UtilJvmKt.toHostHeader(httpUrl0, z);
    }

    public static final List toImmutableList(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        List list1 = Collections.unmodifiableList(CollectionsKt.toMutableList(list0));
        Intrinsics.checkNotNullExpressionValue(list1, "unmodifiableList(toMutableList())");
        return list1;
    }

    public static final void wait(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "<this>");
        object0.wait();
    }
}

