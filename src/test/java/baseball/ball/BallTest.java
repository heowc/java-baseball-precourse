package baseball.ball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("볼에 대해 테스트한다.")
class BallTest {

    @ParameterizedTest(name = Ball.MIN_VALUE + " ~ " + Ball.MAX_VALUE + " 사이의 숫자로 생성할 수 있다. value={0}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void successfulCreate(int value) {
        final Ball ball = Ball.of(value);
        assertThat(ball).isNotNull();
    }

    @ParameterizedTest(name = Ball.MIN_VALUE + " ~ " + Ball.MAX_VALUE + " 사이 이외의 숫자로 생성할 수 없다. value={0}")
    @ValueSource(ints = {Ball.MIN_VALUE - 1, Ball.MAX_VALUE + 1})
    void failureCreate(int value) {
        assertThatThrownBy(() -> {
            Ball.of(value);
        }).isInstanceOf(BallOutOfBoundsException.class);
    }

    @ParameterizedTest(name = "같은 볼은 동등성을 보장한다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void deepEquals(int value) {
        final Ball ball = Ball.of(value);
        final Ball sameBall = Ball.of(value);

        assertThat(ball).isEqualTo(sameBall);
    }
}
