package kotlin.reflect.jvm.internal;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004:\u0001\u001BB)\b\u0016\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b\u0012\u0006\u0010\t\u001A\u00020\b\u0012\b\u0010\n\u001A\u0004\u0018\u00010\u000B¢\u0006\u0002\u0010\fB\u0017\b\u0016\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\r\u001A\u00020\u000E¢\u0006\u0002\u0010\u000FJ\u001D\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00028\u00002\u0006\u0010\u0019\u001A\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001AR \u0010\u0010\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0013\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0014\u0010\u0015¨\u0006\u001C"}, d2 = {"Lkotlin/reflect/jvm/internal/KMutableProperty1Impl;", "T", "V", "Lkotlin/reflect/jvm/internal/KProperty1Impl;", "Lkotlin/reflect/KMutableProperty1;", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "name", "", "signature", "boundReceiver", "", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;)V", "_setter", "Lkotlin/Lazy;", "Lkotlin/reflect/jvm/internal/KMutableProperty1Impl$Setter;", "setter", "getSetter", "()Lkotlin/reflect/jvm/internal/KMutableProperty1Impl$Setter;", "set", "", "receiver", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "Setter", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class KMutableProperty1Impl extends KProperty1Impl implements KMutableProperty1 {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u0019\u0012\u0012\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\u0002\u0010\u0007J\u001E\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00028\u00022\u0006\u0010\r\u001A\u00028\u0003H\u0096\u0002¢\u0006\u0002\u0010\u000ER \u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t¨\u0006\u000F"}, d2 = {"Lkotlin/reflect/jvm/internal/KMutableProperty1Impl$Setter;", "T", "V", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Setter;", "Lkotlin/reflect/KMutableProperty1$Setter;", "property", "Lkotlin/reflect/jvm/internal/KMutableProperty1Impl;", "(Lkotlin/reflect/jvm/internal/KMutableProperty1Impl;)V", "getProperty", "()Lkotlin/reflect/jvm/internal/KMutableProperty1Impl;", "invoke", "", "receiver", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Setter extends kotlin.reflect.jvm.internal.KPropertyImpl.Setter implements kotlin.reflect.KMutableProperty1.Setter {
        private final KMutableProperty1Impl property;

        public Setter(KMutableProperty1Impl kMutableProperty1Impl0) {
            Intrinsics.checkNotNullParameter(kMutableProperty1Impl0, "property");
            super();
            this.property = kMutableProperty1Impl0;
        }

        @Override  // kotlin.reflect.KProperty$Accessor
        public KProperty getProperty() {
            return this.getProperty();
        }

        public KMutableProperty1Impl getProperty() {
            return this.property;
        }

        @Override  // kotlin.reflect.jvm.internal.KPropertyImpl$Accessor
        public KPropertyImpl getProperty() {
            return this.getProperty();
        }

        @Override  // kotlin.jvm.functions.Function2
        public Object invoke(Object object0, Object object1) {
            this.invoke(object0, object1);
            return Unit.INSTANCE;
        }

        public void invoke(Object object0, Object object1) {
            this.getProperty().set(object0, object1);
        }
    }

    private final Lazy _setter;

    public KMutableProperty1Impl(KDeclarationContainerImpl kDeclarationContainerImpl0, String s, String s1, Object object0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "signature");
        super(kDeclarationContainerImpl0, s, s1, object0);
        Function0 function00 = new Function0() {
            {
                KMutableProperty1Impl.this = kMutableProperty1Impl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Setter invoke() {
                return new Setter(KMutableProperty1Impl.this);
            }
        };
        this._setter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
    }

    public KMutableProperty1Impl(KDeclarationContainerImpl kDeclarationContainerImpl0, PropertyDescriptor propertyDescriptor0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
        super(kDeclarationContainerImpl0, propertyDescriptor0);
        Function0 function00 = new kotlin.reflect.jvm.internal.KMutableProperty1Impl._setter.1(this);
        this._setter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
    }

    @Override  // kotlin.reflect.KMutableProperty
    public kotlin.reflect.KMutableProperty.Setter getSetter() {
        return this.getSetter();
    }

    @Override  // kotlin.reflect.KMutableProperty1
    public kotlin.reflect.KMutableProperty1.Setter getSetter() {
        return this.getSetter();
    }

    public Setter getSetter() {
        return (Setter)this._setter.getValue();
    }

    @Override  // kotlin.reflect.KMutableProperty1
    public void set(Object object0, Object object1) {
        this.getSetter().call(new Object[]{object0, object1});
    }
}

