package androidx.activity;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A4\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0010\b\n\u0010\u0005\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0087\bø\u0001\u0000\u001AF\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0010\b\n\u0010\b\u001A\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00062\u0010\b\n\u0010\u0005\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0087\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"viewModels", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/activity/ComponentActivity;", "factoryProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "extrasProducer", "Landroidx/lifecycle/viewmodel/CreationExtras;", "activity_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ActivityViewModelLazyKt {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Superseded by viewModels that takes a CreationExtras")
    public static final Lazy viewModels(ComponentActivity componentActivity0, Function0 function00) {
        Intrinsics.checkNotNullParameter(componentActivity0, "<this>");
        if(function00 == null) {
            function00 = new Function0(componentActivity0) {
                final ComponentActivity $this_viewModels;

                {
                    this.$this_viewModels = componentActivity0;
                    super(0);
                }

                public final Factory invoke() {
                    return this.$this_viewModels.getDefaultViewModelProviderFactory();
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }
            };
        }
        Intrinsics.reifiedOperationMarker(4, "VM");
        return new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), new Function0(componentActivity0) {
            final ComponentActivity $this_viewModels;

            {
                this.$this_viewModels = componentActivity0;
                super(0);
            }

            public final ViewModelStore invoke() {
                return this.$this_viewModels.getViewModelStore();
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        }, function00, new Function0(componentActivity0) {
            final ComponentActivity $this_viewModels;

            {
                this.$this_viewModels = componentActivity0;
                super(0);
            }

            public final CreationExtras invoke() {
                return this.$this_viewModels.getDefaultViewModelCreationExtras();
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
    }

    public static final Lazy viewModels(ComponentActivity componentActivity0, Function0 function00, Function0 function01) {
        Intrinsics.checkNotNullParameter(componentActivity0, "<this>");
        if(function01 == null) {
            function01 = new Function0(componentActivity0) {
                final ComponentActivity $this_viewModels;

                {
                    this.$this_viewModels = componentActivity0;
                    super(0);
                }

                public final Factory invoke() {
                    return this.$this_viewModels.getDefaultViewModelProviderFactory();
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }
            };
        }
        Intrinsics.reifiedOperationMarker(4, "VM");
        return new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), new Function0(componentActivity0) {
            final ComponentActivity $this_viewModels;

            {
                this.$this_viewModels = componentActivity0;
                super(0);
            }

            public final ViewModelStore invoke() {
                return this.$this_viewModels.getViewModelStore();
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        }, function01, new Function0(function00, componentActivity0) {
            final Function0 $extrasProducer;
            final ComponentActivity $this_viewModels;

            {
                this.$extrasProducer = function00;
                this.$this_viewModels = componentActivity0;
                super(0);
            }

            public final CreationExtras invoke() {
                Function0 function00 = this.$extrasProducer;
                if(function00 != null) {
                    CreationExtras creationExtras0 = (CreationExtras)function00.invoke();
                    return creationExtras0 == null ? this.$this_viewModels.getDefaultViewModelCreationExtras() : creationExtras0;
                }
                return this.$this_viewModels.getDefaultViewModelCreationExtras();
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
    }

    public static Lazy viewModels$default(ComponentActivity componentActivity0, Function0 function00, int v, Object object0) {
        if((v & 1) != 0) {
            function00 = null;
        }
        Intrinsics.checkNotNullParameter(componentActivity0, "<this>");
        if(function00 == null) {
            function00 = new androidx.activity.ActivityViewModelLazyKt.viewModels.factoryPromise.1(componentActivity0);
        }
        Intrinsics.reifiedOperationMarker(4, "VM");
        return new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), new androidx.activity.ActivityViewModelLazyKt.viewModels.1(componentActivity0), function00, new androidx.activity.ActivityViewModelLazyKt.viewModels.2(componentActivity0));
    }

    public static Lazy viewModels$default(ComponentActivity componentActivity0, Function0 function00, Function0 function01, int v, Object object0) {
        if((v & 1) != 0) {
            function00 = null;
        }
        if((v & 2) != 0) {
            function01 = null;
        }
        Intrinsics.checkNotNullParameter(componentActivity0, "<this>");
        if(function01 == null) {
            function01 = new androidx.activity.ActivityViewModelLazyKt.viewModels.factoryPromise.2(componentActivity0);
        }
        Intrinsics.reifiedOperationMarker(4, "VM");
        return new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), new androidx.activity.ActivityViewModelLazyKt.viewModels.3(componentActivity0), function01, new androidx.activity.ActivityViewModelLazyKt.viewModels.4(function00, componentActivity0));
    }
}

