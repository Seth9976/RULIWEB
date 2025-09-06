package androidx.core.view;

import android.content.ClipData.Item;
import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Pair;
import android.view.ContentInfo.Builder;
import android.view.ContentInfo;
import androidx.core.util.HalfKt..ExternalSyntheticApiModelOutline0;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public final class ContentInfoCompat {
    static final class Api31Impl {
        public static Pair partition(ContentInfo contentInfo0, Predicate predicate0) {
            ClipData clipData0 = contentInfo0.getClip();
            if(clipData0.getItemCount() == 1) {
                boolean z = predicate0.test(clipData0.getItemAt(0));
                ContentInfo contentInfo1 = z ? contentInfo0 : null;
                if(z) {
                    contentInfo0 = null;
                }
                return Pair.create(contentInfo1, contentInfo0);
            }
            Objects.requireNonNull(predicate0);
            Pair pair0 = ContentInfoCompat.partition(clipData0, new ContentInfoCompat.Api31Impl..ExternalSyntheticLambda0(predicate0));
            if(pair0.first == null) {
                return Pair.create(null, contentInfo0);
            }
            return pair0.second == null ? Pair.create(contentInfo0, null) : Pair.create(new ContentInfo.Builder(contentInfo0).setClip(((ClipData)pair0.first)).build(), new ContentInfo.Builder(contentInfo0).setClip(((ClipData)pair0.second)).build());
        }
    }

    public static final class Builder {
        private final BuilderCompat mBuilderCompat;

        public Builder(ClipData clipData0, int v) {
            if(Build.VERSION.SDK_INT >= 0x1F) {
                this.mBuilderCompat = new BuilderCompat31Impl(clipData0, v);
                return;
            }
            this.mBuilderCompat = new BuilderCompatImpl(clipData0, v);
        }

        public Builder(ContentInfoCompat contentInfoCompat0) {
            if(Build.VERSION.SDK_INT >= 0x1F) {
                this.mBuilderCompat = new BuilderCompat31Impl(contentInfoCompat0);
                return;
            }
            this.mBuilderCompat = new BuilderCompatImpl(contentInfoCompat0);
        }

        public ContentInfoCompat build() {
            return this.mBuilderCompat.build();
        }

        public Builder setClip(ClipData clipData0) {
            this.mBuilderCompat.setClip(clipData0);
            return this;
        }

        public Builder setExtras(Bundle bundle0) {
            this.mBuilderCompat.setExtras(bundle0);
            return this;
        }

        public Builder setFlags(int v) {
            this.mBuilderCompat.setFlags(v);
            return this;
        }

        public Builder setLinkUri(Uri uri0) {
            this.mBuilderCompat.setLinkUri(uri0);
            return this;
        }

        public Builder setSource(int v) {
            this.mBuilderCompat.setSource(v);
            return this;
        }
    }

    static final class BuilderCompat31Impl implements BuilderCompat {
        private final ContentInfo.Builder mPlatformBuilder;

        BuilderCompat31Impl(ClipData clipData0, int v) {
            this.mPlatformBuilder = HalfKt..ExternalSyntheticApiModelOutline0.m(clipData0, v);
        }

        BuilderCompat31Impl(ContentInfoCompat contentInfoCompat0) {
            this.mPlatformBuilder = HalfKt..ExternalSyntheticApiModelOutline0.m(contentInfoCompat0.toContentInfo());
        }

        @Override  // androidx.core.view.ContentInfoCompat$BuilderCompat
        public ContentInfoCompat build() {
            return new ContentInfoCompat(new Compat31Impl(this.mPlatformBuilder.build()));
        }

        @Override  // androidx.core.view.ContentInfoCompat$BuilderCompat
        public void setClip(ClipData clipData0) {
            this.mPlatformBuilder.setClip(clipData0);
        }

        @Override  // androidx.core.view.ContentInfoCompat$BuilderCompat
        public void setExtras(Bundle bundle0) {
            this.mPlatformBuilder.setExtras(bundle0);
        }

        @Override  // androidx.core.view.ContentInfoCompat$BuilderCompat
        public void setFlags(int v) {
            this.mPlatformBuilder.setFlags(v);
        }

        @Override  // androidx.core.view.ContentInfoCompat$BuilderCompat
        public void setLinkUri(Uri uri0) {
            this.mPlatformBuilder.setLinkUri(uri0);
        }

        @Override  // androidx.core.view.ContentInfoCompat$BuilderCompat
        public void setSource(int v) {
            this.mPlatformBuilder.setSource(v);
        }
    }

    interface BuilderCompat {
        ContentInfoCompat build();

        void setClip(ClipData arg1);

        void setExtras(Bundle arg1);

        void setFlags(int arg1);

        void setLinkUri(Uri arg1);

        void setSource(int arg1);
    }

    static final class BuilderCompatImpl implements BuilderCompat {
        ClipData mClip;
        Bundle mExtras;
        int mFlags;
        Uri mLinkUri;
        int mSource;

        BuilderCompatImpl(ClipData clipData0, int v) {
            this.mClip = clipData0;
            this.mSource = v;
        }

        BuilderCompatImpl(ContentInfoCompat contentInfoCompat0) {
            this.mClip = contentInfoCompat0.getClip();
            this.mSource = contentInfoCompat0.getSource();
            this.mFlags = contentInfoCompat0.getFlags();
            this.mLinkUri = contentInfoCompat0.getLinkUri();
            this.mExtras = contentInfoCompat0.getExtras();
        }

        @Override  // androidx.core.view.ContentInfoCompat$BuilderCompat
        public ContentInfoCompat build() {
            return new ContentInfoCompat(new CompatImpl(this));
        }

        @Override  // androidx.core.view.ContentInfoCompat$BuilderCompat
        public void setClip(ClipData clipData0) {
            this.mClip = clipData0;
        }

        @Override  // androidx.core.view.ContentInfoCompat$BuilderCompat
        public void setExtras(Bundle bundle0) {
            this.mExtras = bundle0;
        }

        @Override  // androidx.core.view.ContentInfoCompat$BuilderCompat
        public void setFlags(int v) {
            this.mFlags = v;
        }

        @Override  // androidx.core.view.ContentInfoCompat$BuilderCompat
        public void setLinkUri(Uri uri0) {
            this.mLinkUri = uri0;
        }

        @Override  // androidx.core.view.ContentInfoCompat$BuilderCompat
        public void setSource(int v) {
            this.mSource = v;
        }
    }

    static final class Compat31Impl implements Compat {
        private final ContentInfo mWrapped;

        Compat31Impl(ContentInfo contentInfo0) {
            this.mWrapped = HalfKt..ExternalSyntheticApiModelOutline0.m(Preconditions.checkNotNull(contentInfo0));
        }

        @Override  // androidx.core.view.ContentInfoCompat$Compat
        public ClipData getClip() {
            return this.mWrapped.getClip();
        }

        @Override  // androidx.core.view.ContentInfoCompat$Compat
        public Bundle getExtras() {
            return this.mWrapped.getExtras();
        }

        @Override  // androidx.core.view.ContentInfoCompat$Compat
        public int getFlags() {
            return this.mWrapped.getFlags();
        }

        @Override  // androidx.core.view.ContentInfoCompat$Compat
        public Uri getLinkUri() {
            return this.mWrapped.getLinkUri();
        }

        @Override  // androidx.core.view.ContentInfoCompat$Compat
        public int getSource() {
            return this.mWrapped.getSource();
        }

        @Override  // androidx.core.view.ContentInfoCompat$Compat
        public ContentInfo getWrapped() {
            return this.mWrapped;
        }

        @Override
        public String toString() {
            return "ContentInfoCompat{" + this.mWrapped + "}";
        }
    }

    interface Compat {
        ClipData getClip();

        Bundle getExtras();

        int getFlags();

        Uri getLinkUri();

        int getSource();

        ContentInfo getWrapped();
    }

    static final class CompatImpl implements Compat {
        private final ClipData mClip;
        private final Bundle mExtras;
        private final int mFlags;
        private final Uri mLinkUri;
        private final int mSource;

        CompatImpl(BuilderCompatImpl contentInfoCompat$BuilderCompatImpl0) {
            this.mClip = (ClipData)Preconditions.checkNotNull(contentInfoCompat$BuilderCompatImpl0.mClip);
            this.mSource = Preconditions.checkArgumentInRange(contentInfoCompat$BuilderCompatImpl0.mSource, 0, 5, "source");
            this.mFlags = Preconditions.checkFlagsArgument(contentInfoCompat$BuilderCompatImpl0.mFlags, 1);
            this.mLinkUri = contentInfoCompat$BuilderCompatImpl0.mLinkUri;
            this.mExtras = contentInfoCompat$BuilderCompatImpl0.mExtras;
        }

        @Override  // androidx.core.view.ContentInfoCompat$Compat
        public ClipData getClip() {
            return this.mClip;
        }

        @Override  // androidx.core.view.ContentInfoCompat$Compat
        public Bundle getExtras() {
            return this.mExtras;
        }

        @Override  // androidx.core.view.ContentInfoCompat$Compat
        public int getFlags() {
            return this.mFlags;
        }

        @Override  // androidx.core.view.ContentInfoCompat$Compat
        public Uri getLinkUri() {
            return this.mLinkUri;
        }

        @Override  // androidx.core.view.ContentInfoCompat$Compat
        public int getSource() {
            return this.mSource;
        }

        @Override  // androidx.core.view.ContentInfoCompat$Compat
        public ContentInfo getWrapped() {
            return null;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder0 = new StringBuilder("ContentInfoCompat{clip=");
            stringBuilder0.append(this.mClip.getDescription());
            stringBuilder0.append(", source=");
            stringBuilder0.append(ContentInfoCompat.sourceToString(this.mSource));
            stringBuilder0.append(", flags=");
            stringBuilder0.append(ContentInfoCompat.flagsToString(this.mFlags));
            String s = "";
            stringBuilder0.append((this.mLinkUri == null ? "" : ", hasLinkUri(" + this.mLinkUri.toString().length() + ")"));
            if(this.mExtras != null) {
                s = ", hasExtras";
            }
            stringBuilder0.append(s);
            stringBuilder0.append("}");
            return stringBuilder0.toString();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Source {
    }

    public static final int FLAG_CONVERT_TO_PLAIN_TEXT = 1;
    public static final int SOURCE_APP = 0;
    public static final int SOURCE_AUTOFILL = 4;
    public static final int SOURCE_CLIPBOARD = 1;
    public static final int SOURCE_DRAG_AND_DROP = 3;
    public static final int SOURCE_INPUT_METHOD = 2;
    public static final int SOURCE_PROCESS_TEXT = 5;
    private final Compat mCompat;

    ContentInfoCompat(Compat contentInfoCompat$Compat0) {
        this.mCompat = contentInfoCompat$Compat0;
    }

    static ClipData buildClipData(ClipDescription clipDescription0, List list0) {
        ClipData clipData0 = new ClipData(new ClipDescription(clipDescription0), ((ClipData.Item)list0.get(0)));
        for(int v = 1; v < list0.size(); ++v) {
            clipData0.addItem(((ClipData.Item)list0.get(v)));
        }
        return clipData0;
    }

    static String flagsToString(int v) {
        return (v & 1) == 0 ? String.valueOf(v) : "FLAG_CONVERT_TO_PLAIN_TEXT";
    }

    public ClipData getClip() {
        return this.mCompat.getClip();
    }

    public Bundle getExtras() {
        return this.mCompat.getExtras();
    }

    public int getFlags() {
        return this.mCompat.getFlags();
    }

    public Uri getLinkUri() {
        return this.mCompat.getLinkUri();
    }

    public int getSource() {
        return this.mCompat.getSource();
    }

    static Pair partition(ClipData clipData0, androidx.core.util.Predicate predicate0) {
        ArrayList arrayList0 = null;
        ArrayList arrayList1 = null;
        for(int v = 0; v < clipData0.getItemCount(); ++v) {
            ClipData.Item clipData$Item0 = clipData0.getItemAt(v);
            if(predicate0.test(clipData$Item0)) {
                if(arrayList0 == null) {
                    arrayList0 = new ArrayList();
                }
                arrayList0.add(clipData$Item0);
            }
            else {
                if(arrayList1 == null) {
                    arrayList1 = new ArrayList();
                }
                arrayList1.add(clipData$Item0);
            }
        }
        if(arrayList0 == null) {
            return Pair.create(null, clipData0);
        }
        return arrayList1 == null ? Pair.create(clipData0, null) : Pair.create(ContentInfoCompat.buildClipData(clipData0.getDescription(), arrayList0), ContentInfoCompat.buildClipData(clipData0.getDescription(), arrayList1));
    }

    public static Pair partition(ContentInfo contentInfo0, Predicate predicate0) {
        return Api31Impl.partition(contentInfo0, predicate0);
    }

    public Pair partition(androidx.core.util.Predicate predicate0) {
        ClipData clipData0 = this.mCompat.getClip();
        ContentInfoCompat contentInfoCompat0 = null;
        if(clipData0.getItemCount() == 1) {
            boolean z = predicate0.test(clipData0.getItemAt(0));
            if(!z) {
                contentInfoCompat0 = this;
            }
            return Pair.create((z ? this : null), contentInfoCompat0);
        }
        Pair pair0 = ContentInfoCompat.partition(clipData0, predicate0);
        if(pair0.first == null) {
            return Pair.create(null, this);
        }
        return pair0.second == null ? Pair.create(this, null) : Pair.create(new Builder(this).setClip(((ClipData)pair0.first)).build(), new Builder(this).setClip(((ClipData)pair0.second)).build());
    }

    static String sourceToString(int v) {
        switch(v) {
            case 0: {
                return "SOURCE_APP";
            }
            case 1: {
                return "SOURCE_CLIPBOARD";
            }
            case 2: {
                return "SOURCE_INPUT_METHOD";
            }
            case 3: {
                return "SOURCE_DRAG_AND_DROP";
            }
            case 4: {
                return "SOURCE_AUTOFILL";
            }
            case 5: {
                return "SOURCE_PROCESS_TEXT";
            }
            default: {
                return String.valueOf(v);
            }
        }
    }

    public ContentInfo toContentInfo() {
        return HalfKt..ExternalSyntheticApiModelOutline0.m(Objects.requireNonNull(this.mCompat.getWrapped()));
    }

    public static ContentInfoCompat toContentInfoCompat(ContentInfo contentInfo0) {
        return new ContentInfoCompat(new Compat31Impl(contentInfo0));
    }

    @Override
    public String toString() {
        return this.mCompat.toString();
    }
}

