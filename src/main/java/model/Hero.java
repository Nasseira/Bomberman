package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

class Hero extends BreakableElement {
    Hero(Cell cell, Game game) {
        super(cell);
        this.game = game;
    }

    @Override
    void exploding() {
        super.exploding();
        game.heroExploding();
    }

    @Override
    boolean isCrossable() {
        return true;
    }

    @Override
    public boolean isHero() {
        return true;
    }

    Bomb dropBomb() {
        if (game.getNbBombs() > 0) {
            Bomb bomb = new Bomb(getCell(), game);
            getCell().addElement(bomb);
            new Timeline(new KeyFrame(Duration.millis(Game.DELAY_BOMB), e -> {
                bomb.explode();
            })).play();
            game.heroDropBomb();
            return bomb;
        }
        return null;
    }

    private void move(int row, int col) {
        if (getCell().isReachable(row, col)) {
            if (getCell().getCell(row, col).isEmpty() || getCell().getCell(row, col).getFirstElement().isCrossable()) {
                getCell().removeElement(this);
                Cell move = this.getCell().getCell(row, col);
                move.addElement(this);
                this.setCell(move);
                if (getCell().listElementSize() > 1) {
                    getCell().getSecondElement().heroTakeMe();
                }
            }
        }
    }

    void moveUp() {
        move(getCell().getRow() - 1, getCell().getCol());
    }

    void moveDown() {
        move(getCell().getRow() + 1, getCell().getCol());
    }

    void moveLeft() {
        move(getCell().getRow(), getCell().getCol() - 1);
    }

    void moveRight() {
        move(getCell().getRow(), getCell().getCol() + 1);
    }
}
