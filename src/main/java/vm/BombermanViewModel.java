package vm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;
import model.Game;
import model.GameStatus;

import static model.Game.DELAY_BOMB;

public class BombermanViewModel {
    private final MenuViewModel menuViewModel;
    private final GridViewModel gridViewModel;
    private final MenuConfigViewModel menuConfigViewModel;
    private final Game game = new Game();
    private final BooleanProperty isStarted = new SimpleBooleanProperty();
    private final BooleanProperty isFirstGame = new SimpleBooleanProperty();
    private final BooleanProperty isValidInputs = new SimpleBooleanProperty();

    public BombermanViewModel() {
        menuViewModel = new MenuViewModel(game);
        gridViewModel = new GridViewModel(game);
        menuConfigViewModel = new MenuConfigViewModel(game);
        configBindings();
    }

    private void configBindings() {
        isStarted.bind(game.isStartedProperty());
        isFirstGame.bind(game.isFirstGameProperty());
        isValidInputsProperty().bind(game.isInValidInputsProperty());
    }

    public BooleanProperty isStartedProperty() {
        return isStarted;
    }

    public MenuViewModel getMenuViewModel() {
        return menuViewModel;
    }

    public GridViewModel getGridViewModel() {
        return gridViewModel;
    }

    public MenuConfigViewModel getMenuConfigViewModel() {
        return menuConfigViewModel;
    }

    private IntegerProperty nbLivesProperty() {
        return game.nbLivesProperty();
    }

    private IntegerProperty nbBombsProperty() {
        return game.nbBombsProperty();
    }

    private IntegerProperty nbKeysProperty() {
        return game.nbKeysProperty();
    }

    public BooleanProperty isValidInputsProperty() {
        return isValidInputs;
    }

    public void play() {
        if (isFirstGame.get()) {
            game.start();
        } else {
            setGameData();
            game.reStart();
        }
    }

    public void setGameData() {
        nbLivesProperty().set(menuConfigViewModel.nbLivesProperty().get());
        nbBombsProperty().set(menuConfigViewModel.nbBombsProperty().get());
        nbKeysProperty().set(menuConfigViewModel.nbKeysProperty().get());
    }

    public void moveUp() {
        if (game.getGameStatus() == GameStatus.STARTED) {
            game.moveUp();
        }
    }

    public void moveDown() {
        if (game.getGameStatus() == GameStatus.STARTED) {
            game.moveDown();
        }
    }

    public void moveLeft() {
        if (game.getGameStatus() == GameStatus.STARTED) {
            game.moveLeft();
        }
    }

    public void moveRight() {
        if (game.getGameStatus() == GameStatus.STARTED) {
            game.moveRight();
        }
    }

    public void dropBomb() {
        if (nbBombsProperty().get() > 0 && game.getGameStatus() == GameStatus.STARTED) {
            game.dropBomb();
            new Timeline(new KeyFrame(Duration.millis(DELAY_BOMB), e -> {
            })).play();

        }
    }
}
