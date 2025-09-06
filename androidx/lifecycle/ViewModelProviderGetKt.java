package androidx.lifecycle;

import androidx.lifecycle.viewmodel.CreationExtras.Empty;
import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0010\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0000\u001A\u001E\u0010\u0004\u001A\u0002H\u0005\"\n\b\u0000\u0010\u0005\u0018\u0001*\u00020\u0006*\u00020\u0007H\u0087\b¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"defaultCreationExtras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "owner", "Landroidx/lifecycle/ViewModelStoreOwner;", "get", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/lifecycle/ViewModelProvider;", "(Landroidx/lifecycle/ViewModelProvider;)Landroidx/lifecycle/ViewModel;", "lifecycle-viewmodel_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ViewModelProviderGetKt {
    public static final CreationExtras defaultCreationExtras(ViewModelStoreOwner viewModelStoreOwner0) {
        Intrinsics.checkNotNullParameter(viewModelStoreOwner0, "owner");
        return viewModelStoreOwner0 instanceof HasDefaultViewModelProviderFactory ? ((HasDefaultViewModelProviderFactory)viewModelStoreOwner0).getDefaultViewModelCreationExtras() : Empty.INSTANCE;
    }

    public static final ViewModel get(ViewModelProvider viewModelProvider0) {
        Intrinsics.checkNotNullParameter(viewModelProvider0, "<this>");
        Intrinsics.reifiedOperationMarker(4, "VM");
        return viewModelProvider0.get(ViewModel.class);
    }
}

