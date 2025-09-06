package androidx.core.graphics;

import android.graphics.Matrix;
import android.graphics.Shader;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u001A\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A&\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001A\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006H\u0086\b¨\u0006\u0007"}, d2 = {"transform", "", "Landroid/graphics/Shader;", "block", "Lkotlin/Function1;", "Landroid/graphics/Matrix;", "Lkotlin/ExtensionFunctionType;", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ShaderKt {
    public static final void transform(Shader shader0, Function1 function10) {
        Matrix matrix0 = new Matrix();
        shader0.getLocalMatrix(matrix0);
        function10.invoke(matrix0);
        shader0.setLocalMatrix(matrix0);
    }
}

