package com.squareup.moshi;

import java.io.IOException;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

final class JsonValueSource implements AutoCloseable, Source {
    static final ByteString STATE_C_STYLE_COMMENT;
    static final ByteString STATE_DOUBLE_QUOTED;
    static final ByteString STATE_END_OF_JSON;
    static final ByteString STATE_END_OF_LINE_COMMENT;
    static final ByteString STATE_JSON;
    static final ByteString STATE_SINGLE_QUOTED;
    private final Buffer buffer;
    private boolean closed;
    private long limit;
    private final Buffer prefix;
    private final BufferedSource source;
    private int stackSize;
    private ByteString state;

    static {
        JsonValueSource.STATE_JSON = ByteString.encodeUtf8("[]{}\"\'/#");
        JsonValueSource.STATE_SINGLE_QUOTED = ByteString.encodeUtf8("\'\\");
        JsonValueSource.STATE_DOUBLE_QUOTED = ByteString.encodeUtf8("\"\\");
        JsonValueSource.STATE_END_OF_LINE_COMMENT = ByteString.encodeUtf8("\r\n");
        JsonValueSource.STATE_C_STYLE_COMMENT = ByteString.encodeUtf8("*");
        JsonValueSource.STATE_END_OF_JSON = ByteString.EMPTY;
    }

    JsonValueSource(BufferedSource bufferedSource0) {
        this(bufferedSource0, new Buffer(), JsonValueSource.STATE_JSON, 0);
    }

    JsonValueSource(BufferedSource bufferedSource0, Buffer buffer0, ByteString byteString0, int v) {
        this.limit = 0L;
        this.closed = false;
        this.source = bufferedSource0;
        this.buffer = bufferedSource0.getBuffer();
        this.prefix = buffer0;
        this.state = byteString0;
        this.stackSize = v;
    }

    private void advanceLimit(long v) throws IOException {
        long v1;
        while((v1 = this.limit) < v) {
            ByteString byteString0 = JsonValueSource.STATE_END_OF_JSON;
            if(this.state == byteString0) {
                break;
            }
            if(v1 == this.buffer.size()) {
                if(this.limit > 0L) {
                    break;
                }
                this.source.require(1L);
            }
            long v2 = this.buffer.indexOfElement(this.state, this.limit);
            if(v2 == -1L) {
                this.limit = this.buffer.size();
            }
            else {
                int v3 = this.buffer.getByte(v2);
                ByteString byteString1 = this.state;
                ByteString byteString2 = JsonValueSource.STATE_JSON;
                if(byteString1 == byteString2) {
                    switch(v3) {
                        case 34: {
                            this.state = JsonValueSource.STATE_DOUBLE_QUOTED;
                            this.limit = v2 + 1L;
                            break;
                        }
                        case 35: {
                            this.state = JsonValueSource.STATE_END_OF_LINE_COMMENT;
                            this.limit = v2 + 1L;
                            break;
                        }
                        case 39: {
                            this.state = JsonValueSource.STATE_SINGLE_QUOTED;
                            this.limit = v2 + 1L;
                            break;
                        }
                        case 0x2F: {
                            this.source.require(v2 + 2L);
                            int v4 = this.buffer.getByte(v2 + 1L);
                            if(v4 == 0x2F) {
                                this.state = JsonValueSource.STATE_END_OF_LINE_COMMENT;
                                this.limit = v2 + 2L;
                            }
                            else if(v4 == 42) {
                                this.state = JsonValueSource.STATE_C_STYLE_COMMENT;
                                this.limit = v2 + 2L;
                            }
                            else {
                                this.limit = v2 + 1L;
                            }
                            break;
                        }
                        case 91: 
                        case 0x7B: {
                            ++this.stackSize;
                            this.limit = v2 + 1L;
                            break;
                        }
                        case 93: 
                        case 0x7D: {
                            int v5 = this.stackSize - 1;
                            this.stackSize = v5;
                            if(v5 == 0) {
                                this.state = byteString0;
                            }
                            this.limit = v2 + 1L;
                        }
                    }
                }
                else {
                    if(byteString1 != JsonValueSource.STATE_SINGLE_QUOTED && byteString1 != JsonValueSource.STATE_DOUBLE_QUOTED) {
                        if(byteString1 == JsonValueSource.STATE_C_STYLE_COMMENT) {
                            this.source.require(v2 + 2L);
                            if(this.buffer.getByte(v2 + 1L) == 0x2F) {
                                this.limit = v2 + 2L;
                                this.state = byteString2;
                            }
                            else {
                                this.limit = v2 + 1L;
                            }
                        }
                        else {
                            if(byteString1 != JsonValueSource.STATE_END_OF_LINE_COMMENT) {
                                throw new AssertionError();
                            }
                            this.limit = v2 + 1L;
                            this.state = byteString2;
                        }
                        continue;
                    }
                    if(v3 == 92) {
                        this.source.require(v2 + 2L);
                        this.limit = v2 + 2L;
                    }
                    else {
                        if(this.stackSize > 0) {
                            byteString0 = byteString2;
                        }
                        this.state = byteString0;
                        this.limit = v2 + 1L;
                    }
                }
            }
        }
    }

    @Override  // okio.Source
    public void close() throws IOException {
        this.closed = true;
    }

    public void discard() throws IOException {
        this.closed = true;
        while(this.state != JsonValueSource.STATE_END_OF_JSON) {
            this.advanceLimit(0x2000L);
            this.source.skip(this.limit);
        }
    }

    @Override  // okio.Source
    public long read(Buffer buffer0, long v) throws IOException {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        if(v == 0L) {
            return 0L;
        }
        if(!this.prefix.exhausted()) {
            long v1 = this.prefix.read(buffer0, v);
            if(!this.buffer.exhausted()) {
                long v2 = this.read(buffer0, v - v1);
                return v2 == -1L ? v1 : v2 + v1;
            }
            return v1;
        }
        this.advanceLimit(v);
        long v3 = this.limit;
        if(v3 == 0L) {
            if(this.state != JsonValueSource.STATE_END_OF_JSON) {
                throw new AssertionError();
            }
            return -1L;
        }
        long v4 = Math.min(v, v3);
        buffer0.write(this.buffer, v4);
        this.limit -= v4;
        return v4;
    }

    @Override  // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }
}

