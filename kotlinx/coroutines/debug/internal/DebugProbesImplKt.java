package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000E\n\u0000\u001A\f\u0010\u0000\u001A\u00020\u0001*\u00020\u0001H\u0002¨\u0006\u0002"}, d2 = {"repr", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DebugProbesImplKt {
    private static final String repr(String s) {
        StringBuilder stringBuilder0 = new StringBuilder("\"");
        int v = s.length();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = s.charAt(v1);
            switch(v2) {
                case 8: {
                    stringBuilder0.append("\\b");
                    break;
                }
                case 9: {
                    stringBuilder0.append("\\t");
                    break;
                }
                case 10: {
                    stringBuilder0.append("\\n");
                    break;
                }
                case 13: {
                    stringBuilder0.append("\\r");
                    break;
                }
                case 34: {
                    stringBuilder0.append("\\\"");
                    break;
                }
                case 92: {
                    stringBuilder0.append("\\\\");
                    break;
                }
                default: {
                    stringBuilder0.append(((char)v2));
                }
            }
        }
        stringBuilder0.append('\"');
        String s1 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s1, "StringBuilder().apply(builderAction).toString()");
        return s1;
    }
}

