package AthleteQASession.DatabaseControllers;

import AthleteQASession.DatabaseControllers.DatabaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.*;

public class QuestionDatabaseController extends DatabaseController {

    public QuestionDatabaseController() {
        super();
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
                    "imageUrl VARCHAR(128)," +
                    "answeredByUsername VARCHAR(1024));");
            System.out.println("Successfully initialized questions table: " + returnCode);

        }
        catch (Exception e) {
            System.out.println("Exception caught initializing table");
            System.out.println(e);
        }
    }

    public ResponseEntity<?> createQuestion(int sessionId, String question, String askedByUsername) {
        try {
            Statement stmt = this.connection.createStatement();
            // Inserts question into questions table only if the sessionId provided is for a currently live session
            int rowsUpdated = stmt.executeUpdate(String.format("INSERT INTO questions (sessionId, question, askedByUsername) " +
                    "SELECT %x, \"%s\", \"%s\" " +
                    "WHERE EXISTS (SELECT * FROM sessions WHERE sessionId = %x AND startTime < now() AND endTime > now());"
                    , sessionId, question, askedByUsername, sessionId));
            if(rowsUpdated == 0) {
                System.out.println("Failed to add question to database");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            System.out.println("Successfully added question to database");
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Exception caught adding question to database");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<?> answerQuestion(int questionId, String answer, String imageUrl, String answeredByUsername) {
        try {
            if(answer == null && imageUrl == null) {
                System.out.println("Failed to add answer to database either answer or image url must be provided");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Statement stmt = this.connection.createStatement();
            // Updates a question entry with an answer if and only if it does not already contain an answer
            int rowsUpdated = stmt.executeUpdate(String.format("UPDATE questions " +
                    "SET answer = \"%s\", imageUrl = \"%s\", answeredByUsername = \"%s\" " +
                    "WHERE (questionId = %x AND answer IS NULL);",
                    answer, imageUrl, answeredByUsername, questionId));
            if(rowsUpdated == 0) {
                System.out.println("Failed to add answer to database");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            System.out.println("Successfully added question to database");
            return new ResponseEntity<>(HttpStatus.OK);


        } catch (Exception e) {
            System.out.println("Exception caught adding answer to question table");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static final int QUESTION_ID_INDEX = 1;
    public static final int SESSION_ID_INDEX = 2;
    public static final int QUESTION_INDEX = 3;
    public static final int ASKED_BY_USERNAME_INDEX = 4;
    public static final int ANSWER_INDEX = 5;
    public static final int IMAGE_URL_INDEX = 6;
    public static final int ANSWERED_BY_USERNAME_INDEX = 7;

}
