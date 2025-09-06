package kotlin.reflect;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0087\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0019\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000B\u0010\u000B\u001A\u0004\u0018\u00010\u0003HÆ\u0003J\u000B\u0010\f\u001A\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\r\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001A\u00020\u0012HÖ\u0001J\b\u0010\u0013\u001A\u00020\u0014H\u0016R\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lkotlin/reflect/KTypeProjection;", "", "variance", "Lkotlin/reflect/KVariance;", "type", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KVariance;Lkotlin/reflect/KType;)V", "getType", "()Lkotlin/reflect/KType;", "getVariance", "()Lkotlin/reflect/KVariance;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class KTypeProjection {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001A\u00020\u00042\u0006\u0010\n\u001A\u00020\u000BH\u0007J\u0010\u0010\f\u001A\u00020\u00042\u0006\u0010\n\u001A\u00020\u000BH\u0007J\u0010\u0010\r\u001A\u00020\u00042\u0006\u0010\n\u001A\u00020\u000BH\u0007R\u0011\u0010\u0003\u001A\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001A\u00020\u00048\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002¨\u0006\u000E"}, d2 = {"Lkotlin/reflect/KTypeProjection$Companion;", "", "()V", "STAR", "Lkotlin/reflect/KTypeProjection;", "getSTAR", "()Lkotlin/reflect/KTypeProjection;", "star", "getStar$annotations", "contravariant", "type", "Lkotlin/reflect/KType;", "covariant", "invariant", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final KTypeProjection contravariant(KType kType0) {
            Intrinsics.checkNotNullParameter(kType0, "type");
            return new KTypeProjection(KVariance.IN, kType0);
        }

        @JvmStatic
        public final KTypeProjection covariant(KType kType0) {
            Intrinsics.checkNotNullParameter(kType0, "type");
            return new KTypeProjection(KVariance.OUT, kType0);
        }

        public final KTypeProjection getSTAR() {
            return KTypeProjection.star;
        }

        public static void getStar$annotations() {
        }

        @JvmStatic
        public final KTypeProjection invariant(KType kType0) {
            Intrinsics.checkNotNullParameter(kType0, "type");
            return new KTypeProjection(KVariance.INVARIANT, kType0);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[KVariance.values().length];
            try {
                arr_v[KVariance.INVARIANT.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[KVariance.IN.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[KVariance.OUT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final Companion Companion;
    public static final KTypeProjection star;
    private final KType type;
    private final KVariance variance;

    static {
        KTypeProjection.Companion = new Companion(null);
        KTypeProjection.star = new KTypeProjection(null, null);
    }

    public KTypeProjection(KVariance kVariance0, KType kType0) {
        this.variance = kVariance0;
        this.type = kType0;
        if((kVariance0 == null ? 1 : 0) != (kType0 == null ? 1 : 0)) {
            throw new IllegalArgumentException((kVariance0 == null ? "Star projection must have no type specified." : "The projection variance " + kVariance0 + " requires type to be specified.").toString());
        }
    }

    public final KVariance component1() {
        return this.variance;
    }

    public final KType component2() {
        return this.type;
    }

    @JvmStatic
    public static final KTypeProjection contravariant(KType kType0) {
        return KTypeProjection.Companion.contravariant(kType0);
    }

    public final KTypeProjection copy(KVariance kVariance0, KType kType0) {
        return new KTypeProjection(kVariance0, kType0);
    }

    public static KTypeProjection copy$default(KTypeProjection kTypeProjection0, KVariance kVariance0, KType kType0, int v, Object object0) {
        if((v & 1) != 0) {
            kVariance0 = kTypeProjection0.variance;
        }
        if((v & 2) != 0) {
            kType0 = kTypeProjection0.type;
        }
        return kTypeProjection0.copy(kVariance0, kType0);
    }

    @JvmStatic
    public static final KTypeProjection covariant(KType kType0) {
        return KTypeProjection.Companion.covariant(kType0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof KTypeProjection)) {
            return false;
        }
        return this.variance == ((KTypeProjection)object0).variance ? Intrinsics.areEqual(this.type, ((KTypeProjection)object0).type) : false;
    }

    public final KType getType() {
        return this.type;
    }

    public final KVariance getVariance() {
        return this.variance;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.variance == null ? 0 : this.variance.hashCode();
        KType kType0 = this.type;
        if(kType0 != null) {
            v = kType0.hashCode();
        }
        return v1 * 0x1F + v;
    }

    @JvmStatic
    public static final KTypeProjection invariant(KType kType0) {
        return KTypeProjection.Companion.invariant(kType0);
    }

    @Override
    public String toString() {
        switch((this.variance == null ? -1 : WhenMappings.$EnumSwitchMapping$0[this.variance.ordinal()])) {
            case -1: {
                return "*";
            }
            case 1: {
                return String.valueOf(this.type);
            }
            case 2: {
                return "in " + this.type;
            }
            case 3: {
                return "out " + this.type;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }
}

