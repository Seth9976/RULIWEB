package androidx.collection;

import androidx.collection.internal.Lock;
import androidx.collection.internal.LruHashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import jeb.synthetic.FIN;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u000E\n\u0002\u0010%\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B\u000F\u0012\b\b\u0001\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006J\u0017\u0010\u0011\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0012\u001A\u00028\u0000H\u0014\u00A2\u0006\u0002\u0010\u0013J\u0006\u0010\u0007\u001A\u00020\u0005J/\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0012\u001A\u00028\u00002\u0006\u0010\u0018\u001A\u00028\u00012\b\u0010\u0019\u001A\u0004\u0018\u00018\u0001H\u0014\u00A2\u0006\u0002\u0010\u001AJ\u0006\u0010\u001B\u001A\u00020\u0015J\u0006\u0010\b\u001A\u00020\u0005J\u0018\u0010\u001C\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0012\u001A\u00028\u0000H\u0086\u0002\u00A2\u0006\u0002\u0010\u0013J\u0006\u0010\t\u001A\u00020\u0005J\u0006\u0010\u0004\u001A\u00020\u0005J\u0006\u0010\u000E\u001A\u00020\u0005J\u001D\u0010\u001D\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0012\u001A\u00028\u00002\u0006\u0010\u001E\u001A\u00028\u0001\u00A2\u0006\u0002\u0010\u001FJ\u0006\u0010\u000F\u001A\u00020\u0005J\u0015\u0010 \u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0012\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\u0013J\u0012\u0010!\u001A\u00020\u00152\b\b\u0001\u0010\u0004\u001A\u00020\u0005H\u0016J\u001D\u0010\"\u001A\u00020\u00052\u0006\u0010\u0012\u001A\u00028\u00002\u0006\u0010\u001E\u001A\u00028\u0001H\u0002\u00A2\u0006\u0002\u0010#J\u0006\u0010\u0010\u001A\u00020\u0005J\u001D\u0010$\u001A\u00020\u00052\u0006\u0010\u0012\u001A\u00028\u00002\u0006\u0010\u001E\u001A\u00028\u0001H\u0014\u00A2\u0006\u0002\u0010#J\u0012\u0010%\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010&J\b\u0010\'\u001A\u00020(H\u0016J\u0010\u0010)\u001A\u00020\u00152\u0006\u0010\u0004\u001A\u00020\u0005H\u0016R\u000E\u0010\u0007\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\f\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\rX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u0006*"}, d2 = {"Landroidx/collection/LruCache;", "K", "", "V", "maxSize", "", "(I)V", "createCount", "evictionCount", "hitCount", "lock", "Landroidx/collection/internal/Lock;", "map", "Landroidx/collection/internal/LruHashMap;", "missCount", "putCount", "size", "create", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "entryRemoved", "", "evicted", "", "oldValue", "newValue", "(ZLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "evictAll", "get", "put", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove", "resize", "safeSizeOf", "(Ljava/lang/Object;Ljava/lang/Object;)I", "sizeOf", "snapshot", "", "toString", "", "trimToSize", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class LruCache {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final Lock lock;
    private final LruHashMap map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int v) {
        this.maxSize = v;
        if(v <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.map = new LruHashMap(0, 0.75f);
        this.lock = new Lock();
    }

    protected Object create(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "key");
        return null;
    }

    public final int createCount() {
        synchronized(this.lock) {
        }
        return this.createCount;
    }

    protected void entryRemoved(boolean z, Object object0, Object object1, Object object2) {
        Intrinsics.checkNotNullParameter(object0, "key");
        Intrinsics.checkNotNullParameter(object1, "oldValue");
    }

    public final void evictAll() {
        this.trimToSize(-1);
    }

    public final int evictionCount() {
        synchronized(this.lock) {
        }
        return this.evictionCount;
    }

    public final Object get(Object object0) {
        Object object3;
        Intrinsics.checkNotNullParameter(object0, "key");
        synchronized(this.lock) {
            Object object1 = this.map.get(object0);
            if(object1 != null) {
                ++this.hitCount;
                return object1;
            }
            ++this.missCount;
        }
        Object object2 = this.create(object0);
        if(object2 == null) {
            return null;
        }
        synchronized(this.lock) {
            ++this.createCount;
            object3 = this.map.put(object0, object2);
            if(object3 == null) {
                this.size += this.safeSizeOf(object0, object2);
            }
            else {
                this.map.put(object0, object3);
            }
        }
        if(object3 != null) {
            this.entryRemoved(false, object0, object2, object3);
            return object3;
        }
        this.trimToSize(this.maxSize);
        return object2;
    }

    public final int hitCount() {
        synchronized(this.lock) {
        }
        return this.hitCount;
    }

    public final int maxSize() {
        synchronized(this.lock) {
        }
        return this.maxSize;
    }

    public final int missCount() {
        synchronized(this.lock) {
        }
        return this.missCount;
    }

    public final Object put(Object object0, Object object1) {
        Object object2;
        Intrinsics.checkNotNullParameter(object0, "key");
        Intrinsics.checkNotNullParameter(object1, "value");
        synchronized(this.lock) {
            ++this.putCount;
            this.size += this.safeSizeOf(object0, object1);
            object2 = this.map.put(object0, object1);
            if(object2 != null) {
                this.size -= this.safeSizeOf(object0, object2);
            }
        }
        if(object2 != null) {
            this.entryRemoved(false, object0, object2, object1);
        }
        this.trimToSize(this.maxSize);
        return object2;
    }

    public final int putCount() {
        synchronized(this.lock) {
        }
        return this.putCount;
    }

    public final Object remove(Object object0) {
        Object object1;
        Intrinsics.checkNotNullParameter(object0, "key");
        synchronized(this.lock) {
            object1 = this.map.remove(object0);
            if(object1 != null) {
                this.size -= this.safeSizeOf(object0, object1);
            }
        }
        if(object1 != null) {
            this.entryRemoved(false, object0, object1, null);
        }
        return object1;
    }

    public void resize(int v) {
        if(v <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized(this.lock) {
            this.maxSize = v;
        }
        this.trimToSize(v);
    }

    private final int safeSizeOf(Object object0, Object object1) {
        int v = this.sizeOf(object0, object1);
        if(v < 0) {
            throw new IllegalStateException(("Negative size: " + object0 + '=' + object1).toString());
        }
        return v;
    }

    public final int size() {
        synchronized(this.lock) {
        }
        return this.size;
    }

    protected int sizeOf(Object object0, Object object1) {
        Intrinsics.checkNotNullParameter(object0, "key");
        Intrinsics.checkNotNullParameter(object1, "value");
        return 1;
    }

    public final Map snapshot() {
        LinkedHashMap linkedHashMap0 = new LinkedHashMap();
        synchronized(this.lock) {
            for(Object object0: this.map.getEntries()) {
                linkedHashMap0.put(((Map.Entry)object0).getKey(), ((Map.Entry)object0).getValue());
            }
            return linkedHashMap0;
        }
    }

    @Override
    public String toString() {
        synchronized(this.lock) {
            int v1 = this.missCount + this.hitCount;
            int v2 = v1 == 0 ? 0 : this.hitCount * 100 / v1;
            return "LruCache[maxSize=" + this.maxSize + ",hits=" + this.hitCount + ",misses=" + this.missCount + ",hitRate=" + v2 + "%]";
        }
    }

    public void trimToSize(int v) {
        int v1;
        while(true) {
            Lock lock0 = this.lock;
            __monitor_enter(lock0);
            v1 = FIN.finallyOpen$NT();
            if(this.size < 0 || this.map.isEmpty() && this.size != 0) {
                break;
            }
            if(this.size > v && !this.map.isEmpty()) {
                Map.Entry map$Entry0 = (Map.Entry)CollectionsKt.firstOrNull(this.map.getEntries());
                if(map$Entry0 == null) {
                    FIN.finallyExec$NT(v1);
                    return;
                }
                Object object0 = map$Entry0.getKey();
                Object object1 = map$Entry0.getValue();
                this.map.remove(object0);
                this.size -= this.safeSizeOf(object0, object1);
                ++this.evictionCount;
                FIN.finallyCodeBegin$NT(v1);
                __monitor_exit(lock0);
                FIN.finallyCodeEnd$NT(v1);
                this.entryRemoved(true, object0, object1, null);
                continue;
            }
            FIN.finallyExec$NT(v1);
            return;
        }
        FIN.finallyExec$NT(v1);
        throw new IllegalStateException("LruCache.sizeOf() is reporting inconsistent results!");
    }
}

