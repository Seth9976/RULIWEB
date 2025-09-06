package com.google.android.datatransport.cct.internal;

import android.util.JsonReader;
import android.util.JsonToken;
import java.io.IOException;
import java.io.Reader;

public abstract class LogResponse {
    private static final String LOG_TAG = "LogResponseInternal";

    static LogResponse create(long v) {
        return new AutoValue_LogResponse(v);
    }

    public static LogResponse fromJson(Reader reader0) throws IOException {
        try(JsonReader jsonReader0 = new JsonReader(reader0)) {
            jsonReader0.beginObject();
            while(true) {
                if(!jsonReader0.hasNext()) {
                    break;
                }
                if(jsonReader0.nextName().equals("nextRequestWaitMillis")) {
                    return jsonReader0.peek() == JsonToken.STRING ? LogResponse.create(Long.parseLong(jsonReader0.nextString())) : LogResponse.create(jsonReader0.nextLong());
                }
                jsonReader0.skipValue();
            }
            throw new IOException("Response is missing nextRequestWaitMillis field.");
        }
    }

    public abstract long getNextRequestWaitMillis();
}

