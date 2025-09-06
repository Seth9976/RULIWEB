package androidx.appcompat.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

class AppCompatHintHelper {
    static InputConnection onCreateInputConnection(InputConnection inputConnection0, EditorInfo editorInfo0, View view0) {
        if(inputConnection0 != null && editorInfo0.hintText == null) {
            for(ViewParent viewParent0 = view0.getParent(); viewParent0 instanceof View; viewParent0 = viewParent0.getParent()) {
                if(viewParent0 instanceof WithHint) {
                    editorInfo0.hintText = ((WithHint)viewParent0).getHint();
                    return inputConnection0;
                }
            }
        }
        return inputConnection0;
    }
}

