package baseball.referee;

import baseball.ball.Balls;

@FunctionalInterface
public interface Rule {

    static Rule ofStrike() {
        return StrikeRule.INSTANCE;
    }

    static Rule ofBall() {
        return BallRule.INSTANCE;
    }

    static Rule ofNothing() {
        return NothingRule.INSTANCE;
    }

    Judgment doJudge(Balls balls, Balls other, int position, JudgmentRuleChain chain);
}
