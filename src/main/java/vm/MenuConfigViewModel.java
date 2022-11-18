package vm;

import javafx.beans.property.*;
import model.Game;

public class MenuConfigViewModel {
    private final Game game;
    private final IntegerProperty nbLives = new SimpleIntegerProperty();
    private final IntegerProperty nbBombs = new SimpleIntegerProperty();
    private final IntegerProperty nbKeys = new SimpleIntegerProperty();

    public MenuConfigViewModel(Game game) {
        this.game = game;
        configListeners();
    }

    public IntegerProperty nbLivesProperty() {
        return nbLives;
    }

    public IntegerProperty nbBombsProperty() {
        return nbBombs;
    }

    public IntegerProperty nbKeysProperty() {
        return nbKeys;
    }

    public void setNbLives(int nbLives) {
        this.nbLives.set(nbLives);
    }

    public void setNbBombs(int nbBombs) {
        this.nbBombs.set(nbBombs);
    }

    public void setNbKeys(int nbKeys) {
        this.nbKeys.set(nbKeys);
    }

    private void configListeners() {
        nbLives.addListener((obs, old, newVal) -> {
            game.setNbLives(newVal.intValue());
        });

        nbBombs.addListener((obs, old, newVal) -> {
            game.setNbBombs(newVal.intValue());
        });

        nbKeys.addListener((obs, old, newVal) -> {
            game.setNbKeys(newVal.intValue());
        });
    }
}
