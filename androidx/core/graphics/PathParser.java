package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import java.util.ArrayList;

public final class PathParser {
    static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

    }

    public static class PathDataNode {
        private final float[] mParams;
        private char mType;

        PathDataNode(char c, float[] arr_f) {
            this.mType = c;
            this.mParams = arr_f;
        }

        PathDataNode(PathDataNode pathParser$PathDataNode0) {
            this.mType = pathParser$PathDataNode0.mType;
            this.mParams = PathParser.copyOfRange(pathParser$PathDataNode0.mParams, 0, pathParser$PathDataNode0.mParams.length);
        }

        private static void addCommand(Path path0, float[] arr_f, char c, char c1, float[] arr_f1) {
            float f27;
            float f26;
            float f25;
            float f24;
            float f23;
            float f22;
            float f21;
            float f16;
            float f15;
            int v3;
            int v;
            float f = arr_f[0];
            float f1 = arr_f[1];
            float f2 = arr_f[2];
            float f3 = arr_f[3];
            float f4 = arr_f[4];
            float f5 = arr_f[5];
            switch(c1) {
                case 65: 
                case 97: {
                    v = 7;
                    break;
                }
                case 67: 
                case 99: {
                    v = 6;
                    break;
                }
                case 81: 
                case 83: 
                case 0x71: 
                case 0x73: {
                    v = 4;
                    break;
                }
                case 72: 
                case 86: 
                case 104: 
                case 0x76: {
                    v = 1;
                    break;
                }
                case 90: 
                case 0x7A: {
                    path0.close();
                    path0.moveTo(f4, f5);
                    f = f4;
                    f2 = f;
                    f1 = f5;
                    f3 = f1;
                    v = 2;
                    break;
                }
                default: {
                    v = 2;
                }
            }
            float f6 = f;
            float f7 = f1;
            float f8 = f4;
            float f9 = f5;
            int v1 = 0;
            int v2 = c;
            while(v1 < arr_f1.length) {
                switch(c1) {
                    case 65: {
                        v3 = v1;
                        PathDataNode.drawArc(path0, f6, f7, arr_f1[v3 + 5], arr_f1[v3 + 6], arr_f1[v3], arr_f1[v3 + 1], arr_f1[v3 + 2], arr_f1[v3 + 3] != 0.0f, arr_f1[v3 + 4] != 0.0f);
                        f2 = arr_f1[v3 + 5];
                        f6 = f2;
                        f3 = arr_f1[v3 + 6];
                        f7 = f3;
                        break;
                    }
                    case 67: {
                        v3 = v1;
                        path0.cubicTo(arr_f1[v3], arr_f1[v3 + 1], arr_f1[v3 + 2], arr_f1[v3 + 3], arr_f1[v3 + 4], arr_f1[v3 + 5]);
                        float f10 = arr_f1[v3 + 4];
                        float f11 = arr_f1[v3 + 5];
                        float f12 = arr_f1[v3 + 2];
                        f6 = f10;
                        f7 = f11;
                        f3 = arr_f1[v3 + 3];
                        f2 = f12;
                        break;
                    }
                    case 72: {
                        v3 = v1;
                        path0.lineTo(arr_f1[v3], f7);
                        f6 = arr_f1[v3];
                        break;
                    }
                    case 76: {
                        path0.lineTo(arr_f1[v1], arr_f1[v1 + 1]);
                        f6 = arr_f1[v1];
                        f7 = arr_f1[v1 + 1];
                        v3 = v1;
                        break;
                    }
                    case 77: {
                        float f13 = arr_f1[v1];
                        float f14 = arr_f1[v1 + 1];
                        if(v1 > 0) {
                            path0.lineTo(f13, f14);
                            f6 = f13;
                            f7 = f14;
                        }
                        else {
                            path0.moveTo(f13, f14);
                            f6 = f13;
                            f8 = f6;
                            f7 = f14;
                            f9 = f7;
                        }
                        v3 = v1;
                        break;
                    }
                    case 81: {
                        v3 = v1;
                        path0.quadTo(arr_f1[v3], arr_f1[v3 + 1], arr_f1[v3 + 2], arr_f1[v3 + 3]);
                        f15 = arr_f1[v3];
                        f16 = arr_f1[v3 + 1];
                        f6 = arr_f1[v3 + 2];
                        f7 = arr_f1[v3 + 3];
                        f2 = f15;
                        f3 = f16;
                        break;
                    }
                    case 83: {
                        if(v2 == 99 || v2 == 0x73 || v2 == 67 || v2 == 83) {
                            f6 = f6 * 2.0f - f2;
                            f7 = f7 * 2.0f - f3;
                        }
                        path0.cubicTo(f6, f7, arr_f1[v1], arr_f1[v1 + 1], arr_f1[v1 + 2], arr_f1[v1 + 3]);
                        f15 = arr_f1[v1];
                        f16 = arr_f1[v1 + 1];
                        f6 = arr_f1[v1 + 2];
                        f7 = arr_f1[v1 + 3];
                        v3 = v1;
                        f2 = f15;
                        f3 = f16;
                        break;
                    }
                    case 84: {
                        if(v2 == 81 || v2 == 84 || v2 == 0x71 || v2 == 0x74) {
                            f6 = f6 * 2.0f - f2;
                            f7 = f7 * 2.0f - f3;
                        }
                        path0.quadTo(f6, f7, arr_f1[v1], arr_f1[v1 + 1]);
                        f2 = f6;
                        f3 = f7;
                        v3 = v1;
                        f6 = arr_f1[v1];
                        f7 = arr_f1[v1 + 1];
                        break;
                    }
                    case 86: {
                        v3 = v1;
                        path0.lineTo(f6, arr_f1[v3]);
                        f7 = arr_f1[v3];
                        break;
                    }
                    case 97: {
                        v3 = v1;
                        PathDataNode.drawArc(path0, f6, f7, arr_f1[v1 + 5] + f6, arr_f1[v1 + 6] + f7, arr_f1[v1], arr_f1[v1 + 1], arr_f1[v1 + 2], Float.compare(arr_f1[v1 + 3], 0.0f) != 0, Float.compare(arr_f1[v1 + 4], 0.0f) != 0);
                        f6 += arr_f1[v1 + 5];
                        f7 += arr_f1[v1 + 6];
                        f2 = f6;
                        f3 = f7;
                        break;
                    }
                    case 99: {
                        path0.rCubicTo(arr_f1[v1], arr_f1[v1 + 1], arr_f1[v1 + 2], arr_f1[v1 + 3], arr_f1[v1 + 4], arr_f1[v1 + 5]);
                        float f17 = arr_f1[v1 + 2] + f6;
                        float f18 = arr_f1[v1 + 3] + f7;
                        f6 += arr_f1[v1 + 4];
                        f7 += arr_f1[v1 + 5];
                        f2 = f17;
                        f3 = f18;
                        v3 = v1;
                        break;
                    }
                    case 104: {
                        path0.rLineTo(arr_f1[v1], 0.0f);
                        f6 += arr_f1[v1];
                        v3 = v1;
                        break;
                    }
                    case 108: {
                        path0.rLineTo(arr_f1[v1], arr_f1[v1 + 1]);
                        f6 += arr_f1[v1];
                        f7 += arr_f1[v1 + 1];
                        v3 = v1;
                        break;
                    }
                    case 109: {
                        float f19 = arr_f1[v1];
                        f6 += f19;
                        float f20 = arr_f1[v1 + 1];
                        f7 += f20;
                        if(v1 > 0) {
                            path0.rLineTo(f19, f20);
                        }
                        else {
                            path0.rMoveTo(f19, f20);
                            f8 = f6;
                            f9 = f7;
                        }
                        v3 = v1;
                        break;
                    }
                    case 0x71: {
                        path0.rQuadTo(arr_f1[v1], arr_f1[v1 + 1], arr_f1[v1 + 2], arr_f1[v1 + 3]);
                        f21 = arr_f1[v1] + f6;
                        f22 = arr_f1[v1 + 1] + f7;
                        f6 += arr_f1[v1 + 2];
                        f23 = arr_f1[v1 + 3];
                        goto label_156;
                    }
                    case 0x73: {
                        if(v2 == 99 || v2 == 0x73 || v2 == 67 || v2 == 83) {
                            f25 = f7 - f3;
                            f24 = f6 - f2;
                        }
                        else {
                            f24 = 0.0f;
                            f25 = 0.0f;
                        }
                        path0.rCubicTo(f24, f25, arr_f1[v1], arr_f1[v1 + 1], arr_f1[v1 + 2], arr_f1[v1 + 3]);
                        f21 = arr_f1[v1] + f6;
                        f22 = arr_f1[v1 + 1] + f7;
                        f6 += arr_f1[v1 + 2];
                        f23 = arr_f1[v1 + 3];
                    label_156:
                        f7 += f23;
                        f2 = f21;
                        f3 = f22;
                        v3 = v1;
                        break;
                    }
                    case 0x74: {
                        if(v2 == 81 || v2 == 84 || v2 == 0x71 || v2 == 0x74) {
                            f27 = f6 - f2;
                            f26 = f7 - f3;
                        }
                        else {
                            f26 = 0.0f;
                            f27 = 0.0f;
                        }
                        path0.rQuadTo(f27, f26, arr_f1[v1], arr_f1[v1 + 1]);
                        float f28 = f27 + f6;
                        float f29 = f26 + f7;
                        f6 += arr_f1[v1];
                        f7 += arr_f1[v1 + 1];
                        f3 = f29;
                        f2 = f28;
                        v3 = v1;
                        break;
                    }
                    case 0x76: {
                        path0.rLineTo(0.0f, arr_f1[v1]);
                        f7 += arr_f1[v1];
                        v3 = v1;
                        break;
                    }
                    default: {
                        v3 = v1;
                        break;
                    }
                }
                v1 = v3 + v;
                v2 = c1;
            }
            arr_f[0] = f6;
            arr_f[1] = f7;
            arr_f[2] = f2;
            arr_f[3] = f3;
            arr_f[4] = f8;
            arr_f[5] = f9;
        }

        private static void arcToBezier(Path path0, double f, double f1, double f2, double f3, double f4, double f5, double f6, double f7, double f8) {
            int v = (int)Math.ceil(Math.abs(f8 * 4.0 / 3.141593));
            double f9 = Math.cos(f6);
            double f10 = Math.sin(f6);
            double f11 = Math.cos(f7);
            double f12 = Math.sin(f7);
            double f13 = -f2 * f9;
            double f14 = f3 * f10;
            double f15 = -f2 * f10;
            double f16 = f3 * f9;
            double f17 = f7;
            double f18 = f12 * f15 + f11 * f16;
            double f19 = f13 * f12 - f14 * f11;
            int v1 = 0;
            double f20 = f4;
            double f21 = f5;
            while(v1 < v) {
                double f22 = f17 + f8 / ((double)v);
                double f23 = Math.sin(f22);
                double f24 = Math.cos(f22);
                double f25 = f + f2 * f9 * f24 - f14 * f23;
                double f26 = f1 + f2 * f10 * f24 + f16 * f23;
                double f27 = f13 * f23 - f14 * f24;
                double f28 = f23 * f15 + f24 * f16;
                double f29 = f22 - f17;
                double f30 = Math.tan(f29 / 2.0);
                double f31 = Math.sin(f29) * (Math.sqrt(f30 * 3.0 * f30 + 4.0) - 1.0) / 3.0;
                path0.rLineTo(0.0f, 0.0f);
                path0.cubicTo(((float)(f20 + f19 * f31)), ((float)(f21 + f18 * f31)), ((float)(f25 - f31 * f27)), ((float)(f26 - f31 * f28)), ((float)f25), ((float)f26));
                f20 = f25;
                f17 = f22;
                f18 = f28;
                f21 = f26;
                ++v1;
                f19 = f27;
            }
        }

        private static void drawArc(Path path0, float f, float f1, float f2, float f3, float f4, float f5, float f6, boolean z, boolean z1) {
            double f25;
            double f24;
            double f7 = Math.toRadians(f6);
            double f8 = Math.cos(f7);
            double f9 = Math.sin(f7);
            double f10 = (((double)f) * f8 + ((double)f1) * f9) / ((double)f4);
            double f11 = (((double)(-f)) * f9 + ((double)f1) * f8) / ((double)f5);
            double f12 = (((double)f2) * f8 + ((double)f3) * f9) / ((double)f4);
            double f13 = (((double)(-f2)) * f9 + ((double)f3) * f8) / ((double)f5);
            double f14 = f10 - f12;
            double f15 = f11 - f13;
            double f16 = (f10 + f12) / 2.0;
            double f17 = (f11 + f13) / 2.0;
            double f18 = f14 * f14 + f15 * f15;
            if(f18 == 0.0) {
                Log.w("PathParser", " Points are coincident");
                return;
            }
            double f19 = 1.0 / f18 - 0.25;
            if(f19 < 0.0) {
                Log.w("PathParser", "Points are too far apart " + f18);
                double f20 = Math.sqrt(f18);
                PathDataNode.drawArc(path0, f, f1, f2, f3, f4 * ((float)(f20 / 1.99999)), ((float)(f20 / 1.99999)) * f5, f6, z, z1);
                return;
            }
            double f21 = Math.sqrt(f19);
            double f22 = f14 * f21;
            double f23 = f21 * f15;
            if(z == z1) {
                f24 = f16 - f23;
                f25 = f17 + f22;
            }
            else {
                f24 = f16 + f23;
                f25 = f17 - f22;
            }
            double f26 = Math.atan2(f11 - f25, f10 - f24);
            double f27 = Math.atan2(f13 - f25, f12 - f24) - f26;
            int v = Double.compare(f27, 0.0);
            if(z1 != v >= 0) {
                f27 = v <= 0 ? f27 + 6.283185 : f27 - 6.283185;
            }
            double f28 = f24 * ((double)f4);
            double f29 = f25 * ((double)f5);
            PathDataNode.arcToBezier(path0, f28 * f8 - f29 * f9, f28 * f9 + f29 * f8, ((double)f4), ((double)f5), ((double)f), ((double)f1), f7, f26, f27);
        }

        public float[] getParams() {
            return this.mParams;
        }

        public char getType() {
            return this.mType;
        }

        public void interpolatePathDataNode(PathDataNode pathParser$PathDataNode0, PathDataNode pathParser$PathDataNode1, float f) {
            this.mType = pathParser$PathDataNode0.mType;
            for(int v = 0; true; ++v) {
                float[] arr_f = pathParser$PathDataNode0.mParams;
                if(v >= arr_f.length) {
                    break;
                }
                this.mParams[v] = arr_f[v] * (1.0f - f) + pathParser$PathDataNode1.mParams[v] * f;
            }
        }

        @Deprecated
        public static void nodesToPath(PathDataNode[] arr_pathParser$PathDataNode, Path path0) {
            PathParser.nodesToPath(arr_pathParser$PathDataNode, path0);
        }
    }

    private static final String LOGTAG = "PathParser";

    private static void addNode(ArrayList arrayList0, char c, float[] arr_f) {
        arrayList0.add(new PathDataNode(c, arr_f));
    }

    public static boolean canMorph(PathDataNode[] arr_pathParser$PathDataNode, PathDataNode[] arr_pathParser$PathDataNode1) {
        if(arr_pathParser$PathDataNode == null || arr_pathParser$PathDataNode1 == null || arr_pathParser$PathDataNode.length != arr_pathParser$PathDataNode1.length) {
            return false;
        }
        int v = 0;
        while(v < arr_pathParser$PathDataNode.length) {
            if(arr_pathParser$PathDataNode[v].mType == arr_pathParser$PathDataNode1[v].mType && arr_pathParser$PathDataNode[v].mParams.length == arr_pathParser$PathDataNode1[v].mParams.length) {
                ++v;
                continue;
            }
            return false;
        }
        return true;
    }

    static float[] copyOfRange(float[] arr_f, int v, int v1) {
        if(v > v1) {
            throw new IllegalArgumentException();
        }
        if(v < 0 || v > arr_f.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int v2 = v1 - v;
        float[] arr_f1 = new float[v2];
        System.arraycopy(arr_f, v, arr_f1, 0, Math.min(v2, arr_f.length - v));
        return arr_f1;
    }

    public static PathDataNode[] createNodesFromPathData(String s) {
        ArrayList arrayList0 = new ArrayList();
        int v = 1;
        int v1 = 0;
        while(v < s.length()) {
            int v2 = PathParser.nextStart(s, v);
            String s1 = s.substring(v1, v2).trim();
            if(!s1.isEmpty()) {
                PathParser.addNode(arrayList0, s1.charAt(0), PathParser.getFloats(s1));
            }
            v1 = v2;
            v = v2 + 1;
        }
        if(v - v1 == 1 && v1 < s.length()) {
            PathParser.addNode(arrayList0, s.charAt(v1), new float[0]);
        }
        return (PathDataNode[])arrayList0.toArray(new PathDataNode[0]);
    }

    public static Path createPathFromPathData(String s) {
        Path path0 = new Path();
        PathDataNode[] arr_pathParser$PathDataNode = PathParser.createNodesFromPathData(s);
        try {
            PathDataNode.nodesToPath(arr_pathParser$PathDataNode, path0);
            return path0;
        }
        catch(RuntimeException runtimeException0) {
            throw new RuntimeException("Error in parsing " + s, runtimeException0);
        }
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] arr_pathParser$PathDataNode) {
        PathDataNode[] arr_pathParser$PathDataNode1 = new PathDataNode[arr_pathParser$PathDataNode.length];
        for(int v = 0; v < arr_pathParser$PathDataNode.length; ++v) {
            arr_pathParser$PathDataNode1[v] = new PathDataNode(arr_pathParser$PathDataNode[v]);
        }
        return arr_pathParser$PathDataNode1;
    }

    private static void extract(String s, int v, ExtractFloatResult pathParser$ExtractFloatResult0) {
        pathParser$ExtractFloatResult0.mEndWithNegOrDot = false;
        int v1 = v;
        boolean z = false;
        boolean z1 = false;
        boolean z2 = false;
        while(v1 < s.length()) {
            switch(s.charAt(v1)) {
                case 0x20: 
                case 44: {
                    z = false;
                    z2 = true;
                    break;
                }
                case 45: {
                    if(v1 == v || z) {
                        z = false;
                    }
                    else {
                        pathParser$ExtractFloatResult0.mEndWithNegOrDot = true;
                        z = false;
                        z2 = true;
                    }
                    break;
                }
                case 46: {
                    if(z1) {
                        pathParser$ExtractFloatResult0.mEndWithNegOrDot = true;
                        z = false;
                        z2 = true;
                    }
                    else {
                        z = false;
                        z1 = true;
                    }
                    break;
                }
                case 69: 
                case 101: {
                    z = true;
                    break;
                }
                default: {
                    z = false;
                }
            }
            if(z2) {
                break;
            }
            ++v1;
        }
        pathParser$ExtractFloatResult0.mEndPosition = v1;
    }

    private static float[] getFloats(String s) {
        switch(s.charAt(0)) {
            case 90: 
            case 0x7A: {
                return new float[0];
            }
            default: {
                try {
                    float[] arr_f = new float[s.length()];
                    ExtractFloatResult pathParser$ExtractFloatResult0 = new ExtractFloatResult();
                    int v = s.length();
                    int v2 = 0;
                    for(int v1 = 1; v1 < v; v1 = pathParser$ExtractFloatResult0.mEndWithNegOrDot ? v3 : v3 + 1) {
                        PathParser.extract(s, v1, pathParser$ExtractFloatResult0);
                        int v3 = pathParser$ExtractFloatResult0.mEndPosition;
                        if(v1 < v3) {
                            arr_f[v2] = Float.parseFloat(s.substring(v1, v3));
                            ++v2;
                        }
                    }
                    return PathParser.copyOfRange(arr_f, 0, v2);
                }
                catch(NumberFormatException numberFormatException0) {
                    throw new RuntimeException("error in parsing \"" + s + "\"", numberFormatException0);
                }
            }
        }
    }

    public static void interpolatePathDataNodes(PathDataNode[] arr_pathParser$PathDataNode, float f, PathDataNode[] arr_pathParser$PathDataNode1, PathDataNode[] arr_pathParser$PathDataNode2) {
        if(!PathParser.interpolatePathDataNodes(arr_pathParser$PathDataNode, arr_pathParser$PathDataNode1, arr_pathParser$PathDataNode2, f)) {
            throw new IllegalArgumentException("Can\'t interpolate between two incompatible pathData");
        }
    }

    @Deprecated
    public static boolean interpolatePathDataNodes(PathDataNode[] arr_pathParser$PathDataNode, PathDataNode[] arr_pathParser$PathDataNode1, PathDataNode[] arr_pathParser$PathDataNode2, float f) {
        if(arr_pathParser$PathDataNode.length != arr_pathParser$PathDataNode1.length || arr_pathParser$PathDataNode1.length != arr_pathParser$PathDataNode2.length) {
            throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes must have the same length");
        }
        if(!PathParser.canMorph(arr_pathParser$PathDataNode1, arr_pathParser$PathDataNode2)) {
            return false;
        }
        for(int v = 0; v < arr_pathParser$PathDataNode.length; ++v) {
            arr_pathParser$PathDataNode[v].interpolatePathDataNode(arr_pathParser$PathDataNode1[v], arr_pathParser$PathDataNode2[v], f);
        }
        return true;
    }

    private static int nextStart(String s, int v) {
        while(v < s.length()) {
            int v1 = s.charAt(v);
            if(((v1 - 65) * (v1 - 90) <= 0 || (v1 - 97) * (v1 - 0x7A) <= 0) && v1 != 101 && v1 != 69) {
                break;
            }
            ++v;
        }
        return v;
    }

    public static void nodesToPath(PathDataNode[] arr_pathParser$PathDataNode, Path path0) {
        int v = 109;
        for(int v1 = 0; v1 < arr_pathParser$PathDataNode.length; ++v1) {
            PathDataNode pathParser$PathDataNode0 = arr_pathParser$PathDataNode[v1];
            int v2 = pathParser$PathDataNode0.mType;
            float[] arr_f = pathParser$PathDataNode0.mParams;
            PathDataNode.addCommand(path0, new float[6], ((char)v), ((char)v2), arr_f);
            v = pathParser$PathDataNode0.mType;
        }
    }

    public static void updateNodes(PathDataNode[] arr_pathParser$PathDataNode, PathDataNode[] arr_pathParser$PathDataNode1) {
        for(int v = 0; v < arr_pathParser$PathDataNode1.length; ++v) {
            arr_pathParser$PathDataNode[v].mType = arr_pathParser$PathDataNode1[v].mType;
            for(int v1 = 0; v1 < arr_pathParser$PathDataNode1[v].mParams.length; ++v1) {
                arr_pathParser$PathDataNode[v].mParams[v1] = arr_pathParser$PathDataNode1[v].mParams[v1];
            }
        }
    }
}

