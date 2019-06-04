package AthleteQASession.DatabaseControllers;

import java.io.*;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;

abstract public class DatabaseController {
    protected Connection connection;
    private static String user;
    private static String password;
    private static int portNumber;


    public DatabaseController() {
        try {
            getAuthenticationInfo();
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:" + this.portNumber + " /qasession?serverTimezone=UTC", this.user, this.password);
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
    public void getAuthenticationInfo() {
        try {
            BufferedReader authFileReader = new BufferedReader(new FileReader("src/main/resources/auth.txt"));
            String line = authFileReader.readLine();
            while(line != null) {
                String field = line.split("=")[0];
                String value = line.split("=")[1];
                switch(field) {
                    case "user":
                        this.user = value;
                        break;
                    case "password":
                        this.password = value;
                        break;
                    case "port":
                        this.portNumber = Integer.parseInt(value);
                    default:
                        break;
                }
                line = authFileReader.readLine();
            }
            authFileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
