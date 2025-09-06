package androidx.core.view.inputmethod;

import android.content.ClipData.Item;
import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ContentInfoCompat.Builder;
import androidx.core.view.ViewCompat;
import androidx.print.PrintHelper..ExternalSyntheticApiModelOutline0;

public final class InputConnectionCompat {
    static class Api25Impl {
        static boolean commitContent(InputConnection inputConnection0, InputContentInfo inputContentInfo0, int v, Bundle bundle0) {
            return inputConnection0.commitContent(inputContentInfo0, v, bundle0);
        }
    }

    public interface OnCommitContentListener {
        boolean onCommitContent(InputContentInfoCompat arg1, int arg2, Bundle arg3);
    }

    private static final String COMMIT_CONTENT_ACTION = "androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
    private static final String COMMIT_CONTENT_CONTENT_URI_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI";
    private static final String COMMIT_CONTENT_CONTENT_URI_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI";
    private static final String COMMIT_CONTENT_DESCRIPTION_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
    private static final String COMMIT_CONTENT_DESCRIPTION_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
    private static final String COMMIT_CONTENT_FLAGS_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
    private static final String COMMIT_CONTENT_FLAGS_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
    private static final String COMMIT_CONTENT_INTEROP_ACTION = "android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
    private static final String COMMIT_CONTENT_LINK_URI_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
    private static final String COMMIT_CONTENT_LINK_URI_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
    private static final String COMMIT_CONTENT_OPTS_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
    private static final String COMMIT_CONTENT_OPTS_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
    private static final String COMMIT_CONTENT_RESULT_INTEROP_RECEIVER_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
    private static final String COMMIT_CONTENT_RESULT_RECEIVER_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
    private static final String EXTRA_INPUT_CONTENT_INFO = "androidx.core.view.extra.INPUT_CONTENT_INFO";
    public static final int INPUT_CONTENT_GRANT_READ_URI_PERMISSION = 1;
    private static final String LOG_TAG = "InputConnectionCompat";

