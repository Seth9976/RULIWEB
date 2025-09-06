package com.squareup.moshi;

import com.squareup.moshi.internal.NonNullJsonAdapter;
import com.squareup.moshi.internal.NullSafeJsonAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001A\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u001B\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\u0087\b\u001A \u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0007\u001A#\u0010\u0006\u001A\u00020\u0007\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00072\f\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0087\b¨\u0006\b"}, d2 = {"adapter", "Lcom/squareup/moshi/JsonAdapter;", "T", "Lcom/squareup/moshi/Moshi;", "ktype", "Lkotlin/reflect/KType;", "addAdapter", "Lcom/squareup/moshi/Moshi$Builder;", "moshi"}, k = 2, mv = {1, 4, 0})
public final class _MoshiKotlinExtensionsKt {
    public static final JsonAdapter adapter(Moshi moshi0) {
        Intrinsics.checkNotNullParameter(moshi0, "$this$adapter");
        Intrinsics.reifiedOperationMarker(6, "T");
        return _MoshiKotlinExtensionsKt.adapter(moshi0, null);
    }

    public static final JsonAdapter adapter(Moshi moshi0, KType kType0) {
        Intrinsics.checkNotNullParameter(moshi0, "$this$adapter");
        Intrinsics.checkNotNullParameter(kType0, "ktype");
        JsonAdapter jsonAdapter0 = moshi0.adapter(TypesJVMKt.getJavaType(kType0));
        if(!(jsonAdapter0 instanceof NullSafeJsonAdapter) && !(jsonAdapter0 instanceof NonNullJsonAdapter)) {
            jsonAdapter0 = kType0.isMarkedNullable() ? jsonAdapter0.nullSafe() : jsonAdapter0.nonNull();
            Intrinsics.checkNotNullExpressionValue(jsonAdapter0, "if (ktype.isMarkedNullab…    adapter.nonNull()\n  }");
        }
        return jsonAdapter0;
    }

    public static final Builder addAdapter(Builder moshi$Builder0, JsonAdapter jsonAdapter0) {
        Intrinsics.checkNotNullParameter(moshi$Builder0, "$this$addAdapter");
        Intrinsics.checkNotNullParameter(jsonAdapter0, "adapter");
        Intrinsics.reifiedOperationMarker(6, "T");
        Builder moshi$Builder1 = moshi$Builder0.add(TypesJVMKt.getJavaType(null), jsonAdapter0);
        Intrinsics.checkNotNullExpressionValue(moshi$Builder1, "add(typeOf<T>().javaType, adapter)");
        return moshi$Builder1;
    }
}

