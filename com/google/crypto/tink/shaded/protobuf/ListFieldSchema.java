package com.google.crypto.tink.shaded.protobuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CheckReturnValue
abstract class ListFieldSchema {
    static final class ListFieldSchemaFull extends ListFieldSchema {
        private static final Class UNMODIFIABLE_LIST_CLASS;

        static {
            ListFieldSchemaFull.UNMODIFIABLE_LIST_CLASS = Collections.unmodifiableList(Collections.EMPTY_LIST).getClass();
        }

        private ListFieldSchemaFull() {
            super(null);
        }

        ListFieldSchemaFull(com.google.crypto.tink.shaded.protobuf.ListFieldSchema.1 listFieldSchema$10) {
        }

        static List getList(Object object0, long v) {
            return (List)UnsafeUtil.getObject(object0, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        void makeImmutableListAt(Object object0, long v) {
            List list1;
            List list0 = (List)UnsafeUtil.getObject(object0, v);
            if(list0 instanceof LazyStringList) {
                list1 = ((LazyStringList)list0).getUnmodifiableView();
                UnsafeUtil.putObject(object0, v, list1);
                return;
            }
            Class class0 = list0.getClass();
            if(!ListFieldSchemaFull.UNMODIFIABLE_LIST_CLASS.isAssignableFrom(class0)) {
                if(!(list0 instanceof PrimitiveNonBoxingCollection) || !(list0 instanceof ProtobufList)) {
                    list1 = Collections.unmodifiableList(list0);
                    UnsafeUtil.putObject(object0, v, list1);
                }
                else if(((ProtobufList)list0).isModifiable()) {
                    ((ProtobufList)list0).makeImmutable();
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        void mergeListsAt(Object object0, Object object1, long v) {
            List list0 = ListFieldSchemaFull.getList(object1, v);
            List list1 = ListFieldSchemaFull.mutableListAt(object0, v, list0.size());
            int v1 = list1.size();
            if(v1 > 0 && list0.size() > 0) {
                list1.addAll(list0);
            }
            if(v1 > 0) {
                list0 = list1;
            }
            UnsafeUtil.putObject(object0, v, list0);
        }

        private static List mutableListAt(Object object0, long v, int v1) {
            List list1;
            List list0 = ListFieldSchemaFull.getList(object0, v);
            if(list0.isEmpty()) {
                if(list0 instanceof LazyStringList) {
                    list1 = new LazyStringArrayList(v1);
                }
                else if(!(list0 instanceof PrimitiveNonBoxingCollection) || !(list0 instanceof ProtobufList)) {
                    list1 = new ArrayList(v1);
                }
                else {
                    list1 = ((ProtobufList)list0).mutableCopyWithCapacity(v1);
                }
                UnsafeUtil.putObject(object0, v, list1);
                return list1;
            }
            Class class0 = list0.getClass();
            if(ListFieldSchemaFull.UNMODIFIABLE_LIST_CLASS.isAssignableFrom(class0)) {
                List list2 = new ArrayList(list0.size() + v1);
                ((ArrayList)list2).addAll(list0);
                UnsafeUtil.putObject(object0, v, list2);
                return list2;
            }
            if(list0 instanceof UnmodifiableLazyStringList) {
                List list3 = new LazyStringArrayList(list0.size() + v1);
                ((LazyStringArrayList)list3).addAll(((UnmodifiableLazyStringList)list0));
                UnsafeUtil.putObject(object0, v, list3);
                return list3;
            }
            if(list0 instanceof PrimitiveNonBoxingCollection && list0 instanceof ProtobufList && !((ProtobufList)list0).isModifiable()) {
                List list4 = ((ProtobufList)list0).mutableCopyWithCapacity(list0.size() + v1);
                UnsafeUtil.putObject(object0, v, list4);
                return list4;
            }
            return list0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        List mutableListAt(Object object0, long v) {
            return ListFieldSchemaFull.mutableListAt(object0, v, 10);
        }
    }

    static final class ListFieldSchemaLite extends ListFieldSchema {
        private ListFieldSchemaLite() {
            super(null);
        }

        ListFieldSchemaLite(com.google.crypto.tink.shaded.protobuf.ListFieldSchema.1 listFieldSchema$10) {
        }

        static ProtobufList getProtobufList(Object object0, long v) {
            return (ProtobufList)UnsafeUtil.getObject(object0, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        void makeImmutableListAt(Object object0, long v) {
            ListFieldSchemaLite.getProtobufList(object0, v).makeImmutable();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        void mergeListsAt(Object object0, Object object1, long v) {
            ProtobufList internal$ProtobufList0 = ListFieldSchemaLite.getProtobufList(object0, v);
            ProtobufList internal$ProtobufList1 = ListFieldSchemaLite.getProtobufList(object1, v);
            int v1 = internal$ProtobufList0.size();
            int v2 = internal$ProtobufList1.size();
            if(v1 > 0 && v2 > 0) {
                if(!internal$ProtobufList0.isModifiable()) {
                    internal$ProtobufList0 = internal$ProtobufList0.mutableCopyWithCapacity(v2 + v1);
                }
                internal$ProtobufList0.addAll(internal$ProtobufList1);
            }
            if(v1 > 0) {
                internal$ProtobufList1 = internal$ProtobufList0;
            }
            UnsafeUtil.putObject(object0, v, internal$ProtobufList1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        List mutableListAt(Object object0, long v) {
            List list0 = ListFieldSchemaLite.getProtobufList(object0, v);
            if(!((ProtobufList)list0).isModifiable()) {
                int v1 = ((ProtobufList)list0).size();
                list0 = ((ProtobufList)list0).mutableCopyWithCapacity((v1 == 0 ? 10 : v1 * 2));
                UnsafeUtil.putObject(object0, v, list0);
            }
            return list0;
        }
    }

    private static final ListFieldSchema FULL_INSTANCE;
    private static final ListFieldSchema LITE_INSTANCE;

    static {
        ListFieldSchema.FULL_INSTANCE = new ListFieldSchemaFull(null);
        ListFieldSchema.LITE_INSTANCE = new ListFieldSchemaLite(null);
    }

    private ListFieldSchema() {
    }

    ListFieldSchema(com.google.crypto.tink.shaded.protobuf.ListFieldSchema.1 listFieldSchema$10) {
    }

    static ListFieldSchema full() {
        return ListFieldSchema.FULL_INSTANCE;
    }

    static ListFieldSchema lite() {
        return ListFieldSchema.LITE_INSTANCE;
    }

    abstract void makeImmutableListAt(Object arg1, long arg2);

    abstract void mergeListsAt(Object arg1, Object arg2, long arg3);

    abstract List mutableListAt(Object arg1, long arg2);

    class com.google.crypto.tink.shaded.protobuf.ListFieldSchema.1 {
    }

}

