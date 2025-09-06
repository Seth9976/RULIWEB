package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

public final class ClassLiteralValue {
    private final int arrayNestedness;
    private final ClassId classId;

    public ClassLiteralValue(ClassId classId0, int v) {
        Intrinsics.checkNotNullParameter(classId0, "classId");
        super();
        this.classId = classId0;
        this.arrayNestedness = v;
    }

    public final ClassId component1() {
        return this.classId;
    }

    public final int component2() {
        return this.arrayNestedness;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof ClassLiteralValue)) {
            return false;
        }
        return Intrinsics.areEqual(this.classId, ((ClassLiteralValue)object0).classId) ? this.arrayNestedness == ((ClassLiteralValue)object0).arrayNestedness : false;
    }

    public final int getArrayNestedness() {
        return this.arrayNestedness;
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    @Override
    public int hashCode() {
        return this.classId.hashCode() * 0x1F + this.arrayNestedness;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        int v = this.arrayNestedness;
        for(int v2 = 0; v2 < v; ++v2) {
            stringBuilder0.append("kotlin/Array<");
        }
        stringBuilder0.append(this.classId);
        int v3 = this.arrayNestedness;
        for(int v1 = 0; v1 < v3; ++v1) {
            stringBuilder0.append(">");
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }
}

