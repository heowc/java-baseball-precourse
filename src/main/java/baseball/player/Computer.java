package baseball.player;

import baseball.ball.Ball;
import baseball.ball.Balls;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

class Computer implements Player {

    private final List<Integer> rawBallList;

    Computer() {
        final List<Integer> list = new ArrayList<>();
        for (int i = Ball.MIN_VALUE; i <= Ball.MAX_VALUE; i++) {
            list.add(i);
        }
        rawBallList = list;
    }

    @Override
    public Balls balls() {
        final List<Integer> numbers = new ArrayList<>(Balls.SIZE);
        while (numbers.size() != 3) {
            final int number = Randoms.pickNumberInRange(Ball.MIN_VALUE, Ball.MAX_VALUE);
            addIfNotContains(numbers, number);
        }

        final List<Ball> ballList = new ArrayList<>(numbers.size());
        for (int value : numbers) {
            ballList.add(Ball.of(value));
        }
        return Balls.of(ballList);
    }

    private static void addIfNotContains(List<Integer> numbers, int number) {
        if(!numbers.contains(number)){
            numbers.add(number);
        }
    }
}