    public static boolean commitContent(InputConnection inputConnection0, EditorInfo editorInfo0, InputContentInfoCompat inputContentInfoCompat0, int v, Bundle bundle0) {
        if(Build.VERSION.SDK_INT >= 25) {
            return Api25Impl.commitContent(inputConnection0, PrintHelper..ExternalSyntheticApiModelOutline0.m(inputContentInfoCompat0.unwrap()), v, bundle0);
        }
        boolean z = false;
        int v1 = EditorInfoCompat.getProtocol(editorInfo0);
        if(v1 == 2) {
            z = true;
        }
        else if(v1 != 3 && v1 != 4) {
            return false;
        }
        Bundle bundle1 = new Bundle();
        bundle1.putParcelable((z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI"), inputContentInfoCompat0.getContentUri());
        bundle1.putParcelable((z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION"), inputContentInfoCompat0.getDescription());
        bundle1.putParcelable((z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI"), inputContentInfoCompat0.getLinkUri());
        bundle1.putInt((z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS"), v);
        bundle1.putParcelable((z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS"), bundle0);
        return z ? inputConnection0.performPrivateCommand("android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", bundle1) : inputConnection0.performPrivateCommand("androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", bundle1);
    }

    private static OnCommitContentListener createOnCommitContentListenerUsingPerformReceiveContent(View view0) {
        Preconditions.checkNotNull(view0);
        return (InputContentInfoCompat inputContentInfoCompat0, int v, Bundle bundle0) -> {
            if(Build.VERSION.SDK_INT >= 25 && (v & 1) != 0) {
                try {
                    inputContentInfoCompat0.requestPermission();
                }
                catch(Exception exception0) {
                    Log.w("InputConnectionCompat", "Can\'t insert content from IME; requestPermission() failed", exception0);
                    return false;
                }
                Parcelable parcelable0 = (Parcelable)inputContentInfoCompat0.unwrap();
                bundle0 = bundle0 == null ? new Bundle() : new Bundle(bundle0);
                bundle0.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", parcelable0);
                return ViewCompat.performReceiveContent(view0, new Builder(new ClipData(inputContentInfoCompat0.getDescription(), new ClipData.Item(inputContentInfoCompat0.getContentUri())), 2).setLinkUri(inputContentInfoCompat0.getLinkUri()).setExtras(bundle0).build()) == null;
            }
            return ViewCompat.performReceiveContent(view0, new Builder(new ClipData(inputContentInfoCompat0.getDescription(), new ClipData.Item(inputContentInfoCompat0.getContentUri())), 2).setLinkUri(inputContentInfoCompat0.getLinkUri()).setExtras(bundle0).build()) == null;
        };
    }

    public static InputConnection createWrapper(View view0, InputConnection inputConnection0, EditorInfo editorInfo0) {
        return InputConnectionCompat.createWrapper(inputConnection0, editorInfo0, InputConnectionCompat.createOnCommitContentListenerUsingPerformReceiveContent(view0));
    }

    @Deprecated
    public static InputConnection createWrapper(InputConnection inputConnection0, EditorInfo editorInfo0, OnCommitContentListener inputConnectionCompat$OnCommitContentListener0) {
        ObjectsCompat.requireNonNull(inputConnection0, "inputConnection must be non-null");
        ObjectsCompat.requireNonNull(editorInfo0, "editorInfo must be non-null");
        ObjectsCompat.requireNonNull(inputConnectionCompat$OnCommitContentListener0, "onCommitContentListener must be non-null");
        if(Build.VERSION.SDK_INT >= 25) {
            return new InputConnectionWrapper(inputConnection0, false) {
                @Override  // android.view.inputmethod.InputConnectionWrapper
                public boolean commitContent(InputContentInfo inputContentInfo0, int v, Bundle bundle0) {
                    InputContentInfoCompat inputContentInfoCompat0 = InputContentInfoCompat.wrap(inputContentInfo0);
                    return inputConnectionCompat$OnCommitContentListener0.onCommitContent(inputContentInfoCompat0, v, bundle0) ? true : super.commitContent(inputContentInfo0, v, bundle0);
                }
            };
        }
        return EditorInfoCompat.getContentMimeTypes(editorInfo0).length == 0 ? inputConnection0 : new InputConnectionWrapper(inputConnection0, false) {
            // 去混淆评级： 低(20)
            @Override  // android.view.inputmethod.InputConnectionWrapper
            public boolean performPrivateCommand(String s, Bundle bundle0) {
                return InputConnectionCompat.handlePerformPrivateCommand(s, bundle0, inputConnectionCompat$OnCommitContentListener0) ? true : super.performPrivateCommand(s, bundle0);
            }
        };
    }

    static boolean handlePerformPrivateCommand(String s, Bundle bundle0, OnCommitContentListener inputConnectionCompat$OnCommitContentListener0) {
        ResultReceiver resultReceiver0;
        boolean z1;
        boolean z = false;
        if(bundle0 == null) {
            return false;
        }
        if(TextUtils.equals("androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", s)) {
            z1 = false;
            goto label_8;
        }
        if(TextUtils.equals("android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", s)) {
            z1 = true;
            try {
            label_8:
                resultReceiver0 = null;
                resultReceiver0 = (ResultReceiver)bundle0.getParcelable((z1 ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER"));
                Uri uri0 = (Uri)bundle0.getParcelable((z1 ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI"));
                ClipDescription clipDescription0 = (ClipDescription)bundle0.getParcelable((z1 ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION"));
                Uri uri1 = (Uri)bundle0.getParcelable((z1 ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI"));
                int v = bundle0.getInt((z1 ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS"));
                Bundle bundle1 = (Bundle)bundle0.getParcelable((z1 ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS"));
                if(uri0 != null && clipDescription0 != null) {
                    z = inputConnectionCompat$OnCommitContentListener0.onCommitContent(new InputContentInfoCompat(uri0, clipDescription0, uri1), v, bundle1);
                }
            }
            catch(Throwable throwable0) {
                if(resultReceiver0 != null) {
                    resultReceiver0.send(0, null);
                }
                throw throwable0;
            }
            if(resultReceiver0 != null) {
                resultReceiver0.send(((int)z), null);
            }
            return z;
        }
        return false;
    }

    // 检测为 Lambda 实现
    static boolean lambda$createOnCommitContentListenerUsingPerformReceiveContent$0(View view0, InputContentInfoCompat inputContentInfoCompat0, int v, Bundle bundle0) [...]
}

