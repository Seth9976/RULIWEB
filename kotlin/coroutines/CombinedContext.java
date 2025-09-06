package kotlin.coroutines;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.IntRef;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001!B\u0015\u0012\u0006\u0010\u0004\u001A\u00020\u0001\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\u0005\u001A\u00020\u0006H\u0002J\u0010\u0010\n\u001A\u00020\t2\u0006\u0010\u000B\u001A\u00020\u0000H\u0002J\u0013\u0010\f\u001A\u00020\t2\b\u0010\r\u001A\u0004\u0018\u00010\u000EH\u0096\u0002J5\u0010\u000F\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u0011\u001A\u0002H\u00102\u0018\u0010\u0012\u001A\u0014\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00100\u0013H\u0016¢\u0006\u0002\u0010\u0014J(\u0010\u0015\u001A\u0004\u0018\u0001H\u0016\"\b\b\u0000\u0010\u0016*\u00020\u00062\f\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00160\u0018H\u0096\u0002¢\u0006\u0002\u0010\u0019J\b\u0010\u001A\u001A\u00020\u001BH\u0016J\u0014\u0010\u001C\u001A\u00020\u00012\n\u0010\u0017\u001A\u0006\u0012\u0002\b\u00030\u0018H\u0016J\b\u0010\u001D\u001A\u00020\u001BH\u0002J\b\u0010\u001E\u001A\u00020\u001FH\u0016J\b\u0010 \u001A\u00020\u000EH\u0002R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lkotlin/coroutines/CombinedContext;", "Lkotlin/coroutines/CoroutineContext;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "left", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext$Element;)V", "contains", "", "containsAll", "context", "equals", "other", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "hashCode", "", "minusKey", "size", "toString", "", "writeReplace", "Serialized", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class CombinedContext implements Serializable, CoroutineContext {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \f2\u00060\u0001j\u0002`\u0002:\u0001\fB\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001A\u00020\u000BH\u0002R\u0019\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\t\u001A\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lkotlin/coroutines/CombinedContext$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "elements", "", "Lkotlin/coroutines/CoroutineContext;", "([Lkotlin/coroutines/CoroutineContext;)V", "getElements", "()[Lkotlin/coroutines/CoroutineContext;", "[Lkotlin/coroutines/CoroutineContext;", "readResolve", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class Serialized implements Serializable {
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/coroutines/CombinedContext$Serialized$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        public static final class Companion {
            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }
        }

        public static final Companion Companion;
        private final CoroutineContext[] elements;
        private static final long serialVersionUID;

        static {
            Serialized.Companion = new Companion(null);
        }

        public Serialized(CoroutineContext[] arr_coroutineContext) {
            Intrinsics.checkNotNullParameter(arr_coroutineContext, "elements");
            super();
            this.elements = arr_coroutineContext;
        }

        public final CoroutineContext[] getElements() {
            return this.elements;
        }

        private final Object readResolve() {
            CoroutineContext[] arr_coroutineContext = this.elements;
            CoroutineContext coroutineContext0 = EmptyCoroutineContext.INSTANCE;
            for(int v = 0; v < arr_coroutineContext.length; ++v) {
                coroutineContext0 = coroutineContext0.plus(arr_coroutineContext[v]);
            }
            return coroutineContext0;
        }
    }

    private final Element element;
    private final CoroutineContext left;

    public CombinedContext(CoroutineContext coroutineContext0, Element coroutineContext$Element0) {
        Intrinsics.checkNotNullParameter(coroutineContext0, "left");
        Intrinsics.checkNotNullParameter(coroutineContext$Element0, "element");
        super();
        this.left = coroutineContext0;
        this.element = coroutineContext$Element0;
    }

    private final boolean contains(Element coroutineContext$Element0) {
        return Intrinsics.areEqual(this.get(coroutineContext$Element0.getKey()), coroutineContext$Element0);
    }

    private final boolean containsAll(CombinedContext combinedContext0) {
        CoroutineContext coroutineContext0;
        while(true) {
            if(!this.contains(combinedContext0.element)) {
                return false;
            }
            coroutineContext0 = combinedContext0.left;
            if(!(coroutineContext0 instanceof CombinedContext)) {
                break;
            }
            combinedContext0 = (CombinedContext)coroutineContext0;
        }
        Intrinsics.checkNotNull(coroutineContext0, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
        return this.contains(((Element)coroutineContext0));
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return this == object0 || object0 instanceof CombinedContext && ((CombinedContext)object0).size() == this.size() && ((CombinedContext)object0).containsAll(this);
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public Object fold(Object object0, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "operation");
        return function20.invoke(this.left.fold(object0, function20), this.element);
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public Element get(Key coroutineContext$Key0) {
        CoroutineContext coroutineContext0;
        Intrinsics.checkNotNullParameter(coroutineContext$Key0, "key");
        for(CombinedContext combinedContext0 = this; true; combinedContext0 = (CombinedContext)coroutineContext0) {
            Element coroutineContext$Element0 = combinedContext0.element.get(coroutineContext$Key0);
            if(coroutineContext$Element0 != null) {
                return coroutineContext$Element0;
            }
            coroutineContext0 = combinedContext0.left;
            if(!(coroutineContext0 instanceof CombinedContext)) {
                break;
            }
        }
        return coroutineContext0.get(coroutineContext$Key0);
    }

    @Override
    public int hashCode() {
        return this.left.hashCode() + this.element.hashCode();
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(Key coroutineContext$Key0) {
        Intrinsics.checkNotNullParameter(coroutineContext$Key0, "key");
        if(this.element.get(coroutineContext$Key0) != null) {
            return this.left;
        }
        CoroutineContext coroutineContext0 = this.left.minusKey(coroutineContext$Key0);
        if(coroutineContext0 == this.left) {
            return this;
        }
        return coroutineContext0 == EmptyCoroutineContext.INSTANCE ? this.element : new CombinedContext(coroutineContext0, this.element);
    }

    @Override  // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext0) {
        return DefaultImpls.plus(this, coroutineContext0);
    }

    private final int size() {
        int v = 2;
        CombinedContext combinedContext0 = this;
        while(true) {
            combinedContext0 = combinedContext0.left instanceof CombinedContext ? ((CombinedContext)combinedContext0.left) : null;
            if(combinedContext0 == null) {
                break;
            }
            ++v;
        }
        return v;
    }

    @Override
    public String toString() {
        return "[" + ((String)this.fold("", kotlin.coroutines.CombinedContext.toString.1.INSTANCE)) + ']';

        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "acc", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
        final class kotlin.coroutines.CombinedContext.toString.1 extends Lambda implements Function2 {
            public static final kotlin.coroutines.CombinedContext.toString.1 INSTANCE;

            static {
                kotlin.coroutines.CombinedContext.toString.1.INSTANCE = new kotlin.coroutines.CombinedContext.toString.1();
            }

            kotlin.coroutines.CombinedContext.toString.1() {
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((String)object0), ((Element)object1));
            }

            public final String invoke(String s, Element coroutineContext$Element0) {
                Intrinsics.checkNotNullParameter(s, "acc");
                Intrinsics.checkNotNullParameter(coroutineContext$Element0, "element");
                return s.length() == 0 ? coroutineContext$Element0.toString() : s + ", " + coroutineContext$Element0;
            }
        }

    }

    private final Object writeReplace() {
        int v = this.size();
        CoroutineContext[] arr_coroutineContext = new CoroutineContext[v];
        IntRef ref$IntRef0 = new IntRef();
        Function2 function20 = new Function2(ref$IntRef0) {
            final CoroutineContext[] $elements;
            final IntRef $index;

            {
                this.$elements = arr_coroutineContext;
                this.$index = ref$IntRef0;
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                this.invoke(((Unit)object0), ((Element)object1));
                return Unit.INSTANCE;
            }

            public final void invoke(Unit unit0, Element coroutineContext$Element0) {
                Intrinsics.checkNotNullParameter(unit0, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(coroutineContext$Element0, "element");
                int v = this.$index.element;
                this.$index.element = v + 1;
                this.$elements[v] = coroutineContext$Element0;
            }
        };
        this.fold(Unit.INSTANCE, function20);
        if(ref$IntRef0.element != v) {
            throw new IllegalStateException("Check failed.");
        }
        return new Serialized(arr_coroutineContext);
    }
}

