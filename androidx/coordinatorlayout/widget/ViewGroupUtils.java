package androidx.coordinatorlayout.widget;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class ViewGroupUtils {
    private static final ThreadLocal sMatrix;
    private static final ThreadLocal sRectF;

    static {
        ViewGroupUtils.sMatrix = new ThreadLocal();
        ViewGroupUtils.sRectF = new ThreadLocal();
    }

    public static void getDescendantRect(ViewGroup viewGroup0, View view0, Rect rect0) {
        rect0.set(0, 0, view0.getWidth(), view0.getHeight());
        ViewGroupUtils.offsetDescendantRect(viewGroup0, view0, rect0);
    }

    private static void offsetDescendantMatrix(ViewParent viewParent0, View view0, Matrix matrix0) {
        ViewParent viewParent1 = view0.getParent();
        if(viewParent1 instanceof View && viewParent1 != viewParent0) {
            ViewGroupUtils.offsetDescendantMatrix(viewParent0, ((View)viewParent1), matrix0);
            matrix0.preTranslate(((float)(-((View)viewParent1).getScrollX())), ((float)(-((View)viewParent1).getScrollY())));
        }
        matrix0.preTranslate(((float)view0.getLeft()), ((float)view0.getTop()));
        if(!view0.getMatrix().isIdentity()) {
            matrix0.preConcat(view0.getMatrix());
        }
    }

    static void offsetDescendantRect(ViewGroup viewGroup0, View view0, Rect rect0) {
        ThreadLocal threadLocal0 = ViewGroupUtils.sMatrix;
        Matrix matrix0 = (Matrix)threadLocal0.get();
        if(matrix0 == null) {
            matrix0 = new Matrix();
            threadLocal0.set(matrix0);
        }
        else {
            matrix0.reset();
        }
        ViewGroupUtils.offsetDescendantMatrix(viewGroup0, view0, matrix0);
        ThreadLocal threadLocal1 = ViewGroupUtils.sRectF;
        RectF rectF0 = (RectF)threadLocal1.get();
        if(rectF0 == null) {
            rectF0 = new RectF();
            threadLocal1.set(rectF0);
        }
        rectF0.set(rect0);
        matrix0.mapRect(rectF0);
        rect0.set(((int)(rectF0.left + 0.5f)), ((int)(rectF0.top + 0.5f)), ((int)(rectF0.right + 0.5f)), ((int)(rectF0.bottom + 0.5f)));
    }
}

