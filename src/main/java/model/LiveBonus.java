package model;

public class LiveBonus extends Bonus {
    LiveBonus(Cell cell, Game game) {
        super(cell);
        this.game = game;
    }

    @Override
    void exploding() {
        super.exploding();
        game.liveBonusExploding();
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
    public boolean isLiveBonus() {
        return true;
    }

    @Override
    void heroTakeMe() {
        super.heroTakeMe();
        game.liveBonusTaken();
    }

    public String toString() {
        return "LiveBonus : " + getCell().getRow() + "," + getCell().getCol();
    }
}
