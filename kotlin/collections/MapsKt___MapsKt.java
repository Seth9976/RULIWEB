package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\u001C\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u001F\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000F\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001AJ\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\u0005\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\b\u00F8\u0001\u0000\u001A$\u0010\b\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001AJ\u0010\b\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\u0005\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\b\u00F8\u0001\u0000\u001A9\u0010\t\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\n\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0087\b\u001A6\u0010\u000B\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001A\'\u0010\r\u001A\u00020\u000E\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\u0087\b\u001AJ\u0010\r\u001A\u00020\u000E\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\u0005\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\b\u00F8\u0001\u0000\u001A[\u0010\u000F\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001A\u001C\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0013\u001A]\u0010\u0014\u001A\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001A\u001C\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0013\u001A\\\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001A \u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0006H\u0086\b\u00F8\u0001\u0000\u001Aa\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001A \u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f0\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\b\u0017\u001Au\u0010\u0018\u001A\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001A*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001B\u001A\u0002H\u00192$\u0010\u0012\u001A \u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\n0\u0006H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001Aw\u0010\u0018\u001A\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001A*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001B\u001A\u0002H\u00192$\u0010\u0012\u001A \u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\f0\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001D\u0010\u001C\u001AJ\u0010\u001E\u001A\u00020\u001F\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010 \u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001F0\u0006H\u0087\b\u00F8\u0001\u0000\u001AV\u0010!\u001A\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\u0012\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0086\b\u00F8\u0001\u0000\u001A\\\u0010\"\u001A\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001A\u001C\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\u0086\b\u00F8\u0001\u0000\u001Au\u0010#\u001A\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0010*\u00020\u0011\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001A*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001B\u001A\u0002H\u00192 \u0010\u0012\u001A\u001C\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00100\u0006H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001Ao\u0010$\u001A\u0002H\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010\"\u0010\b\u0003\u0010\u0019*\n\u0012\u0006\b\u0000\u0012\u0002H\u00100\u001A*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u001B\u001A\u0002H\u00192\u001E\u0010\u0012\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001Ak\u0010%\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000E\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\b(\u001Ah\u0010)\u001A\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000E\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u001A_\u0010*\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000E\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010+\u001AJ\u0010*\u001A\u00020,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\u0087\b\u00F8\u0001\u0000\u001AJ\u0010*\u001A\u00020-\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020-0\u0006H\u0087\b\u00F8\u0001\u0000\u001Aa\u0010.\u001A\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000E\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010+\u001AQ\u0010.\u001A\u0004\u0018\u00010,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010/\u001AQ\u0010.\u001A\u0004\u0018\u00010-\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020-0\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u00100\u001Aq\u00101\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001A\u00102\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001003j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`42\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u00105\u001As\u00106\u001A\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001A\u00102\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001003j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`42\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u00105\u001Al\u00107\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00102\u001A.\u0012\u0012\b\u0000\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000703j\u0016\u0012\u0012\b\u0000\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`4H\u0087\b\u00A2\u0006\u0002\b8\u001Ai\u00109\u001A\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00102\u001A.\u0012\u0012\b\u0000\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000703j\u0016\u0012\u0012\b\u0000\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`4H\u0087\b\u001Ak\u0010:\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000E\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\b;\u001Ah\u0010<\u001A\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000E\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u001A_\u0010=\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000E\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010+\u001AJ\u0010=\u001A\u00020,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\u0087\b\u00F8\u0001\u0000\u001AJ\u0010=\u001A\u00020-\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020-0\u0006H\u0087\b\u00F8\u0001\u0000\u001Aa\u0010>\u001A\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000E\b\u0002\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010+\u001AQ\u0010>\u001A\u0004\u0018\u00010,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020,0\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010/\u001AQ\u0010>\u001A\u0004\u0018\u00010-\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020-0\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u00100\u001Aq\u0010?\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001A\u00102\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001003j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`42\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u00105\u001As\u0010@\u001A\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001A\u00102\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u001003j\n\u0012\u0006\b\u0000\u0012\u0002H\u0010`42\u001E\u0010\'\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00100\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u00105\u001Al\u0010A\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00102\u001A.\u0012\u0012\b\u0000\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000703j\u0016\u0012\u0012\b\u0000\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`4H\u0087\b\u00A2\u0006\u0002\bB\u001Ai\u0010C\u001A\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u00102\u001A.\u0012\u0012\b\u0000\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000703j\u0016\u0012\u0012\b\u0000\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`4H\u0087\b\u001A$\u0010D\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001AJ\u0010D\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001E\u0010\u0005\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\b\u00F8\u0001\u0000\u001AY\u0010E\u001A\u0002HF\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010F*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002HF2\u001E\u0010 \u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001F0\u0006H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010G\u001An\u0010H\u001A\u0002HF\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010F*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002HF23\u0010 \u001A/\u0012\u0013\u0012\u00110\u000E\u00A2\u0006\f\bJ\u0012\b\bK\u0012\u0004\b\b(L\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u001F0IH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010M\u001A6\u0010N\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030O0\u0016\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006P"}, d2 = {"all", "", "K", "V", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "count", "", "firstNotNullOf", "R", "", "transform", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "firstNotNullOfOrNull", "flatMap", "", "flatMapSequence", "flatMapTo", "C", "", "destination", "(Ljava/util/Map;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "flatMapSequenceTo", "forEach", "", "action", "map", "mapNotNull", "mapNotNullTo", "mapTo", "maxBy", "", "selector", "maxByOrThrow", "maxByOrNull", "maxOf", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Comparable;", "", "", "maxOfOrNull", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Double;", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Float;", "maxOfWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/Map;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "maxOfWithOrNull", "maxWith", "maxWithOrThrow", "maxWithOrNull", "minBy", "minByOrThrow", "minByOrNull", "minOf", "minOfOrNull", "minOfWith", "minOfWithOrNull", "minWith", "minWithOrThrow", "minWithOrNull", "none", "onEach", "M", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "onEachIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "index", "(Ljava/util/Map;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "toList", "Lkotlin/Pair;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/MapsKt")
class MapsKt___MapsKt extends MapsKt___MapsJvmKt {
    public static final boolean all(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        if(map0.isEmpty()) {
            return true;
        }
        for(Object object0: map0.entrySet()) {
            if(!((Boolean)function10.invoke(((Map.Entry)object0))).booleanValue()) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public static final boolean any(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return !map0.isEmpty();
    }

    public static final boolean any(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        if(map0.isEmpty()) {
            return false;
        }
        for(Object object0: map0.entrySet()) {
            if(((Boolean)function10.invoke(((Map.Entry)object0))).booleanValue()) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private static final Iterable asIterable(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return map0.entrySet();
    }

    public static final Sequence asSequence(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return CollectionsKt.asSequence(map0.entrySet());
    }

    private static final int count(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return map0.size();
    }

    public static final int count(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        int v = 0;
        if(map0.isEmpty()) {
            return 0;
        }
        for(Object object0: map0.entrySet()) {
            if(((Boolean)function10.invoke(((Map.Entry)object0))).booleanValue()) {
                ++v;
            }
        }
        return v;
    }

    private static final Object firstNotNullOf(Map map0, Function1 function10) {
        Object object0 = null;
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "transform");
        for(Object object1: map0.entrySet()) {
            Object object2 = function10.invoke(((Map.Entry)object1));
            if(object2 != null) {
                object0 = object2;
                break;
            }
        }
        if(object0 == null) {
            throw new NoSuchElementException("No element of the map was transformed to a non-null value.");
        }
        return object0;
    }

    private static final Object firstNotNullOfOrNull(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "transform");
        for(Object object0: map0.entrySet()) {
            Object object1 = function10.invoke(((Map.Entry)object0));
            if(object1 != null) {
                return object1;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public static final List flatMap(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "transform");
        Collection collection0 = new ArrayList();
        for(Object object0: map0.entrySet()) {
            CollectionsKt.addAll(collection0, ((Iterable)function10.invoke(((Map.Entry)object0))));
        }
        return (List)collection0;
    }

    public static final List flatMapSequence(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "transform");
        Collection collection0 = new ArrayList();
        for(Object object0: map0.entrySet()) {
            CollectionsKt.addAll(collection0, ((Sequence)function10.invoke(((Map.Entry)object0))));
        }
        return (List)collection0;
    }

    public static final Collection flatMapSequenceTo(Map map0, Collection collection0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(collection0, "destination");
        Intrinsics.checkNotNullParameter(function10, "transform");
        for(Object object0: map0.entrySet()) {
            CollectionsKt.addAll(collection0, ((Sequence)function10.invoke(((Map.Entry)object0))));
        }
        return collection0;
    }

    public static final Collection flatMapTo(Map map0, Collection collection0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(collection0, "destination");
        Intrinsics.checkNotNullParameter(function10, "transform");
        for(Object object0: map0.entrySet()) {
            CollectionsKt.addAll(collection0, ((Iterable)function10.invoke(((Map.Entry)object0))));
        }
        return collection0;
    }

    public static final void forEach(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "action");
        for(Object object0: map0.entrySet()) {
            function10.invoke(((Map.Entry)object0));
        }
    }

    public static final List map(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "transform");
        ArrayList arrayList0 = new ArrayList(map0.size());
        for(Object object0: map0.entrySet()) {
            arrayList0.add(function10.invoke(((Map.Entry)object0)));
        }
        return arrayList0;
    }

    public static final List mapNotNull(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "transform");
        Collection collection0 = new ArrayList();
        for(Object object0: map0.entrySet()) {
            Object object1 = function10.invoke(((Map.Entry)object0));
            if(object1 != null) {
                collection0.add(object1);
            }
        }
        return (List)collection0;
    }

