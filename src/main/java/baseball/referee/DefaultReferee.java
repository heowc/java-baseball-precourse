package baseball.referee;

import baseball.ball.Balls;

import java.util.Collections;
import java.util.List;

final class DefaultReferee implements Referee {

    private final List<Rule> rules;

    DefaultReferee(List<Rule> rules) {
        this.rules = Collections.unmodifiableList(rules);
    }

    @Override
    public JudgmentResult judge(Balls balls, Balls otherBalls) {
        final JudgmentResult result = JudgmentResult.create();
        for (int i = 0; i < Balls.SIZE; i++) {
            final JudgmentRuleChain ruleChain = JudgmentRuleChain.of(rules);
            result.add(ruleChain.judge(balls, otherBalls, i));
        }
        return result;
    }
}
