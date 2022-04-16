package baseball.referee;

import baseball.ball.Ball;
import baseball.ball.Balls;

enum StrikeRule implements Rule {

    INSTANCE;

    @Override
    public Judgment doJudge(Balls balls, Balls other, int index, JudgmentRuleChain chain) {
        final Ball ball = balls.get(index);
        final Ball otherBall = other.get(index);
        if (ball.equals(otherBall)) {
            return Judgment.STRIKE;
        }

        return chain.judge(balls, other, index);
    }
}
