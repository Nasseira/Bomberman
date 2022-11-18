package vm;

import model.Game;


public class GridViewModel {
    private final Game game;

    public GridViewModel(Game game) {
        this.game = game;
    }

    public CellViewModel getCellViewModel(int line, int col) {
        return new CellViewModel(line, col, game);
    }

}
