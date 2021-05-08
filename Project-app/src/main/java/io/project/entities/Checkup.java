package io.project.entities;

public class Checkup {
    private int id;
    private String question;
    private String answer;
    private Violation violation;

    public Checkup() {
    }

    public Checkup(int id, String question, String answer, Violation violation) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.violation = violation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Violation getViolation() {
        return violation;
    }

    public void setViolation(Violation violation) {
        this.violation = violation;
    }
}
