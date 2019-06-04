package AthleteQASession.RestControllers;

import AthleteQASession.DatabaseControllers.SessionDatabaseController;
import AthleteQASession.Models.Question;
import AthleteQASession.Models.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class SessionController {

    private SessionDatabaseController sessionDbCon =
            new SessionDatabaseController();

    @PostMapping(path = "/qa")
    public ResponseEntity<?> createSession(@RequestBody Map<String, String> body) {
        String hostName = body.get("host_name");
        String startTime = body.get("start_date");
        String endTime = body.get("end_date");
        System.out.println(String.format("Creating session with params: %s, %s, %s",
                hostName, startTime, endTime));
        return sessionDbCon.createSession(hostName, startTime, endTime);
    }
    @GetMapping(path = "/qa/{qa_id}")
    @ResponseBody
    public ResponseEntity<Session> getSession(@PathVariable("qa_id") int qaSessionId) {
        System.out.println(String.format("Getting session with params: %x",
                qaSessionId));

        return sessionDbCon.selectSessionFromTable(qaSessionId);
    }
    @GetMapping(path = "/qa/{qa_id}/questions")
    public ResponseEntity<ArrayList<Question>> getSessionQuestions(
            @PathVariable("qa_id") int qaSessionId,
            @RequestParam(name = "only_answered", defaultValue = "false") boolean showAnsweredQuestions) {

        System.out.println(String.format("Getting session questions with params: %x, %b",
                qaSessionId, showAnsweredQuestions));
        return sessionDbCon.selectQuestionsFromSession(qaSessionId, showAnsweredQuestions);
    }
}
