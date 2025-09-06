package com.squareup.moshi;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

abstract class CollectionJsonAdapter extends JsonAdapter {
    public static final Factory FACTORY;
    private final JsonAdapter elementAdapter;

    static {
        CollectionJsonAdapter.FACTORY = new Factory() {
            @Override  // com.squareup.moshi.JsonAdapter$Factory
            @Nullable
            public JsonAdapter create(Type type0, Set set0, Moshi moshi0) {
                Class class0 = Types.getRawType(type0);
                if(!set0.isEmpty()) {
                    return null;
                }
                if(class0 != List.class && class0 != Collection.class) {
                    return class0 == Set.class ? CollectionJsonAdapter.newLinkedHashSetAdapter(type0, moshi0).nullSafe() : null;
                }
                return CollectionJsonAdapter.newArrayListAdapter(type0, moshi0).nullSafe();
            }
        };
    }

    private CollectionJsonAdapter(JsonAdapter jsonAdapter0) {
        this.elementAdapter = jsonAdapter0;
    }

    CollectionJsonAdapter(JsonAdapter jsonAdapter0, com.squareup.moshi.CollectionJsonAdapter.1 collectionJsonAdapter$10) {
        this(jsonAdapter0);
    }

    @Override  // com.squareup.moshi.JsonAdapter
    public Object fromJson(JsonReader jsonReader0) throws IOException {
        return this.fromJson(jsonReader0);
    }

    public Collection fromJson(JsonReader jsonReader0) throws IOException {
        Collection collection0 = this.newCollection();
        jsonReader0.beginArray();
        while(jsonReader0.hasNext()) {
            collection0.add(this.elementAdapter.fromJson(jsonReader0));
        }
        jsonReader0.endArray();
        return collection0;
    }

    static JsonAdapter newArrayListAdapter(Type type0, Moshi moshi0) {
        return new CollectionJsonAdapter(moshi0.adapter(Types.collectionElementType(type0, Collection.class))) {
            {
                super(jsonAdapter0, null);
            }

            @Override  // com.squareup.moshi.CollectionJsonAdapter
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return super.fromJson(jsonReader0);
            }

            @Override  // com.squareup.moshi.CollectionJsonAdapter
            Collection newCollection() {
                return new ArrayList();
            }

            @Override  // com.squareup.moshi.CollectionJsonAdapter
            public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
                super.toJson(jsonWriter0, ((Collection)object0));
            }
        };
    }

    abstract Collection newCollection();

    static JsonAdapter newLinkedHashSetAdapter(Type type0, Moshi moshi0) {
        return new CollectionJsonAdapter(moshi0.adapter(Types.collectionElementType(type0, Collection.class))) {
            {
                super(jsonAdapter0, null);
            }

            @Override  // com.squareup.moshi.CollectionJsonAdapter
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return super.fromJson(jsonReader0);
            }

            @Override  // com.squareup.moshi.CollectionJsonAdapter
            Collection newCollection() {
                return this.newCollection();
            }

            Set newCollection() {
                return new LinkedHashSet();
            }

            @Override  // com.squareup.moshi.CollectionJsonAdapter
            public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
                super.toJson(jsonWriter0, ((Collection)object0));
            }
        };
    }

    @Override  // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
        this.toJson(jsonWriter0, ((Collection)object0));
    }

    public void toJson(JsonWriter jsonWriter0, Collection collection0) throws IOException {
        jsonWriter0.beginArray();
        for(Object object0: collection0) {
            this.elementAdapter.toJson(jsonWriter0, object0);
        }
        jsonWriter0.endArray();
    }

    @Override
    public String toString() {
        return this.elementAdapter + ".collection()";
    }
}

