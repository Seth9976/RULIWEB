package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;
import androidx.core.util.Preconditions;

public final class EmojiTextViewHelper {
    static class HelperInternal19 extends HelperInternal {
        private final EmojiInputFilter mEmojiInputFilter;
        private boolean mEnabled;
        private final TextView mTextView;

        HelperInternal19(TextView textView0) {
            this.mTextView = textView0;
            this.mEnabled = true;
            this.mEmojiInputFilter = new EmojiInputFilter(textView0);
        }

        private InputFilter[] addEmojiInputFilterIfMissing(InputFilter[] arr_inputFilter) {
            for(int v = 0; v < arr_inputFilter.length; ++v) {
                if(arr_inputFilter[v] == this.mEmojiInputFilter) {
                    return arr_inputFilter;
                }
            }
            InputFilter[] arr_inputFilter1 = new InputFilter[arr_inputFilter.length + 1];
            System.arraycopy(arr_inputFilter, 0, arr_inputFilter1, 0, arr_inputFilter.length);
            arr_inputFilter1[arr_inputFilter.length] = this.mEmojiInputFilter;
            return arr_inputFilter1;
        }

        private SparseArray getEmojiInputFilterPositionArray(InputFilter[] arr_inputFilter) {
            SparseArray sparseArray0 = new SparseArray(1);
            for(int v = 0; v < arr_inputFilter.length; ++v) {
                InputFilter inputFilter0 = arr_inputFilter[v];
                if(inputFilter0 instanceof EmojiInputFilter) {
                    sparseArray0.put(v, inputFilter0);
                }
            }
            return sparseArray0;
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiTextViewHelper$HelperInternal
        InputFilter[] getFilters(InputFilter[] arr_inputFilter) {
            return this.mEnabled ? this.addEmojiInputFilterIfMissing(arr_inputFilter) : this.removeEmojiInputFilterIfPresent(arr_inputFilter);
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiTextViewHelper$HelperInternal
        public boolean isEnabled() {
            return this.mEnabled;
        }

        private InputFilter[] removeEmojiInputFilterIfPresent(InputFilter[] arr_inputFilter) {
            SparseArray sparseArray0 = this.getEmojiInputFilterPositionArray(arr_inputFilter);
            if(sparseArray0.size() == 0) {
                return arr_inputFilter;
            }
            InputFilter[] arr_inputFilter1 = new InputFilter[arr_inputFilter.length - sparseArray0.size()];
            int v1 = 0;
            for(int v = 0; v < arr_inputFilter.length; ++v) {
                if(sparseArray0.indexOfKey(v) < 0) {
                    arr_inputFilter1[v1] = arr_inputFilter[v];
                    ++v1;
                }
            }
            return arr_inputFilter1;
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiTextViewHelper$HelperInternal
        void setAllCaps(boolean z) {
            if(z) {
                this.updateTransformationMethod();
            }
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiTextViewHelper$HelperInternal
        void setEnabled(boolean z) {
            this.mEnabled = z;
            this.updateTransformationMethod();
            this.updateFilters();
        }

        void setEnabledUnsafe(boolean z) {
            this.mEnabled = z;
        }

        // 去混淆评级： 低(20)
        private TransformationMethod unwrapForDisabled(TransformationMethod transformationMethod0) {
            return transformationMethod0 instanceof EmojiTransformationMethod ? ((EmojiTransformationMethod)transformationMethod0).getOriginalTransformationMethod() : transformationMethod0;
        }

        private void updateFilters() {
            InputFilter[] arr_inputFilter = this.getFilters(this.mTextView.getFilters());
            this.mTextView.setFilters(arr_inputFilter);
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiTextViewHelper$HelperInternal
        void updateTransformationMethod() {
            TransformationMethod transformationMethod0 = this.wrapTransformationMethod(this.mTextView.getTransformationMethod());
            this.mTextView.setTransformationMethod(transformationMethod0);
        }

        private TransformationMethod wrapForEnabled(TransformationMethod transformationMethod0) {
            return transformationMethod0 instanceof EmojiTransformationMethod || transformationMethod0 instanceof PasswordTransformationMethod ? transformationMethod0 : new EmojiTransformationMethod(transformationMethod0);
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.emoji2.viewsintegration.EmojiTextViewHelper$HelperInternal
        TransformationMethod wrapTransformationMethod(TransformationMethod transformationMethod0) {
            return this.mEnabled ? this.wrapForEnabled(transformationMethod0) : this.unwrapForDisabled(transformationMethod0);
        }
    }

    static class HelperInternal {
        InputFilter[] getFilters(InputFilter[] arr_inputFilter) {
            return arr_inputFilter;
        }

        public boolean isEnabled() {
            return false;
        }

        void setAllCaps(boolean z) {
        }

        void setEnabled(boolean z) {
        }

        void updateTransformationMethod() {
        }

        TransformationMethod wrapTransformationMethod(TransformationMethod transformationMethod0) {
            return transformationMethod0;
        }
    }

    static class SkippingHelper19 extends HelperInternal {
        private final HelperInternal19 mHelperDelegate;

        SkippingHelper19(TextView textView0) {
            this.mHelperDelegate = new HelperInternal19(textView0);
        }

        // 去混淆评级： 低(30)
        @Override  // androidx.emoji2.viewsintegration.EmojiTextViewHelper$HelperInternal
        InputFilter[] getFilters(InputFilter[] arr_inputFilter) {
            return arr_inputFilter;
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiTextViewHelper$HelperInternal
        public boolean isEnabled() {
            return this.mHelperDelegate.isEnabled();
        }

        // 去混淆评级： 低(30)
        @Override  // androidx.emoji2.viewsintegration.EmojiTextViewHelper$HelperInternal
        void setAllCaps(boolean z) {
        }

        @Override  // androidx.emoji2.viewsintegration.EmojiTextViewHelper$HelperInternal
        void setEnabled(boolean z) {
            this.mHelperDelegate.setEnabledUnsafe(z);
        }

        // 去混淆评级： 低(20)
        private boolean skipBecauseEmojiCompatNotInitialized() [...] // 潜在的解密器

        // 去混淆评级： 低(30)
        @Override  // androidx.emoji2.viewsintegration.EmojiTextViewHelper$HelperInternal
        void updateTransformationMethod() {
        }

        // 去混淆评级： 低(30)
        @Override  // androidx.emoji2.viewsintegration.EmojiTextViewHelper$HelperInternal
        TransformationMethod wrapTransformationMethod(TransformationMethod transformationMethod0) {
            return transformationMethod0;
        }
    }

    private final HelperInternal mHelper;

    public EmojiTextViewHelper(TextView textView0) {
        this(textView0, true);
    }

    public EmojiTextViewHelper(TextView textView0, boolean z) {
        Preconditions.checkNotNull(textView0, "textView cannot be null");
        if(!z) {
            this.mHelper = new SkippingHelper19(textView0);
            return;
        }
        this.mHelper = new HelperInternal19(textView0);
    }

    public InputFilter[] getFilters(InputFilter[] arr_inputFilter) {
        return this.mHelper.getFilters(arr_inputFilter);
    }

    public boolean isEnabled() {
        return this.mHelper.isEnabled();
    }

    public void setAllCaps(boolean z) {
        this.mHelper.setAllCaps(z);
    }

    public void setEnabled(boolean z) {
        this.mHelper.setEnabled(z);
    }

    public void updateTransformationMethod() {
        this.mHelper.updateTransformationMethod();
    }

    public TransformationMethod wrapTransformationMethod(TransformationMethod transformationMethod0) {
        return this.mHelper.wrapTransformationMethod(transformationMethod0);
    }
}

