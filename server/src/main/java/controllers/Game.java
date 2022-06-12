package controllers;

import com.esotericsoftware.kryonet.Connection;
import common.messages.GameJoinMessage;
import common.messages.GameMoveMessage;
import common.states.BallState;
import common.states.GameState;
import common.states.RacketState;

import java.util.List;

public class Game {
    private List<Connection> connections;

    GameState state;

    Racket leftRacket;
    Racket rightRacket;

    Ball ball;

    public Game(RacketState leftRacket, RacketState rightRacket, BallState ball) {
        this.state = new GameState();
        this.state.leftRacket = leftRacket;
        this.state.rightRacket = rightRacket;
        this.state.ball = ball;

        this.leftRacket = new Racket(state.leftRacket);
        this.rightRacket = new Racket(state.rightRacket);
        this.ball = new Ball(state.ball);
    }
    public void onMoveSignal(GameMoveMessage message, Connection connection) {

    }

    public void onGameJoinSignal(GameJoinMessage message) {

    }

    public void onTimerInvocation(double secondsPassed) {
        ball.calculateStep(secondsPassed, ball.state.position, this);
        System.out.println(ball.state.position);
    }
}
