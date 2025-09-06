package androidx.appcompat.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

class ActivityChooserModel extends DataSetObservable {
    public interface ActivityChooserModelClient {
        void setActivityChooserModel(ActivityChooserModel arg1);
    }

    public static final class ActivityResolveInfo implements Comparable {
        public final ResolveInfo resolveInfo;
        public float weight;

        public ActivityResolveInfo(ResolveInfo resolveInfo0) {
            this.resolveInfo = resolveInfo0;
        }

        public int compareTo(ActivityResolveInfo activityChooserModel$ActivityResolveInfo0) {
            return Float.floatToIntBits(activityChooserModel$ActivityResolveInfo0.weight) - Float.floatToIntBits(this.weight);
        }

        @Override
        public int compareTo(Object object0) {
            return this.compareTo(((ActivityResolveInfo)object0));
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(object0 == null) {
                return false;
            }
            return this.getClass() == object0.getClass() ? Float.floatToIntBits(this.weight) == Float.floatToIntBits(((ActivityResolveInfo)object0).weight) : false;
        }

        @Override
        public int hashCode() {
            return Float.floatToIntBits(this.weight) + 0x1F;
        }

        @Override
        public String toString() {
            return "[resolveInfo:" + this.resolveInfo.toString() + "; weight:" + new BigDecimal(((double)this.weight)) + "]";
        }
    }

    public interface ActivitySorter {
        void sort(Intent arg1, List arg2, List arg3);
    }

    static final class DefaultSorter implements ActivitySorter {
        private static final float WEIGHT_DECAY_COEFFICIENT = 0.95f;
        private final Map mPackageNameToActivityMap;

        DefaultSorter() {
            this.mPackageNameToActivityMap = new HashMap();
        }

