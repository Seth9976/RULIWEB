package com.android.volley.toolbox;

import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.Cache.Entry;
import com.android.volley.Cache;
import com.android.volley.Header;
import com.android.volley.VolleyLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import jeb.synthetic.FIN;

public class DiskBasedCache implements Cache {
    static class CacheHeader {
        final List allResponseHeaders;
        final String etag;
        final String key;
        final long lastModified;
        final long serverDate;
        long size;
        final long softTtl;
        final long ttl;

        CacheHeader(String s, Entry cache$Entry0) {
            this(s, cache$Entry0.etag, cache$Entry0.serverDate, cache$Entry0.lastModified, cache$Entry0.ttl, cache$Entry0.softTtl, CacheHeader.getAllResponseHeaders(cache$Entry0));
        }

        private CacheHeader(String s, String s1, long v, long v1, long v2, long v3, List list0) {
            this.key = s;
            if("".equals(s1)) {
                s1 = null;
            }
            this.etag = s1;
            this.serverDate = v;
            this.lastModified = v1;
            this.ttl = v2;
            this.softTtl = v3;
            this.allResponseHeaders = list0;
        }

        private static List getAllResponseHeaders(Entry cache$Entry0) {
            return cache$Entry0.allResponseHeaders == null ? HttpHeaderParser.toAllHeaderList(cache$Entry0.responseHeaders) : cache$Entry0.allResponseHeaders;
        }

        static CacheHeader readHeader(CountingInputStream diskBasedCache$CountingInputStream0) throws IOException {
            if(DiskBasedCache.readInt(diskBasedCache$CountingInputStream0) != 0x20150306) {
                throw new IOException();
            }
            return new CacheHeader(DiskBasedCache.readString(diskBasedCache$CountingInputStream0), DiskBasedCache.readString(diskBasedCache$CountingInputStream0), DiskBasedCache.readLong(diskBasedCache$CountingInputStream0), DiskBasedCache.readLong(diskBasedCache$CountingInputStream0), DiskBasedCache.readLong(diskBasedCache$CountingInputStream0), DiskBasedCache.readLong(diskBasedCache$CountingInputStream0), DiskBasedCache.readHeaderList(diskBasedCache$CountingInputStream0));
        }

        Entry toCacheEntry(byte[] arr_b) {
            Entry cache$Entry0 = new Entry();
            cache$Entry0.data = arr_b;
            cache$Entry0.etag = this.etag;
            cache$Entry0.serverDate = this.serverDate;
            cache$Entry0.lastModified = this.lastModified;
            cache$Entry0.ttl = this.ttl;
            cache$Entry0.softTtl = this.softTtl;
            cache$Entry0.responseHeaders = HttpHeaderParser.toHeaderMap(this.allResponseHeaders);
            cache$Entry0.allResponseHeaders = Collections.unmodifiableList(this.allResponseHeaders);
            return cache$Entry0;
        }

        boolean writeHeader(OutputStream outputStream0) {
            try {
                DiskBasedCache.writeInt(outputStream0, 0x20150306);
                DiskBasedCache.writeString(outputStream0, this.key);
                DiskBasedCache.writeString(outputStream0, (this.etag == null ? "" : this.etag));
                DiskBasedCache.writeLong(outputStream0, this.serverDate);
                DiskBasedCache.writeLong(outputStream0, this.lastModified);
                DiskBasedCache.writeLong(outputStream0, this.ttl);
                DiskBasedCache.writeLong(outputStream0, this.softTtl);
                DiskBasedCache.writeHeaderList(this.allResponseHeaders, outputStream0);
                outputStream0.flush();
                return true;
            }
            catch(IOException iOException0) {
                VolleyLog.d("%s", new Object[]{iOException0.toString()});
                return false;
            }
        }
    }

    static class CountingInputStream extends FilterInputStream {
        private long bytesRead;
        private final long length;

        CountingInputStream(InputStream inputStream0, long v) {
            super(inputStream0);
            this.length = v;
        }

        long bytesRead() {
            return this.bytesRead;
        }

        long bytesRemaining() {
            return this.length - this.bytesRead;
        }

        @Override
        public int read() throws IOException {
            int v = super.read();
            if(v != -1) {
                ++this.bytesRead;
            }
            return v;
        }

        @Override
        public int read(byte[] arr_b, int v, int v1) throws IOException {
            int v2 = super.read(arr_b, v, v1);
            if(v2 != -1) {
                this.bytesRead += (long)v2;
            }
            return v2;
        }
    }

    public interface FileSupplier {
        File get();
    }

