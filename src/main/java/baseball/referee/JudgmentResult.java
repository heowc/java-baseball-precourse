package baseball.referee;

import baseball.ball.Balls;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public final class JudgmentResult {

    private static final int DEFAULT_COUNT = 0;

    private final Map<Judgment, Integer> value;

    private JudgmentResult(final Map<Judgment, Integer> value) {
        this.value = new EnumMap<>(value);
    }

    public static JudgmentResult create() {
        return new JudgmentResult(new EnumMap<>(Judgment.class));
    }

    public void add(final Judgment judgment) {
        value.merge(judgment, 1, Integer::sum);
    }

    public boolean isCompleted() {
        return value.getOrDefault(Judgment.STRIKE, DEFAULT_COUNT).equals(Balls.SIZE);
    }

    public Map<Judgment, Integer> asMap() {
        return Collections.unmodifiableMap(value);
    }

    int count(final Judgment judgment) {
        return value.getOrDefault(judgment, DEFAULT_COUNT);
    }

    @Override
    public String toString() {
        return value.toString();
    }

//    public String prettyHint() {
//        final StringBuilder builder = new StringBuilder();
//        for (Map.Entry<Judgment, Integer> entry : value.entrySet()) {
//            final Judgment judgment = entry.getKey();
//            final Integer count = entry.getValue();
//            builder.append(judgment.hint(count))
//                   .append(DELIMITER);
//        }
//        return builder.toString().trim();
//    }
}
