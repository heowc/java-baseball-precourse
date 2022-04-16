package baseball.referee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("판정 보고서에 대해 테스트한다.")
class JudgmentResultTest {

    @DisplayName("1개 판정에 대한 개수를 증가시킬 수 있다.")
    @Test
    void add() {
        final JudgmentResult result = JudgmentResult.create();
        for (Judgment judgment : Judgment.values()) {
            result.add(judgment);
        }
        assertThat(result.count(Judgment.STRIKE)).isEqualTo(1);
        assertThat(result.count(Judgment.BALL)).isEqualTo(1);
        assertThat(result.count(Judgment.MISS)).isEqualTo(1);
    }

    @ParameterizedTest(name = "완료 상태 여부를 확인할 수 있다. judgments={0}, isCompleted={1}")
    @MethodSource("provideIsCompletedMethodArguments")
    void isCompleted(List<Judgment> judgments, boolean isCompleted) {
        final JudgmentResult result = JudgmentResult.create();
        for (Judgment judgment : judgments) {
            result.add(judgment);
        }
        assertThat(result.isCompleted()).isEqualTo(isCompleted);
    }

    private static Stream<? extends Arguments> provideIsCompletedMethodArguments() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(Judgment.STRIKE, Judgment.STRIKE, Judgment.STRIKE),
                        true
                ),
                Arguments.of(
                        Arrays.asList(Judgment.STRIKE, Judgment.STRIKE, Judgment.BALL),
                        false
                ),
                Arguments.of(
                        Arrays.asList(Judgment.STRIKE, Judgment.STRIKE, Judgment.MISS),
                        false
                )
        );
    }
}
