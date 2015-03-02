package sql.exec.config;

/**
 * Created by mstupin on 2/26/2015.
 */
public class TimeHolder {
    private long startTime;
    private static TimeHolder instance = new TimeHolder();

    public TimeHolder() {
    }

    public static TimeHolder getInstance() {
        return instance;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getOffset(long now) {
        return now - startTime;
    }
}
