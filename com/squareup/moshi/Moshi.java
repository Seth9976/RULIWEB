package com.squareup.moshi;

import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

public final class Moshi {
    public static final class Builder {
        final List factories;
        int lastOffset;

        public Builder() {
            this.factories = new ArrayList();
            this.lastOffset = 0;
        }

        public Builder add(Factory jsonAdapter$Factory0) {
            if(jsonAdapter$Factory0 == null) {
                throw new IllegalArgumentException("factory == null");
            }
            int v = this.lastOffset;
            this.lastOffset = v + 1;
            this.factories.add(v, jsonAdapter$Factory0);
            return this;
        }

        public Builder add(Object object0) {
            if(object0 == null) {
                throw new IllegalArgumentException("adapter == null");
            }
            return this.add(AdapterMethodsFactory.get(object0));
        }

        public Builder add(Type type0, JsonAdapter jsonAdapter0) {
            return this.add(Moshi.newAdapterFactory(type0, jsonAdapter0));
        }

        public Builder add(Type type0, Class class0, JsonAdapter jsonAdapter0) {
            return this.add(Moshi.newAdapterFactory(type0, class0, jsonAdapter0));
        }

        public Builder addLast(Factory jsonAdapter$Factory0) {
            if(jsonAdapter$Factory0 == null) {
                throw new IllegalArgumentException("factory == null");
            }
            this.factories.add(jsonAdapter$Factory0);
            return this;
        }

        public Builder addLast(Object object0) {
            if(object0 == null) {
                throw new IllegalArgumentException("adapter == null");
            }
            return this.addLast(AdapterMethodsFactory.get(object0));
        }

        public Builder addLast(Type type0, JsonAdapter jsonAdapter0) {
            return this.addLast(Moshi.newAdapterFactory(type0, jsonAdapter0));
        }

        public Builder addLast(Type type0, Class class0, JsonAdapter jsonAdapter0) {
            return this.addLast(Moshi.newAdapterFactory(type0, class0, jsonAdapter0));
        }

        @CheckReturnValue
        public Moshi build() {
            return new Moshi(this);
        }
    }

    static final class Lookup extends JsonAdapter {
        @Nullable
        JsonAdapter adapter;
        final Object cacheKey;
        @Nullable
        final String fieldName;
        final Type type;

        Lookup(Type type0, @Nullable String s, Object object0) {
            this.type = type0;
            this.fieldName = s;
            this.cacheKey = object0;
        }

        @Override  // com.squareup.moshi.JsonAdapter
        public Object fromJson(JsonReader jsonReader0) throws IOException {
            JsonAdapter jsonAdapter0 = this.adapter;
            if(jsonAdapter0 == null) {
                throw new IllegalStateException("JsonAdapter isn\'t ready");
            }
            return jsonAdapter0.fromJson(jsonReader0);
        }

        @Override  // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
            JsonAdapter jsonAdapter0 = this.adapter;
            if(jsonAdapter0 == null) {
                throw new IllegalStateException("JsonAdapter isn\'t ready");
            }
            jsonAdapter0.toJson(jsonWriter0, object0);
        }

