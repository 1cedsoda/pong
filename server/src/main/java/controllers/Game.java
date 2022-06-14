package controllers;

import com.esotericsoftware.kryonet.Connection;
import common.Utils;
import common.geometry.Point;
import common.messages.GameMoveMessage;
import common.states.BallState;
import common.states.GameState;
import common.states.RacketState;
import server.PlayerConnection;

import java.util.List;

public class Game {
    private List<Connection> connections;

    GameState state;
    PlayerConnection leftPlayer;
    PlayerConnection rightPlayer;
    Racket leftRacket;
    Racket rightRacket;
    Ball ball;

    GameTimer timer;

    public Game() {
        this.state = new GameState();
        this.state.gameId = Utils.randomString(10);
        this.state.ball = new BallState();

        this.ball = new Ball(state.ball);
    }

    public void onMoveSignal(GameMoveMessage message, Connection connection) {

    }

    public void onTimerInvocation(double secondsPassed) {
        ball.calculateStep(secondsPassed, ball.state.position, this);
        System.out.println(ball.state.position);
    }

    public boolean addPlayer(PlayerConnection playerConnection) {
        if (this.state.leftRacket == null) {
            System.out.println("Game " +state.gameId+ " : Adding left player");
            RacketState racketState = new RacketState();
            racketState.score = 0;
            racketState.position = new Point(-1, 0);
            racketState.size = 0.3;
            racketState.name = playerConnection.name;
            this.state.leftRacket = racketState;
            this.leftRacket = new Racket(this.state.leftRacket);
            this.leftPlayer = playerConnection;
            return true;
        } else if (this.state.rightRacket == null) {
            System.out.println("Game " +state.gameId+ " : Adding right player");
            RacketState racketState = new RacketState();
            racketState.score = 0;
            racketState.position = new Point(1, 0);
            racketState.size = 0.3;
            racketState.name = playerConnection.name;
            this.state.rightRacket = racketState;
            this.rightRacket = new Racket(this.state.rightRacket);
            this.rightPlayer = playerConnection;
            return true;
        }
        return false;
    }

    public void removePlayer(PlayerConnection playerConnection) {
        if (this.rightPlayer == playerConnection) {
            System.out.println("Game " +state.gameId+ " : Removing right player");
            this.state.rightRacket = null;
            this.rightRacket = null;
            this.rightPlayer = null;
        } else if (this.leftPlayer == playerConnection) {
            System.out.println("Game " +state.gameId+ " : Removing left player");
            this.state.leftRacket = null;
            this.leftRacket = null;
            this.leftPlayer = null;
        }
    }

    public void sendToPlayers(Object message) {
        if (this.leftPlayer != null) {
            this.leftPlayer.sendTCP(message);
        }
        if (this.rightPlayer != null) {
            this.rightPlayer.sendTCP(message);
        }
    }

    public boolean hasPlayerConnected(PlayerConnection playerConnection) {
        return this.leftPlayer == playerConnection || this.rightPlayer == playerConnection;
    }

    public boolean isFull() {
        return this.leftPlayer != null && this.rightPlayer != null;
    }

    // isEmpty
    public boolean isEmpty() {
        return this.leftPlayer == null && this.rightPlayer == null;
    }

    public void reset() {
        this.timer = new GameTimer(this);
    }

    public void start() {
        this.timer.runThread();
    }
}
