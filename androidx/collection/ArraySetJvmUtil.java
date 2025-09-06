package androidx.collection;

import java.lang.reflect.Array;

class ArraySetJvmUtil {
    static Object[] resizeForToArray(Object[] arr_object, int v) {
        if(arr_object.length < v) {
            return (Object[])Array.newInstance(arr_object.getClass().getComponentType(), v);
        }
        if(arr_object.length > v) {
            arr_object[v] = null;
        }
        return arr_object;
    }
}

