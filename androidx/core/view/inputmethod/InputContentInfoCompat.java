package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.inputmethod.InputContentInfo;

public final class InputContentInfoCompat {
    static final class InputContentInfoCompatApi25Impl implements InputContentInfoCompatImpl {
        final InputContentInfo mObject;

        InputContentInfoCompatApi25Impl(Uri uri0, ClipDescription clipDescription0, Uri uri1) {
            this.mObject = new InputContentInfo(uri0, clipDescription0, uri1);
        }

        InputContentInfoCompatApi25Impl(Object object0) {
            this.mObject = (InputContentInfo)object0;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public Uri getContentUri() {
            return this.mObject.getContentUri();
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public ClipDescription getDescription() {
            return this.mObject.getDescription();
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public Object getInputContentInfo() {
            return this.mObject;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public Uri getLinkUri() {
            return this.mObject.getLinkUri();
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public void releasePermission() {
            this.mObject.releasePermission();
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public void requestPermission() {
            this.mObject.requestPermission();
        }
    }

    static final class InputContentInfoCompatBaseImpl implements InputContentInfoCompatImpl {
        private final Uri mContentUri;
        private final ClipDescription mDescription;
        private final Uri mLinkUri;

        InputContentInfoCompatBaseImpl(Uri uri0, ClipDescription clipDescription0, Uri uri1) {
            this.mContentUri = uri0;
            this.mDescription = clipDescription0;
            this.mLinkUri = uri1;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public Uri getContentUri() {
            return this.mContentUri;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public ClipDescription getDescription() {
            return this.mDescription;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public Object getInputContentInfo() {
            return null;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public Uri getLinkUri() {
            return this.mLinkUri;
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public void releasePermission() {
        }

        @Override  // androidx.core.view.inputmethod.InputContentInfoCompat$InputContentInfoCompatImpl
        public void requestPermission() {
        }
    }

    interface InputContentInfoCompatImpl {
        Uri getContentUri();

        ClipDescription getDescription();

        Object getInputContentInfo();

        Uri getLinkUri();

        void releasePermission();

        void requestPermission();
    }

    private final InputContentInfoCompatImpl mImpl;

    public InputContentInfoCompat(Uri uri0, ClipDescription clipDescription0, Uri uri1) {
        if(Build.VERSION.SDK_INT >= 25) {
            this.mImpl = new InputContentInfoCompatApi25Impl(uri0, clipDescription0, uri1);
            return;
        }
        this.mImpl = new InputContentInfoCompatBaseImpl(uri0, clipDescription0, uri1);
    }

    private InputContentInfoCompat(InputContentInfoCompatImpl inputContentInfoCompat$InputContentInfoCompatImpl0) {
        this.mImpl = inputContentInfoCompat$InputContentInfoCompatImpl0;
    }

    public Uri getContentUri() {
        return this.mImpl.getContentUri();
    }

    public ClipDescription getDescription() {
        return this.mImpl.getDescription();
    }

    public Uri getLinkUri() {
        return this.mImpl.getLinkUri();
    }

    public void releasePermission() {
        this.mImpl.releasePermission();
    }

    public void requestPermission() {
        this.mImpl.requestPermission();
    }

    public Object unwrap() {
        return this.mImpl.getInputContentInfo();
    }

    public static InputContentInfoCompat wrap(Object object0) {
        if(object0 == null) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 25 ? new InputContentInfoCompat(new InputContentInfoCompatApi25Impl(object0)) : null;
    }
}

