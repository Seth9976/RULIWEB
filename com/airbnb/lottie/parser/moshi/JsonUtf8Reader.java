package com.airbnb.lottie.parser.moshi;

import java.io.EOFException;
import java.io.IOException;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

final class JsonUtf8Reader extends JsonReader {
    private static final ByteString CLOSING_BLOCK_COMMENT = null;
    private static final ByteString DOUBLE_QUOTE_OR_SLASH = null;
    private static final ByteString LINEFEED_OR_CARRIAGE_RETURN = null;
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_BUFFERED_NAME = 15;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 18;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 16;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 17;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private static final ByteString SINGLE_QUOTE_OR_SLASH;
    private static final ByteString UNQUOTED_STRING_TERMINALS;
    private final Buffer buffer;
    private int peeked;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private final BufferedSource source;

    static {
        JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH = ByteString.encodeUtf8("\'\\");
        JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH = ByteString.encodeUtf8("\"\\");
        JsonUtf8Reader.UNQUOTED_STRING_TERMINALS = ByteString.encodeUtf8("{}[]:, \n\t\r\f/\\;#=");
        JsonUtf8Reader.LINEFEED_OR_CARRIAGE_RETURN = ByteString.encodeUtf8("\n\r");
        JsonUtf8Reader.CLOSING_BLOCK_COMMENT = ByteString.encodeUtf8("*/");
    }

