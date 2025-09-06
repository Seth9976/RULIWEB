package androidx.transition;

import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;

class GhostViewUtils {
    static GhostView addGhost(View view0, ViewGroup viewGroup0, Matrix matrix0) {
        return Build.VERSION.SDK_INT == 28 ? GhostViewPlatform.addGhost(view0, viewGroup0, matrix0) : GhostViewPort.addGhost(view0, viewGroup0, matrix0);
    }

    static void removeGhost(View view0) {
        if(Build.VERSION.SDK_INT == 28) {
            GhostViewPlatform.removeGhost(view0);
            return;
        }
        GhostViewPort.removeGhost(view0);
    }
}

