package com.google.android.datatransport.runtime.dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

public final class SetFactory implements Factory {
    public static final class Builder {
        static final boolean $assertionsDisabled;
        private final List collectionProviders;
        private final List individualProviders;

        static {
        }

        private Builder(int v, int v1) {
            this.individualProviders = DaggerCollections.presizedList(v);
            this.collectionProviders = DaggerCollections.presizedList(v1);
        }

        Builder(int v, int v1, com.google.android.datatransport.runtime.dagger.internal.SetFactory.1 setFactory$10) {
            this(v, v1);
        }

        public Builder addCollectionProvider(Provider provider0) {
            this.collectionProviders.add(provider0);
            return this;
        }

        public Builder addProvider(Provider provider0) {
            this.individualProviders.add(provider0);
            return this;
        }

        public SetFactory build() {
            return new SetFactory(this.individualProviders, this.collectionProviders, null);
        }
    }

    private static final Factory EMPTY_FACTORY;
    private final List collectionProviders;
    private final List individualProviders;

    static {
        SetFactory.EMPTY_FACTORY = InstanceFactory.create(Collections.EMPTY_SET);
    }

    private SetFactory(List list0, List list1) {
        this.individualProviders = list0;
        this.collectionProviders = list1;
    }

    SetFactory(List list0, List list1, com.google.android.datatransport.runtime.dagger.internal.SetFactory.1 setFactory$10) {
        this(list0, list1);
    }

    public static Builder builder(int v, int v1) {
        return new Builder(v, v1, null);
    }

    public static Factory empty() {
        return SetFactory.EMPTY_FACTORY;
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public Set get() {
        int v = this.individualProviders.size();
        ArrayList arrayList0 = new ArrayList(this.collectionProviders.size());
        int v1 = this.collectionProviders.size();
        for(int v3 = 0; v3 < v1; ++v3) {
            Collection collection0 = (Collection)((Provider)this.collectionProviders.get(v3)).get();
            v += collection0.size();
            arrayList0.add(collection0);
        }
        HashSet hashSet0 = DaggerCollections.newHashSetWithExpectedSize(v);
        int v4 = this.individualProviders.size();
        for(int v5 = 0; v5 < v4; ++v5) {
            hashSet0.add(Preconditions.checkNotNull(((Provider)this.individualProviders.get(v5)).get()));
        }
        int v6 = arrayList0.size();
        for(int v2 = 0; v2 < v6; ++v2) {
            for(Object object0: ((Collection)arrayList0.get(v2))) {
                hashSet0.add(Preconditions.checkNotNull(object0));
            }
        }
        return Collections.unmodifiableSet(hashSet0);
    }

    class com.google.android.datatransport.runtime.dagger.internal.SetFactory.1 {
    }

}

