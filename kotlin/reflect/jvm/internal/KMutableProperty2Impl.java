package kotlin.reflect.jvm.internal;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005:\u0001\u001BB\u001F\b\u0016\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\t¢\u0006\u0002\u0010\u000BB\u0017\b\u0016\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\f\u001A\u00020\r¢\u0006\u0002\u0010\u000EJ%\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00028\u00002\u0006\u0010\u0018\u001A\u00028\u00012\u0006\u0010\u0019\u001A\u00028\u0002H\u0016¢\u0006\u0002\u0010\u001AR&\u0010\u000F\u001A\u001A\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0012\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0013\u0010\u0014¨\u0006\u001C"}, d2 = {"Lkotlin/reflect/jvm/internal/KMutableProperty2Impl;", "D", "E", "V", "Lkotlin/reflect/jvm/internal/KProperty2Impl;", "Lkotlin/reflect/KMutableProperty2;", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "name", "", "signature", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;)V", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;)V", "_setter", "Lkotlin/Lazy;", "Lkotlin/reflect/jvm/internal/KMutableProperty2Impl$Setter;", "setter", "getSetter", "()Lkotlin/reflect/jvm/internal/KMutableProperty2Impl$Setter;", "set", "", "receiver1", "receiver2", "value", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "Setter", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class KMutableProperty2Impl extends KProperty2Impl implements KMutableProperty2 {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0003\u0010\u0001*\u0004\b\u0004\u0010\u0002*\u0004\b\u0005\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005B\u001F\u0012\u0018\u0010\u0006\u001A\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0007¢\u0006\u0002\u0010\bJ&\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00028\u00032\u0006\u0010\u000E\u001A\u00028\u00042\u0006\u0010\u000F\u001A\u00028\u0005H\u0096\u0002¢\u0006\u0002\u0010\u0010R&\u0010\u0006\u001A\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lkotlin/reflect/jvm/internal/KMutableProperty2Impl$Setter;", "D", "E", "V", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Setter;", "Lkotlin/reflect/KMutableProperty2$Setter;", "property", "Lkotlin/reflect/jvm/internal/KMutableProperty2Impl;", "(Lkotlin/reflect/jvm/internal/KMutableProperty2Impl;)V", "getProperty", "()Lkotlin/reflect/jvm/internal/KMutableProperty2Impl;", "invoke", "", "receiver1", "receiver2", "value", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Setter extends kotlin.reflect.jvm.internal.KPropertyImpl.Setter implements kotlin.reflect.KMutableProperty2.Setter {
        private final KMutableProperty2Impl property;

        public Setter(KMutableProperty2Impl kMutableProperty2Impl0) {
            Intrinsics.checkNotNullParameter(kMutableProperty2Impl0, "property");
            super();
            this.property = kMutableProperty2Impl0;
        }

        @Override  // kotlin.reflect.KProperty$Accessor
        public KProperty getProperty() {
            return this.getProperty();
        }

        public KMutableProperty2Impl getProperty() {
            return this.property;
        }

        @Override  // kotlin.reflect.jvm.internal.KPropertyImpl$Accessor
        public KPropertyImpl getProperty() {
            return this.getProperty();
        }

        @Override  // kotlin.jvm.functions.Function3
        public Object invoke(Object object0, Object object1, Object object2) {
            this.invoke(object0, object1, object2);
            return Unit.INSTANCE;
        }

        public void invoke(Object object0, Object object1, Object object2) {
            this.getProperty().set(object0, object1, object2);
        }
    }

    private final Lazy _setter;

    public KMutableProperty2Impl(KDeclarationContainerImpl kDeclarationContainerImpl0, String s, String s1) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "signature");
        super(kDeclarationContainerImpl0, s, s1);
        Function0 function00 = new Function0() {
            {
                KMutableProperty2Impl.this = kMutableProperty2Impl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Setter invoke() {
                return new Setter(KMutableProperty2Impl.this);
            }
        };
        this._setter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
    }

    public KMutableProperty2Impl(KDeclarationContainerImpl kDeclarationContainerImpl0, PropertyDescriptor propertyDescriptor0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
        super(kDeclarationContainerImpl0, propertyDescriptor0);
        Function0 function00 = new kotlin.reflect.jvm.internal.KMutableProperty2Impl._setter.1(this);
        this._setter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
    }

    @Override  // kotlin.reflect.KMutableProperty
    public kotlin.reflect.KMutableProperty.Setter getSetter() {
        return this.getSetter();
    }

    @Override  // kotlin.reflect.KMutableProperty2
    public kotlin.reflect.KMutableProperty2.Setter getSetter() {
        return this.getSetter();
    }

    public Setter getSetter() {
        return (Setter)this._setter.getValue();
    }

    @Override  // kotlin.reflect.KMutableProperty2
    public void set(Object object0, Object object1, Object object2) {
        this.getSetter().call(new Object[]{object0, object1, object2});
    }
}

