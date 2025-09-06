package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;

@CheckReturnValue
abstract class UnknownFieldSchema {
    abstract void addFixed32(Object arg1, int arg2, int arg3);

    abstract void addFixed64(Object arg1, int arg2, long arg3);

    abstract void addGroup(Object arg1, int arg2, Object arg3);

    abstract void addLengthDelimited(Object arg1, int arg2, ByteString arg3);

    abstract void addVarint(Object arg1, int arg2, long arg3);

    abstract Object getBuilderFromMessage(Object arg1);

    abstract Object getFromMessage(Object arg1);

    abstract int getSerializedSize(Object arg1);

    abstract int getSerializedSizeAsMessageSet(Object arg1);

    abstract void makeImmutable(Object arg1);

    abstract Object merge(Object arg1, Object arg2);

    final void mergeFrom(Object object0, Reader reader0) throws IOException {
        while(reader0.getFieldNumber() != 0x7FFFFFFF && this.mergeOneFieldFrom(object0, reader0)) {
        }
    }

    final boolean mergeOneFieldFrom(Object object0, Reader reader0) throws IOException {
        int v = reader0.getTag();
        switch(v & 7) {
            case 0: {
                this.addVarint(object0, v >>> 3, reader0.readInt64());
                return true;
            }
            case 1: {
                this.addFixed64(object0, v >>> 3, reader0.readFixed64());
                return true;
            }
            default: {
                if((v & 7) != 2) {
                    switch(v & 7) {
                        case 3: {
                            Object object1 = this.newBuilder();
                            this.mergeFrom(object1, reader0);
                            if((v >>> 3 << 3 | 4) != reader0.getTag()) {
                                throw InvalidProtocolBufferException.invalidEndTag();
                            }
                            this.addGroup(object0, v >>> 3, this.toImmutable(object1));
                            return true;
                        }
                        case 4: {
                            return false;
                        }
                        default: {
                            if((v & 7) != 5) {
                                throw InvalidProtocolBufferException.invalidWireType();
                            }
                            this.addFixed32(object0, v >>> 3, reader0.readFixed32());
                            return true;
                        }
                    }
                }
                this.addLengthDelimited(object0, v >>> 3, reader0.readBytes());
                return true;
            }
        }
    }

    abstract Object newBuilder();

    abstract void setBuilderToMessage(Object arg1, Object arg2);

    abstract void setToMessage(Object arg1, Object arg2);

    abstract boolean shouldDiscardUnknownFields(Reader arg1);

    abstract Object toImmutable(Object arg1);

    abstract void writeAsMessageSetTo(Object arg1, Writer arg2) throws IOException;

    abstract void writeTo(Object arg1, Writer arg2) throws IOException;
}

