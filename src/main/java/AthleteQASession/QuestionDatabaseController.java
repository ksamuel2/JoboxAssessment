package AthleteQASession;

import java.sql.*;

public class QuestionDatabaseController extends DatabaseController {

    QuestionDatabaseController(String user, String password, int portNumber) {
        super(user, password, portNumber);
    }

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
            System.out.println("Successfully Initialized Questions Table: " + returnCode);

        }
        catch (Exception e) {
            System.out.println("Exception caught initializing table");
            System.out.println(e);
        }
    }

    /*public void add(String greeting) {
        try {

            Statement stmt = this.connection.createStatement();
            int returnCode = stmt.executeUpdate("INSERT INTO greetings (greeting) VALUES (\"" + greeting + "\");");
            System.out.println("Successfully Added Greeting \"" + greeting + "\" To Database: " + returnCode);
        }
        catch (Exception e) {
            System.out.println("Exception caught adding greeting to table");
            System.out.println(e);
        }
    }*/
}
