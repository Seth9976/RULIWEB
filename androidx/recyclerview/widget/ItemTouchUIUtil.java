package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.view.View;

public interface ItemTouchUIUtil {
    void clearView(View arg1);

    void onDraw(Canvas arg1, RecyclerView arg2, View arg3, float arg4, float arg5, int arg6, boolean arg7);

    void onDrawOver(Canvas arg1, RecyclerView arg2, View arg3, float arg4, float arg5, int arg6, boolean arg7);

    void onSelected(View arg1);
}

