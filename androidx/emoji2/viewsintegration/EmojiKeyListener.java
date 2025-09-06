package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import androidx.emoji2.text.EmojiCompat;

final class EmojiKeyListener implements KeyListener {
    public static class EmojiCompatHandleKeyDownHelper {
        public boolean handleKeyDown(Editable editable0, int v, KeyEvent keyEvent0) {
            return EmojiCompat.handleOnKeyDown(editable0, v, keyEvent0);
        }
    }

    private final EmojiCompatHandleKeyDownHelper mEmojiCompatHandleKeyDownHelper;
    private final KeyListener mKeyListener;

    EmojiKeyListener(KeyListener keyListener0) {
        this(keyListener0, new EmojiCompatHandleKeyDownHelper());
    }

    EmojiKeyListener(KeyListener keyListener0, EmojiCompatHandleKeyDownHelper emojiKeyListener$EmojiCompatHandleKeyDownHelper0) {
        this.mKeyListener = keyListener0;
        this.mEmojiCompatHandleKeyDownHelper = emojiKeyListener$EmojiCompatHandleKeyDownHelper0;
    }

    @Override  // android.text.method.KeyListener
    public void clearMetaKeyState(View view0, Editable editable0, int v) {
        this.mKeyListener.clearMetaKeyState(view0, editable0, v);
    }

    @Override  // android.text.method.KeyListener
    public int getInputType() {
        return this.mKeyListener.getInputType();
    }

    // 去混淆评级： 低(20)
    @Override  // android.text.method.KeyListener
    public boolean onKeyDown(View view0, Editable editable0, int v, KeyEvent keyEvent0) {
        return this.mEmojiCompatHandleKeyDownHelper.handleKeyDown(editable0, v, keyEvent0) || this.mKeyListener.onKeyDown(view0, editable0, v, keyEvent0);
    }

    @Override  // android.text.method.KeyListener
    public boolean onKeyOther(View view0, Editable editable0, KeyEvent keyEvent0) {
        return this.mKeyListener.onKeyOther(view0, editable0, keyEvent0);
    }

    @Override  // android.text.method.KeyListener
    public boolean onKeyUp(View view0, Editable editable0, int v, KeyEvent keyEvent0) {
        return this.mKeyListener.onKeyUp(view0, editable0, v, keyEvent0);
    }
}

