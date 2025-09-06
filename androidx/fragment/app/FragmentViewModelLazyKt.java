package androidx.fragment.app;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001A4\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0010\b\n\u0010\u0005\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0087\bø\u0001\u0000\u001AJ\u0010\b\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\f\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00020\n2\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\f0\u00062\u0010\b\u0002\u0010\u0005\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0007\u001AD\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u000E\b\n\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u000F0\u00062\u0010\b\n\u0010\u0005\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0087\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"activityViewModels", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "factoryProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "createViewModelLazy", "viewModelClass", "Lkotlin/reflect/KClass;", "storeProducer", "Landroidx/lifecycle/ViewModelStore;", "viewModels", "ownerProducer", "Landroidx/lifecycle/ViewModelStoreOwner;", "fragment-ktx_release"}, k = 2, mv = {1, 4, 1})
public final class FragmentViewModelLazyKt {
    public static final Lazy activityViewModels(Fragment fragment0, Function0 function00) {
        Intrinsics.checkNotNullParameter(fragment0, "$this$activityViewModels");
        Intrinsics.reifiedOperationMarker(4, "VM");
        KClass kClass0 = Reflection.getOrCreateKotlinClass(ViewModel.class);
        Function0 function01 = new Function0(fragment0) {
            final Fragment $this_activityViewModels;

            {
                this.$this_activityViewModels = fragment0;
                super(0);
            }

            public final ViewModelStore invoke() {
                FragmentActivity fragmentActivity0 = this.$this_activityViewModels.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivity0, "requireActivity()");
                ViewModelStore viewModelStore0 = fragmentActivity0.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore0, "requireActivity().viewModelStore");
                return viewModelStore0;
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        };
        if(function00 == null) {
            function00 = new Function0(fragment0) {
                final Fragment $this_activityViewModels;

                {
                    this.$this_activityViewModels = fragment0;
                    super(0);
                }

                public final Factory invoke() {
                    FragmentActivity fragmentActivity0 = this.$this_activityViewModels.requireActivity();
                    Intrinsics.checkNotNullExpressionValue(fragmentActivity0, "requireActivity()");
                    return fragmentActivity0.getDefaultViewModelProviderFactory();
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }
            };
        }
        return FragmentViewModelLazyKt.createViewModelLazy(fragment0, kClass0, function01, function00);
    }

    public static Lazy activityViewModels$default(Fragment fragment0, Function0 function00, int v, Object object0) {
        if((v & 1) != 0) {
            function00 = null;
        }
        Intrinsics.checkNotNullParameter(fragment0, "$this$activityViewModels");
        Intrinsics.reifiedOperationMarker(4, "VM");
        KClass kClass0 = Reflection.getOrCreateKotlinClass(ViewModel.class);
        Function0 function01 = new androidx.fragment.app.FragmentViewModelLazyKt.activityViewModels.1(fragment0);
        if(function00 == null) {
            function00 = new androidx.fragment.app.FragmentViewModelLazyKt.activityViewModels.2(fragment0);
        }
        return FragmentViewModelLazyKt.createViewModelLazy(fragment0, kClass0, function01, function00);
    }

    public static final Lazy createViewModelLazy(Fragment fragment0, KClass kClass0, Function0 function00, Function0 function01) {
        Intrinsics.checkNotNullParameter(fragment0, "$this$createViewModelLazy");
        Intrinsics.checkNotNullParameter(kClass0, "viewModelClass");
        Intrinsics.checkNotNullParameter(function00, "storeProducer");
        if(function01 == null) {
            function01 = new Function0(fragment0) {
                final Fragment $this_createViewModelLazy;

                {
                    this.$this_createViewModelLazy = fragment0;
                    super(0);
                }

                public final Factory invoke() {
                    return this.$this_createViewModelLazy.getDefaultViewModelProviderFactory();
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }
            };
        }
        return new ViewModelLazy(kClass0, function00, function01);
    }

    public static Lazy createViewModelLazy$default(Fragment fragment0, KClass kClass0, Function0 function00, Function0 function01, int v, Object object0) {
        if((v & 4) != 0) {
            function01 = null;
        }
        return FragmentViewModelLazyKt.createViewModelLazy(fragment0, kClass0, function00, function01);
    }

    public static final Lazy viewModels(Fragment fragment0, Function0 function00, Function0 function01) {
        Intrinsics.checkNotNullParameter(fragment0, "$this$viewModels");
        Intrinsics.checkNotNullParameter(function00, "ownerProducer");
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy(fragment0, Reflection.getOrCreateKotlinClass(ViewModel.class), new Function0(function00) {
            final Function0 $ownerProducer;

            {
                this.$ownerProducer = function00;
                super(0);
            }

            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore0 = ((ViewModelStoreOwner)this.$ownerProducer.invoke()).getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore0, "ownerProducer().viewModelStore");
                return viewModelStore0;
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        }, function01);
    }

    public static Lazy viewModels$default(Fragment fragment0, Function0 function00, Function0 function01, int v, Object object0) {
        if((v & 1) != 0) {
            function00 = new Function0(fragment0) {
                final Fragment $this_viewModels;

                {
                    this.$this_viewModels = fragment0;
                    super(0);
                }

                public final Fragment invoke() {
                    return this.$this_viewModels;
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }
            };
        }
        if((v & 2) != 0) {
            function01 = null;
        }
        Intrinsics.checkNotNullParameter(fragment0, "$this$viewModels");
        Intrinsics.checkNotNullParameter(function00, "ownerProducer");
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy(fragment0, Reflection.getOrCreateKotlinClass(ViewModel.class), new androidx.fragment.app.FragmentViewModelLazyKt.viewModels.2(function00), function01);
    }
}

