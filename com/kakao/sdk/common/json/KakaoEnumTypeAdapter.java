package com.kakao.sdk.common.json;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0019\u0010\u0006\u001A\u0004\u0018\u00018\u00002\b\u0010\u0007\u001A\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\tJ\u001F\u0010\n\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\r2\u0006\u0010\u000E\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000FR\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/kakao/sdk/common/json/KakaoEnumTypeAdapter;", "T", "Lcom/google/gson/TypeAdapter;", "enumClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "read", "in", "Lcom/google/gson/stream/JsonReader;", "(Lcom/google/gson/stream/JsonReader;)Ljava/lang/Object;", "write", "", "out", "Lcom/google/gson/stream/JsonWriter;", "value", "(Lcom/google/gson/stream/JsonWriter;Ljava/lang/Object;)V", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class KakaoEnumTypeAdapter extends TypeAdapter {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[JsonToken.values().length];
            arr_v[JsonToken.NUMBER.ordinal()] = 1;
            arr_v[JsonToken.STRING.ordinal()] = 2;
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private final Class enumClass;

    public KakaoEnumTypeAdapter(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "enumClass");
        super();
        this.enumClass = class0;
    }

    @Override  // com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader0) {
        Pair pair0;
        Object object0 = null;
        if((jsonReader0 == null ? null : jsonReader0.peek()) == JsonToken.NULL) {
            jsonReader0.nextNull();
            return null;
        }
        JsonToken jsonToken0 = jsonReader0 == null ? null : jsonReader0.peek();
        switch((jsonToken0 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[jsonToken0.ordinal()])) {
            case 1: {
                pair0 = new Pair(jsonReader0.nextLong(), null);
                break;
            }
            case 2: {
                pair0 = new Pair(null, jsonReader0.nextString());
                break;
            }
            default: {
                pair0 = new Pair(null, null);
            }
        }
        Object[] arr_object = this.enumClass.getEnumConstants();
        if(arr_object != null) {
            int v = 0;
            while(v < arr_object.length) {
                object0 = arr_object[v];
                try {
                    if(object0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Enum<*>");
                    }
                    String s = ((Enum)object0).name();
                    Field field0 = this.enumClass.getField(s);
                    if(pair0.getFirst() != null) {
                        SerializedName serializedName0 = (SerializedName)field0.getAnnotation(SerializedName.class);
                        if(serializedName0 != null) {
                            Long long0 = (Long)pair0.getFirst();
                            if(long0 != null && ((long)long0) == Long.parseLong(serializedName0.value())) {
                                return object0;
                            }
                        }
                    }
                    else if(pair0.getSecond() != null) {
                        if(!Intrinsics.areEqual(pair0.getSecond(), s)) {
                            SerializedName serializedName1 = (SerializedName)field0.getAnnotation(SerializedName.class);
                            if(serializedName1 != null && Intrinsics.areEqual(pair0.getSecond(), serializedName1.value())) {
                                return object0;
                            }
                            goto label_31;
                        }
                        return object0;
                    }
                label_31:
                    UnknownValue unknownValue0 = (UnknownValue)field0.getAnnotation(UnknownValue.class);
                    ++v;
                    continue;
                }
                catch(NoSuchFieldException noSuchFieldException0) {
                }
                throw new IOException(noSuchFieldException0);
            }
        }
        if(object0 == null) {
            throw new IOException("No matching enum field");
        }
        return object0;
    }

    @Override  // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter0, Object object0) {
        if(object0 == null) {
            if(jsonWriter0 == null) {
                return;
            }
            jsonWriter0.nullValue();
            return;
        }
        boolean z = this.enumClass.isAnnotationPresent(IntEnum.class);
        Object[] arr_object = this.enumClass.getEnumConstants();
        if(arr_object != null) {
            int v = 0;
            while(true) {
                if(v >= arr_object.length) {
                    break;
                }
                Object object1 = arr_object[v];
                try {
                    if(object1 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Enum<*>");
                    }
                    SerializedName serializedName0 = (SerializedName)this.enumClass.getField(((Enum)object1).name()).getAnnotation(SerializedName.class);
                    if(serializedName0 != null && Intrinsics.areEqual(object1, object0)) {
                        if(z) {
                            if(jsonWriter0 == null) {
                                return;
                            }
                            jsonWriter0.value(Integer.parseInt(serializedName0.value()));
                            return;
                        }
                        if(jsonWriter0 == null) {
                            return;
                        }
                        jsonWriter0.value(serializedName0.value());
                        return;
                    }
                    ++v;
                    continue;
                label_23:
                    if(jsonWriter0 == null) {
                        return;
                    }
                    goto label_27;
                }
                catch(NoSuchFieldException noSuchFieldException0) {
                    throw new IOException(noSuchFieldException0);
                }
            }
        }
        goto label_23;
    label_27:
        jsonWriter0.value(object0.toString());
    }
}

