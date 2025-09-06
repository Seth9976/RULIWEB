package androidx.core.content;

import android.content.ClipData.Item;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import androidx.core.util.Predicate;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class IntentSanitizer {
    static class Api29Impl {
        static String getIdentifier(Intent intent0) {
            return intent0.getIdentifier();
        }

        static Intent setIdentifier(Intent intent0, String s) {
            return intent0.setIdentifier(s);
        }
    }

    static class Api31Impl {
        static void checkOtherMembers(int v, ClipData.Item clipData$Item0, Consumer consumer0) {
            if(clipData$Item0.getHtmlText() == null && clipData$Item0.getIntent() == null && clipData$Item0.getTextLinks() == null) {
                return;
            }
            consumer0.accept("ClipData item at position " + v + " contains htmlText, textLinks or intent: " + clipData$Item0);
        }
    }

    public static final class Builder {
        private static final int HISTORY_STACK_FLAGS = 0x7DEBF000;
        private static final int RECEIVER_FLAGS = 0x78200000;
        private boolean mAllowAnyComponent;
        private boolean mAllowClipDataText;
        private boolean mAllowIdentifier;
        private boolean mAllowSelector;
        private boolean mAllowSomeComponents;
        private boolean mAllowSourceBounds;
        private Predicate mAllowedActions;
        private Predicate mAllowedCategories;
        private Predicate mAllowedClipData;
        private Predicate mAllowedClipDataUri;
        private Predicate mAllowedComponents;
        private Predicate mAllowedData;
        private Map mAllowedExtras;
        private int mAllowedFlags;
        private Predicate mAllowedPackages;
        private Predicate mAllowedTypes;

        public Builder() {
            this.mAllowedActions = new IntentSanitizer.Builder..ExternalSyntheticLambda10();
            this.mAllowedData = new IntentSanitizer.Builder..ExternalSyntheticLambda11();
            this.mAllowedTypes = new IntentSanitizer.Builder..ExternalSyntheticLambda12();
            this.mAllowedCategories = new IntentSanitizer.Builder..ExternalSyntheticLambda13();
            this.mAllowedPackages = new IntentSanitizer.Builder..ExternalSyntheticLambda14();
            this.mAllowedComponents = new IntentSanitizer.Builder..ExternalSyntheticLambda15();
            this.mAllowedExtras = new HashMap();
            this.mAllowClipDataText = false;
            this.mAllowedClipDataUri = new IntentSanitizer.Builder..ExternalSyntheticLambda16();
            this.mAllowedClipData = new IntentSanitizer.Builder..ExternalSyntheticLambda17();
        }

        public Builder allowAction(Predicate predicate0) {
            Preconditions.checkNotNull(predicate0);
            this.mAllowedActions = this.mAllowedActions.or(predicate0);
            return this;
        }

        public Builder allowAction(String s) {
            Preconditions.checkNotNull(s);
            Objects.requireNonNull(s);
            this.allowAction(new IntentSanitizer.Builder..ExternalSyntheticLambda1(s));
            return this;
        }

        public Builder allowAnyComponent() {
            this.mAllowAnyComponent = true;
            this.mAllowedComponents = new IntentSanitizer.Builder..ExternalSyntheticLambda6();
            return this;
        }

        public Builder allowCategory(Predicate predicate0) {
            Preconditions.checkNotNull(predicate0);
            this.mAllowedCategories = this.mAllowedCategories.or(predicate0);
            return this;
        }

        public Builder allowCategory(String s) {
            Preconditions.checkNotNull(s);
            Objects.requireNonNull(s);
            return this.allowCategory(new IntentSanitizer.Builder..ExternalSyntheticLambda1(s));
        }

        public Builder allowClipData(Predicate predicate0) {
            Preconditions.checkNotNull(predicate0);
            this.mAllowedClipData = this.mAllowedClipData.or(predicate0);
            return this;
        }

        public Builder allowClipDataText() {
            this.mAllowClipDataText = true;
            return this;
        }

        public Builder allowClipDataUri(Predicate predicate0) {
            Preconditions.checkNotNull(predicate0);
            this.mAllowedClipDataUri = this.mAllowedClipDataUri.or(predicate0);
            return this;
        }

        public Builder allowClipDataUriWithAuthority(String s) {
            Preconditions.checkNotNull(s);
            return this.allowClipDataUri(new IntentSanitizer.Builder..ExternalSyntheticLambda7(s));
        }

        public Builder allowComponent(ComponentName componentName0) {
            Preconditions.checkNotNull(componentName0);
            Objects.requireNonNull(componentName0);
            return this.allowComponent(new IntentSanitizer.Builder..ExternalSyntheticLambda5(componentName0));
        }

        public Builder allowComponent(Predicate predicate0) {
            Preconditions.checkNotNull(predicate0);
            this.mAllowSomeComponents = true;
            this.mAllowedComponents = this.mAllowedComponents.or(predicate0);
            return this;
        }

        public Builder allowComponentWithPackage(String s) {
            Preconditions.checkNotNull(s);
            return this.allowComponent(new IntentSanitizer.Builder..ExternalSyntheticLambda2(s));
        }

        public Builder allowData(Predicate predicate0) {
            Preconditions.checkNotNull(predicate0);
            this.mAllowedData = this.mAllowedData.or(predicate0);
            return this;
        }

        public Builder allowDataWithAuthority(String s) {
            Preconditions.checkNotNull(s);
            this.allowData(new IntentSanitizer.Builder..ExternalSyntheticLambda3(s));
            return this;
        }

        public Builder allowExtra(String s, Predicate predicate0) {
            Preconditions.checkNotNull(s);
            Preconditions.checkNotNull(predicate0);
            Predicate predicate1 = (Predicate)this.mAllowedExtras.get(s);
            if(predicate1 == null) {
                predicate1 = new IntentSanitizer.Builder..ExternalSyntheticLambda8();
            }
            Predicate predicate2 = predicate1.or(predicate0);
            this.mAllowedExtras.put(s, predicate2);
            return this;
        }

        public Builder allowExtra(String s, Class class0) {
            return this.allowExtra(s, class0, new IntentSanitizer.Builder..ExternalSyntheticLambda18());
        }

        public Builder allowExtra(String s, Class class0, Predicate predicate0) {
            Preconditions.checkNotNull(s);
            Preconditions.checkNotNull(class0);
            Preconditions.checkNotNull(predicate0);
            return this.allowExtra(s, new IntentSanitizer.Builder..ExternalSyntheticLambda4(class0, predicate0));
        }

        public Builder allowExtraOutput(Predicate predicate0) {
            this.allowExtra("output", Uri.class, predicate0);
            return this;
        }

        public Builder allowExtraOutput(String s) {
            IntentSanitizer.Builder..ExternalSyntheticLambda0 intentSanitizer$Builder$$ExternalSyntheticLambda00 = new IntentSanitizer.Builder..ExternalSyntheticLambda0(s);
            this.allowExtra("output", Uri.class, intentSanitizer$Builder$$ExternalSyntheticLambda00);
            return this;
        }

        public Builder allowExtraStream(Predicate predicate0) {
            this.allowExtra("android.intent.extra.STREAM", Uri.class, predicate0);
            return this;
        }

        public Builder allowExtraStreamUriWithAuthority(String s) {
            Preconditions.checkNotNull(s);
            IntentSanitizer.Builder..ExternalSyntheticLambda9 intentSanitizer$Builder$$ExternalSyntheticLambda90 = new IntentSanitizer.Builder..ExternalSyntheticLambda9(s);
            this.allowExtra("android.intent.extra.STREAM", Uri.class, intentSanitizer$Builder$$ExternalSyntheticLambda90);
            return this;
        }

        public Builder allowFlags(int v) {
            this.mAllowedFlags |= v;
            return this;
        }

        public Builder allowHistoryStackFlags() {
            this.mAllowedFlags |= 0x7DEBF000;
            return this;
        }

        public Builder allowIdentifier() {
            this.mAllowIdentifier = true;
            return this;
        }

        public Builder allowPackage(Predicate predicate0) {
            Preconditions.checkNotNull(predicate0);
            this.mAllowedPackages = this.mAllowedPackages.or(predicate0);
            return this;
        }

        public Builder allowPackage(String s) {
            Preconditions.checkNotNull(s);
            Objects.requireNonNull(s);
            return this.allowPackage(new IntentSanitizer.Builder..ExternalSyntheticLambda1(s));
        }

        public Builder allowReceiverFlags() {
            this.mAllowedFlags |= 0x78200000;
            return this;
        }

        public Builder allowSelector() {
            this.mAllowSelector = true;
            return this;
        }

        public Builder allowSourceBounds() {
            this.mAllowSourceBounds = true;
            return this;
        }

        public Builder allowType(Predicate predicate0) {
            Preconditions.checkNotNull(predicate0);
            this.mAllowedTypes = this.mAllowedTypes.or(predicate0);
            return this;
        }

        public Builder allowType(String s) {
            Preconditions.checkNotNull(s);
            Objects.requireNonNull(s);
            return this.allowType(new IntentSanitizer.Builder..ExternalSyntheticLambda1(s));
        }

        public IntentSanitizer build() {
            if(this.mAllowAnyComponent && this.mAllowSomeComponents || !this.mAllowAnyComponent && !this.mAllowSomeComponents) {
                throw new SecurityException("You must call either allowAnyComponent or one or more of the allowComponent methods; but not both.");
            }
            IntentSanitizer intentSanitizer0 = new IntentSanitizer(null);
            intentSanitizer0.mAllowedFlags = this.mAllowedFlags;
            intentSanitizer0.mAllowedActions = this.mAllowedActions;
            intentSanitizer0.mAllowedData = this.mAllowedData;
            intentSanitizer0.mAllowedTypes = this.mAllowedTypes;
            intentSanitizer0.mAllowedCategories = this.mAllowedCategories;
            intentSanitizer0.mAllowedPackages = this.mAllowedPackages;
            intentSanitizer0.mAllowAnyComponent = this.mAllowAnyComponent;
            intentSanitizer0.mAllowedComponents = this.mAllowedComponents;
            intentSanitizer0.mAllowedExtras = this.mAllowedExtras;
            intentSanitizer0.mAllowClipDataText = this.mAllowClipDataText;
            intentSanitizer0.mAllowedClipDataUri = this.mAllowedClipDataUri;
            intentSanitizer0.mAllowedClipData = this.mAllowedClipData;
            intentSanitizer0.mAllowIdentifier = this.mAllowIdentifier;
            intentSanitizer0.mAllowSelector = this.mAllowSelector;
            intentSanitizer0.mAllowSourceBounds = this.mAllowSourceBounds;
            return intentSanitizer0;
        }

        static boolean lambda$allowAnyComponent$10(ComponentName componentName0) {
            return true;
        }

        static boolean lambda$allowClipDataUriWithAuthority$11(String s, Uri uri0) {
            return s.equals(uri0.getAuthority());
        }

        static boolean lambda$allowComponentWithPackage$9(String s, ComponentName componentName0) {
            return s.equals(componentName0.getPackageName());
        }

        static boolean lambda$allowDataWithAuthority$8(String s, Uri uri0) {
            return s.equals(uri0.getAuthority());
        }

        static boolean lambda$allowExtra$12(Object object0) [...] // Inlined contents

        // 去混淆评级： 低(20)
        static boolean lambda$allowExtra$13(Class class0, Predicate predicate0, Object object0) {
            return class0.isInstance(object0) && predicate0.test(class0.cast(object0));
        }

        static boolean lambda$allowExtra$14(Object object0) [...] // Inlined contents

        static boolean lambda$allowExtraOutput$16(String s, Uri uri0) {
            return s.equals(uri0.getAuthority());
        }

        static boolean lambda$allowExtraStreamUriWithAuthority$15(String s, Uri uri0) {
            return s.equals(uri0.getAuthority());
        }

        static boolean lambda$new$0(String s) {
            return false;
        }

        static boolean lambda$new$1(Uri uri0) {
            return false;
        }

        static boolean lambda$new$2(String s) {
            return false;
        }

        static boolean lambda$new$3(String s) {
            return false;
        }

        static boolean lambda$new$4(String s) {
            return false;
        }

        static boolean lambda$new$5(ComponentName componentName0) {
            return false;
        }

        static boolean lambda$new$6(Uri uri0) {
            return false;
        }

        static boolean lambda$new$7(ClipData clipData0) {
            return false;
        }
    }

    private static final String TAG = "IntentSanitizer";
    private boolean mAllowAnyComponent;
    private boolean mAllowClipDataText;
    private boolean mAllowIdentifier;
    private boolean mAllowSelector;
    private boolean mAllowSourceBounds;
    private Predicate mAllowedActions;
    private Predicate mAllowedCategories;
    private Predicate mAllowedClipData;
    private Predicate mAllowedClipDataUri;
    private Predicate mAllowedComponents;
    private Predicate mAllowedData;
    private Map mAllowedExtras;
    private int mAllowedFlags;
    private Predicate mAllowedPackages;
    private Predicate mAllowedTypes;

    private IntentSanitizer() {
    }

    IntentSanitizer(androidx.core.content.IntentSanitizer.1 intentSanitizer$10) {
    }

    private static void checkOtherMembers(int v, ClipData.Item clipData$Item0, Consumer consumer0) {
        if(clipData$Item0.getHtmlText() == null && clipData$Item0.getIntent() == null) {
            return;
        }
        consumer0.accept("ClipData item at position " + v + " contains htmlText, textLinks or intent: " + clipData$Item0);
    }

    static void lambda$sanitizeByFiltering$0(String s) {
    }

    // 检测为 Lambda 实现
    static void lambda$sanitizeByThrowing$1(String s) [...]

    private void putExtra(Intent intent0, String s, Object object0) {
        if(object0 == null) {
            intent0.getExtras().putString(s, null);
            return;
        }
        if(object0 instanceof Parcelable) {
            intent0.putExtra(s, ((Parcelable)object0));
            return;
        }
        if(object0 instanceof Parcelable[]) {
            intent0.putExtra(s, ((Parcelable[])object0));
            return;
        }
        if(!(object0 instanceof Serializable)) {
            throw new IllegalArgumentException("Unsupported type " + object0.getClass());
        }
        intent0.putExtra(s, ((Serializable)object0));
    }

    public Intent sanitize(Intent intent0, Consumer consumer0) {
        Intent intent1 = new Intent();
        ComponentName componentName0 = intent0.getComponent();
        if((!this.mAllowAnyComponent || componentName0 != null) && !this.mAllowedComponents.test(componentName0)) {
            consumer0.accept("Component is not allowed: " + componentName0);
            intent1.setComponent(new ComponentName("android", "java.lang.Void"));
        }
        else {
            intent1.setComponent(componentName0);
        }
        String s = intent0.getPackage();
        if(s == null || this.mAllowedPackages.test(s)) {
            intent1.setPackage(s);
        }
        else {
            consumer0.accept("Package is not allowed: " + s);
        }
        int v = this.mAllowedFlags;
        int v1 = intent0.getFlags();
        int v2 = this.mAllowedFlags;
        if((v | v1) == v2) {
            intent1.setFlags(intent0.getFlags());
        }
        else {
            intent1.setFlags(intent0.getFlags() & v2);
            consumer0.accept("The intent contains flags that are not allowed: 0x" + Integer.toHexString(intent0.getFlags() & ~this.mAllowedFlags));
        }
        String s1 = intent0.getAction();
        if(s1 == null || this.mAllowedActions.test(s1)) {
            intent1.setAction(s1);
        }
        else {
            consumer0.accept("Action is not allowed: " + s1);
        }
        Uri uri0 = intent0.getData();
        if(uri0 == null || this.mAllowedData.test(uri0)) {
            intent1.setData(uri0);
        }
        else {
            consumer0.accept("Data is not allowed: " + uri0);
        }
        String s2 = intent0.getType();
        if(s2 == null || this.mAllowedTypes.test(s2)) {
            intent1.setDataAndType(intent1.getData(), s2);
        }
        else {
            consumer0.accept("Type is not allowed: " + s2);
        }
        Set set0 = intent0.getCategories();
        if(set0 != null) {
            for(Object object0: set0) {
                String s3 = (String)object0;
                if(this.mAllowedCategories.test(s3)) {
                    intent1.addCategory(s3);
                }
                else {
                    consumer0.accept("Category is not allowed: " + s3);
                }
            }
        }
        Bundle bundle0 = intent0.getExtras();
        if(bundle0 != null) {
            for(Object object1: bundle0.keySet()) {
                String s4 = (String)object1;
                if(s4.equals("android.intent.extra.STREAM") && (this.mAllowedFlags & 1) == 0) {
                    consumer0.accept("Allowing Extra Stream requires also allowing at least  FLAG_GRANT_READ_URI_PERMISSION Flag.");
                }
                else if(s4.equals("output") && (~this.mAllowedFlags & 3) != 0) {
                    consumer0.accept("Allowing Extra Output requires also allowing FLAG_GRANT_READ_URI_PERMISSION and FLAG_GRANT_WRITE_URI_PERMISSION Flags.");
                }
                else {
                    Object object2 = bundle0.get(s4);
                    Predicate predicate0 = (Predicate)this.mAllowedExtras.get(s4);
                    if(predicate0 != null && predicate0.test(object2)) {
                        this.putExtra(intent1, s4, object2);
                    }
                    else {
                        consumer0.accept("Extra is not allowed. Key: " + s4 + ". Value: " + object2);
                    }
                }
            }
        }
        IntentSanitizer.sanitizeClipData(intent0, intent1, this.mAllowedClipData, this.mAllowClipDataText, this.mAllowedClipDataUri, consumer0);
        if(Build.VERSION.SDK_INT >= 29) {
            if(this.mAllowIdentifier) {
                Api29Impl.setIdentifier(intent1, Api29Impl.getIdentifier(intent0));
            }
            else if(Api29Impl.getIdentifier(intent0) != null) {
                consumer0.accept("Identifier is not allowed: " + Api29Impl.getIdentifier(intent0));
            }
        }
        if(this.mAllowSelector) {
            intent1.setSelector(intent0.getSelector());
        }
        else if(intent0.getSelector() != null) {
            consumer0.accept("Selector is not allowed: " + intent0.getSelector());
        }
        if(this.mAllowSourceBounds) {
            intent1.setSourceBounds(intent0.getSourceBounds());
            return intent1;
        }
        if(intent0.getSourceBounds() != null) {
            consumer0.accept("SourceBounds is not allowed: " + intent0.getSourceBounds());
        }
        return intent1;
    }

    public Intent sanitizeByFiltering(Intent intent0) {
        return this.sanitize(intent0, new IntentSanitizer..ExternalSyntheticLambda1());
    }

    public Intent sanitizeByThrowing(Intent intent0) {
        return this.sanitize(intent0, (String s) -> throw new SecurityException(s));
    }

    static void sanitizeClipData(Intent intent0, Intent intent1, Predicate predicate0, boolean z, Predicate predicate1, Consumer consumer0) {
        Uri uri0;
        CharSequence charSequence0;
        ClipData clipData0 = intent0.getClipData();
        if(clipData0 != null) {
            if(predicate0 != null && predicate0.test(clipData0)) {
                intent1.setClipData(clipData0);
                return;
            }
            ClipData clipData1 = null;
            for(int v = 0; v < clipData0.getItemCount(); ++v) {
                ClipData.Item clipData$Item0 = clipData0.getItemAt(v);
                if(Build.VERSION.SDK_INT >= 0x1F) {
                    Api31Impl.checkOtherMembers(v, clipData$Item0, consumer0);
                }
                else {
                    IntentSanitizer.checkOtherMembers(v, clipData$Item0, consumer0);
                }
                if(z) {
                    charSequence0 = clipData$Item0.getText();
                }
                else {
                    if(clipData$Item0.getText() != null) {
                        consumer0.accept("Item text cannot contain value. Item position: " + v + ". Text: " + clipData$Item0.getText());
                    }
                    charSequence0 = null;
                }
                if(predicate1 == null) {
                    if(clipData$Item0.getUri() != null) {
                        consumer0.accept("Item URI is not allowed. Item position: " + v + ". URI: " + clipData$Item0.getUri());
                    }
                    uri0 = null;
                }
                else if(clipData$Item0.getUri() == null || predicate1.test(clipData$Item0.getUri())) {
                    uri0 = clipData$Item0.getUri();
                }
                else {
                    consumer0.accept("Item URI is not allowed. Item position: " + v + ". URI: " + clipData$Item0.getUri());
                    uri0 = null;
                }
                if(charSequence0 != null || uri0 != null) {
                    if(clipData1 == null) {
                        clipData1 = new ClipData(clipData0.getDescription(), new ClipData.Item(charSequence0, null, uri0));
                    }
                    else {
                        clipData1.addItem(new ClipData.Item(charSequence0, null, uri0));
                    }
                }
            }
            if(clipData1 != null) {
                intent1.setClipData(clipData1);
            }
        }
    }

    class androidx.core.content.IntentSanitizer.1 {
    }

}

