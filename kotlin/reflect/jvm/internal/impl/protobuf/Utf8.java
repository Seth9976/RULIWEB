package kotlin.reflect.jvm.internal.impl.protobuf;

final class Utf8 {
    private static int incompleteStateFor(int v) {
        return v <= -12 ? v : -1;
    }

    private static int incompleteStateFor(int v, int v1) {
        return v > -12 || v1 > -65 ? -1 : v ^ v1 << 8;
    }

    private static int incompleteStateFor(int v, int v1, int v2) {
        return v > -12 || v1 > -65 || v2 > -65 ? -1 : v ^ v1 << 8 ^ v2 << 16;
    }

    private static int incompleteStateFor(byte[] arr_b, int v, int v1) {
        int v2 = arr_b[v - 1];
        switch(v1 - v) {
            case 0: {
                return Utf8.incompleteStateFor(v2);
            }
            case 1: {
                return Utf8.incompleteStateFor(v2, arr_b[v]);
            }
            case 2: {
                return Utf8.incompleteStateFor(v2, arr_b[v], arr_b[v + 1]);
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    public static boolean isValidUtf8(byte[] arr_b) {
        return Utf8.isValidUtf8(arr_b, 0, arr_b.length);
    }

    public static boolean isValidUtf8(byte[] arr_b, int v, int v1) {
        return Utf8.partialIsValidUtf8(arr_b, v, v1) == 0;
    }

    public static int partialIsValidUtf8(int v, byte[] arr_b, int v1, int v2) {
        int v7;
        int v6;
        if(v != 0) {
            if(v1 >= v2) {
                return v;
            }
            if(((byte)v) < 0xFFFFFFE0) {
                if(((byte)v) >= -62) {
                    return arr_b[v1] > -65 ? -1 : Utf8.partialIsValidUtf8(arr_b, v1 + 1, v2);
                }
                return -1;
            }
            if(((byte)v) < -16) {
                int v3 = (byte)(~(v >> 8));
                if(v3 == 0) {
                    int v4 = arr_b[v1];
                    if(v1 + 1 >= v2) {
                        return Utf8.incompleteStateFor(((byte)v), v4);
                    }
                    ++v1;
                    v3 = v4;
                }
                if(v3 <= -65 && (((byte)v) != 0xFFFFFFE0 || v3 >= 0xFFFFFFA0) && (((byte)v) != -19 || v3 < 0xFFFFFFA0)) {
                    return arr_b[v1] <= -65 ? Utf8.partialIsValidUtf8(arr_b, v1 + 1, v2) : -1;
                }
                return -1;
            }
            int v5 = (byte)(~(v >> 8));
            if(v5 == 0) {
                v6 = v1 + 1;
                v5 = arr_b[v1];
                if(v6 >= v2) {
                    return Utf8.incompleteStateFor(((byte)v), v5);
                }
                v7 = 0;
            }
            else {
                v7 = (byte)(v >> 16);
                v6 = v1;
            }
            if(v7 == 0) {
                int v8 = arr_b[v6];
                if(v6 + 1 >= v2) {
                    return Utf8.incompleteStateFor(((byte)v), v5, v8);
                }
                v7 = v8;
                ++v6;
            }
            if(v5 <= -65 && (((byte)v) << 28) + (v5 + 0x70) >> 30 == 0 && v7 <= -65) {
                return arr_b[v6] <= -65 ? Utf8.partialIsValidUtf8(arr_b, v6 + 1, v2) : -1;
            }
            return -1;
        }
        return Utf8.partialIsValidUtf8(arr_b, v1, v2);
    }

    public static int partialIsValidUtf8(byte[] arr_b, int v, int v1) {
        while(v < v1 && arr_b[v] >= 0) {
            ++v;
        }
        return v < v1 ? Utf8.partialIsValidUtf8NonAscii(arr_b, v, v1) : 0;
    }

    private static int partialIsValidUtf8NonAscii(byte[] arr_b, int v, int v1) {
        while(true) {
            if(v >= v1) {
                return 0;
            }
            int v2 = v + 1;
            int v3 = arr_b[v];
            if(v3 < 0) {
                if(v3 < 0xFFFFFFE0) {
                    if(v2 >= v1) {
                        return v3;
                    }
                    if(v3 >= -62) {
                        v += 2;
                        if(arr_b[v2] > -65) {
                            return -1;
                        }
                        continue;
                    }
                }
                else if(v3 < -16) {
                    if(v2 >= v1 - 1) {
                        return Utf8.incompleteStateFor(arr_b, v2, v1);
                    }
                    int v4 = v + 2;
                    int v5 = arr_b[v2];
                    if(v5 <= -65 && (v3 != 0xFFFFFFE0 || v5 >= 0xFFFFFFA0) && (v3 != -19 || v5 < 0xFFFFFFA0)) {
                        v += 3;
                        if(arr_b[v4] > -65) {
                            return -1;
                        }
                        continue;
                    }
                }
                else {
                    if(v2 >= v1 - 2) {
                        return Utf8.incompleteStateFor(arr_b, v2, v1);
                    }
                    int v6 = arr_b[v2];
                    if(v6 <= -65 && (v3 << 28) + (v6 + 0x70) >> 30 == 0) {
                        int v7 = v + 3;
                        if(arr_b[v + 2] <= -65) {
                            v += 4;
                            if(arr_b[v7] > -65) {
                                return -1;
                            }
                            continue;
                        }
                    }
                }
                return -1;
            }
            v = v2;
        }
    }
}

