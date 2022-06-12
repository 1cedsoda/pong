package controllers;

import common.geometry.Line;
import common.geometry.Point;
import common.states.RacketState;

public class Racket {

    RacketState state;

    public Racket(RacketState state) {
        this.state = state;
    }

    public Racket(String name, double x) {
        this.state = new RacketState();
        this.state.name = name;
        this.state.position = new Point(x, 0);
        this.state.score = 0;
        this.state.size = 0.3;
    }

    public void incrementScore() {
        this.state.score++;
    }

    public Line getLine() {
        return new Line(
                new Point(state.position.x, state.position.y + state.size / 2),
                new Point(state.position.x, state.position.y - state.size / 2)
        );
    }
}
