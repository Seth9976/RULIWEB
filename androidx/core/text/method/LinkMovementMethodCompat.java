package androidx.core.text.method;

import android.os.Build.VERSION;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.Touch;
import android.view.MotionEvent;
import android.widget.TextView;

public class LinkMovementMethodCompat extends LinkMovementMethod {
    private static LinkMovementMethodCompat sInstance;

    public static LinkMovementMethodCompat getInstance() {
        if(LinkMovementMethodCompat.sInstance == null) {
            LinkMovementMethodCompat.sInstance = new LinkMovementMethodCompat();
        }
        return LinkMovementMethodCompat.sInstance;
    }

    @Override  // android.text.method.LinkMovementMethod
    public boolean onTouchEvent(TextView textView0, Spannable spannable0, MotionEvent motionEvent0) {
        if(Build.VERSION.SDK_INT < 35) {
            switch(motionEvent0.getAction()) {
                case 0: 
                case 1: {
                    int v = (int)motionEvent0.getX();
                    int v1 = (int)motionEvent0.getY();
                    int v2 = textView0.getTotalPaddingLeft();
                    int v3 = textView0.getTotalPaddingTop();
                    int v4 = textView0.getScrollX();
                    int v5 = v1 - v3 + textView0.getScrollY();
                    Layout layout0 = textView0.getLayout();
                    if(v5 < 0 || v5 > layout0.getHeight()) {
                        Selection.removeSelection(spannable0);
                        return Touch.onTouchEvent(textView0, spannable0, motionEvent0);
                    }
                    int v6 = layout0.getLineForVertical(v5);
                    float f = (float)(v - v2 + v4);
                    if(f < layout0.getLineLeft(v6) || f > layout0.getLineRight(v6)) {
                        Selection.removeSelection(spannable0);
                        return Touch.onTouchEvent(textView0, spannable0, motionEvent0);
                    }
                    break;
                }
                default: {
                    return super.onTouchEvent(textView0, spannable0, motionEvent0);
                }
            }
        }
        return super.onTouchEvent(textView0, spannable0, motionEvent0);
    }
}

