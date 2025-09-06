package androidx.lifecycle;

import android.app.Application;
import androidx.lifecycle.viewmodel.CreationExtras.Empty;
import androidx.lifecycle.viewmodel.CreationExtras.Key;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactory;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.lifecycle.viewmodel.ViewModelInitializer;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001:\u0004\u0016\u0017\u0018\u0019B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007B!\b\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\b\b\u0002\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fJ&\u0010\r\u001A\u0002H\u000E\"\b\b\u0000\u0010\u000E*\u00020\u000F2\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u0011H\u0097\u0002¢\u0006\u0002\u0010\u0012J.\u0010\r\u001A\u0002H\u000E\"\b\b\u0000\u0010\u000E*\u00020\u000F2\u0006\u0010\u0013\u001A\u00020\u00142\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u0011H\u0097\u0002¢\u0006\u0002\u0010\u0015R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001A"}, d2 = {"Landroidx/lifecycle/ViewModelProvider;", "", "owner", "Landroidx/lifecycle/ViewModelStoreOwner;", "(Landroidx/lifecycle/ViewModelStoreOwner;)V", "factory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "(Landroidx/lifecycle/ViewModelStoreOwner;Landroidx/lifecycle/ViewModelProvider$Factory;)V", "store", "Landroidx/lifecycle/ViewModelStore;", "defaultCreationExtras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Landroidx/lifecycle/ViewModelStore;Landroidx/lifecycle/ViewModelProvider$Factory;Landroidx/lifecycle/viewmodel/CreationExtras;)V", "get", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "key", "", "(Ljava/lang/String;Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "AndroidViewModelFactory", "Factory", "NewInstanceFactory", "OnRequeryFactory", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class ViewModelProvider {
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000F\b\u0016\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0019\b\u0002\u0012\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ%\u0010\t\u001A\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000B2\f\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000EJ-\u0010\t\u001A\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000B2\f\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\n0\r2\u0006\u0010\u000F\u001A\u00020\u0004H\u0002¢\u0006\u0002\u0010\u0010J-\u0010\t\u001A\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000B2\f\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\n0\r2\u0006\u0010\u0011\u001A\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0013R\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$AndroidViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "()V", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "unused", "", "(Landroid/app/Application;I)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app", "(Ljava/lang/Class;Landroid/app/Application;)Landroidx/lifecycle/ViewModel;", "extras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Ljava/lang/Class;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "Companion", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class AndroidViewModelFactory extends NewInstanceFactory {
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0000¢\u0006\u0002\b\u000EJ\u0010\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0010\u001A\u00020\u0005H\u0007R\u0016\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u0004\u0018\u00010\tX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$AndroidViewModelFactory$Companion;", "", "()V", "APPLICATION_KEY", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "Landroid/app/Application;", "DEFAULT_KEY", "", "sInstance", "Landroidx/lifecycle/ViewModelProvider$AndroidViewModelFactory;", "defaultFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "owner", "Landroidx/lifecycle/ViewModelStoreOwner;", "defaultFactory$lifecycle_viewmodel_release", "getInstance", "application", "ApplicationKeyImpl", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class Companion {
            @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$AndroidViewModelFactory$Companion$ApplicationKeyImpl;", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "Landroid/app/Application;", "()V", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
            static final class ApplicationKeyImpl implements Key {
                public static final ApplicationKeyImpl INSTANCE;

                static {
                    ApplicationKeyImpl.INSTANCE = new ApplicationKeyImpl();
                }
            }

            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            public final Factory defaultFactory$lifecycle_viewmodel_release(ViewModelStoreOwner viewModelStoreOwner0) {
                Intrinsics.checkNotNullParameter(viewModelStoreOwner0, "owner");
                return viewModelStoreOwner0 instanceof HasDefaultViewModelProviderFactory ? ((HasDefaultViewModelProviderFactory)viewModelStoreOwner0).getDefaultViewModelProviderFactory() : NewInstanceFactory.Companion.getInstance();
            }

            @JvmStatic
            public final AndroidViewModelFactory getInstance(Application application0) {
                Intrinsics.checkNotNullParameter(application0, "application");
                if(AndroidViewModelFactory.sInstance == null) {
                    AndroidViewModelFactory.sInstance = new AndroidViewModelFactory(application0);
                }
                AndroidViewModelFactory viewModelProvider$AndroidViewModelFactory0 = AndroidViewModelFactory.sInstance;
                Intrinsics.checkNotNull(viewModelProvider$AndroidViewModelFactory0);
                return viewModelProvider$AndroidViewModelFactory0;
            }
        }

        public static final Key APPLICATION_KEY = null;
        public static final Companion Companion = null;
        public static final String DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey";
        private final Application application;
        private static AndroidViewModelFactory sInstance;

        static {
            AndroidViewModelFactory.Companion = new Companion(null);
            AndroidViewModelFactory.APPLICATION_KEY = ApplicationKeyImpl.INSTANCE;
        }

        public AndroidViewModelFactory() {
            this(null, 0);
        }

        public AndroidViewModelFactory(Application application0) {
            Intrinsics.checkNotNullParameter(application0, "application");
            this(application0, 0);
        }

        private AndroidViewModelFactory(Application application0, int v) {
            this.application = application0;
        }

        private final ViewModel create(Class class0, Application application0) {
            ViewModel viewModel0;
            if(AndroidViewModel.class.isAssignableFrom(class0)) {
                try {
                    viewModel0 = (ViewModel)class0.getConstructor(Application.class).newInstance(application0);
                }
                catch(NoSuchMethodException noSuchMethodException0) {
                    throw new RuntimeException("Cannot create an instance of " + class0, noSuchMethodException0);
                }
                catch(IllegalAccessException illegalAccessException0) {
                    throw new RuntimeException("Cannot create an instance of " + class0, illegalAccessException0);
                }
                catch(InstantiationException instantiationException0) {
                    throw new RuntimeException("Cannot create an instance of " + class0, instantiationException0);
                }
                catch(InvocationTargetException invocationTargetException0) {
                    throw new RuntimeException("Cannot create an instance of " + class0, invocationTargetException0);
                }
                Intrinsics.checkNotNullExpressionValue(viewModel0, "{\n                try {\n…          }\n            }");
                return viewModel0;
            }
            return super.create(class0);
        }

        @Override  // androidx.lifecycle.ViewModelProvider$NewInstanceFactory
        public ViewModel create(Class class0) {
            Intrinsics.checkNotNullParameter(class0, "modelClass");
            Application application0 = this.application;
            if(application0 == null) {
                throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
            }
            return this.create(class0, application0);
        }

        @Override  // androidx.lifecycle.ViewModelProvider$NewInstanceFactory
        public ViewModel create(Class class0, CreationExtras creationExtras0) {
            Intrinsics.checkNotNullParameter(class0, "modelClass");
            Intrinsics.checkNotNullParameter(creationExtras0, "extras");
            if(this.application != null) {
                return this.create(class0);
            }
            Application application0 = (Application)creationExtras0.get(AndroidViewModelFactory.APPLICATION_KEY);
            if(application0 != null) {
                return this.create(class0, application0);
            }
            if(AndroidViewModel.class.isAssignableFrom(class0)) {
                throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
            }
            return super.create(class0);
        }

        @JvmStatic
        public static final AndroidViewModelFactory getInstance(Application application0) {
            return AndroidViewModelFactory.Companion.getInstance(application0);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u000B2\u00020\u0001:\u0001\u000BJ%\u0010\u0002\u001A\u0002H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00030\u0006H\u0016¢\u0006\u0002\u0010\u0007J-\u0010\u0002\u001A\u0002H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00030\u00062\u0006\u0010\b\u001A\u00020\tH\u0016¢\u0006\u0002\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$Factory;", "", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "extras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Ljava/lang/Class;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "Companion", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public interface Factory {
        @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001A\u00020\u00042\u001A\u0010\u0005\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006\"\u0006\u0012\u0002\b\u00030\u0007H\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$Factory$Companion;", "", "()V", "from", "Landroidx/lifecycle/ViewModelProvider$Factory;", "initializers", "", "Landroidx/lifecycle/viewmodel/ViewModelInitializer;", "([Landroidx/lifecycle/viewmodel/ViewModelInitializer;)Landroidx/lifecycle/ViewModelProvider$Factory;", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class androidx.lifecycle.ViewModelProvider.Factory.Companion {
            static final androidx.lifecycle.ViewModelProvider.Factory.Companion $$INSTANCE;

            static {
                androidx.lifecycle.ViewModelProvider.Factory.Companion.$$INSTANCE = new androidx.lifecycle.ViewModelProvider.Factory.Companion();
            }

            @JvmStatic
            public final Factory from(ViewModelInitializer[] arr_viewModelInitializer) {
                Intrinsics.checkNotNullParameter(arr_viewModelInitializer, "initializers");
                return new InitializerViewModelFactory(((ViewModelInitializer[])Arrays.copyOf(arr_viewModelInitializer, arr_viewModelInitializer.length)));
            }
        }

        public static final androidx.lifecycle.ViewModelProvider.Factory.Companion Companion;

        static {
            Factory.Companion = androidx.lifecycle.ViewModelProvider.Factory.Companion.$$INSTANCE;
        }

        ViewModel create(Class arg1);

        ViewModel create(Class arg1, CreationExtras arg2);
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001A\u0002H\u0004\"\b\b\u0000\u0010\u0004*\u00020\u00052\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0007H\u0016¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "()V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "Companion", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class NewInstanceFactory implements Factory {
        @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u0006\u001A\u00020\u00078GX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0002\u001A\u0004\b\t\u0010\nR\u0010\u0010\u000B\u001A\u0004\u0018\u00010\u0007X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory$Companion;", "", "()V", "VIEW_MODEL_KEY", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "", "instance", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "getInstance$annotations", "getInstance", "()Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "sInstance", "ViewModelKeyImpl", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion {
            @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory$Companion$ViewModelKeyImpl;", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "", "()V", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
            static final class ViewModelKeyImpl implements Key {
                public static final ViewModelKeyImpl INSTANCE;

                static {
                    ViewModelKeyImpl.INSTANCE = new ViewModelKeyImpl();
                }
            }

            private androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion() {
            }

            public androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            public final NewInstanceFactory getInstance() {
                if(NewInstanceFactory.sInstance == null) {
                    NewInstanceFactory.sInstance = new NewInstanceFactory();
                }
                NewInstanceFactory viewModelProvider$NewInstanceFactory0 = NewInstanceFactory.sInstance;
                Intrinsics.checkNotNull(viewModelProvider$NewInstanceFactory0);
                return viewModelProvider$NewInstanceFactory0;
            }

            @JvmStatic
            public static void getInstance$annotations() {
            }
        }

        public static final androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion Companion;
        public static final Key VIEW_MODEL_KEY;
        private static NewInstanceFactory sInstance;

        static {
            NewInstanceFactory.Companion = new androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion(null);
            NewInstanceFactory.VIEW_MODEL_KEY = ViewModelKeyImpl.INSTANCE;
        }

        @Override  // androidx.lifecycle.ViewModelProvider$Factory
        public ViewModel create(Class class0) {
            Intrinsics.checkNotNullParameter(class0, "modelClass");
            try {
                Object object0 = class0.getDeclaredConstructor(null).newInstance(null);
                Intrinsics.checkNotNullExpressionValue(object0, "{\n                modelC…wInstance()\n            }");
                return (ViewModel)object0;
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                throw new RuntimeException("Cannot create an instance of " + class0, noSuchMethodException0);
            }
            catch(InstantiationException instantiationException0) {
                throw new RuntimeException("Cannot create an instance of " + class0, instantiationException0);
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new RuntimeException("Cannot create an instance of " + class0, illegalAccessException0);
            }
        }

        @Override  // androidx.lifecycle.ViewModelProvider$Factory
        public ViewModel create(Class class0, CreationExtras creationExtras0) {
            return ViewModelProvider.Factory.-CC.$default$create(this, class0, creationExtras0);
        }

        public static final NewInstanceFactory getInstance() {
            return NewInstanceFactory.Companion.getInstance();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$OnRequeryFactory;", "", "()V", "onRequery", "", "viewModel", "Landroidx/lifecycle/ViewModel;", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class OnRequeryFactory {
        public void onRequery(ViewModel viewModel0) {
            Intrinsics.checkNotNullParameter(viewModel0, "viewModel");
        }
    }

    private final CreationExtras defaultCreationExtras;
    private final Factory factory;
    private final ViewModelStore store;

    public ViewModelProvider(ViewModelStore viewModelStore0, Factory viewModelProvider$Factory0) {
        Intrinsics.checkNotNullParameter(viewModelStore0, "store");
        Intrinsics.checkNotNullParameter(viewModelProvider$Factory0, "factory");
        this(viewModelStore0, viewModelProvider$Factory0, null, 4, null);
    }

    public ViewModelProvider(ViewModelStore viewModelStore0, Factory viewModelProvider$Factory0, CreationExtras creationExtras0) {
        Intrinsics.checkNotNullParameter(viewModelStore0, "store");
        Intrinsics.checkNotNullParameter(viewModelProvider$Factory0, "factory");
        Intrinsics.checkNotNullParameter(creationExtras0, "defaultCreationExtras");
        super();
        this.store = viewModelStore0;
        this.factory = viewModelProvider$Factory0;
        this.defaultCreationExtras = creationExtras0;
    }

    public ViewModelProvider(ViewModelStore viewModelStore0, Factory viewModelProvider$Factory0, CreationExtras creationExtras0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 4) != 0) {
            creationExtras0 = Empty.INSTANCE;
        }
        this(viewModelStore0, viewModelProvider$Factory0, creationExtras0);
    }

    public ViewModelProvider(ViewModelStoreOwner viewModelStoreOwner0) {
        Intrinsics.checkNotNullParameter(viewModelStoreOwner0, "owner");
        this(viewModelStoreOwner0.getViewModelStore(), AndroidViewModelFactory.Companion.defaultFactory$lifecycle_viewmodel_release(viewModelStoreOwner0), ViewModelProviderGetKt.defaultCreationExtras(viewModelStoreOwner0));
    }

    public ViewModelProvider(ViewModelStoreOwner viewModelStoreOwner0, Factory viewModelProvider$Factory0) {
        Intrinsics.checkNotNullParameter(viewModelStoreOwner0, "owner");
        Intrinsics.checkNotNullParameter(viewModelProvider$Factory0, "factory");
        this(viewModelStoreOwner0.getViewModelStore(), viewModelProvider$Factory0, ViewModelProviderGetKt.defaultCreationExtras(viewModelStoreOwner0));
    }

    public ViewModel get(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "modelClass");
        String s = class0.getCanonicalName();
        if(s == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return this.get("androidx.lifecycle.ViewModelProvider.DefaultKey:" + s, class0);
    }

    public ViewModel get(String s, Class class0) {
        ViewModel viewModel1;
        Intrinsics.checkNotNullParameter(s, "key");
        Intrinsics.checkNotNullParameter(class0, "modelClass");
        ViewModel viewModel0 = this.store.get(s);
        if(class0.isInstance(viewModel0)) {
            OnRequeryFactory viewModelProvider$OnRequeryFactory0 = this.factory instanceof OnRequeryFactory ? ((OnRequeryFactory)this.factory) : null;
            if(viewModelProvider$OnRequeryFactory0 != null) {
                Intrinsics.checkNotNull(viewModel0);
                viewModelProvider$OnRequeryFactory0.onRequery(viewModel0);
            }
            Intrinsics.checkNotNull(viewModel0, "null cannot be cast to non-null type T of androidx.lifecycle.ViewModelProvider.get");
            return viewModel0;
        }
        MutableCreationExtras mutableCreationExtras0 = new MutableCreationExtras(this.defaultCreationExtras);
        mutableCreationExtras0.set(NewInstanceFactory.VIEW_MODEL_KEY, s);
        try {
            viewModel1 = this.factory.create(class0, mutableCreationExtras0);
        }
        catch(AbstractMethodError unused_ex) {
            viewModel1 = this.factory.create(class0);
        }
        this.store.put(s, viewModel1);
        return viewModel1;
    }
}

