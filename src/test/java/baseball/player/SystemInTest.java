package baseball.player;

import baseball.ball.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("SystemIn에 대해 테스트한다.")
class SystemInTest {

    @DisplayName("성공적으로 SystemIn를 생성한다.")
    @Test
    void successful() {
        final SystemIn systemIn = new SystemIn();
        final byte[] buf = "246".getBytes();
        System.setIn(new ByteArrayInputStream(buf));
        assertThat(systemIn.balls()).isEqualTo(Balls.of(Ball.of(2), Ball.of(4), Ball.of(6)));
    }

    @ParameterizedTest(name = "SystemIn를 생성하는데 실패한다. value={0}, cause={1}")
    @MethodSource("provideBallsArguments")
    void failure(String value, Class<Exception> cause) throws IOException {
        assertThatThrownBy(() -> {
            final SystemIn systemIn = new SystemIn();
            final byte[] buf = value.getBytes();
            System.setIn(new ByteArrayInputStream(buf));
            systemIn.balls();
        }).isInstanceOf(cause);
    }

    private static Stream<? extends Arguments> provideBallsArguments() {
        return Stream.of(
                Arguments.of("012", BallOutOfBoundsException.class),
                Arguments.of("a12", NumberFormatException.class),
                Arguments.of("!23", NumberFormatException.class),
                Arguments.of("223", DuplicatedBallException.class),
                Arguments.of("1234", InvalidBallSizeException.class)
        );
    }
}
