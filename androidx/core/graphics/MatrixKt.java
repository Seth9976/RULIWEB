package androidx.core.graphics;

import android.graphics.Matrix;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000B\n\u0002\u0010\u0014\n\u0000\u001A\"\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00032\b\b\u0002\u0010\u0005\u001A\u00020\u0003\u001A\u001A\u0010\u0006\u001A\u00020\u00012\b\b\u0002\u0010\u0007\u001A\u00020\u00032\b\b\u0002\u0010\b\u001A\u00020\u0003\u001A\u001A\u0010\t\u001A\u00020\u00012\b\b\u0002\u0010\n\u001A\u00020\u00032\b\b\u0002\u0010\u000B\u001A\u00020\u0003\u001A\u0015\u0010\f\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\r\u001A\u00020\u0001H\u0086\n\u001A\r\u0010\u000E\u001A\u00020\u000F*\u00020\u0001H\u0086\b¨\u0006\u0010"}, d2 = {"rotationMatrix", "Landroid/graphics/Matrix;", "degrees", "", "px", "py", "scaleMatrix", "sx", "sy", "translationMatrix", "tx", "ty", "times", "m", "values", "", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class MatrixKt {
    public static final Matrix rotationMatrix(float f, float f1, float f2) {
        Matrix matrix0 = new Matrix();
        matrix0.setRotate(f, f1, f2);
        return matrix0;
    }

    public static Matrix rotationMatrix$default(float f, float f1, float f2, int v, Object object0) {
        if((v & 2) != 0) {
            f1 = 0.0f;
        }
        if((v & 4) != 0) {
            f2 = 0.0f;
        }
        return MatrixKt.rotationMatrix(f, f1, f2);
    }

    public static final Matrix scaleMatrix(float f, float f1) {
        Matrix matrix0 = new Matrix();
        matrix0.setScale(f, f1);
        return matrix0;
    }

    public static Matrix scaleMatrix$default(float f, float f1, int v, Object object0) {
        if((v & 1) != 0) {
            f = 1.0f;
        }
        if((v & 2) != 0) {
            f1 = 1.0f;
        }
        return MatrixKt.scaleMatrix(f, f1);
    }

    public static final Matrix times(Matrix matrix0, Matrix matrix1) {
        Matrix matrix2 = new Matrix(matrix0);
        matrix2.preConcat(matrix1);
        return matrix2;
    }

    public static final Matrix translationMatrix(float f, float f1) {
        Matrix matrix0 = new Matrix();
        matrix0.setTranslate(f, f1);
        return matrix0;
    }

    public static Matrix translationMatrix$default(float f, float f1, int v, Object object0) {
        if((v & 1) != 0) {
            f = 0.0f;
        }
        if((v & 2) != 0) {
            f1 = 0.0f;
        }
        return MatrixKt.translationMatrix(f, f1);
    }

    public static final float[] values(Matrix matrix0) {
        float[] arr_f = new float[9];
        matrix0.getValues(arr_f);
        return arr_f;
    }
}

