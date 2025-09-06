package androidx.lifecycle;

import androidx.lifecycle.viewmodel.CreationExtras.Empty;
import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003BA\b\u0007\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001A\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\u000E\b\u0002\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\u0002\u0010\rJ\b\u0010\u0013\u001A\u00020\u0014H\u0016R\u0012\u0010\u000E\u001A\u0004\u0018\u00018\u0000X\u0082\u000E¢\u0006\u0004\n\u0002\u0010\u000FR\u0014\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001A\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001A\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/lifecycle/ViewModelLazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Lkotlin/Lazy;", "viewModelClass", "Lkotlin/reflect/KClass;", "storeProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelStore;", "factoryProducer", "Landroidx/lifecycle/ViewModelProvider$Factory;", "extrasProducer", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "cached", "Landroidx/lifecycle/ViewModel;", "value", "getValue", "()Landroidx/lifecycle/ViewModel;", "isInitialized", "", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ViewModelLazy implements Lazy {
    private ViewModel cached;
    private final Function0 extrasProducer;
    private final Function0 factoryProducer;
    private final Function0 storeProducer;
    private final KClass viewModelClass;

    public ViewModelLazy(KClass kClass0, Function0 function00, Function0 function01) {
        Intrinsics.checkNotNullParameter(kClass0, "viewModelClass");
        Intrinsics.checkNotNullParameter(function00, "storeProducer");
        Intrinsics.checkNotNullParameter(function01, "factoryProducer");
        this(kClass0, function00, function01, null, 8, null);
    }

    public ViewModelLazy(KClass kClass0, Function0 function00, Function0 function01, Function0 function02) {
        Intrinsics.checkNotNullParameter(kClass0, "viewModelClass");
        Intrinsics.checkNotNullParameter(function00, "storeProducer");
        Intrinsics.checkNotNullParameter(function01, "factoryProducer");
        Intrinsics.checkNotNullParameter(function02, "extrasProducer");
        super();
        this.viewModelClass = kClass0;
        this.storeProducer = function00;
        this.factoryProducer = function01;
        this.extrasProducer = function02;
    }

    public ViewModelLazy(KClass kClass0, Function0 function00, Function0 function01, Function0 function02, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 8) != 0) {
            function02 = androidx.lifecycle.ViewModelLazy.1.INSTANCE;
        }
        this(kClass0, function00, function01, function02);

        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/lifecycle/viewmodel/CreationExtras$Empty;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class androidx.lifecycle.ViewModelLazy.1 extends Lambda implements Function0 {
            public static final androidx.lifecycle.ViewModelLazy.1 INSTANCE;

            static {
                androidx.lifecycle.ViewModelLazy.1.INSTANCE = new androidx.lifecycle.ViewModelLazy.1();
            }

            androidx.lifecycle.ViewModelLazy.1() {
                super(0);
            }

            public final Empty invoke() {
                return Empty.INSTANCE;
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        }

    }

    public ViewModel getValue() {
        ViewModel viewModel0 = this.cached;
        if(viewModel0 == null) {
            Factory viewModelProvider$Factory0 = (Factory)this.factoryProducer.invoke();
            viewModel0 = new ViewModelProvider(((ViewModelStore)this.storeProducer.invoke()), viewModelProvider$Factory0, ((CreationExtras)this.extrasProducer.invoke())).get(JvmClassMappingKt.getJavaClass(this.viewModelClass));
            this.cached = viewModel0;
        }
        return viewModel0;
    }

    @Override  // kotlin.Lazy
    public Object getValue() {
        return this.getValue();
    }

    @Override  // kotlin.Lazy
    public boolean isInitialized() {
        return this.cached != null;
    }
}

