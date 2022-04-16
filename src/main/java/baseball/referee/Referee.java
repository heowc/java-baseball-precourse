package baseball.referee;

import baseball.ball.Balls;

import java.util.Arrays;

@FunctionalInterface
public interface Referee {

    static Referee of() {
        return new DefaultReferee(Arrays.asList(Rule.ofStrike(),
                                                Rule.ofBall(),
                                                Rule.ofNothing()));
    }

    JudgmentResult judge(Balls balls, Balls otherBalls);
}
