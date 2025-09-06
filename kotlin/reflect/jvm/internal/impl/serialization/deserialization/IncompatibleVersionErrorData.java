package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

public final class IncompatibleVersionErrorData {
    private final Object actualVersion;
    private final ClassId classId;
    private final Object compilerVersion;
    private final Object expectedVersion;
    private final String filePath;
    private final Object languageVersion;

    public IncompatibleVersionErrorData(Object object0, Object object1, Object object2, Object object3, String s, ClassId classId0) {
        Intrinsics.checkNotNullParameter(s, "filePath");
        Intrinsics.checkNotNullParameter(classId0, "classId");
        super();
        this.actualVersion = object0;
        this.compilerVersion = object1;
        this.languageVersion = object2;
        this.expectedVersion = object3;
        this.filePath = s;
        this.classId = classId0;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof IncompatibleVersionErrorData)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.actualVersion, ((IncompatibleVersionErrorData)object0).actualVersion)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.compilerVersion, ((IncompatibleVersionErrorData)object0).compilerVersion)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.languageVersion, ((IncompatibleVersionErrorData)object0).languageVersion)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.expectedVersion, ((IncompatibleVersionErrorData)object0).expectedVersion)) {
            return false;
        }
        return Intrinsics.areEqual(this.filePath, ((IncompatibleVersionErrorData)object0).filePath) ? Intrinsics.areEqual(this.classId, ((IncompatibleVersionErrorData)object0).classId) : false;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.actualVersion == null ? 0 : this.actualVersion.hashCode();
        int v2 = this.compilerVersion == null ? 0 : this.compilerVersion.hashCode();
        int v3 = this.languageVersion == null ? 0 : this.languageVersion.hashCode();
        Object object0 = this.expectedVersion;
        if(object0 != null) {
            v = object0.hashCode();
        }
        return ((((v1 * 0x1F + v2) * 0x1F + v3) * 0x1F + v) * 0x1F + this.filePath.hashCode()) * 0x1F + this.classId.hashCode();
    }

    @Override
    public String toString() {
        return "IncompatibleVersionErrorData(actualVersion=" + this.actualVersion + ", compilerVersion=" + this.compilerVersion + ", languageVersion=" + this.languageVersion + ", expectedVersion=" + this.expectedVersion + ", filePath=" + this.filePath + ", classId=" + this.classId + ')';
    }
}

