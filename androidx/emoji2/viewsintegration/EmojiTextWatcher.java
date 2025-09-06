package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.emoji2.text.EmojiCompat.InitCallback;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

final class EmojiTextWatcher implements TextWatcher {
    static class InitCallbackImpl extends InitCallback {
        private final Reference mViewRef;

        InitCallbackImpl(EditText editText0) {
            this.mViewRef = new WeakReference(editText0);
        }

        @Override  // androidx.emoji2.text.EmojiCompat$InitCallback
        public void onInitialized() {
            super.onInitialized();
            EmojiTextWatcher.processTextOnEnablingEvent(((EditText)this.mViewRef.get()), 1);
        }
    }

    private final EditText mEditText;
    private int mEmojiReplaceStrategy;
    private boolean mEnabled;
    private final boolean mExpectInitializedEmojiCompat;
    private InitCallback mInitCallback;
    private int mMaxEmojiCount;

    EmojiTextWatcher(EditText editText0, boolean z) {
        this.mMaxEmojiCount = 0x7FFFFFFF;
        this.mEmojiReplaceStrategy = 0;
        this.mEditText = editText0;
        this.mExpectInitializedEmojiCompat = z;
        this.mEnabled = true;
    }

    @Override  // android.text.TextWatcher
    public void afterTextChanged(Editable editable0) {
    }

    @Override  // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
    }

    int getEmojiReplaceStrategy() {
        return this.mEmojiReplaceStrategy;
    }

    private InitCallback getInitCallback() {
        if(this.mInitCallback == null) {
            this.mInitCallback = new InitCallbackImpl(this.mEditText);
        }
        return this.mInitCallback;
    }

    int getMaxEmojiCount() {
        return this.mMaxEmojiCount;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    @Override  // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
        if(!this.mEditText.isInEditMode() && !this.shouldSkipForDisabledOrNotConfigured() && v1 <= v2 && charSequence0 instanceof Spannable) {
            switch(EmojiCompat.get().getLoadState()) {
                case 1: {
                    EmojiCompat.get().process(((Spannable)charSequence0), v, v + v2, this.mMaxEmojiCount, this.mEmojiReplaceStrategy);
                    return;
                }
                case 0: 
                case 3: {
                    EmojiCompat.get().registerInitCallback(this.getInitCallback());
                    break;
                }
            }
        }
    }

    static void processTextOnEnablingEvent(EditText editText0, int v) {
        if(v == 1 && editText0 != null && editText0.isAttachedToWindow()) {
            Editable editable0 = editText0.getEditableText();
            int v1 = Selection.getSelectionStart(editable0);
            int v2 = Selection.getSelectionEnd(editable0);
            EmojiCompat.get().process(editable0);
            EmojiInputFilter.updateSelection(editable0, v1, v2);
        }
    }

    void setEmojiReplaceStrategy(int v) {
        this.mEmojiReplaceStrategy = v;
    }

    public void setEnabled(boolean z) {
        if(this.mEnabled != z) {
            if(this.mInitCallback != null) {
                EmojiCompat.get().unregisterInitCallback(this.mInitCallback);
            }
            this.mEnabled = z;
            if(z) {
                int v = EmojiCompat.get().getLoadState();
                EmojiTextWatcher.processTextOnEnablingEvent(this.mEditText, v);
            }
        }
    }

    void setMaxEmojiCount(int v) {
        this.mMaxEmojiCount = v;
    }

    // 去混淆评级： 中等(50)
    private boolean shouldSkipForDisabledOrNotConfigured() {
        return !this.mEnabled || !this.mExpectInitializedEmojiCompat;
    }
}

