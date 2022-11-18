package model;

class Wall extends BreakableElement {
    Wall(Cell cell) {
        super(cell);
    }

    @Override
    void exploding() {
        super.exploding();
    }

    @Override
    public boolean isWall() {
        return true;
    }

    @Override
    boolean isCrossable() {
        return false;
    }
}
