package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Arrays;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public interface JavaClassFinder {
    public static final class Request {
        private final ClassId classId;
        private final JavaClass outerClass;
        private final byte[] previouslyFoundClassFileContent;

        public Request(ClassId classId0, byte[] arr_b, JavaClass javaClass0) {
            Intrinsics.checkNotNullParameter(classId0, "classId");
            super();
            this.classId = classId0;
            this.previouslyFoundClassFileContent = arr_b;
            this.outerClass = javaClass0;
        }

        public Request(ClassId classId0, byte[] arr_b, JavaClass javaClass0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
            if((v & 2) != 0) {
                arr_b = null;
            }
            if((v & 4) != 0) {
                javaClass0 = null;
            }
            this(classId0, arr_b, javaClass0);
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof Request)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.classId, ((Request)object0).classId)) {
                return false;
            }
            return Intrinsics.areEqual(this.previouslyFoundClassFileContent, ((Request)object0).previouslyFoundClassFileContent) ? Intrinsics.areEqual(this.outerClass, ((Request)object0).outerClass) : false;
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        @Override
        public int hashCode() {
            int v = this.classId.hashCode();
            int v1 = 0;
            int v2 = this.previouslyFoundClassFileContent == null ? 0 : Arrays.hashCode(this.previouslyFoundClassFileContent);
            JavaClass javaClass0 = this.outerClass;
            if(javaClass0 != null) {
                v1 = javaClass0.hashCode();
            }
            return (v * 0x1F + v2) * 0x1F + v1;
        }

        @Override
        public String toString() {
            return "Request(classId=" + this.classId + ", previouslyFoundClassFileContent=" + Arrays.toString(this.previouslyFoundClassFileContent) + ", outerClass=" + this.outerClass + ')';
        }
    }

    JavaClass findClass(Request arg1);

    JavaPackage findPackage(FqName arg1, boolean arg2);

    Set knownClassNamesInPackage(FqName arg1);
}

