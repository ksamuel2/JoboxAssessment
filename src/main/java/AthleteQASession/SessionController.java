package AthleteQASession;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class SessionController {
    //TODO Ensure that these fields are customizable based off a configuration file
    private String username = "root";
    private String password = "spr1ng01";
    private int port_number = 3306;
    private SessionDatabaseController sessionDbCon =
            new SessionDatabaseController(username, password, port_number);

    @PostMapping(path = "/qa")
    public int createSession(@RequestBody Map<String, String> body) {
        String hostName = body.get("host_name");
        String startTime = body.get("start_date");
        String endTime = body.get("end_date");
        System.out.println(String.format("Creating session with params: %s, %s, %s",
                hostName, startTime, endTime));
        sessionDbCon.createSession(hostName, startTime, endTime);
        return 0;
    }
    @GetMapping(path = "/qa/{qa_id}")
    @ResponseBody
    public Session getSession(@PathVariable("qa_id") int qaSessionId) {
        System.out.println(String.format("Getting session with params: %x",
                qaSessionId));
        return sessionDbCon.selectSessionFromTable(qaSessionId);
    }
    @GetMapping(path = "/qa/{qa_id}/questions")
    public ArrayList<Question> getSessionQuestions(@PathVariable("qa_id") int qaSessionId,
                                                   @RequestParam(name = "only_answered", defaultValue = "false") boolean showAnsweredQuestions) {
        System.out.println(String.format("Getting session questions with params: %x, %b",
                qaSessionId, showAnsweredQuestions));
        return sessionDbCon.selectQuestionsFromSession(qaSessionId, showAnsweredQuestions);
    }
}