    public static final Collection mapNotNullTo(Map map0, Collection collection0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(collection0, "destination");
        Intrinsics.checkNotNullParameter(function10, "transform");
        for(Object object0: map0.entrySet()) {
            Object object1 = function10.invoke(((Map.Entry)object0));
            if(object1 != null) {
                collection0.add(object1);
            }
        }
        return collection0;
    }

    public static final Collection mapTo(Map map0, Collection collection0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(collection0, "destination");
        Intrinsics.checkNotNullParameter(function10, "transform");
        for(Object object0: map0.entrySet()) {
            collection0.add(function10.invoke(((Map.Entry)object0)));
        }
        return collection0;
    }

    private static final Map.Entry maxByOrNull(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        if(!iterator0.hasNext()) {
            return (Map.Entry)object0;
        }
        Comparable comparable0 = (Comparable)function10.invoke(object0);
        do {
            Object object1 = iterator0.next();
            Comparable comparable1 = (Comparable)function10.invoke(object1);
            if(comparable0.compareTo(comparable1) < 0) {
                object0 = object1;
                comparable0 = comparable1;
            }
        }
        while(iterator0.hasNext());
        return (Map.Entry)object0;
    }

    private static final Map.Entry maxByOrThrow(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object0 = iterator0.next();
        if(iterator0.hasNext()) {
            Comparable comparable0 = (Comparable)function10.invoke(object0);
            while(true) {
                Object object1 = iterator0.next();
                Comparable comparable1 = (Comparable)function10.invoke(object1);
                if(comparable0.compareTo(comparable1) < 0) {
                    object0 = object1;
                    comparable0 = comparable1;
                }
                if(!iterator0.hasNext()) {
                    break;
                }
            }
        }
        return (Map.Entry)object0;
    }

