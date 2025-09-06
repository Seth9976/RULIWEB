package androidx.core.view.contentcapture;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import androidx.core.util.HalfKt..ExternalSyntheticApiModelOutline0;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewStructureCompat;
import androidx.core.view.autofill.AutofillIdCompat;
import java.util.List;
import java.util.Objects;

public class ContentCaptureSessionCompat {
    static class Api23Impl {
        static Bundle getExtras(ViewStructure viewStructure0) {
            return viewStructure0.getExtras();
        }
    }

    static class Api29Impl {
        static AutofillId newAutofillId(ContentCaptureSession contentCaptureSession0, AutofillId autofillId0, long v) {
            return contentCaptureSession0.newAutofillId(autofillId0, v);
        }

        static ViewStructure newViewStructure(ContentCaptureSession contentCaptureSession0, View view0) {
            return contentCaptureSession0.newViewStructure(view0);
        }

        static ViewStructure newVirtualViewStructure(ContentCaptureSession contentCaptureSession0, AutofillId autofillId0, long v) {
            return contentCaptureSession0.newVirtualViewStructure(autofillId0, v);
        }

        static void notifyViewAppeared(ContentCaptureSession contentCaptureSession0, ViewStructure viewStructure0) {
            contentCaptureSession0.notifyViewAppeared(viewStructure0);
        }

        public static void notifyViewTextChanged(ContentCaptureSession contentCaptureSession0, AutofillId autofillId0, CharSequence charSequence0) {
            contentCaptureSession0.notifyViewTextChanged(autofillId0, charSequence0);
        }

        static void notifyViewsDisappeared(ContentCaptureSession contentCaptureSession0, AutofillId autofillId0, long[] arr_v) {
            contentCaptureSession0.notifyViewsDisappeared(autofillId0, arr_v);
        }
    }

    static class Api34Impl {
        static void notifyViewsAppeared(ContentCaptureSession contentCaptureSession0, List list0) {
            contentCaptureSession0.notifyViewsAppeared(list0);
        }
    }

    private static final String KEY_VIEW_TREE_APPEARED = "TREAT_AS_VIEW_TREE_APPEARED";
    private static final String KEY_VIEW_TREE_APPEARING = "TREAT_AS_VIEW_TREE_APPEARING";
    private final View mView;
    private final Object mWrappedObj;

    private ContentCaptureSessionCompat(ContentCaptureSession contentCaptureSession0, View view0) {
        this.mWrappedObj = contentCaptureSession0;
        this.mView = view0;
    }

    public AutofillId newAutofillId(long v) {
        if(Build.VERSION.SDK_INT >= 29) {
            AutofillId autofillId0 = ((AutofillIdCompat)Objects.requireNonNull(ViewCompat.getAutofillId(this.mView))).toAutofillId();
            return Api29Impl.newAutofillId(((ContentCaptureSession)this.mWrappedObj), autofillId0, v);
        }
        return null;
    }

    public ViewStructureCompat newVirtualViewStructure(AutofillId autofillId0, long v) {
        return Build.VERSION.SDK_INT < 29 ? null : ViewStructureCompat.toViewStructureCompat(Api29Impl.newVirtualViewStructure(((ContentCaptureSession)this.mWrappedObj), autofillId0, v));
    }

    public void notifyViewTextChanged(AutofillId autofillId0, CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.notifyViewTextChanged(((ContentCaptureSession)this.mWrappedObj), autofillId0, charSequence0);
        }
    }

    public void notifyViewsAppeared(List list0) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.notifyViewsAppeared(((ContentCaptureSession)this.mWrappedObj), list0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 29) {
            ViewStructure viewStructure0 = Api29Impl.newViewStructure(((ContentCaptureSession)this.mWrappedObj), this.mView);
            Api23Impl.getExtras(viewStructure0).putBoolean("TREAT_AS_VIEW_TREE_APPEARING", true);
            Api29Impl.notifyViewAppeared(((ContentCaptureSession)this.mWrappedObj), viewStructure0);
            for(int v = 0; v < list0.size(); ++v) {
                ViewStructure viewStructure1 = HalfKt..ExternalSyntheticApiModelOutline0.m(list0.get(v));
                Api29Impl.notifyViewAppeared(((ContentCaptureSession)this.mWrappedObj), viewStructure1);
            }
            ViewStructure viewStructure2 = Api29Impl.newViewStructure(((ContentCaptureSession)this.mWrappedObj), this.mView);
            Api23Impl.getExtras(viewStructure2).putBoolean("TREAT_AS_VIEW_TREE_APPEARED", true);
            Api29Impl.notifyViewAppeared(((ContentCaptureSession)this.mWrappedObj), viewStructure2);
        }
    }

    public void notifyViewsDisappeared(long[] arr_v) {
        if(Build.VERSION.SDK_INT >= 34) {
            AutofillId autofillId0 = ((AutofillIdCompat)Objects.requireNonNull(ViewCompat.getAutofillId(this.mView))).toAutofillId();
            Api29Impl.notifyViewsDisappeared(((ContentCaptureSession)this.mWrappedObj), autofillId0, arr_v);
            return;
        }
        if(Build.VERSION.SDK_INT >= 29) {
            ViewStructure viewStructure0 = Api29Impl.newViewStructure(((ContentCaptureSession)this.mWrappedObj), this.mView);
            Api23Impl.getExtras(viewStructure0).putBoolean("TREAT_AS_VIEW_TREE_APPEARING", true);
            Api29Impl.notifyViewAppeared(((ContentCaptureSession)this.mWrappedObj), viewStructure0);
            AutofillId autofillId1 = ((AutofillIdCompat)Objects.requireNonNull(ViewCompat.getAutofillId(this.mView))).toAutofillId();
            Api29Impl.notifyViewsDisappeared(((ContentCaptureSession)this.mWrappedObj), autofillId1, arr_v);
            ViewStructure viewStructure1 = Api29Impl.newViewStructure(((ContentCaptureSession)this.mWrappedObj), this.mView);
            Api23Impl.getExtras(viewStructure1).putBoolean("TREAT_AS_VIEW_TREE_APPEARED", true);
            Api29Impl.notifyViewAppeared(((ContentCaptureSession)this.mWrappedObj), viewStructure1);
        }
    }

    public ContentCaptureSession toContentCaptureSession() {
        return (ContentCaptureSession)this.mWrappedObj;
    }

    public static ContentCaptureSessionCompat toContentCaptureSessionCompat(ContentCaptureSession contentCaptureSession0, View view0) {
        return new ContentCaptureSessionCompat(contentCaptureSession0, view0);
    }
}

