package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;

public final class JavaDefaultQualifiers {
    private final boolean definitelyNotNull;
    private final NullabilityQualifierWithMigrationStatus nullabilityQualifier;
    private final Collection qualifierApplicabilityTypes;

    public JavaDefaultQualifiers(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus0, Collection collection0, boolean z) {
        Intrinsics.checkNotNullParameter(nullabilityQualifierWithMigrationStatus0, "nullabilityQualifier");
        Intrinsics.checkNotNullParameter(collection0, "qualifierApplicabilityTypes");
        super();
        this.nullabilityQualifier = nullabilityQualifierWithMigrationStatus0;
        this.qualifierApplicabilityTypes = collection0;
        this.definitelyNotNull = z;
    }

    public JavaDefaultQualifiers(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus0, Collection collection0, boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 4) != 0) {
            z = nullabilityQualifierWithMigrationStatus0.getQualifier() == NullabilityQualifier.NOT_NULL;
        }
        this(nullabilityQualifierWithMigrationStatus0, collection0, z);
    }

    public final JavaDefaultQualifiers copy(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus0, Collection collection0, boolean z) {
        Intrinsics.checkNotNullParameter(nullabilityQualifierWithMigrationStatus0, "nullabilityQualifier");
        Intrinsics.checkNotNullParameter(collection0, "qualifierApplicabilityTypes");
        return new JavaDefaultQualifiers(nullabilityQualifierWithMigrationStatus0, collection0, z);
    }

    public static JavaDefaultQualifiers copy$default(JavaDefaultQualifiers javaDefaultQualifiers0, NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus0, Collection collection0, boolean z, int v, Object object0) {
        if((v & 1) != 0) {
            nullabilityQualifierWithMigrationStatus0 = javaDefaultQualifiers0.nullabilityQualifier;
        }
        if((v & 2) != 0) {
            collection0 = javaDefaultQualifiers0.qualifierApplicabilityTypes;
        }
        if((v & 4) != 0) {
            z = javaDefaultQualifiers0.definitelyNotNull;
        }
        return javaDefaultQualifiers0.copy(nullabilityQualifierWithMigrationStatus0, collection0, z);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof JavaDefaultQualifiers)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.nullabilityQualifier, ((JavaDefaultQualifiers)object0).nullabilityQualifier)) {
            return false;
        }
        return Intrinsics.areEqual(this.qualifierApplicabilityTypes, ((JavaDefaultQualifiers)object0).qualifierApplicabilityTypes) ? this.definitelyNotNull == ((JavaDefaultQualifiers)object0).definitelyNotNull : false;
    }

    public final boolean getDefinitelyNotNull() {
        return this.definitelyNotNull;
    }

    public final NullabilityQualifierWithMigrationStatus getNullabilityQualifier() {
        return this.nullabilityQualifier;
    }

    public final Collection getQualifierApplicabilityTypes() {
        return this.qualifierApplicabilityTypes;
    }

    @Override
    public int hashCode() {
        int v = this.nullabilityQualifier.hashCode();
        int v1 = this.qualifierApplicabilityTypes.hashCode();
        int v2 = this.definitelyNotNull;
        if(v2) {
            v2 = 1;
        }
        return (v * 0x1F + v1) * 0x1F + v2;
    }

    @Override
    public String toString() {
        return "JavaDefaultQualifiers(nullabilityQualifier=" + this.nullabilityQualifier + ", qualifierApplicabilityTypes=" + this.qualifierApplicabilityTypes + ", definitelyNotNull=" + this.definitelyNotNull + ')';
    }
}

