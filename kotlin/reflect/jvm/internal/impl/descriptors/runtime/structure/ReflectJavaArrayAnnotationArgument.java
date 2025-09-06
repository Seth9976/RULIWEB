package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class ReflectJavaArrayAnnotationArgument extends ReflectJavaAnnotationArgument implements JavaArrayAnnotationArgument {
    private final Object[] values;

    public ReflectJavaArrayAnnotationArgument(Name name0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "values");
        super(name0, null);
        this.values = arr_object;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayAnnotationArgument
    public List getElements() {
        Object[] arr_object = this.values;
        ArrayList arrayList0 = new ArrayList(arr_object.length);
        for(int v = 0; v < arr_object.length; ++v) {
            Object object0 = arr_object[v];
            Intrinsics.checkNotNull(object0);
            arrayList0.add(ReflectJavaAnnotationArgument.Factory.create(object0, null));
        }
        return arrayList0;
    }
}

