package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.R.styleable;
import androidx.emoji2.viewsintegration.EmojiTextViewHelper;

class AppCompatEmojiTextHelper {
    private final EmojiTextViewHelper mEmojiTextViewHelper;
    private final TextView mView;

    AppCompatEmojiTextHelper(TextView textView0) {
        this.mView = textView0;
        this.mEmojiTextViewHelper = new EmojiTextViewHelper(textView0, false);
    }

    InputFilter[] getFilters(InputFilter[] arr_inputFilter) {
        return this.mEmojiTextViewHelper.getFilters(arr_inputFilter);
    }

    public boolean isEnabled() {
        return this.mEmojiTextViewHelper.isEnabled();
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

    void setAllCaps(boolean z) {
        this.mEmojiTextViewHelper.setAllCaps(z);
    }

    void setEnabled(boolean z) {
        this.mEmojiTextViewHelper.setEnabled(z);
    }

    public TransformationMethod wrapTransformationMethod(TransformationMethod transformationMethod0) {
        return this.mEmojiTextViewHelper.wrapTransformationMethod(transformationMethod0);
    }
}

