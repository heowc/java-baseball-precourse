package baseball.player;

import baseball.ball.Ball;
import baseball.ball.Balls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Computer에 대해 테스트한다.")
class ComputerTest {

    @DisplayName("성공적으로 Computer를 생성한다.")
    @Test
    void successful() {
        assertRandomNumberInRangeTest(
                () -> {
                    final Computer computer = new Computer();
                    assertThat(computer.balls()).isEqualTo(Balls.of(Ball.of(1), Ball.of(2), Ball.of(3)));
                },
                1, 2, 3
        );
    }
}
