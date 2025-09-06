package androidx.appcompat.app;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

public class AppCompatDialogFragment extends DialogFragment {
    public AppCompatDialogFragment() {
    }

    public AppCompatDialogFragment(int v) {
        super(v);
    }

    @Override  // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle0) {
        return new AppCompatDialog(this.getContext(), this.getTheme());
    }

    @Override  // androidx.fragment.app.DialogFragment
    public void setupDialog(Dialog dialog0, int v) {
        if(dialog0 instanceof AppCompatDialog) {
            switch(v) {
                case 1: 
                case 2: {
                    break;
                }
                case 3: {
                    dialog0.getWindow().addFlags(24);
                    break;
                }
                default: {
                    return;
                }
            }
            ((AppCompatDialog)dialog0).supportRequestWindowFeature(1);
            return;
        }
        super.setupDialog(dialog0, v);
    }
}

