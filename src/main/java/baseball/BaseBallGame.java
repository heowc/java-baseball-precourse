package baseball;

import baseball.ball.Balls;
import baseball.player.Player;
import baseball.referee.Judgment;
import baseball.referee.JudgmentResult;
import baseball.referee.Referee;
import camp.nextstep.edu.missionutils.Console;

import java.util.Map;


public final class BaseBallGame {

    private final Player computer;
    private final Player systemIn;
    private final Referee referee;

    private BaseBallGame(Player computer, Player systemIn, Referee referee) {
        this.computer = computer;
        this.systemIn = systemIn;
        this.referee = referee;
    }

    public static void run() {
        final BaseBallGame baseBallGame = new BaseBallGame(Player.computer(), Player.systemIn(), Referee.of());
        do {
            baseBallGame.play();
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        } while (retrying());
    }

    void play() {
        JudgmentResult result;
        final Balls balls = computer.balls();
        do {
            System.out.print("숫자를 입력해주세요 : ");
            result = referee.judge(balls, systemIn.balls());
            System.out.println(makeHint(result.asMap()));
        } while (!result.isCompleted());
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    private static String makeHint(Map<Judgment, Integer> map) {
        final Integer strikeCount = map.getOrDefault(Judgment.STRIKE, 0);
        final Integer ballCount = map.getOrDefault(Judgment.BALL, 0);
        final Integer missCount = map.getOrDefault(Judgment.MISS, 0);

        if (missCount.equals(3)) {
            return Judgment.MISS.content();
        }
        return makeHint(strikeCount, ballCount);
    }

    private static String makeHint(Integer strikeCount, Integer ballCount) {
        final StringBuilder builder = new StringBuilder();
        if (ballCount > 0) {
            builder.append(String.format("%d%s ", ballCount, Judgment.BALL.content()));
        }
        if (strikeCount > 0) {
            builder.append(String.format("%d%s", strikeCount, Judgment.STRIKE.content()));
        }
        return builder.toString().trim();
    }

    private static boolean retrying() {
        final String readLine = Console.readLine();
        return RetryCode.of(readLine).isRetryable();
    }

    private enum RetryCode {
        START(1, true),
        END(2, false)
        ;

        private final int code;
        private final boolean retryable;

        RetryCode(int code, boolean retryable) {
            this.code = code;
            this.retryable = retryable;
        }

        static RetryCode of(String value) {
            final int code = Integer.parseInt(value);
            if (START.code == code) {
                return START;
            }
            if (END.code == code) {
                return END;
            }
            throw new IllegalArgumentException("게임 종료");
        }

        boolean isRetryable() {
            return retryable;
        }
    }
}
