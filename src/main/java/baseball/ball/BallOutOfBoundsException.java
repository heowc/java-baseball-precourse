package baseball.ball;

public final class BallOutOfBoundsException extends RuntimeException {

    public BallOutOfBoundsException(int value) {
        super(String.format("볼은 " + Ball.MIN_VALUE + " ~ " + Ball.MAX_VALUE + " 사이의 값으로만 생성할 수 있습니다." +
                            " (입력 값: %s)", value));
    }
}
