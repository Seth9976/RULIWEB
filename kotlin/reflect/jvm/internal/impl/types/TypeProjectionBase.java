package kotlin.reflect.jvm.internal.impl.types;

public abstract class TypeProjectionBase implements TypeProjection {
    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof TypeProjection)) {
            return false;
        }
        if(this.isStarProjection() != ((TypeProjection)object0).isStarProjection()) {
            return false;
        }
        return this.getProjectionKind() == ((TypeProjection)object0).getProjectionKind() ? this.getType().equals(((TypeProjection)object0).getType()) : false;
    }

    @Override
    public int hashCode() {
        int v = this.getProjectionKind().hashCode();
        if(TypeUtils.noExpectedType(this.getType())) {
            return v * 0x1F + 19;
        }
        return this.isStarProjection() ? v * 0x1F + 17 : v * 0x1F + this.getType().hashCode();
    }

    @Override
    public String toString() {
        if(this.isStarProjection()) {
            return "*";
        }
        return this.getProjectionKind() == Variance.INVARIANT ? this.getType().toString() : this.getProjectionKind() + " " + this.getType();
    }
}

