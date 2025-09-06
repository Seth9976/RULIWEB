package kotlin.enums;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \f*\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004:\u0001\fB\u0013\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001A\u00020\u000BH\u0002R\u0014\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/enums/EnumEntriesSerializationProxy;", "E", "", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "entries", "", "([Ljava/lang/Enum;)V", "c", "Ljava/lang/Class;", "readResolve", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class EnumEntriesSerializationProxy implements Serializable {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/enums/EnumEntriesSerializationProxy$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private static final Companion Companion;
    private final Class c;
    private static final long serialVersionUID;

    static {
        EnumEntriesSerializationProxy.Companion = new Companion(null);
    }

    public EnumEntriesSerializationProxy(Enum[] arr_enum) {
        Intrinsics.checkNotNullParameter(arr_enum, "entries");
        super();
        Class class0 = arr_enum.getClass().getComponentType();
        Intrinsics.checkNotNull(class0);
        this.c = class0;
    }

    private final Object readResolve() {
        Object[] arr_object = this.c.getEnumConstants();
        Intrinsics.checkNotNullExpressionValue(arr_object, "c.enumConstants");
        return EnumEntriesKt.enumEntries(((Enum[])arr_object));
    }
}

