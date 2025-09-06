package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.DefaultTab.PhotosTab;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.DefaultTab;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageAndVideo;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.VisualMediaType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001#B\u0007\b\u0000¢\u0006\u0002\u0010\u0002R$\u0010\u0005\u001A\u00020\u00042\u0006\u0010\u0003\u001A\u00020\u0004@@X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\u000B\u001A\u00020\n2\u0006\u0010\u0003\u001A\u00020\n@@X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\f\u0010\r\"\u0004\b\u000E\u0010\u000FR$\u0010\u0011\u001A\u00020\u00102\u0006\u0010\u0003\u001A\u00020\u0010@@X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001A\u00020\u00102\u0006\u0010\u0003\u001A\u00020\u0010@@X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R$\u0010\u0018\u001A\u00020\u00172\u0006\u0010\u0003\u001A\u00020\u0017@@X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0019\u0010\u001A\"\u0004\b\u001B\u0010\u001CR$\u0010\u001E\u001A\u00020\u001D2\u0006\u0010\u0003\u001A\u00020\u001D@@X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u001F\u0010 \"\u0004\b!\u0010\"¨\u0006$"}, d2 = {"Landroidx/activity/result/PickVisualMediaRequest;", "", "()V", "<set-?>", "", "accentColor", "getAccentColor", "()J", "setAccentColor$activity_release", "(J)V", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$DefaultTab;", "defaultTab", "getDefaultTab", "()Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$DefaultTab;", "setDefaultTab$activity_release", "(Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$DefaultTab;)V", "", "isCustomAccentColorApplied", "()Z", "setCustomAccentColorApplied$activity_release", "(Z)V", "isOrderedSelection", "setOrderedSelection$activity_release", "", "maxItems", "getMaxItems", "()I", "setMaxItems$activity_release", "(I)V", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "mediaType", "getMediaType", "()Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "setMediaType$activity_release", "(Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;)V", "Builder", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class PickVisualMediaRequest {
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000E\u001A\u00020\u000FJ\u000E\u0010\u0010\u001A\u00020\u00002\u0006\u0010\u0003\u001A\u00020\u0004J\u000E\u0010\u0011\u001A\u00020\u00002\u0006\u0010\u0005\u001A\u00020\u0006J\u0010\u0010\u0012\u001A\u00020\u00002\b\b\u0001\u0010\n\u001A\u00020\u000BJ\u000E\u0010\u0013\u001A\u00020\u00002\u0006\u0010\f\u001A\u00020\rJ\u000E\u0010\u0014\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\bR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\rX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/activity/result/PickVisualMediaRequest$Builder;", "", "()V", "accentColor", "", "defaultTab", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$DefaultTab;", "isCustomAccentColorApplied", "", "isOrderedSelection", "maxItems", "", "mediaType", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "build", "Landroidx/activity/result/PickVisualMediaRequest;", "setAccentColor", "setDefaultTab", "setMaxItems", "setMediaType", "setOrderedSelection", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Builder {
        private long accentColor;
        private DefaultTab defaultTab;
        private boolean isCustomAccentColorApplied;
        private boolean isOrderedSelection;
        private int maxItems;
        private VisualMediaType mediaType;

        public Builder() {
            this.mediaType = ImageAndVideo.INSTANCE;
            this.maxItems = PickMultipleVisualMedia.Companion.getMaxItems$activity_release();
            this.defaultTab = PhotosTab.INSTANCE;
        }

        public final PickVisualMediaRequest build() {
            PickVisualMediaRequest pickVisualMediaRequest0 = new PickVisualMediaRequest();
            pickVisualMediaRequest0.setMediaType$activity_release(this.mediaType);
            pickVisualMediaRequest0.setMaxItems$activity_release(this.maxItems);
            pickVisualMediaRequest0.setOrderedSelection$activity_release(this.isOrderedSelection);
            pickVisualMediaRequest0.setDefaultTab$activity_release(this.defaultTab);
            pickVisualMediaRequest0.setCustomAccentColorApplied$activity_release(this.isCustomAccentColorApplied);
            pickVisualMediaRequest0.setAccentColor$activity_release(this.accentColor);
            return pickVisualMediaRequest0;
        }

        public final Builder setAccentColor(long v) {
            this.accentColor = v;
            this.isCustomAccentColorApplied = true;
            return this;
        }

        public final Builder setDefaultTab(DefaultTab activityResultContracts$PickVisualMedia$DefaultTab0) {
            Intrinsics.checkNotNullParameter(activityResultContracts$PickVisualMedia$DefaultTab0, "defaultTab");
            this.defaultTab = activityResultContracts$PickVisualMedia$DefaultTab0;
            return this;
        }

        public final Builder setMaxItems(int v) {
            this.maxItems = v;
            return this;
        }

        public final Builder setMediaType(VisualMediaType activityResultContracts$PickVisualMedia$VisualMediaType0) {
            Intrinsics.checkNotNullParameter(activityResultContracts$PickVisualMedia$VisualMediaType0, "mediaType");
            this.mediaType = activityResultContracts$PickVisualMedia$VisualMediaType0;
            return this;
        }

        public final Builder setOrderedSelection(boolean z) {
            this.isOrderedSelection = z;
            return this;
        }
    }

    private long accentColor;
    private DefaultTab defaultTab;
    private boolean isCustomAccentColorApplied;
    private boolean isOrderedSelection;
    private int maxItems;
    private VisualMediaType mediaType;

    public PickVisualMediaRequest() {
        this.mediaType = ImageAndVideo.INSTANCE;
        this.maxItems = PickMultipleVisualMedia.Companion.getMaxItems$activity_release();
        this.defaultTab = PhotosTab.INSTANCE;
    }

    public final long getAccentColor() {
        return this.accentColor;
    }

    public final DefaultTab getDefaultTab() {
        return this.defaultTab;
    }

    public final int getMaxItems() {
        return this.maxItems;
    }

    public final VisualMediaType getMediaType() {
        return this.mediaType;
    }

    public final boolean isCustomAccentColorApplied() {
        return this.isCustomAccentColorApplied;
    }

    public final boolean isOrderedSelection() {
        return this.isOrderedSelection;
    }

    public final void setAccentColor$activity_release(long v) {
        this.accentColor = v;
    }

    public final void setCustomAccentColorApplied$activity_release(boolean z) {
        this.isCustomAccentColorApplied = z;
    }

    public final void setDefaultTab$activity_release(DefaultTab activityResultContracts$PickVisualMedia$DefaultTab0) {
        Intrinsics.checkNotNullParameter(activityResultContracts$PickVisualMedia$DefaultTab0, "<set-?>");
        this.defaultTab = activityResultContracts$PickVisualMedia$DefaultTab0;
    }

    public final void setMaxItems$activity_release(int v) {
        this.maxItems = v;
    }

    public final void setMediaType$activity_release(VisualMediaType activityResultContracts$PickVisualMedia$VisualMediaType0) {
        Intrinsics.checkNotNullParameter(activityResultContracts$PickVisualMedia$VisualMediaType0, "<set-?>");
        this.mediaType = activityResultContracts$PickVisualMedia$VisualMediaType0;
    }

    public final void setOrderedSelection$activity_release(boolean z) {
        this.isOrderedSelection = z;
    }
}

