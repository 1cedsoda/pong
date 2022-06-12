package controllers;

import common.messages.GameJoinSignal;
import common.messages.MoveSignal;
import common.models.BallModel;
import common.models.GameModel;
import common.models.PlayerModel;
import common.models.RacketModel;
import server.Connection;

import java.util.List;

public class Game extends GameModel<Game, Ball, Racket, Player> {
    private List<Connection> connections;

    Ball ball;

    public Game(Racket leftRacket, Racket rightRacket, Ball ball) {
        super(leftRacket, rightRacket, ball);
        this.ball = ball;
    }
    public void onMoveSignal(MoveSignal message) {
    }

    public void onGameJoinSignal(GameJoinSignal message, Connection connection) {
        this.connections.add(connection);
    }

    public void onTimerInvocation(double secondsPassed) {
        ball.calculateStep(secondsPassed, ball.position, this);
        System.out.println(ball.position);
    }

    public static Game newGame() {
        Player p1 = new Player("Hans");
        Player p2 = new Player("Dieter");

        Racket r1 = new Racket(p1, -1);
        Racket r2 = new Racket(p2, 1);

        Ball b = new Ball();

        return new Game(r1, r2, b);
    }
}
