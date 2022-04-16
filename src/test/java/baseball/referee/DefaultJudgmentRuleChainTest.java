package baseball.referee;

import baseball.ball.Ball;
import baseball.ball.Balls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("판정 체인에 대해 테스트한다.")
class DefaultJudgmentRuleChainTest {

    private final List<Rule> withStrike = Collections.singletonList(Rule.ofStrike());
    private final List<Rule> withBall = Collections.singletonList(Rule.ofBall());
    private final List<Rule> all = Arrays.asList(Rule.ofStrike(), Rule.ofBall());

    @ParameterizedTest(name = "스트라이크를 판정할 수 있다. (그 외 미스) balls={0}, otherBalls={1}, expected={2}")
    @MethodSource("provideStrikeMethodArguments")
    void strike(Balls balls, Balls otherBalls, Judgment expected) {
        for (int i = 0; i < Balls.SIZE; i++) {
            final JudgmentRuleChain ruleChain = JudgmentRuleChain.of(withStrike);
            final Judgment judgment = ruleChain.judge(balls, otherBalls, i);
            assertThat(judgment).isEqualTo(expected);
        }
    }

    private static Stream<? extends Arguments> provideStrikeMethodArguments() {
        return Stream.of(
                Arguments.of(
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        Judgment.STRIKE
                ),
                Arguments.of(
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        Balls.of(Ball.of(3), Ball.of(1), Ball.of(2)),
                        Judgment.MISS
                ),
                Arguments.of(
                        Balls.of(Ball.of(4), Ball.of(5), Ball.of(6)),
                        Balls.of(Ball.of(7), Ball.of(8), Ball.of(9)),
                        Judgment.MISS
                )
        );
    }

    @ParameterizedTest(name = "볼를 판정할 수 있다. (그 외 미스) balls={0}, otherBalls={1}, expected={2}")
    @MethodSource("provideBallMethodArguments")
    void ball(Balls balls, Balls otherBalls, Judgment expected) {
        for (int i = 0; i < Balls.SIZE; i++) {
            final JudgmentRuleChain ruleChain = JudgmentRuleChain.of(withBall);
            final Judgment judgment = ruleChain.judge(balls, otherBalls, i);
            assertThat(judgment).isEqualTo(expected);
        }
    }

    private static Stream<? extends Arguments> provideBallMethodArguments() {
        return Stream.of(
                Arguments.of(
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        Judgment.MISS
                ),
                Arguments.of(
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        Balls.of(Ball.of(3), Ball.of(1), Ball.of(2)),
                        Judgment.BALL
                ),
                Arguments.of(
                        Balls.of(Ball.of(4), Ball.of(5), Ball.of(6)),
                        Balls.of(Ball.of(7), Ball.of(8), Ball.of(9)),
                        Judgment.MISS
                )
        );
    }

    @ParameterizedTest(name = "스트라이크와 볼을 판정할 수 있다. (그 외 미스) balls={0}, otherBalls={1}, expected={2}")
    @MethodSource("provideStrikeAndBallMethodArguments")
    void strikeAndBall(Balls balls, Balls otherBalls, Judgment expected) {
        for (int i = 0; i < Balls.SIZE; i++) {
            final JudgmentRuleChain ruleChain = JudgmentRuleChain.of(all);
            final Judgment judgment = ruleChain.judge(balls, otherBalls, i);
            assertThat(judgment).isEqualTo(expected);
        }
    }

    private static Stream<? extends Arguments> provideStrikeAndBallMethodArguments() {
        return Stream.of(
                Arguments.of(
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        Judgment.STRIKE
                ),
                Arguments.of(
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        Balls.of(Ball.of(3), Ball.of(1), Ball.of(2)),
                        Judgment.BALL
                ),
                Arguments.of(
                        Balls.of(Ball.of(4), Ball.of(5), Ball.of(6)),
                        Balls.of(Ball.of(7), Ball.of(8), Ball.of(9)),
                        Judgment.MISS
                )
        );
    }
}
