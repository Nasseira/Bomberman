package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import static model.Game.DELAY_MINE;

public class Mine extends Bonus {
    Mine(Cell cell, Game game) {
        super(cell);
        this.game = game;
    }

    private final Bomb bomb = new Bomb(getCell(), game);

    private final Timeline timer = new Timeline(
            new KeyFrame(Duration.millis(DELAY_MINE / 3f), e -> {
                game.mineAboutToExplodeInTwoSec();
            }),
            new KeyFrame(Duration.millis(DELAY_MINE / 2f), e -> {
                game.mineAboutToExplodeInOneSec();
            }),
            new KeyFrame(Duration.millis(DELAY_MINE), e -> {
                bomb.explode();
                game.setInitialStateMineCounter();
            }
            ));

    @Override
    void exploding() {
        super.exploding();
    }

    @Override
    boolean isBreakable() {
        return true;
    }

    @Override
    boolean isCrossable() {
        return true;
    }

    @Override
    public boolean isMine() {
        return true;
    }

    @Override
    void heroTakeMe() {
        super.heroTakeMe();
        timer.stop();
        game.setInitialStateMineCounter();
    }

    @Override
    void visible() {
        timer.play();
    }

    public String toString() {
        return "Mine : " + getCell().getRow() + "," + getCell().getCol();
    }
}
