package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.appcompat.R.styleable;
import androidx.emoji2.viewsintegration.EmojiEditTextHelper;

class AppCompatEmojiEditTextHelper {
    private final EmojiEditTextHelper mEmojiEditTextHelper;
    private final EditText mView;

    AppCompatEmojiEditTextHelper(EditText editText0) {
        this.mView = editText0;
        this.mEmojiEditTextHelper = new EmojiEditTextHelper(editText0, false);
    }

    // 去混淆评级： 低(20)
    KeyListener getKeyListener(KeyListener keyListener0) {
        return this.isEmojiCapableKeyListener(keyListener0) ? this.mEmojiEditTextHelper.getKeyListener(keyListener0) : keyListener0;
    }

    boolean isEmojiCapableKeyListener(KeyListener keyListener0) {
        return !(keyListener0 instanceof NumberKeyListener);
    }

    boolean isEnabled() {
        return this.mEmojiEditTextHelper.isEnabled();
    }

    void loadFromAttributes(AttributeSet attributeSet0, int v) {
        boolean z = true;
        TypedArray typedArray0 = this.mView.getContext().obtainStyledAttributes(attributeSet0, styleable.AppCompatTextView, v, 0);
        try {
            if(typedArray0.hasValue(styleable.AppCompatTextView_emojiCompatEnabled)) {
                z = typedArray0.getBoolean(styleable.AppCompatTextView_emojiCompatEnabled, true);
            }
        }
        finally {
            typedArray0.recycle();
        }
        this.setEnabled(z);
    }

    InputConnection onCreateInputConnection(InputConnection inputConnection0, EditorInfo editorInfo0) {
        return this.mEmojiEditTextHelper.onCreateInputConnection(inputConnection0, editorInfo0);
    }

    void setEnabled(boolean z) {
        this.mEmojiEditTextHelper.setEnabled(z);
    }
}

