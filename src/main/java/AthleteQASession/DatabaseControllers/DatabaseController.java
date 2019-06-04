package AthleteQASession.DatabaseControllers;

import java.sql.Connection;
import java.sql.DriverManager;

abstract public class DatabaseController {
    protected Connection connection;

    public DatabaseController(String user, String password, int portNumber) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:" + portNumber + " /qasession?serverTimezone=UTC", user, password);
        } catch (Exception e) {
            System.out.println("Exception caught initializing database connection");
            System.out.println(e);
        }

    }

    abstract void initializeTables();
    void endConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("Exception caught closing database connection");
            System.out.println(e);
        }
    }
}
