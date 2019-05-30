package AthleteQASession;

import java.sql.Time;

public class Session {
    private final int sessionId;
    private final int hostName;
    private final Time startTime;
    private final Time endTime;
    public Session(int sessionId, int hostName, Time startTime, Time endTime) {
        this.sessionId = sessionId;
        this.hostName = hostName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
