package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public abstract class ViewModel {
    private final Map mBagOfTags;
    private volatile boolean mCleared;
    private final Set mCloseables;

    public ViewModel() {
        this.mBagOfTags = new HashMap();
        this.mCloseables = new LinkedHashSet();
        this.mCleared = false;
    }

    public ViewModel(Closeable[] arr_closeable) {
        this.mBagOfTags = new HashMap();
        LinkedHashSet linkedHashSet0 = new LinkedHashSet();
        this.mCloseables = linkedHashSet0;
        this.mCleared = false;
        linkedHashSet0.addAll(Arrays.asList(arr_closeable));
    }

    public void addCloseable(Closeable closeable0) {
        Set set0 = this.mCloseables;
        if(set0 != null) {
            synchronized(set0) {
                this.mCloseables.add(closeable0);
            }
        }
    }

    final void clear() {
        this.mCleared = true;
        Map map0 = this.mBagOfTags;
        if(map0 != null) {
            synchronized(map0) {
                for(Object object0: this.mBagOfTags.values()) {
                    ViewModel.closeWithRuntimeException(object0);
                }
            }
        }
        Set set0 = this.mCloseables;
        if(set0 != null) {
            synchronized(set0) {
                for(Object object1: this.mCloseables) {
                    ViewModel.closeWithRuntimeException(((Closeable)object1));
                }
            }
        }
        this.onCleared();
    }

    private static void closeWithRuntimeException(Object object0) {
        if(object0 instanceof Closeable) {
            try {
                ((Closeable)object0).close();
            }
            catch(IOException iOException0) {
                throw new RuntimeException(iOException0);
            }
        }
    }

    Object getTag(String s) {
        Map map0 = this.mBagOfTags;
        return map0 == null ? null : this.mBagOfTags.get(s);
    }

    protected void onCleared() {
    }

    Object setTagIfAbsent(String s, Object object0) {
        Object object1;
        synchronized(this.mBagOfTags) {
            object1 = this.mBagOfTags.get(s);
            if(object1 == null) {
                this.mBagOfTags.put(s, object0);
            }
        }
        if(object1 != null) {
            object0 = object1;
        }
        if(this.mCleared) {
            ViewModel.closeWithRuntimeException(object0);
        }
        return object0;
    }
}

