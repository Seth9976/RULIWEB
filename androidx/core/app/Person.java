package androidx.core.app;

import android.app.Person.Builder;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.core.graphics.drawable.IconCompat;
import java.util.Objects;

public class Person {
    static class Api22Impl {
        static Person fromPersistableBundle(PersistableBundle persistableBundle0) {
            return new Builder().setName(persistableBundle0.getString("name")).setUri(persistableBundle0.getString("uri")).setKey(persistableBundle0.getString("key")).setBot(persistableBundle0.getBoolean("isBot")).setImportant(persistableBundle0.getBoolean("isImportant")).build();
        }

        static PersistableBundle toPersistableBundle(Person person0) {
            PersistableBundle persistableBundle0 = new PersistableBundle();
            persistableBundle0.putString("name", (person0.mName == null ? null : person0.mName.toString()));
            persistableBundle0.putString("uri", person0.mUri);
            persistableBundle0.putString("key", person0.mKey);
            persistableBundle0.putBoolean("isBot", person0.mIsBot);
            persistableBundle0.putBoolean("isImportant", person0.mIsImportant);
            return persistableBundle0;
        }
    }

    static class Api28Impl {
        static Person fromAndroidPerson(android.app.Person person0) {
            Builder person$Builder0 = new Builder().setName(person0.getName());
            return person0.getIcon() == null ? person$Builder0.setIcon(null).setUri(person0.getUri()).setKey(person0.getKey()).setBot(person0.isBot()).setImportant(person0.isImportant()).build() : person$Builder0.setIcon(IconCompat.createFromIcon(person0.getIcon())).setUri(person0.getUri()).setKey(person0.getKey()).setBot(person0.isBot()).setImportant(person0.isImportant()).build();
        }

        static android.app.Person toAndroidPerson(Person person0) {
            Person.Builder person$Builder0 = new Person.Builder().setName(person0.getName());
            return person0.getIcon() == null ? person$Builder0.setIcon(null).setUri(person0.getUri()).setKey(person0.getKey()).setBot(person0.isBot()).setImportant(person0.isImportant()).build() : person$Builder0.setIcon(person0.getIcon().toIcon()).setUri(person0.getUri()).setKey(person0.getKey()).setBot(person0.isBot()).setImportant(person0.isImportant()).build();
        }
    }

    public static class Builder {
        IconCompat mIcon;
        boolean mIsBot;
        boolean mIsImportant;
        String mKey;
        CharSequence mName;
        String mUri;

        public Builder() {
        }

        Builder(Person person0) {
            this.mName = person0.mName;
            this.mIcon = person0.mIcon;
            this.mUri = person0.mUri;
            this.mKey = person0.mKey;
            this.mIsBot = person0.mIsBot;
            this.mIsImportant = person0.mIsImportant;
        }

        public Person build() {
            return new Person(this);
        }

        public Builder setBot(boolean z) {
            this.mIsBot = z;
            return this;
        }

        public Builder setIcon(IconCompat iconCompat0) {
            this.mIcon = iconCompat0;
            return this;
        }

        public Builder setImportant(boolean z) {
            this.mIsImportant = z;
            return this;
        }

        public Builder setKey(String s) {
            this.mKey = s;
            return this;
        }

        public Builder setName(CharSequence charSequence0) {
            this.mName = charSequence0;
            return this;
        }

        public Builder setUri(String s) {
            this.mUri = s;
            return this;
        }
    }

    private static final String ICON_KEY = "icon";
    private static final String IS_BOT_KEY = "isBot";
    private static final String IS_IMPORTANT_KEY = "isImportant";
    private static final String KEY_KEY = "key";
    private static final String NAME_KEY = "name";
    private static final String URI_KEY = "uri";
    IconCompat mIcon;
    boolean mIsBot;
    boolean mIsImportant;
    String mKey;
    CharSequence mName;
    String mUri;

    Person(Builder person$Builder0) {
        this.mName = person$Builder0.mName;
        this.mIcon = person$Builder0.mIcon;
        this.mUri = person$Builder0.mUri;
        this.mKey = person$Builder0.mKey;
        this.mIsBot = person$Builder0.mIsBot;
        this.mIsImportant = person$Builder0.mIsImportant;
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof Person)) {
            return false;
        }
        String s = this.getKey();
        String s1 = ((Person)object0).getKey();
        return s != null || s1 != null ? Objects.equals(s, s1) : Objects.equals(Objects.toString(this.getName()), Objects.toString(((Person)object0).getName())) && Objects.equals(this.getUri(), ((Person)object0).getUri()) && Objects.equals(Boolean.valueOf(this.isBot()), Boolean.valueOf(((Person)object0).isBot())) && Objects.equals(Boolean.valueOf(this.isImportant()), Boolean.valueOf(((Person)object0).isImportant()));
    }

    public static Person fromAndroidPerson(android.app.Person person0) {
        return Api28Impl.fromAndroidPerson(person0);
    }

    public static Person fromBundle(Bundle bundle0) {
        Bundle bundle1 = bundle0.getBundle("icon");
        Builder person$Builder0 = new Builder().setName(bundle0.getCharSequence("name"));
        return bundle1 == null ? person$Builder0.setIcon(null).setUri(bundle0.getString("uri")).setKey(bundle0.getString("key")).setBot(bundle0.getBoolean("isBot")).setImportant(bundle0.getBoolean("isImportant")).build() : person$Builder0.setIcon(IconCompat.createFromBundle(bundle1)).setUri(bundle0.getString("uri")).setKey(bundle0.getString("key")).setBot(bundle0.getBoolean("isBot")).setImportant(bundle0.getBoolean("isImportant")).build();
    }

    public static Person fromPersistableBundle(PersistableBundle persistableBundle0) {
        return Api22Impl.fromPersistableBundle(persistableBundle0);
    }

    public IconCompat getIcon() {
        return this.mIcon;
    }

    public String getKey() {
        return this.mKey;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public String getUri() {
        return this.mUri;
    }

    @Override
    public int hashCode() {
        String s = this.getKey();
        return s == null ? Objects.hash(new Object[]{this.getName(), this.getUri(), Boolean.valueOf(this.isBot()), Boolean.valueOf(this.isImportant())}) : s.hashCode();
    }

    public boolean isBot() {
        return this.mIsBot;
    }

    public boolean isImportant() {
        return this.mIsImportant;
    }

    public String resolveToLegacyUri() {
        String s = this.mUri;
        if(s != null) {
            return s;
        }
        return this.mName == null ? "" : "name:" + this.mName;
    }

    public android.app.Person toAndroidPerson() {
        return Api28Impl.toAndroidPerson(this);
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public Bundle toBundle() {
        Bundle bundle0 = new Bundle();
        bundle0.putCharSequence("name", this.mName);
        bundle0.putBundle("icon", (this.mIcon == null ? null : this.mIcon.toBundle()));
        bundle0.putString("uri", this.mUri);
        bundle0.putString("key", this.mKey);
        bundle0.putBoolean("isBot", this.mIsBot);
        bundle0.putBoolean("isImportant", this.mIsImportant);
        return bundle0;
    }

    public PersistableBundle toPersistableBundle() {
        return Api22Impl.toPersistableBundle(this);
    }
}

