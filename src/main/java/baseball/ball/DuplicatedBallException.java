package baseball.ball;

import java.util.List;

import static java.util.Objects.requireNonNull;

public final class DuplicatedBallException extends RuntimeException {

    public DuplicatedBallException(List<Ball> balls) {
        super(String.format("중복되는 볼이 존재합니다. (입력 값: %s)", requireNonNull(balls, "balls")));
    }
}
