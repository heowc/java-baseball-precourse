package baseball.ball;

import java.util.*;

import static java.util.Objects.requireNonNull;

public final class Balls {

    public static final int SIZE = 3;

    private final List<Ball> value;

    private Balls(List<Ball> value) {
        this.value = Collections.unmodifiableList(value);
    }

    public static Balls of(Ball... value) {
        requireNonNull(value, "value");
        return of(Arrays.asList(value));
    }
    public static Balls of(List<Ball> value) {
        requireNonNull(value, "value");
        return new Balls(validate(value));
    }

    private static List<Ball> validate(List<Ball> value) {
        if (value.size() != SIZE) {
            throw new InvalidBallSizeException(value);
        }
        final Set<Ball> uniqueBalls = new HashSet<>(value);
        if (uniqueBalls.size() != SIZE) {
            throw new DuplicatedBallException(value);
        }
        return value;
    }

    public Ball get(int index) {
        return value.get(index);
    }

    public int indexOf(Ball ball) {
        return value.indexOf(ball);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Balls other = (Balls) o;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
