package baseball.referee;

import baseball.ball.Ball;
import baseball.ball.Balls;

enum BallRule implements Rule {

    INSTANCE;

    @Override
    public Judgment doJudge(Balls balls, Balls other, int position, JudgmentRuleChain chain) {
        final Ball ball = other.get(position);
        final int index = balls.indexOf(ball);
        if (index > -1 && index != position) {
            return Judgment.BALL;
        }
        return chain.judge(balls, other, position);
    }
}
