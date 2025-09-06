package com.google.crypto.tink.shaded.protobuf;

import java.util.Collection;
import java.util.List;

public interface LazyStringList extends ProtocolStringList {
    void add(ByteString arg1);

    void add(byte[] arg1);

    boolean addAllByteArray(Collection arg1);

    boolean addAllByteString(Collection arg1);

    List asByteArrayList();

    byte[] getByteArray(int arg1);

    ByteString getByteString(int arg1);

    Object getRaw(int arg1);

    List getUnderlyingElements();

    LazyStringList getUnmodifiableView();

    void mergeFrom(LazyStringList arg1);

    void set(int arg1, ByteString arg2);

    void set(int arg1, byte[] arg2);
}

