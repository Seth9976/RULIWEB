package com.google.crypto.tink.shaded.protobuf;

@CheckReturnValue
final class ProtobufLists {
    public static BooleanList emptyBooleanList() {
        return BooleanArrayList.emptyList();
    }

    public static DoubleList emptyDoubleList() {
        return DoubleArrayList.emptyList();
    }

    public static FloatList emptyFloatList() {
        return FloatArrayList.emptyList();
    }

    public static IntList emptyIntList() {
        return IntArrayList.emptyList();
    }

    public static LongList emptyLongList() {
        return LongArrayList.emptyList();
    }

    public static ProtobufList emptyProtobufList() {
        return ProtobufArrayList.emptyList();
    }

    public static ProtobufList mutableCopy(ProtobufList internal$ProtobufList0) {
        int v = internal$ProtobufList0.size();
        return v == 0 ? internal$ProtobufList0.mutableCopyWithCapacity(10) : internal$ProtobufList0.mutableCopyWithCapacity(v * 2);
    }

    public static BooleanList newBooleanList() {
        return new BooleanArrayList();
    }

    public static DoubleList newDoubleList() {
        return new DoubleArrayList();
    }

    public static FloatList newFloatList() {
        return new FloatArrayList();
    }

    public static IntList newIntList() {
        return new IntArrayList();
    }

    public static LongList newLongList() {
        return new LongArrayList();
    }
}

