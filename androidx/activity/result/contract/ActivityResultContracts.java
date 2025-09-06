package androidx.activity.result.contract;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.ext.SdkExtensions;
import android.provider.MediaStore;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0013\u0018\u00002\u00020\u0001:\u0011\u0003\u0004\u0005\u0006\u0007\b\t\n\u000B\f\r\u000E\u000F\u0010\u0011\u0012\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0014"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts;", "", "()V", "CaptureVideo", "CreateDocument", "GetContent", "GetMultipleContents", "OpenDocument", "OpenDocumentTree", "OpenMultipleDocuments", "PickContact", "PickMultipleVisualMedia", "PickVisualMedia", "RequestMultiplePermissions", "RequestPermission", "StartActivityForResult", "StartIntentSenderForResult", "TakePicture", "TakePicturePreview", "TakeVideo", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ActivityResultContracts {
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002H\u0017J\u001E\u0010\n\u001A\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000B2\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002J\u001D\u0010\f\u001A\u00020\u00032\u0006\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$CaptureVideo;", "Landroidx/activity/result/contract/ActivityResultContract;", "Landroid/net/Uri;", "", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "(ILandroid/content/Intent;)Ljava/lang/Boolean;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class CaptureVideo extends ActivityResultContract {
        public Intent createIntent(Context context0, Uri uri0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(uri0, "input");
            Intent intent0 = new Intent("android.media.action.VIDEO_CAPTURE").putExtra("output", uri0);
            Intrinsics.checkNotNullExpressionValue(intent0, "Intent(MediaStore.ACTION…tore.EXTRA_OUTPUT, input)");
            return intent0;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((Uri)object0));
        }

        public final SynchronousResult getSynchronousResult(Context context0, Uri uri0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(uri0, "input");
            return null;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((Uri)object0));
        }

        public final Boolean parseResult(int v, Intent intent0) {
            return v == -1;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u0007\b\u0017¢\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0005\u001A\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u0002H\u0017J \u0010\f\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\r2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u0002J\u001A\u0010\u000E\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u000F\u001A\u00020\u00102\b\u0010\u0011\u001A\u0004\u0018\u00010\bR\u000E\u0010\u0005\u001A\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$CreateDocument;", "Landroidx/activity/result/contract/ActivityResultContract;", "", "Landroid/net/Uri;", "()V", "mimeType", "(Ljava/lang/String;)V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class CreateDocument extends ActivityResultContract {
        private final String mimeType;

        @Deprecated(message = "Using a wildcard mime type with CreateDocument is not recommended as it breaks the automatic handling of file extensions. Instead, specify the mime type by using the constructor that takes an concrete mime type (e.g.., CreateDocument(\"image/png\")).", replaceWith = @ReplaceWith(expression = "CreateDocument(\"todo/todo\")", imports = {}))
        public CreateDocument() {
            this("*/*");
        }

        public CreateDocument(String s) {
            Intrinsics.checkNotNullParameter(s, "mimeType");
            super();
            this.mimeType = s;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((String)object0));
        }

        public Intent createIntent(Context context0, String s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(s, "input");
            Intent intent0 = new Intent("android.intent.action.CREATE_DOCUMENT").setType(this.mimeType).putExtra("android.intent.extra.TITLE", s);
            Intrinsics.checkNotNullExpressionValue(intent0, "Intent(Intent.ACTION_CRE…ntent.EXTRA_TITLE, input)");
            return intent0;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((String)object0));
        }

        public final SynchronousResult getSynchronousResult(Context context0, String s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(s, "input");
            return null;
        }

        public final Uri parseResult(int v, Intent intent0) {
            if(v != -1) {
                intent0 = null;
            }
            return intent0 == null ? null : intent0.getData();
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002H\u0017J \u0010\n\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000B2\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002J\u001A\u0010\f\u001A\u0004\u0018\u00010\u00032\u0006\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0006¨\u0006\u0010"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$GetContent;", "Landroidx/activity/result/contract/ActivityResultContract;", "", "Landroid/net/Uri;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class GetContent extends ActivityResultContract {
        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((String)object0));
        }

        public Intent createIntent(Context context0, String s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(s, "input");
            Intent intent0 = new Intent("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE").setType(s);
            Intrinsics.checkNotNullExpressionValue(intent0, "Intent(Intent.ACTION_GET…          .setType(input)");
            return intent0;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((String)object0));
        }

        public final SynchronousResult getSynchronousResult(Context context0, String s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(s, "input");
            return null;
        }

        public final Uri parseResult(int v, Intent intent0) {
            if(v != -1) {
                intent0 = null;
            }
            return intent0 == null ? null : intent0.getData();
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00122\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u000F\u0012\r\u0012\t\u0012\u00070\u0004¢\u0006\u0002\b\u00050\u00030\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u0002H\u0017J$\u0010\f\u001A\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\r2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u0002J\u001E\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u000F\u001A\u00020\u00102\b\u0010\u0011\u001A\u0004\u0018\u00010\b¨\u0006\u0013"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$GetMultipleContents;", "Landroidx/activity/result/contract/ActivityResultContract;", "", "", "Landroid/net/Uri;", "Lkotlin/jvm/JvmSuppressWildcards;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "Companion", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class GetMultipleContents extends ActivityResultContract {
        @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004*\u00020\u0006H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$GetMultipleContents$Companion;", "", "()V", "getClipDataUris", "", "Landroid/net/Uri;", "Landroid/content/Intent;", "getClipDataUris$activity_release", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class Companion {
            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            public final List getClipDataUris$activity_release(Intent intent0) {
                Intrinsics.checkNotNullParameter(intent0, "<this>");
                LinkedHashSet linkedHashSet0 = new LinkedHashSet();
                Uri uri0 = intent0.getData();
                if(uri0 != null) {
                    linkedHashSet0.add(uri0);
                }
                ClipData clipData0 = intent0.getClipData();
                if(clipData0 == null && linkedHashSet0.isEmpty()) {
                    return CollectionsKt.emptyList();
                }
                if(clipData0 != null) {
                    int v = clipData0.getItemCount();
                    for(int v1 = 0; v1 < v; ++v1) {
                        Uri uri1 = clipData0.getItemAt(v1).getUri();
                        if(uri1 != null) {
                            linkedHashSet0.add(uri1);
                        }
                    }
                }
                return new ArrayList(linkedHashSet0);
            }
        }

        public static final Companion Companion;

        static {
            GetMultipleContents.Companion = new Companion(null);
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((String)object0));
        }

        public Intent createIntent(Context context0, String s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(s, "input");
            Intent intent0 = new Intent("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE").setType(s).putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
            Intrinsics.checkNotNullExpressionValue(intent0, "Intent(Intent.ACTION_GET…TRA_ALLOW_MULTIPLE, true)");
            return intent0;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((String)object0));
        }

        public final SynchronousResult getSynchronousResult(Context context0, String s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(s, "input");
            return null;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }

        public final List parseResult(int v, Intent intent0) {
            if(v != -1) {
                intent0 = null;
            }
            if(intent0 != null) {
                List list0 = GetMultipleContents.Companion.getClipDataUris$activity_release(intent0);
                return list0 == null ? CollectionsKt.emptyList() : list0;
            }
            return CollectionsKt.emptyList();
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0001B\u0005¢\u0006\u0002\u0010\u0005J#\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\f\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0017¢\u0006\u0002\u0010\u000BJ+\u0010\f\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\r2\u0006\u0010\b\u001A\u00020\t2\f\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\u0010\u000EJ\u001A\u0010\u000F\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001A\u00020\u00112\b\u0010\u0012\u001A\u0004\u0018\u00010\u0007¨\u0006\u0013"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$OpenDocument;", "Landroidx/activity/result/contract/ActivityResultContract;", "", "", "Landroid/net/Uri;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "(Landroid/content/Context;[Ljava/lang/String;)Landroid/content/Intent;", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "(Landroid/content/Context;[Ljava/lang/String;)Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class OpenDocument extends ActivityResultContract {
        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((String[])object0));
        }

        public Intent createIntent(Context context0, String[] arr_s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(arr_s, "input");
            Intent intent0 = new Intent("android.intent.action.OPEN_DOCUMENT").putExtra("android.intent.extra.MIME_TYPES", arr_s).setType("*/*");
            Intrinsics.checkNotNullExpressionValue(intent0, "Intent(Intent.ACTION_OPE…          .setType(\"*/*\")");
            return intent0;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((String[])object0));
        }

        public final SynchronousResult getSynchronousResult(Context context0, String[] arr_s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(arr_s, "input");
            return null;
        }

        public final Uri parseResult(int v, Intent intent0) {
            if(v != -1) {
                intent0 = null;
            }
            return intent0 == null ? null : intent0.getData();
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0017\u0018\u00002\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001A\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\b\u0010\b\u001A\u0004\u0018\u00010\u0002H\u0017J\"\u0010\t\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\n2\u0006\u0010\u0006\u001A\u00020\u00072\b\u0010\b\u001A\u0004\u0018\u00010\u0002J\u001A\u0010\u000B\u001A\u0004\u0018\u00010\u00022\u0006\u0010\f\u001A\u00020\r2\b\u0010\u000E\u001A\u0004\u0018\u00010\u0005¨\u0006\u000F"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$OpenDocumentTree;", "Landroidx/activity/result/contract/ActivityResultContract;", "Landroid/net/Uri;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class OpenDocumentTree extends ActivityResultContract {
        public Intent createIntent(Context context0, Uri uri0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intent intent0 = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            if(Build.VERSION.SDK_INT >= 26 && uri0 != null) {
                intent0.putExtra("android.provider.extra.INITIAL_URI", uri0);
            }
            return intent0;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((Uri)object0));
        }

        public final SynchronousResult getSynchronousResult(Context context0, Uri uri0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            return null;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((Uri)object0));
        }

        public final Uri parseResult(int v, Intent intent0) {
            if(v != -1) {
                intent0 = null;
            }
            return intent0 == null ? null : intent0.getData();
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u001F\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u000F\u0012\r\u0012\t\u0012\u00070\u0005¢\u0006\u0002\b\u00060\u00040\u0001B\u0005¢\u0006\u0002\u0010\u0007J#\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0017¢\u0006\u0002\u0010\rJ/\u0010\u000E\u001A\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u000F2\u0006\u0010\n\u001A\u00020\u000B2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\u0010\u0010J\u001E\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0012\u001A\u00020\u00132\b\u0010\u0014\u001A\u0004\u0018\u00010\t¨\u0006\u0015"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$OpenMultipleDocuments;", "Landroidx/activity/result/contract/ActivityResultContract;", "", "", "", "Landroid/net/Uri;", "Lkotlin/jvm/JvmSuppressWildcards;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "(Landroid/content/Context;[Ljava/lang/String;)Landroid/content/Intent;", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "(Landroid/content/Context;[Ljava/lang/String;)Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class OpenMultipleDocuments extends ActivityResultContract {
        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((String[])object0));
        }

        public Intent createIntent(Context context0, String[] arr_s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(arr_s, "input");
            Intent intent0 = new Intent("android.intent.action.OPEN_DOCUMENT").putExtra("android.intent.extra.MIME_TYPES", arr_s).putExtra("android.intent.extra.ALLOW_MULTIPLE", true).setType("*/*");
            Intrinsics.checkNotNullExpressionValue(intent0, "Intent(Intent.ACTION_OPE…          .setType(\"*/*\")");
            return intent0;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((String[])object0));
        }

        public final SynchronousResult getSynchronousResult(Context context0, String[] arr_s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(arr_s, "input");
            return null;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }

        public final List parseResult(int v, Intent intent0) {
            if(v != -1) {
                intent0 = null;
            }
            if(intent0 != null) {
                List list0 = GetMultipleContents.Companion.getClipDataUris$activity_release(intent0);
                return list0 == null ? CollectionsKt.emptyList() : list0;
            }
            return CollectionsKt.emptyList();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001A\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\b\u0010\t\u001A\u0004\u0018\u00010\u0002H\u0016J\u001C\u0010\n\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u000B\u001A\u00020\f2\b\u0010\r\u001A\u0004\u0018\u00010\u0006H\u0016¨\u0006\u000E"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickContact;", "Landroidx/activity/result/contract/ActivityResultContract;", "Ljava/lang/Void;", "Landroid/net/Uri;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "parseResult", "resultCode", "", "intent", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class PickContact extends ActivityResultContract {
        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((Void)object0));
        }

        public Intent createIntent(Context context0, Void void0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intent intent0 = new Intent("android.intent.action.PICK").setType("vnd.android.cursor.dir/contact");
            Intrinsics.checkNotNullExpressionValue(intent0, "Intent(Intent.ACTION_PIC…ct.Contacts.CONTENT_TYPE)");
            return intent0;
        }

        public Uri parseResult(int v, Intent intent0) {
            if(v != -1) {
                intent0 = null;
            }
            return intent0 == null ? null : intent0.getData();
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00132\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u000F\u0012\r\u0012\t\u0012\u00070\u0004¢\u0006\u0002\b\u00050\u00030\u0001:\u0001\u0013B\u000F\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u0002H\u0017J)\u0010\u000E\u001A\u0015\u0012\u000F\u0012\r\u0012\t\u0012\u00070\u0004¢\u0006\u0002\b\u00050\u0003\u0018\u00010\u000F2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u0002J\u001E\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0011\u001A\u00020\u00072\b\u0010\u0012\u001A\u0004\u0018\u00010\nR\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickMultipleVisualMedia;", "Landroidx/activity/result/contract/ActivityResultContract;", "Landroidx/activity/result/PickVisualMediaRequest;", "", "Landroid/net/Uri;", "Lkotlin/jvm/JvmSuppressWildcards;", "maxItems", "", "(I)V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "intent", "Companion", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class PickMultipleVisualMedia extends ActivityResultContract {
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0003\u001A\u00020\u0004H\u0001¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickMultipleVisualMedia$Companion;", "", "()V", "getMaxItems", "", "getMaxItems$activity_release", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia.Companion {
            private androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia.Companion() {
            }

            public androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            // 去混淆评级： 低(20)
            public final int getMaxItems$activity_release() {
                return PickVisualMedia.Companion.isSystemPickerAvailable$activity_release() ? MediaStore.getPickImagesMaxLimit() : 0x7FFFFFFF;
            }
        }

        public static final androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia.Companion Companion;
        private final int maxItems;

        static {
            PickMultipleVisualMedia.Companion = new androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia.Companion(null);
        }

        public PickMultipleVisualMedia() {
            this(0, 1, null);
        }

        public PickMultipleVisualMedia(int v) {
            this.maxItems = v;
            if(v <= 1) {
                throw new IllegalArgumentException("Max items must be higher than 1");
            }
        }

        public PickMultipleVisualMedia(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
            if((v1 & 1) != 0) {
                v = PickMultipleVisualMedia.Companion.getMaxItems$activity_release();
            }
            this(v);
        }

        public Intent createIntent(Context context0, PickVisualMediaRequest pickVisualMediaRequest0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(pickVisualMediaRequest0, "input");
            if(PickVisualMedia.Companion.isSystemPickerAvailable$activity_release()) {
                Intent intent0 = new Intent("android.provider.action.PICK_IMAGES");
                intent0.setType(PickVisualMedia.Companion.getVisualMimeType$activity_release(pickVisualMediaRequest0.getMediaType()));
                int v = Math.min(this.maxItems, pickVisualMediaRequest0.getMaxItems());
                if(v <= 1 || v > MediaStore.getPickImagesMaxLimit()) {
                    throw new IllegalArgumentException("Max items must be greater than 1 and lesser than or equal to MediaStore.getPickImagesMaxLimit()");
                }
                intent0.putExtra("android.provider.extra.PICK_IMAGES_MAX", v);
                intent0.putExtra("android.provider.extra.PICK_IMAGES_LAUNCH_TAB", pickVisualMediaRequest0.getDefaultTab().getValue());
                intent0.putExtra("android.provider.extra.PICK_IMAGES_IN_ORDER", pickVisualMediaRequest0.isOrderedSelection());
                if(pickVisualMediaRequest0.isCustomAccentColorApplied()) {
                    intent0.putExtra("android.provider.extra.PICK_IMAGES_ACCENT_COLOR", pickVisualMediaRequest0.getAccentColor());
                }
                return intent0;
            }
            if(PickVisualMedia.Companion.isSystemFallbackPickerAvailable$activity_release(context0)) {
                ResolveInfo resolveInfo0 = PickVisualMedia.Companion.getSystemFallbackPicker$activity_release(context0);
                if(resolveInfo0 == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                ActivityInfo activityInfo0 = resolveInfo0.activityInfo;
                Intent intent1 = new Intent("androidx.activity.result.contract.action.PICK_IMAGES");
                intent1.setClassName(activityInfo0.applicationInfo.packageName, activityInfo0.name);
                intent1.setType(PickVisualMedia.Companion.getVisualMimeType$activity_release(pickVisualMediaRequest0.getMediaType()));
                int v1 = Math.min(this.maxItems, pickVisualMediaRequest0.getMaxItems());
                if(v1 <= 1) {
                    throw new IllegalArgumentException("Max items must be greater than 1");
                }
                intent1.putExtra("androidx.activity.result.contract.extra.PICK_IMAGES_MAX", v1);
                intent1.putExtra("androidx.activity.result.contract.extra.PICK_IMAGES_LAUNCH_TAB", pickVisualMediaRequest0.getDefaultTab().getValue());
                intent1.putExtra("androidx.activity.result.contract.extra.PICK_IMAGES_IN_ORDER", pickVisualMediaRequest0.isOrderedSelection());
                if(pickVisualMediaRequest0.isCustomAccentColorApplied()) {
                    intent1.putExtra("androidx.activity.result.contract.extra.PICK_IMAGES_ACCENT_COLOR", pickVisualMediaRequest0.getAccentColor());
                }
                return intent1;
            }
            Intent intent2 = new Intent("android.intent.action.OPEN_DOCUMENT");
            intent2.setType(PickVisualMedia.Companion.getVisualMimeType$activity_release(pickVisualMediaRequest0.getMediaType()));
            intent2.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
            if(intent2.getType() == null) {
                intent2.setType("*/*");
                intent2.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
            }
            return intent2;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((PickVisualMediaRequest)object0));
        }

        public final SynchronousResult getSynchronousResult(Context context0, PickVisualMediaRequest pickVisualMediaRequest0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(pickVisualMediaRequest0, "input");
            return null;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((PickVisualMediaRequest)object0));
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }

        public final List parseResult(int v, Intent intent0) {
            if(v != -1) {
                intent0 = null;
            }
            if(intent0 != null) {
                List list0 = GetMultipleContents.Companion.getClipDataUris$activity_release(intent0);
                return list0 == null ? CollectionsKt.emptyList() : list0;
            }
            return CollectionsKt.emptyList();
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0016\u0018\u0000 \u00102\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001:\u0007\u0010\u0011\u0012\u0013\u0014\u0015\u0016B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002H\u0017J \u0010\n\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000B2\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002J\u001A\u0010\f\u001A\u0004\u0018\u00010\u00032\u0006\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0006¨\u0006\u0017"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia;", "Landroidx/activity/result/contract/ActivityResultContract;", "Landroidx/activity/result/PickVisualMediaRequest;", "Landroid/net/Uri;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "Companion", "DefaultTab", "ImageAndVideo", "ImageOnly", "SingleMimeType", "VideoOnly", "VisualMediaType", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class PickVisualMedia extends ActivityResultContract {
        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0010\u001A\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001A\u00020\u0013H\u0001¢\u0006\u0002\b\u0014J\u0017\u0010\u0015\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0016\u001A\u00020\u0017H\u0000¢\u0006\u0002\b\u0018J\b\u0010\u0019\u001A\u00020\u001AH\u0007J\u0010\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u0012\u001A\u00020\u0013H\u0007J\u0015\u0010\u001B\u001A\u00020\u001A2\u0006\u0010\u0012\u001A\u00020\u0013H\u0001¢\u0006\u0002\b\u001CJ\r\u0010\u001D\u001A\u00020\u001AH\u0001¢\u0006\u0002\b\u001ER\u0014\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0014\u0010\u0006\u001A\u00020\u0004X\u0086T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0014\u0010\b\u001A\u00020\u0004X\u0086T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u0014\u0010\n\u001A\u00020\u0004X\u0086T¢\u0006\b\n\u0000\u0012\u0004\b\u000B\u0010\u0002R\u0014\u0010\f\u001A\u00020\u0004X\u0086T¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0002R\u000E\u0010\u000E\u001A\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001F"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$Companion;", "", "()V", "ACTION_SYSTEM_FALLBACK_PICK_IMAGES", "", "getACTION_SYSTEM_FALLBACK_PICK_IMAGES$annotations", "EXTRA_SYSTEM_FALLBACK_PICK_IMAGES_ACCENT_COLOR", "getEXTRA_SYSTEM_FALLBACK_PICK_IMAGES_ACCENT_COLOR$annotations", "EXTRA_SYSTEM_FALLBACK_PICK_IMAGES_IN_ORDER", "getEXTRA_SYSTEM_FALLBACK_PICK_IMAGES_IN_ORDER$annotations", "EXTRA_SYSTEM_FALLBACK_PICK_IMAGES_LAUNCH_TAB", "getEXTRA_SYSTEM_FALLBACK_PICK_IMAGES_LAUNCH_TAB$annotations", "EXTRA_SYSTEM_FALLBACK_PICK_IMAGES_MAX", "getEXTRA_SYSTEM_FALLBACK_PICK_IMAGES_MAX$annotations", "GMS_ACTION_PICK_IMAGES", "GMS_EXTRA_PICK_IMAGES_MAX", "getSystemFallbackPicker", "Landroid/content/pm/ResolveInfo;", "context", "Landroid/content/Context;", "getSystemFallbackPicker$activity_release", "getVisualMimeType", "input", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "getVisualMimeType$activity_release", "isPhotoPickerAvailable", "", "isSystemFallbackPickerAvailable", "isSystemFallbackPickerAvailable$activity_release", "isSystemPickerAvailable", "isSystemPickerAvailable$activity_release", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.Companion {
            private androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.Companion() {
            }

            public androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            public static void getACTION_SYSTEM_FALLBACK_PICK_IMAGES$annotations() {
            }

            public static void getEXTRA_SYSTEM_FALLBACK_PICK_IMAGES_ACCENT_COLOR$annotations() {
            }

            public static void getEXTRA_SYSTEM_FALLBACK_PICK_IMAGES_IN_ORDER$annotations() {
            }

            public static void getEXTRA_SYSTEM_FALLBACK_PICK_IMAGES_LAUNCH_TAB$annotations() {
            }

            public static void getEXTRA_SYSTEM_FALLBACK_PICK_IMAGES_MAX$annotations() {
            }

            @JvmStatic
            public final ResolveInfo getSystemFallbackPicker$activity_release(Context context0) {
                Intrinsics.checkNotNullParameter(context0, "context");
                return context0.getPackageManager().resolveActivity(new Intent("androidx.activity.result.contract.action.PICK_IMAGES"), 0x110000);
            }

            public final String getVisualMimeType$activity_release(VisualMediaType activityResultContracts$PickVisualMedia$VisualMediaType0) {
                Intrinsics.checkNotNullParameter(activityResultContracts$PickVisualMedia$VisualMediaType0, "input");
                if(activityResultContracts$PickVisualMedia$VisualMediaType0 instanceof ImageOnly) {
                    return "image/*";
                }
                if(activityResultContracts$PickVisualMedia$VisualMediaType0 instanceof VideoOnly) {
                    return "video/*";
                }
                if(activityResultContracts$PickVisualMedia$VisualMediaType0 instanceof SingleMimeType) {
                    return ((SingleMimeType)activityResultContracts$PickVisualMedia$VisualMediaType0).getMimeType();
                }
                if(!(activityResultContracts$PickVisualMedia$VisualMediaType0 instanceof ImageAndVideo)) {
                    throw new NoWhenBranchMatchedException();
                }
                return null;
            }

            @Deprecated(message = "This method is deprecated in favor of isPhotoPickerAvailable(context) to support the picker provided by updatable system apps", replaceWith = @ReplaceWith(expression = "isPhotoPickerAvailable(context)", imports = {}))
            @JvmStatic
            public final boolean isPhotoPickerAvailable() {
                return this.isSystemPickerAvailable$activity_release();
            }

            @JvmStatic
            public final boolean isPhotoPickerAvailable(Context context0) {
                Intrinsics.checkNotNullParameter(context0, "context");
                return this.isSystemPickerAvailable$activity_release() || this.isSystemFallbackPickerAvailable$activity_release(context0);
            }

            @JvmStatic
            public final boolean isSystemFallbackPickerAvailable$activity_release(Context context0) {
                Intrinsics.checkNotNullParameter(context0, "context");
                return this.getSystemFallbackPicker$activity_release(context0) != null;
            }

            @JvmStatic
            public final boolean isSystemPickerAvailable$activity_release() {
                return Build.VERSION.SDK_INT < 33 ? Build.VERSION.SDK_INT >= 30 && SdkExtensions.getExtensionVersion(30) >= 2 : true;
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001A\u00020\u0004X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$DefaultTab;", "", "()V", "value", "", "getValue", "()I", "AlbumsTab", "PhotosTab", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static abstract class DefaultTab {
            @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001A\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$DefaultTab$AlbumsTab;", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$DefaultTab;", "()V", "value", "", "getValue", "()I", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
            public static final class AlbumsTab extends DefaultTab {
                public static final AlbumsTab INSTANCE;
                private static final int value;

                static {
                    AlbumsTab.INSTANCE = new AlbumsTab();
                }

                private AlbumsTab() {
                    super(null);
                }

                @Override  // androidx.activity.result.contract.ActivityResultContracts$PickVisualMedia$DefaultTab
                public int getValue() {
                    return AlbumsTab.value;
                }
            }

            @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001A\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$DefaultTab$PhotosTab;", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$DefaultTab;", "()V", "value", "", "getValue", "()I", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
            public static final class PhotosTab extends DefaultTab {
                public static final PhotosTab INSTANCE;
                private static final int value;

                static {
                    PhotosTab.INSTANCE = new PhotosTab();
                    PhotosTab.value = 1;
                }

                private PhotosTab() {
                    super(null);
                }

                @Override  // androidx.activity.result.contract.ActivityResultContracts$PickVisualMedia$DefaultTab
                public int getValue() {
                    return PhotosTab.value;
                }
            }

            private DefaultTab() {
            }

            public DefaultTab(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            public abstract int getValue();
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$ImageAndVideo;", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "()V", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class ImageAndVideo implements VisualMediaType {
            public static final ImageAndVideo INSTANCE;

            static {
                ImageAndVideo.INSTANCE = new ImageAndVideo();
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$ImageOnly;", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "()V", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class ImageOnly implements VisualMediaType {
            public static final ImageOnly INSTANCE;

            static {
                ImageOnly.INSTANCE = new ImageOnly();
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$SingleMimeType;", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "mimeType", "", "(Ljava/lang/String;)V", "getMimeType", "()Ljava/lang/String;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class SingleMimeType implements VisualMediaType {
            private final String mimeType;

            public SingleMimeType(String s) {
                Intrinsics.checkNotNullParameter(s, "mimeType");
                super();
                this.mimeType = s;
            }

            public final String getMimeType() {
                return this.mimeType;
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VideoOnly;", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "()V", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class VideoOnly implements VisualMediaType {
            public static final VideoOnly INSTANCE;

            static {
                VideoOnly.INSTANCE = new VideoOnly();
            }
        }

        @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001\u0082\u0001\u0004\u0002\u0003\u0004\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$ImageAndVideo;", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$ImageOnly;", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$SingleMimeType;", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VideoOnly;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public interface VisualMediaType {
        }

        public static final String ACTION_SYSTEM_FALLBACK_PICK_IMAGES = "androidx.activity.result.contract.action.PICK_IMAGES";
        public static final androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.Companion Companion = null;
        public static final String EXTRA_SYSTEM_FALLBACK_PICK_IMAGES_ACCENT_COLOR = "androidx.activity.result.contract.extra.PICK_IMAGES_ACCENT_COLOR";
        public static final String EXTRA_SYSTEM_FALLBACK_PICK_IMAGES_IN_ORDER = "androidx.activity.result.contract.extra.PICK_IMAGES_IN_ORDER";
        public static final String EXTRA_SYSTEM_FALLBACK_PICK_IMAGES_LAUNCH_TAB = "androidx.activity.result.contract.extra.PICK_IMAGES_LAUNCH_TAB";
        public static final String EXTRA_SYSTEM_FALLBACK_PICK_IMAGES_MAX = "androidx.activity.result.contract.extra.PICK_IMAGES_MAX";
        public static final String GMS_ACTION_PICK_IMAGES = "com.google.android.gms.provider.action.PICK_IMAGES";
        public static final String GMS_EXTRA_PICK_IMAGES_MAX = "com.google.android.gms.provider.extra.PICK_IMAGES_MAX";

        static {
            PickVisualMedia.Companion = new androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.Companion(null);
        }

        public Intent createIntent(Context context0, PickVisualMediaRequest pickVisualMediaRequest0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(pickVisualMediaRequest0, "input");
            androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.Companion activityResultContracts$PickVisualMedia$Companion0 = PickVisualMedia.Companion;
            if(activityResultContracts$PickVisualMedia$Companion0.isSystemPickerAvailable$activity_release()) {
                Intent intent0 = new Intent("android.provider.action.PICK_IMAGES");
                intent0.setType(activityResultContracts$PickVisualMedia$Companion0.getVisualMimeType$activity_release(pickVisualMediaRequest0.getMediaType()));
                intent0.putExtra("android.provider.extra.PICK_IMAGES_LAUNCH_TAB", pickVisualMediaRequest0.getDefaultTab().getValue());
                if(pickVisualMediaRequest0.isCustomAccentColorApplied()) {
                    intent0.putExtra("android.provider.extra.PICK_IMAGES_ACCENT_COLOR", pickVisualMediaRequest0.getAccentColor());
                }
                return intent0;
            }
            if(activityResultContracts$PickVisualMedia$Companion0.isSystemFallbackPickerAvailable$activity_release(context0)) {
                ResolveInfo resolveInfo0 = activityResultContracts$PickVisualMedia$Companion0.getSystemFallbackPicker$activity_release(context0);
                if(resolveInfo0 == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                ActivityInfo activityInfo0 = resolveInfo0.activityInfo;
                Intent intent1 = new Intent("androidx.activity.result.contract.action.PICK_IMAGES");
                intent1.setClassName(activityInfo0.applicationInfo.packageName, activityInfo0.name);
                intent1.setType(activityResultContracts$PickVisualMedia$Companion0.getVisualMimeType$activity_release(pickVisualMediaRequest0.getMediaType()));
                intent1.putExtra("androidx.activity.result.contract.extra.PICK_IMAGES_LAUNCH_TAB", pickVisualMediaRequest0.getDefaultTab().getValue());
                if(pickVisualMediaRequest0.isCustomAccentColorApplied()) {
                    intent1.putExtra("androidx.activity.result.contract.extra.PICK_IMAGES_ACCENT_COLOR", pickVisualMediaRequest0.getAccentColor());
                }
                return intent1;
            }
            Intent intent2 = new Intent("android.intent.action.OPEN_DOCUMENT");
            intent2.setType(activityResultContracts$PickVisualMedia$Companion0.getVisualMimeType$activity_release(pickVisualMediaRequest0.getMediaType()));
            if(intent2.getType() == null) {
                intent2.setType("*/*");
                intent2.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
            }
            return intent2;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((PickVisualMediaRequest)object0));
        }

        public final SynchronousResult getSynchronousResult(Context context0, PickVisualMediaRequest pickVisualMediaRequest0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(pickVisualMediaRequest0, "input");
            return null;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((PickVisualMediaRequest)object0));
        }

        @JvmStatic
        public static final ResolveInfo getSystemFallbackPicker$activity_release(Context context0) {
            return PickVisualMedia.Companion.getSystemFallbackPicker$activity_release(context0);
        }

        @Deprecated(message = "This method is deprecated in favor of isPhotoPickerAvailable(context) to support the picker provided by updatable system apps", replaceWith = @ReplaceWith(expression = "isPhotoPickerAvailable(context)", imports = {}))
        @JvmStatic
        public static final boolean isPhotoPickerAvailable() {
            return PickVisualMedia.Companion.isPhotoPickerAvailable();
        }

        @JvmStatic
        public static final boolean isPhotoPickerAvailable(Context context0) {
            return PickVisualMedia.Companion.isPhotoPickerAvailable(context0);
        }

        @JvmStatic
        public static final boolean isSystemFallbackPickerAvailable$activity_release(Context context0) {
            return PickVisualMedia.Companion.isSystemFallbackPickerAvailable$activity_release(context0);
        }

        @JvmStatic
        public static final boolean isSystemPickerAvailable$activity_release() {
            return PickVisualMedia.Companion.isSystemPickerAvailable$activity_release();
        }

        public final Uri parseResult(int v, Intent intent0) {
            if(v != -1) {
                intent0 = null;
            }
            if(intent0 != null) {
                Uri uri0 = intent0.getData();
                return uri0 == null ? ((Uri)CollectionsKt.firstOrNull(GetMultipleContents.Companion.getClipDataUris$activity_release(intent0))) : uri0;
            }
            return null;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\u0010$\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00152%\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0003\u0012\t\u0012\u00070\u0005¢\u0006\u0002\b\u00060\u00040\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0007J#\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¢\u0006\u0002\u0010\rJ7\u0010\u000E\u001A\u0016\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u000F2\u0006\u0010\n\u001A\u00020\u000B2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¢\u0006\u0002\u0010\u0010J&\u0010\u0011\u001A\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0012\u001A\u00020\u00132\b\u0010\u0014\u001A\u0004\u0018\u00010\tH\u0016¨\u0006\u0016"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$RequestMultiplePermissions;", "Landroidx/activity/result/contract/ActivityResultContract;", "", "", "", "", "Lkotlin/jvm/JvmSuppressWildcards;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "(Landroid/content/Context;[Ljava/lang/String;)Landroid/content/Intent;", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "(Landroid/content/Context;[Ljava/lang/String;)Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "Companion", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class RequestMultiplePermissions extends ActivityResultContract {
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001D\u0010\u0007\u001A\u00020\b2\f\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00040\nH\u0000¢\u0006\u0004\b\u000B\u0010\fR\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$RequestMultiplePermissions$Companion;", "", "()V", "ACTION_REQUEST_PERMISSIONS", "", "EXTRA_PERMISSIONS", "EXTRA_PERMISSION_GRANT_RESULTS", "createIntent", "Landroid/content/Intent;", "input", "", "createIntent$activity_release", "([Ljava/lang/String;)Landroid/content/Intent;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions.Companion {
            private androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions.Companion() {
            }

            public androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            public final Intent createIntent$activity_release(String[] arr_s) {
                Intrinsics.checkNotNullParameter(arr_s, "input");
                Intent intent0 = new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", arr_s);
                Intrinsics.checkNotNullExpressionValue(intent0, "Intent(ACTION_REQUEST_PE…EXTRA_PERMISSIONS, input)");
                return intent0;
            }
        }

        public static final String ACTION_REQUEST_PERMISSIONS = "androidx.activity.result.contract.action.REQUEST_PERMISSIONS";
        public static final androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions.Companion Companion = null;
        public static final String EXTRA_PERMISSIONS = "androidx.activity.result.contract.extra.PERMISSIONS";
        public static final String EXTRA_PERMISSION_GRANT_RESULTS = "androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS";

        static {
            RequestMultiplePermissions.Companion = new androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions.Companion(null);
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((String[])object0));
        }

        public Intent createIntent(Context context0, String[] arr_s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(arr_s, "input");
            return RequestMultiplePermissions.Companion.createIntent$activity_release(arr_s);
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((String[])object0));
        }

        public SynchronousResult getSynchronousResult(Context context0, String[] arr_s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(arr_s, "input");
            if(arr_s.length == 0) {
                return new SynchronousResult(MapsKt.emptyMap());
            }
            int v1 = 0;
            while(v1 < arr_s.length) {
                if(ContextCompat.checkSelfPermission(context0, arr_s[v1]) == 0) {
                    ++v1;
                    continue;
                }
                return null;
            }
            Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(arr_s.length), 16));
            for(int v = 0; v < arr_s.length; ++v) {
                Pair pair0 = TuplesKt.to(arr_s[v], Boolean.TRUE);
                map0.put(pair0.getFirst(), pair0.getSecond());
            }
            return new SynchronousResult(map0);
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }

        public Map parseResult(int v, Intent intent0) {
            if(v != -1) {
                return MapsKt.emptyMap();
            }
            if(intent0 == null) {
                return MapsKt.emptyMap();
            }
            String[] arr_s = intent0.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
            int[] arr_v = intent0.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
            if(arr_v != null && arr_s != null) {
                ArrayList arrayList0 = new ArrayList(arr_v.length);
                for(int v1 = 0; v1 < arr_v.length; ++v1) {
                    arrayList0.add(Boolean.valueOf(arr_v[v1] == 0));
                }
                return MapsKt.toMap(CollectionsKt.zip(ArraysKt.filterNotNull(arr_s), arrayList0));
            }
            return MapsKt.emptyMap();
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002H\u0016J \u0010\n\u001A\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000B2\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002H\u0016J\u001F\u0010\f\u001A\u00020\u00032\u0006\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$RequestPermission;", "Landroidx/activity/result/contract/ActivityResultContract;", "", "", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "(ILandroid/content/Intent;)Ljava/lang/Boolean;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class RequestPermission extends ActivityResultContract {
        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((String)object0));
        }

        public Intent createIntent(Context context0, String s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(s, "input");
            return RequestMultiplePermissions.Companion.createIntent$activity_release(new String[]{s});
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((String)object0));
        }

        public SynchronousResult getSynchronousResult(Context context0, String s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(s, "input");
            return ContextCompat.checkSelfPermission(context0, s) == 0 ? new SynchronousResult(Boolean.TRUE) : null;
        }

        public Boolean parseResult(int v, Intent intent0) {
            if(intent0 != null && v == -1) {
                int[] arr_v = intent0.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
                if(arr_v != null) {
                    for(int v1 = 0; v1 < arr_v.length; ++v1) {
                        if(arr_v[v1] == 0) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return false;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \r2\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001A\u00020\u00022\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\u0002H\u0016J\u001A\u0010\t\u001A\u00020\u00032\u0006\u0010\n\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\u0002H\u0016¨\u0006\u000E"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$StartActivityForResult;", "Landroidx/activity/result/contract/ActivityResultContract;", "Landroid/content/Intent;", "Landroidx/activity/result/ActivityResult;", "()V", "createIntent", "context", "Landroid/content/Context;", "input", "parseResult", "resultCode", "", "intent", "Companion", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class StartActivityForResult extends ActivityResultContract {
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$StartActivityForResult$Companion;", "", "()V", "EXTRA_ACTIVITY_OPTIONS_BUNDLE", "", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult.Companion {
            private androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult.Companion() {
            }

            public androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }
        }

        public static final androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult.Companion Companion = null;
        public static final String EXTRA_ACTIVITY_OPTIONS_BUNDLE = "androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE";

        static {
            StartActivityForResult.Companion = new androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult.Companion(null);
        }

        public Intent createIntent(Context context0, Intent intent0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(intent0, "input");
            return intent0;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((Intent)object0));
        }

        public ActivityResult parseResult(int v, Intent intent0) {
            return new ActivityResult(v, intent0);
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u000E2\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u000EB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002H\u0016J\u001A\u0010\n\u001A\u00020\u00032\u0006\u0010\u000B\u001A\u00020\f2\b\u0010\r\u001A\u0004\u0018\u00010\u0006H\u0016¨\u0006\u000F"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$StartIntentSenderForResult;", "Landroidx/activity/result/contract/ActivityResultContract;", "Landroidx/activity/result/IntentSenderRequest;", "Landroidx/activity/result/ActivityResult;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "parseResult", "resultCode", "", "intent", "Companion", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class StartIntentSenderForResult extends ActivityResultContract {
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$StartIntentSenderForResult$Companion;", "", "()V", "ACTION_INTENT_SENDER_REQUEST", "", "EXTRA_INTENT_SENDER_REQUEST", "EXTRA_SEND_INTENT_EXCEPTION", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult.Companion {
            private androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult.Companion() {
            }

            public androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }
        }

        public static final String ACTION_INTENT_SENDER_REQUEST = "androidx.activity.result.contract.action.INTENT_SENDER_REQUEST";
        public static final androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult.Companion Companion = null;
        public static final String EXTRA_INTENT_SENDER_REQUEST = "androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST";
        public static final String EXTRA_SEND_INTENT_EXCEPTION = "androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION";

        static {
            StartIntentSenderForResult.Companion = new androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult.Companion(null);
        }

        public Intent createIntent(Context context0, IntentSenderRequest intentSenderRequest0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(intentSenderRequest0, "input");
            Intent intent0 = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", intentSenderRequest0);
            Intrinsics.checkNotNullExpressionValue(intent0, "Intent(ACTION_INTENT_SEN…NT_SENDER_REQUEST, input)");
            return intent0;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((IntentSenderRequest)object0));
        }

        public ActivityResult parseResult(int v, Intent intent0) {
            return new ActivityResult(v, intent0);
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002H\u0017J\u001E\u0010\n\u001A\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000B2\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002J\u001D\u0010\f\u001A\u00020\u00032\u0006\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$TakePicture;", "Landroidx/activity/result/contract/ActivityResultContract;", "Landroid/net/Uri;", "", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "(ILandroid/content/Intent;)Ljava/lang/Boolean;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class TakePicture extends ActivityResultContract {
        public Intent createIntent(Context context0, Uri uri0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(uri0, "input");
            Intent intent0 = new Intent("android.media.action.IMAGE_CAPTURE").putExtra("output", uri0);
            Intrinsics.checkNotNullExpressionValue(intent0, "Intent(MediaStore.ACTION…tore.EXTRA_OUTPUT, input)");
            return intent0;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((Uri)object0));
        }

        public final SynchronousResult getSynchronousResult(Context context0, Uri uri0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(uri0, "input");
            return null;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((Uri)object0));
        }

        public final Boolean parseResult(int v, Intent intent0) {
            return v == -1;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001A\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\b\u0010\t\u001A\u0004\u0018\u00010\u0002H\u0017J\"\u0010\n\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000B2\u0006\u0010\u0007\u001A\u00020\b2\b\u0010\t\u001A\u0004\u0018\u00010\u0002J\u001A\u0010\f\u001A\u0004\u0018\u00010\u00032\u0006\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0006¨\u0006\u0010"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$TakePicturePreview;", "Landroidx/activity/result/contract/ActivityResultContract;", "Ljava/lang/Void;", "Landroid/graphics/Bitmap;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class TakePicturePreview extends ActivityResultContract {
        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((Void)object0));
        }

        public Intent createIntent(Context context0, Void void0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            return new Intent("android.media.action.IMAGE_CAPTURE");
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((Void)object0));
        }

        public final SynchronousResult getSynchronousResult(Context context0, Void void0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            return null;
        }

        public final Bitmap parseResult(int v, Intent intent0) {
            if(v != -1) {
                intent0 = null;
            }
            return intent0 == null ? null : ((Bitmap)intent0.getParcelableExtra("data"));
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    @Deprecated(message = "The thumbnail bitmap is rarely returned and is not a good signal to determine\n      whether the video was actually successfully captured. Use {@link CaptureVideo} instead.")
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0017\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002H\u0017J \u0010\n\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000B2\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0002J\u001A\u0010\f\u001A\u0004\u0018\u00010\u00032\u0006\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0006¨\u0006\u0010"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$TakeVideo;", "Landroidx/activity/result/contract/ActivityResultContract;", "Landroid/net/Uri;", "Landroid/graphics/Bitmap;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "getSynchronousResult", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "parseResult", "resultCode", "", "intent", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class TakeVideo extends ActivityResultContract {
        public Intent createIntent(Context context0, Uri uri0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(uri0, "input");
            Intent intent0 = new Intent("android.media.action.VIDEO_CAPTURE").putExtra("output", uri0);
            Intrinsics.checkNotNullExpressionValue(intent0, "Intent(MediaStore.ACTION…tore.EXTRA_OUTPUT, input)");
            return intent0;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((Uri)object0));
        }

        public final SynchronousResult getSynchronousResult(Context context0, Uri uri0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(uri0, "input");
            return null;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public SynchronousResult getSynchronousResult(Context context0, Object object0) {
            return this.getSynchronousResult(context0, ((Uri)object0));
        }

        public final Bitmap parseResult(int v, Intent intent0) {
            if(v != -1) {
                intent0 = null;
            }
            return intent0 == null ? null : ((Bitmap)intent0.getParcelableExtra("data"));
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

}

