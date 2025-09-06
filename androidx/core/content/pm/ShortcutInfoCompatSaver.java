package androidx.core.content.pm;

import java.util.ArrayList;
import java.util.List;

public abstract class ShortcutInfoCompatSaver {
    public static class NoopImpl extends ShortcutInfoCompatSaver {
        @Override  // androidx.core.content.pm.ShortcutInfoCompatSaver
        public Object addShortcuts(List list0) {
            return null;
        }

        public Void addShortcuts(List list0) [...] // Inlined contents

        @Override  // androidx.core.content.pm.ShortcutInfoCompatSaver
        public Object removeAllShortcuts() {
            return null;
        }

        public Void removeAllShortcuts() [...] // Inlined contents

        @Override  // androidx.core.content.pm.ShortcutInfoCompatSaver
        public Object removeShortcuts(List list0) {
            return null;
        }

        public Void removeShortcuts(List list0) [...] // Inlined contents
    }

    public abstract Object addShortcuts(List arg1);

    public List getShortcuts() throws Exception {
        return new ArrayList();
    }

    public abstract Object removeAllShortcuts();

    public abstract Object removeShortcuts(List arg1);
}

