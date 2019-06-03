package AthleteQASession;

import java.sql.*;

public class QuestionDatabaseController extends DatabaseController {

    QuestionDatabaseController(String user, String password, int portNumber) {
        super(user, password, portNumber);
    }

    @Override
    public void initializeTables() {
        try {
            Statement stmt = this.connection.createStatement();
            int returnCode = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS questions (" +
                    "questionId INT AUTO_INCREMENT PRIMARY KEY," +
                    "sessionId INT," +
                    "question VARCHAR(1024)," +
                    "askedByUsername VARCHAR(20)," +
                    "answer VARCHAR(1024)," +
                    "answeredByUsername VARCHAR(1024));");
            System.out.println("Successfully initialized questions table: " + returnCode);

        }
        catch (Exception e) {
            System.out.println("Exception caught initializing table");
            System.out.println(e);
        }
    }

    public void createQuestion(int sessionId, String question, String askedByUsername) {
        try {
            Statement stmt = this.connection.createStatement();
            // Inserts question into questions table only if the sessionId provided is for a currently live session
            int rowsUpdated = stmt.executeUpdate(String.format("INSERT INTO questions (sessionId, question, askedByUsername) " +
                    "SELECT %x, \"%s\", \"%s\" " +
                    "WHERE EXISTS (SELECT * FROM sessions WHERE sessionId = %x AND startTime < now() AND endTime > now());"
                    , sessionId, question, askedByUsername, sessionId));
            if(rowsUpdated == 0) {
                System.out.println("Failed to add question to database");
                return;
            }
            System.out.println("Successfully added question to database");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void answerQuestion(int questionId, String answer, String answeredByUsername) {
        try {
            Statement stmt = this.connection.createStatement();
            // Updates a question entry with an answer if and only if it does not already contain an answer
            int rowsUpdated = stmt.executeUpdate(String.format("UPDATE questions " +
                    "SET answer = \"%s\", answeredByUsername = \"%s\" " +
                    "WHERE (questionId = %x AND answer IS NULL);",
                    answer, answeredByUsername, questionId));
            if(rowsUpdated == 0) {
                System.out.println("Failed to add answer to database");
                return;
            }
            System.out.println("Successfully added question to database");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final int QUESTION_ID_INDEX = 1;
    public static final int SESSION_ID_INDEX = 2;
    public static final int QUESTION_INDEX = 3;
    public static final int ASKED_BY_USERNAME_INDEX = 4;
    public static final int ANSWER_INDEX = 5;
    public static final int ANSWERED_BY_USERNAME_INDEX = 6;

}
