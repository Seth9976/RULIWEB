package androidx.activity;

import android.app.Notification.DecoratedCustomViewStyle;
import android.app.Notification.MessagingStyle;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.Person;
import android.app.job.JobWorkItem;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.view.inspector.InspectionCompanion.UninitializedPropertyMapException;
import android.widget.ThemedSpinnerAdapter;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

public final class ComponentDialog..ExternalSyntheticApiModelOutline0 {
    public static Notification.MessagingStyle m(Object object0) [...] // Inlined contents

    public static NotificationChannel m(Object object0) [...] // Inlined contents

    public static NotificationChannel m(String s, CharSequence charSequence0, int v) {
        return new NotificationChannel(s, charSequence0, v);
    }

    public static NotificationChannelGroup m(Object object0) [...] // Inlined contents

    public static Person m(Object object0) [...] // Inlined contents

    public static JobWorkItem m(Intent intent0) {
        return new JobWorkItem(intent0);
    }

    public static Icon m(Object object0) [...] // Inlined contents

    public static InspectionCompanion.UninitializedPropertyMapException m() {
        return new InspectionCompanion.UninitializedPropertyMapException();
    }

    public static ThemedSpinnerAdapter m(Object object0) [...] // Inlined contents

    public static OnBackInvokedCallback m(Object object0) [...] // Inlined contents

    public static OnBackInvokedDispatcher m(Object object0) [...] // Inlined contents

    public static Class m() {
        return Notification.DecoratedCustomViewStyle.class;
    }

    public static boolean m(Object object0) {
        return object0 instanceof ThemedSpinnerAdapter;
    }

    public static Class m$1() {
        return Notification.MessagingStyle.class;
    }

    public static boolean m$1(Object object0) {
        return object0 instanceof Icon;
    }
}

