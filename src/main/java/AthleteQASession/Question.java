package AthleteQASession;

public class Question {
    private final int questionId;
    private final int sessionId;
    private final String question;
    private final String askedByUsername;
    private final String answer;
    public Question(int questionId, int sessionId, String question, String askedByUsername, String answer) {
        this.questionId = questionId;
        this.sessionId = sessionId;
        this.question = question;
        this.askedByUsername = askedByUsername;
        this.answer = answer;
    }
}
