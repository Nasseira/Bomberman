package model;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Game {
    public static final int GRID_WIDTH = 15;
    public static final double CHANCE_TO_CONTAIN_WALL = 0.3;
    public static final int NB_LIVES = 3;
    public static final int NB_BOMBS = 20;
    public static final int NB_KEYS = 1;
    public static final int NB_BOMB_BONUS = 2;
    public static final int NB_LIVE_BONUS = 2;
    public static final int NB_TREASURE = 2;
    public static final int NB_MINE = 3;
    public static final int BOMB_AREA_EFFECT = 1;
    public static final int DELAY_BOMB = 1000;
    public static final int DELAY_MINE = 3000;
    private final Grid grid;
    private final Hero hero;
    private Bomb bomb;
    private final List<Cell> gameElementList = new ArrayList<>();
    private final List<Cell> keysInGame = new ArrayList<>();
    private final List<Cell> minesInGame = new LinkedList<>();

    public Game() {
        this.grid = new Grid();
        hero = new Hero(grid.getFirstCell(), this);
        checkInputs();
    }


    private final ObjectProperty<GameStatus> gameStatus = new SimpleObjectProperty<>(GameStatus.NOT_STARTED);

    private final BooleanProperty isStarted = new SimpleBooleanProperty(false);
    private final BooleanProperty isFirstGame = new SimpleBooleanProperty(true);
    private final BooleanProperty isInValidInputs = new SimpleBooleanProperty(false);

    private final IntegerProperty nbKeys = new SimpleIntegerProperty(NB_KEYS);
    private final IntegerProperty nbLives = new SimpleIntegerProperty(NB_LIVES);
    private final IntegerProperty nbBombs = new SimpleIntegerProperty(NB_BOMBS);
    private final IntegerProperty mineAboutToExplodeCounter = new SimpleIntegerProperty();

    public void checkInputs() {
        nbLives.addListener((o, old, newV) -> {
            if (nbKeys.get() >= 1 && nbKeys.get() <= 5 && nbBombs.get() >= nbKeys.get())
                setIsInValidInputs(newV.intValue() < 1);
        });
        nbBombs.addListener((o, old, newV) -> {
            if (nbLives.get() >= 1 && nbKeys.get() >= 1 && nbKeys.get() <= 5)
                setIsInValidInputs(newV.intValue() < nbKeys.get());

        });
        nbKeys.addListener((o, old, newV) -> {
            if (nbLives.get() >= 1 && (newV.intValue() <= nbBombs.get() || old.intValue() <= nbBombs.get()))
                setIsInValidInputs(newV.intValue() < 1 || newV.intValue() > 5 || newV.intValue() > nbBombs.get());
        });
    }

    public int getNbLives() {
        return nbLives.get();
    }

    public void setNbLives(int nbLives) {
        this.nbLives.set(nbLives);
    }

    public int getNbBombs() {
        return nbBombs.get();
    }

    public void setNbBombs(int nbBombs) {
        this.nbBombs.set(nbBombs);
    }

    public int getNbKeys() {
        return nbKeys.get();
    }

    public IntegerProperty nbKeysProperty() {
        return nbKeys;
    }

    public void setNbKeys(int nbKeys) {
        this.nbKeys.set(nbKeys);
    }

    public ReadOnlyBooleanProperty isFirstGameProperty() {
        return isFirstGame;
    }

    public void setIsFirstGame(boolean isFirstGame) {
        this.isFirstGame.set(isFirstGame);
    }

    public ReadOnlyBooleanProperty isStartedProperty() {
        return isStarted;
    }

    public void setIsStarted(boolean isStarted) {
        this.isStarted.set(isStarted);
    }

    public GameStatus getGameStatus() {
        return gameStatus.get();
    }

    public ReadOnlyObjectProperty<GameStatus> gameStatusProperty() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus.set(gameStatus);
    }

    public IntegerProperty nbLivesProperty() {
        return nbLives;
    }

    public IntegerProperty nbBombsProperty() {
        return nbBombs;
    }

    public BooleanProperty isInValidInputsProperty() {
        return isInValidInputs;
    }

    public void setIsInValidInputs(boolean isInValidInputs) {
        this.isInValidInputs.set(isInValidInputs);
    }

    public ObjectProperty<GameElement> firstElementProperty(int row, int col) {
        return grid.firstElementProperty(row, col);
    }

    public IntegerProperty mineAboutToExplodeCounterProperty() {
        return mineAboutToExplodeCounter;
    }

    public void setMineAboutToExplodeCounter(int mineAboutToExplodeCounter) {
        this.mineAboutToExplodeCounter.set(mineAboutToExplodeCounter);
    }


    void mineAboutToExplodeInTwoSec() {
        setMineAboutToExplodeCounter(2);
    }

    void mineAboutToExplodeInOneSec() {
        setMineAboutToExplodeCounter(1);
    }

    void setInitialStateMineCounter() {
        setMineAboutToExplodeCounter(0);
    }

    void putHeroInGrid() {
        hero.setCell(grid.getFirstCell());
        hero.getCell().addElement(hero);
    }

    void putKeyInGrid() {
        for (int i = 0; i < nbKeys.get(); i++) {
            Cell key = grid.generateElementCell();
            Key keyToFind = new Key(grid.getElementCell(), this);
            key.addElement(keyToFind);
            keysInGame.add(key);
            System.out.println("Key : " + "(" + key.getRow() + "," + key.getCol() + ")");
        }
        gameElementList.addAll(keysInGame);
    }

    void putBombBonusInGrid() {
        for (int i = 0; i < NB_BOMB_BONUS; i++) {
            Cell bombBonusCell = grid.generateElementCell();
            BombBonus bombBonus = new BombBonus(bombBonusCell, this);
            bombBonusCell.addElement(bombBonus);
            gameElementList.add(bombBonusCell);
            System.out.println(bombBonus);
        }
    }

    void putLiveBonusInGrid() {
        for (int i = 0; i < NB_LIVE_BONUS; i++) {
            Cell liveBonusCell = grid.generateElementCell();
            LiveBonus liveBonus = new LiveBonus(liveBonusCell, this);
            liveBonusCell.addElement(liveBonus);
            gameElementList.add(liveBonusCell);
            System.out.println(liveBonus);
        }
    }

    void putTreasureInGrid() {
        for (int i = 0; i < NB_TREASURE; i++) {
            Cell treasureCell = grid.generateElementCell();
            Treasure treasure = new Treasure(treasureCell, this);
            treasure.completeListWithRandom();
            treasureCell.addElement(treasure);
            gameElementList.add(treasureCell);
            System.out.println(treasure);
        }
    }

    void putMineInGrid() {
        for (int i = 0; i < NB_MINE; i++) {
            Cell mine = grid.generateCell(grid.getPossibilityToPlaceMineList());
            Mine mineToPlace = new Mine(grid.getElementCell(), this);
            grid.getElementCell().addElement(mineToPlace);
            System.out.println("( Mine : " + mine.getRow() + "," + mine.getCol() + ")");
            minesInGame.add(mine);
        }
        gameElementList.addAll(minesInGame);
    }

    private void removeAllGameElement() {
        gameElementList.add(hero.getCell());
        for (Cell cell : gameElementList) {
            if (cell.getFirstElement() != null) {
                cell.removeAllElement();
            }
        }
        keysInGame.clear();
    }

    private boolean isKeyHidden(Cell cell) {
        return cell.listElementSize() > 1 && !cell.getFirstElement().isKey();
    }

    private boolean isKeysHidden() {
        for (Cell cell : keysInGame) {
            if (isKeyHidden(cell))
                return isKeyHidden(cell);
        }
        return false;
    }

    public void start() {
        putHeroInGrid();
        putKeyInGrid();
        putBombBonusInGrid();
        putLiveBonusInGrid();
        putTreasureInGrid();
        putMineInGrid();
        gameStatus.setValue(GameStatus.STARTED);
        setIsStarted(true);
        setIsFirstGame(false);
        setIsInValidInputs(false);
    }

    public void reStart() {
        removeAllGameElement();
        grid.replaceWall();
        putHeroInGrid();
        putKeyInGrid();
        putBombBonusInGrid();
        putLiveBonusInGrid();
        putTreasureInGrid();
        putMineInGrid();
        gameStatus.setValue(GameStatus.STARTED);
        setIsStarted(true);
    }

    private int getNbKeysHidden() {
        int idx = 0;
        while (keysInGame.size() > idx) {
            if (!isKeyHidden(keysInGame.get(idx))) {
                keysInGame.remove(idx);
            } else
                idx++;
        }
        return keysInGame.size();
    }

    void bombExploding() {
        if (isKeysHidden() && getNbBombs() < getNbKeysHidden() && bomb.getCell().isEmpty()) {
            setGameStatus(GameStatus.LOST_NO_BOMB_TO_FIND_KEY);
            setIsStarted(false);
        }
    }

    void heroExploding() {
        setNbLives(getNbLives() - 1);
        if (getNbLives() > 0 && getGameStatus() == GameStatus.STARTED)
            putHeroInGrid();
        else {
            setGameStatus(GameStatus.LOST_NO_LIVE);
            setIsStarted(false);
        }
    }

    void keyExploding() {
        setNbKeys(getNbKeys() - 1);
        setGameStatus(GameStatus.LOST_KEY_BROKEN);
        setIsStarted(false);
    }

    void liveBonusExploding() {
        setNbLives(getNbLives() - 1);
        if (getNbLives() == 0) {
            setGameStatus(GameStatus.LOST_NO_LIVE);
            setIsStarted(false);
        }
    }

    void keyFound() {
        setNbKeys(getNbKeys() - 1);
        if (getNbKeys() == 0) {
            setGameStatus(GameStatus.WON);
            setIsStarted(false);
        }
    }

    void bombBonusTaken() {
        setNbBombs(getNbBombs() + 1);
    }

    void liveBonusTaken() {
        setNbLives(getNbLives() + 1);
    }

    void heroDropBomb() {
        setNbBombs(getNbBombs() - 1);
    }

    public void moveUp() {
        hero.moveUp();
    }

    public void moveDown() {
        hero.moveDown();
    }

    public void moveLeft() {
        hero.moveLeft();
    }

    public void moveRight() {
        hero.moveRight();
    }

    public void dropBomb() {
        bomb = hero.dropBomb();
    }
}