    private static final double maxOf(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object0 = iterator0.next();
        double f;
        for(f = ((Number)function10.invoke(object0)).doubleValue(); iterator0.hasNext(); f = Math.max(f, ((Number)function10.invoke(object1)).doubleValue())) {
            Object object1 = iterator0.next();
        }
        return f;
    }

    private static final float maxOf(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object0 = iterator0.next();
        float f;
        for(f = ((Number)function10.invoke(object0)).floatValue(); iterator0.hasNext(); f = Math.max(f, ((Number)function10.invoke(object1)).floatValue())) {
            Object object1 = iterator0.next();
        }
        return f;
    }

    private static final Comparable maxOf(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object0 = iterator0.next();
        Comparable comparable0 = (Comparable)function10.invoke(object0);
        while(iterator0.hasNext()) {
            Object object1 = iterator0.next();
            Comparable comparable1 = (Comparable)function10.invoke(object1);
            if(comparable0.compareTo(comparable1) < 0) {
                comparable0 = comparable1;
            }
        }
        return comparable0;
    }

    private static final Comparable maxOfOrNull(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        Comparable comparable0 = (Comparable)function10.invoke(object0);
        while(iterator0.hasNext()) {
            Object object1 = iterator0.next();
            Comparable comparable1 = (Comparable)function10.invoke(object1);
            if(comparable0.compareTo(comparable1) < 0) {
                comparable0 = comparable1;
            }
        }
        return comparable0;
    }

