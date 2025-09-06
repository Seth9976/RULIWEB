package kotlin.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010(\n\u0002\u0010)\n\u0002\u0010\'\n\u0002\b\n\n\u0002\u0010\u001C\n\u0002\u0018\u0002\n\u0002\b\u0017\u001A`\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001A\u00020\u00052%\b\u0001\u0010\u0006\u001A\u001F\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020\t0\u0007\u00A2\u0006\u0002\b\nH\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001AX\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032%\b\u0001\u0010\u0006\u001A\u001F\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020\t0\u0007\u00A2\u0006\u0002\b\nH\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001A\u001E\u0010\u000B\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\u001A1\u0010\f\u001A\u001E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\rj\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u000E\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\b\u001A_\u0010\f\u001A\u001E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\rj\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u000E\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u000F\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u00A2\u0006\u0002\u0010\u0012\u001A1\u0010\u0013\u001A\u001E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0014j\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\b\u001A_\u0010\u0013\u001A\u001E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0014j\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u000F\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u00A2\u0006\u0002\u0010\u0016\u001A!\u0010\u0017\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\b\u001AO\u0010\u0017\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u000F\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u00A2\u0006\u0002\u0010\u0018\u001A!\u0010\u0019\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\b\u001AO\u0010\u0019\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u000F\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\"\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\u00A2\u0006\u0002\u0010\u0018\u001A*\u0010\u001A\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001BH\u0087\n\u00A2\u0006\u0002\u0010\u001C\u001A*\u0010\u001D\u001A\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001BH\u0087\n\u00A2\u0006\u0002\u0010\u001C\u001A9\u0010\u001E\u001A\u00020\u001F\"\t\b\u0000\u0010\u0002\u00A2\u0006\u0002\b \"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001A\u0002H\u0002H\u0087\n\u00A2\u0006\u0002\u0010\"\u001A1\u0010#\u001A\u00020\u001F\"\t\b\u0000\u0010\u0002\u00A2\u0006\u0002\b *\u000E\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0002\b\u00030\u00012\u0006\u0010!\u001A\u0002H\u0002H\u0087\b\u00A2\u0006\u0002\u0010\"\u001A7\u0010$\u001A\u00020\u001F\"\u0004\b\u0000\u0010\u0002\"\t\b\u0001\u0010\u0003\u00A2\u0006\u0002\b *\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010%\u001A\u0002H\u0003H\u0087\b\u00A2\u0006\u0002\u0010\"\u001AV\u0010&\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001B\u0012\u0004\u0012\u00020\u001F0\u0007H\u0086\b\u00F8\u0001\u0000\u001AJ\u0010(\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010\'\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u001F0\u0007H\u0086\b\u00F8\u0001\u0000\u001AV\u0010)\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001B\u0012\u0004\u0012\u00020\u001F0\u0007H\u0086\b\u00F8\u0001\u0000\u001Aq\u0010*\u001A\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001A\u0002H+2\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001B\u0012\u0004\u0012\u00020\u001F0\u0007H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010-\u001Aq\u0010.\u001A\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001A\u0002H+2\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001B\u0012\u0004\u0012\u00020\u001F0\u0007H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010-\u001AJ\u0010/\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010\'\u001A\u000E\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u001F0\u0007H\u0086\b\u00F8\u0001\u0000\u001A;\u00100\u001A\u0004\u0018\u0001H\u0003\"\t\b\u0000\u0010\u0002\u00A2\u0006\u0002\b \"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001A\u0002H\u0002H\u0087\n\u00A2\u0006\u0002\u00101\u001AC\u00102\u001A\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001A\u0002H\u00022\f\u00103\u001A\b\u0012\u0004\u0012\u0002H\u000304H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u00105\u001AC\u00106\u001A\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001A\u0002H\u00022\f\u00103\u001A\b\u0012\u0004\u0012\u0002H\u000304H\u0080\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u00105\u001AC\u00107\u001A\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010!\u001A\u0002H\u00022\f\u00103\u001A\b\u0012\u0004\u0012\u0002H\u000304H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u00105\u001A1\u00108\u001A\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001A\u0002H\u0002H\u0007\u00A2\u0006\u0002\u00101\u001A?\u00109\u001A\u0002H:\"\u0014\b\u0000\u0010+*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0001*\u0002H:\"\u0004\b\u0001\u0010:*\u0002H+2\f\u00103\u001A\b\u0012\u0004\u0012\u0002H:04H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010;\u001A\'\u0010<\u001A\u00020\u001F\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\b\u001A:\u0010=\u001A\u00020\u001F\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001H\u0087\b\u0082\u0002\u000E\n\f\b\u0000\u0012\u0002\u0018\u0001\u001A\u0004\b\u0003\u0010\u0000\u001A9\u0010>\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001B0?\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\n\u001A<\u0010>\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030A0@\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\bH\u0087\n\u00A2\u0006\u0002\bB\u001A\\\u0010C\u001A\u000E\u0012\u0004\u0012\u0002H:\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010:*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001E\u0010D\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001B\u0012\u0004\u0012\u0002H:0\u0007H\u0086\b\u00F8\u0001\u0000\u001Aw\u0010E\u001A\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010:\"\u0018\b\u0003\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H:\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001A\u0002H+2\u001E\u0010D\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001B\u0012\u0004\u0012\u0002H:0\u0007H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010-\u001A\\\u0010F\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H:0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010:*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001E\u0010D\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001B\u0012\u0004\u0012\u0002H:0\u0007H\u0086\b\u00F8\u0001\u0000\u001Aw\u0010G\u001A\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010:\"\u0018\b\u0003\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H:0\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001A\u0002H+2\u001E\u0010D\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001B\u0012\u0004\u0012\u0002H:0\u0007H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010-\u001A@\u0010H\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010!\u001A\u0002H\u0002H\u0087\u0002\u00A2\u0006\u0002\u0010I\u001AH\u0010H\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u000E\u0010J\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0010H\u0087\u0002\u00A2\u0006\u0002\u0010K\u001AA\u0010H\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010J\u001A\b\u0012\u0004\u0012\u0002H\u00020LH\u0087\u0002\u001AA\u0010H\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010J\u001A\b\u0012\u0004\u0012\u0002H\u00020MH\u0087\u0002\u001A2\u0010N\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010!\u001A\u0002H\u0002H\u0087\n\u00A2\u0006\u0002\u0010O\u001A:\u0010N\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u000E\u0010J\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0010H\u0087\n\u00A2\u0006\u0002\u0010P\u001A3\u0010N\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\f\u0010J\u001A\b\u0012\u0004\u0012\u0002H\u00020LH\u0087\n\u001A3\u0010N\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\f\u0010J\u001A\b\u0012\u0004\u0012\u0002H\u00020MH\u0087\n\u001A0\u0010Q\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0000\u001A3\u0010R\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001H\u0087\b\u001AT\u0010S\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001A\u0010\u000F\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010H\u0086\u0002\u00A2\u0006\u0002\u0010T\u001AG\u0010S\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010U\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011H\u0086\u0002\u001AM\u0010S\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0018\u0010\u000F\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110LH\u0086\u0002\u001AI\u0010S\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0014\u0010V\u001A\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0086\u0002\u001AM\u0010S\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0018\u0010\u000F\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110MH\u0086\u0002\u001AJ\u0010W\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u001A\u0010\u000F\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010H\u0087\n\u00A2\u0006\u0002\u0010X\u001A=\u0010W\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0012\u0010U\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011H\u0087\n\u001AC\u0010W\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0018\u0010\u000F\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110LH\u0087\n\u001A=\u0010W\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0012\u0010V\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\n\u001AC\u0010W\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0018\u0010\u000F\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110MH\u0087\n\u001AG\u0010Y\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u001A\u0010\u000F\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\u00A2\u0006\u0002\u0010X\u001A@\u0010Y\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0018\u0010\u000F\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110L\u001A@\u0010Y\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b2\u0018\u0010\u000F\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110M\u001A;\u0010Z\u001A\u0004\u0018\u0001H\u0003\"\t\b\u0000\u0010\u0002\u00A2\u0006\u0002\b \"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010!\u001A\u0002H\u0002H\u0087\b\u00A2\u0006\u0002\u00101\u001A:\u0010[\u001A\u00020\t\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010!\u001A\u0002H\u00022\u0006\u0010%\u001A\u0002H\u0003H\u0087\n\u00A2\u0006\u0002\u0010\\\u001A;\u0010]\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u0010\u00A2\u0006\u0002\u0010\u0018\u001AQ\u0010]\u001A\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110\u00102\u0006\u0010,\u001A\u0002H+\u00A2\u0006\u0002\u0010^\u001A4\u0010]\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110L\u001AO\u0010]\u001A\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110L2\u0006\u0010,\u001A\u0002H+\u00A2\u0006\u0002\u0010_\u001A2\u0010]\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u001AM\u0010]\u001A\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010,\u001A\u0002H+H\u0007\u00A2\u0006\u0002\u0010`\u001A4\u0010]\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110M\u001AO\u0010]\u001A\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010+*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\b*\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00110M2\u0006\u0010,\u001A\u0002H+\u00A2\u0006\u0002\u0010a\u001A2\u0010b\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u001A1\u0010c\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u001BH\u0087\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006d"}, d2 = {"buildMap", "", "K", "V", "capacity", "", "builderAction", "Lkotlin/Function1;", "", "", "Lkotlin/ExtensionFunctionType;", "emptyMap", "hashMapOf", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Ljava/util/HashMap;", "linkedMapOf", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "([Lkotlin/Pair;)Ljava/util/LinkedHashMap;", "mapOf", "([Lkotlin/Pair;)Ljava/util/Map;", "mutableMapOf", "component1", "", "(Ljava/util/Map$Entry;)Ljava/lang/Object;", "component2", "contains", "", "Lkotlin/internal/OnlyInputTypes;", "key", "(Ljava/util/Map;Ljava/lang/Object;)Z", "containsKey", "containsValue", "value", "filter", "predicate", "filterKeys", "filterNot", "filterNotTo", "M", "destination", "(Ljava/util/Map;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "filterTo", "filterValues", "get", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getOrElseNullable", "getOrPut", "getValue", "ifEmpty", "R", "(Ljava/util/Map;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "isNullOrEmpty", "iterator", "", "", "", "mutableIterator", "mapKeys", "transform", "mapKeysTo", "mapValues", "mapValuesTo", "minus", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/util/Map;", "keys", "(Ljava/util/Map;[Ljava/lang/Object;)Ljava/util/Map;", "", "Lkotlin/sequences/Sequence;", "minusAssign", "(Ljava/util/Map;Ljava/lang/Object;)V", "(Ljava/util/Map;[Ljava/lang/Object;)V", "optimizeReadOnlyMap", "orEmpty", "plus", "(Ljava/util/Map;[Lkotlin/Pair;)Ljava/util/Map;", "pair", "map", "plusAssign", "(Ljava/util/Map;[Lkotlin/Pair;)V", "putAll", "remove", "set", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)V", "toMap", "([Lkotlin/Pair;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/lang/Iterable;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/util/Map;Ljava/util/Map;)Ljava/util/Map;", "(Lkotlin/sequences/Sequence;Ljava/util/Map;)Ljava/util/Map;", "toMutableMap", "toPair", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/MapsKt")
class MapsKt__MapsKt extends MapsKt__MapsJVMKt {
    private static final Map buildMap(int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        Map map0 = MapsKt.createMapBuilder(v);
        function10.invoke(map0);
        return MapsKt.build(map0);
    }

