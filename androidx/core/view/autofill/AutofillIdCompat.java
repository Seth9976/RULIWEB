package androidx.core.view.autofill;

import android.view.autofill.AutofillId;

public class AutofillIdCompat {
    private final Object mWrappedObj;

    private AutofillIdCompat(AutofillId autofillId0) {
        this.mWrappedObj = autofillId0;
    }

    public AutofillId toAutofillId() {
        return (AutofillId)this.mWrappedObj;
    }

    public static AutofillIdCompat toAutofillIdCompat(AutofillId autofillId0) {
        return new AutofillIdCompat(autofillId0);
    }
}

