package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.KotlinMetadataFinder;

public interface KotlinClassFinder extends KotlinMetadataFinder {
    public static abstract class Result {
        public static final class ClassFileContent extends Result {
            private final byte[] content;

            public final byte[] getContent() {
                return this.content;
            }
        }

        public static final class KotlinClass extends Result {
            private final byte[] byteContent;
            private final KotlinJvmBinaryClass kotlinJvmBinaryClass;

            public KotlinClass(KotlinJvmBinaryClass kotlinJvmBinaryClass0, byte[] arr_b) {
                Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass0, "kotlinJvmBinaryClass");
                super(null);
                this.kotlinJvmBinaryClass = kotlinJvmBinaryClass0;
                this.byteContent = arr_b;
            }

            public KotlinClass(KotlinJvmBinaryClass kotlinJvmBinaryClass0, byte[] arr_b, int v, DefaultConstructorMarker defaultConstructorMarker0) {
                if((v & 2) != 0) {
                    arr_b = null;
                }
                this(kotlinJvmBinaryClass0, arr_b);
            }

            public final KotlinJvmBinaryClass getKotlinJvmBinaryClass() {
                return this.kotlinJvmBinaryClass;
            }
        }

        private Result() {
        }

        public Result(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final KotlinJvmBinaryClass toKotlinJvmBinaryClass() {
            KotlinClass kotlinClassFinder$Result$KotlinClass0 = this instanceof KotlinClass ? ((KotlinClass)this) : null;
            return kotlinClassFinder$Result$KotlinClass0 == null ? null : kotlinClassFinder$Result$KotlinClass0.getKotlinJvmBinaryClass();
        }
    }

    Result findKotlinClassOrContent(JavaClass arg1, JvmMetadataVersion arg2);

    Result findKotlinClassOrContent(ClassId arg1, JvmMetadataVersion arg2);
}