    private static final int CACHE_MAGIC = 0x20150306;
    private static final int DEFAULT_DISK_USAGE_BYTES = 0x500000;
    static final float HYSTERESIS_FACTOR = 0.9f;
    private final Map mEntries;
    private final int mMaxCacheSizeInBytes;
    private final FileSupplier mRootDirectorySupplier;
    private long mTotalSize;

    public DiskBasedCache(FileSupplier diskBasedCache$FileSupplier0) {
        this(diskBasedCache$FileSupplier0, 0x500000);
    }

    public DiskBasedCache(FileSupplier diskBasedCache$FileSupplier0, int v) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0L;
        this.mRootDirectorySupplier = diskBasedCache$FileSupplier0;
        this.mMaxCacheSizeInBytes = v;
    }

    public DiskBasedCache(File file0) {
        this(file0, 0x500000);
    }

    public DiskBasedCache(File file0, int v) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0L;
        this.mRootDirectorySupplier = new FileSupplier() {
            @Override  // com.android.volley.toolbox.DiskBasedCache$FileSupplier
            public File get() {
                return file0;
            }
        };
        this.mMaxCacheSizeInBytes = v;
    }

    @Override  // com.android.volley.Cache
    public void clear() {
        synchronized(this) {
            File[] arr_file = this.mRootDirectorySupplier.get().listFiles();
            if(arr_file != null) {
                for(int v1 = 0; v1 < arr_file.length; ++v1) {
                    arr_file[v1].delete();
                }
            }
            this.mEntries.clear();
            this.mTotalSize = 0L;
            VolleyLog.d("Cache cleared.", new Object[0]);
        }
    }

    InputStream createInputStream(File file0) throws FileNotFoundException {
        return new FileInputStream(file0);
    }

    OutputStream createOutputStream(File file0) throws FileNotFoundException {
        return new FileOutputStream(file0);
    }

    @Override  // com.android.volley.Cache
    public Entry get(String s) {
        Entry cache$Entry0;
        __monitor_enter(this);
        int v = FIN.finallyOpen$NT();
        CacheHeader diskBasedCache$CacheHeader0 = (CacheHeader)this.mEntries.get(s);
        if(diskBasedCache$CacheHeader0 == null) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(this);
            FIN.finallyCodeEnd$NT(v);
            return null;
        }
        File file0 = this.getFileForKey(s);
        try {
            CountingInputStream diskBasedCache$CountingInputStream0 = new CountingInputStream(new BufferedInputStream(this.createInputStream(file0)), file0.length());
            try {
                CacheHeader diskBasedCache$CacheHeader1 = CacheHeader.readHeader(diskBasedCache$CountingInputStream0);
                if(!TextUtils.equals(s, diskBasedCache$CacheHeader1.key)) {
                    VolleyLog.d("%s: key=%s, found=%s", new Object[]{file0.getAbsolutePath(), s, diskBasedCache$CacheHeader1.key});
                    this.removeEntry(s);
                    FIN.finallyExec$NT(v);
                    return null;
                }
                cache$Entry0 = diskBasedCache$CacheHeader0.toCacheEntry(DiskBasedCache.streamToBytes(diskBasedCache$CountingInputStream0, diskBasedCache$CountingInputStream0.bytesRemaining()));
            }
            finally {
                diskBasedCache$CountingInputStream0.close();
            }
            FIN.finallyExec$NT(v);
            return cache$Entry0;
        }
        catch(IOException iOException0) {
            VolleyLog.d("%s: %s", new Object[]{file0.getAbsolutePath(), iOException0.toString()});
            this.remove(s);
            FIN.finallyExec$NT(v);
            return null;
        }
    }

    public File getFileForKey(String s) {
        return new File(this.mRootDirectorySupplier.get(), this.getFilenameForKey(s));
    }

    private String getFilenameForKey(String s) {
        int v = s.length();
        return s.substring(0, v / 2).hashCode() + String.valueOf(s.substring(v / 2).hashCode());
    }

    @Override  // com.android.volley.Cache
    public void initialize() {
        synchronized(this) {
            File file0 = this.mRootDirectorySupplier.get();
            if(!file0.exists()) {
                if(!file0.mkdirs()) {
                    VolleyLog.e("Unable to create cache dir %s", new Object[]{file0.getAbsolutePath()});
                }
                return;
            }
            File[] arr_file = file0.listFiles();
            if(arr_file == null) {
                return;
            }
            for(int v1 = 0; v1 < arr_file.length; ++v1) {
                File file1 = arr_file[v1];
                try {
                    long v2 = file1.length();
                    CountingInputStream diskBasedCache$CountingInputStream0 = new CountingInputStream(new BufferedInputStream(this.createInputStream(file1)), v2);
                    try {
                        CacheHeader diskBasedCache$CacheHeader0 = CacheHeader.readHeader(diskBasedCache$CountingInputStream0);
                        diskBasedCache$CacheHeader0.size = v2;
                        this.putEntry(diskBasedCache$CacheHeader0.key, diskBasedCache$CacheHeader0);
                    }
                    finally {
                        diskBasedCache$CountingInputStream0.close();
                    }
                }
                catch(IOException unused_ex) {
                    file1.delete();
                }
            }
        }
    }

    private void initializeIfRootDirectoryDeleted() {
        if(!this.mRootDirectorySupplier.get().exists()) {
            VolleyLog.d("Re-initializing cache after external clearing.", new Object[0]);
            this.mEntries.clear();
            this.mTotalSize = 0L;
            this.initialize();
        }
    }

    @Override  // com.android.volley.Cache
    public void invalidate(String s, boolean z) {
        synchronized(this) {
            Entry cache$Entry0 = this.get(s);
            if(cache$Entry0 != null) {
                cache$Entry0.softTtl = 0L;
                if(z) {
                    cache$Entry0.ttl = 0L;
                }
                this.put(s, cache$Entry0);
            }
        }
    }

    private void pruneIfNeeded() {
        if(this.mTotalSize >= ((long)this.mMaxCacheSizeInBytes)) {
            if(VolleyLog.DEBUG) {
                VolleyLog.v("Pruning old cache entries.", new Object[0]);
            }
            long v = this.mTotalSize;
            long v1 = SystemClock.elapsedRealtime();
            int v2 = 0;
            Iterator iterator0 = this.mEntries.entrySet().iterator();
            while(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                CacheHeader diskBasedCache$CacheHeader0 = (CacheHeader)((Map.Entry)object0).getValue();
                if(this.getFileForKey(diskBasedCache$CacheHeader0.key).delete()) {
                    this.mTotalSize -= diskBasedCache$CacheHeader0.size;
                }
                else {
                    String s = this.getFilenameForKey(diskBasedCache$CacheHeader0.key);
                    VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", new Object[]{diskBasedCache$CacheHeader0.key, s});
                }
                iterator0.remove();
                ++v2;
                if(((float)this.mTotalSize) < ((float)this.mMaxCacheSizeInBytes) * 0.9f) {
                    break;
                }
            }
            if(VolleyLog.DEBUG) {
                VolleyLog.v("pruned %d files, %d bytes, %d ms", new Object[]{v2, ((long)(this.mTotalSize - v)), ((long)(SystemClock.elapsedRealtime() - v1))});
            }
        }
    }

    @Override  // com.android.volley.Cache
    public void put(String s, Entry cache$Entry0) {
        synchronized(this) {
            if(this.mTotalSize + ((long)cache$Entry0.data.length) > ((long)this.mMaxCacheSizeInBytes) && ((float)cache$Entry0.data.length) > ((float)this.mMaxCacheSizeInBytes) * 0.9f) {
                return;
            }
            File file0 = this.getFileForKey(s);
            try {
                BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream(this.createOutputStream(file0));
                CacheHeader diskBasedCache$CacheHeader0 = new CacheHeader(s, cache$Entry0);
                if(diskBasedCache$CacheHeader0.writeHeader(bufferedOutputStream0)) {
                    bufferedOutputStream0.write(cache$Entry0.data);
                    bufferedOutputStream0.close();
                    diskBasedCache$CacheHeader0.size = file0.length();
                    this.putEntry(s, diskBasedCache$CacheHeader0);
                    this.pruneIfNeeded();
                }
                else {
                    bufferedOutputStream0.close();
                    VolleyLog.d("Failed to write header for %s", new Object[]{file0.getAbsolutePath()});
                    goto label_19;
                }
                return;
            }
            catch(IOException unused_ex) {
            }
        label_19:
            if(!file0.delete()) {
                VolleyLog.d("Could not clean up file %s", new Object[]{file0.getAbsolutePath()});
            }
            this.initializeIfRootDirectoryDeleted();
        }
    }

    private void putEntry(String s, CacheHeader diskBasedCache$CacheHeader0) {
        if(this.mEntries.containsKey(s)) {
            CacheHeader diskBasedCache$CacheHeader1 = (CacheHeader)this.mEntries.get(s);
            this.mTotalSize += diskBasedCache$CacheHeader0.size - diskBasedCache$CacheHeader1.size;
        }
        else {
            this.mTotalSize += diskBasedCache$CacheHeader0.size;
        }
        this.mEntries.put(s, diskBasedCache$CacheHeader0);
    }

    private static int read(InputStream inputStream0) throws IOException {
        int v = inputStream0.read();
        if(v == -1) {
            throw new EOFException();
        }
        return v;
    }

    static List readHeaderList(CountingInputStream diskBasedCache$CountingInputStream0) throws IOException {
        int v = DiskBasedCache.readInt(diskBasedCache$CountingInputStream0);
        if(v < 0) {
            throw new IOException("readHeaderList size=" + v);
        }
        List list0 = v == 0 ? Collections.EMPTY_LIST : new ArrayList();
        for(int v1 = 0; v1 < v; ++v1) {
            list0.add(new Header(DiskBasedCache.readString(diskBasedCache$CountingInputStream0).intern(), DiskBasedCache.readString(diskBasedCache$CountingInputStream0).intern()));
        }
        return list0;
    }

    static int readInt(InputStream inputStream0) throws IOException {
        int v = DiskBasedCache.read(inputStream0);
        int v1 = DiskBasedCache.read(inputStream0);
        int v2 = DiskBasedCache.read(inputStream0);
        return DiskBasedCache.read(inputStream0) << 24 | (v | v1 << 8 | v2 << 16);
    }

    static long readLong(InputStream inputStream0) throws IOException {
        return ((long)DiskBasedCache.read(inputStream0)) & 0xFFL | (((long)DiskBasedCache.read(inputStream0)) & 0xFFL) << 8 | (((long)DiskBasedCache.read(inputStream0)) & 0xFFL) << 16 | (((long)DiskBasedCache.read(inputStream0)) & 0xFFL) << 24 | (((long)DiskBasedCache.read(inputStream0)) & 0xFFL) << 0x20 | (((long)DiskBasedCache.read(inputStream0)) & 0xFFL) << 40 | (((long)DiskBasedCache.read(inputStream0)) & 0xFFL) << 0x30 | (0xFFL & ((long)DiskBasedCache.read(inputStream0))) << 56;
    }

    static String readString(CountingInputStream diskBasedCache$CountingInputStream0) throws IOException {
        return new String(DiskBasedCache.streamToBytes(diskBasedCache$CountingInputStream0, DiskBasedCache.readLong(diskBasedCache$CountingInputStream0)), "UTF-8");
    }

    @Override  // com.android.volley.Cache
    public void remove(String s) {
        synchronized(this) {
            boolean z = this.getFileForKey(s).delete();
            this.removeEntry(s);
            if(!z) {
                VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", new Object[]{s, this.getFilenameForKey(s)});
            }
        }
    }

    private void removeEntry(String s) {
        CacheHeader diskBasedCache$CacheHeader0 = (CacheHeader)this.mEntries.remove(s);
        if(diskBasedCache$CacheHeader0 != null) {
            this.mTotalSize -= diskBasedCache$CacheHeader0.size;
        }
    }

    static byte[] streamToBytes(CountingInputStream diskBasedCache$CountingInputStream0, long v) throws IOException {
        long v1 = diskBasedCache$CountingInputStream0.bytesRemaining();
        if(v < 0L || v > v1 || ((long)(((int)v))) != v) {
            throw new IOException("streamToBytes length=" + v + ", maxLength=" + v1);
        }
        byte[] arr_b = new byte[((int)v)];
        new DataInputStream(diskBasedCache$CountingInputStream0).readFully(arr_b);
        return arr_b;
    }

    static void writeHeaderList(List list0, OutputStream outputStream0) throws IOException {
        if(list0 != null) {
            DiskBasedCache.writeInt(outputStream0, list0.size());
            for(Object object0: list0) {
                DiskBasedCache.writeString(outputStream0, ((Header)object0).getName());
                DiskBasedCache.writeString(outputStream0, ((Header)object0).getValue());
            }
            return;
        }
        DiskBasedCache.writeInt(outputStream0, 0);
    }

    static void writeInt(OutputStream outputStream0, int v) throws IOException {
        outputStream0.write(v & 0xFF);
        outputStream0.write(v >> 8 & 0xFF);
        outputStream0.write(v >> 16 & 0xFF);
        outputStream0.write(v >> 24 & 0xFF);
    }

    static void writeLong(OutputStream outputStream0, long v) throws IOException {
        outputStream0.write(((int)(((byte)(((int)v))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 8)))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 16)))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 24)))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 0x20)))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 40)))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 0x30)))))));
        outputStream0.write(((int)(((byte)(((int)(v >>> 56)))))));
    }

    static void writeString(OutputStream outputStream0, String s) throws IOException {
        byte[] arr_b = s.getBytes("UTF-8");
        DiskBasedCache.writeLong(outputStream0, ((long)arr_b.length));
        outputStream0.write(arr_b, 0, arr_b.length);
    }
}

