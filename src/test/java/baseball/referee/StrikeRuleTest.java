package baseball.referee;

import baseball.ball.Ball;
import baseball.ball.Balls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("스트라이크 판정에 대해 테스트한다.")
class StrikeRuleTest {

    private final JudgmentRuleChain stubRuleChain = (my, other, index) -> Judgment.MISS;
    private final Rule strikeRule = StrikeRule.INSTANCE;

    @ParameterizedTest(name = "동일한 위치에 동일한 볼이 존재할 경우 스트라이크로 판정한다. balls={0}")
    @MethodSource("provideStrikeMethodArguments")
    void strike(Balls balls) {
        for (int i = 0; i < Balls.SIZE; i++) {
            final Judgment judgment = strikeRule.doJudge(balls, balls, i, stubRuleChain);
            assertThat(judgment).isEqualTo(Judgment.STRIKE);
        }
    }

    private static Stream<? extends Arguments> provideStrikeMethodArguments() {
        return Stream.of(
                Arguments.of(Balls.of(Ball.of(1), Ball.of(2), Ball.of(3))),
                Arguments.of(Balls.of(Ball.of(5), Ball.of(6), Ball.of(7))),
                Arguments.of(Balls.of(Ball.of(8), Ball.of(5), Ball.of(2)))
        );
    }

    @ParameterizedTest(name = "동일한 위치에 동일한 볼이 존재하지 않을 경우 스트라이크로 판정하지 않는다. balls={0}, " +
                              "otherBalls={1}")
    @MethodSource("provideNotStrikeMethodArguments")
    void notStrike(Balls balls, Balls otherBalls) {
        for (int i = 0; i < Balls.SIZE; i++) {
            final Judgment judgment = strikeRule.doJudge(balls, otherBalls, i, (my, other, index) -> Judgment.MISS);
            assertThat(judgment).isNotEqualTo(Judgment.STRIKE);
        }
    }

    private static Stream<? extends Arguments> provideNotStrikeMethodArguments() {
        return Stream.of(
                Arguments.of(
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)),
                        Balls.of(Ball.of(4), Ball.of(5), Ball.of(6))
                ),
                Arguments.of(
                        Balls.of(Ball.of(4), Ball.of(5), Ball.of(6)),
                        Balls.of(Ball.of(7), Ball.of(8), Ball.of(9))
                ),
                Arguments.of(
                        Balls.of(Ball.of(1), Ball.of(2), Ball.of(4)),
                        Balls.of(Ball.of(7), Ball.of(8), Ball.of(9))
                )
        );
    }
}
