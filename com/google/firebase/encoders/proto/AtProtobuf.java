package com.google.firebase.encoders.proto;

public final class AtProtobuf {
    static final class ProtobufImpl implements Protobuf {
        private final IntEncoding intEncoding;
        private final int tag;

        ProtobufImpl(int v, IntEncoding protobuf$IntEncoding0) {
            this.tag = v;
            this.intEncoding = protobuf$IntEncoding0;
        }

        @Override
        public Class annotationType() {
            return Protobuf.class;
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof Protobuf)) {
                return false;
            }
            int v = ((Protobuf)object0).tag();
            if(this.tag == v) {
                IntEncoding protobuf$IntEncoding0 = ((Protobuf)object0).intEncoding();
                return this.intEncoding.equals(protobuf$IntEncoding0);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (0xDE0D66 ^ this.tag) + (this.intEncoding.hashCode() ^ 2041407134);
        }

        @Override  // com.google.firebase.encoders.proto.Protobuf
        public IntEncoding intEncoding() {
            return this.intEncoding;
        }

        @Override  // com.google.firebase.encoders.proto.Protobuf
        public int tag() {
            return this.tag;
        }

        @Override
        public String toString() {
            return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.tag + "intEncoding=" + this.intEncoding + ')';
        }
    }

    private IntEncoding intEncoding;
    private int tag;

    public AtProtobuf() {
        this.intEncoding = IntEncoding.DEFAULT;
    }

    public Protobuf build() {
        return new ProtobufImpl(this.tag, this.intEncoding);
    }

    public static AtProtobuf builder() {
        return new AtProtobuf();
    }

    public AtProtobuf intEncoding(IntEncoding protobuf$IntEncoding0) {
        this.intEncoding = protobuf$IntEncoding0;
        return this;
    }

    public AtProtobuf tag(int v) {
        this.tag = v;
        return this;
    }
}

