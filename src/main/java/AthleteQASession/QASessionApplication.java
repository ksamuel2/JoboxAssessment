package AthleteQASession;

import AthleteQASession.DatabaseControllers.DatabaseController;
import AthleteQASession.DatabaseControllers.QuestionDatabaseController;
import AthleteQASession.DatabaseControllers.SessionDatabaseController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QASessionApplication {

    public static void main(String[] args) {
        QuestionDatabaseController questionDbCon =
                new QuestionDatabaseController();
        questionDbCon.initializeTables();
        SessionDatabaseController sessionDbCon =
                new SessionDatabaseController();
        sessionDbCon.initializeTables();
        SpringApplication.run(QASessionApplication.class, args);
    }
}
