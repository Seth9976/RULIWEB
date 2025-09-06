package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.core.util.Preconditions;

final class AppCompatTextClassifierHelper {
    static final class Api26Impl {
        static TextClassifier getTextClassifier(TextView textView0) {
            TextClassificationManager textClassificationManager0 = (TextClassificationManager)textView0.getContext().getSystemService(TextClassificationManager.class);
            return textClassificationManager0 == null ? TextClassifier.NO_OP : textClassificationManager0.getTextClassifier();
        }
    }

    private TextClassifier mTextClassifier;
    private TextView mTextView;

    AppCompatTextClassifierHelper(TextView textView0) {
        this.mTextView = (TextView)Preconditions.checkNotNull(textView0);
    }

    public TextClassifier getTextClassifier() {
        return this.mTextClassifier == null ? Api26Impl.getTextClassifier(this.mTextView) : this.mTextClassifier;
    }

    public void setTextClassifier(TextClassifier textClassifier0) {
        this.mTextClassifier = textClassifier0;
    }
}

