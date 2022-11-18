package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.BombermanView;

public class BombermanApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BombermanView bombermanView = new BombermanView(stage);
    }
}
