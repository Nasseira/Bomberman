package model;

class Key extends BreakableElement {
    Key(Cell cell, Game game) {
        super(cell);
        this.game = game;
    }

    @Override
    public boolean isKey() {
        return true;
    }

    @Override
    boolean isCrossable() {
        return true;
    }

    @Override
    void exploding() {
        super.exploding();
        game.keyExploding();
    }

    @Override
    void heroTakeMe() {
        super.heroTakeMe();
        game.keyFound();
    }
}
