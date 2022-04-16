package baseball.referee;

import baseball.ball.Balls;

enum NothingRule implements Rule {

    INSTANCE;

    @Override
    public Judgment doJudge(Balls balls, Balls other, int position, JudgmentRuleChain chain) {
        return Judgment.MISS;
    }
}
