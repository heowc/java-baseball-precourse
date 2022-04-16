package baseball.ball;

import java.util.List;

import static java.util.Objects.requireNonNull;

public final class InvalidBallSizeException extends RuntimeException {

    public InvalidBallSizeException(List<Ball> balls) {
        super(message(requireNonNull(balls, "balls")));
    }

    private static String message(List<Ball> balls) {
        return String.format("볼의 갯수는 " + Balls.SIZE + "개만 허용됩니다. (입력 값: %s, %d개)",
                             balls, balls.size());
    }
}
