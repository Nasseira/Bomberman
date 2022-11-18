package model;

class Pillar extends UnbreakableElement {
    Pillar(Cell cell) {
        super(cell);
    }

    public boolean isPillar() {
        return true;
    }

    @Override
    boolean isCrossable() {
        return false;
    }

    @Override
    public boolean isHero() {
        return false;
    }
}
