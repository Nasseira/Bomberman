package vm;

import javafx.beans.property.*;
import model.Game;
import model.GameElement;

public class CellViewModel {
    private Game game;
    private final int row, col;
    private final ObjectProperty<GameElement> firstElement = new SimpleObjectProperty<>();
    private final IntegerProperty mineAboutToExplodeCounter = new SimpleIntegerProperty();

    public ObjectProperty<GameElement> firstElementProperty() {
        return firstElement;
    }

    public IntegerProperty mineAboutToExplodeCounterProperty() {
        return mineAboutToExplodeCounter;
    }

    public CellViewModel(int row, int col, Game game) {
        this.row = row;
        this.col = col;
        this.game = game;
        firstElement.bind(game.firstElementProperty(this.row, this.col));
        mineAboutToExplodeCounter.bind(game.mineAboutToExplodeCounterProperty());
    }
}
