package views;

import common.states.BallState;
import common.states.GameState;
import common.states.RacketState;
import controllers.Game;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    static final int padding = 40;
    static final int ballRadius = 10;
    static final int racketWidth = 20;
    static final int borderStrength = 10;
    static final int ballAreaWidth = 500;
    static final int ballAreaHeight = 300;
    static final int arenaOffsetTop = padding + borderStrength + ballRadius;
    static final int arenaOffsetLeft = padding + borderStrength + ballRadius;

    static int width = arenaOffsetLeft * 2 + ballAreaWidth;
    static int height = arenaOffsetTop * 2 + ballAreaHeight;

    private GameState state;

    public GameCanvas(Game game) {
        if (game != null) {
            this.state = game.state;
        }
        setBackground(Color.YELLOW);
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // draw arena
        drawBallArena(g2d);

        g2d.setColor(Color.red);

        // draw rackets
        drawRacket(g2d, state.leftRacket);
        drawRacket(g2d, state.rightRacket);

        // draw ball
        drawBall(g2d, state.ball);
    }

    private void drawBallArena(Graphics2D g2d) {
        int x = arenaOffsetLeft + gameXToArenaX(-1);
        int y = arenaOffsetTop + gameYToArenaY(1);

        // draw area
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, ballAreaWidth, ballAreaHeight);

        // top border
        int borderTopY = arenaOffsetTop + gameYToArenaY(1) - borderStrength - ballRadius;
        g2d.setColor(Color.black);
        g2d.fillRect(x, borderTopY, ballAreaWidth, borderStrength);

        // bottom border
        int borderBottomY = arenaOffsetTop + gameYToArenaY(-1) + ballRadius;
        g2d.setColor(Color.black);
        g2d.fillRect(x, borderBottomY, ballAreaWidth, borderStrength);

    }

    private void drawRacket(Graphics2D g2d, RacketState racketState) {
        if (racketState == null) {
            return;
        }
        int x = (int) (arenaOffsetLeft + gameXToArenaX(racketState.position.x) - (racketWidth / 2) + ((racketWidth / 2) * racketState.position.x) + (ballRadius * racketState.position.x));
        int racketHeight = (int) (racketState.size * ballAreaHeight * 0.5);
        int y = arenaOffsetTop + gameYToArenaY(racketState.position.y) - racketHeight / 2;
        g2d.fillRect(x, y, racketWidth, racketHeight);
    }

    private void drawBall(Graphics2D g2d, BallState ballState) {
        if (ballState == null) {
            return;
        }
        int x = (int) (arenaOffsetLeft + gameXToArenaX(ballState.position.x) - ballRadius);
        int y = (int) (arenaOffsetTop + gameYToArenaY(ballState.position.y) - ballRadius);

        g2d.fillRect(x, y, ballRadius * 2, ballRadius * 2);
    }

    private int gameXToArenaX(double gameX) {
        double zeroToOneX = (gameX + 1) / 2;
        return (int) (ballAreaWidth * zeroToOneX);

    }

    private int gameYToArenaY(double gameY) {
        double zeroToOneY = (-gameY + 1) / 2;
        return (int) (ballAreaHeight * zeroToOneY);
    }
}
