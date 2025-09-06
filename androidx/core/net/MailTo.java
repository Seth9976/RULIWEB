package androidx.core.net;

import android.net.Uri;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Map;

public final class MailTo {
    private static final String BCC = "bcc";
    private static final String BODY = "body";
    private static final String CC = "cc";
    private static final String MAILTO = "mailto";
    public static final String MAILTO_SCHEME = "mailto:";
    private static final String SUBJECT = "subject";
    private static final String TO = "to";
    private HashMap mHeaders;

    private MailTo() {
        this.mHeaders = new HashMap();
    }

    public String getBcc() {
        return (String)this.mHeaders.get("bcc");
    }

    public String getBody() {
        return (String)this.mHeaders.get("body");
    }

    public String getCc() {
        return (String)this.mHeaders.get("cc");
    }

    public Map getHeaders() {
        return this.mHeaders;
    }

    public String getSubject() {
        return (String)this.mHeaders.get("subject");
    }

    public String getTo() {
        return (String)this.mHeaders.get("to");
    }

    public static boolean isMailTo(Uri uri0) {
        return uri0 != null && "mailto".equals(uri0.getScheme());
    }

    public static boolean isMailTo(String s) {
        return s != null && s.startsWith("mailto:");
    }

    public static MailTo parse(Uri uri0) throws ParseException {
        return MailTo.parse(uri0.toString());
    }

    public static MailTo parse(String s) throws ParseException {
        String s2;
        String s1;
        Preconditions.checkNotNull(s);
        if(!MailTo.isMailTo(s)) {
            throw new ParseException("Not a mailto scheme");
        }
        int v = s.indexOf(35);
        if(v != -1) {
            s = s.substring(0, v);
        }
        int v1 = s.indexOf(0x3F);
        if(v1 == -1) {
            s1 = Uri.decode(s.substring(7));
            s2 = null;
        }
        else {
            s1 = Uri.decode(s.substring(7, v1));
            s2 = s.substring(v1 + 1);
        }
        MailTo mailTo0 = new MailTo();
        if(s2 != null) {
            String[] arr_s = s2.split("&");
            for(int v2 = 0; v2 < arr_s.length; ++v2) {
                String[] arr_s1 = arr_s[v2].split("=", 2);
                if(arr_s1.length != 0) {
                    String s3 = Uri.decode(arr_s1[0]).toLowerCase(Locale.ROOT);
                    String s4 = arr_s1.length <= 1 ? null : Uri.decode(arr_s1[1]);
                    mailTo0.mHeaders.put(s3, s4);
                }
            }
        }
        String s5 = mailTo0.getTo();
        if(s5 != null) {
            s1 = s1 + ", " + s5;
        }
        mailTo0.mHeaders.put("to", s1);
        return mailTo0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("mailto:?");
        for(Object object0: this.mHeaders.entrySet()) {
            stringBuilder0.append(Uri.encode(((String)((Map.Entry)object0).getKey())));
            stringBuilder0.append('=');
            stringBuilder0.append(Uri.encode(((String)((Map.Entry)object0).getValue())));
            stringBuilder0.append('&');
        }
        return stringBuilder0.toString();
    }
}

