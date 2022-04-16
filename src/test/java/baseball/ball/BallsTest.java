package baseball.ball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("볼들에 대해 테스트한다.")
class BallsTest {

    @ParameterizedTest(name = "중복되지 않은 3자리의 숫자로 생성한다. ballList={0}")
    @MethodSource("provideCreateMethodArguments")
    void create(List<Ball> ballList) {
        final Balls balls = Balls.of(ballList);
        assertThat(balls).isNotNull();
    }

    private static Stream<? extends Arguments> provideCreateMethodArguments() {
        return Stream.of(
                Arguments.of(Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3))),
                Arguments.of(Arrays.asList(Ball.of(4), Ball.of(5), Ball.of(6))),
                Arguments.of(Arrays.asList(Ball.of(7), Ball.of(8), Ball.of(9)))
        );
    }

    @ParameterizedTest(name = "3자리가 아니면 생성할 수 없다. ballList={0}")
    @MethodSource("provideCreateInvalidSizeMethodArguments")
    void createInvalidSize(List<Ball> ballList) {
        assertThatThrownBy(() -> {
            Balls.of(ballList);
        }).isInstanceOf(InvalidBallSizeException.class);
    }

    private static Stream<? extends Arguments> provideCreateInvalidSizeMethodArguments() {
        return Stream.of(
                Arguments.of(Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4))),
                Arguments.of(Arrays.asList(Ball.of(5), Ball.of(6))),
                Arguments.of(Collections.emptyList())
        );
    }

    @ParameterizedTest(name = "동일한 볼은 가질 수 없다. ballList={0}")
    @MethodSource("provideCreateDuplicatedBallMethodArguments")
    void createDuplicatedBall(List<Ball> ballList) {
        assertThrows(DuplicatedBallException.class, () -> Balls.of(ballList));
    }

    private static Stream<? extends Arguments> provideCreateDuplicatedBallMethodArguments() {
        return Stream.of(
                Arguments.of(Arrays.asList(Ball.of(1), Ball.of(1), Ball.of(4))),
                Arguments.of(Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(2))),
                Arguments.of(Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(1)))
        );
    }

    @ParameterizedTest(name = "특정 위치의 값을 포함하는지 확인한다. balls={0}, otherBalls={1}")
    @MethodSource("provideIndexOfMethodArguments")
    void indexOf(Balls balls, Balls otherBalls, boolean expected) {
        for (int i = 0; i < Balls.SIZE; i++) {
            final Ball ball = otherBalls.get(i);
            final boolean contains = balls.indexOf(ball) != -1;

            assertThat(contains).isEqualTo(expected);
        }
    }

    private static Stream<? extends Arguments> provideIndexOfMethodArguments() {
        return Stream.of(
                Arguments.of(
                        Balls.of(Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3))),
                        Balls.of(Arrays.asList(Ball.of(2), Ball.of(3), Ball.of(1))),
                        true
                ),
                Arguments.of(
                        Balls.of(Arrays.asList(Ball.of(4), Ball.of(6), Ball.of(8))),
                        Balls.of(Arrays.asList(Ball.of(6), Ball.of(8), Ball.of(4))),
                        true
                ),
                Arguments.of(
                        Balls.of(Arrays.asList(Ball.of(3), Ball.of(2), Ball.of(1))),
                        Balls.of(Arrays.asList(Ball.of(2), Ball.of(1), Ball.of(3))),
                        true
                ),
                Arguments.of(
                        Balls.of(Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3))),
                        Balls.of(Arrays.asList(Ball.of(4), Ball.of(5), Ball.of(6))),
                        false
                ),
                Arguments.of(
                        Balls.of(Arrays.asList(Ball.of(4), Ball.of(5), Ball.of(6))),
                        Balls.of(Arrays.asList(Ball.of(7), Ball.of(8), Ball.of(9))),
                        false
                ),
                Arguments.of(
                        Balls.of(Arrays.asList(Ball.of(2), Ball.of(3), Ball.of(4))),
                        Balls.of(Arrays.asList(Ball.of(9), Ball.of(8), Ball.of(6))),
                        false
                )
        );
    }

    @ParameterizedTest(name = "특정 위치의 값을 조회한다. ballList={0}")
    @MethodSource("provideGetMethodArguments")
    void get(List<Ball> ballList) {
        final Balls balls = Balls.of(ballList);

        for (int i = 0; i < Balls.SIZE; i++) {
            final Ball ball = balls.get(i);
            final Ball expect = ballList.get(i);

            assertThat(ball).isEqualTo(expect);
        }
    }

    private static Stream<? extends Arguments> provideGetMethodArguments() {
        return Stream.of(
                Arguments.of(Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3))),
                Arguments.of(Arrays.asList(Ball.of(2), Ball.of(3), Ball.of(4))),
                Arguments.of(Arrays.asList(Ball.of(5), Ball.of(6), Ball.of(7))),
                Arguments.of(Arrays.asList(Ball.of(1), Ball.of(6), Ball.of(9))),
                Arguments.of(Arrays.asList(Ball.of(8), Ball.of(5), Ball.of(2)))
        );
    }

    @ParameterizedTest(name = "같은 볼들은 동등성을 보장한다. ballList={0}")
    @MethodSource("provideDeepEqualsMethodArguments")
    void deepEquals(List<Ball> ballList) {
        final Balls balls = Balls.of(ballList);
        final Balls sameBalls = Balls.of(ballList);

        assertThat(balls).isEqualTo(sameBalls);
    }

    private static Stream<? extends Arguments> provideDeepEqualsMethodArguments() {
        return Stream.of(
                Arguments.of(Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3))),
                Arguments.of(Arrays.asList(Ball.of(2), Ball.of(3), Ball.of(4))),
                Arguments.of(Arrays.asList(Ball.of(5), Ball.of(6), Ball.of(7))),
                Arguments.of(Arrays.asList(Ball.of(1), Ball.of(6), Ball.of(9))),
                Arguments.of(Arrays.asList(Ball.of(8), Ball.of(5), Ball.of(2)))
        );
    }
}
