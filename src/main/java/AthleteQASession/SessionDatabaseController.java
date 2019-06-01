package AthleteQASession;

import java.sql.*;

public class SessionDatabaseController extends DatabaseController {

    SessionDatabaseController(String user, String password, int portNumber) {
        super(user, password, portNumber);
    }

    public void initializeTables() {
        try {
            Statement stmt = this.connection.createStatement();
            int returnCode = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS sessions (" +
                    "sessionId INT AUTO_INCREMENT PRIMARY KEY," +
                    "hostName VARCHAR(255)," +
                    "startTime DATETIME," +
                    "endTime DATETIME);");
            System.out.println("Successfully Initialized Sessions Table: " + returnCode);

        }
        catch (Exception e) {
            System.out.println("Exception caught initializing table");
            System.out.println(e);
        }
    }

    public void createSession(String hostName, String startTime, String endTime) {
        try {
            Statement stmt = this.connection.createStatement();
            int returnCode = stmt.executeUpdate("INSERT INTO sessions (hostName, startTime, endTime) " +
                    "VALUES (\"" + hostName + "\", \"" + startTime + "\", \"" + endTime + "\");");
            System.out.println("Successfully Added Session To Database: " + returnCode);
        }
        catch (Exception e) {
            System.out.println("Exception caught adding session to database");
            System.out.println(e);
        }
    }
}