    private static final Map buildMap(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        Map map0 = MapsKt.createMapBuilder();
        function10.invoke(map0);
        return MapsKt.build(map0);
    }

    private static final Object component1(Map.Entry map$Entry0) {
        Intrinsics.checkNotNullParameter(map$Entry0, "<this>");
        return map$Entry0.getKey();
    }

    private static final Object component2(Map.Entry map$Entry0) {
        Intrinsics.checkNotNullParameter(map$Entry0, "<this>");
        return map$Entry0.getValue();
    }

    private static final boolean contains(Map map0, Object object0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return map0.containsKey(object0);
    }

    private static final boolean containsKey(Map map0, Object object0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return map0.containsKey(object0);
    }

    private static final boolean containsValue(Map map0, Object object0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return map0.containsValue(object0);
    }

    public static final Map emptyMap() {
        Intrinsics.checkNotNull(EmptyMap.INSTANCE, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
        return EmptyMap.INSTANCE;
    }

    public static final Map filter(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Map map1 = new LinkedHashMap();
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(((Boolean)function10.invoke(map$Entry0)).booleanValue()) {
                map1.put(map$Entry0.getKey(), map$Entry0.getValue());
            }
        }
        return map1;
    }

    public static final Map filterKeys(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        LinkedHashMap linkedHashMap0 = new LinkedHashMap();
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(((Boolean)function10.invoke(map$Entry0.getKey())).booleanValue()) {
                linkedHashMap0.put(map$Entry0.getKey(), map$Entry0.getValue());
            }
        }
        return linkedHashMap0;
    }

    public static final Map filterNot(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Map map1 = new LinkedHashMap();
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(!((Boolean)function10.invoke(map$Entry0)).booleanValue()) {
                map1.put(map$Entry0.getKey(), map$Entry0.getValue());
            }
        }
        return map1;
    }

    public static final Map filterNotTo(Map map0, Map map1, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(map1, "destination");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(!((Boolean)function10.invoke(map$Entry0)).booleanValue()) {
                map1.put(map$Entry0.getKey(), map$Entry0.getValue());
            }
        }
        return map1;
    }

    public static final Map filterTo(Map map0, Map map1, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(map1, "destination");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(((Boolean)function10.invoke(map$Entry0)).booleanValue()) {
                map1.put(map$Entry0.getKey(), map$Entry0.getValue());
            }
        }
        return map1;
    }

    public static final Map filterValues(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        LinkedHashMap linkedHashMap0 = new LinkedHashMap();
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(((Boolean)function10.invoke(map$Entry0.getValue())).booleanValue()) {
                linkedHashMap0.put(map$Entry0.getKey(), map$Entry0.getValue());
            }
        }
        return linkedHashMap0;
    }

    private static final Object get(Map map0, Object object0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return map0.get(object0);
    }

    private static final Object getOrElse(Map map0, Object object0, Function0 function00) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        Object object1 = map0.get(object0);
        return object1 == null ? function00.invoke() : object1;
    }

    public static final Object getOrElseNullable(Map map0, Object object0, Function0 function00) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        Object object1 = map0.get(object0);
        return object1 != null || map0.containsKey(object0) ? object1 : function00.invoke();
    }

    public static final Object getOrPut(Map map0, Object object0, Function0 function00) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        Object object1 = map0.get(object0);
        if(object1 == null) {
            Object object2 = function00.invoke();
            map0.put(object0, object2);
            return object2;
        }
        return object1;
    }

    public static final Object getValue(Map map0, Object object0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return MapsKt.getOrImplicitDefaultNullable(map0, object0);
    }

    private static final HashMap hashMapOf() {
        return new HashMap();
    }

    public static final HashMap hashMapOf(Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        HashMap hashMap0 = new HashMap(MapsKt.mapCapacity(arr_pair.length));
        MapsKt.putAll(hashMap0, arr_pair);
        return hashMap0;
    }

    private static final Object ifEmpty(Map map0, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        return map0.isEmpty() ? function00.invoke() : map0;
    }

    private static final boolean isNotEmpty(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return !map0.isEmpty();
    }

    private static final boolean isNullOrEmpty(Map map0) {
        return map0 == null || map0.isEmpty();
    }

    private static final Iterator iterator(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return map0.entrySet().iterator();
    }

    private static final LinkedHashMap linkedMapOf() {
        return new LinkedHashMap();
    }

    public static final LinkedHashMap linkedMapOf(Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        return (LinkedHashMap)MapsKt.toMap(arr_pair, new LinkedHashMap(MapsKt.mapCapacity(arr_pair.length)));
    }

    public static final Map mapKeys(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "transform");
        LinkedHashMap linkedHashMap0 = new LinkedHashMap(MapsKt.mapCapacity(map0.size()));
        for(Object object0: map0.entrySet()) {
            linkedHashMap0.put(function10.invoke(object0), ((Map.Entry)object0).getValue());
        }
        return linkedHashMap0;
    }

    public static final Map mapKeysTo(Map map0, Map map1, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(map1, "destination");
        Intrinsics.checkNotNullParameter(function10, "transform");
        for(Object object0: map0.entrySet()) {
            map1.put(function10.invoke(object0), ((Map.Entry)object0).getValue());
        }
        return map1;
    }

    private static final Map mapOf() {
        return MapsKt.emptyMap();
    }

    public static final Map mapOf(Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        return arr_pair.length <= 0 ? MapsKt.emptyMap() : MapsKt.toMap(arr_pair, new LinkedHashMap(MapsKt.mapCapacity(arr_pair.length)));
    }

    public static final Map mapValues(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "transform");
        LinkedHashMap linkedHashMap0 = new LinkedHashMap(MapsKt.mapCapacity(map0.size()));
        for(Object object0: map0.entrySet()) {
            linkedHashMap0.put(((Map.Entry)object0).getKey(), function10.invoke(object0));
        }
        return linkedHashMap0;
    }

    public static final Map mapValuesTo(Map map0, Map map1, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(map1, "destination");
        Intrinsics.checkNotNullParameter(function10, "transform");
        for(Object object0: map0.entrySet()) {
            map1.put(((Map.Entry)object0).getKey(), function10.invoke(object0));
        }
        return map1;
    }

    public static final Map minus(Map map0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "keys");
        Map map1 = MapsKt.toMutableMap(map0);
        CollectionsKt.removeAll(map1.keySet(), iterable0);
        return MapsKt.optimizeReadOnlyMap(map1);
    }

    public static final Map minus(Map map0, Object object0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Map map1 = MapsKt.toMutableMap(map0);
        map1.remove(object0);
        return MapsKt.optimizeReadOnlyMap(map1);
    }

    public static final Map minus(Map map0, Sequence sequence0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "keys");
        Map map1 = MapsKt.toMutableMap(map0);
        CollectionsKt.removeAll(map1.keySet(), sequence0);
        return MapsKt.optimizeReadOnlyMap(map1);
    }

    public static final Map minus(Map map0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(arr_object, "keys");
        Map map1 = MapsKt.toMutableMap(map0);
        CollectionsKt.removeAll(map1.keySet(), arr_object);
        return MapsKt.optimizeReadOnlyMap(map1);
    }

    private static final void minusAssign(Map map0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "keys");
        CollectionsKt.removeAll(map0.keySet(), iterable0);
    }

    private static final void minusAssign(Map map0, Object object0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        map0.remove(object0);
    }

    private static final void minusAssign(Map map0, Sequence sequence0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "keys");
        CollectionsKt.removeAll(map0.keySet(), sequence0);
    }

    private static final void minusAssign(Map map0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(arr_object, "keys");
        CollectionsKt.removeAll(map0.keySet(), arr_object);
    }

    private static final Iterator mutableIterator(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return map0.entrySet().iterator();
    }

    private static final Map mutableMapOf() {
        return new LinkedHashMap();
    }

    public static final Map mutableMapOf(Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        LinkedHashMap linkedHashMap0 = new LinkedHashMap(MapsKt.mapCapacity(arr_pair.length));
        MapsKt.putAll(linkedHashMap0, arr_pair);
        return linkedHashMap0;
    }

    public static final Map optimizeReadOnlyMap(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        switch(map0.size()) {
            case 0: {
                return MapsKt.emptyMap();
            }
            case 1: {
                return MapsKt.toSingletonMap(map0);
            }
            default: {
                return map0;
            }
        }
    }

    private static final Map orEmpty(Map map0) {
        return map0 == null ? MapsKt.emptyMap() : map0;
    }

    public static final Map plus(Map map0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "pairs");
        if(map0.isEmpty()) {
            return MapsKt.toMap(iterable0);
        }
        Map map1 = new LinkedHashMap(map0);
        MapsKt.putAll(map1, iterable0);
        return map1;
    }

    public static final Map plus(Map map0, Map map1) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(map1, "map");
        LinkedHashMap linkedHashMap0 = new LinkedHashMap(map0);
        linkedHashMap0.putAll(map1);
        return linkedHashMap0;
    }

    public static final Map plus(Map map0, Pair pair0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(pair0, "pair");
        if(map0.isEmpty()) {
            return MapsKt.mapOf(pair0);
        }
        LinkedHashMap linkedHashMap0 = new LinkedHashMap(map0);
        linkedHashMap0.put(pair0.getFirst(), pair0.getSecond());
        return linkedHashMap0;
    }

    public static final Map plus(Map map0, Sequence sequence0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "pairs");
        Map map1 = new LinkedHashMap(map0);
        MapsKt.putAll(map1, sequence0);
        return MapsKt.optimizeReadOnlyMap(map1);
    }

    public static final Map plus(Map map0, Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        if(map0.isEmpty()) {
            return MapsKt.toMap(arr_pair);
        }
        Map map1 = new LinkedHashMap(map0);
        MapsKt.putAll(map1, arr_pair);
        return map1;
    }

    private static final void plusAssign(Map map0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "pairs");
        MapsKt.putAll(map0, iterable0);
    }

    private static final void plusAssign(Map map0, Map map1) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(map1, "map");
        map0.putAll(map1);
    }

    private static final void plusAssign(Map map0, Pair pair0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(pair0, "pair");
        map0.put(pair0.getFirst(), pair0.getSecond());
    }

    private static final void plusAssign(Map map0, Sequence sequence0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "pairs");
        MapsKt.putAll(map0, sequence0);
    }

    private static final void plusAssign(Map map0, Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        MapsKt.putAll(map0, arr_pair);
    }

    public static final void putAll(Map map0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "pairs");
        for(Object object0: iterable0) {
            map0.put(((Pair)object0).component1(), ((Pair)object0).component2());
        }
    }

    public static final void putAll(Map map0, Sequence sequence0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "pairs");
        for(Object object0: sequence0) {
            map0.put(((Pair)object0).component1(), ((Pair)object0).component2());
        }
    }

    public static final void putAll(Map map0, Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        for(int v = 0; v < arr_pair.length; ++v) {
            Pair pair0 = arr_pair[v];
            map0.put(pair0.component1(), pair0.component2());
        }
    }

    private static final Object remove(Map map0, Object object0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return TypeIntrinsics.asMutableMap(map0).remove(object0);
    }

    private static final void set(Map map0, Object object0, Object object1) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        map0.put(object0, object1);
    }

    public static final Map toMap(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        if(iterable0 instanceof Collection) {
            switch(((Collection)iterable0).size()) {
                case 0: {
                    return MapsKt.emptyMap();
                }
                case 1: {
                    if(iterable0 instanceof List) {
                        return MapsKt.mapOf(((Pair)((List)iterable0).get(0)));
                    }
                    Object object0 = iterable0.iterator().next();
                    return MapsKt.mapOf(((Pair)object0));
                }
                default: {
                    return MapsKt.toMap(iterable0, new LinkedHashMap(MapsKt.mapCapacity(((Collection)iterable0).size())));
                }
            }
        }
        return MapsKt.optimizeReadOnlyMap(MapsKt.toMap(iterable0, new LinkedHashMap()));
    }

    public static final Map toMap(Iterable iterable0, Map map0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(map0, "destination");
        MapsKt.putAll(map0, iterable0);
        return map0;
    }

    public static final Map toMap(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        switch(map0.size()) {
            case 0: {
                return MapsKt.emptyMap();
            }
            case 1: {
                return MapsKt.toSingletonMap(map0);
            }
            default: {
                return MapsKt.toMutableMap(map0);
            }
        }
    }

    public static final Map toMap(Map map0, Map map1) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(map1, "destination");
        map1.putAll(map0);
        return map1;
    }

    public static final Map toMap(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        return MapsKt.optimizeReadOnlyMap(MapsKt.toMap(sequence0, new LinkedHashMap()));
    }

    public static final Map toMap(Sequence sequence0, Map map0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        Intrinsics.checkNotNullParameter(map0, "destination");
        MapsKt.putAll(map0, sequence0);
        return map0;
    }

    public static final Map toMap(Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(arr_pair, "<this>");
        switch(arr_pair.length) {
            case 0: {
                return MapsKt.emptyMap();
            }
            case 1: {
                return MapsKt.mapOf(arr_pair[0]);
            }
            default: {
                return MapsKt.toMap(arr_pair, new LinkedHashMap(MapsKt.mapCapacity(arr_pair.length)));
            }
        }
    }

    public static final Map toMap(Pair[] arr_pair, Map map0) {
        Intrinsics.checkNotNullParameter(arr_pair, "<this>");
        Intrinsics.checkNotNullParameter(map0, "destination");
        MapsKt.putAll(map0, arr_pair);
        return map0;
    }

    public static final Map toMutableMap(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return new LinkedHashMap(map0);
    }

    private static final Pair toPair(Map.Entry map$Entry0) {
        Intrinsics.checkNotNullParameter(map$Entry0, "<this>");
        return new Pair(map$Entry0.getKey(), map$Entry0.getValue());
    }
}

