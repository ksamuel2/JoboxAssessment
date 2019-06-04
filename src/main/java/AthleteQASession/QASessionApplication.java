package AthleteQASession;

import AthleteQASession.DatabaseControllers.QuestionDatabaseController;
import AthleteQASession.DatabaseControllers.SessionDatabaseController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QASessionApplication {

    public static void main(String[] args) {
        String username = "root";
        String password = "spr1ng01";
        int port_number = 3306;
        QuestionDatabaseController questionDbCon =
                new QuestionDatabaseController(username, password, port_number);
        questionDbCon.initializeTables();
        SessionDatabaseController sessionDbCon =
                new SessionDatabaseController(username, password, port_number);
        sessionDbCon.initializeTables();
        SpringApplication.run(QASessionApplication.class, args);

    }
}
