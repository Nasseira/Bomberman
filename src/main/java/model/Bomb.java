package model;

import java.util.List;

class Bomb extends BreakableElement {
    Bomb(Cell cell, Game game) {
        super(cell);
        this.game = game;
    }

    @Override
    void exploding() {
        super.exploding();
        game.bombExploding();
    }

    @Override
    public boolean isBomb() {
        return true;
    }

    boolean isCrossable() {
        return true;
    }

    void explode() {
        List<Cell> myNeighborCells = getCell().getNeighbors();
        if (!myNeighborCells.isEmpty()) {
            for (Cell cell : myNeighborCells) {
                cell.explosing();
            }
        }
    }

    @Override
    void heroTakeMe() {
    }
}
