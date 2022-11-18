package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vm.BombermanViewModel;


public class BombermanView extends VBox {
    private final BombermanViewModel bombermanViewModel = new BombermanViewModel();
    private final DoubleProperty gridWidthProperty = new SimpleDoubleProperty();

    private final Button playBtn = new Button("Play");
    private final Button configGameBtn = new Button("Config game");

    // Constantes de mise en page
    private static final int MENU_WIDTH = 160;
    private static final int SCENE_MIN_WIDTH = 820;
    private static final int SCENE_MIN_HEIGHT = 820;
    static final int SPACING = 20;

    // Composants principaux
    private GridView gridView;
    private MenuView menuView;
    private MenuConfigView menuConfigView;

    public BombermanView(Stage primaryStage) {
        start(primaryStage);
    }

    public void start(Stage stage) {
        configMenuConfig();
        configGridView();
        configPlayBtn();

        // Mise en place de la scène et affichage de la fenêtre
        Scene scene = new Scene(this, SCENE_MIN_WIDTH, SCENE_MIN_HEIGHT);
        stage.setTitle("BombermanApp");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
        this.requestFocus();
        this.setAlignment(Pos.CENTER);
        configHandlers();
        configBtnListener();
    }

    private void configMenuConfig() {
        menuConfigView = new MenuConfigView(bombermanViewModel.getMenuConfigViewModel());
        this.getChildren().add(0, menuConfigView);
    }

    private void configPlayBtn() {
        configComponent();
        configHandler();
        configBindingPlayBtn();
    }

    private void configBindingPlayBtn() {
        playBtn.disableProperty().bind(bombermanViewModel.isStartedProperty().or(bombermanViewModel.isValidInputsProperty()));
    }

    private void configBtnListener() {
        bombermanViewModel.isStartedProperty().addListener((obs, old, newVal) -> {
            if (!newVal) {
                removeStartBtn();
                addConfigGameBtn();
            }
        });
    }

    private void removeStartBtn() {
        getChildren().remove(playBtn);
    }

    private void addConfigGameBtn() {
        getChildren().add(configGameBtn);
    }

    private void configComponent() {
        getChildren().add(playBtn);
        playBtn.setMaxSize(80, 80);
    }

    private void configHandler() {
        playBtn.setOnAction(e -> {
            removeMenuConfigView();
            createMenuView();
            bombermanViewModel.play();
            this.requestFocus();
        });

        configGameBtn.setOnAction(e -> {
            removeMenuView();
            createMenuConfig();
            removeConfigGameBtn();
            bombermanViewModel.setGameData();
            addPlayBtn();
        });
    }

    private void createMenuConfig() {
        this.getChildren().add(0, menuConfigView);
    }

    private void addPlayBtn() {
        getChildren().add(playBtn);
    }

    private void removeConfigGameBtn() {
        getChildren().remove(configGameBtn);
    }

    private void createMenuView() {
        menuView = new MenuView(bombermanViewModel.getMenuViewModel());
        getChildren().add(0, menuView);
    }

    private void removeMenuConfigView() {
        getChildren().remove(menuConfigView);
    }

    private void removeMenuView() {
        getChildren().remove(menuView);
    }

    private void configHandlers() {
        setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.RIGHT)) {
                bombermanViewModel.moveRight();
            }
            if (e.getCode().equals(KeyCode.LEFT)) {
                bombermanViewModel.moveLeft();
            }
            if (e.getCode().equals(KeyCode.UP)) {
                bombermanViewModel.moveUp();
            }
            if (e.getCode().equals(KeyCode.DOWN)) {
                bombermanViewModel.moveDown();
            }
            if (e.getCode().equals(KeyCode.SPACE)) {
                bombermanViewModel.dropBomb();
            }
        });
    }

    private void configGridView() {
        gridView = new GridView(
                bombermanViewModel.getGridViewModel(),
                gridWidthProperty
        );
        this.getChildren().add(gridView);
        gridView.minHeightProperty().bind(gridWidthProperty);
        gridView.minWidthProperty().bind(gridWidthProperty);
        gridView.maxHeightProperty().bind(gridWidthProperty);
        gridView.maxWidthProperty().bind(gridWidthProperty);
        gridWidthProperty.bind(Bindings.min(widthProperty().subtract(MENU_WIDTH + 2 * SPACING), heightProperty()));
    }
}
