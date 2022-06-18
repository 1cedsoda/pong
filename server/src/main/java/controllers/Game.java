package controllers;

import com.esotericsoftware.kryonet.Connection;
import common.Utils;
import common.geometry.Point;
import common.geometry.Vector;
import common.messages.GameMoveMessage;
import common.messages.GameStateMessage;
import common.states.BallState;
import common.states.GameState;
import common.states.RacketState;
import networking.PlayerConnection;

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

        this.state.ball.position = new Point(0, 0);
        this.state.ball.velocity = new Vector(0, 1.5);

        this.ball = new Ball(state.ball);

        reset();
    }

    public void onTimerInvocation(double secondsPassed) {
        System.out.println(String.valueOf(secondsPassed) + " " + String.valueOf(ball.state.roundSeconds));
        ball.state.roundSeconds += secondsPassed;
        if (ball.state.roundSeconds >= 3.0) {
            ball.calculateStep(secondsPassed, ball.state.position, this);
        }
        sendGameState();
    }

    // Send game state to clients
    public void sendGameState() {
        GameStateMessage gameStateMessage = new GameStateMessage();
        gameStateMessage.gameState = state;
        sendToPlayers(gameStateMessage);
    }

    public boolean addPlayer(PlayerConnection playerConnection) {
        if (this.state.leftRacket == null) {
            log("Adding left player " + playerConnection.name);
            RacketState racketState = new RacketState();
            racketState.score = 0;
            racketState.position = new Point(-1, 0);
            racketState.size = 0.5;
            racketState.name = playerConnection.name;
            this.state.leftRacket = racketState;
            this.leftRacket = new Racket(this.state.leftRacket);
            this.leftPlayer = playerConnection;
            return true;
        } else if (this.state.rightRacket == null) {
            log("Adding right player " + playerConnection.name);
            RacketState racketState = new RacketState();
            racketState.score = 0;
            racketState.position = new Point(1, 0);
            racketState.size = 0.5;
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
            log("Removing right player" + playerConnection.name);
            this.state.rightRacket = null;
            this.rightRacket = null;
            this.rightPlayer = null;
        } else if (this.leftPlayer == playerConnection) {
            log("Removing left player" + playerConnection.name);
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
        log("Resetting game");
        this.timer = new GameTimer(this);
    }

    public void start() {
        log("Starting game");
        this.timer.runThread();
    }

    public void onGameMoveMessage(GameMoveMessage message, PlayerConnection playerConnection) {
        if (this.leftPlayer == playerConnection) {
            this.leftRacket.move(message.moveDirection);
        } else if (this.rightPlayer == playerConnection) {
            this.rightRacket.move(message.moveDirection);
        }
    }

    public void log(String log) {
        System.out.println("[" + state.gameId + "] " + log);
    }
}