        @Override  // androidx.appcompat.widget.ActivityChooserModel$ActivitySorter
        public void sort(Intent intent0, List list0, List list1) {
            Map map0 = this.mPackageNameToActivityMap;
            map0.clear();
            int v = list0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ActivityResolveInfo activityChooserModel$ActivityResolveInfo0 = (ActivityResolveInfo)list0.get(v1);
                activityChooserModel$ActivityResolveInfo0.weight = 0.0f;
                map0.put(new ComponentName(activityChooserModel$ActivityResolveInfo0.resolveInfo.activityInfo.packageName, activityChooserModel$ActivityResolveInfo0.resolveInfo.activityInfo.name), activityChooserModel$ActivityResolveInfo0);
            }
            int v2 = list1.size() - 1;
            float f = 1.0f;
            while(v2 >= 0) {
                HistoricalRecord activityChooserModel$HistoricalRecord0 = (HistoricalRecord)list1.get(v2);
                ActivityResolveInfo activityChooserModel$ActivityResolveInfo1 = (ActivityResolveInfo)map0.get(activityChooserModel$HistoricalRecord0.activity);
                if(activityChooserModel$ActivityResolveInfo1 != null) {
                    activityChooserModel$ActivityResolveInfo1.weight += activityChooserModel$HistoricalRecord0.weight * f;
                    f *= 0.95f;
                }
                --v2;
            }
            Collections.sort(list0);
        }
    }

    public static final class HistoricalRecord {
        public final ComponentName activity;
        public final long time;
        public final float weight;

        public HistoricalRecord(ComponentName componentName0, long v, float f) {
            this.activity = componentName0;
            this.time = v;
            this.weight = f;
        }

        public HistoricalRecord(String s, long v, float f) {
            this(ComponentName.unflattenFromString(s), v, f);
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(object0 == null) {
                return false;
            }
            if(this.getClass() != object0.getClass()) {
                return false;
            }
            ComponentName componentName0 = this.activity;
            if(componentName0 == null) {
                if(((HistoricalRecord)object0).activity != null) {
                    return false;
                }
            }
            else if(!componentName0.equals(((HistoricalRecord)object0).activity)) {
                return false;
            }
            return this.time == ((HistoricalRecord)object0).time ? Float.floatToIntBits(this.weight) == Float.floatToIntBits(((HistoricalRecord)object0).weight) : false;
        }

        @Override
        public int hashCode() {
            return this.activity == null ? (961 + ((int)(this.time ^ this.time >>> 0x20))) * 0x1F + Float.floatToIntBits(this.weight) : ((this.activity.hashCode() + 0x1F) * 0x1F + ((int)(this.time ^ this.time >>> 0x20))) * 0x1F + Float.floatToIntBits(this.weight);
        }

        @Override
        public String toString() {
            return "[; activity:" + this.activity + "; time:" + this.time + "; weight:" + new BigDecimal(((double)this.weight)) + "]";
        }
    }

    public interface OnChooseActivityListener {
        boolean onChooseActivity(ActivityChooserModel arg1, Intent arg2);
    }

    final class PersistHistoryAsyncTask extends AsyncTask {
        @Override  // android.os.AsyncTask
        public Object doInBackground(Object[] arr_object) {
            return this.doInBackground(arr_object);
        }

        public Void doInBackground(Object[] arr_object) {
            FileOutputStream fileOutputStream0;
            List list0 = (List)arr_object[0];
            String s = (String)arr_object[1];
            try {
                fileOutputStream0 = ActivityChooserModel.this.mContext.openFileOutput(s, 0);
            }
            catch(FileNotFoundException fileNotFoundException0) {
                Log.e("ActivityChooserModel", "Error writing historical record file: " + s, fileNotFoundException0);
                return null;
            }
            XmlSerializer xmlSerializer0 = Xml.newSerializer();
            try {
                try {
                    xmlSerializer0.setOutput(fileOutputStream0, null);
                    xmlSerializer0.startDocument("UTF-8", Boolean.TRUE);
                    xmlSerializer0.startTag(null, "historical-records");
                    int v = list0.size();
                    for(int v1 = 0; v1 < v; ++v1) {
                        HistoricalRecord activityChooserModel$HistoricalRecord0 = (HistoricalRecord)list0.remove(0);
                        xmlSerializer0.startTag(null, "historical-record");
                        xmlSerializer0.attribute(null, "activity", activityChooserModel$HistoricalRecord0.activity.flattenToString());
                        xmlSerializer0.attribute(null, "time", String.valueOf(activityChooserModel$HistoricalRecord0.time));
                        xmlSerializer0.attribute(null, "weight", String.valueOf(activityChooserModel$HistoricalRecord0.weight));
                        xmlSerializer0.endTag(null, "historical-record");
                    }
                    xmlSerializer0.endTag(null, "historical-records");
                    xmlSerializer0.endDocument();
                    ActivityChooserModel.this.mCanReadHistoricalData = true;
                }
                catch(IllegalArgumentException illegalArgumentException0) {
                    Log.e("ActivityChooserModel", "Error writing historical record file: " + ActivityChooserModel.this.mHistoryFileName, illegalArgumentException0);
                    ActivityChooserModel.this.mCanReadHistoricalData = true;
                    if(fileOutputStream0 != null) {
                        goto label_47;
                    }
                    return null;
                }
                catch(IllegalStateException illegalStateException0) {
                    Log.e("ActivityChooserModel", "Error writing historical record file: " + ActivityChooserModel.this.mHistoryFileName, illegalStateException0);
                    ActivityChooserModel.this.mCanReadHistoricalData = true;
                    if(fileOutputStream0 != null) {
                        goto label_47;
                    }
                    return null;
                }
                catch(IOException iOException0) {
                    Log.e("ActivityChooserModel", "Error writing historical record file: " + ActivityChooserModel.this.mHistoryFileName, iOException0);
                    ActivityChooserModel.this.mCanReadHistoricalData = true;
                    if(fileOutputStream0 != null) {
                        goto label_47;
                    }
                    return null;
                }
            }
            catch(Throwable throwable0) {
                ActivityChooserModel.this.mCanReadHistoricalData = true;
                if(fileOutputStream0 != null) {
                    try {
                        fileOutputStream0.close();
                    }
                    catch(IOException unused_ex) {
                    }
                }
                throw throwable0;
            }
            if(fileOutputStream0 != null) {
                try {
                label_47:
                    fileOutputStream0.close();
                    return null;
                }
                catch(IOException unused_ex) {
                }
            }
            return null;
        }
    }

    static final String ATTRIBUTE_ACTIVITY = "activity";
    static final String ATTRIBUTE_TIME = "time";
    static final String ATTRIBUTE_WEIGHT = "weight";
    static final boolean DEBUG = false;
    private static final int DEFAULT_ACTIVITY_INFLATION = 5;
    private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0f;
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String HISTORY_FILE_EXTENSION = ".xml";
    private static final int INVALID_INDEX = -1;
    static final String LOG_TAG = "ActivityChooserModel";
    static final String TAG_HISTORICAL_RECORD = "historical-record";
    static final String TAG_HISTORICAL_RECORDS = "historical-records";
    private final List mActivities;
    private OnChooseActivityListener mActivityChoserModelPolicy;
    private ActivitySorter mActivitySorter;
    boolean mCanReadHistoricalData;
    final Context mContext;
    private final List mHistoricalRecords;
    private boolean mHistoricalRecordsChanged;
    final String mHistoryFileName;
    private int mHistoryMaxSize;
    private final Object mInstanceLock;
    private Intent mIntent;
    private boolean mReadShareHistoryCalled;
    private boolean mReloadActivities;
    private static final Map sDataModelRegistry;
    private static final Object sRegistryLock;

    static {
        ActivityChooserModel.sRegistryLock = new Object();
        ActivityChooserModel.sDataModelRegistry = new HashMap();
    }

    private ActivityChooserModel(Context context0, String s) {
        this.mInstanceLock = new Object();
        this.mActivities = new ArrayList();
        this.mHistoricalRecords = new ArrayList();
        this.mActivitySorter = new DefaultSorter();
        this.mHistoryMaxSize = 50;
        this.mCanReadHistoricalData = true;
        this.mReadShareHistoryCalled = false;
        this.mHistoricalRecordsChanged = true;
        this.mReloadActivities = false;
        this.mContext = context0.getApplicationContext();
        if(!TextUtils.isEmpty(s) && !s.endsWith(".xml")) {
            this.mHistoryFileName = s + ".xml";
            return;
        }
        this.mHistoryFileName = s;
    }

    private boolean addHistoricalRecord(HistoricalRecord activityChooserModel$HistoricalRecord0) {
        boolean z = this.mHistoricalRecords.add(activityChooserModel$HistoricalRecord0);
        if(z) {
            this.mHistoricalRecordsChanged = true;
            this.pruneExcessiveHistoricalRecordsIfNeeded();
            this.persistHistoricalDataIfNeeded();
            this.sortActivitiesIfNeeded();
            this.notifyChanged();
        }
        return z;
    }

    public Intent chooseActivity(int v) {
        synchronized(this.mInstanceLock) {
            if(this.mIntent == null) {
                return null;
            }
            this.ensureConsistentState();
            ActivityResolveInfo activityChooserModel$ActivityResolveInfo0 = (ActivityResolveInfo)this.mActivities.get(v);
            ComponentName componentName0 = new ComponentName(activityChooserModel$ActivityResolveInfo0.resolveInfo.activityInfo.packageName, activityChooserModel$ActivityResolveInfo0.resolveInfo.activityInfo.name);
            Intent intent0 = new Intent(this.mIntent);
            intent0.setComponent(componentName0);
            if(this.mActivityChoserModelPolicy != null) {
                Intent intent1 = new Intent(intent0);
                if(this.mActivityChoserModelPolicy.onChooseActivity(this, intent1)) {
                    return null;
                }
            }
            this.addHistoricalRecord(new HistoricalRecord(componentName0, System.currentTimeMillis(), 1.0f));
            return intent0;
        }
    }

    private void ensureConsistentState() {
        boolean z = this.loadActivitiesIfNeeded();
        boolean z1 = this.readHistoricalDataIfNeeded();
        this.pruneExcessiveHistoricalRecordsIfNeeded();
        if(z || z1) {
            this.sortActivitiesIfNeeded();
            this.notifyChanged();
        }
    }

    public static ActivityChooserModel get(Context context0, String s) {
        synchronized(ActivityChooserModel.sRegistryLock) {
            Map map0 = ActivityChooserModel.sDataModelRegistry;
            ActivityChooserModel activityChooserModel0 = (ActivityChooserModel)map0.get(s);
            if(activityChooserModel0 == null) {
                activityChooserModel0 = new ActivityChooserModel(context0, s);
                map0.put(s, activityChooserModel0);
            }
            return activityChooserModel0;
        }
    }

    public ResolveInfo getActivity(int v) {
        synchronized(this.mInstanceLock) {
            this.ensureConsistentState();
        }
        return ((ActivityResolveInfo)this.mActivities.get(v)).resolveInfo;
    }

    public int getActivityCount() {
        synchronized(this.mInstanceLock) {
            this.ensureConsistentState();
        }
        return this.mActivities.size();
    }

    public int getActivityIndex(ResolveInfo resolveInfo0) {
        synchronized(this.mInstanceLock) {
            this.ensureConsistentState();
            List list0 = this.mActivities;
            int v1 = list0.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                if(((ActivityResolveInfo)list0.get(v2)).resolveInfo == resolveInfo0) {
                    return v2;
                }
            }
            return -1;
        }
    }

    public ResolveInfo getDefaultActivity() {
        synchronized(this.mInstanceLock) {
            this.ensureConsistentState();
            return !this.mActivities.isEmpty() ? ((ActivityResolveInfo)this.mActivities.get(0)).resolveInfo : null;
        }
    }

    public int getHistoryMaxSize() {
        synchronized(this.mInstanceLock) {
        }
        return this.mHistoryMaxSize;
    }

    public int getHistorySize() {
        synchronized(this.mInstanceLock) {
            this.ensureConsistentState();
        }
        return this.mHistoricalRecords.size();
    }

    public Intent getIntent() {
        synchronized(this.mInstanceLock) {
        }
        return this.mIntent;
    }

    private boolean loadActivitiesIfNeeded() {
        if(this.mReloadActivities && this.mIntent != null) {
            this.mReloadActivities = false;
            this.mActivities.clear();
            List list0 = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
            int v1 = list0.size();
            for(int v = 0; v < v1; ++v) {
                ActivityResolveInfo activityChooserModel$ActivityResolveInfo0 = new ActivityResolveInfo(((ResolveInfo)list0.get(v)));
                this.mActivities.add(activityChooserModel$ActivityResolveInfo0);
            }
            return true;
        }
        return false;
    }

    private void persistHistoricalDataIfNeeded() {
        if(!this.mReadShareHistoryCalled) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
        if(this.mHistoricalRecordsChanged) {
            this.mHistoricalRecordsChanged = false;
            if(!TextUtils.isEmpty(this.mHistoryFileName)) {
                new PersistHistoryAsyncTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{new ArrayList(this.mHistoricalRecords), this.mHistoryFileName});
            }
        }
    }

    private void pruneExcessiveHistoricalRecordsIfNeeded() {
        int v = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
        if(v > 0) {
            this.mHistoricalRecordsChanged = true;
            for(int v1 = 0; v1 < v; ++v1) {
                HistoricalRecord activityChooserModel$HistoricalRecord0 = (HistoricalRecord)this.mHistoricalRecords.remove(0);
            }
        }
    }

    private boolean readHistoricalDataIfNeeded() {
        if(this.mCanReadHistoricalData && this.mHistoricalRecordsChanged && !TextUtils.isEmpty(this.mHistoryFileName)) {
            this.mCanReadHistoricalData = false;
            this.mReadShareHistoryCalled = true;
            this.readHistoricalDataImpl();
            return true;
        }
        return false;
    }

    private void readHistoricalDataImpl() {
        List list0;
        XmlPullParser xmlPullParser0;
        FileInputStream fileInputStream0;
        try {
            fileInputStream0 = this.mContext.openFileInput(this.mHistoryFileName);
        }
        catch(FileNotFoundException unused_ex) {
            return;
        }
        try {
            xmlPullParser0 = Xml.newPullParser();
            xmlPullParser0.setInput(fileInputStream0, "UTF-8");
            for(int v = 0; v != 1 && v != 2; v = xmlPullParser0.next()) {
            }
            if(!"historical-records".equals(xmlPullParser0.getName())) {
                throw new XmlPullParserException("Share records file does not start with historical-records tag.");
            }
            list0 = this.mHistoricalRecords;
            list0.clear();
            while(true) {
            label_11:
                int v1 = xmlPullParser0.next();
                if(v1 == 1) {
                    break;
                }
                goto label_16;
            }
        }
        catch(XmlPullParserException xmlPullParserException0) {
            goto label_22;
        }
        catch(IOException iOException0) {
            goto label_26;
        }
        catch(Throwable throwable0) {
            goto label_32;
        }
        if(fileInputStream0 != null) {
            try {
                fileInputStream0.close();
            }
            catch(IOException unused_ex) {
            }
            return;
        label_16:
            if(v1 == 3 || v1 == 4) {
                goto label_11;
            }
            try {
                try {
                    if(!"historical-record".equals(xmlPullParser0.getName())) {
                        throw new XmlPullParserException("Share records file not well-formed.");
                    }
                    list0.add(new HistoricalRecord(xmlPullParser0.getAttributeValue(null, "activity"), Long.parseLong(xmlPullParser0.getAttributeValue(null, "time")), Float.parseFloat(xmlPullParser0.getAttributeValue(null, "weight"))));
                    goto label_11;
                }
                catch(XmlPullParserException xmlPullParserException0) {
                label_22:
                    Log.e("ActivityChooserModel", "Error reading historical recrod file: " + this.mHistoryFileName, xmlPullParserException0);
                    if(fileInputStream0 != null) {
                        goto label_28;
                    }
                    return;
                }
                catch(IOException iOException0) {
                label_26:
                    Log.e("ActivityChooserModel", "Error reading historical recrod file: " + this.mHistoryFileName, iOException0);
                    if(fileInputStream0 != null) {
                        goto label_28;
                    }
                    return;
                }
            }
            catch(Throwable throwable0) {
                goto label_32;
            }
            try {
            label_28:
                fileInputStream0.close();
            }
            catch(IOException unused_ex) {
            }
        }
        return;
    label_32:
        if(fileInputStream0 != null) {
            try {
                fileInputStream0.close();
            }
            catch(IOException unused_ex) {
            }
        }
        throw throwable0;
    }

    public void setActivitySorter(ActivitySorter activityChooserModel$ActivitySorter0) {
        synchronized(this.mInstanceLock) {
            if(this.mActivitySorter == activityChooserModel$ActivitySorter0) {
                return;
            }
            this.mActivitySorter = activityChooserModel$ActivitySorter0;
            if(this.sortActivitiesIfNeeded()) {
                this.notifyChanged();
            }
        }
    }

    public void setDefaultActivity(int v) {
        synchronized(this.mInstanceLock) {
            this.ensureConsistentState();
            ActivityResolveInfo activityChooserModel$ActivityResolveInfo0 = (ActivityResolveInfo)this.mActivities.get(v);
            ActivityResolveInfo activityChooserModel$ActivityResolveInfo1 = (ActivityResolveInfo)this.mActivities.get(0);
            float f = activityChooserModel$ActivityResolveInfo1 == null ? 1.0f : activityChooserModel$ActivityResolveInfo1.weight - activityChooserModel$ActivityResolveInfo0.weight + 5.0f;
            this.addHistoricalRecord(new HistoricalRecord(new ComponentName(activityChooserModel$ActivityResolveInfo0.resolveInfo.activityInfo.packageName, activityChooserModel$ActivityResolveInfo0.resolveInfo.activityInfo.name), System.currentTimeMillis(), f));
        }
    }

    public void setHistoryMaxSize(int v) {
        synchronized(this.mInstanceLock) {
            if(this.mHistoryMaxSize == v) {
                return;
            }
            this.mHistoryMaxSize = v;
            this.pruneExcessiveHistoricalRecordsIfNeeded();
            if(this.sortActivitiesIfNeeded()) {
                this.notifyChanged();
            }
        }
    }

    public void setIntent(Intent intent0) {
        synchronized(this.mInstanceLock) {
            if(this.mIntent == intent0) {
                return;
            }
            this.mIntent = intent0;
            this.mReloadActivities = true;
            this.ensureConsistentState();
        }
    }

    public void setOnChooseActivityListener(OnChooseActivityListener activityChooserModel$OnChooseActivityListener0) {
        synchronized(this.mInstanceLock) {
            this.mActivityChoserModelPolicy = activityChooserModel$OnChooseActivityListener0;
        }
    }

    private boolean sortActivitiesIfNeeded() {
        if(this.mActivitySorter != null && this.mIntent != null && !this.mActivities.isEmpty() && !this.mHistoricalRecords.isEmpty()) {
            ActivitySorter activityChooserModel$ActivitySorter0 = this.mActivitySorter;
            Intent intent0 = this.mIntent;
            List list0 = Collections.unmodifiableList(this.mHistoricalRecords);
            activityChooserModel$ActivitySorter0.sort(intent0, this.mActivities, list0);
            return true;
        }
        return false;
    }
}

