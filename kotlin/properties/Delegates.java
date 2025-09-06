package kotlin.properties;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001E\u0010\u0003\u001A\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0000\u0010\u0005*\u00020\u0001J\u0080\u0001\u0010\u0006\u001A\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052Q\b\u0004\u0010\b\u001AK\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\n¢\u0006\f\b\u000B\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\u000B\u0012\b\b\f\u0012\u0004\b\b(\u000E\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\u000B\u0012\b\b\f\u0012\u0004\b\b(\u000F\u0012\u0004\u0012\u00020\u00100\tH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0080\u0001\u0010\u0012\u001A\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052Q\b\u0004\u0010\b\u001AK\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\n¢\u0006\f\b\u000B\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\u000B\u0012\b\b\f\u0012\u0004\b\b(\u000E\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\u000B\u0012\b\b\f\u0012\u0004\b\b(\u000F\u0012\u0004\u0012\u00020\u00130\tH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0011\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"Lkotlin/properties/Delegates;", "", "()V", "notNull", "Lkotlin/properties/ReadWriteProperty;", "T", "observable", "initialValue", "onChange", "Lkotlin/Function3;", "Lkotlin/reflect/KProperty;", "Lkotlin/ParameterName;", "name", "property", "oldValue", "newValue", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlin/properties/ReadWriteProperty;", "vetoable", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class Delegates {
    public static final Delegates INSTANCE;

    static {
        Delegates.INSTANCE = new Delegates();
    }

    public final ReadWriteProperty notNull() {
        return new NotNullVar();
    }

    public final ReadWriteProperty observable(Object object0, Function3 function30) {
        Intrinsics.checkNotNullParameter(function30, "onChange");
        return new ObservableProperty(function30) {
            @Override  // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty kProperty0, Object object0, Object object1) {
                Intrinsics.checkNotNullParameter(kProperty0, "property");
                this.$onChange.invoke(kProperty0, object0, object1);
            }
        };
    }

    public final ReadWriteProperty vetoable(Object object0, Function3 function30) {
        Intrinsics.checkNotNullParameter(function30, "onChange");
        return new ObservableProperty(function30) {
            @Override  // kotlin.properties.ObservableProperty
            protected boolean beforeChange(KProperty kProperty0, Object object0, Object object1) {
                Intrinsics.checkNotNullParameter(kProperty0, "property");
                return ((Boolean)this.$onChange.invoke(kProperty0, object0, object1)).booleanValue();
            }
        };
    }
}

