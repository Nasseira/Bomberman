package model;

public abstract class GameElement {
    private Cell cell;

    GameElement(Cell cell) {
        this.cell = cell;
    }

    Cell getCell() {
        return cell;
    }

    void setCell(Cell cell) {
        this.cell = cell;
    }

    void exploding() {
        getCell().removeElement(this);
    }

    abstract boolean isBreakable();

    abstract boolean isCrossable();

    void heroTakeMe() {
        getCell().removeElement(this);
    }

    public boolean isHero() {
        return false;
    }

    public boolean isKey() {
        return false;
    }

    public boolean isBomb() {
        return false;
    }

    public boolean isWall() {
        return false;
    }

    public boolean isBombBonus() {
        return false;
    }

    public boolean isLiveBonus() {
        return false;
    }

    public boolean isTreasure() {
        return false;
    }

    public boolean isMine() {
        return false;
    }

    void visible() {
    }

    public boolean isPillar() {
        return false;
    }
}

