package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.TextView;
import androidx.emoji2.text.EmojiCompat;

final class EmojiInputConnection extends InputConnectionWrapper {
    public static class EmojiCompatDeleteHelper {
        public boolean handleDeleteSurroundingText(InputConnection inputConnection0, Editable editable0, int v, int v1, boolean z) {
            return EmojiCompat.handleDeleteSurroundingText(inputConnection0, editable0, v, v1, z);
        }

        // 去混淆评级： 低(30)
        public void updateEditorInfoAttrs(EditorInfo editorInfo0) {
        }
    }

    private final EmojiCompatDeleteHelper mEmojiCompatDeleteHelper;
    private final TextView mTextView;

    EmojiInputConnection(TextView textView0, InputConnection inputConnection0, EditorInfo editorInfo0) {
        this(textView0, inputConnection0, editorInfo0, new EmojiCompatDeleteHelper());
    }

    EmojiInputConnection(TextView textView0, InputConnection inputConnection0, EditorInfo editorInfo0, EmojiCompatDeleteHelper emojiInputConnection$EmojiCompatDeleteHelper0) {
        super(inputConnection0, false);
        this.mTextView = textView0;
        this.mEmojiCompatDeleteHelper = emojiInputConnection$EmojiCompatDeleteHelper0;
        emojiInputConnection$EmojiCompatDeleteHelper0.updateEditorInfoAttrs(editorInfo0);
    }

    @Override  // android.view.inputmethod.InputConnectionWrapper
    public boolean deleteSurroundingText(int v, int v1) {
        Editable editable0 = this.getEditable();
        return this.mEmojiCompatDeleteHelper.handleDeleteSurroundingText(this, editable0, v, v1, false) || super.deleteSurroundingText(v, v1);
    }

    @Override  // android.view.inputmethod.InputConnectionWrapper
    public boolean deleteSurroundingTextInCodePoints(int v, int v1) {
        Editable editable0 = this.getEditable();
        return this.mEmojiCompatDeleteHelper.handleDeleteSurroundingText(this, editable0, v, v1, true) || super.deleteSurroundingTextInCodePoints(v, v1);
    }

    private Editable getEditable() {
        return this.mTextView.getEditableText();
    }
}

