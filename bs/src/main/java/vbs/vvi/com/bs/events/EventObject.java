package vbs.vvi.com.bs.events;

/**
 * Created by Wayne on 2016/7/19.
 */
public class EventObject {
    public int mEventId;
    public Object mObject;

    public EventObject() {
    }

    public EventObject(int eventId, Object object) {
        mEventId = eventId;
        mObject = object;
    }

    public int getEventId() {
        return mEventId;
    }

    public void setEventId(int eventId) {
        mEventId = eventId;
    }

    public Object getObject() {
        return mObject;
    }

    public void setObject(Object object) {
        mObject = object;
    }
}
