package androidx.work;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00042\b\u0010\u0006\u001A\u0004\u0018\u00010\u00042\n\u0010\u0007\u001A\u0006\u0012\u0002\b\u00030\bH\u0002J\u0018\u0010\t\u001A\u00020\u00042\u0006\u0010\n\u001A\u00020\u00042\u0006\u0010\u000B\u001A\u00020\u0004H\u0002J\u001E\u0010\f\u001A\u00020\u00042\b\u0010\u0006\u001A\u0004\u0018\u00010\u00042\n\u0010\u0007\u001A\u0006\u0012\u0002\b\u00030\bH\u0002J\u0016\u0010\r\u001A\u00020\u000E2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u000E0\u0010H\u0016¨\u0006\u0011"}, d2 = {"Landroidx/work/ArrayCreatingInputMerger;", "Landroidx/work/InputMerger;", "()V", "concatenateArrayAndNonArray", "", "array", "obj", "valueClass", "Ljava/lang/Class;", "concatenateArrays", "array1", "array2", "createArrayFor", "merge", "Landroidx/work/Data;", "inputs", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ArrayCreatingInputMerger extends InputMerger {
    private final Object concatenateArrayAndNonArray(Object object0, Object object1, Class class0) {
        int v = Array.getLength(object0);
        Object object2 = Array.newInstance(class0, v + 1);
        System.arraycopy(object0, 0, object2, 0, v);
        Array.set(object2, v, object1);
        Intrinsics.checkNotNullExpressionValue(object2, "newArray");
        return object2;
    }

    private final Object concatenateArrays(Object object0, Object object1) {
        int v = Array.getLength(object0);
        int v1 = Array.getLength(object1);
        Class class0 = object0.getClass().getComponentType();
        Intrinsics.checkNotNull(class0);
        Object object2 = Array.newInstance(class0, v + v1);
        System.arraycopy(object0, 0, object2, 0, v);
        System.arraycopy(object1, 0, object2, v, v1);
        Intrinsics.checkNotNullExpressionValue(object2, "newArray");
        return object2;
    }

    private final Object createArrayFor(Object object0, Class class0) {
        Object object1 = Array.newInstance(class0, 1);
        Array.set(object1, 0, object0);
        Intrinsics.checkNotNullExpressionValue(object1, "newArray");
        return object1;
    }

    @Override  // androidx.work.InputMerger
    public Data merge(List list0) {
        Class class1;
        Intrinsics.checkNotNullParameter(list0, "inputs");
        Builder data$Builder0 = new Builder();
        Map map0 = new HashMap();
        for(Object object0: list0) {
            for(Object object1: ((Data)object0).getKeyValueMap().entrySet()) {
                String s = (String)((Map.Entry)object1).getKey();
                Object object2 = ((Map.Entry)object1).getValue();
                if(object2 == null) {
                    class1 = String.class;
                }
                else {
                    Class class0 = object2.getClass();
                    if(class0 != null) {
                        class1 = class0;
                    }
                }
                Object object3 = map0.get(s);
                if(object3 != null) {
                    Class class2 = object3.getClass();
                    if(Intrinsics.areEqual(class2, class1)) {
                        Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type kotlin.Any");
                        object2 = this.concatenateArrays(object3, object2);
                    }
                    else {
                        if(!Intrinsics.areEqual(class2.getComponentType(), class1)) {
                            throw new IllegalArgumentException();
                        }
                        object2 = this.concatenateArrayAndNonArray(object3, object2, class1);
                    }
                }
                else if(class1.isArray()) {
                    Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type kotlin.Any");
                }
                else {
                    object2 = this.createArrayFor(object2, class1);
                }
                map0.put(s, object2);
            }
        }
        data$Builder0.putAll(map0);
        return data$Builder0.build();
    }
}

