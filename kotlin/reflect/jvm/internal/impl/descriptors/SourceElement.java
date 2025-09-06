package kotlin.reflect.jvm.internal.impl.descriptors;

public interface SourceElement {
    public static final SourceElement NO_SOURCE;

    static {
        SourceElement.NO_SOURCE = new SourceElement() {
            // 去混淆评级： 低(20)
            private static void $$$reportNull$$$0(int v) {
                throw new IllegalStateException("@NotNull method kotlin/reflect/jvm/internal/impl/descriptors/SourceElement$1.getContainingFile must not return null");
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.SourceElement
            public SourceFile getContainingFile() {
                SourceFile sourceFile0 = SourceFile.NO_SOURCE_FILE;
                if(sourceFile0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.SourceElement.1.$$$reportNull$$$0(0);
                }
                return sourceFile0;
            }

            @Override
            public String toString() {
                return "NO_SOURCE";
            }
        };
    }

    SourceFile getContainingFile();
}

