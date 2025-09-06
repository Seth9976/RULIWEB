package com.squareup.moshi;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;
import javax.annotation.Nullable;

final class ArrayJsonAdapter extends JsonAdapter {
    public static final Factory FACTORY;
    private final JsonAdapter elementAdapter;
    private final Class elementClass;

    static {
        ArrayJsonAdapter.FACTORY = new Factory() {
            @Override  // com.squareup.moshi.JsonAdapter$Factory
            @Nullable
            public JsonAdapter create(Type type0, Set set0, Moshi moshi0) {
                Type type1 = Types.arrayComponentType(type0);
                if(type1 == null) {
                    return null;
                }
                return set0.isEmpty() ? new ArrayJsonAdapter(Types.getRawType(type1), moshi0.adapter(type1)).nullSafe() : null;
            }
        };
    }

    ArrayJsonAdapter(Class class0, JsonAdapter jsonAdapter0) {
        this.elementClass = class0;
        this.elementAdapter = jsonAdapter0;
    }

    @Override  // com.squareup.moshi.JsonAdapter
    public Object fromJson(JsonReader jsonReader0) throws IOException {
        ArrayList arrayList0 = new ArrayList();
        jsonReader0.beginArray();
        while(jsonReader0.hasNext()) {
            arrayList0.add(this.elementAdapter.fromJson(jsonReader0));
        }
        jsonReader0.endArray();
        Object object0 = Array.newInstance(this.elementClass, arrayList0.size());
        for(int v = 0; v < arrayList0.size(); ++v) {
            Array.set(object0, v, arrayList0.get(v));
        }
        return object0;
    }

    @Override  // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
        jsonWriter0.beginArray();
        int v = Array.getLength(object0);
        for(int v1 = 0; v1 < v; ++v1) {
            Object object1 = Array.get(object0, v1);
            this.elementAdapter.toJson(jsonWriter0, object1);
        }
        jsonWriter0.endArray();
    }

    @Override
    public String toString() {
        return this.elementAdapter + ".array()";
    }
}

