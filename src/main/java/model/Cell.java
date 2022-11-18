package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

class Cell {
    private final List<GameElement> gameElements = new ArrayList<>();
    private final int row;
    private final int col;
    private final Grid grid;
    private final ObjectProperty<GameElement> firstElement = new SimpleObjectProperty<>(null);

    Cell(int row, int col, Grid grid) {
        this.row = row;
        this.col = col;
        this.grid = grid;
    }

    GameElement getFirstElement() {
        if (gameElements.size() > 0) {
            return gameElements.get(0);
        }
        return null;
    }

    GameElement getSecondElement() {
        if (gameElements.size() > 0) {
            return gameElements.get(1);
        }
        return null;
    }

    private boolean isHeroOrWall(GameElement gameElement) {
        return gameElement.isHero() || gameElement.isWall();
    }

    void addElement(GameElement gameElement) {
        if (isHeroOrWall(gameElement)) {
            gameElements.add(0, gameElement);
        } else if (gameElement.isBomb())
            gameElements.add(1, gameElement);
        else {
            gameElements.add(gameElement);
            getFirstElement().visible();
        }
        setFirstElement(getFirstElement());
    }

    int listElementSize() {
        return gameElements.size();
    }

    boolean isEmpty() {
        return listElementSize() == 0;
    }

    ObjectProperty<GameElement> firstElementProperty() {
        return firstElement;
    }

    void setFirstElement(GameElement firstElement) {
        this.firstElement.set(firstElement);
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    Cell getCell(int row, int col) {
        return grid.getCell(row, col);
    }

    void removeElement(GameElement gameElement) {
        gameElements.remove(gameElement);
        setFirstElement(getFirstElement());
        if (getFirstElement() != null)
            getFirstElement().visible();
    }

    void removeAllElement() {
        if (!isEmpty()) {
            for (int i = 0; i <= listElementSize(); i++) {
                removeElement(getFirstElement());
            }
        }
    }

    boolean isReachable(int row, int col) {
        return grid.isReachable(row, col);
    }

    List<Cell> getNeighbors() {
        return grid.getNeighbors(row, col);
    }

    void explosing() {
        if (getFirstElement() != null) {
            if (listElementSize() > 1 && getSecondElement().isBomb())
                getSecondElement().exploding();
            else if (listElementSize() > 1 &&( getFirstElement().isBomb() || getFirstElement().isMine()))
                getSecondElement().exploding();
            if (getFirstElement() != null)
                getFirstElement().exploding();
        }
    }
}
