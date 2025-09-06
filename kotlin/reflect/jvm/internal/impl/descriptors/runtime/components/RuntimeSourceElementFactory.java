package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaElement;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;

public final class RuntimeSourceElementFactory implements JavaSourceElementFactory {
    public static final class RuntimeSourceElement implements JavaSourceElement {
        private final ReflectJavaElement javaElement;

        public RuntimeSourceElement(ReflectJavaElement reflectJavaElement0) {
            Intrinsics.checkNotNullParameter(reflectJavaElement0, "javaElement");
            super();
            this.javaElement = reflectJavaElement0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.SourceElement
        public SourceFile getContainingFile() {
            Intrinsics.checkNotNullExpressionValue(SourceFile.NO_SOURCE_FILE, "NO_SOURCE_FILE");
            return SourceFile.NO_SOURCE_FILE;
        }

        public ReflectJavaElement getJavaElement() {
            return this.javaElement;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement
        public JavaElement getJavaElement() {
            return this.getJavaElement();
        }

        @Override
        public String toString() {
            return this.getClass().getName() + ": " + this.getJavaElement();
        }
    }

    public static final RuntimeSourceElementFactory INSTANCE;

    static {
        RuntimeSourceElementFactory.INSTANCE = new RuntimeSourceElementFactory();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory
    public JavaSourceElement source(JavaElement javaElement0) {
        Intrinsics.checkNotNullParameter(javaElement0, "javaElement");
        return new RuntimeSourceElement(((ReflectJavaElement)javaElement0));
    }
}

