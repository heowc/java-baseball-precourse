package baseball.player;

import baseball.ball.Balls;

@FunctionalInterface
public interface Player {

    static Player computer() {
        return new Computer();
    }

    static Player systemIn() {
        return new SystemIn();
    }

    Balls balls();
}
