package AthleteQASession;

import java.sql.*;
import java.util.ArrayList;

public class SessionDatabaseController extends DatabaseController {

    SessionDatabaseController(String user, String password, int portNumber) {
        super(user, password, portNumber);
    }

    @Override
    public void initializeTables() {
        try {
            Statement stmt = this.connection.createStatement();
            int returnCode = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS sessions (" +
                    "sessionId INT AUTO_INCREMENT PRIMARY KEY," +
                    "hostName VARCHAR(255)," +
                    "startTime DATETIME," +
                    "endTime DATETIME);");
            System.out.println("Successfully initialized sessions table: " + returnCode);

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
            System.out.println("Successfully added session to database: " + returnCode);
        }
        catch (Exception e) {
            System.out.println("Exception caught adding session to database");
            System.out.println(e);
        }
    }

    public Session selectSessionFromTable(int sessionId) {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM sessions WHERE sessionId = " + sessionId + ";");

            result.next();
            Session resultSession = new Session(
                    result.getInt(SESSION_ID_INDEX),
                    result.getString(HOST_NAME_INDEX),
                    result.getTime(START_TIME_INDEX),
                    result.getTime(END_TIME_INDEX));
            System.out.println("Successfully selected session[" + sessionId + "] from database");
            return resultSession;
        }
        catch (Exception e) {
            System.out.println("Exception caught retrieving session[" + sessionId + "] from database");
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<Question> selectQuestionsFromSession(int sessionId, boolean showAnsweredQuestions) {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet result;
            if (showAnsweredQuestions) {
                result = stmt.executeQuery("SELECT * FROM questions WHERE sessionId = " + sessionId + " AND answer IS NOT NULL;");
            } else {
                result = stmt.executeQuery("SELECT * FROM questions WHERE sessionId = " + sessionId + ";");
            }
            ArrayList<Question> sessionQuestions = new ArrayList<Question>();
            while (result.next()) {
                Question currQuestion = new Question(
                        result.getInt(QuestionDatabaseController.QUESTION_ID_INDEX),
                        result.getInt(QuestionDatabaseController.SESSION_ID_INDEX),
                        result.getString(QuestionDatabaseController.QUESTION_INDEX),
                        result.getString(QuestionDatabaseController.ASKED_BY_USERNAME_INDEX),
                        result.getString(QuestionDatabaseController.ANSWER_INDEX),
                        result.getString(QuestionDatabaseController.ANSWERED_BY_USERNAME_INDEX));
                sessionQuestions.add(currQuestion);
            }
            System.out.println("Successfully selected questions for session[" + sessionId + "] from database");
            return sessionQuestions;
        } catch (Exception e) {
            System.out.println("Exception caught retrieving quesetions for session[" + sessionId + "] from database");
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<Session> selectAllSessions() {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM sessions;");
            ArrayList<Session> resultSessions = new ArrayList<Session>();
            while(result.next()) {

                Session currSession = new Session(
                        result.getInt(SESSION_ID_INDEX),
                        result.getString(HOST_NAME_INDEX),
                        result.getTime(START_TIME_INDEX),
                        result.getTime(END_TIME_INDEX));
                resultSessions.add(currSession);
            }
            System.out.println("Successfully selected all sessions from database");
            return resultSessions;
        } catch (Exception e) {
            System.out.println("Exception caught retrieving all sessions from database");
            System.out.println(e);
            return null;
        }
    }

    public static final int SESSION_ID_INDEX = 1;
    public static final int HOST_NAME_INDEX = 2;
    public static final int START_TIME_INDEX = 3;
    public static final int END_TIME_INDEX = 4;


}
