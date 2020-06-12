package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public abstract class SceneView {
    private HBox hBox = new HBox();
    private BorderPane borderPane = new BorderPane();
    private GridPane gridPane = new GridPane();
    private Label label = new Label();


    public SceneView() {
        this.setGridPane();
    }

    private void sethBox() {
        hBox.getChildren().add(label);
    }

    public void setPageTitle(String title) {
        label.setText(title);
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    void setGridPane() {
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
    }

    public void setLayout() {
        BorderPane.setAlignment(hBox, Pos.CENTER);
        BorderPane.setMargin(hBox, new Insets(10.0D, 10.0D, 10.0D, 10.0D));
        borderPane.setTop(hBox);
        borderPane.setCenter(gridPane);
    }


}
