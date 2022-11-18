package model;

public class BombBonus extends Bonus {

    BombBonus(Cell cell, Game game) {
        super(cell);
        this.game = game;
    }

    private final Bomb bomb = new Bomb(getCell(), game);

    @Override
    void exploding() {
        super.exploding();
        bomb.explode();
    }

    @Override
    boolean isCrossable() {
        return true;
    }

    @Override
    public boolean isBombBonus() {
        return true;
    }

    @Override
    void heroTakeMe() {
        super.heroTakeMe();
        game.bombBonusTaken();
    }

    @Override
    public String toString() {
        return "BombBonus : " + getCell().getRow() + "," + getCell().getCol();
    }
}
