package androidx.core.provider;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri.Builder;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.tracing.Trace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import kotlin.UByte..ExternalSyntheticBackport0;

class FontProvider {
    interface ContentQueryWrapper {
        void close();

        Cursor query(Uri arg1, String[] arg2, String arg3, String[] arg4, String arg5, CancellationSignal arg6);
    }

    static class ContentQueryWrapperApi16Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        ContentQueryWrapperApi16Impl(Context context0, Uri uri0) {
            this.mClient = context0.getContentResolver().acquireUnstableContentProviderClient(uri0);
        }

        @Override  // androidx.core.provider.FontProvider$ContentQueryWrapper
        public void close() {
            ContentProviderClient contentProviderClient0 = this.mClient;
            if(contentProviderClient0 != null) {
                contentProviderClient0.release();
            }
        }

        @Override  // androidx.core.provider.FontProvider$ContentQueryWrapper
        public Cursor query(Uri uri0, String[] arr_s, String s, String[] arr_s1, String s1, CancellationSignal cancellationSignal0) {
            ContentProviderClient contentProviderClient0 = this.mClient;
            if(contentProviderClient0 == null) {
                return null;
            }
            try {
                return contentProviderClient0.query(uri0, arr_s, s, arr_s1, s1, cancellationSignal0);
            }
            catch(RemoteException remoteException0) {
                Log.w("FontsProvider", "Unable to query the content provider", remoteException0);
                return null;
            }
        }
    }

    static class ContentQueryWrapperApi24Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        ContentQueryWrapperApi24Impl(Context context0, Uri uri0) {
            this.mClient = context0.getContentResolver().acquireUnstableContentProviderClient(uri0);
        }

        @Override  // androidx.core.provider.FontProvider$ContentQueryWrapper
        public void close() {
            ContentProviderClient contentProviderClient0 = this.mClient;
            if(contentProviderClient0 != null) {
                UByte..ExternalSyntheticBackport0.m(contentProviderClient0);
            }
        }

        @Override  // androidx.core.provider.FontProvider$ContentQueryWrapper
        public Cursor query(Uri uri0, String[] arr_s, String s, String[] arr_s1, String s1, CancellationSignal cancellationSignal0) {
            ContentProviderClient contentProviderClient0 = this.mClient;
            if(contentProviderClient0 == null) {
                return null;
            }
            try {
                return contentProviderClient0.query(uri0, arr_s, s, arr_s1, s1, cancellationSignal0);
            }
            catch(RemoteException remoteException0) {
                Log.w("FontsProvider", "Unable to query the content provider", remoteException0);
                return null;
            }
        }
    }

    static class ProviderCacheKey {
        String mAuthority;
        List mCertificates;
        String mPackageName;

        ProviderCacheKey(String s, String s1, List list0) {
            this.mAuthority = s;
            this.mPackageName = s1;
            this.mCertificates = list0;
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            return object0 instanceof ProviderCacheKey ? Objects.equals(this.mAuthority, ((ProviderCacheKey)object0).mAuthority) && Objects.equals(this.mPackageName, ((ProviderCacheKey)object0).mPackageName) && Objects.equals(this.mCertificates, ((ProviderCacheKey)object0).mCertificates) : false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(new Object[]{this.mAuthority, this.mPackageName, this.mCertificates});
        }
    }

    private static final Comparator sByteArrayComparator;
    private static final LruCache sProviderCache;

    static {
        FontProvider.sProviderCache = new LruCache(2);
        FontProvider.sByteArrayComparator = (byte[] arr_b, byte[] arr_b1) -> {
            if(arr_b.length != arr_b1.length) {
                return arr_b.length - arr_b1.length;
            }
            for(int v = 0; v < arr_b.length; ++v) {
                int v1 = arr_b[v];
                int v2 = arr_b1[v];
                if(v1 != v2) {
                    return v1 - v2;
                }
            }
            return 0;
        };
    }

    static void clearProviderCache() {
        FontProvider.sProviderCache.evictAll();
    }

    private static List convertToByteArrayList(Signature[] arr_signature) {
        List list0 = new ArrayList();
        for(int v = 0; v < arr_signature.length; ++v) {
            list0.add(arr_signature[v].toByteArray());
        }
        return list0;
    }

    private static boolean equalsByteArrayList(List list0, List list1) {
        if(list0.size() != list1.size()) {
            return false;
        }
        for(int v = 0; v < list0.size(); ++v) {
            if(!Arrays.equals(((byte[])list0.get(v)), ((byte[])list1.get(v)))) {
                return false;
            }
        }
        return true;
    }

    private static List getCertificates(FontRequest fontRequest0, Resources resources0) {
        return fontRequest0.getCertificates() == null ? FontResourcesParserCompat.readCerts(resources0, fontRequest0.getCertificatesArrayResId()) : fontRequest0.getCertificates();
    }

    static FontFamilyResult getFontFamilyResult(Context context0, List list0, CancellationSignal cancellationSignal0) throws PackageManager.NameNotFoundException {
        Trace.beginSection("FontProvider.getFontFamilyResult");
        try {
            ArrayList arrayList0 = new ArrayList();
            for(int v1 = 0; v1 < list0.size(); ++v1) {
                FontRequest fontRequest0 = (FontRequest)list0.get(v1);
                ProviderInfo providerInfo0 = FontProvider.getProvider(context0.getPackageManager(), fontRequest0, context0.getResources());
                if(providerInfo0 == null) {
                    return FontFamilyResult.create(1, null);
                }
                arrayList0.add(FontProvider.query(context0, fontRequest0, providerInfo0.authority, cancellationSignal0));
            }
            return FontFamilyResult.create(0, arrayList0);
        }
        finally {
            Trace.endSection();
        }
    }

    static ProviderInfo getProvider(PackageManager packageManager0, FontRequest fontRequest0, Resources resources0) throws PackageManager.NameNotFoundException {
        String s;
        Trace.beginSection("FontProvider.getProvider");
        try {
            List list0 = FontProvider.getCertificates(fontRequest0, resources0);
            ProviderCacheKey fontProvider$ProviderCacheKey0 = new ProviderCacheKey(fontRequest0.getProviderAuthority(), fontRequest0.getProviderPackage(), list0);
            ProviderInfo providerInfo0 = (ProviderInfo)FontProvider.sProviderCache.get(fontProvider$ProviderCacheKey0);
            if(providerInfo0 != null) {
                return providerInfo0;
            }
            s = fontRequest0.getProviderAuthority();
            ProviderInfo providerInfo1 = packageManager0.resolveContentProvider(s, 0);
            if(providerInfo1 != null) {
                if(!providerInfo1.packageName.equals(fontRequest0.getProviderPackage())) {
                    throw new PackageManager.NameNotFoundException("Found content provider " + s + ", but package was not " + fontRequest0.getProviderPackage());
                }
                List list1 = FontProvider.convertToByteArrayList(packageManager0.getPackageInfo(providerInfo1.packageName, 0x40).signatures);
                Collections.sort(list1, FontProvider.sByteArrayComparator);
                for(int v1 = 0; v1 < list0.size(); ++v1) {
                    ArrayList arrayList0 = new ArrayList(((Collection)list0.get(v1)));
                    Collections.sort(arrayList0, FontProvider.sByteArrayComparator);
                    if(FontProvider.equalsByteArrayList(list1, arrayList0)) {
                        FontProvider.sProviderCache.put(fontProvider$ProviderCacheKey0, providerInfo1);
                        return providerInfo1;
                    }
                }
                return null;
            }
        }
        finally {
            Trace.endSection();
        }
        throw new PackageManager.NameNotFoundException("No package found for authority: " + s);
    }

    // 检测为 Lambda 实现
    static int lambda$static$0(byte[] arr_b, byte[] arr_b1) [...]

    static FontInfo[] query(Context context0, FontRequest fontRequest0, String s, CancellationSignal cancellationSignal0) {
        Trace.beginSection("FontProvider.query");
        try {
            ArrayList arrayList0 = new ArrayList();
            Uri uri0 = new Uri.Builder().scheme("content").authority(s).build();
            Uri uri1 = new Uri.Builder().scheme("content").authority(s).appendPath("file").build();
            ContentQueryWrapper fontProvider$ContentQueryWrapper0 = FontProvider.ContentQueryWrapper.-CC.make(context0, uri0);
            Cursor cursor0 = null;
            try {
                Trace.beginSection("ContentQueryWrapper.query");
                try {
                    cursor0 = fontProvider$ContentQueryWrapper0.query(uri0, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{fontRequest0.getQuery()}, null, cancellationSignal0);
                }
                finally {
                    Trace.endSection();
                }
                if(cursor0 != null && cursor0.getCount() > 0) {
                    int v2 = cursor0.getColumnIndex("result_code");
                    ArrayList arrayList1 = new ArrayList();
                    int v3 = cursor0.getColumnIndex("_id");
                    int v4 = cursor0.getColumnIndex("file_id");
                    int v5 = cursor0.getColumnIndex("font_ttc_index");
                    int v6 = cursor0.getColumnIndex("font_weight");
                    int v7 = cursor0.getColumnIndex("font_italic");
                    while(cursor0.moveToNext()) {
                        int v8 = v2 == -1 ? 0 : cursor0.getInt(v2);
                        int v9 = v5 == -1 ? 0 : cursor0.getInt(v5);
                        arrayList1.add(FontInfo.create((v4 == -1 ? ContentUris.withAppendedId(uri0, cursor0.getLong(v3)) : ContentUris.withAppendedId(uri1, cursor0.getLong(v4))), v9, (v6 == -1 ? 400 : cursor0.getInt(v6)), v7 != -1 && cursor0.getInt(v7) == 1, v8));
                    }
                    arrayList0 = arrayList1;
                }
            }
            catch(Throwable throwable0) {
                if(cursor0 != null) {
                    cursor0.close();
                }
                fontProvider$ContentQueryWrapper0.close();
                throw throwable0;
            }
            if(cursor0 != null) {
                cursor0.close();
            }
            fontProvider$ContentQueryWrapper0.close();
            return (FontInfo[])arrayList0.toArray(new FontInfo[0]);
        }
        finally {
            Trace.endSection();
        }
    }
}