    private static final Double maxOfOrNull(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        double f;
        for(f = ((Number)function10.invoke(object0)).doubleValue(); iterator0.hasNext(); f = Math.max(f, ((Number)function10.invoke(object1)).doubleValue())) {
            Object object1 = iterator0.next();
        }
        return f;
    }

    private static final Float maxOfOrNull(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        float f;
        for(f = ((Number)function10.invoke(object0)).floatValue(); iterator0.hasNext(); f = Math.max(f, ((Number)function10.invoke(object1)).floatValue())) {
            Object object1 = iterator0.next();
        }
        return f;
    }

    private static final Object maxOfWith(Map map0, Comparator comparator0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object0 = iterator0.next();
        Object object1 = function10.invoke(object0);
        while(iterator0.hasNext()) {
            Object object2 = iterator0.next();
            Object object3 = function10.invoke(object2);
            if(comparator0.compare(object1, object3) < 0) {
                object1 = object3;
            }
        }
        return object1;
    }

    private static final Object maxOfWithOrNull(Map map0, Comparator comparator0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        Object object1 = function10.invoke(object0);
        while(iterator0.hasNext()) {
            Object object2 = iterator0.next();
            Object object3 = function10.invoke(object2);
            if(comparator0.compare(object1, object3) < 0) {
                object1 = object3;
            }
        }
        return object1;
    }

    private static final Map.Entry maxWithOrNull(Map map0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return (Map.Entry)CollectionsKt.maxWithOrNull(map0.entrySet(), comparator0);
    }

    private static final Map.Entry maxWithOrThrow(Map map0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return (Map.Entry)CollectionsKt.maxWithOrThrow(map0.entrySet(), comparator0);
    }

    private static final Map.Entry minByOrNull(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        if(!iterator0.hasNext()) {
            return (Map.Entry)object0;
        }
        Comparable comparable0 = (Comparable)function10.invoke(object0);
        do {
            Object object1 = iterator0.next();
            Comparable comparable1 = (Comparable)function10.invoke(object1);
            if(comparable0.compareTo(comparable1) > 0) {
                object0 = object1;
                comparable0 = comparable1;
            }
        }
        while(iterator0.hasNext());
        return (Map.Entry)object0;
    }

    private static final Map.Entry minByOrThrow(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object0 = iterator0.next();
        if(iterator0.hasNext()) {
            Comparable comparable0 = (Comparable)function10.invoke(object0);
            while(true) {
                Object object1 = iterator0.next();
                Comparable comparable1 = (Comparable)function10.invoke(object1);
                if(comparable0.compareTo(comparable1) > 0) {
                    object0 = object1;
                    comparable0 = comparable1;
                }
                if(!iterator0.hasNext()) {
                    break;
                }
            }
        }
        return (Map.Entry)object0;
    }

    private static final double minOf(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object0 = iterator0.next();
        double f;
        for(f = ((Number)function10.invoke(object0)).doubleValue(); iterator0.hasNext(); f = Math.min(f, ((Number)function10.invoke(object1)).doubleValue())) {
            Object object1 = iterator0.next();
        }
        return f;
    }

    private static final float minOf(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object0 = iterator0.next();
        float f;
        for(f = ((Number)function10.invoke(object0)).floatValue(); iterator0.hasNext(); f = Math.min(f, ((Number)function10.invoke(object1)).floatValue())) {
            Object object1 = iterator0.next();
        }
        return f;
    }

