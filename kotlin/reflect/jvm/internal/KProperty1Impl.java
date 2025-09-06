package kotlin.reflect.jvm.internal;

import java.lang.reflect.Member;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004:\u0001\u001DB)\b\u0016\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b\u0012\u0006\u0010\t\u001A\u00020\b\u0012\b\u0010\n\u001A\u0004\u0018\u00010\u000B¢\u0006\u0002\u0010\fB\u0017\b\u0016\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\r\u001A\u00020\u000E¢\u0006\u0002\u0010\u000FJ\u0015\u0010\u0018\u001A\u00028\u00012\u0006\u0010\u0019\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001AJ\u0017\u0010\u001B\u001A\u0004\u0018\u00010\u000B2\u0006\u0010\u0019\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001AJ\u0016\u0010\u001C\u001A\u00028\u00012\u0006\u0010\u0019\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001AR \u0010\u0010\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0015\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0016\u0010\u0017¨\u0006\u001E"}, d2 = {"Lkotlin/reflect/jvm/internal/KProperty1Impl;", "T", "V", "Lkotlin/reflect/KProperty1;", "Lkotlin/reflect/jvm/internal/KPropertyImpl;", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "name", "", "signature", "boundReceiver", "", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;)V", "_getter", "Lkotlin/Lazy;", "Lkotlin/reflect/jvm/internal/KProperty1Impl$Getter;", "delegateSource", "Ljava/lang/reflect/Member;", "getter", "getGetter", "()Lkotlin/reflect/jvm/internal/KProperty1Impl$Getter;", "get", "receiver", "(Ljava/lang/Object;)Ljava/lang/Object;", "getDelegate", "invoke", "Getter", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class KProperty1Impl extends KPropertyImpl implements KProperty1 {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0006\b\u0003\u0010\u0002 \u00012\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u0019\u0012\u0012\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\n\u001A\u00028\u00032\u0006\u0010\u000B\u001A\u00028\u0002H\u0096\u0002¢\u0006\u0002\u0010\fR \u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lkotlin/reflect/jvm/internal/KProperty1Impl$Getter;", "T", "V", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Getter;", "Lkotlin/reflect/KProperty1$Getter;", "property", "Lkotlin/reflect/jvm/internal/KProperty1Impl;", "(Lkotlin/reflect/jvm/internal/KProperty1Impl;)V", "getProperty", "()Lkotlin/reflect/jvm/internal/KProperty1Impl;", "invoke", "receiver", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Getter extends kotlin.reflect.jvm.internal.KPropertyImpl.Getter implements kotlin.reflect.KProperty1.Getter {
        private final KProperty1Impl property;

        public Getter(KProperty1Impl kProperty1Impl0) {
            Intrinsics.checkNotNullParameter(kProperty1Impl0, "property");
            super();
            this.property = kProperty1Impl0;
        }

        @Override  // kotlin.reflect.KProperty$Accessor
        public KProperty getProperty() {
            return this.getProperty();
        }

        public KProperty1Impl getProperty() {
            return this.property;
        }

        @Override  // kotlin.reflect.jvm.internal.KPropertyImpl$Accessor
        public KPropertyImpl getProperty() {
            return this.getProperty();
        }

        @Override  // kotlin.jvm.functions.Function1
        public Object invoke(Object object0) {
            return this.getProperty().get(object0);
        }
    }

    private final Lazy _getter;
    private final Lazy delegateSource;

    public KProperty1Impl(KDeclarationContainerImpl kDeclarationContainerImpl0, String s, String s1, Object object0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "signature");
        super(kDeclarationContainerImpl0, s, s1, object0);
        Function0 function00 = new Function0() {
            {
                KProperty1Impl.this = kProperty1Impl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Getter invoke() {
                return new Getter(KProperty1Impl.this);
            }
        };
        this._getter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        Function0 function01 = new Function0() {
            {
                KProperty1Impl.this = kProperty1Impl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Member invoke() {
                return KProperty1Impl.this.computeDelegateSource();
            }
        };
        this.delegateSource = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function01);
    }

    public KProperty1Impl(KDeclarationContainerImpl kDeclarationContainerImpl0, PropertyDescriptor propertyDescriptor0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
        super(kDeclarationContainerImpl0, propertyDescriptor0);
        Function0 function00 = new kotlin.reflect.jvm.internal.KProperty1Impl._getter.1(this);
        this._getter = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        Function0 function01 = new kotlin.reflect.jvm.internal.KProperty1Impl.delegateSource.1(this);
        this.delegateSource = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function01);
    }

    @Override  // kotlin.reflect.KProperty1
    public Object get(Object object0) {
        return this.getGetter().call(new Object[]{object0});
    }

    @Override  // kotlin.reflect.KProperty1
    public Object getDelegate(Object object0) {
        return this.getDelegateImpl(((Member)this.delegateSource.getValue()), object0, null);
    }

    @Override  // kotlin.reflect.KProperty
    public kotlin.reflect.KProperty.Getter getGetter() {
        return this.getGetter();
    }

    @Override  // kotlin.reflect.KProperty1
    public kotlin.reflect.KProperty1.Getter getGetter() {
        return this.getGetter();
    }

    public Getter getGetter() {
        return (Getter)this._getter.getValue();
    }

    @Override  // kotlin.reflect.jvm.internal.KPropertyImpl
    public kotlin.reflect.jvm.internal.KPropertyImpl.Getter getGetter() {
        return this.getGetter();
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return this.get(object0);
    }
}

