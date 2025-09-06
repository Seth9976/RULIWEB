package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class JsonReader implements Closeable, AutoCloseable {
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
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final char[] buffer;
    private final Reader in;
    private boolean lenient;
    private int limit;
    private int lineNumber;
    private int lineStart;
    private int[] pathIndices;
    private String[] pathNames;
    int peeked;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int pos;
    private int[] stack;
    private int stackSize;

    static {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() {
            @Override  // com.google.gson.internal.JsonReaderInternalAccess
            public void promoteNameToValue(JsonReader jsonReader0) throws IOException {
                if(jsonReader0 instanceof JsonTreeReader) {
                    ((JsonTreeReader)jsonReader0).promoteNameToValue();
                    return;
                }
                switch((jsonReader0.peeked == 0 ? jsonReader0.doPeek() : jsonReader0.peeked)) {
                    case 12: {
                        jsonReader0.peeked = 8;
                        return;
                    }
                    case 13: {
                        jsonReader0.peeked = 9;
                        return;
                    }
                    case 14: {
                        jsonReader0.peeked = 10;
                        return;
                    }
                    default: {
                        throw new IllegalStateException("Expected a name but was " + jsonReader0.peek() + jsonReader0.locationString());
                    }
                }
            }
        };
    }

    public JsonReader(Reader reader0) {
        this.lenient = false;
        this.buffer = new char[0x400];
        this.pos = 0;
        this.limit = 0;
        this.lineNumber = 0;
        this.lineStart = 0;
        this.peeked = 0;
        int[] arr_v = new int[0x20];
        this.stack = arr_v;
        this.stackSize = 1;
        arr_v[0] = 6;
        this.pathNames = new String[0x20];
        this.pathIndices = new int[0x20];
        if(reader0 == null) {
            throw new NullPointerException("in == null");
        }
        this.in = reader0;
    }

    public void beginArray() throws IOException {
        if((this.peeked == 0 ? this.doPeek() : this.peeked) != 3) {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + this.peek() + this.locationString());
        }
        this.push(1);
        this.pathIndices[this.stackSize - 1] = 0;
        this.peeked = 0;
    }

    public void beginObject() throws IOException {
        if((this.peeked == 0 ? this.doPeek() : this.peeked) != 1) {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + this.peek() + this.locationString());
        }
        this.push(3);
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
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }

    private void consumeNonExecutePrefix() throws IOException {
        this.nextNonWhitespace(true);
        int v = this.pos;
        this.pos = v - 1;
        if((v + 4 <= this.limit || this.fillBuffer(5)) && (this.buffer[v - 1] == 41 && this.buffer[v] == 93 && this.buffer[v + 1] == 0x7D && this.buffer[v + 2] == 39 && this.buffer[v + 3] == 10)) {
            this.pos += 5;
        }
    }

    int doPeek() throws IOException {
        int[] arr_v = this.stack;
        int v = this.stackSize;
        int v1 = arr_v[v - 1];
        if(v1 == 1) {
            arr_v[v - 1] = 2;
            goto label_37;
        }
        if(v1 == 2) {
            switch(this.nextNonWhitespace(true)) {
                case 44: {
                    goto label_37;
                }
                case 59: {
                    this.checkLenient();
                    goto label_37;
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
        if(v1 != 3 && v1 != 5) {
            switch(v1) {
                case 4: {
                    arr_v[v - 1] = 5;
                    int v2 = this.nextNonWhitespace(true);
                    if(v2 != 58) {
                        if(v2 != 61) {
                            throw this.syntaxError("Expected \':\'");
                        }
                        this.checkLenient();
                        if(this.pos < this.limit || this.fillBuffer(1)) {
                            int v3 = this.pos;
                            if(this.buffer[v3] == 62) {
                                this.pos = v3 + 1;
                                break;
                            }
                        }
                    }
                    break;
                }
                case 6: {
                    if(this.lenient) {
                        this.consumeNonExecutePrefix();
                    }
                    this.stack[this.stackSize - 1] = 7;
                    break;
                }
                case 7: {
                    if(this.nextNonWhitespace(false) == -1) {
                        this.peeked = 17;
                        return 17;
                    }
                    this.checkLenient();
                    --this.pos;
                    break;
                }
                case 8: {
                    throw new IllegalStateException("JsonReader is closed");
                }
            }
        label_37:
            int v4 = this.nextNonWhitespace(true);
            switch(v4) {
                case 34: {
                    this.peeked = 9;
                    return 9;
                }
                case 39: {
                    this.checkLenient();
                    this.peeked = 8;
                    return 8;
                }
                case 44: 
                case 59: {
                    break;
                }
                case 91: {
                    this.peeked = 3;
                    return 3;
                }
                case 93: {
                    if(v1 == 1) {
                        this.peeked = 4;
                        return 4;
                    }
                    break;
                }
                default: {
                    if(v4 != 0x7B) {
                        --this.pos;
                        int v5 = this.peekKeyword();
                        if(v5 != 0) {
                            return v5;
                        }
                        int v6 = this.peekNumber();
                        if(v6 != 0) {
                            return v6;
                        }
                        if(!this.isLiteral(this.buffer[this.pos])) {
                            throw this.syntaxError("Expected value");
                        }
                        this.checkLenient();
                        this.peeked = 10;
                        return 10;
                    }
                    this.peeked = 1;
                    return 1;
                }
            }
            if(v1 != 1 && v1 != 2) {
                throw this.syntaxError("Unexpected value");
            }
            this.checkLenient();
            --this.pos;
            this.peeked = 7;
            return 7;
        }
        arr_v[v - 1] = 4;
        if(v1 == 5) {
            switch(this.nextNonWhitespace(true)) {
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
                this.peeked = 13;
                return 13;
            }
            case 39: {
                this.checkLenient();
                this.peeked = 12;
                return 12;
            }
            default: {
                if(v7 != 0x7D) {
                    this.checkLenient();
                    --this.pos;
                    if(!this.isLiteral(((char)v7))) {
                        throw this.syntaxError("Expected name");
                    }
                    this.peeked = 14;
                    return 14;
                }
                if(v1 == 5) {
                    throw this.syntaxError("Expected name");
                }
                this.peeked = 2;
                return 2;
            }
        }
    }

    public void endArray() throws IOException {
        if((this.peeked == 0 ? this.doPeek() : this.peeked) != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + this.peek() + this.locationString());
        }
        int v = this.stackSize;
        this.stackSize = v - 1;
        ++this.pathIndices[v - 2];
        this.peeked = 0;
    }

    public void endObject() throws IOException {
        if((this.peeked == 0 ? this.doPeek() : this.peeked) != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + this.peek() + this.locationString());
        }
        int v = this.stackSize;
        this.stackSize = v - 1;
        this.pathNames[v - 1] = null;
        ++this.pathIndices[v - 2];
        this.peeked = 0;
    }

    private boolean fillBuffer(int v) throws IOException {
        char[] arr_c = this.buffer;
        int v1 = this.pos;
        this.lineStart -= v1;
        int v2 = this.limit;
        if(v2 == v1) {
            this.limit = 0;
        }
        else {
            int v3 = v2 - v1;
            this.limit = v3;
            System.arraycopy(arr_c, v1, arr_c, 0, v3);
        }
        this.pos = 0;
        int v4;
        while((v4 = this.in.read(arr_c, this.limit, arr_c.length - this.limit)) != -1) {
            int v5 = this.limit + v4;
            this.limit = v5;
            if(this.lineNumber == 0 && this.lineStart == 0 && v5 > 0 && arr_c[0] == 0xFEFF) {
                ++this.pos;
                this.lineStart = 1;
                ++v;
            }
            if(v5 >= v) {
                return true;
            }
        }
        return false;
    }

    public String getPath() {
        StringBuilder stringBuilder0 = new StringBuilder("$");
        int v = this.stackSize;
        for(int v1 = 0; v1 < v; ++v1) {
            switch(this.stack[v1]) {
                case 1: 
                case 2: {
                    stringBuilder0.append('[');
                    stringBuilder0.append(this.pathIndices[v1]);
                    stringBuilder0.append(']');
                    break;
                }
                case 3: 
                case 4: 
                case 5: {
                    stringBuilder0.append('.');
                    String s = this.pathNames[v1];
                    if(s != null) {
                        stringBuilder0.append(s);
                    }
                }
            }
        }
        return stringBuilder0.toString();
    }

    public boolean hasNext() throws IOException {
        int v = this.peeked == 0 ? this.doPeek() : this.peeked;
        return v != 2 && v != 4;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    private boolean isLiteral(char c) throws IOException {
        switch(c) {
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

    String locationString() {
        return " at line " + (this.lineNumber + 1) + " column " + (this.pos - this.lineStart + 1) + " path " + this.getPath();
    }

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
                throw new IllegalStateException("Expected a boolean but was " + this.peek() + this.locationString());
            }
        }
    }

    public double nextDouble() throws IOException {
        int v = this.peeked == 0 ? this.doPeek() : this.peeked;
        switch(v) {
            case 8: 
            case 9: {
                this.peekedString = this.nextQuotedValue(((char)(v == 8 ? 39 : 34)));
                break;
            }
            case 10: {
                this.peekedString = this.nextUnquotedValue();
                break;
            }
            case 11: {
                break;
            }
            case 15: {
                this.peeked = 0;
                int v1 = this.stackSize - 1;
                ++this.pathIndices[v1];
                return (double)this.peekedLong;
            }
            case 16: {
                this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
                this.pos += this.peekedNumberLength;
                break;
            }
            default: {
                throw new IllegalStateException("Expected a double but was " + this.peek() + this.locationString());
            }
        }
        this.peeked = 11;
        double f = Double.parseDouble(this.peekedString);
        if(!this.lenient && (Double.isNaN(f) || Double.isInfinite(f))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + f + this.locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int v2 = this.stackSize - 1;
        ++this.pathIndices[v2];
        return f;
    }

    public int nextInt() throws IOException {
        int v = this.peeked == 0 ? this.doPeek() : this.peeked;
        switch(v) {
            case 8: {
            label_12:
                this.peekedString = v == 10 ? this.nextUnquotedValue() : this.nextQuotedValue(((char)(v == 8 ? 39 : 34)));
                try {
                    int v3 = Integer.parseInt(this.peekedString);
                    this.peeked = 0;
                    int v4 = this.stackSize - 1;
                    ++this.pathIndices[v4];
                    return v3;
                }
                catch(NumberFormatException unused_ex) {
                    goto label_23;
                }
            }
            case 15: {
                int v1 = (int)this.peekedLong;
                if(this.peekedLong != ((long)v1)) {
                    throw new NumberFormatException("Expected an int but was " + this.peekedLong + this.locationString());
                }
                this.peeked = 0;
                int v2 = this.stackSize - 1;
                ++this.pathIndices[v2];
                return v1;
            label_10:
                if(v != 9 && v != 10) {
                    throw new IllegalStateException("Expected an int but was " + this.peek() + this.locationString());
                }
                goto label_12;
            }
            case 16: {
                break;
            }
            default: {
                goto label_10;
            }
        }
        this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
        this.pos += this.peekedNumberLength;
    label_23:
        this.peeked = 11;
        double f = Double.parseDouble(this.peekedString);
        if(((double)(((int)f))) != f) {
            throw new NumberFormatException("Expected an int but was " + this.peekedString + this.locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int v5 = this.stackSize - 1;
        ++this.pathIndices[v5];
        return (int)f;
    }

    public long nextLong() throws IOException {
        int v = this.peeked == 0 ? this.doPeek() : this.peeked;
        switch(v) {
            case 8: {
            label_9:
                this.peekedString = v == 10 ? this.nextUnquotedValue() : this.nextQuotedValue(((char)(v == 8 ? 39 : 34)));
                try {
                    long v2 = Long.parseLong(this.peekedString);
                    this.peeked = 0;
                    int v3 = this.stackSize - 1;
                    ++this.pathIndices[v3];
                    return v2;
                }
                catch(NumberFormatException unused_ex) {
                    goto label_20;
                }
            }
            case 15: {
                this.peeked = 0;
                int v1 = this.stackSize - 1;
                ++this.pathIndices[v1];
                return this.peekedLong;
            label_7:
                if(v != 9 && v != 10) {
                    throw new IllegalStateException("Expected a long but was " + this.peek() + this.locationString());
                }
                goto label_9;
            }
            case 16: {
                break;
            }
            default: {
                goto label_7;
            }
        }
        this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
        this.pos += this.peekedNumberLength;
    label_20:
        this.peeked = 11;
        double f = Double.parseDouble(this.peekedString);
        if(((double)(((long)f))) != f) {
            throw new NumberFormatException("Expected a long but was " + this.peekedString + this.locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int v4 = this.stackSize - 1;
        ++this.pathIndices[v4];
        return (long)f;
    }

    public String nextName() throws IOException {
        String s;
        switch((this.peeked == 0 ? this.doPeek() : this.peeked)) {
            case 12: {
                s = this.nextQuotedValue('\'');
                break;
            }
            case 13: {
                s = this.nextQuotedValue('\"');
                break;
            }
            case 14: {
                s = this.nextUnquotedValue();
                break;
            }
            default: {
                throw new IllegalStateException("Expected a name but was " + this.peek() + this.locationString());
            }
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = s;
        return s;
    }

    private int nextNonWhitespace(boolean z) throws IOException {
        char[] arr_c = this.buffer;
        int v = this.pos;
        int v1 = this.limit;
        while(true) {
            if(v == v1) {
                this.pos = v;
                if(!this.fillBuffer(1)) {
                    if(z) {
                        throw new EOFException("End of input" + this.locationString());
                    }
                    return -1;
                }
                v = this.pos;
                v1 = this.limit;
            }
            int v2 = arr_c[v];
            switch(v2) {
                case 10: {
                    ++this.lineNumber;
                    this.lineStart = v + 1;
                    break;
                }
                case 9: 
                case 13: 
                case 0x20: {
                    break;
                }
                case 35: {
                    this.pos = v + 1;
                    this.checkLenient();
                    this.skipToEndOfLine();
                    v = this.pos;
                    v1 = this.limit;
                    continue;
                }
                case 0x2F: {
                    this.pos = v + 1;
                    if(v + 1 == v1) {
                        this.pos = v;
                        boolean z1 = this.fillBuffer(2);
                        ++this.pos;
                        if(z1) {
                            goto label_24;
                        }
                        return 0x2F;
                    }
                label_24:
                    this.checkLenient();
                    int v3 = this.pos;
                    switch(arr_c[v3]) {
                        case 42: {
                            this.pos = v3 + 1;
                            if(!this.skipTo("*/")) {
                                throw this.syntaxError("Unterminated comment");
                            }
                            v = this.pos + 2;
                            v1 = this.limit;
                            continue;
                        }
                        case 0x2F: {
                            this.pos = v3 + 1;
                            this.skipToEndOfLine();
                            v = this.pos;
                            v1 = this.limit;
                            continue;
                        }
                        default: {
                            return 0x2F;
                        }
                    }
                }
                default: {
                    this.pos = v + 1;
                    return v2;
                }
            }
            ++v;
        }
    }

    public void nextNull() throws IOException {
        if((this.peeked == 0 ? this.doPeek() : this.peeked) != 7) {
            throw new IllegalStateException("Expected null but was " + this.peek() + this.locationString());
        }
        this.peeked = 0;
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
    }

    private String nextQuotedValue(char c) throws IOException {
        int v3;
        char[] arr_c = this.buffer;
        StringBuilder stringBuilder0 = null;
        do {
            int v = this.pos;
            int v1 = this.limit;
        alab1:
            while(true) {
                int v2 = v1;
                v3 = v;
                while(true) {
                    if(v >= v2) {
                        break alab1;
                    }
                    int v4 = arr_c[v];
                    if(v4 == c) {
                        this.pos = v + 1;
                        int v5 = v + 1 - v3 - 1;
                        if(stringBuilder0 == null) {
                            return new String(arr_c, v3, v5);
                        }
                        stringBuilder0.append(arr_c, v3, v5);
                        return stringBuilder0.toString();
                    }
                    switch(v4) {
                        case 10: {
                            ++this.lineNumber;
                            this.lineStart = v + 1;
                            ++v;
                            continue;
                        }
                        case 92: {
                            break;
                        }
                        default: {
                            ++v;
                            continue;
                        }
                    }
                    this.pos = v + 1;
                    int v6 = v + 1 - v3;
                    if(stringBuilder0 == null) {
                        stringBuilder0 = new StringBuilder(Math.max(v6 * 2, 16));
                    }
                    stringBuilder0.append(arr_c, v3, v6 - 1);
                    stringBuilder0.append(this.readEscapeCharacter());
                    v = this.pos;
                    v1 = this.limit;
                    break;
                }
            }
            if(stringBuilder0 == null) {
                stringBuilder0 = new StringBuilder(Math.max((v - v3) * 2, 16));
            }
            stringBuilder0.append(arr_c, v3, v - v3);
            this.pos = v;
        }
        while(this.fillBuffer(1));
        throw this.syntaxError("Unterminated string");
    }

    public String nextString() throws IOException {
        String s;
    alab1:
        switch(this.peeked) {
            case 0: {
                switch(this.doPeek()) {
                    case 8: {
                        s = this.nextQuotedValue('\'');
                        break alab1;
                    }
                    case 9: {
                        s = this.nextQuotedValue('\"');
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
                    case 15: {
                        s = Long.toString(this.peekedLong);
                        break alab1;
                    }
                    case 16: {
                        s = new String(this.buffer, this.pos, this.peekedNumberLength);
                        this.pos += this.peekedNumberLength;
                        break alab1;
                    }
                    default: {
                        throw new IllegalStateException("Expected a string but was " + this.peek() + this.locationString());
                    }
                }
            }
            case 8: {
                s = this.nextQuotedValue('\'');
                break;
            }
            case 9: {
                s = this.nextQuotedValue('\"');
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
            case 15: {
                s = Long.toString(this.peekedLong);
                break;
            }
            case 16: {
                s = new String(this.buffer, this.pos, this.peekedNumberLength);
                this.pos += this.peekedNumberLength;
                break;
            }
            default: {
                throw new IllegalStateException("Expected a string but was " + this.peek() + this.locationString());
            }
        }
        this.peeked = 0;
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return s;
    }

    private String nextUnquotedValue() throws IOException {
        String s;
        StringBuilder stringBuilder0 = null;
        int v = 0;
        do {
            int v1 = 0;
        alab1:
            while(true) {
                int v2 = this.pos;
                if(v2 + v1 < this.limit) {
                    switch(this.buffer[v2 + v1]) {
                        case 35: 
                        case 0x2F: 
                        case 59: 
                        case 61: 
                        case 92: {
                            this.checkLenient();
                            break alab1;
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
                            break alab1;
                        }
                        default: {
                            ++v1;
                            continue;
                        }
                    }
                }
                if(v1 >= this.buffer.length) {
                    goto label_14;
                }
                if(!this.fillBuffer(v1 + 1)) {
                    break;
                }
            }
            v = v1;
            break;
        label_14:
            if(stringBuilder0 == null) {
                stringBuilder0 = new StringBuilder(Math.max(v1, 16));
            }
            stringBuilder0.append(this.buffer, this.pos, v1);
            this.pos += v1;
        }
        while(this.fillBuffer(1));
        if(stringBuilder0 == null) {
            s = new String(this.buffer, this.pos, v);
        }
        else {
            stringBuilder0.append(this.buffer, this.pos, v);
            s = stringBuilder0.toString();
        }
        this.pos += v;
        return s;
    }

    public JsonToken peek() throws IOException {
        switch((this.peeked == 0 ? this.doPeek() : this.peeked)) {
            case 1: {
                return JsonToken.BEGIN_OBJECT;
            }
            case 2: {
                return JsonToken.END_OBJECT;
            }
            case 3: {
                return JsonToken.BEGIN_ARRAY;
            }
            case 4: {
                return JsonToken.END_ARRAY;
            }
            case 5: 
            case 6: {
                return JsonToken.BOOLEAN;
            }
            case 7: {
                return JsonToken.NULL;
            }
            case 8: 
            case 9: 
            case 10: 
            case 11: {
                return JsonToken.STRING;
            }
            case 12: 
            case 13: 
            case 14: {
                return JsonToken.NAME;
            }
            case 15: 
            case 16: {
                return JsonToken.NUMBER;
            }
            case 17: {
                return JsonToken.END_DOCUMENT;
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
        switch(this.buffer[this.pos]) {
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
            if(this.pos + v2 >= this.limit && !this.fillBuffer(v2 + 1)) {
                return 0;
            }
            int v3 = this.buffer[this.pos + v2];
            if(v3 != s.charAt(v2) && v3 != s1.charAt(v2)) {
                return 0;
            }
        }
        if((this.pos + v1 < this.limit || this.fillBuffer(v1 + 1)) && this.isLiteral(this.buffer[this.pos + v1])) {
            return 0;
        }
        this.pos += v1;
        this.peeked = v;
        return v;
    }

    private int peekNumber() throws IOException {
        char[] arr_c = this.buffer;
        int v = this.pos;
        int v1 = this.limit;
        int v2 = 0;
        int v3 = 0;
        int v4 = 1;
        long v5 = 0L;
        boolean z = false;
        while(true) {
            if(v + v2 == v1) {
                if(v2 == arr_c.length) {
                    return 0;
                }
                if(!this.fillBuffer(v2 + 1)) {
                    goto label_36;
                }
                v = this.pos;
                v1 = this.limit;
            }
            int v6 = arr_c[v + v2];
        alab1:
            switch(v6) {
                case 43: {
                    if(v3 != 5) {
                        return 0;
                    }
                    v3 = 6;
                    break;
                }
                case 45: {
                    switch(v3) {
                        case 0: {
                            v3 = 1;
                            z = true;
                            break alab1;
                        }
                        case 5: {
                            v3 = 6;
                            break alab1;
                        }
                        default: {
                            return 0;
                        }
                    }
                }
                case 46: {
                    if(v3 == 2) {
                        v3 = 3;
                        break;
                    }
                    return 0;
                }
                case 69: 
                case 101: {
                    if(v3 != 2 && v3 != 4) {
                        return 0;
                    }
                    v3 = 5;
                    break;
                }
                default: {
                    if(v6 >= 0x30 && v6 <= 57) {
                        if(v3 == 0 || v3 == 1) {
                            v5 = (long)(-(v6 - 0x30));
                            v3 = 2;
                        }
                        else {
                            switch(v3) {
                                case 2: {
                                    if(v5 == 0L) {
                                        return 0;
                                    }
                                    long v7 = 10L * v5 - ((long)(v6 - 0x30));
                                    int v8 = Long.compare(v5, -922337203685477580L);
                                    v4 &= (v8 > 0 || v8 == 0 && v7 < v5 ? 1 : 0);
                                    v5 = v7;
                                    break;
                                }
                                case 3: {
                                    v3 = 4;
                                    break;
                                }
                                case 5: 
                                case 6: {
                                    v3 = 7;
                                }
                            }
                        }
                        break;
                    }
                    if(!this.isLiteral(((char)v6))) {
                    label_36:
                        if(v3 == 2 && v4 != 0 && (v5 != 0x8000000000000000L || z) && (v5 != 0L || !z)) {
                            if(!z) {
                                v5 = -v5;
                            }
                            this.peekedLong = v5;
                            this.pos += v2;
                            this.peeked = 15;
                            return 15;
                        }
                        if(v3 != 2 && v3 != 4 && v3 != 7) {
                            return 0;
                        }
                        this.peekedNumberLength = v2;
                        this.peeked = 16;
                        return 16;
                    }
                    return 0;
                }
            }
            ++v2;
        }
    }

    private void push(int v) {
        int v1 = this.stackSize;
        int[] arr_v = this.stack;
        if(v1 == arr_v.length) {
            this.stack = Arrays.copyOf(arr_v, v1 * 2);
            this.pathIndices = Arrays.copyOf(this.pathIndices, v1 * 2);
            this.pathNames = (String[])Arrays.copyOf(this.pathNames, v1 * 2);
        }
        int v2 = this.stackSize;
        this.stackSize = v2 + 1;
        this.stack[v2] = v;
    }

    private char readEscapeCharacter() throws IOException {
        int v4;
        if(this.pos == this.limit && !this.fillBuffer(1)) {
            throw this.syntaxError("Unterminated escape sequence");
        }
        int v = this.pos;
        this.pos = v + 1;
        char c = this.buffer[v];
        switch(c) {
            case 10: {
                ++this.lineNumber;
                this.lineStart = v + 1;
                return 10;
            }
            case 34: 
            case 39: 
            case 0x2F: 
            case 92: {
                return c;
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
                if(v + 5 > this.limit && !this.fillBuffer(4)) {
                    throw this.syntaxError("Unterminated escape sequence");
                }
                int v1 = this.pos;
                int v2 = v1 + 4;
                char c1 = '\u0000';
                while(v1 < v2) {
                    int v3 = this.buffer[v1];
                    if(v3 >= 0x30 && v3 <= 57) {
                        v4 = v3 - 0x30;
                    }
                    else if(v3 < 97 || v3 > 102) {
                        if(v3 < 65 || v3 > 70) {
                            throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
                        }
                        v4 = v3 - 55;
                    }
                    else {
                        v4 = v3 - 87;
                    }
                    c1 = (char)(((char)(c1 << 4)) + v4);
                    ++v1;
                }
                this.pos += 4;
                return c1;
            }
            default: {
                throw this.syntaxError("Invalid escape sequence");
            }
        }
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    private void skipQuotedValue(char c) throws IOException {
        char[] arr_c = this.buffer;
        do {
            int v = this.pos;
            int v1 = this.limit;
            while(v < v1) {
                int v2 = arr_c[v];
                if(v2 == c) {
                    this.pos = v + 1;
                    return;
                }
                switch(v2) {
                    case 10: {
                        ++this.lineNumber;
                        this.lineStart = v + 1;
                        ++v;
                        continue;
                    }
                    case 92: {
                        break;
                    }
                    default: {
                        ++v;
                        continue;
                    }
                }
                this.pos = v + 1;
                this.readEscapeCharacter();
                v = this.pos;
                v1 = this.limit;
            }
            this.pos = v;
        }
        while(this.fillBuffer(1));
        throw this.syntaxError("Unterminated string");
    }

    private boolean skipTo(String s) throws IOException {
        int v = s.length();
    alab1:
        while(true) {
            if(this.pos + v > this.limit && !this.fillBuffer(v)) {
                return false;
            }
            int v2 = this.pos;
            if(this.buffer[v2] == 10) {
                ++this.lineNumber;
                this.lineStart = v2 + 1;
            }
            else {
                for(int v1 = 0; true; ++v1) {
                    if(v1 >= v) {
                        break alab1;
                    }
                    if(this.buffer[this.pos + v1] != s.charAt(v1)) {
                        break;
                    }
                }
            }
            ++this.pos;
        }
        return true;
    }

    private void skipToEndOfLine() throws IOException {
        while(this.pos < this.limit || this.fillBuffer(1)) {
            int v = this.pos;
            int v1 = v + 1;
            this.pos = v1;
            int v2 = this.buffer[v];
            if(v2 == 10) {
                ++this.lineNumber;
                this.lineStart = v1;
                return;
            }
            if(v2 == 13) {
                break;
            }
        }
    }

    private void skipUnquotedValue() throws IOException {
        int v1;
        do {
            int v = 0;
        alab1:
            while(true) {
                v1 = this.pos;
                if(v1 + v >= this.limit) {
                    goto label_9;
                }
                switch(this.buffer[v1 + v]) {
                    case 35: 
                    case 0x2F: 
                    case 59: 
                    case 61: 
                    case 92: {
                        break alab1;
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
                        this.pos += v;
                        return;
                    }
                    default: {
                        ++v;
                    }
                }
            }
            this.checkLenient();
            this.pos += v;
            return;
        label_9:
            this.pos = v1 + v;
        }
        while(this.fillBuffer(1));
    }

    public void skipValue() throws IOException {
        int v = 0;
        do {
        alab1:
            switch(this.peeked) {
                case 0: {
                    switch(this.doPeek()) {
                        case 1: {
                            this.push(3);
                            ++v;
                            break alab1;
                        }
                        case 2: {
                            --this.stackSize;
                            --v;
                            break alab1;
                        }
                        case 3: {
                            this.push(1);
                            ++v;
                            break alab1;
                        }
                        case 4: {
                            --this.stackSize;
                            --v;
                            break alab1;
                        }
                        case 8: 
                        case 12: {
                            this.skipQuotedValue('\'');
                            break alab1;
                        }
                        case 9: 
                        case 13: {
                            this.skipQuotedValue('\"');
                            break alab1;
                        }
                        case 10: 
                        case 14: {
                            this.skipUnquotedValue();
                            break alab1;
                        }
                        case 16: {
                            this.pos += this.peekedNumberLength;
                            break alab1;
                        }
                        default: {
                            break alab1;
                        }
                    }
                }
                case 1: {
                    this.push(3);
                    ++v;
                    break;
                }
                case 2: {
                    --this.stackSize;
                    --v;
                    break;
                }
                case 3: {
                    this.push(1);
                    ++v;
                    break;
                }
                case 4: {
                    --this.stackSize;
                    --v;
                    break;
                }
                case 8: 
                case 12: {
                    this.skipQuotedValue('\'');
                    break;
                }
                case 9: 
                case 13: {
                    this.skipQuotedValue('\"');
                    break;
                }
                case 10: 
                case 14: {
                    this.skipUnquotedValue();
                    break;
                }
                case 16: {
                    this.pos += this.peekedNumberLength;
                }
            }
            this.peeked = 0;
        }
        while(v != 0);
        int v1 = this.stackSize;
        ++this.pathIndices[v1 - 1];
        this.pathNames[v1 - 1] = "null";
    }

    private IOException syntaxError(String s) throws IOException {
        throw new MalformedJsonException(s + this.locationString());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + this.locationString();
    }
}

