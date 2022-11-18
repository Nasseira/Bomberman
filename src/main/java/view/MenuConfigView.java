package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import vm.MenuConfigViewModel;

import static model.Game.*;
import static view.BombermanView.SPACING;

public class MenuConfigView extends VBox {
    private final Label nbLivesLabel = new Label("Nombre de vies : \t\t");
    private final TextField nbLivesTextField = new TextField(String.valueOf(NB_LIVES));
    private final Label nbBombsLabel = new Label("Nombre de bombes : \t");
    private final TextField nbBombsTextField = new TextField(String.valueOf(NB_BOMBS));
    private final Label nbKeysLabel = new Label("Nombre de clés : \t\t");
    private final TextField nbKeysTextField = new TextField(String.valueOf(NB_KEYS));

    private final MenuConfigViewModel menuConfigViewModel;
    private final HBox livesHBox = new HBox();
    private final HBox bombsHBox = new HBox();
    private final HBox keysHBox = new HBox();

    public MenuConfigView(MenuConfigViewModel menuConfigViewModel) {
        this.menuConfigViewModel = menuConfigViewModel;
        setMenuConfigVmData();
        configMenu();
        configBinding();
    }

    private void setMenuConfigVmData() {
        menuConfigViewModel.setNbLives(Integer.parseInt(nbLivesTextField.getText()));
        menuConfigViewModel.setNbBombs(Integer.parseInt(nbBombsTextField.getText()));
        menuConfigViewModel.setNbKeys(Integer.parseInt(nbKeysTextField.getText()));
    }

    private void configMenu() {
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        this.setPadding(new Insets(SPACING, 0, SPACING, SPACING));
        setMargin(this, new Insets(0, 115, 10, 120));
        livesHBox.getChildren().addAll(nbLivesLabel, nbLivesTextField);
        bombsHBox.getChildren().addAll(nbBombsLabel, nbBombsTextField);
        keysHBox.getChildren().addAll(nbKeysLabel, nbKeysTextField);
        this.getChildren().addAll(livesHBox, bombsHBox, keysHBox);
    }

    private void checkInput(TextField txField) {
        txField.addEventFilter(KeyEvent.KEY_TYPED, ev -> {
            if (!("1234567890".contains(ev.getCharacter())))
                ev.consume();
        });
    }

    private void configBinding() {
        checkInput(nbBombsTextField);
        nbBombsTextField.textProperty().addListener((e, old, newVal) -> {
            if (!newVal.isEmpty()) {
                menuConfigViewModel.setNbBombs(Integer.parseInt(newVal));
            }
        });

        checkInput(nbLivesTextField);
        nbLivesTextField.textProperty().addListener((e, old, newVal) -> {
            if (!newVal.isEmpty()) {
                menuConfigViewModel.setNbLives(Integer.parseInt(newVal));
            }
        });

        checkInput(nbKeysTextField);
        nbKeysTextField.textProperty().addListener((e, old, newVal) -> {
            if (!newVal.isEmpty()) {
                menuConfigViewModel.setNbKeys(Integer.parseInt(newVal));
            }
        });
    }
}
