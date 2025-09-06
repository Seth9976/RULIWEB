package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.name.Name;

public abstract class ReflectJavaAnnotationArgument implements JavaAnnotationArgument {
    public static final class Factory {
        private Factory() {
        }

        public Factory(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final ReflectJavaAnnotationArgument create(Object object0, Name name0) {
            Intrinsics.checkNotNullParameter(object0, "value");
            if(ReflectClassUtilKt.isEnumClassOrSpecializedEnumEntryClass(object0.getClass())) {
                return new ReflectJavaEnumValueAnnotationArgument(name0, ((Enum)object0));
            }
            if(object0 instanceof Annotation) {
                return new ReflectJavaAnnotationAsAnnotationArgument(name0, ((Annotation)object0));
            }
            if(object0 instanceof Object[]) {
                return new ReflectJavaArrayAnnotationArgument(name0, ((Object[])object0));
            }
            return object0 instanceof Class ? new ReflectJavaClassObjectAnnotationArgument(name0, ((Class)object0)) : new ReflectJavaLiteralAnnotationArgument(name0, object0);
        }
    }

    public static final Factory Factory;
    private final Name name;

    static {
        ReflectJavaAnnotationArgument.Factory = new Factory(null);
    }

    private ReflectJavaAnnotationArgument(Name name0) {
        this.name = name0;
    }

    public ReflectJavaAnnotationArgument(Name name0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(name0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument
    public Name getName() {
        return this.name;
    }
}

