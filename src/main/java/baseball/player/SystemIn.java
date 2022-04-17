package baseball.player;

import baseball.ball.*;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SystemIn implements Player {

    private static final Pattern PATTERN = Pattern.compile("");

    @Override
    public Balls balls() {
        final String rawBalls = Console.readLine();
        final List<Ball> balls = new ArrayList<>(Balls.SIZE);
        for (String rawBall : PATTERN.split(rawBalls)) {
            final Ball ball = Ball.of(Integer.parseInt(rawBall));
            balls.add(ball);
        }
        return Balls.of(balls);
    }
}
