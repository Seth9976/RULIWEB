package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = null;
    private static final String UTC_ID = "UTC";

    static {
        ISO8601Utils.TIMEZONE_UTC = TimeZone.getTimeZone("UTC");
    }

    private static boolean checkOffset(String s, int v, char c) {
        return v < s.length() && s.charAt(v) == c;
    }

    public static String format(Date date0) {
        return ISO8601Utils.format(date0, false, ISO8601Utils.TIMEZONE_UTC);
    }

    public static String format(Date date0, boolean z) {
        return ISO8601Utils.format(date0, z, ISO8601Utils.TIMEZONE_UTC);
    }

    public static String format(Date date0, boolean z, TimeZone timeZone0) {
        GregorianCalendar gregorianCalendar0 = new GregorianCalendar(timeZone0, Locale.US);
        gregorianCalendar0.setTime(date0);
        StringBuilder stringBuilder0 = new StringBuilder((z ? 4 : 0) + 19 + (timeZone0.getRawOffset() == 0 ? 1 : 6));
        ISO8601Utils.padInt(stringBuilder0, gregorianCalendar0.get(1), 4);
        int v = 45;
        stringBuilder0.append('-');
        ISO8601Utils.padInt(stringBuilder0, gregorianCalendar0.get(2) + 1, 2);
        stringBuilder0.append('-');
        ISO8601Utils.padInt(stringBuilder0, gregorianCalendar0.get(5), 2);
        stringBuilder0.append('T');
        ISO8601Utils.padInt(stringBuilder0, gregorianCalendar0.get(11), 2);
        stringBuilder0.append(':');
        ISO8601Utils.padInt(stringBuilder0, gregorianCalendar0.get(12), 2);
        stringBuilder0.append(':');
        ISO8601Utils.padInt(stringBuilder0, gregorianCalendar0.get(13), 2);
        if(z) {
            stringBuilder0.append('.');
            ISO8601Utils.padInt(stringBuilder0, gregorianCalendar0.get(14), 3);
        }
        int v1 = timeZone0.getOffset(gregorianCalendar0.getTimeInMillis());
        if(v1 != 0) {
            int v2 = Math.abs(v1 / 60000 / 60);
            int v3 = Math.abs(v1 / 60000 % 60);
            if(v1 >= 0) {
                v = 43;
            }
            stringBuilder0.append(((char)v));
            ISO8601Utils.padInt(stringBuilder0, v2, 2);
            stringBuilder0.append(':');
            ISO8601Utils.padInt(stringBuilder0, v3, 2);
            return stringBuilder0.toString();
        }
        stringBuilder0.append('Z');
        return stringBuilder0.toString();
    }

    private static int indexOfNonDigit(String s, int v) {
        while(v < s.length()) {
            int v1 = s.charAt(v);
            if(v1 >= 0x30 && v1 <= 57) {
                ++v;
                continue;
            }
            return v;
        }
        return s.length();
    }

    private static void padInt(StringBuilder stringBuilder0, int v, int v1) {
        String s = Integer.toString(v);
        for(int v2 = v1 - s.length(); v2 > 0; --v2) {
            stringBuilder0.append('0');
        }
        stringBuilder0.append(s);
    }

    public static Date parse(String s, ParsePosition parsePosition0) throws ParseException {
        int v19;
        TimeZone timeZone0;
        int v17;
        int v16;
        int v15;
        int v11;
        try {
            int v = parsePosition0.getIndex();
            int v1 = v + 4;
            int v2 = ISO8601Utils.parseInt(s, v, v1);
            if(ISO8601Utils.checkOffset(s, v1, '-')) {
                v1 = v + 5;
            }
            int v3 = v1 + 2;
            int v4 = ISO8601Utils.parseInt(s, v1, v3);
            if(ISO8601Utils.checkOffset(s, v3, '-')) {
                v3 = v1 + 3;
            }
            int v5 = v3 + 2;
            int v6 = ISO8601Utils.parseInt(s, v3, v5);
            boolean z = ISO8601Utils.checkOffset(s, v5, 'T');
            if(!z && s.length() <= v5) {
                GregorianCalendar gregorianCalendar0 = new GregorianCalendar(v2, v4 - 1, v6);
                parsePosition0.setIndex(v5);
                return gregorianCalendar0.getTime();
            }
            if(z) {
                int v7 = v3 + 5;
                int v8 = ISO8601Utils.parseInt(s, v3 + 3, v7);
                if(ISO8601Utils.checkOffset(s, v7, ':')) {
                    v7 = v3 + 6;
                }
                int v9 = v7 + 2;
                int v10 = ISO8601Utils.parseInt(s, v7, v9);
                if(ISO8601Utils.checkOffset(s, v9, ':')) {
                    v9 = v7 + 3;
                }
                if(s.length() <= v9 || (s.charAt(v9) == 43 || s.charAt(v9) == 45 || s.charAt(v9) == 90)) {
                    v5 = v9;
                    v15 = v8;
                    v16 = v10;
                    v17 = 0;
                    v11 = 0;
                }
                else {
                    v11 = ISO8601Utils.parseInt(s, v9, v9 + 2);
                    if(v11 > 59 && v11 < 0x3F) {
                        v11 = 59;
                    }
                    if(ISO8601Utils.checkOffset(s, v9 + 2, '.')) {
                        int v12 = ISO8601Utils.indexOfNonDigit(s, v9 + 4);
                        int v13 = Math.min(v12, v9 + 6);
                        int v14 = ISO8601Utils.parseInt(s, v9 + 3, v13);
                        switch(v13 - (v9 + 3)) {
                            case 1: {
                                v14 *= 100;
                                break;
                            }
                            case 2: {
                                v14 *= 10;
                            }
                        }
                        v15 = v8;
                        v5 = v12;
                        v16 = v10;
                        v17 = v14;
                    }
                    else {
                        v15 = v8;
                        v5 = v9 + 2;
                        v16 = v10;
                        v17 = 0;
                    }
                }
            }
            else {
                v15 = 0;
                v16 = 0;
                v17 = 0;
                v11 = 0;
            }
            if(s.length() <= v5) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            int v18 = s.charAt(v5);
            if(v18 == 90) {
                timeZone0 = ISO8601Utils.TIMEZONE_UTC;
                v19 = v5 + 1;
            }
            else {
                if(v18 != 43 && v18 != 45) {
                    throw new IndexOutOfBoundsException("Invalid time zone indicator \'" + ((char)v18) + "\'");
                }
                String s1 = s.substring(v5);
                s1 = s1.length() >= 5 ? s.substring(v5) : s1 + "00";
                v19 = v5 + s1.length();
                if("+0000".equals(s1) || "+00:00".equals(s1)) {
                    timeZone0 = ISO8601Utils.TIMEZONE_UTC;
                }
                else {
                    TimeZone timeZone1 = TimeZone.getTimeZone(("GMT" + s1));
                    String s2 = timeZone1.getID();
                    if(!s2.equals("GMT" + s1) && !s2.replace(":", "").equals("GMT" + s1)) {
                        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + ("GMT" + s1) + " given, resolves to " + timeZone1.getID());
                    }
                    timeZone0 = timeZone1;
                }
            }
            GregorianCalendar gregorianCalendar1 = new GregorianCalendar(timeZone0);
            gregorianCalendar1.setLenient(false);
            gregorianCalendar1.set(1, v2);
            gregorianCalendar1.set(2, v4 - 1);
            gregorianCalendar1.set(5, v6);
            gregorianCalendar1.set(11, v15);
            gregorianCalendar1.set(12, v16);
            gregorianCalendar1.set(13, v11);
            gregorianCalendar1.set(14, v17);
            parsePosition0.setIndex(v19);
            return gregorianCalendar1.getTime();
        }
        catch(IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException indexOutOfBoundsException0) {
            String s3 = indexOutOfBoundsException0.getMessage();
            ParseException parseException0 = new ParseException("Failed to parse date [" + (s == null ? null : "\"" + s + '\"') + "]: " + (s3 != null && !s3.isEmpty() ? indexOutOfBoundsException0.getMessage() : "(" + indexOutOfBoundsException0.getClass().getName() + ")"), parsePosition0.getIndex());
            parseException0.initCause(indexOutOfBoundsException0);
            throw parseException0;
        }
    }

    private static int parseInt(String s, int v, int v1) throws NumberFormatException {
        int v4;
        int v2;
        if(v < 0 || v1 > s.length() || v > v1) {
            throw new NumberFormatException(s);
        }
        if(v < v1) {
            v2 = v + 1;
            int v3 = Character.digit(s.charAt(v), 10);
            if(v3 < 0) {
                throw new NumberFormatException("Invalid number: " + s.substring(v, v1));
            }
            v4 = -v3;
        }
        else {
            v4 = 0;
            v2 = v;
        }
        while(v2 < v1) {
            int v5 = Character.digit(s.charAt(v2), 10);
            if(v5 < 0) {
                throw new NumberFormatException("Invalid number: " + s.substring(v, v1));
            }
            v4 = v4 * 10 - v5;
            ++v2;
        }
        return -v4;
    }
}

