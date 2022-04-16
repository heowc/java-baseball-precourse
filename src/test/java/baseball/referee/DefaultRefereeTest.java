package baseball.referee;

import baseball.ball.Ball;
import baseball.ball.Balls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("심판에 대해 테스트한다.")
class DefaultRefereeTest {

    private final List<Rule> rules = Arrays.asList(Rule.ofStrike(), Rule.ofBall(), Rule.ofNothing());

    @ParameterizedTest(name = "볼들을 비교한 결과를 가져올 수 있다. balls={0}, otherBalls={1}, isCompleted={2}")
    @MethodSource("provideJudgeMethodArguments")
    void judge(Balls balls, Balls otherBalls, boolean isCompleted) {
        final Referee referee = new DefaultReferee(rules);
        final JudgmentResult result = referee.judge(balls, otherBalls);
        assertThat(result.isCompleted()).isEqualTo(isCompleted);
    }

    private static Stream<? extends Arguments> provideJudgeMethodArguments() {
        return Stream.of(
                Arguments.of(
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        true
                ),
                Arguments.of(
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        Balls.of(Ball.of(3), Ball.of(1), Ball.of(2)),
                        false
                ),
                Arguments.of(
                        Balls.of(Ball.of(4), Ball.of(5), Ball.of(6)),
                        Balls.of(Ball.of(7), Ball.of(8), Ball.of(9)),
                        false
                )
        );
    }
}
