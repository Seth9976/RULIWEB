package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.List;

public interface LazyStringList extends ProtocolStringList {
    void add(ByteString arg1);

    ByteString getByteString(int arg1);

    List getUnderlyingElements();

    LazyStringList getUnmodifiableView();
}

