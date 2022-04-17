package baseball.referee;

import baseball.ball.Balls;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public final class JudgmentResult {

    private static final int DEFAULT_COUNT = 0;

    private final Map<Judgment, Integer> value;

    private JudgmentResult(Map<Judgment, Integer> value) {
        this.value = new EnumMap<>(value);
    }

    public static JudgmentResult create() {
        return new JudgmentResult(new EnumMap<>(Judgment.class));
    }

    public void add(Judgment judgment) {
        value.merge(judgment, 1, Integer::sum);
    }

    public boolean isCompleted() {
        return value.getOrDefault(Judgment.STRIKE, DEFAULT_COUNT).equals(Balls.SIZE);
    }

    public Map<Judgment, Integer> asMap() {
        return Collections.unmodifiableMap(value);
    }

    int count(Judgment judgment) {
        return value.getOrDefault(judgment, DEFAULT_COUNT);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
