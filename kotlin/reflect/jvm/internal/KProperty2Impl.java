package kotlin.reflect.jvm.internal;

import java.lang.reflect.Member;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0006\b\u0002\u0010\u0003 \u00012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\b\u0012\u0004\u0012\u0002H\u00030\u0005:\u0001\u001EB\u001F\b\u0016\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\t¢\u0006\u0002\u0010\u000BB\u0017\b\u0016\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\f\u001A\u00020\r¢\u0006\u0002\u0010\u000EJ\u001D\u0010\u0017\u001A\u00028\u00022\u0006\u0010\u0018\u001A\u00028\u00002\u0006\u0010\u0019\u001A\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001AJ\u001F\u0010\u001B\u001A\u0004\u0018\u00010\u001C2\u0006\u0010\u0018\u001A\u00028\u00002\u0006\u0010\u0019\u001A\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001AJ\u001E\u0010\u001D\u001A\u00028\u00022\u0006\u0010\u0018\u001A\u00028\u00002\u0006\u0010\u0019\u001A\u00028\u0001H\u0096\u0002¢\u0006\u0002\u0010\u001AR&\u0010\u000F\u001A\u001A\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0014\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0015\u0010\u0016¨\u0006\u001F"}, d2 = {"Lkotlin/reflect/jvm/internal/KProperty2Impl;", "D", "E", "V", "Lkotlin/reflect/KProperty2;", "Lkotlin/reflect/jvm/internal/KPropertyImpl;", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "name", "", "signature", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;)V", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;)V", "_getter", "Lkotlin/Lazy;", "Lkotlin/reflect/jvm/internal/KProperty2Impl$Getter;", "delegateSource", "Ljava/lang/reflect/Member;", "getter", "getGetter", "()Lkotlin/reflect/jvm/internal/KProperty2Impl$Getter;", "get", "receiver1", "receiver2", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getDelegate", "", "invoke", "Getter", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class KProperty2Impl extends KPropertyImpl implements KProperty2 {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000*\u0004\b\u0003\u0010\u0001*\u0004\b\u0004\u0010\u0002*\u0006\b\u0005\u0010\u0003 \u00012\b\u0012\u0004\u0012\u0002H\u00030\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005B\u001F\u0012\u0018\u0010\u0006\u001A\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0007¢\u0006\u0002\u0010\bJ\u001E\u0010\u000B\u001A\u00028\u00052\u0006\u0010\f\u001A\u00028\u00032\u0006\u0010\r\u001A\u00028\u0004H\u0096\u0002¢\u0006\u0002\u0010\u000ER&\u0010\u0006\u001A\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n¨\u0006\u000F"}, d2 = {"Lkotlin/reflect/jvm/internal/KProperty2Impl$Getter;", "D", "E", "V", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Getter;", "Lkotlin/reflect/KProperty2$Getter;", "property", "Lkotlin/reflect/jvm/internal/KProperty2Impl;", "(Lkotlin/reflect/jvm/internal/KProperty2Impl;)V", "getProperty", "()Lkotlin/reflect/jvm/internal/KProperty2Impl;", "invoke", "receiver1", "receiver2", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Getter extends kotlin.reflect.jvm.internal.KPropertyImpl.Getter implements kotlin.reflect.KProperty2.Getter {
        private final KProperty2Impl property;

        public Getter(KProperty2Impl kProperty2Impl0) {
            Intrinsics.checkNotNullParameter(kProperty2Impl0, "property");
            super();
            this.property = kProperty2Impl0;
        }

        @Override  // kotlin.reflect.KProperty$Accessor
        public KProperty getProperty() {
            return this.getProperty();
        }

        public KProperty2Impl getProperty() {
            return this.property;
        }

        @Override  // kotlin.reflect.jvm.internal.KPropertyImpl$Accessor
        public KPropertyImpl getProperty() {
            return this.getProperty();
        }

        @Override  // kotlin.jvm.functions.Function2
        public Object invoke(Object object0, Object object1) {
            return this.getProperty().get(object0, object1);
        }
    }

    private final Lazy _getter;
    private final Lazy delegateSource;

    public KProperty2Impl(KDeclarationContainerImpl kDeclarationContainerImpl0, String s, String s1) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "signature");
        super(kDeclarationContainerImpl0, s, s1, CallableReference.NO_RECEIVER);
        Function0 function00 = new Function0() {
            {
                KProperty2Impl.this = kProperty2Impl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Getter invoke() {
                return new Getter(KProperty2Impl.this);
            }
        };
        this._getter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        Function0 function01 = new Function0() {
            {
                KProperty2Impl.this = kProperty2Impl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Member invoke() {
                return KProperty2Impl.this.computeDelegateSource();
            }
        };
        this.delegateSource = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function01);
    }

    public KProperty2Impl(KDeclarationContainerImpl kDeclarationContainerImpl0, PropertyDescriptor propertyDescriptor0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
        super(kDeclarationContainerImpl0, propertyDescriptor0);
        Function0 function00 = new kotlin.reflect.jvm.internal.KProperty2Impl._getter.1(this);
        this._getter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        Function0 function01 = new kotlin.reflect.jvm.internal.KProperty2Impl.delegateSource.1(this);
        this.delegateSource = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function01);
    }

    @Override  // kotlin.reflect.KProperty2
    public Object get(Object object0, Object object1) {
        return this.getGetter().call(new Object[]{object0, object1});
    }

    @Override  // kotlin.reflect.KProperty2
    public Object getDelegate(Object object0, Object object1) {
        return this.getDelegateImpl(((Member)this.delegateSource.getValue()), object0, object1);
    }

    @Override  // kotlin.reflect.KProperty
    public kotlin.reflect.KProperty.Getter getGetter() {
        return this.getGetter();
    }

    @Override  // kotlin.reflect.KProperty2
    public kotlin.reflect.KProperty2.Getter getGetter() {
        return this.getGetter();
    }

    public Getter getGetter() {
        return (Getter)this._getter.getValue();
    }

    @Override  // kotlin.reflect.jvm.internal.KPropertyImpl
    public kotlin.reflect.jvm.internal.KPropertyImpl.Getter getGetter() {
        return this.getGetter();
    }

    @Override  // kotlin.jvm.functions.Function2
    public Object invoke(Object object0, Object object1) {
        return this.get(object0, object1);
    }
}

