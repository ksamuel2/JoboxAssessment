package AthleteQASession.Models;

public class Question {
    private final int questionId;
    private final int sessionId;
    private final String question;
    private final String askedByUsername;
    private final String answer;
    private final String imageUrl;
    private final String answeredByUsername;

    public Question(int questionId, int sessionId, String question, String askedByUsername, String answer, String imageUrl, String answeredByUsername) {
        this.questionId = questionId;
        this.sessionId = sessionId;
        this.question = question;
        this.askedByUsername = askedByUsername;
        this.answer = answer;
        this.imageUrl = imageUrl;
        this.answeredByUsername = answeredByUsername;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public String getQuestion() {
        return question;
    }

    public String getAskedByUsername() {
        return askedByUsername;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAnsweredByUsername() {
        return answeredByUsername;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
