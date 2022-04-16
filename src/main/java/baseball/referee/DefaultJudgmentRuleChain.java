package baseball.referee;

import baseball.ball.Balls;

import java.util.List;

final class DefaultJudgmentRuleChain implements JudgmentRuleChain {

    private final List<Rule> rules;
    private int position;

    DefaultJudgmentRuleChain(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public Judgment judge(Balls balls, Balls other, int position) {
        if (this.position < rules.size()) {
            return rules.get(this.position++).doJudge(balls, other, position, this);
        }
        return Judgment.MISS;
    }
}
