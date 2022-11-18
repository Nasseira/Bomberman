package model;

abstract class UnbreakableElement extends GameElement {
    UnbreakableElement(Cell cell) {
        super(cell);
    }

    @Override
    boolean isBreakable() {
        return false;
    }

    @Override
    boolean isCrossable() {
        return false;
    }
}
