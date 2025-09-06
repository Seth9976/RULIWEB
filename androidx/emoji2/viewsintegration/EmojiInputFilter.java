package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.widget.TextView;
import androidx.emoji2.text.EmojiCompat.InitCallback;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

final class EmojiInputFilter implements InputFilter {
    static class InitCallbackImpl extends InitCallback {
        private final Reference mEmojiInputFilterReference;
        private final Reference mViewRef;

        InitCallbackImpl(TextView textView0, EmojiInputFilter emojiInputFilter0) {
            this.mViewRef = new WeakReference(textView0);
            this.mEmojiInputFilterReference = new WeakReference(emojiInputFilter0);
        }

        private boolean isInputFilterCurrentlyRegisteredOnTextView(TextView textView0, InputFilter inputFilter0) {
            if(inputFilter0 != null && textView0 != null) {
                InputFilter[] arr_inputFilter = textView0.getFilters();
                if(arr_inputFilter == null) {
                    return false;
                }
                for(int v = 0; v < arr_inputFilter.length; ++v) {
                    if(arr_inputFilter[v] == inputFilter0) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override  // androidx.emoji2.text.EmojiCompat$InitCallback
        public void onInitialized() {
            super.onInitialized();
            TextView textView0 = (TextView)this.mViewRef.get();
            if(this.isInputFilterCurrentlyRegisteredOnTextView(textView0, ((InputFilter)this.mEmojiInputFilterReference.get())) && textView0.isAttachedToWindow()) {
                CharSequence charSequence0 = textView0.getText();
                CharSequence charSequence1 = EmojiCompat.get().process(charSequence0);
                if(charSequence0 != charSequence1) {
                    int v = Selection.getSelectionStart(charSequence1);
                    int v1 = Selection.getSelectionEnd(charSequence1);
                    textView0.setText(charSequence1);
                    if(charSequence1 instanceof Spannable) {
                        EmojiInputFilter.updateSelection(((Spannable)charSequence1), v, v1);
                    }
                }
            }
        }
    }

    private InitCallback mInitCallback;
    private final TextView mTextView;

    EmojiInputFilter(TextView textView0) {
        this.mTextView = textView0;
    }

    @Override  // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence0, int v, int v1, Spanned spanned0, int v2, int v3) {
        if(!this.mTextView.isInEditMode()) {
            switch(EmojiCompat.get().getLoadState()) {
                case 1: {
                    if(v3 == 0 && v2 == 0 && spanned0.length() == 0 && charSequence0 == this.mTextView.getText()) {
                        return charSequence0;
                    }
                    if(charSequence0 != null) {
                        if(v != 0 || v1 != charSequence0.length()) {
                            charSequence0 = charSequence0.subSequence(v, v1);
                        }
                        return EmojiCompat.get().process(charSequence0, 0, charSequence0.length());
                    }
                    break;
                }
                case 0: 
                case 3: {
                    EmojiCompat.get().registerInitCallback(this.getInitCallback());
                    return charSequence0;
                }
                default: {
                    return charSequence0;
                }
            }
        }
        return charSequence0;
    }

    private InitCallback getInitCallback() {
        if(this.mInitCallback == null) {
            this.mInitCallback = new InitCallbackImpl(this.mTextView, this);
        }
        return this.mInitCallback;
    }

    static void updateSelection(Spannable spannable0, int v, int v1) {
        if(v >= 0 && v1 >= 0) {
            Selection.setSelection(spannable0, v, v1);
            return;
        }
        if(v >= 0) {
            Selection.setSelection(spannable0, v);
            return;
        }
        if(v1 >= 0) {
            Selection.setSelection(spannable0, v1);
        }
    }
}

