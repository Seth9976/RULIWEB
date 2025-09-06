package androidx.core.view.inputmethod;

import android.os.Bundle;
import android.view.View;

public final class InputConnectionCompat..ExternalSyntheticLambda1 implements OnCommitContentListener {
    public final View f$0;

    public InputConnectionCompat..ExternalSyntheticLambda1(View view0) {
        this.f$0 = view0;
    }

    @Override  // androidx.core.view.inputmethod.InputConnectionCompat$OnCommitContentListener
    public final boolean onCommitContent(InputContentInfoCompat inputContentInfoCompat0, int v, Bundle bundle0) {
        return InputConnectionCompat.lambda$createOnCommitContentListenerUsingPerformReceiveContent$0(this.f$0, inputContentInfoCompat0, v, bundle0);
    }
}

