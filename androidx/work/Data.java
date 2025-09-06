package androidx.work;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 A2\u00020\u0001:\u0002@AB\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0000\u00A2\u0006\u0002\u0010\u0003B\u0019\b\u0010\u0012\u0010\u0010\u0004\u001A\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u0005\u00A2\u0006\u0002\u0010\u0007J\u0013\u0010\u000B\u001A\u00020\f2\b\u0010\u0002\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\u0016\u0010\r\u001A\u00020\f2\u0006\u0010\u000E\u001A\u00020\u00062\u0006\u0010\u000F\u001A\u00020\fJ\u0010\u0010\u0010\u001A\u0004\u0018\u00010\u00112\u0006\u0010\u000E\u001A\u00020\u0006J\u0016\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u000E\u001A\u00020\u00062\u0006\u0010\u000F\u001A\u00020\u0013J\u0010\u0010\u0014\u001A\u0004\u0018\u00010\u00152\u0006\u0010\u000E\u001A\u00020\u0006J\u0016\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u000E\u001A\u00020\u00062\u0006\u0010\u000F\u001A\u00020\u0017J\u0010\u0010\u0018\u001A\u0004\u0018\u00010\u00192\u0006\u0010\u000E\u001A\u00020\u0006J\u0016\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u000E\u001A\u00020\u00062\u0006\u0010\u000F\u001A\u00020\u001BJ\u0010\u0010\u001C\u001A\u0004\u0018\u00010\u001D2\u0006\u0010\u000E\u001A\u00020\u0006J\u0016\u0010\u001E\u001A\u00020\u001F2\u0006\u0010\u000E\u001A\u00020\u00062\u0006\u0010\u000F\u001A\u00020\u001FJ\u0010\u0010 \u001A\u0004\u0018\u00010!2\u0006\u0010\u000E\u001A\u00020\u0006J\u0016\u0010\"\u001A\u00020#2\u0006\u0010\u000E\u001A\u00020\u00062\u0006\u0010\u000F\u001A\u00020#J\u0010\u0010$\u001A\u0004\u0018\u00010%2\u0006\u0010\u000E\u001A\u00020\u0006J*\u0010&\u001A\u0002H\'\"\n\b\u0000\u0010\'\u0018\u0001*\u00020\u00012\u0006\u0010\u000E\u001A\u00020\u00062\u0006\u0010\u000F\u001A\u0002H\'H\u0082\b\u00A2\u0006\u0002\u0010(J\u0010\u0010)\u001A\u0004\u0018\u00010\u00062\u0006\u0010\u000E\u001A\u00020\u0006J\u001B\u0010*\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010+2\u0006\u0010\u000E\u001A\u00020\u0006\u00A2\u0006\u0002\u0010,J}\u0010-\u001A\u0004\u0018\u0001H.\"\n\b\u0000\u0010\'\u0018\u0001*\u00020\u0001\"\u0004\b\u0001\u0010.2\u0006\u0010\u000E\u001A\u00020\u00062Q\u0010/\u001AM\u0012\u0013\u0012\u00110\u001F\u00A2\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012.\u0012,\u0012\u0013\u0012\u00110\u001F\u00A2\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(5\u0012\u0004\u0012\u0002H\'04\u00A2\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(6\u0012\u0004\u0012\u0002H.00H\u0082\b\u00A2\u0006\u0002\u00107J\u001E\u00108\u001A\u00020\f\"\u0006\b\u0000\u0010\'\u0018\u00012\u0006\u0010\u000E\u001A\u00020\u0006H\u0080\b\u00A2\u0006\u0002\b9J\"\u0010:\u001A\u00020\f\"\u0004\b\u0000\u0010\'2\u0006\u0010\u000E\u001A\u00020\u00062\f\u0010;\u001A\b\u0012\u0004\u0012\u0002H\'0<J\b\u0010=\u001A\u00020\u001FH\u0016J\b\u00103\u001A\u00020\u001FH\u0007J\u0006\u0010>\u001A\u00020\u0015J\b\u0010?\u001A\u00020\u0006H\u0016R\u001F\u0010\b\u001A\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00058F\u00A2\u0006\u0006\u001A\u0004\b\t\u0010\nR\u001C\u0010\u0004\u001A\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006B"}, d2 = {"Landroidx/work/Data;", "", "other", "(Landroidx/work/Data;)V", "values", "", "", "(Ljava/util/Map;)V", "keyValueMap", "getKeyValueMap", "()Ljava/util/Map;", "equals", "", "getBoolean", "key", "defaultValue", "getBooleanArray", "", "getByte", "", "getByteArray", "", "getDouble", "", "getDoubleArray", "", "getFloat", "", "getFloatArray", "", "getInt", "", "getIntArray", "", "getLong", "", "getLongArray", "", "getOrDefault", "T", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getString", "getStringArray", "", "(Ljava/lang/String;)[Ljava/lang/String;", "getTypedArray", "TArray", "constructor", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "size", "Lkotlin/Function1;", "index", "init", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "hasKey", "hasKey$work_runtime_release", "hasKeyWithValueOfType", "klass", "Ljava/lang/Class;", "hashCode", "toByteArray", "toString", "Builder", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class Data {
    @Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00A2\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001A\u00020\u0007J\u001A\u0010\b\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\b\u0010\n\u001A\u0004\u0018\u00010\u0001H\u0007J\u000E\u0010\u000B\u001A\u00020\u00002\u0006\u0010\f\u001A\u00020\u0007J\u001C\u0010\u000B\u001A\u00020\u00002\u0014\u0010\u0003\u001A\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rJ\u0016\u0010\u000E\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020\u000FJ\u0016\u0010\u0010\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020\u0011J\u0016\u0010\u0012\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020\u0013J\u0016\u0010\u0014\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020\u0015J\u001A\u0010\u0016\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\b\u0010\n\u001A\u0004\u0018\u00010\u0001H\u0002J\u0016\u0010\u0017\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020\u0018J\u0016\u0010\u0019\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020\u001AJ\u0016\u0010\u001B\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020\u001CJ\u0016\u0010\u001D\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020\u001EJ\u0016\u0010\u001F\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020 J\u0016\u0010!\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020\"J\u0016\u0010#\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020$J\u0016\u0010%\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\n\u001A\u00020&J\u0018\u0010\'\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\b\u0010\n\u001A\u0004\u0018\u00010\u0005J#\u0010(\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u00052\u000E\u0010\n\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050)\u00A2\u0006\u0002\u0010*R\u001C\u0010\u0003\u001A\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006+"}, d2 = {"Landroidx/work/Data$Builder;", "", "()V", "values", "", "", "build", "Landroidx/work/Data;", "put", "key", "value", "putAll", "data", "", "putBoolean", "", "putBooleanArray", "", "putByte", "", "putByteArray", "", "putDirect", "putDouble", "", "putDoubleArray", "", "putFloat", "", "putFloatArray", "", "putInt", "", "putIntArray", "", "putLong", "", "putLongArray", "", "putString", "putStringArray", "", "(Ljava/lang/String;[Ljava/lang/String;)Landroidx/work/Data$Builder;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Builder {
        private final Map values;

        public Builder() {
            this.values = new LinkedHashMap();
        }

        public final Data build() {
            Data data0 = new Data(this.values);
            Data.Companion.toByteArrayInternalV1(data0);
            return data0;
        }

        public final Builder put(String s, Object object0) {
            Intrinsics.checkNotNullParameter(s, "key");
            Map map0 = this.values;
            if(object0 == null) {
                object0 = null;
            }
            else {
                KClass kClass0 = Reflection.getOrCreateKotlinClass(object0.getClass());
                if(!(((((((((((((Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Boolean.TYPE)) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Byte.TYPE))) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Integer.TYPE))) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Long.TYPE))) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Float.TYPE))) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Double.TYPE))) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(String.class))) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Boolean[].class))) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Byte[].class))) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Integer[].class))) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Long[].class))) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Float[].class))) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Double[].class))) ? true : Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(String[].class)))) {
                    if(Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(boolean[].class))) {
                        object0 = Data_Kt.convertPrimitiveArray(((boolean[])object0));
                    }
                    else if(Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(byte[].class))) {
                        object0 = Data_Kt.convertPrimitiveArray(((byte[])object0));
                    }
                    else if(Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(int[].class))) {
                        object0 = Data_Kt.convertPrimitiveArray(((int[])object0));
                    }
                    else if(Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(long[].class))) {
                        object0 = Data_Kt.convertPrimitiveArray(((long[])object0));
                    }
                    else if(Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(float[].class))) {
                        object0 = Data_Kt.convertPrimitiveArray(((float[])object0));
                    }
                    else {
                        if(!Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(double[].class))) {
                            throw new IllegalArgumentException("Key " + s + " has invalid type " + kClass0);
                        }
                        object0 = Data_Kt.convertPrimitiveArray(((double[])object0));
                    }
                }
            }
            map0.put(s, object0);
            return this;
        }

        public final Builder putAll(Data data0) {
            Intrinsics.checkNotNullParameter(data0, "data");
            this.putAll(data0.values);
            return this;
        }

        public final Builder putAll(Map map0) {
            Intrinsics.checkNotNullParameter(map0, "values");
            for(Object object0: map0.entrySet()) {
                this.put(((String)((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue());
            }
            return this;
        }

        public final Builder putBoolean(String s, boolean z) {
            Intrinsics.checkNotNullParameter(s, "key");
            return this.putDirect(s, Boolean.valueOf(z));
        }

        public final Builder putBooleanArray(String s, boolean[] arr_z) {
            Intrinsics.checkNotNullParameter(s, "key");
            Intrinsics.checkNotNullParameter(arr_z, "value");
            Boolean[] arr_boolean = Data_Kt.convertPrimitiveArray(arr_z);
            this.values.put(s, arr_boolean);
            return this;
        }

        public final Builder putByte(String s, byte b) {
            Intrinsics.checkNotNullParameter(s, "key");
            return this.putDirect(s, b);
        }

        public final Builder putByteArray(String s, byte[] arr_b) {
            Intrinsics.checkNotNullParameter(s, "key");
            Intrinsics.checkNotNullParameter(arr_b, "value");
            Byte[] arr_byte = Data_Kt.convertPrimitiveArray(arr_b);
            this.values.put(s, arr_byte);
            return this;
        }

        private final Builder putDirect(String s, Object object0) {
            this.values.put(s, object0);
            return this;
        }

        public final Builder putDouble(String s, double f) {
            Intrinsics.checkNotNullParameter(s, "key");
            return this.putDirect(s, f);
        }

        public final Builder putDoubleArray(String s, double[] arr_f) {
            Intrinsics.checkNotNullParameter(s, "key");
            Intrinsics.checkNotNullParameter(arr_f, "value");
            Double[] arr_double = Data_Kt.convertPrimitiveArray(arr_f);
            this.values.put(s, arr_double);
            return this;
        }

        public final Builder putFloat(String s, float f) {
            Intrinsics.checkNotNullParameter(s, "key");
            return this.putDirect(s, f);
        }

        public final Builder putFloatArray(String s, float[] arr_f) {
            Intrinsics.checkNotNullParameter(s, "key");
            Intrinsics.checkNotNullParameter(arr_f, "value");
            Float[] arr_float = Data_Kt.convertPrimitiveArray(arr_f);
            this.values.put(s, arr_float);
            return this;
        }

        public final Builder putInt(String s, int v) {
            Intrinsics.checkNotNullParameter(s, "key");
            return this.putDirect(s, v);
        }

        public final Builder putIntArray(String s, int[] arr_v) {
            Intrinsics.checkNotNullParameter(s, "key");
            Intrinsics.checkNotNullParameter(arr_v, "value");
            Integer[] arr_integer = Data_Kt.convertPrimitiveArray(arr_v);
            this.values.put(s, arr_integer);
            return this;
        }

        public final Builder putLong(String s, long v) {
            Intrinsics.checkNotNullParameter(s, "key");
            return this.putDirect(s, v);
        }

        public final Builder putLongArray(String s, long[] arr_v) {
            Intrinsics.checkNotNullParameter(s, "key");
            Intrinsics.checkNotNullParameter(arr_v, "value");
            Long[] arr_long = Data_Kt.convertPrimitiveArray(arr_v);
            this.values.put(s, arr_long);
            return this;
        }

        public final Builder putString(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "key");
            return this.putDirect(s, s1);
        }

        public final Builder putStringArray(String s, String[] arr_s) {
            Intrinsics.checkNotNullParameter(s, "key");
            Intrinsics.checkNotNullParameter(arr_s, "value");
            return this.putDirect(s, arr_s);
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0010\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001C\u001A\u00020\u00042\u0006\u0010\u001D\u001A\u00020\u001EH\u0007J\u0010\u0010\u001F\u001A\u00020\u001E2\u0006\u0010 \u001A\u00020\u0004H\u0007J\u0010\u0010!\u001A\u00020\u001E2\u0006\u0010 \u001A\u00020\u0004H\u0007R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00068\u0006X\u0087T¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0013\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0014\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0015\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0016\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0017\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0018\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0019\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u001A\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u001B\u001A\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Landroidx/work/Data$Companion;", "", "()V", "EMPTY", "Landroidx/work/Data;", "MAX_DATA_BYTES", "", "NULL_STRING_V1", "", "STREAM_MAGIC", "", "STREAM_VERSION", "TYPE_BOOLEAN", "", "TYPE_BOOLEAN_ARRAY", "TYPE_BYTE", "TYPE_BYTE_ARRAY", "TYPE_DOUBLE", "TYPE_DOUBLE_ARRAY", "TYPE_FLOAT", "TYPE_FLOAT_ARRAY", "TYPE_INTEGER", "TYPE_INTEGER_ARRAY", "TYPE_LONG", "TYPE_LONG_ARRAY", "TYPE_NULL", "TYPE_STRING", "TYPE_STRING_ARRAY", "fromByteArray", "bytes", "", "toByteArrayInternalV0", "data", "toByteArrayInternalV1", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final Data fromByteArray(byte[] arr_b) {
            Closeable closeable1;
            Closeable closeable0;
            int v;
            ByteArrayInputStream byteArrayInputStream0;
            Intrinsics.checkNotNullParameter(arr_b, "bytes");
            if(arr_b.length > 0x2800) {
                throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
            }
            if(arr_b.length == 0) {
                return Data.EMPTY;
            }
            Map map0 = new LinkedHashMap();
            try {
                byteArrayInputStream0 = new ByteArrayInputStream(arr_b);
                v = 0;
                if(Companion.fromByteArray$isObjectStream(byteArrayInputStream0)) {
                    closeable0 = new ObjectInputStream(byteArrayInputStream0);
                    goto label_9;
                }
                goto label_21;
            }
            catch(IOException iOException0) {
                Logger.get().error("WM-Data", "Error in Data#fromByteArray: ", iOException0);
                return new Data(map0);
            }
            catch(ClassNotFoundException classNotFoundException0) {
                Logger.get().error("WM-Data", "Error in Data#fromByteArray: ", classNotFoundException0);
                return new Data(map0);
            }
            try {
            label_9:
                int v1 = ((ObjectInputStream)closeable0).readInt();
                while(true) {
                    if(v >= v1) {
                        goto label_19;
                    }
                    String s = ((ObjectInputStream)closeable0).readUTF();
                    Intrinsics.checkNotNullExpressionValue(s, "readUTF()");
                    map0.put(s, ((ObjectInputStream)closeable0).readObject());
                    ++v;
                }
            }
            catch(Throwable throwable0) {
                try {
                    CloseableKt.closeFinally(closeable0, throwable0);
                    throw throwable0;
                label_19:
                    CloseableKt.closeFinally(closeable0, null);
                }
                catch(IOException iOException0) {
                    Logger.get().error("WM-Data", "Error in Data#fromByteArray: ", iOException0);
                    return new Data(map0);
                }
                catch(ClassNotFoundException classNotFoundException0) {
                    Logger.get().error("WM-Data", "Error in Data#fromByteArray: ", classNotFoundException0);
                    return new Data(map0);
                }
            }
            return new Data(map0);
            try {
            label_21:
                closeable1 = new DataInputStream(byteArrayInputStream0);
            }
            catch(IOException iOException0) {
                Logger.get().error("WM-Data", "Error in Data#fromByteArray: ", iOException0);
                return new Data(map0);
            }
            catch(ClassNotFoundException classNotFoundException0) {
                Logger.get().error("WM-Data", "Error in Data#fromByteArray: ", classNotFoundException0);
                return new Data(map0);
            }
            try {
                Companion.fromByteArray$readHeader(((DataInputStream)closeable1));
                int v2 = ((DataInputStream)closeable1).readInt();
                while(true) {
                    if(v >= v2) {
                        goto label_34;
                    }
                    Object object0 = Companion.fromByteArray$readValue(((DataInputStream)closeable1), ((DataInputStream)closeable1).readByte());
                    String s1 = ((DataInputStream)closeable1).readUTF();
                    Intrinsics.checkNotNullExpressionValue(s1, "key");
                    map0.put(s1, object0);
                    ++v;
                }
            }
            catch(Throwable throwable1) {
                try {
                    CloseableKt.closeFinally(closeable1, throwable1);
                    throw throwable1;
                label_34:
                    CloseableKt.closeFinally(closeable1, null);
                    return new Data(map0);
                }
                catch(IOException iOException0) {
                }
                catch(ClassNotFoundException classNotFoundException0) {
                    Logger.get().error("WM-Data", "Error in Data#fromByteArray: ", classNotFoundException0);
                    return new Data(map0);
                }
            }
            Logger.get().error("WM-Data", "Error in Data#fromByteArray: ", iOException0);
            return new Data(map0);
        }

        private static final boolean fromByteArray$isObjectStream(ByteArrayInputStream byteArrayInputStream0) {
            byte[] arr_b = new byte[2];
            byteArrayInputStream0.read(arr_b);
            boolean z = arr_b[0] == -84 && arr_b[1] == -19;
            byteArrayInputStream0.reset();
            return z;
        }

        private static final void fromByteArray$readHeader(DataInputStream dataInputStream0) {
            int v = dataInputStream0.readShort();
            if(v != 0xFFFFABEF) {
                throw new IllegalStateException(("Magic number doesn\'t match: " + v).toString());
            }
            int v1 = dataInputStream0.readShort();
            if(v1 != 1) {
                throw new IllegalStateException(("Unsupported version number: " + v1).toString());
            }
        }

        private static final Object fromByteArray$readValue(DataInputStream dataInputStream0, byte b) {
            switch(b) {
                case 0: {
                    return null;
                }
                case 1: {
                    return Boolean.valueOf(dataInputStream0.readBoolean());
                }
                case 2: {
                    return dataInputStream0.readByte();
                }
                case 3: {
                    return dataInputStream0.readInt();
                }
                case 4: {
                    return dataInputStream0.readLong();
                }
                case 5: {
                    return dataInputStream0.readFloat();
                }
                case 6: {
                    return dataInputStream0.readDouble();
                }
                default: {
                    int v = 0;
                    switch(b) {
                        case 7: {
                            return dataInputStream0.readUTF();
                        }
                        case 8: {
                            int v1 = dataInputStream0.readInt();
                            Boolean[] arr_boolean = new Boolean[v1];
                            while(v < v1) {
                                arr_boolean[v] = Boolean.valueOf(dataInputStream0.readBoolean());
                                ++v;
                            }
                            return (Serializable)arr_boolean;
                        }
                        case 9: {
                            int v2 = dataInputStream0.readInt();
                            Byte[] arr_byte = new Byte[v2];
                            while(v < v2) {
                                arr_byte[v] = dataInputStream0.readByte();
                                ++v;
                            }
                            return (Serializable)arr_byte;
                        }
                        case 10: {
                            int v3 = dataInputStream0.readInt();
                            Integer[] arr_integer = new Integer[v3];
                            while(v < v3) {
                                arr_integer[v] = dataInputStream0.readInt();
                                ++v;
                            }
                            return (Serializable)arr_integer;
                        }
                        case 11: {
                            int v4 = dataInputStream0.readInt();
                            Long[] arr_long = new Long[v4];
                            while(v < v4) {
                                arr_long[v] = dataInputStream0.readLong();
                                ++v;
                            }
                            return (Serializable)arr_long;
                        }
                        case 12: {
                            int v5 = dataInputStream0.readInt();
                            Float[] arr_float = new Float[v5];
                            while(v < v5) {
                                arr_float[v] = dataInputStream0.readFloat();
                                ++v;
                            }
                            return (Serializable)arr_float;
                        }
                        case 13: {
                            int v6 = dataInputStream0.readInt();
                            Double[] arr_double = new Double[v6];
                            while(v < v6) {
                                arr_double[v] = dataInputStream0.readDouble();
                                ++v;
                            }
                            return (Serializable)arr_double;
                        }
                        case 14: {
                            int v7 = dataInputStream0.readInt();
                            String[] arr_s = new String[v7];
                            while(v < v7) {
                                String s = dataInputStream0.readUTF();
                                if(Intrinsics.areEqual(s, "androidx.work.Data-95ed6082-b8e9-46e8-a73f-ff56f00f5d9d")) {
                                    s = null;
                                }
                                arr_s[v] = s;
                                ++v;
                            }
                            return (Serializable)arr_s;
                        }
                        default: {
                            throw new IllegalStateException("Unsupported type " + ((int)b));
                        }
                    }
                }
            }
        }

        @Deprecated(message = "This is kept for testing migration", replaceWith = @ReplaceWith(expression = "toByteArrayInternalV1", imports = {}))
        @JvmStatic
        public final byte[] toByteArrayInternalV0(Data data0) {
            ByteArrayOutputStream byteArrayOutputStream0;
            Intrinsics.checkNotNullParameter(data0, "data");
            try {
                Closeable closeable0 = new ByteArrayOutputStream();
                try {
                    byteArrayOutputStream0 = (ByteArrayOutputStream)closeable0;
                    Closeable closeable1 = new ObjectOutputStream(byteArrayOutputStream0);
                    try {
                        ((ObjectOutputStream)closeable1).writeInt(data0.size());
                        for(Object object0: data0.values.entrySet()) {
                            String s = (String)((Map.Entry)object0).getKey();
                            Object object1 = ((Map.Entry)object0).getValue();
                            ((ObjectOutputStream)closeable1).writeUTF(s);
                            ((ObjectOutputStream)closeable1).writeObject(object1);
                        }
                    }
                    catch(Throwable throwable1) {
                        CloseableKt.closeFinally(closeable1, throwable1);
                        throw throwable1;
                    }
                    CloseableKt.closeFinally(closeable1, null);
                }
                catch(Throwable throwable0) {
                    CloseableKt.closeFinally(closeable0, throwable0);
                    throw throwable0;
                }
                CloseableKt.closeFinally(closeable0, null);
                if(byteArrayOutputStream0.size() > 0x2800) {
                    throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
                }
                byte[] arr_b = byteArrayOutputStream0.toByteArray();
                Intrinsics.checkNotNullExpressionValue(arr_b, "{\n                val st…ByteArray()\n            }");
                return arr_b;
            }
            catch(IOException iOException0) {
                Logger.get().error("WM-Data", "Error in Data#toByteArray: ", iOException0);
                return new byte[0];
            }
        }

        @JvmStatic
        public final byte[] toByteArrayInternalV1(Data data0) {
            byte[] arr_b;
            Intrinsics.checkNotNullParameter(data0, "data");
            try {
                ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
                Closeable closeable0 = new DataOutputStream(byteArrayOutputStream0);
                try {
                    Companion.toByteArrayInternalV1$writeHeader(((DataOutputStream)closeable0));
                    ((DataOutputStream)closeable0).writeInt(data0.size());
                    for(Object object0: data0.values.entrySet()) {
                        Companion.toByteArrayInternalV1$writeEntry(((DataOutputStream)closeable0), ((String)((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue());
                    }
                    ((DataOutputStream)closeable0).flush();
                    if(((DataOutputStream)closeable0).size() > 0x2800) {
                        throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
                    }
                    arr_b = byteArrayOutputStream0.toByteArray();
                }
                catch(Throwable throwable0) {
                    CloseableKt.closeFinally(closeable0, throwable0);
                    throw throwable0;
                }
                CloseableKt.closeFinally(closeable0, null);
                Intrinsics.checkNotNullExpressionValue(arr_b, "{\n                ByteAr…          }\n            }");
                return arr_b;
            }
            catch(IOException iOException0) {
                Logger.get().error("WM-Data", "Error in Data#toByteArray: ", iOException0);
                return new byte[0];
            }
        }

        private static final void toByteArrayInternalV1$writeArray(DataOutputStream dataOutputStream0, Object[] arr_object) {
            int v;
            KClass kClass0 = Reflection.getOrCreateKotlinClass(arr_object.getClass());
            if(Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Boolean[].class))) {
                v = 8;
            }
            else {
                if(Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Byte[].class))) {
                    v = 9;
                    goto label_21;
                }
                if(Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Integer[].class))) {
                    v = 10;
                    goto label_21;
                }
                if(Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Long[].class))) {
                    v = 11;
                    goto label_21;
                }
                if(Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Float[].class))) {
                    v = 12;
                    goto label_21;
                }
                if(Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(Double[].class))) {
                    v = 13;
                }
                else if(Intrinsics.areEqual(kClass0, Reflection.getOrCreateKotlinClass(String[].class))) {
                    v = 14;
                }
                else {
                    throw new IllegalArgumentException("Unsupported value type " + Reflection.getOrCreateKotlinClass(arr_object.getClass()).getQualifiedName());
                }
            }
        label_21:
            dataOutputStream0.writeByte(v);
            dataOutputStream0.writeInt(arr_object.length);
            for(int v1 = 0; v1 < arr_object.length; ++v1) {
                Object object0 = arr_object[v1];
                Boolean boolean0 = null;
                switch(v) {
                    case 8: {
                        if(object0 instanceof Boolean) {
                            boolean0 = (Boolean)object0;
                        }
                        dataOutputStream0.writeBoolean((boolean0 == null ? false : boolean0.booleanValue()));
                        break;
                    }
                    case 9: {
                        if(object0 instanceof Byte) {
                            boolean0 = (Byte)object0;
                        }
                        dataOutputStream0.writeByte((boolean0 == null ? 0 : ((byte)(((Byte)boolean0)))));
                        break;
                    }
                    case 10: {
                        if(object0 instanceof Integer) {
                            boolean0 = (Integer)object0;
                        }
                        dataOutputStream0.writeInt((boolean0 == null ? 0 : ((int)(((Integer)boolean0)))));
                        break;
                    }
                    case 11: {
                        if(object0 instanceof Long) {
                            boolean0 = (Long)object0;
                        }
                        dataOutputStream0.writeLong((boolean0 == null ? 0L : ((long)(((Long)boolean0)))));
                        break;
                    }
                    case 12: {
                        if(object0 instanceof Float) {
                            boolean0 = (Float)object0;
                        }
                        dataOutputStream0.writeFloat((boolean0 == null ? 0.0f : ((float)(((Float)boolean0)))));
                        break;
                    }
                    case 13: {
                        if(object0 instanceof Double) {
                            boolean0 = (Double)object0;
                        }
                        dataOutputStream0.writeDouble((boolean0 == null ? 0.0 : ((double)(((Double)boolean0)))));
                        break;
                    }
                    case 14: {
                        if(object0 instanceof String) {
                            boolean0 = (String)object0;
                        }
                        if(boolean0 == null) {
                            boolean0 = "androidx.work.Data-95ed6082-b8e9-46e8-a73f-ff56f00f5d9d";
                        }
                        dataOutputStream0.writeUTF(((String)boolean0));
                    }
                }
            }
        }

        private static final void toByteArrayInternalV1$writeEntry(DataOutputStream dataOutputStream0, String s, Object object0) {
            if(object0 == null) {
                dataOutputStream0.writeByte(0);
            }
            else {
                if(object0 instanceof Boolean) {
                    dataOutputStream0.writeByte(1);
                    dataOutputStream0.writeBoolean(((Boolean)object0).booleanValue());
                    dataOutputStream0.writeUTF(s);
                    return;
                }
                if(object0 instanceof Byte) {
                    dataOutputStream0.writeByte(2);
                    dataOutputStream0.writeByte(((Number)object0).byteValue());
                    dataOutputStream0.writeUTF(s);
                    return;
                }
                if(object0 instanceof Integer) {
                    dataOutputStream0.writeByte(3);
                    dataOutputStream0.writeInt(((Number)object0).intValue());
                    dataOutputStream0.writeUTF(s);
                    return;
                }
                if(object0 instanceof Long) {
                    dataOutputStream0.writeByte(4);
                    dataOutputStream0.writeLong(((Number)object0).longValue());
                    dataOutputStream0.writeUTF(s);
                    return;
                }
                if(object0 instanceof Float) {
                    dataOutputStream0.writeByte(5);
                    dataOutputStream0.writeFloat(((Number)object0).floatValue());
                    dataOutputStream0.writeUTF(s);
                    return;
                }
                if(object0 instanceof Double) {
                    dataOutputStream0.writeByte(6);
                    dataOutputStream0.writeDouble(((Number)object0).doubleValue());
                    dataOutputStream0.writeUTF(s);
                    return;
                }
                if(object0 instanceof String) {
                    dataOutputStream0.writeByte(7);
                    dataOutputStream0.writeUTF(((String)object0));
                }
                else if(object0 instanceof Object[]) {
                    Companion.toByteArrayInternalV1$writeArray(dataOutputStream0, ((Object[])object0));
                }
                else {
                    throw new IllegalArgumentException("Unsupported value type " + Reflection.getOrCreateKotlinClass(object0.getClass()).getSimpleName());
                }
            }
            dataOutputStream0.writeUTF(s);
        }

        private static final void toByteArrayInternalV1$writeHeader(DataOutputStream dataOutputStream0) {
            dataOutputStream0.writeShort(0xFFFFABEF);
            dataOutputStream0.writeShort(1);
        }
    }

    public static final Companion Companion = null;
    public static final Data EMPTY = null;
    public static final int MAX_DATA_BYTES = 0x2800;
    private static final String NULL_STRING_V1 = "androidx.work.Data-95ed6082-b8e9-46e8-a73f-ff56f00f5d9d";
    private static final short STREAM_MAGIC = (short)0xABEF;
    private static final short STREAM_VERSION = 1;
    private static final byte TYPE_BOOLEAN = 1;
    private static final byte TYPE_BOOLEAN_ARRAY = 8;
    private static final byte TYPE_BYTE = 2;
    private static final byte TYPE_BYTE_ARRAY = 9;
    private static final byte TYPE_DOUBLE = 6;
    private static final byte TYPE_DOUBLE_ARRAY = 13;
    private static final byte TYPE_FLOAT = 5;
    private static final byte TYPE_FLOAT_ARRAY = 12;
    private static final byte TYPE_INTEGER = 3;
    private static final byte TYPE_INTEGER_ARRAY = 10;
    private static final byte TYPE_LONG = 4;
    private static final byte TYPE_LONG_ARRAY = 11;
    private static final byte TYPE_NULL = 0;
    private static final byte TYPE_STRING = 7;
    private static final byte TYPE_STRING_ARRAY = 14;
    private final Map values;

    static {
        Data.Companion = new Companion(null);
        Data.EMPTY = new Builder().build();
    }

    public Data(Data data0) {
        Intrinsics.checkNotNullParameter(data0, "other");
        super();
        this.values = new HashMap(data0.values);
    }

    public Data(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "values");
        super();
        this.values = new HashMap(map0);
    }

    @Override
    public boolean equals(Object object0) {
        boolean z;
        if(this == object0) {
            return true;
        }
        if(object0 != null && Intrinsics.areEqual(this.getClass(), object0.getClass())) {
            Set set0 = this.values.keySet();
            if(!Intrinsics.areEqual(set0, ((Data)object0).values.keySet())) {
                return false;
            }
            for(Object object1: set0) {
                Object object2 = this.values.get(((String)object1));
                Object object3 = ((Data)object0).values.get(((String)object1));
                if(object2 == null || object3 == null) {
                    z = object2 == object3;
                }
                else if(!(object2 instanceof Object[]) || !(((Object[])object2) instanceof Object[]) || !(object3 instanceof Object[]) || !(((Object[])object3) instanceof Object[])) {
                    z = Intrinsics.areEqual(object2, object3);
                }
                else {
                    z = ArraysKt.contentDeepEquals(((Object[])object2), ((Object[])object3));
                }
                if(!z) {
                    return false;
                }
                if(false) {
                    break;
                }
            }
            return true;
        }
        return false;
    }

    @JvmStatic
    public static final Data fromByteArray(byte[] arr_b) {
        return Data.Companion.fromByteArray(arr_b);
    }

    public final boolean getBoolean(String s, boolean z) {
        Intrinsics.checkNotNullParameter(s, "key");
        Boolean boolean0 = Boolean.valueOf(z);
        Object object0 = this.values.get(s);
        if(object0 instanceof Boolean) {
            boolean0 = object0;
        }
        return boolean0.booleanValue();
    }

    public final boolean[] getBooleanArray(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        Object object0 = this.values.get(s);
        if(object0 instanceof Object[] && ((Object[])object0) instanceof Object[]) {
            Function1 function10 = new Function1() {
                final Object $value;

                {
                    this.$value = object0;
                    super(1);
                }

                public final Object invoke(int v) {
                    Object object0 = ((Object[])this.$value)[v];
                    if(object0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                    }
                    return (Boolean)object0;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Number)object0).intValue());
                }
            };
            boolean[] arr_z = new boolean[((Object[])object0).length];
            for(int v = 0; v < ((Object[])object0).length; ++v) {
                arr_z[v] = ((Boolean)function10.invoke(v)).booleanValue();
            }
            return arr_z;
        }
        return null;
    }

    public final byte getByte(String s, byte b) {
        Intrinsics.checkNotNullParameter(s, "key");
        Byte byte0 = b;
        Object object0 = this.values.get(s);
        if(object0 instanceof Byte) {
            byte0 = object0;
        }
        return byte0.byteValue();
    }

    public final byte[] getByteArray(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        Object object0 = this.values.get(s);
        if(object0 instanceof Object[] && ((Object[])object0) instanceof Object[]) {
            Function1 function10 = new Function1() {
                final Object $value;

                {
                    this.$value = object0;
                    super(1);
                }

                public final Object invoke(int v) {
                    Object object0 = ((Object[])this.$value)[v];
                    if(object0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Byte");
                    }
                    return (Byte)object0;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Number)object0).intValue());
                }
            };
            byte[] arr_b = new byte[((Object[])object0).length];
            for(int v = 0; v < ((Object[])object0).length; ++v) {
                arr_b[v] = ((Number)function10.invoke(v)).byteValue();
            }
            return arr_b;
        }
        return null;
    }

    public final double getDouble(String s, double f) {
        Intrinsics.checkNotNullParameter(s, "key");
        Double double0 = f;
        Object object0 = this.values.get(s);
        if(object0 instanceof Double) {
            double0 = object0;
        }
        return double0.doubleValue();
    }

    public final double[] getDoubleArray(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        Object object0 = this.values.get(s);
        if(object0 instanceof Object[] && ((Object[])object0) instanceof Object[]) {
            Function1 function10 = new Function1() {
                final Object $value;

                {
                    this.$value = object0;
                    super(1);
                }

                public final Object invoke(int v) {
                    Object object0 = ((Object[])this.$value)[v];
                    if(object0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Double");
                    }
                    return (Double)object0;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Number)object0).intValue());
                }
            };
            double[] arr_f = new double[((Object[])object0).length];
            for(int v = 0; v < ((Object[])object0).length; ++v) {
                arr_f[v] = ((Number)function10.invoke(v)).doubleValue();
            }
            return arr_f;
        }
        return null;
    }

    public final float getFloat(String s, float f) {
        Intrinsics.checkNotNullParameter(s, "key");
        Float float0 = f;
        Object object0 = this.values.get(s);
        if(object0 instanceof Float) {
            float0 = object0;
        }
        return float0.floatValue();
    }

    public final float[] getFloatArray(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        Object object0 = this.values.get(s);
        if(object0 instanceof Object[] && ((Object[])object0) instanceof Object[]) {
            Function1 function10 = new Function1() {
                final Object $value;

                {
                    this.$value = object0;
                    super(1);
                }

                public final Object invoke(int v) {
                    Object object0 = ((Object[])this.$value)[v];
                    if(object0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
                    }
                    return (Float)object0;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Number)object0).intValue());
                }
            };
            float[] arr_f = new float[((Object[])object0).length];
            for(int v = 0; v < ((Object[])object0).length; ++v) {
                arr_f[v] = ((Number)function10.invoke(v)).floatValue();
            }
            return arr_f;
        }
        return null;
    }

    public final int getInt(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "key");
        Integer integer0 = v;
        Object object0 = this.values.get(s);
        if(object0 instanceof Integer) {
            integer0 = object0;
        }
        return integer0.intValue();
    }

    public final int[] getIntArray(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        Object object0 = this.values.get(s);
        if(object0 instanceof Object[] && ((Object[])object0) instanceof Object[]) {
            Function1 function10 = new Function1() {
                final Object $value;

                {
                    this.$value = object0;
                    super(1);
                }

                public final Object invoke(int v) {
                    Object object0 = ((Object[])this.$value)[v];
                    if(object0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                    }
                    return (Integer)object0;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Number)object0).intValue());
                }
            };
            int[] arr_v = new int[((Object[])object0).length];
            for(int v = 0; v < ((Object[])object0).length; ++v) {
                arr_v[v] = ((Number)function10.invoke(v)).intValue();
            }
            return arr_v;
        }
        return null;
    }

    public final Map getKeyValueMap() {
        Map map0 = Collections.unmodifiableMap(this.values);
        Intrinsics.checkNotNullExpressionValue(map0, "unmodifiableMap(values)");
        return map0;
    }

    public final long getLong(String s, long v) {
        Intrinsics.checkNotNullParameter(s, "key");
        Long long0 = v;
        Object object0 = this.values.get(s);
        if(object0 instanceof Long) {
            long0 = object0;
        }
        return long0.longValue();
    }

    public final long[] getLongArray(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        Object object0 = this.values.get(s);
        if(object0 instanceof Object[] && ((Object[])object0) instanceof Object[]) {
            Function1 function10 = new Function1() {
                final Object $value;

                {
                    this.$value = object0;
                    super(1);
                }

                public final Object invoke(int v) {
                    Object object0 = ((Object[])this.$value)[v];
                    if(object0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                    }
                    return (Long)object0;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Number)object0).intValue());
                }
            };
            long[] arr_v = new long[((Object[])object0).length];
            for(int v = 0; v < ((Object[])object0).length; ++v) {
                arr_v[v] = ((Number)function10.invoke(v)).longValue();
            }
            return arr_v;
        }
        return null;
    }

    private final Object getOrDefault(String s, Object object0) {
        Object object1 = this.values.get(s);
        Intrinsics.reifiedOperationMarker(3, "T");
        return object1 instanceof Object ? object1 : object0;
    }

    public final String getString(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        Object object0 = this.values.get(s);
        return object0 instanceof String ? ((String)object0) : null;
    }

    public final String[] getStringArray(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        Object object0 = this.values.get(s);
        if(object0 instanceof Object[] && ((Object[])object0) instanceof Object[]) {
            Function1 function10 = new Function1() {
                final Object $value;

                {
                    this.$value = object0;
                    super(1);
                }

                public final Object invoke(int v) {
                    Object object0 = ((Object[])this.$value)[v];
                    if(object0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    return (String)object0;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Number)object0).intValue());
                }
            };
            String[] arr_s = new String[((Object[])object0).length];
            for(int v = 0; v < ((Object[])object0).length; ++v) {
                arr_s[v] = function10.invoke(v);
            }
            return arr_s;
        }
        return null;
    }

    private final Object getTypedArray(String s, Function2 function20) {
        Object object0 = this.values.get(s);
        if(object0 instanceof Object[] && ((Object[])object0) instanceof Object[]) {
            Intrinsics.needClassReification();
            androidx.work.Data.getTypedArray.1 data$getTypedArray$10 = new Function1() {
                final Object $value;

                {
                    this.$value = object0;
                    super(1);
                }

                public final Object invoke(int v) {
                    Object object0 = ((Object[])this.$value)[v];
                    Intrinsics.reifiedOperationMarker(1, "T");
                    return object0;
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Number)object0).intValue());
                }
            };
            return function20.invoke(((int)((Object[])object0).length), data$getTypedArray$10);
        }
        return null;
    }

    public final boolean hasKey$work_runtime_release(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        Intrinsics.reifiedOperationMarker(4, "T");
        return this.hasKeyWithValueOfType(s, Object.class);
    }

    public final boolean hasKeyWithValueOfType(String s, Class class0) {
        Intrinsics.checkNotNullParameter(s, "key");
        Intrinsics.checkNotNullParameter(class0, "klass");
        Object object0 = this.values.get(s);
        return object0 != null && class0.isAssignableFrom(object0.getClass());
    }

    @Override
    public int hashCode() {
        int v = 0;
        for(Object object0: this.values.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            Object object1 = map$Entry0.getValue();
            v += (object1 instanceof Object[] ? Objects.hashCode(map$Entry0.getKey()) ^ ArraysKt.contentDeepHashCode(((Object[])object1)) : map$Entry0.hashCode());
        }
        return v * 0x1F;
    }

    public final int size() {
        return this.values.size();
    }

    public final byte[] toByteArray() {
        return Data.Companion.toByteArrayInternalV1(this);
    }

    @Deprecated(message = "This is kept for testing migration", replaceWith = @ReplaceWith(expression = "toByteArrayInternalV1", imports = {}))
    @JvmStatic
    public static final byte[] toByteArrayInternalV0(Data data0) {
        return Data.Companion.toByteArrayInternalV0(data0);
    }

    @JvmStatic
    public static final byte[] toByteArrayInternalV1(Data data0) {
        return Data.Companion.toByteArrayInternalV1(data0);
    }

    @Override
    public String toString() {
        String s = "Data {" + CollectionsKt.joinToString$default(this.values.entrySet(), null, null, null, 0, null, androidx.work.Data.toString.1.content.1.INSTANCE, 0x1F, null) + "}";
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;

        @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010&\n\u0002\u0010\u000E\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001A\u00020\u00012\u0014\u0010\u0002\u001A\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<name for destructuring parameter 0>", "", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class androidx.work.Data.toString.1.content.1 extends Lambda implements Function1 {
            public static final androidx.work.Data.toString.1.content.1 INSTANCE;

            static {
                androidx.work.Data.toString.1.content.1.INSTANCE = new androidx.work.Data.toString.1.content.1();
            }

            androidx.work.Data.toString.1.content.1() {
                super(1);
            }

            public final CharSequence invoke(Map.Entry map$Entry0) {
                Intrinsics.checkNotNullParameter(map$Entry0, "<name for destructuring parameter 0>");
                String s = (String)map$Entry0.getKey();
                String s1 = map$Entry0.getValue();
                StringBuilder stringBuilder0 = new StringBuilder();
                stringBuilder0.append(s);
                stringBuilder0.append(" : ");
                if(s1 instanceof Object[]) {
                    s1 = Arrays.toString(((Object[])s1));
                    Intrinsics.checkNotNullExpressionValue(s1, "toString(this)");
                }
                stringBuilder0.append(s1);
                return stringBuilder0.toString();
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Map.Entry)object0));
            }
        }

    }
}

