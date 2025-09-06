package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaEnumValueAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class ReflectJavaEnumValueAnnotationArgument extends ReflectJavaAnnotationArgument implements JavaEnumValueAnnotationArgument {
    private final Enum value;

    public ReflectJavaEnumValueAnnotationArgument(Name name0, Enum enum0) {
        Intrinsics.checkNotNullParameter(enum0, "value");
        super(name0, null);
        this.value = enum0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaEnumValueAnnotationArgument
    public Name getEntryName() {
        return Name.identifier(this.value.name());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaEnumValueAnnotationArgument
    public ClassId getEnumClassId() {
        Class class0 = this.value.getClass();
        if(!class0.isEnum()) {
            class0 = class0.getEnclosingClass();
        }
        Intrinsics.checkNotNullExpressionValue(class0, "enumClass");
        return ReflectClassUtilKt.getClassId(class0);
    }
}

