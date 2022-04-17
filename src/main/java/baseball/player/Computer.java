package baseball.player;

import baseball.ball.Ball;
import baseball.ball.Balls;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

class Computer implements Player {

    @Override
    public Balls balls() {
        final List<Integer> numbers = new ArrayList<>(Balls.SIZE);
        while (numbers.size() != 3) {
            final int number = Randoms.pickNumberInRange(Ball.MIN_VALUE, Ball.MAX_VALUE);
            addIfNotContains(numbers, number);
        }
        return toBalls(numbers);
    }

    private static void addIfNotContains(List<Integer> numbers, int number) {
        if(!numbers.contains(number)){
            numbers.add(number);
        }
    }

    private static Balls toBalls(List<Integer> numbers) {
        final List<Ball> ballList = new ArrayList<>(numbers.size());
        for (int value : numbers) {
            ballList.add(Ball.of(value));
        }
        return Balls.of(ballList);
    }
}
