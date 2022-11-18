package view;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.GameElement;
import vm.CellViewModel;


public class CellView extends StackPane {
    private static final Image heroImage = new Image("heroImage.jpg");
    private static final Image bombImage = new Image("bombImage.jpg");
    private static final Image pillarImage = new Image("pillarImage.png");
    private static final Image wallImage = new Image("wallImage.png");
    private static final Image keyImage = new Image("keyImage.jpg");
    private static final Image grassImage = new Image("grassImage.png");
    private static final Image treasureImage = new Image("treasure.jpg");
    private static final Image mineImage = new Image("mineImage.jpg");
    private static final Image bonusLiveImage = new Image("bonusLive.jpg");
    private static final Image bonusBombImage = new Image("bonusBomb.jpg");

    private final ImageView imageView = new ImageView();
    private final ObjectProperty<GameElement> firstElement = new SimpleObjectProperty<>();
    private final IntegerProperty mineAboutToExplodeCounter = new SimpleIntegerProperty();

    public CellView(CellViewModel cellViewModel, DoubleBinding cellWidthProperty) {
        firstElement.bind(cellViewModel.firstElementProperty());
        mineAboutToExplodeCounter.bind(cellViewModel.mineAboutToExplodeCounterProperty());
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(cellWidthProperty);
        getChildren().add(imageView);

        setBomberManImage();

        firstElement.addListener((obj, old, newVal) -> {
            setBomberManImage();
        });

        mineAboutToExplodeCounter.addListener((obs, old, newVal) -> {
            if (newVal.intValue() == 2)
                setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (newVal.intValue() == 1)
                setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            else if (newVal.intValue() == 0)
                setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        });
    }

    private void setBomberManImage() {
        if (firstElement.get() != null) {
            if (firstElement.get().isHero())
                imageView.setImage(heroImage);
            else if (firstElement.get().isBomb())
                imageView.setImage(bombImage);
            else if (firstElement.get().isPillar())
                imageView.setImage(pillarImage);
            else if (firstElement.get().isKey())
                imageView.setImage(keyImage);
            else if (firstElement.get().isLiveBonus())
                imageView.setImage(bonusLiveImage);
            else if (firstElement.get().isBombBonus())
                imageView.setImage(bonusBombImage);
            else if (firstElement.get().isTreasure())
                imageView.setImage(treasureImage);
            else if (firstElement.get().isMine())
                imageView.setImage(mineImage);
            else if (firstElement.get().isWall())
                imageView.setImage(wallImage);
        } else {
            imageView.setImage(grassImage);
        }
    }
}
