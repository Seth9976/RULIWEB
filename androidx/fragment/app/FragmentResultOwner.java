package androidx.fragment.app;

import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;

public interface FragmentResultOwner {
    void clearFragmentResult(String arg1);

    void clearFragmentResultListener(String arg1);

    void setFragmentResult(String arg1, Bundle arg2);

    void setFragmentResultListener(String arg1, LifecycleOwner arg2, FragmentResultListener arg3);
}

