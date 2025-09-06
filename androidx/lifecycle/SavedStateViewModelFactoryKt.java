package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\u001A6\u0010\u0004\u001A\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u0002H\u00060\u00022\u0010\u0010\b\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001H\u0000\u001AI\u0010\t\u001A\u0002H\u0006\"\n\b\u0000\u0010\u0006*\u0004\u0018\u00010\n2\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u0002H\u00060\u00022\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u0002H\u00060\u00052\u0012\u0010\f\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u000E0\r\"\u00020\u000EH\u0000¢\u0006\u0002\u0010\u000F\"\u0018\u0010\u0000\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0003\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"ANDROID_VIEWMODEL_SIGNATURE", "", "Ljava/lang/Class;", "VIEWMODEL_SIGNATURE", "findMatchingConstructor", "Ljava/lang/reflect/Constructor;", "T", "modelClass", "signature", "newInstance", "Landroidx/lifecycle/ViewModel;", "constructor", "params", "", "", "(Ljava/lang/Class;Ljava/lang/reflect/Constructor;[Ljava/lang/Object;)Landroidx/lifecycle/ViewModel;", "lifecycle-viewmodel-savedstate_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SavedStateViewModelFactoryKt {
    private static final List ANDROID_VIEWMODEL_SIGNATURE;
    private static final List VIEWMODEL_SIGNATURE;

    static {
        SavedStateViewModelFactoryKt.ANDROID_VIEWMODEL_SIGNATURE = CollectionsKt.listOf(new Class[]{Application.class, SavedStateHandle.class});
        SavedStateViewModelFactoryKt.VIEWMODEL_SIGNATURE = CollectionsKt.listOf(SavedStateHandle.class);
    }

    public static final Constructor findMatchingConstructor(Class class0, List list0) {
        Intrinsics.checkNotNullParameter(class0, "modelClass");
        Intrinsics.checkNotNullParameter(list0, "signature");
        Constructor[] arr_constructor = class0.getConstructors();
        Intrinsics.checkNotNullExpressionValue(arr_constructor, "modelClass.constructors");
        for(int v = 0; v < arr_constructor.length; ++v) {
            Constructor constructor0 = arr_constructor[v];
            Class[] arr_class = constructor0.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(arr_class, "constructor.parameterTypes");
            List list1 = ArraysKt.toList(arr_class);
            if(Intrinsics.areEqual(list0, list1)) {
                Intrinsics.checkNotNull(constructor0, "null cannot be cast to non-null type java.lang.reflect.Constructor<T of androidx.lifecycle.SavedStateViewModelFactoryKt.findMatchingConstructor>");
                return constructor0;
            }
            if(list0.size() == list1.size() && list1.containsAll(list0)) {
                throw new UnsupportedOperationException("Class " + class0.getSimpleName() + " must have parameters in the proper order: " + list0);
            }
        }
        return null;
    }

    public static final ViewModel newInstance(Class class0, Constructor constructor0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(class0, "modelClass");
        Intrinsics.checkNotNullParameter(constructor0, "constructor");
        Intrinsics.checkNotNullParameter(arr_object, "params");
        try {
            return (ViewModel)constructor0.newInstance(Arrays.copyOf(arr_object, arr_object.length));
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException("Failed to access " + class0, illegalAccessException0);
        }
        catch(InstantiationException instantiationException0) {
            throw new RuntimeException("A " + class0 + " cannot be instantiated.", instantiationException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new RuntimeException("An exception happened in constructor of " + class0, invocationTargetException0.getCause());
        }
    }
}

