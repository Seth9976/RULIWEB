package androidx.emoji2.viewsintegration;

import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.core.util.Preconditions;

public final class EmojiEditTextHelper {
    static class HelperInternal19 extends HelperInternal {
        private final EditText mEditText;
        private final EmojiTextWatcher mTextWatcher;

        HelperInternal19(EditText editText0, boolean z) {
            this.mEditText = editText0;
            EmojiTextWatcher emojiTextWatcher0 = new EmojiTextWatcher(editText0, z);
            this.mTextWatcher = emojiTextWatcher0;
            editText0.addTextChangedListener(emojiTextWatcher0);
            editText0.setEditableFactory(EmojiEditableFactory.getInstance());
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiEditTextHelper$HelperInternal
        KeyListener getKeyListener(KeyListener keyListener0) {
            if(!(keyListener0 instanceof EmojiKeyListener)) {
                if(keyListener0 == null) {
                    return null;
                }
                if(!(keyListener0 instanceof NumberKeyListener)) {
                    return new EmojiKeyListener(keyListener0);
                }
            }
            return keyListener0;
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiEditTextHelper$HelperInternal
        boolean isEnabled() {
            return this.mTextWatcher.isEnabled();
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiEditTextHelper$HelperInternal
        InputConnection onCreateInputConnection(InputConnection inputConnection0, EditorInfo editorInfo0) {
            return inputConnection0 instanceof EmojiInputConnection ? inputConnection0 : new EmojiInputConnection(this.mEditText, inputConnection0, editorInfo0);
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiEditTextHelper$HelperInternal
        void setEmojiReplaceStrategy(int v) {
            this.mTextWatcher.setEmojiReplaceStrategy(v);
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiEditTextHelper$HelperInternal
        void setEnabled(boolean z) {
            this.mTextWatcher.setEnabled(z);
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiEditTextHelper$HelperInternal
        void setMaxEmojiCount(int v) {
            this.mTextWatcher.setMaxEmojiCount(v);
        }
    }

    static class HelperInternal {
        KeyListener getKeyListener(KeyListener keyListener0) {
            return keyListener0;
        }

        boolean isEnabled() {
            return false;
        }

        InputConnection onCreateInputConnection(InputConnection inputConnection0, EditorInfo editorInfo0) {
            return inputConnection0;
        }

        void setEmojiReplaceStrategy(int v) {
        }

        void setEnabled(boolean z) {
        }

        void setMaxEmojiCount(int v) {
        }
    }

    private int mEmojiReplaceStrategy;
    private final HelperInternal mHelper;
    private int mMaxEmojiCount;

    public EmojiEditTextHelper(EditText editText0) {
        this(editText0, true);
    }

    public EmojiEditTextHelper(EditText editText0, boolean z) {
        this.mMaxEmojiCount = 0x7FFFFFFF;
        this.mEmojiReplaceStrategy = 0;
        Preconditions.checkNotNull(editText0, "editText cannot be null");
        this.mHelper = new HelperInternal19(editText0, z);
    }

    public int getEmojiReplaceStrategy() {
        return this.mEmojiReplaceStrategy;
    }

    public KeyListener getKeyListener(KeyListener keyListener0) {
        return this.mHelper.getKeyListener(keyListener0);
    }

    public int getMaxEmojiCount() {
        return this.mMaxEmojiCount;
    }

    public boolean isEnabled() {
        return this.mHelper.isEnabled();
    }

    public InputConnection onCreateInputConnection(InputConnection inputConnection0, EditorInfo editorInfo0) {
        return inputConnection0 == null ? null : this.mHelper.onCreateInputConnection(inputConnection0, editorInfo0);
    }

    public void setEmojiReplaceStrategy(int v) {
        this.mEmojiReplaceStrategy = v;
        this.mHelper.setEmojiReplaceStrategy(v);
    }

    public void setEnabled(boolean z) {
        this.mHelper.setEnabled(z);
    }

    public void setMaxEmojiCount(int v) {
        Preconditions.checkArgumentNonnegative(v, "maxEmojiCount should be greater than 0");
        this.mMaxEmojiCount = v;
        this.mHelper.setMaxEmojiCount(v);
    }
}

