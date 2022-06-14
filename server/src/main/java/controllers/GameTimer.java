package controllers;

import controllers.Game;

import java.util.Date;

public class GameTimer implements Runnable {

    private long startTime = 0;
    private long lastTime = 0;

    private Game game;

    public GameTimer(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (true) {
            // 30 times a second create event

                try {
                    Thread.sleep(500);
                    if (startTime == 0) {
                        startTime = new Date().getTime();
                        lastTime = startTime;
                    } else {
                        long now = new Date().getTime();
                        double timePassed = now - lastTime;
                        lastTime = now;
                        game.onTimerInvocation(timePassed/1000);
                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

        }
    }

    public void runThread() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
