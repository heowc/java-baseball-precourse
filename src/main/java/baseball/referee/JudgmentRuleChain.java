package baseball.referee;

import baseball.ball.Balls;

import java.util.List;

@FunctionalInterface
interface JudgmentRuleChain {

    static JudgmentRuleChain of(List<Rule> ruleList) {
        return new DefaultJudgmentRuleChain(ruleList);
    }

    Judgment judge(Balls balls, Balls other, int index);
}
