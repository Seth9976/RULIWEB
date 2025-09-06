package androidx.core.view;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;

public final class DragAndDropPermissionsCompat {
    static class Api24Impl {
        static void release(DragAndDropPermissions dragAndDropPermissions0) {
            dragAndDropPermissions0.release();
        }

        static DragAndDropPermissions requestDragAndDropPermissions(Activity activity0, DragEvent dragEvent0) {
            return activity0.requestDragAndDropPermissions(dragEvent0);
        }
    }

    private final DragAndDropPermissions mDragAndDropPermissions;

    private DragAndDropPermissionsCompat(DragAndDropPermissions dragAndDropPermissions0) {
        this.mDragAndDropPermissions = dragAndDropPermissions0;
    }

    public void release() {
        if(Build.VERSION.SDK_INT >= 24) {
            Api24Impl.release(this.mDragAndDropPermissions);
        }
    }

    public static DragAndDropPermissionsCompat request(Activity activity0, DragEvent dragEvent0) {
        if(Build.VERSION.SDK_INT >= 24) {
            DragAndDropPermissions dragAndDropPermissions0 = Api24Impl.requestDragAndDropPermissions(activity0, dragEvent0);
            return dragAndDropPermissions0 == null ? null : new DragAndDropPermissionsCompat(dragAndDropPermissions0);
        }
        return null;
    }
}

