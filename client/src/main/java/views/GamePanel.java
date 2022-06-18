package views;

import common.enums.MoveDirection;
import controllers.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    public Game game;

    public GamePanel() {
        refresh();

        // Key Listener
        InputMap im = getInputMap(WHEN_FOCUSED);
        ActionMap am = getActionMap();

        // on arrow key up
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "onArrowUp");
        // on arrow key down
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "onArrowDown");

        am.put("onEnter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        am.put("onArrowUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.log("Move: up");
                game.move(MoveDirection.UP);
            }
        });

        am.put("onArrowDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.log("Move: down");
                game.move(MoveDirection.DOWN);
            }
        });
    }

    public void setGameId(String gameId) {
        this.game = new Game(gameId);
    }

    public void refresh() {
        removeAll();
        this.setLayout(new BorderLayout());

        // title row
        JPanel titleRow = new JPanel();
        titleRow.setBackground(Color.BLACK);
        titleRow.setLayout(new FlowLayout());
        if (game != null && game.state != null && game.state.leftRacket != null) {
            JLabel name1 = new JLabel(game.state.leftRacket.name + "          ");
            name1.setForeground(Color.WHITE);
            titleRow.add(name1);
            JLabel score1 = new JLabel(String.valueOf(game.state.leftRacket.score));
            score1.setForeground(Color.WHITE);
            titleRow.add(score1);
        }
        JLabel vs = new JLabel(" VS ");
        vs.setForeground(Color.WHITE);
        titleRow.add(vs);
        if (game != null && game.state != null && game.state.rightRacket != null) {
            JLabel score2 = new JLabel(String.valueOf(game.state.leftRacket.score));
            score2.setForeground(Color.WHITE);
            titleRow.add(score2);
            JLabel name2 = new JLabel("          " + game.state.leftRacket.name);
            name2.setForeground(Color.WHITE);
            titleRow.add(name2);

        }
        this.add(titleRow, BorderLayout.NORTH);

        // Show game canvas
        GameCanvas canvas = new GameCanvas(game);
        this.add(canvas, BorderLayout.CENTER);

        // Exit Button in the bottom
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            game.exitGame();
        });
        add(exitButton, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }
}
