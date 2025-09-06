package kotlin.jvm.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVariance;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001F2\u00020\u0001:\u0001\u001FB\'\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\nJ\u0013\u0010\u0018\u001A\u00020\t2\b\u0010\u0019\u001A\u0004\u0018\u00010\u0003H\u0096\u0002J\b\u0010\u001A\u001A\u00020\u001BH\u0016J\u0014\u0010\u001C\u001A\u00020\u001D2\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\r0\fJ\b\u0010\u001E\u001A\u00020\u0005H\u0016R\u0016\u0010\u000B\u001A\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001A\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u000ER\u0014\u0010\u0004\u001A\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R \u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\r0\f8VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0013\u001A\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0006\u001A\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0016\u0010\u0017¨\u0006 "}, d2 = {"Lkotlin/jvm/internal/TypeParameterReference;", "Lkotlin/reflect/KTypeParameter;", "container", "", "name", "", "variance", "Lkotlin/reflect/KVariance;", "isReified", "", "(Ljava/lang/Object;Ljava/lang/String;Lkotlin/reflect/KVariance;Z)V", "bounds", "", "Lkotlin/reflect/KType;", "()Z", "getName", "()Ljava/lang/String;", "upperBounds", "getUpperBounds$annotations", "()V", "getUpperBounds", "()Ljava/util/List;", "getVariance", "()Lkotlin/reflect/KVariance;", "equals", "other", "hashCode", "", "setUpperBounds", "", "toString", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class TypeParameterReference implements KTypeParameter {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/jvm/internal/TypeParameterReference$Companion;", "", "()V", "toString", "", "typeParameter", "Lkotlin/reflect/KTypeParameter;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
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

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final String toString(KTypeParameter kTypeParameter0) {
            Intrinsics.checkNotNullParameter(kTypeParameter0, "typeParameter");
            StringBuilder stringBuilder0 = new StringBuilder();
            switch(WhenMappings.$EnumSwitchMapping$0[kTypeParameter0.getVariance().ordinal()]) {
                case 2: {
                    stringBuilder0.append("in ");
                    break;
                }
                case 3: {
                    stringBuilder0.append("out ");
                }
            }
            stringBuilder0.append(kTypeParameter0.getName());
            String s = stringBuilder0.toString();
            Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
            return s;
        }
    }

    public static final Companion Companion;
    private volatile List bounds;
    private final Object container;
    private final boolean isReified;
    private final String name;
    private final KVariance variance;

    static {
        TypeParameterReference.Companion = new Companion(null);
    }

    public TypeParameterReference(Object object0, String s, KVariance kVariance0, boolean z) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(kVariance0, "variance");
        super();
        this.container = object0;
        this.name = s;
        this.variance = kVariance0;
        this.isReified = z;
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof TypeParameterReference && Intrinsics.areEqual(this.container, ((TypeParameterReference)object0).container) && Intrinsics.areEqual(this.getName(), ((TypeParameterReference)object0).getName());
    }

    @Override  // kotlin.reflect.KTypeParameter
    public String getName() {
        return this.name;
    }

    @Override  // kotlin.reflect.KTypeParameter
    public List getUpperBounds() {
        List list0 = this.bounds;
        if(list0 == null) {
            list0 = CollectionsKt.listOf(Reflection.nullableTypeOf(Object.class));
            this.bounds = list0;
        }
        return list0;
    }

    public static void getUpperBounds$annotations() {
    }

    @Override  // kotlin.reflect.KTypeParameter
    public KVariance getVariance() {
        return this.variance;
    }

    @Override
    public int hashCode() {
        return this.container == null ? this.getName().hashCode() : this.container.hashCode() * 0x1F + this.getName().hashCode();
    }

    @Override  // kotlin.reflect.KTypeParameter
    public boolean isReified() {
        return this.isReified;
    }

    public final void setUpperBounds(List list0) {
        Intrinsics.checkNotNullParameter(list0, "upperBounds");
        if(this.bounds != null) {
            throw new IllegalStateException(("Upper bounds of type parameter \'" + this + "\' have already been initialized.").toString());
        }
        this.bounds = list0;
    }

    @Override
    public String toString() {
        return TypeParameterReference.Companion.toString(this);
    }
}

