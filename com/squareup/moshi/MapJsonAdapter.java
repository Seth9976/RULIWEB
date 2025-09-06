package com.squareup.moshi;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

final class MapJsonAdapter extends JsonAdapter {
    public static final Factory FACTORY;
    private final JsonAdapter keyAdapter;
    private final JsonAdapter valueAdapter;

    static {
        MapJsonAdapter.FACTORY = new Factory() {
            @Override  // com.squareup.moshi.JsonAdapter$Factory
            @Nullable
            public JsonAdapter create(Type type0, Set set0, Moshi moshi0) {
                if(!set0.isEmpty()) {
                    return null;
                }
                Class class0 = Types.getRawType(type0);
                if(class0 != Map.class) {
                    return null;
                }
                Type[] arr_type = Types.mapKeyAndValueTypes(type0, class0);
                return new MapJsonAdapter(moshi0, arr_type[0], arr_type[1]).nullSafe();
            }
        };
    }

    MapJsonAdapter(Moshi moshi0, Type type0, Type type1) {
        this.keyAdapter = moshi0.adapter(type0);
        this.valueAdapter = moshi0.adapter(type1);
    }

    @Override  // com.squareup.moshi.JsonAdapter
    public Object fromJson(JsonReader jsonReader0) throws IOException {
        return this.fromJson(jsonReader0);
    }

    public Map fromJson(JsonReader jsonReader0) throws IOException {
        Map map0 = new LinkedHashTreeMap();
        jsonReader0.beginObject();
        while(jsonReader0.hasNext()) {
            jsonReader0.promoteNameToValue();
            Object object0 = this.keyAdapter.fromJson(jsonReader0);
            Object object1 = this.valueAdapter.fromJson(jsonReader0);
            Object object2 = ((LinkedHashTreeMap)map0).put(object0, object1);
            if(object2 != null) {
                throw new JsonDataException("Map key \'" + object0 + "\' has multiple values at path " + "$" + ": " + object2 + " and " + object1);
            }
            if(false) {
                break;
            }
        }
        jsonReader0.endObject();
        return map0;
    }

    @Override  // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
        this.toJson(jsonWriter0, ((Map)object0));
    }

    public void toJson(JsonWriter jsonWriter0, Map map0) throws IOException {
        jsonWriter0.beginObject();
        for(Object object0: map0.entrySet()) {
            if(((Map.Entry)object0).getKey() == null) {
                throw new JsonDataException("Map key is null at $");
            }
            jsonWriter0.promoteValueToName();
            Object object1 = ((Map.Entry)object0).getKey();
            this.keyAdapter.toJson(jsonWriter0, object1);
            Object object2 = ((Map.Entry)object0).getValue();
            this.valueAdapter.toJson(jsonWriter0, object2);
        }
        jsonWriter0.endObject();
    }

    @Override
    public String toString() {
        return "JsonAdapter(" + this.keyAdapter + "=" + this.valueAdapter + ")";
    }
}

