package com.kakao.sdk.common.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.kakao.sdk.common.util.Utility;
import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001A\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007H\u0016J(\u0010\b\u001A\u00020\t2\b\u0010\n\u001A\u0004\u0018\u00010\u000B2\u0014\u0010\f\u001A\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016¨\u0006\r"}, d2 = {"Lcom/kakao/sdk/common/json/MapToQueryAdapter;", "Lcom/google/gson/TypeAdapter;", "", "", "()V", "read", "in", "Lcom/google/gson/stream/JsonReader;", "write", "", "out", "Lcom/google/gson/stream/JsonWriter;", "value", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class MapToQueryAdapter extends TypeAdapter {
    @Override  // com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader0) {
        return this.read(jsonReader0);
    }

    public Map read(JsonReader jsonReader0) {
        String s = null;
        if((jsonReader0 == null ? null : jsonReader0.peek()) == JsonToken.NULL) {
            jsonReader0.nextNull();
            return null;
        }
        if(jsonReader0 != null) {
            s = jsonReader0.nextString();
        }
        return Utility.INSTANCE.parseQuery(s);
    }

    @Override  // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter0, Object object0) {
        this.write(jsonWriter0, ((Map)object0));
    }

    public void write(JsonWriter jsonWriter0, Map map0) {
        if(map0 == null) {
            if(jsonWriter0 == null) {
                return;
            }
            jsonWriter0.nullValue();
            return;
        }
        String s = Utility.INSTANCE.buildQuery(map0);
        if(jsonWriter0 == null) {
            return;
        }
        jsonWriter0.value(s);
    }
}

