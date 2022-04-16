package baseball.referee;

public enum Judgment {

    STRIKE("스트라이크"),
    BALL("볼"),
    MISS("낫싱");

    private final String content;

    Judgment(String content) {
        this.content = content;
    }

    public String content() {
        return content;
    }
}
