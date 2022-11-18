package vm;

import javafx.beans.property.*;
import model.Game;

public class MenuViewModel {
    private final Game game;
    private final IntegerProperty nbLives = new SimpleIntegerProperty();
    private final IntegerProperty nbBombs = new SimpleIntegerProperty();
    private final StringProperty gameStatusTxt = new SimpleStringProperty("Statut de la partie :  Partie pas lancée");
    private final IntegerProperty nbKeys = new SimpleIntegerProperty();

    public MenuViewModel(Game game) {
        this.game = game;
        game.gameStatusProperty().addListener((obj, old, newVal) -> {
            refreshStatus();
        });
        configBinding();
    }

    private void configBinding() {
        nbLivesProperty().bind(game.nbLivesProperty());
        nbBombsProperty().bind(game.nbBombsProperty());
        nbKeysProperty().bind(game.nbKeysProperty());
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
    public ReadOnlyStringProperty gameStatusTxtProperty() {
        return gameStatusTxt;
    }

    private void setGameStatusTxt(String gameStatusTxt) {
        this.gameStatusTxt.set(gameStatusTxt);
    }

    private void refreshStatus() {
        switch (game.getGameStatus()) {
            case WON:
                setGameStatusTxt("Statut de la partie :  Le joueur a gagné");
                break;
            case STARTED:
                setGameStatusTxt("Statut de la partie :  En cours");
                break;
            case NOT_STARTED:
                setGameStatusTxt("Statut de la partie :  Partie pas lancée");
                break;
            case LOST_NO_LIVE:
                setGameStatusTxt("Statut de la partie : Partie perdue ! Le joueur n'a plus de vie !");
                break;
            case LOST_KEY_BROKEN:
                setGameStatusTxt("Statut de la partie : Partie perdue ! Le joueur a cassé une clé !");
                break;
            case LOST_NO_BOMB_TO_FIND_KEY:
                setGameStatusTxt("Statut de la partie : Partie perdue ! Le joueur n'a plus assez de bombes pour découvrir les clés restantes !");
                break;
        }
    }
}