package androidx.core.graphics;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\u001A.\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00042\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006\u00A2\u0006\u0002\b\u0007H\u0086\b\u001A.\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001A\u00020\t2\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006\u00A2\u0006\u0002\b\u0007H\u0086\b\u001A.\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001A\u00020\n2\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006\u00A2\u0006\u0002\b\u0007H\u0086\b\u001AF\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\f2\u0006\u0010\u000E\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\f2\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006\u00A2\u0006\u0002\b\u0007H\u0086\b\u001AF\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u000B\u001A\u00020\u00102\u0006\u0010\r\u001A\u00020\u00102\u0006\u0010\u000E\u001A\u00020\u00102\u0006\u0010\u000F\u001A\u00020\u00102\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006\u00A2\u0006\u0002\b\u0007H\u0086\b\u001A0\u0010\u0011\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0012\u001A\u00020\u00132\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006\u00A2\u0006\u0002\b\u0007H\u0086\b\u001AD\u0010\u0014\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0015\u001A\u00020\f2\b\b\u0002\u0010\u0016\u001A\u00020\f2\b\b\u0002\u0010\u0017\u001A\u00020\f2\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006\u00A2\u0006\u0002\b\u0007H\u0086\b\u001A&\u0010\u0018\u001A\u00020\u0001*\u00020\u00022\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006\u00A2\u0006\u0002\b\u0007H\u0086\b\u001AN\u0010\u0019\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u001A\u001A\u00020\f2\b\b\u0002\u0010\u001B\u001A\u00020\f2\b\b\u0002\u0010\u0016\u001A\u00020\f2\b\b\u0002\u0010\u0017\u001A\u00020\f2\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006\u00A2\u0006\u0002\b\u0007H\u0086\b\u001A:\u0010\u001C\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u001A\u001A\u00020\f2\b\b\u0002\u0010\u001B\u001A\u00020\f2\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006\u00A2\u0006\u0002\b\u0007H\u0086\b\u001A:\u0010\u001D\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u001A\u001A\u00020\f2\b\b\u0002\u0010\u001B\u001A\u00020\f2\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006\u00A2\u0006\u0002\b\u0007H\u0086\b\u00A8\u0006\u001E"}, d2 = {"withClip", "", "Landroid/graphics/Canvas;", "clipPath", "Landroid/graphics/Path;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "clipRect", "Landroid/graphics/Rect;", "Landroid/graphics/RectF;", "left", "", "top", "right", "bottom", "", "withMatrix", "matrix", "Landroid/graphics/Matrix;", "withRotation", "degrees", "pivotX", "pivotY", "withSave", "withScale", "x", "y", "withSkew", "withTranslation", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CanvasKt {
    public static final void withClip(Canvas canvas0, float f, float f1, float f2, float f3, Function1 function10) {
        int v = canvas0.save();
        canvas0.clipRect(f, f1, f2, f3);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v);
        }
    }

    public static final void withClip(Canvas canvas0, int v, int v1, int v2, int v3, Function1 function10) {
        int v4 = canvas0.save();
        canvas0.clipRect(v, v1, v2, v3);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v4);
        }
    }

    public static final void withClip(Canvas canvas0, Path path0, Function1 function10) {
        int v = canvas0.save();
        canvas0.clipPath(path0);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v);
        }
    }

    public static final void withClip(Canvas canvas0, Rect rect0, Function1 function10) {
        int v = canvas0.save();
        canvas0.clipRect(rect0);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v);
        }
    }

    public static final void withClip(Canvas canvas0, RectF rectF0, Function1 function10) {
        int v = canvas0.save();
        canvas0.clipRect(rectF0);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v);
        }
    }

    public static final void withMatrix(Canvas canvas0, Matrix matrix0, Function1 function10) {
        int v = canvas0.save();
        canvas0.concat(matrix0);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v);
        }
    }

    public static void withMatrix$default(Canvas canvas0, Matrix matrix0, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            matrix0 = new Matrix();
        }
        int v1 = canvas0.save();
        canvas0.concat(matrix0);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v1);
        }
    }

    public static final void withRotation(Canvas canvas0, float f, float f1, float f2, Function1 function10) {
        int v = canvas0.save();
        canvas0.rotate(f, f1, f2);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v);
        }
    }

    public static void withRotation$default(Canvas canvas0, float f, float f1, float f2, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            f = 0.0f;
        }
        if((v & 2) != 0) {
            f1 = 0.0f;
        }
        if((v & 4) != 0) {
            f2 = 0.0f;
        }
        int v1 = canvas0.save();
        canvas0.rotate(f, f1, f2);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v1);
        }
    }

    public static final void withSave(Canvas canvas0, Function1 function10) {
        int v = canvas0.save();
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v);
        }
    }

    public static final void withScale(Canvas canvas0, float f, float f1, float f2, float f3, Function1 function10) {
        int v = canvas0.save();
        canvas0.scale(f, f1, f2, f3);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v);
        }
    }

    public static void withScale$default(Canvas canvas0, float f, float f1, float f2, float f3, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            f = 1.0f;
        }
        if((v & 2) != 0) {
            f1 = 1.0f;
        }
        if((v & 4) != 0) {
            f2 = 0.0f;
        }
        if((v & 8) != 0) {
            f3 = 0.0f;
        }
        int v1 = canvas0.save();
        canvas0.scale(f, f1, f2, f3);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v1);
        }
    }

    public static final void withSkew(Canvas canvas0, float f, float f1, Function1 function10) {
        int v = canvas0.save();
        canvas0.skew(f, f1);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v);
        }
    }

    public static void withSkew$default(Canvas canvas0, float f, float f1, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            f = 0.0f;
        }
        if((v & 2) != 0) {
            f1 = 0.0f;
        }
        int v1 = canvas0.save();
        canvas0.skew(f, f1);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v1);
        }
    }

    public static final void withTranslation(Canvas canvas0, float f, float f1, Function1 function10) {
        int v = canvas0.save();
        canvas0.translate(f, f1);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v);
        }
    }

    public static void withTranslation$default(Canvas canvas0, float f, float f1, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            f = 0.0f;
        }
        if((v & 2) != 0) {
            f1 = 0.0f;
        }
        int v1 = canvas0.save();
        canvas0.translate(f, f1);
        try {
            function10.invoke(canvas0);
        }
        finally {
            canvas0.restoreToCount(v1);
        }
    }
}

