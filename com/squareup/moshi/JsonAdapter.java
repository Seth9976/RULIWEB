package com.squareup.moshi;

import com.squareup.moshi.internal.NonNullJsonAdapter;
import com.squareup.moshi.internal.NullSafeJsonAdapter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;

public abstract class JsonAdapter {
    public interface Factory {
        @CheckReturnValue
        @Nullable
        JsonAdapter create(Type arg1, Set arg2, Moshi arg3);
    }

    @CheckReturnValue
    public final JsonAdapter failOnUnknown() {
        return new JsonAdapter() {
            @Override  // com.squareup.moshi.JsonAdapter
            @Nullable
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                jsonReader0.setFailOnUnknown(true);
                try {
                    return this.fromJson(jsonReader0);
                }
                finally {
                    jsonReader0.setFailOnUnknown(jsonReader0.failOnUnknown());
                }
            }

            @Override  // com.squareup.moshi.JsonAdapter
            boolean isLenient() {
                return this.isLenient();
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, @Nullable Object object0) throws IOException {
                this.toJson(jsonWriter0, object0);
            }

            @Override
            public String toString() {
                return this + ".failOnUnknown()";
            }
        };
    }

    @CheckReturnValue
    @Nullable
    public abstract Object fromJson(JsonReader arg1) throws IOException;

    @CheckReturnValue
    @Nullable
    public final Object fromJson(String s) throws IOException {
        JsonReader jsonReader0 = JsonReader.of(new Buffer().writeUtf8(s));
        Object object0 = this.fromJson(jsonReader0);
        if(!this.isLenient() && jsonReader0.peek() != Token.END_DOCUMENT) {
            throw new JsonDataException("JSON document was not fully consumed.");
        }
        return object0;
    }

    @CheckReturnValue
    @Nullable
    public final Object fromJson(BufferedSource bufferedSource0) throws IOException {
        return this.fromJson(JsonReader.of(bufferedSource0));
    }

    @CheckReturnValue
    @Nullable
    public final Object fromJsonValue(@Nullable Object object0) {
        JsonValueReader jsonValueReader0 = new JsonValueReader(object0);
        try {
            return this.fromJson(jsonValueReader0);
        }
        catch(IOException iOException0) {
            throw new AssertionError(iOException0);
        }
    }

    @CheckReturnValue
    public JsonAdapter indent(String s) {
        if(s == null) {
            throw new NullPointerException("indent == null");
        }
        return new JsonAdapter() {
            @Override  // com.squareup.moshi.JsonAdapter
            @Nullable
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return this.fromJson(jsonReader0);
            }

            @Override  // com.squareup.moshi.JsonAdapter
            boolean isLenient() {
                return this.isLenient();
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, @Nullable Object object0) throws IOException {
                jsonWriter0.setIndent(s);
                try {
                    this.toJson(jsonWriter0, object0);
                }
                finally {
                    jsonWriter0.setIndent("");
                }
            }

            @Override
            public String toString() {
                return this + ".indent(\"" + s + "\")";
            }
        };
    }

    boolean isLenient() {
        return false;
    }

    @CheckReturnValue
    public final JsonAdapter lenient() {
        return new JsonAdapter() {
            @Override  // com.squareup.moshi.JsonAdapter
            @Nullable
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                jsonReader0.setLenient(true);
                try {
                    return this.fromJson(jsonReader0);
                }
                finally {
                    jsonReader0.setLenient(jsonReader0.isLenient());
                }
            }

            @Override  // com.squareup.moshi.JsonAdapter
            boolean isLenient() {
                return true;
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, @Nullable Object object0) throws IOException {
                jsonWriter0.setLenient(true);
                try {
                    this.toJson(jsonWriter0, object0);
                }
                finally {
                    jsonWriter0.setLenient(jsonWriter0.isLenient());
                }
            }

            @Override
            public String toString() {
                return this + ".lenient()";
            }
        };
    }

    @CheckReturnValue
    public final JsonAdapter nonNull() {
        return this instanceof NonNullJsonAdapter ? this : new NonNullJsonAdapter(this);
    }

    @CheckReturnValue
    public final JsonAdapter nullSafe() {
        return this instanceof NullSafeJsonAdapter ? this : new NullSafeJsonAdapter(this);
    }

    @CheckReturnValue
    public final JsonAdapter serializeNulls() {
        return new JsonAdapter() {
            @Override  // com.squareup.moshi.JsonAdapter
            @Nullable
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return this.fromJson(jsonReader0);
            }

            @Override  // com.squareup.moshi.JsonAdapter
            boolean isLenient() {
                return this.isLenient();
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, @Nullable Object object0) throws IOException {
                jsonWriter0.setSerializeNulls(true);
                try {
                    this.toJson(jsonWriter0, object0);
                }
                finally {
                    jsonWriter0.setSerializeNulls(jsonWriter0.getSerializeNulls());
                }
            }

            @Override
            public String toString() {
                return this + ".serializeNulls()";
            }
        };
    }

    @CheckReturnValue
    public final String toJson(@Nullable Object object0) {
        Buffer buffer0 = new Buffer();
        try {
            this.toJson(buffer0, object0);
            return "";
        }
        catch(IOException iOException0) {
            throw new AssertionError(iOException0);
        }
    }

    public abstract void toJson(JsonWriter arg1, @Nullable Object arg2) throws IOException;

    public final void toJson(BufferedSink bufferedSink0, @Nullable Object object0) throws IOException {
        this.toJson(JsonWriter.of(bufferedSink0), object0);
    }

    @CheckReturnValue
    @Nullable
    public final Object toJsonValue(@Nullable Object object0) {
        JsonValueWriter jsonValueWriter0 = new JsonValueWriter();
        try {
            this.toJson(jsonValueWriter0, object0);
            return jsonValueWriter0.root();
        }
        catch(IOException iOException0) {
            throw new AssertionError(iOException0);
        }
    }
}

