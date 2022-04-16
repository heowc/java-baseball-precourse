package baseball.ball;

import java.util.Objects;

public final class Ball {

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 9;

    private final int value;

    private Ball(int value) {
        this.value = value;
    }

    public static Ball of(int value) {
        return new Ball(validate(value));
    }

    private static int validate(int value) {
        if (MIN_VALUE > value || value > MAX_VALUE) {
            throw new BallOutOfBoundsException(value);
        }
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Ball other = (Ball) o;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
