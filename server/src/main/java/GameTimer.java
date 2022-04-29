import controllers.GameController;

import java.util.Date;

public class GameTimer implements Runnable {

    private long startTime = 0;
    private long lastTime = 0;

    private GameController gameController;

    public GameTimer(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void run() {
        while (true) {
            // 30 times a second create event

                try {
                    Thread.sleep(1000/30);
                    if (startTime == 0) {
                        startTime = new Date().getTime();
                        lastTime = startTime;
                    } else {
                        long timePassed = new Date().getTime() - lastTime;
                        lastTime = new Date().getTime();
                        gameController.onTimerInvocation(timePassed);
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
