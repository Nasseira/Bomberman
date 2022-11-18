package model;

import javafx.beans.property.ObjectProperty;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static model.Game.BOMB_AREA_EFFECT;
import static model.Game.GRID_WIDTH;

class Grid {
    private final Cell[][] matrix;
    private final List<Cell> walls = new LinkedList<>();

    public List<Cell> getPossibilityToPlaceMineList() {
        return possibilityToPlaceMineList;
    }

    private final List<Cell> possibilityToPlaceMineList = new ArrayList<>();
    private Cell elementCell;

    public Cell getElementCell() {
        return elementCell;
    }

    Grid() {
        matrix = new Cell[GRID_WIDTH][];
        for (int i = 0; i < GRID_WIDTH; i++) {
            matrix[i] = new Cell[GRID_WIDTH];
            for (int j = 0; j < GRID_WIDTH; j++) {
                matrix[i][j] = new Cell(i, j, this);
                //Add Pillar
                if (i % 2 != 0 && j % 2 != 0) {
                    Pillar pillar = new Pillar(matrix[i][j]);
                    matrix[i][j].addElement(pillar);
                } else if (!isForbiddenElementInPos(i, j)) {
                    double pourc = Math.random();
                    if (pourc <= Game.CHANCE_TO_CONTAIN_WALL) {
                        Wall wall = new Wall(matrix[i][j]);
                        matrix[i][j].addElement(wall);
                        //add walls to list to get its positions
                        walls.add(matrix[i][j]);
                    } else {
                        possibilityToPlaceMineList.add(matrix[i][j]);
                    }
                }
            }
        }
        possibilityToPlaceMineList.addAll(walls);
    }

    private boolean isForbiddenElementInPos(int row, int col) {
        return row <= 2 && col <= 2;
    }

    void replaceWall() {
        for (Cell wallCell : walls) {
            if (wallCell.isEmpty())
                wallCell.addElement(new Wall(wallCell));
        }
    }

    public Cell getFirstCell() {
        return matrix[0][0];
    }

    Cell generateElementCell() {
        Random rand = new Random();
        int positionRand = rand.nextInt(walls.size());
        return elementCell = walls.get(positionRand);
    }

    Cell generateCell(List<Cell> cells) {
        Random rand = new Random();
        int positionRand = rand.nextInt(cells.size());
        return elementCell = cells.get(positionRand);
    }

    boolean isReachable(int row, int col) {
        return row >= 0 && row < GRID_WIDTH && col >= 0 && col < GRID_WIDTH;
    }

    Cell getCell(int row, int col) {
        return matrix[row][col];
    }

    public ObjectProperty<GameElement> firstElementProperty(int row, int col) {
        return matrix[row][col].firstElementProperty();
    }

    List<Cell> getNeighbors(int row, int col) {
        List<Cell> neighbors = new ArrayList<>();
        neighbors.add(this.matrix[row][col]);
        for (int i = 1; i <= BOMB_AREA_EFFECT; i++) {
            //UP
            if (isReachable(row - i, col)) {
                if (!getCell(row - i, col).isEmpty() && getCell(row - i, col).getFirstElement().isBreakable()) {
                    neighbors.add(this.matrix[row - i][col]);
                }
            }
            //DOWN
            if (isReachable(row + i, col)) {
                if (!getCell(row + i, col).isEmpty() && getCell(row + i, col).getFirstElement().isBreakable()) {
                    neighbors.add(this.matrix[row + i][col]);
                }
            }
            //LEFT
            if (isReachable(row, col - i)) {
                if (!getCell(row, col - i).isEmpty() && getCell(row, col - i).getFirstElement().isBreakable()) {
                    neighbors.add(this.matrix[row][col - i]);
                }
            }
            //RIGHT
            if (isReachable(row, col + i)) {
                if (!getCell(row, col + i).isEmpty() && getCell(row, col + i).getFirstElement().isBreakable()) {
                    neighbors.add(this.matrix[row][col + i]);
                }
            }
        }
        return neighbors;
    }
}
