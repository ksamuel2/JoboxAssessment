package AthleteQASession;

import java.sql.Time;

public class Session {
    private final int sessionId;
    private final String hostName;
    private final Time startTime;
    private final Time endTime;
    public Session(int sessionId, String hostName, Time startTime, Time endTime) {
        this.sessionId = sessionId;
        this.hostName = hostName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getSessionId() {
        return sessionId;
    }

    public String getHostName() {
        return hostName;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }
}