    JsonUtf8Reader(BufferedSource bufferedSource0) {
        this.peeked = 0;
        if(bufferedSource0 == null) {
            throw new NullPointerException("source == null");
        }
        this.source = bufferedSource0;
        this.buffer = bufferedSource0.getBuffer();
        this.pushScope(6);
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public void beginArray() throws IOException {
        if((this.peeked == 0 ? this.doPeek() : this.peeked) != 3) {
            throw new JsonDataException("Expected BEGIN_ARRAY but was " + this.peek() + " at path " + this.getPath());
        }
        this.pushScope(1);
        this.pathIndices[this.stackSize - 1] = 0;
        this.peeked = 0;
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public void beginObject() throws IOException {
        if((this.peeked == 0 ? this.doPeek() : this.peeked) != 1) {
            throw new JsonDataException("Expected BEGIN_OBJECT but was " + this.peek() + " at path " + this.getPath());
        }
        this.pushScope(3);
        this.peeked = 0;
    }

    private void checkLenient() throws IOException {
        if(!this.lenient) {
            throw this.syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    @Override
    public void close() throws IOException {
        this.peeked = 0;
        this.scopes[0] = 8;
        this.stackSize = 1;
        this.buffer.clear();
        this.source.close();
    }

    private int doPeek() throws IOException {
        int v = this.scopes[this.stackSize - 1];
        if(v == 1) {
            this.scopes[this.stackSize - 1] = 2;
            goto label_33;
        }
        if(v == 2) {
            int v1 = this.nextNonWhitespace(true);
            this.buffer.readByte();
            switch(v1) {
                case 44: {
                    goto label_33;
                }
                case 59: {
                    this.checkLenient();
                    goto label_33;
                }
                case 93: {
                    this.peeked = 4;
                    return 4;
                }
                default: {
                    throw this.syntaxError("Unterminated array");
                }
            }
        }
        if(v != 3 && v != 5) {
            switch(v) {
                case 4: {
                    this.scopes[this.stackSize - 1] = 5;
                    int v2 = this.nextNonWhitespace(true);
                    this.buffer.readByte();
                    if(v2 != 58) {
                        if(v2 != 61) {
                            throw this.syntaxError("Expected \':\'");
                        }
                        this.checkLenient();
                        if(this.source.request(1L) && this.buffer.getByte(0L) == 62) {
                            this.buffer.readByte();
                            break;
                        }
                    }
                    break;
                }
                case 6: {
                    this.scopes[this.stackSize - 1] = 7;
                    break;
                }
                case 7: {
                    if(this.nextNonWhitespace(false) == -1) {
                        this.peeked = 18;
                        return 18;
                    }
                    this.checkLenient();
                    break;
                }
                case 8: {
                    throw new IllegalStateException("JsonReader is closed");
                }
            }
        label_33:
            int v3 = this.nextNonWhitespace(true);
            if(v3 != 34) {
                switch(v3) {
                    case 39: {
                        this.checkLenient();
                        this.buffer.readByte();
                        this.peeked = 8;
                        return 8;
                    }
                    case 44: 
                    case 59: {
                        break;
                    }
                    case 91: {
                        this.buffer.readByte();
                        this.peeked = 3;
                        return 3;
                    }
                    case 93: {
                        if(v == 1) {
                            this.buffer.readByte();
                            this.peeked = 4;
                            return 4;
                        }
                        break;
                    }
                    default: {
                        if(v3 != 0x7B) {
                            int v4 = this.peekKeyword();
                            if(v4 != 0) {
                                return v4;
                            }
                            int v5 = this.peekNumber();
                            if(v5 != 0) {
                                return v5;
                            }
                            if(!this.isLiteral(this.buffer.getByte(0L))) {
                                throw this.syntaxError("Expected value");
                            }
                            this.checkLenient();
                            this.peeked = 10;
                            return 10;
                        }
                        this.buffer.readByte();
                        this.peeked = 1;
                        return 1;
                    }
                }
                if(v != 1 && v != 2) {
                    throw this.syntaxError("Unexpected value");
                }
                this.checkLenient();
                this.peeked = 7;
                return 7;
            }
            this.buffer.readByte();
            this.peeked = 9;
            return 9;
        }
        this.scopes[this.stackSize - 1] = 4;
        if(v == 5) {
            int v6 = this.nextNonWhitespace(true);
            this.buffer.readByte();
            switch(v6) {
                case 44: {
                    break;
                }
                case 59: {
                    this.checkLenient();
                    break;
                }
                case 0x7D: {
                    this.peeked = 2;
                    return 2;
                }
                default: {
                    throw this.syntaxError("Unterminated object");
                }
            }
        }
        int v7 = this.nextNonWhitespace(true);
        switch(v7) {
            case 34: {
                this.buffer.readByte();
                this.peeked = 13;
                return 13;
            }
            case 39: {
                this.buffer.readByte();
                this.checkLenient();
                this.peeked = 12;
                return 12;
            }
            case 0x7D: {
                if(v == 5) {
                    throw this.syntaxError("Expected name");
                }
                this.buffer.readByte();
                this.peeked = 2;
                return 2;
            }
            default: {
                this.checkLenient();
                if(!this.isLiteral(((int)(((char)v7))))) {
                    throw this.syntaxError("Expected name");
                }
                this.peeked = 14;
                return 14;
            }
        }
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public void endArray() throws IOException {
        if((this.peeked == 0 ? this.doPeek() : this.peeked) != 4) {
            throw new JsonDataException("Expected END_ARRAY but was " + this.peek() + " at path " + this.getPath());
        }
        --this.stackSize;
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        this.peeked = 0;
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public void endObject() throws IOException {
        if((this.peeked == 0 ? this.doPeek() : this.peeked) != 2) {
            throw new JsonDataException("Expected END_OBJECT but was " + this.peek() + " at path " + this.getPath());
        }
        --this.stackSize;
        this.pathNames[this.stackSize] = null;
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        this.peeked = 0;
    }

    private int findName(String s, Options jsonReader$Options0) {
        for(int v = 0; v < jsonReader$Options0.strings.length; ++v) {
            if(s.equals(jsonReader$Options0.strings[v])) {
                this.peeked = 0;
                this.pathNames[this.stackSize - 1] = s;
                return v;
            }
        }
        return -1;
    }

    private int findString(String s, Options jsonReader$Options0) {
        for(int v = 0; v < jsonReader$Options0.strings.length; ++v) {
            if(s.equals(jsonReader$Options0.strings[v])) {
                this.peeked = 0;
                int v1 = this.stackSize - 1;
                ++this.pathIndices[v1];
                return v;
            }
        }
        return -1;
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean hasNext() throws IOException {
        int v = this.peeked == 0 ? this.doPeek() : this.peeked;
        return v != 2 && v != 4 && v != 18;
    }

    private boolean isLiteral(int v) throws IOException {
        switch(v) {
            case 35: 
            case 0x2F: 
            case 59: 
            case 61: 
            case 92: {
                this.checkLenient();
                break;
            }
            case 9: 
            case 10: 
            case 12: 
            case 13: 
            case 0x20: 
            case 44: 
            case 58: 
            case 91: 
            case 93: 
            case 0x7B: 
            case 0x7D: {
                break;
            }
            default: {
                return true;
            }
        }
        return false;
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean nextBoolean() throws IOException {
        switch((this.peeked == 0 ? this.doPeek() : this.peeked)) {
            case 5: {
                this.peeked = 0;
                int v = this.stackSize - 1;
                ++this.pathIndices[v];
                return true;
            }
            case 6: {
                this.peeked = 0;
                int v1 = this.stackSize - 1;
                ++this.pathIndices[v1];
                return false;
            }
            default: {
                throw new JsonDataException("Expected a boolean but was " + this.peek() + " at path " + this.getPath());
            }
        }
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public double nextDouble() throws IOException {
        double f;
    alab1:
        switch(this.peeked) {
            case 0: {
                switch(this.doPeek()) {
                    case 8: {
                        goto label_13;
                    }
                    case 9: {
                        goto label_11;
                    }
                    case 10: {
                        goto label_15;
                    }
                    case 11: {
                        break alab1;
                    }
                    case 16: {
                        goto label_4;
                    }
                    case 17: {
                        goto label_9;
                    }
                    default: {
                        throw new JsonDataException("Expected a double but was " + this.peek() + " at path " + this.getPath());
                    }
                }
            }
            case 8: {
                this.peekedString = this.nextQuotedValue(JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                break;
            }
            case 9: {
                this.peekedString = this.nextQuotedValue(JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                break;
            }
            case 10: {
                this.peekedString = this.nextUnquotedValue();
                break;
            }
            case 11: {
                break;
            }
            case 16: {
            label_4:
                this.peeked = 0;
                int v = this.stackSize - 1;
                ++this.pathIndices[v];
                return (double)this.peekedLong;
            label_9:
                this.peekedString = this.buffer.readUtf8(((long)this.peekedNumberLength));
                break;
            label_11:
                this.peekedString = this.nextQuotedValue(JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                break;
            label_13:
                this.peekedString = this.nextQuotedValue(JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                break;
            label_15:
                this.peekedString = this.nextUnquotedValue();
                break;
            }
            case 17: {
                this.peekedString = this.buffer.readUtf8(((long)this.peekedNumberLength));
                break;
            }
            default: {
                throw new JsonDataException("Expected a double but was " + this.peek() + " at path " + this.getPath());
            }
        }
        try {
            this.peeked = 11;
            f = Double.parseDouble(this.peekedString);
        }
        catch(NumberFormatException unused_ex) {
            throw new JsonDataException("Expected a double but was " + this.peekedString + " at path " + this.getPath());
        }
        if(!this.lenient && (Double.isNaN(f) || Double.isInfinite(f))) {
            throw new JsonEncodingException("JSON forbids NaN and infinities: " + f + " at path " + this.getPath());
        }
        this.peekedString = null;
        this.peeked = 0;
        int v1 = this.stackSize - 1;
        ++this.pathIndices[v1];
        return f;
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public int nextInt() throws IOException {
        double f;
        int v = this.peeked == 0 ? this.doPeek() : this.peeked;
        switch(v) {
            case 8: 
            case 9: {
                String s = v == 9 ? this.nextQuotedValue(JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH) : this.nextQuotedValue(JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                try {
                    this.peekedString = s;
                    int v1 = Integer.parseInt(s);
                    this.peeked = 0;
                    int v2 = this.stackSize - 1;
                    ++this.pathIndices[v2];
                    return v1;
                }
                catch(NumberFormatException unused_ex) {
                    break;
                }
            }
            case 11: {
                break;
            }
            case 16: {
                int v3 = (int)this.peekedLong;
                if(this.peekedLong != ((long)v3)) {
                    throw new JsonDataException("Expected an int but was " + this.peekedLong + " at path " + this.getPath());
                }
                this.peeked = 0;
                int v4 = this.stackSize - 1;
                ++this.pathIndices[v4];
                return v3;
            }
            case 17: {
                this.peekedString = this.buffer.readUtf8(((long)this.peekedNumberLength));
                break;
            }
            default: {
                throw new JsonDataException("Expected an int but was " + this.peek() + " at path " + this.getPath());
            }
        }
        try {
            this.peeked = 11;
            f = Double.parseDouble(this.peekedString);
        }
        catch(NumberFormatException unused_ex) {
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + this.getPath());
        }
        if(((double)(((int)f))) != f) {
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + this.getPath());
        }
        this.peekedString = null;
        this.peeked = 0;
        int v5 = this.stackSize - 1;
        ++this.pathIndices[v5];
        return (int)f;
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public String nextName() throws IOException {
        String s;
    alab1:
        switch(this.peeked) {
            case 0: {
                switch(this.doPeek()) {
                    case 12: {
                        s = this.nextQuotedValue(JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                        break alab1;
                    }
                    case 13: {
                        s = this.nextQuotedValue(JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                        break alab1;
                    }
                    case 14: {
                        s = this.nextUnquotedValue();
                        break alab1;
                    }
                    case 15: {
                        s = this.peekedString;
                        break alab1;
                    }
                    default: {
                        throw new JsonDataException("Expected a name but was " + this.peek() + " at path " + this.getPath());
                    }
                }
            }
            case 12: {
                s = this.nextQuotedValue(JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                break;
            }
            case 13: {
                s = this.nextQuotedValue(JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                break;
            }
            case 14: {
                s = this.nextUnquotedValue();
                break;
            }
            case 15: {
                s = this.peekedString;
                break;
            }
            default: {
                throw new JsonDataException("Expected a name but was " + this.peek() + " at path " + this.getPath());
            }
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = s;
        return s;
    }

    private int nextNonWhitespace(boolean z) throws IOException {
    alab1:
        while(true) {
            int v = 0;
        alab2:
            while(true) {
                if(!this.source.request(((long)(v + 1)))) {
                    break alab1;
                }
                int v1 = this.buffer.getByte(((long)v));
                if(v1 == 9 || v1 == 10 || v1 == 13 || v1 == 0x20) {
                    ++v;
                    continue;
                }
                this.buffer.skip(((long)v));
                if(v1 == 0x2F) {
                    if(!this.source.request(2L)) {
                        return v1;
                    }
                    this.checkLenient();
                    switch(this.buffer.getByte(1L)) {
                        case 42: {
                            this.buffer.readByte();
                            this.buffer.readByte();
                            if(!this.skipToEndOfBlockComment()) {
                                throw this.syntaxError("Unterminated comment");
                            }
                            break alab2;
                        }
                        case 0x2F: {
                            this.buffer.readByte();
                            this.buffer.readByte();
                            this.skipToEndOfLine();
                            break alab2;
                        }
                        default: {
                            return 0x2F;
                        }
                    }
                }
                else {
                    if(v1 == 35) {
                        this.checkLenient();
                        this.skipToEndOfLine();
                        break;
                    }
                    return v1;
                }
                break alab1;
            }
        }
        if(z) {
            throw new EOFException("End of input");
        }
        return -1;
    }

    private String nextQuotedValue(ByteString byteString0) throws IOException {
        StringBuilder stringBuilder0 = null;
        long v;
        while((v = this.source.indexOfElement(byteString0)) != -1L) {
            if(this.buffer.getByte(v) == 92) {
                if(stringBuilder0 == null) {
                    stringBuilder0 = new StringBuilder();
                }
                stringBuilder0.append(this.buffer.readUtf8(v));
                this.buffer.readByte();
                stringBuilder0.append(this.readEscapeCharacter());
                continue;
            }
            if(stringBuilder0 == null) {
                String s = this.buffer.readUtf8(v);
                this.buffer.readByte();
                return s;
            }
            stringBuilder0.append(this.buffer.readUtf8(v));
            this.buffer.readByte();
            return stringBuilder0.toString();
        }
        throw this.syntaxError("Unterminated string");
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public String nextString() throws IOException {
        String s;
    alab1:
        switch(this.peeked) {
            case 0: {
                switch(this.doPeek()) {
                    case 8: {
                        s = this.nextQuotedValue(JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                        break alab1;
                    }
                    case 9: {
                        s = this.nextQuotedValue(JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                        break alab1;
                    }
                    case 10: {
                        s = this.nextUnquotedValue();
                        break alab1;
                    }
                    case 11: {
                        s = this.peekedString;
                        this.peekedString = null;
                        break alab1;
                    }
                    case 16: {
                        s = Long.toString(this.peekedLong);
                        break alab1;
                    }
                    case 17: {
                        s = this.buffer.readUtf8(((long)this.peekedNumberLength));
                        break alab1;
                    }
                    default: {
                        throw new JsonDataException("Expected a string but was " + this.peek() + " at path " + this.getPath());
                    }
                }
            }
            case 8: {
                s = this.nextQuotedValue(JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                break;
            }
            case 9: {
                s = this.nextQuotedValue(JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                break;
            }
            case 10: {
                s = this.nextUnquotedValue();
                break;
            }
            case 11: {
                s = this.peekedString;
                this.peekedString = null;
                break;
            }
            case 16: {
                s = Long.toString(this.peekedLong);
                break;
            }
            case 17: {
                s = this.buffer.readUtf8(((long)this.peekedNumberLength));
                break;
            }
            default: {
                throw new JsonDataException("Expected a string but was " + this.peek() + " at path " + this.getPath());
            }
        }
        this.peeked = 0;
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return s;
    }

    private String nextUnquotedValue() throws IOException {
        long v = this.source.indexOfElement(JsonUtf8Reader.UNQUOTED_STRING_TERMINALS);
        return v == -1L ? "" : this.buffer.readUtf8(v);
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public Token peek() throws IOException {
        switch((this.peeked == 0 ? this.doPeek() : this.peeked)) {
            case 1: {
                return Token.BEGIN_OBJECT;
            }
            case 2: {
                return Token.END_OBJECT;
            }
            case 3: {
                return Token.BEGIN_ARRAY;
            }
            case 4: {
                return Token.END_ARRAY;
            }
            case 5: 
            case 6: {
                return Token.BOOLEAN;
            }
            case 7: {
                return Token.NULL;
            }
            case 8: 
            case 9: 
            case 10: 
            case 11: {
                return Token.STRING;
            }
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                return Token.NAME;
            }
            case 16: 
            case 17: {
                return Token.NUMBER;
            }
            case 18: {
                return Token.END_DOCUMENT;
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    private int peekKeyword() throws IOException {
        int v;
        String s1;
        String s;
        switch(this.buffer.getByte(0L)) {
            case 70: 
            case 102: {
                s = "false";
                s1 = "FALSE";
                v = 6;
                break;
            }
            case 78: 
            case 110: {
                s = "null";
                s1 = "NULL";
                v = 7;
                break;
            }
            case 84: 
            case 0x74: {
                s = "true";
                s1 = "TRUE";
                v = 5;
                break;
            }
            default: {
                return 0;
            }
        }
        int v1 = s.length();
        for(int v2 = 1; v2 < v1; ++v2) {
            if(!this.source.request(((long)(v2 + 1)))) {
                return 0;
            }
            int v3 = this.buffer.getByte(((long)v2));
            if(v3 != s.charAt(v2) && v3 != s1.charAt(v2)) {
                return 0;
            }
        }
        if(this.source.request(((long)(v1 + 1))) && this.isLiteral(this.buffer.getByte(((long)v1)))) {
            return 0;
        }
        this.buffer.skip(((long)v1));
        this.peeked = v;
        return v;
    }

    private int peekNumber() throws IOException {
        int v = 0;
        int v1 = 0;
        int v2 = 1;
        long v3 = 0L;
        boolean z = false;
    alab1:
        while(this.source.request(((long)(v + 1)))) {
            int v4 = this.buffer.getByte(((long)v));
            switch(v4) {
                case 43: {
                    if(v1 != 5) {
                        return 0;
                    }
                    v1 = 6;
                    break;
                }
                case 45: {
                    if(v1 == 0) {
                        v1 = 1;
                        z = true;
                    }
                    else {
                        if(v1 != 5) {
                            return 0;
                        }
                        v1 = 6;
                    }
                    break;
                }
                case 46: {
                    if(v1 == 2) {
                        v1 = 3;
                        break;
                    }
                    return 0;
                }
                case 69: 
                case 101: {
                    if(v1 != 2 && v1 != 4) {
                        return 0;
                    }
                    v1 = 5;
                    break;
                }
                default: {
                    if(v4 < 0x30 || v4 > 57) {
                        goto label_26;
                    }
                    if(v1 == 0 || v1 == 1) {
                        v3 = (long)(-(v4 - 0x30));
                        v1 = 2;
                        break;
                    label_26:
                        if(!this.isLiteral(v4)) {
                            break alab1;
                        }
                        return 0;
                    }
                    else {
                        switch(v1) {
                            case 2: {
                                if(v3 == 0L) {
                                    return 0;
                                }
                                long v5 = 10L * v3 - ((long)(v4 - 0x30));
                                int v6 = Long.compare(v3, -922337203685477580L);
                                v2 &= (v6 > 0 || v6 == 0 && v5 < v3 ? 1 : 0);
                                v3 = v5;
                                break;
                            }
                            case 3: {
                                v1 = 4;
                                break;
                            }
                            default: {
                                if(v1 == 5 || v1 == 6) {
                                    v1 = 7;
                                }
                            }
                        }
                    }
                }
            }
            ++v;
        }
        if(v1 == 2 && v2 != 0 && (v3 != 0x8000000000000000L || z) && (v3 != 0L || !z)) {
            if(!z) {
                v3 = -v3;
            }
            this.peekedLong = v3;
            this.buffer.skip(((long)v));
            this.peeked = 16;
            return 16;
        }
        if(v1 != 2 && v1 != 4 && v1 != 7) {
            return 0;
        }
        this.peekedNumberLength = v;
        this.peeked = 17;
        return 17;
    }

    private char readEscapeCharacter() throws IOException {
        int v3;
        if(this.source.request(1L)) {
            int v = this.buffer.readByte();
            switch(v) {
                case 10: 
                case 34: 
                case 39: 
                case 0x2F: 
                case 92: {
                    return (char)v;
                }
                case 98: {
                    return '\b';
                }
                case 102: {
                    return '\f';
                }
                case 110: {
                    return '\n';
                }
                case 0x72: {
                    return '\r';
                }
                case 0x74: {
                    return '\t';
                }
                case 0x75: {
                    if(!this.source.request(4L)) {
                        throw new EOFException("Unterminated escape sequence at path " + this.getPath());
                    }
                    char c = '\u0000';
                    for(int v1 = 0; v1 < 4; ++v1) {
                        int v2 = this.buffer.getByte(((long)v1));
                        if(v2 >= 0x30 && v2 <= 57) {
                            v3 = v2 - 0x30;
                        }
                        else if(v2 < 97 || v2 > 102) {
                            if(v2 < 65 || v2 > 70) {
                                throw this.syntaxError("\\u" + this.buffer.readUtf8(4L));
                            }
                            v3 = v2 - 55;
                        }
                        else {
                            v3 = v2 - 87;
                        }
                        c = (char)(((char)(c << 4)) + v3);
                    }
                    this.buffer.skip(4L);
                    return c;
                }
                default: {
                    if(!this.lenient) {
                        throw this.syntaxError("Invalid escape sequence: \\" + ((char)v));
                    }
                    return (char)v;
                }
            }
        }
        throw this.syntaxError("Unterminated escape sequence");
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public int selectName(Options jsonReader$Options0) throws IOException {
        int v = this.peeked == 0 ? this.doPeek() : this.peeked;
        if(v >= 12 && v <= 15) {
            if(v == 15) {
                return this.findName(this.peekedString, jsonReader$Options0);
            }
            int v1 = this.source.select(jsonReader$Options0.doubleQuoteSuffix);
            if(v1 != -1) {
                this.peeked = 0;
                this.pathNames[this.stackSize - 1] = jsonReader$Options0.strings[v1];
                return v1;
            }
            String s = this.pathNames[this.stackSize - 1];
            String s1 = this.nextName();
            int v2 = this.findName(s1, jsonReader$Options0);
            if(v2 == -1) {
                this.peeked = 15;
                this.peekedString = s1;
                this.pathNames[this.stackSize - 1] = s;
            }
            return v2;
        }
        return -1;
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public void skipName() throws IOException {
        if(this.failOnUnknown) {
            throw new JsonDataException("Cannot skip unexpected " + this.peek() + " at " + this.getPath());
        }
    alab1:
        switch(this.peeked) {
            case 0: {
                switch(this.doPeek()) {
                    case 12: {
                        this.skipQuotedValue(JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                        break alab1;
                    }
                    case 13: {
                        this.skipQuotedValue(JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                        break alab1;
                    }
                    case 14: {
                        this.skipUnquotedValue();
                        break alab1;
                    }
                    case 15: {
                        break alab1;
                    }
                    default: {
                        throw new JsonDataException("Expected a name but was " + this.peek() + " at path " + this.getPath());
                    }
                }
            }
            case 12: {
                this.skipQuotedValue(JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                break;
            }
            case 13: {
                this.skipQuotedValue(JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                break;
            }
            case 14: {
                this.skipUnquotedValue();
                break;
            }
            case 15: {
                break;
            }
            default: {
                throw new JsonDataException("Expected a name but was " + this.peek() + " at path " + this.getPath());
            }
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = "null";
    }

    private void skipQuotedValue(ByteString byteString0) throws IOException {
        long v;
        while((v = this.source.indexOfElement(byteString0)) != -1L) {
            if(this.buffer.getByte(v) == 92) {
                this.buffer.skip(v + 1L);
                this.readEscapeCharacter();
                continue;
            }
            this.buffer.skip(v + 1L);
            return;
        }
        throw this.syntaxError("Unterminated string");
    }

    private boolean skipToEndOfBlockComment() throws IOException {
        long v = this.source.indexOf(JsonUtf8Reader.CLOSING_BLOCK_COMMENT);
        this.buffer.skip((v == -1L ? v + ((long)JsonUtf8Reader.CLOSING_BLOCK_COMMENT.size()) : this.buffer.size()));
        return v != -1L;
    }

    private void skipToEndOfLine() throws IOException {
        long v = this.source.indexOfElement(JsonUtf8Reader.LINEFEED_OR_CARRIAGE_RETURN);
        this.buffer.skip((v == -1L ? this.buffer.size() : v + 1L));
    }

    private void skipUnquotedValue() throws IOException {
        long v = this.source.indexOfElement(JsonUtf8Reader.UNQUOTED_STRING_TERMINALS);
        Buffer buffer0 = this.buffer;
        if(v == -1L) {
            v = buffer0.size();
        }
        buffer0.skip(v);
    }

    @Override  // com.airbnb.lottie.parser.moshi.JsonReader
    public void skipValue() throws IOException {
        if(this.failOnUnknown) {
            throw new JsonDataException("Cannot skip unexpected " + this.peek() + " at " + this.getPath());
        }
        int v = 0;
        do {
            switch(this.peeked) {
                case 0: {
                    switch(this.doPeek()) {
                        case 1: {
                            goto label_11;
                        }
                        case 2: {
                            goto label_33;
                        }
                        case 3: {
                            goto label_8;
                        }
                        case 4: {
                            goto label_28;
                        }
                        case 8: 
                        case 12: {
                            goto label_16;
                        }
                        case 9: 
                        case 13: {
                            goto label_18;
                        }
                        case 10: 
                        case 14: {
                            goto label_20;
                        }
                        case 17: {
                            goto label_14;
                        }
                        case 18: {
                            throw new JsonDataException("Expected a value but was " + this.peek() + " at path " + this.getPath());
                        }
                        default: {
                            goto label_7;
                        }
                    }
                }
                case 1: {
                    this.pushScope(3);
                    ++v;
                    break;
                }
                case 2: {
                label_33:
                    --v;
                    if(v < 0) {
                        throw new JsonDataException("Expected a value but was " + this.peek() + " at path " + this.getPath());
                    }
                    --this.stackSize;
                    break;
                }
                case 3: {
                    this.pushScope(1);
                    ++v;
                    break;
                }
                case 4: {
                label_28:
                    --v;
                    if(v < 0) {
                        throw new JsonDataException("Expected a value but was " + this.peek() + " at path " + this.getPath());
                    }
                    --this.stackSize;
                    break;
                }
                case 8: 
                case 12: {
                    this.skipQuotedValue(JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                    break;
                }
                case 9: 
                case 13: {
                    this.skipQuotedValue(JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                    break;
                }
                case 10: 
                case 14: {
                    this.skipUnquotedValue();
                    break;
                }
                case 17: {
                    this.buffer.skip(((long)this.peekedNumberLength));
                    break;
                }
                case 18: {
                    throw new JsonDataException("Expected a value but was " + this.peek() + " at path " + this.getPath());
                label_7:
                    break;
                label_8:
                    this.pushScope(1);
                    ++v;
                    break;
                label_11:
                    this.pushScope(3);
                    ++v;
                    break;
                label_14:
                    this.buffer.skip(((long)this.peekedNumberLength));
                    break;
                label_16:
                    this.skipQuotedValue(JsonUtf8Reader.SINGLE_QUOTE_OR_SLASH);
                    break;
                label_18:
                    this.skipQuotedValue(JsonUtf8Reader.DOUBLE_QUOTE_OR_SLASH);
                    break;
                label_20:
                    this.skipUnquotedValue();
                }
            }
            this.peeked = 0;
        }
        while(v != 0);
        int v1 = this.stackSize - 1;
        ++this.pathIndices[v1];
        this.pathNames[this.stackSize - 1] = "null";
    }

    @Override
    public String toString() {
        return "JsonReader(" + this.source + ")";
    }
}

