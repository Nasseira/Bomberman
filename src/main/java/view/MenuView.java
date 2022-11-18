package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import vm.MenuViewModel;

import static view.BombermanView.SPACING;
 
public class MenuView extends VBox {
    private final Label nbLivesLabel = new Label("");
    private final Label nbBombsLabel = new Label("");
    private final Label statusLabel = new Label("");
    private final Label nbKeysLabel = new Label("");
    private final MenuViewModel menuViewModel;

    MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        configMenu();
        configBinding();
    }

    private void configBinding() {
        nbLivesLabel.textProperty().bind(menuViewModel.nbLivesProperty().asString("Nombre de vies :  %d"));
        nbBombsLabel.textProperty().bind(menuViewModel.nbBombsProperty().asString("Nombre de bombes :  %d"));
        nbKeysLabel.textProperty().bind(menuViewModel.nbKeysProperty().asString("Nombre de clés restantes :  %d / " + menuViewModel.nbKeysProperty().get()));
        statusLabel.textProperty().bind(menuViewModel.gameStatusTxtProperty());
    }

    private void configMenu() {
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        this.setPadding(new Insets(SPACING, 0, SPACING, SPACING));
        setMargin(this, new Insets(0, 115, 10, 120));
        getChildren().addAll(nbLivesLabel, nbBombsLabel, nbKeysLabel, statusLabel);
    }
}
