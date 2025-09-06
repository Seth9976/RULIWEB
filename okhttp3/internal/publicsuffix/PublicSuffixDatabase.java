package okhttp3.internal.publicsuffix;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.platform.Platform;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u001C\u0010\n\u001A\b\u0012\u0004\u0012\u00020\f0\u000B2\f\u0010\r\u001A\b\u0012\u0004\u0012\u00020\f0\u000BH\u0002J\u0010\u0010\u000E\u001A\u0004\u0018\u00010\f2\u0006\u0010\u000F\u001A\u00020\fJ\b\u0010\u0010\u001A\u00020\u0011H\u0002J\b\u0010\u0012\u001A\u00020\u0011H\u0002J\u0016\u0010\u0013\u001A\u00020\u00112\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\u0005\u001A\u00020\u0006J\u0016\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\f0\u000B2\u0006\u0010\u000F\u001A\u00020\fH\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "", "()V", "listRead", "Ljava/util/concurrent/atomic/AtomicBoolean;", "publicSuffixExceptionListBytes", "", "publicSuffixListBytes", "readCompleteLatch", "Ljava/util/concurrent/CountDownLatch;", "findMatchingRule", "", "", "domainLabels", "getEffectiveTldPlusOne", "domain", "readTheList", "", "readTheListUninterruptibly", "setListBytes", "splitDomain", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class PublicSuffixDatabase {
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001A\u00020\fJ)\u0010\u000E\u001A\u0004\u0018\u00010\u0007*\u00020\n2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\n0\u00102\u0006\u0010\u0011\u001A\u00020\u0012H\u0002¢\u0006\u0002\u0010\u0013R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;", "", "()V", "EXCEPTION_MARKER", "", "PREVAILING_RULE", "", "", "PUBLIC_SUFFIX_RESOURCE", "WILDCARD_LABEL", "", "instance", "Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "get", "binarySearch", "labels", "", "labelIndex", "", "([B[[BI)Ljava/lang/String;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        // This method was un-flattened
        private final String binarySearch(byte[] arr_b, byte[][] arr2_b, int v) {
            int v6;
            int v1 = arr_b.length;
            int v2 = 0;
            while(v2 < v1) {
                int v3;
                for(v3 = (v2 + v1) / 2; v3 > -1 && arr_b[v3] != 10; --v3) {
                }
                int v4 = v3 + 1;
                for(int v5 = 1; true; ++v5) {
                    v6 = v4 + v5;
                    if(arr_b[v6] == 10) {
                        break;
                    }
                }
                int v7 = v6 - v4;
                int v8 = v;
                int v9 = 0;
                int v10 = 0;
            alab1:
                while(true) {
                    for(int v11 = _UtilCommonKt.and(arr2_b[v8][v9], ((byte)0xFF)); true; v11 = 46) {
                        int v12 = v11 - _UtilCommonKt.and(arr_b[v4 + v10], ((byte)0xFF));
                        if(v12 != 0) {
                            break alab1;
                        }
                        ++v10;
                        ++v9;
                        if(v10 == v7) {
                            break alab1;
                        }
                        if(arr2_b[v8].length != v9) {
                            break;
                        }
                        if(v8 == arr2_b.length - 1) {
                            break alab1;
                        }
                        ++v8;
                        v9 = -1;
                    }
                }
                if(v12 >= 0) {
                    if(v12 <= 0) {
                        int v13 = v7 - v10;
                        int v14 = arr2_b[v8].length - v9;
                        for(int v15 = v8 + 1; v15 < arr2_b.length; ++v15) {
                            v14 += arr2_b[v15].length;
                        }
                        if(v14 >= v13) {
                            if(v14 <= v13) {
                                return new String(arr_b, v4, v7, Charsets.UTF_8);
                            }
                            v2 = v6 + 1;
                            continue;
                        }
                    }
                    else {
                        v2 = v6 + 1;
                        continue;
                    }
                }
                v1 = v3;
            }
            return null;
        }

        public final PublicSuffixDatabase get() {
            return PublicSuffixDatabase.instance;
        }
    }

    public static final Companion Companion = null;
    private static final char EXCEPTION_MARKER = '!';
    private static final List PREVAILING_RULE;
    public static final String PUBLIC_SUFFIX_RESOURCE;
    private static final byte[] WILDCARD_LABEL;
    private static final PublicSuffixDatabase instance;
    private final AtomicBoolean listRead;
    private byte[] publicSuffixExceptionListBytes;
    private byte[] publicSuffixListBytes;
    private final CountDownLatch readCompleteLatch;

    static {
        PublicSuffixDatabase.Companion = new Companion(null);
        PublicSuffixDatabase.PUBLIC_SUFFIX_RESOURCE = "PublicSuffixDatabase.gz";
        PublicSuffixDatabase.WILDCARD_LABEL = new byte[]{42};
        PublicSuffixDatabase.PREVAILING_RULE = CollectionsKt.listOf("*");
        PublicSuffixDatabase.instance = new PublicSuffixDatabase();
    }

    public PublicSuffixDatabase() {
        this.listRead = new AtomicBoolean(false);
        this.readCompleteLatch = new CountDownLatch(1);
    }

    private final List findMatchingRule(List list0) {
        List list2;
        List list1;
        String s1;
        String s;
        if(this.listRead.get() || !this.listRead.compareAndSet(false, true)) {
            try {
                this.readCompleteLatch.await();
            }
            catch(InterruptedException unused_ex) {
                Thread.currentThread().interrupt();
            }
        }
        else {
            this.readTheListUninterruptibly();
        }
        if(this.publicSuffixListBytes == null) {
            throw new IllegalStateException("Unable to load PublicSuffixDatabase.gz resource from the classpath.");
        }
        int v = list0.size();
        byte[][] arr2_b = new byte[v][];
        for(int v1 = 0; v1 < v; ++v1) {
            byte[] arr_b = ((String)list0.get(v1)).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(arr_b, "this as java.lang.String).getBytes(charset)");
            arr2_b[v1] = arr_b;
        }
        for(int v2 = 0; true; ++v2) {
            s = null;
            s1 = null;
            if(v2 >= arr2_b.length) {
                break;
            }
            Companion publicSuffixDatabase$Companion0 = PublicSuffixDatabase.Companion;
            byte[] arr_b1 = this.publicSuffixListBytes;
            if(arr_b1 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");
                arr_b1 = null;
            }
            String s2 = publicSuffixDatabase$Companion0.binarySearch(arr_b1, arr2_b, v2);
            if(s2 != null) {
                s = s2;
                break;
            }
        }
        String s3 = null;
        if(arr2_b.length > 1) {
            byte[][] arr2_b1 = (byte[][])arr2_b.clone();
            int v3 = arr2_b1.length - 1;
            for(int v4 = 0; v4 < v3; ++v4) {
                arr2_b1[v4] = PublicSuffixDatabase.WILDCARD_LABEL;
                Companion publicSuffixDatabase$Companion1 = PublicSuffixDatabase.Companion;
                byte[] arr_b2 = this.publicSuffixListBytes;
                if(arr_b2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");
                    arr_b2 = null;
                }
                String s4 = publicSuffixDatabase$Companion1.binarySearch(arr_b2, arr2_b1, v4);
                if(s4 != null) {
                    s3 = s4;
                    break;
                }
            }
        }
        if(s3 != null) {
            int v5 = arr2_b.length - 1;
            for(int v6 = 0; v6 < v5; ++v6) {
                Companion publicSuffixDatabase$Companion2 = PublicSuffixDatabase.Companion;
                byte[] arr_b3 = this.publicSuffixExceptionListBytes;
                if(arr_b3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("publicSuffixExceptionListBytes");
                    arr_b3 = null;
                }
                String s5 = publicSuffixDatabase$Companion2.binarySearch(arr_b3, arr2_b, v6);
                if(s5 != null) {
                    s1 = s5;
                    break;
                }
            }
        }
        if(s1 != null) {
            return StringsKt.split$default(("!" + s1), new char[]{'.'}, false, 0, 6, null);
        }
        if(s == null && s3 == null) {
            return PublicSuffixDatabase.PREVAILING_RULE;
        }
        if(s == null) {
            list1 = CollectionsKt.emptyList();
        }
        else {
            list1 = StringsKt.split$default(s, new char[]{'.'}, false, 0, 6, null);
            if(list1 == null) {
                list1 = CollectionsKt.emptyList();
            }
        }
        if(s3 == null) {
            list2 = CollectionsKt.emptyList();
        }
        else {
            list2 = StringsKt.split$default(s3, new char[]{'.'}, false, 0, 6, null);
            if(list2 == null) {
                list2 = CollectionsKt.emptyList();
                return list1.size() > list2.size() ? list1 : list2;
            }
        }
        return list1.size() > list2.size() ? list1 : list2;
    }

    public final String getEffectiveTldPlusOne(String s) {
        Intrinsics.checkNotNullParameter(s, "domain");
        String s1 = IDN.toUnicode(s);
        Intrinsics.checkNotNullExpressionValue(s1, "unicodeDomain");
        List list0 = this.splitDomain(s1);
        List list1 = this.findMatchingRule(list0);
        if(list0.size() == list1.size() && ((String)list1.get(0)).charAt(0) != 33) {
            return null;
        }
        return ((String)list1.get(0)).charAt(0) == 33 ? SequencesKt.joinToString$default(SequencesKt.drop(CollectionsKt.asSequence(this.splitDomain(s)), list0.size() - list1.size()), ".", null, null, 0, null, null, 62, null) : SequencesKt.joinToString$default(SequencesKt.drop(CollectionsKt.asSequence(this.splitDomain(s)), list0.size() - (list1.size() + 1)), ".", null, null, 0, null, null, 62, null);
    }

    private final void readTheList() throws IOException {
        byte[] arr_b1;
        byte[] arr_b;
        InputStream inputStream0 = PublicSuffixDatabase.class.getResourceAsStream("PublicSuffixDatabase.gz");
        if(inputStream0 == null) {
            return;
        }
        Closeable closeable0 = Okio.buffer(new GzipSource(Okio.source(inputStream0)));
        try {
            arr_b = ((BufferedSource)closeable0).readByteArray(((long)((BufferedSource)closeable0).readInt()));
            arr_b1 = ((BufferedSource)closeable0).readByteArray(((long)((BufferedSource)closeable0).readInt()));
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        synchronized(this) {
            Intrinsics.checkNotNull(arr_b);
            this.publicSuffixListBytes = arr_b;
            Intrinsics.checkNotNull(arr_b1);
            this.publicSuffixExceptionListBytes = arr_b1;
        }
        this.readCompleteLatch.countDown();
    }

    private final void readTheListUninterruptibly() {
        boolean z = false;
        try {
            while(true) {
                try {
                    this.readTheList();
                    goto label_10;
                }
                catch(InterruptedIOException unused_ex) {
                }
                catch(IOException iOException0) {
                    break;
                }
                Thread.interrupted();
                z = true;
            }
            Platform.Companion.get().log("Failed to read public suffix list", 5, iOException0);
            if(z) {
                goto label_11;
            }
            return;
        }
        catch(Throwable throwable0) {
            goto label_15;
        }
        goto label_11;
    label_10:
        if(z) {
        label_11:
            Thread.currentThread().interrupt();
            return;
        }
        try {
        }
        catch(Throwable throwable0) {
        label_15:
            if(z) {
                Thread.currentThread().interrupt();
            }
            throw throwable0;
        }
    }

    public final void setListBytes(byte[] arr_b, byte[] arr_b1) {
        Intrinsics.checkNotNullParameter(arr_b, "publicSuffixListBytes");
        Intrinsics.checkNotNullParameter(arr_b1, "publicSuffixExceptionListBytes");
        this.publicSuffixListBytes = arr_b;
        this.publicSuffixExceptionListBytes = arr_b1;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }

    private final List splitDomain(String s) {
        List list0 = StringsKt.split$default(s, new char[]{'.'}, false, 0, 6, null);
        return Intrinsics.areEqual(CollectionsKt.last(list0), "") ? CollectionsKt.dropLast(list0, 1) : list0;
    }
}

