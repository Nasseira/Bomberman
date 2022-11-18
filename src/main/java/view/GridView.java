package view;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import vm.GridViewModel;

import static model.Game.GRID_WIDTH;
import static view.BombermanView.SPACING;

public class GridView extends GridPane {
    public GridView(GridViewModel gridViewModel, DoubleProperty gridWidthProperty) {
        setPadding(new Insets(SPACING));
        RowConstraints rowConstraints = new RowConstraints();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        DoubleBinding cellWidthProperty = gridWidthProperty.add(SPACING * 8).divide(SPACING);

        for (int i = 0; i < GRID_WIDTH; ++i) {
            getColumnConstraints().add(columnConstraints);
            getRowConstraints().add(rowConstraints);
        }

        for (int i = 0; i < GRID_WIDTH; ++i) {
            for (int j = 0; j < GRID_WIDTH; ++j) {
                CellView caseView = new CellView(gridViewModel.getCellViewModel(i, j), cellWidthProperty);
                add(caseView, j, i);
            }
        }
    }
}
