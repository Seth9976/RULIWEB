package com.kakao.sdk.common.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.util.Date;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0004\u001A\u0004\u0018\u00010\u00022\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0016J\u001C\u0010\u0007\u001A\u00020\b2\b\u0010\t\u001A\u0004\u0018\u00010\n2\b\u0010\u000B\u001A\u0004\u0018\u00010\u0002H\u0016¨\u0006\f"}, d2 = {"Lcom/kakao/sdk/common/json/KakaoIntDateTypeAdapter;", "Lcom/google/gson/TypeAdapter;", "Ljava/util/Date;", "()V", "read", "in", "Lcom/google/gson/stream/JsonReader;", "write", "", "out", "Lcom/google/gson/stream/JsonWriter;", "value", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class KakaoIntDateTypeAdapter extends TypeAdapter {
    @Override  // com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader0) {
        return this.read(jsonReader0);
    }

    public Date read(JsonReader jsonReader0) {
        if((jsonReader0 == null ? null : jsonReader0.peek()) == JsonToken.NULL) {
            jsonReader0.nextNull();
            return null;
        }
        return (jsonReader0 == null ? null : jsonReader0.peek()) == JsonToken.NUMBER ? new Date(jsonReader0.nextLong() * 1000L) : null;
    }

    @Override  // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter0, Object object0) {
        this.write(jsonWriter0, ((Date)object0));
    }

    public void write(JsonWriter jsonWriter0, Date date0) {
        if(date0 == null) {
            if(jsonWriter0 == null) {
                return;
            }
            jsonWriter0.nullValue();
            return;
        }
        if(jsonWriter0 == null) {
            return;
        }
        jsonWriter0.value(date0.getTime() / 1000L);
    }
}

