package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Stack;

public class FastParser {
    public static class ParseException extends Exception {
        public ParseException(String s) {
            super(s);
        }

        public ParseException(String s, Throwable throwable0) {
            super("Error instantiating inner object", throwable0);
        }

        public ParseException(Throwable throwable0) {
            super(throwable0);
        }
    }

    private static final char[] zaa;
    private static final char[] zab;
    private static final char[] zac;
    private static final char[] zad;
    private static final char[] zae;
    private static final char[] zaf;
    private static final zai zag;
    private static final zai zah;
    private static final zai zai;
    private static final zai zaj;
    private static final zai zak;
    private static final zai zal;
    private static final zai zam;
    private static final zai zan;
    private final char[] zao;
    private final char[] zap;
    private final char[] zaq;
    private final StringBuilder zar;
    private final StringBuilder zas;
    private final Stack zat;

    static {
        FastParser.zaa = new char[]{'u', 'l', 'l'};
        FastParser.zab = new char[]{'r', 'u', 'e'};
        FastParser.zac = new char[]{'r', 'u', 'e', '\"'};
        FastParser.zad = new char[]{'a', 'l', 's', 'e'};
        FastParser.zae = new char[]{'a', 'l', 's', 'e', '\"'};
        FastParser.zaf = new char[]{'\n'};
        FastParser.zag = (FastParser this, BufferedReader bufferedReader0) -> {
            int v5;
            int v3;
            int v = this.zam(bufferedReader0, this.zaq);
            if(v == 0) {
                return 0;
            }
            char[] arr_c = this.zaq;
            if(v <= 0) {
                throw new ParseException("No number to parse");
            }
            int v1 = arr_c[0];
            int v2 = v1 == 45 ? 1 : 0;
            if(v2 < v) {
                v3 = v2 + 1;
                int v4 = Character.digit(arr_c[v2], 10);
                if(v4 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                v5 = -v4;
            }
            else {
                v3 = v2;
                v5 = 0;
            }
            while(v3 < v) {
                int v6 = Character.digit(arr_c[v3], 10);
                if(v6 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if(v5 < 0xF3333334 || v5 * 10 < (v1 == 45 ? 0x80000000 : 0x80000001) + v6) {
                    throw new ParseException("Number too large");
                }
                v5 = v5 * 10 - v6;
                ++v3;
            }
            if(v2 != 0) {
                if(v3 <= 1) {
                    throw new ParseException("No digits to parse");
                }
                return v5;
            }
            return -v5;
        };
        FastParser.zah = (FastParser this, BufferedReader bufferedReader0) -> {
            long v5;
            int v3;
            int v = this.zam(bufferedReader0, this.zaq);
            if(v == 0) {
                return 0L;
            }
            char[] arr_c = this.zaq;
            if(v <= 0) {
                throw new ParseException("No number to parse");
            }
            int v1 = 0;
            int v2 = arr_c[0];
            if(v2 == 45) {
                v1 = 1;
            }
            if(v1 < v) {
                v3 = v1 + 1;
                int v4 = Character.digit(arr_c[v1], 10);
                if(v4 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                v5 = (long)(-v4);
            }
            else {
                v5 = 0L;
                v3 = v1;
            }
            while(v3 < v) {
                int v6 = Character.digit(arr_c[v3], 10);
                if(v6 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if(v5 < -922337203685477580L || v5 * 10L < (v2 == 45 ? 0x8000000000000000L : 0x8000000000000001L) + ((long)v6)) {
                    throw new ParseException("Number too large");
                }
                v5 = v5 * 10L - ((long)v6);
                ++v3;
            }
            if(v1 != 0) {
                if(v3 <= 1) {
                    throw new ParseException("No digits to parse");
                }
                return v5;
            }
            return -v5;
        };
        FastParser.zai = (FastParser this, BufferedReader bufferedReader0) -> {
            int v = this.zam(bufferedReader0, this.zaq);
            return v == 0 ? 0.0f : Float.parseFloat(new String(this.zaq, 0, v));
        };
        FastParser.zaj = (FastParser this, BufferedReader bufferedReader0) -> {
            int v = this.zam(bufferedReader0, this.zaq);
            return v == 0 ? 0.0 : Double.parseDouble(new String(this.zaq, 0, v));
        };
        FastParser.zak = new zae();
        FastParser.zal = (FastParser this, BufferedReader bufferedReader0) -> this.zap(bufferedReader0, this.zap, this.zar, null);
        FastParser.zam = (FastParser this, BufferedReader bufferedReader0) -> {
            int v = this.zam(bufferedReader0, this.zaq);
            return v == 0 ? null : new BigInteger(new String(this.zaq, 0, v));
        };
        FastParser.zan = (FastParser this, BufferedReader bufferedReader0) -> {
            int v = this.zam(bufferedReader0, this.zaq);
            return v == 0 ? null : new BigDecimal(new String(this.zaq, 0, v));
        };
    }

    public FastParser() {
        this.zao = new char[1];
        this.zap = new char[0x20];
        this.zaq = new char[0x400];
        this.zar = new StringBuilder(0x20);
        this.zas = new StringBuilder(0x400);
        this.zat = new Stack();
    }

    public void parse(InputStream inputStream0, FastJsonResponse fastJsonResponse0) throws ParseException {
        BufferedReader bufferedReader0 = new BufferedReader(new InputStreamReader(inputStream0), 0x400);
        try {
            this.zat.push(0);
            int v1 = this.zai(bufferedReader0);
            switch(v1) {
                case 0: {
                    throw new ParseException("No data to parse");
                }
                case 91: {
                    this.zat.push(5);
                    Map map0 = fastJsonResponse0.getFieldMappings();
                    if(map0.size() != 1) {
                        throw new ParseException("Object array response class must have a single Field");
                    }
                    Object object0 = map0.entrySet().iterator().next();
                    Field fastJsonResponse$Field0 = (Field)((Map.Entry)object0).getValue();
                    ArrayList arrayList0 = this.zav(bufferedReader0, fastJsonResponse$Field0);
                    fastJsonResponse0.addConcreteTypeArrayInternal(fastJsonResponse$Field0, fastJsonResponse$Field0.zae, arrayList0);
                    break;
                }
                case 0x7B: {
                    this.zat.push(1);
                    this.zaz(bufferedReader0, fastJsonResponse0);
                    break;
                }
                default: {
                    throw new ParseException("Unexpected token: " + ((char)v1));
                }
            }
            this.zaw(0);
        }
        catch(IOException iOException0) {
            throw new ParseException(iOException0);
        }
        finally {
            try {
                bufferedReader0.close();
            }
            catch(IOException unused_ex) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
        }
    }

    private static final String zaA(BufferedReader bufferedReader0, char[] arr_c, StringBuilder stringBuilder0, char[] arr_c1) throws ParseException, IOException {
        stringBuilder0.setLength(0);
        bufferedReader0.mark(arr_c.length);
        int v = 0;
        boolean z = false;
        int v1;
        while((v1 = bufferedReader0.read(arr_c)) != -1) {
            for(int v2 = 0; v2 < v1; ++v2) {
                int v3 = arr_c[v2];
                if(Character.isISOControl(((char)v3)) && (arr_c1 == null || arr_c1[0] != v3)) {
                    throw new ParseException("Unexpected control character while reading string");
                }
                switch(v3) {
                    case 34: {
                        if(v == 0) {
                            stringBuilder0.append(arr_c, 0, v2);
                            bufferedReader0.reset();
                            bufferedReader0.skip(((long)(v2 + 1)));
                            return z ? JsonUtils.unescapeString(stringBuilder0.toString()) : stringBuilder0.toString();
                        }
                        v = 0;
                        break;
                    }
                    case 92: {
                        v ^= 1;
                        z = true;
                        break;
                    }
                    default: {
                        v = 0;
                    }
                }
            }
            stringBuilder0.append(arr_c, 0, v1);
            bufferedReader0.mark(arr_c.length);
        }
        throw new ParseException("Unexpected EOF while parsing string");
    }

    static boolean zah(FastParser fastParser0, BufferedReader bufferedReader0, boolean z) {
        return fastParser0.zay(bufferedReader0, false);
    }

    private final char zai(BufferedReader bufferedReader0) throws ParseException, IOException {
        if(bufferedReader0.read(this.zao) != -1) {
            while(Character.isWhitespace(this.zao[0])) {
                if(bufferedReader0.read(this.zao) == -1) {
                    return '\u0000';
                }
                if(false) {
                    break;
                }
            }
            return this.zao[0];
        }
        return '\u0000';
    }

    // 检测为 Lambda 实现
    private final double zaj(BufferedReader bufferedReader0) throws ParseException, IOException [...]

    // 检测为 Lambda 实现
    private final float zak(BufferedReader bufferedReader0) throws ParseException, IOException [...]

    // 检测为 Lambda 实现
    private final int zal(BufferedReader bufferedReader0) throws ParseException, IOException [...]

    @ResultIgnorabilityUnspecified
    private final int zam(BufferedReader bufferedReader0, char[] arr_c) throws ParseException, IOException {
        int v;
        char c = this.zai(bufferedReader0);
        switch(c) {
            case 0: {
                throw new ParseException("Unexpected EOF");
            }
            case 44: {
                throw new ParseException("Missing value");
            }
            case 110: {
                this.zax(bufferedReader0, FastParser.zaa);
                return 0;
            }
            default: {
                bufferedReader0.mark(0x400);
                if(c == 34) {
                    v = 0;
                    int v1 = 0;
                    while(v < 0x400 && bufferedReader0.read(arr_c, v, 1) != -1) {
                        int v2 = arr_c[v];
                        if(Character.isISOControl(((char)v2))) {
                            throw new ParseException("Unexpected control character while reading string");
                        }
                        switch(v2) {
                            case 34: {
                                if(v1 == 0) {
                                    bufferedReader0.reset();
                                    bufferedReader0.skip(((long)(v + 1)));
                                    return v;
                                }
                                v1 = 0;
                                break;
                            }
                            case 92: {
                                v1 ^= 1;
                                break;
                            }
                            default: {
                                v1 = 0;
                            }
                        }
                        ++v;
                    }
                }
                else {
                    arr_c[0] = c;
                    v = 1;
                    while(v < 0x400 && bufferedReader0.read(arr_c, v, 1) != -1) {
                        int v3 = arr_c[v];
                        if(v3 != 44 && v3 != 0x7D && !Character.isWhitespace(((char)v3)) && arr_c[v] != 93) {
                            ++v;
                            continue;
                        }
                        bufferedReader0.reset();
                        bufferedReader0.skip(((long)(v - 1)));
                        arr_c[v] = '\u0000';
                        return v;
                    }
                }
                throw v == 0x400 ? new ParseException("Absurdly long value") : new ParseException("Unexpected EOF");
            }
        }
    }

    // 检测为 Lambda 实现
    private final long zan(BufferedReader bufferedReader0) throws ParseException, IOException [...]

    // 检测为 Lambda 实现
    private final String zao(BufferedReader bufferedReader0) throws ParseException, IOException [...]

    private final String zap(BufferedReader bufferedReader0, char[] arr_c, StringBuilder stringBuilder0, char[] arr_c1) throws ParseException, IOException {
        switch(this.zai(bufferedReader0)) {
            case 34: {
                return FastParser.zaA(bufferedReader0, arr_c, stringBuilder0, arr_c1);
            }
            case 110: {
                this.zax(bufferedReader0, FastParser.zaa);
                return null;
            }
            default: {
                throw new ParseException("Expected string");
            }
        }
    }

    @ResultIgnorabilityUnspecified
    private final String zaq(BufferedReader bufferedReader0) throws ParseException, IOException {
        this.zat.push(2);
        int v = this.zai(bufferedReader0);
        switch(v) {
            case 34: {
                this.zat.push(3);
                String s = FastParser.zaA(bufferedReader0, this.zap, this.zar, null);
                this.zaw(3);
                if(this.zai(bufferedReader0) != 58) {
                    throw new ParseException("Expected key/value separator");
                }
                return s;
            }
            case 93: {
                this.zaw(2);
                this.zaw(1);
                this.zaw(5);
                return null;
            }
            case 0x7D: {
                this.zaw(2);
                return null;
            }
            default: {
                throw new ParseException("Unexpected token: " + ((char)v));
            }
        }
    }

    private final String zar(BufferedReader bufferedReader0) throws ParseException, IOException {
        int v4;
        bufferedReader0.mark(0x400);
        int v = 1;
        switch(this.zai(bufferedReader0)) {
            case 34: {
                if(bufferedReader0.read(this.zao) == -1) {
                    throw new ParseException("Unexpected EOF while parsing string");
                }
                int v1 = this.zao[0];
                int v2 = 0;
                do {
                    if(v1 == 34) {
                        if(v2 == 0) {
                            goto label_63;
                        }
                        v1 = 34;
                        v2 = 1;
                    }
                    v2 = v1 == 92 ? v2 ^ 1 : 0;
                    if(bufferedReader0.read(this.zao) == -1) {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                    v1 = this.zao[0];
                }
                while(!Character.isISOControl(((char)v1)));
                throw new ParseException("Unexpected control character while reading string");
            }
            case 44: {
                throw new ParseException("Missing value");
            }
            case 91: {
                this.zat.push(5);
                bufferedReader0.mark(0x20);
                if(this.zai(bufferedReader0) == 93) {
                    this.zaw(5);
                    goto label_63;
                }
                bufferedReader0.reset();
                int v3 = 0;
                do {
                    v4 = 0;
                label_30:
                    if(v <= 0) {
                        this.zaw(5);
                        goto label_63;
                    }
                    int v5 = this.zai(bufferedReader0);
                    if(v5 == 0) {
                        throw new ParseException("Unexpected EOF while parsing array");
                    }
                    if(Character.isISOControl(((char)v5))) {
                        throw new ParseException("Unexpected control character while reading array");
                    }
                    if(v5 == 34) {
                        if(v4 == 0) {
                            v3 ^= 1;
                        }
                        v5 = 34;
                    }
                    if(v5 == 91) {
                        if(v3 == 0) {
                            ++v;
                        }
                        v5 = 91;
                    }
                    if(v5 == 93 && v3 == 0) {
                        --v;
                    }
                }
                while(v5 != 92 || v3 == 0);
                v4 ^= 1;
                goto label_30;
            }
            case 0x7B: {
                this.zat.push(1);
                bufferedReader0.mark(0x20);
                int v6 = this.zai(bufferedReader0);
                if(v6 == 0x7D) {
                    this.zaw(1);
                }
                else if(v6 == 34) {
                    bufferedReader0.reset();
                    this.zaq(bufferedReader0);
                    while(this.zar(bufferedReader0) != null) {
                    }
                    this.zaw(1);
                }
                else {
                    throw new ParseException("Unexpected token " + ((char)v6));
                }
            label_63:
                int v7 = this.zai(bufferedReader0);
                switch(v7) {
                    case 44: {
                        this.zaw(2);
                        return this.zaq(bufferedReader0);
                    }
                    case 0x7D: {
                        this.zaw(2);
                        return null;
                    }
                    default: {
                        throw new ParseException("Unexpected token " + ((char)v7));
                    }
                }
            }
            default: {
                bufferedReader0.reset();
                this.zam(bufferedReader0, this.zaq);
                goto label_63;
            }
        }
    }

    // 检测为 Lambda 实现
    private final BigDecimal zas(BufferedReader bufferedReader0) throws ParseException, IOException [...]

    // 检测为 Lambda 实现
    private final BigInteger zat(BufferedReader bufferedReader0) throws ParseException, IOException [...]

    private final ArrayList zau(BufferedReader bufferedReader0, zai zai0) throws ParseException, IOException {
        int v = this.zai(bufferedReader0);
        if(v == 110) {
            this.zax(bufferedReader0, FastParser.zaa);
            return null;
        }
        if(v == 91) {
            this.zat.push(5);
            ArrayList arrayList0 = new ArrayList();
            while(true) {
                bufferedReader0.mark(0x400);
                switch(this.zai(bufferedReader0)) {
                    case 0: {
                        throw new ParseException("Unexpected EOF");
                    }
                    case 44: {
                        break;
                    }
                    case 93: {
                        this.zaw(5);
                        return arrayList0;
                    }
                    default: {
                        bufferedReader0.reset();
                        arrayList0.add(zai0.zaa(this, bufferedReader0));
                        break;
                    }
                }
            }
        }
        throw new ParseException("Expected start of array");
    }

    private final ArrayList zav(BufferedReader bufferedReader0, Field fastJsonResponse$Field0) throws ParseException, IOException {
        ArrayList arrayList0 = new ArrayList();
        int v = this.zai(bufferedReader0);
        switch(v) {
            case 93: {
                this.zaw(5);
                return arrayList0;
            }
            case 110: {
                this.zax(bufferedReader0, FastParser.zaa);
                this.zaw(5);
                return null;
            }
            case 0x7B: {
                this.zat.push(1);
            alab1:
                while(true) {
                    try {
                        FastJsonResponse fastJsonResponse0 = fastJsonResponse$Field0.zad();
                        if(!this.zaz(bufferedReader0, fastJsonResponse0)) {
                            return arrayList0;
                        }
                        arrayList0.add(fastJsonResponse0);
                    }
                    catch(InstantiationException instantiationException0) {
                        throw new ParseException("Error instantiating inner object", instantiationException0);
                    }
                    catch(IllegalAccessException illegalAccessException0) {
                        throw new ParseException("Error instantiating inner object", illegalAccessException0);
                    }
                    int v1 = this.zai(bufferedReader0);
                    switch(v1) {
                        case 44: {
                            if(this.zai(bufferedReader0) == 0x7B) {
                                break;
                            }
                            break alab1;
                        }
                        case 93: {
                            this.zaw(5);
                            return arrayList0;
                        }
                        default: {
                            throw new ParseException("Unexpected token: " + ((char)v1));
                        }
                    }
                    this.zat.push(1);
                }
                throw new ParseException("Expected start of next object in array");
            }
            default: {
                throw new ParseException("Unexpected token: " + ((char)v));
            }
        }
    }

    private final void zaw(int v) throws ParseException {
        if(this.zat.isEmpty()) {
            throw new ParseException("Expected state " + v + " but had empty stack");
        }
        int v1 = (int)(((Integer)this.zat.pop()));
        if(v1 != v) {
            throw new ParseException("Expected state " + v + " but had " + v1);
        }
    }

    private final void zax(BufferedReader bufferedReader0, char[] arr_c) throws ParseException, IOException {
        for(int v = 0; v < arr_c.length; v += v1) {
            int v1 = bufferedReader0.read(this.zap, 0, arr_c.length - v);
            if(v1 == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for(int v2 = 0; v2 < v1; ++v2) {
                if(arr_c[v2 + v] != this.zap[v2]) {
                    throw new ParseException("Unexpected character");
                }
            }
        }
    }

    private final boolean zay(BufferedReader bufferedReader0, boolean z) throws ParseException, IOException {
        int v = this.zai(bufferedReader0);
        switch(v) {
            case 34: {
                if(z) {
                    throw new ParseException("No boolean value found in string");
                }
                return this.zay(bufferedReader0, true);
            }
            case 102: {
                this.zax(bufferedReader0, (z ? FastParser.zae : FastParser.zad));
                return false;
            }
            case 110: {
                this.zax(bufferedReader0, FastParser.zaa);
                return false;
            }
            case 0x74: {
                this.zax(bufferedReader0, (z ? FastParser.zac : FastParser.zab));
                return true;
            }
            default: {
                throw new ParseException("Unexpected token: " + ((char)v));
            }
        }
    }

    @ResultIgnorabilityUnspecified
    private final boolean zaz(BufferedReader bufferedReader0, FastJsonResponse fastJsonResponse0) throws ParseException, IOException {
        Map map0 = fastJsonResponse0.getFieldMappings();
        String s = this.zaq(bufferedReader0);
        if(s != null) {
            while(s != null) {
                Field fastJsonResponse$Field0 = (Field)map0.get(s);
                if(fastJsonResponse$Field0 == null) {
                    s = this.zar(bufferedReader0);
                }
                else {
                    this.zat.push(4);
                    int v = fastJsonResponse$Field0.zaa;
                alab1:
                    switch(v) {
                        case 0: {
                            if(fastJsonResponse$Field0.zab) {
                                fastJsonResponse0.zav(fastJsonResponse$Field0, this.zau(bufferedReader0, FastParser.zag));
                            }
                            else {
                                fastJsonResponse0.zau(fastJsonResponse$Field0, this.zal(bufferedReader0));
                            }
                            break;
                        }
                        case 1: {
                            if(fastJsonResponse$Field0.zab) {
                                fastJsonResponse0.zag(fastJsonResponse$Field0, this.zau(bufferedReader0, FastParser.zam));
                            }
                            else {
                                fastJsonResponse0.zae(fastJsonResponse$Field0, this.zat(bufferedReader0));
                            }
                            break;
                        }
                        case 2: {
                            if(fastJsonResponse$Field0.zab) {
                                fastJsonResponse0.zay(fastJsonResponse$Field0, this.zau(bufferedReader0, FastParser.zah));
                            }
                            else {
                                fastJsonResponse0.zax(fastJsonResponse$Field0, this.zan(bufferedReader0));
                            }
                            break;
                        }
                        case 3: {
                            if(fastJsonResponse$Field0.zab) {
                                fastJsonResponse0.zas(fastJsonResponse$Field0, this.zau(bufferedReader0, FastParser.zai));
                            }
                            else {
                                fastJsonResponse0.zaq(fastJsonResponse$Field0, this.zak(bufferedReader0));
                            }
                            break;
                        }
                        case 4: {
                            if(fastJsonResponse$Field0.zab) {
                                fastJsonResponse0.zao(fastJsonResponse$Field0, this.zau(bufferedReader0, FastParser.zaj));
                            }
                            else {
                                fastJsonResponse0.zam(fastJsonResponse$Field0, this.zaj(bufferedReader0));
                            }
                            break;
                        }
                        case 5: {
                            if(fastJsonResponse$Field0.zab) {
                                fastJsonResponse0.zac(fastJsonResponse$Field0, this.zau(bufferedReader0, FastParser.zan));
                            }
                            else {
                                fastJsonResponse0.zaa(fastJsonResponse$Field0, this.zas(bufferedReader0));
                            }
                            break;
                        }
                        case 6: {
                            if(fastJsonResponse$Field0.zab) {
                                fastJsonResponse0.zaj(fastJsonResponse$Field0, this.zau(bufferedReader0, FastParser.zak));
                            }
                            else {
                                fastJsonResponse0.zai(fastJsonResponse$Field0, this.zay(bufferedReader0, false));
                            }
                            break;
                        }
                        case 7: {
                            if(fastJsonResponse$Field0.zab) {
                                fastJsonResponse0.zaC(fastJsonResponse$Field0, this.zau(bufferedReader0, FastParser.zal));
                            }
                            else {
                                fastJsonResponse0.zaA(fastJsonResponse$Field0, this.zao(bufferedReader0));
                            }
                            break;
                        }
                        case 8: {
                            fastJsonResponse0.zal(fastJsonResponse$Field0, Base64Utils.decode(this.zap(bufferedReader0, this.zaq, this.zas, FastParser.zaf)));
                            break;
                        }
                        case 9: {
                            fastJsonResponse0.zal(fastJsonResponse$Field0, Base64Utils.decodeUrlSafe(this.zap(bufferedReader0, this.zaq, this.zas, FastParser.zaf)));
                            break;
                        }
                        case 10: {
                            int v1 = this.zai(bufferedReader0);
                            if(v1 == 110) {
                                this.zax(bufferedReader0, FastParser.zaa);
                                fastJsonResponse0.zaB(fastJsonResponse$Field0, null);
                                break;
                            }
                            else {
                                if(v1 != 0x7B) {
                                    throw new ParseException("Expected start of a map object");
                                }
                                this.zat.push(1);
                                Map map1 = new HashMap();
                            label_64:
                                int v2 = this.zai(bufferedReader0);
                                if(v2 != 0) {
                                    switch(v2) {
                                        case 34: {
                                            String s1 = FastParser.zaA(bufferedReader0, this.zap, this.zar, null);
                                            if(this.zai(bufferedReader0) != 58) {
                                                throw new ParseException("No map value found for key " + s1);
                                            }
                                            if(this.zai(bufferedReader0) == 34) {
                                                ((HashMap)map1).put(s1, FastParser.zaA(bufferedReader0, this.zap, this.zar, null));
                                                int v3 = this.zai(bufferedReader0);
                                                switch(v3) {
                                                    case 44: {
                                                        goto label_64;
                                                    }
                                                    case 0x7D: {
                                                        this.zaw(1);
                                                        fastJsonResponse0.zaB(fastJsonResponse$Field0, map1);
                                                        break alab1;
                                                    }
                                                    default: {
                                                        throw new ParseException("Unexpected character while parsing string map: " + ((char)v3));
                                                    }
                                                }
                                            }
                                            throw new ParseException("Expected String value for key " + s1);
                                        }
                                        case 0x7D: {
                                            this.zaw(1);
                                            fastJsonResponse0.zaB(fastJsonResponse$Field0, map1);
                                            break alab1;
                                        }
                                        default: {
                                            goto label_64;
                                        }
                                    }
                                }
                                throw new ParseException("Unexpected EOF");
                            }
                            goto label_85;
                        }
                        case 11: {
                        label_85:
                            if(fastJsonResponse$Field0.zab) {
                                int v4 = this.zai(bufferedReader0);
                                if(v4 == 110) {
                                    this.zax(bufferedReader0, FastParser.zaa);
                                    fastJsonResponse0.addConcreteTypeArrayInternal(fastJsonResponse$Field0, fastJsonResponse$Field0.zae, null);
                                }
                                else {
                                    this.zat.push(5);
                                    if(v4 != 91) {
                                        throw new ParseException("Expected array start");
                                    }
                                    ArrayList arrayList0 = this.zav(bufferedReader0, fastJsonResponse$Field0);
                                    fastJsonResponse0.addConcreteTypeArrayInternal(fastJsonResponse$Field0, fastJsonResponse$Field0.zae, arrayList0);
                                }
                                break;
                            }
                            else {
                                int v5 = this.zai(bufferedReader0);
                                if(v5 == 110) {
                                    this.zax(bufferedReader0, FastParser.zaa);
                                    fastJsonResponse0.addConcreteTypeInternal(fastJsonResponse$Field0, fastJsonResponse$Field0.zae, null);
                                }
                                else {
                                    this.zat.push(1);
                                    if(v5 != 0x7B) {
                                        throw new ParseException("Expected start of object");
                                    }
                                    try {
                                        FastJsonResponse fastJsonResponse1 = fastJsonResponse$Field0.zad();
                                        this.zaz(bufferedReader0, fastJsonResponse1);
                                        fastJsonResponse0.addConcreteTypeInternal(fastJsonResponse$Field0, fastJsonResponse$Field0.zae, fastJsonResponse1);
                                    }
                                    catch(InstantiationException instantiationException0) {
                                        throw new ParseException("Error instantiating inner object", instantiationException0);
                                    }
                                    catch(IllegalAccessException illegalAccessException0) {
                                        throw new ParseException("Error instantiating inner object", illegalAccessException0);
                                    }
                                }
                            }
                            break;
                        }
                        default: {
                            throw new ParseException("Invalid field type " + v);
                        }
                    }
                    this.zaw(4);
                    this.zaw(2);
                    int v6 = this.zai(bufferedReader0);
                    switch(v6) {
                        case 44: {
                            s = this.zaq(bufferedReader0);
                            break;
                        }
                        case 0x7D: {
                            s = null;
                            break;
                        }
                        default: {
                            throw new ParseException("Expected end of object or field separator, but found: " + ((char)v6));
                        }
                    }
                }
            }
            this.zaw(1);
            return true;
        }
        this.zaw(1);
        return false;
    }
}