        @Override
        public String toString() {
            return this.adapter == null ? super.toString() : this.adapter.toString();
        }
    }

    final class LookupChain {
        final List callLookups;
        boolean exceptionAnnotated;
        final Deque stack;

        LookupChain() {
            this.callLookups = new ArrayList();
            this.stack = new ArrayDeque();
        }

        void adapterFound(JsonAdapter jsonAdapter0) {
            ((Lookup)this.stack.getLast()).adapter = jsonAdapter0;
        }

        IllegalArgumentException exceptionWithLookupStack(IllegalArgumentException illegalArgumentException0) {
            if(!this.exceptionAnnotated) {
                this.exceptionAnnotated = true;
                if(this.stack.size() != 1 || ((Lookup)this.stack.getFirst()).fieldName != null) {
                    StringBuilder stringBuilder0 = new StringBuilder(illegalArgumentException0.getMessage());
                    Iterator iterator0 = this.stack.descendingIterator();
                    while(iterator0.hasNext()) {
                        Object object0 = iterator0.next();
                        Lookup moshi$Lookup0 = (Lookup)object0;
                        stringBuilder0.append("\nfor ");
                        stringBuilder0.append(moshi$Lookup0.type);
                        if(moshi$Lookup0.fieldName != null) {
                            stringBuilder0.append(' ');
                            stringBuilder0.append(moshi$Lookup0.fieldName);
                        }
                    }
                    return new IllegalArgumentException(stringBuilder0.toString(), illegalArgumentException0);
                }
            }
            return illegalArgumentException0;
        }

        void pop(boolean z) {
            this.stack.removeLast();
            if(this.stack.isEmpty()) {
                Moshi.this.lookupChainThreadLocal.remove();
                if(z) {
                    synchronized(Moshi.this.adapterCache) {
                        int v1 = this.callLookups.size();
                        for(int v2 = 0; v2 < v1; ++v2) {
                            Lookup moshi$Lookup0 = (Lookup)this.callLookups.get(v2);
                            JsonAdapter jsonAdapter0 = (JsonAdapter)Moshi.this.adapterCache.put(moshi$Lookup0.cacheKey, moshi$Lookup0.adapter);
                            if(jsonAdapter0 != null) {
                                moshi$Lookup0.adapter = jsonAdapter0;
                                Moshi.this.adapterCache.put(moshi$Lookup0.cacheKey, jsonAdapter0);
                            }
                        }
                    }
                }
            }
        }

        JsonAdapter push(Type type0, @Nullable String s, Object object0) {
            int v = this.callLookups.size();
            for(int v1 = 0; v1 < v; ++v1) {
                JsonAdapter jsonAdapter0 = (Lookup)this.callLookups.get(v1);
                if(jsonAdapter0.cacheKey.equals(object0)) {
                    this.stack.add(jsonAdapter0);
                    return jsonAdapter0.adapter == null ? jsonAdapter0 : jsonAdapter0.adapter;
                }
            }
            Lookup moshi$Lookup0 = new Lookup(type0, s, object0);
            this.callLookups.add(moshi$Lookup0);
            this.stack.add(moshi$Lookup0);
            return null;
        }
    }

    static final List BUILT_IN_FACTORIES;
    private final Map adapterCache;
    private final List factories;
    private final int lastOffset;
    private final ThreadLocal lookupChainThreadLocal;

    static {
        ArrayList arrayList0 = new ArrayList(5);
        Moshi.BUILT_IN_FACTORIES = arrayList0;
        arrayList0.add(StandardJsonAdapters.FACTORY);
        arrayList0.add(CollectionJsonAdapter.FACTORY);
        arrayList0.add(MapJsonAdapter.FACTORY);
        arrayList0.add(ArrayJsonAdapter.FACTORY);
        arrayList0.add(ClassJsonAdapter.FACTORY);
    }

    Moshi(Builder moshi$Builder0) {
        this.lookupChainThreadLocal = new ThreadLocal();
        this.adapterCache = new LinkedHashMap();
        ArrayList arrayList0 = new ArrayList(moshi$Builder0.factories.size() + Moshi.BUILT_IN_FACTORIES.size());
        arrayList0.addAll(moshi$Builder0.factories);
        arrayList0.addAll(Moshi.BUILT_IN_FACTORIES);
        this.factories = Collections.unmodifiableList(arrayList0);
        this.lastOffset = moshi$Builder0.lastOffset;
    }

    @CheckReturnValue
    public JsonAdapter adapter(Class class0) {
        return this.adapter(class0, Util.NO_ANNOTATIONS);
    }

    @CheckReturnValue
    public JsonAdapter adapter(Type type0) {
        return this.adapter(type0, Util.NO_ANNOTATIONS);
    }

    @CheckReturnValue
    public JsonAdapter adapter(Type type0, Class class0) {
        if(class0 == null) {
            throw new NullPointerException("annotationType == null");
        }
        return this.adapter(type0, Collections.singleton(Types.createJsonQualifierImplementation(class0)));
    }

    @CheckReturnValue
    public JsonAdapter adapter(Type type0, Set set0) {
        return this.adapter(type0, set0, null);
    }

    @CheckReturnValue
    public JsonAdapter adapter(Type type0, Set set0, @Nullable String s) {
        JsonAdapter jsonAdapter2;
        if(type0 == null) {
            throw new NullPointerException("type == null");
        }
        if(set0 == null) {
            throw new NullPointerException("annotations == null");
        }
        Type type1 = Util.removeSubtypeWildcard(Util.canonicalize(type0));
        Object object0 = this.cacheKey(type1, set0);
        synchronized(this.adapterCache) {
            JsonAdapter jsonAdapter0 = (JsonAdapter)this.adapterCache.get(object0);
            if(jsonAdapter0 != null) {
                return jsonAdapter0;
            }
        }
        LookupChain moshi$LookupChain0 = (LookupChain)this.lookupChainThreadLocal.get();
        if(moshi$LookupChain0 == null) {
            moshi$LookupChain0 = new LookupChain(this);
            this.lookupChainThreadLocal.set(moshi$LookupChain0);
        }
        JsonAdapter jsonAdapter1 = moshi$LookupChain0.push(type1, s, object0);
        if(jsonAdapter1 != null) {
            moshi$LookupChain0.pop(false);
            return jsonAdapter1;
        }
        try {
            try {
                int v1 = this.factories.size();
                for(int v2 = 0; true; ++v2) {
                    if(v2 >= v1) {
                        throw new IllegalArgumentException("No JsonAdapter for " + Util.typeAnnotatedWithAnnotations(type1, set0));
                    }
                    jsonAdapter2 = ((Factory)this.factories.get(v2)).create(type1, set0, this);
                    if(jsonAdapter2 != null) {
                        break;
                    }
                }
                moshi$LookupChain0.adapterFound(jsonAdapter2);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                throw moshi$LookupChain0.exceptionWithLookupStack(illegalArgumentException0);
            }
        }
        catch(Throwable throwable0) {
            moshi$LookupChain0.pop(false);
            throw throwable0;
        }
        moshi$LookupChain0.pop(true);
        return jsonAdapter2;
    }

    @CheckReturnValue
    public JsonAdapter adapter(Type type0, Class[] arr_class) {
        if(arr_class.length == 1) {
            return this.adapter(type0, arr_class[0]);
        }
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(arr_class.length);
        for(int v = 0; v < arr_class.length; ++v) {
            linkedHashSet0.add(Types.createJsonQualifierImplementation(arr_class[v]));
        }
        return this.adapter(type0, Collections.unmodifiableSet(linkedHashSet0));
    }

    private Object cacheKey(Type type0, Set set0) {
        return set0.isEmpty() ? type0 : Arrays.asList(new Object[]{type0, set0});
    }

    static Factory newAdapterFactory(Type type0, JsonAdapter jsonAdapter0) {
        if(type0 == null) {
            throw new IllegalArgumentException("type == null");
        }
        if(jsonAdapter0 == null) {
            throw new IllegalArgumentException("jsonAdapter == null");
        }
        return new Factory() {
            // 去混淆评级： 低(20)
            @Override  // com.squareup.moshi.JsonAdapter$Factory
            @Nullable
            public JsonAdapter create(Type type0, Set set0, Moshi moshi0) {
                return !set0.isEmpty() || !Util.typesMatch(type0, type0) ? null : jsonAdapter0;
            }
        };
    }

    static Factory newAdapterFactory(Type type0, Class class0, JsonAdapter jsonAdapter0) {
        if(type0 == null) {
            throw new IllegalArgumentException("type == null");
        }
        if(class0 == null) {
            throw new IllegalArgumentException("annotation == null");
        }
        if(jsonAdapter0 == null) {
            throw new IllegalArgumentException("jsonAdapter == null");
        }
        if(!class0.isAnnotationPresent(JsonQualifier.class)) {
            throw new IllegalArgumentException(class0 + " does not have @JsonQualifier");
        }
        if(class0.getDeclaredMethods().length > 0) {
            throw new IllegalArgumentException("Use JsonAdapter.Factory for annotations with elements");
        }
        return new Factory() {
            // 去混淆评级： 低(20)
            @Override  // com.squareup.moshi.JsonAdapter$Factory
            @Nullable
            public JsonAdapter create(Type type0, Set set0, Moshi moshi0) {
                return !Util.typesMatch(type0, type0) || set0.size() != 1 || !Util.isAnnotationPresent(set0, class0) ? null : jsonAdapter0;
            }
        };
    }

    @CheckReturnValue
    public Builder newBuilder() {
        Builder moshi$Builder0 = new Builder();
        int v = this.lastOffset;
        for(int v1 = 0; v1 < v; ++v1) {
            moshi$Builder0.add(((Factory)this.factories.get(v1)));
        }
        int v2 = this.lastOffset;
        int v3 = this.factories.size();
        int v4 = Moshi.BUILT_IN_FACTORIES.size();
        while(v2 < v3 - v4) {
            moshi$Builder0.addLast(((Factory)this.factories.get(v2)));
            ++v2;
        }
        return moshi$Builder0;
    }

    @CheckReturnValue
    public JsonAdapter nextAdapter(Factory jsonAdapter$Factory0, Type type0, Set set0) {
        if(set0 == null) {
            throw new NullPointerException("annotations == null");
        }
        Type type1 = Util.removeSubtypeWildcard(Util.canonicalize(type0));
        int v = this.factories.indexOf(jsonAdapter$Factory0);
        if(v == -1) {
            throw new IllegalArgumentException("Unable to skip past unknown factory " + jsonAdapter$Factory0);
        }
        int v1 = v + 1;
        int v2 = this.factories.size();
        while(v1 < v2) {
            JsonAdapter jsonAdapter0 = ((Factory)this.factories.get(v1)).create(type1, set0, this);
            if(jsonAdapter0 != null) {
                return jsonAdapter0;
            }
            ++v1;
        }
        throw new IllegalArgumentException("No next JsonAdapter for " + Util.typeAnnotatedWithAnnotations(type1, set0));
    }
}

