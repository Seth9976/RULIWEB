package com.google.android.material.bottomsheet;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatDialogFragment;

public class BottomSheetDialogFragment extends AppCompatDialogFragment {
    class BottomSheetDismissCallback extends BottomSheetCallback {
        private BottomSheetDismissCallback() {
        }

        BottomSheetDismissCallback(com.google.android.material.bottomsheet.BottomSheetDialogFragment.1 bottomSheetDialogFragment$10) {
        }

        @Override  // com.google.android.material.bottomsheet.BottomSheetBehavior$BottomSheetCallback
        public void onSlide(View view0, float f) {
        }

        @Override  // com.google.android.material.bottomsheet.BottomSheetBehavior$BottomSheetCallback
        public void onStateChanged(View view0, int v) {
            if(v == 5) {
                BottomSheetDialogFragment.this.dismissAfterAnimation();
            }
        }
    }

    private boolean waitingForDismissAllowingStateLoss;

    public BottomSheetDialogFragment() {
    }

    public BottomSheetDialogFragment(int v) {
        super(v);
    }

    @Override  // androidx.fragment.app.DialogFragment
    public void dismiss() {
        if(!this.tryDismissWithAnimation(false)) {
            super.dismiss();
        }
    }

    private void dismissAfterAnimation() {
        if(this.waitingForDismissAllowingStateLoss) {
            super.dismissAllowingStateLoss();
            return;
        }
        super.dismiss();
    }

    @Override  // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        if(!this.tryDismissWithAnimation(true)) {
            super.dismissAllowingStateLoss();
        }
    }

    private void dismissWithAnimation(BottomSheetBehavior bottomSheetBehavior0, boolean z) {
        this.waitingForDismissAllowingStateLoss = z;
        if(bottomSheetBehavior0.getState() == 5) {
            this.dismissAfterAnimation();
            return;
        }
        if(this.getDialog() instanceof BottomSheetDialog) {
            ((BottomSheetDialog)this.getDialog()).removeDefaultCallback();
        }
        bottomSheetBehavior0.addBottomSheetCallback(new BottomSheetDismissCallback(this, null));
        bottomSheetBehavior0.setState(5);
    }

    @Override  // androidx.appcompat.app.AppCompatDialogFragment
    public Dialog onCreateDialog(Bundle bundle0) {
        return new BottomSheetDialog(this.getContext(), this.getTheme());
    }

    private boolean tryDismissWithAnimation(boolean z) {
        Dialog dialog0 = this.getDialog();
        if(dialog0 instanceof BottomSheetDialog) {
            BottomSheetBehavior bottomSheetBehavior0 = ((BottomSheetDialog)dialog0).getBehavior();
            if(bottomSheetBehavior0.isHideable() && ((BottomSheetDialog)dialog0).getDismissWithAnimation()) {
                this.dismissWithAnimation(bottomSheetBehavior0, z);
                return true;
            }
        }
        return false;
    }

    class com.google.android.material.bottomsheet.BottomSheetDialogFragment.1 {
    }

}

