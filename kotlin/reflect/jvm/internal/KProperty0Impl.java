package kotlin.reflect.jvm.internal;

import java.lang.reflect.Member;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0010\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u001AB\u0017\b\u0016\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bB)\b\u0016\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\t\u001A\u00020\n\u0012\u0006\u0010\u000B\u001A\u00020\n\u0012\b\u0010\f\u001A\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000EJ\r\u0010\u0016\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0017J\n\u0010\u0018\u001A\u0004\u0018\u00010\rH\u0016J\u000E\u0010\u0019\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0017R\u001A\u0010\u000F\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u00118VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0014\u0010\u0015¨\u0006\u001B"}, d2 = {"Lkotlin/reflect/jvm/internal/KProperty0Impl;", "V", "Lkotlin/reflect/KProperty0;", "Lkotlin/reflect/jvm/internal/KPropertyImpl;", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;)V", "name", "", "signature", "boundReceiver", "", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "_getter", "Lkotlin/Lazy;", "Lkotlin/reflect/jvm/internal/KProperty0Impl$Getter;", "delegateValue", "getter", "getGetter", "()Lkotlin/reflect/jvm/internal/KProperty0Impl$Getter;", "get", "()Ljava/lang/Object;", "getDelegate", "invoke", "Getter", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class KProperty0Impl extends KPropertyImpl implements KProperty0 {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000E\u0010\t\u001A\u00028\u0001H\u0096\u0002¢\u0006\u0002\u0010\nR\u001A\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\b¨\u0006\u000B"}, d2 = {"Lkotlin/reflect/jvm/internal/KProperty0Impl$Getter;", "R", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Getter;", "Lkotlin/reflect/KProperty0$Getter;", "property", "Lkotlin/reflect/jvm/internal/KProperty0Impl;", "(Lkotlin/reflect/jvm/internal/KProperty0Impl;)V", "getProperty", "()Lkotlin/reflect/jvm/internal/KProperty0Impl;", "invoke", "()Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Getter extends kotlin.reflect.jvm.internal.KPropertyImpl.Getter implements kotlin.reflect.KProperty0.Getter {
        private final KProperty0Impl property;

        public Getter(KProperty0Impl kProperty0Impl0) {
            Intrinsics.checkNotNullParameter(kProperty0Impl0, "property");
            super();
            this.property = kProperty0Impl0;
        }

        @Override  // kotlin.reflect.KProperty$Accessor
        public KProperty getProperty() {
            return this.getProperty();
        }

        public KProperty0Impl getProperty() {
            return this.property;
        }

        @Override  // kotlin.reflect.jvm.internal.KPropertyImpl$Accessor
        public KPropertyImpl getProperty() {
            return this.getProperty();
        }

        @Override  // kotlin.jvm.functions.Function0
        public Object invoke() {
            return this.getProperty().get();
        }
    }

    private final Lazy _getter;
    private final Lazy delegateValue;

    public KProperty0Impl(KDeclarationContainerImpl kDeclarationContainerImpl0, String s, String s1, Object object0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "signature");
        super(kDeclarationContainerImpl0, s, s1, object0);
        Function0 function00 = new kotlin.reflect.jvm.internal.KProperty0Impl._getter.1(this);
        this._getter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        Function0 function01 = new kotlin.reflect.jvm.internal.KProperty0Impl.delegateValue.1(this);
        this.delegateValue = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function01);
    }

    public KProperty0Impl(KDeclarationContainerImpl kDeclarationContainerImpl0, PropertyDescriptor propertyDescriptor0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
        super(kDeclarationContainerImpl0, propertyDescriptor0);
        Function0 function00 = new Function0() {
            {
                KProperty0Impl.this = kProperty0Impl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Getter invoke() {
                return new Getter(KProperty0Impl.this);
            }
        };
        this._getter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        Function0 function01 = new Function0() {
            {
                KProperty0Impl.this = kProperty0Impl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Member member0 = KProperty0Impl.this.computeDelegateSource();
                return KProperty0Impl.this.getDelegateImpl(member0, null, null);
            }
        };
        this.delegateValue = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function01);
    }

    @Override  // kotlin.reflect.KProperty0
    public Object get() {
        return this.getGetter().call(new Object[0]);
    }

    @Override  // kotlin.reflect.KProperty0
    public Object getDelegate() {
        return this.delegateValue.getValue();
    }

    @Override  // kotlin.reflect.KProperty
    public kotlin.reflect.KProperty.Getter getGetter() {
        return this.getGetter();
    }

    @Override  // kotlin.reflect.KProperty0
    public kotlin.reflect.KProperty0.Getter getGetter() {
        return this.getGetter();
    }

    public Getter getGetter() {
        return (Getter)this._getter.getValue();
    }

    @Override  // kotlin.reflect.jvm.internal.KPropertyImpl
    public kotlin.reflect.jvm.internal.KPropertyImpl.Getter getGetter() {
        return this.getGetter();
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.get();
    }
}