    private static final Comparable minOf(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object0 = iterator0.next();
        Comparable comparable0 = (Comparable)function10.invoke(object0);
        while(iterator0.hasNext()) {
            Object object1 = iterator0.next();
            Comparable comparable1 = (Comparable)function10.invoke(object1);
            if(comparable0.compareTo(comparable1) > 0) {
                comparable0 = comparable1;
            }
        }
        return comparable0;
    }

    private static final Comparable minOfOrNull(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        Comparable comparable0 = (Comparable)function10.invoke(object0);
        while(iterator0.hasNext()) {
            Object object1 = iterator0.next();
            Comparable comparable1 = (Comparable)function10.invoke(object1);
            if(comparable0.compareTo(comparable1) > 0) {
                comparable0 = comparable1;
            }
        }
        return comparable0;
    }

    private static final Double minOfOrNull(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        double f;
        for(f = ((Number)function10.invoke(object0)).doubleValue(); iterator0.hasNext(); f = Math.min(f, ((Number)function10.invoke(object1)).doubleValue())) {
            Object object1 = iterator0.next();
        }
        return f;
    }

    private static final Float minOfOrNull(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        float f;
        for(f = ((Number)function10.invoke(object0)).floatValue(); iterator0.hasNext(); f = Math.min(f, ((Number)function10.invoke(object1)).floatValue())) {
            Object object1 = iterator0.next();
        }
        return f;
    }

    private static final Object minOfWith(Map map0, Comparator comparator0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object0 = iterator0.next();
        Object object1 = function10.invoke(object0);
        while(iterator0.hasNext()) {
            Object object2 = iterator0.next();
            Object object3 = function10.invoke(object2);
            if(comparator0.compare(object1, object3) > 0) {
                object1 = object3;
            }
        }
        return object1;
    }

    private static final Object minOfWithOrNull(Map map0, Comparator comparator0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        Object object1 = function10.invoke(object0);
        while(iterator0.hasNext()) {
            Object object2 = iterator0.next();
            Object object3 = function10.invoke(object2);
            if(comparator0.compare(object1, object3) > 0) {
                object1 = object3;
            }
        }
        return object1;
    }

    private static final Map.Entry minWithOrNull(Map map0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return (Map.Entry)CollectionsKt.minWithOrNull(map0.entrySet(), comparator0);
    }

    private static final Map.Entry minWithOrThrow(Map map0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return (Map.Entry)CollectionsKt.minWithOrThrow(map0.entrySet(), comparator0);
    }

    public static final boolean none(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return map0.isEmpty();
    }

    public static final boolean none(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        if(map0.isEmpty()) {
            return true;
        }
        for(Object object0: map0.entrySet()) {
            if(((Boolean)function10.invoke(((Map.Entry)object0))).booleanValue()) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public static final Map onEach(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "action");
        for(Object object0: map0.entrySet()) {
            function10.invoke(((Map.Entry)object0));
        }
        return map0;
    }

    public static final Map onEachIndexed(Map map0, Function2 function20) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function20, "action");
        int v = 0;
        for(Object object0: map0.entrySet()) {
            if(v < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            function20.invoke(v, object0);
            ++v;
        }
        return map0;
    }

    public static final List toList(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        if(map0.size() == 0) {
            return CollectionsKt.emptyList();
        }
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return CollectionsKt.emptyList();
        }
        Object object0 = iterator0.next();
        if(!iterator0.hasNext()) {
            return CollectionsKt.listOf(new Pair(((Map.Entry)object0).getKey(), ((Map.Entry)object0).getValue()));
        }
        ArrayList arrayList0 = new ArrayList(map0.size());
        arrayList0.add(new Pair(((Map.Entry)object0).getKey(), ((Map.Entry)object0).getValue()));
        do {
            Object object1 = iterator0.next();
            arrayList0.add(new Pair(((Map.Entry)object1).getKey(), ((Map.Entry)object1).getValue()));
        }
        while(iterator0.hasNext());
        return arrayList0;
    }
}

