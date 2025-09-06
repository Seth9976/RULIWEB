package kotlin.reflect.jvm.internal;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u0019B\u0017\b\u0016\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bB)\b\u0016\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\t\u001A\u00020\n\u0012\u0006\u0010\u000B\u001A\u00020\n\u0012\b\u0010\f\u001A\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000EJ\u0015\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018R\u001A\u0010\u000F\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u0012\u001A\b\u0012\u0004\u0012\u00028\u00000\u00118VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0013\u0010\u0014¨\u0006\u001A"}, d2 = {"Lkotlin/reflect/jvm/internal/KMutableProperty0Impl;", "V", "Lkotlin/reflect/jvm/internal/KProperty0Impl;", "Lkotlin/reflect/KMutableProperty0;", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;)V", "name", "", "signature", "boundReceiver", "", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "_setter", "Lkotlin/Lazy;", "Lkotlin/reflect/jvm/internal/KMutableProperty0Impl$Setter;", "setter", "getSetter", "()Lkotlin/reflect/jvm/internal/KMutableProperty0Impl$Setter;", "set", "", "value", "(Ljava/lang/Object;)V", "Setter", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class KMutableProperty0Impl extends KProperty0Impl implements KMutableProperty0 {
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00028\u0001H\u0096\u0002¢\u0006\u0002\u0010\fR\u001A\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lkotlin/reflect/jvm/internal/KMutableProperty0Impl$Setter;", "R", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Setter;", "Lkotlin/reflect/KMutableProperty0$Setter;", "property", "Lkotlin/reflect/jvm/internal/KMutableProperty0Impl;", "(Lkotlin/reflect/jvm/internal/KMutableProperty0Impl;)V", "getProperty", "()Lkotlin/reflect/jvm/internal/KMutableProperty0Impl;", "invoke", "", "value", "(Ljava/lang/Object;)V", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Setter extends kotlin.reflect.jvm.internal.KPropertyImpl.Setter implements kotlin.reflect.KMutableProperty0.Setter {
        private final KMutableProperty0Impl property;

        public Setter(KMutableProperty0Impl kMutableProperty0Impl0) {
            Intrinsics.checkNotNullParameter(kMutableProperty0Impl0, "property");
            super();
            this.property = kMutableProperty0Impl0;
        }

        @Override  // kotlin.reflect.KProperty$Accessor
        public KProperty getProperty() {
            return this.getProperty();
        }

        public KMutableProperty0Impl getProperty() {
            return this.property;
        }

        @Override  // kotlin.reflect.jvm.internal.KPropertyImpl$Accessor
        public KPropertyImpl getProperty() {
            return this.getProperty();
        }

        @Override  // kotlin.jvm.functions.Function1
        public Object invoke(Object object0) {
            this.invoke(object0);
            return Unit.INSTANCE;
        }

        public void invoke(Object object0) {
            this.getProperty().set(object0);
        }
    }

    private final Lazy _setter;

    public KMutableProperty0Impl(KDeclarationContainerImpl kDeclarationContainerImpl0, String s, String s1, Object object0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "signature");
        super(kDeclarationContainerImpl0, s, s1, object0);
        Function0 function00 = new kotlin.reflect.jvm.internal.KMutableProperty0Impl._setter.1(this);
        this._setter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
    }

    public KMutableProperty0Impl(KDeclarationContainerImpl kDeclarationContainerImpl0, PropertyDescriptor propertyDescriptor0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
        super(kDeclarationContainerImpl0, propertyDescriptor0);
        Function0 function00 = new Function0() {
            {
                KMutableProperty0Impl.this = kMutableProperty0Impl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Setter invoke() {
                return new Setter(KMutableProperty0Impl.this);
            }
        };
        this._setter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
    }

    @Override  // kotlin.reflect.KMutableProperty
    public kotlin.reflect.KMutableProperty.Setter getSetter() {
        return this.getSetter();
    }

    @Override  // kotlin.reflect.KMutableProperty0
    public kotlin.reflect.KMutableProperty0.Setter getSetter() {
        return this.getSetter();
    }

    public Setter getSetter() {
        return (Setter)this._setter.getValue();
    }

    @Override  // kotlin.reflect.KMutableProperty0
    public void set(Object object0) {
        this.getSetter().call(new Object[]{object0});
    }
}

