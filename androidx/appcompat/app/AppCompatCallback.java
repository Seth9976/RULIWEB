package androidx.appcompat.app;

import androidx.appcompat.view.ActionMode.Callback;
import androidx.appcompat.view.ActionMode;

public interface AppCompatCallback {
    void onSupportActionModeFinished(ActionMode arg1);

    void onSupportActionModeStarted(ActionMode arg1);

    ActionMode onWindowStartingSupportActionMode(Callback arg1);
}

