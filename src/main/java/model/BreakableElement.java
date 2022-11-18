package model;

abstract class BreakableElement extends GameElement {
    Game game;

    BreakableElement(Cell cell){
        super(cell);
    }

    @Override
    boolean isBreakable() {
        return true;
    }
}
