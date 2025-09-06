package com.google.android.material.timepicker;

import com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener;
import com.google.android.material.button.MaterialButtonToggleGroup;

public final class TimePickerTextInputPresenter..ExternalSyntheticLambda0 implements OnButtonCheckedListener {
    public final TimePickerTextInputPresenter f$0;

    public TimePickerTextInputPresenter..ExternalSyntheticLambda0(TimePickerTextInputPresenter timePickerTextInputPresenter0) {
        this.f$0 = timePickerTextInputPresenter0;
    }

    @Override  // com.google.android.material.button.MaterialButtonToggleGroup$OnButtonCheckedListener
    public final void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup0, int v, boolean z) {
        this.f$0.lambda$setupPeriodToggle$0$com-google-android-material-timepicker-TimePickerTextInputPresenter(materialButtonToggleGroup0, v, z);
    }
}

