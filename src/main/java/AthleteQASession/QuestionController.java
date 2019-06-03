package AthleteQASession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class QuestionController {
    //TODO Ensure that these fields are customizable based off a configuration file
    private String username = "root";
    private String password = "spr1ng01";
    private int port_number = 3306;
    private QuestionDatabaseController questionDbCon =
            new QuestionDatabaseController(username, password, port_number);

    @PostMapping(path = "/question/{qa_id}")
    public int askQuestion(@PathVariable("qa_id") int qaSessionId,
                                @RequestBody Map<String, String> body) {
        String question = body.get("text");
        String askedByName = body.get("asked_by_name");
        System.out.println(String.format("Asking question with params: %x, %s, %s",
                qaSessionId, question, askedByName));
        questionDbCon.createQuestion(qaSessionId, question, askedByName);
        return 0;
    }

    //TODO Make image_url/text optional so either or is required
    @PostMapping(path = "/answer/{question_id}")
    public int answerQuestion(@PathVariable("question_id") int questionId,
                                @RequestBody Map<String, String> body) {
        String answerText = body.get("text");
        String imageUrl = body.get("image_url");
        String answeredByUsername = body.get("answered_by_name");
        System.out.println(String.format("Answering question with params: %x, %s, %s, %s",
                questionId, answerText, imageUrl, answeredByUsername));
        questionDbCon.answerQuestion(questionId, answerText, answeredByUsername);
        return 0;
    }
}
