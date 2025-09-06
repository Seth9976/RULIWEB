package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaRecordComponent;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;

public final class ReflectJavaRecordComponent extends ReflectJavaMember implements JavaRecordComponent {
    private final Object recordComponent;

    public ReflectJavaRecordComponent(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "recordComponent");
        super();
        this.recordComponent = object0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaMember
    public Member getMember() {
        Method method0 = Java16RecordComponentsLoader.INSTANCE.loadGetAccessor(this.recordComponent);
        if(method0 == null) {
            throw new NoSuchMethodError("Can\'t find `getAccessor` method");
        }
        return method0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaRecordComponent
    public JavaType getType() {
        Class class0 = Java16RecordComponentsLoader.INSTANCE.loadGetType(this.recordComponent);
        if(class0 == null) {
            throw new NoSuchMethodError("Can\'t find `getType` method");
        }
        return new ReflectJavaClassifierType(class0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaRecordComponent
    public boolean isVararg() {
        return false;
    }
}

