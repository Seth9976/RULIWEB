package androidx.lifecycle;

import androidx.arch.core.internal.SafeIterableMap;
import java.util.Map.Entry;

public class MediatorLiveData extends MutableLiveData {
    static class Source implements Observer {
        final LiveData mLiveData;
        final Observer mObserver;
        int mVersion;

        Source(LiveData liveData0, Observer observer0) {
            this.mVersion = -1;
            this.mLiveData = liveData0;
            this.mObserver = observer0;
        }

        @Override  // androidx.lifecycle.Observer
        public void onChanged(Object object0) {
            if(this.mVersion != this.mLiveData.getVersion()) {
                this.mVersion = this.mLiveData.getVersion();
                this.mObserver.onChanged(object0);
            }
        }

        void plug() {
            this.mLiveData.observeForever(this);
        }

        void unplug() {
            this.mLiveData.removeObserver(this);
        }
    }

    private SafeIterableMap mSources;

    public MediatorLiveData() {
        this.mSources = new SafeIterableMap();
    }

    public MediatorLiveData(Object object0) {
        super(object0);
        this.mSources = new SafeIterableMap();
    }

    public void addSource(LiveData liveData0, Observer observer0) {
        if(liveData0 == null) {
            throw new NullPointerException("source cannot be null");
        }
        Source mediatorLiveData$Source0 = new Source(liveData0, observer0);
        Source mediatorLiveData$Source1 = (Source)this.mSources.putIfAbsent(liveData0, mediatorLiveData$Source0);
        if(mediatorLiveData$Source1 != null && mediatorLiveData$Source1.mObserver != observer0) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        }
        if(mediatorLiveData$Source1 == null && this.hasActiveObservers()) {
            mediatorLiveData$Source0.plug();
        }
    }

    @Override  // androidx.lifecycle.LiveData
    protected void onActive() {
        for(Object object0: this.mSources) {
            ((Source)((Map.Entry)object0).getValue()).plug();
        }
    }

    @Override  // androidx.lifecycle.LiveData
    protected void onInactive() {
        for(Object object0: this.mSources) {
            ((Source)((Map.Entry)object0).getValue()).unplug();
        }
    }

    public void removeSource(LiveData liveData0) {
        Source mediatorLiveData$Source0 = (Source)this.mSources.remove(liveData0);
        if(mediatorLiveData$Source0 != null) {
            mediatorLiveData$Source0.unplug();
        }
    }
}

