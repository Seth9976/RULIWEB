package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class NullabilityQualifierWithMigrationStatus {
    private final boolean isForWarningOnly;
    private final NullabilityQualifier qualifier;

    public NullabilityQualifierWithMigrationStatus(NullabilityQualifier nullabilityQualifier0, boolean z) {
        Intrinsics.checkNotNullParameter(nullabilityQualifier0, "qualifier");
        super();
        this.qualifier = nullabilityQualifier0;
        this.isForWarningOnly = z;
    }

    public NullabilityQualifierWithMigrationStatus(NullabilityQualifier nullabilityQualifier0, boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            z = false;
        }
        this(nullabilityQualifier0, z);
    }

    public final NullabilityQualifierWithMigrationStatus copy(NullabilityQualifier nullabilityQualifier0, boolean z) {
        Intrinsics.checkNotNullParameter(nullabilityQualifier0, "qualifier");
        return new NullabilityQualifierWithMigrationStatus(nullabilityQualifier0, z);
    }

    public static NullabilityQualifierWithMigrationStatus copy$default(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus0, NullabilityQualifier nullabilityQualifier0, boolean z, int v, Object object0) {
        if((v & 1) != 0) {
            nullabilityQualifier0 = nullabilityQualifierWithMigrationStatus0.qualifier;
        }
        if((v & 2) != 0) {
            z = nullabilityQualifierWithMigrationStatus0.isForWarningOnly;
        }
        return nullabilityQualifierWithMigrationStatus0.copy(nullabilityQualifier0, z);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof NullabilityQualifierWithMigrationStatus)) {
            return false;
        }
        return this.qualifier == ((NullabilityQualifierWithMigrationStatus)object0).qualifier ? this.isForWarningOnly == ((NullabilityQualifierWithMigrationStatus)object0).isForWarningOnly : false;
    }

    public final NullabilityQualifier getQualifier() {
        return this.qualifier;
    }

    @Override
    public int hashCode() {
        int v = this.qualifier.hashCode();
        int v1 = this.isForWarningOnly;
        if(v1) {
            v1 = 1;
        }
        return v * 0x1F + v1;
    }

    public final boolean isForWarningOnly() {
        return this.isForWarningOnly;
    }

    @Override
    public String toString() {
        return "NullabilityQualifierWithMigrationStatus(qualifier=" + this.qualifier + ", isForWarningOnly=" + this.isForWarningOnly + ')';
    }
}

