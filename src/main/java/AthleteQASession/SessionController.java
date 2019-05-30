package AthleteQASession;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class SessionController {
    private final AtomicLong sessionIdCounter = new AtomicLong();

    @PostMapping(path = "/qa")
    public int createSession(@RequestBody Map<String, String> body) {
        String hostName = body.get("host_name");
        String startTime = body.get("start_date");
        String endTime = body.get("end_date");
        System.out.println(String.format("Creating session with params: %s, %s, %s",
                hostName, startTime, endTime));
        return 0;
    }
    @GetMapping(path = "/qa/{qa_id}")
    public Session getSession(@PathVariable("qa_id") int qaSessionId) {
        System.out.println(String.format("Getting session with params: %x",
                qaSessionId));
        return null;
    }
    @GetMapping(path = "/qa/{qa_id}/questions")
    public ArrayList<Question> getSessionQuestions(@PathVariable("qa_id") int qaSessionId) {
        System.out.println(String.format("Getting session questions with params: %x",
                qaSessionId));
        return null;
    }
}
