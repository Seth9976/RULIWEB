package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.DefaultTab.PhotosTab;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.DefaultTab;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageAndVideo;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.VisualMediaType;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001A\u0012\u0010\u0000\u001A\u00020\u00012\b\b\u0002\u0010\u0002\u001A\u00020\u0003H\u0007\u001A\u001C\u0010\u0000\u001A\u00020\u00012\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0003\u0010\u0004\u001A\u00020\u0005H\u0007\u001A.\u0010\u0000\u001A\u00020\u00012\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0003\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u0006\u001A\u00020\u00072\b\b\u0002\u0010\b\u001A\u00020\t\u001A6\u0010\u0000\u001A\u00020\u00012\u0006\u0010\n\u001A\u00020\u000B2\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0003\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u0006\u001A\u00020\u00072\b\b\u0002\u0010\b\u001A\u00020\t¨\u0006\f"}, d2 = {"PickVisualMediaRequest", "Landroidx/activity/result/PickVisualMediaRequest;", "mediaType", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "maxItems", "", "isOrderedSelection", "", "defaultTab", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$DefaultTab;", "accentColor", "", "activity_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class PickVisualMediaRequestKt {
    public static final PickVisualMediaRequest PickVisualMediaRequest(long v, VisualMediaType activityResultContracts$PickVisualMedia$VisualMediaType0, int v1, boolean z, DefaultTab activityResultContracts$PickVisualMedia$DefaultTab0) {
        Intrinsics.checkNotNullParameter(activityResultContracts$PickVisualMedia$VisualMediaType0, "mediaType");
        Intrinsics.checkNotNullParameter(activityResultContracts$PickVisualMedia$DefaultTab0, "defaultTab");
        return new Builder().setMediaType(activityResultContracts$PickVisualMedia$VisualMediaType0).setMaxItems(v1).setOrderedSelection(z).setDefaultTab(activityResultContracts$PickVisualMedia$DefaultTab0).setAccentColor(v).build();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Superseded by PickVisualMediaRequest that takes an optional maxItems")
    public static final PickVisualMediaRequest PickVisualMediaRequest(VisualMediaType activityResultContracts$PickVisualMedia$VisualMediaType0) {
        Intrinsics.checkNotNullParameter(activityResultContracts$PickVisualMedia$VisualMediaType0, "mediaType");
        return new Builder().setMediaType(activityResultContracts$PickVisualMedia$VisualMediaType0).build();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Superseded by PickVisualMediaRequest that take optional isOrderedSelection and defaultTab")
    public static final PickVisualMediaRequest PickVisualMediaRequest(VisualMediaType activityResultContracts$PickVisualMedia$VisualMediaType0, int v) {
        Intrinsics.checkNotNullParameter(activityResultContracts$PickVisualMedia$VisualMediaType0, "mediaType");
        return new Builder().setMediaType(activityResultContracts$PickVisualMedia$VisualMediaType0).setMaxItems(v).build();
    }

    public static final PickVisualMediaRequest PickVisualMediaRequest(VisualMediaType activityResultContracts$PickVisualMedia$VisualMediaType0, int v, boolean z, DefaultTab activityResultContracts$PickVisualMedia$DefaultTab0) {
        Intrinsics.checkNotNullParameter(activityResultContracts$PickVisualMedia$VisualMediaType0, "mediaType");
        Intrinsics.checkNotNullParameter(activityResultContracts$PickVisualMedia$DefaultTab0, "defaultTab");
        return new Builder().setMediaType(activityResultContracts$PickVisualMedia$VisualMediaType0).setMaxItems(v).setOrderedSelection(z).setDefaultTab(activityResultContracts$PickVisualMedia$DefaultTab0).build();
    }

    public static PickVisualMediaRequest PickVisualMediaRequest$default(long v, VisualMediaType activityResultContracts$PickVisualMedia$VisualMediaType0, int v1, boolean z, DefaultTab activityResultContracts$PickVisualMedia$DefaultTab0, int v2, Object object0) {
        if((v2 & 2) != 0) {
            activityResultContracts$PickVisualMedia$VisualMediaType0 = ImageAndVideo.INSTANCE;
        }
        if((v2 & 4) != 0) {
            v1 = PickMultipleVisualMedia.Companion.getMaxItems$activity_release();
        }
        if((v2 & 16) != 0) {
            activityResultContracts$PickVisualMedia$DefaultTab0 = PhotosTab.INSTANCE;
        }
        return PickVisualMediaRequestKt.PickVisualMediaRequest(v, activityResultContracts$PickVisualMedia$VisualMediaType0, v1, ((v2 & 8) == 0 ? z : false), activityResultContracts$PickVisualMedia$DefaultTab0);
    }

    public static PickVisualMediaRequest PickVisualMediaRequest$default(VisualMediaType activityResultContracts$PickVisualMedia$VisualMediaType0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            activityResultContracts$PickVisualMedia$VisualMediaType0 = ImageAndVideo.INSTANCE;
        }
        if((v1 & 2) != 0) {
            v = PickMultipleVisualMedia.Companion.getMaxItems$activity_release();
        }
        return PickVisualMediaRequestKt.PickVisualMediaRequest(activityResultContracts$PickVisualMedia$VisualMediaType0, v);
    }

    public static PickVisualMediaRequest PickVisualMediaRequest$default(VisualMediaType activityResultContracts$PickVisualMedia$VisualMediaType0, int v, Object object0) {
        if((v & 1) != 0) {
            activityResultContracts$PickVisualMedia$VisualMediaType0 = ImageAndVideo.INSTANCE;
        }
        return PickVisualMediaRequestKt.PickVisualMediaRequest(activityResultContracts$PickVisualMedia$VisualMediaType0);
    }

    public static PickVisualMediaRequest PickVisualMediaRequest$default(VisualMediaType activityResultContracts$PickVisualMedia$VisualMediaType0, int v, boolean z, DefaultTab activityResultContracts$PickVisualMedia$DefaultTab0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            activityResultContracts$PickVisualMedia$VisualMediaType0 = ImageAndVideo.INSTANCE;
        }
        if((v1 & 2) != 0) {
            v = PickMultipleVisualMedia.Companion.getMaxItems$activity_release();
        }
        if((v1 & 4) != 0) {
            z = false;
        }
        if((v1 & 8) != 0) {
            activityResultContracts$PickVisualMedia$DefaultTab0 = PhotosTab.INSTANCE;
        }
        return PickVisualMediaRequestKt.PickVisualMediaRequest(activityResultContracts$PickVisualMedia$VisualMediaType0, v, z, activityResultContracts$PickVisualMedia$DefaultTab0);
    }
}

